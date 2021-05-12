package fm.liveswitch.g722;

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
    private Codec __codec;
    private CodecState __codecState;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "G.722 Decoder";
    }

    private AudioBuffer decode(AudioBuffer audioBuffer) {
        DataBuffer dataBuffer = audioBuffer.getDataBuffer();
        CodecState codecState = this.__codecState;
        DataBuffer take = __dataBufferPool.take(((dataBuffer.getLength() * 4) * codecState.getBitsPerSample()) / 8, ((AudioFormat) super.getOutputFormat()).getLittleEndian());
        this.__codec.decode(codecState, dataBuffer, take);
        return new AudioBuffer(take, (AudioFormat) super.getOutputFormat());
    }

    public Decoder() {
        this(new Format().getConfig());
    }

    public Decoder(AudioConfig audioConfig) {
        super(new Format(audioConfig), new Format(audioConfig));
        this.__codec = new Codec();
        this.__codecState = new CodecState(64000);
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
