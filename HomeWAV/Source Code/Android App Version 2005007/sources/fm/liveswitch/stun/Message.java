package fm.liveswitch.stun;

import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.Global;
import fm.liveswitch.Guid;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.stun.ice.ControlledAttribute;
import fm.liveswitch.stun.ice.ControllingAttribute;
import fm.liveswitch.stun.ice.PriorityAttribute;
import fm.liveswitch.stun.ice.UseCandidateAttribute;
import fm.liveswitch.stun.turn.AllocateRequest;
import fm.liveswitch.stun.turn.AllocateResponse;
import fm.liveswitch.stun.turn.ChannelBindRequest;
import fm.liveswitch.stun.turn.ChannelBindResponse;
import fm.liveswitch.stun.turn.ChannelNumberAttribute;
import fm.liveswitch.stun.turn.ConnectRequest;
import fm.liveswitch.stun.turn.ConnectResponse;
import fm.liveswitch.stun.turn.ConnectionAttemptIndication;
import fm.liveswitch.stun.turn.ConnectionBindRequest;
import fm.liveswitch.stun.turn.ConnectionBindResponse;
import fm.liveswitch.stun.turn.ConnectionIdAttribute;
import fm.liveswitch.stun.turn.CreatePermissionRequest;
import fm.liveswitch.stun.turn.CreatePermissionResponse;
import fm.liveswitch.stun.turn.DataAttribute;
import fm.liveswitch.stun.turn.DataIndication;
import fm.liveswitch.stun.turn.DontFragmentAttribute;
import fm.liveswitch.stun.turn.EvenPortAttribute;
import fm.liveswitch.stun.turn.LifetimeAttribute;
import fm.liveswitch.stun.turn.RefreshRequest;
import fm.liveswitch.stun.turn.RefreshResponse;
import fm.liveswitch.stun.turn.RequestedAddressFamilyAttribute;
import fm.liveswitch.stun.turn.RequestedTransportAttribute;
import fm.liveswitch.stun.turn.ReservationTokenAttribute;
import fm.liveswitch.stun.turn.SendIndication;
import fm.liveswitch.stun.turn.XorPeerAddressAttribute;
import fm.liveswitch.stun.turn.XorRelayedAddressAttribute;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Message {
    private static byte[] _magicCookieBytes;
    private ArrayList<Attribute> _attributeList;
    private MessageType _messageType;
    private DataBuffer _transactionId;

    public static int getAllocateMethod() {
        return 3;
    }

    public static int getBindingMethod() {
        return 1;
    }

    public static int getChannelBindMethod() {
        return 9;
    }

    public static int getConnectMethod() {
        return 10;
    }

    public static int getConnectionAttemptMethod() {
        return 12;
    }

    public static int getConnectionBindMethod() {
        return 11;
    }

    public static int getCreatePermissionMethod() {
        return 8;
    }

    public static int getDataMethod() {
        return 7;
    }

    public static long getMagicCookieValue() {
        return 554869826;
    }

    public static int getRefreshMethod() {
        return 4;
    }

    public static int getSendMethod() {
        return 6;
    }

    public abstract int getMethod();

    public static Message createMessage(int i, MessageType messageType, DataBuffer dataBuffer) {
        if (i == getBindingMethod()) {
            if (Global.equals(messageType, MessageType.Request)) {
                return new BindingRequest();
            }
            if (Global.equals(messageType, MessageType.Indication)) {
                return new BindingIndication();
            }
            if (Global.equals(messageType, MessageType.SuccessResponse)) {
                return new BindingResponse(dataBuffer, true);
            }
            if (Global.equals(messageType, MessageType.ErrorResponse)) {
                return new BindingResponse(dataBuffer, false);
            }
            return null;
        } else if (i == getAllocateMethod()) {
            if (Global.equals(messageType, MessageType.Request)) {
                return new AllocateRequest();
            }
            if (Global.equals(messageType, MessageType.SuccessResponse)) {
                return new AllocateResponse(dataBuffer, true);
            }
            if (Global.equals(messageType, MessageType.ErrorResponse)) {
                return new AllocateResponse(dataBuffer, false);
            }
            return null;
        } else if (i == getRefreshMethod()) {
            if (Global.equals(messageType, MessageType.Request)) {
                return new RefreshRequest();
            }
            if (Global.equals(messageType, MessageType.SuccessResponse)) {
                return new RefreshResponse(dataBuffer, true);
            }
            if (Global.equals(messageType, MessageType.ErrorResponse)) {
                return new RefreshResponse(dataBuffer, false);
            }
            return null;
        } else if (i == getSendMethod()) {
            if (Global.equals(messageType, MessageType.Indication)) {
                return new SendIndication();
            }
            return null;
        } else if (i == getDataMethod()) {
            if (Global.equals(messageType, MessageType.Indication)) {
                return new DataIndication();
            }
            return null;
        } else if (i == getCreatePermissionMethod()) {
            if (Global.equals(messageType, MessageType.Request)) {
                return new CreatePermissionRequest();
            }
            if (Global.equals(messageType, MessageType.SuccessResponse)) {
                return new CreatePermissionResponse(dataBuffer, true);
            }
            if (Global.equals(messageType, MessageType.ErrorResponse)) {
                return new CreatePermissionResponse(dataBuffer, false);
            }
            return null;
        } else if (i == getChannelBindMethod()) {
            if (Global.equals(messageType, MessageType.Request)) {
                return new ChannelBindRequest();
            }
            if (Global.equals(messageType, MessageType.SuccessResponse)) {
                return new ChannelBindResponse(dataBuffer, true);
            }
            if (Global.equals(messageType, MessageType.ErrorResponse)) {
                return new ChannelBindResponse(dataBuffer, false);
            }
            return null;
        } else if (i == getConnectMethod()) {
            if (Global.equals(messageType, MessageType.Request)) {
                return new ConnectRequest();
            }
            if (Global.equals(messageType, MessageType.SuccessResponse)) {
                return new ConnectResponse(dataBuffer, true);
            }
            if (Global.equals(messageType, MessageType.ErrorResponse)) {
                return new ConnectResponse(dataBuffer, false);
            }
            return null;
        } else if (i == getConnectionBindMethod()) {
            if (Global.equals(messageType, MessageType.Request)) {
                return new ConnectionBindRequest();
            }
            if (Global.equals(messageType, MessageType.SuccessResponse)) {
                return new ConnectionBindResponse(dataBuffer, true);
            }
            if (Global.equals(messageType, MessageType.ErrorResponse)) {
                return new ConnectionBindResponse(dataBuffer, false);
            }
            return null;
        } else if (i != getConnectionAttemptMethod() || !Global.equals(messageType, MessageType.Indication)) {
            return null;
        } else {
            return new ConnectionAttemptIndication();
        }
    }

    protected static DataBuffer generateTransactionId() {
        return DataBuffer.wrap(Guid.newGuid().toByteArray(), 0, 12);
    }

    public AlternateServerAttribute getAlternateServer() {
        return (AlternateServerAttribute) getAttribute(Attribute.getAlternateServerType());
    }

    private Attribute getAttribute(int i) {
        Iterator<Attribute> it = getAttributeList().iterator();
        while (it.hasNext()) {
            Attribute next = it.next();
            if (next.getTypeValue() == i) {
                return next;
            }
        }
        return null;
    }

    private ArrayList<Attribute> getAttributeList() {
        return this._attributeList;
    }

    public Attribute[] getAttributes() {
        return (Attribute[]) getAttributeList().toArray(new Attribute[0]);
    }

    public ChannelNumberAttribute getChannelNumber() {
        return (ChannelNumberAttribute) getAttribute(Attribute.getChannelNumberType());
    }

    public ConnectionIdAttribute getConnectionId() {
        return (ConnectionIdAttribute) getAttribute(Attribute.getConnectionIdType());
    }

    public DataAttribute getData() {
        return (DataAttribute) getAttribute(Attribute.getDataType());
    }

    public DontFragmentAttribute getDontFragment() {
        return (DontFragmentAttribute) getAttribute(Attribute.getDontFragmentType());
    }

    public ErrorCodeAttribute getErrorCode() {
        return (ErrorCodeAttribute) getAttribute(Attribute.getErrorCodeType());
    }

    public EvenPortAttribute getEvenPort() {
        return (EvenPortAttribute) getAttribute(Attribute.getEvenPortType());
    }

    public FingerprintAttribute getFingerprint() {
        return (FingerprintAttribute) getAttribute(Attribute.getFingerprintType());
    }

    public ControlledAttribute getIceControlled() {
        return (ControlledAttribute) getAttribute(Attribute.getControlledType());
    }

    public ControllingAttribute getIceControlling() {
        return (ControllingAttribute) getAttribute(Attribute.getControllingType());
    }

    public int getLength() {
        return getPayloadLength() + 20;
    }

    public LifetimeAttribute getLifetime() {
        return (LifetimeAttribute) getAttribute(Attribute.getLifetimeType());
    }

    public static byte[] getMagicCookieBytes() {
        return _magicCookieBytes;
    }

    public MappedAddressAttribute getMappedAddress() {
        return (MappedAddressAttribute) getAttribute(Attribute.getMappedAddressType());
    }

    public MessageIntegrityAttribute getMessageIntegrity() {
        return (MessageIntegrityAttribute) getAttribute(Attribute.getMessageIntegrityType());
    }

    public MessageType getMessageType() {
        return this._messageType;
    }

    public NonceAttribute getNonce() {
        return (NonceAttribute) getAttribute(Attribute.getNonceType());
    }

    public int getPayloadLength() {
        Iterator<Attribute> it = getAttributeList().iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getLength();
        }
        return i;
    }

    public PriorityAttribute getPriority() {
        return (PriorityAttribute) getAttribute(Attribute.getPriorityType());
    }

    public RealmAttribute getRealm() {
        return (RealmAttribute) getAttribute(Attribute.getRealmType());
    }

    public RequestedAddressFamilyAttribute getRequestedAddressFamily() {
        return (RequestedAddressFamilyAttribute) getAttribute(Attribute.getRequestedAddressFamilyType());
    }

    public RequestedTransportAttribute getRequestedTransport() {
        return (RequestedTransportAttribute) getAttribute(Attribute.getRequestedTransportType());
    }

    public ReservationTokenAttribute getReservationToken() {
        return (ReservationTokenAttribute) getAttribute(Attribute.getReservationTokenType());
    }

    public SoftwareAttribute getSoftware() {
        return (SoftwareAttribute) getAttribute(Attribute.getSoftwareType());
    }

    public DataBuffer getTransactionId() {
        return this._transactionId;
    }

    public TransactionTransmitCounterAttribute getTransactionTransmitCounter() {
        return (TransactionTransmitCounterAttribute) getAttribute(Attribute.getTransactionTransmitCounterType());
    }

    public UnknownAttributesAttribute getUnknownAttributes() {
        return (UnknownAttributesAttribute) getAttribute(Attribute.getUnknownAttributesType());
    }

    public UseCandidateAttribute getUseCandidate() {
        return (UseCandidateAttribute) getAttribute(Attribute.getUseCandidateType());
    }

    public UsernameAttribute getUsername() {
        return (UsernameAttribute) getAttribute(Attribute.getUsernameType());
    }

    public XorMappedAddressAttribute getXorMappedAddress() {
        return (XorMappedAddressAttribute) getAttribute(Attribute.getXorMappedAddressType());
    }

    public XorPeerAddressAttribute getXorPeerAddress() {
        return (XorPeerAddressAttribute) getAttribute(Attribute.getXorPeerAddressType());
    }

    public XorRelayedAddressAttribute getXorRelayedAddress() {
        return (XorRelayedAddressAttribute) getAttribute(Attribute.getXorRelayedAddressType());
    }

    static {
        setMagicCookieBytes(new byte[]{33, 18, -92, 66});
    }

    protected Message(MessageType messageType, DataBuffer dataBuffer) {
        if (dataBuffer.getLength() == 12) {
            setMessageType(messageType);
            setTransactionId(dataBuffer);
            setAttributeList(new ArrayList());
            return;
        }
        throw new RuntimeException(new Exception("transactionId must be exactly 12 bytes."));
    }

    public static Message readFrom(DataBuffer dataBuffer) {
        return readFrom(dataBuffer, 0);
    }

    public static Message readFrom(DataBuffer dataBuffer, int i) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        Message readFrom = readFrom(dataBuffer, i, integerHolder);
        integerHolder.getValue();
        return readFrom;
    }

    public static Message readFrom(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        if (dataBuffer == null || dataBuffer.getLength() < 20 || dataBuffer.read8(i) > 1) {
            integerHolder.setValue(0);
            return null;
        }
        IntegerHolder integerHolder2 = new IntegerHolder(i);
        int read8 = dataBuffer.read8(i, integerHolder2);
        int value = integerHolder2.getValue();
        int i2 = read8 & 1;
        int read82 = dataBuffer.read8(value) & 15;
        IntegerHolder integerHolder3 = new IntegerHolder(value);
        int read83 = dataBuffer.read8(value, integerHolder3);
        int value2 = integerHolder3.getValue();
        int i3 = read83 & 16;
        MessageType messageType = MessageType.Request;
        if (i2 == 1 && i3 == 0) {
            messageType = MessageType.SuccessResponse;
        } else if (i2 == 1 && i3 == 16) {
            messageType = MessageType.ErrorResponse;
        } else if (i2 == 0 && i3 == 16) {
            messageType = MessageType.Indication;
        }
        IntegerHolder integerHolder4 = new IntegerHolder(value2);
        int read16 = dataBuffer.read16(value2, integerHolder4);
        int value3 = integerHolder4.getValue();
        integerHolder.setValue(read16 + 20);
        if (integerHolder.getValue() > dataBuffer.getLength()) {
            return null;
        }
        IntegerHolder integerHolder5 = new IntegerHolder(value3);
        long read32 = dataBuffer.read32(value3, integerHolder5);
        int value4 = integerHolder5.getValue();
        if (read32 != getMagicCookieValue()) {
            return null;
        }
        DataBuffer subset = dataBuffer.subset(value4, 12);
        int i4 = value4 + 12;
        Message createMessage = createMessage(read82, messageType, subset);
        createMessage.setTransactionId(subset);
        while (i4 - i != integerHolder.getValue() && Attribute.nextLength(dataBuffer, i4) != -1) {
            IntegerHolder integerHolder6 = new IntegerHolder(i4);
            Attribute readFrom = Attribute.readFrom(dataBuffer, i4, integerHolder6, subset, dataBuffer.subset(i, i4));
            int value5 = integerHolder6.getValue();
            if (readFrom != null) {
                createMessage.setAttribute(readFrom);
            }
            i4 = value5;
        }
        return createMessage;
    }

    public void setAlternateServer(AlternateServerAttribute alternateServerAttribute) {
        setAttribute(alternateServerAttribute);
    }

    private void setAttribute(Attribute attribute) {
        int i = 0;
        while (i < ArrayListExtensions.getCount(getAttributeList())) {
            if (((Attribute) ArrayListExtensions.getItem(getAttributeList()).get(i)).getTypeValue() == attribute.getTypeValue()) {
                ArrayListExtensions.removeAt(getAttributeList(), i);
                i--;
            }
            i++;
        }
        getAttributeList().add(attribute);
    }

    private void setAttributeList(ArrayList<Attribute> arrayList) {
        this._attributeList = arrayList;
    }

    public void setChannelNumber(ChannelNumberAttribute channelNumberAttribute) {
        setAttribute(channelNumberAttribute);
    }

    public void setConnectionId(ConnectionIdAttribute connectionIdAttribute) {
        setAttribute(connectionIdAttribute);
    }

    public void setData(DataAttribute dataAttribute) {
        setAttribute(dataAttribute);
    }

    public void setDontFragment(DontFragmentAttribute dontFragmentAttribute) {
        setAttribute(dontFragmentAttribute);
    }

    public void setErrorCode(ErrorCodeAttribute errorCodeAttribute) {
        setAttribute(errorCodeAttribute);
    }

    public void setEvenPort(EvenPortAttribute evenPortAttribute) {
        setAttribute(evenPortAttribute);
    }

    public void setFingerprint(FingerprintAttribute fingerprintAttribute) {
        setAttribute(fingerprintAttribute);
    }

    public void setIceControlled(ControlledAttribute controlledAttribute) {
        setAttribute(controlledAttribute);
    }

    public void setIceControlling(ControllingAttribute controllingAttribute) {
        setAttribute(controllingAttribute);
    }

    public void setLifetime(LifetimeAttribute lifetimeAttribute) {
        setAttribute(lifetimeAttribute);
    }

    private static void setMagicCookieBytes(byte[] bArr) {
        _magicCookieBytes = bArr;
    }

    public void setMappedAddress(MappedAddressAttribute mappedAddressAttribute) {
        setAttribute(mappedAddressAttribute);
    }

    public void setMessageIntegrity(MessageIntegrityAttribute messageIntegrityAttribute) {
        setAttribute(messageIntegrityAttribute);
    }

    private void setMessageType(MessageType messageType) {
        this._messageType = messageType;
    }

    public void setNonce(NonceAttribute nonceAttribute) {
        setAttribute(nonceAttribute);
    }

    public void setPriority(PriorityAttribute priorityAttribute) {
        setAttribute(priorityAttribute);
    }

    public void setRealm(RealmAttribute realmAttribute) {
        setAttribute(realmAttribute);
    }

    public void setRequestedAddressFamily(RequestedAddressFamilyAttribute requestedAddressFamilyAttribute) {
        setAttribute(requestedAddressFamilyAttribute);
    }

    public void setRequestedTransport(RequestedTransportAttribute requestedTransportAttribute) {
        setAttribute(requestedTransportAttribute);
    }

    public void setReservationToken(ReservationTokenAttribute reservationTokenAttribute) {
        setAttribute(reservationTokenAttribute);
    }

    public void setSoftware(SoftwareAttribute softwareAttribute) {
        setAttribute(softwareAttribute);
    }

    private void setTransactionId(DataBuffer dataBuffer) {
        this._transactionId = dataBuffer;
    }

    public void setTransactionTransmitCounter(TransactionTransmitCounterAttribute transactionTransmitCounterAttribute) {
        setAttribute(transactionTransmitCounterAttribute);
    }

    public void setUnknownAttributes(UnknownAttributesAttribute unknownAttributesAttribute) {
        setAttribute(unknownAttributesAttribute);
    }

    public void setUseCandidate(UseCandidateAttribute useCandidateAttribute) {
        setAttribute(useCandidateAttribute);
    }

    public void setUsername(UsernameAttribute usernameAttribute) {
        setAttribute(usernameAttribute);
    }

    public void setXorMappedAddress(XorMappedAddressAttribute xorMappedAddressAttribute) {
        setAttribute(xorMappedAddressAttribute);
    }

    public void setXorPeerAddress(XorPeerAddressAttribute xorPeerAddressAttribute) {
        setAttribute(xorPeerAddressAttribute);
    }

    public void setXorRelayedAddress(XorRelayedAddressAttribute xorRelayedAddressAttribute) {
        setAttribute(xorRelayedAddressAttribute);
    }

    public void writeTo(DataBuffer dataBuffer) {
        writeTo(dataBuffer, 0);
    }

    public void writeTo(DataBuffer dataBuffer, int i) {
        int i2;
        int i3;
        if (Global.equals(getMessageType(), MessageType.Request) || Global.equals(getMessageType(), MessageType.Indication)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            dataBuffer.write8(0, i, integerHolder);
            i2 = integerHolder.getValue();
        } else {
            IntegerHolder integerHolder2 = new IntegerHolder(i);
            dataBuffer.write8(1, i, integerHolder2);
            i2 = integerHolder2.getValue();
        }
        if (Global.equals(getMessageType(), MessageType.Request) || Global.equals(getMessageType(), MessageType.SuccessResponse)) {
            IntegerHolder integerHolder3 = new IntegerHolder(i2);
            dataBuffer.write8(getMethod(), i2, integerHolder3);
            i3 = integerHolder3.getValue();
        } else {
            IntegerHolder integerHolder4 = new IntegerHolder(i2);
            dataBuffer.write8(getMethod() | 16, i2, integerHolder4);
            i3 = integerHolder4.getValue();
        }
        IntegerHolder integerHolder5 = new IntegerHolder(i3);
        dataBuffer.write16(getPayloadLength(), i3, integerHolder5);
        int value = integerHolder5.getValue();
        IntegerHolder integerHolder6 = new IntegerHolder(value);
        dataBuffer.write32(getMagicCookieValue(), value, integerHolder6);
        int value2 = integerHolder6.getValue();
        IntegerHolder integerHolder7 = new IntegerHolder(value2);
        dataBuffer.write(getTransactionId(), value2, integerHolder7);
        int value3 = integerHolder7.getValue();
        MessageIntegrityAttribute messageIntegrity = getMessageIntegrity();
        FingerprintAttribute fingerprint = getFingerprint();
        Iterator<Attribute> it = getAttributeList().iterator();
        while (it.hasNext()) {
            Attribute next = it.next();
            if (!Global.equals(next, messageIntegrity) && !Global.equals(next, fingerprint)) {
                IntegerHolder integerHolder8 = new IntegerHolder(value3);
                next.writeTo(dataBuffer, value3, integerHolder8);
                value3 = integerHolder8.getValue();
            }
        }
        if (messageIntegrity != null) {
            messageIntegrity.setMessageBuffer(dataBuffer.subset(i, value3));
            IntegerHolder integerHolder9 = new IntegerHolder(value3);
            messageIntegrity.writeTo(dataBuffer, value3, integerHolder9);
            value3 = integerHolder9.getValue();
        }
        if (fingerprint != null) {
            fingerprint.setMessageBuffer(dataBuffer.subset(i, value3));
            IntegerHolder integerHolder10 = new IntegerHolder(value3);
            fingerprint.writeTo(dataBuffer, value3, integerHolder10);
            integerHolder10.getValue();
        }
    }

    public void writeTo(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(getLength() + i);
        writeTo(dataBuffer, i);
    }
}
