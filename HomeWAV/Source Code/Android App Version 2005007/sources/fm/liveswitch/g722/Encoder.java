package fm.liveswitch.g722;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioEncoder;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.pcm.Format;

public class Encoder extends AudioEncoder {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(Encoder.class);
    private Codec __codec;
    private CodecState __codecState;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "G.722 Encoder";
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        AudioBuffer encode = encode(audioBuffer);
        if (encode != null) {
            audioFrame.addBuffer(encode);
            raiseFrame(audioFrame);
            encode.free();
        }
    }

    private AudioBuffer encode(AudioBuffer audioBuffer) {
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        DataBuffer take = __dataBufferPool.take(((dataBuffer.getLength() * 8) / this.__codecState.getBitsPerSample()) / 4, ((AudioFormat) super.getOutputFormat()).getLittleEndian());
        this.__codec.encode(this.__codecState, dataBuffer, take);
        return new AudioBuffer(take, (AudioFormat) super.getOutputFormat());
    }

    public Encoder() {
        this(new Format().getConfig());
    }

    public Encoder(AudioConfig audioConfig) {
        super(new Format(audioConfig), new Format(audioConfig));
        setTargetBitrate(((AudioFormat) super.getOutputFormat()).getMaxBitrate());
        setBitrate(((AudioFormat) super.getOutputFormat()).getMaxBitrate());
        this.__codec = new Codec();
        this.__codecState = new CodecState(((AudioFormat) super.getOutputFormat()).getMaxBitrate() * 1000);
    }

    public Encoder(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }
}
