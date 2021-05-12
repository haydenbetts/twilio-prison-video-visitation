package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class StateMachine<T> {
    private HashMap<String, ArrayList<PendingPromise>> __pendingPromises = new HashMap<>();
    private int __stateValue;
    private ArrayList<String> __states = new ArrayList<>();
    private HashMap<String, HashMap<String, Boolean>> __transitionReachabilityMatrix = new HashMap<>();
    private HashMap<String, ArrayList<String>> __transitions = new HashMap<>();
    private Object __transitionsLock = new Object();
    private long _lastStateTicks;
    private long _systemTimestamp;

    /* access modifiers changed from: protected */
    public abstract int stateToValue(T t);

    /* access modifiers changed from: protected */
    public abstract T valueToState(int i);

    private <R> void addPendingPromise(String str, Promise<R> promise, R r) {
        PendingPromise pendingPromise = new PendingPromise(promise, r);
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__pendingPromises, str, holder);
        ArrayList arrayList = (ArrayList) holder.getValue();
        if (!tryGetValue) {
            HashMap<String, ArrayList<PendingPromise>> item = HashMapExtensions.getItem(this.__pendingPromises);
            ArrayList arrayList2 = new ArrayList();
            HashMapExtensions.set(item, str, arrayList2);
            arrayList = arrayList2;
        }
        arrayList.add(pendingPromise);
    }

    public void addTransition(T t, T t2) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(stateToValue(t)));
        String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(stateToValue(t2)));
        synchronized (this.__transitionsLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__transitions, integerExtensions, holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (!tryGetValue) {
                HashMap<String, ArrayList<String>> item = HashMapExtensions.getItem(this.__transitions);
                arrayList = new ArrayList();
                HashMapExtensions.set(item, integerExtensions, arrayList);
            }
            if (!arrayList.contains(integerExtensions2)) {
                arrayList.add(integerExtensions2);
            }
            if (!this.__states.contains(integerExtensions)) {
                this.__states.add(integerExtensions);
            }
            if (!this.__states.contains(integerExtensions2)) {
                this.__states.add(integerExtensions2);
            }
            computeTransitionMatrix();
        }
    }

    public boolean canTransition(T t) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(stateToValue(t)));
        synchronized (this.__transitionsLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__transitions, IntegerExtensions.toString(Integer.valueOf(getStateValue())), holder);
            ArrayList arrayList = (ArrayList) holder.getValue();
            if (!tryGetValue) {
                return false;
            }
            boolean contains = arrayList.contains(integerExtensions);
            return contains;
        }
    }

    private boolean checkReachable(String str) {
        return ((Boolean) HashMapExtensions.getItem(HashMapExtensions.getItem(this.__transitionReachabilityMatrix).get(IntegerExtensions.toString(Integer.valueOf(getStateValue())))).get(str)).booleanValue();
    }

    private void computeTransitionMatrix() {
        this.__transitionReachabilityMatrix.clear();
        Iterator<String> it = this.__states.iterator();
        while (it.hasNext()) {
            String next = it.next();
            HashMap hashMap = new HashMap();
            Iterator<String> it2 = this.__states.iterator();
            while (it2.hasNext()) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), it2.next(), false);
            }
            exploreStates(next, hashMap);
            HashMapExtensions.set(HashMapExtensions.getItem(this.__transitionReachabilityMatrix), next, hashMap);
        }
    }

    private void exploreStates(String str, HashMap<String, Boolean> hashMap) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__transitions, str, holder);
        ArrayList arrayList = (ArrayList) holder.getValue();
        if (tryGetValue) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (!HashMapExtensions.getItem(hashMap).get(str2).booleanValue()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str2, true);
                    exploreStates(str2, hashMap);
                }
            }
        }
    }

    public long getLastStateMillis() {
        long lastStateTicks = getLastStateTicks();
        if (lastStateTicks == -1) {
            return -1;
        }
        return lastStateTicks / ((long) Constants.getTicksPerMillisecond());
    }

    public long getLastStateTicks() {
        return this._lastStateTicks;
    }

    public <R> Promise<R> getPromise(T t, R r) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(stateToValue(t)));
        Promise<R> promise = new Promise<>();
        synchronized (this.__transitionsLock) {
            if (getStateValue() == stateToValue(t)) {
                promise.resolve(r);
                return promise;
            } else if (!checkReachable(integerExtensions)) {
                promise.reject(new Exception(StringExtensions.format("Cannot transition to state {0}.", (Object) t.toString())));
                return promise;
            } else {
                addPendingPromise(integerExtensions, promise, r);
                return promise;
            }
        }
    }

    public T getState() {
        return valueToState(this.__stateValue);
    }

    /* access modifiers changed from: protected */
    public int getStateValue() {
        return this.__stateValue;
    }

    public long getSystemTimestamp() {
        return this._systemTimestamp;
    }

    public boolean isReachable(T t) {
        return checkReachable(IntegerExtensions.toString(Integer.valueOf(stateToValue(t))));
    }

    private void setLastStateTicks(long j) {
        this._lastStateTicks = j;
    }

    private void setStateValue(int i) {
        long timestamp = ManagedStopwatch.getTimestamp();
        if (getSystemTimestamp() != -1) {
            setLastStateTicks(timestamp - getSystemTimestamp());
        }
        setSystemTimestamp(timestamp);
        this.__stateValue = i;
    }

    private void setSystemTimestamp(long j) {
        this._systemTimestamp = j;
    }

    public StateMachine(T t) {
        setSystemTimestamp(-1);
        setLastStateTicks(-1);
        setStateValue(stateToValue(t));
    }

    public boolean transition(T t) {
        synchronized (this.__transitionsLock) {
            if (!canTransition(t)) {
                return false;
            }
            setStateValue(stateToValue(t));
            validatePendingPromises();
            return true;
        }
    }

    private void validatePendingPromises() {
        for (String next : HashMapExtensions.getKeys(this.__pendingPromises)) {
            if (Global.equals(IntegerExtensions.toString(Integer.valueOf(getStateValue())), next)) {
                Iterator it = HashMapExtensions.getItem(this.__pendingPromises).get(next).iterator();
                while (it.hasNext()) {
                    ((PendingPromise) it.next()).resolve();
                }
                HashMapExtensions.remove(this.__pendingPromises, next);
            } else if (!checkReachable(next)) {
                Iterator it2 = HashMapExtensions.getItem(this.__pendingPromises).get(next).iterator();
                while (it2.hasNext()) {
                    ((PendingPromise) it2.next()).reject(new Exception(StringExtensions.format("Cannot transition to state {0}.", (Object) next.toString())));
                }
                HashMapExtensions.remove(this.__pendingPromises, next);
            }
        }
    }
}
