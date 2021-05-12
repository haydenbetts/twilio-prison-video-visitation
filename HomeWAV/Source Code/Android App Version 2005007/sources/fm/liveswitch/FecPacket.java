package fm.liveswitch;

import java.util.ArrayList;

class FecPacket extends FecSortablePacket {
    private ArrayList<FecProtectedPacket> _protectedPacketList;
    private FecRawPacket _raw;
    private long _synchronizationSource;

    public FecPacket() {
        setProtectedPacketList(new ArrayList());
    }

    public ArrayList<FecProtectedPacket> getProtectedPacketList() {
        return this._protectedPacketList;
    }

    public FecRawPacket getRaw() {
        return this._raw;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    public void setProtectedPacketList(ArrayList<FecProtectedPacket> arrayList) {
        this._protectedPacketList = arrayList;
    }

    public void setRaw(FecRawPacket fecRawPacket) {
        this._raw = fecRawPacket;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }
}
