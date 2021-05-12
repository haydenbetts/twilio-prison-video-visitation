package fm.liveswitch.vp8;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.vpx.Codec;

public class Decoder extends fm.liveswitch.vpx.Decoder {
    public String getLabel() {
        return "VP8 Decoder";
    }

    public Decoder() {
        super(new Format());
    }

    public Decoder(IVideoOutput iVideoOutput) {
        this();
        super.addInput(iVideoOutput);
    }

    public Codec getCodec() {
        return Codec.Vp8;
    }

    /* access modifiers changed from: protected */
    public boolean isKeyFrame(DataBuffer dataBuffer) {
        return Utility.isKeyFrame(dataBuffer);
    }
}
