package tvi.webrtc;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import tvi.webrtc.EglBase;
import tvi.webrtc.EncodedImage;
import tvi.webrtc.SurfaceTextureHelper;
import tvi.webrtc.ThreadUtils;
import tvi.webrtc.VideoDecoder;
import tvi.webrtc.VideoFrame;

class HardwareVideoDecoder implements VideoDecoder, SurfaceTextureHelper.OnTextureFrameAvailableListener {
    private static final int DEQUEUE_INPUT_TIMEOUT_US = 500000;
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String MEDIA_FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String MEDIA_FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String MEDIA_FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String MEDIA_FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String MEDIA_FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String MEDIA_FORMAT_KEY_STRIDE = "stride";
    private static final String TAG = "HardwareVideoDecoder";
    @Nullable
    private VideoDecoder.Callback callback;
    @Nullable
    private MediaCodec codec = null;
    private final String codecName;
    private final VideoCodecType codecType;
    private int colorFormat;
    private ThreadUtils.ThreadChecker decoderThreadChecker;
    private final Object dimensionLock = new Object();
    private final BlockingDeque<FrameInfo> frameInfos;
    private boolean hasDecodedFirstFrame;
    private int height;
    private boolean keyFrameRequired;
    @Nullable
    private Thread outputThread;
    /* access modifiers changed from: private */
    public ThreadUtils.ThreadChecker outputThreadChecker;
    @Nullable
    private DecodedTextureMetadata renderedTextureMetadata;
    private final Object renderedTextureMetadataLock = new Object();
    /* access modifiers changed from: private */
    public volatile boolean running = false;
    private final EglBase.Context sharedContext;
    @Nullable
    private volatile Exception shutdownException = null;
    private int sliceHeight;
    private int stride;
    @Nullable
    private Surface surface = null;
    @Nullable
    private SurfaceTextureHelper surfaceTextureHelper;
    private int width;

    public String getImplementationName() {
        return "HWDecoder";
    }

    public boolean getPrefersLateDecoding() {
        return true;
    }

    private static class FrameInfo {
        final long decodeStartTimeMs;
        final int rotation;

        FrameInfo(long j, int i) {
            this.decodeStartTimeMs = j;
            this.rotation = i;
        }
    }

    private static class DecodedTextureMetadata {
        final Integer decodeTimeMs;
        final int height;
        final long presentationTimestampUs;
        final int rotation;
        final int width;

        DecodedTextureMetadata(int i, int i2, int i3, long j, Integer num) {
            this.width = i;
            this.height = i2;
            this.rotation = i3;
            this.presentationTimestampUs = j;
            this.decodeTimeMs = num;
        }
    }

    HardwareVideoDecoder(String str, VideoCodecType videoCodecType, int i, EglBase.Context context) {
        if (isSupportedColorFormat(i)) {
            this.codecName = str;
            this.codecType = videoCodecType;
            this.colorFormat = i;
            this.sharedContext = context;
            this.frameInfos = new LinkedBlockingDeque();
            return;
        }
        throw new IllegalArgumentException("Unsupported color format: " + i);
    }

    public VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback2) {
        this.decoderThreadChecker = new ThreadUtils.ThreadChecker();
        this.callback = callback2;
        EglBase.Context context = this.sharedContext;
        if (context != null) {
            this.surfaceTextureHelper = SurfaceTextureHelper.create("decoder-texture-thread", context);
            this.surface = new Surface(this.surfaceTextureHelper.getSurfaceTexture());
            this.surfaceTextureHelper.startListening(this);
        }
        return initDecodeInternal(settings.width, settings.height);
    }

    private VideoCodecStatus initDecodeInternal(int i, int i2) {
        this.decoderThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initDecodeInternal");
        if (this.outputThread != null) {
            Logging.e(TAG, "initDecodeInternal called while the codec is already running");
            return VideoCodecStatus.FALLBACK_SOFTWARE;
        }
        this.width = i;
        this.height = i2;
        this.stride = i;
        this.sliceHeight = i2;
        this.hasDecodedFirstFrame = false;
        this.keyFrameRequired = true;
        try {
            this.codec = MediaCodec.createByCodecName(this.codecName);
            try {
                MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.codecType.mimeType(), i, i2);
                if (this.sharedContext == null) {
                    createVideoFormat.setInteger("color-format", this.colorFormat);
                }
                this.codec.configure(createVideoFormat, this.surface, (MediaCrypto) null, 0);
                this.codec.start();
                this.running = true;
                Thread createOutputThread = createOutputThread();
                this.outputThread = createOutputThread;
                createOutputThread.start();
                Logging.d(TAG, "initDecodeInternal done");
                return VideoCodecStatus.OK;
            } catch (IllegalStateException e) {
                Logging.e(TAG, "initDecode failed", e);
                release();
                return VideoCodecStatus.FALLBACK_SOFTWARE;
            }
        } catch (IOException | IllegalArgumentException unused) {
            Logging.e(TAG, "Cannot create media decoder " + this.codecName);
            return VideoCodecStatus.FALLBACK_SOFTWARE;
        }
    }

    public VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        int i;
        int i2;
        VideoCodecStatus reinitDecode;
        this.decoderThreadChecker.checkIsOnValidThread();
        if (this.codec == null || this.callback == null) {
            Logging.d(TAG, "decode uninitalized, codec: " + this.codec + ", callback: " + this.callback);
            return VideoCodecStatus.UNINITIALIZED;
        } else if (encodedImage.buffer == null) {
            Logging.e(TAG, "decode() - no input data");
            return VideoCodecStatus.ERR_PARAMETER;
        } else {
            int remaining = encodedImage.buffer.remaining();
            if (remaining == 0) {
                Logging.e(TAG, "decode() - input buffer empty");
                return VideoCodecStatus.ERR_PARAMETER;
            }
            synchronized (this.dimensionLock) {
                i = this.width;
                i2 = this.height;
            }
            if (encodedImage.encodedWidth * encodedImage.encodedHeight > 0 && ((encodedImage.encodedWidth != i || encodedImage.encodedHeight != i2) && (reinitDecode = reinitDecode(encodedImage.encodedWidth, encodedImage.encodedHeight)) != VideoCodecStatus.OK)) {
                return reinitDecode;
            }
            if (this.keyFrameRequired) {
                if (encodedImage.frameType != EncodedImage.FrameType.VideoFrameKey) {
                    Logging.e(TAG, "decode() - key frame required first");
                    return VideoCodecStatus.NO_OUTPUT;
                } else if (!encodedImage.completeFrame) {
                    Logging.e(TAG, "decode() - complete frame required first");
                    return VideoCodecStatus.NO_OUTPUT;
                }
            }
            try {
                int dequeueInputBuffer = this.codec.dequeueInputBuffer(500000);
                if (dequeueInputBuffer < 0) {
                    Logging.e(TAG, "decode() - no HW buffers available; decoder falling behind");
                    return VideoCodecStatus.ERROR;
                }
                try {
                    ByteBuffer byteBuffer = this.codec.getInputBuffers()[dequeueInputBuffer];
                    if (byteBuffer.capacity() < remaining) {
                        Logging.e(TAG, "decode() - HW buffer too small");
                        return VideoCodecStatus.ERROR;
                    }
                    byteBuffer.put(encodedImage.buffer);
                    this.frameInfos.offer(new FrameInfo(SystemClock.elapsedRealtime(), encodedImage.rotation));
                    try {
                        this.codec.queueInputBuffer(dequeueInputBuffer, 0, remaining, TimeUnit.NANOSECONDS.toMicros(encodedImage.captureTimeNs), 0);
                        if (this.keyFrameRequired) {
                            this.keyFrameRequired = false;
                        }
                        return VideoCodecStatus.OK;
                    } catch (IllegalStateException e) {
                        Logging.e(TAG, "queueInputBuffer failed", e);
                        this.frameInfos.pollLast();
                        return VideoCodecStatus.ERROR;
                    }
                } catch (IllegalStateException e2) {
                    Logging.e(TAG, "getInputBuffers failed", e2);
                    return VideoCodecStatus.ERROR;
                }
            } catch (IllegalStateException e3) {
                Logging.e(TAG, "dequeueInputBuffer failed", e3);
                return VideoCodecStatus.ERROR;
            }
        }
    }

    public VideoCodecStatus release() {
        Logging.d(TAG, "release");
        VideoCodecStatus releaseInternal = releaseInternal();
        Surface surface2 = this.surface;
        if (surface2 != null) {
            surface2.release();
            this.surface = null;
            this.surfaceTextureHelper.stopListening();
            this.surfaceTextureHelper.dispose();
            this.surfaceTextureHelper = null;
        }
        synchronized (this.renderedTextureMetadataLock) {
            this.renderedTextureMetadata = null;
        }
        this.callback = null;
        this.frameInfos.clear();
        return releaseInternal;
    }

    private VideoCodecStatus releaseInternal() {
        if (!this.running) {
            Logging.d(TAG, "release: Decoder is not running.");
            return VideoCodecStatus.OK;
        }
        try {
            this.running = false;
            if (!ThreadUtils.joinUninterruptibly(this.outputThread, 5000)) {
                Logging.e(TAG, "Media decoder release timeout", new RuntimeException());
                return VideoCodecStatus.TIMEOUT;
            } else if (this.shutdownException != null) {
                Logging.e(TAG, "Media decoder release error", new RuntimeException(this.shutdownException));
                this.shutdownException = null;
                VideoCodecStatus videoCodecStatus = VideoCodecStatus.ERROR;
                this.codec = null;
                this.outputThread = null;
                return videoCodecStatus;
            } else {
                this.codec = null;
                this.outputThread = null;
                return VideoCodecStatus.OK;
            }
        } finally {
            this.codec = null;
            this.outputThread = null;
        }
    }

    private VideoCodecStatus reinitDecode(int i, int i2) {
        this.decoderThreadChecker.checkIsOnValidThread();
        VideoCodecStatus releaseInternal = releaseInternal();
        if (releaseInternal != VideoCodecStatus.OK) {
            return releaseInternal;
        }
        return initDecodeInternal(i, i2);
    }

    private Thread createOutputThread() {
        return new Thread("HardwareVideoDecoder.outputThread") {
            public void run() {
                ThreadUtils.ThreadChecker unused = HardwareVideoDecoder.this.outputThreadChecker = new ThreadUtils.ThreadChecker();
                while (HardwareVideoDecoder.this.running) {
                    HardwareVideoDecoder.this.deliverDecodedFrame();
                }
                HardwareVideoDecoder.this.releaseCodecOnOutputThread();
            }
        };
    }

    /* access modifiers changed from: private */
    public void deliverDecodedFrame() {
        this.outputThreadChecker.checkIsOnValidThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int dequeueOutputBuffer = this.codec.dequeueOutputBuffer(bufferInfo, 100000);
            if (dequeueOutputBuffer == -2) {
                reformat(this.codec.getOutputFormat());
            } else if (dequeueOutputBuffer < 0) {
                Logging.v(TAG, "dequeueOutputBuffer returned " + dequeueOutputBuffer);
            } else {
                FrameInfo poll = this.frameInfos.poll();
                Integer num = null;
                int i = 0;
                if (poll != null) {
                    num = Integer.valueOf((int) (SystemClock.elapsedRealtime() - poll.decodeStartTimeMs));
                    i = poll.rotation;
                }
                this.hasDecodedFirstFrame = true;
                if (this.surfaceTextureHelper != null) {
                    deliverTextureFrame(dequeueOutputBuffer, bufferInfo, i, num);
                } else {
                    deliverByteFrame(dequeueOutputBuffer, bufferInfo, i, num);
                }
            }
        } catch (IllegalStateException e) {
            Logging.e(TAG, "deliverDecodedFrame failed", e);
        }
    }

    private void deliverTextureFrame(int i, MediaCodec.BufferInfo bufferInfo, int i2, Integer num) {
        int i3;
        int i4;
        synchronized (this.dimensionLock) {
            i3 = this.width;
            i4 = this.height;
        }
        synchronized (this.renderedTextureMetadataLock) {
            if (this.renderedTextureMetadata == null) {
                this.renderedTextureMetadata = new DecodedTextureMetadata(i3, i4, i2, bufferInfo.presentationTimeUs, num);
                this.codec.releaseOutputBuffer(i, true);
            }
        }
    }

    public void onTextureFrameAvailable(int i, float[] fArr, long j) {
        VideoFrame videoFrame;
        int intValue;
        synchronized (this.renderedTextureMetadataLock) {
            DecodedTextureMetadata decodedTextureMetadata = this.renderedTextureMetadata;
            if (decodedTextureMetadata != null) {
                videoFrame = new VideoFrame(this.surfaceTextureHelper.createTextureBuffer(decodedTextureMetadata.width, this.renderedTextureMetadata.height, RendererCommon.convertMatrixToAndroidGraphicsMatrix(fArr)), this.renderedTextureMetadata.rotation, this.renderedTextureMetadata.presentationTimestampUs * 1000);
                intValue = this.renderedTextureMetadata.decodeTimeMs.intValue();
                this.renderedTextureMetadata = null;
            } else {
                throw new IllegalStateException("Rendered texture metadata was null in onTextureFrameAvailable.");
            }
        }
        this.callback.onDecodedFrame(videoFrame, Integer.valueOf(intValue), (Integer) null);
        videoFrame.release();
    }

    private void deliverByteFrame(int i, MediaCodec.BufferInfo bufferInfo, int i2, Integer num) {
        int i3;
        int i4;
        int i5;
        int i6;
        VideoFrame.Buffer buffer;
        synchronized (this.dimensionLock) {
            i3 = this.width;
            i4 = this.height;
            i5 = this.stride;
            i6 = this.sliceHeight;
        }
        if (bufferInfo.size < ((i3 * i4) * 3) / 2) {
            Logging.e(TAG, "Insufficient output buffer size: " + bufferInfo.size);
            return;
        }
        int i7 = (bufferInfo.size >= ((i5 * i4) * 3) / 2 || i6 != i4 || i5 <= i3) ? i5 : (bufferInfo.size * 2) / (i4 * 3);
        ByteBuffer byteBuffer = this.codec.getOutputBuffers()[i];
        byteBuffer.position(bufferInfo.offset);
        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
        ByteBuffer slice = byteBuffer.slice();
        if (this.colorFormat == 19) {
            buffer = copyI420Buffer(slice, i7, i6, i3, i4);
        } else {
            buffer = copyNV12ToI420Buffer(slice, i7, i6, i3, i4);
        }
        this.codec.releaseOutputBuffer(i, false);
        VideoFrame videoFrame = new VideoFrame(buffer, i2, bufferInfo.presentationTimeUs * 1000);
        this.callback.onDecodedFrame(videoFrame, num, (Integer) null);
        videoFrame.release();
    }

    private VideoFrame.Buffer copyNV12ToI420Buffer(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        return new NV12Buffer(i3, i4, i, i2, byteBuffer, (Runnable) null).toI420();
    }

    private VideoFrame.Buffer copyI420Buffer(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        int i5 = i / 2;
        int i6 = (i * i2) + 0;
        int i7 = (i2 / 2) * i5;
        int i8 = i6 + i7;
        int i9 = ((i5 * i2) / 2) + i6;
        int i10 = i7 + i9;
        JavaI420Buffer allocate = JavaI420Buffer.allocate(i3, i4);
        ByteBuffer dataY = allocate.getDataY();
        byteBuffer.position(0);
        byteBuffer.limit(i6);
        dataY.put(byteBuffer);
        ByteBuffer dataU = allocate.getDataU();
        byteBuffer.position(i6);
        byteBuffer.limit(i8);
        dataU.put(byteBuffer);
        int i11 = i2 % 2;
        if (i11 != 0) {
            byteBuffer.position(i8 - i5);
            dataU.put(byteBuffer);
        }
        ByteBuffer dataV = allocate.getDataV();
        byteBuffer.position(i9);
        byteBuffer.limit(i10);
        dataV.put(byteBuffer);
        if (i11 != 0) {
            byteBuffer.position(i10 - i5);
            dataV.put(byteBuffer);
        }
        return allocate;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b7, code lost:
        if (r5.surfaceTextureHelper != null) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00bf, code lost:
        if (r6.containsKey("color-format") == false) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c1, code lost:
        r5.colorFormat = r6.getInteger("color-format");
        tvi.webrtc.Logging.d(TAG, "Color: 0x" + java.lang.Integer.toHexString(r5.colorFormat));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00eb, code lost:
        if (isSupportedColorFormat(r5.colorFormat) != false) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ed, code lost:
        stopOnOutputThread(new java.lang.IllegalStateException("Unsupported color format: " + r5.colorFormat));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0108, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0109, code lost:
        r0 = r5.dimensionLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x010b, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0112, code lost:
        if (r6.containsKey(MEDIA_FORMAT_KEY_STRIDE) == false) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0114, code lost:
        r5.stride = r6.getInteger(MEDIA_FORMAT_KEY_STRIDE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0122, code lost:
        if (r6.containsKey(MEDIA_FORMAT_KEY_SLICE_HEIGHT) == false) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0124, code lost:
        r5.sliceHeight = r6.getInteger(MEDIA_FORMAT_KEY_SLICE_HEIGHT);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x012c, code lost:
        tvi.webrtc.Logging.d(TAG, "Frame stride and slice height: " + r5.stride + " x " + r5.sliceHeight);
        r5.stride = java.lang.Math.max(r5.width, r5.stride);
        r5.sliceHeight = java.lang.Math.max(r5.height, r5.sliceHeight);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0162, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0163, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void reformat(android.media.MediaFormat r6) {
        /*
            r5 = this;
            tvi.webrtc.ThreadUtils$ThreadChecker r0 = r5.outputThreadChecker
            r0.checkIsOnValidThread()
            java.lang.String r0 = "HardwareVideoDecoder"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Decoder format changed: "
            r1.append(r2)
            java.lang.String r2 = r6.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            tvi.webrtc.Logging.d(r0, r1)
            java.lang.String r0 = "crop-left"
            boolean r0 = r6.containsKey(r0)
            if (r0 == 0) goto L_0x005e
            java.lang.String r0 = "crop-right"
            boolean r0 = r6.containsKey(r0)
            if (r0 == 0) goto L_0x005e
            java.lang.String r0 = "crop-bottom"
            boolean r0 = r6.containsKey(r0)
            if (r0 == 0) goto L_0x005e
            java.lang.String r0 = "crop-top"
            boolean r0 = r6.containsKey(r0)
            if (r0 == 0) goto L_0x005e
            java.lang.String r0 = "crop-right"
            int r0 = r6.getInteger(r0)
            int r0 = r0 + 1
            java.lang.String r1 = "crop-left"
            int r1 = r6.getInteger(r1)
            int r0 = r0 - r1
            java.lang.String r1 = "crop-bottom"
            int r1 = r6.getInteger(r1)
            int r1 = r1 + 1
            java.lang.String r2 = "crop-top"
            int r2 = r6.getInteger(r2)
            int r1 = r1 - r2
            goto L_0x006a
        L_0x005e:
            java.lang.String r0 = "width"
            int r0 = r6.getInteger(r0)
            java.lang.String r1 = "height"
            int r1 = r6.getInteger(r1)
        L_0x006a:
            java.lang.Object r2 = r5.dimensionLock
            monitor-enter(r2)
            boolean r3 = r5.hasDecodedFirstFrame     // Catch:{ all -> 0x0167 }
            if (r3 == 0) goto L_0x00b0
            int r3 = r5.width     // Catch:{ all -> 0x0167 }
            if (r3 != r0) goto L_0x0079
            int r3 = r5.height     // Catch:{ all -> 0x0167 }
            if (r3 == r1) goto L_0x00b0
        L_0x0079:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ all -> 0x0167 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0167 }
            r3.<init>()     // Catch:{ all -> 0x0167 }
            java.lang.String r4 = "Unexpected size change. Configured "
            r3.append(r4)     // Catch:{ all -> 0x0167 }
            int r4 = r5.width     // Catch:{ all -> 0x0167 }
            r3.append(r4)     // Catch:{ all -> 0x0167 }
            java.lang.String r4 = "*"
            r3.append(r4)     // Catch:{ all -> 0x0167 }
            int r4 = r5.height     // Catch:{ all -> 0x0167 }
            r3.append(r4)     // Catch:{ all -> 0x0167 }
            java.lang.String r4 = ". New "
            r3.append(r4)     // Catch:{ all -> 0x0167 }
            r3.append(r0)     // Catch:{ all -> 0x0167 }
            java.lang.String r0 = "*"
            r3.append(r0)     // Catch:{ all -> 0x0167 }
            r3.append(r1)     // Catch:{ all -> 0x0167 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0167 }
            r6.<init>(r0)     // Catch:{ all -> 0x0167 }
            r5.stopOnOutputThread(r6)     // Catch:{ all -> 0x0167 }
            monitor-exit(r2)     // Catch:{ all -> 0x0167 }
            return
        L_0x00b0:
            r5.width = r0     // Catch:{ all -> 0x0167 }
            r5.height = r1     // Catch:{ all -> 0x0167 }
            monitor-exit(r2)     // Catch:{ all -> 0x0167 }
            tvi.webrtc.SurfaceTextureHelper r0 = r5.surfaceTextureHelper
            if (r0 != 0) goto L_0x0109
            java.lang.String r0 = "color-format"
            boolean r0 = r6.containsKey(r0)
            if (r0 == 0) goto L_0x0109
            java.lang.String r0 = "color-format"
            int r0 = r6.getInteger(r0)
            r5.colorFormat = r0
            java.lang.String r0 = "HardwareVideoDecoder"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Color: 0x"
            r1.append(r2)
            int r2 = r5.colorFormat
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            tvi.webrtc.Logging.d(r0, r1)
            int r0 = r5.colorFormat
            boolean r0 = r5.isSupportedColorFormat(r0)
            if (r0 != 0) goto L_0x0109
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unsupported color format: "
            r0.append(r1)
            int r1 = r5.colorFormat
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            r5.stopOnOutputThread(r6)
            return
        L_0x0109:
            java.lang.Object r0 = r5.dimensionLock
            monitor-enter(r0)
            java.lang.String r1 = "stride"
            boolean r1 = r6.containsKey(r1)     // Catch:{ all -> 0x0164 }
            if (r1 == 0) goto L_0x011c
            java.lang.String r1 = "stride"
            int r1 = r6.getInteger(r1)     // Catch:{ all -> 0x0164 }
            r5.stride = r1     // Catch:{ all -> 0x0164 }
        L_0x011c:
            java.lang.String r1 = "slice-height"
            boolean r1 = r6.containsKey(r1)     // Catch:{ all -> 0x0164 }
            if (r1 == 0) goto L_0x012c
            java.lang.String r1 = "slice-height"
            int r6 = r6.getInteger(r1)     // Catch:{ all -> 0x0164 }
            r5.sliceHeight = r6     // Catch:{ all -> 0x0164 }
        L_0x012c:
            java.lang.String r6 = "HardwareVideoDecoder"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0164 }
            r1.<init>()     // Catch:{ all -> 0x0164 }
            java.lang.String r2 = "Frame stride and slice height: "
            r1.append(r2)     // Catch:{ all -> 0x0164 }
            int r2 = r5.stride     // Catch:{ all -> 0x0164 }
            r1.append(r2)     // Catch:{ all -> 0x0164 }
            java.lang.String r2 = " x "
            r1.append(r2)     // Catch:{ all -> 0x0164 }
            int r2 = r5.sliceHeight     // Catch:{ all -> 0x0164 }
            r1.append(r2)     // Catch:{ all -> 0x0164 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0164 }
            tvi.webrtc.Logging.d(r6, r1)     // Catch:{ all -> 0x0164 }
            int r6 = r5.width     // Catch:{ all -> 0x0164 }
            int r1 = r5.stride     // Catch:{ all -> 0x0164 }
            int r6 = java.lang.Math.max(r6, r1)     // Catch:{ all -> 0x0164 }
            r5.stride = r6     // Catch:{ all -> 0x0164 }
            int r6 = r5.height     // Catch:{ all -> 0x0164 }
            int r1 = r5.sliceHeight     // Catch:{ all -> 0x0164 }
            int r6 = java.lang.Math.max(r6, r1)     // Catch:{ all -> 0x0164 }
            r5.sliceHeight = r6     // Catch:{ all -> 0x0164 }
            monitor-exit(r0)     // Catch:{ all -> 0x0164 }
            return
        L_0x0164:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0164 }
            throw r6
        L_0x0167:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0167 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: tvi.webrtc.HardwareVideoDecoder.reformat(android.media.MediaFormat):void");
    }

    /* access modifiers changed from: private */
    public void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        try {
            this.codec.stop();
        } catch (Exception e) {
            Logging.e(TAG, "Media decoder stop failed", e);
        }
        try {
            this.codec.release();
        } catch (Exception e2) {
            Logging.e(TAG, "Media decoder release failed", e2);
            this.shutdownException = e2;
        }
        Logging.d(TAG, "Release on output thread done");
    }

    private void stopOnOutputThread(Exception exc) {
        this.outputThreadChecker.checkIsOnValidThread();
        this.running = false;
        this.shutdownException = exc;
    }

    private boolean isSupportedColorFormat(int i) {
        for (int i2 : MediaCodecUtils.DECODER_COLOR_FORMATS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
