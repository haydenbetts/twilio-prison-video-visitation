package fm.liveswitch.android;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.view.Surface;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.IActionDelegate1;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.Log;
import fm.liveswitch.ManagedCondition;
import fm.liveswitch.ManagedThread;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoDecoder;
import fm.liveswitch.VideoFormat;
import fm.liveswitch.VideoFrame;
import java.nio.ByteBuffer;

public class MediaCodecDecoder extends VideoDecoder {
    private static final int QCOM_TILE_GROUP_SIZE = 8192;
    private static final int QCOM_TILE_HEIGHT = 32;
    private static final int QCOM_TILE_SIZE = 2048;
    private static final int QCOM_TILE_WIDTH = 64;
    private static final int QOMX_COLOR_FormatYUV420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    private static final int infinite = -1;
    private int _colorFormat;
    private ManagedCondition _condition;
    private int _currentHeight;
    private int _currentSliceHeight;
    private int _currentStride;
    private int _currentWidth;
    private MediaCodec _decoder;
    private MediaCodecInfo _decoderInfo;
    private volatile boolean _decoding = false;
    private String _mimeType;
    private boolean _needsKeyFrame;
    private Object _stateLock = new Object();
    private ManagedThread _thread;
    private MediaCodec.BufferInfo outputBufferInfo = new MediaCodec.BufferInfo();

    private static int getQcomTilePosition(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = ((i2 & -2) * i3) + i;
        if ((i2 & 1) != 0) {
            i5 = (i & -4) + 2;
        } else if ((i4 & 1) != 0 && i2 == i4 - 1) {
            return i6;
        } else {
            i5 = (i + 2) & -4;
        }
        return i6 + i5;
    }

    public String getLabel() {
        return "MediaCodecDecoder";
    }

    /* access modifiers changed from: protected */
    public boolean isKeyFrame(DataBuffer dataBuffer) {
        return true;
    }

    public void setNeedsKeyFrame() {
        this._needsKeyFrame = true;
    }

    public boolean getNeedsKeyFrame() {
        return this._needsKeyFrame;
    }

    public MediaCodecDecoder(MediaCodecInfo mediaCodecInfo, VideoFormat videoFormat, VideoFormat videoFormat2) {
        super(videoFormat, videoFormat2);
        initialize(mediaCodecInfo, videoFormat, videoFormat2);
    }

    public MediaCodecDecoder(MediaCodecInfo mediaCodecInfo, IVideoOutput iVideoOutput, VideoFormat videoFormat) {
        super((VideoFormat) iVideoOutput.getOutputFormat(), videoFormat);
        initialize(mediaCodecInfo, (VideoFormat) iVideoOutput.getOutputFormat(), videoFormat);
    }

    private void initialize(MediaCodecInfo mediaCodecInfo, VideoFormat videoFormat, VideoFormat videoFormat2) {
        if (Build.VERSION.SDK_INT >= 19) {
            this._currentWidth = 640;
            this._currentHeight = 480;
            this._currentStride = 0;
            this._currentSliceHeight = 0;
            this._decoderInfo = mediaCodecInfo;
            if (!mediaCodecInfo.isEncoder()) {
                String mimeType = MediaCodecUtility.getMimeType(videoFormat);
                this._mimeType = mimeType;
                if (mimeType == null) {
                    throw new RuntimeException("Invalid input format " + videoFormat.getName());
                } else if (MediaCodecUtility.hasMimeType(this._decoderInfo, mimeType)) {
                    int colorFormat = MediaCodecUtility.getColorFormat(videoFormat2);
                    this._colorFormat = colorFormat;
                    if (colorFormat < 0 || !MediaCodecUtility.hasColorFormat(this._decoderInfo, this._mimeType, colorFormat)) {
                        throw new RuntimeException("Invalid output format " + videoFormat2.getName());
                    }
                    this._decoding = true;
                } else {
                    throw new RuntimeException("Invalid input format " + videoFormat.getName() + " and respective mime type " + this._mimeType + " for this decoderInfo");
                }
            } else {
                throw new RuntimeException("decoderInfo is not a valid encoder");
            }
        } else {
            throw new RuntimeException("Android 4.4 or higher is required to use hardware encoding.");
        }
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        synchronized (this._stateLock) {
            this._decoding = false;
            this._condition.pulse();
        }
        MediaCodec mediaCodec = this._decoder;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this._decoder.release();
            this._decoder = null;
        }
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(VideoFrame videoFrame, VideoBuffer videoBuffer) {
        decode(videoBuffer);
    }

    private void initializeMediaCodec() throws Exception {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this._mimeType, this._currentWidth, this._currentHeight);
        createVideoFormat.setInteger("color-format", this._colorFormat);
        MediaCodec createByCodecName = MediaCodec.createByCodecName(this._decoderInfo.getName());
        this._decoder = createByCodecName;
        createByCodecName.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 0);
        this._condition = new ManagedCondition();
        this._thread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
            public String getId() {
                return "fm.liveswitch.android.MediaCodecDecoder.loop";
            }

            public void invoke(ManagedThread managedThread) {
                MediaCodecDecoder.this.loop(managedThread);
            }
        });
        this._decoder.start();
        this._thread.start();
    }

    public void decode(VideoBuffer videoBuffer) {
        try {
            if (this._decoder == null) {
                initializeMediaCodec();
            }
            DataBuffer[] dataBuffers = videoBuffer.getDataBuffers();
            if (dataBuffers.length > 1) {
                Log.error("VideoBuffer must contain a single plane packed with all planes of the given video format.");
            }
            queueEncodedFrame(dataBuffers[0]);
        } catch (Exception e) {
            MediaCodec mediaCodec = this._decoder;
            if (mediaCodec != null) {
                mediaCodec.stop();
                this._decoder.release();
                this._decoder = null;
            }
            Log.error("MediaCodec decode failed for " + this._mimeType + "\n" + e.getMessage());
        }
    }

    private void queueEncodedFrame(DataBuffer dataBuffer) {
        ByteBuffer byteBuffer;
        int i = -1;
        while (i < 0) {
            i = this._decoder.dequeueInputBuffer(-1);
        }
        if (Build.VERSION.SDK_INT < 21) {
            byteBuffer = this._decoder.getInputBuffers()[i];
        } else {
            byteBuffer = this._decoder.getInputBuffer(i);
        }
        byteBuffer.position(0);
        byteBuffer.limit(dataBuffer.getLength());
        byteBuffer.put(dataBuffer.toArray());
        byteBuffer.position(0);
        this._decoder.queueInputBuffer(i, 0, dataBuffer.getLength(), 0, 0);
    }

    /* access modifiers changed from: private */
    public void loop(ManagedThread managedThread) {
        ByteBuffer byteBuffer;
        while (this._decoding) {
            managedThread.loopBegin();
            DataBuffer dataBuffer = null;
            int dequeueOutputBuffer = this._decoder.dequeueOutputBuffer(this.outputBufferInfo, 0);
            if (dequeueOutputBuffer >= 0) {
                if (Build.VERSION.SDK_INT < 21) {
                    byteBuffer = this._decoder.getOutputBuffers()[dequeueOutputBuffer];
                } else {
                    byteBuffer = this._decoder.getOutputBuffer(dequeueOutputBuffer);
                }
                byteBuffer.position(this.outputBufferInfo.offset);
                byteBuffer.limit(this.outputBufferInfo.offset + this.outputBufferInfo.size);
                int i = this._currentStride;
                if (i <= 0) {
                    i = (int) (((double) this.outputBufferInfo.size) / (((double) this._currentWidth) * 1.5d));
                }
                int i2 = i;
                int i3 = this._currentSliceHeight;
                if (this._colorFormat == QOMX_COLOR_FormatYUV420PackedSemiPlanar64x32Tile2m8ka) {
                    int i4 = this.outputBufferInfo.size;
                    dataBuffer = DataBuffer.allocate(this.outputBufferInfo.size);
                }
                DataBuffer dataBuffer2 = dataBuffer;
                this._decoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                if (this._colorFormat == QOMX_COLOR_FormatYUV420PackedSemiPlanar64x32Tile2m8ka) {
                    DataBuffer allocate = DataBuffer.allocate((int) (((double) (this._currentWidth * this._currentHeight)) * 1.5d));
                    convertQcomYuv420PackedSemiPlanar64x32Tile2m8kaToYuv420PackedSemiPlanar(dataBuffer2, allocate, this._currentWidth, this._currentHeight, i2, true);
                    dataBuffer2 = allocate;
                }
                raiseFrame(new VideoFrame(new VideoBuffer(this._currentWidth, this._currentHeight, dataBuffer2, (VideoFormat) getOutputFormat())));
            } else if (dequeueOutputBuffer == -2) {
                MediaFormat outputFormat = this._decoder.getOutputFormat();
                this._currentWidth = outputFormat.getInteger("width");
                this._currentHeight = outputFormat.getInteger("height");
                this._currentStride = outputFormat.getInteger("stride");
                this._currentSliceHeight = outputFormat.getInteger("slice-height");
                int integer = outputFormat.getInteger("color-format");
                if (integer != this._colorFormat) {
                    if (integer == QOMX_COLOR_FormatYUV420PackedSemiPlanar64x32Tile2m8ka) {
                        Log.warn("Unexpected color format: " + integer + ". Format will be converted.");
                    } else {
                        Log.error("Unexpected color format: " + integer + ". Output may be incorrect.");
                    }
                }
                this._colorFormat = integer;
            } else {
                if (dequeueOutputBuffer == -1) {
                    synchronized (this._condition) {
                        this._condition.halt(1);
                    }
                }
                this._needsKeyFrame = true;
            }
            managedThread.loopEnd();
        }
    }

    private void convertQcomYuv420PackedSemiPlanar64x32Tile2m8kaToYuv420PackedSemiPlanar(DataBuffer dataBuffer, DataBuffer dataBuffer2, int i, int i2, int i3, boolean z) {
        int i4;
        int i5;
        DataBuffer dataBuffer3 = dataBuffer;
        DataBuffer dataBuffer4 = dataBuffer2;
        int i6 = 64;
        int i7 = ((i - 1) / 64) + 1;
        int i8 = (i7 + 1) & -2;
        int i9 = 32;
        int i10 = ((i2 - 1) / 32) + 1;
        int i11 = (((i2 / 2) - 1) / 32) + 1;
        int i12 = i8 * i10 * 2048;
        if (i12 % 8192 != 0) {
            i12 = (((i12 - 1) / 8192) + 1) * 8192;
        }
        int i13 = i * i2;
        int i14 = i13 / 4;
        int i15 = i2;
        int i16 = 0;
        while (i16 < i10) {
            int i17 = i;
            int i18 = 0;
            while (i18 < i7) {
                int qcomTilePosition = getQcomTilePosition(i18, i16, i8, i10) * 2048;
                int qcomTilePosition2 = (getQcomTilePosition(i18, i16 / 2, i8, i11) * 2048) + i12;
                if ((i16 & 1) != 0) {
                    qcomTilePosition2 += 1024;
                }
                if (i17 <= i6) {
                    i6 = i17;
                }
                int i19 = (i16 * 32 * i3) + (i18 * 64);
                int i20 = (i15 > i9 ? 32 : i15) / 2;
                int i21 = i19;
                int i22 = i7;
                int i23 = i13 + (((i19 / i3) * i3) / 2) + (i19 % i3);
                while (true) {
                    int i24 = i20 - 1;
                    if (i20 <= 0) {
                        break;
                    }
                    int i25 = i8;
                    dataBuffer4.write(dataBuffer3.subset(qcomTilePosition, i6), i21);
                    int i26 = qcomTilePosition + 64;
                    int i27 = i21 + i3;
                    int i28 = i10;
                    dataBuffer4.write(dataBuffer3.subset(i26, i6), i27);
                    qcomTilePosition = i26 + 64;
                    int i29 = i27 + i3;
                    if (z) {
                        int i30 = (i23 - i13) / 2;
                        int i31 = 0;
                        while (i31 < i6) {
                            dataBuffer4.write16(dataBuffer3.read16(qcomTilePosition2 + i31 + 0), i13 + i30);
                            i30++;
                            i31 += 2;
                            i11 = i11;
                            i29 = i29;
                        }
                        i4 = i29;
                        i5 = i11;
                    } else {
                        i4 = i29;
                        i5 = i11;
                        dataBuffer4.write(dataBuffer3.subset(qcomTilePosition2, i6), i23);
                    }
                    qcomTilePosition2 += 64;
                    i23 += i3;
                    i10 = i28;
                    i8 = i25;
                    i20 = i24;
                    i11 = i5;
                    i21 = i4;
                }
                int i32 = i8;
                int i33 = i10;
                int i34 = i11;
                i17 -= 64;
                i18++;
                i7 = i22;
                i6 = 64;
                i9 = 32;
            }
            int i35 = i7;
            int i36 = i8;
            int i37 = i10;
            int i38 = i11;
            i15 -= 32;
            i16++;
            i6 = 64;
            i9 = 32;
        }
    }
}
