package fm.liveswitch;

import java.util.HashMap;

class VirtualAdapter extends VirtualDevice {
    private ManagedConcurrentDictionary<String, Object> __boundPorts = new ManagedConcurrentDictionary<>();
    private HashMap<String, VirtualReceivePort> __receivePorts = new HashMap<>();
    private Object __receivePortsLock = new Object();

    public boolean bind(ProtocolType protocolType, String str, int i, BooleanHolder booleanHolder, Holder<String> holder, IntegerHolder integerHolder) {
        if (str == null) {
            str = super.getIPAddress();
        }
        if (!Global.equals(str, super.getIPAddress())) {
            booleanHolder.setValue(false);
            holder.setValue(null);
            integerHolder.setValue(0);
            return false;
        }
        if (i <= 0) {
            i = bind(protocolType);
            if (i <= 0) {
                booleanHolder.setValue(true);
                holder.setValue(null);
                integerHolder.setValue(0);
                return false;
            }
        } else if (!bind(protocolType, i)) {
            booleanHolder.setValue(true);
            holder.setValue(null);
            integerHolder.setValue(0);
            return false;
        }
        booleanHolder.setValue(false);
        holder.setValue(str);
        integerHolder.setValue(i);
        return true;
    }

    public int bind(ProtocolType protocolType) {
        for (int i = SctpParameterType.ForwardTsnSupportedParameter; i <= 65535; i++) {
            if (bind(protocolType, i)) {
                return i;
            }
        }
        return 0;
    }

    public boolean bind(ProtocolType protocolType, int i) {
        return this.__boundPorts.tryAdd(getKey(protocolType, i), new Object());
    }

    /* access modifiers changed from: protected */
    public boolean doProcessSend(VirtualPacket virtualPacket) {
        if (Global.equals(virtualPacket.getType(), VirtualPacketType.Connect) || Global.equals(virtualPacket.getProtocolType(), ProtocolType.Udp)) {
            getReceivePort(virtualPacket.getDestinationPort(), (String) null, 0, virtualPacket.getProtocolType()).send(virtualPacket);
            return true;
        }
        getReceivePort(virtualPacket.getDestinationPort(), virtualPacket.getSourceIPAddress(), virtualPacket.getSourcePort(), virtualPacket.getProtocolType()).send(virtualPacket);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean doRaiseSend(VirtualPacket virtualPacket) {
        return super.getNetwork() != null && super.getNetwork().raiseSend(virtualPacket);
    }

    private String getKey(ProtocolType protocolType, int i) {
        if (Global.equals(protocolType, ProtocolType.Tls)) {
            protocolType = ProtocolType.Tcp;
        }
        return StringExtensions.format("{0}-{1}", protocolType.toString(), IntegerExtensions.toString(Integer.valueOf(i)));
    }

    private VirtualReceivePort getReceivePort(int i, String str, int i2, ProtocolType protocolType) {
        VirtualReceivePort virtualReceivePort;
        if (str == null) {
            str = "null";
        }
        String format = StringExtensions.format("{0}-{1}-{2}-{3}-{4}", new Object[]{super.getIPAddress(), IntegerExtensions.toString(Integer.valueOf(i)), str, IntegerExtensions.toString(Integer.valueOf(i2)), protocolType.toString()});
        synchronized (this.__receivePortsLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__receivePorts, format, holder);
            virtualReceivePort = (VirtualReceivePort) holder.getValue();
            if (!tryGetValue) {
                HashMap<String, VirtualReceivePort> item = HashMapExtensions.getItem(this.__receivePorts);
                virtualReceivePort = new VirtualReceivePort();
                HashMapExtensions.set(item, format, virtualReceivePort);
            }
        }
        return virtualReceivePort;
    }

    public VirtualPacket receive(int i, String str, int i2, ProtocolType protocolType, int i3, VirtualPacketType virtualPacketType) {
        return getReceivePort(i, str, i2, protocolType).receive(i3, virtualPacketType);
    }

    public boolean send(VirtualPacket virtualPacket) {
        return super.raiseSend(virtualPacket);
    }

    public boolean unbind(ProtocolType protocolType, int i) {
        Holder holder = new Holder(null);
        boolean tryRemove = this.__boundPorts.tryRemove(getKey(protocolType, i), holder);
        holder.getValue();
        return tryRemove;
    }
}
