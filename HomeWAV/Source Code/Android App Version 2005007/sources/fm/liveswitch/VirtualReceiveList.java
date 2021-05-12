package fm.liveswitch;

import java.util.ArrayList;

class VirtualReceiveList {
    private ManagedCondition __condition = new ManagedCondition();
    private ArrayList<VirtualPacket> __packets = new ArrayList<>();

    /* access modifiers changed from: private */
    public CompareResult comparePackets(VirtualPacket virtualPacket, VirtualPacket virtualPacket2) {
        if (virtualPacket.getMinReceiveTimestamp() < virtualPacket2.getMinReceiveTimestamp()) {
            return CompareResult.Positive;
        }
        if (virtualPacket.getMinReceiveTimestamp() > virtualPacket2.getMinReceiveTimestamp()) {
            return CompareResult.Negative;
        }
        if (virtualPacket.getSendTimestamp() < virtualPacket2.getSendTimestamp()) {
            return CompareResult.Positive;
        }
        if (virtualPacket.getSendTimestamp() > virtualPacket2.getSendTimestamp()) {
            return CompareResult.Negative;
        }
        return CompareResult.Equal;
    }

    private VirtualPacket getPacket() {
        int count = ArrayListExtensions.getCount(this.__packets) - 1;
        VirtualPacket virtualPacket = (VirtualPacket) ArrayListExtensions.getItem(this.__packets).get(count);
        ArrayListExtensions.removeAt(this.__packets, count);
        return virtualPacket;
    }

    private boolean getPacketReadyForReceive() {
        if (ArrayListExtensions.getCount(this.__packets) == 0) {
            return false;
        }
        return ((VirtualPacket) ArrayListExtensions.getItem(this.__packets).get(ArrayListExtensions.getCount(this.__packets) - 1)).getReadyForReceive();
    }

    public VirtualPacket receive(int i) {
        synchronized (this.__condition) {
            if (getPacketReadyForReceive()) {
                VirtualPacket packet = getPacket();
                return packet;
            } else if (!this.__condition.halt(i)) {
                return null;
            } else {
                VirtualPacket packet2 = getPacket();
                return packet2;
            }
        }
    }

    public void send(VirtualPacket virtualPacket) {
        synchronized (this.__condition) {
            virtualPacket.setSendTimestamp(ManagedStopwatch.getTimestamp());
            if (virtualPacket.getDelay() > 0) {
                virtualPacket.setMinReceiveTimestamp(virtualPacket.getSendTimestamp() + ((long) (virtualPacket.getDelay() * Constants.getTicksPerMillisecond())));
            }
            this.__packets.add(virtualPacket);
            Sort.quickSort(this.__packets, new IFunctionDelegate2<VirtualPacket, VirtualPacket, CompareResult>() {
                public String getId() {
                    return "fm.liveswitch.VirtualReceiveList.comparePackets";
                }

                public CompareResult invoke(VirtualPacket virtualPacket, VirtualPacket virtualPacket2) {
                    return VirtualReceiveList.this.comparePackets(virtualPacket, virtualPacket2);
                }
            });
            if (getPacketReadyForReceive()) {
                this.__condition.pulse();
            }
        }
    }
}
