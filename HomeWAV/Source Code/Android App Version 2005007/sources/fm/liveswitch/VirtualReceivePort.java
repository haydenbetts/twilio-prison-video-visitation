package fm.liveswitch;

import java.util.HashMap;

class VirtualReceivePort {
    private HashMap<VirtualPacketType, VirtualReceiveList> __lists;

    public VirtualPacket receive(int i, VirtualPacketType virtualPacketType) {
        return HashMapExtensions.getItem(this.__lists).get(virtualPacketType).receive(i);
    }

    public void send(VirtualPacket virtualPacket) {
        HashMapExtensions.getItem(this.__lists).get(virtualPacket.getType()).send(virtualPacket);
    }

    public VirtualReceivePort() {
        HashMap<VirtualPacketType, VirtualReceiveList> hashMap = new HashMap<>();
        HashMapExtensions.add(hashMap, VirtualPacketType.Data, new VirtualReceiveList());
        HashMapExtensions.add(hashMap, VirtualPacketType.DataAck, new VirtualReceiveList());
        HashMapExtensions.add(hashMap, VirtualPacketType.Connect, new VirtualReceiveList());
        HashMapExtensions.add(hashMap, VirtualPacketType.ConnectAck, new VirtualReceiveList());
        this.__lists = hashMap;
    }
}
