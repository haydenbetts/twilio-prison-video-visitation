package fm.liveswitch;

import fm.liveswitch.stun.MessageIntegrityAttribute;
import fm.liveswitch.stun.NonceAttribute;
import fm.liveswitch.stun.RealmAttribute;
import fm.liveswitch.stun.StaleNonceError;
import fm.liveswitch.stun.UsernameAttribute;
import fm.liveswitch.stun.Utility;
import fm.liveswitch.stun.turn.CreatePermissionRequest;
import fm.liveswitch.stun.turn.LifetimeAttribute;
import fm.liveswitch.stun.turn.RefreshRequest;
import fm.liveswitch.stun.turn.RefreshResponse;
import fm.liveswitch.stun.turn.XorPeerAddressAttribute;

class IceLocalRelayedCandidate extends IceCandidate {
    private String __deallocateItemId;
    private long __expires;
    private int __hashCode;
    private String __refreshItemId;
    private IceLocalRelayedCandidateState __relayState;
    private int __smoothedRelayServerRoundTripTime = -1;
    private int __stunRequestTimeout = ScheduledItem.getUnset();
    private IceTransactionManager __transactionManager;
    private String __turnPassword;
    private String __turnRealm;
    private TransportAddress __turnServer;
    private String __turnUsername;
    private int _lastRelayServerRoundTripTime;
    private String _nonce;
    private IAction1<IceLocalRelayedCandidateState> _onRelayStateChanged;

    public void deallocate() {
        setRelayState(IceLocalRelayedCandidateState.Closing);
        RefreshRequest refreshRequest = new RefreshRequest();
        refreshRequest.setLifetime(new LifetimeAttribute(0));
        refreshRequest.setUsername(new UsernameAttribute(this.__turnUsername));
        refreshRequest.setRealm(new RealmAttribute(this.__turnRealm));
        refreshRequest.setNonce(new NonceAttribute(getNonce()));
        refreshRequest.setMessageIntegrity(new MessageIntegrityAttribute(Utility.createLongTermKey(this.__turnUsername, this.__turnRealm, this.__turnPassword)));
        IceSendMessageArgs iceSendMessageArgs = new IceSendMessageArgs(refreshRequest, getTurnServer());
        iceSendMessageArgs.setRelayPassword(this.__turnPassword);
        iceSendMessageArgs.setOnResponse(new IActionDelegate1<IceSendRequestSuccessArgs>() {
            public String getId() {
                return "fm.liveswitch.IceLocalRelayedCandidate.processDeallocateResponse";
            }

            public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                IceLocalRelayedCandidate.this.processDeallocateResponse(iceSendRequestSuccessArgs);
            }
        });
        iceSendMessageArgs.setOnFailure(new IActionDelegate1<IceSendRequestFailureArgs>() {
            public String getId() {
                return "fm.liveswitch.IceLocalRelayedCandidate.processDeallocateExecutionFailed";
            }

            public void invoke(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
                IceLocalRelayedCandidate.this.processDeallocateExecutionFailed(iceSendRequestFailureArgs);
            }
        });
        ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
            public String getId() {
                return "fm.liveswitch.IceCandidate.sendStunMessage";
            }

            public void invoke(ScheduledItem scheduledItem) {
                IceLocalRelayedCandidate.this.sendStunMessage(scheduledItem);
            }
        }, 0, 100, this.__stunRequestTimeout, ScheduledItem.getUnset());
        scheduledItem.setState(iceSendMessageArgs);
        scheduledItem.setIntervalBackoffMultiplier(2.0f);
        scheduledItem.setTimeoutCallback(new IActionDelegate1<ScheduledItem>() {
            public String getId() {
                return "fm.liveswitch.IceLocalRelayedCandidate.processDeallocateTimedout";
            }

            public void invoke(ScheduledItem scheduledItem) {
                IceLocalRelayedCandidate.this.processDeallocateTimedout(scheduledItem);
            }
        });
        this.__deallocateItemId = this.__transactionManager.addTransaction(scheduledItem, this);
    }

    private ScheduledItem generateAllocationRefreshScheduledItem() {
        RefreshRequest refreshRequest = new RefreshRequest();
        refreshRequest.setUsername(new UsernameAttribute(this.__turnUsername));
        refreshRequest.setRealm(new RealmAttribute(this.__turnRealm));
        refreshRequest.setNonce(new NonceAttribute(getNonce()));
        refreshRequest.setMessageIntegrity(new MessageIntegrityAttribute(Utility.createLongTermKey(this.__turnUsername, this.__turnRealm, this.__turnPassword)));
        IceSendMessageArgs iceSendMessageArgs = new IceSendMessageArgs(refreshRequest, getTurnServer());
        iceSendMessageArgs.setRelayPassword(this.__turnPassword);
        iceSendMessageArgs.setOnResponse(new IActionDelegate1<IceSendRequestSuccessArgs>() {
            public String getId() {
                return "fm.liveswitch.IceLocalRelayedCandidate.processRefreshResponse";
            }

            public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                IceLocalRelayedCandidate.this.processRefreshResponse(iceSendRequestSuccessArgs);
            }
        });
        iceSendMessageArgs.setOnFailure(new IActionDelegate1<IceSendRequestFailureArgs>() {
            public String getId() {
                return "fm.liveswitch.IceLocalRelayedCandidate.processRefreshExecutionFailed";
            }

            public void invoke(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
                IceLocalRelayedCandidate.this.processRefreshExecutionFailed(iceSendRequestFailureArgs);
            }
        });
        ScheduledItem scheduledItem = new ScheduledItem(new IActionDelegate1<ScheduledItem>() {
            public String getId() {
                return "fm.liveswitch.IceCandidate.sendStunAndInsertAttemptMessage";
            }

            public void invoke(ScheduledItem scheduledItem) {
                IceLocalRelayedCandidate.this.sendStunAndInsertAttemptMessage(scheduledItem);
            }
        }, MathAssistant.max(0, (int) ((getExpires() - Scheduler.getCurrentTime()) - 60000)), 100, 15000, ScheduledItem.getUnset());
        scheduledItem.setState(iceSendMessageArgs);
        scheduledItem.setIntervalBackoffMultiplier(2.0f);
        scheduledItem.setTimeoutCallback(new IActionDelegate1<ScheduledItem>() {
            public String getId() {
                return "fm.liveswitch.IceLocalRelayedCandidate.processRefreshTimedout";
            }

            public void invoke(ScheduledItem scheduledItem) {
                IceLocalRelayedCandidate.this.processRefreshTimedout(scheduledItem);
            }
        });
        return scheduledItem;
    }

    /* access modifiers changed from: package-private */
    public CreatePermissionRequest generatePermissionRequest(String str) {
        CreatePermissionRequest createPermissionRequest = new CreatePermissionRequest();
        createPermissionRequest.setXorPeerAddress(new XorPeerAddressAttribute(str, 0, createPermissionRequest.getTransactionId()));
        createPermissionRequest.setUsername(new UsernameAttribute(this.__turnUsername));
        createPermissionRequest.setRealm(new RealmAttribute(this.__turnRealm));
        createPermissionRequest.setNonce(new NonceAttribute(getNonce()));
        createPermissionRequest.setMessageIntegrity(new MessageIntegrityAttribute(Utility.createLongTermKey(this.__turnUsername, this.__turnRealm, this.__turnPassword)));
        return createPermissionRequest;
    }

    private boolean get_RefreshScheduled() {
        return !StringExtensions.isNullOrEmpty(this.__refreshItemId);
    }

    private long getExpires() {
        return this.__expires;
    }

    public int getLastRelayServerRoundTripTime() {
        return this._lastRelayServerRoundTripTime;
    }

    /* access modifiers changed from: package-private */
    public String getNonce() {
        return this._nonce;
    }

    public IAction1<IceLocalRelayedCandidateState> getOnRelayStateChanged() {
        return this._onRelayStateChanged;
    }

    public boolean getOpen() {
        return Global.equals(this.__relayState, IceLocalRelayedCandidateState.Allocated) || Global.equals(this.__relayState, IceLocalRelayedCandidateState.Refreshing);
    }

    public IceLocalRelayedCandidateState getRelayState() {
        return this.__relayState;
    }

    public int getSmoothedRelayServerRoundTripTime() {
        return this.__smoothedRelayServerRoundTripTime;
    }

    public TransportAddress getTurnServer() {
        return this.__turnServer;
    }

    public int hashCode() {
        return this.__hashCode;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IceLocalRelayedCandidate(Object obj, String str, ProtocolType protocolType, String str2, int i, String str3, int i2, long j, String str4, String str5, String str6, String str7, TransportAddress transportAddress, IceTransactionManager iceTransactionManager, int i3) {
        super(obj, str, protocolType, str2, i, CandidateType.Relayed, str3, i2);
        this.__expires = Scheduler.getCurrentTime() + (1000 * j);
        if (j > 0) {
            setRelayState(IceLocalRelayedCandidateState.Allocated);
        } else {
            setRelayState(IceLocalRelayedCandidateState.Closed);
        }
        this.__turnServer = transportAddress;
        this.__turnUsername = str4;
        this.__turnPassword = str5;
        setNonce(str6);
        this.__turnRealm = str7;
        this.__transactionManager = iceTransactionManager;
        this.__hashCode = Guid.newGuid().hashCode();
        this.__stunRequestTimeout = i3;
    }

    /* access modifiers changed from: private */
    public void processDeallocateExecutionFailed(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
        if (Log.getIsWarnEnabled()) {
            Log.warn(StringExtensions.format("Deallocate timed out for {0}.", (Object) toString()));
        }
        if (this.__transactionManager != null && !StringExtensions.isNullOrEmpty(this.__deallocateItemId)) {
            this.__transactionManager.remove(this.__deallocateItemId, (Object) this);
            this.__deallocateItemId = null;
        }
        setRelayState(IceLocalRelayedCandidateState.Closed);
    }

    /* access modifiers changed from: private */
    public void processDeallocateResponse(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
        if (iceSendRequestSuccessArgs.getResponse() instanceof RefreshResponse) {
            ScheduledItem item = iceSendRequestSuccessArgs.getItem();
            int invocationLifetimeLimit = (int) ((((long) item.getInvocationLifetimeLimit()) + item.getOriginalInvocationTime()) - Scheduler.getCurrentTime());
            Error validateResponse = IceDatagramSocketManager.validateResponse((IceSendMessageArgs) item.getState(), iceSendRequestSuccessArgs.getResponse(), iceSendRequestSuccessArgs.getRemoteAddress());
            if (validateResponse == null) {
                this.__transactionManager.remove(item, (Object) this);
                Log.debug(StringExtensions.format("Local relayed candidate {0} was successfully deallocated on the relay server.", (Object) toString()));
                this.__deallocateItemId = null;
                setRelayState(IceLocalRelayedCandidateState.Closed);
                return;
            }
            if (Global.equals(validateResponse.getCode(), ErrorCode.StunStaleNonce)) {
                setNonce(((StaleNonceError) validateResponse).getNonce().getValue());
                ((IceSendMessageArgs) item.getState()).getMessage().setNonce(new NonceAttribute(getNonce()));
            } else if (!Global.equals(validateResponse.getCode(), ErrorCode.StunServerError)) {
                if (Global.equals(validateResponse.getCode(), ErrorCode.StunTurnAllocationMismatch)) {
                    this.__transactionManager.remove(item, (Object) this);
                    this.__deallocateItemId = null;
                    setRelayState(IceLocalRelayedCandidateState.Closed);
                    return;
                } else if (validateResponse != null) {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("Deallocate failed for {0}. {1}", toString(), validateResponse.getDescription()));
                    }
                    this.__deallocateItemId = null;
                    this.__transactionManager.remove(item, (Object) this);
                    setRelayState(IceLocalRelayedCandidateState.Closed);
                    return;
                }
            }
            if (invocationLifetimeLimit <= 0) {
                if (Log.getIsWarnEnabled()) {
                    Log.warn(StringExtensions.format("Deallocate failed for {0} after several attempts. {1}", toString(), validateResponse.getDescription()));
                }
                synchronized (super.getLock()) {
                    setRelayState(IceLocalRelayedCandidateState.Closed);
                }
                return;
            }
            this.__transactionManager.remove(item, (Object) this);
            item.setInvocationLifetimeLimit(invocationLifetimeLimit);
            item.setDelay(20);
            this.__transactionManager.addTransaction(item, this);
        }
    }

    /* access modifiers changed from: private */
    public void processDeallocateTimedout(ScheduledItem scheduledItem) {
        if (Log.getIsWarnEnabled()) {
            Log.warn(StringExtensions.format("Deallocate timed out for {0} after several attempts.", (Object) toString()));
        }
        this.__transactionManager.remove(scheduledItem, (Object) this);
        setRelayState(IceLocalRelayedCandidateState.Closed);
    }

    /* access modifiers changed from: package-private */
    public void processDSMFailure() {
        synchronized (super.getLock()) {
            IceTransactionManager iceTransactionManager = this.__transactionManager;
            if (iceTransactionManager != null) {
                iceTransactionManager.remove(this);
                this.__refreshItemId = null;
                this.__deallocateItemId = null;
            }
            setRelayState(IceLocalRelayedCandidateState.Closed);
        }
    }

    /* access modifiers changed from: private */
    public void processRefreshExecutionFailed(IceSendRequestFailureArgs iceSendRequestFailureArgs) {
        Error error = iceSendRequestFailureArgs.getError();
        Log.error(StringExtensions.format(StringExtensions.format("Refresh request failed for {0}. {1}", toString(), error != null ? error.getDescription() : ""), new Object[0]));
        processRefreshFailure(error);
    }

    private void processRefreshFailure(Error error) {
        synchronized (super.getLock()) {
            if (get_RefreshScheduled()) {
                IceTransactionManager iceTransactionManager = this.__transactionManager;
                if (iceTransactionManager != null) {
                    iceTransactionManager.remove(this.__refreshItemId, (Object) this);
                    this.__refreshItemId = null;
                }
                super.setError(error);
                setRelayState(IceLocalRelayedCandidateState.Failed);
            }
        }
    }

    /* access modifiers changed from: private */
    public void processRefreshResponse(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
        if (iceSendRequestSuccessArgs.getResponse() instanceof RefreshResponse) {
            ScheduledItem item = iceSendRequestSuccessArgs.getItem();
            long currentTime = Scheduler.getCurrentTime();
            int invocationLifetimeLimit = (int) ((((long) item.getInvocationLifetimeLimit()) + item.getOriginalInvocationTime()) - currentTime);
            Error validateResponse = IceDatagramSocketManager.validateResponse((IceSendMessageArgs) item.getState(), iceSendRequestSuccessArgs.getResponse(), iceSendRequestSuccessArgs.getRemoteAddress());
            int calculateInstantaneousRtt = IceTransactionManager.calculateInstantaneousRtt(false, iceSendRequestSuccessArgs, true, currentTime);
            if (calculateInstantaneousRtt > 0) {
                setLastRelayServerRoundTripTime(IceTransactionManager.calculateInstantaneousRtt(false, iceSendRequestSuccessArgs, true, currentTime));
                setSmoothedRelayServerRoundTripTime(IceTransactionManager.calculateSmoothedRtt(calculateInstantaneousRtt, getSmoothedRelayServerRoundTripTime()));
            }
            if (validateResponse == null) {
                LifetimeAttribute lifetime = iceSendRequestSuccessArgs.getResponse().getLifetime();
                if (lifetime != null && get_RefreshScheduled()) {
                    if (Log.getIsDebugEnabled()) {
                        Log.debug(StringExtensions.format("Allocation extended by {0} seconds for {1}.", LongExtensions.toString(Long.valueOf(lifetime.getLifetime())), toString()));
                    }
                    setExpires(currentTime + (lifetime.getLifetime() * 1000));
                }
                this.__transactionManager.remove(item, (Object) this);
                return;
            }
            if (Global.equals(validateResponse.getCode(), ErrorCode.StunStaleNonce)) {
                setNonce(((StaleNonceError) validateResponse).getNonce().getValue());
            } else if (!Global.equals(validateResponse.getCode(), ErrorCode.StunServerError)) {
                String format = StringExtensions.format("Refresh request failed for {0}. {1}", toString(), validateResponse.getDescription());
                Error error = new Error(ErrorCode.IceRefreshError, new Exception(format));
                Log.error(StringExtensions.format(format, new Object[0]));
                processRefreshFailure(error);
                return;
            }
            if (invocationLifetimeLimit <= 0 || !get_RefreshScheduled()) {
                processRefreshTimedout(item);
                return;
            }
            this.__transactionManager.remove(item, (Object) this);
            ScheduledItem generateAllocationRefreshScheduledItem = generateAllocationRefreshScheduledItem();
            generateAllocationRefreshScheduledItem.setInvocationLifetimeLimit(invocationLifetimeLimit);
            generateAllocationRefreshScheduledItem.setDelay(20);
            synchronized (super.getLock()) {
                if (!Global.equals(getRelayState(), IceLocalRelayedCandidateState.Closed)) {
                    this.__refreshItemId = this.__transactionManager.addTransaction(generateAllocationRefreshScheduledItem, this);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void processRefreshTimedout(ScheduledItem scheduledItem) {
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
        this.__transactionManager.remove(scheduledItem, (Object) this);
        TransportAddress transportAddress = super.getTransportAddress();
        String format = StringExtensions.format("Refresh request request from {2} to {0}:{1} timed out.", iceSendMessageArgs.getAddress().getIPAddress(), IntegerExtensions.toString(Integer.valueOf(iceSendMessageArgs.getAddress().getPort())), transportAddress == null ? StringExtensions.empty : transportAddress.toString());
        Log.error(format);
        processRefreshFailure(new Error(ErrorCode.IceRefreshTimeout, new Exception(format)));
    }

    private void setExpires(long j) {
        if (j != this.__expires) {
            synchronized (super.getLock()) {
                this.__expires = j;
                if (!StringExtensions.isNullOrEmpty(this.__refreshItemId)) {
                    this.__transactionManager.remove(this.__refreshItemId, (Object) this);
                }
                this.__refreshItemId = this.__transactionManager.addTransaction(generateAllocationRefreshScheduledItem(), this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setLastRelayServerRoundTripTime(int i) {
        this._lastRelayServerRoundTripTime = i;
    }

    /* access modifiers changed from: package-private */
    public void setNonce(String str) {
        this._nonce = str;
    }

    public void setOnRelayStateChanged(IAction1<IceLocalRelayedCandidateState> iAction1) {
        this._onRelayStateChanged = iAction1;
    }

    private void setRelayState(IceLocalRelayedCandidateState iceLocalRelayedCandidateState) {
        synchronized (super.getLock()) {
            if (!Global.equals(iceLocalRelayedCandidateState, this.__relayState)) {
                this.__relayState = iceLocalRelayedCandidateState;
                IAction1<IceLocalRelayedCandidateState> onRelayStateChanged = getOnRelayStateChanged();
                if (onRelayStateChanged != null) {
                    onRelayStateChanged.invoke(this.__relayState);
                }
                if (!Global.equals(this.__relayState, IceLocalRelayedCandidateState.Closing)) {
                    if (!Global.equals(this.__relayState, IceLocalRelayedCandidateState.Closed)) {
                        if (Global.equals(this.__relayState, IceLocalRelayedCandidateState.Failed)) {
                            if (Global.equals(super.getState(), IceLocalCandidateState.Ready)) {
                                super.setState(IceLocalCandidateState.Failed);
                            }
                        } else if (!Global.equals(this.__relayState, IceLocalRelayedCandidateState.Refreshing)) {
                            Global.equals(this.__relayState, IceLocalRelayedCandidateState.Allocated);
                        }
                    }
                }
                if (Global.equals(super.getState(), IceLocalCandidateState.Ready)) {
                    super.setState(IceLocalCandidateState.Closed);
                }
            }
        }
    }

    public void setSmoothedRelayServerRoundTripTime(int i) {
        this.__smoothedRelayServerRoundTripTime = i;
    }

    public void startScheduleRefreshTransactions() {
        synchronized (super.getLock()) {
            if (!get_RefreshScheduled() || !getOpen()) {
                synchronized (super.getLock()) {
                    if (Global.equals(getRelayState(), IceLocalRelayedCandidateState.Allocated)) {
                        this.__refreshItemId = this.__transactionManager.addTransaction(generateAllocationRefreshScheduledItem(), this);
                    }
                }
            }
        }
    }

    public void stopRelayTransactions(boolean z) {
        synchronized (super.getLock()) {
            if (get_RefreshScheduled()) {
                if (this.__transactionManager != null && !StringExtensions.isNullOrEmpty(this.__refreshItemId)) {
                    this.__transactionManager.remove(this.__refreshItemId, (Object) this);
                    this.__refreshItemId = null;
                }
                if (z) {
                    deallocate();
                } else {
                    setRelayState(IceLocalRelayedCandidateState.Closing);
                    setRelayState(IceLocalRelayedCandidateState.Closed);
                }
            } else if (Global.equals(getRelayState(), IceLocalRelayedCandidateState.Allocated)) {
                setRelayState(IceLocalRelayedCandidateState.Closed);
            } else if (Global.equals(getRelayState(), IceLocalRelayedCandidateState.Refreshing)) {
                throw new RuntimeException(new Exception(StringExtensions.format("Inconsistent state {0} of the local relayed candidate when stop request received.", (Object) getRelayState().toString())));
            }
        }
    }
}
