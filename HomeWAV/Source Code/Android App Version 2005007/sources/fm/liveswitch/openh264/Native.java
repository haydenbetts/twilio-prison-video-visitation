package fm.liveswitch.openh264;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.Global;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.Log;
import fm.liveswitch.VideoBuffer;
import fm.liveswitch.VideoFormat;
import java.util.Arrays;
import java.util.HashMap;

class Native extends fm.liveswitch.Native {
    static IDataBufferPool _dataBufferPool = DataBufferPool.getTracer(Native.class);
    static HashMap<Integer, DataBuffer> allocMap = new HashMap<>();
    private long _decoderState = 0;
    private long _encoderState = 0;

    private static native long OpenH264FMDecoderCreate(long j, long j2, long j3, long j4);

    private static native byte[] OpenH264FMDecoderDecode(long j, byte[] bArr, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5);

    private static native void OpenH264FMDecoderDestroy(long j);

    private static native int OpenH264FMDecoderGetNeedsKeyFrame(long j);

    private static native int OpenH264FMDecoderSetConfig(long j, NativeDecoderConfig nativeDecoderConfig);

    private static native void OpenH264FMDecoderSetNeedsKeyFrame(long j, int i);

    private static native int OpenH264FMEncoderApplyConfigChanges(long j);

    private static native long OpenH264FMEncoderCreate(long j, long j2, long j3, long j4);

    private static native void OpenH264FMEncoderDestroy(long j);

    private static native byte[] OpenH264FMEncoderEncodei420(long j, int i, int i2, byte[][] bArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    private static native int OpenH264FMEncoderGetBitrate(long j);

    private static native int OpenH264FMEncoderGetForceKeyFrame(long j);

    private static native void OpenH264FMEncoderSetBitrate(long j, int i);

    private static native int OpenH264FMEncoderSetComplexityMode(long j, int i);

    private static native int OpenH264FMEncoderSetConfig(long j, NativeEncoderConfig nativeEncoderConfig);

    private static native void OpenH264FMEncoderSetForceKeyFrame(long j, int i);

    private static native int OpenH264FMEncoderSetSpatialLayerConfig(long j, NativeEncoderSpatialLayerConfig nativeEncoderSpatialLayerConfig, int i);

    static {
        Global.loadLibrary("openh264fmJNI");
    }

    public boolean getForceKeyFrame() {
        return OpenH264FMEncoderGetForceKeyFrame(this._encoderState) != 0;
    }

    public void setForceKeyFrame(boolean z) {
        OpenH264FMEncoderSetForceKeyFrame(this._encoderState, z ? 1 : 0);
    }

    public int getBitrate() {
        return OpenH264FMEncoderGetBitrate(this._encoderState);
    }

    public void setBitrate(int i) {
        OpenH264FMEncoderSetBitrate(this._encoderState, i);
    }

    public boolean getNeedsKeyFrame() {
        return OpenH264FMDecoderGetNeedsKeyFrame(this._decoderState) != 0;
    }

    public void setNeedsKeyFrame(boolean z) {
        OpenH264FMDecoderSetNeedsKeyFrame(this._decoderState, z ? 1 : 0);
    }

    public int setComplexityMode(ComplexityMode complexityMode) {
        long j = this._encoderState;
        if (j == 0) {
            return -1;
        }
        return OpenH264FMEncoderSetComplexityMode(j, complexityMode.getValue());
    }

    public int setEncoderConfig(NativeEncoderConfig nativeEncoderConfig) {
        long j = this._encoderState;
        if (j == 0) {
            return -1;
        }
        return OpenH264FMEncoderSetConfig(j, nativeEncoderConfig);
    }

    public int setEncoderSpatialLayerConfig(NativeEncoderSpatialLayerConfig nativeEncoderSpatialLayerConfig, int i) {
        long j = this._encoderState;
        if (j == 0) {
            return -1;
        }
        return OpenH264FMEncoderSetSpatialLayerConfig(j, nativeEncoderSpatialLayerConfig, i);
    }

    public int applyEncoderConfig() {
        long j = this._encoderState;
        if (j == 0) {
            return -1;
        }
        return OpenH264FMEncoderApplyConfigChanges(j);
    }

    public int setDecoderConfig(NativeDecoderConfig nativeDecoderConfig) {
        long j = this._decoderState;
        if (j == 0) {
            return -1;
        }
        return OpenH264FMDecoderSetConfig(j, nativeDecoderConfig);
    }

    public Native(boolean z) {
        if (z) {
            this._encoderState = OpenH264FMEncoderCreate(0, 0, 0, 0);
        } else {
            this._decoderState = OpenH264FMDecoderCreate(0, 0, 0, 0);
        }
    }

    public void destroy() {
        long j = this._encoderState;
        if (j != 0) {
            OpenH264FMEncoderDestroy(j);
        }
        long j2 = this._decoderState;
        if (j2 != 0) {
            OpenH264FMDecoderDestroy(j2);
        }
        this._encoderState = 0;
        this._decoderState = 0;
    }

    public VideoBuffer encode(VideoBuffer videoBuffer, VideoFormat videoFormat) {
        byte[][] bArr = new byte[4][];
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        for (int i = 0; i < videoBuffer.getDataBuffers().length; i++) {
            DataBuffer dataBuffer = videoBuffer.getDataBuffers()[i];
            bArr[i] = dataBuffer.getData();
            iArr[i] = videoBuffer.getStrides()[i];
            iArr2[i] = dataBuffer.getIndex();
            iArr3[i] = dataBuffer.getLength();
        }
        byte[] OpenH264FMEncoderEncodei420 = OpenH264FMEncoderEncodei420(this._encoderState, videoBuffer.getWidth(), videoBuffer.getHeight(), bArr, iArr, iArr2, iArr3, new int[1]);
        if (OpenH264FMEncoderEncodei420 != null) {
            return new VideoBuffer(videoBuffer.getWidth(), videoBuffer.getHeight(), getBuffer(OpenH264FMEncoderEncodei420), videoFormat);
        }
        return null;
    }

    public VideoBuffer decode(VideoBuffer videoBuffer, VideoFormat videoFormat) {
        int[] iArr = new int[1];
        int[] iArr2 = new int[1];
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        int[] iArr5 = new int[1];
        DataBuffer dataBuffer = videoBuffer.getDataBuffers()[0];
        byte[] OpenH264FMDecoderDecode = OpenH264FMDecoderDecode(this._decoderState, Arrays.copyOfRange(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength()), dataBuffer.getLength(), iArr, iArr2, iArr3, iArr4, iArr5);
        if (OpenH264FMDecoderDecode == null) {
            return null;
        }
        DataBuffer buffer = getBuffer(OpenH264FMDecoderDecode);
        int i = iArr[0];
        int i2 = iArr2[0];
        int[] iArr6 = new int[4];
        int i3 = iArr5[0];
        DataBuffer[] dataBufferArr = new DataBuffer[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            iArr6[i5] = iArr3[i5];
            dataBufferArr[i5] = buffer.subset(i4, iArr4[i5]);
            i4 += iArr4[i5];
        }
        return new VideoBuffer(i, i2, dataBufferArr, videoFormat, iArr6) {
            final /* synthetic */ int[] val$strides;

            {
                this.val$strides = r6;
                setStrides(r6);
            }
        };
    }

    private static void DebugCallback(String str) {
        Log.info("LibOpenH264FM: " + str);
    }

    private static void ErrorCallback(String str) {
        Log.error("LibOpenH264FM: " + str);
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
