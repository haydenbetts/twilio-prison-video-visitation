package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class IceCheckList {
    private Object __lock;
    private ArrayList<IceCandidatePair> __ordinaryCheckList = new ArrayList<>();
    private HashMap<String, IceCandidatePair> __ordinaryCheckListHash = new HashMap<>();
    private ArrayList<IceCandidatePair> __triggeredCheckList = new ArrayList<>();
    private HashMap<String, IceCandidatePair> __waitingForPermissionRequestResponseHash = new HashMap<>();

    /* access modifiers changed from: package-private */
    public void addToAwaitingOriginalRelayPermissionsList(IceCandidatePair iceCandidatePair) {
        synchronized (this.__lock) {
            String iceCandidatePair2 = iceCandidatePair.toString();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__waitingForPermissionRequestResponseHash, iceCandidatePair2, holder);
            IceCandidatePair iceCandidatePair3 = (IceCandidatePair) holder.getValue();
            if (!tryGetValue) {
                HashMapExtensions.set(HashMapExtensions.getItem(this.__waitingForPermissionRequestResponseHash), iceCandidatePair2, iceCandidatePair);
                iceCandidatePair.startPermissionRequests();
            } else if (!Global.equals(iceCandidatePair, iceCandidatePair3)) {
                iceCandidatePair.stop();
            }
        }
    }

    public boolean addToOrdinaryCheckList(IceCandidatePair iceCandidatePair) {
        if (Global.equals(iceCandidatePair.getLocal().getType(), CandidateType.ServerReflexive)) {
            iceCandidatePair.setLocal(((IceLocalReflexiveCandidate) iceCandidatePair.getLocal()).getLocalHostBaseCandidate());
        }
        String iceCandidatePair2 = iceCandidatePair.toString();
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__ordinaryCheckListHash, iceCandidatePair2, holder);
        IceCandidatePair iceCandidatePair3 = (IceCandidatePair) holder.getValue();
        boolean z = false;
        if (tryGetValue && !Global.equals(iceCandidatePair3, iceCandidatePair)) {
            if (!Global.equals(iceCandidatePair3.getState(), CandidatePairState.Closed) || !Global.equals(iceCandidatePair3.getState(), CandidatePairState.Failed)) {
                iceCandidatePair.stop();
                return false;
            }
            this.__ordinaryCheckList.remove(iceCandidatePair3);
        }
        synchronized (this.__lock) {
            int i = 0;
            while (!z) {
                try {
                    if (i >= ArrayListExtensions.getCount(this.__ordinaryCheckList)) {
                        break;
                    } else if (((IceCandidatePair) ArrayListExtensions.getItem(this.__ordinaryCheckList).get(i)).getPriority() <= iceCandidatePair.getPriority()) {
                        if (!this.__ordinaryCheckList.contains(iceCandidatePair)) {
                            ArrayListExtensions.insert(this.__ordinaryCheckList, i, iceCandidatePair);
                        }
                        z = true;
                    } else {
                        i++;
                    }
                } finally {
                }
            }
            if (!z && !this.__ordinaryCheckList.contains(iceCandidatePair)) {
                this.__ordinaryCheckList.add(iceCandidatePair);
            }
            iceCandidatePair.setState(CandidatePairState.Waiting);
            HashMapExtensions.set(HashMapExtensions.getItem(this.__ordinaryCheckListHash), iceCandidatePair2, iceCandidatePair);
        }
        return true;
    }

    public void addToTriggeredCheckList(IceCandidatePair iceCandidatePair, boolean z) {
        if (z) {
            if (Global.equals(iceCandidatePair.getState(), CandidatePairState.New)) {
                iceCandidatePair.setOnOriginalRelayPermissionsObtained((IAction1<IceCandidatePair>) null);
                iceCandidatePair.setAwaitingOriginalRelayPermissions(false);
                addToOrdinaryCheckList(iceCandidatePair);
                synchronized (this.__lock) {
                    HashMapExtensions.remove(this.__waitingForPermissionRequestResponseHash, iceCandidatePair.toString());
                }
            }
            if (Global.equals(iceCandidatePair.getState(), CandidatePairState.Waiting)) {
                synchronized (this.__lock) {
                    if (!this.__triggeredCheckList.contains(iceCandidatePair)) {
                        this.__triggeredCheckList.add(iceCandidatePair);
                    }
                }
            } else if (Global.equals(iceCandidatePair.getState(), CandidatePairState.InProgress)) {
                iceCandidatePair.cancelConnectivityCheck();
                synchronized (this.__lock) {
                    if (!this.__triggeredCheckList.contains(iceCandidatePair)) {
                        this.__triggeredCheckList.add(iceCandidatePair);
                    }
                }
                iceCandidatePair.setState(CandidatePairState.Waiting);
            } else if (Global.equals(iceCandidatePair.getState(), CandidatePairState.Failed)) {
                synchronized (this.__lock) {
                    if (!this.__triggeredCheckList.contains(iceCandidatePair)) {
                        this.__triggeredCheckList.add(iceCandidatePair);
                    }
                }
                iceCandidatePair.setState(CandidatePairState.Waiting);
            }
        } else if (!Global.equals(iceCandidatePair.getState(), CandidatePairState.Succeeded)) {
            if (Global.equals(iceCandidatePair.getLocal().getType(), CandidateType.Relayed)) {
                iceCandidatePair.startPermissionRequests();
            }
            synchronized (this.__lock) {
                if (!this.__triggeredCheckList.contains(iceCandidatePair)) {
                    this.__triggeredCheckList.add(iceCandidatePair);
                }
            }
            iceCandidatePair.setAwaitingOriginalRelayPermissions(false);
            addToOrdinaryCheckList(iceCandidatePair);
        }
    }

    public void clear() {
        synchronized (this.__lock) {
            Iterator<IceCandidatePair> it = this.__triggeredCheckList.iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
            this.__triggeredCheckList.clear();
            Iterator<IceCandidatePair> it2 = this.__ordinaryCheckList.iterator();
            while (it2.hasNext()) {
                it2.next().stop();
            }
            this.__ordinaryCheckList.clear();
            this.__ordinaryCheckListHash.clear();
            for (IceCandidatePair stop : HashMapExtensions.getValues(this.__waitingForPermissionRequestResponseHash)) {
                stop.stop();
            }
            this.__waitingForPermissionRequestResponseHash.clear();
        }
    }

    public IceCandidatePair findMatchingCandidatePair(IceCandidatePair iceCandidatePair) {
        return findMatchingCandidatePair(iceCandidatePair.getLocal(), iceCandidatePair.getRemote());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.IceCandidatePair findMatchingCandidatePair(fm.liveswitch.IceCandidate r4, fm.liveswitch.IceCandidate r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.__lock
            monitor-enter(r0)
            java.lang.String r4 = fm.liveswitch.IceCandidatePair.toString(r4, r5)     // Catch:{ all -> 0x0033 }
            fm.liveswitch.Holder r5 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0033 }
            r1 = 0
            r5.<init>(r1)     // Catch:{ all -> 0x0033 }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidatePair> r1 = r3.__ordinaryCheckListHash     // Catch:{ all -> 0x0033 }
            boolean r1 = fm.liveswitch.HashMapExtensions.tryGetValue(r1, r4, r5)     // Catch:{ all -> 0x0033 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ all -> 0x0033 }
            fm.liveswitch.IceCandidatePair r5 = (fm.liveswitch.IceCandidatePair) r5     // Catch:{ all -> 0x0033 }
            fm.liveswitch.Holder r2 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0033 }
            r2.<init>(r5)     // Catch:{ all -> 0x0033 }
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidatePair> r5 = r3.__waitingForPermissionRequestResponseHash     // Catch:{ all -> 0x0033 }
            boolean r4 = fm.liveswitch.HashMapExtensions.tryGetValue(r5, r4, r2)     // Catch:{ all -> 0x0033 }
            java.lang.Object r5 = r2.getValue()     // Catch:{ all -> 0x0033 }
            fm.liveswitch.IceCandidatePair r5 = (fm.liveswitch.IceCandidatePair) r5     // Catch:{ all -> 0x0033 }
            if (r1 != 0) goto L_0x0031
            if (r4 == 0) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return r5
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return r5
        L_0x0033:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceCheckList.findMatchingCandidatePair(fm.liveswitch.IceCandidate, fm.liveswitch.IceCandidate):fm.liveswitch.IceCandidatePair");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        r1 = r4.__lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0 = fm.liveswitch.HashMapExtensions.getValues(r4.__waitingForPermissionRequestResponseHash).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        if (r0.hasNext() == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        r2 = r0.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0059, code lost:
        if (fm.liveswitch.Global.equals(r2.getLocal().getIPAddress(), r5) == false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
        if (r2.getLocal().getPort() != r6) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0065, code lost:
        r5 = r2.getLocal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006a, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006b, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.IceCandidate findMatchingLocalCandidate(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__lock
            monitor-enter(r0)
            java.util.ArrayList<fm.liveswitch.IceCandidatePair> r1 = r4.__ordinaryCheckList     // Catch:{ all -> 0x0071 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0071 }
        L_0x0009:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0071 }
            if (r2 == 0) goto L_0x0033
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0071 }
            fm.liveswitch.IceCandidatePair r2 = (fm.liveswitch.IceCandidatePair) r2     // Catch:{ all -> 0x0071 }
            fm.liveswitch.IceCandidate r3 = r2.getLocal()     // Catch:{ all -> 0x0071 }
            java.lang.String r3 = r3.getIPAddress()     // Catch:{ all -> 0x0071 }
            boolean r3 = fm.liveswitch.Global.equals(r3, r5)     // Catch:{ all -> 0x0071 }
            if (r3 == 0) goto L_0x0009
            fm.liveswitch.IceCandidate r3 = r2.getLocal()     // Catch:{ all -> 0x0071 }
            int r3 = r3.getPort()     // Catch:{ all -> 0x0071 }
            if (r3 != r6) goto L_0x0009
            fm.liveswitch.IceCandidate r5 = r2.getLocal()     // Catch:{ all -> 0x0071 }
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            return r5
        L_0x0033:
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            java.lang.Object r1 = r4.__lock
            monitor-enter(r1)
            java.util.HashMap<java.lang.String, fm.liveswitch.IceCandidatePair> r0 = r4.__waitingForPermissionRequestResponseHash     // Catch:{ all -> 0x006e }
            java.util.Collection r0 = fm.liveswitch.HashMapExtensions.getValues(r0)     // Catch:{ all -> 0x006e }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x006e }
        L_0x0041:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x006e }
            if (r2 == 0) goto L_0x006b
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x006e }
            fm.liveswitch.IceCandidatePair r2 = (fm.liveswitch.IceCandidatePair) r2     // Catch:{ all -> 0x006e }
            fm.liveswitch.IceCandidate r3 = r2.getLocal()     // Catch:{ all -> 0x006e }
            java.lang.String r3 = r3.getIPAddress()     // Catch:{ all -> 0x006e }
            boolean r3 = fm.liveswitch.Global.equals(r3, r5)     // Catch:{ all -> 0x006e }
            if (r3 == 0) goto L_0x0041
            fm.liveswitch.IceCandidate r3 = r2.getLocal()     // Catch:{ all -> 0x006e }
            int r3 = r3.getPort()     // Catch:{ all -> 0x006e }
            if (r3 != r6) goto L_0x0041
            fm.liveswitch.IceCandidate r5 = r2.getLocal()     // Catch:{ all -> 0x006e }
            monitor-exit(r1)     // Catch:{ all -> 0x006e }
            return r5
        L_0x006b:
            monitor-exit(r1)     // Catch:{ all -> 0x006e }
            r5 = 0
            return r5
        L_0x006e:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x006e }
            throw r5
        L_0x0071:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceCheckList.findMatchingLocalCandidate(java.lang.String, int):fm.liveswitch.IceCandidate");
    }

    public boolean getAllCandidatePairsCompletedAndAtLeastOneSucceeded() {
        boolean z;
        boolean z2;
        if (HashMapExtensions.getCount(this.__waitingForPermissionRequestResponseHash) > 0) {
            return false;
        }
        synchronized (this.__lock) {
            Iterator<IceCandidatePair> it = this.__ordinaryCheckList.iterator();
            z = true;
            loop0:
            while (true) {
                z2 = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    IceCandidatePair next = it.next();
                    z = z && (Global.equals(next.getState(), CandidatePairState.Succeeded) || Global.equals(next.getState(), CandidatePairState.Failed) || Global.equals(next.getState(), CandidatePairState.Closed));
                    if (z2 || Global.equals(next.getState(), CandidatePairState.Succeeded)) {
                        z2 = true;
                    }
                }
            }
        }
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    public boolean getAtLeastOneCandidatePairSucceeded() {
        synchronized (this.__lock) {
            Iterator<IceCandidatePair> it = this.__ordinaryCheckList.iterator();
            while (it.hasNext()) {
                if (Global.equals(it.next().getState(), CandidatePairState.Succeeded)) {
                    return true;
                }
            }
            return false;
        }
    }

    public IceCandidatePair[] getCandidatePairs() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.__lock) {
            ArrayListExtensions.addRange(arrayList, this.__ordinaryCheckList);
            Iterator<IceCandidatePair> it = this.__triggeredCheckList.iterator();
            while (it.hasNext()) {
                IceCandidatePair next = it.next();
                if (!this.__ordinaryCheckListHash.containsKey(next.toString())) {
                    arrayList.add(next);
                }
            }
            for (IceCandidatePair next2 : HashMapExtensions.getValues(this.__waitingForPermissionRequestResponseHash)) {
                if (!this.__ordinaryCheckListHash.containsKey(next2.toString())) {
                    arrayList.add(next2);
                }
            }
        }
        return (IceCandidatePair[]) arrayList.toArray(new IceCandidatePair[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        return false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getConnectivityChecksCompletedAndNoneSucceeded() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.__lock
            monitor-enter(r0)
            java.util.ArrayList<fm.liveswitch.IceCandidatePair> r1 = r6.__ordinaryCheckList     // Catch:{ all -> 0x004a }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x004a }
        L_0x0009:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x004a }
            r3 = 0
            if (r2 == 0) goto L_0x0048
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x004a }
            fm.liveswitch.IceCandidatePair r2 = (fm.liveswitch.IceCandidatePair) r2     // Catch:{ all -> 0x004a }
            fm.liveswitch.CandidatePairState r4 = r2.getState()     // Catch:{ all -> 0x004a }
            fm.liveswitch.CandidatePairState r5 = fm.liveswitch.CandidatePairState.InProgress     // Catch:{ all -> 0x004a }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ all -> 0x004a }
            if (r4 != 0) goto L_0x0046
            fm.liveswitch.CandidatePairState r4 = r2.getState()     // Catch:{ all -> 0x004a }
            fm.liveswitch.CandidatePairState r5 = fm.liveswitch.CandidatePairState.Succeeded     // Catch:{ all -> 0x004a }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ all -> 0x004a }
            if (r4 != 0) goto L_0x0046
            fm.liveswitch.CandidatePairState r4 = r2.getState()     // Catch:{ all -> 0x004a }
            fm.liveswitch.CandidatePairState r5 = fm.liveswitch.CandidatePairState.Waiting     // Catch:{ all -> 0x004a }
            boolean r4 = fm.liveswitch.Global.equals(r4, r5)     // Catch:{ all -> 0x004a }
            if (r4 != 0) goto L_0x0046
            fm.liveswitch.CandidatePairState r2 = r2.getState()     // Catch:{ all -> 0x004a }
            fm.liveswitch.CandidatePairState r4 = fm.liveswitch.CandidatePairState.New     // Catch:{ all -> 0x004a }
            boolean r2 = fm.liveswitch.Global.equals(r2, r4)     // Catch:{ all -> 0x004a }
            if (r2 == 0) goto L_0x0009
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r3
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r3
        L_0x004a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceCheckList.getConnectivityChecksCompletedAndNoneSucceeded():boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getConnectivityChecksNotCompleted() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.__lock
            monitor-enter(r0)
            java.util.ArrayList<fm.liveswitch.IceCandidatePair> r1 = r5.__ordinaryCheckList     // Catch:{ all -> 0x003f }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x003f }
        L_0x0009:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x003c
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x003f }
            fm.liveswitch.IceCandidatePair r2 = (fm.liveswitch.IceCandidatePair) r2     // Catch:{ all -> 0x003f }
            fm.liveswitch.CandidatePairState r3 = r2.getState()     // Catch:{ all -> 0x003f }
            fm.liveswitch.CandidatePairState r4 = fm.liveswitch.CandidatePairState.InProgress     // Catch:{ all -> 0x003f }
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)     // Catch:{ all -> 0x003f }
            if (r3 != 0) goto L_0x0039
            fm.liveswitch.CandidatePairState r3 = r2.getState()     // Catch:{ all -> 0x003f }
            fm.liveswitch.CandidatePairState r4 = fm.liveswitch.CandidatePairState.Waiting     // Catch:{ all -> 0x003f }
            boolean r3 = fm.liveswitch.Global.equals(r3, r4)     // Catch:{ all -> 0x003f }
            if (r3 != 0) goto L_0x0039
            fm.liveswitch.CandidatePairState r2 = r2.getState()     // Catch:{ all -> 0x003f }
            fm.liveswitch.CandidatePairState r3 = fm.liveswitch.CandidatePairState.New     // Catch:{ all -> 0x003f }
            boolean r2 = fm.liveswitch.Global.equals(r2, r3)     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x0009
        L_0x0039:
            r1 = 1
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return r1
        L_0x003c:
            r1 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return r1
        L_0x003f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceCheckList.getConnectivityChecksNotCompleted():boolean");
    }

    public IceCandidatePair getWaitingCandidatePair() {
        int i;
        IceCandidatePair iceCandidatePair;
        boolean z;
        synchronized (this.__lock) {
            ArrayList arrayList = new ArrayList();
            iceCandidatePair = null;
            z = false;
            for (int i2 = 0; i2 < ArrayListExtensions.getCount(this.__triggeredCheckList) && !z; i2++) {
                if (Global.equals(((IceCandidatePair) ArrayListExtensions.getItem(this.__triggeredCheckList).get(i2)).getState(), CandidatePairState.Waiting)) {
                    iceCandidatePair = (IceCandidatePair) ArrayListExtensions.getItem(this.__triggeredCheckList).get(i2);
                    arrayList.add(iceCandidatePair);
                    z = true;
                } else {
                    arrayList.add((IceCandidatePair) ArrayListExtensions.getItem(this.__triggeredCheckList).get(i2));
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.__triggeredCheckList.remove((IceCandidatePair) it.next());
            }
        }
        if (!z) {
            synchronized (this.__lock) {
                for (i = 0; i < ArrayListExtensions.getCount(this.__ordinaryCheckList) && !z; i++) {
                    if (Global.equals(((IceCandidatePair) ArrayListExtensions.getItem(this.__ordinaryCheckList).get(i)).getState(), CandidatePairState.Waiting)) {
                        iceCandidatePair = (IceCandidatePair) ArrayListExtensions.getItem(this.__ordinaryCheckList).get(i);
                        z = true;
                    }
                }
            }
        }
        return iceCandidatePair;
    }

    public IceCheckList(Object obj) {
        this.__lock = obj;
    }

    /* access modifiers changed from: package-private */
    public boolean processGotOriginalRelayPermission(IceCandidatePair iceCandidatePair) {
        boolean addToOrdinaryCheckList;
        synchronized (this.__lock) {
            addToOrdinaryCheckList = Global.equals(iceCandidatePair.getState(), CandidatePairState.New) ? addToOrdinaryCheckList(iceCandidatePair) : false;
            HashMapExtensions.remove(this.__waitingForPermissionRequestResponseHash, iceCandidatePair.toString());
        }
        iceCandidatePair.setOnOriginalRelayPermissionsObtained((IAction1<IceCandidatePair>) null);
        return addToOrdinaryCheckList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.__lock) {
            Iterator<IceCandidatePair> it = this.__ordinaryCheckList.iterator();
            while (it.hasNext()) {
                StringBuilderExtensions.append(sb, StringExtensions.concat(it.next().toString(), "\n"));
            }
            for (IceCandidatePair iceCandidatePair : HashMapExtensions.getValues(this.__waitingForPermissionRequestResponseHash)) {
                StringBuilderExtensions.append(sb, StringExtensions.concat(iceCandidatePair.toString(), "\n"));
            }
        }
        return StringExtensions.trimEnd(sb.toString(), new char[]{10});
    }
}
