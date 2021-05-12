package fm.liveswitch.vp9;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.vpx.Codec;
import fm.liveswitch.vpx.EncoderConfig;

public class Encoder extends fm.liveswitch.vpx.Encoder {
    public String getLabel() {
        return "VP9 Encoder";
    }

    public Encoder() {
        super(new Format());
        EncoderConfig codecConfig = super.getCodecConfig();
        codecConfig.setCpu(7);
        super.setCodecConfig(codecConfig);
    }

    public Encoder(IVideoOutput iVideoOutput) {
        this();
        super.addInput(iVideoOutput);
    }

    public Codec getCodec() {
        return Codec.Vp9;
    }

    /* access modifiers changed from: protected */
    public boolean isKeyFrame(DataBuffer dataBuffer) {
        return Utility.isKeyFrame(dataBuffer);
    }
}
