package fm.liveswitch.stun;

import fm.liveswitch.AddressType;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.LocalNetwork;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.TransportAddress;

public class AlternateServerAttribute extends Attribute {
    private String _ipAddress;
    private int _port;

    private AlternateServerAttribute() {
    }

    public AlternateServerAttribute(String str, int i) {
        setIPAddress(TransportAddress.sanitizeIPAddress(str));
        setPort(i);
    }

    public String getIPAddress() {
        return this._ipAddress;
    }

    public int getPort() {
        return this._port;
    }

    public int getTypeValue() {
        return Attribute.getAlternateServerType();
    }

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return Global.equals(LocalNetwork.getAddressType(getIPAddress()), AddressType.IPv4) ? 8 : 20;
    }

    public static AlternateServerAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        AddressType readAddressType = Attribute.readAddressType(dataBuffer, i + 1);
        if (Global.equals(readAddressType, AddressType.getByAssignedValue(0))) {
            return null;
        }
        AlternateServerAttribute alternateServerAttribute = new AlternateServerAttribute();
        alternateServerAttribute.setPort(Attribute.readPort(dataBuffer, i + 2));
        alternateServerAttribute.setIPAddress(Attribute.readIPAddress(dataBuffer, i + 4, readAddressType));
        return alternateServerAttribute;
    }

    public void setIPAddress(String str) {
        this._ipAddress = str;
    }

    public void setPort(int i) {
        this._port = i;
    }

    public String toString() {
        return StringExtensions.format("ALTERNATE-SERVER {0}:{1}", getIPAddress(), IntegerExtensions.toString(Integer.valueOf(getPort())));
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
