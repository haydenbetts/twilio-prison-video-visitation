package fm.liveswitch;

import com.google.android.exoplayer2.DefaultLoadControl;
import fm.liveswitch.stun.Attribute;
import fm.liveswitch.stun.BadRequestError;
import fm.liveswitch.stun.Error;
import fm.liveswitch.stun.FingerprintAttribute;
import fm.liveswitch.stun.MappedAddressAttribute;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageIntegrityAttribute;
import fm.liveswitch.stun.NonceAttribute;
import fm.liveswitch.stun.RealmAttribute;
import fm.liveswitch.stun.ServerError;
import fm.liveswitch.stun.StaleNonceError;
import fm.liveswitch.stun.TransactionTransmitCounterAttribute;
import fm.liveswitch.stun.UnauthorizedStunError;
import fm.liveswitch.stun.UsernameAttribute;
import fm.liveswitch.stun.Utility;
import fm.liveswitch.stun.XorMappedAddressAttribute;
import fm.liveswitch.stun.turn.AllocateRequest;
import fm.liveswitch.stun.turn.AllocateResponse;
import fm.liveswitch.stun.turn.AllocationMismatchError;
import fm.liveswitch.stun.turn.ChannelBindRequest;
import fm.liveswitch.stun.turn.ChannelBindResponse;
import fm.liveswitch.stun.turn.ChannelNumberAttribute;
import fm.liveswitch.stun.turn.CreatePermissionRequest;
import fm.liveswitch.stun.turn.CreatePermissionResponse;
import fm.liveswitch.stun.turn.DataAttribute;
import fm.liveswitch.stun.turn.DataIndication;
import fm.liveswitch.stun.turn.EvenPortAttribute;
import fm.liveswitch.stun.turn.LifetimeAttribute;
import fm.liveswitch.stun.turn.RefreshRequest;
import fm.liveswitch.stun.turn.RequestedTransportAttribute;
import fm.liveswitch.stun.turn.ReservationTokenAttribute;
import fm.liveswitch.stun.turn.SendIndication;
import fm.liveswitch.stun.turn.UnsupportedTransportProtocolError;
import fm.liveswitch.stun.turn.WrongCredentialsError;
import fm.liveswitch.stun.turn.XorPeerAddressAttribute;
import fm.liveswitch.stun.turn.XorRelayedAddressAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.joda.time.DateTimeConstants;

public class TurnServer extends StunServer {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(TurnServer.class);
    private static Object _nextUdpPortLock = new Object();
    private int __allocationPortMax = 65535;
    private int __allocationPortMin = SctpParameterType.ForwardTsnSupportedParameter;
    private long __defaultAllocateLifetime = 600;
    private long __defaultRefreshLifetime = 600;
    private long __maxAllocateLifetime = 3600;
    private long __maxRefreshLifetime = 3600;
    private HashMap<String, TurnAllocation> _allocationsByClientAddress = new HashMap<>();
    private HashMap<Integer, TurnAllocation> _allocationsByLocalPort = new HashMap<>();
    private Object _allocationsLock = new Object();
    private IFunction1<TurnAuthArgs, TurnAuthResult> _authCallback = null;
    private boolean _disableBypass;
    private boolean _forceDefaultAllocateLifetime;
    private boolean _forceDefaultRefreshLifetime;
    private int _nextUdpPort = 0;
    private String _nonce;
    private TimeoutTimer _nonceTimer;
    private String _realm;
    private byte _requestBitmask = BitAssistant.castByte(192);
    private HashMap<String, DatagramSocket> _reservations = new HashMap<>();
    private Object _reservationsLock = new Object();
    private boolean _staleNonceSecurity;

    /* access modifiers changed from: protected */
    public String getLabel() {
        return "TURN";
    }

    public long getMinAllocateLifetime() {
        return 600;
    }

    public long getMinRefreshLifetime() {
        return 600;
    }

    private void allocateUdpSocket(ServerAddress serverAddress, EvenPortAttribute evenPortAttribute, TransportAddress transportAddress, Holder<DatagramSocket> holder, Holder<DataBuffer> holder2) {
        do {
        } while (!tryAllocateUdpSocket(serverAddress, evenPortAttribute, transportAddress, holder, holder2));
    }

    /* access modifiers changed from: package-private */
    public Error authenticate(Message message, TransportAddress transportAddress, TurnAuthOperation turnAuthOperation, Holder<byte[]> holder) {
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean tryAuthenticate = tryAuthenticate(message, turnAuthOperation, booleanHolder, holder);
        boolean value = booleanHolder.getValue();
        if (tryAuthenticate) {
            return null;
        }
        if (!value && Log.getIsWarnEnabled()) {
            Log.warn(StringExtensions.format("Could not process {0} request for {1} - authentication failed.", getOperationName(turnAuthOperation), transportAddress.toString()));
        }
        return new UnauthorizedStunError();
    }

    private boolean authorize(TurnAllocation turnAllocation, Message message) {
        UsernameAttribute username = message.getUsername();
        RealmAttribute realm = message.getRealm();
        MessageIntegrityAttribute messageIntegrity = message.getMessageIntegrity();
        if (username == null || realm == null || messageIntegrity == null || !Global.equals(turnAllocation.getUsername(), username.getValue()) || !Global.equals(turnAllocation.getRealm(), realm.getValue())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean checkNonce(Message message, BooleanHolder booleanHolder) {
        if (!getStaleNonceSecurity()) {
            booleanHolder.setValue(false);
            return true;
        }
        NonceAttribute nonce = message.getNonce();
        if (nonce == null) {
            booleanHolder.setValue(true);
            return false;
        }
        booleanHolder.setValue(false);
        return Global.equals(nonce.getValue(), getNonce());
    }

    /* access modifiers changed from: package-private */
    public Error checkNonce(Message message, TransportAddress transportAddress, TurnAuthOperation turnAuthOperation) {
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean checkNonce = checkNonce(message, booleanHolder);
        boolean value = booleanHolder.getValue();
        if (checkNonce) {
            return null;
        }
        if (value) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not process {0} request for {1} - missing nonce.", getOperationName(turnAuthOperation), transportAddress.toString()));
            }
            return new UnauthorizedStunError();
        }
        if (Log.getIsDebugEnabled()) {
            Log.debug(StringExtensions.format("Could not process {0} request for {1} - stale nonce.", getOperationName(turnAuthOperation), transportAddress.toString()));
        }
        return new StaleNonceError();
    }

    private AllocateResponse createAllocateResponse(AllocateRequest allocateRequest, TurnAllocation turnAllocation, TransportAddress transportAddress) {
        AllocateResponse allocateResponse = new AllocateResponse(allocateRequest.getTransactionId(), true);
        allocateResponse.setXorRelayedAddress(new XorRelayedAddressAttribute(turnAllocation.getPublicIPAddress() != null ? turnAllocation.getPublicIPAddress() : turnAllocation.getLocalIPAddress(), turnAllocation.getLocalPort(), allocateRequest.getTransactionId()));
        allocateResponse.setLifetime(new LifetimeAttribute(turnAllocation.getLastLifetime()));
        if (turnAllocation.getReservation() != null) {
            allocateResponse.setReservationToken(new ReservationTokenAttribute(turnAllocation.getReservation()));
        }
        allocateResponse.setXorMappedAddress(new XorMappedAddressAttribute(transportAddress.getIPAddress(), transportAddress.getPort(), allocateRequest.getTransactionId()));
        allocateResponse.setMappedAddress(new MappedAddressAttribute(transportAddress.getIPAddress(), transportAddress.getPort()));
        return allocateResponse;
    }

    /* access modifiers changed from: protected */
    public Message createExceptionResponse(Message message, TransportAddress transportAddress, Error error) {
        Message createExceptionResponse = super.createExceptionResponse(message, transportAddress, error);
        int stunCode = error.getStunCode();
        if (stunCode == 401 || stunCode == 438) {
            if (getNonce() != null) {
                createExceptionResponse.setNonce(new NonceAttribute(getNonce()));
            }
            if (getRealm() != null) {
                createExceptionResponse.setRealm(new RealmAttribute(getRealm()));
            }
        }
        return createExceptionResponse;
    }

    private void createUdpSocket(ServerAddress serverAddress, Holder<DatagramSocket> holder) {
        boolean equals = Global.equals(LocalNetwork.getAddressType(serverAddress.getIPAddress()), AddressType.IPv6);
        holder.setValue(null);
        if (super.getCreateDatagramSocket() != null) {
            holder.setValue(super.getCreateDatagramSocket().invoke(new DatagramSocketCreateArgs(equals)));
        }
        if (holder.getValue() == null) {
            holder.setValue(new UdpSocket(equals));
        }
        holder.getValue().setPublicIPAddress(serverAddress.getPublicIPAddress());
        BooleanHolder booleanHolder = new BooleanHolder(false);
        boolean bind = holder.getValue().bind(serverAddress.getIPAddress(), serverAddress.getPort(), booleanHolder);
        boolean value = booleanHolder.getValue();
        if (!bind) {
            try {
                holder.getValue().close();
            } catch (Exception unused) {
            }
            holder.setValue(null);
            if (value) {
                Log.warn("Could not bind UDP allocation socket. Socket address is in use.");
            } else {
                Log.warn("Could not bind UDP allocation socket.");
            }
        }
    }

    /* access modifiers changed from: private */
    public void expireReservedUdpSocket(Object obj) {
        DatagramSocket datagramSocket;
        String str = (String) obj;
        synchronized (this._reservationsLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._reservations, str, holder);
            datagramSocket = (DatagramSocket) holder.getValue();
            if (tryGetValue) {
                HashMapExtensions.remove(this._reservations, str);
            }
        }
        if (datagramSocket != null) {
            try {
                datagramSocket.close();
                if (!Log.getIsWarnEnabled()) {
                    return;
                }
            } catch (Exception unused) {
                if (!Log.getIsWarnEnabled()) {
                    return;
                }
            } catch (Throwable th) {
                if (Log.getIsWarnEnabled()) {
                    Log.warn(StringExtensions.format("UDP socket reservation {0} has expired and been removed.", (Object) str));
                }
                throw th;
            }
            Log.warn(StringExtensions.format("UDP socket reservation {0} has expired and been removed.", (Object) str));
        }
    }

    private String generateNonce() {
        return Base64.encode(Guid.newGuid().toByteArray());
    }

    private String generateRealm() {
        return StringExtensions.substring(Base64.encode(Guid.newGuid().toByteArray()), 0, 16);
    }

    public int getAllocationCount() {
        int count;
        synchronized (this._allocationsLock) {
            count = HashMapExtensions.getCount(this._allocationsByClientAddress);
        }
        return count;
    }

    public int getAllocationPortMax() {
        return this.__allocationPortMax;
    }

    public int getAllocationPortMin() {
        return this.__allocationPortMin;
    }

    public long getDefaultAllocateLifetime() {
        return this.__defaultAllocateLifetime;
    }

    public long getDefaultRefreshLifetime() {
        return this.__defaultRefreshLifetime;
    }

    public boolean getDisableBypass() {
        return this._disableBypass;
    }

    public boolean getForceDefaultAllocateLifetime() {
        return this._forceDefaultAllocateLifetime;
    }

    public boolean getForceDefaultRefreshLifetime() {
        return this._forceDefaultRefreshLifetime;
    }

    public long getMaxAllocateLifetime() {
        return this.__maxAllocateLifetime;
    }

    public long getMaxRefreshLifetime() {
        return this.__maxRefreshLifetime;
    }

    public String getNonce() {
        return this._nonce;
    }

    private String getOperationName(TurnAuthOperation turnAuthOperation) {
        if (turnAuthOperation == TurnAuthOperation.Allocate) {
            return "Allocate";
        }
        if (turnAuthOperation == TurnAuthOperation.CreatePermission) {
            return "Create-Permission";
        }
        return turnAuthOperation == TurnAuthOperation.Refresh ? "Refresh" : "Unknown";
    }

    public String getRealm() {
        return this._realm;
    }

    public boolean getStaleNonceSecurity() {
        return this._staleNonceSecurity;
    }

    /* access modifiers changed from: private */
    public void nonceTimerCallback(Object obj) {
        processNonce();
    }

    /* access modifiers changed from: private */
    public void onAllocationExpired(TransportAddress transportAddress) {
        synchronized (this._allocationsLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._allocationsByClientAddress, transportAddress.toString(), holder);
            TurnAllocation turnAllocation = (TurnAllocation) holder.getValue();
            if (tryGetValue) {
                removeAllocation(turnAllocation);
                if (Log.getIsDebugEnabled()) {
                    Log.debug(StringExtensions.format("Socket allocation for {0} has expired and been removed.", (Object) transportAddress.toString()));
                }
            }
        }
    }

    private void onPacketDataReceived(TurnUdpAllocation turnUdpAllocation, TransportAddress transportAddress, DataBuffer dataBuffer) {
        int i;
        DatagramSocket udpServerSocket = turnUdpAllocation.getUdpServerSocket();
        StreamSocket tcpServerSocket = turnUdpAllocation.getTcpServerSocket();
        int hasChannelBindingAddress = turnUdpAllocation.hasChannelBindingAddress(transportAddress);
        if (hasChannelBindingAddress != 0) {
            int length = dataBuffer.getLength() + 4;
            if (tcpServerSocket != null && (i = length % 4) > 0) {
                length += 4 - i;
            }
            DataBuffer take = __dataBufferPool.take(length, false);
            take.write16(hasChannelBindingAddress, 0);
            take.write16(dataBuffer.getLength(), 2);
            take.write(dataBuffer, 4);
            if (udpServerSocket != null) {
                udpServerSocket.send(take, turnUdpAllocation.getClientAddress().getIPAddress(), turnUdpAllocation.getClientAddress().getPort());
            } else if (tcpServerSocket != null) {
                tcpServerSocket.sendAsync(take, super.getStreamSendTimeout(), (IAction0) null, (IAction2<Exception, Boolean>) null);
            }
            take.free();
        } else if (turnUdpAllocation.hasPermission(transportAddress.getIPAddress())) {
            DataIndication dataIndication = new DataIndication();
            dataIndication.setXorPeerAddress(new XorPeerAddressAttribute(transportAddress.getIPAddress(), transportAddress.getPort(), dataIndication.getTransactionId()));
            dataIndication.setData(new DataAttribute(dataBuffer));
            DataBuffer take2 = __dataBufferPool.take(dataIndication.getLength());
            dataIndication.writeTo(take2, 0);
            if (udpServerSocket != null) {
                udpServerSocket.send(take2, turnUdpAllocation.getClientAddress().getIPAddress(), turnUdpAllocation.getClientAddress().getPort());
            } else if (tcpServerSocket != null) {
                tcpServerSocket.sendAsync(take2, super.getStreamSendTimeout(), (IAction0) null, (IAction2<Exception, Boolean>) null);
            }
            take2.free();
        }
    }

    /* access modifiers changed from: private */
    public void onUdpDataReceived(TurnUdpAllocation turnUdpAllocation, TransportAddress transportAddress, DataBuffer dataBuffer) {
        onPacketDataReceived(turnUdpAllocation, transportAddress, dataBuffer);
    }

    /* access modifiers changed from: protected */
    public Message process(Message message, DatagramSocket datagramSocket, StreamSocket streamSocket, ServerAddress serverAddress, TransportAddress transportAddress) {
        Message message2;
        TransactionTransmitCounterAttribute transactionTransmitCounter;
        Message processChannelBindRequest;
        byte[] bArr;
        Message message3 = message;
        TransportAddress transportAddress2 = transportAddress;
        boolean z = true;
        boolean z2 = streamSocket == null;
        if (z2 || !streamSocket.getSecure()) {
            z = false;
        }
        String prefix = super.getPrefix(z2, z);
        String str = StringExtensions.empty;
        try {
            byte[] bArr2 = null;
            if (message3 instanceof SendIndication) {
                processSendIndication((SendIndication) message3, transportAddress2);
                message2 = null;
            } else {
                if (message3 instanceof AllocateRequest) {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("{0}Processing UDP allocate request from {1}{2}.", prefix, transportAddress.toString(), str));
                    }
                    Holder holder = new Holder(null);
                    processChannelBindRequest = processAllocateRequest((AllocateRequest) message3, ProtocolType.Udp, datagramSocket, streamSocket, serverAddress, transportAddress, holder);
                    bArr = (byte[]) holder.getValue();
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("{0}Processed UDP allocate request from {1}{2}.", prefix, transportAddress.toString(), str));
                    }
                } else if (message3 instanceof RefreshRequest) {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("{0}Processing UDP refresh request from {1}{2}.", prefix, transportAddress.toString(), str));
                    }
                    Holder holder2 = new Holder(null);
                    processChannelBindRequest = processRefreshRequest((RefreshRequest) message3, transportAddress2, holder2);
                    bArr = (byte[]) holder2.getValue();
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("{0}Processed UDP refresh request from {1}{2}.", prefix, transportAddress.toString(), str));
                    }
                } else if (message3 instanceof CreatePermissionRequest) {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("{0}Processing UDP create-permission request from {1}{2}.", prefix, transportAddress.toString(), str));
                    }
                    Holder holder3 = new Holder(null);
                    processChannelBindRequest = processCreatePermissionRequest((CreatePermissionRequest) message3, transportAddress2, holder3);
                    bArr = (byte[]) holder3.getValue();
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("{0}Processed UDP create-permission request from {1}{2}.", prefix, transportAddress.toString(), str));
                    }
                } else if (message3 instanceof ChannelBindRequest) {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("{0}Processing UDP channel-bind request from {1}{2}.", prefix, transportAddress.toString(), str));
                    }
                    Holder holder4 = new Holder(null);
                    processChannelBindRequest = processChannelBindRequest((ChannelBindRequest) message3, transportAddress2, holder4);
                    bArr = (byte[]) holder4.getValue();
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("{0}Processed UDP channel-bind request from {1}{2}.", prefix, transportAddress.toString(), str));
                    }
                } else {
                    message2 = super.process(message, datagramSocket, streamSocket, serverAddress, transportAddress);
                }
                message2 = processChannelBindRequest;
                bArr2 = bArr;
            }
            if (bArr2 != null) {
                message2.setMessageIntegrity(new MessageIntegrityAttribute(bArr2));
                message2.setFingerprint(new FingerprintAttribute());
            }
        } catch (Exception e) {
            message2 = createExceptionResponse(message, transportAddress2, new ServerError(e.getMessage()));
        }
        if (!(message2 == null || (transactionTransmitCounter = message.getTransactionTransmitCounter()) == null)) {
            message2.setTransactionTransmitCounter(transactionTransmitCounter);
        }
        return message2;
    }

    private Message processAllocateRequest(AllocateRequest allocateRequest, ProtocolType protocolType, DatagramSocket datagramSocket, StreamSocket streamSocket, ServerAddress serverAddress, TransportAddress transportAddress, Holder<byte[]> holder) {
        Holder holder2 = new Holder(null);
        Message processAllocateRequest = processAllocateRequest(allocateRequest, protocolType, datagramSocket, streamSocket, serverAddress, transportAddress, holder, holder2);
        TurnAllocation turnAllocation = (TurnAllocation) holder2.getValue();
        return processAllocateRequest;
    }

    private Message processAllocateRequest(AllocateRequest allocateRequest, ProtocolType protocolType, DatagramSocket datagramSocket, StreamSocket streamSocket, ServerAddress serverAddress, TransportAddress transportAddress, Holder<byte[]> holder, Holder<TurnAllocation> holder2) {
        Object obj;
        ReservationTokenAttribute reservationTokenAttribute;
        long j;
        DatagramSocket datagramSocket2;
        DataBuffer dataBuffer;
        DatagramSocket datagramSocket3;
        TurnUdpAllocation turnUdpAllocation;
        TransportAddress transportAddress2;
        AllocateRequest allocateRequest2 = allocateRequest;
        TransportAddress transportAddress3 = transportAddress;
        Holder<byte[]> holder3 = holder;
        Holder<TurnAllocation> holder4 = holder2;
        holder3.setValue(null);
        holder4.setValue(null);
        Error checkNonce = checkNonce(allocateRequest2, transportAddress3, TurnAuthOperation.Allocate);
        if (checkNonce != null) {
            return createExceptionResponse(allocateRequest2, transportAddress3, checkNonce);
        }
        Error authenticate = authenticate(allocateRequest2, transportAddress3, TurnAuthOperation.Allocate, holder3);
        if (authenticate != null) {
            return createExceptionResponse(allocateRequest2, transportAddress3, authenticate);
        }
        RequestedTransportAttribute requestedTransport = allocateRequest.getRequestedTransport();
        ReservationTokenAttribute reservationToken = allocateRequest.getReservationToken();
        EvenPortAttribute evenPort = allocateRequest.getEvenPort();
        LifetimeAttribute lifetime = allocateRequest.getLifetime();
        UsernameAttribute username = allocateRequest.getUsername();
        RealmAttribute realm = allocateRequest.getRealm();
        allocateRequest.getMessageIntegrity();
        Object obj2 = this._allocationsLock;
        synchronized (obj2) {
            try {
                Holder holder5 = new Holder(null);
                boolean tryGetAllocationByClientAddress = tryGetAllocationByClientAddress(transportAddress3, holder5);
                TurnAllocation turnAllocation = (TurnAllocation) holder5.getValue();
                if (tryGetAllocationByClientAddress) {
                    if (!turnAllocation.getTransactionId().sequenceEquals(allocateRequest.getTransactionId())) {
                        String format = StringExtensions.format("Could not process allocate request for {0} - allocation already exists (mismatch).", (Object) transportAddress.toString());
                        if (Log.getIsDebugEnabled()) {
                            Log.debug(format);
                        }
                        Message createExceptionResponse = createExceptionResponse(allocateRequest2, transportAddress3, new AllocationMismatchError(format));
                        return createExceptionResponse;
                    }
                    int localPort = turnAllocation.getLocalPort();
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("Socket already allocated on port {0} for {1}.", IntegerExtensions.toString(Integer.valueOf(localPort)), transportAddress.toString()));
                    }
                    holder4.setValue(turnAllocation);
                    AllocateResponse createAllocateResponse = createAllocateResponse(allocateRequest2, turnAllocation, transportAddress3);
                    return createAllocateResponse;
                } else if (requestedTransport == null) {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("Could not process allocate request for {0} - no requested transport.", (Object) transportAddress.toString()));
                    }
                    Message createExceptionResponse2 = createExceptionResponse(allocateRequest2, transportAddress3, new BadRequestError());
                    return createExceptionResponse2;
                } else if (requestedTransport.getProtocol() != RequestedTransportAttribute.getUdpProtocol()) {
                    String format2 = StringExtensions.format("Could not process allocate request for {0} - requested transport protocol is not UDP.", (Object) transportAddress.toString());
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(format2);
                    }
                    Message createExceptionResponse3 = createExceptionResponse(allocateRequest2, transportAddress3, new UnsupportedTransportProtocolError(format2));
                    return createExceptionResponse3;
                } else if (reservationToken == null || evenPort == null) {
                    long defaultAllocateLifetime = getDefaultAllocateLifetime();
                    if (lifetime == null || getForceDefaultAllocateLifetime()) {
                        reservationTokenAttribute = reservationToken;
                        j = defaultAllocateLifetime;
                    } else {
                        reservationTokenAttribute = reservationToken;
                        j = MathAssistant.min(getMaxAllocateLifetime(), MathAssistant.max(getMinAllocateLifetime(), lifetime.getLifetime()));
                    }
                    if (reservationTokenAttribute != null) {
                        String encodeBuffer = Base64.encodeBuffer(reservationTokenAttribute.getToken());
                        synchronized (this._reservationsLock) {
                            Holder holder6 = new Holder(null);
                            boolean tryGetValue = HashMapExtensions.tryGetValue(this._reservations, encodeBuffer, holder6);
                            datagramSocket2 = (DatagramSocket) holder6.getValue();
                            if (tryGetValue) {
                                HashMapExtensions.remove(this._reservations, encodeBuffer);
                            }
                        }
                    } else {
                        datagramSocket2 = null;
                    }
                    if (datagramSocket2 == null) {
                        Holder holder7 = new Holder(datagramSocket2);
                        Holder holder8 = new Holder(null);
                        allocateUdpSocket(serverAddress, evenPort, transportAddress, holder7, holder8);
                        datagramSocket3 = (DatagramSocket) holder7.getValue();
                        dataBuffer = (DataBuffer) holder8.getValue();
                    } else {
                        dataBuffer = null;
                        datagramSocket3 = datagramSocket2;
                    }
                    if (Global.equals(protocolType, ProtocolType.Tcp)) {
                        DataBuffer transactionId = allocateRequest.getTransactionId();
                        String value = username.getValue();
                        obj = obj2;
                        DatagramSocket datagramSocket4 = datagramSocket3;
                        Holder<TurnAllocation> holder9 = holder4;
                        transportAddress2 = transportAddress3;
                        try {
                            TurnUdpAllocation turnUdpAllocation2 = new TurnUdpAllocation(datagramSocket, streamSocket, transactionId, dataBuffer, datagramSocket4, transportAddress, value, realm.getValue(), j, new IActionDelegate3<TurnUdpAllocation, TransportAddress, DataBuffer>() {
                                public String getId() {
                                    return "fm.liveswitch.TurnServer.onUdpDataReceived";
                                }

                                public void invoke(TurnUdpAllocation turnUdpAllocation, TransportAddress transportAddress, DataBuffer dataBuffer) {
                                    TurnServer.this.onUdpDataReceived(turnUdpAllocation, transportAddress, dataBuffer);
                                }
                            }, new IActionDelegate1<TransportAddress>() {
                                public String getId() {
                                    return "fm.liveswitch.TurnServer.onAllocationExpired";
                                }

                                public void invoke(TransportAddress transportAddress) {
                                    TurnServer.this.onAllocationExpired(transportAddress);
                                }
                            });
                            holder9.setValue(turnUdpAllocation2);
                            turnUdpAllocation = turnUdpAllocation2;
                            Holder<TurnAllocation> holder10 = holder9;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    } else {
                        obj = obj2;
                        transportAddress2 = transportAddress3;
                        turnUdpAllocation = new TurnUdpAllocation(datagramSocket, streamSocket, allocateRequest.getTransactionId(), dataBuffer, datagramSocket3, transportAddress, username.getValue(), realm.getValue(), j, new IActionDelegate3<TurnUdpAllocation, TransportAddress, DataBuffer>() {
                            public String getId() {
                                return "fm.liveswitch.TurnServer.onUdpDataReceived";
                            }

                            public void invoke(TurnUdpAllocation turnUdpAllocation, TransportAddress transportAddress, DataBuffer dataBuffer) {
                                TurnServer.this.onUdpDataReceived(turnUdpAllocation, transportAddress, dataBuffer);
                            }
                        }, new IActionDelegate1<TransportAddress>() {
                            public String getId() {
                                return "fm.liveswitch.TurnServer.onAllocationExpired";
                            }

                            public void invoke(TransportAddress transportAddress) {
                                TurnServer.this.onAllocationExpired(transportAddress);
                            }
                        });
                        holder4.setValue(turnUdpAllocation);
                    }
                    HashMapExtensions.set(HashMapExtensions.getItem(this._allocationsByClientAddress), transportAddress.toString(), holder2.getValue());
                    HashMapExtensions.set(HashMapExtensions.getItem(this._allocationsByLocalPort), Integer.valueOf(holder2.getValue().getLocalPort()), holder2.getValue());
                    turnUdpAllocation.startReceiving();
                    AllocateResponse createAllocateResponse2 = createAllocateResponse(allocateRequest, holder2.getValue(), transportAddress2);
                    return createAllocateResponse2;
                } else {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("Could not process allocate request for {0} - reservation token cannot be sent with even-port attribute.", (Object) transportAddress.toString()));
                    }
                    Message createExceptionResponse4 = createExceptionResponse(allocateRequest2, transportAddress3, new BadRequestError());
                    return createExceptionResponse4;
                }
            } catch (Throwable th2) {
                th = th2;
                obj = obj2;
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean processBuffer(DataBuffer dataBuffer, DatagramSocket datagramSocket, StreamSocket streamSocket, ServerAddress serverAddress, TransportAddress transportAddress, IntegerHolder integerHolder) {
        int value;
        if (dataBuffer.getLength() > 0) {
            byte read8 = dataBuffer.read8(0) & this._requestBitmask;
            if (read8 == 64 && dataBuffer.getLength() >= 4) {
                int read16 = dataBuffer.read16(0);
                int read162 = dataBuffer.read16(2);
                int i = read162 + 4;
                if (i <= dataBuffer.getLength()) {
                    processChannelData(dataBuffer.subset(4, read162), read16, transportAddress);
                    integerHolder.setValue(i);
                    if (streamSocket == null || (value = integerHolder.getValue() % 4) <= 0) {
                        return true;
                    }
                    integerHolder.setValue(integerHolder.getValue() + (4 - value));
                    return true;
                }
            } else if (read8 == 0) {
                return super.processBuffer(dataBuffer, datagramSocket, streamSocket, serverAddress, transportAddress, integerHolder);
            }
        }
        integerHolder.setValue(0);
        return false;
    }

    /* access modifiers changed from: package-private */
    public Message processChannelBindRequest(ChannelBindRequest channelBindRequest, TransportAddress transportAddress, Holder<byte[]> holder) {
        holder.setValue(null);
        Error checkNonce = checkNonce(channelBindRequest, transportAddress, TurnAuthOperation.ChannelBind);
        if (checkNonce != null) {
            return createExceptionResponse(channelBindRequest, transportAddress, checkNonce);
        }
        Error authenticate = authenticate(channelBindRequest, transportAddress, TurnAuthOperation.ChannelBind, holder);
        if (authenticate != null) {
            return createExceptionResponse(channelBindRequest, transportAddress, authenticate);
        }
        XorPeerAddressAttribute xorPeerAddress = channelBindRequest.getXorPeerAddress();
        if (xorPeerAddress == null) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not process channel-bind request for {0} - no peer addresses.", (Object) transportAddress.toString()));
            }
            return createExceptionResponse(channelBindRequest, transportAddress, new BadRequestError());
        }
        ChannelNumberAttribute channelNumber = channelBindRequest.getChannelNumber();
        if (channelNumber == null) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not process channel-bind request for {0} - no channel number.", (Object) transportAddress.toString()));
            }
            return createExceptionResponse(channelBindRequest, transportAddress, new BadRequestError());
        } else if (channelNumber.getChannelNumber() < 16384 || channelNumber.getChannelNumber() > 32766) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not process channel-bind request for {0} - invalid channel number.", (Object) transportAddress.toString()));
            }
            return createExceptionResponse(channelBindRequest, transportAddress, new BadRequestError());
        } else {
            synchronized (this._allocationsLock) {
                Holder holder2 = new Holder(null);
                boolean tryGetAllocationByClientAddress = tryGetAllocationByClientAddress(transportAddress, holder2);
                TurnAllocation turnAllocation = (TurnAllocation) holder2.getValue();
                if (!tryGetAllocationByClientAddress) {
                    String format = StringExtensions.format("Could not process channel-bind request for {0} - allocation does not exist (mismatch).", (Object) transportAddress.toString());
                    AllocationMismatchError allocationMismatchError = new AllocationMismatchError(format);
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(format);
                    }
                    Message createExceptionResponse = createExceptionResponse(channelBindRequest, transportAddress, allocationMismatchError);
                    return createExceptionResponse;
                } else if (!authorize(turnAllocation, channelBindRequest)) {
                    String format2 = StringExtensions.format("Could not process channel-bind request for {0} - authorization failed.", (Object) transportAddress.toString());
                    WrongCredentialsError wrongCredentialsError = new WrongCredentialsError(format2);
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(format2);
                    }
                    Message createExceptionResponse2 = createExceptionResponse(channelBindRequest, transportAddress, wrongCredentialsError);
                    return createExceptionResponse2;
                } else {
                    Error addChannelBinding = turnAllocation.addChannelBinding(new TransportAddress(xorPeerAddress.getIPAddress(), xorPeerAddress.getPort()), channelNumber.getChannelNumber(), transportAddress);
                    if (addChannelBinding == null) {
                        return new ChannelBindResponse(channelBindRequest.getTransactionId(), true);
                    }
                    Message createExceptionResponse3 = createExceptionResponse(channelBindRequest, transportAddress, addChannelBinding);
                    return createExceptionResponse3;
                }
            }
        }
    }

    private void processChannelData(DataBuffer dataBuffer, int i, TransportAddress transportAddress) {
        TurnAllocation turnAllocation;
        synchronized (this._allocationsLock) {
            Holder holder = new Holder(null);
            turnAllocation = !tryGetAllocationByClientAddress(transportAddress, holder) ? null : (TurnAllocation) holder.getValue();
        }
        if (turnAllocation != null) {
            TransportAddress hasChannelBindingNumber = turnAllocation.hasChannelBindingNumber(i);
            if (hasChannelBindingNumber != null) {
                sendDataToPeer(dataBuffer, hasChannelBindingNumber, turnAllocation, transportAddress, true);
            } else if (Log.getIsWarnEnabled()) {
                Log.warn(StringExtensions.format("Could not channel-send {0} data for {1} - no channel binding.", hasChannelBindingNumber.toString(), transportAddress.toString()));
            }
        } else if (Log.getIsWarnEnabled()) {
            Log.warn(StringExtensions.format("Could not channel-send data for {0} - no allocation.", (Object) transportAddress.toString()));
        }
    }

    /* access modifiers changed from: package-private */
    public Message processCreatePermissionRequest(CreatePermissionRequest createPermissionRequest, TransportAddress transportAddress, Holder<byte[]> holder) {
        holder.setValue(null);
        Error checkNonce = checkNonce(createPermissionRequest, transportAddress, TurnAuthOperation.CreatePermission);
        if (checkNonce != null) {
            return createExceptionResponse(createPermissionRequest, transportAddress, checkNonce);
        }
        Error authenticate = authenticate(createPermissionRequest, transportAddress, TurnAuthOperation.CreatePermission, holder);
        if (authenticate != null) {
            return createExceptionResponse(createPermissionRequest, transportAddress, authenticate);
        }
        ArrayList arrayList = new ArrayList();
        for (Attribute attribute : createPermissionRequest.getAttributes()) {
            if (Global.equals(attribute.getClass(), XorPeerAddressAttribute.class)) {
                arrayList.add((XorPeerAddressAttribute) attribute);
            }
        }
        if (ArrayListExtensions.getCount(arrayList) == 0) {
            if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not process create-permission request for {0} - no peer addresses.", (Object) transportAddress.toString()));
            }
            return createExceptionResponse(createPermissionRequest, transportAddress, new BadRequestError());
        }
        synchronized (this._allocationsLock) {
            Holder holder2 = new Holder(null);
            boolean tryGetAllocationByClientAddress = tryGetAllocationByClientAddress(transportAddress, holder2);
            TurnAllocation turnAllocation = (TurnAllocation) holder2.getValue();
            if (!tryGetAllocationByClientAddress) {
                String format = StringExtensions.format("Could not process create-permission request for {0} - allocation does not exist (mismatch).", (Object) transportAddress.toString());
                AllocationMismatchError allocationMismatchError = new AllocationMismatchError(format);
                if (Log.getIsDebugEnabled()) {
                    Log.debug(format);
                }
                Message createExceptionResponse = createExceptionResponse(createPermissionRequest, transportAddress, allocationMismatchError);
                return createExceptionResponse;
            }
            if (!authorize(turnAllocation, createPermissionRequest)) {
                String format2 = StringExtensions.format("Could not process create-permission request for {0} - authorization failed.", (Object) transportAddress.toString());
                WrongCredentialsError wrongCredentialsError = new WrongCredentialsError(format2);
                if (Log.getIsDebugEnabled()) {
                    Log.debug(format2);
                }
                createExceptionResponse(createPermissionRequest, transportAddress, wrongCredentialsError);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                XorPeerAddressAttribute xorPeerAddressAttribute = (XorPeerAddressAttribute) it.next();
                if (turnAllocation.addPermission(xorPeerAddressAttribute.getIPAddress())) {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("Adding {0}:{1} allocation permission for {2}.", xorPeerAddressAttribute.getIPAddress(), IntegerExtensions.toString(Integer.valueOf(xorPeerAddressAttribute.getPort())), transportAddress.toString()));
                    }
                } else if (Log.getIsDebugEnabled()) {
                    Log.debug(StringExtensions.format("Extending {0}:{1} allocation permission for {2}.", xorPeerAddressAttribute.getIPAddress(), IntegerExtensions.toString(Integer.valueOf(xorPeerAddressAttribute.getPort())), transportAddress.toString()));
                }
            }
            return new CreatePermissionResponse(createPermissionRequest.getTransactionId(), true);
        }
    }

    private void processNonce() {
        setNonce(generateNonce());
        if (Log.getIsInfoEnabled() && getStaleNonceSecurity()) {
            Log.info(StringExtensions.format("Nonce updated. ({0})", (Object) getNonce()));
        }
        TimeoutTimer timeoutTimer = new TimeoutTimer(new IActionDelegate1<Object>() {
            public String getId() {
                return "fm.liveswitch.TurnServer.nonceTimerCallback";
            }

            public void invoke(Object obj) {
                TurnServer.this.nonceTimerCallback(obj);
            }
        }, (Object) null);
        this._nonceTimer = timeoutTimer;
        try {
            timeoutTimer.start(DateTimeConstants.MILLIS_PER_HOUR);
        } catch (Exception e) {
            Log.error("Could not start nonce timer.", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a7, code lost:
        if (r8 != false) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a9, code lost:
        r11 = getDefaultRefreshLifetime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ad, code lost:
        if (r3 == null) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b3, code lost:
        if (getForceDefaultRefreshLifetime() != false) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b5, code lost:
        r11 = fm.liveswitch.MathAssistant.min(getMaxRefreshLifetime(), fm.liveswitch.MathAssistant.max(getMinRefreshLifetime(), (long) ((int) r3.getLifetime())));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cf, code lost:
        if (fm.liveswitch.Log.getIsDebugEnabled() == false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d1, code lost:
        fm.liveswitch.Log.debug(fm.liveswitch.StringExtensions.format("Extending allocation expiration for {0} by {1} seconds.", r17.toString(), fm.liveswitch.LongExtensions.toString(java.lang.Long.valueOf(r11))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e6, code lost:
        r10.refresh(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e9, code lost:
        r2 = new fm.liveswitch.stun.turn.RefreshResponse(r16.getTransactionId(), true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f2, code lost:
        if (r8 != false) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f4, code lost:
        r2.setLifetime(new fm.liveswitch.stun.turn.LifetimeAttribute(r10.getLastLifetime()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0101, code lost:
        r2.setLifetime(new fm.liveswitch.stun.turn.LifetimeAttribute(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0109, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.stun.Message processRefreshRequest(fm.liveswitch.stun.turn.RefreshRequest r16, fm.liveswitch.TransportAddress r17, fm.liveswitch.Holder<byte[]> r18) {
        /*
            r15 = this;
            r1 = r15
            r0 = r16
            r2 = r17
            r3 = r18
            r4 = 0
            r3.setValue(r4)
            fm.liveswitch.TurnAuthOperation r5 = fm.liveswitch.TurnAuthOperation.Refresh
            fm.liveswitch.stun.Error r5 = r15.checkNonce(r0, r2, r5)
            if (r5 == 0) goto L_0x0018
            fm.liveswitch.stun.Message r0 = r15.createExceptionResponse(r0, r2, r5)
            return r0
        L_0x0018:
            fm.liveswitch.TurnAuthOperation r5 = fm.liveswitch.TurnAuthOperation.Refresh
            fm.liveswitch.stun.Error r3 = r15.authenticate(r0, r2, r5, r3)
            if (r3 == 0) goto L_0x0025
            fm.liveswitch.stun.Message r0 = r15.createExceptionResponse(r0, r2, r3)
            return r0
        L_0x0025:
            fm.liveswitch.stun.turn.LifetimeAttribute r3 = r16.getLifetime()
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L_0x0038
            long r8 = r3.getLifetime()
            int r10 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r10 != 0) goto L_0x0038
            r8 = 1
            goto L_0x0039
        L_0x0038:
            r8 = 0
        L_0x0039:
            java.lang.Object r9 = r1._allocationsLock
            monitor-enter(r9)
            fm.liveswitch.Holder r10 = new fm.liveswitch.Holder     // Catch:{ all -> 0x010a }
            r10.<init>(r4)     // Catch:{ all -> 0x010a }
            boolean r4 = r15.tryGetAllocationByClientAddress(r2, r10)     // Catch:{ all -> 0x010a }
            java.lang.Object r10 = r10.getValue()     // Catch:{ all -> 0x010a }
            fm.liveswitch.TurnAllocation r10 = (fm.liveswitch.TurnAllocation) r10     // Catch:{ all -> 0x010a }
            if (r4 != 0) goto L_0x006d
            java.lang.String r3 = "Could not process refresh request for {0} - allocation does not exist (mismatch)."
            java.lang.String r4 = r17.toString()     // Catch:{ all -> 0x010a }
            java.lang.String r3 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x010a }
            fm.liveswitch.stun.turn.AllocationMismatchError r4 = new fm.liveswitch.stun.turn.AllocationMismatchError     // Catch:{ all -> 0x010a }
            r4.<init>(r3)     // Catch:{ all -> 0x010a }
            if (r8 != 0) goto L_0x0067
            boolean r5 = fm.liveswitch.Log.getIsDebugEnabled()     // Catch:{ all -> 0x010a }
            if (r5 == 0) goto L_0x0067
            fm.liveswitch.Log.debug(r3)     // Catch:{ all -> 0x010a }
        L_0x0067:
            fm.liveswitch.stun.Message r0 = r15.createExceptionResponse(r0, r2, r4)     // Catch:{ all -> 0x010a }
            monitor-exit(r9)     // Catch:{ all -> 0x010a }
            return r0
        L_0x006d:
            boolean r4 = r15.authorize(r10, r0)     // Catch:{ all -> 0x010a }
            if (r4 != 0) goto L_0x008e
            java.lang.String r4 = "Could not process refresh request for {0} - authorization failed."
            java.lang.String r11 = r17.toString()     // Catch:{ all -> 0x010a }
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r11)     // Catch:{ all -> 0x010a }
            fm.liveswitch.stun.turn.WrongCredentialsError r11 = new fm.liveswitch.stun.turn.WrongCredentialsError     // Catch:{ all -> 0x010a }
            r11.<init>(r4)     // Catch:{ all -> 0x010a }
            boolean r12 = fm.liveswitch.Log.getIsDebugEnabled()     // Catch:{ all -> 0x010a }
            if (r12 == 0) goto L_0x008b
            fm.liveswitch.Log.debug(r4)     // Catch:{ all -> 0x010a }
        L_0x008b:
            r15.createExceptionResponse(r0, r2, r11)     // Catch:{ all -> 0x010a }
        L_0x008e:
            if (r8 == 0) goto L_0x00a6
            boolean r4 = fm.liveswitch.Log.getIsDebugEnabled()     // Catch:{ all -> 0x010a }
            if (r4 == 0) goto L_0x00a3
            java.lang.String r4 = "Deallocating socket for {0} by request."
            java.lang.String r11 = r17.toString()     // Catch:{ all -> 0x010a }
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r11)     // Catch:{ all -> 0x010a }
            fm.liveswitch.Log.debug(r4)     // Catch:{ all -> 0x010a }
        L_0x00a3:
            r15.removeAllocation(r10)     // Catch:{ all -> 0x010a }
        L_0x00a6:
            monitor-exit(r9)     // Catch:{ all -> 0x010a }
            if (r8 != 0) goto L_0x00e9
            long r11 = r15.getDefaultRefreshLifetime()
            if (r3 == 0) goto L_0x00cb
            boolean r4 = r15.getForceDefaultRefreshLifetime()
            if (r4 != 0) goto L_0x00cb
            long r11 = r15.getMaxRefreshLifetime()
            long r13 = r15.getMinRefreshLifetime()
            long r3 = r3.getLifetime()
            int r4 = (int) r3
            long r3 = (long) r4
            long r3 = fm.liveswitch.MathAssistant.max((long) r13, (long) r3)
            long r11 = fm.liveswitch.MathAssistant.min((long) r11, (long) r3)
        L_0x00cb:
            boolean r3 = fm.liveswitch.Log.getIsDebugEnabled()
            if (r3 == 0) goto L_0x00e6
            java.lang.String r3 = "Extending allocation expiration for {0} by {1} seconds."
            java.lang.String r2 = r17.toString()
            java.lang.Long r4 = java.lang.Long.valueOf(r11)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r3, r2, r4)
            fm.liveswitch.Log.debug(r2)
        L_0x00e6:
            r10.refresh(r11)
        L_0x00e9:
            fm.liveswitch.stun.turn.RefreshResponse r2 = new fm.liveswitch.stun.turn.RefreshResponse
            fm.liveswitch.DataBuffer r0 = r16.getTransactionId()
            r2.<init>(r0, r5)
            if (r8 != 0) goto L_0x0101
            fm.liveswitch.stun.turn.LifetimeAttribute r0 = new fm.liveswitch.stun.turn.LifetimeAttribute
            long r3 = r10.getLastLifetime()
            r0.<init>(r3)
            r2.setLifetime(r0)
            goto L_0x0109
        L_0x0101:
            fm.liveswitch.stun.turn.LifetimeAttribute r0 = new fm.liveswitch.stun.turn.LifetimeAttribute
            r0.<init>(r6)
            r2.setLifetime(r0)
        L_0x0109:
            return r2
        L_0x010a:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x010a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TurnServer.processRefreshRequest(fm.liveswitch.stun.turn.RefreshRequest, fm.liveswitch.TransportAddress, fm.liveswitch.Holder):fm.liveswitch.stun.Message");
    }

    /* access modifiers changed from: package-private */
    public TurnAllocation processSendIndication(SendIndication sendIndication, TransportAddress transportAddress) {
        XorPeerAddressAttribute xorPeerAddress = sendIndication.getXorPeerAddress();
        DataAttribute data = sendIndication.getData();
        TurnAllocation turnAllocation = null;
        if (!(xorPeerAddress == null || data == null)) {
            synchronized (this._allocationsLock) {
                Holder holder = new Holder(null);
                boolean tryGetAllocationByClientAddress = tryGetAllocationByClientAddress(transportAddress, holder);
                TurnAllocation turnAllocation2 = (TurnAllocation) holder.getValue();
                if (tryGetAllocationByClientAddress) {
                    turnAllocation = turnAllocation2;
                }
            }
            if (turnAllocation != null) {
                sendDataToPeer(data.getData(), new TransportAddress(xorPeerAddress.getIPAddress(), xorPeerAddress.getPort()), turnAllocation, transportAddress, false);
                return turnAllocation;
            } else if (Log.getIsDebugEnabled()) {
                Log.debug(StringExtensions.format("Could not send data for {0} - no allocation.", (Object) transportAddress.toString()));
            }
        }
        return turnAllocation;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:2|3|4|5|6|7) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0020 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void removeAllocation(fm.liveswitch.TurnAllocation r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3._allocationsLock
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, fm.liveswitch.TurnAllocation> r1 = r3._allocationsByClientAddress     // Catch:{ all -> 0x0022 }
            fm.liveswitch.TransportAddress r2 = r4.getClientAddress()     // Catch:{ all -> 0x0022 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0022 }
            fm.liveswitch.HashMapExtensions.remove(r1, r2)     // Catch:{ all -> 0x0022 }
            java.util.HashMap<java.lang.Integer, fm.liveswitch.TurnAllocation> r1 = r3._allocationsByLocalPort     // Catch:{ all -> 0x0022 }
            int r2 = r4.getLocalPort()     // Catch:{ all -> 0x0022 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0022 }
            fm.liveswitch.HashMapExtensions.remove(r1, r2)     // Catch:{ all -> 0x0022 }
            r4.close()     // Catch:{ Exception -> 0x0020 }
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return
        L_0x0022:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TurnServer.removeAllocation(fm.liveswitch.TurnAllocation):void");
    }

    private void removeAllocations() {
        synchronized (this._allocationsLock) {
            ArrayList arrayList = new ArrayList();
            for (String add : HashMapExtensions.getKeys(this._allocationsByClientAddress)) {
                arrayList.add(add);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                removeAllocation(HashMapExtensions.getItem(this._allocationsByClientAddress).get((String) it.next()));
            }
        }
    }

    private void removeReservations() {
        synchronized (this._reservationsLock) {
            ArrayList arrayList = new ArrayList();
            for (String add : HashMapExtensions.getKeys(this._reservations)) {
                arrayList.add(add);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    HashMapExtensions.getItem(this._reservations).get((String) it.next()).close();
                } catch (Exception unused) {
                }
            }
            this._reservations.clear();
        }
    }

    private void sendDataToPeer(DataBuffer dataBuffer, TransportAddress transportAddress, TurnAllocation turnAllocation, TransportAddress transportAddress2, boolean z) {
        TurnAllocation turnAllocation2;
        synchronized (this._allocationsLock) {
            Holder holder = new Holder(null);
            boolean tryGetAllocationByServerAddress = tryGetAllocationByServerAddress(transportAddress, holder);
            turnAllocation2 = (TurnAllocation) holder.getValue();
            if (!tryGetAllocationByServerAddress) {
                turnAllocation2 = null;
            }
        }
        TransportAddress transportAddress3 = new TransportAddress(turnAllocation.getPublicIPAddress() != null ? turnAllocation.getPublicIPAddress() : turnAllocation.getLocalIPAddress(), turnAllocation.getLocalPort());
        if (getDisableBypass() || turnAllocation2 == null) {
            Holder holder2 = new Holder(null);
            boolean sendData = ((TurnUdpAllocation) turnAllocation).sendData(dataBuffer, transportAddress, holder2);
            Exception exc = (Exception) holder2.getValue();
            if (!sendData && Log.getIsWarnEnabled()) {
                if (z) {
                    Log.warn(StringExtensions.format("Could not channel-send {0} data for {1}).", transportAddress.toString(), transportAddress2.toString()), exc);
                } else {
                    Log.warn(StringExtensions.format("Could not send {0} data for {1}.", transportAddress.toString(), transportAddress2.toString()), exc);
                }
            }
        } else {
            onPacketDataReceived((TurnUdpAllocation) turnAllocation2, transportAddress3, dataBuffer);
        }
    }

    public void setAllocationPortMax(int i) {
        if (i <= 0 || i >= 65535) {
            throw new RuntimeException(new Exception("Value must be greater than 0 and less than 65,535."));
        }
        this.__allocationPortMax = i;
    }

    public void setAllocationPortMin(int i) {
        if (i <= 0 || i >= 65535) {
            throw new RuntimeException(new Exception("Value must be greater than 0 and less than 65,535."));
        }
        this.__allocationPortMin = i;
    }

    public void setDefaultAllocateLifetime(long j) {
        this.__defaultAllocateLifetime = MathAssistant.min(getMaxAllocateLifetime(), MathAssistant.max(getMinAllocateLifetime(), j));
    }

    public void setDefaultRefreshLifetime(long j) {
        this.__defaultRefreshLifetime = MathAssistant.min(getMaxRefreshLifetime(), MathAssistant.max(getMinRefreshLifetime(), j));
    }

    public void setDisableBypass(boolean z) {
        this._disableBypass = z;
    }

    public void setForceDefaultAllocateLifetime(boolean z) {
        this._forceDefaultAllocateLifetime = z;
    }

    public void setForceDefaultRefreshLifetime(boolean z) {
        this._forceDefaultRefreshLifetime = z;
    }

    public void setMaxAllocateLifetime(long j) {
        this.__maxAllocateLifetime = MathAssistant.max(getMinAllocateLifetime(), j);
    }

    public void setMaxRefreshLifetime(long j) {
        this.__maxRefreshLifetime = MathAssistant.max(getMinRefreshLifetime(), j);
    }

    private void setNonce(String str) {
        this._nonce = str;
    }

    public void setRealm(String str) {
        this._realm = str;
    }

    public void setStaleNonceSecurity(boolean z) {
        this._staleNonceSecurity = z;
    }

    public boolean start(ServerAddress[] serverAddressArr, ServerAddress[] serverAddressArr2, ServerAddress[] serverAddressArr3) {
        if (getAllocationPortMin() > getAllocationPortMax()) {
            throw new RuntimeException(new Exception("AllocationPortMin cannot be greater than AllocationPortMax."));
        } else if (!super.start(serverAddressArr, serverAddressArr2, serverAddressArr3)) {
            return false;
        } else {
            processNonce();
            return true;
        }
    }

    public boolean stop() {
        if (!super.stop()) {
            return false;
        }
        this._nonceTimer.stop();
        removeReservations();
        removeAllocations();
        return true;
    }

    private boolean tryAllocateUdpSocket(ServerAddress serverAddress, EvenPortAttribute evenPortAttribute, TransportAddress transportAddress, Holder<DatagramSocket> holder, Holder<DataBuffer> holder2) {
        int i;
        synchronized (_nextUdpPortLock) {
            if (this._nextUdpPort == 0) {
                this._nextUdpPort = getAllocationPortMin();
            }
            if (evenPortAttribute != null) {
                int i2 = this._nextUdpPort;
                if (i2 % 2 != 0) {
                    this._nextUdpPort = i2 + 1;
                }
            }
            if (this._nextUdpPort > getAllocationPortMax()) {
                this._nextUdpPort = getAllocationPortMin();
            }
            i = this._nextUdpPort;
            this._nextUdpPort = i + 1;
        }
        createUdpSocket(new ServerAddress(serverAddress.getIPAddress(), i, serverAddress.getPublicIPAddress()), holder);
        if (holder.getValue() == null) {
            holder2.setValue(null);
            return false;
        }
        if (Log.getIsDebugEnabled()) {
            Log.debug(StringExtensions.format("UDP socket allocated on port {0} for {1}.", IntegerExtensions.toString(Integer.valueOf(i)), transportAddress.toString()));
        }
        if (evenPortAttribute == null || !evenPortAttribute.getReserveNextHigher()) {
            holder2.setValue(null);
            return true;
        }
        return tryAllocateUdpSocketReservation(serverAddress, transportAddress, i + 1, holder.getValue(), holder2);
    }

    private boolean tryAllocateUdpSocketReservation(ServerAddress serverAddress, TransportAddress transportAddress, int i, DatagramSocket datagramSocket, Holder<DataBuffer> holder) {
        Holder holder2 = new Holder(null);
        createUdpSocket(new ServerAddress(serverAddress.getIPAddress(), i, serverAddress.getPublicIPAddress()), holder2);
        DatagramSocket datagramSocket2 = (DatagramSocket) holder2.getValue();
        if (datagramSocket2 == null) {
            try {
                datagramSocket.close();
            } catch (Exception unused) {
            }
            holder.setValue(null);
            return false;
        }
        holder.setValue(DataBuffer.allocate(8, false));
        LockedRandomizer.nextBytes(holder.getValue().getData());
        String encodeBuffer = Base64.encodeBuffer(holder.getValue());
        if (Log.getIsDebugEnabled()) {
            Log.debug(StringExtensions.format("UDP socket reservation {0} created on port {1} for {2}.", encodeBuffer, IntegerExtensions.toString(Integer.valueOf(i)), transportAddress.toString()));
        }
        synchronized (this._reservationsLock) {
            HashMapExtensions.set(HashMapExtensions.getItem(this._reservations), encodeBuffer, datagramSocket2);
        }
        new TimeoutTimer(new IActionDelegate1<Object>() {
            public String getId() {
                return "fm.liveswitch.TurnServer.expireReservedUdpSocket";
            }

            public void invoke(Object obj) {
                TurnServer.this.expireReservedUdpSocket(obj);
            }
        }, encodeBuffer).start(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean tryAuthenticate(Message message, TurnAuthOperation turnAuthOperation, BooleanHolder booleanHolder, Holder<byte[]> holder) {
        UsernameAttribute username = message.getUsername();
        RealmAttribute realm = message.getRealm();
        MessageIntegrityAttribute messageIntegrity = message.getMessageIntegrity();
        if (username == null || realm == null || messageIntegrity == null) {
            booleanHolder.setValue(true);
            holder.setValue(null);
            return false;
        }
        booleanHolder.setValue(false);
        IFunction1<TurnAuthArgs, TurnAuthResult> iFunction1 = this._authCallback;
        if (iFunction1 != null) {
            TurnAuthResult invoke = iFunction1.invoke(new TurnAuthArgs(username.getValue(), realm.getValue(), turnAuthOperation));
            if (invoke == null) {
                holder.setValue(null);
                return false;
            }
            if (invoke.getPassword() != null) {
                holder.setValue(Utility.createLongTermKey(username.getValue(), realm.getValue(), invoke.getPassword()));
            } else {
                holder.setValue(invoke.getLongTermKeyBytes());
            }
            return messageIntegrity.isValid(holder.getValue());
        }
        holder.setValue(null);
        return false;
    }

    private boolean tryGetAllocationByClientAddress(TransportAddress transportAddress, Holder<TurnAllocation> holder) {
        synchronized (this._allocationsLock) {
            if (!HashMapExtensions.tryGetValue(this._allocationsByClientAddress, transportAddress.toString(), holder)) {
                holder.setValue(null);
                return false;
            } else if (!holder.getValue().getIsExpired()) {
                return true;
            } else {
                removeAllocation(holder.getValue());
                if (Log.getIsWarnEnabled()) {
                    Log.warn(StringExtensions.format("Socket allocation for {0} has expired and been removed.", (Object) holder.getValue().getClientAddress().toString()));
                }
                holder.setValue(null);
                return false;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
        r6.setValue(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean tryGetAllocationByServerAddress(fm.liveswitch.TransportAddress r5, fm.liveswitch.Holder<fm.liveswitch.TurnAllocation> r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4._allocationsLock
            monitor-enter(r0)
            java.util.HashMap<java.lang.Integer, fm.liveswitch.TurnAllocation> r1 = r4._allocationsByLocalPort     // Catch:{ all -> 0x0074 }
            int r2 = r5.getPort()     // Catch:{ all -> 0x0074 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0074 }
            boolean r1 = fm.liveswitch.HashMapExtensions.tryGetValue(r1, r2, r6)     // Catch:{ all -> 0x0074 }
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L_0x006f
            java.lang.Object r1 = r6.getValue()     // Catch:{ all -> 0x0074 }
            fm.liveswitch.TurnAllocation r1 = (fm.liveswitch.TurnAllocation) r1     // Catch:{ all -> 0x0074 }
            boolean r1 = r1.getIsExpired()     // Catch:{ all -> 0x0074 }
            if (r1 == 0) goto L_0x004c
            java.lang.Object r5 = r6.getValue()     // Catch:{ all -> 0x0074 }
            fm.liveswitch.TurnAllocation r5 = (fm.liveswitch.TurnAllocation) r5     // Catch:{ all -> 0x0074 }
            r4.removeAllocation(r5)     // Catch:{ all -> 0x0074 }
            boolean r5 = fm.liveswitch.Log.getIsWarnEnabled()     // Catch:{ all -> 0x0074 }
            if (r5 == 0) goto L_0x0047
            java.lang.String r5 = "Socket allocation for {0} has expired and been removed."
            java.lang.Object r1 = r6.getValue()     // Catch:{ all -> 0x0074 }
            fm.liveswitch.TurnAllocation r1 = (fm.liveswitch.TurnAllocation) r1     // Catch:{ all -> 0x0074 }
            fm.liveswitch.TransportAddress r1 = r1.getClientAddress()     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0074 }
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r5, (java.lang.Object) r1)     // Catch:{ all -> 0x0074 }
            fm.liveswitch.Log.warn(r5)     // Catch:{ all -> 0x0074 }
        L_0x0047:
            r6.setValue(r3)     // Catch:{ all -> 0x0074 }
            monitor-exit(r0)     // Catch:{ all -> 0x0074 }
            return r2
        L_0x004c:
            java.lang.Object r1 = r6.getValue()     // Catch:{ all -> 0x0074 }
            fm.liveswitch.TurnAllocation r1 = (fm.liveswitch.TurnAllocation) r1     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = r1.getPublicIPAddress()     // Catch:{ all -> 0x0074 }
            if (r1 != 0) goto L_0x0062
            java.lang.Object r1 = r6.getValue()     // Catch:{ all -> 0x0074 }
            fm.liveswitch.TurnAllocation r1 = (fm.liveswitch.TurnAllocation) r1     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = r1.getLocalIPAddress()     // Catch:{ all -> 0x0074 }
        L_0x0062:
            java.lang.String r5 = r5.getIPAddress()     // Catch:{ all -> 0x0074 }
            boolean r5 = fm.liveswitch.Global.equals(r5, r1)     // Catch:{ all -> 0x0074 }
            if (r5 == 0) goto L_0x006f
            r5 = 1
            monitor-exit(r0)     // Catch:{ all -> 0x0074 }
            return r5
        L_0x006f:
            monitor-exit(r0)     // Catch:{ all -> 0x0074 }
            r6.setValue(r3)
            return r2
        L_0x0074:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0074 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TurnServer.tryGetAllocationByServerAddress(fm.liveswitch.TransportAddress, fm.liveswitch.Holder):boolean");
    }

    public TurnServer(IFunction1<TurnAuthArgs, TurnAuthResult> iFunction1) {
        setRealm(generateRealm());
        this._authCallback = iFunction1;
    }
}
