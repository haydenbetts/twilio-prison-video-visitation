package fm.liveswitch.vpx;

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
    long _decoderState = 0;
    long _encoderState = 0;
    boolean _isVP9;

    private static native long VpxFMDecoderCreate(long j, long j2, long j3, long j4);

    private static native byte[] VpxFMDecoderDecode(long j, byte[] bArr, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6);

    private static native void VpxFMDecoderDestroy(long j);

    private static native int VpxFMDecoderGetNeedsKeyFrame(long j);

    private static native void VpxFMDecoderSetNeedsKeyFrame(long j, int i);

    private static native void VpxFMDecoderSetVP9(long j, int i);

    private static native long VpxFMEncoderCreate(long j, long j2, long j3, long j4);

    private static native void VpxFMEncoderDestroy(long j);

    private static native byte[] VpxFMEncoderEncodei420(long j, int i, int i2, byte[][] bArr, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    private static native int VpxFMEncoderGetBitrate(long j);

    private static native int VpxFMEncoderGetForceKeyFrame(long j);

    private static native int VpxFMEncoderSetBitrate(long j, int i);

    private static native int VpxFMEncoderSetConfig(long j, NativeEncoderConfig nativeEncoderConfig);

    private static native void VpxFMEncoderSetForceKeyFrame(long j, int i);

    private static native void VpxFMEncoderSetVP9(long j, int i);

    static {
        Global.loadLibrary("vpxfmJNI");
    }

    public void setNeedsKeyFrame(boolean z) {
        VpxFMDecoderSetNeedsKeyFrame(this._decoderState, z ? 1 : 0);
    }

    public boolean getNeedsKeyFrame() {
        return VpxFMDecoderGetNeedsKeyFrame(this._decoderState) != 0;
    }

    public void setForceKeyFrame(boolean z) {
        VpxFMEncoderSetForceKeyFrame(this._encoderState, z ? 1 : 0);
    }

    public boolean getForceKeyFrame() {
        return VpxFMEncoderGetForceKeyFrame(this._encoderState) != 0;
    }

    public Codec getCodec() {
        return this._isVP9 ? Codec.Vp9 : Codec.Vp8;
    }

    public void setCodec(Codec codec) {
        boolean z = codec == Codec.Vp9;
        this._isVP9 = z;
        long j = this._encoderState;
        if (j != 0) {
            VpxFMEncoderSetVP9(j, z ? 1 : 0);
        }
        long j2 = this._decoderState;
        if (j2 != 0) {
            VpxFMDecoderSetVP9(j2, this._isVP9 ? 1 : 0);
        }
    }

    public int getBitrate() {
        return VpxFMEncoderGetBitrate(this._encoderState);
    }

    public void setBitrate(int i) {
        VpxFMEncoderSetBitrate(this._encoderState, i);
    }

    public int setEncoderConfig(NativeEncoderConfig nativeEncoderConfig) {
        long j = this._encoderState;
        if (j == 0) {
            return -1;
        }
        return VpxFMEncoderSetConfig(j, nativeEncoderConfig);
    }

    public Native(boolean z) {
        if (z) {
            this._encoderState = VpxFMEncoderCreate(0, 0, 0, 0);
        } else {
            this._decoderState = VpxFMDecoderCreate(0, 0, 0, 0);
        }
    }

    public void destroy() {
        long j = this._encoderState;
        if (j != 0) {
            VpxFMEncoderDestroy(j);
            this._encoderState = 0;
        }
        long j2 = this._decoderState;
        if (j2 != 0) {
            VpxFMDecoderDestroy(j2);
            this._decoderState = 0;
        }
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
        byte[] VpxFMEncoderEncodei420 = VpxFMEncoderEncodei420(this._encoderState, videoBuffer.getWidth(), videoBuffer.getHeight(), bArr, iArr, iArr2, iArr3, new int[1]);
        if (VpxFMEncoderEncodei420 != null) {
            return new VideoBuffer(videoBuffer.getWidth(), videoBuffer.getHeight(), getBuffer(VpxFMEncoderEncodei420), videoFormat);
        }
        return null;
    }

    public VideoBuffer decode(VideoBuffer videoBuffer, VideoFormat videoFormat) {
        try {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            int[] iArr3 = new int[4];
            int[] iArr4 = new int[4];
            int[] iArr5 = new int[1];
            DataBuffer dataBuffer = videoBuffer.getDataBuffers()[0];
            byte[] VpxFMDecoderDecode = VpxFMDecoderDecode(this._decoderState, Arrays.copyOfRange(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength()), videoBuffer.getDataBuffers()[0].getLength(), iArr, iArr2, iArr3, new int[4], iArr4, iArr5);
            if (VpxFMDecoderDecode == null) {
                return null;
            }
            int i = iArr[0];
            int i2 = iArr2[0];
            int[] iArr6 = new int[4];
            DataBuffer buffer = getBuffer(VpxFMDecoderDecode);
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
        } catch (Exception e) {
            Log.error("Decode failed with exception. Attempting to recover with keyframe request.", e);
            setNeedsKeyFrame(true);
            return null;
        }
    }

    private static void DebugCallback(String str) {
        Log.info("LibVpxFM: " + str);
    }

    private static void ErrorCallback(String str) {
        Log.error("LibVpxFM: " + str);
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
