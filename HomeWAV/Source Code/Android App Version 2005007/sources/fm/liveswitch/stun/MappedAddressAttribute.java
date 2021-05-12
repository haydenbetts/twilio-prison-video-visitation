package fm.liveswitch.stun;

import fm.liveswitch.AddressType;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.LocalNetwork;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.TransportAddress;

public class MappedAddressAttribute extends Attribute {
    private String _ipAddress;
    private int _port;

    public String getIPAddress() {
        return this._ipAddress;
    }

    public int getPort() {
        return this._port;
    }

    public int getTypeValue() {
        return Attribute.getMappedAddressType();
    }

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return Global.equals(LocalNetwork.getAddressType(getIPAddress()), AddressType.IPv4) ? 8 : 20;
    }

    private MappedAddressAttribute() {
    }

    public MappedAddressAttribute(String str, int i) {
        setIPAddress(TransportAddress.sanitizeIPAddress(str));
        setPort(i);
    }

    public static MappedAddressAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        AddressType readAddressType = Attribute.readAddressType(dataBuffer, i + 1);
        if (Global.equals(readAddressType, AddressType.getByAssignedValue(0))) {
            return null;
        }
        MappedAddressAttribute mappedAddressAttribute = new MappedAddressAttribute();
        mappedAddressAttribute.setPort(Attribute.readPort(dataBuffer, i + 2));
        mappedAddressAttribute.setIPAddress(Attribute.readIPAddress(dataBuffer, i + 4, readAddressType));
        return mappedAddressAttribute;
    }

    public void setIPAddress(String str) {
        this._ipAddress = str;
    }

    public void setPort(int i) {
        this._port = i;
    }

    public String toString() {
        return StringExtensions.format("MAPPED-ADDRESS {0}:{1}", getIPAddress(), IntegerExtensions.toString(Integer.valueOf(getPort())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        AddressType addressType = LocalNetwork.getAddressType(getIPAddress());
        dataBuffer.write8(0, i);
        Attribute.writeAddressType(dataBuffer, i + 1, addressType);
        super.writePort(dataBuffer, i + 2, getPort());
        super.writeIPAddress(dataBuffer, i + 4, addressType, getIPAddress());
    }
}
