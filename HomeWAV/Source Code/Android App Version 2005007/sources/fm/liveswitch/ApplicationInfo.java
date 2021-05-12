package fm.liveswitch;

import java.util.HashMap;

public class ApplicationInfo extends Info {
    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
    }

    public static ApplicationInfo fromJson(String str) {
        return (ApplicationInfo) JsonSerializer.deserializeObject(str, new IFunction0<ApplicationInfo>() {
            public ApplicationInfo invoke() {
                return new ApplicationInfo();
            }
        }, new IAction3<ApplicationInfo, String, String>() {
            public void invoke(ApplicationInfo applicationInfo, String str, String str2) {
                applicationInfo.deserializeProperties(str, str2);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
    }

    public static String toJson(ApplicationInfo applicationInfo) {
        return JsonSerializer.serializeObject(applicationInfo, new IAction2<ApplicationInfo, HashMap<String, String>>() {
            public void invoke(ApplicationInfo applicationInfo, HashMap<String, String> hashMap) {
                applicationInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
