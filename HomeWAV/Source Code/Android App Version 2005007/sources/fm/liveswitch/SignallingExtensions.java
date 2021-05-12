package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class SignallingExtensions extends Dynamic {
    private HashMap<String, String> _extensionJsons;

    public static SignallingExtensions fromJson(String str) {
        return (SignallingExtensions) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingExtensions>() {
            public SignallingExtensions invoke() {
                return new SignallingExtensions();
            }
        }, new IAction3<SignallingExtensions, String, String>() {
            public void invoke(SignallingExtensions signallingExtensions, String str, String str2) {
                signallingExtensions.setValueJson(str, JsonSerializer.deserializeRaw(str2), false);
            }
        });
    }

    public int getCount() {
        return HashMapExtensions.getCount(getExtensionJsons());
    }

    private HashMap<String, String> getExtensionJsons() {
        return this._extensionJsons;
    }

    public ArrayList<String> getNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayListExtensions.addRange(arrayList, HashMapExtensions.getKeys(getExtensionJsons()));
        return arrayList;
    }

    public String getValueJson(String str) {
        if (getExtensionJsons().containsKey(str)) {
            return HashMapExtensions.getItem(getExtensionJsons()).get(str);
        }
        return null;
    }

    private void setExtensionJsons(HashMap<String, String> hashMap) {
        this._extensionJsons = hashMap;
    }

    public void setValueJson(String str, String str2) {
        setValueJson(str, str2, true);
    }

    public void setValueJson(String str, String str2, boolean z) {
        if (str2 == null) {
            if (getExtensionJsons().containsKey(str)) {
                HashMapExtensions.remove(getExtensionJsons(), str);
            }
        } else if (!z || JsonSerializer.isValidJson(str2)) {
            HashMapExtensions.set(HashMapExtensions.getItem(getExtensionJsons()), str, str2);
        } else {
            throw new RuntimeException(new Exception("The value is not valid JSON."));
        }
        super.setIsDirty(true);
    }

    public SignallingExtensions() {
        setExtensionJsons(new HashMap());
    }

    public static String toJson(SignallingExtensions signallingExtensions) {
        return JsonSerializer.serializeObjectFast(signallingExtensions, new IAction2<SignallingExtensions, HashMap<String, String>>() {
            public void invoke(SignallingExtensions signallingExtensions, HashMap<String, String> hashMap) {
                Iterator<String> it = signallingExtensions.getNames().iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), next, JsonSerializer.serializeRaw(signallingExtensions.getValueJson(next)));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
