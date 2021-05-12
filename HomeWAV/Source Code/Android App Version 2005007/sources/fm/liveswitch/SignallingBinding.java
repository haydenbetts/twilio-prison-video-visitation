package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

class SignallingBinding extends Dynamic {
    private boolean __isPrivate;
    private SignallingRecord __record;

    public SignallingBinding duplicate() {
        return new SignallingBinding(getRecord(), getIsPrivate());
    }

    public static SignallingBinding fromJson(String str) {
        return (SignallingBinding) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingBinding>() {
            public SignallingBinding invoke() {
                return new SignallingBinding(new SignallingRecord("key"));
            }
        }, new IAction3<SignallingBinding, String, String>() {
            public void invoke(SignallingBinding signallingBinding, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "record")) {
                    signallingBinding.setRecord(SignallingRecord.fromJson(str2));
                } else if (Global.equals(str, "isPrivate")) {
                    NullableBoolean deserializeBoolean = JsonSerializer.deserializeBoolean(str2);
                    if (deserializeBoolean.getHasValue()) {
                        signallingBinding.setIsPrivate(deserializeBoolean.getValue());
                    }
                }
            }
        });
    }

    public static SignallingBinding[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, SignallingBinding>() {
            public String getId() {
                return "fm.liveswitch.SignallingBinding.fromJson";
            }

            public SignallingBinding invoke(String str) {
                return SignallingBinding.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (SignallingBinding[]) deserializeObjectArray.toArray(new SignallingBinding[0]);
    }

    public boolean getIsPrivate() {
        return this.__isPrivate;
    }

    public SignallingRecord getRecord() {
        return this.__record;
    }

    public void setIsPrivate(boolean z) {
        this.__isPrivate = z;
        super.setIsDirty(true);
    }

    public void setRecord(SignallingRecord signallingRecord) {
        if (signallingRecord != null) {
            this.__record = signallingRecord;
            super.setIsDirty(true);
            return;
        }
        throw new RuntimeException(new Exception("record cannot be null."));
    }

    public SignallingBinding(SignallingRecord signallingRecord) {
        this(signallingRecord, false);
    }

    public SignallingBinding(SignallingRecord signallingRecord, boolean z) {
        setRecord(signallingRecord);
        setIsPrivate(z);
    }

    public static String toJson(SignallingBinding signallingBinding) {
        return JsonSerializer.serializeObjectFast(signallingBinding, new IAction2<SignallingBinding, HashMap<String, String>>() {
            public void invoke(SignallingBinding signallingBinding, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "record", SignallingRecord.toJson(signallingBinding.getRecord()));
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "isPrivate", JsonSerializer.serializeBoolean(new NullableBoolean(signallingBinding.getIsPrivate())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(SignallingBinding[] signallingBindingArr) {
        return JsonSerializer.serializeObjectArray(signallingBindingArr, new IFunctionDelegate1<SignallingBinding, String>() {
            public String getId() {
                return "fm.liveswitch.SignallingBinding.toJson";
            }

            public String invoke(SignallingBinding signallingBinding) {
                return SignallingBinding.toJson(signallingBinding);
            }
        });
    }
}
