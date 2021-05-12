package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;

class IceValidList {
    private ArrayList<IceCandidatePair> __candidatePairs = new ArrayList<>();
    private Object __lock;

    public boolean addCandidatePair(IceCandidatePair iceCandidatePair) {
        synchronized (this.__lock) {
            for (int i = 0; i < ArrayListExtensions.getCount(this.__candidatePairs); i++) {
                IceCandidatePair iceCandidatePair2 = (IceCandidatePair) ArrayListExtensions.getItem(this.__candidatePairs).get(i);
                if (iceCandidatePair.equals(iceCandidatePair2)) {
                    return false;
                }
                if (iceCandidatePair2.getPriority() <= iceCandidatePair.getPriority()) {
                    ArrayListExtensions.insert(this.__candidatePairs, i, iceCandidatePair);
                    return true;
                }
            }
            this.__candidatePairs.add(iceCandidatePair);
            return true;
        }
    }

    public void clear() {
        synchronized (this.__lock) {
            Iterator<IceCandidatePair> it = this.__candidatePairs.iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
            this.__candidatePairs.clear();
        }
    }

    public IceCandidatePair findMatchingCandidatePair(IceCandidate iceCandidate, IceCandidate iceCandidate2) {
        synchronized (this.__lock) {
            Iterator<IceCandidatePair> it = this.__candidatePairs.iterator();
            while (it.hasNext()) {
                IceCandidatePair next = it.next();
                if (next.equals(iceCandidate, iceCandidate2)) {
                    return next;
                }
            }
            return null;
        }
    }

    public IceCandidatePair[] getCandidatePairs() {
        IceCandidatePair[] iceCandidatePairArr;
        synchronized (this.__lock) {
            iceCandidatePairArr = (IceCandidatePair[]) this.__candidatePairs.toArray(new IceCandidatePair[0]);
        }
        return iceCandidatePairArr;
    }

    public boolean getHasNominatedSucceededPair() {
        for (IceCandidatePair iceCandidatePair : getCandidatePairs()) {
            if (iceCandidatePair.getNominated() && Global.equals(iceCandidatePair.getState(), CandidatePairState.Succeeded)) {
                return true;
            }
        }
        return false;
    }

    public IceCandidatePair getNominatedPairWithHighestPriority() {
        synchronized (this.__lock) {
            Iterator<IceCandidatePair> it = this.__candidatePairs.iterator();
            while (it.hasNext()) {
                IceCandidatePair next = it.next();
                if (next.getNominated() && Global.equals(next.getState(), CandidatePairState.Succeeded)) {
                    return next;
                }
            }
            return null;
        }
    }

    public IceValidList(Object obj) {
        this.__lock = obj;
    }

    public void purgePairs() {
        synchronized (this.__lock) {
            ArrayList arrayList = new ArrayList();
            Iterator<IceCandidatePair> it = this.__candidatePairs.iterator();
            while (it.hasNext()) {
                IceCandidatePair next = it.next();
                if (Global.equals(next.getState(), CandidatePairState.Failed) || Global.equals(next.getState(), CandidatePairState.Closed)) {
                    arrayList.add(next);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                removeCandidatePair((IceCandidatePair) it2.next());
            }
        }
    }

    public boolean removeCandidatePair(IceCandidatePair iceCandidatePair) {
        boolean remove;
        synchronized (this.__lock) {
            remove = this.__candidatePairs.remove(iceCandidatePair);
        }
        return remove;
    }

    public void reorder(IceCandidatePair iceCandidatePair) {
        synchronized (this.__lock) {
            removeCandidatePair(iceCandidatePair);
            addCandidatePair(iceCandidatePair);
        }
    }
}
