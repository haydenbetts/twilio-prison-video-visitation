package fm.liveswitch;

import java.util.HashMap;

public abstract class Dynamic extends Serializable {
    private HashMap<String, Object> __dynamicProperties = null;
    private Object __dynamicPropertiesLock = new Object();

    protected Dynamic() {
    }

    public HashMap<String, Object> getDynamicProperties() {
        synchronized (this.__dynamicPropertiesLock) {
            if (this.__dynamicProperties == null) {
                return null;
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            for (String next : HashMapExtensions.getKeys(this.__dynamicProperties)) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), next, HashMapExtensions.getItem(this.__dynamicProperties).get(next));
            }
            return hashMap;
        }
    }

    public Object getDynamicValue(String str) {
        synchronized (this.__dynamicPropertiesLock) {
            tryInitDynamicProperties();
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__dynamicProperties, str, holder);
            Object value = holder.getValue();
            if (tryGetValue) {
                return value;
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setDynamicProperties(HashMap<String, Object> hashMap) {
        synchronized (this.__dynamicPropertiesLock) {
            if (hashMap == null) {
                this.__dynamicProperties = null;
            } else {
                this.__dynamicProperties = new HashMap<>();
                for (String next : HashMapExtensions.getKeys(hashMap)) {
                    HashMapExtensions.set(HashMapExtensions.getItem(this.__dynamicProperties), next, HashMapExtensions.getItem(hashMap).get(next));
                }
            }
        }
    }

    public void setDynamicValue(String str, Object obj) {
        synchronized (this.__dynamicPropertiesLock) {
            tryInitDynamicProperties();
            HashMapExtensions.set(HashMapExtensions.getItem(this.__dynamicProperties), str, obj);
        }
    }

    private boolean tryInitDynamicProperties() {
        if (this.__dynamicProperties != null) {
            return false;
        }
        this.__dynamicProperties = new HashMap<>();
        return true;
    }

    public boolean unsetDynamicValue(String str) {
        boolean remove;
        synchronized (this.__dynamicPropertiesLock) {
            tryInitDynamicProperties();
            remove = HashMapExtensions.remove(this.__dynamicProperties, str);
        }
        return remove;
    }
}
