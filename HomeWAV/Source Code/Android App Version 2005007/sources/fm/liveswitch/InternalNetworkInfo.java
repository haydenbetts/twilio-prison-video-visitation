package fm.liveswitch;

import java.util.HashMap;

public class InternalNetworkInfo extends NetworkInfo {
    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
    }

    public static InternalNetworkInfo fromJson(String str) {
        return (InternalNetworkInfo) JsonSerializer.deserializeObject(str, new IFunction0<InternalNetworkInfo>() {
            public InternalNetworkInfo invoke() {
                return new InternalNetworkInfo();
            }
        }, new IAction3<InternalNetworkInfo, String, String>() {
            public void invoke(InternalNetworkInfo internalNetworkInfo, String str, String str2) {
                internalNetworkInfo.deserializeProperties(str, str2);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
    }

    public static String toJson(InternalNetworkInfo internalNetworkInfo) {
        return JsonSerializer.serializeObject(internalNetworkInfo, new IAction2<InternalNetworkInfo, HashMap<String, String>>() {
            public void invoke(InternalNetworkInfo internalNetworkInfo, HashMap<String, String> hashMap) {
                internalNetworkInfo.serializeProperties(hashMap);
            }
        });
    }
}
