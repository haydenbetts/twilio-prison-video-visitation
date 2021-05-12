package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;

class VirtualNat {
    private int __mappedPortIndex = 0;
    private int[] __mappedPorts = new int[16384];
    private ArrayList<VirtualNatMapping> __mappings = new ArrayList<>();
    private Object __mappingsLock = new Object();
    private VirtualNatMode _mode;
    private int _timeout;

    /* access modifiers changed from: package-private */
    public void addMapping(IntegerHolder integerHolder, String str, int i, String str2, int i2) {
        TransportAddress transportAddress = new TransportAddress(str, i);
        TransportAddress transportAddress2 = new TransportAddress(str2, i2);
        synchronized (this.__mappingsLock) {
            if (!reuseMapping(integerHolder, transportAddress, transportAddress2)) {
                integerHolder.setValue(retainPort());
                VirtualNatMapping virtualNatMapping = new VirtualNatMapping(getTimeout(), new IActionDelegate1<VirtualNatMapping>() {
                    public String getId() {
                        return "fm.liveswitch.VirtualNat.removeMapping";
                    }

                    public void invoke(VirtualNatMapping virtualNatMapping) {
                        VirtualNat.this.removeMapping(virtualNatMapping);
                    }
                });
                virtualNatMapping.setInternalAddress(transportAddress);
                virtualNatMapping.setPort(integerHolder.getValue());
                virtualNatMapping.addExternalPermission(transportAddress2, getMode());
                this.__mappings.add(virtualNatMapping);
            }
        }
    }

    public boolean getMapping(int i, Holder<String> holder, IntegerHolder integerHolder, String str, int i2) {
        TransportAddress transportAddress = new TransportAddress(str, i2);
        VirtualNatMapping[] mappings = getMappings();
        int length = mappings.length;
        int i3 = 0;
        while (i3 < length) {
            VirtualNatMapping virtualNatMapping = mappings[i3];
            if (virtualNatMapping.getPort() != i || (!virtualNatMapping.getIsForwarding() && !virtualNatMapping.checkExternalPermission(transportAddress, getMode()))) {
                i3++;
            } else {
                holder.setValue(virtualNatMapping.getInternalAddress().getIPAddress());
                integerHolder.setValue(virtualNatMapping.getInternalAddress().getPort());
                return true;
            }
        }
        holder.setValue(null);
        integerHolder.setValue(0);
        return false;
    }

    public VirtualNatMapping[] getMappings() {
        VirtualNatMapping[] virtualNatMappingArr;
        synchronized (this.__mappingsLock) {
            virtualNatMappingArr = (VirtualNatMapping[]) this.__mappings.toArray(new VirtualNatMapping[0]);
        }
        return virtualNatMappingArr;
    }

    public VirtualNatMode getMode() {
        return this._mode;
    }

    public int getTimeout() {
        return this._timeout;
    }

    private void releasePort(int i) {
        this.__mappedPorts[i - SctpParameterType.ForwardTsnSupportedParameter] = i;
    }

    /* access modifiers changed from: private */
    public void removeMapping(VirtualNatMapping virtualNatMapping) {
        synchronized (this.__mappingsLock) {
            this.__mappings.remove(virtualNatMapping);
        }
    }

    private int retainPort() {
        for (int i = this.__mappedPortIndex; i < ArrayExtensions.getLength(this.__mappedPorts); i++) {
            int[] iArr = this.__mappedPorts;
            int i2 = iArr[i];
            if (i2 > 0) {
                iArr[i] = 0;
                this.__mappedPortIndex = i;
                return i2;
            }
        }
        for (int i3 = 0; i3 < this.__mappedPortIndex; i3++) {
            int[] iArr2 = this.__mappedPorts;
            int i4 = iArr2[i3];
            if (i4 > 0) {
                iArr2[i3] = 0;
                this.__mappedPortIndex = i3;
                return i4;
            }
        }
        throw new RuntimeException(new Exception("All ports in use!"));
    }

    private boolean reuseMapping(IntegerHolder integerHolder, TransportAddress transportAddress, TransportAddress transportAddress2) {
        Iterator<VirtualNatMapping> it = this.__mappings.iterator();
        while (it.hasNext()) {
            VirtualNatMapping next = it.next();
            if (next.tryReuse(transportAddress, transportAddress2, getMode())) {
                integerHolder.setValue(next.getPort());
                return true;
            }
        }
        integerHolder.setValue(0);
        return false;
    }

    private void setMode(VirtualNatMode virtualNatMode) {
        this._mode = virtualNatMode;
    }

    public void setTimeout(int i) {
        this._timeout = i;
    }

    public VirtualNat(VirtualNatMode virtualNatMode) {
        setMode(virtualNatMode);
        setTimeout(60000);
        for (int i = SctpParameterType.ForwardTsnSupportedParameter; i <= 65535; i++) {
            this.__mappedPorts[i - SctpParameterType.ForwardTsnSupportedParameter] = i;
        }
        int length = ArrayExtensions.getLength(this.__mappedPorts);
        while (length > 1) {
            int i2 = length - 1;
            int next = LockedRandomizer.next(length);
            int[] iArr = this.__mappedPorts;
            int i3 = iArr[i2];
            iArr[i2] = iArr[next];
            iArr[next] = i3;
            length = i2;
        }
    }
}
