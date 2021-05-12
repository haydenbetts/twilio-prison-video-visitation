package fm.liveswitch.pcma;

import fm.liveswitch.AudioBuffer;
import fm.liveswitch.AudioConfig;
import fm.liveswitch.AudioDecoder;
import fm.liveswitch.AudioFormat;
import fm.liveswitch.AudioFrame;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.IAudioOutput;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.pcm.Format;

public class Decoder extends AudioDecoder {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(Decoder.class);

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "PCMA (G.711a) Decoder";
    }

    private AudioBuffer decode(AudioBuffer audioBuffer) {
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        DataBuffer take = __dataBufferPool.take(dataBuffer.getLength() * 2, ((AudioFormat) super.getOutputFormat()).getLittleEndian());
        int i = 0;
        int i2 = 0;
        while (i < dataBuffer.getLength()) {
            take.write16(Codec.decompress(dataBuffer.read8(i)), i2);
            i++;
            i2 += 2;
        }
        return new AudioBuffer(take, (AudioFormat) super.getOutputFormat());
    }

    public Decoder() {
        this(new Format().getConfig());
    }

    public Decoder(AudioConfig audioConfig) {
        super(new Format(audioConfig), new Format(audioConfig));
    }

    public Decoder(IAudioOutput iAudioOutput) {
        this(iAudioOutput.getConfig());
        super.addInput(iAudioOutput);
    }

    /* access modifiers changed from: protected */
    public void doProcessFrame(AudioFrame audioFrame, AudioBuffer audioBuffer) {
        AudioBuffer decode = decode(audioBuffer);
        if (decode != null) {
            audioFrame.addBuffer(decode);
            raiseFrame(audioFrame);
            decode.free();
        }
    }
}
