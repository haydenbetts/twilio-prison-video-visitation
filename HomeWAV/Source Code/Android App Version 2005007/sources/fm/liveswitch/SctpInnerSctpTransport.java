package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class SctpInnerSctpTransport extends Transport {
    private boolean __active;
    private Object __lock;
    /* access modifiers changed from: private */
    public List<IAction1<DataBuffer>> __onReceive = new ArrayList();
    private Scheduler __scheduler;
    private SctpInnerSctpTransportMode _mode;
    private IAction1<DataBuffer> _onReceive = null;

    public void addOnReceive(IAction1<DataBuffer> iAction1) {
        if (iAction1 != null) {
            if (this._onReceive == null) {
                this._onReceive = new IAction1<DataBuffer>() {
                    public void invoke(DataBuffer dataBuffer) {
                        Iterator it = new ArrayList(SctpInnerSctpTransport.this.__onReceive).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(dataBuffer);
                        }
                    }
                };
            }
            this.__onReceive.add(iAction1);
        }
    }

    public boolean getIsClosed() {
        return !this.__active;
    }

    public SctpInnerSctpTransportMode getMode() {
        return this._mode;
    }

    public void removeOnReceive(IAction1<DataBuffer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceive, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceive.remove(iAction1);
        if (this.__onReceive.size() == 0) {
            this._onReceive = null;
        }
    }

    public SctpInnerSctpTransport(Object obj, Scheduler scheduler, SctpInnerSctpTransportMode sctpInnerSctpTransportMode) {
        this.__lock = obj;
        this.__scheduler = scheduler;
        setMode(sctpInnerSctpTransportMode);
    }

    public void send(DataBuffer dataBuffer) {
        IAction1<DataBuffer> iAction1;
        SctpPacket parseBytes;
        if (this.__active) {
            try {
                boolean z = true;
                if (!Global.equals(getMode(), SctpInnerSctpTransportMode.DeliverAllPackets)) {
                    if (!Global.equals(getMode(), SctpInnerSctpTransportMode.FilterOutINITChunks) || (parseBytes = SctpPacket.parseBytes(dataBuffer.toArray())) == null || parseBytes.getChunks()[0].getType() == SctpChunkType.getInitiation()) {
                        z = false;
                    }
                }
                if (z && (iAction1 = this._onReceive) != null) {
                    iAction1.invoke(dataBuffer);
                }
            } catch (Exception unused) {
            }
        }
    }

    public void setMode(SctpInnerSctpTransportMode sctpInnerSctpTransportMode) {
        this._mode = sctpInnerSctpTransportMode;
    }

    public boolean start() {
        synchronized (this.__lock) {
            if (this.__active) {
                return false;
            }
            this.__active = true;
            return true;
        }
    }

    public boolean stop() {
        synchronized (this.__lock) {
            if (!this.__active) {
                return false;
            }
            this.__active = false;
            return true;
        }
    }
}
