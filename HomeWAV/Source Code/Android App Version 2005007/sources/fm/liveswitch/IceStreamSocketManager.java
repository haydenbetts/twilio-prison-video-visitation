package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.stun.BindingRequest;
import fm.liveswitch.stun.BindingResponse;
import fm.liveswitch.stun.Error;
import fm.liveswitch.stun.ErrorCodeAttribute;
import fm.liveswitch.stun.MappedAddressAttribute;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;
import fm.liveswitch.stun.NonceAttribute;
import fm.liveswitch.stun.RealmAttribute;
import fm.liveswitch.stun.StaleNonceError;
import fm.liveswitch.stun.TransactionTransmitCounterAttribute;
import fm.liveswitch.stun.TryAlternateStunError;
import fm.liveswitch.stun.UnauthorizedStunError;
import fm.liveswitch.stun.XorMappedAddressAttribute;
import fm.liveswitch.stun.turn.AllocateRequest;
import fm.liveswitch.stun.turn.AllocateResponse;
import fm.liveswitch.stun.turn.DataAttribute;
import fm.liveswitch.stun.turn.DataIndication;
import fm.liveswitch.stun.turn.LifetimeAttribute;
import fm.liveswitch.stun.turn.XorPeerAddressAttribute;
import fm.liveswitch.stun.turn.XorRelayedAddressAttribute;

class IceStreamSocketManager extends IceSocketManager {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(IceStreamSocketManager.class);
    private static ILog __log = Log.getLogger(IceDatagramSocketManager.class);
    private CircularDataBuffer __receiveBuffer;
    private Scheduler __scheduler;
    private int __streamSendTimeout;
    private Object _context;
    private IceLocalRelayedCandidate _localRelayedCandidate;
    private byte _requestBitmask;
    private String _serverIPAddress;
    private StreamSocket _socket;

    /* access modifiers changed from: private */
    public void sendAllocateSuccess() {
    }

    /* access modifiers changed from: protected */
    public void closeSocket() {
        StreamSocket socket = getSocket();
        if (socket != null && !socket.getIsClosed()) {
            socket.close();
            __log.debug(StringExtensions.format("Closed socket for socket manager {0}.", (Object) super.getId()));
        } else if (socket == null && !super.getIsTerminatingOrTerminated()) {
            __log.debug(StringExtensions.format("Could not close socket for socket manager {0}: no socket instantiated - nothing to close.", (Object) super.getId()));
        } else if (socket.getIsClosed() && !super.getIsTerminatingOrTerminated()) {
            __log.debug(StringExtensions.format("Could not close socket for socket manager {0}: socket already closed.", (Object) super.getId()));
        }
    }

    private boolean doProcessServerResponse(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
        String str;
        String str2;
        Message response = iceSendRequestSuccessArgs.getResponse();
        TransportAddress remoteAddress = iceSendRequestSuccessArgs.getRemoteAddress();
        ScheduledItem item = iceSendRequestSuccessArgs.getItem();
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) item.getState();
        Error validateResponse = validateResponse(iceSendMessageArgs, response, remoteAddress);
        boolean z = response instanceof AllocateResponse;
        String str3 = z ? super.getServer().getIsSecure() ? "TURNS" : "TURN" : "STUN";
        if (validateResponse != null) {
            int intValue = HashMapExtensions.getItem(super.getNumberOfStunRequests()).get(iceSendMessageArgs.getAddress().getIPAddress()).intValue();
            String str4 = null;
            if (!z || !Global.equals(validateResponse.getCode(), ErrorCode.StunUnauthorized) || iceSendMessageArgs.getMessage().getNonce() != null || iceSendMessageArgs.getMessage().getRealm() != null) {
                String str5 = "";
                if (Global.equals(validateResponse.getCode(), ErrorCode.StunStaleNonce)) {
                    StaleNonceError staleNonceError = (StaleNonceError) validateResponse;
                    String value = staleNonceError.getNonce() != null ? staleNonceError.getNonce().getValue() : null;
                    if (staleNonceError.getRealm() != null) {
                        str4 = staleNonceError.getRealm().getValue();
                    }
                    if (intValue < super.getGatherOptions().getTurnAllocateRequestLimit()) {
                        str5 = " Further attempts to establish server allocation will be made with an updated nonce.";
                    }
                    __log.debug(StringExtensions.format("{3} server {0} reports error {1}.{2}", new Object[]{remoteAddress.toString(), validateResponse.getDescription(), str5, str3}));
                    str = str4;
                    str4 = value;
                } else if (Global.equals(validateResponse.getCode(), ErrorCode.StunTryAlternate)) {
                    TryAlternateStunError tryAlternateStunError = (TryAlternateStunError) validateResponse;
                    iceSendMessageArgs.setAddress(new TransportAddress(tryAlternateStunError.getAlternateServer().getIPAddress(), tryAlternateStunError.getAlternateServer().getPort()));
                    if (intValue < super.getGatherOptions().getTurnAllocateRequestLimit()) {
                        str5 = z ? " Attempts to establish server allocation with a new server will be made." : " Attempts to establish server binding with a new server will be made.";
                    }
                    __log.debug(StringExtensions.format("{2} server sent an instruction to try an alternate server {0}.{2}.", iceSendMessageArgs.getAddress().toString(), str5, str3));
                    str = null;
                } else {
                    if (Global.equals(validateResponse.getCode(), ErrorCode.StunTurnAllocationMismatch)) {
                        str2 = StringExtensions.format("TURN allocate request to {0} from {1}: allocation mismatch detected.", remoteAddress.toString(), getSocket().getLocalIPAddress());
                        IAction1<IceSocketManager> onAllocationMismatchException = super.getOnAllocationMismatchException();
                        if (onAllocationMismatchException != null) {
                            onAllocationMismatchException.invoke(this);
                        }
                    } else {
                        str2 = StringExtensions.format("{2} server {0} reports error {1}.", remoteAddress.toString(), validateResponse.getDescription(), str3);
                    }
                    __log.debug(str2);
                    super.removeTransaction(item);
                    super.setError(validateResponse);
                    super.setState(IceGatheringState.Failed);
                    return false;
                }
            } else {
                UnauthorizedStunError unauthorizedStunError = (UnauthorizedStunError) validateResponse;
                String value2 = unauthorizedStunError.getNonce() != null ? unauthorizedStunError.getNonce().getValue() : null;
                if (unauthorizedStunError.getRealm() != null) {
                    str4 = unauthorizedStunError.getRealm().getValue();
                }
                str = str4;
                str4 = value2;
            }
            if (intValue < super.getGatherOptions().getTurnAllocateRequestLimit()) {
                if (!StringExtensions.isNullOrEmpty(str4)) {
                    iceSendMessageArgs.getMessage().setNonce(new NonceAttribute(str4));
                }
                if (!StringExtensions.isNullOrEmpty(str)) {
                    iceSendMessageArgs.getMessage().setRealm(new RealmAttribute(str));
                }
                if (z) {
                    super.serverAllocate(iceSendMessageArgs.getMessage().getUsername().getValue(), iceSendMessageArgs.getRelayPassword(), iceSendMessageArgs.getAddress().getIPAddress(), iceSendMessageArgs.getAddress().getPort(), false, str4, str, new IActionDelegate1<ScheduledItem>() {
                        public String getId() {
                            return "fm.liveswitch.IceStreamSocketManager.sendAllocateMessage";
                        }

                        public void invoke(ScheduledItem scheduledItem) {
                            IceStreamSocketManager.this.sendAllocateMessage(scheduledItem);
                        }
                    }, new IActionDelegate1<IceSendRequestSuccessArgs>() {
                        public String getId() {
                            return "fm.liveswitch.IceStreamSocketManager.processServerResponse";
                        }

                        public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                            IceStreamSocketManager.this.processServerResponse(iceSendRequestSuccessArgs);
                        }
                    }, new IActionDelegate1<ScheduledItem>() {
                        public String getId() {
                            return "fm.liveswitch.IceStreamSocketManager.sendAllocateTimedOut";
                        }

                        public void invoke(ScheduledItem scheduledItem) {
                            IceStreamSocketManager.this.sendAllocateTimedOut(scheduledItem);
                        }
                    });
                }
                super.removeTransaction(item);
            } else {
                super.setError(validateResponse);
                super.setState(IceGatheringState.Failed);
            }
        } else if (!remoteAddress.equals(iceSendMessageArgs.getAddress())) {
            __log.debug(StringExtensions.format("{2} server response source {0} does not match targeted endpoint {1}.", remoteAddress.toString(), iceSendMessageArgs.getAddress().toString(), str3));
            super.removeTransaction(item);
            return false;
        } else {
            XorMappedAddressAttribute xorMappedAddress = response.getXorMappedAddress();
            if (xorMappedAddress != null) {
                xorMappedAddress.getIPAddress();
                xorMappedAddress.getPort();
            } else {
                MappedAddressAttribute mappedAddress = response.getMappedAddress();
                if (mappedAddress != null) {
                    mappedAddress.getIPAddress();
                    mappedAddress.getPort();
                }
            }
            if (z) {
                try {
                    AllocateRequest allocateRequest = (AllocateRequest) iceSendMessageArgs.getMessage();
                    String relayPassword = iceSendMessageArgs.getRelayPassword();
                    String value3 = allocateRequest.getUsername() == null ? StringExtensions.empty : allocateRequest.getUsername().getValue();
                    XorRelayedAddressAttribute xorRelayedAddress = response.getXorRelayedAddress();
                    LifetimeAttribute lifetime = response.getLifetime();
                    String value4 = allocateRequest.getRealm() == null ? StringExtensions.empty : allocateRequest.getRealm().getValue();
                    String value5 = allocateRequest.getNonce() == null ? StringExtensions.empty : allocateRequest.getNonce().getValue();
                    if (TransportAddress.isAny(xorRelayedAddress.getIPAddress())) {
                        xorRelayedAddress.setIPAddress(remoteAddress.getIPAddress());
                    }
                    IceLocalRelayedCandidate iceLocalRelayedCandidate = new IceLocalRelayedCandidate(super.getLock(), IceCandidate.generateLocalCandidateFoundation(CandidateType.Relayed, xorRelayedAddress.getIPAddress(), remoteAddress, ProtocolType.Udp), ProtocolType.Udp, xorRelayedAddress.getIPAddress(), xorRelayedAddress.getPort(), xorMappedAddress.getIPAddress(), xorMappedAddress.getPort(), lifetime.getLifetime(), value3, relayPassword, value5, value4, iceSendMessageArgs.getAddress(), this._transactionManager, super.getGatherOptions().getStunRequestTimeout());
                    iceLocalRelayedCandidate.setAdapterSpeed(getSocket().getAdapterSpeed());
                    iceLocalRelayedCandidate.setDispatchStunMessage(new IActionDelegate1<IceSendMessageArgs>() {
                        public String getId() {
                            return "fm.liveswitch.IceStreamSocketManager.sendStunMessage";
                        }

                        public void invoke(IceSendMessageArgs iceSendMessageArgs) {
                            IceStreamSocketManager.this.sendStunMessage(iceSendMessageArgs);
                        }
                    });
                    iceLocalRelayedCandidate.setDispatchApplicationData(new IFunctionDelegate3<DataBuffer, IceCandidate, TransportAddress, Error>() {
                        public String getId() {
                            return "fm.liveswitch.IceSocketManager.sendApplicationData";
                        }

                        public Error invoke(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
                            return IceStreamSocketManager.this.sendApplicationData(dataBuffer, iceCandidate, transportAddress);
                        }
                    });
                    iceLocalRelayedCandidate.setOnRelayStateChanged(new IActionDelegate1<IceLocalRelayedCandidateState>() {
                        public String getId() {
                            return "fm.liveswitch.IceSocketManager.processRelayedCandidateStateChanged";
                        }

                        public void invoke(IceLocalRelayedCandidateState iceLocalRelayedCandidateState) {
                            IceStreamSocketManager.this.processRelayedCandidateStateChanged(iceLocalRelayedCandidateState);
                        }
                    });
                    iceLocalRelayedCandidate.setRelayProtocol(super.getProtocol());
                    int calculateInstantaneousRtt = IceTransactionManager.calculateInstantaneousRtt(false, iceSendRequestSuccessArgs, true, Scheduler.getCurrentTime());
                    if (calculateInstantaneousRtt >= 0) {
                        iceLocalRelayedCandidate.setLastRelayServerRoundTripTime(calculateInstantaneousRtt);
                        iceLocalRelayedCandidate.setSmoothedRelayServerRoundTripTime(IceTransactionManager.calculateSmoothedRtt(calculateInstantaneousRtt, iceLocalRelayedCandidate.getSmoothedRelayServerRoundTripTime()));
                    }
                    setLocalRelayedCandidate(iceLocalRelayedCandidate);
                    super.raiseLocalCandidate(iceLocalRelayedCandidate);
                    iceLocalRelayedCandidate.startScheduleRefreshTransactions();
                } catch (Exception e) {
                    __log.debug(StringExtensions.format("Stream Socket Manager: Could not process TURN server response: {0}", (Object) new Error(ErrorCode.IceLocalRelayedDatagramCandidateError, e).getDescription()));
                }
            }
            super.removeTransaction(item);
        }
        return true;
    }

    private void doReceive() {
        StreamSocket socket = getSocket();
        if (socket == null || !socket.getIsClosed()) {
            receive();
        } else {
            raiseClose();
        }
    }

    private Scheduler get_Scheduler() {
        return this.__scheduler;
    }

    /* access modifiers changed from: package-private */
    public long getAdapterSpeed() {
        if (getSocket() != null) {
            return getSocket().getAdapterSpeed();
        }
        return 0;
    }

    public Object getContext() {
        return this._context;
    }

    /* access modifiers changed from: package-private */
    public String getLocalIpAddress() {
        if (getSocket() != null) {
            return getSocket().getLocalIPAddress();
        }
        return StringExtensions.empty;
    }

    /* access modifiers changed from: package-private */
    public int getLocalPort() {
        if (getSocket() != null) {
            return getSocket().getLocalPort();
        }
        return 0;
    }

    public IceLocalRelayedCandidate getLocalRelayedCandidate() {
        return this._localRelayedCandidate;
    }

    public String getServerIPAddress() {
        return this._serverIPAddress;
    }

    public StreamSocket getSocket() {
        return this._socket;
    }

    /* access modifiers changed from: package-private */
    public int getStreamSendTimeout() {
        return this.__streamSendTimeout;
    }

    public IceStreamSocketManager(Object obj, StreamSocket streamSocket, IceTransactionManager iceTransactionManager, IceServer iceServer, Scheduler scheduler) {
        this(obj, streamSocket, iceTransactionManager, iceServer, scheduler, (String) null);
    }

    public IceStreamSocketManager(Object obj, StreamSocket streamSocket, IceTransactionManager iceTransactionManager, IceServer iceServer, Scheduler scheduler, String str) {
        super(obj, iceTransactionManager);
        this.__streamSendTimeout = -1;
        this._requestBitmask = BitAssistant.castByte(192);
        this.__receiveBuffer = CircularDataBuffer.create(5000);
        super.setProtocol(iceServer.getIsSecure() ? ProtocolType.Tls : ProtocolType.Tcp);
        if (streamSocket != null) {
            setSocket(streamSocket);
            getSocket().setOnReceiveSuccess(new IActionDelegate1<DataBuffer>() {
                public String getId() {
                    return "fm.liveswitch.IceStreamSocketManager.processReceiveSuccess";
                }

                public void invoke(DataBuffer dataBuffer) {
                    IceStreamSocketManager.this.processReceiveSuccess(dataBuffer);
                }
            });
            getSocket().setOnReceiveFailure(new IActionDelegate2<Exception, Boolean>() {
                public String getId() {
                    return "fm.liveswitch.IceStreamSocketManager.processReceiveFailure";
                }

                public void invoke(Exception exc, Boolean bool) {
                    IceStreamSocketManager.this.processReceiveFailure(exc, bool.booleanValue());
                }
            });
            super.setServer(iceServer);
            set_Scheduler(scheduler);
            setServerIPAddress(str);
            if (str == null) {
                setServerIPAddress(super.getServer() == null ? null : super.getServer().getIPAddress());
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception("Socket cannot be null."));
    }

    /* access modifiers changed from: private */
    public void onTurnServerConnectedFailure(Exception exc, boolean z) {
        if (!Global.equals(super.getState(), IceGatheringState.Closing) && !Global.equals(super.getState(), IceGatheringState.Closed) && !Global.equals(super.getState(), IceGatheringState.Failed)) {
            String str = z ? "timed out" : "failed";
            int port = super.getServer().getPort();
            ErrorCode errorCode = ErrorCode.IceLocalRelayedStreamCandidateError;
            Object[] objArr = new Object[6];
            objArr[0] = exc == null ? "" : StringExtensions.concat(" ", exc.getMessage());
            objArr[1] = str;
            objArr[2] = super.getServer().getUrl();
            objArr[3] = IntegerExtensions.toString(Integer.valueOf(port));
            objArr[4] = getLocalIpAddress();
            objArr[5] = IntegerExtensions.toString(Integer.valueOf(getLocalPort()));
            super.setError(new Error(errorCode, new Exception(StringExtensions.format("TCP TURN server connect request from {4}:{5} {1}.{0} Is a TCP socket listening on port {3} on server {2}? Is the TURN server reachable from this interface?", objArr))));
            __log.warn(super.getError().getDescription());
            super.setState(IceGatheringState.Failed);
        }
    }

    /* access modifiers changed from: private */
    public void onTurnServerConnectedSuccess() {
        synchronized (super.getLock()) {
            if (!Global.equals(super.getState(), IceGatheringState.Failed)) {
                doReceive();
                super.serverAllocate(super.getServer().getUsername(), super.getServer().getPassword(), super.getServer().getIPAddress(), super.getServer().getPort(), false, (String) null, (String) null, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceStreamSocketManager.sendAllocateMessage";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceStreamSocketManager.this.sendAllocateMessage(scheduledItem);
                    }
                }, new IActionDelegate1<IceSendRequestSuccessArgs>() {
                    public String getId() {
                        return "fm.liveswitch.IceStreamSocketManager.processServerResponse";
                    }

                    public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                        IceStreamSocketManager.this.processServerResponse(iceSendRequestSuccessArgs);
                    }
                }, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceStreamSocketManager.sendAllocateTimedOut";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceStreamSocketManager.this.sendAllocateTimedOut(scheduledItem);
                    }
                });
            }
        }
    }

    private Message postProcess(DataBuffer dataBuffer, TransportAddress transportAddress, Holder<DataBuffer> holder, Holder<TransportAddress> holder2, BooleanHolder booleanHolder, IntegerHolder integerHolder) {
        Message message;
        integerHolder.setValue(0);
        try {
            message = Message.readFrom(dataBuffer, 0, integerHolder);
            try {
                if (message instanceof DataIndication) {
                    DataAttribute data = message.getData();
                    XorPeerAddressAttribute xorPeerAddress = message.getXorPeerAddress();
                    holder.setValue(data.getData());
                    holder2.setValue(new TransportAddress(xorPeerAddress.getIPAddress(), xorPeerAddress.getPort()));
                    booleanHolder.setValue(true);
                    return Message.readFrom(holder.getValue());
                }
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            message = null;
        }
        holder.setValue(dataBuffer);
        holder2.setValue(transportAddress);
        booleanHolder.setValue(false);
        return message;
    }

    private boolean process(DataBuffer dataBuffer, IntegerHolder integerHolder) {
        IAction3<Message, IceCandidate, TransportAddress> onStunRequest;
        integerHolder.setValue(0);
        StreamSocket socket = getSocket();
        if (socket != null && !socket.getIsClosed()) {
            TransportAddress transportAddress = new TransportAddress(socket.getRemoteIPAddress(), socket.getRemotePort());
            String str = null;
            Holder holder = new Holder(null);
            Holder holder2 = new Holder(transportAddress);
            BooleanHolder booleanHolder = new BooleanHolder(false);
            Message postProcess = postProcess(dataBuffer, transportAddress, holder, holder2, booleanHolder, integerHolder);
            DataBuffer dataBuffer2 = (DataBuffer) holder.getValue();
            TransportAddress transportAddress2 = (TransportAddress) holder2.getValue();
            boolean value = booleanHolder.getValue();
            if (postProcess != null) {
                str = Base64.encodeBuffer(postProcess.getTransactionId());
            }
            IceSendRequestSuccessArgs iceSendRequestSuccessArgs = new IceSendRequestSuccessArgs();
            iceSendRequestSuccessArgs.setRemoteAddress(transportAddress2);
            iceSendRequestSuccessArgs.setResponse(postProcess);
            iceSendRequestSuccessArgs.setRelayed(value);
            if (!(str != null && this._transactionManager.tryTriggerOnResponse(str, iceSendRequestSuccessArgs)) && postProcess != null && (postProcess instanceof BindingRequest) && (onStunRequest = super.getOnStunRequest()) != null) {
                onStunRequest.invoke(postProcess, getLocalRelayedCandidate(), transportAddress2);
            }
            if (postProcess != null) {
                return true;
            }
            if (!Global.equals(dataBuffer2, dataBuffer)) {
                super.getOnIncomingData().invoke(dataBuffer2, getLocalRelayedCandidate(), transportAddress2);
                return true;
            }
        }
        return false;
    }

    private void processBuffer(DataBuffer dataBuffer) {
        synchronized (super.getLock()) {
            this.__receiveBuffer.appendDataBuffer(dataBuffer);
            processTcpReceive(this.__receiveBuffer);
        }
        if (getSocket().getIsClosed()) {
            raiseClose();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean processBuffer(DataBuffer dataBuffer, IntegerHolder integerHolder) {
        integerHolder.setValue(0);
        if (dataBuffer.getLength() > 0) {
            byte read8 = dataBuffer.read8(0) & this._requestBitmask;
            boolean z = true;
            boolean z2 = read8 == 0;
            if (read8 != 64) {
                z = false;
            }
            if (z2) {
                return process(dataBuffer, integerHolder) | false;
            }
            if (z) {
                dataBuffer.getLength();
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void processReceiveFailure(Exception exc, boolean z) {
        if (!z) {
            super.setError(new Error(ErrorCode.SocketReceiveError, exc));
            super.setState(IceGatheringState.Failed);
        }
        if (!Global.equals(super.getState(), IceGatheringState.Closed) && !Global.equals(super.getState(), IceGatheringState.Failed)) {
            doReceive();
        }
    }

    /* access modifiers changed from: package-private */
    public void processReceiveSuccess(DataBuffer dataBuffer) {
        try {
            if (!Global.equals(super.getState(), IceGatheringState.Closed) && !Global.equals(super.getState(), IceGatheringState.Failed)) {
                processBuffer(dataBuffer);
            }
        } catch (Exception unused) {
        }
        if (!Global.equals(super.getState(), IceGatheringState.Closed) && !Global.equals(super.getState(), IceGatheringState.Failed)) {
            doReceive();
        }
    }

    /* access modifiers changed from: private */
    public void processServerResponse(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
        try {
            if (!(iceSendRequestSuccessArgs.getResponse() instanceof BindingResponse)) {
                if (!(iceSendRequestSuccessArgs.getResponse() instanceof AllocateResponse)) {
                    __log.error("Currently unsupported TCP response received from server.");
                    return;
                }
            }
            doProcessServerResponse(iceSendRequestSuccessArgs);
        } catch (Exception e) {
            __log.error(e.toString());
        }
    }

    private void processTcpReceive(CircularDataBuffer circularDataBuffer) {
        do {
            IntegerHolder integerHolder = new IntegerHolder(0);
            boolean processBuffer = processBuffer(DataBuffer.wrap(circularDataBuffer.toArray()), integerHolder);
            int value = integerHolder.getValue();
            if (processBuffer) {
                circularDataBuffer.discard(value);
            } else {
                return;
            }
        } while (circularDataBuffer.getLength() != 0);
    }

    private void raiseClose() {
        synchronized (super.getLock()) {
            if (!Global.equals(super.getState(), IceGatheringState.Closed)) {
                super.setError(new Error(ErrorCode.SocketClosed, new Exception("Socket closed.")));
                super.setState(IceGatheringState.Failed);
            }
        }
        this.__receiveBuffer.free();
    }

    private void receive() {
        try {
            getSocket().receiveAsync(0);
        } catch (Exception e) {
            if (__log.getIsDebugEnabled()) {
                __log.debug(StringExtensions.format("Could not receive on server TCP socket. {0}", (Object) e.getMessage()));
            }
            getSocket().close();
            raiseClose();
        }
    }

    /* access modifiers changed from: private */
    public void sendAllocateFailure(IceSendMessageArgs iceSendMessageArgs, Exception exc, boolean z) {
        if (!Global.equals(super.getState(), IceGatheringState.Closed)) {
            super.setError(exc.getMessage().equals("Socket closed") ? new Error(ErrorCode.SocketClosed, new Exception("Could not send allocate request over TCP connection: socket closed.")) : z ? new Error(ErrorCode.SocketSendError, new Exception("Could not send allocate request over TCP connection: request timed out.")) : new Error(ErrorCode.SocketSendError, exc));
            IAction1<IceSendRequestFailureArgs> onFailure = iceSendMessageArgs.getOnFailure();
            IceSendRequestFailureArgs iceSendRequestFailureArgs = new IceSendRequestFailureArgs();
            iceSendRequestFailureArgs.setCandidatePairId(iceSendMessageArgs.getCandidatePairId());
            iceSendRequestFailureArgs.setError(super.getError());
            iceSendRequestFailureArgs.setAddress(iceSendMessageArgs.getAddress());
            iceSendRequestFailureArgs.setTurnRelay(iceSendMessageArgs.getTurnRelay());
            if (onFailure != null) {
                onFailure.invoke(iceSendRequestFailureArgs);
            } else {
                __log.debug(StringExtensions.format("Unable to send request: {0}", (Object) iceSendRequestFailureArgs.getError().getDescription()));
            }
            super.setState(IceGatheringState.Failed);
        }
    }

    /* access modifiers changed from: private */
    public void sendAllocateMessage(ScheduledItem scheduledItem) {
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
        iceSendMessageArgs.getMessage().setTransactionTransmitCounter(new TransactionTransmitCounterAttribute(scheduledItem.getInvocationCount()));
        sendStunMessage(iceSendMessageArgs, false, new IActionDelegate0() {
            public String getId() {
                return "fm.liveswitch.IceStreamSocketManager.sendAllocateSuccess";
            }

            public void invoke() {
                IceStreamSocketManager.this.sendAllocateSuccess();
            }
        }, new IActionDelegate3<IceSendMessageArgs, Exception, Boolean>() {
            public String getId() {
                return "fm.liveswitch.IceStreamSocketManager.sendAllocateFailure";
            }

            public void invoke(IceSendMessageArgs iceSendMessageArgs, Exception exc, Boolean bool) {
                IceStreamSocketManager.this.sendAllocateFailure(iceSendMessageArgs, exc, bool.booleanValue());
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendAllocateTimedOut(ScheduledItem scheduledItem) {
        super.removeTransaction(scheduledItem);
        if (!Global.equals(super.getState(), IceGatheringState.Closing) && !Global.equals(super.getState(), IceGatheringState.Closed) && !Global.equals(super.getState(), IceGatheringState.Failed)) {
            IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
            __log.warn(StringExtensions.format("Allocate request to {0}:{1} timed out.", iceSendMessageArgs.getAddress().getIPAddress(), IntegerExtensions.toString(Integer.valueOf(iceSendMessageArgs.getAddress().getPort()))));
            onTurnServerConnectedFailure((Exception) null, true);
        }
    }

    /* access modifiers changed from: protected */
    public Error sendApplicationData(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
        if (Global.equals(super.getState(), IceGatheringState.Closed) || Global.equals(super.getState(), IceGatheringState.Closing) || Global.equals(super.getState(), IceGatheringState.Failed) || Global.equals(super.getState(), IceGatheringState.New)) {
            return new Error(ErrorCode.SocketManagerInvalidState, StringExtensions.format("Attempt to send message while Stream Socket Manager in {0} state.", (Object) super.getState().toString()));
        }
        if (iceCandidate == null) {
            __log.error(StringExtensions.format("Attempted to send payload data to null candidate.", new Object[0]));
            return null;
        } else if (dataBuffer == null) {
            __log.error(StringExtensions.format("Attempted to send null payload data.", new Object[0]));
            return null;
        } else {
            StreamSocket socket = getSocket();
            if (socket == null) {
                super.setError(new Error(ErrorCode.SocketClosed, new Exception("Could not send application data: socket is closed.")));
                super.setState(IceGatheringState.Failed);
                return super.getError();
            }
            try {
                TransportAddress transportAddress2 = iceCandidate.getTransportAddress();
                Holder holder = new Holder(null);
                Holder holder2 = new Holder(transportAddress2);
                super.turnPreProcess(dataBuffer, transportAddress2, transportAddress, holder, holder2);
                DataBuffer dataBuffer2 = (DataBuffer) holder.getValue();
                TransportAddress transportAddress3 = (TransportAddress) holder2.getValue();
                if (!socket.getIsClosed()) {
                    socket.sendAsync(dataBuffer2, getStreamSendTimeout(), (IAction0) null, (IAction2<Exception, Boolean>) null);
                    return null;
                }
                super.setError(new Error(ErrorCode.SocketClosed, new Exception("Could not send application data: socket is not open.")));
                super.setState(IceGatheringState.Failed);
                return super.getError();
            } catch (Exception e) {
                if (__log.getIsDebugEnabled()) {
                    __log.debug("Could not send on socket.", e);
                }
                super.setError(new Error(ErrorCode.SocketSendError, e));
                super.setState(IceGatheringState.Failed);
                return super.getError();
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendStunMessage(IceSendMessageArgs iceSendMessageArgs) {
        sendStunMessage(iceSendMessageArgs, true, (IAction0) null, (IAction3<IceSendMessageArgs, Exception, Boolean>) null);
    }

    private void sendStunMessage(IceSendMessageArgs iceSendMessageArgs, boolean z, IAction0 iAction0, IAction3<IceSendMessageArgs, Exception, Boolean> iAction3) {
        tryDispatchStunMessage(iceSendMessageArgs, z, iAction0, iAction3);
    }

    private void set_Scheduler(Scheduler scheduler) {
        this.__scheduler = scheduler;
    }

    public void setContext(Object obj) {
        this._context = obj;
    }

    private void setLocalRelayedCandidate(IceLocalRelayedCandidate iceLocalRelayedCandidate) {
        this._localRelayedCandidate = iceLocalRelayedCandidate;
    }

    private void setServerIPAddress(String str) {
        this._serverIPAddress = str;
    }

    private void setSocket(StreamSocket streamSocket) {
        this._socket = streamSocket;
    }

    /* access modifiers changed from: package-private */
    public void setStreamSendTimeout(int i) {
        if (i >= -1) {
            this.__streamSendTimeout = i;
        }
    }

    public boolean start(IceGatherOptions iceGatherOptions) {
        synchronized (super.getLock()) {
            if (Global.equals(super.getState(), IceGatheringState.Gathering) || Global.equals(super.getState(), IceGatheringState.Complete)) {
                String format = StringExtensions.format("Could not start Stream Socket Manager {1}: it is in {0} state.", super.getState().toString(), super.getId());
                __log.debug(format);
                throw new RuntimeException(new Exception(format));
            }
            try {
                super.setGatherOptions(iceGatherOptions);
                String str = null;
                String localIPAddress = getSocket() == null ? null : getSocket().getLocalIPAddress();
                String serverIPAddress = getServerIPAddress();
                if (localIPAddress != null) {
                    if (Global.equals(LocalNetwork.getAddressType(localIPAddress), AddressType.IPv6)) {
                        str = StringExtensions.concat("[", localIPAddress, "]:", IntegerExtensions.toString(Integer.valueOf(getSocket().getLocalPort())));
                    } else {
                        str = StringExtensions.concat(localIPAddress, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, IntegerExtensions.toString(Integer.valueOf(getSocket().getLocalPort())));
                    }
                }
                if (!(str == null || serverIPAddress == null)) {
                    if (Global.equals(LocalNetwork.getAddressType(localIPAddress), LocalNetwork.getAddressType(serverIPAddress))) {
                        if (!Global.equals(super.getState(), IceGatheringState.New)) {
                            if (!Global.equals(super.getState(), IceGatheringState.Complete)) {
                                if (Global.equals(super.getState(), IceGatheringState.Closed)) {
                                    __log.error("Stream socket manager in closed state when attempting to gather local candidates");
                                    return false;
                                }
                                if (super.getServer().getIsTurn() || serverIPAddress == null) {
                                    super.setError(new Error(ErrorCode.IceInvalidServerAssignmentError, new Exception("Invalid TURN server assigned or server not resolved.")));
                                    __log.error(StringExtensions.format("Could not allocate on TURN server: {0}", (Object) super.getError().getDescription()));
                                } else {
                                    getSocket().connectAsync(super.getServer().getHost(), serverIPAddress, super.getServer().getPort(), iceGatherOptions.getTcpConnectTimeout(), new IActionDelegate0() {
                                        public String getId() {
                                            return "fm.liveswitch.IceStreamSocketManager.onTurnServerConnectedSuccess";
                                        }

                                        public void invoke() {
                                            IceStreamSocketManager.this.onTurnServerConnectedSuccess();
                                        }
                                    }, new IActionDelegate2<Exception, Boolean>() {
                                        public String getId() {
                                            return "fm.liveswitch.IceStreamSocketManager.onTurnServerConnectedFailure";
                                        }

                                        public void invoke(Exception exc, Boolean bool) {
                                            IceStreamSocketManager.this.onTurnServerConnectedFailure(exc, bool.booleanValue());
                                        }
                                    });
                                }
                                return true;
                            }
                        }
                        super.setState(IceGatheringState.Gathering);
                        if (super.getServer().getIsTurn()) {
                        }
                        super.setError(new Error(ErrorCode.IceInvalidServerAssignmentError, new Exception("Invalid TURN server assigned or server not resolved.")));
                        __log.error(StringExtensions.format("Could not allocate on TURN server: {0}", (Object) super.getError().getDescription()));
                        return true;
                    }
                }
                String format2 = str == null ? "Local socket ip address not set" : serverIPAddress == null ? "Server socket ip address not set." : StringExtensions.format("Local socket {0} IP version does not match remote server {1} IP version.", str, serverIPAddress);
                __log.debug(format2);
                super.setError(new Error(ErrorCode.SocketIPError, new Exception(format2)));
                super.setState(IceGatheringState.Failed);
                return false;
            } catch (Exception e) {
                __log.debug(StringExtensions.format("Could not start Stream Socket Manager {0}:{1}.", super.getId(), e.toString()));
                throw new RuntimeException(e);
            }
        }
    }

    private void tryDispatchStunMessage(final IceSendMessageArgs iceSendMessageArgs, boolean z, IAction0 iAction0, final IAction3<IceSendMessageArgs, Exception, Boolean> iAction3) {
        DataBuffer take;
        DataBuffer dataBuffer;
        try {
            if (getSocket().getIsClosed() && iAction3 != null) {
                iAction3.invoke(iceSendMessageArgs, new Exception("Socket closed"), false);
            }
            take = __dataBufferPool.take(iceSendMessageArgs.getMessage().getLength());
            iceSendMessageArgs.getMessage().writeTo(take);
            TransportAddress address = iceSendMessageArgs.getAddress();
            if (z) {
                Holder holder = new Holder(take);
                Holder holder2 = new Holder(address);
                super.turnPreProcess(take, address, iceSendMessageArgs.getTurnRelay(), holder, holder2);
                dataBuffer = (DataBuffer) holder.getValue();
                TransportAddress transportAddress = (TransportAddress) holder2.getValue();
            } else {
                dataBuffer = take;
            }
            if (!getSocket().getIsClosed()) {
                if (iAction3 != null) {
                    getSocket().sendAsync(dataBuffer, getStreamSendTimeout(), iAction0, new IAction2<Exception, Boolean>() {
                        public void invoke(Exception exc, Boolean bool) {
                            iAction3.invoke(iceSendMessageArgs, exc, bool);
                        }
                    });
                } else {
                    getSocket().sendAsync(dataBuffer, getStreamSendTimeout(), iAction0, (IAction2<Exception, Boolean>) null);
                }
            }
            take.free();
        } catch (Exception e) {
            iAction3.invoke(iceSendMessageArgs, e, false);
        } catch (Throwable th) {
            take.free();
            throw th;
        }
    }

    static Error validateResponse(IceSendMessageArgs iceSendMessageArgs, Message message, TransportAddress transportAddress) {
        if ((iceSendMessageArgs.getMessage() instanceof BindingRequest) && !(message instanceof BindingResponse)) {
            return new Error(ErrorCode.StunInvalidResponseType, new Exception("Client generated a Stun Binding Request but received a message of the type other than Binding Response."));
        }
        if (!iceSendMessageArgs.getMessage().getTransactionId().sequenceEquals(message.getTransactionId())) {
            return new Error(ErrorCode.StunInvalidTransactionId, new Exception(StringExtensions.format("Response transaction ID {0} does not match request transaction ID {1}.", message.getTransactionId().toHexString(), iceSendMessageArgs.getMessage().getTransactionId().toHexString())));
        }
        if (!Global.equals(message.getMessageType(), MessageType.ErrorResponse)) {
            return null;
        }
        ErrorCodeAttribute errorCode = message.getErrorCode();
        if (errorCode == null) {
            return new Error(ErrorCode.StunInvalidErrorCode, new Exception("Error response received, but no error code was supplied."));
        }
        Error createStunError = Error.createStunError(errorCode.getCode(), message);
        if (createStunError != null) {
            return createStunError;
        }
        return new Error(ErrorCode.StunUnknownStunErrorCode, new Exception(StringExtensions.format("Server responded with an unknown error code ({0}).", (Object) IntegerExtensions.toString(Integer.valueOf(errorCode.getCode())))));
    }
}
