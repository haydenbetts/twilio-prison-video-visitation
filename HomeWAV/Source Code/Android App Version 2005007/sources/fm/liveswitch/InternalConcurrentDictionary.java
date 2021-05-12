package fm.liveswitch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

class InternalConcurrentDictionary<TKey, TValue> {
    private HashMap<TKey, TValue> __dictionary = new HashMap<>();
    private Object __locker = new Object();

    public TValue addOrUpdate(TKey tkey, TValue tvalue, IFunction2<TKey, TValue, TValue> iFunction2) {
        synchronized (this.__locker) {
            if (containsKey(tkey)) {
                TValue invoke = iFunction2.invoke(tkey, HashMapExtensions.getItem(this.__dictionary).get(tkey));
                HashMapExtensions.set(HashMapExtensions.getItem(this.__dictionary), tkey, invoke);
                return invoke;
            }
            HashMapExtensions.add(this.__dictionary, tkey, tvalue);
            return tvalue;
        }
    }

    public void clear() {
        synchronized (this.__locker) {
            this.__dictionary.clear();
        }
    }

    public boolean containsKey(TKey tkey) {
        boolean containsKey;
        synchronized (this.__locker) {
            containsKey = this.__dictionary.containsKey(tkey);
        }
        return containsKey;
    }

    public int getCount() {
        int count;
        synchronized (this.__locker) {
            count = HashMapExtensions.getCount(this.__dictionary);
        }
        return count;
    }

    public boolean getIsEmpty() {
        boolean z;
        synchronized (this.__locker) {
            z = getCount() == 0;
        }
        return z;
    }

    public Collection<TKey> getKeys() {
        Set<TKey> keys;
        synchronized (this.__locker) {
            keys = HashMapExtensions.getKeys(this.__dictionary);
        }
        return keys;
    }

    public TValue getOrAdd(TKey tkey, IFunction1<TKey, TValue> iFunction1) {
        synchronized (this.__locker) {
            if (containsKey(tkey)) {
                TValue tvalue = HashMapExtensions.getItem(this.__dictionary).get(tkey);
                return tvalue;
            }
            TValue invoke = iFunction1.invoke(tkey);
            HashMapExtensions.add(this.__dictionary, tkey, invoke);
            return invoke;
        }
    }

    public Collection<TValue> getValues() {
        Collection<TValue> values;
        synchronized (this.__locker) {
            values = HashMapExtensions.getValues(this.__dictionary);
        }
        return values;
    }

    public InternalConcurrentDictionary(IFunction1<TKey, String> iFunction1) {
    }

    public boolean tryAdd(TKey tkey, TValue tvalue) {
        synchronized (this.__locker) {
            if (containsKey(tkey)) {
                return false;
            }
            HashMapExtensions.add(this.__dictionary, tkey, tvalue);
            return true;
        }
    }

    public boolean tryGetValue(TKey tkey, Holder<TValue> holder) {
        TValue tvalue;
        synchronized (this.__locker) {
            if (!containsKey(tkey) || (tvalue = HashMapExtensions.getItem(this.__dictionary).get(tkey)) == null) {
                holder.setValue(null);
                return false;
            }
            holder.setValue(tvalue);
            return true;
        }
    }

    public boolean tryRemove(TKey tkey, Holder<TValue> holder) {
        synchronized (this.__locker) {
            if (containsKey(tkey)) {
                holder.setValue(HashMapExtensions.getItem(this.__dictionary).get(tkey));
                HashMapExtensions.remove(this.__dictionary, tkey);
                return true;
            }
            holder.setValue(null);
            return false;
        }
    }

    public boolean tryUpdate(TKey tkey, TValue tvalue, TValue tvalue2) {
        synchronized (this.__locker) {
            if (!containsKey(tkey) || HashMapExtensions.getItem(this.__dictionary).get(tkey) != tvalue2) {
                return false;
            }
            HashMapExtensions.set(HashMapExtensions.getItem(this.__dictionary), tkey, tvalue);
            return true;
        }
    }
}
