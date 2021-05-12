package fm.liveswitch.vp8;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IVideoOutput;
import fm.liveswitch.RtpPacketHeader;
import fm.liveswitch.VideoDepacketizer;
import fm.liveswitch.VideoFormat;

public class Depacketizer extends VideoDepacketizer<Fragment> {
    /* access modifiers changed from: protected */
    public void doDestroy() {
    }

    public String getLabel() {
        return "VP8 Depacketizer";
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

    /* access modifiers changed from: protected */
    public boolean isGapAllowed(Fragment fragment, Fragment fragment2) {
        return Utility.isGapAllowed(fragment, fragment2);
    }

    /* access modifiers changed from: protected */
    public boolean isKeyFrame(DataBuffer dataBuffer) {
        return Utility.isKeyFrame(dataBuffer);
    }
}
