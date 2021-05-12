package fm.liveswitch.h264;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.RtpPacketHeader;
import fm.liveswitch.VideoFragment;

public class Fragment extends VideoFragment {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(Fragment.class);

    public void destroy() {
        super.getBuffer().free();
    }

    public Fragment(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer) {
        int i;
        Packet wrap = Packet.wrap(dataBuffer);
        if (wrap != null) {
            if (wrap.getNaluType() == 28) {
                super.setFirst(wrap.getFragmentStart());
                super.setLast(wrap.getFragmentEnd());
            } else {
                super.setFirst(true);
                super.setLast(true);
            }
            int i2 = 0;
            for (Nalu nalu : wrap.getNalus()) {
                if (super.getFirst()) {
                    i2 += Nalu.getStartCode().getLength();
                    i = nalu.getBuffer().getLength();
                } else {
                    i = nalu.getBuffer().getLength() - 1;
                }
                i2 += i;
            }
            super.setBuffer(__dataBufferPool.take(i2));
            int i3 = 0;
            for (Nalu nalu2 : wrap.getNalus()) {
                if (super.getFirst()) {
                    IntegerHolder integerHolder = new IntegerHolder(i3);
                    super.getBuffer().write(Nalu.getStartCode(), i3, integerHolder);
                    int value = integerHolder.getValue();
                    IntegerHolder integerHolder2 = new IntegerHolder(value);
                    super.getBuffer().write(nalu2.getBuffer(), value, integerHolder2);
                    i3 = integerHolder2.getValue();
                } else {
                    IntegerHolder integerHolder3 = new IntegerHolder(i3);
                    super.getBuffer().write(nalu2.getBuffer().subset(1), i3, integerHolder3);
                    i3 = integerHolder3.getValue();
                }
            }
            return;
        }
        throw new RuntimeException(new Exception("Could not parse H.264 packet."));
    }
}
