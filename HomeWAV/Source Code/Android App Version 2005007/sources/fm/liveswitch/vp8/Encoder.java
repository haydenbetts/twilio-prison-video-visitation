package fm.liveswitch.vp8;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.vpx.Codec;

public class Encoder extends fm.liveswitch.vpx.Encoder {
    public String getLabel() {
        return "VP8 Encoder";
    }

    public Encoder() {
        super(new Format());
    }

    public Encoder(IVideoOutput iVideoOutput) {
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
