package fm.liveswitch;

import java.util.HashMap;

public class ProxyCredentialsCache {
    private ManagedConcurrentDictionary<String, ProxyCredentials> _cache;
    private ProxyCredentials _defaultCredentials;

    /* access modifiers changed from: private */
    public static ManagedConcurrentDictionary<String, ProxyCredentials> deserializeCache(String str) {
        ManagedConcurrentDictionary<String, ProxyCredentials> managedConcurrentDictionary = new ManagedConcurrentDictionary<>();
        HashMap deserializeDictionary = JsonSerializer.deserializeDictionary(str, new IFunction0<HashMap<String, ProxyCredentials>>() {
            public HashMap<String, ProxyCredentials> invoke() {
                return new HashMap<>();
            }
        }, new IFunction1<String, ProxyCredentials>() {
            public ProxyCredentials invoke(String str) {
                return ProxyCredentials.fromJson(str);
            }
        });
        for (String str2 : HashMapExtensions.getKeys(deserializeDictionary)) {
            managedConcurrentDictionary.tryAdd(str2, HashMapExtensions.getItem(deserializeDictionary).get(str2));
        }
        return managedConcurrentDictionary;
    }

    public static ProxyCredentialsCache fromJson(String str) {
        return (ProxyCredentialsCache) JsonSerializer.deserializeObject(str, new IFunction0<ProxyCredentialsCache>() {
            public ProxyCredentialsCache invoke() {
                return new ProxyCredentialsCache();
            }
        }, new IAction3<ProxyCredentialsCache, String, String>() {
            public void invoke(ProxyCredentialsCache proxyCredentialsCache, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "defaultCredentials")) {
                    proxyCredentialsCache.setDefaultCredentials(ProxyCredentials.fromJson(str2));
                } else if (Global.equals(str, "cache")) {
                    proxyCredentialsCache.setCache(ProxyCredentialsCache.deserializeCache(str2));
                }
            }
        });
    }

    public ManagedConcurrentDictionary<String, ProxyCredentials> getCache() {
        return this._cache;
    }

    public ProxyCredentials getCredentials(String str) {
        Holder holder = new Holder(null);
        getCache().tryGetValue(str, holder);
        ProxyCredentials proxyCredentials = (ProxyCredentials) holder.getValue();
        return proxyCredentials != null ? proxyCredentials : getDefaultCredentials();
    }

    public ProxyCredentials getDefaultCredentials() {
        return this._defaultCredentials;
    }

    public ProxyCredentialsCache() {
        setCache(new ManagedConcurrentDictionary());
    }

    /* access modifiers changed from: private */
    public static String serializeCache(ManagedConcurrentDictionary<String, ProxyCredentials> managedConcurrentDictionary) {
        HashMap hashMap = new HashMap();
        for (String next : managedConcurrentDictionary.getKeys()) {
            Holder holder = new Holder(null);
            boolean tryGetValue = managedConcurrentDictionary.tryGetValue(next, holder);
            ProxyCredentials proxyCredentials = (ProxyCredentials) holder.getValue();
            if (tryGetValue) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), next, proxyCredentials);
            }
        }
        return JsonSerializer.serializeDictionary(hashMap, new IFunctionDelegate1<ProxyCredentials, String>() {
            public String getId() {
                return "fm.liveswitch.ProxyCredentials.toJson";
            }

            public String invoke(ProxyCredentials proxyCredentials) {
                return ProxyCredentials.toJson(proxyCredentials);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setCache(ManagedConcurrentDictionary<String, ProxyCredentials> managedConcurrentDictionary) {
        this._cache = managedConcurrentDictionary;
    }

    public void setCredentials(String str, ProxyCredentials proxyCredentials) {
        if (str == null) {
            setDefaultCredentials(proxyCredentials);
            return;
        }
        setCredentials(new String[]{str}, proxyCredentials);
    }

    public void setCredentials(String[] strArr, ProxyCredentials proxyCredentials) {
        if (strArr == null) {
            setDefaultCredentials(proxyCredentials);
            return;
        }
        for (String addOrUpdate : strArr) {
            getCache().addOrUpdate(addOrUpdate, proxyCredentials);
        }
    }

    public void setDefaultCredentials(ProxyCredentials proxyCredentials) {
        this._defaultCredentials = proxyCredentials;
    }

    public static String toJson(ProxyCredentialsCache proxyCredentialsCache) {
        return JsonSerializer.serializeObject(proxyCredentialsCache, new IAction2<ProxyCredentialsCache, HashMap<String, String>>() {
            public void invoke(ProxyCredentialsCache proxyCredentialsCache, HashMap<String, String> hashMap) {
                if (proxyCredentialsCache.getDefaultCredentials() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "defaultCredentials", ProxyCredentials.toJson(proxyCredentialsCache.getDefaultCredentials()));
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "cache", ProxyCredentialsCache.serializeCache(proxyCredentialsCache.getCache()));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
