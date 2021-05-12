package fm.liveswitch.stun;

import fm.liveswitch.AddressType;
import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.Global;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.LocalNetwork;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.TransportAddress;
import fm.liveswitch.stun.ice.ControlledAttribute;
import fm.liveswitch.stun.ice.ControllingAttribute;
import fm.liveswitch.stun.ice.PriorityAttribute;
import fm.liveswitch.stun.ice.UseCandidateAttribute;
import fm.liveswitch.stun.turn.ChannelNumberAttribute;
import fm.liveswitch.stun.turn.ConnectionIdAttribute;
import fm.liveswitch.stun.turn.DataAttribute;
import fm.liveswitch.stun.turn.DontFragmentAttribute;
import fm.liveswitch.stun.turn.EvenPortAttribute;
import fm.liveswitch.stun.turn.LifetimeAttribute;
import fm.liveswitch.stun.turn.RequestedAddressFamilyAttribute;
import fm.liveswitch.stun.turn.RequestedTransportAttribute;
import fm.liveswitch.stun.turn.ReservationTokenAttribute;
import fm.liveswitch.stun.turn.XorPeerAddressAttribute;
import fm.liveswitch.stun.turn.XorRelayedAddressAttribute;
import org.bytedeco.ffmpeg.global.avcodec;

public abstract class Attribute {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(Attribute.class);
    private static String _blankIPv4Address = "0.0.0.0";
    private static byte[] _blankIPv4AddressBytes = {0, 0, 0, 0};
    private static String _blankIPv6Address = "0:0:0:0:0:0:0:0";
    private static byte[] _blankIPv6AddressBytes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static int getAlternateServerType() {
        return avcodec.AV_CODEC_ID_FITS;
    }

    public static int getChannelNumberType() {
        return 12;
    }

    public static int getConnectionIdType() {
        return 42;
    }

    public static int getControlledType() {
        return avcodec.AV_CODEC_ID_HYMT;
    }

    public static int getControllingType() {
        return avcodec.AV_CODEC_ID_ARBC;
    }

    public static int getDataType() {
        return 19;
    }

    public static int getDontFragmentType() {
        return 26;
    }

    public static int getErrorCodeType() {
        return 9;
    }

    public static int getEvenPortType() {
        return 24;
    }

    public static int getFingerprintType() {
        return avcodec.AV_CODEC_ID_RASC;
    }

    public static int getLifetimeType() {
        return 13;
    }

    public static int getMappedAddressType() {
        return 1;
    }

    public static int getMessageIntegrityType() {
        return 8;
    }

    public static int getNonceType() {
        return 21;
    }

    public static int getPriorityType() {
        return 36;
    }

    public static int getRealmType() {
        return 20;
    }

    public static int getRequestedAddressFamilyType() {
        return 23;
    }

    public static int getRequestedTransportType() {
        return 25;
    }

    public static int getReservationTokenType() {
        return 34;
    }

    public static int getSoftwareType() {
        return avcodec.AV_CODEC_ID_GDV;
    }

    public static int getTransactionTransmitCounterType() {
        return avcodec.AV_CODEC_ID_PROSUMER;
    }

    public static int getUnknownAttributesType() {
        return 10;
    }

    public static int getUseCandidateType() {
        return 37;
    }

    public static int getUsernameType() {
        return 6;
    }

    public static int getXorMappedAddressType() {
        return 32;
    }

    public static int getXorPeerAddressType() {
        return 18;
    }

    public static int getXorRelayedAddressType() {
        return 22;
    }

    public abstract int getTypeValue();

    /* access modifiers changed from: protected */
    public abstract int getValueLength();

    /* access modifiers changed from: protected */
    public abstract void writeValueTo(DataBuffer dataBuffer, int i);

    protected Attribute() {
    }

    private static Attribute createAttributeFromValue(int i, DataBuffer dataBuffer, int i2, int i3, DataBuffer dataBuffer2, DataBuffer dataBuffer3) {
        if (i == getAlternateServerType()) {
            return AlternateServerAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getTransactionTransmitCounterType()) {
            return TransactionTransmitCounterAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getErrorCodeType()) {
            return ErrorCodeAttribute.readValueFrom(dataBuffer, i2, i3);
        }
        if (i == getFingerprintType()) {
            return FingerprintAttribute.readValueFrom(dataBuffer, i2, dataBuffer3);
        }
        if (i == getMappedAddressType()) {
            return MappedAddressAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getMessageIntegrityType()) {
            return MessageIntegrityAttribute.readValueFrom(dataBuffer, i2, i3, dataBuffer3);
        }
        if (i == getNonceType()) {
            return NonceAttribute.readValueFrom(dataBuffer, i2, i3);
        }
        if (i == getRealmType()) {
            return RealmAttribute.readValueFrom(dataBuffer, i2, i3);
        }
        if (i == getSoftwareType()) {
            return SoftwareAttribute.readValueFrom(dataBuffer, i2, i3);
        }
        if (i == getUnknownAttributesType()) {
            return UnknownAttributesAttribute.readValueFrom(dataBuffer, i2, i3);
        }
        if (i == getUsernameType()) {
            return UsernameAttribute.readValueFrom(dataBuffer, i2, i3);
        }
        if (i == getXorMappedAddressType()) {
            return XorMappedAddressAttribute.readValueFrom(dataBuffer, i2, dataBuffer2);
        }
        if (i == getControlledType()) {
            return ControlledAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getControllingType()) {
            return ControllingAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getPriorityType()) {
            return PriorityAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getUseCandidateType()) {
            return UseCandidateAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getChannelNumberType()) {
            return ChannelNumberAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getConnectionIdType()) {
            return ConnectionIdAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getDataType()) {
            return DataAttribute.readValueFrom(dataBuffer, i2, i3);
        }
        if (i == getDontFragmentType()) {
            return DontFragmentAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getEvenPortType()) {
            return EvenPortAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getLifetimeType()) {
            return LifetimeAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getRequestedTransportType()) {
            return RequestedTransportAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getReservationTokenType()) {
            return ReservationTokenAttribute.readValueFrom(dataBuffer, i2);
        }
        if (i == getXorPeerAddressType()) {
            return XorPeerAddressAttribute.readValueFrom(dataBuffer, i2, dataBuffer2);
        }
        if (i == getXorRelayedAddressType()) {
            return XorRelayedAddressAttribute.readValueFrom(dataBuffer, i2, dataBuffer2);
        }
        if (i == getRequestedAddressFamilyType()) {
            return RequestedAddressFamilyAttribute.readValueFrom(dataBuffer, i2);
        }
        return null;
    }

    public int getLength() {
        return getValueLength() + 4 + getPaddingLength(getValueLength());
    }

    public int getPaddingLength(int i) {
        return (4 - (i % 4)) % 4;
    }

    public static int nextLength(DataBuffer dataBuffer, int i) {
        if (i + 4 > dataBuffer.getLength()) {
            return -1;
        }
        int read16 = dataBuffer.read16(i + 2) + 4;
        while (read16 % 4 != 0) {
            read16++;
        }
        return read16;
    }

    protected static AddressType readAddressType(DataBuffer dataBuffer, int i) {
        if (dataBuffer.read8(i) == AddressFamily.getIPv4()) {
            return AddressType.IPv4;
        }
        if (dataBuffer.read8(i) == AddressFamily.getIPv6()) {
            return AddressType.IPv6;
        }
        return AddressType.getByAssignedValue(0);
    }

    public static Attribute readFrom(DataBuffer dataBuffer, int i, IntegerHolder integerHolder, DataBuffer dataBuffer2, DataBuffer dataBuffer3) {
        integerHolder.setValue(nextLength(dataBuffer, i) + i);
        return readFrom(dataBuffer, i, dataBuffer2, dataBuffer3);
    }

    public static Attribute readFrom(DataBuffer dataBuffer, int i, DataBuffer dataBuffer2, DataBuffer dataBuffer3) {
        IntegerHolder integerHolder = new IntegerHolder(i);
        int read16 = dataBuffer.read16(i, integerHolder);
        int value = integerHolder.getValue();
        IntegerHolder integerHolder2 = new IntegerHolder(value);
        DataBuffer dataBuffer4 = dataBuffer;
        Attribute createAttributeFromValue = createAttributeFromValue(read16, dataBuffer4, integerHolder2.getValue(), dataBuffer.read16(value, integerHolder2), dataBuffer2, dataBuffer3);
        if (createAttributeFromValue != null) {
            return createAttributeFromValue;
        }
        return null;
    }

    protected static String readIPAddress(DataBuffer dataBuffer, int i, AddressType addressType) {
        try {
            return TransportAddress.sanitizeIPAddress(LocalNetwork.getAddress(dataBuffer.subset(i, Global.equals(addressType, AddressType.IPv4) ? 4 : 16).toArray()));
        } catch (Exception unused) {
            if (Global.equals(addressType, AddressType.IPv4)) {
                return _blankIPv4Address;
            }
            return _blankIPv6Address;
        }
    }

    protected static int readPort(DataBuffer dataBuffer, int i) {
        return dataBuffer.read16(i);
    }

    protected static String readXorIPAddress(DataBuffer dataBuffer, int i, AddressType addressType, DataBuffer dataBuffer2) {
        String str;
        int i2 = Global.equals(addressType, AddressType.IPv4) ? 4 : 16;
        DataBuffer take = __dataBufferPool.take(i2);
        take.write(dataBuffer.subset(i, i2), 0);
        xorIPAddress(take, dataBuffer2);
        String str2 = StringExtensions.empty;
        try {
            str = TransportAddress.sanitizeIPAddress(LocalNetwork.getAddress(take.toArray()));
        } catch (Exception unused) {
            if (Global.equals(addressType, AddressType.IPv4)) {
                str = _blankIPv4Address;
            } else {
                str = _blankIPv6Address;
            }
        }
        take.free();
        return str;
    }

    protected static int readXorPort(DataBuffer dataBuffer, int i, DataBuffer dataBuffer2) {
        DataBuffer take = __dataBufferPool.take(2);
        take.write(dataBuffer.subset(i, 2), 0);
        xorPort(take, dataBuffer2);
        int read16 = take.read16(0);
        take.free();
        return read16;
    }

    protected static void writeAddressType(DataBuffer dataBuffer, int i, AddressType addressType) {
        if (Global.equals(addressType, AddressType.IPv4)) {
            dataBuffer.write8(AddressFamily.getIPv4(), i);
        } else if (Global.equals(addressType, AddressType.IPv6)) {
            dataBuffer.write8(AddressFamily.getIPv6(), i);
        }
    }

    /* access modifiers changed from: protected */
    public void writeIPAddress(DataBuffer dataBuffer, int i, AddressType addressType, String str) {
        byte[] bArr;
        try {
            bArr = LocalNetwork.getAddressBytes(str);
        } catch (Exception unused) {
            if (Global.equals(addressType, AddressType.IPv4)) {
                bArr = _blankIPv4AddressBytes;
            } else {
                bArr = _blankIPv6AddressBytes;
            }
        }
        dataBuffer.writeBytes(bArr, i);
    }

    /* access modifiers changed from: protected */
    public void writePort(DataBuffer dataBuffer, int i, int i2) {
        dataBuffer.write16(i2, i);
    }

    public void writeTo(DataBuffer dataBuffer, int i) {
        int typeValue = getTypeValue();
        int valueLength = getValueLength();
        int paddingLength = getPaddingLength(valueLength);
        IntegerHolder integerHolder = new IntegerHolder(i);
        dataBuffer.write16(typeValue, i, integerHolder);
        int value = integerHolder.getValue();
        IntegerHolder integerHolder2 = new IntegerHolder(value);
        dataBuffer.write16(valueLength, value, integerHolder2);
        int value2 = integerHolder2.getValue();
        writeValueTo(dataBuffer, value2);
        int i2 = value2 + valueLength;
        if (paddingLength > 0) {
            dataBuffer.set((byte) 0, i2, paddingLength);
        }
    }

    public void writeTo(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(getLength() + i);
        writeTo(dataBuffer, i);
    }

    /* access modifiers changed from: protected */
    public void writeXorIPAddress(DataBuffer dataBuffer, int i, AddressType addressType, String str, DataBuffer dataBuffer2) {
        writeIPAddress(dataBuffer, i, addressType, str);
        xorIPAddress(dataBuffer.subset(i, Global.equals(addressType, AddressType.IPv4) ? 4 : 16), dataBuffer2);
    }

    /* access modifiers changed from: protected */
    public void writeXorPort(DataBuffer dataBuffer, int i, int i2, DataBuffer dataBuffer2) {
        writePort(dataBuffer, i, i2);
        xorPort(dataBuffer.subset(i, 2), dataBuffer2);
    }

    private static void xorIPAddress(DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        for (int i = 0; i < ArrayExtensions.getLength(Message.getMagicCookieBytes()); i++) {
            dataBuffer.write8(dataBuffer.read8(i) ^ Message.getMagicCookieBytes()[i], i);
        }
        if (dataBuffer.getLength() > ArrayExtensions.getLength(Message.getMagicCookieBytes())) {
            for (int i2 = 0; i2 < dataBuffer2.getLength(); i2++) {
                dataBuffer.write8(dataBuffer.read8(ArrayExtensions.getLength(Message.getMagicCookieBytes()) + i2) ^ dataBuffer2.read8(i2), ArrayExtensions.getLength(Message.getMagicCookieBytes()) + i2);
            }
        }
    }

    private static void xorPort(DataBuffer dataBuffer, DataBuffer dataBuffer2) {
        for (int i = 0; i < dataBuffer.getLength(); i++) {
            dataBuffer.write8(dataBuffer.read8(i) ^ Message.getMagicCookieBytes()[i], i);
        }
    }
}
