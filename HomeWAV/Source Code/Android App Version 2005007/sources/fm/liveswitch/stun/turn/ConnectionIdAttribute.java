package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class ConnectionIdAttribute extends Attribute {
    private long _connectionId;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 4;
    }

    private ConnectionIdAttribute() {
    }

    public ConnectionIdAttribute(long j) {
        setConnectionId(j);
    }

    public long getConnectionId() {
        return this._connectionId;
    }

    public int getTypeValue() {
        return Attribute.getConnectionIdType();
    }

    public static ConnectionIdAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        ConnectionIdAttribute connectionIdAttribute = new ConnectionIdAttribute();
        connectionIdAttribute.setConnectionId(dataBuffer.read32(i));
        return connectionIdAttribute;
    }

    public void setConnectionId(long j) {
        this._connectionId = j;
    }

    public String toString() {
        return StringExtensions.format("CONNECTION-ID {0}", (Object) LongExtensions.toString(Long.valueOf(getConnectionId())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write32(getConnectionId(), i);
    }
}
