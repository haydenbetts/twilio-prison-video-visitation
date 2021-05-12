package fm.liveswitch;

import fm.liveswitch.stun.BindingResponse;
import fm.liveswitch.stun.ErrorCodeAttribute;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.ice.ControlledAttribute;
import fm.liveswitch.stun.ice.ControllingAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class IceTransport extends Transport {
    private static ILog __log = Log.getLogger(IceTransport.class);
    private IceCandidatePair __activeCandidatePair;
    private boolean __activeIceKeepAliveEnabled = true;
    private HashMap<String, IceCandidatePair> __candidatePairCache = new HashMap<>();
    private ScheduledItem __candidatePairCheckSI;
    private IceCheckList __checkList;
    private IceComponent __component;
    private String __connectionId;
    private IceGatherer __gatherer;
    private IcePolicy __icePolicy = IcePolicy.Negotiated;
    private volatile boolean __keepAliveActive;
    private ScheduledItem __keepAliveScheduledItem;
    private long __lastStateChangeSystemTimestamp;
    private ArrayList<IceCandidate> __localCandidates = new ArrayList<>();
    private Object __lock;
    /* access modifiers changed from: private */
    public List<IAction2<IceTransport, IceCandidatePair>> __onActiveCandidatePairChange = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<DataBuffer>> __onReceive = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<IceTransport>> __onStateChange = new ArrayList();
    private ArrayList<IceCandidate> __remoteCandidates = new ArrayList<>();
    private IceParameters __remoteParameters;
    private IceRole __role = IceRole.Controlling;
    private IceTransport __rtcpTransport = null;
    private Scheduler __scheduler;
    private ArrayList<DataBuffer> __sendQueue = new ArrayList<>();
    private IceCandidate __singleRemoteCandidateForIceBypass;
    private IceTransportState __state = IceTransportState.New;
    private long __tieBreaker;
    private IceTransactionManager __transactionManager;
    private ScheduledItem __transitionToFailedStateSI;
    private boolean __useIce = true;
    private IceValidList __validList;
    private AtomicLong _bytesReceived = new AtomicLong();
    private AtomicLong _bytesSent = new AtomicLong();
    private boolean _closingShouldNotTriggerGlobalNonGracefulShutdown;
    private Error _error;
    private String _id;
    private IAction2<IceTransport, IceCandidatePair> _onActiveCandidatePairChange = null;
    private IAction1<DataBuffer> _onReceive = null;
    private IAction1<IceTransport> _onStateChange = null;
    private IceTransportOptions _options;

    private void addCandidatePair(IceCandidate iceCandidate, IceCandidate iceCandidate2) {
        boolean isPrivate = TransportAddress.isPrivate(iceCandidate.getIPAddress());
        boolean isPrivate2 = TransportAddress.isPrivate(iceCandidate2.getIPAddress());
        if (!Global.equals(iceCandidate.getType(), CandidateType.Relayed) || isPrivate || !isPrivate2) {
            AddressType addressType = LocalNetwork.getAddressType(iceCandidate.getIPAddress());
            if (Global.equals(addressType, LocalNetwork.getAddressType(iceCandidate2.getIPAddress())) && Global.equals(iceCandidate.getProtocol(), iceCandidate2.getProtocol())) {
                if (!Global.equals(addressType, AddressType.IPv6) || Global.equals(Boolean.valueOf(TransportAddress.isPrivate(iceCandidate.getIPAddress())), Boolean.valueOf(TransportAddress.isPrivate(iceCandidate2.getIPAddress())))) {
                    IceParameters iceParameters = this.__remoteParameters;
                    if (iceParameters != null) {
                        iceCandidate2.setUsername(iceParameters.getUsernameFragment());
                        iceCandidate2.setPassword(this.__remoteParameters.getPassword());
                    }
                    IceCandidatePair iceCandidatePair = new IceCandidatePair(this.__lock, iceCandidate, iceCandidate2, getGatherer().getLocalParameters(), getRemoteParameters(), getRole(), this.__tieBreaker, getComponent(), this.__transactionManager, getUseIce());
                    iceCandidatePair.setOnStunResponse(new IActionDelegate1<IceSendRequestSuccessArgs>() {
                        public String getId() {
                            return "fm.liveswitch.IceTransport.processStunResponse";
                        }

                        public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                            IceTransport.this.processStunResponse(iceSendRequestSuccessArgs);
                        }
                    });
                    iceCandidatePair.setOnStateChange(new IActionDelegate1<IceCandidatePair>() {
                        public String getId() {
                            return "fm.liveswitch.IceTransport.processCandidatePairStateChange";
                        }

                        public void invoke(IceCandidatePair iceCandidatePair) {
                            IceTransport.this.processCandidatePairStateChange(iceCandidatePair);
                        }
                    });
                    iceCandidatePair._onPriorityChange = new IActionDelegate1<IceCandidatePair>() {
                        public String getId() {
                            return "fm.liveswitch.IceTransport.processCandidatePairPriorityChange";
                        }

                        public void invoke(IceCandidatePair iceCandidatePair) {
                            IceTransport.this.processCandidatePairPriorityChange(iceCandidatePair);
                        }
                    };
                    boolean z = false;
                    if (iceCandidatePair.getAwaitingOriginalRelayPermissions()) {
                        iceCandidatePair.setOnOriginalRelayPermissionsObtained(new IActionDelegate1<IceCandidatePair>() {
                            public String getId() {
                                return "fm.liveswitch.IceTransport.processGotOriginalRelayPermission";
                            }

                            public void invoke(IceCandidatePair iceCandidatePair) {
                                IceTransport.this.processGotOriginalRelayPermission(iceCandidatePair);
                            }
                        });
                        this.__checkList.addToAwaitingOriginalRelayPermissionsList(iceCandidatePair);
                    } else {
                        z = this.__checkList.addToOrdinaryCheckList(iceCandidatePair);
                    }
                    if (z) {
                        addCandidatePairToCache(iceCandidatePair);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void addCandidatePairToCache(IceCandidatePair iceCandidatePair) {
        synchronized (this.__lock) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__candidatePairCache), iceCandidatePair.getId(), iceCandidatePair);
        }
    }

    private void addLocalCandidate(IceCandidate iceCandidate) {
        synchronized (this.__lock) {
            if (Global.equals(iceCandidate.getType(), CandidateType.ServerReflexive)) {
                iceCandidate = ((IceLocalReflexiveCandidate) iceCandidate).getLocalHostBaseCandidate();
            }
            if (!this.__localCandidates.contains(iceCandidate)) {
                this.__localCandidates.add(iceCandidate);
                if (!getIsClosed()) {
                    Iterator<IceCandidate> it = this.__remoteCandidates.iterator();
                    while (it.hasNext()) {
                        addCandidatePair(iceCandidate, it.next());
                    }
                }
            }
        }
    }

    public void addOnActiveCandidatePairChange(IAction2<IceTransport, IceCandidatePair> iAction2) {
        if (iAction2 != null) {
            if (this._onActiveCandidatePairChange == null) {
                this._onActiveCandidatePairChange = new IAction2<IceTransport, IceCandidatePair>() {
                    public void invoke(IceTransport iceTransport, IceCandidatePair iceCandidatePair) {
                        Iterator it = new ArrayList(IceTransport.this.__onActiveCandidatePairChange).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(iceTransport, iceCandidatePair);
                        }
                    }
                };
            }
            this.__onActiveCandidatePairChange.add(iAction2);
        }
    }

    public void addOnReceive(IAction1<DataBuffer> iAction1) {
        if (iAction1 != null) {
            if (this._onReceive == null) {
                this._onReceive = new IAction1<DataBuffer>() {
                    public void invoke(DataBuffer dataBuffer) {
                        Iterator it = new ArrayList(IceTransport.this.__onReceive).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(dataBuffer);
                        }
                    }
                };
            }
            this.__onReceive.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<IceTransport> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<IceTransport>() {
                    public void invoke(IceTransport iceTransport) {
                        Iterator it = new ArrayList(IceTransport.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(iceTransport);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    public void addRemoteCandidate(IceCandidate iceCandidate) {
        synchronized (this.__lock) {
            if (!getUseIce()) {
                setSingleRemoteCandidateForIceBypass(iceCandidate);
            } else if (!getIsClosed()) {
                IceCandidate iceCandidate2 = null;
                Iterator<IceCandidate> it = this.__remoteCandidates.iterator();
                while (it.hasNext()) {
                    IceCandidate next = it.next();
                    if (next.equals(iceCandidate)) {
                        iceCandidate2 = next;
                    }
                }
                if (iceCandidate2 != null && Global.equals(iceCandidate2.getType(), CandidateType.PeerReflexive)) {
                    if (iceCandidate2.getPriority() != iceCandidate.getPriority()) {
                        Log.debug(StringExtensions.format("Remote peer signalled a candidate {0} but this peer is already known via ICE peer reflection. Will update this candidate's properties and change its priority from {1} to {2}. This may change active candidate pair for ICE Transport {3}.", new Object[]{iceCandidate.toString(), LongExtensions.toString(Long.valueOf(iceCandidate2.getPriority())), LongExtensions.toString(Long.valueOf(iceCandidate.getPriority())), getId()}));
                    }
                    iceCandidate2.updateProperties(iceCandidate);
                }
                if (iceCandidate2 == null) {
                    this.__remoteCandidates.add(iceCandidate);
                    Iterator<IceCandidate> it2 = this.__localCandidates.iterator();
                    while (it2.hasNext()) {
                        addCandidatePair(it2.next(), iceCandidate);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkCandidatePair(ScheduledItem scheduledItem) {
        synchronized (this.__lock) {
            IceCandidatePair waitingCandidatePair = this.__checkList.getWaitingCandidatePair();
            if (waitingCandidatePair == null) {
                this.__candidatePairCheckSI.setSuspended(true);
            } else {
                waitingCandidatePair.checkForConnectivity();
            }
        }
    }

    private BindingResponse createIceRoleConflictResponse(DataBuffer dataBuffer) {
        BindingResponse bindingResponse = new BindingResponse(dataBuffer, false);
        bindingResponse.setErrorCode(new ErrorCodeAttribute(487, StringExtensions.format("Ice Role Conflict. Both peers assume the {0} role, but this entity won the tie-breaker.", (Object) Global.equals(getRole(), IceRole.Controlled) ? "controlled" : "controlling")));
        if (Global.equals(getRole(), IceRole.Controlled)) {
            bindingResponse.setIceControlled(new ControlledAttribute(this.__tieBreaker));
            return bindingResponse;
        }
        bindingResponse.setIceControlling(new ControllingAttribute(this.__tieBreaker));
        return bindingResponse;
    }

    private ScheduledItem createKeepAliveScheduledItem() {
        int keepAliveInterval = getOptions().getKeepAliveInterval();
        return new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
            public String getId() {
                return "fm.liveswitch.IceTransport.dispatchActiveKeepAlive";
            }

            public void invoke(ScheduledItem scheduledItem) {
                IceTransport.this.dispatchActiveKeepAlive(scheduledItem);
            }
        }, keepAliveInterval, keepAliveInterval, ScheduledItem.getUnset(), ScheduledItem.getUnset());
    }

    public IceTransport createRtcpTransport() {
        synchronized (this.__lock) {
            if (this.__rtcpTransport == null && !Global.equals(getState(), IceTransportState.Closed)) {
                if (!Global.equals(this.__component, IceComponent.Rtcp)) {
                    IceTransport iceTransport = new IceTransport(this.__lock, this.__connectionId, this.__scheduler, getOptions());
                    iceTransport.__component = IceComponent.Rtcp;
                    iceTransport.setIcePolicy(getIcePolicy());
                    iceTransport.setUseIce(getUseIce());
                    iceTransport.setActiveIceKeepAliveEnabled(getActiveIceKeepAliveEnabled());
                    this.__rtcpTransport = iceTransport;
                    return iceTransport;
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void dispatchActiveKeepAlive(ScheduledItem scheduledItem) {
        synchronized (this.__lock) {
            if (this.__keepAliveActive) {
                for (IceCandidatePair dispatchActiveKeepAlive : this.__validList.getCandidatePairs()) {
                    dispatchActiveKeepAlive.dispatchActiveKeepAlive();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:118:0x03b2  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x03b5  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x03b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean doProcessStunResponse(fm.liveswitch.IceSendRequestSuccessArgs r25, fm.liveswitch.IceGatherer r26, fm.liveswitch.IceParameters r27, fm.liveswitch.IceTransportState r28, boolean r29, java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidatePair> r30, fm.liveswitch.IceCheckList r31, fm.liveswitch.IceValidList r32, long r33, fm.liveswitch.IceComponent r35, int r36, fm.liveswitch.IFunction2<java.lang.String, java.lang.Integer, fm.liveswitch.IceCandidate> r37, fm.liveswitch.IAction1<fm.liveswitch.IceSendRequestSuccessArgs> r38, fm.liveswitch.IAction1<fm.liveswitch.IceCandidatePair> r39, fm.liveswitch.IAction1<fm.liveswitch.IceCandidatePair> r40, fm.liveswitch.IAction1<fm.liveswitch.IceCandidatePair> r41, fm.liveswitch.IAction0 r42, fm.liveswitch.IAction1<fm.liveswitch.IceRole> r43, fm.liveswitch.IceRole r44, fm.liveswitch.IceTransactionManager r45, java.lang.String r46, java.lang.Object r47) {
        /*
            r0 = r26
            r1 = r28
            r2 = r30
            r3 = r31
            r4 = r32
            r5 = r37
            r6 = r41
            r7 = r43
            r14 = r44
            r15 = r45
            r8 = r46
            if (r25 == 0) goto L_0x045d
            fm.liveswitch.IceTransportState r9 = fm.liveswitch.IceTransportState.Closed
            boolean r9 = fm.liveswitch.Global.equals(r1, r9)
            r10 = 0
            if (r9 != 0) goto L_0x045b
            fm.liveswitch.IceTransportState r9 = fm.liveswitch.IceTransportState.Failed
            boolean r1 = fm.liveswitch.Global.equals(r1, r9)
            if (r1 != 0) goto L_0x045b
            if (r0 == 0) goto L_0x044e
            if (r27 == 0) goto L_0x0441
            if (r2 == 0) goto L_0x0434
            if (r3 == 0) goto L_0x0427
            if (r4 == 0) goto L_0x041a
            if (r15 == 0) goto L_0x040d
            if (r8 == 0) goto L_0x0400
            if (r47 == 0) goto L_0x03f3
            fm.liveswitch.ScheduledItem r1 = r25.getItem()
            java.lang.Object r1 = r1.getState()
            fm.liveswitch.IceSendMessageArgs r1 = (fm.liveswitch.IceSendMessageArgs) r1
            fm.liveswitch.IceSendMessageArgs r1 = (fm.liveswitch.IceSendMessageArgs) r1
            java.lang.String r9 = r1.getCandidatePairId()
            fm.liveswitch.Holder r11 = new fm.liveswitch.Holder
            r12 = 0
            r11.<init>(r12)
            boolean r2 = fm.liveswitch.HashMapExtensions.tryGetValue(r2, r9, r11)
            java.lang.Object r11 = r11.getValue()
            r13 = r11
            fm.liveswitch.IceCandidatePair r13 = (fm.liveswitch.IceCandidatePair) r13
            if (r2 == 0) goto L_0x03e7
            fm.liveswitch.TransportAddress r2 = r25.getRemoteAddress()
            java.lang.String r2 = r2.getIPAddress()
            fm.liveswitch.IceCandidate r9 = r13.getRemote()
            java.lang.String r9 = r9.getIPAddress()
            boolean r2 = fm.liveswitch.Global.equals(r2, r9)
            if (r2 == 0) goto L_0x03c5
            fm.liveswitch.TransportAddress r2 = r25.getRemoteAddress()
            int r2 = r2.getPort()
            fm.liveswitch.IceCandidate r9 = r13.getRemote()
            int r9 = r9.getPort()
            if (r2 == r9) goto L_0x0086
            goto L_0x03c5
        L_0x0086:
            if (r29 == 0) goto L_0x00e6
            fm.liveswitch.stun.Message r2 = r25.getResponse()
            fm.liveswitch.stun.MessageIntegrityAttribute r2 = r2.getMessageIntegrity()
            if (r2 != 0) goto L_0x00b3
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r1 = fm.liveswitch.ErrorCode.IceConnectivityCheckFailed
            fm.liveswitch.IceCandidate r2 = r13.getLocal()
            java.lang.String r2 = r2.toString()
            fm.liveswitch.IceCandidate r3 = r13.getRemote()
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "Check discarded from {0} to {1}. Message integrity not found."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r4, r2, r3)
            r0.<init>((fm.liveswitch.ErrorCode) r1, (java.lang.String) r2)
            r13.fail(r0)
            return r10
        L_0x00b3:
            fm.liveswitch.IceCandidate r9 = r13.getRemote()
            java.lang.String r9 = r9.getPassword()
            byte[] r9 = fm.liveswitch.stun.Utility.createShortTermKey(r9)
            boolean r2 = r2.isValid(r9)
            if (r2 != 0) goto L_0x00e6
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r1 = fm.liveswitch.ErrorCode.IceConnectivityCheckFailed
            fm.liveswitch.IceCandidate r2 = r13.getLocal()
            java.lang.String r2 = r2.toString()
            fm.liveswitch.IceCandidate r3 = r13.getRemote()
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "Check discarded from {0} to {1}. Message integrity check failed."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r4, r2, r3)
            r0.<init>((fm.liveswitch.ErrorCode) r1, (java.lang.String) r2)
            r13.fail(r0)
            return r10
        L_0x00e6:
            fm.liveswitch.stun.Message r2 = r25.getResponse()
            fm.liveswitch.TransportAddress r9 = r25.getRemoteAddress()
            fm.liveswitch.Error r2 = fm.liveswitch.IceDatagramSocketManager.validateResponse(r1, r2, r9)
            r11 = 2
            r12 = 4
            r9 = 1
            if (r2 == 0) goto L_0x01c8
            fm.liveswitch.ErrorCode r0 = r2.getCode()
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunUnauthorized
            if (r0 == r4) goto L_0x01a0
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunStaleCredentials
            if (r0 == r4) goto L_0x01a0
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunInvalidMessageIntegrity
            if (r0 == r4) goto L_0x01a0
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunMissingUsername
            if (r0 == r4) goto L_0x01a0
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunServerError
            if (r0 != r4) goto L_0x0111
            goto L_0x01a0
        L_0x0111:
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunIceRoleConflict
            if (r0 != r4) goto L_0x016c
            fm.liveswitch.stun.Message r0 = r1.getMessage()
            fm.liveswitch.stun.ice.ControlledAttribute r0 = r0.getIceControlled()
            if (r0 != 0) goto L_0x0122
            fm.liveswitch.IceRole r0 = fm.liveswitch.IceRole.Controlled
            goto L_0x0124
        L_0x0122:
            fm.liveswitch.IceRole r0 = fm.liveswitch.IceRole.Controlling
        L_0x0124:
            fm.liveswitch.ILog r1 = __log
            java.lang.Object[] r4 = new java.lang.Object[r12]
            fm.liveswitch.IceCandidate r5 = r13.getLocal()
            fm.liveswitch.TransportAddress r5 = r5.getTransportAddress()
            java.lang.String r5 = r5.toString()
            r4[r10] = r5
            fm.liveswitch.IceCandidate r5 = r13.getRemote()
            fm.liveswitch.TransportAddress r5 = r5.getTransportAddress()
            java.lang.String r5 = r5.toString()
            r4[r9] = r5
            java.lang.String r2 = r2.getDescription()
            r4[r11] = r2
            fm.liveswitch.IceRole r2 = fm.liveswitch.IceRole.Controlled
            boolean r2 = fm.liveswitch.Global.equals(r0, r2)
            if (r2 == 0) goto L_0x0155
            java.lang.String r2 = "controlled"
            goto L_0x0157
        L_0x0155:
            java.lang.String r2 = "controlling"
        L_0x0157:
            r5 = 3
            r4[r5] = r2
            java.lang.String r2 = "The remote peer reports ICE Role Conflict. Changing local ICE Role to {3}. Will retry check from {0} to {1}. {2}"
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object[]) r4)
            r1.debug(r2)
            if (r7 == 0) goto L_0x0168
            r7.invoke(r0)
        L_0x0168:
            r3.addToTriggeredCheckList(r13, r9)
            return r10
        L_0x016c:
            fm.liveswitch.ScheduledItem r0 = r25.getItem()
            r15.remove((fm.liveswitch.ScheduledItem) r0, (java.lang.Object) r13)
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r1 = fm.liveswitch.ErrorCode.IceConnectivityCheckFailed
            fm.liveswitch.IceCandidate r3 = r13.getLocal()
            fm.liveswitch.TransportAddress r3 = r3.getTransportAddress()
            java.lang.String r3 = r3.toString()
            fm.liveswitch.IceCandidate r4 = r13.getRemote()
            fm.liveswitch.TransportAddress r4 = r4.getTransportAddress()
            java.lang.String r4 = r4.toString()
            java.lang.String r2 = r2.getDescription()
            java.lang.String r5 = "Check failed from {0} to {1}: {2}"
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r5, r3, r4, r2)
            r0.<init>((fm.liveswitch.ErrorCode) r1, (java.lang.String) r2)
            r13.fail(r0)
            return r10
        L_0x01a0:
            fm.liveswitch.ILog r0 = __log
            fm.liveswitch.IceCandidate r1 = r13.getLocal()
            fm.liveswitch.TransportAddress r1 = r1.getTransportAddress()
            java.lang.String r1 = r1.toString()
            fm.liveswitch.IceCandidate r3 = r13.getRemote()
            fm.liveswitch.TransportAddress r3 = r3.getTransportAddress()
            java.lang.String r3 = r3.toString()
            java.lang.String r2 = r2.getDescription()
            java.lang.String r4 = "Retrying check from {0} to {1}. {2}"
            java.lang.String r1 = fm.liveswitch.StringExtensions.format(r4, r1, r3, r2)
            r0.debug(r1)
            return r10
        L_0x01c8:
            fm.liveswitch.ScheduledItem r2 = r25.getItem()
            r15.remove((fm.liveswitch.ScheduledItem) r2, (java.lang.Object) r13)
            fm.liveswitch.stun.Message r2 = r25.getResponse()
            fm.liveswitch.stun.XorMappedAddressAttribute r2 = r2.getXorMappedAddress()
            if (r2 != 0) goto L_0x01fa
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r1 = fm.liveswitch.ErrorCode.IceConnectivityCheckFailed
            fm.liveswitch.IceCandidate r2 = r13.getLocal()
            java.lang.String r2 = r2.toString()
            fm.liveswitch.IceCandidate r3 = r13.getRemote()
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "Check discarded from {0} to {1}. XOR-mapped address not found."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r4, r2, r3)
            r0.<init>((fm.liveswitch.ErrorCode) r1, (java.lang.String) r2)
            r13.fail(r0)
            return r10
        L_0x01fa:
            if (r5 == 0) goto L_0x020f
            java.lang.String r7 = r2.getIPAddress()
            int r16 = r2.getPort()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r16)
            java.lang.Object r5 = r5.invoke(r7, r11)
            fm.liveswitch.IceCandidate r5 = (fm.liveswitch.IceCandidate) r5
            goto L_0x0210
        L_0x020f:
            r5 = 0
        L_0x0210:
            if (r5 != 0) goto L_0x02ea
            java.lang.String r5 = r2.getIPAddress()
            boolean r5 = fm.liveswitch.TransportAddress.isPrivate(r5)
            if (r5 == 0) goto L_0x025f
            boolean r5 = r25.getRelayed()
            if (r5 == 0) goto L_0x025f
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r1 = fm.liveswitch.ErrorCode.IceConnectivityCheckFailed
            java.lang.Object[] r3 = new java.lang.Object[r12]
            fm.liveswitch.IceCandidate r4 = r13.getLocal()
            java.lang.String r4 = r4.toString()
            r3[r10] = r4
            fm.liveswitch.IceCandidate r4 = r13.getRemote()
            java.lang.String r4 = r4.toString()
            r3[r9] = r4
            java.lang.String r4 = r2.getIPAddress()
            r5 = 2
            r3[r5] = r4
            int r2 = r2.getPort()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            r4 = 3
            r3[r4] = r2
            java.lang.String r2 = "Check discarded from {0} to {1}. Potential local peer-reflexive candidate had a private XOR-mapped IP address {2} and port {3}. (Is the TURN server on the same network as the remote client?)"
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object[]) r3)
            r0.<init>((fm.liveswitch.ErrorCode) r1, (java.lang.String) r2)
            r13.fail(r0)
            return r10
        L_0x025f:
            fm.liveswitch.IceCandidate r5 = r13.getLocal()
            fm.liveswitch.CandidateType r7 = r5.getType()
            fm.liveswitch.CandidateType r11 = fm.liveswitch.CandidateType.PeerReflexive
            boolean r7 = fm.liveswitch.Global.equals(r7, r11)
            if (r7 == 0) goto L_0x0277
            fm.liveswitch.IceLocalReflexiveCandidate r5 = (fm.liveswitch.IceLocalReflexiveCandidate) r5
            fm.liveswitch.IceLocalReflexiveCandidate r5 = (fm.liveswitch.IceLocalReflexiveCandidate) r5
            fm.liveswitch.IceCandidate r5 = r5.getLocalHostBaseCandidate()
        L_0x0277:
            fm.liveswitch.stun.Message r7 = r1.getMessage()
            fm.liveswitch.stun.ice.PriorityAttribute r7 = r7.getPriority()
            long r11 = r7.getPriority()
            fm.liveswitch.CandidateType r7 = fm.liveswitch.CandidateType.PeerReflexive
            java.lang.String r9 = r2.getIPAddress()
            fm.liveswitch.ProtocolType r10 = fm.liveswitch.ProtocolType.Udp
            r14 = 0
            java.lang.String r18 = fm.liveswitch.IceCandidate.generateLocalCandidateFoundation(r7, r9, r14, r10)
            fm.liveswitch.IceLocalReflexiveCandidate r7 = new fm.liveswitch.IceLocalReflexiveCandidate
            fm.liveswitch.ProtocolType r19 = fm.liveswitch.ProtocolType.Udp
            java.lang.String r20 = r2.getIPAddress()
            int r21 = r2.getPort()
            fm.liveswitch.CandidateType r22 = fm.liveswitch.CandidateType.PeerReflexive
            java.lang.String r23 = r5.getIPAddress()
            int r24 = r5.getPort()
            r16 = r7
            r17 = r47
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24)
            long r9 = r5.getAdapterSpeed()
            r7.setAdapterSpeed(r9)
            r7.setLocalHostBaseCandidate(r5)
            r7.setPriority(r11)
            java.lang.String r2 = r5.getUsername()
            r7.setUsername(r2)
            java.lang.String r2 = r5.getPassword()
            r7.setPassword(r2)
            fm.liveswitch.IceCandidate r2 = r0.registerLocalPeerReflexiveCandidate(r7)
            fm.liveswitch.ILog r5 = __log
            boolean r5 = r5.getIsDebugEnabled()
            if (r5 == 0) goto L_0x0304
            boolean r5 = fm.liveswitch.Global.equals(r2, r7)
            if (r5 == 0) goto L_0x0304
            fm.liveswitch.ILog r5 = __log
            java.lang.String r7 = r7.toString()
            java.lang.String r9 = "Learned new local peer reflexive candidate {0} for IceTransport {1}."
            java.lang.String r7 = fm.liveswitch.StringExtensions.format(r9, r7, r8)
            r5.debug(r7)
            goto L_0x0304
        L_0x02ea:
            fm.liveswitch.CandidateType r2 = r5.getType()
            fm.liveswitch.CandidateType r7 = fm.liveswitch.CandidateType.ServerReflexive
            boolean r2 = fm.liveswitch.Global.equals(r2, r7)
            if (r2 == 0) goto L_0x0306
            fm.liveswitch.IceLocalReflexiveCandidate r5 = (fm.liveswitch.IceLocalReflexiveCandidate) r5
            fm.liveswitch.IceLocalReflexiveCandidate r5 = (fm.liveswitch.IceLocalReflexiveCandidate) r5
            fm.liveswitch.IceCandidate r2 = r5.getLocalHostBaseCandidate()
            if (r2 != 0) goto L_0x0304
            fm.liveswitch.IceCandidate r2 = r13.getLocal()
        L_0x0304:
            r9 = r2
            goto L_0x0307
        L_0x0306:
            r9 = r5
        L_0x0307:
            fm.liveswitch.IceCandidate r10 = r13.getRemote()
            if (r9 == 0) goto L_0x03ac
            if (r10 != 0) goto L_0x0311
            goto L_0x03ac
        L_0x0311:
            fm.liveswitch.IceCandidatePair r2 = r3.findMatchingCandidatePair(r9, r10)
            if (r2 != 0) goto L_0x035b
            fm.liveswitch.IceCandidatePair r2 = new fm.liveswitch.IceCandidatePair
            fm.liveswitch.IceParameters r11 = r26.getLocalParameters()
            r7 = r2
            r8 = r47
            r0 = 1
            r12 = r27
            r3 = r13
            r13 = r44
            r5 = r44
            r14 = r33
            r16 = r35
            r17 = r45
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r16, r17)
            r7 = r38
            r2.setOnStunResponse(r7)
            r7 = r39
            r2.setOnStateChange(r7)
            r7 = r40
            r2._onPriorityChange = r7
            if (r6 == 0) goto L_0x0344
            r6.invoke(r2)
        L_0x0344:
            r2.assignPriority(r5)
            fm.liveswitch.IceCandidate r6 = r2.getLocal()
            fm.liveswitch.CandidateType r6 = r6.getType()
            fm.liveswitch.CandidateType r7 = fm.liveswitch.CandidateType.Relayed
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x035f
            r2.startPermissionRequests()
            goto L_0x035f
        L_0x035b:
            r5 = r44
            r3 = r13
            r0 = 1
        L_0x035f:
            r2.setValid(r0)
            java.lang.String r6 = r2.getRelatedValidPairId()
            if (r6 != 0) goto L_0x036f
            java.lang.String r6 = r2.getId()
            r2.setRelatedValidPairId(r6)
        L_0x036f:
            fm.liveswitch.CandidatePairState r6 = fm.liveswitch.CandidatePairState.Succeeded
            r2.setState(r6)
            r4.addCandidatePair(r2)
            java.lang.String r4 = r2.getId()
            r3.setRelatedValidPairId(r4)
            fm.liveswitch.CandidatePairState r4 = fm.liveswitch.CandidatePairState.Succeeded
            r3.setState(r4)
            fm.liveswitch.IceRole r4 = fm.liveswitch.IceRole.Controlling
            boolean r4 = fm.liveswitch.Global.equals(r5, r4)
            if (r4 == 0) goto L_0x0395
            fm.liveswitch.stun.Message r1 = r1.getMessage()
            fm.liveswitch.stun.ice.UseCandidateAttribute r1 = r1.getUseCandidate()
            if (r1 != 0) goto L_0x03a3
        L_0x0395:
            fm.liveswitch.IceRole r1 = fm.liveswitch.IceRole.Controlled
            boolean r1 = fm.liveswitch.Global.equals(r5, r1)
            if (r1 == 0) goto L_0x03ab
            boolean r1 = r3.getUseCandidateReceived()
            if (r1 == 0) goto L_0x03ab
        L_0x03a3:
            r2.setNominated(r0)
            if (r42 == 0) goto L_0x03ab
            r42.invoke()
        L_0x03ab:
            return r0
        L_0x03ac:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r1 = ""
            if (r9 != 0) goto L_0x03b5
            java.lang.String r2 = "Local candidate was null. "
            goto L_0x03b6
        L_0x03b5:
            r2 = r1
        L_0x03b6:
            if (r10 != 0) goto L_0x03ba
            java.lang.String r1 = "Remote candidate was null."
        L_0x03ba:
            java.lang.String r3 = "Invalid candidates for candidate pair check. {0}{1}"
            java.lang.String r1 = fm.liveswitch.StringExtensions.format(r3, r2, r1)
            r0.error(r1)
        L_0x03c3:
            r0 = 0
            return r0
        L_0x03c5:
            r3 = r13
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r1 = fm.liveswitch.ErrorCode.IceConnectivityCheckFailed
            fm.liveswitch.TransportAddress r2 = r25.getRemoteAddress()
            java.lang.String r2 = r2.toString()
            fm.liveswitch.IceCandidate r4 = r3.getRemote()
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "Encountered error while processing STUN Binding Response. Remote address {0} did not match expected value {1}."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r5, r2, r4)
            r0.<init>((fm.liveswitch.ErrorCode) r1, (java.lang.String) r2)
            r3.fail(r0)
            goto L_0x03c3
        L_0x03e7:
            fm.liveswitch.ILog r0 = __log
            java.lang.String r1 = "Received STUN Response to a connectivity check on a candidate pair with ID {0}, but candidate pair with this ID does not exist in local cache. Discarding."
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r9)
            r0.error(r1)
            goto L_0x045b
        L_0x03f3:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but lock object was not created in IceTransport."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x0400:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but Ice TransactionId was not set for IceTransport."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x040d:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but Ice TransactionManager was not created in IceTransport."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x041a:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but ValidList was not created in IceTransport."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x0427:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but CheckList was not created in IceTransport."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x0434:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but CandidatePairCache was not created in IceTransport."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x0441:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but RemoteParameters is not assigned to the IceTransport."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x044e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but Gatherer is not assigned to the IceTransport."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x045b:
            r0 = 0
            return r0
        L_0x045d:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "ICE: Received STUN response but missing SendRequestSuccessArgs."
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransport.doProcessStunResponse(fm.liveswitch.IceSendRequestSuccessArgs, fm.liveswitch.IceGatherer, fm.liveswitch.IceParameters, fm.liveswitch.IceTransportState, boolean, java.util.HashMap, fm.liveswitch.IceCheckList, fm.liveswitch.IceValidList, long, fm.liveswitch.IceComponent, int, fm.liveswitch.IFunction2, fm.liveswitch.IAction1, fm.liveswitch.IAction1, fm.liveswitch.IAction1, fm.liveswitch.IAction1, fm.liveswitch.IAction0, fm.liveswitch.IAction1, fm.liveswitch.IceRole, fm.liveswitch.IceTransactionManager, java.lang.String, java.lang.Object):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0081, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean doSetActiveCandidatePair(fm.liveswitch.IceCandidatePair r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.__lock
            monitor-enter(r0)
            r1 = 1
            r2 = 0
            r3 = 0
            if (r7 != 0) goto L_0x0015
            fm.liveswitch.IceCandidatePair r4 = r6.__activeCandidatePair     // Catch:{ all -> 0x00d6 }
            if (r4 == 0) goto L_0x00c3
            r6.__activeCandidatePair = r2     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.Disconnected     // Catch:{ all -> 0x00d6 }
            r6.setState(r2)     // Catch:{ all -> 0x00d6 }
            goto L_0x00c3
        L_0x0015:
            fm.liveswitch.ScheduledItem r4 = r7.getStopCandidatePair()     // Catch:{ all -> 0x00d6 }
            if (r4 == 0) goto L_0x0023
            fm.liveswitch.Scheduler r5 = r6.__scheduler     // Catch:{ all -> 0x00d6 }
            r5.remove(r4)     // Catch:{ all -> 0x00d6 }
            r7.setStopCandidatePair(r2)     // Catch:{ all -> 0x00d6 }
        L_0x0023:
            boolean r2 = r6.getUseIce()     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x0082
            fm.liveswitch.CandidatePairState r2 = r7.getState()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.CandidatePairState r4 = fm.liveswitch.CandidatePairState.Closed     // Catch:{ all -> 0x00d6 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r4)     // Catch:{ all -> 0x00d6 }
            if (r2 != 0) goto L_0x0080
            fm.liveswitch.CandidatePairState r2 = r7.getState()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.CandidatePairState r4 = fm.liveswitch.CandidatePairState.Failed     // Catch:{ all -> 0x00d6 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r4)     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x0042
            goto L_0x0080
        L_0x0042:
            fm.liveswitch.IceCandidate r2 = r7.getLocal()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.CandidateType r2 = r2.getType()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.CandidateType r4 = fm.liveswitch.CandidateType.Relayed     // Catch:{ all -> 0x00d6 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r4)     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x0082
            fm.liveswitch.IceCandidate r2 = r7.getLocal()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.IceLocalRelayedCandidate r2 = (fm.liveswitch.IceLocalRelayedCandidate) r2     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.IceLocalRelayedCandidate r2 = (fm.liveswitch.IceLocalRelayedCandidate) r2     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.IceLocalRelayedCandidateState r4 = r2.getRelayState()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.IceLocalRelayedCandidateState r5 = fm.liveswitch.IceLocalRelayedCandidateState.Closed     // Catch:{ all -> 0x00d6 }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ all -> 0x00d6 }
            if (r4 != 0) goto L_0x007e
            fm.liveswitch.IceLocalRelayedCandidateState r4 = r2.getRelayState()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.IceLocalRelayedCandidateState r5 = fm.liveswitch.IceLocalRelayedCandidateState.Closing     // Catch:{ all -> 0x00d6 }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ all -> 0x00d6 }
            if (r4 != 0) goto L_0x007e
            fm.liveswitch.IceLocalRelayedCandidateState r2 = r2.getRelayState()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.IceLocalRelayedCandidateState r4 = fm.liveswitch.IceLocalRelayedCandidateState.Failed     // Catch:{ all -> 0x00d6 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r4)     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x0082
        L_0x007e:
            monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
            return r3
        L_0x0080:
            monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
            return r3
        L_0x0082:
            fm.liveswitch.IceCandidatePair r2 = r6.__activeCandidatePair     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x008c
            boolean r2 = r2.equals(r7)     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x0090
        L_0x008c:
            fm.liveswitch.IceCandidatePair r2 = r6.__activeCandidatePair     // Catch:{ all -> 0x00d6 }
            if (r2 != 0) goto L_0x00c3
        L_0x0090:
            r6.__activeCandidatePair = r7     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = "Setting new active candidate pair for ICE transport {1}: {0}"
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x00d6 }
            java.lang.String r5 = r6.getId()     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r3, r4, r5)     // Catch:{ all -> 0x00d6 }
            r2.debug(r3)     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.CandidatePairState r2 = r7.getState()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.CandidatePairState r3 = fm.liveswitch.CandidatePairState.Succeeded     // Catch:{ all -> 0x00d6 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x00c2
            fm.liveswitch.IceTransportState r2 = r6.getState()     // Catch:{ all -> 0x00d6 }
            fm.liveswitch.IceTransportState r3 = fm.liveswitch.IceTransportState.Disconnected     // Catch:{ all -> 0x00d6 }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x00c2
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.Connected     // Catch:{ all -> 0x00d6 }
            r6.setState(r2)     // Catch:{ all -> 0x00d6 }
        L_0x00c2:
            r3 = 1
        L_0x00c3:
            if (r3 == 0) goto L_0x00d1
            fm.liveswitch.IAction2<fm.liveswitch.IceTransport, fm.liveswitch.IceCandidatePair> r2 = r6._onActiveCandidatePairChange     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x00cc
            r2.invoke(r6, r7)     // Catch:{ all -> 0x00d6 }
        L_0x00cc:
            fm.liveswitch.IceCandidatePair r7 = r6.__activeCandidatePair     // Catch:{ all -> 0x00d6 }
            r6.processCandidatePairStateChange(r7)     // Catch:{ all -> 0x00d6 }
        L_0x00d1:
            r6.stopNonActivePairs()     // Catch:{ all -> 0x00d6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
            return r1
        L_0x00d6:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransport.doSetActiveCandidatePair(fm.liveswitch.IceCandidatePair):boolean");
    }

    /* access modifiers changed from: private */
    public IceCandidate findMatchingLocalCandidate(String str, int i) {
        IceGatherer iceGatherer = this.__gatherer;
        if (iceGatherer != null) {
            return iceGatherer.findMatchingLocalCandidate(str, i);
        }
        return null;
    }

    public IceCandidate findMatchingRemoteCandidate(String str, int i) {
        synchronized (this.__lock) {
            Iterator<IceCandidate> it = this.__remoteCandidates.iterator();
            while (it.hasNext()) {
                IceCandidate next = it.next();
                if (Global.equals(next.getIPAddress(), str) && next.getPort() == i) {
                    return next;
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public IceCandidatePair getActiveCandidatePair() {
        return this.__activeCandidatePair;
    }

    /* access modifiers changed from: package-private */
    public boolean getActiveIceKeepAliveEnabled() {
        return this.__activeIceKeepAliveEnabled;
    }

    public boolean getClosingShouldNotTriggerGlobalNonGracefulShutdown() {
        return this._closingShouldNotTriggerGlobalNonGracefulShutdown;
    }

    public IceComponent getComponent() {
        return this.__component;
    }

    public Error getError() {
        return this._error;
    }

    public IceGatherer getGatherer() {
        return this.__gatherer;
    }

    /* access modifiers changed from: package-private */
    public IcePolicy getIcePolicy() {
        return this.__icePolicy;
    }

    public String getId() {
        return this._id;
    }

    public boolean getIsClosed() {
        return Global.equals(getState(), IceTransportState.Closed);
    }

    public int getLastRelayServerRoundTripTime() {
        IceCandidatePair activeCandidatePair = getActiveCandidatePair();
        if (activeCandidatePair == null || !Global.equals(activeCandidatePair.getLocal().getType(), CandidateType.Relayed)) {
            return -1;
        }
        return ((IceLocalRelayedCandidate) activeCandidatePair.getLocal()).getLastRelayServerRoundTripTime();
    }

    public int getLastRoundTripTime() {
        IceCandidatePair activeCandidatePair = getActiveCandidatePair();
        if (activeCandidatePair != null) {
            return activeCandidatePair.getLastRoundTripTime();
        }
        return -1;
    }

    public IceCandidate[] getLocalCandidates() {
        IceCandidate[] iceCandidateArr;
        synchronized (this.__lock) {
            iceCandidateArr = (IceCandidate[]) this.__localCandidates.toArray(new IceCandidate[0]);
        }
        return iceCandidateArr;
    }

    /* access modifiers changed from: package-private */
    public IceTransportOptions getOptions() {
        return this._options;
    }

    public IceCandidate[] getRemoteCandidates() {
        IceCandidate[] iceCandidateArr;
        synchronized (this.__lock) {
            iceCandidateArr = (IceCandidate[]) this.__remoteCandidates.toArray(new IceCandidate[0]);
        }
        return iceCandidateArr;
    }

    public IceParameters getRemoteParameters() {
        return this.__remoteParameters;
    }

    public IceRole getRole() {
        IceRole iceRole;
        synchronized (this.__lock) {
            iceRole = this.__role;
        }
        return iceRole;
    }

    public int getRoundTripTime() {
        return getSmoothedRoundTripTime();
    }

    public IceCandidate getSingleRemoteCandidateForIceBypass() {
        return this.__singleRemoteCandidateForIceBypass;
    }

    public int getSmoothedRelayServerRoundTripTime() {
        IceCandidatePair activeCandidatePair = getActiveCandidatePair();
        if (activeCandidatePair == null || !Global.equals(activeCandidatePair.getLocal().getType(), CandidateType.Relayed)) {
            return -1;
        }
        return ((IceLocalRelayedCandidate) activeCandidatePair.getLocal()).getSmoothedRelayServerRoundTripTime();
    }

    public int getSmoothedRoundTripTime() {
        IceCandidatePair activeCandidatePair = getActiveCandidatePair();
        if (activeCandidatePair != null) {
            return activeCandidatePair.getSmoothedRoundTripTime();
        }
        return -1;
    }

    public IceTransportState getState() {
        return this.__state;
    }

    /* access modifiers changed from: package-private */
    public boolean getUseIce() {
        return this.__useIce;
    }

    public IceTransport(Object obj, String str, Scheduler scheduler, IceTransportOptions iceTransportOptions) {
        setId(Utility.generateId());
        ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
            public String getId() {
                return "fm.liveswitch.IceTransport.checkCandidatePair";
            }

            public void invoke(ScheduledItem scheduledItem) {
                IceTransport.this.checkCandidatePair(scheduledItem);
            }
        }, 0, 20, ScheduledItem.getUnset(), ScheduledItem.getUnset());
        this.__candidatePairCheckSI = scheduledItem;
        scheduledItem.setSuspended(getUseIce());
        this.__lock = obj;
        this.__connectionId = str;
        this.__checkList = new IceCheckList(obj);
        this.__validList = new IceValidList(this.__lock);
        this.__scheduler = scheduler;
        this.__component = IceComponent.Rtp;
        this.__gatherer = null;
        this.__remoteParameters = null;
        setRole(IceRole.Controlling);
        this.__activeCandidatePair = null;
        setOptions(iceTransportOptions);
    }

    /* access modifiers changed from: private */
    public void processCandidatePairPriorityChange(IceCandidatePair iceCandidatePair) {
        synchronized (this.__lock) {
            IceCandidatePair findMatchingCandidatePair = this.__validList.findMatchingCandidatePair(iceCandidatePair.getLocal(), iceCandidatePair.getRemote());
            if (findMatchingCandidatePair != null) {
                Log.debug(StringExtensions.format("The priority of the candidate pair {0} changed. This may lead to setting a new active candidate pair.", (Object) iceCandidatePair.toString()));
                this.__validList.reorder(findMatchingCandidatePair);
                updateActivePair();
            } else {
                Log.debug(StringExtensions.format("The priority of the candidate pair {0} changed, but connectivity checks on this candidate pair have not yet completed. No priority reordering is necessary.", (Object) iceCandidatePair.toString()));
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0083, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a3, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b8, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ce, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processCandidatePairStateChange(fm.liveswitch.IceCandidatePair r4) {
        /*
            r3 = this;
            fm.liveswitch.IceTransportState r0 = r3.getState()
            fm.liveswitch.IceTransportState r1 = fm.liveswitch.IceTransportState.New
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 != 0) goto L_0x00fb
            fm.liveswitch.IceTransportState r0 = r3.getState()
            fm.liveswitch.IceTransportState r1 = fm.liveswitch.IceTransportState.Closed
            boolean r0 = fm.liveswitch.Global.equals(r0, r1)
            if (r0 != 0) goto L_0x00fb
            java.lang.Object r0 = r3.__lock
            monitor-enter(r0)
            fm.liveswitch.IceTransportState r1 = r3.getState()     // Catch:{ all -> 0x00f8 }
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.New     // Catch:{ all -> 0x00f8 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x00f8 }
            if (r1 != 0) goto L_0x00f6
            fm.liveswitch.IceTransportState r1 = r3.getState()     // Catch:{ all -> 0x00f8 }
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.Closed     // Catch:{ all -> 0x00f8 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x00f8 }
            if (r1 != 0) goto L_0x00f6
            if (r4 == 0) goto L_0x00f6
            fm.liveswitch.CandidatePairState r1 = r4.getState()     // Catch:{ all -> 0x00f8 }
            fm.liveswitch.CandidatePairState r2 = fm.liveswitch.CandidatePairState.Succeeded     // Catch:{ all -> 0x00f8 }
            if (r1 != r2) goto L_0x006b
            boolean r4 = r3.getUseIce()     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x0046
            r3.updateActivePair()     // Catch:{ all -> 0x00f8 }
        L_0x0046:
            fm.liveswitch.IceTransportState r4 = r3.getState()     // Catch:{ all -> 0x00f8 }
            fm.liveswitch.IceTransportState r1 = fm.liveswitch.IceTransportState.Checking     // Catch:{ all -> 0x00f8 }
            boolean r4 = fm.liveswitch.Global.equals(r4, r1)     // Catch:{ all -> 0x00f8 }
            if (r4 != 0) goto L_0x005e
            fm.liveswitch.IceTransportState r4 = r3.getState()     // Catch:{ all -> 0x00f8 }
            fm.liveswitch.IceTransportState r1 = fm.liveswitch.IceTransportState.Disconnected     // Catch:{ all -> 0x00f8 }
            boolean r4 = fm.liveswitch.Global.equals(r4, r1)     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x0069
        L_0x005e:
            fm.liveswitch.IceCandidatePair r4 = r3.getActiveCandidatePair()     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x0069
            fm.liveswitch.IceTransportState r4 = fm.liveswitch.IceTransportState.Connected     // Catch:{ all -> 0x00f8 }
            r3.setState(r4)     // Catch:{ all -> 0x00f8 }
        L_0x0069:
            monitor-exit(r0)     // Catch:{ all -> 0x00f8 }
            return
        L_0x006b:
            fm.liveswitch.CandidatePairState r2 = fm.liveswitch.CandidatePairState.InProgress     // Catch:{ all -> 0x00f8 }
            if (r1 != r2) goto L_0x0084
            fm.liveswitch.IceCheckList r4 = r3.__checkList     // Catch:{ all -> 0x00f8 }
            boolean r4 = r4.getAtLeastOneCandidatePairSucceeded()     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x007d
            fm.liveswitch.IceTransportState r4 = fm.liveswitch.IceTransportState.Connected     // Catch:{ all -> 0x00f8 }
            r3.setState(r4)     // Catch:{ all -> 0x00f8 }
            goto L_0x0082
        L_0x007d:
            fm.liveswitch.IceTransportState r4 = fm.liveswitch.IceTransportState.Checking     // Catch:{ all -> 0x00f8 }
            r3.setState(r4)     // Catch:{ all -> 0x00f8 }
        L_0x0082:
            monitor-exit(r0)     // Catch:{ all -> 0x00f8 }
            return
        L_0x0084:
            fm.liveswitch.CandidatePairState r2 = fm.liveswitch.CandidatePairState.Failed     // Catch:{ all -> 0x00f8 }
            if (r1 != r2) goto L_0x00a4
            fm.liveswitch.IceCandidatePair r1 = r3.getActiveCandidatePair()     // Catch:{ all -> 0x00f8 }
            boolean r4 = fm.liveswitch.Global.equals(r1, r4)     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x0095
            r3.updateActivePair()     // Catch:{ all -> 0x00f8 }
        L_0x0095:
            fm.liveswitch.IceCheckList r4 = r3.__checkList     // Catch:{ all -> 0x00f8 }
            boolean r4 = r4.getConnectivityChecksCompletedAndNoneSucceeded()     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x00a2
            fm.liveswitch.IceTransportState r4 = fm.liveswitch.IceTransportState.Disconnected     // Catch:{ all -> 0x00f8 }
            r3.setState(r4)     // Catch:{ all -> 0x00f8 }
        L_0x00a2:
            monitor-exit(r0)     // Catch:{ all -> 0x00f8 }
            return
        L_0x00a4:
            fm.liveswitch.CandidatePairState r2 = fm.liveswitch.CandidatePairState.ConnectivityLost     // Catch:{ all -> 0x00f8 }
            if (r1 != r2) goto L_0x00b9
            fm.liveswitch.IceCandidatePair r1 = r3.getActiveCandidatePair()     // Catch:{ all -> 0x00f8 }
            if (r1 == 0) goto L_0x00b4
            boolean r4 = r4.equals(r1)     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x00b7
        L_0x00b4:
            r3.updateActivePair()     // Catch:{ all -> 0x00f8 }
        L_0x00b7:
            monitor-exit(r0)     // Catch:{ all -> 0x00f8 }
            return
        L_0x00b9:
            fm.liveswitch.CandidatePairState r4 = fm.liveswitch.CandidatePairState.Closed     // Catch:{ all -> 0x00f8 }
            if (r1 != r4) goto L_0x00cf
            r3.updateActivePair()     // Catch:{ all -> 0x00f8 }
            fm.liveswitch.IceCheckList r4 = r3.__checkList     // Catch:{ all -> 0x00f8 }
            boolean r4 = r4.getConnectivityChecksCompletedAndNoneSucceeded()     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x00cd
            fm.liveswitch.IceTransportState r4 = fm.liveswitch.IceTransportState.Failed     // Catch:{ all -> 0x00f8 }
            r3.setState(r4)     // Catch:{ all -> 0x00f8 }
        L_0x00cd:
            monitor-exit(r0)     // Catch:{ all -> 0x00f8 }
            return
        L_0x00cf:
            fm.liveswitch.CandidatePairState r4 = fm.liveswitch.CandidatePairState.Waiting     // Catch:{ all -> 0x00f8 }
            boolean r4 = fm.liveswitch.Global.equals(r1, r4)     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x00f6
            boolean r4 = r3.getUseIce()     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x00f6
            fm.liveswitch.ScheduledItem r4 = r3.__candidatePairCheckSI     // Catch:{ all -> 0x00f8 }
            r1 = 0
            r4.setSuspended(r1)     // Catch:{ all -> 0x00f8 }
            fm.liveswitch.IceCheckList r4 = r3.__checkList     // Catch:{ all -> 0x00f8 }
            boolean r4 = r4.getAtLeastOneCandidatePairSucceeded()     // Catch:{ all -> 0x00f8 }
            if (r4 == 0) goto L_0x00f1
            fm.liveswitch.IceTransportState r4 = fm.liveswitch.IceTransportState.Connected     // Catch:{ all -> 0x00f8 }
            r3.setState(r4)     // Catch:{ all -> 0x00f8 }
            goto L_0x00f6
        L_0x00f1:
            fm.liveswitch.IceTransportState r4 = fm.liveswitch.IceTransportState.Checking     // Catch:{ all -> 0x00f8 }
            r3.setState(r4)     // Catch:{ all -> 0x00f8 }
        L_0x00f6:
            monitor-exit(r0)     // Catch:{ all -> 0x00f8 }
            goto L_0x00fb
        L_0x00f8:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00f8 }
            throw r4
        L_0x00fb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransport.processCandidatePairStateChange(fm.liveswitch.IceCandidatePair):void");
    }

    /* access modifiers changed from: private */
    public void processGotOriginalRelayPermission(IceCandidatePair iceCandidatePair) {
        synchronized (this.__lock) {
            if (this.__checkList.processGotOriginalRelayPermission(iceCandidatePair)) {
                addCandidatePairToCache(iceCandidatePair);
            }
        }
    }

    /* access modifiers changed from: private */
    public void processGotOriginalRelayPermissionsNonIceCP(IceCandidatePair iceCandidatePair) {
        doSetActiveCandidatePair(iceCandidatePair);
    }

    /* access modifiers changed from: private */
    public void processIncomingApplicationData(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
        boolean z;
        IAction1<DataBuffer> iAction1;
        IceCandidatePair activeCandidatePair;
        synchronized (this.__lock) {
            if (!Global.equals(getState(), IceTransportState.Connected)) {
                if (!Global.equals(getState(), IceTransportState.Disconnected)) {
                    z = false;
                }
            }
            IceCandidate findMatchingRemoteCandidate = findMatchingRemoteCandidate(transportAddress.getIPAddress(), transportAddress.getPort());
            if (findMatchingRemoteCandidate != null) {
                IceCandidatePair findMatchingCandidatePair = this.__validList.findMatchingCandidatePair(iceCandidate, findMatchingRemoteCandidate);
                if (findMatchingCandidatePair == null) {
                    findMatchingCandidatePair = this.__checkList.findMatchingCandidatePair(iceCandidate, findMatchingRemoteCandidate);
                }
                if (findMatchingCandidatePair != null) {
                    findMatchingCandidatePair.notifyDataReceived(dataBuffer);
                }
            } else if (!getUseIce() && (activeCandidatePair = getActiveCandidatePair()) != null) {
                activeCandidatePair.notifyDataReceived(dataBuffer);
            }
            this._bytesReceived.add((long) dataBuffer.getLength());
            z = true;
        }
        if (z && (iAction1 = this._onReceive) != null) {
            iAction1.invoke(dataBuffer);
        }
    }

    /* access modifiers changed from: private */
    public void processLocalCandidate(IceGatherer iceGatherer, IceCandidate iceCandidate) {
        addLocalCandidate(iceCandidate);
    }

    /* access modifiers changed from: private */
    public void processNominatedPair() {
        IceCandidatePair nominatedPairWithHighestPriority = this.__validList.getNominatedPairWithHighestPriority();
        synchronized (this.__lock) {
            if (!getIsClosed() || nominatedPairWithHighestPriority == null) {
                setActiveCandidatePair(nominatedPairWithHighestPriority);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: fm.liveswitch.IceCandidate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v46, resolved type: fm.liveswitch.IceLocalReflexiveCandidate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v12, resolved type: fm.liveswitch.IceLocalReflexiveCandidate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v13, resolved type: fm.liveswitch.IceLocalReflexiveCandidate} */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processStunRequest(fm.liveswitch.stun.Message r26, fm.liveswitch.IceCandidate r27, fm.liveswitch.TransportAddress r28) {
        /*
            r25 = this;
            r1 = r25
            r0 = r27
            boolean r2 = r25.getIsClosed()
            if (r2 != 0) goto L_0x02af
            fm.liveswitch.IceGatherer r2 = r1.__gatherer
            if (r2 == 0) goto L_0x02af
            fm.liveswitch.IceParameters r6 = r2.getLocalParameters()
            fm.liveswitch.IceParameters r7 = r25.getRemoteParameters()
            if (r7 == 0) goto L_0x02af
            fm.liveswitch.stun.UsernameAttribute r2 = r26.getUsername()
            if (r2 != 0) goto L_0x0037
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsDebugEnabled()
            if (r0 == 0) goto L_0x02af
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Ignoring request from {0}. Username required."
            java.lang.String r3 = r28.toString()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r3)
            r0.debug(r2)
            goto L_0x02af
        L_0x0037:
            java.lang.String r3 = "{0}:{1}"
            java.lang.String r4 = r6.getUsernameFragment()
            java.lang.String r5 = r7.getUsernameFragment()
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r3, r4, r5)
            java.lang.String r4 = r2.getValue()
            boolean r4 = fm.liveswitch.Global.equals(r4, r3)
            if (r4 != 0) goto L_0x006c
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsDebugEnabled()
            if (r0 == 0) goto L_0x02af
            fm.liveswitch.ILog r0 = __log
            java.lang.String r4 = "Ignoring request from {0}. Username '{1}' does not match expected value '{2}'."
            java.lang.String r5 = r28.toString()
            java.lang.String r2 = r2.getValue()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r4, r5, r2, r3)
            r0.debug(r2)
            goto L_0x02af
        L_0x006c:
            fm.liveswitch.stun.MessageIntegrityAttribute r2 = r26.getMessageIntegrity()
            if (r2 == 0) goto L_0x0296
            java.lang.String r3 = r6.getPassword()
            byte[] r3 = fm.liveswitch.stun.Utility.createShortTermKey(r3)
            boolean r2 = r2.isValid(r3)
            if (r2 != 0) goto L_0x0082
            goto L_0x0296
        L_0x0082:
            fm.liveswitch.stun.TransactionTransmitCounterAttribute r2 = r26.getTransactionTransmitCounter()
            fm.liveswitch.stun.BindingResponse r3 = r25.verifyIceRole(r26)
            r13 = 0
            r14 = 1
            if (r3 != 0) goto L_0x0099
            fm.liveswitch.stun.BindingResponse r3 = new fm.liveswitch.stun.BindingResponse
            fm.liveswitch.DataBuffer r4 = r26.getTransactionId()
            r3.<init>(r4, r14)
            r4 = 0
            goto L_0x009a
        L_0x0099:
            r4 = 1
        L_0x009a:
            java.lang.String r5 = r28.getIPAddress()
            fm.liveswitch.stun.XorMappedAddressAttribute r8 = new fm.liveswitch.stun.XorMappedAddressAttribute
            int r9 = r28.getPort()
            fm.liveswitch.DataBuffer r10 = r26.getTransactionId()
            r8.<init>(r5, r9, r10)
            r3.setXorMappedAddress(r8)
            fm.liveswitch.stun.MessageIntegrityAttribute r5 = new fm.liveswitch.stun.MessageIntegrityAttribute
            java.lang.String r8 = r6.getPassword()
            byte[] r8 = fm.liveswitch.stun.Utility.createShortTermKey(r8)
            r5.<init>(r8)
            r3.setMessageIntegrity(r5)
            fm.liveswitch.stun.FingerprintAttribute r5 = new fm.liveswitch.stun.FingerprintAttribute
            r5.<init>()
            r3.setFingerprint(r5)
            if (r2 == 0) goto L_0x00cb
            r3.setTransactionTransmitCounter(r2)
        L_0x00cb:
            fm.liveswitch.CandidateType r2 = r27.getType()
            fm.liveswitch.CandidateType r5 = fm.liveswitch.CandidateType.Relayed
            boolean r2 = fm.liveswitch.Global.equals(r2, r5)
            r15 = 0
            if (r2 == 0) goto L_0x00e2
            r2 = r0
            fm.liveswitch.IceLocalRelayedCandidate r2 = (fm.liveswitch.IceLocalRelayedCandidate) r2
            fm.liveswitch.IceLocalRelayedCandidate r2 = (fm.liveswitch.IceLocalRelayedCandidate) r2
            fm.liveswitch.TransportAddress r2 = r2.getTurnServer()
            goto L_0x00e3
        L_0x00e2:
            r2 = r15
        L_0x00e3:
            fm.liveswitch.IceSendMessageArgs r5 = new fm.liveswitch.IceSendMessageArgs
            r8 = r28
            r5.<init>(r3, r8)
            r5.setTurnRelay(r2)
            r0.sendStunMessage((fm.liveswitch.IceSendMessageArgs) r5)
            if (r4 != 0) goto L_0x02af
            java.lang.String r2 = r28.getIPAddress()
            int r3 = r28.getPort()
            fm.liveswitch.IceCandidate r2 = r1.findMatchingRemoteCandidate(r2, r3)
            if (r2 != 0) goto L_0x01bf
            java.lang.String r2 = r28.getIPAddress()
            boolean r2 = fm.liveswitch.TransportAddress.isPrivate(r2)
            if (r2 == 0) goto L_0x0132
            fm.liveswitch.CandidateType r2 = r27.getType()
            fm.liveswitch.CandidateType r3 = fm.liveswitch.CandidateType.Relayed
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)
            if (r2 == 0) goto L_0x0132
            fm.liveswitch.ILog r2 = __log
            boolean r2 = r2.getIsWarnEnabled()
            if (r2 == 0) goto L_0x0131
            fm.liveswitch.ILog r2 = __log
            java.lang.String r3 = "Check request discarded from {0} to {1}. Potential peer-reflexive candidate had a private IP address. (Is the TURN server on the same network as the local client?)"
            java.lang.String r4 = r28.toString()
            java.lang.String r0 = r27.toString()
            java.lang.String r0 = fm.liveswitch.StringExtensions.format(r3, r4, r0)
            r2.warn(r0)
        L_0x0131:
            return
        L_0x0132:
            java.lang.String r2 = ""
            java.lang.Object r3 = r1.__lock
            monitor-enter(r3)
            r18 = r2
            r2 = 0
        L_0x013a:
            if (r2 != 0) goto L_0x0163
            java.lang.String r2 = fm.liveswitch.Utility.generateId()     // Catch:{ all -> 0x01bc }
            java.util.ArrayList<fm.liveswitch.IceCandidate> r4 = r1.__remoteCandidates     // Catch:{ all -> 0x01bc }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x01bc }
            r5 = 1
        L_0x0147:
            boolean r9 = r4.hasNext()     // Catch:{ all -> 0x01bc }
            if (r9 == 0) goto L_0x015f
            java.lang.Object r9 = r4.next()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceCandidate r9 = (fm.liveswitch.IceCandidate) r9     // Catch:{ all -> 0x01bc }
            java.lang.String r9 = r9.getFoundation()     // Catch:{ all -> 0x01bc }
            boolean r9 = fm.liveswitch.Global.equals(r9, r2)     // Catch:{ all -> 0x01bc }
            if (r9 == 0) goto L_0x0147
            r5 = 0
            goto L_0x0147
        L_0x015f:
            r18 = r2
            r2 = r5
            goto L_0x013a
        L_0x0163:
            fm.liveswitch.stun.ice.PriorityAttribute r2 = r26.getPriority()     // Catch:{ all -> 0x01bc }
            long r4 = r2.getPriority()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceLocalReflexiveCandidate r2 = new fm.liveswitch.IceLocalReflexiveCandidate     // Catch:{ all -> 0x01bc }
            java.lang.Object r9 = r1.__lock     // Catch:{ all -> 0x01bc }
            fm.liveswitch.ProtocolType r19 = fm.liveswitch.ProtocolType.Udp     // Catch:{ all -> 0x01bc }
            java.lang.String r20 = r28.getIPAddress()     // Catch:{ all -> 0x01bc }
            int r21 = r28.getPort()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.CandidateType r22 = fm.liveswitch.CandidateType.PeerReflexive     // Catch:{ all -> 0x01bc }
            java.lang.String r23 = r27.getIPAddress()     // Catch:{ all -> 0x01bc }
            int r24 = r27.getPort()     // Catch:{ all -> 0x01bc }
            r16 = r2
            r17 = r9
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ all -> 0x01bc }
            r2.setLocalHostBaseCandidate(r0)     // Catch:{ all -> 0x01bc }
            r2.setPriority(r4)     // Catch:{ all -> 0x01bc }
            java.lang.String r4 = r7.getUsernameFragment()     // Catch:{ all -> 0x01bc }
            r2.setUsername(r4)     // Catch:{ all -> 0x01bc }
            java.lang.String r4 = r7.getPassword()     // Catch:{ all -> 0x01bc }
            r2.setPassword(r4)     // Catch:{ all -> 0x01bc }
            fm.liveswitch.ILog r4 = __log     // Catch:{ all -> 0x01bc }
            boolean r4 = r4.getIsDebugEnabled()     // Catch:{ all -> 0x01bc }
            if (r4 == 0) goto L_0x01b5
            fm.liveswitch.ILog r4 = __log     // Catch:{ all -> 0x01bc }
            java.lang.String r5 = "Learned new remote peer reflexive candidate {0}."
            java.lang.String r8 = r2.toString()     // Catch:{ all -> 0x01bc }
            java.lang.String r5 = fm.liveswitch.StringExtensions.format((java.lang.String) r5, (java.lang.Object) r8)     // Catch:{ all -> 0x01bc }
            r4.debug(r5)     // Catch:{ all -> 0x01bc }
        L_0x01b5:
            java.util.ArrayList<fm.liveswitch.IceCandidate> r4 = r1.__remoteCandidates     // Catch:{ all -> 0x01bc }
            r4.add(r2)     // Catch:{ all -> 0x01bc }
            monitor-exit(r3)     // Catch:{ all -> 0x01bc }
            goto L_0x01bf
        L_0x01bc:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x01bc }
            throw r0
        L_0x01bf:
            r5 = r2
            java.lang.Object r12 = r1.__lock
            monitor-enter(r12)
            fm.liveswitch.IceCheckList r2 = r1.__checkList     // Catch:{ all -> 0x028f }
            fm.liveswitch.IceCandidatePair r2 = r2.findMatchingCandidatePair(r0, r5)     // Catch:{ all -> 0x028f }
            if (r2 != 0) goto L_0x020a
            fm.liveswitch.IceCandidatePair r11 = new fm.liveswitch.IceCandidatePair     // Catch:{ all -> 0x028f }
            java.lang.Object r3 = r1.__lock     // Catch:{ all -> 0x028f }
            fm.liveswitch.IceRole r8 = r25.getRole()     // Catch:{ all -> 0x028f }
            long r9 = r1.__tieBreaker     // Catch:{ all -> 0x028f }
            fm.liveswitch.IceComponent r16 = r25.getComponent()     // Catch:{ all -> 0x028f }
            fm.liveswitch.IceTransactionManager r4 = r1.__transactionManager     // Catch:{ all -> 0x028f }
            r2 = r11
            r17 = r4
            r4 = r27
            r0 = r11
            r11 = r16
            r16 = r12
            r12 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r11, r12)     // Catch:{ all -> 0x0294 }
            fm.liveswitch.IceTransport$10 r2 = new fm.liveswitch.IceTransport$10     // Catch:{ all -> 0x0294 }
            r2.<init>()     // Catch:{ all -> 0x0294 }
            r0.setOnStunResponse(r2)     // Catch:{ all -> 0x0294 }
            fm.liveswitch.IceTransport$11 r2 = new fm.liveswitch.IceTransport$11     // Catch:{ all -> 0x0294 }
            r2.<init>()     // Catch:{ all -> 0x0294 }
            r0.setOnStateChange(r2)     // Catch:{ all -> 0x0294 }
            fm.liveswitch.IceTransport$12 r2 = new fm.liveswitch.IceTransport$12     // Catch:{ all -> 0x0294 }
            r2.<init>()     // Catch:{ all -> 0x0294 }
            r0._onPriorityChange = r2     // Catch:{ all -> 0x0294 }
            r0.notifyDataReceived(r15)     // Catch:{ all -> 0x0294 }
            r1.addCandidatePairToCache(r0)     // Catch:{ all -> 0x0294 }
            r2 = r0
            r0 = 0
            goto L_0x0225
        L_0x020a:
            r16 = r12
            fm.liveswitch.CandidatePairState r0 = r2.getState()     // Catch:{ all -> 0x0294 }
            fm.liveswitch.CandidatePairState r3 = fm.liveswitch.CandidatePairState.Succeeded     // Catch:{ all -> 0x0294 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)     // Catch:{ all -> 0x0294 }
            if (r0 == 0) goto L_0x0221
            boolean r0 = r2.getNominated()     // Catch:{ all -> 0x0294 }
            if (r0 == 0) goto L_0x0221
            r0 = 1
            r13 = 1
            goto L_0x0225
        L_0x0221:
            r1.addCandidatePairToCache(r2)     // Catch:{ all -> 0x0294 }
            r0 = 1
        L_0x0225:
            if (r13 != 0) goto L_0x0281
            fm.liveswitch.IceCheckList r3 = r1.__checkList     // Catch:{ all -> 0x0294 }
            r3.addToTriggeredCheckList(r2, r0)     // Catch:{ all -> 0x0294 }
            fm.liveswitch.IceRole r0 = r25.getRole()     // Catch:{ all -> 0x0294 }
            fm.liveswitch.IceRole r3 = fm.liveswitch.IceRole.Controlled     // Catch:{ all -> 0x0294 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)     // Catch:{ all -> 0x0294 }
            if (r0 == 0) goto L_0x028d
            fm.liveswitch.stun.ice.UseCandidateAttribute r0 = r26.getUseCandidate()     // Catch:{ all -> 0x0294 }
            if (r0 == 0) goto L_0x028d
            fm.liveswitch.CandidatePairState r0 = r2.getState()     // Catch:{ all -> 0x0294 }
            fm.liveswitch.CandidatePairState r3 = fm.liveswitch.CandidatePairState.Succeeded     // Catch:{ all -> 0x0294 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)     // Catch:{ all -> 0x0294 }
            if (r0 == 0) goto L_0x027d
            fm.liveswitch.Holder r0 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0294 }
            r0.<init>(r15)     // Catch:{ all -> 0x0294 }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidatePair> r3 = r1.__candidatePairCache     // Catch:{ all -> 0x0294 }
            java.lang.String r4 = r2.getRelatedValidPairId()     // Catch:{ all -> 0x0294 }
            boolean r3 = fm.liveswitch.HashMapExtensions.tryGetValue(r3, r4, r0)     // Catch:{ all -> 0x0294 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ all -> 0x0294 }
            fm.liveswitch.IceCandidatePair r0 = (fm.liveswitch.IceCandidatePair) r0     // Catch:{ all -> 0x0294 }
            if (r3 == 0) goto L_0x026b
            r2.setNominated(r14)     // Catch:{ all -> 0x0294 }
            r0.setNominated(r14)     // Catch:{ all -> 0x0294 }
            r25.processNominatedPair()     // Catch:{ all -> 0x0294 }
            goto L_0x028d
        L_0x026b:
            java.lang.String r3 = "Received STUN response during connectivity checks in Controlled state with USE-Candidate flag set. Candidate Pair state with id {0} is Succeeded, but related valid pair with Id {1} has not been found in cache."
            java.lang.String r2 = r2.getId()     // Catch:{ all -> 0x0294 }
            java.lang.String r0 = r0.getId()     // Catch:{ all -> 0x0294 }
            java.lang.String r0 = fm.liveswitch.StringExtensions.format(r3, r2, r0)     // Catch:{ all -> 0x0294 }
            fm.liveswitch.Log.error(r0)     // Catch:{ all -> 0x0294 }
            goto L_0x028d
        L_0x027d:
            r2.setUseCandidateReceived(r14)     // Catch:{ all -> 0x0294 }
            goto L_0x028d
        L_0x0281:
            r2.receivedSomething()     // Catch:{ all -> 0x0294 }
            boolean r0 = r25.getActiveIceKeepAliveEnabled()     // Catch:{ all -> 0x0294 }
            if (r0 != 0) goto L_0x028d
            r2.sendPassiveKeepAlive()     // Catch:{ all -> 0x0294 }
        L_0x028d:
            monitor-exit(r16)     // Catch:{ all -> 0x0294 }
            goto L_0x02af
        L_0x028f:
            r0 = move-exception
            r16 = r12
        L_0x0292:
            monitor-exit(r16)     // Catch:{ all -> 0x0294 }
            throw r0
        L_0x0294:
            r0 = move-exception
            goto L_0x0292
        L_0x0296:
            r8 = r28
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsDebugEnabled()
            if (r0 == 0) goto L_0x02af
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = "Ignoring request from {0}. Message integrity check failed."
            java.lang.String r3 = r28.toString()
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r3)
            r0.debug(r2)
        L_0x02af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransport.processStunRequest(fm.liveswitch.stun.Message, fm.liveswitch.IceCandidate, fm.liveswitch.TransportAddress):void");
    }

    /* access modifiers changed from: private */
    public void processStunResponse(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
        synchronized (this.__lock) {
            IceGatherer iceGatherer = this.__gatherer;
            IceParameters remoteParameters = getRemoteParameters();
            IceTransportState state = getState();
            HashMap<String, IceCandidatePair> hashMap = this.__candidatePairCache;
            IceCheckList iceCheckList = this.__checkList;
            IceValidList iceValidList = this.__validList;
            long j = this.__tieBreaker;
            IceComponent component = getComponent();
            int keepAliveInterval = getOptions().getKeepAliveInterval();
            AnonymousClass13 r15 = new IFunctionDelegate2<String, Integer, IceCandidate>() {
                public String getId() {
                    return "fm.liveswitch.IceTransport.findMatchingLocalCandidate";
                }

                public IceCandidate invoke(String str, Integer num) {
                    return IceTransport.this.findMatchingLocalCandidate(str, num.intValue());
                }
            };
            AnonymousClass14 r0 = new IActionDelegate1<IceSendRequestSuccessArgs>() {
                public String getId() {
                    return "fm.liveswitch.IceTransport.processStunResponse";
                }

                public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                    IceTransport.this.processStunResponse(iceSendRequestSuccessArgs);
                }
            };
            AnonymousClass15 r3 = new IActionDelegate1<IceCandidatePair>() {
                public String getId() {
                    return "fm.liveswitch.IceTransport.processCandidatePairStateChange";
                }

                public void invoke(IceCandidatePair iceCandidatePair) {
                    IceTransport.this.processCandidatePairStateChange(iceCandidatePair);
                }
            };
            AnonymousClass16 r17 = new IActionDelegate1<IceCandidatePair>() {
                public String getId() {
                    return "fm.liveswitch.IceTransport.processCandidatePairPriorityChange";
                }

                public void invoke(IceCandidatePair iceCandidatePair) {
                    IceTransport.this.processCandidatePairPriorityChange(iceCandidatePair);
                }
            };
            AnonymousClass17 r18 = new IActionDelegate1<IceCandidatePair>() {
                public String getId() {
                    return "fm.liveswitch.IceTransport.addCandidatePairToCache";
                }

                public void invoke(IceCandidatePair iceCandidatePair) {
                    IceTransport.this.addCandidatePairToCache(iceCandidatePair);
                }
            };
            AnonymousClass18 r19 = new IActionDelegate0() {
                public String getId() {
                    return "fm.liveswitch.IceTransport.processNominatedPair";
                }

                public void invoke() {
                    IceTransport.this.processNominatedPair();
                }
            };
            AnonymousClass19 r7 = new IActionDelegate1<IceRole>() {
                public String getId() {
                    return "fm.liveswitch.IceTransport.updateRole";
                }

                public void invoke(IceRole iceRole) {
                    IceTransport.this.updateRole(iceRole);
                }
            };
            IceRole role = getRole();
            AnonymousClass19 r20 = r7;
            AnonymousClass15 r23 = r3;
            IceTransactionManager iceTransactionManager = this.__transactionManager;
            AnonymousClass19 r21 = r20;
            AnonymousClass18 r202 = r19;
            AnonymousClass17 r192 = r18;
            AnonymousClass16 r182 = r17;
            doProcessStunResponse(iceSendRequestSuccessArgs, iceGatherer, remoteParameters, state, true, hashMap, iceCheckList, iceValidList, j, component, keepAliveInterval, r15, r0, r23, r182, r192, r202, r21, role, iceTransactionManager, getId(), this.__lock);
        }
    }

    private void raiseStateChange() {
        IAction1<IceTransport> iAction1 = this._onStateChange;
        if (iAction1 != null) {
            iAction1.invoke(this);
        }
    }

    public void removeOnActiveCandidatePairChange(IAction2<IceTransport, IceCandidatePair> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onActiveCandidatePairChange, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onActiveCandidatePairChange.remove(iAction2);
        if (this.__onActiveCandidatePairChange.size() == 0) {
            this._onActiveCandidatePairChange = null;
        }
    }

    public void removeOnReceive(IAction1<DataBuffer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onReceive, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onReceive.remove(iAction1);
        if (this.__onReceive.size() == 0) {
            this._onReceive = null;
        }
    }

    public void removeOnStateChange(IAction1<IceTransport> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public void removeRtcpTransport() {
        synchronized (this.__lock) {
            this.__rtcpTransport = null;
        }
    }

    public void send(DataBuffer dataBuffer) {
        IceCandidatePair activeCandidatePair = getActiveCandidatePair();
        if (activeCandidatePair != null) {
            try {
                this._bytesSent.add((long) dataBuffer.getLength());
                activeCandidatePair.send(dataBuffer);
            } catch (Exception e) {
                setError(new Error(ErrorCode.IceSendError, e));
            }
        }
    }

    private void setActiveCandidatePair(IceCandidatePair iceCandidatePair) {
        doSetActiveCandidatePair(iceCandidatePair);
    }

    /* access modifiers changed from: package-private */
    public void setActiveIceKeepAliveEnabled(boolean z) {
        this.__activeIceKeepAliveEnabled = z;
    }

    public void setClosingShouldNotTriggerGlobalNonGracefulShutdown(boolean z) {
        this._closingShouldNotTriggerGlobalNonGracefulShutdown = z;
    }

    private void setError(Error error) {
        this._error = error;
    }

    /* access modifiers changed from: package-private */
    public void setIcePolicy(IcePolicy icePolicy) {
        if (!Global.equals(this.__icePolicy, icePolicy)) {
            this.__icePolicy = icePolicy;
            if (Global.equals(icePolicy, IcePolicy.Disabled)) {
                setUseIce(false);
            } else if (Global.equals(this.__icePolicy, IcePolicy.Required)) {
                setUseIce(true);
            }
        }
    }

    private void setId(String str) {
        this._id = str;
    }

    /* access modifiers changed from: package-private */
    public void setOptions(IceTransportOptions iceTransportOptions) {
        this._options = iceTransportOptions;
    }

    private void setRole(IceRole iceRole) {
        synchronized (this.__lock) {
            if (!Global.equals(iceRole, this.__role)) {
                this.__role = iceRole;
                for (IceCandidatePair iceCandidatePair : this.__checkList.getCandidatePairs()) {
                    iceCandidatePair.setLocalRole(this.__role);
                    iceCandidatePair.assignPriority(this.__role);
                }
            }
        }
    }

    public void setSingleRemoteCandidateForIceBypass(IceCandidate iceCandidate) {
        IceGatherer iceGatherer;
        IceCandidate defaultLocalCandidate;
        synchronized (this.__lock) {
            if (!getIsClosed() && (iceCandidate == null || this.__singleRemoteCandidateForIceBypass == null || !Global.equals(iceCandidate.getIPAddress(), this.__singleRemoteCandidateForIceBypass.getIPAddress()) || iceCandidate.getPort() != this.__singleRemoteCandidateForIceBypass.getPort())) {
                this.__singleRemoteCandidateForIceBypass = iceCandidate;
                if (!(getUseIce() || this.__singleRemoteCandidateForIceBypass == null || (iceGatherer = this.__gatherer) == null || (defaultLocalCandidate = iceGatherer.getDefaultLocalCandidate()) == null)) {
                    IceCandidatePair iceCandidatePair = new IceCandidatePair(this.__lock, defaultLocalCandidate, this.__singleRemoteCandidateForIceBypass, getGatherer().getLocalParameters(), getRemoteParameters(), getRole(), this.__tieBreaker, getComponent(), this.__transactionManager, getUseIce());
                    iceCandidatePair.setOnStunResponse(new IActionDelegate1<IceSendRequestSuccessArgs>() {
                        public String getId() {
                            return "fm.liveswitch.IceTransport.processStunResponse";
                        }

                        public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                            IceTransport.this.processStunResponse(iceSendRequestSuccessArgs);
                        }
                    });
                    iceCandidatePair.setOnStateChange(new IActionDelegate1<IceCandidatePair>() {
                        public String getId() {
                            return "fm.liveswitch.IceTransport.processCandidatePairStateChange";
                        }

                        public void invoke(IceCandidatePair iceCandidatePair) {
                            IceTransport.this.processCandidatePairStateChange(iceCandidatePair);
                        }
                    });
                    addCandidatePairToCache(iceCandidatePair);
                    iceCandidatePair.bypassConnectivityChecks();
                    if (Global.equals(defaultLocalCandidate.getType(), CandidateType.Relayed)) {
                        iceCandidatePair.setOnOriginalRelayPermissionsObtained(new IActionDelegate1<IceCandidatePair>() {
                            public String getId() {
                                return "fm.liveswitch.IceTransport.processGotOriginalRelayPermissionsNonIceCP";
                            }

                            public void invoke(IceCandidatePair iceCandidatePair) {
                                IceTransport.this.processGotOriginalRelayPermissionsNonIceCP(iceCandidatePair);
                            }
                        });
                        iceCandidatePair.startPermissionRequests();
                    } else {
                        doSetActiveCandidatePair(iceCandidatePair);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setState(fm.liveswitch.IceTransportState r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.__lock
            monitor-enter(r0)
            fm.liveswitch.IceTransportState r1 = r11.__state     // Catch:{ all -> 0x01bc }
            boolean r1 = fm.liveswitch.Global.equals(r1, r12)     // Catch:{ all -> 0x01bc }
            if (r1 != 0) goto L_0x01ba
            fm.liveswitch.Scheduler r1 = r11.__scheduler     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceTransportState r2 = r11.__state     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceTransportState r3 = fm.liveswitch.IceTransportState.Closed     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x01bc }
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x001b
        L_0x0019:
            r2 = 1
            goto L_0x0047
        L_0x001b:
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.Connected     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r12, r2)     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x002f
            if (r1 == 0) goto L_0x002a
            fm.liveswitch.ScheduledItem r2 = r11.__transitionToFailedStateSI     // Catch:{ all -> 0x01bc }
            r1.remove(r2)     // Catch:{ all -> 0x01bc }
        L_0x002a:
            fm.liveswitch.IceCandidatePair r2 = r11.__activeCandidatePair     // Catch:{ all -> 0x01bc }
            if (r2 != 0) goto L_0x0046
            goto L_0x0019
        L_0x002f:
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.Closed     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r12, r2)     // Catch:{ all -> 0x01bc }
            if (r2 != 0) goto L_0x003f
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.Failed     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r12, r2)     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x0046
        L_0x003f:
            if (r1 == 0) goto L_0x0046
            fm.liveswitch.ScheduledItem r2 = r11.__transitionToFailedStateSI     // Catch:{ all -> 0x01bc }
            r1.remove(r2)     // Catch:{ all -> 0x01bc }
        L_0x0046:
            r2 = 0
        L_0x0047:
            if (r2 != 0) goto L_0x01ba
            boolean r2 = fm.liveswitch.Log.getIsDebugEnabled()     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x00e6
            fm.liveswitch.IceTransportState r2 = r11.__state     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceTransportState r5 = fm.liveswitch.IceTransportState.Checking     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r2, r5)     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x0086
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.Connected     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r12, r2)     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x0086
            long r5 = fm.liveswitch.ManagedStopwatch.getTimestamp()     // Catch:{ all -> 0x01bc }
            long r7 = r11.__lastStateChangeSystemTimestamp     // Catch:{ all -> 0x01bc }
            long r5 = r5 - r7
            int r2 = fm.liveswitch.Constants.getTicksPerMillisecond()     // Catch:{ all -> 0x01bc }
            long r7 = (long) r2     // Catch:{ all -> 0x01bc }
            long r5 = r5 / r7
            int r2 = (int) r5     // Catch:{ all -> 0x01bc }
            java.lang.String r5 = "ICE transport {0} took {1}ms to find a successful candidate pair for connection {2}."
            java.lang.String r6 = r11.getId()     // Catch:{ all -> 0x01bc }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x01bc }
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)     // Catch:{ all -> 0x01bc }
            java.lang.String r7 = r11.__connectionId     // Catch:{ all -> 0x01bc }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r5, r6, r2, r7)     // Catch:{ all -> 0x01bc }
            fm.liveswitch.Log.debug(r2)     // Catch:{ all -> 0x01bc }
        L_0x0086:
            fm.liveswitch.IceTransportState r2 = r11.__state     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceTransportState r5 = fm.liveswitch.IceTransportState.Failed     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r2, r5)     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x00cf
            fm.liveswitch.Error r2 = r11.getError()     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x00cf
            java.lang.String r2 = "Setting ICE transport {0} state to {1} for connection {2}. ({3})"
            r5 = 4
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x01bc }
            java.lang.String r6 = r11.getId()     // Catch:{ all -> 0x01bc }
            r5[r4] = r6     // Catch:{ all -> 0x01bc }
            java.lang.String r6 = r12.toString()     // Catch:{ all -> 0x01bc }
            java.lang.String r6 = fm.liveswitch.StringExtensions.toLower(r6)     // Catch:{ all -> 0x01bc }
            r5[r3] = r6     // Catch:{ all -> 0x01bc }
            r3 = 2
            java.lang.String r6 = r11.__connectionId     // Catch:{ all -> 0x01bc }
            r5[r3] = r6     // Catch:{ all -> 0x01bc }
            r3 = 3
            fm.liveswitch.Error r6 = r11.getError()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.ErrorCode r6 = r6.getCode()     // Catch:{ all -> 0x01bc }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x01bc }
            r5[r3] = r6     // Catch:{ all -> 0x01bc }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object[]) r5)     // Catch:{ all -> 0x01bc }
            fm.liveswitch.Error r3 = r11.getError()     // Catch:{ all -> 0x01bc }
            java.lang.Exception r3 = r3.getException()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.Log.debug(r2, r3)     // Catch:{ all -> 0x01bc }
            goto L_0x00e6
        L_0x00cf:
            java.lang.String r2 = "Setting ICE transport {0} state to {1} for connection {2}."
            java.lang.String r3 = r11.getId()     // Catch:{ all -> 0x01bc }
            java.lang.String r5 = r12.toString()     // Catch:{ all -> 0x01bc }
            java.lang.String r5 = fm.liveswitch.StringExtensions.toLower(r5)     // Catch:{ all -> 0x01bc }
            java.lang.String r6 = r11.__connectionId     // Catch:{ all -> 0x01bc }
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r2, r3, r5, r6)     // Catch:{ all -> 0x01bc }
            fm.liveswitch.Log.debug(r2)     // Catch:{ all -> 0x01bc }
        L_0x00e6:
            r11.__state = r12     // Catch:{ all -> 0x01bc }
            long r2 = fm.liveswitch.ManagedStopwatch.getTimestamp()     // Catch:{ all -> 0x01bc }
            r11.__lastStateChangeSystemTimestamp = r2     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceTransportState r2 = r11.__state     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceTransportState r3 = fm.liveswitch.IceTransportState.Connected     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x011b
            java.util.ArrayList<fm.liveswitch.DataBuffer> r2 = r11.__sendQueue     // Catch:{ all -> 0x01bc }
            int r2 = fm.liveswitch.ArrayListExtensions.getCount(r2)     // Catch:{ all -> 0x01bc }
            if (r2 <= 0) goto L_0x011b
            java.util.ArrayList<fm.liveswitch.DataBuffer> r2 = r11.__sendQueue     // Catch:{ all -> 0x01bc }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x01bc }
        L_0x0106:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x01bc }
            if (r3 == 0) goto L_0x0116
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.DataBuffer r3 = (fm.liveswitch.DataBuffer) r3     // Catch:{ all -> 0x01bc }
            r11.send(r3)     // Catch:{ all -> 0x01bc }
            goto L_0x0106
        L_0x0116:
            java.util.ArrayList<fm.liveswitch.DataBuffer> r2 = r11.__sendQueue     // Catch:{ all -> 0x01bc }
            r2.clear()     // Catch:{ all -> 0x01bc }
        L_0x011b:
            r11.raiseStateChange()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceTransportState r2 = fm.liveswitch.IceTransportState.Disconnected     // Catch:{ all -> 0x01bc }
            boolean r2 = fm.liveswitch.Global.equals(r12, r2)     // Catch:{ all -> 0x01bc }
            if (r2 == 0) goto L_0x015a
            if (r1 == 0) goto L_0x015a
            fm.liveswitch.IceTransportOptions r2 = r11.getOptions()     // Catch:{ all -> 0x01bc }
            if (r2 != 0) goto L_0x0130
        L_0x012e:
            r8 = 0
            goto L_0x0144
        L_0x0130:
            fm.liveswitch.IceTransportOptions r2 = r11.getOptions()     // Catch:{ all -> 0x01bc }
            int r2 = r2.getDeadStreamTimeout()     // Catch:{ all -> 0x01bc }
            if (r2 >= 0) goto L_0x013b
            goto L_0x012e
        L_0x013b:
            fm.liveswitch.IceTransportOptions r2 = r11.getOptions()     // Catch:{ all -> 0x01bc }
            int r4 = r2.getDeadStreamTimeout()     // Catch:{ all -> 0x01bc }
            r8 = r4
        L_0x0144:
            fm.liveswitch.ScheduledItem r2 = new fm.liveswitch.ScheduledItem     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceTransport$23 r6 = new fm.liveswitch.IceTransport$23     // Catch:{ all -> 0x01bc }
            r6.<init>()     // Catch:{ all -> 0x01bc }
            int r9 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x01bc }
            r10 = 1
            r5 = r2
            r7 = r8
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x01bc }
            r11.__transitionToFailedStateSI = r2     // Catch:{ all -> 0x01bc }
            r1.add(r2)     // Catch:{ all -> 0x01bc }
        L_0x015a:
            fm.liveswitch.IceTransportState r1 = fm.liveswitch.IceTransportState.Failed     // Catch:{ all -> 0x01bc }
            boolean r12 = fm.liveswitch.Global.equals(r12, r1)     // Catch:{ all -> 0x01bc }
            if (r12 == 0) goto L_0x01ba
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidatePair> r12 = r11.__candidatePairCache     // Catch:{ all -> 0x01bc }
            int r12 = fm.liveswitch.HashMapExtensions.getCount(r12)     // Catch:{ all -> 0x01bc }
            if (r12 <= 0) goto L_0x01ab
            fm.liveswitch.ILog r12 = __log     // Catch:{ all -> 0x01bc }
            java.lang.String r1 = "ICE transport {0}: State of connectivity checks on candidate pairs:"
            java.lang.String r2 = r11.getId()     // Catch:{ all -> 0x01bc }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x01bc }
            r12.debug(r1)     // Catch:{ all -> 0x01bc }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidatePair> r12 = r11.__candidatePairCache     // Catch:{ all -> 0x01bc }
            java.util.Collection r12 = fm.liveswitch.HashMapExtensions.getValues(r12)     // Catch:{ all -> 0x01bc }
            java.util.Iterator r12 = r12.iterator()     // Catch:{ all -> 0x01bc }
        L_0x0183:
            boolean r1 = r12.hasNext()     // Catch:{ all -> 0x01bc }
            if (r1 == 0) goto L_0x01ba
            java.lang.Object r1 = r12.next()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.IceCandidatePair r1 = (fm.liveswitch.IceCandidatePair) r1     // Catch:{ all -> 0x01bc }
            fm.liveswitch.ILog r2 = __log     // Catch:{ all -> 0x01bc }
            java.lang.String r3 = "ICE transport {0}: {1} - {2}"
            java.lang.String r4 = r11.getId()     // Catch:{ all -> 0x01bc }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x01bc }
            fm.liveswitch.CandidatePairState r1 = r1.getState()     // Catch:{ all -> 0x01bc }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01bc }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format(r3, r4, r5, r1)     // Catch:{ all -> 0x01bc }
            r2.debug(r1)     // Catch:{ all -> 0x01bc }
            goto L_0x0183
        L_0x01ab:
            fm.liveswitch.ILog r12 = __log     // Catch:{ all -> 0x01bc }
            java.lang.String r1 = "ICE transport {0}: Connectivity checks could not be conducted - there were no candidate pairs."
            java.lang.String r2 = r11.getId()     // Catch:{ all -> 0x01bc }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x01bc }
            r12.debug(r1)     // Catch:{ all -> 0x01bc }
        L_0x01ba:
            monitor-exit(r0)     // Catch:{ all -> 0x01bc }
            return
        L_0x01bc:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01bc }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransport.setState(fm.liveswitch.IceTransportState):void");
    }

    /* access modifiers changed from: package-private */
    public void setUseIce(boolean z) {
        if (!Global.equals(Boolean.valueOf(this.__useIce), Boolean.valueOf(z))) {
            this.__candidatePairCheckSI.setSuspended(!z);
            this.__useIce = z;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0134, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean start(fm.liveswitch.IceGatherer r18, fm.liveswitch.IceParameters r19, fm.liveswitch.IceRole r20, long r21) {
        /*
            r17 = this;
            r1 = r17
            java.lang.Object r2 = r1.__lock
            monitor-enter(r2)
            fm.liveswitch.IceTransportState r0 = r17.getState()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransportState r3 = fm.liveswitch.IceTransportState.New     // Catch:{ all -> 0x0142 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)     // Catch:{ all -> 0x0142 }
            r3 = 0
            if (r0 != 0) goto L_0x0020
            fm.liveswitch.IceTransportState r0 = r17.getState()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransportState r4 = fm.liveswitch.IceTransportState.Closed     // Catch:{ all -> 0x0142 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r4)     // Catch:{ all -> 0x0142 }
            if (r0 != 0) goto L_0x0020
            monitor-exit(r2)     // Catch:{ all -> 0x0142 }
            return r3
        L_0x0020:
            fm.liveswitch.IceGatheringState r0 = r18.getState()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceGatheringState r4 = fm.liveswitch.IceGatheringState.Closed     // Catch:{ all -> 0x0142 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r4)     // Catch:{ all -> 0x0142 }
            if (r0 != 0) goto L_0x0135
            fm.liveswitch.IceComponent r0 = r18.getComponent()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceComponent r4 = r17.getComponent()     // Catch:{ all -> 0x0142 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r4)     // Catch:{ all -> 0x0142 }
            if (r0 != 0) goto L_0x0043
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x0142 }
            java.lang.String r4 = "Components do not match."
            r0.error(r4)     // Catch:{ all -> 0x0142 }
            monitor-exit(r2)     // Catch:{ all -> 0x0142 }
            return r3
        L_0x0043:
            fm.liveswitch.Scheduler r0 = r1.__scheduler     // Catch:{ all -> 0x0142 }
            fm.liveswitch.ScheduledItem r4 = r1.__candidatePairCheckSI     // Catch:{ all -> 0x0142 }
            r0.add(r4)     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransactionManager r0 = r18.getTransactionManager()     // Catch:{ all -> 0x0142 }
            r1.__transactionManager = r0     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransportState r0 = fm.liveswitch.IceTransportState.Checking     // Catch:{ all -> 0x0142 }
            r1.setState(r0)     // Catch:{ all -> 0x0142 }
            r0 = r18
            r1.__gatherer = r0     // Catch:{ all -> 0x0142 }
            r4 = r19
            r1.__remoteParameters = r4     // Catch:{ all -> 0x0142 }
            r4 = r20
            r1.setRole(r4)     // Catch:{ all -> 0x0142 }
            r4 = r21
            r1.__tieBreaker = r4     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceGatherer r4 = r1.__gatherer     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransport$24 r5 = new fm.liveswitch.IceTransport$24     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            r4.removeOnLocalCandidate(r5)     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceGatherer r4 = r1.__gatherer     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransport$25 r5 = new fm.liveswitch.IceTransport$25     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            r4.addOnLocalCandidate(r5)     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceGatherer r4 = r1.__gatherer     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransport$26 r5 = new fm.liveswitch.IceTransport$26     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            r4.removeOnIncomingApplicationData(r5)     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceGatherer r4 = r1.__gatherer     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransport$27 r5 = new fm.liveswitch.IceTransport$27     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            r4.addOnIncomingApplicationData(r5)     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceGatherer r4 = r1.__gatherer     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransport$28 r5 = new fm.liveswitch.IceTransport$28     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            r4.setOnStunRequest(r5)     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceGatherer r4 = r1.__gatherer     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceCandidate[] r4 = r4.getLocalCandidates()     // Catch:{ all -> 0x0142 }
            int r5 = r4.length     // Catch:{ all -> 0x0142 }
        L_0x009f:
            if (r3 >= r5) goto L_0x00a9
            r6 = r4[r3]     // Catch:{ all -> 0x0142 }
            r1.addLocalCandidate(r6)     // Catch:{ all -> 0x0142 }
            int r3 = r3 + 1
            goto L_0x009f
        L_0x00a9:
            boolean r3 = r17.getUseIce()     // Catch:{ all -> 0x0142 }
            r4 = 1
            if (r3 == 0) goto L_0x00c3
            boolean r3 = r17.getActiveIceKeepAliveEnabled()     // Catch:{ all -> 0x0142 }
            if (r3 == 0) goto L_0x00c3
            r1.__keepAliveActive = r4     // Catch:{ all -> 0x0142 }
            fm.liveswitch.ScheduledItem r3 = r17.createKeepAliveScheduledItem()     // Catch:{ all -> 0x0142 }
            r1.__keepAliveScheduledItem = r3     // Catch:{ all -> 0x0142 }
            fm.liveswitch.Scheduler r5 = r1.__scheduler     // Catch:{ all -> 0x0142 }
            r5.add(r3)     // Catch:{ all -> 0x0142 }
        L_0x00c3:
            boolean r3 = r17.getUseIce()     // Catch:{ all -> 0x0142 }
            if (r3 != 0) goto L_0x0133
            fm.liveswitch.IceCandidatePair r3 = r17.getActiveCandidatePair()     // Catch:{ all -> 0x0142 }
            if (r3 != 0) goto L_0x0133
            fm.liveswitch.IceCandidate r3 = r17.getSingleRemoteCandidateForIceBypass()     // Catch:{ all -> 0x0142 }
            if (r3 == 0) goto L_0x0133
            fm.liveswitch.IceCandidate r0 = r18.getDefaultLocalCandidate()     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x0133
            fm.liveswitch.IceCandidatePair r3 = new fm.liveswitch.IceCandidatePair     // Catch:{ all -> 0x0142 }
            java.lang.Object r6 = r1.__lock     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceCandidate r8 = r1.__singleRemoteCandidateForIceBypass     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceGatherer r5 = r17.getGatherer()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceParameters r9 = r5.getLocalParameters()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceParameters r10 = r17.getRemoteParameters()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceRole r11 = r17.getRole()     // Catch:{ all -> 0x0142 }
            long r12 = r1.__tieBreaker     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceComponent r14 = r17.getComponent()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransactionManager r15 = r1.__transactionManager     // Catch:{ all -> 0x0142 }
            boolean r16 = r17.getUseIce()     // Catch:{ all -> 0x0142 }
            r5 = r3
            r7 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r14, r15, r16)     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransport$29 r5 = new fm.liveswitch.IceTransport$29     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            r3.setOnStunResponse(r5)     // Catch:{ all -> 0x0142 }
            fm.liveswitch.IceTransport$30 r5 = new fm.liveswitch.IceTransport$30     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            r3.setOnStateChange(r5)     // Catch:{ all -> 0x0142 }
            r1.addCandidatePairToCache(r3)     // Catch:{ all -> 0x0142 }
            r3.bypassConnectivityChecks()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.CandidateType r0 = r0.getType()     // Catch:{ all -> 0x0142 }
            fm.liveswitch.CandidateType r5 = fm.liveswitch.CandidateType.Relayed     // Catch:{ all -> 0x0142 }
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x0130
            fm.liveswitch.IceTransport$31 r0 = new fm.liveswitch.IceTransport$31     // Catch:{ all -> 0x0142 }
            r0.<init>()     // Catch:{ all -> 0x0142 }
            r3.setOnOriginalRelayPermissionsObtained(r0)     // Catch:{ all -> 0x0142 }
            r3.startPermissionRequests()     // Catch:{ all -> 0x0142 }
            goto L_0x0133
        L_0x0130:
            r1.doSetActiveCandidatePair(r3)     // Catch:{ all -> 0x0142 }
        L_0x0133:
            monitor-exit(r2)     // Catch:{ all -> 0x0142 }
            return r4
        L_0x0135:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0142 }
            java.lang.Exception r3 = new java.lang.Exception     // Catch:{ all -> 0x0142 }
            java.lang.String r4 = "Cannot start ICE transport: supplied gatherer is in an invalid state."
            r3.<init>(r4)     // Catch:{ all -> 0x0142 }
            r0.<init>(r3)     // Catch:{ all -> 0x0142 }
            throw r0     // Catch:{ all -> 0x0142 }
        L_0x0142:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0142 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceTransport.start(fm.liveswitch.IceGatherer, fm.liveswitch.IceParameters, fm.liveswitch.IceRole, long):boolean");
    }

    public boolean stop() {
        synchronized (this.__lock) {
            if (!Global.equals(getState(), IceTransportState.New) && !Global.equals(getState(), IceTransportState.Checking) && !Global.equals(getState(), IceTransportState.Failed) && !Global.equals(getState(), IceTransportState.Connected) && !Global.equals(getState(), IceTransportState.Disconnected)) {
                return false;
            }
            removeRtcpTransport();
            IceTransactionManager iceTransactionManager = this.__transactionManager;
            if (iceTransactionManager != null) {
                iceTransactionManager.remove(this);
            }
            this.__keepAliveActive = false;
            this.__scheduler.remove(this.__keepAliveScheduledItem);
            this.__scheduler.remove(this.__candidatePairCheckSI);
            this.__scheduler.remove(this.__transitionToFailedStateSI);
            this.__sendQueue.clear();
            this.__localCandidates.clear();
            this.__remoteCandidates.clear();
            this.__validList.clear();
            this.__checkList.clear();
            this.__candidatePairCache.clear();
            if (!Global.equals(getState(), IceTransportState.Failed)) {
                setState(IceTransportState.Closed);
            }
            IceGatherer iceGatherer = this.__gatherer;
            if (iceGatherer != null) {
                iceGatherer.removeOnIncomingApplicationData(new IActionDelegate3<DataBuffer, IceCandidate, TransportAddress>() {
                    public String getId() {
                        return "fm.liveswitch.IceTransport.processIncomingApplicationData";
                    }

                    public void invoke(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
                        IceTransport.this.processIncomingApplicationData(dataBuffer, iceCandidate, transportAddress);
                    }
                });
                iceGatherer.removeOnLocalCandidate(new IActionDelegate2<IceGatherer, IceCandidate>() {
                    public String getId() {
                        return "fm.liveswitch.IceTransport.processLocalCandidate";
                    }

                    public void invoke(IceGatherer iceGatherer, IceCandidate iceCandidate) {
                        IceTransport.this.processLocalCandidate(iceGatherer, iceCandidate);
                    }
                });
                this.__gatherer = null;
            }
            this.__remoteParameters = null;
            return true;
        }
    }

    private void stopNonActivePair(final IceCandidatePair iceCandidatePair) {
        if (iceCandidatePair.equals(getActiveCandidatePair())) {
            return;
        }
        if (getActiveCandidatePair() == null || getActiveCandidatePair().getPriority() > iceCandidatePair.getPriority()) {
            iceCandidatePair.stop();
        } else if (iceCandidatePair.getStopCandidatePair() == null && Global.equals(iceCandidatePair.getState(), CandidatePairState.Succeeded)) {
            ScheduledItem scheduledItem = new ScheduledItem(new IAction1<ScheduledItem>() {
                public void invoke(ScheduledItem scheduledItem) {
                    iceCandidatePair.stop();
                }
            }, 5000, ScheduledItem.getUnset(), ScheduledItem.getUnset(), 1);
            iceCandidatePair.setStopCandidatePair(scheduledItem);
            Scheduler scheduler = this.__scheduler;
            if (scheduler != null) {
                scheduler.add(scheduledItem);
            }
        }
    }

    private void stopNonActivePairs() {
        if (Global.equals(getOptions().getKeepAlivePolicy(), IceKeepAlivePolicy.ActiveOnly)) {
            synchronized (this.__lock) {
                for (IceCandidatePair stopNonActivePair : this.__validList.getCandidatePairs()) {
                    stopNonActivePair(stopNonActivePair);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void transitionToFailedStateOnDeadStreamTimeout(ScheduledItem scheduledItem) {
        Scheduler scheduler = this.__scheduler;
        if (scheduler != null) {
            scheduler.remove(scheduledItem);
        }
        synchronized (this.__lock) {
            if (Global.equals(getState(), IceTransportState.Disconnected)) {
                setError(new Error(ErrorCode.ConnectionDeadStream, new Exception("Dead stream detected.")));
                setState(IceTransportState.Failed);
            }
        }
    }

    private void updateActivePair() {
        processNominatedPair();
    }

    public void updateInfo(TransportInfo transportInfo) {
        IceCandidate[] localCandidates;
        IceCandidate[] remoteCandidates;
        IceCandidatePair[] iceCandidatePairArr;
        IceCandidatePair activeCandidatePair;
        TransportReport transportReport = new TransportReport();
        transportReport.setBytesSent(new NullableLong(this._bytesSent.getValue()));
        transportReport.setBytesReceived(new NullableLong(this._bytesReceived.getValue()));
        transportInfo.setReport(transportReport);
        synchronized (this.__lock) {
            localCandidates = getLocalCandidates();
            remoteCandidates = getRemoteCandidates();
            ArrayList arrayList = new ArrayList();
            for (IceCandidatePair add : HashMapExtensions.getValues(this.__candidatePairCache)) {
                arrayList.add(add);
            }
            iceCandidatePairArr = (IceCandidatePair[]) arrayList.toArray(new IceCandidatePair[0]);
            activeCandidatePair = getActiveCandidatePair();
        }
        ArrayList arrayList2 = new ArrayList();
        for (IceCandidate info : localCandidates) {
            arrayList2.add(info.getInfo());
        }
        ArrayList arrayList3 = new ArrayList();
        for (IceCandidate info2 : remoteCandidates) {
            arrayList3.add(info2.getInfo());
        }
        ArrayList arrayList4 = new ArrayList();
        for (IceCandidatePair info3 : iceCandidatePairArr) {
            arrayList4.add(info3.getInfo(getId()));
        }
        transportInfo.setLocalCandidates((CandidateInfo[]) arrayList2.toArray(new CandidateInfo[0]));
        transportInfo.setRemoteCandidates((CandidateInfo[]) arrayList3.toArray(new CandidateInfo[0]));
        transportInfo.setCandidatePairs((CandidatePairInfo[]) arrayList4.toArray(new CandidatePairInfo[0]));
        if (activeCandidatePair != null) {
            transportInfo.setActiveCandidatePairId(activeCandidatePair.getId());
        }
    }

    /* access modifiers changed from: private */
    public void updateRole(IceRole iceRole) {
        setRole(iceRole);
    }

    public void updateStats(TransportStats transportStats) {
        IceCandidate[] localCandidates;
        IceCandidate[] remoteCandidates;
        IceCandidatePair[] iceCandidatePairArr;
        IceCandidatePair activeCandidatePair;
        transportStats.setBytesSent(this._bytesSent.getValue());
        transportStats.setBytesReceived(this._bytesReceived.getValue());
        synchronized (this.__lock) {
            localCandidates = getLocalCandidates();
            remoteCandidates = getRemoteCandidates();
            ArrayList arrayList = new ArrayList();
            for (IceCandidatePair add : HashMapExtensions.getValues(this.__candidatePairCache)) {
                arrayList.add(add);
            }
            iceCandidatePairArr = (IceCandidatePair[]) arrayList.toArray(new IceCandidatePair[0]);
            activeCandidatePair = getActiveCandidatePair();
        }
        ArrayList arrayList2 = new ArrayList();
        for (IceCandidate stats : localCandidates) {
            arrayList2.add(stats.getStats());
        }
        ArrayList arrayList3 = new ArrayList();
        for (IceCandidate stats2 : remoteCandidates) {
            arrayList3.add(stats2.getStats());
        }
        ArrayList arrayList4 = new ArrayList();
        for (IceCandidatePair stats3 : iceCandidatePairArr) {
            arrayList4.add(stats3.getStats(getId()));
        }
        transportStats.setLocalCandidates((CandidateStats[]) arrayList2.toArray(new CandidateStats[0]));
        transportStats.setRemoteCandidates((CandidateStats[]) arrayList3.toArray(new CandidateStats[0]));
        transportStats.setCandidatePairs((CandidatePairStats[]) arrayList4.toArray(new CandidatePairStats[0]));
        if (activeCandidatePair != null) {
            transportStats.setActiveCandidatePair(activeCandidatePair.getStats(getId()));
        }
    }

    /* access modifiers changed from: package-private */
    public void verifyConnectivity() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.__lock) {
            ArrayListExtensions.addRange(arrayList, HashMapExtensions.getValues(this.__candidatePairCache));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((IceCandidatePair) it.next()).verifyConnectivity();
        }
    }

    private BindingResponse verifyIceRole(Message message) {
        long j;
        ControlledAttribute iceControlled = message.getIceControlled();
        ControllingAttribute iceControlling = message.getIceControlling();
        boolean z = true;
        boolean z2 = false;
        if (iceControlled == null && iceControlling == null) {
            if (__log.getIsWarnEnabled()) {
                __log.warn("Incoming STUN request contains neither ICE-CONTROLLING not ICE-CONTROLLED attributes. There may exist an ICE-Role conflict, but it is not detectable.");
            }
            j = -1;
        } else if (iceControlled != null) {
            j = iceControlled.getValue();
            z = false;
            z2 = true;
        } else {
            j = iceControlling.getValue();
            z = false;
        }
        if (z || !Global.equals(getRole(), IceRole.Controlling) || z2) {
            if (z || !Global.equals(getRole(), IceRole.Controlled) || !z2) {
                return null;
            }
            if (this.__tieBreaker >= j) {
                if (__log.getIsWarnEnabled()) {
                    __log.warn("Incoming STUN request contains ICE-CONTROLLED attribute, but local IceTransport also assumes the controlled role. Won the tie-breaker. Will switch to the controlling role.");
                }
                setRole(IceRole.Controlling);
                return null;
            }
            if (__log.getIsWarnEnabled()) {
                __log.warn("Incoming STUN request contains ICE-CONTROLLED attribute, but local IceTransport also assumes the controllED role. Lost the tie-breaker. Will send an error message back and retain the controlled role.");
            }
            return createIceRoleConflictResponse(message.getTransactionId());
        } else if (this.__tieBreaker >= j) {
            if (__log.getIsWarnEnabled()) {
                __log.warn("Incoming STUN request contains ICE-CONTROLLING attribute, but local IceTransport also assumes the controlling role. Won the tie-breaker. Will send an error message back and retain the controlling role.");
            }
            return createIceRoleConflictResponse(message.getTransactionId());
        } else {
            if (__log.getIsWarnEnabled()) {
                __log.warn("Incoming STUN request contains ICE-CONTROLLING attribute, but local IceTransport also assumes the controlling role. Lost the tie-breaker. Will switch to the controlled role.");
            }
            setRole(IceRole.Controlled);
            return null;
        }
    }
}
