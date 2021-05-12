package fm.liveswitch.vp9;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.RtpPacketHeader;
import fm.liveswitch.VideoFragment;

public class Fragment extends VideoFragment {
    public Fragment(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer) {
        Packet wrap = Packet.wrap(dataBuffer);
        super.setFirst(wrap.getStartOfLayerFrame());
        super.setLast(rtpPacketHeader.getMarker());
        super.setBuffer(wrap.getPayload());
    }
}
