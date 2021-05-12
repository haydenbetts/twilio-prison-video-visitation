package fm.liveswitch;

import com.google.android.exoplayer2.DefaultLoadControl;
import fm.liveswitch.stun.BindingRequest;
import fm.liveswitch.stun.FingerprintAttribute;
import fm.liveswitch.stun.MessageIntegrityAttribute;
import fm.liveswitch.stun.UsernameAttribute;
import fm.liveswitch.stun.Utility;
import fm.liveswitch.stun.ice.ControlledAttribute;
import fm.liveswitch.stun.ice.ControllingAttribute;
import fm.liveswitch.stun.ice.PriorityAttribute;
import fm.liveswitch.stun.ice.UseCandidateAttribute;

class IceCandidatePair {
    private static ILog __log = Log.getLogger(IceCandidatePair.class);
    private boolean __awaitingOriginalRelayPermissions;
    private IceComponent __component;
    private int __connectionTimeout;
    private ScheduledItem __connectivityCheckScheduledItem;
    private String __id;
    private int __keepAliveTimeout;
    private String __lastKeepAliveTransactionId;
    private long __lastReceivedSomethingTimestamp;
    public int __lastRoundTripTime;
    private IceCandidate __local;
    private IceParameters __localParameters;
    private IceRole __localRole;
    private Object __lock;
    private long __permissionExpiresTimestamp;
    private volatile boolean __relayPermissionsActive;
    private ScheduledItem __relayPermissionsRefreshScheduledItem;
    private IceCandidate __remote;
    private IceParameters __remoteParameters;
    private int __smoothedRoundTripTime;
    private CandidatePairState __state;
    private long __tieBreaker;
    private IceTransactionManager __transactionManager;
    private AtomicLong _bytesReceived;
    private AtomicLong _bytesSent;
    private AtomicLong _consentRequestsReceived;
    private AtomicLong _consentRequestsSent;
    private AtomicLong _consentResponsesReceived;
    private AtomicLong _consentResponsesSent;
    private Error _error;
    private boolean _nominated;
    IAction1<IceSendRequestSuccessArgs> _onKeepAliveResponseReceived;
    private IAction1<IceCandidatePair> _onOriginalRelayPermissionsObtained;
    IAction1<IceCandidatePair> _onPriorityChange;
    private IAction1<IceCandidatePair> _onStateChange;
    private IAction1<IceSendRequestSuccessArgs> _onStunResponse;
    private long _priority;
    private String _relatedValidPairId;
    private AtomicLong _requestsSent;
    private AtomicLong _responsesReceived;
    private ScheduledItem _stopCandidatePair;
    private int _totalRoundTripTime;
    private boolean _useCandidateReceived;
    private boolean _valid;
    private boolean _verboseDebugMessages;

    public void assignPriority(IceRole iceRole) {
        long j;
        long j2;
        if (Global.equals(iceRole, IceRole.Controlling)) {
            j2 = getLocal().getPriority();
            j = getRemote().getPriority();
        } else {
            j2 = getRemote().getPriority();
            j = getLocal().getPriority();
        }
        setPriority((MathAssistant.min(j2, j) * 4294967296L) + (MathAssistant.max(j2, j) * 2) + (j2 > j ? 1 : 0));
    }

    /* access modifiers changed from: package-private */
    public void bypassConnectivityChecks() {
        setState(CandidatePairState.Succeeded);
    }

    public static String calculateFoundation(IceCandidate iceCandidate, IceCandidate iceCandidate2) {
        return StringExtensions.concat(iceCandidate.getFoundation(), iceCandidate2.getFoundation());
    }

    private void cancelAssociatedTransactions() {
        if (Global.equals(getLocal().getType(), CandidateType.Relayed)) {
            synchronized (this.__lock) {
                this.__relayPermissionsActive = false;
                IceTransactionManager iceTransactionManager = this.__transactionManager;
                if (iceTransactionManager != null) {
                    iceTransactionManager.remove(this.__relayPermissionsRefreshScheduledItem, (Object) this);
                }
                this.__relayPermissionsRefreshScheduledItem = null;
            }
        }
        cancelConnectivityCheck();
    }

    public void cancelConnectivityCheck() {
        ScheduledItem scheduledItem = this.__connectivityCheckScheduledItem;
        if (scheduledItem != null) {
            ((IceSendMessageArgs) scheduledItem.getState()).cancelTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    public void checkForConnectivity() {
        if (Global.equals(getState(), CandidatePairState.Waiting)) {
            setState(CandidatePairState.InProgress);
            BindingRequest createBindingRequest = createBindingRequest();
            TransportAddress transportAddress = null;
            if (Global.equals(getLocal().getType(), CandidateType.Relayed)) {
                transportAddress = ((IceLocalRelayedCandidate) getLocal()).getTurnServer();
            }
            IceSendMessageArgs iceSendMessageArgs = new IceSendMessageArgs(createBindingRequest, new TransportAddress(getRemote().getIPAddress(), getRemote().getPort()));
            iceSendMessageArgs.setTurnRelay(transportAddress);
            iceSendMessageArgs.setCandidatePairId(getId());
            iceSendMessageArgs.setOnResponse(new IActionDelegate1<IceSendRequestSuccessArgs>() {
                public String getId() {
                    return "fm.liveswitch.IceCandidatePair.processStunResponse";
                }

                public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                    IceCandidatePair.this.processStunResponse(iceSendRequestSuccessArgs);
                }
            });
            iceSendMessageArgs.setOnFailure(new IActionDelegate1<IceSendRequestFailureArgs>() {
                public String getId() {
                    return "fm.liveswitch.IceCandidatePair.processConnectivityCheckExecutionFailure";
                }

                public void invoke(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
                    IceCandidatePair.this.processConnectivityCheckExecutionFailure(iceSendRequestFailureArgs);
                }
            });
            ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
                public String getId() {
                    return "fm.liveswitch.IceCandidatePair.sendConnectivityCheck";
                }

                public void invoke(ScheduledItem scheduledItem) {
                    IceCandidatePair.this.sendConnectivityCheck(scheduledItem);
                }
            }, 0, Global.equals(getLocal().getProtocol(), ProtocolType.Udp) ? 100 : ScheduledItem.getUnset(), getRelayConnectionTimeout(), Global.equals(getLocal().getProtocol(), ProtocolType.Udp) ? ScheduledItem.getUnset() : 1);
            scheduledItem.setState(iceSendMessageArgs);
            scheduledItem.setIntervalBackoffMultiplier(2.0f);
            scheduledItem.setTimeoutCallback(new IActionDelegate1<ScheduledItem>() {
                public String getId() {
                    return "fm.liveswitch.IceCandidatePair.connectivityCheckTimedout";
                }

                public void invoke(ScheduledItem scheduledItem) {
                    IceCandidatePair.this.connectivityCheckTimedout(scheduledItem);
                }
            });
            scheduledItem.setRecordDetailedInvocationTimes(true);
            this.__connectivityCheckScheduledItem = scheduledItem;
            if (this._verboseDebugMessages) {
                __log.debug(StringExtensions.format("Checking candidate pair {0} for connectivity.", (Object) toString()));
            }
            this.__transactionManager.addTransaction(this.__connectivityCheckScheduledItem, this);
        }
    }

    /* access modifiers changed from: private */
    public void connectivityCheckTimedout(ScheduledItem scheduledItem) {
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
        synchronized (this.__lock) {
            if (!Global.equals(getState(), CandidatePairState.Succeeded)) {
                this.__transactionManager.remove(scheduledItem, (Object) this);
                if (!iceSendMessageArgs.getCancelled()) {
                    fail(new Error(ErrorCode.IceConnectivityCheckFailed, StringExtensions.format("Connectivity check failed from {0} to {1}.", getLocal().getTransportAddress().toString(), getRemote().getTransportAddress().toString())));
                }
            }
        }
    }

    static BindingRequest createBindingRequest(IceCandidate iceCandidate, IceParameters iceParameters, IceParameters iceParameters2, IceComponent iceComponent, IceRole iceRole, long j) {
        if (iceParameters2.getUsernameFragment() != null && iceParameters2.getPassword() != null) {
            BindingRequest bindingRequest = new BindingRequest();
            bindingRequest.setUsername(new UsernameAttribute(StringExtensions.format("{0}:{1}", iceParameters2.getUsernameFragment(), iceParameters.getUsernameFragment())));
            if (Global.equals(iceRole, IceRole.Controlling)) {
                bindingRequest.setIceControlling(new ControllingAttribute(j));
                bindingRequest.setUseCandidate(new UseCandidateAttribute());
            } else {
                bindingRequest.setIceControlled(new ControlledAttribute(j));
            }
            bindingRequest.setPriority(new PriorityAttribute(IceCandidate.calculatePriority(CandidateType.PeerReflexive, IceCandidate.calculateLocalPreference(iceCandidate.getType(), iceCandidate.getPriority(), iceComponent, iceCandidate.getRelayProtocol()), iceComponent, iceCandidate.getRelayProtocol())));
            bindingRequest.setMessageIntegrity(new MessageIntegrityAttribute(Utility.createShortTermKey(iceParameters2.getPassword())));
            bindingRequest.setFingerprint(new FingerprintAttribute());
            return bindingRequest;
        } else if (iceParameters2.getUsernameFragment() == null && iceParameters2.getPassword() == null) {
            __log.warn("Could not create binding request. Remote candidate username and password were null.");
            return null;
        } else if (iceParameters2.getUsernameFragment() == null) {
            __log.warn("Could not create binding request. Remote candidate username was null.");
            return null;
        } else {
            __log.warn("Could not create binding request. Remote candidate password was null.");
            return null;
        }
    }

    public BindingRequest createBindingRequest() {
        return createBindingRequest(getLocal(), this.__localParameters, this.__remoteParameters, this.__component, this.__localRole, this.__tieBreaker);
    }

    /* access modifiers changed from: private */
    public void createPermissionFailure(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
        Error error = iceSendRequestFailureArgs.getError();
        synchronized (this.__lock) {
            if (this.__relayPermissionsActive) {
                String description = error != null ? error.getDescription() : "";
                if (__log.getIsWarnEnabled()) {
                    __log.error(StringExtensions.format("CreatePermission request failed for {0}. {1}", toString(), description));
                }
            }
            setError(error);
            setState(CandidatePairState.Failed);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchActiveKeepAlive() {
        if (Global.equals(getState(), CandidatePairState.Succeeded) || Global.equals(getState(), CandidatePairState.ConnectivityLost)) {
            verifyConnectivity();
            doSendKeepAlive();
        }
    }

    /* access modifiers changed from: package-private */
    public void doSendKeepAlive() {
        IceTransactionManager iceTransactionManager;
        IceSendMessageArgs prepareBindingRequestContext = prepareBindingRequestContext(getLocal(), this.__localParameters, getRemote(), this.__remoteParameters, this.__component, this.__localRole, this.__tieBreaker, getId(), this._onKeepAliveResponseReceived, new IActionDelegate1<IceSendRequestFailureArgs>() {
            public String getId() {
                return "fm.liveswitch.IceCandidatePair.processKeepAliveExecutionFailure";
            }

            public void invoke(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
                IceCandidatePair.this.processKeepAliveExecutionFailure(iceSendRequestFailureArgs);
            }
        });
        String str = this.__lastKeepAliveTransactionId;
        if (!(str == null || (iceTransactionManager = this.__transactionManager) == null)) {
            iceTransactionManager.remove(str, (Object) this);
        }
        this.__lastKeepAliveTransactionId = Base64.encodeBuffer(prepareBindingRequestContext.getMessage().getTransactionId());
        this._requestsSent.add(1);
        IceTransactionManager iceTransactionManager2 = this.__transactionManager;
        if (iceTransactionManager2 != null) {
            iceTransactionManager2.addTransaction(prepareBindingRequestContext, this, this.__lastKeepAliveTransactionId);
        }
        IceCandidate local = getLocal();
        if (local != null) {
            local.sendStunMessage(prepareBindingRequestContext);
        }
    }

    public boolean equals(IceCandidate iceCandidate, IceCandidate iceCandidate2) {
        return iceCandidate.equals(getLocal()) && iceCandidate2.equals(getRemote());
    }

    public boolean equals(Object obj) {
        IceCandidatePair iceCandidatePair = (IceCandidatePair) Global.tryCast(obj, IceCandidatePair.class);
        if (iceCandidatePair == null) {
            return false;
        }
        return equals(iceCandidatePair.getLocal(), iceCandidatePair.getRemote());
    }

    /* access modifiers changed from: package-private */
    public void fail(Error error) {
        setError(error);
        setState(CandidatePairState.Failed);
    }

    /* access modifiers changed from: package-private */
    public boolean getAwaitingOriginalRelayPermissions() {
        return this.__awaitingOriginalRelayPermissions;
    }

    public Error getError() {
        return this._error;
    }

    public String getFoundation() {
        return calculateFoundation(getLocal(), getRemote());
    }

    public String getId() {
        return this.__id;
    }

    public CandidatePairInfo getInfo(String str) {
        CandidatePairInfo candidatePairInfo = new CandidatePairInfo();
        candidatePairInfo.setId(getId());
        candidatePairInfo.setLocalCandidateId(getLocal().getId());
        candidatePairInfo.setRemoteCandidateId(getRemote().getId());
        candidatePairInfo.setNominated(new NullableBoolean(getNominated()));
        candidatePairInfo.setPriority(new NullableLong(getPriority()));
        candidatePairInfo.setState(CandidateUtility.candidatePairStateToString(getState()));
        CandidatePairReport candidatePairReport = new CandidatePairReport();
        candidatePairReport.setTotalRoundTripTime(Report.processInteger(getTotalRoundTripTime(), 0));
        candidatePairReport.setCurrentRoundTripTime(Report.processInteger(getLastRoundTripTime(), 0));
        candidatePairReport.setBytesSent(Report.processLong(this._bytesSent.getValue(), 0));
        candidatePairReport.setBytesReceived(Report.processLong(this._bytesReceived.getValue(), 0));
        candidatePairReport.setResponsesReceived(Report.processLong(this._responsesReceived.getValue(), 0));
        candidatePairReport.setRequestsSent(Report.processLong(this._requestsSent.getValue(), 0));
        candidatePairInfo.setReport(candidatePairReport);
        return candidatePairInfo;
    }

    /* access modifiers changed from: package-private */
    public int getKeepAliveTimeout() {
        return this.__keepAliveTimeout;
    }

    public int getLastRoundTripTime() {
        return this.__lastRoundTripTime;
    }

    public IceCandidate getLocal() {
        return this.__local;
    }

    public IceRole getLocalRole() {
        return this.__localRole;
    }

    /* access modifiers changed from: package-private */
    public boolean getNominated() {
        return this._nominated;
    }

    /* access modifiers changed from: package-private */
    public IAction1<IceCandidatePair> getOnOriginalRelayPermissionsObtained() {
        return this._onOriginalRelayPermissionsObtained;
    }

    public IAction1<IceCandidatePair> getOnStateChange() {
        return this._onStateChange;
    }

    /* access modifiers changed from: package-private */
    public IAction1<IceSendRequestSuccessArgs> getOnStunResponse() {
        return this._onStunResponse;
    }

    public long getPriority() {
        return this._priority;
    }

    /* access modifiers changed from: package-private */
    public String getRelatedValidPairId() {
        return this._relatedValidPairId;
    }

    /* access modifiers changed from: package-private */
    public int getRelayConnectionTimeout() {
        return this.__connectionTimeout;
    }

    public IceCandidate getRemote() {
        return this.__remote;
    }

    public int getSmoothedRoundTripTime() {
        return this.__smoothedRoundTripTime;
    }

    public CandidatePairState getState() {
        return this.__state;
    }

    public CandidatePairStats getStats(String str) {
        CandidatePairStats candidatePairStats = new CandidatePairStats();
        candidatePairStats.setId(getId());
        candidatePairStats.setTimestamp(DateExtensions.getUtcNow());
        candidatePairStats.setLocalCandidateId(getLocal().getId());
        candidatePairStats.setRemoteCandidateId(getRemote().getId());
        candidatePairStats.setNominated(getNominated());
        candidatePairStats.setPriority(getPriority());
        candidatePairStats.setState(getState());
        candidatePairStats.setTransportId(str);
        candidatePairStats.setTotalRoundTripTime(getTotalRoundTripTime());
        candidatePairStats.setCurrentRoundTripTime(getLastRoundTripTime());
        candidatePairStats.setBytesSent(this._bytesSent.getValue());
        candidatePairStats.setBytesReceived(this._bytesReceived.getValue());
        candidatePairStats.setResponsesReceived(this._responsesReceived.getValue());
        candidatePairStats.setRequestsSent(this._requestsSent.getValue());
        return candidatePairStats;
    }

    /* access modifiers changed from: package-private */
    public ScheduledItem getStopCandidatePair() {
        return this._stopCandidatePair;
    }

    public int getTotalRoundTripTime() {
        return this._totalRoundTripTime;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseCandidateReceived() {
        return this._useCandidateReceived;
    }

    /* access modifiers changed from: package-private */
    public boolean getValid() {
        return this._valid;
    }

    public int hashCode() {
        return super.hashCode();
    }

    IceCandidatePair(Object obj, IceCandidate iceCandidate, IceCandidate iceCandidate2, IceParameters iceParameters, IceParameters iceParameters2, IceRole iceRole, long j, IceComponent iceComponent, IceTransactionManager iceTransactionManager) {
        this(obj, iceCandidate, iceCandidate2, iceParameters, iceParameters2, iceRole, j, iceComponent, iceTransactionManager, true);
    }

    IceCandidatePair(Object obj, IceCandidate iceCandidate, IceCandidate iceCandidate2, IceParameters iceParameters, IceParameters iceParameters2, IceRole iceRole, long j, IceComponent iceComponent, IceTransactionManager iceTransactionManager, boolean z) {
        this.__id = Utility.generateId();
        this._verboseDebugMessages = false;
        this.__keepAliveTimeout = 3000;
        this.__connectionTimeout = 10000;
        this.__state = CandidatePairState.New;
        this.__connectivityCheckScheduledItem = null;
        this.__lastReceivedSomethingTimestamp = (long) ScheduledItem.getUnset();
        this.__smoothedRoundTripTime = -1;
        this._bytesSent = new AtomicLong();
        this._bytesReceived = new AtomicLong();
        this._requestsSent = new AtomicLong();
        this._responsesReceived = new AtomicLong();
        this._consentRequestsReceived = new AtomicLong();
        this._consentRequestsSent = new AtomicLong();
        this._consentResponsesReceived = new AtomicLong();
        this._consentResponsesSent = new AtomicLong();
        this.__lock = obj;
        if (!z) {
            setKeepAliveTimeout(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
        }
        setLocal(iceCandidate);
        setRemote(iceCandidate2);
        this.__localParameters = iceParameters;
        this.__remoteParameters = iceParameters2;
        setLocalRole(iceRole);
        this.__tieBreaker = j;
        this.__component = iceComponent;
        getRemote().setUsername(this.__remoteParameters.getUsernameFragment());
        getRemote().setPassword(this.__remoteParameters.getPassword());
        this.__transactionManager = iceTransactionManager;
        this.__permissionExpiresTimestamp = 0;
        if (Global.equals(iceCandidate.getType(), CandidateType.Relayed)) {
            setAwaitingOriginalRelayPermissions(true);
        }
        this._onKeepAliveResponseReceived = new IActionDelegate1<IceSendRequestSuccessArgs>() {
            public String getId() {
                return "fm.liveswitch.IceCandidatePair.processKeepAliveResponse";
            }

            public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                IceCandidatePair.this.processKeepAliveResponse(iceSendRequestSuccessArgs);
            }
        };
    }

    public void notifyDataReceived(DataBuffer dataBuffer) {
        if (dataBuffer != null) {
            this._bytesReceived.add((long) dataBuffer.getLength());
        }
        receivedSomething();
    }

    static IceSendMessageArgs prepareBindingRequestContext(IceCandidate iceCandidate, IceParameters iceParameters, IceCandidate iceCandidate2, IceParameters iceParameters2, IceComponent iceComponent, IceRole iceRole, long j, String str, IAction1<IceSendRequestSuccessArgs> iAction1, IAction1<IceSendRequestFailureArgs> iAction12) {
        BindingRequest createBindingRequest = createBindingRequest(iceCandidate, iceParameters, iceParameters2, iceComponent, iceRole, j);
        TransportAddress turnServer = Global.equals(iceCandidate.getType(), CandidateType.Relayed) ? ((IceLocalRelayedCandidate) iceCandidate).getTurnServer() : null;
        IceSendMessageArgs iceSendMessageArgs = new IceSendMessageArgs(createBindingRequest, new TransportAddress(iceCandidate2.getIPAddress(), iceCandidate2.getPort()));
        iceSendMessageArgs.setTurnRelay(turnServer);
        String str2 = str;
        iceSendMessageArgs.setCandidatePairId(str);
        iceSendMessageArgs.setOnResponse(iAction1);
        iceSendMessageArgs.setOnFailure(iAction12);
        return iceSendMessageArgs;
    }

    /* access modifiers changed from: private */
    public void processCandidatePriorityChange(long j) {
        assignPriority(getLocalRole());
        IAction1<IceCandidatePair> iAction1 = this._onPriorityChange;
        if (iAction1 != null) {
            iAction1.invoke(this);
        }
    }

    /* access modifiers changed from: private */
    public void processConnectivityCheckExecutionFailure(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
        Error error = iceSendRequestFailureArgs.getError();
        synchronized (this.__lock) {
            fail(new Error(ErrorCode.IceConnectivityCheckFailed, StringExtensions.format("Connectivity check failed from {0} to {1}: {2}", getLocal().getTransportAddress().toString(), getRemote().getTransportAddress().toString(), error != null ? error.getDescription() : "")));
        }
    }

    /* access modifiers changed from: private */
    public void processKeepAliveExecutionFailure(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
        Error error = iceSendRequestFailureArgs.getError();
        String description = error != null ? error.getDescription() : "";
        synchronized (this.__lock) {
            if (!Global.equals(this.__state, CandidatePairState.Failed)) {
                fail(new Error(ErrorCode.IceConnectivityCheckFailed, StringExtensions.format("KeepAlive request failed for {0}. {1}", toString(), description)));
            }
        }
    }

    /* access modifiers changed from: private */
    public void processKeepAliveResponse(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
        receivedSomething();
        this.__transactionManager.remove(Base64.encodeBuffer(iceSendRequestSuccessArgs.getResponse().getTransactionId()), (Object) this);
        int calculateInstantaneousRtt = IceTransactionManager.calculateInstantaneousRtt(true, iceSendRequestSuccessArgs, false, Scheduler.getCurrentTime());
        if (calculateInstantaneousRtt >= 0) {
            setLastRoundTripTime(calculateInstantaneousRtt);
            setSmoothedRoundTripTime(IceTransactionManager.calculateSmoothedRtt(calculateInstantaneousRtt, getSmoothedRoundTripTime()));
        }
    }

    /* access modifiers changed from: private */
    public void processLocalCandidateStateChange(IceLocalCandidateState iceLocalCandidateState, Error error) {
        synchronized (this.__lock) {
            if (Global.equals(iceLocalCandidateState, IceLocalCandidateState.Failed)) {
                fail(error);
            } else if (Global.equals(iceLocalCandidateState, IceLocalCandidateState.Closed) && (!Global.equals(getState(), CandidatePairState.Closed) || !Global.equals(getState(), CandidatePairState.Failed))) {
                fail(new Error(ErrorCode.ConnectionInternalError, "Candidate Pair not in Closed or Failed state when Candidate transitions to Closed state."));
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0113, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0115, code lost:
        fail(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processPermissionExtensionResponse(fm.liveswitch.IceSendRequestSuccessArgs r11) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.__lock
            monitor-enter(r0)
            boolean r1 = r10.__relayPermissionsActive     // Catch:{ all -> 0x0119 }
            r2 = 0
            if (r1 == 0) goto L_0x0112
            fm.liveswitch.stun.Message r1 = r11.getResponse()     // Catch:{ all -> 0x0119 }
            boolean r1 = r1 instanceof fm.liveswitch.stun.turn.CreatePermissionResponse     // Catch:{ all -> 0x0119 }
            if (r1 != 0) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x0119 }
            return
        L_0x0012:
            fm.liveswitch.ScheduledItem r1 = r11.getItem()     // Catch:{ all -> 0x0119 }
            java.lang.Object r3 = r1.getState()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceSendMessageArgs r3 = (fm.liveswitch.IceSendMessageArgs) r3     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceSendMessageArgs r3 = (fm.liveswitch.IceSendMessageArgs) r3     // Catch:{ all -> 0x0119 }
            long r4 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x0119 }
            int r6 = r1.getInvocationLifetimeLimit()     // Catch:{ all -> 0x0119 }
            long r6 = (long) r6     // Catch:{ all -> 0x0119 }
            long r8 = r1.getOriginalInvocationTime()     // Catch:{ all -> 0x0119 }
            long r6 = r6 + r8
            long r6 = r6 - r4
            int r7 = (int) r6     // Catch:{ all -> 0x0119 }
            fm.liveswitch.stun.Message r6 = r11.getResponse()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.TransportAddress r8 = r11.getRemoteAddress()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.Error r3 = fm.liveswitch.IceDatagramSocketManager.validateResponse(r3, r6, r8)     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceTransactionManager r6 = r10.__transactionManager     // Catch:{ all -> 0x0119 }
            fm.liveswitch.ScheduledItem r8 = r10.__relayPermissionsRefreshScheduledItem     // Catch:{ all -> 0x0119 }
            r6.remove((fm.liveswitch.ScheduledItem) r8, (java.lang.Object) r10)     // Catch:{ all -> 0x0119 }
            r6 = 1
            r8 = 0
            int r11 = fm.liveswitch.IceTransactionManager.calculateInstantaneousRtt(r8, r11, r6, r4)     // Catch:{ all -> 0x0119 }
            if (r11 < 0) goto L_0x006f
            fm.liveswitch.IceCandidate r4 = r10.getLocal()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceLocalRelayedCandidate r4 = (fm.liveswitch.IceLocalRelayedCandidate) r4     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceLocalRelayedCandidate r4 = (fm.liveswitch.IceLocalRelayedCandidate) r4     // Catch:{ all -> 0x0119 }
            r4.setLastRelayServerRoundTripTime(r11)     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceCandidate r4 = r10.getLocal()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceLocalRelayedCandidate r4 = (fm.liveswitch.IceLocalRelayedCandidate) r4     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceLocalRelayedCandidate r4 = (fm.liveswitch.IceLocalRelayedCandidate) r4     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceCandidate r5 = r10.getLocal()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceLocalRelayedCandidate r5 = (fm.liveswitch.IceLocalRelayedCandidate) r5     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceLocalRelayedCandidate r5 = (fm.liveswitch.IceLocalRelayedCandidate) r5     // Catch:{ all -> 0x0119 }
            int r5 = r5.getSmoothedRelayServerRoundTripTime()     // Catch:{ all -> 0x0119 }
            int r11 = fm.liveswitch.IceTransactionManager.calculateSmoothedRtt(r11, r5)     // Catch:{ all -> 0x0119 }
            r4.setSmoothedRelayServerRoundTripTime(r11)     // Catch:{ all -> 0x0119 }
        L_0x006f:
            if (r3 != 0) goto L_0x008c
            long r3 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ all -> 0x0119 }
            r5 = 300000(0x493e0, double:1.482197E-318)
            long r3 = r3 + r5
            r10.__permissionExpiresTimestamp = r3     // Catch:{ all -> 0x0119 }
            boolean r11 = r10.getAwaitingOriginalRelayPermissions()     // Catch:{ all -> 0x0119 }
            if (r11 == 0) goto L_0x0084
            r10.setAwaitingOriginalRelayPermissions(r8)     // Catch:{ all -> 0x0119 }
        L_0x0084:
            r11 = 240000(0x3a980, float:3.36312E-40)
            r10.schedulePermissionExtension(r11)     // Catch:{ all -> 0x0119 }
            goto L_0x0112
        L_0x008c:
            fm.liveswitch.ErrorCode r11 = r3.getCode()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunStaleNonce     // Catch:{ all -> 0x0119 }
            boolean r11 = fm.liveswitch.Global.equals(r11, r4)     // Catch:{ all -> 0x0119 }
            if (r11 == 0) goto L_0x00c4
            r11 = r3
            fm.liveswitch.stun.StaleNonceError r11 = (fm.liveswitch.stun.StaleNonceError) r11     // Catch:{ all -> 0x0119 }
            fm.liveswitch.stun.StaleNonceError r11 = (fm.liveswitch.stun.StaleNonceError) r11     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceCandidate r4 = r10.getLocal()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceLocalRelayedCandidate r4 = (fm.liveswitch.IceLocalRelayedCandidate) r4     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceLocalRelayedCandidate r4 = (fm.liveswitch.IceLocalRelayedCandidate) r4     // Catch:{ all -> 0x0119 }
            fm.liveswitch.stun.NonceAttribute r5 = r11.getNonce()     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = r5.getValue()     // Catch:{ all -> 0x0119 }
            r4.setNonce(r5)     // Catch:{ all -> 0x0119 }
            java.lang.Object r4 = r1.getState()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceSendMessageArgs r4 = (fm.liveswitch.IceSendMessageArgs) r4     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceSendMessageArgs r4 = (fm.liveswitch.IceSendMessageArgs) r4     // Catch:{ all -> 0x0119 }
            fm.liveswitch.stun.Message r4 = r4.getMessage()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.stun.NonceAttribute r11 = r11.getNonce()     // Catch:{ all -> 0x0119 }
            r4.setNonce(r11)     // Catch:{ all -> 0x0119 }
            goto L_0x00ea
        L_0x00c4:
            fm.liveswitch.ErrorCode r11 = r3.getCode()     // Catch:{ all -> 0x0119 }
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunServerError     // Catch:{ all -> 0x0119 }
            boolean r11 = fm.liveswitch.Global.equals(r11, r4)     // Catch:{ all -> 0x0119 }
            if (r11 != 0) goto L_0x00ea
            java.lang.String r11 = "CreatePermission request failed for {0}. {1}"
            java.lang.String r2 = r10.toString()     // Catch:{ all -> 0x0119 }
            java.lang.String r4 = r3.getDescription()     // Catch:{ all -> 0x0119 }
            java.lang.String r11 = fm.liveswitch.StringExtensions.format(r11, r2, r4)     // Catch:{ all -> 0x0119 }
            fm.liveswitch.Error r2 = new fm.liveswitch.Error     // Catch:{ all -> 0x0119 }
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.IceCreatePermissionError     // Catch:{ all -> 0x0119 }
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ all -> 0x0119 }
            r5.<init>(r11)     // Catch:{ all -> 0x0119 }
            r2.<init>((fm.liveswitch.ErrorCode) r4, (java.lang.Exception) r5)     // Catch:{ all -> 0x0119 }
        L_0x00ea:
            if (r7 <= 0) goto L_0x00f8
            r1.setInvocationLifetimeLimit(r7)     // Catch:{ all -> 0x0119 }
            r1.setDelay(r8)     // Catch:{ all -> 0x0119 }
            fm.liveswitch.IceTransactionManager r11 = r10.__transactionManager     // Catch:{ all -> 0x0119 }
            r11.addTransaction(r1, r10)     // Catch:{ all -> 0x0119 }
            goto L_0x0112
        L_0x00f8:
            java.lang.String r11 = "CreatePermission request failed for {0} after several attempts. {1}"
            java.lang.String r1 = r10.toString()     // Catch:{ all -> 0x0119 }
            java.lang.String r2 = r3.getDescription()     // Catch:{ all -> 0x0119 }
            java.lang.String r11 = fm.liveswitch.StringExtensions.format(r11, r1, r2)     // Catch:{ all -> 0x0119 }
            fm.liveswitch.Error r2 = new fm.liveswitch.Error     // Catch:{ all -> 0x0119 }
            fm.liveswitch.ErrorCode r1 = fm.liveswitch.ErrorCode.IceCreatePermissionTimeout     // Catch:{ all -> 0x0119 }
            java.lang.Exception r3 = new java.lang.Exception     // Catch:{ all -> 0x0119 }
            r3.<init>(r11)     // Catch:{ all -> 0x0119 }
            r2.<init>((fm.liveswitch.ErrorCode) r1, (java.lang.Exception) r3)     // Catch:{ all -> 0x0119 }
        L_0x0112:
            monitor-exit(r0)     // Catch:{ all -> 0x0119 }
            if (r2 == 0) goto L_0x0118
            r10.fail(r2)
        L_0x0118:
            return
        L_0x0119:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0119 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceCandidatePair.processPermissionExtensionResponse(fm.liveswitch.IceSendRequestSuccessArgs):void");
    }

    /* access modifiers changed from: private */
    public void processPermissionTimedout(ScheduledItem scheduledItem) {
        String format = StringExtensions.format("CreatePermission request failed for {0} after several attempts. Relay server did not respond.", (Object) toString());
        synchronized (this.__lock) {
            if (this.__relayPermissionsActive && __log.getIsWarnEnabled()) {
                __log.error(format);
            }
            fail(new Error(ErrorCode.IceCreatePermissionTimeout, new Exception(format)));
        }
    }

    /* access modifiers changed from: private */
    public void processStunResponse(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
        int calculateInstantaneousRtt = IceTransactionManager.calculateInstantaneousRtt(false, iceSendRequestSuccessArgs, true, Scheduler.getCurrentTime());
        if (calculateInstantaneousRtt >= 0) {
            setLastRoundTripTime(calculateInstantaneousRtt);
            setSmoothedRoundTripTime(IceTransactionManager.calculateSmoothedRtt(calculateInstantaneousRtt, getSmoothedRoundTripTime()));
        }
        IAction1<IceSendRequestSuccessArgs> onStunResponse = getOnStunResponse();
        if (onStunResponse != null) {
            onStunResponse.invoke(iceSendRequestSuccessArgs);
        }
    }

    /* access modifiers changed from: package-private */
    public void receivedSomething() {
        this.__lastReceivedSomethingTimestamp = Scheduler.getCurrentTime();
        if (Global.equals(getState(), CandidatePairState.ConnectivityLost)) {
            synchronized (this.__lock) {
                if (Global.equals(getState(), CandidatePairState.ConnectivityLost)) {
                    __log.debug(StringExtensions.format("Candidate pair {0} regained connectivity.", (Object) toString()));
                    setState(CandidatePairState.Succeeded);
                }
            }
        }
    }

    private void schedulePermissionExtension(int i) {
        IceLocalRelayedCandidate iceLocalRelayedCandidate = (IceLocalRelayedCandidate) getLocal();
        IceSendMessageArgs iceSendMessageArgs = new IceSendMessageArgs(iceLocalRelayedCandidate.generatePermissionRequest(getRemote().getIPAddress()), iceLocalRelayedCandidate.getTurnServer());
        iceSendMessageArgs.setCandidatePairId(getId());
        iceSendMessageArgs.setOnResponse(new IActionDelegate1<IceSendRequestSuccessArgs>() {
            public String getId() {
                return "fm.liveswitch.IceCandidatePair.processPermissionExtensionResponse";
            }

            public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                IceCandidatePair.this.processPermissionExtensionResponse(iceSendRequestSuccessArgs);
            }
        });
        iceSendMessageArgs.setOnFailure(new IActionDelegate1<IceSendRequestFailureArgs>() {
            public String getId() {
                return "fm.liveswitch.IceCandidatePair.createPermissionFailure";
            }

            public void invoke(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
                IceCandidatePair.this.createPermissionFailure(iceSendRequestFailureArgs);
            }
        });
        int unset = Global.equals(getLocal().getProtocol(), ProtocolType.Udp) ? ScheduledItem.getUnset() : 1;
        int unset2 = Global.equals(getLocal().getProtocol(), ProtocolType.Udp) ? 100 : ScheduledItem.getUnset();
        synchronized (this.__lock) {
            if (this.__relayPermissionsActive) {
                ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceCandidatePair.triggerPermissionExtension";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceCandidatePair.this.triggerPermissionExtension(scheduledItem);
                    }
                }, i, unset2, getRelayConnectionTimeout(), unset);
                scheduledItem.setState(iceSendMessageArgs);
                scheduledItem.setIntervalBackoffMultiplier(2.0f);
                scheduledItem.setTimeoutCallback(new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceCandidatePair.processPermissionTimedout";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceCandidatePair.this.processPermissionTimedout(scheduledItem);
                    }
                });
                scheduledItem.setRecordDetailedInvocationTimes(true);
                this.__relayPermissionsRefreshScheduledItem = scheduledItem;
                this.__transactionManager.addTransaction(scheduledItem, this);
            }
        }
    }

    public void send(DataBuffer dataBuffer) {
        IceCandidate local;
        if (Global.equals(getState(), CandidatePairState.Succeeded) && (local = getLocal()) != null) {
            this._bytesSent.add((long) dataBuffer.getLength());
            Error send = local.send(dataBuffer, getRemote());
            if (send == null) {
                return;
            }
            if (Global.equals(send.getCode(), ErrorCode.IPProtocolMismatch) || Global.equals(send.getCode(), ErrorCode.SocketSendError)) {
                fail(send);
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendConnectivityCheck(ScheduledItem scheduledItem) {
        if (((IceSendMessageArgs) scheduledItem.getState()).getCancelled() || Global.equals(getState(), CandidatePairState.Succeeded)) {
            this.__transactionManager.remove(scheduledItem, (Object) this);
            return;
        }
        try {
            getLocal().sendStunAndInsertAttemptMessage(scheduledItem);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void sendPassiveKeepAlive() {
        if (!Global.equals(getState(), CandidatePairState.Failed) && !Global.equals(getState(), CandidatePairState.Closed)) {
            doSendKeepAlive();
        }
    }

    /* access modifiers changed from: package-private */
    public void setAwaitingOriginalRelayPermissions(boolean z) {
        IAction1<IceCandidatePair> onOriginalRelayPermissionsObtained;
        synchronized (this.__lock) {
            if (!Global.equals(Boolean.valueOf(z), Boolean.valueOf(this.__awaitingOriginalRelayPermissions))) {
                this.__awaitingOriginalRelayPermissions = z;
                if (!z && (onOriginalRelayPermissionsObtained = getOnOriginalRelayPermissionsObtained()) != null) {
                    onOriginalRelayPermissionsObtained.invoke(this);
                }
            }
        }
    }

    private void setError(Error error) {
        this._error = error;
    }

    /* access modifiers changed from: package-private */
    public void setKeepAliveTimeout(int i) {
        this.__keepAliveTimeout = i;
    }

    public void setLastRoundTripTime(int i) {
        setTotalRoundTripTime(getTotalRoundTripTime() + i);
        this.__lastRoundTripTime = i;
    }

    /* access modifiers changed from: package-private */
    public void setLocal(IceCandidate iceCandidate) {
        if (!Global.equals(this.__local, iceCandidate)) {
            IceCandidate iceCandidate2 = this.__local;
            if (iceCandidate2 != null) {
                iceCandidate2.removeOnStateChange(new IActionDelegate2<IceLocalCandidateState, Error>() {
                    public String getId() {
                        return "fm.liveswitch.IceCandidatePair.processLocalCandidateStateChange";
                    }

                    public void invoke(IceLocalCandidateState iceLocalCandidateState, Error error) {
                        IceCandidatePair.this.processLocalCandidateStateChange(iceLocalCandidateState, error);
                    }
                });
            }
            this.__local = iceCandidate;
            if (iceCandidate != null) {
                iceCandidate.removeOnStateChange(new IActionDelegate2<IceLocalCandidateState, Error>() {
                    public String getId() {
                        return "fm.liveswitch.IceCandidatePair.processLocalCandidateStateChange";
                    }

                    public void invoke(IceLocalCandidateState iceLocalCandidateState, Error error) {
                        IceCandidatePair.this.processLocalCandidateStateChange(iceLocalCandidateState, error);
                    }
                });
                this.__local.addOnStateChange(new IActionDelegate2<IceLocalCandidateState, Error>() {
                    public String getId() {
                        return "fm.liveswitch.IceCandidatePair.processLocalCandidateStateChange";
                    }

                    public void invoke(IceLocalCandidateState iceLocalCandidateState, Error error) {
                        IceCandidatePair.this.processLocalCandidateStateChange(iceLocalCandidateState, error);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setLocalRole(IceRole iceRole) {
        this.__localRole = iceRole;
        assignPriority(iceRole);
    }

    /* access modifiers changed from: package-private */
    public void setNominated(boolean z) {
        this._nominated = z;
    }

    /* access modifiers changed from: package-private */
    public void setOnOriginalRelayPermissionsObtained(IAction1<IceCandidatePair> iAction1) {
        this._onOriginalRelayPermissionsObtained = iAction1;
    }

    public void setOnStateChange(IAction1<IceCandidatePair> iAction1) {
        this._onStateChange = iAction1;
    }

    /* access modifiers changed from: package-private */
    public void setOnStunResponse(IAction1<IceSendRequestSuccessArgs> iAction1) {
        this._onStunResponse = iAction1;
    }

    public void setPriority(long j) {
        this._priority = j;
    }

    /* access modifiers changed from: package-private */
    public void setRelatedValidPairId(String str) {
        this._relatedValidPairId = str;
    }

    /* access modifiers changed from: package-private */
    public void setRelayConnectionTimeout(int i) {
        if (i > 0) {
            this.__connectionTimeout = i;
        }
    }

    /* access modifiers changed from: package-private */
    public void setRemote(IceCandidate iceCandidate) {
        if (!Global.equals(this.__remote, iceCandidate)) {
            IceCandidate iceCandidate2 = this.__remote;
            if (iceCandidate2 != null) {
                iceCandidate2.removeOnPriorityChange(new IActionDelegate1<Long>() {
                    public String getId() {
                        return "fm.liveswitch.IceCandidatePair.processCandidatePriorityChange";
                    }

                    public void invoke(Long l) {
                        IceCandidatePair.this.processCandidatePriorityChange(l.longValue());
                    }
                });
            }
            this.__remote = iceCandidate;
            if (iceCandidate != null) {
                iceCandidate.removeOnPriorityChange(new IActionDelegate1<Long>() {
                    public String getId() {
                        return "fm.liveswitch.IceCandidatePair.processCandidatePriorityChange";
                    }

                    public void invoke(Long l) {
                        IceCandidatePair.this.processCandidatePriorityChange(l.longValue());
                    }
                });
                this.__remote.addOnPriorityChange(new IActionDelegate1<Long>() {
                    public String getId() {
                        return "fm.liveswitch.IceCandidatePair.processCandidatePriorityChange";
                    }

                    public void invoke(Long l) {
                        IceCandidatePair.this.processCandidatePriorityChange(l.longValue());
                    }
                });
            }
        }
    }

    public void setSmoothedRoundTripTime(int i) {
        this.__smoothedRoundTripTime = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ab  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setState(fm.liveswitch.CandidatePairState r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__lock
            monitor-enter(r0)
            fm.liveswitch.CandidatePairState r1 = r4.__state     // Catch:{ all -> 0x00bc }
            boolean r1 = fm.liveswitch.Global.equals(r1, r5)     // Catch:{ all -> 0x00bc }
            if (r1 != 0) goto L_0x00ba
            fm.liveswitch.CandidatePairState r1 = r4.__state     // Catch:{ all -> 0x00bc }
            fm.liveswitch.CandidatePairState r2 = fm.liveswitch.CandidatePairState.Failed     // Catch:{ all -> 0x00bc }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x00bc }
            if (r1 != 0) goto L_0x00ba
            fm.liveswitch.CandidatePairState r1 = r4.__state     // Catch:{ all -> 0x00bc }
            fm.liveswitch.CandidatePairState r2 = fm.liveswitch.CandidatePairState.Closed     // Catch:{ all -> 0x00bc }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x00bc }
            if (r1 != 0) goto L_0x00ba
            r4.__state = r5     // Catch:{ all -> 0x00bc }
            fm.liveswitch.CandidatePairState r1 = fm.liveswitch.CandidatePairState.Failed     // Catch:{ all -> 0x00bc }
            boolean r5 = fm.liveswitch.Global.equals(r5, r1)     // Catch:{ all -> 0x00bc }
            if (r5 != 0) goto L_0x0048
            fm.liveswitch.CandidatePairState r5 = r4.__state     // Catch:{ all -> 0x00bc }
            fm.liveswitch.CandidatePairState r1 = fm.liveswitch.CandidatePairState.Closed     // Catch:{ all -> 0x00bc }
            boolean r5 = fm.liveswitch.Global.equals(r5, r1)     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x0034
            goto L_0x0048
        L_0x0034:
            fm.liveswitch.CandidatePairState r5 = r4.__state     // Catch:{ all -> 0x00bc }
            fm.liveswitch.CandidatePairState r1 = fm.liveswitch.CandidatePairState.Succeeded     // Catch:{ all -> 0x00bc }
            boolean r5 = fm.liveswitch.Global.equals(r5, r1)     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x0076
            fm.liveswitch.IceTransactionManager r5 = r4.__transactionManager     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x0076
            fm.liveswitch.ScheduledItem r1 = r4.__connectivityCheckScheduledItem     // Catch:{ all -> 0x00bc }
            r5.remove((fm.liveswitch.ScheduledItem) r1, (java.lang.Object) r4)     // Catch:{ all -> 0x00bc }
            goto L_0x0076
        L_0x0048:
            r4.cancelAssociatedTransactions()     // Catch:{ all -> 0x00bc }
            fm.liveswitch.IceTransactionManager r5 = r4.__transactionManager     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x0052
            r5.remove(r4)     // Catch:{ all -> 0x00bc }
        L_0x0052:
            fm.liveswitch.IceCandidate r5 = r4.getLocal()     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x0064
            fm.liveswitch.IceCandidate r5 = r4.getLocal()     // Catch:{ all -> 0x00bc }
            fm.liveswitch.IceCandidatePair$17 r1 = new fm.liveswitch.IceCandidatePair$17     // Catch:{ all -> 0x00bc }
            r1.<init>()     // Catch:{ all -> 0x00bc }
            r5.removeOnStateChange(r1)     // Catch:{ all -> 0x00bc }
        L_0x0064:
            fm.liveswitch.IceCandidate r5 = r4.getRemote()     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x0076
            fm.liveswitch.IceCandidate r5 = r4.getRemote()     // Catch:{ all -> 0x00bc }
            fm.liveswitch.IceCandidatePair$18 r1 = new fm.liveswitch.IceCandidatePair$18     // Catch:{ all -> 0x00bc }
            r1.<init>()     // Catch:{ all -> 0x00bc }
            r5.removeOnPriorityChange(r1)     // Catch:{ all -> 0x00bc }
        L_0x0076:
            fm.liveswitch.IAction1 r5 = r4.getOnStateChange()     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x007f
            r5.invoke(r4)     // Catch:{ all -> 0x00bc }
        L_0x007f:
            fm.liveswitch.CandidatePairState r5 = r4.__state     // Catch:{ all -> 0x00bc }
            fm.liveswitch.CandidatePairState r1 = fm.liveswitch.CandidatePairState.Failed     // Catch:{ all -> 0x00bc }
            boolean r5 = fm.liveswitch.Global.equals(r5, r1)     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x00ba
            fm.liveswitch.ILog r5 = __log     // Catch:{ all -> 0x00bc }
            boolean r5 = r5.getIsDebugEnabled()     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x00ba
            fm.liveswitch.Error r5 = r4.getError()     // Catch:{ all -> 0x00bc }
            if (r5 == 0) goto L_0x00ab
            fm.liveswitch.ILog r1 = __log     // Catch:{ all -> 0x00bc }
            java.lang.String r2 = "Candidate Pair {0} failed: {1}"
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x00bc }
            java.lang.String r5 = r5.getMessage()     // Catch:{ all -> 0x00bc }
            java.lang.String r5 = fm.liveswitch.StringExtensions.format(r2, r3, r5)     // Catch:{ all -> 0x00bc }
            r1.verbose(r5)     // Catch:{ all -> 0x00bc }
            goto L_0x00ba
        L_0x00ab:
            fm.liveswitch.ILog r5 = __log     // Catch:{ all -> 0x00bc }
            java.lang.String r1 = "Candidate Pair {0} failed."
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x00bc }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x00bc }
            r5.verbose(r1)     // Catch:{ all -> 0x00bc }
        L_0x00ba:
            monitor-exit(r0)     // Catch:{ all -> 0x00bc }
            return
        L_0x00bc:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00bc }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceCandidatePair.setState(fm.liveswitch.CandidatePairState):void");
    }

    /* access modifiers changed from: package-private */
    public void setStopCandidatePair(ScheduledItem scheduledItem) {
        this._stopCandidatePair = scheduledItem;
    }

    /* access modifiers changed from: package-private */
    public void setTotalRoundTripTime(int i) {
        this._totalRoundTripTime = i;
    }

    /* access modifiers changed from: package-private */
    public void setUseCandidateReceived(boolean z) {
        this._useCandidateReceived = z;
    }

    /* access modifiers changed from: package-private */
    public void setValid(boolean z) {
        this._valid = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean startPermissionRequests() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.__lock
            monitor-enter(r0)
            fm.liveswitch.IceCandidate r1 = r3.getLocal()     // Catch:{ all -> 0x0022 }
            fm.liveswitch.CandidateType r1 = r1.getType()     // Catch:{ all -> 0x0022 }
            fm.liveswitch.CandidateType r2 = fm.liveswitch.CandidateType.Relayed     // Catch:{ all -> 0x0022 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x0022 }
            r2 = 0
            if (r1 == 0) goto L_0x0020
            fm.liveswitch.ScheduledItem r1 = r3.__relayPermissionsRefreshScheduledItem     // Catch:{ all -> 0x0022 }
            if (r1 != 0) goto L_0x0020
            r1 = 1
            r3.__relayPermissionsActive = r1     // Catch:{ all -> 0x0022 }
            r3.schedulePermissionExtension(r2)     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return r1
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return r2
        L_0x0022:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceCandidatePair.startPermissionRequests():boolean");
    }

    public void stop() {
        synchronized (this.__lock) {
            setStopCandidatePair((ScheduledItem) null);
            if (!Global.equals(getState(), CandidatePairState.Failed) && !Global.equals(getState(), CandidatePairState.Closed)) {
                setState(CandidatePairState.Closed);
            }
        }
    }

    static String toString(IceCandidate iceCandidate, IceCandidate iceCandidate2) {
        if (iceCandidate == null || iceCandidate2 == null) {
            __log.error(StringExtensions.format("Invalid candidate pair passed ToString. {0}{1}", iceCandidate == null ? "Local candidate was null. " : "", iceCandidate2 == null ? "Remote candidate was null." : ""));
            return "";
        }
        String str = "TCP";
        String str2 = Global.equals(iceCandidate.getProtocol(), ProtocolType.Udp) ? "UDP" : Global.equals(iceCandidate.getProtocol(), ProtocolType.Tcp) ? str : "Unknown";
        if (Global.equals(iceCandidate2.getProtocol(), ProtocolType.Udp)) {
            str = "UDP";
        } else if (!Global.equals(iceCandidate2.getProtocol(), ProtocolType.Tcp)) {
            str = "Unknown";
        }
        return StringExtensions.format("{0} {2} ({4}) | {1} {3} ({5})", new Object[]{iceCandidate.toString(), iceCandidate2.toString(), iceCandidate.getType().toString(), iceCandidate2.getType().toString(), str2, str});
    }

    public String toString() {
        return toString(getLocal(), getRemote());
    }

    /* access modifiers changed from: private */
    public void triggerPermissionExtension(ScheduledItem scheduledItem) {
        synchronized (this.__lock) {
            if (this.__relayPermissionsActive) {
                getLocal().sendStunAndInsertAttemptMessage(scheduledItem);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void verifyConnectivity() {
        if (this.__lastReceivedSomethingTimestamp == ((long) ScheduledItem.getUnset())) {
            this.__lastReceivedSomethingTimestamp = Scheduler.getCurrentTime();
        }
        if (Scheduler.getCurrentTime() > this.__lastReceivedSomethingTimestamp + ((long) getKeepAliveTimeout()) && Global.equals(getState(), CandidatePairState.Succeeded)) {
            synchronized (this.__lock) {
                if (Scheduler.getCurrentTime() > this.__lastReceivedSomethingTimestamp + ((long) getKeepAliveTimeout()) && Global.equals(getState(), CandidatePairState.Succeeded)) {
                    __log.debug(StringExtensions.format("Candidate pair {0} lost connectivity.", (Object) toString()));
                    setState(CandidatePairState.ConnectivityLost);
                }
            }
        }
    }
}
