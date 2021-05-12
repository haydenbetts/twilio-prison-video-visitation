package fm.liveswitch;

import com.urbanairship.MessageCenterDataManager;
import java.util.ArrayList;
import java.util.HashMap;

class SignallingPublication extends SignallingMessageBase {
    private String __channel;

    public static SignallingPublication fromJson(String str) {
        return (SignallingPublication) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingPublication>() {
            public SignallingPublication invoke() {
                return new SignallingPublication();
            }
        }, new IAction3<SignallingPublication, String, String>() {
            public void invoke(SignallingPublication signallingPublication, String str, String str2) {
                boolean z = false;
                signallingPublication.setValidate(false);
                if (str != null) {
                    if (Global.equals(str, Modules.CHANNEL_MODULE)) {
                        signallingPublication.setChannel(JsonSerializer.deserializeString(str2));
                    } else if (Global.equals(str, "data")) {
                        signallingPublication.setDataJson(JsonSerializer.deserializeRaw(str2));
                    } else if (Global.equals(str, "ext")) {
                        signallingPublication.setExtensions(SignallingExtensions.fromJson(str2));
                    } else if (Global.equals(str, "successful")) {
                        NullableBoolean deserializeBoolean = JsonSerializer.deserializeBoolean(str2);
                        if (deserializeBoolean.hasValue() && deserializeBoolean.getValue()) {
                            z = true;
                        }
                        signallingPublication.setSuccessful(z);
                    } else if (Global.equals(str, "error")) {
                        signallingPublication.setError(JsonSerializer.deserializeString(str2));
                    } else if (Global.equals(str, MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP)) {
                        signallingPublication.setTimestamp(new NullableDate(SignallingMessageBase.deserializeTimestamp(str2)));
                    }
                }
                signallingPublication.setValidate(true);
            }
        });
    }

    public static SignallingPublication[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, SignallingPublication>() {
            public String getId() {
                return "fm.liveswitch.SignallingPublication.fromJson";
            }

            public SignallingPublication invoke(String str) {
                return SignallingPublication.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (SignallingPublication[]) deserializeObjectArray.toArray(new SignallingPublication[0]);
    }

    public static SignallingPublication fromMessage(SignallingMessage signallingMessage) {
        if (signallingMessage == null) {
            return null;
        }
        SignallingPublication signallingPublication = new SignallingPublication();
        signallingPublication.setChannel(signallingMessage.getBayeuxChannel());
        signallingPublication.setSuccessful(signallingMessage.getSuccessful());
        signallingPublication.setError(signallingMessage.getError());
        signallingPublication.setTimestamp(signallingMessage.getTimestamp());
        signallingPublication.setDataJson(signallingMessage.getDataJson());
        signallingPublication.setExtensions(signallingMessage.getExtensions());
        return signallingPublication;
    }

    public static SignallingPublication[] fromMessages(SignallingMessage[] signallingMessageArr) {
        if (signallingMessageArr == null) {
            return null;
        }
        SignallingPublication[] signallingPublicationArr = new SignallingPublication[ArrayExtensions.getLength((Object[]) signallingMessageArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) signallingMessageArr); i++) {
            signallingPublicationArr[i] = fromMessage(signallingMessageArr[i]);
        }
        return signallingPublicationArr;
    }

    public String getChannel() {
        return this.__channel;
    }

    public NullableBoolean getPresence() {
        return JsonSerializer.deserializeBoolean(super.getExtensionValueJson(SignallingExtensible.getPresenceExtensionName()));
    }

    public NullableBoolean getReturnData() {
        return JsonSerializer.deserializeBoolean(super.getExtensionValueJson(SignallingExtensible.getReturnDataExtensionName()));
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName()));
    }

    public void setChannel(String str) {
        if (str == null) {
            this.__channel = str;
            super.setIsDirty(true);
            return;
        }
        Holder holder = new Holder(null);
        boolean validateChannel = SignallingExtensible.validateChannel(str, holder);
        String str2 = (String) holder.getValue();
        if (!super.getValidate() || validateChannel) {
            this.__channel = str;
            super.setIsDirty(true);
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Invalid channel. {0}", (Object) str2)));
    }

    public void setPresence(NullableBoolean nullableBoolean) {
        super.setExtensionValueJson(SignallingExtensible.getPresenceExtensionName(), JsonSerializer.serializeBoolean(nullableBoolean), false);
        super.setIsDirty(true);
    }

    public void setReturnData(NullableBoolean nullableBoolean) {
        super.setExtensionValueJson(SignallingExtensible.getTagExtensionName(), JsonSerializer.serializeBoolean(nullableBoolean), false);
        super.setIsDirty(true);
    }

    public void setTag(String str) {
        super.setExtensionValueJson(SignallingExtensible.getTagExtensionName(), JsonSerializer.serializeString(str), false);
        super.setIsDirty(true);
    }

    public SignallingPublication() {
    }

    public SignallingPublication(String str) {
        setChannel(str);
    }

    public SignallingPublication(String str, byte[] bArr) {
        this(str, bArr, (String) null);
    }

    public SignallingPublication(String str, byte[] bArr, String str2) {
        setChannel(str);
        super.setDataBytes(bArr);
        setTag(str2);
    }

    public SignallingPublication(String str, String str2) {
        this(str, str2, (String) null);
    }

    public SignallingPublication(String str, String str2, String str3) {
        setChannel(str);
        super.setDataJson(str2);
        setTag(str3);
    }

    public static String toJson(SignallingPublication signallingPublication) {
        return JsonSerializer.serializeObjectFast(signallingPublication, new IAction2<SignallingPublication, HashMap<String, String>>() {
            public void invoke(SignallingPublication signallingPublication, HashMap<String, String> hashMap) {
                if (signallingPublication.getChannel() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), Modules.CHANNEL_MODULE, JsonSerializer.serializeString(signallingPublication.getChannel()));
                }
                if (signallingPublication.getError() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "error", JsonSerializer.serializeString(signallingPublication.getError()));
                }
                if (signallingPublication.getSuccessful()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "successful", JsonSerializer.serializeBoolean(new NullableBoolean(signallingPublication.getSuccessful())));
                }
                if (signallingPublication.getTimestamp().getHasValue()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, SignallingMessageBase.serializeTimestamp(signallingPublication.getTimestamp()));
                }
                if (signallingPublication.getExtensions().getCount() > 0) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ext", SignallingExtensions.toJson(signallingPublication.getExtensions()));
                }
                if (signallingPublication.getDataJson() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "data", JsonSerializer.serializeRaw(signallingPublication.getDataJson()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(SignallingPublication[] signallingPublicationArr) {
        return JsonSerializer.serializeObjectArray(signallingPublicationArr, new IFunctionDelegate1<SignallingPublication, String>() {
            public String getId() {
                return "fm.liveswitch.SignallingPublication.toJson";
            }

            public String invoke(SignallingPublication signallingPublication) {
                return SignallingPublication.toJson(signallingPublication);
            }
        });
    }

    public static SignallingMessage toMessage(SignallingPublication signallingPublication) {
        if (signallingPublication == null) {
            return null;
        }
        SignallingMessage signallingMessage = new SignallingMessage();
        signallingMessage.setBayeuxChannel(signallingPublication.getChannel());
        signallingMessage.setSuccessful(signallingPublication.getSuccessful());
        signallingMessage.setError(signallingPublication.getError());
        signallingMessage.setTimestamp(signallingPublication.getTimestamp());
        signallingMessage.setDataJson(signallingPublication.getDataJson());
        signallingMessage.setExtensions(signallingPublication.getExtensions());
        return signallingMessage;
    }

    public static SignallingMessage[] toMessages(SignallingPublication[] signallingPublicationArr) {
        if (signallingPublicationArr == null) {
            return null;
        }
        SignallingMessage[] signallingMessageArr = new SignallingMessage[ArrayExtensions.getLength((Object[]) signallingPublicationArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) signallingPublicationArr); i++) {
            signallingMessageArr[i] = toMessage(signallingPublicationArr[i]);
        }
        return signallingMessageArr;
    }
}
