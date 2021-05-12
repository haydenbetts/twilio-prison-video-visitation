package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class RequestedTransportAttribute extends Attribute {
    private static int __tcpProtocol = 6;
    private static int __udpProtocol = 17;
    private int _protocol;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 4;
    }

    public int getProtocol() {
        return this._protocol;
    }

    public static int getTcpProtocol() {
        return __tcpProtocol;
    }

    public int getTypeValue() {
        return Attribute.getRequestedTransportType();
    }

    public static int getUdpProtocol() {
        return __udpProtocol;
    }

    public static RequestedTransportAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        return new RequestedTransportAttribute(dataBuffer.read8(i));
    }

    public RequestedTransportAttribute(int i) {
        setProtocol(i);
    }

    public void setProtocol(int i) {
        this._protocol = i;
    }

    public String toString() {
        return StringExtensions.format("REQUESTED-TRANSPORT {0}", (Object) IntegerExtensions.toString(Integer.valueOf(getProtocol())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        IntegerHolder integerHolder = new IntegerHolder(i);
        dataBuffer.write8(getProtocol(), i, integerHolder);
        dataBuffer.write24(0, integerHolder.getValue());
    }
}
