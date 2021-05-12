package fm.liveswitch;

import java.util.HashMap;

public class WebhookInfo extends Info {
    private int _result;
    private String _url;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "url")) {
            setUrl(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "result")) {
            setResult(JsonSerializer.deserializeInteger(str2).getValue());
        }
    }

    public static WebhookInfo fromJson(String str) {
        return (WebhookInfo) JsonSerializer.deserializeObject(str, new IFunction0<WebhookInfo>() {
            public WebhookInfo invoke() {
                return new WebhookInfo();
            }
        }, new IAction3<WebhookInfo, String, String>() {
            public void invoke(WebhookInfo webhookInfo, String str, String str2) {
                webhookInfo.deserializeProperties(str, str2);
            }
        });
    }

    public int getResult() {
        return this._result;
    }

    public String getUrl() {
        return this._url;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getUrl() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "url", JsonSerializer.serializeString(getUrl()));
        }
        if (getResult() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "result", JsonSerializer.serializeInteger(new NullableInteger(getResult())));
        }
    }

    public void setResult(int i) {
        this._result = i;
    }

    public void setUrl(String str) {
        this._url = str;
    }

    public static String toJson(WebhookInfo webhookInfo) {
        return JsonSerializer.serializeObject(webhookInfo, new IAction2<WebhookInfo, HashMap<String, String>>() {
            public void invoke(WebhookInfo webhookInfo, HashMap<String, String> hashMap) {
                webhookInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
