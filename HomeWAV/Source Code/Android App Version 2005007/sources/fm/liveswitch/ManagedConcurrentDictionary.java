package fm.liveswitch;

import java.util.Collection;

public class ManagedConcurrentDictionary<TKey, TValue> {
    private InternalConcurrentDictionary<TKey, TValue> __dictionary;

    public TValue addOrUpdate(TKey tkey, final TValue tvalue) {
        return this.__dictionary.addOrUpdate(tkey, tvalue, new IFunction2<TKey, TValue, TValue>() {
            public TValue invoke(TKey tkey, TValue tvalue) {
                return tvalue;
            }
        });
    }

    public TValue addOrUpdate(TKey tkey, TValue tvalue, final IFunction2<TKey, TValue, TValue> iFunction2) {
        return this.__dictionary.addOrUpdate(tkey, tvalue, new IFunction2<TKey, TValue, TValue>() {
            public TValue invoke(TKey tkey, TValue tvalue) {
                return iFunction2.invoke(tkey, tvalue);
            }
        });
    }

    public void clear() {
        this.__dictionary.clear();
    }

    public boolean containsKey(TKey tkey) {
        return this.__dictionary.containsKey(tkey);
    }

    public int getCount() {
        return this.__dictionary.getCount();
    }

    public boolean getIsEmpty() {
        return this.__dictionary.getIsEmpty();
    }

    public Collection<TKey> getKeys() {
        return this.__dictionary.getKeys();
    }

    public TValue getOrAdd(TKey tkey, final IFunction1<TKey, TValue> iFunction1) {
        return this.__dictionary.getOrAdd(tkey, new IFunction1<TKey, TValue>() {
            public TValue invoke(TKey tkey) {
                return iFunction1.invoke(tkey);
            }
        });
    }

    public Collection<TValue> getValues() {
        return this.__dictionary.getValues();
    }

    /* access modifiers changed from: private */
    public String hash(TKey tkey) {
        return tkey.toString();
    }

    public ManagedConcurrentDictionary() {
        this((IFunction1) null);
    }

    public ManagedConcurrentDictionary(IFunction1<TKey, String> iFunction1) {
        if (iFunction1 == null) {
            this.__dictionary = new InternalConcurrentDictionary<>(new IFunctionDelegate1<TKey, String>() {
                public String getId() {
                    return "fm.liveswitch.ManagedConcurrentDictionary<TKey,TValue>.hash";
                }

                public String invoke(TKey tkey) {
                    return ManagedConcurrentDictionary.this.hash(tkey);
                }
            });
        } else {
            this.__dictionary = new InternalConcurrentDictionary<>(iFunction1);
        }
    }

    public boolean tryAdd(TKey tkey, TValue tvalue) {
        return this.__dictionary.tryAdd(tkey, tvalue);
    }

    public boolean tryGetValue(TKey tkey, Holder<TValue> holder) {
        return this.__dictionary.tryGetValue(tkey, holder);
    }

    public boolean tryRemove(TKey tkey) {
        Holder holder = new Holder(null);
        boolean tryRemove = this.__dictionary.tryRemove(tkey, holder);
        holder.getValue();
        return tryRemove;
    }

    public boolean tryRemove(TKey tkey, Holder<TValue> holder) {
        return this.__dictionary.tryRemove(tkey, holder);
    }

    public boolean tryUpdate(TKey tkey, TValue tvalue, TValue tvalue2) {
        return this.__dictionary.tryUpdate(tkey, tvalue, tvalue2);
    }
}
