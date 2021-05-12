package fm.liveswitch.pcma;

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

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "PCMA (G.711a) Encoder";
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
        DataBuffer take = __dataBufferPool.take(dataBuffer.getLength() / 2, ((AudioFormat) super.getOutputFormat()).getLittleEndian());
        int i = 0;
        int i2 = 0;
        while (i < take.getLength()) {
            take.write8(Codec.compress(dataBuffer.read16(i2)), i);
            i++;
            i2 += 2;
        }
        return new AudioBuffer(take, (AudioFormat) super.getOutputFormat());
    }

    public Encoder() {
        this(new Format().getConfig());
    }

    public Encoder(AudioConfig audioConfig) {
        super(new Format(audioConfig), new Format(audioConfig));
        setTargetBitrate(((AudioFormat) super.getOutputFormat()).getMaxBitrate());
        setBitrate(((AudioFormat) super.getOutputFormat()).getMaxBitrate());
    }

    public Encoder(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }
}
