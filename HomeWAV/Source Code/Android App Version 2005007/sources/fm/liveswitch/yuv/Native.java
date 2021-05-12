package fm.liveswitch.yuv;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.Global;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.Log;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import java.util.HashMap;

class Native extends fm.liveswitch.Native {
    static IDataBufferPool _dataBufferPool = DataBufferPool.getTracer(Native.class);
    static HashMap<Integer, DataBuffer> allocMap = new HashMap<>();
    long _state = YuvFMCreate(0, 0, 0, 0);

    private static native byte[] YuvFMConvertFromI420(long j, int i, int i2, byte[][] bArr, int[] iArr, int[] iArr2, int[] iArr3, int i3, int[] iArr4, long j2);

    private static native byte[] YuvFMConvertToi420(long j, int i, int i2, int i3, int i4, byte[][] bArr, int[] iArr, int[] iArr2, int[] iArr3, int i5, int[] iArr4, long j2);

    private static native byte[] YuvFMConverti420ToRgb24(long j, int i, int i2, int i3, byte[][] bArr, int[] iArr, int[] iArr2, int[] iArr3, int i4, int[] iArr4, long j2);

    private static native long YuvFMCreate(long j, long j2, long j3, long j4);

    private static native long YuvFMDestroy(long j);

    private static native byte[] YuvFMI420Rotate(long j, int i, int i2, int i3, byte[][] bArr, int[] iArr, int[] iArr2, int[] iArr3, int i4, int[] iArr4);

    private static native byte[] YuvFMScalei420(long j, int i, int i2, int i3, int i4, int i5, byte[][] bArr, int[] iArr, int[] iArr2, int[] iArr3, int i6, int[] iArr4);

    static {
        Global.loadLibrary("yuvfmJNI");
    }

    public void destroy() {
        YuvFMDestroy(this._state);
        this._state = 0;
    }

    public VideoBuffer convertToI420(VideoBuffer videoBuffer, int i) {
        int length = videoBuffer.getDataBuffers().length;
        try {
            byte[][] bArr = new byte[length][];
            int[] iArr = new int[length];
            int[] iArr2 = new int[length];
            int[] iArr3 = new int[length];
            for (int i2 = 0; i2 < videoBuffer.getDataBuffers().length; i2++) {
                DataBuffer dataBuffer = videoBuffer.getDataBuffers()[i2];
                bArr[i2] = dataBuffer.getData();
                iArr[i2] = videoBuffer.getStrides()[i2];
                iArr2[i2] = dataBuffer.getIndex();
                iArr3[i2] = dataBuffer.getLength();
            }
            byte[] YuvFMConvertToi420 = YuvFMConvertToi420(this._state, videoBuffer.getWidth(), videoBuffer.getHeight(), videoBuffer.getOrientation(), i, bArr, iArr, iArr2, iArr3, length, new int[3], (long) ((VideoFormat) videoBuffer.getFormat()).getFourCC());
            if (YuvFMConvertToi420 == null) {
                return null;
            }
            DataBuffer buffer = getBuffer(YuvFMConvertToi420);
            buffer.keep();
            buffer.keep();
            int length2 = buffer.getLength();
            VideoFormat clone = ((VideoFormat) videoBuffer.getFormat()).clone();
            clone.setName(VideoFormat.getI420Name());
            if (videoBuffer.getOrientation() != 90) {
                if (videoBuffer.getOrientation() != 270) {
                    return new VideoBuffer(videoBuffer.getWidth(), videoBuffer.getHeight(), buffer, clone, length2, videoBuffer) {
                        final /* synthetic */ int val$bufferLength;
                        final /* synthetic */ VideoBuffer val$fromBuffer;

                        {
                            this.val$bufferLength = r6;
                            this.val$fromBuffer = r7;
                            setStrides(new int[]{(r6 * 2) / (r7.getHeight() * 3)});
                        }
                    };
                }
            }
            return new VideoBuffer(videoBuffer.getHeight(), videoBuffer.getWidth(), buffer, clone, length2, videoBuffer) {
                final /* synthetic */ int val$bufferLength;
                final /* synthetic */ VideoBuffer val$fromBuffer;

                {
                    this.val$bufferLength = r6;
                    this.val$fromBuffer = r7;
                    setStrides(new int[]{(r6 * 2) / (r7.getHeight() * 3)});
                }
            };
        } catch (Exception e) {
            Log.error("LibYuvFM: " + e.getMessage() + e.getStackTrace());
            return null;
        }
    }

    public VideoBuffer convertFromI420(VideoBuffer videoBuffer, VideoFormat videoFormat) {
        int length = videoBuffer.getDataBuffers().length;
        try {
            byte[][] bArr = new byte[length][];
            int[] iArr = new int[length];
            int[] iArr2 = new int[length];
            int[] iArr3 = new int[length];
            for (int i = 0; i < videoBuffer.getDataBuffers().length; i++) {
                DataBuffer dataBuffer = videoBuffer.getDataBuffers()[i];
                bArr[i] = dataBuffer.getData();
                iArr[i] = videoBuffer.getStrides()[i];
                iArr2[i] = dataBuffer.getIndex();
                iArr3[i] = dataBuffer.getLength();
            }
            byte[] YuvFMConvertFromI420 = YuvFMConvertFromI420(this._state, videoBuffer.getWidth(), videoBuffer.getHeight(), bArr, iArr, iArr2, iArr3, length, new int[3], (long) videoFormat.getFourCC());
            if (YuvFMConvertFromI420 == null) {
                return null;
            }
            DataBuffer buffer = getBuffer(YuvFMConvertFromI420);
            buffer.keep();
            buffer.keep();
            int height = videoBuffer.getHeight();
            int length2 = buffer.getLength();
            if (!videoFormat.getIsYuvType()) {
                buffer.free();
                buffer.free();
                return new VideoBuffer(videoBuffer.getWidth(), videoBuffer.getHeight(), buffer, videoFormat, length2, height) {
                    final /* synthetic */ int val$bufferHeight;
                    final /* synthetic */ int val$bufferLength;

                    {
                        this.val$bufferLength = r6;
                        this.val$bufferHeight = r7;
                        setStrides(new int[]{r6 / r7});
                    }
                };
            } else if (!videoFormat.getIsYuvType()) {
                return null;
            } else {
                if (videoFormat.getIsNv12() || videoFormat.getIsNv21()) {
                    buffer.free();
                }
                return new VideoBuffer(videoBuffer.getWidth(), videoBuffer.getHeight(), buffer, videoFormat, length2, height) {
                    final /* synthetic */ int val$bufferHeight;
                    final /* synthetic */ int val$bufferLength;

                    {
                        this.val$bufferLength = r6;
                        this.val$bufferHeight = r7;
                        setStrides(new int[]{(r6 * 2) / (r7 * 3)});
                    }
                };
            }
        } catch (Exception e) {
            Log.error("LibYuvFM: " + e.getMessage() + e.getStackTrace());
            return null;
        }
    }

    public VideoBuffer scale(VideoBuffer videoBuffer, int i, int i2, int i3) {
        int length = videoBuffer.getDataBuffers().length;
        try {
            byte[][] bArr = new byte[length][];
            int[] iArr = new int[length];
            int[] iArr2 = new int[length];
            int[] iArr3 = new int[length];
            for (int i4 = 0; i4 < videoBuffer.getDataBuffers().length; i4++) {
                DataBuffer dataBuffer = videoBuffer.getDataBuffers()[i4];
                bArr[i4] = dataBuffer.getData();
                iArr[i4] = videoBuffer.getStrides()[i4];
                iArr2[i4] = dataBuffer.getIndex();
                iArr3[i4] = dataBuffer.getLength();
            }
            int[] iArr4 = new int[3];
            byte[] YuvFMScalei420 = YuvFMScalei420(this._state, videoBuffer.getWidth(), videoBuffer.getHeight(), i, i2, i3, bArr, iArr, iArr2, iArr3, length, iArr4);
            if (YuvFMScalei420 == null) {
                return null;
            }
            DataBuffer buffer = getBuffer(YuvFMScalei420);
            buffer.keep();
            buffer.keep();
            DataBuffer[] dataBufferArr = {buffer.subset(0, iArr4[0]), buffer.subset(iArr4[0], iArr4[1]), buffer.subset(iArr4[0] + iArr4[1], iArr4[2])};
            VideoFormat clone = ((VideoFormat) videoBuffer.getFormat()).clone();
            clone.setName(VideoFormat.getI420Name());
            return new VideoBuffer(i, i2, dataBufferArr, clone, i) {
                final /* synthetic */ int val$toWidth;

                {
                    this.val$toWidth = r6;
                    setStrides(new int[]{r6, r6 / 2, r6 / 2});
                }
            };
        } catch (Exception e) {
            Log.error("LibYuvFM: " + e.getMessage() + e.getStackTrace());
            return null;
        }
    }

    public VideoBuffer rotate(VideoBuffer videoBuffer) {
        int length = videoBuffer.getDataBuffers().length;
        try {
            byte[][] bArr = new byte[length][];
            int[] iArr = new int[length];
            int[] iArr2 = new int[length];
            int[] iArr3 = new int[length];
            for (int i = 0; i < videoBuffer.getDataBuffers().length; i++) {
                DataBuffer dataBuffer = videoBuffer.getDataBuffers()[i];
                bArr[i] = dataBuffer.getData();
                iArr[i] = videoBuffer.getStrides()[i];
                iArr2[i] = dataBuffer.getIndex();
                iArr3[i] = dataBuffer.getLength();
            }
            int[] iArr4 = new int[3];
            byte[] YuvFMI420Rotate = YuvFMI420Rotate(this._state, videoBuffer.getWidth(), videoBuffer.getHeight(), videoBuffer.getOrientation(), bArr, iArr, iArr2, iArr3, length, iArr4);
            if (YuvFMI420Rotate == null) {
                return null;
            }
            DataBuffer buffer = getBuffer(YuvFMI420Rotate);
            buffer.keep();
            buffer.keep();
            DataBuffer[] dataBufferArr = {buffer.subset(0, iArr4[0]), buffer.subset(iArr4[0], iArr4[1]), buffer.subset(iArr4[0] + iArr4[1], iArr4[2])};
            VideoFormat clone = ((VideoFormat) videoBuffer.getFormat()).clone();
            if (videoBuffer.getOrientation() != 90) {
                if (videoBuffer.getOrientation() != 270) {
                    return new VideoBuffer(videoBuffer.getWidth(), videoBuffer.getHeight(), dataBufferArr, clone, videoBuffer) {
                        final /* synthetic */ VideoBuffer val$inputBuffer;

                        {
                            this.val$inputBuffer = r6;
                            setStrides(new int[]{r6.getWidth(), r6.getWidth() / 2, r6.getWidth() / 2});
                        }
                    };
                }
            }
            return new VideoBuffer(videoBuffer.getHeight(), videoBuffer.getWidth(), dataBufferArr, clone, videoBuffer) {
                final /* synthetic */ VideoBuffer val$inputBuffer;

                {
                    this.val$inputBuffer = r6;
                    setStrides(new int[]{r6.getHeight(), r6.getHeight() / 2, r6.getHeight() / 2});
                }
            };
        } catch (Exception e) {
            Log.error("LibYuvFM: " + e.getMessage() + e.getStackTrace());
            return null;
        }
    }

    private static void DebugCallback(String str) {
        Log.info("LibYuvFM: " + str);
    }

    private static void ErrorCallback(String str) {
        Log.error("LibYuvFM: " + str);
    }

    private static byte[] AllocCallback(int i) {
        byte[] data;
        synchronized (allocMap) {
            DataBuffer take = _dataBufferPool.take(i);
            data = take.getData();
            allocMap.put(Integer.valueOf(data.hashCode()), take);
        }
        return data;
    }

    private static DataBuffer getBuffer(byte[] bArr) {
        DataBuffer remove;
        synchronized (allocMap) {
            remove = allocMap.remove(Integer.valueOf(bArr.hashCode()));
        }
        return remove;
    }
}
