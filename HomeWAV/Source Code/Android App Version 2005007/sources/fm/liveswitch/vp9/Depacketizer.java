package fm.liveswitch.vp9;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.RtpPacketHeader;
import fm.liveswitch.VideoDepacketizer;
import fm.liveswitch.VideoFormat;

public class Depacketizer extends VideoDepacketizer<Fragment> {
    private boolean _sequenceNumberingViolated;

    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "VP9 Depacketizer";
    }

    public Depacketizer() {
        super((VideoFormat) new Format());
    }

    public Depacketizer(IVideoOutput iVideoOutput) {
        this();
        super.addInput(iVideoOutput);
    }

    /* access modifiers changed from: protected */
    public Fragment doCreateFragment(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer) {
        return new Fragment(rtpPacketHeader, dataBuffer);
    }

    public boolean getSequenceNumberingViolated() {
        return this._sequenceNumberingViolated;
    }

    /* access modifiers changed from: protected */
    public boolean isKeyFrame(DataBuffer dataBuffer) {
        return Utility.isKeyFrame(dataBuffer);
    }

    private void setSequenceNumberingViolated(boolean z) {
        this._sequenceNumberingViolated = z;
    }
}
