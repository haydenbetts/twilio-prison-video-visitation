package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

class SignallingSubscription extends Dynamic {
    private String __channel;
    private String __tag;

    public SignallingSubscription duplicate() {
        return new SignallingSubscription(getChannel(), getTag());
    }

    public static SignallingSubscription fromJson(String str) {
        return (SignallingSubscription) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingSubscription>() {
            public SignallingSubscription invoke() {
                return new SignallingSubscription("/");
            }
        }, new IAction3<SignallingSubscription, String, String>() {
            public void invoke(SignallingSubscription signallingSubscription, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, Modules.CHANNEL_MODULE)) {
                    signallingSubscription.setChannel(JsonSerializer.deserializeString(str2));
                } else if (Global.equals(str, "tag")) {
                    signallingSubscription.setTag(JsonSerializer.deserializeString(str2));
                }
            }
        });
    }

    public static SignallingSubscription[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, SignallingSubscription>() {
            public String getId() {
                return "fm.liveswitch.SignallingSubscription.fromJson";
            }

            public SignallingSubscription invoke(String str) {
                return SignallingSubscription.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (SignallingSubscription[]) deserializeObjectArray.toArray(new SignallingSubscription[0]);
    }

    public String getChannel() {
        return this.__channel;
    }

    public String getTag() {
        String str = this.__tag;
        return str != null ? str : StringExtensions.empty;
    }

    public void setChannel(String str) {
        if (str != null) {
            this.__channel = str;
            super.setIsDirty(true);
            return;
        }
        throw new RuntimeException(new Exception("channel cannot be null."));
    }

    public void setTag(String str) {
        if (str == null) {
            str = StringExtensions.empty;
        }
        this.__tag = str;
        super.setIsDirty(true);
    }

    public SignallingSubscription(String str) {
        setChannel(str);
    }

    public SignallingSubscription(String str, String str2) {
        setChannel(str);
        setTag(str2);
    }

    public static String toJson(SignallingSubscription signallingSubscription) {
        return JsonSerializer.serializeObjectFast(signallingSubscription, new IAction2<SignallingSubscription, HashMap<String, String>>(signallingSubscription) {
            final /* synthetic */ SignallingSubscription val$subscription;

            {
                this.val$subscription = r1;
            }

            public void invoke(SignallingSubscription signallingSubscription, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), Modules.CHANNEL_MODULE, JsonSerializer.serializeString(signallingSubscription.getChannel()));
                if (this.val$subscription.getTag() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(signallingSubscription.getTag()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(SignallingSubscription[] signallingSubscriptionArr) {
        return JsonSerializer.serializeObjectArray(signallingSubscriptionArr, new IFunctionDelegate1<SignallingSubscription, String>() {
            public String getId() {
                return "fm.liveswitch.SignallingSubscription.toJson";
            }

            public String invoke(SignallingSubscription signallingSubscription) {
                return SignallingSubscription.toJson(signallingSubscription);
            }
        });
    }
}
