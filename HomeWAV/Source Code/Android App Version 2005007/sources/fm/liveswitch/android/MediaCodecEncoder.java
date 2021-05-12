package fm.liveswitch.android;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.IActionDelegate1;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedCondition;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoEncoder;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import java.nio.ByteBuffer;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class MediaCodecEncoder extends VideoEncoder {
    private static final int _infinite = -1;
    private int _colorFormat;
    private ManagedCondition _condition;
    private int _currentBitrate;
    private int _currentHeight;
    /* access modifiers changed from: private */
    public long _currentSynchronizationSource;
    private int _currentWidth;
    private MediaCodec _encoder;
    private MediaCodecInfo _encoderInfo;
    private volatile boolean _encoding = false;
    private boolean _forceKeyFrame;
    private DataBuffer _lastPpsBuffer = null;
    private DataBuffer _lastSpsBuffer = null;
    private String _mimeType;
    private MediaCodec.BufferInfo _outputBufferInfo = new MediaCodec.BufferInfo();
    private long _startNanos = 0;
    private Object _stateLock = new Object();
    private ManagedThread _thread;

    public String getLabel() {
        return "MediaCodecEncoder";
    }

    public int getMaxCodecBitrate() {
        return 100000;
    }

    public int getMinCodecBitrate() {
        return 100;
    }

    public boolean getForceKeyFrame() {
        return this._forceKeyFrame;
    }

    public void setForceKeyFrame(boolean z) {
        this._forceKeyFrame = z;
    }

    public MediaCodecEncoder(MediaCodecInfo mediaCodecInfo, VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
        initialize(mediaCodecInfo, videoFormat, videoFormat2);
    }

    public MediaCodecEncoder(MediaCodecInfo mediaCodecInfo, IVideoOutput iVideoOutput, VideoFormat videoFormat) {
        super((VideoFormat) iVideoOutput.getOutputFormat(), videoFormat);
        initialize(mediaCodecInfo, (VideoFormat) iVideoOutput.getOutputFormat(), videoFormat);
    }

    private void initialize(MediaCodecInfo mediaCodecInfo, VideoFormat videoFormat, VideoFormat videoFormat2) {
        if (Build.VERSION.SDK_INT >= 19) {
            setBitrate(768);
            this._encoderInfo = mediaCodecInfo;
            if (mediaCodecInfo.isEncoder()) {
                String mimeType = MediaCodecUtility.getMimeType(videoFormat2);
                this._mimeType = mimeType;
                if (mimeType == null) {
                    throw new RuntimeException("Invalid input format " + videoFormat2.getName());
                } else if (MediaCodecUtility.hasMimeType(this._encoderInfo, mimeType)) {
                    int colorFormat = MediaCodecUtility.getColorFormat(videoFormat);
                    this._colorFormat = colorFormat;
                    if (colorFormat < 0 || !MediaCodecUtility.hasColorFormat(this._encoderInfo, this._mimeType, colorFormat)) {
                        throw new RuntimeException("Invalid output format " + videoFormat.getName());
                    }
                } else {
                    throw new RuntimeException("Invalid input format " + videoFormat.getName() + " and respective mime type " + this._mimeType + " for this encoderInfo");
                }
            } else {
                throw new RuntimeException("encoderInfo is not a valid encoder");
            }
        } else {
            throw new RuntimeException("Android 4.4 or higher is required to use hardware encoding.");
        }
    }

    public void doDestroy() {
        synchronized (this._stateLock) {
            this._encoding = false;
            this._condition.pulse();
        }
        MediaCodec mediaCodec = this._encoder;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this._encoder.release();
            this._encoder = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        this._currentSynchronizationSource = videoFrame.getSynchronizationSource();
        encode(videoBuffer, (VideoFormat) getOutputFormat());
    }

    private void initializeMediaCodec(int i, int i2) throws Exception {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this._mimeType, i, i2);
        this._encoder = MediaCodec.createByCodecName(this._encoderInfo.getName());
        int bitrate = getBitrate();
        createVideoFormat.setInteger(FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE, bitrate * 1000);
        createVideoFormat.setInteger("color-format", this._colorFormat);
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("i-frame-interval", 1800);
        createVideoFormat.setInteger("stride", i);
        createVideoFormat.setInteger("slice-height", i2);
        this._currentWidth = i;
        this._currentHeight = i2;
        this._currentBitrate = bitrate;
        this._encoder.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this._encoder.start();
        synchronized (this._stateLock) {
            this._encoding = true;
        }
        this._condition = new ManagedCondition();
        ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
            public String getId() {
                return "fm.liveswitch.android.MediaCodecEncoder.loop";
            }

            public void invoke(ManagedThread managedThread) {
                MediaCodecEncoder.this.loop(managedThread);
            }
        });
        this._thread = managedThread;
        managedThread.start();
    }

    public void encode(VideoBuffer videoBuffer, VideoFormat videoFormat) {
        try {
            int bitrate = getBitrate();
            if (!(this._encoder == null || (videoBuffer.getWidth() == this._currentWidth && videoBuffer.getHeight() == this._currentHeight && bitrate == this._currentBitrate))) {
                this._encoder.stop();
                this._encoder.release();
                this._encoder = null;
            }
            this._currentWidth = videoBuffer.getWidth();
            int height = videoBuffer.getHeight();
            this._currentHeight = height;
            this._currentBitrate = bitrate;
            if (this._encoder == null) {
                initializeMediaCodec(this._currentWidth, height);
                this._forceKeyFrame = true;
            }
            if (this._forceKeyFrame) {
                Bundle bundle = new Bundle();
                bundle.putInt("request-sync", 0);
                this._encoder.setParameters(bundle);
            }
            queueFrame(videoBuffer.getDataBuffers());
        } catch (Exception e) {
            MediaCodec mediaCodec = this._encoder;
            if (mediaCodec != null) {
                mediaCodec.stop();
                this._encoder.release();
                this._encoder = null;
            }
            Log.error("MediaCodec encode failed for " + this._mimeType + " \n" + e.getMessage());
        }
    }

    private void queueFrame(DataBuffer[] dataBufferArr) {
        ByteBuffer byteBuffer;
        int i = -1;
        while (i < 0) {
            i = this._encoder.dequeueInputBuffer(-1);
        }
        if (Build.VERSION.SDK_INT < 21) {
            byteBuffer = this._encoder.getInputBuffers()[i];
        } else {
            byteBuffer = this._encoder.getInputBuffer(i);
        }
        int i2 = 0;
        for (DataBuffer length : dataBufferArr) {
            i2 += length.getLength();
        }
        byteBuffer.position(0);
        byteBuffer.limit(i2);
        for (DataBuffer array : dataBufferArr) {
            byteBuffer.put(array.toArray());
        }
        byteBuffer.position(0);
        long j = 0;
        if (this._startNanos == 0) {
            this._startNanos = System.nanoTime();
        } else {
            j = (((System.nanoTime() - this._startNanos) / 1000) / 33333) * 33333;
        }
        this._encoder.queueInputBuffer(i, 0, i2, j, 0);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00b2, code lost:
        if ((r9._outputBufferInfo.flags & 1) > 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loop(fm.liveswitch.ManagedThread r10) {
        /*
            r9 = this;
        L_0x0000:
            boolean r0 = r9._encoding
            if (r0 == 0) goto L_0x0151
            r10.loopBegin()
            android.media.MediaCodec r0 = r9._encoder
            android.media.MediaCodec$BufferInfo r1 = r9._outputBufferInfo
            r2 = 0
            int r0 = r0.dequeueOutputBuffer(r1, r2)
            r1 = 1
            if (r0 < 0) goto L_0x013c
            java.lang.Object r2 = r9._stateLock
            monitor-enter(r2)
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0139 }
            r4 = 21
            if (r3 >= r4) goto L_0x0026
            android.media.MediaCodec r3 = r9._encoder     // Catch:{ all -> 0x0139 }
            java.nio.ByteBuffer[] r3 = r3.getOutputBuffers()     // Catch:{ all -> 0x0139 }
            r3 = r3[r0]     // Catch:{ all -> 0x0139 }
            goto L_0x002c
        L_0x0026:
            android.media.MediaCodec r3 = r9._encoder     // Catch:{ all -> 0x0139 }
            java.nio.ByteBuffer r3 = r3.getOutputBuffer(r0)     // Catch:{ all -> 0x0139 }
        L_0x002c:
            android.media.MediaCodec$BufferInfo r5 = r9._outputBufferInfo     // Catch:{ all -> 0x0139 }
            int r5 = r5.offset     // Catch:{ all -> 0x0139 }
            r3.position(r5)     // Catch:{ all -> 0x0139 }
            android.media.MediaCodec$BufferInfo r5 = r9._outputBufferInfo     // Catch:{ all -> 0x0139 }
            int r5 = r5.offset     // Catch:{ all -> 0x0139 }
            android.media.MediaCodec$BufferInfo r6 = r9._outputBufferInfo     // Catch:{ all -> 0x0139 }
            int r6 = r6.size     // Catch:{ all -> 0x0139 }
            int r5 = r5 + r6
            r3.limit(r5)     // Catch:{ all -> 0x0139 }
            android.media.MediaCodec$BufferInfo r5 = r9._outputBufferInfo     // Catch:{ all -> 0x0139 }
            int r5 = r5.size     // Catch:{ all -> 0x0139 }
            fm.liveswitch.DataBuffer r5 = fm.liveswitch.DataBuffer.allocate(r5)     // Catch:{ all -> 0x0139 }
            byte[] r6 = r5.getData()     // Catch:{ all -> 0x0139 }
            r3.get(r6)     // Catch:{ all -> 0x0139 }
            android.media.MediaCodec r3 = r9._encoder     // Catch:{ all -> 0x0139 }
            r6 = 0
            r3.releaseOutputBuffer(r0, r6)     // Catch:{ all -> 0x0139 }
            fm.liveswitch.MediaFormat r0 = r9.getOutputFormat()     // Catch:{ all -> 0x0139 }
            fm.liveswitch.VideoFormat r0 = (fm.liveswitch.VideoFormat) r0     // Catch:{ all -> 0x0139 }
            java.lang.String r0 = r0.getName()     // Catch:{ all -> 0x0139 }
            java.lang.String r3 = fm.liveswitch.VideoFormat.getH264Name()     // Catch:{ all -> 0x0139 }
            boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x0139 }
            fm.liveswitch.MediaFormat r3 = r9.getOutputFormat()     // Catch:{ all -> 0x0139 }
            fm.liveswitch.VideoFormat r3 = (fm.liveswitch.VideoFormat) r3     // Catch:{ all -> 0x0139 }
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x0139 }
            java.lang.String r7 = fm.liveswitch.VideoFormat.getVp8Name()     // Catch:{ all -> 0x0139 }
            boolean r3 = r3.equals(r7)     // Catch:{ all -> 0x0139 }
            fm.liveswitch.MediaFormat r7 = r9.getOutputFormat()     // Catch:{ all -> 0x0139 }
            fm.liveswitch.VideoFormat r7 = (fm.liveswitch.VideoFormat) r7     // Catch:{ all -> 0x0139 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0139 }
            java.lang.String r8 = fm.liveswitch.VideoFormat.getVp9Name()     // Catch:{ all -> 0x0139 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0139 }
            if (r3 == 0) goto L_0x0091
            boolean r1 = fm.liveswitch.vp8.Utility.isKeyFrame(r5)     // Catch:{ all -> 0x0139 }
            goto L_0x00b4
        L_0x0091:
            if (r7 == 0) goto L_0x0098
            boolean r1 = fm.liveswitch.vp8.Utility.isKeyFrame(r5)     // Catch:{ all -> 0x0139 }
            goto L_0x00b4
        L_0x0098:
            if (r0 == 0) goto L_0x009f
            boolean r1 = fm.liveswitch.h264.Utility.isKeyFrame(r5)     // Catch:{ all -> 0x0139 }
            goto L_0x00b4
        L_0x009f:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0139 }
            if (r3 < r4) goto L_0x00ad
            android.media.MediaCodec$BufferInfo r3 = r9._outputBufferInfo     // Catch:{ all -> 0x0139 }
            int r3 = r3.flags     // Catch:{ all -> 0x0139 }
            r3 = r3 & r1
            if (r3 <= 0) goto L_0x00ab
            goto L_0x00b4
        L_0x00ab:
            r1 = 0
            goto L_0x00b4
        L_0x00ad:
            android.media.MediaCodec$BufferInfo r3 = r9._outputBufferInfo     // Catch:{ all -> 0x0139 }
            int r3 = r3.flags     // Catch:{ all -> 0x0139 }
            r3 = r3 & r1
            if (r3 <= 0) goto L_0x00ab
        L_0x00b4:
            boolean r3 = r9._forceKeyFrame     // Catch:{ all -> 0x0139 }
            if (r3 == 0) goto L_0x00bd
            if (r1 != 0) goto L_0x00bd
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
            goto L_0x0000
        L_0x00bd:
            if (r0 == 0) goto L_0x011e
            if (r1 == 0) goto L_0x011e
            java.lang.String r0 = "Encoder generated keyframe."
            fm.liveswitch.Log.debug(r0)     // Catch:{ all -> 0x0139 }
            boolean r0 = fm.liveswitch.h264.Utility.isSps(r5)     // Catch:{ all -> 0x0139 }
            if (r0 == 0) goto L_0x00cf
            r9._lastSpsBuffer = r5     // Catch:{ all -> 0x0139 }
            goto L_0x011e
        L_0x00cf:
            boolean r0 = fm.liveswitch.h264.Utility.isPps(r5)     // Catch:{ all -> 0x0139 }
            if (r0 == 0) goto L_0x00d8
            r9._lastPpsBuffer = r5     // Catch:{ all -> 0x0139 }
            goto L_0x011e
        L_0x00d8:
            boolean r0 = fm.liveswitch.h264.Utility.isIdr(r5)     // Catch:{ all -> 0x0139 }
            if (r0 == 0) goto L_0x011e
            int r0 = r5.getLength()     // Catch:{ all -> 0x0139 }
            fm.liveswitch.DataBuffer r1 = r9._lastSpsBuffer     // Catch:{ all -> 0x0139 }
            if (r1 == 0) goto L_0x00eb
            int r1 = r1.getLength()     // Catch:{ all -> 0x0139 }
            int r0 = r0 + r1
        L_0x00eb:
            fm.liveswitch.DataBuffer r1 = r9._lastPpsBuffer     // Catch:{ all -> 0x0139 }
            if (r1 == 0) goto L_0x00f4
            int r1 = r1.getLength()     // Catch:{ all -> 0x0139 }
            int r0 = r0 + r1
        L_0x00f4:
            int r1 = r5.getLength()     // Catch:{ all -> 0x0139 }
            if (r0 == r1) goto L_0x011e
            fm.liveswitch.DataBuffer r0 = fm.liveswitch.DataBuffer.allocate(r0)     // Catch:{ all -> 0x0139 }
            fm.liveswitch.DataBuffer r1 = r9._lastSpsBuffer     // Catch:{ all -> 0x0139 }
            if (r1 == 0) goto L_0x010c
            r0.write(r1, r6)     // Catch:{ all -> 0x0139 }
            fm.liveswitch.DataBuffer r1 = r9._lastSpsBuffer     // Catch:{ all -> 0x0139 }
            int r1 = r1.getLength()     // Catch:{ all -> 0x0139 }
            int r6 = r6 + r1
        L_0x010c:
            fm.liveswitch.DataBuffer r1 = r9._lastPpsBuffer     // Catch:{ all -> 0x0139 }
            if (r1 == 0) goto L_0x011a
            r0.write(r1, r6)     // Catch:{ all -> 0x0139 }
            fm.liveswitch.DataBuffer r1 = r9._lastPpsBuffer     // Catch:{ all -> 0x0139 }
            int r1 = r1.getLength()     // Catch:{ all -> 0x0139 }
            int r6 = r6 + r1
        L_0x011a:
            r0.write(r5, r6)     // Catch:{ all -> 0x0139 }
            r5 = r0
        L_0x011e:
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
            if (r5 == 0) goto L_0x014c
            fm.liveswitch.android.MediaCodecEncoder$2 r0 = new fm.liveswitch.android.MediaCodecEncoder$2
            fm.liveswitch.VideoBuffer r1 = new fm.liveswitch.VideoBuffer
            int r2 = r9._currentWidth
            int r3 = r9._currentHeight
            fm.liveswitch.MediaFormat r4 = r9.getOutputFormat()
            fm.liveswitch.VideoFormat r4 = (fm.liveswitch.VideoFormat) r4
            r1.<init>((int) r2, (int) r3, (fm.liveswitch.DataBuffer) r5, (fm.liveswitch.VideoFormat) r4)
            r0.<init>(r1)
            r9.raiseFrame((fm.liveswitch.VideoFrame) r0)
            goto L_0x014c
        L_0x0139:
            r10 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
            throw r10
        L_0x013c:
            r2 = -1
            if (r0 != r2) goto L_0x014c
            fm.liveswitch.ManagedCondition r0 = r9._condition
            monitor-enter(r0)
            fm.liveswitch.ManagedCondition r2 = r9._condition     // Catch:{ all -> 0x0149 }
            r2.halt(r1)     // Catch:{ all -> 0x0149 }
            monitor-exit(r0)     // Catch:{ all -> 0x0149 }
            goto L_0x014c
        L_0x0149:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0149 }
            throw r10
        L_0x014c:
            r10.loopEnd()
            goto L_0x0000
        L_0x0151:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.android.MediaCodecEncoder.loop(fm.liveswitch.ManagedThread):void");
    }
}
