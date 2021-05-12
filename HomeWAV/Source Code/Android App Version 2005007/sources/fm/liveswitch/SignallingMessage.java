package fm.liveswitch;

import com.urbanairship.MessageCenterDataManager;
import com.urbanairship.json.matchers.VersionMatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class SignallingMessage extends SignallingMessageBase {
    private SignallingAdvice __advice;
    private String __bayeuxChannel;
    private String[] __channels;
    private String __clientId;
    private SignallingConnectionType __connectionType;
    private String __id;
    private String[] __keys;
    private String __minimumVersion;
    private SignallingRecord[] __records;
    private SignallingConnectionType[] __supportedConnectionTypes;
    private String __version;
    private Object _toJsonNoDataLock;

    private static int bytesToInt(byte[] bArr, int i) {
        if (ArrayExtensions.getLength(bArr) < i + 4) {
            return -1;
        }
        return (int) Binary.fromBytes32(bArr, i, false);
    }

    /* access modifiers changed from: private */
    public static SignallingConnectionType deserializeConnectionType(String str) {
        String deserializeString = JsonSerializer.deserializeString(str);
        if (deserializeString.equals("long-polling")) {
            return SignallingConnectionType.LongPolling;
        }
        if (deserializeString.equals("callback-polling")) {
            return SignallingConnectionType.CallbackPolling;
        }
        if (deserializeString.equals("websocket")) {
            return SignallingConnectionType.WebSocket;
        }
        if (deserializeString.equals("iframe")) {
            return SignallingConnectionType.IFrame;
        }
        if (deserializeString.equals("flash")) {
            return SignallingConnectionType.Flash;
        }
        return SignallingConnectionType.NotSet;
    }

    /* access modifiers changed from: private */
    public static SignallingConnectionType[] deserializeConnectionTypeArray(String str) {
        if (StringExtensions.isNullOrEmpty(str) || Global.equals(str, "null") || StringExtensions.getLength(str) < 2) {
            return null;
        }
        if (str.charAt(0) != '[' || str.charAt(StringExtensions.getLength(str) - 1) != ']') {
            return null;
        }
        String[] split = StringExtensions.split(StringExtensions.substring(str, 1, StringExtensions.getLength(str) - 2), new char[]{','});
        SignallingConnectionType[] signallingConnectionTypeArr = new SignallingConnectionType[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            signallingConnectionTypeArr[i] = deserializeConnectionType(split[i]);
        }
        return signallingConnectionTypeArr;
    }

    private static HashMap<String, SignallingRemoteClient[]> deserializeRemoteClientTable(String str) {
        HashMap<String, SignallingRemoteClient[]> hashMap = (HashMap) JsonSerializer.deserializeObject(str, new IFunction0<HashMap<String, SignallingRemoteClient[]>>() {
            public HashMap<String, SignallingRemoteClient[]> invoke() {
                return new HashMap<>();
            }
        }, new IAction3<HashMap<String, SignallingRemoteClient[]>, String, String>() {
            public void invoke(HashMap<String, SignallingRemoteClient[]> hashMap, String str, String str2) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str, SignallingRemoteClient.fromJsonArray(str2));
            }
        });
        if (hashMap == null) {
            return null;
        }
        return hashMap;
    }

    public static SignallingMessage fromBinary(byte[] bArr) {
        return fromBinary(bArr, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r4 = r4 + 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static fm.liveswitch.SignallingMessage fromBinary(byte[] r3, int r4) {
        /*
            int r0 = bytesToInt(r3, r4)
            r1 = 0
            if (r0 >= 0) goto L_0x0008
            return r1
        L_0x0008:
            int r4 = r4 + 4
            int r2 = bytesToInt(r3, r4)
            if (r2 >= 0) goto L_0x0011
            return r1
        L_0x0011:
            int r4 = r4 + 4
            byte[] r1 = fm.liveswitch.BitAssistant.subArray(r3, r4, r0)
            int r4 = r4 + r0
            byte[] r3 = fm.liveswitch.BitAssistant.subArray(r3, r4, r2)
            r4 = 0
            int r0 = fm.liveswitch.ArrayExtensions.getLength((byte[]) r1)
            java.lang.String r4 = fm.liveswitch.Utf8.decode(r1, r4, r0)
            fm.liveswitch.SignallingMessage r4 = fromJson(r4)
            r4.setDataBytes(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SignallingMessage.fromBinary(byte[], int):fm.liveswitch.SignallingMessage");
    }

    public static SignallingMessage[] fromBinaryMultiple(byte[] bArr) {
        int bytesToInt = bytesToInt(bArr, 0);
        if (bytesToInt < 0) {
            return new SignallingMessage[0];
        }
        SignallingMessage[] signallingMessageArr = new SignallingMessage[bytesToInt];
        int i = 4;
        for (int i2 = 0; i2 < bytesToInt; i2++) {
            int bytesToInt2 = bytesToInt(bArr, i);
            if (bytesToInt2 < 0) {
                return new SignallingMessage[0];
            }
            int i3 = i + 4;
            SignallingMessage fromBinary = fromBinary(bArr, i3);
            if (fromBinary == null) {
                return new SignallingMessage[0];
            }
            i = i3 + bytesToInt2;
            signallingMessageArr[i2] = fromBinary;
        }
        return signallingMessageArr;
    }

    public static SignallingMessage fromJson(String str) {
        return (SignallingMessage) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingMessage>() {
            public SignallingMessage invoke() {
                return new SignallingMessage();
            }
        }, new IAction3<SignallingMessage, String, String>() {
            public void invoke(SignallingMessage signallingMessage, String str, String str2) {
                boolean z = false;
                signallingMessage.setValidate(false);
                if (str.equals("advice")) {
                    signallingMessage.setAdvice(SignallingAdvice.fromJson(str2));
                } else if (str.equals("records")) {
                    signallingMessage.setRecords(SignallingRecord.fromJsonArray(str2));
                } else if (str.equals("keys")) {
                    signallingMessage.setKeys(JsonSerializer.deserializeStringArray(str2));
                } else if (str.equals(Modules.CHANNEL_MODULE)) {
                    signallingMessage.setBayeuxChannel(JsonSerializer.deserializeString(str2));
                } else if (str.equals("clientId")) {
                    signallingMessage.setClientId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("connectionType")) {
                    signallingMessage.setConnectionType(SignallingMessage.deserializeConnectionType(str2));
                } else if (str.equals("data")) {
                    signallingMessage.setDataJson(JsonSerializer.deserializeRaw(str2));
                } else if (str.equals("error")) {
                    signallingMessage.setError(JsonSerializer.deserializeString(str2));
                } else if (str.equals("ext")) {
                    signallingMessage.setExtensions(SignallingExtensions.fromJson(str2));
                } else if (str.equals("id")) {
                    signallingMessage.setId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("minimumVersion")) {
                    signallingMessage.setMinimumVersion(JsonSerializer.deserializeString(str2));
                } else if (str.equals("subscription")) {
                    if (!str2.startsWith("[")) {
                        String deserializeString = JsonSerializer.deserializeString(str2);
                        if (!StringExtensions.isNullOrEmpty(deserializeString)) {
                            signallingMessage.setChannels(new String[]{deserializeString});
                        }
                    } else {
                        signallingMessage.setChannels(JsonSerializer.deserializeStringArray(str2));
                    }
                } else if (str.equals("successful")) {
                    NullableBoolean deserializeBoolean = JsonSerializer.deserializeBoolean(str2);
                    if (deserializeBoolean.hasValue() && deserializeBoolean.getValue()) {
                        z = true;
                    }
                    signallingMessage.setSuccessful(z);
                } else if (str.equals("supportedConnectionTypes")) {
                    signallingMessage.setSupportedConnectionTypes(SignallingMessage.deserializeConnectionTypeArray(str2));
                } else if (str.equals(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP)) {
                    signallingMessage.setTimestamp(new NullableDate(SignallingMessageBase.deserializeTimestamp(str2)));
                } else if (str.equals(VersionMatcher.ALTERNATE_VERSION_KEY)) {
                    signallingMessage.setVersion(JsonSerializer.deserializeString(str2));
                }
                signallingMessage.setValidate(true);
            }
        });
    }

    public static SignallingMessage[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, SignallingMessage>() {
            public String getId() {
                return "fm.liveswitch.SignallingMessage.fromJson";
            }

            public SignallingMessage invoke(String str) {
                return SignallingMessage.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (SignallingMessage[]) deserializeObjectArray.toArray(new SignallingMessage[0]);
    }

    public NullableBoolean getAcknowledgement() {
        return JsonSerializer.deserializeBoolean(super.getExtensionValueJson(SignallingExtensible.getAcknowledgementExtensionName()));
    }

    public SignallingAdvice getAdvice() {
        return this.__advice;
    }

    public String getAuthToken() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getAuthTokenExtensionName()));
    }

    public String getBayeuxChannel() {
        return this.__bayeuxChannel;
    }

    public String getChannel() {
        return SignallingExtensible.sharedGetChannel(this.__channels);
    }

    public String[] getChannels() {
        return SignallingExtensible.sharedGetChannels(this.__channels);
    }

    public String getClientId() {
        return this.__clientId;
    }

    public SignallingConnectionType getConnectionType() {
        return this.__connectionType;
    }

    public String getDeviceId() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getDeviceIdExtensionName()));
    }

    public NullableBoolean getDisableBinary() {
        return JsonSerializer.deserializeBoolean(super.getExtensionValueJson(SignallingExtensible.getDisableBinaryExtensionName()));
    }

    public String getId() {
        return this.__id;
    }

    public NullableBoolean getIsPrivate() {
        return JsonSerializer.deserializeBoolean(super.getExtensionValueJson(SignallingExtensible.getIsPrivateExtensionName()));
    }

    public String getKey() {
        return SignallingExtensible.sharedGetKey(this.__keys);
    }

    public String[] getKeys() {
        return SignallingExtensible.sharedGetKeys(this.__keys);
    }

    public String getMinimumVersion() {
        return this.__minimumVersion;
    }

    public NullableBoolean getPresence() {
        return JsonSerializer.deserializeBoolean(super.getExtensionValueJson(SignallingExtensible.getPresenceExtensionName()));
    }

    public SignallingRecord getRecord() {
        return SignallingExtensible.sharedGetRecord(this.__records);
    }

    public SignallingRecord[] getRecords() {
        return SignallingExtensible.sharedGetRecords(this.__records);
    }

    public SignallingRemoteClient getRemoteClient() {
        return SignallingRemoteClient.fromJson(super.getExtensionValueJson(SignallingExtensible.getRemoteClientExtensionName()));
    }

    public NullableBoolean getReturnData() {
        return JsonSerializer.deserializeBoolean(super.getExtensionValueJson(SignallingExtensible.getReturnDataExtensionName()));
    }

    public SignallingMessage[] getServerActions() {
        SignallingMessage[] fromJsonArray;
        String extensionValueJson = super.getExtensionValueJson(SignallingExtensible.getServerActionsExtensionName());
        if (StringExtensions.isNullOrEmpty(extensionValueJson) || (fromJsonArray = fromJsonArray(extensionValueJson)) == null) {
            return null;
        }
        return fromJsonArray;
    }

    public NullableInteger getServerTimeout() {
        return JsonSerializer.deserializeInteger(super.getExtensionValueJson(SignallingExtensible.getServerTimeoutExtensionName()));
    }

    public String getStreamId() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getStreamIdExtensionName()));
    }

    public HashMap<String, SignallingRemoteClient[]> getSubscribedClients() {
        return deserializeRemoteClientTable(super.getExtensionValueJson(SignallingExtensible.getSubscribedClientsExtensionName()));
    }

    public SignallingConnectionType[] getSupportedConnectionTypes() {
        return this.__supportedConnectionTypes;
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) != null ? JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) : StringExtensions.empty;
    }

    public SignallingMessageType getType() {
        return SignallingMetaChannels.getMessageType(getBayeuxChannel());
    }

    public String getUserId() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getUserIdExtensionName()));
    }

    public String getVersion() {
        return this.__version;
    }

    private static byte[] intToBytes(int i) {
        return Binary.toBytes32((long) i, false);
    }

    public boolean isBind() {
        return Global.equals(getType(), SignallingMessageType.Bind);
    }

    public boolean isBindingTo(String str) {
        if (!Global.equals(getType(), SignallingMessageType.Bind) || getRecords() == null) {
            return false;
        }
        for (SignallingRecord key : getRecords()) {
            if (Global.equals(key.getKey(), str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnect() {
        return Global.equals(getType(), SignallingMessageType.Connect);
    }

    public boolean isDisconnect() {
        return Global.equals(getType(), SignallingMessageType.Disconnect);
    }

    public boolean isPublish() {
        return Global.equals(getType(), SignallingMessageType.Publish);
    }

    public boolean isService() {
        return Global.equals(getType(), SignallingMessageType.Service);
    }

    public boolean isStream() {
        return Global.equals(getType(), SignallingMessageType.Stream);
    }

    public boolean isSubscribe() {
        return Global.equals(getType(), SignallingMessageType.Subscribe);
    }

    public boolean isSubscribingTo(String str) {
        if (!Global.equals(getType(), SignallingMessageType.Subscribe) || getChannels() == null) {
            return false;
        }
        for (String equals : getChannels()) {
            if (Global.equals(equals, str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUnbind() {
        return Global.equals(getType(), SignallingMessageType.Unbind);
    }

    public boolean isUnbindingFrom(String str) {
        if (!Global.equals(getType(), SignallingMessageType.Unbind) || getKeys() == null) {
            return false;
        }
        for (String equals : getKeys()) {
            if (Global.equals(equals, str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUnsubscribe() {
        return Global.equals(getType(), SignallingMessageType.Unsubscribe);
    }

    public boolean isUnsubscribingFrom(String str) {
        if (!Global.equals(getType(), SignallingMessageType.Unsubscribe) || getChannels() == null) {
            return false;
        }
        for (String equals : getChannels()) {
            if (Global.equals(equals, str)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static String serializeConnectionType(SignallingConnectionType signallingConnectionType) {
        String str;
        if (signallingConnectionType == SignallingConnectionType.WebSocket) {
            str = "websocket";
        } else if (signallingConnectionType == SignallingConnectionType.LongPolling) {
            str = "long-polling";
        } else if (signallingConnectionType == SignallingConnectionType.CallbackPolling) {
            str = "callback-polling";
        } else if (signallingConnectionType == SignallingConnectionType.IFrame) {
            str = "iframe";
        } else {
            str = signallingConnectionType == SignallingConnectionType.Flash ? "flash" : null;
        }
        return JsonSerializer.serializeString(str);
    }

    /* access modifiers changed from: private */
    public static String serializeConnectionTypeArray(SignallingConnectionType[] signallingConnectionTypeArr) {
        String[] strArr = new String[ArrayExtensions.getLength((Object[]) signallingConnectionTypeArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) signallingConnectionTypeArr); i++) {
            strArr[i] = serializeConnectionType(signallingConnectionTypeArr[i]);
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    private static String serializeRemoteClientTable(final HashMap<String, SignallingRemoteClient[]> hashMap) {
        return JsonSerializer.serializeObject(hashMap, new IAction2<HashMap<String, SignallingRemoteClient[]>, HashMap<String, String>>() {
            public void invoke(HashMap<String, SignallingRemoteClient[]> hashMap, HashMap<String, String> hashMap2) {
                for (String str : HashMapExtensions.getKeys(hashMap)) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap2), str, SignallingRemoteClient.toJsonArray((SignallingRemoteClient[]) HashMapExtensions.getItem(hashMap).get(str)));
                }
            }
        });
    }

    public void setAcknowledgement(NullableBoolean nullableBoolean) {
        super.setExtensionValueJson(SignallingExtensible.getAcknowledgementExtensionName(), JsonSerializer.serializeBoolean(nullableBoolean), false);
        super.setIsDirty(true);
    }

    public void setAdvice(SignallingAdvice signallingAdvice) {
        this.__advice = signallingAdvice;
        super.setIsDirty(true);
    }

    public void setAuthToken(String str) {
        super.setExtensionValueJson(SignallingExtensible.getAuthTokenExtensionName(), JsonSerializer.serializeString(str), false);
        super.setIsDirty(true);
    }

    public void setBayeuxChannel(String str) {
        this.__bayeuxChannel = str;
        if (SignallingMetaChannels.isServiceChannel(str)) {
            setChannel(SignallingMetaChannels.convertChannelFromServiced(str));
        } else if (!SignallingMetaChannels.isMetaChannel(str)) {
            setChannel(str);
        }
        super.setIsDirty(true);
    }

    public void setChannel(String str) {
        this.__channels = SignallingExtensible.sharedSetChannel(str, super.getValidate());
        super.setIsDirty(true);
    }

    public void setChannels(String[] strArr) {
        this.__channels = SignallingExtensible.sharedSetChannels(strArr, super.getValidate());
        super.setIsDirty(true);
    }

    public void setClientId(String str) {
        this.__clientId = str;
        super.setIsDirty(true);
    }

    public void setConnectionType(SignallingConnectionType signallingConnectionType) {
        this.__connectionType = signallingConnectionType;
        super.setIsDirty(true);
    }

    public void setDeviceId(String str) {
        super.setExtensionValueJson(SignallingExtensible.getDeviceIdExtensionName(), JsonSerializer.serializeString(str), false);
        super.setIsDirty(true);
    }

    public void setDisableBinary(NullableBoolean nullableBoolean) {
        super.setExtensionValueJson(SignallingExtensible.getDisableBinaryExtensionName(), JsonSerializer.serializeBoolean(nullableBoolean), false);
        super.setIsDirty(true);
    }

    public void setId(String str) {
        this.__id = str;
        super.setIsDirty(true);
    }

    public void setIsPrivate(NullableBoolean nullableBoolean) {
        super.setExtensionValueJson(SignallingExtensible.getIsPrivateExtensionName(), JsonSerializer.serializeBoolean(nullableBoolean), false);
        super.setIsDirty(true);
    }

    public void setKey(String str) {
        this.__keys = SignallingExtensible.sharedSetKey(str, super.getValidate());
        super.setIsDirty(true);
    }

    public void setKeys(String[] strArr) {
        this.__keys = SignallingExtensible.sharedSetKeys(strArr, super.getValidate());
        super.setIsDirty(true);
    }

    public void setMinimumVersion(String str) {
        this.__minimumVersion = str;
        super.setIsDirty(true);
    }

    public void setPresence(NullableBoolean nullableBoolean) {
        super.setExtensionValueJson(SignallingExtensible.getPresenceExtensionName(), JsonSerializer.serializeBoolean(nullableBoolean), false);
        super.setIsDirty(true);
    }

    public void setRecord(SignallingRecord signallingRecord) {
        this.__records = SignallingExtensible.sharedSetRecord(signallingRecord, super.getValidate());
        super.setIsDirty(true);
    }

    public void setRecords(SignallingRecord[] signallingRecordArr) {
        this.__records = SignallingExtensible.sharedSetRecords(signallingRecordArr, super.getValidate());
        super.setIsDirty(true);
    }

    public void setRemoteClient(SignallingRemoteClient signallingRemoteClient) {
        super.setExtensionValueJson(SignallingExtensible.getRemoteClientExtensionName(), SignallingRemoteClient.toJson(signallingRemoteClient), false);
        super.setIsDirty(true);
    }

    public void setReturnData(NullableBoolean nullableBoolean) {
        super.setExtensionValueJson(SignallingExtensible.getReturnDataExtensionName(), JsonSerializer.serializeBoolean(nullableBoolean), false);
        super.setIsDirty(true);
    }

    public void setServerActions(SignallingMessage[] signallingMessageArr) {
        super.setExtensionValueJson(SignallingExtensible.getServerActionsExtensionName(), toJsonArray(signallingMessageArr), false);
        super.setIsDirty(true);
    }

    public void setServerTimeout(NullableInteger nullableInteger) {
        super.setExtensionValueJson(SignallingExtensible.getServerTimeoutExtensionName(), JsonSerializer.serializeInteger(nullableInteger), false);
        super.setIsDirty(true);
    }

    public void setStreamId(String str) {
        super.setExtensionValueJson(SignallingExtensible.getStreamIdExtensionName(), JsonSerializer.serializeString(str), false);
        super.setIsDirty(true);
    }

    public void setSubscribedClients(HashMap<String, SignallingRemoteClient[]> hashMap) {
        super.setExtensionValueJson(SignallingExtensible.getSubscribedClientsExtensionName(), serializeRemoteClientTable(hashMap), false);
        super.setIsDirty(true);
    }

    public void setSupportedConnectionTypes(SignallingConnectionType[] signallingConnectionTypeArr) {
        this.__supportedConnectionTypes = signallingConnectionTypeArr;
        super.setIsDirty(true);
    }

    public void setTag(String str) {
        String tagExtensionName = SignallingExtensible.getTagExtensionName();
        if (str == null) {
            str = StringExtensions.empty;
        }
        super.setExtensionValueJson(tagExtensionName, JsonSerializer.serializeString(str), false);
        super.setIsDirty(true);
    }

    public void setUserId(String str) {
        super.setExtensionValueJson(SignallingExtensible.getUserIdExtensionName(), JsonSerializer.serializeString(str), false);
        super.setIsDirty(true);
    }

    public void setVersion(String str) {
        this.__version = str;
        super.setIsDirty(true);
    }

    SignallingMessage() {
        this._toJsonNoDataLock = new Object();
        this.__connectionType = SignallingConnectionType.NotSet;
    }

    public SignallingMessage(String str) {
        this();
        super.setValidate(false);
        setBayeuxChannel(str);
        super.setValidate(true);
    }

    public static byte[] toBinary(SignallingMessage signallingMessage) {
        Holder holder = new Holder(null);
        Holder holder2 = new Holder(null);
        String jsonNoData = signallingMessage.toJsonNoData(holder, holder2);
        byte[] bArr = (byte[]) holder.getValue();
        String str = (String) holder2.getValue();
        if (bArr == null) {
            if (str == null) {
                throw new RuntimeException(new Exception("Message data bytes cannot be null."));
            }
            throw new RuntimeException(new Exception(StringExtensions.format("Message data bytes cannot be null (JSON: {0}).", (Object) str)));
        } else if (jsonNoData != null) {
            byte[] encode = Utf8.encode(jsonNoData);
            byte[] bArr2 = new byte[(ArrayExtensions.getLength(encode) + 8 + ArrayExtensions.getLength(bArr))];
            BitAssistant.copy(intToBytes(ArrayExtensions.getLength(encode)), 0, bArr2, 0, 4);
            BitAssistant.copy(intToBytes(ArrayExtensions.getLength(bArr)), 0, bArr2, 4, 4);
            BitAssistant.copy(encode, 0, bArr2, 8, ArrayExtensions.getLength(encode));
            BitAssistant.copy(bArr, 0, bArr2, 8 + ArrayExtensions.getLength(encode), ArrayExtensions.getLength(bArr));
            ArrayExtensions.getLength(bArr);
            return bArr2;
        } else {
            throw new RuntimeException(new Exception("Message JSON cannot be null."));
        }
    }

    public byte[] toBinary() {
        return toBinary(this);
    }

    public static byte[] toBinaryMultiple(SignallingMessage[] signallingMessageArr) {
        ArrayList arrayList = new ArrayList();
        int i = 4;
        for (SignallingMessage binary : signallingMessageArr) {
            byte[] binary2 = toBinary(binary);
            arrayList.add(binary2);
            i += ArrayExtensions.getLength(binary2) + 4;
        }
        byte[] bArr = new byte[i];
        BitAssistant.copy(intToBytes(ArrayListExtensions.getCount(arrayList)), 0, bArr, 0, 4);
        Iterator it = arrayList.iterator();
        int i2 = 4;
        while (it.hasNext()) {
            byte[] bArr2 = (byte[]) it.next();
            BitAssistant.copy(intToBytes(ArrayExtensions.getLength(bArr2)), 0, bArr, i2, 4);
            int i3 = i2 + 4;
            BitAssistant.copy(bArr2, 0, bArr, i3, ArrayExtensions.getLength(bArr2));
            i2 = i3 + ArrayExtensions.getLength(bArr2);
        }
        return bArr;
    }

    public static String toJson(SignallingMessage signallingMessage) {
        return JsonSerializer.serializeObjectFast(signallingMessage, new IAction2<SignallingMessage, HashMap<String, String>>() {
            public void invoke(SignallingMessage signallingMessage, HashMap<String, String> hashMap) {
                if (signallingMessage.getClientId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientId", JsonSerializer.serializeString(signallingMessage.getClientId()));
                }
                if (signallingMessage.getTimestamp().getHasValue()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, SignallingMessageBase.serializeTimestamp(signallingMessage.getTimestamp()));
                }
                if (signallingMessage.getAdvice() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "advice", SignallingAdvice.toJson(signallingMessage.getAdvice()));
                }
                if (signallingMessage.getRecords() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "records", SignallingRecord.toJsonArray(signallingMessage.getRecords()));
                }
                if (signallingMessage.getKeys() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "keys", JsonSerializer.serializeStringArray(signallingMessage.getKeys()));
                }
                if (signallingMessage.getBayeuxChannel() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), Modules.CHANNEL_MODULE, JsonSerializer.serializeString(signallingMessage.getBayeuxChannel()));
                }
                if (!Global.equals(signallingMessage.getConnectionType(), SignallingConnectionType.NotSet)) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "connectionType", SignallingMessage.serializeConnectionType(signallingMessage.getConnectionType()));
                }
                if (signallingMessage.getDataJson() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "data", JsonSerializer.serializeRaw(signallingMessage.getDataJson()));
                }
                if (signallingMessage.getError() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "error", JsonSerializer.serializeString(signallingMessage.getError()));
                }
                if (signallingMessage.getExtensions().getCount() > 0) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ext", SignallingExtensions.toJson(signallingMessage.getExtensions()));
                }
                if (signallingMessage.getId() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "id", JsonSerializer.serializeString(signallingMessage.getId()));
                }
                if (signallingMessage.getMinimumVersion() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "minimumVersion", JsonSerializer.serializeString(signallingMessage.getMinimumVersion()));
                }
                if (signallingMessage.getChannels() != null && (Global.equals(signallingMessage.getType(), SignallingMessageType.Subscribe) || Global.equals(signallingMessage.getType(), SignallingMessageType.Unsubscribe))) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "subscription", JsonSerializer.serializeStringArray(signallingMessage.getChannels()));
                }
                if (signallingMessage.getSuccessful()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "successful", JsonSerializer.serializeBoolean(new NullableBoolean(signallingMessage.getSuccessful())));
                }
                if (signallingMessage.getSupportedConnectionTypes() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "supportedConnectionTypes", SignallingMessage.serializeConnectionTypeArray(signallingMessage.getSupportedConnectionTypes()));
                }
                if (signallingMessage.getVersion() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), VersionMatcher.ALTERNATE_VERSION_KEY, JsonSerializer.serializeString(signallingMessage.getVersion()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(SignallingMessage[] signallingMessageArr) {
        return JsonSerializer.serializeObjectArray(signallingMessageArr, new IFunctionDelegate1<SignallingMessage, String>() {
            public String getId() {
                return "fm.liveswitch.SignallingMessage.toJson";
            }

            public String invoke(SignallingMessage signallingMessage) {
                return SignallingMessage.toJson(signallingMessage);
            }
        });
    }

    private String toJsonNoData(Holder<byte[]> holder, Holder<String> holder2) {
        synchronized (this._toJsonNoDataLock) {
            if (super.getIsBinary()) {
                holder.setValue(super.getDataBytes());
                holder2.setValue(null);
                super.setDataBytes((byte[]) null);
                String json = toJson();
                super.setDataBytes(holder.getValue());
                return json;
            }
            holder.setValue(null);
            holder2.setValue(super.getDataJson());
            super.setDataJson((String) null);
            String json2 = toJson();
            super.setDataJson(holder2.getValue());
            return json2;
        }
    }
}
