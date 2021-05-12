package fm.liveswitch.opus;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.Global;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.Log;
import java.util.Arrays;
import java.util.HashMap;

class Native extends fm.liveswitch.Native {
    static IDataBufferPool _dataBufferPool = DataBufferPool.getTracer(Native.class);
    static HashMap<Integer, DataBuffer> allocMap = new HashMap<>();
    long _decoderState;
    long _encoderState;

    private static native long OpusFMDecoderCreate(long j, long j2, long j3, long j4, int i, int i2, double d);

    private static native byte[] OpusFMDecoderDecode(long j, byte[] bArr, int i, int[] iArr);

    private static native byte[] OpusFMDecoderDecodeFec(long j, byte[] bArr, int i, int[] iArr, boolean z);

    private static native void OpusFMDecoderDestroy(long j);

    private static native double OpusFMEncoderActivateFec(long j, int i);

    private static native long OpusFMEncoderCreate(long j, long j2, long j3, long j4, int i, int i2, double d);

    private static native void OpusFMEncoderDeactivateFec(long j);

    private static native void OpusFMEncoderDestroy(long j);

    private static native byte[] OpusFMEncoderEncode(long j, byte[] bArr, int i, int i2, int[] iArr);

    private static native void OpusFMEncoderSetBitrate(long j, int i);

    private static native int OpusFMEncoderSetConfig(long j, NativeEncoderConfig nativeEncoderConfig);

    static {
        Global.loadLibrary("opusfmJNI");
    }

    public Native(boolean z, int i, int i2, double d) {
        this._encoderState = OpusFMEncoderCreate(0, 0, 0, 0, i, i2, d);
        long OpusFMDecoderCreate = OpusFMDecoderCreate(0, 0, 0, 0, i, i2, d);
        this._decoderState = OpusFMDecoderCreate;
        if (this._encoderState == 0) {
            throw new RuntimeException("Could not create Opusfm encoder.");
        } else if (OpusFMDecoderCreate == 0) {
            throw new RuntimeException("Could not create Opusfm decoder.");
        }
    }

    public void setBitrate(int i) {
        OpusFMEncoderSetBitrate(this._encoderState, i);
    }

    public int setEncoderConfig(NativeEncoderConfig nativeEncoderConfig) {
        long j = this._encoderState;
        if (j != 0) {
            return OpusFMEncoderSetConfig(j, nativeEncoderConfig);
        }
        return 0;
    }

    public void destroy() {
        OpusFMEncoderDestroy(this._encoderState);
        this._encoderState = 0;
        OpusFMDecoderDestroy(this._decoderState);
        this._decoderState = 0;
    }

    public AudioBuffer encode(AudioBuffer audioBuffer, AudioFormat audioFormat) {
        int length = audioBuffer.getDataBuffers().length;
        if (length <= 0) {
            return null;
        }
        if (length <= 1) {
            DataBuffer dataBuffer = audioBuffer.getDataBuffer();
            int index = dataBuffer.getIndex();
            int[] iArr = new int[1];
            byte[] OpusFMEncoderEncode = OpusFMEncoderEncode(this._encoderState, dataBuffer.getData(), dataBuffer.getLength(), index, iArr);
            if (OpusFMEncoderEncode == null || iArr[0] <= 0) {
                return null;
            }
            DataBuffer subset = getBuffer(OpusFMEncoderEncode).subset(0, iArr[0]);
            subset.setLittleEndian(audioFormat.getLittleEndian());
            return new AudioBuffer(subset, audioFormat);
        }
        throw new RuntimeException("Cannot encode more than one sample at a time. AudioBuffer must contain only one DataBuffer.");
    }

    public AudioBuffer decode(AudioBuffer audioBuffer, AudioFormat audioFormat, boolean z) {
        byte[] bArr;
        int[] iArr = new int[1];
        if (audioBuffer != null) {
            DataBuffer dataBuffer = audioBuffer.getDataBuffers()[0];
            bArr = OpusFMDecoderDecodeFec(this._decoderState, Arrays.copyOfRange(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength() + dataBuffer.getIndex()), audioBuffer.getDataBuffers()[0].getLength(), iArr, z);
        } else {
            bArr = OpusFMDecoderDecodeFec(this._decoderState, (byte[]) null, 0, iArr, z);
        }
        if (bArr == null || iArr[0] <= 0) {
            return null;
        }
        DataBuffer buffer = getBuffer(bArr);
        buffer.setLittleEndian(audioFormat.getLittleEndian());
        return new AudioBuffer(buffer, audioFormat);
    }

    private static void DebugCallback(String str) {
        Log.info("LibOpusFM: " + str);
    }

    private static void ErrorCallback(String str) {
        Log.error("LibOpusFM: " + str);
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
