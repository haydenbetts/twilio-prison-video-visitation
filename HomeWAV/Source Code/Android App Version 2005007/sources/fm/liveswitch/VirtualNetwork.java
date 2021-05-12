package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

class VirtualNetwork extends VirtualDevice {
    private HashMap<String, VirtualDevice> __devices;
    private Object __devicesLock;
    private HashMap<String, VirtualDevice> __routes;
    private Object __routesLock;
    private int _dhcpIPAddress1;
    private int _dhcpIPAddress2;
    private int _dhcpIPAddress3;
    private int _dhcpIPAddress4End;
    private int _dhcpIPAddress4Start;
    private int _internalIPAddress1;
    private int _internalIPAddress2;
    private int _internalIPAddress3;
    private int _internalIPAddress4;
    private VirtualNat _nat;

    public VirtualAdapter addAdapter() {
        return (VirtualAdapter) addDevice(new VirtualAdapter());
    }

    public <TDevice extends VirtualDevice> TDevice addDevice(TDevice tdevice) {
        if (tdevice.getNetwork() == null) {
            maybeAssignIP(tdevice);
            synchronized (this.__devicesLock) {
                HashMapExtensions.add(this.__devices, tdevice.getIPAddress(), tdevice);
            }
            return tdevice;
        }
        throw new RuntimeException(new Exception("Device is currently attached to a network."));
    }

    public boolean connect(String str, String str2, int i, ProtocolType protocolType) {
        if (Global.equals(protocolType, ProtocolType.Tcp) || Global.equals(protocolType, ProtocolType.Tls)) {
            for (VirtualDevice iPAddress : getDevices()) {
                if (Global.equals(iPAddress.getIPAddress(), str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean doProcessSend(VirtualPacket virtualPacket) {
        Holder holder = new Holder(null);
        IntegerHolder integerHolder = new IntegerHolder(0);
        boolean mapping = getNat().getMapping(virtualPacket.getDestinationPort(), holder, integerHolder, virtualPacket.getSourceIPAddress(), virtualPacket.getSourcePort());
        String str = (String) holder.getValue();
        int value = integerHolder.getValue();
        if (mapping) {
            virtualPacket.setDestinationIPAddress(str);
            virtualPacket.setDestinationPort(value);
            VirtualDevice device = getDevice(virtualPacket.getDestinationIPAddress());
            if (device != null) {
                device.processSend(virtualPacket);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean doRaiseSend(VirtualPacket virtualPacket) {
        VirtualDevice device = getDevice(virtualPacket.getDestinationIPAddress());
        if (device != null) {
            device.processSend(virtualPacket);
            return true;
        } else if (super.getNetwork() == null) {
            return false;
        } else {
            IntegerHolder integerHolder = new IntegerHolder(0);
            getNat().addMapping(integerHolder, virtualPacket.getSourceIPAddress(), virtualPacket.getSourcePort(), virtualPacket.getDestinationIPAddress(), virtualPacket.getDestinationPort());
            int value = integerHolder.getValue();
            virtualPacket.setSourceIPAddress(super.getIPAddress());
            virtualPacket.setSourcePort(value);
            return super.getNetwork().raiseSend(virtualPacket);
        }
    }

    private VirtualDevice getDevice(String str) {
        VirtualDevice virtualDevice;
        synchronized (this.__devicesLock) {
            Holder holder = new Holder(null);
            HashMapExtensions.tryGetValue(this.__devices, str, holder);
            virtualDevice = (VirtualDevice) holder.getValue();
        }
        return virtualDevice;
    }

    public VirtualDevice[] getDevices() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.__devicesLock) {
            for (VirtualDevice add : HashMapExtensions.getValues(this.__devices)) {
                arrayList.add(add);
            }
        }
        return (VirtualDevice[]) arrayList.toArray(new VirtualDevice[0]);
    }

    public int getDhcpIPAddress1() {
        return this._dhcpIPAddress1;
    }

    public int getDhcpIPAddress2() {
        return this._dhcpIPAddress2;
    }

    public int getDhcpIPAddress3() {
        return this._dhcpIPAddress3;
    }

    public int getDhcpIPAddress4End() {
        return this._dhcpIPAddress4End;
    }

    public int getDhcpIPAddress4Start() {
        return this._dhcpIPAddress4Start;
    }

    public int getInternalIPAddress1() {
        return this._internalIPAddress1;
    }

    public int getInternalIPAddress2() {
        return this._internalIPAddress2;
    }

    public int getInternalIPAddress3() {
        return this._internalIPAddress3;
    }

    public int getInternalIPAddress4() {
        return this._internalIPAddress4;
    }

    public VirtualNat getNat() {
        return this._nat;
    }

    public VirtualNatMode getNatMode() {
        return getNat().getMode();
    }

    private void maybeAssignIP(VirtualDevice virtualDevice) {
        synchronized (this.__routesLock) {
            if (virtualDevice.getUseDhcp()) {
                int i = 0;
                int dhcpIPAddress4Start = getDhcpIPAddress4Start();
                while (i == 0 && dhcpIPAddress4Start <= getDhcpIPAddress4End()) {
                    if (this.__routes.containsKey(IntegerExtensions.toString(Integer.valueOf(dhcpIPAddress4Start)))) {
                        dhcpIPAddress4Start++;
                    } else {
                        i = dhcpIPAddress4Start;
                    }
                }
                if (i != 0) {
                    virtualDevice.setAddressAndNetwork(getDhcpIPAddress1(), getDhcpIPAddress2(), getDhcpIPAddress3(), i, this);
                    HashMapExtensions.set(HashMapExtensions.getItem(this.__routes), IntegerExtensions.toString(Integer.valueOf(i)), virtualDevice);
                } else {
                    throw new RuntimeException(new Exception("No more DHCP addresses are available."));
                }
            } else if (virtualDevice.getIPAddress1() == getDhcpIPAddress1() && virtualDevice.getIPAddress2() == getDhcpIPAddress2() && virtualDevice.getIPAddress3() == getDhcpIPAddress3()) {
                if (!this.__routes.containsKey(IntegerExtensions.toString(Integer.valueOf(virtualDevice.getIPAddress4())))) {
                    virtualDevice.setNetwork(this);
                    HashMapExtensions.set(HashMapExtensions.getItem(this.__routes), IntegerExtensions.toString(Integer.valueOf(virtualDevice.getIPAddress4())), virtualDevice);
                } else {
                    throw new RuntimeException(new Exception("Device IP address is already in use."));
                }
            }
        }
    }

    private void maybeUnassignIP(VirtualDevice virtualDevice) {
        synchronized (this.__routesLock) {
            HashMapExtensions.remove(this.__routes, IntegerExtensions.toString(Integer.valueOf(virtualDevice.getIPAddress4())));
            if (virtualDevice.getUseDhcp()) {
                virtualDevice.setAddressAndNetwork(0, 0, 0, 0, (VirtualNetwork) null);
            } else {
                virtualDevice.setNetwork((VirtualNetwork) null);
            }
        }
    }

    public <TDevice extends VirtualDevice> boolean removeDevice(TDevice tdevice) {
        synchronized (this.__devicesLock) {
            if (!HashMapExtensions.remove(this.__devices, tdevice.getIPAddress())) {
                return false;
            }
            maybeUnassignIP(tdevice);
            return true;
        }
    }

    private void setDhcpIPAddress1(int i) {
        this._dhcpIPAddress1 = i;
    }

    private void setDhcpIPAddress2(int i) {
        this._dhcpIPAddress2 = i;
    }

    private void setDhcpIPAddress3(int i) {
        this._dhcpIPAddress3 = i;
    }

    private void setDhcpIPAddress4End(int i) {
        this._dhcpIPAddress4End = i;
    }

    private void setDhcpIPAddress4Start(int i) {
        this._dhcpIPAddress4Start = i;
    }

    /* access modifiers changed from: package-private */
    public void setInternalIPAddress1(int i) {
        this._internalIPAddress1 = i;
    }

    /* access modifiers changed from: package-private */
    public void setInternalIPAddress2(int i) {
        this._internalIPAddress2 = i;
    }

    /* access modifiers changed from: package-private */
    public void setInternalIPAddress3(int i) {
        this._internalIPAddress3 = i;
    }

    /* access modifiers changed from: package-private */
    public void setInternalIPAddress4(int i) {
        this._internalIPAddress4 = i;
    }

    private void setNat(VirtualNat virtualNat) {
        this._nat = virtualNat;
    }

    public VirtualNetwork() {
        this(VirtualNatMode.FullCone);
    }

    public VirtualNetwork(VirtualNatMode virtualNatMode) {
        this(virtualNatMode, LockedRandomizer.next(256));
    }

    public VirtualNetwork(VirtualNatMode virtualNatMode, int i) {
        this(virtualNatMode, i, LockedRandomizer.next(256));
    }

    public VirtualNetwork(VirtualNatMode virtualNatMode, int i, int i2) {
        this(virtualNatMode, i, i2, LockedRandomizer.next(256));
    }

    public VirtualNetwork(VirtualNatMode virtualNatMode, int i, int i2, int i3) {
        this.__devices = new HashMap<>();
        this.__devicesLock = new Object();
        this.__routes = new HashMap<>();
        this.__routesLock = new Object();
        setNat(new VirtualNat(virtualNatMode));
        setDhcpIPAddress1(i);
        setDhcpIPAddress2(i2);
        setDhcpIPAddress3(i3);
        setDhcpIPAddress4Start(100);
        setDhcpIPAddress4End(199);
        setInternalIPAddress1(i);
        setInternalIPAddress2(i2);
        setInternalIPAddress3(i3);
        setInternalIPAddress4(1);
    }
}
