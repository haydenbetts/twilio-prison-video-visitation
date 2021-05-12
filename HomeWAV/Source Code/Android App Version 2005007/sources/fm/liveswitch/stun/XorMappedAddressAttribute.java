package fm.liveswitch.stun;

import fm.liveswitch.AddressType;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.LocalNetwork;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.TransportAddress;

public class XorMappedAddressAttribute extends Attribute {
    private String _ipAddress;
    private int _port;
    private DataBuffer _transactionId;

    public String getIPAddress() {
        return this._ipAddress;
    }

    public int getPort() {
        return this._port;
    }

    public DataBuffer getTransactionId() {
        return this._transactionId;
    }

    public int getTypeValue() {
        return Attribute.getXorMappedAddressType();
    }

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return Global.equals(LocalNetwork.getAddressType(getIPAddress()), AddressType.IPv4) ? 8 : 20;
    }

    public static XorMappedAddressAttribute readValueFrom(DataBuffer dataBuffer, int i, DataBuffer dataBuffer2) {
        AddressType readAddressType = Attribute.readAddressType(dataBuffer, i + 1);
        if (Global.equals(readAddressType, AddressType.getByAssignedValue(0))) {
            return null;
        }
        XorMappedAddressAttribute xorMappedAddressAttribute = new XorMappedAddressAttribute();
        xorMappedAddressAttribute.setTransactionId(dataBuffer2);
        xorMappedAddressAttribute.setPort(Attribute.readXorPort(dataBuffer, i + 2, dataBuffer2));
        xorMappedAddressAttribute.setIPAddress(Attribute.readXorIPAddress(dataBuffer, i + 4, readAddressType, dataBuffer2));
        return xorMappedAddressAttribute;
    }

    public void setIPAddress(String str) {
        this._ipAddress = str;
    }

    public void setPort(int i) {
        this._port = i;
    }

    private void setTransactionId(DataBuffer dataBuffer) {
        this._transactionId = dataBuffer;
    }

    public String toString() {
        return StringExtensions.format("XOR-MAPPED-ADDRESS {0}:{1}", getIPAddress(), IntegerExtensions.toString(Integer.valueOf(getPort())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        AddressType addressType = LocalNetwork.getAddressType(getIPAddress());
        dataBuffer.write8(0, i);
        Attribute.writeAddressType(dataBuffer, i + 1, addressType);
        super.writeXorPort(dataBuffer, i + 2, getPort(), getTransactionId());
        super.writeXorIPAddress(dataBuffer, i + 4, addressType, getIPAddress(), getTransactionId());
    }

    private XorMappedAddressAttribute() {
    }

    public XorMappedAddressAttribute(String str, int i, DataBuffer dataBuffer) {
        setIPAddress(TransportAddress.sanitizeIPAddress(str));
        setPort(i);
        setTransactionId(dataBuffer);
    }
}
