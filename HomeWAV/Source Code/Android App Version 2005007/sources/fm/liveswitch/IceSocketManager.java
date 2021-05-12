package fm.liveswitch;

import fm.liveswitch.stun.AddressFamily;
import fm.liveswitch.stun.BindingRequest;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageIntegrityAttribute;
import fm.liveswitch.stun.NonceAttribute;
import fm.liveswitch.stun.RealmAttribute;
import fm.liveswitch.stun.UsernameAttribute;
import fm.liveswitch.stun.Utility;
import fm.liveswitch.stun.turn.AllocateRequest;
import fm.liveswitch.stun.turn.DataAttribute;
import fm.liveswitch.stun.turn.EvenPortAttribute;
import fm.liveswitch.stun.turn.RequestedAddressFamilyAttribute;
import fm.liveswitch.stun.turn.RequestedTransportAttribute;
import fm.liveswitch.stun.turn.SendIndication;
import fm.liveswitch.stun.turn.XorPeerAddressAttribute;
import java.util.HashMap;

abstract class IceSocketManager {
    private int __hashCode;
    private HashMap<String, Integer> __numberOfStunRequests = new HashMap<>();
    public IceGatheringState __state = IceGatheringState.New;
    private Error _error;
    private IceGatherOptions _gatherOptions;
    private String _id;
    public HashMap<String, IceCandidate> _localCandidates = new HashMap<>();
    private int _localPreference;
    HashMap<String, IceLocalRelayedCandidate> _localRelayedCandidates = new HashMap<>();
    private Object _lock;
    private IAction1<IceSocketManager> _onAllocationMismatchException;
    private IAction3<DataBuffer, IceCandidate, TransportAddress> _onIncomingData;
    private IAction1<IceCandidate> _onLocalCandidate;
    private IAction1<IceSocketManager> _onStateChange;
    private IAction3<Message, IceCandidate, TransportAddress> _onStunRequest;
    private ProtocolType _protocol;
    private IceServer _server;
    IceTransactionManager _transactionManager;

    /* access modifiers changed from: protected */
    public abstract void closeSocket();

    /* access modifiers changed from: package-private */
    public abstract long getAdapterSpeed();

    /* access modifiers changed from: package-private */
    public abstract String getLocalIpAddress();

    /* access modifiers changed from: package-private */
    public abstract int getLocalPort();

    /* access modifiers changed from: protected */
    public abstract Error sendApplicationData(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress);

    public abstract boolean start(IceGatherOptions iceGatherOptions);

    private void finalise() {
        synchronized (getLock()) {
            IceTransactionManager iceTransactionManager = this._transactionManager;
            if (iceTransactionManager != null) {
                iceTransactionManager.remove(this);
            }
            this._localCandidates.clear();
            this._localRelayedCandidates.clear();
            if (Global.equals(getState(), IceGatheringState.Closed)) {
                Log.debug(StringExtensions.format("Socket manager {0} is already closed while finalising. This is OK.", (Object) getId()));
            } else {
                try {
                    closeSocket();
                    setState(IceGatheringState.Closed);
                    Log.debug(StringExtensions.format("Closed socket manager {0}.", (Object) getId()));
                } catch (Exception e) {
                    Log.debug(StringExtensions.format("Closed socket manager {0}. {1}", getId(), e.toString()));
                }
            }
        }
    }

    public Error getError() {
        return this._error;
    }

    /* access modifiers changed from: protected */
    public IceGatherOptions getGatherOptions() {
        return this._gatherOptions;
    }

    public String getId() {
        return this._id;
    }

    /* access modifiers changed from: protected */
    public boolean getIsGathering() {
        return this._transactionManager.hasActiveTransactions(this);
    }

    /* access modifiers changed from: package-private */
    public boolean getIsTerminatingOrTerminated() {
        return Global.equals(getState(), IceGatheringState.Closing) || Global.equals(getState(), IceGatheringState.Closed) || Global.equals(getState(), IceGatheringState.Failed);
    }

    public int getLocalPreference() {
        return this._localPreference;
    }

    /* access modifiers changed from: protected */
    public Object getLock() {
        return this._lock;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Integer> getNumberOfStunRequests() {
        return this.__numberOfStunRequests;
    }

    public IAction1<IceSocketManager> getOnAllocationMismatchException() {
        return this._onAllocationMismatchException;
    }

    /* access modifiers changed from: package-private */
    public IAction3<DataBuffer, IceCandidate, TransportAddress> getOnIncomingData() {
        return this._onIncomingData;
    }

    public IAction1<IceCandidate> getOnLocalCandidate() {
        return this._onLocalCandidate;
    }

    public IAction1<IceSocketManager> getOnStateChange() {
        return this._onStateChange;
    }

    /* access modifiers changed from: package-private */
    public IAction3<Message, IceCandidate, TransportAddress> getOnStunRequest() {
        return this._onStunRequest;
    }

    public ProtocolType getProtocol() {
        return this._protocol;
    }

    /* access modifiers changed from: package-private */
    public IceServer getServer() {
        return this._server;
    }

    public IceGatheringState getState() {
        return this.__state;
    }

    public int hashCode() {
        return this.__hashCode;
    }

    protected IceSocketManager(Object obj, IceTransactionManager iceTransactionManager) {
        setId(Utility.generateId());
        setLock(obj);
        if (iceTransactionManager != null) {
            this._transactionManager = iceTransactionManager;
            this.__hashCode = Guid.newGuid().hashCode();
            return;
        }
        throw new RuntimeException(new Exception("Transaction manager cannot be null."));
    }

    /* access modifiers changed from: protected */
    public void processRelayedCandidateStateChanged(IceLocalRelayedCandidateState iceLocalRelayedCandidateState) {
        synchronized (getLock()) {
            if (Global.equals(getState(), IceGatheringState.Closing) && (Global.equals(iceLocalRelayedCandidateState, IceLocalRelayedCandidateState.Closed) || Global.equals(iceLocalRelayedCandidateState, IceLocalRelayedCandidateState.Failed))) {
                verifyAllRelayedCandidatesStopped();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0053, code lost:
        r0 = getOnLocalCandidate();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        r0.invoke(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void raiseLocalCandidate(fm.liveswitch.IceCandidate r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.toString()
            java.lang.Object r1 = r4.getLock()
            monitor-enter(r1)
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidate> r2 = r4._localCandidates     // Catch:{ all -> 0x005d }
            boolean r0 = r2.containsKey(r0)     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0027
            fm.liveswitch.CandidateType r0 = r5.getType()     // Catch:{ all -> 0x005d }
            fm.liveswitch.CandidateType r2 = fm.liveswitch.CandidateType.Relayed     // Catch:{ all -> 0x005d }
            boolean r0 = fm.liveswitch.Global.equals(r0, r2)     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0025
            fm.liveswitch.IceLocalRelayedCandidate r5 = (fm.liveswitch.IceLocalRelayedCandidate) r5     // Catch:{ all -> 0x005d }
            fm.liveswitch.IceLocalRelayedCandidate r5 = (fm.liveswitch.IceLocalRelayedCandidate) r5     // Catch:{ all -> 0x005d }
            r0 = 0
            r5.stopRelayTransactions(r0)     // Catch:{ all -> 0x005d }
        L_0x0025:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            return
        L_0x0027:
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidate> r0 = r4._localCandidates     // Catch:{ all -> 0x005d }
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)     // Catch:{ all -> 0x005d }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x005d }
            fm.liveswitch.HashMapExtensions.set(r0, r2, r5)     // Catch:{ all -> 0x005d }
            fm.liveswitch.CandidateType r0 = r5.getType()     // Catch:{ all -> 0x005d }
            fm.liveswitch.CandidateType r2 = fm.liveswitch.CandidateType.Relayed     // Catch:{ all -> 0x005d }
            boolean r0 = fm.liveswitch.Global.equals(r0, r2)     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0052
            r0 = r5
            fm.liveswitch.IceLocalRelayedCandidate r0 = (fm.liveswitch.IceLocalRelayedCandidate) r0     // Catch:{ all -> 0x005d }
            fm.liveswitch.IceLocalRelayedCandidate r0 = (fm.liveswitch.IceLocalRelayedCandidate) r0     // Catch:{ all -> 0x005d }
            fm.liveswitch.TransportAddress r2 = r0.getTurnServer()     // Catch:{ all -> 0x005d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x005d }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceLocalRelayedCandidate> r3 = r4._localRelayedCandidates     // Catch:{ all -> 0x005d }
            fm.liveswitch.HashMapExtensions.add(r3, r2, r0)     // Catch:{ all -> 0x005d }
        L_0x0052:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            fm.liveswitch.IAction1 r0 = r4.getOnLocalCandidate()
            if (r0 == 0) goto L_0x005c
            r0.invoke(r5)
        L_0x005c:
            return
        L_0x005d:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceSocketManager.raiseLocalCandidate(fm.liveswitch.IceCandidate):void");
    }

    /* access modifiers changed from: package-private */
    public IceCandidate registerLocalPeerReflexiveCandidate(IceLocalReflexiveCandidate iceLocalReflexiveCandidate) {
        IceCandidate iceCandidate;
        synchronized (getLock()) {
            String iceLocalReflexiveCandidate2 = iceLocalReflexiveCandidate.toString();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this._localCandidates, iceLocalReflexiveCandidate2, holder);
            iceCandidate = (IceCandidate) holder.getValue();
            if (!tryGetValue) {
                HashMapExtensions.set(HashMapExtensions.getItem(this._localCandidates), iceLocalReflexiveCandidate2, iceLocalReflexiveCandidate);
            }
        }
        if (iceCandidate != null) {
            return iceCandidate;
        }
        IAction1<IceCandidate> onLocalCandidate = getOnLocalCandidate();
        if (onLocalCandidate != null) {
            onLocalCandidate.invoke(iceLocalReflexiveCandidate);
        }
        return iceLocalReflexiveCandidate;
    }

    /* access modifiers changed from: protected */
    public void removeTransaction(ScheduledItem scheduledItem) {
        this._transactionManager.remove(scheduledItem, (Object) this);
        synchronized (getLock()) {
            if (!getIsGathering() && Global.equals(getState(), IceGatheringState.Gathering)) {
                setState(IceGatheringState.Complete);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serverAllocate(String str, String str2, String str3, int i, boolean z, String str4, String str5, IAction1<ScheduledItem> iAction1, IAction1<IceSendRequestSuccessArgs> iAction12, IAction1<ScheduledItem> iAction13) {
        boolean z2;
        String str6 = str;
        String str7 = str2;
        String str8 = str3;
        String str9 = str5;
        if (getNumberOfStunRequests().containsKey(str3)) {
            HashMapExtensions.set(HashMapExtensions.getItem(getNumberOfStunRequests()), str3, Integer.valueOf(HashMapExtensions.getItem(getNumberOfStunRequests()).get(str3).intValue() + 1));
        } else {
            HashMapExtensions.set(HashMapExtensions.getItem(getNumberOfStunRequests()), str3, 1);
        }
        AllocateRequest allocateRequest = new AllocateRequest();
        allocateRequest.setRequestedTransport(new RequestedTransportAttribute(RequestedTransportAttribute.getUdpProtocol()));
        boolean z3 = false;
        allocateRequest.setEvenPort(new EvenPortAttribute(false));
        allocateRequest.setUsername(new UsernameAttribute(str));
        if (!StringExtensions.isNullOrEmpty(str5)) {
            allocateRequest.setRealm(new RealmAttribute(str9));
            z2 = true;
        } else {
            z2 = false;
        }
        if (!StringExtensions.isNullOrEmpty(str4)) {
            allocateRequest.setNonce(new NonceAttribute(str4));
            z3 = true;
        }
        if (z2 && z3) {
            allocateRequest.setMessageIntegrity(new MessageIntegrityAttribute(Utility.createLongTermKey(str, str9, str2)));
        }
        if (Global.equals(LocalNetwork.getAddressType(str3), AddressType.IPv6)) {
            allocateRequest.setRequestedAddressFamily(new RequestedAddressFamilyAttribute(AddressFamily.getIPv6()));
        }
        int i2 = i;
        IceSendMessageArgs iceSendMessageArgs = new IceSendMessageArgs(allocateRequest, new TransportAddress(str3, i));
        iceSendMessageArgs.setRelayPassword(str2);
        boolean z4 = z;
        iceSendMessageArgs.setRaiseServerReflexiveCandidates(z);
        iceSendMessageArgs.setOnResponse(iAction12);
        ScheduledItem scheduledItem = new ScheduledItem(iAction1, 0, 100, getGatherOptions().getStunRequestTimeout(), ScheduledItem.getUnset());
        scheduledItem.setState(iceSendMessageArgs);
        scheduledItem.setIntervalBackoffMultiplier(2.0f);
        scheduledItem.setTimeoutCallback(iAction13);
        scheduledItem.setRecordDetailedInvocationTimes(true);
        this._transactionManager.addTransaction(scheduledItem, this);
    }

    /* access modifiers changed from: protected */
    public void serverBind(TransportAddress transportAddress, String str, IAction1<ScheduledItem> iAction1, IAction1<IceSendRequestSuccessArgs> iAction12, IAction1<ScheduledItem> iAction13) {
        String iPAddress = transportAddress.getIPAddress();
        if (!getNumberOfStunRequests().containsKey(iPAddress)) {
            HashMapExtensions.set(HashMapExtensions.getItem(getNumberOfStunRequests()), iPAddress, 0);
        }
        HashMapExtensions.set(HashMapExtensions.getItem(getNumberOfStunRequests()), iPAddress, Integer.valueOf(HashMapExtensions.getItem(getNumberOfStunRequests()).get(iPAddress).intValue() + 1));
        BindingRequest bindingRequest = new BindingRequest();
        if (!StringExtensions.isNullOrEmpty(str)) {
            bindingRequest.setNonce(new NonceAttribute(str));
        }
        IceSendMessageArgs iceSendMessageArgs = new IceSendMessageArgs(bindingRequest, transportAddress);
        iceSendMessageArgs.setOnResponse(iAction12);
        ScheduledItem scheduledItem = new ScheduledItem(iAction1, 0, 100, getGatherOptions().getStunRequestTimeout(), ScheduledItem.getUnset());
        scheduledItem.setState(iceSendMessageArgs);
        scheduledItem.setIntervalBackoffMultiplier(2.0f);
        scheduledItem.setTimeoutCallback(iAction13);
        this._transactionManager.addTransaction(scheduledItem, this);
    }

    /* access modifiers changed from: protected */
    public void setError(Error error) {
        this._error = error;
    }

    /* access modifiers changed from: protected */
    public void setGatherOptions(IceGatherOptions iceGatherOptions) {
        this._gatherOptions = iceGatherOptions;
    }

    private void setId(String str) {
        this._id = str;
    }

    public void setLocalPreference(int i) {
        this._localPreference = i;
    }

    /* access modifiers changed from: protected */
    public void setLock(Object obj) {
        this._lock = obj;
    }

    /* access modifiers changed from: protected */
    public void setNumberOfStunRequests(HashMap<String, Integer> hashMap) {
        this.__numberOfStunRequests = hashMap;
    }

    public void setOnAllocationMismatchException(IAction1<IceSocketManager> iAction1) {
        this._onAllocationMismatchException = iAction1;
    }

    /* access modifiers changed from: package-private */
    public void setOnIncomingData(IAction3<DataBuffer, IceCandidate, TransportAddress> iAction3) {
        this._onIncomingData = iAction3;
    }

    public void setOnLocalCandidate(IAction1<IceCandidate> iAction1) {
        this._onLocalCandidate = iAction1;
    }

    public void setOnStateChange(IAction1<IceSocketManager> iAction1) {
        this._onStateChange = iAction1;
    }

    /* access modifiers changed from: package-private */
    public void setOnStunRequest(IAction3<Message, IceCandidate, TransportAddress> iAction3) {
        this._onStunRequest = iAction3;
    }

    public void setProtocol(ProtocolType protocolType) {
        this._protocol = protocolType;
    }

    /* access modifiers changed from: package-private */
    public void setServer(IceServer iceServer) {
        this._server = iceServer;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:11|12|13|14|(2:17|15)|35|18|(1:20)|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0032 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042 A[LOOP:0: B:15:0x003c->B:17:0x0042, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setState(fm.liveswitch.IceGatheringState r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.getLock()
            monitor-enter(r0)
            fm.liveswitch.IceGatheringState r1 = r4.__state     // Catch:{ all -> 0x008d }
            boolean r1 = fm.liveswitch.Global.equals(r1, r5)     // Catch:{ all -> 0x008d }
            if (r1 != 0) goto L_0x008b
            fm.liveswitch.IAction1 r1 = r4.getOnStateChange()     // Catch:{ all -> 0x008d }
            fm.liveswitch.IceGatheringState r2 = r4.__state     // Catch:{ all -> 0x008d }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Closed     // Catch:{ all -> 0x008d }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x008d }
            if (r2 != 0) goto L_0x008b
            fm.liveswitch.IceGatheringState r2 = r4.__state     // Catch:{ all -> 0x008d }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Failed     // Catch:{ all -> 0x008d }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x008d }
            if (r2 != 0) goto L_0x008b
            r4.__state = r5     // Catch:{ all -> 0x008d }
            fm.liveswitch.IceGatheringState r2 = fm.liveswitch.IceGatheringState.Failed     // Catch:{ all -> 0x008d }
            boolean r5 = fm.liveswitch.Global.equals(r5, r2)     // Catch:{ all -> 0x008d }
            if (r5 == 0) goto L_0x0062
            r4.closeSocket()     // Catch:{ Exception -> 0x0032 }
        L_0x0032:
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidate> r5 = r4._localCandidates     // Catch:{ all -> 0x008d }
            java.util.Collection r5 = fm.liveswitch.HashMapExtensions.getValues(r5)     // Catch:{ all -> 0x008d }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x008d }
        L_0x003c:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x0050
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x008d }
            fm.liveswitch.IceCandidate r2 = (fm.liveswitch.IceCandidate) r2     // Catch:{ all -> 0x008d }
            fm.liveswitch.Error r3 = r4.getError()     // Catch:{ all -> 0x008d }
            r2.notifyOfSocketError(r3)     // Catch:{ all -> 0x008d }
            goto L_0x003c
        L_0x0050:
            fm.liveswitch.IceTransactionManager r5 = r4._transactionManager     // Catch:{ all -> 0x008d }
            if (r5 == 0) goto L_0x0057
            r5.remove(r4)     // Catch:{ all -> 0x008d }
        L_0x0057:
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidate> r5 = r4._localCandidates     // Catch:{ all -> 0x008d }
            r5.clear()     // Catch:{ all -> 0x008d }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceLocalRelayedCandidate> r5 = r4._localRelayedCandidates     // Catch:{ all -> 0x008d }
            r5.clear()     // Catch:{ all -> 0x008d }
            goto L_0x0086
        L_0x0062:
            fm.liveswitch.IceGatheringState r5 = r4.__state     // Catch:{ all -> 0x008d }
            fm.liveswitch.IceGatheringState r2 = fm.liveswitch.IceGatheringState.Closing     // Catch:{ all -> 0x008d }
            boolean r5 = fm.liveswitch.Global.equals(r5, r2)     // Catch:{ all -> 0x008d }
            if (r5 == 0) goto L_0x0086
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidate> r5 = r4._localCandidates     // Catch:{ all -> 0x008d }
            java.util.Collection r5 = fm.liveswitch.HashMapExtensions.getValues(r5)     // Catch:{ all -> 0x008d }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x008d }
        L_0x0076:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x0086
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x008d }
            fm.liveswitch.IceCandidate r2 = (fm.liveswitch.IceCandidate) r2     // Catch:{ all -> 0x008d }
            r2.stop()     // Catch:{ all -> 0x008d }
            goto L_0x0076
        L_0x0086:
            if (r1 == 0) goto L_0x008b
            r1.invoke(r4)     // Catch:{ all -> 0x008d }
        L_0x008b:
            monitor-exit(r0)     // Catch:{ all -> 0x008d }
            return
        L_0x008d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceSocketManager.setState(fm.liveswitch.IceGatheringState):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00fc, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean stop() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.getLock()
            monitor-enter(r0)
            java.lang.String r1 = "Closing socket manager {0}."
            java.lang.String r2 = r6.getId()     // Catch:{ all -> 0x011e }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x011e }
            fm.liveswitch.Log.debug(r1)     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceGatheringState r1 = r6.getState()     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceGatheringState r2 = fm.liveswitch.IceGatheringState.Closing     // Catch:{ all -> 0x011e }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x011e }
            r2 = 1
            if (r1 == 0) goto L_0x002e
            java.lang.String r1 = "Socket manager {0} is already closing."
            java.lang.String r3 = r6.getId()     // Catch:{ all -> 0x011e }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x011e }
            fm.liveswitch.Log.debug(r1)     // Catch:{ all -> 0x011e }
            monitor-exit(r0)     // Catch:{ all -> 0x011e }
            return r2
        L_0x002e:
            fm.liveswitch.IceGatheringState r1 = r6.getState()     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Closed     // Catch:{ all -> 0x011e }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x011e }
            if (r1 != 0) goto L_0x00fd
            fm.liveswitch.IceGatheringState r1 = r6.getState()     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Failed     // Catch:{ all -> 0x011e }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x011e }
            if (r1 == 0) goto L_0x0048
            goto L_0x00fd
        L_0x0048:
            fm.liveswitch.IceGatheringState r1 = r6.getState()     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.New     // Catch:{ all -> 0x011e }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x011e }
            if (r1 == 0) goto L_0x0075
            r1 = 0
            r6.setOnLocalCandidate(r1)     // Catch:{ all -> 0x011e }
            r6.setOnStateChange(r1)     // Catch:{ all -> 0x011e }
            r6.setOnIncomingData(r1)     // Catch:{ all -> 0x011e }
            r6.setOnAllocationMismatchException(r1)     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceGatheringState r1 = fm.liveswitch.IceGatheringState.Closed     // Catch:{ all -> 0x011e }
            r6.setState(r1)     // Catch:{ all -> 0x011e }
            java.lang.String r1 = "Closed socket manager {0}."
            java.lang.String r3 = r6.getId()     // Catch:{ all -> 0x011e }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x011e }
            fm.liveswitch.Log.debug(r1)     // Catch:{ all -> 0x011e }
            monitor-exit(r0)     // Catch:{ all -> 0x011e }
            return r2
        L_0x0075:
            fm.liveswitch.IceGatheringState r1 = fm.liveswitch.IceGatheringState.Closing     // Catch:{ all -> 0x011e }
            r6.setState(r1)     // Catch:{ all -> 0x011e }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x011e }
            r1.<init>()     // Catch:{ all -> 0x011e }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceLocalRelayedCandidate> r3 = r6._localRelayedCandidates     // Catch:{ all -> 0x011e }
            java.util.Collection r3 = fm.liveswitch.HashMapExtensions.getValues(r3)     // Catch:{ all -> 0x011e }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x011e }
        L_0x0089:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x011e }
            if (r4 == 0) goto L_0x009f
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceLocalRelayedCandidate r4 = (fm.liveswitch.IceLocalRelayedCandidate) r4     // Catch:{ all -> 0x011e }
            boolean r5 = r4.getOpen()     // Catch:{ all -> 0x011e }
            if (r5 == 0) goto L_0x0089
            r1.add(r4)     // Catch:{ all -> 0x011e }
            goto L_0x0089
        L_0x009f:
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x011e }
            if (r3 != r2) goto L_0x00bf
            java.lang.String r3 = "Will shut down 1 relay candidate while shutting down socket manager {1}."
            int r4 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x011e }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x011e }
            java.lang.String r4 = fm.liveswitch.IntegerExtensions.toString(r4)     // Catch:{ all -> 0x011e }
            java.lang.String r5 = r6.getId()     // Catch:{ all -> 0x011e }
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r3, r4, r5)     // Catch:{ all -> 0x011e }
            fm.liveswitch.Log.debug(r3)     // Catch:{ all -> 0x011e }
            goto L_0x00de
        L_0x00bf:
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x011e }
            if (r3 <= r2) goto L_0x00de
            java.lang.String r3 = "Will shut down {0} relay candidates while shutting down socket manager {1}."
            int r4 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x011e }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x011e }
            java.lang.String r4 = fm.liveswitch.IntegerExtensions.toString(r4)     // Catch:{ all -> 0x011e }
            java.lang.String r5 = r6.getId()     // Catch:{ all -> 0x011e }
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r3, r4, r5)     // Catch:{ all -> 0x011e }
            fm.liveswitch.Log.debug(r3)     // Catch:{ all -> 0x011e }
        L_0x00de:
            java.util.Iterator r3 = r1.iterator()     // Catch:{ all -> 0x011e }
        L_0x00e2:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x011e }
            if (r4 == 0) goto L_0x00f2
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceLocalRelayedCandidate r4 = (fm.liveswitch.IceLocalRelayedCandidate) r4     // Catch:{ all -> 0x011e }
            r4.stopRelayTransactions(r2)     // Catch:{ all -> 0x011e }
            goto L_0x00e2
        L_0x00f2:
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x011e }
            if (r1 != 0) goto L_0x00fb
            r6.finalise()     // Catch:{ all -> 0x011e }
        L_0x00fb:
            monitor-exit(r0)     // Catch:{ all -> 0x011e }
            return r2
        L_0x00fd:
            fm.liveswitch.IceGatheringState r1 = r6.getState()     // Catch:{ all -> 0x011e }
            fm.liveswitch.IceGatheringState r2 = fm.liveswitch.IceGatheringState.Closed     // Catch:{ all -> 0x011e }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x011e }
            if (r1 == 0) goto L_0x010c
            java.lang.String r1 = "closed"
            goto L_0x010e
        L_0x010c:
            java.lang.String r1 = "failed"
        L_0x010e:
            java.lang.String r2 = "Socket manager {0} is already {1}."
            java.lang.String r3 = r6.getId()     // Catch:{ all -> 0x011e }
            java.lang.String r1 = fm.liveswitch.StringExtensions.format(r2, r3, r1)     // Catch:{ all -> 0x011e }
            fm.liveswitch.Log.debug(r1)     // Catch:{ all -> 0x011e }
            r1 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x011e }
            return r1
        L_0x011e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x011e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceSocketManager.stop():boolean");
    }

    /* access modifiers changed from: protected */
    public void turnPreProcess(DataBuffer dataBuffer, TransportAddress transportAddress, TransportAddress transportAddress2, Holder<DataBuffer> holder, Holder<TransportAddress> holder2) {
        if (transportAddress2 != null) {
            SendIndication sendIndication = new SendIndication();
            sendIndication.setXorPeerAddress(new XorPeerAddressAttribute(transportAddress.getIPAddress(), transportAddress.getPort(), sendIndication.getTransactionId()));
            sendIndication.setData(new DataAttribute(dataBuffer));
            holder.setValue(DataBuffer.allocate(sendIndication.getLength()));
            sendIndication.writeTo(holder.getValue());
            holder2.setValue(transportAddress2);
            return;
        }
        holder.setValue(dataBuffer);
        holder2.setValue(transportAddress);
    }

    /* access modifiers changed from: protected */
    public void verifyAllRelayedCandidatesStopped() {
        synchronized (getLock()) {
            boolean z = true;
            for (IceLocalRelayedCandidate next : HashMapExtensions.getValues(this._localRelayedCandidates)) {
                if (!Global.equals(next.getRelayState(), IceLocalRelayedCandidateState.Closed) && !Global.equals(next.getRelayState(), IceLocalRelayedCandidateState.Failed)) {
                    z = false;
                }
            }
            if (z) {
                finalise();
            }
        }
    }
}
