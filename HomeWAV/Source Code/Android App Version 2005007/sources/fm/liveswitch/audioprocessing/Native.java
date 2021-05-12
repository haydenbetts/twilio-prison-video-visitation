package fm.liveswitch.audioprocessing;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.Global;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.Log;
import java.util.HashMap;

class Native extends fm.liveswitch.Native {
    static IDataBufferPool _dataBufferPool = DataBufferPool.getTracer(Native.class);
    static HashMap<Integer, DataBuffer> allocMap = new HashMap<>();
    private long _state;

    private static native long AudioProcessingFMCreate(long j, long j2, long j3, long j4, int i, int i2, boolean z);

    private static native void AudioProcessingFMDestroy(long j);

    private static native void AudioProcessingFMProcessReverseStream(long j, byte[] bArr, int i, int i2);

    private static native byte[] AudioProcessingFMProcessStream(long j, byte[] bArr, int i, int i2, int i3);

    static {
        Global.loadLibrary("audioprocessingfmJNI");
    }

    public Native(int i, int i2) {
        this._state = AudioProcessingFMCreate(0, 0, 0, 0, i, i2, true);
    }

    public void destroy() {
        AudioProcessingFMDestroy(this._state);
        this._state = 0;
    }

    public AudioBuffer capture(AudioFrame audioFrame, AudioBuffer audioBuffer, AudioFormat audioFormat, int i) {
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        byte[] AudioProcessingFMProcessStream = AudioProcessingFMProcessStream(this._state, dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength(), i);
        if (AudioProcessingFMProcessStream == null) {
            return null;
        }
        DataBuffer buffer = getBuffer(AudioProcessingFMProcessStream);
        buffer.setLittleEndian(dataBuffer.getLittleEndian());
        return new AudioBuffer(buffer, audioFormat);
    }

    public void render(AudioFrame audioFrame) {
        DataBuffer dataBuffer = ((AudioBuffer[]) audioFrame.getBuffers())[((AudioBuffer[]) audioFrame.getBuffers()).length - 1].getDataBuffer();
        AudioProcessingFMProcessReverseStream(this._state, dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
    }

    private static void DebugCallback(String str) {
        Log.info("LibAudioProcessingFM: " + str);
    }

    private static void ErrorCallback(String str) {
        Log.error("LibAudioProcessingFM: " + str);
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
