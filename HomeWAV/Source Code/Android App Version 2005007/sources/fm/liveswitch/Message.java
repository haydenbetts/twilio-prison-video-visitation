package fm.liveswitch;

import com.urbanairship.MessageCenterDataManager;
import fm.liveswitch.dtmf.Tone;
import java.util.ArrayList;
import java.util.HashMap;

public class Message extends Dynamic {
    private String _apiVersion;
    private String _applicationId;
    private String _channelId;
    private ChannelReport[] _channelReports;
    private String _clientId;
    private String[] _clientRoles;
    private String _clientTag;
    private String _connectionId;
    private String _connectionTag;
    private String _connectionType;
    private String _connectorId;
    private String _deviceAlias;
    private String _deviceId;
    private String _mediaId;
    private String _mediaServerId;
    private String _payload;
    private String _region;
    private String _remoteClientId;
    private String _remoteConnectionId;
    private String _remoteDeviceId;
    private String _remoteMediaId;
    private String _remoteUserId;
    private long _timestamp;
    private String _token;
    private String _type;
    private String _userAlias;
    private String _userId;

    public Message clone() {
        String[] strArr;
        String[] clientRoles = getClientRoles();
        if (clientRoles != null) {
            strArr = new String[ArrayExtensions.getLength((Object[]) clientRoles)];
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) strArr); i++) {
                strArr[i] = clientRoles[i];
            }
        } else {
            strArr = null;
        }
        Message message = new Message();
        message.setApiVersion(getApiVersion());
        message.setApplicationId(getApplicationId());
        message.setChannelId(getChannelId());
        message.setClientId(getClientId());
        message.setClientTag(getClientTag());
        message.setClientRoles(strArr);
        message.setConnectionId(getConnectionId());
        message.setConnectionTag(getConnectionTag());
        message.setConnectionType(getConnectionType());
        message.setConnectorId(getConnectorId());
        message.setDeviceAlias(getDeviceAlias());
        message.setDeviceId(getDeviceId());
        message.setTimestamp(getTimestamp());
        message.setMediaServerId(getMediaServerId());
        message.setMediaId(getMediaId());
        message.setPayload(getPayload());
        message.setRemoteClientId(getRemoteClientId());
        message.setRemoteConnectionId(getRemoteConnectionId());
        message.setRemoteDeviceId(getRemoteDeviceId());
        message.setRemoteMediaId(getRemoteMediaId());
        message.setRemoteUserId(getRemoteUserId());
        message.setRegion(getRegion());
        message.setToken(getToken());
        message.setType(getType());
        message.setUserAlias(getUserAlias());
        message.setUserId(getUserId());
        return message;
    }

    public static Message createBitrateNotificationMessage(BitrateNotification bitrateNotification) {
        return createBitrateNotificationMessage(new BitrateNotification[]{bitrateNotification});
    }

    public static Message createBitrateNotificationMessage(BitrateNotification[] bitrateNotificationArr) {
        Message message = new Message(MessageType.getBitrateNotification());
        message.setPayload(BitrateNotification.toJsonArray(bitrateNotificationArr));
        return message;
    }

    public static Message createBitrateRequestMessage(BitrateRequest bitrateRequest) {
        return createBitrateRequestMessage(new BitrateRequest[]{bitrateRequest});
    }

    public static Message createBitrateRequestMessage(BitrateRequest[] bitrateRequestArr) {
        Message message = new Message(MessageType.getBitrateRequest());
        message.setPayload(BitrateRequest.toJsonArray(bitrateRequestArr));
        return message;
    }

    public static Message createCancelInviteMessage(String str, String str2) {
        Message message = new Message(MessageType.getCancelInvite());
        message.setRemoteUserId(str);
        message.setPayload(str2);
        return message;
    }

    public static Message createCandidateMessage(String str) {
        Message message = new Message(MessageType.getCandidate());
        message.setPayload(str);
        return message;
    }

    public static Message createCloseMessage() {
        return new Message(MessageType.getClose());
    }

    public static Message createDtmfTonesMessage(Tone[] toneArr) {
        Message message = new Message(MessageType.getDtmfTones());
        message.setPayload(Tone.toJsonArray(toneArr));
        return message;
    }

    public static Message createErrorMessage(String str) {
        Message message = new Message(MessageType.getError());
        message.setPayload(JsonSerializer.serializeString(str));
        return message;
    }

    public static Message createErrorMessage(String str, String str2, String str3, String str4) {
        Message message = new Message(MessageType.getError());
        message.setPayload(JsonSerializer.serializeString(str));
        message.setRemoteUserId(str2);
        message.setRemoteDeviceId(str3);
        message.setRemoteClientId(str4);
        return message;
    }

    public static Message createEventMessage(String str) {
        Message message = new Message(MessageType.getEvent());
        message.setPayload(str);
        return message;
    }

    public static Message createIceServersMessage() {
        return new Message(MessageType.getIceServers());
    }

    public static Message createInviteMessage(String str, String str2) {
        Message message = new Message(MessageType.getInvite());
        message.setRemoteUserId(str);
        message.setPayload(str2);
        return message;
    }

    public static Message createJoinMessage(String str, String str2) {
        Message message = new Message(MessageType.getJoin());
        message.setChannelId(str);
        message.setToken(str2);
        return message;
    }

    public static Message createKeyFrameRequestMessage(long[] jArr) {
        Message message = new Message(MessageType.getKeyFrameRequest());
        message.setPayload(JsonSerializer.serializeLongArray(jArr));
        return message;
    }

    public static Message createKickMessage(String str, String str2) {
        Message message = new Message(MessageType.getKick());
        message.setChannelId(str);
        message.setRemoteClientId(str2);
        return message;
    }

    public static Message createLeaveMessage(String str, ChannelReport channelReport) {
        Message message = new Message(MessageType.getLeave());
        message.setChannelId(str);
        message.setChannelReports(channelReport == null ? null : new ChannelReport[]{channelReport});
        return message;
    }

    public static Message createMcuOfferMessage(String str, String str2) {
        Message message = new Message(MessageType.getOffer());
        message.setConnectionType(ConnectionType.getMcu());
        message.setConnectionTag(str);
        message.setPayload(str2);
        return message;
    }

    public static Message createMessageMessage(String str) {
        return createMessageMessage(str, (String) null);
    }

    public static Message createMessageMessage(String str, String str2) {
        return createMessageMessage(str, str2, (String) null);
    }

    public static Message createMessageMessage(String str, String str2, String str3) {
        return createMessageMessage(str, str2, str3, (String) null);
    }

    public static Message createMessageMessage(String str, String str2, String str3, String str4) {
        Message message = new Message(MessageType.getMessage());
        message.setPayload(JsonSerializer.serializeString(str));
        message.setRemoteUserId(str2);
        message.setRemoteDeviceId(str3);
        message.setRemoteClientId(str4);
        return message;
    }

    public static Message createPeerAnswerMessage(String str, String str2, String str3, String str4, String str5) {
        Message message = new Message(MessageType.getAnswer());
        message.setConnectionType(ConnectionType.getPeer());
        message.setConnectionTag(str);
        message.setPayload(str2);
        message.setRemoteUserId(str3);
        message.setRemoteDeviceId(str4);
        message.setRemoteClientId(str5);
        return message;
    }

    public static Message createPeerCandidateMessage(String str, String str2, String str3, String str4) {
        Message message = new Message(MessageType.getCandidate());
        message.setPayload(str);
        message.setRemoteUserId(str2);
        message.setRemoteDeviceId(str3);
        message.setRemoteClientId(str4);
        return message;
    }

    public static Message createPeerCloseMessage(String str, String str2, String str3) {
        Message message = new Message(MessageType.getClose());
        message.setRemoteUserId(str);
        message.setRemoteDeviceId(str2);
        message.setRemoteClientId(str3);
        return message;
    }

    public static Message createPeerOfferMessage(String str, String str2, String str3, String str4, String str5) {
        Message message = new Message(MessageType.getOffer());
        message.setConnectionType(ConnectionType.getPeer());
        message.setConnectionTag(str);
        message.setPayload(str2);
        message.setRemoteUserId(str3);
        message.setRemoteDeviceId(str4);
        message.setRemoteClientId(str5);
        return message;
    }

    public static Message createPeerRejectMessage(String str, String str2, String str3, String str4) {
        Message message = new Message(MessageType.getReject());
        message.setConnectionType(ConnectionType.getPeer());
        message.setRemoteUserId(str);
        message.setRemoteDeviceId(str2);
        message.setRemoteClientId(str3);
        message.setRemoteConnectionId(str4);
        return message;
    }

    public static Message createRegisterMessage(String str, String str2, String str3, String str4, String str5, String str6, String str7, String[] strArr, String str8, String str9) {
        Message message = new Message(MessageType.getRegister());
        message.setApplicationId(str);
        message.setUserId(str2);
        message.setUserAlias(str3);
        message.setDeviceId(str4);
        message.setDeviceAlias(str5);
        message.setClientId(str6);
        message.setClientTag(str7);
        message.setClientRoles(strArr);
        message.setRegion(str8);
        message.setToken(str9);
        return message;
    }

    public static Message createSfuOfferMessage(String str, String str2) {
        Message message = new Message(MessageType.getOffer());
        message.setConnectionType(ConnectionType.getSfu());
        message.setConnectionTag(str);
        message.setPayload(str2);
        return message;
    }

    public static Message createSfuOfferMessage(String str, String str2, String str3) {
        Message message = new Message(MessageType.getOffer());
        message.setConnectionType(ConnectionType.getSfu());
        message.setConnectionTag(str);
        message.setPayload(str2);
        message.setRemoteMediaId(str3);
        return message;
    }

    public static Message createSfuOfferMessage(String str, String str2, String str3, String str4, String str5, String str6) {
        Message message = new Message(MessageType.getOffer());
        message.setConnectionType(ConnectionType.getSfu());
        message.setConnectionTag(str);
        message.setPayload(str2);
        message.setRemoteUserId(str3);
        message.setRemoteDeviceId(str4);
        message.setRemoteClientId(str5);
        message.setRemoteConnectionId(str6);
        return message;
    }

    public static Message createUnregisterMessage() {
        return new Message(MessageType.getUnregister());
    }

    public static Message createUnregisterMessage(ChannelReport[] channelReportArr) {
        Message message = new Message(MessageType.getUnregister());
        message.setChannelReports(channelReportArr);
        return message;
    }

    public static Message createUpdateMessage(String str) {
        return createUpdateMessage(str, (String) null, (String) null, (String) null);
    }

    public static Message createUpdateMessage(String str, String str2, String str3, String str4) {
        Message message = new Message(MessageType.getUpdate());
        message.setPayload(str);
        message.setRemoteUserId(str2);
        message.setRemoteDeviceId(str3);
        message.setRemoteClientId(str4);
        return message;
    }

    public static Message fromJson(String str) {
        return (Message) JsonSerializer.deserializeObject(str, new IFunction0<Message>() {
            public Message invoke() {
                return new Message((String) null);
            }
        }, new IAction3<Message, String, String>() {
            public void invoke(Message message, String str, String str2) {
                message.propertyFromJson(str, str2);
            }
        });
    }

    public static Message[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, Message>() {
            public String getId() {
                return "fm.liveswitch.Message.fromJson";
            }

            public Message invoke(String str) {
                return Message.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (Message[]) deserializeObjectArray.toArray(new Message[0]);
    }

    public String getApiVersion() {
        return this._apiVersion;
    }

    public String getApplicationId() {
        return this._applicationId;
    }

    public String getChannelId() {
        return this._channelId;
    }

    public ChannelReport[] getChannelReports() {
        return this._channelReports;
    }

    public String getClientId() {
        return this._clientId;
    }

    public String[] getClientRoles() {
        return this._clientRoles;
    }

    public String getClientTag() {
        return this._clientTag;
    }

    public String getConnectionId() {
        return this._connectionId;
    }

    public String getConnectionTag() {
        return this._connectionTag;
    }

    public String getConnectionType() {
        return this._connectionType;
    }

    public String getConnectorId() {
        return this._connectorId;
    }

    public String getDeviceAlias() {
        return this._deviceAlias;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public String getMediaId() {
        return this._mediaId;
    }

    public String getMediaServerId() {
        return this._mediaServerId;
    }

    public String getPayload() {
        return this._payload;
    }

    public String getRegion() {
        return this._region;
    }

    public String getRemoteClientId() {
        return this._remoteClientId;
    }

    public String getRemoteConnectionId() {
        return this._remoteConnectionId;
    }

    public String getRemoteDeviceId() {
        return this._remoteDeviceId;
    }

    public String getRemoteMediaId() {
        return this._remoteMediaId;
    }

    public String getRemoteUserId() {
        return this._remoteUserId;
    }

    public long getTimestamp() {
        return this._timestamp;
    }

    public String getToken() {
        return this._token;
    }

    public String getType() {
        return this._type;
    }

    public String getUserAlias() {
        return this._userAlias;
    }

    public String getUserId() {
        return this._userId;
    }

    private Message() {
    }

    Message(String str) {
        setType(str);
        setApiVersion(Build.getVersion());
    }

    /* access modifiers changed from: protected */
    public void propertiesToJson(HashMap<String, String> hashMap) {
        if (getMediaServerId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "mediaServerId", JsonSerializer.serializeString(getMediaServerId()));
        }
        if (getConnectorId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "connectorId", JsonSerializer.serializeString(getConnectorId()));
        }
        if (getApplicationId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "applicationId", JsonSerializer.serializeString(getApplicationId()));
        }
        if (getChannelId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channelId", JsonSerializer.serializeString(getChannelId()));
        }
        if (getChannelReports() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channelReports", ChannelReport.toJsonArray(getChannelReports()));
        }
        if (getUserId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userId", JsonSerializer.serializeString(getUserId()));
        }
        if (getUserAlias() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "userAlias", JsonSerializer.serializeString(getUserAlias()));
        }
        if (getDeviceId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deviceId", JsonSerializer.serializeString(getDeviceId()));
        }
        if (getDeviceAlias() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deviceAlias", JsonSerializer.serializeString(getDeviceAlias()));
        }
        if (getClientId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientId", JsonSerializer.serializeString(getClientId()));
        }
        if (getClientTag() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientTag", JsonSerializer.serializeString(getClientTag()));
        }
        if (getClientRoles() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientRoles", JsonSerializer.serializeStringArray(getClientRoles()));
        }
        if (getConnectionId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "connectionId", JsonSerializer.serializeString(getConnectionId()));
        }
        if (getConnectionTag() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "connectionTag", JsonSerializer.serializeString(getConnectionTag()));
        }
        if (getConnectionType() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "connectionType", JsonSerializer.serializeString(getConnectionType()));
        }
        if (getMediaId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "mediaId", JsonSerializer.serializeString(getMediaId()));
        }
        if (getToken() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "token", JsonSerializer.serializeString(getToken()));
        }
        if (getType() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", JsonSerializer.serializeString(getType()));
        }
        if (getPayload() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "payload", JsonSerializer.serializeString(getPayload()));
        }
        if (getRemoteUserId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteUserId", JsonSerializer.serializeString(getRemoteUserId()));
        }
        if (getRemoteDeviceId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteDeviceId", JsonSerializer.serializeString(getRemoteDeviceId()));
        }
        if (getRemoteClientId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteClientId", JsonSerializer.serializeString(getRemoteClientId()));
        }
        if (getRemoteConnectionId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteConnectionId", JsonSerializer.serializeString(getRemoteConnectionId()));
        }
        if (getRemoteMediaId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteMediaId", JsonSerializer.serializeString(getRemoteMediaId()));
        }
        if (getRegion() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "region", JsonSerializer.serializeString(getRegion()));
        }
        if (getApiVersion() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "apiVersion", JsonSerializer.serializeString(getApiVersion()));
        }
    }

    /* access modifiers changed from: protected */
    public void propertyFromJson(String str, String str2) {
        if (str.equals("mediaServerId")) {
            setMediaServerId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("connectorId")) {
            setConnectorId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("applicationId")) {
            setApplicationId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("channelId")) {
            setChannelId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("channelReports")) {
            setChannelReports(ChannelReport.fromJsonArray(str2));
        } else if (str.equals("userId")) {
            setUserId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("userAlias")) {
            setUserAlias(JsonSerializer.deserializeString(str2));
        } else if (str.equals("deviceId")) {
            setDeviceId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("deviceAlias")) {
            setDeviceAlias(JsonSerializer.deserializeString(str2));
        } else if (str.equals("clientId")) {
            setClientId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("clientTag")) {
            setClientTag(JsonSerializer.deserializeString(str2));
        } else if (str.equals("clientRoles")) {
            setClientRoles(JsonSerializer.deserializeStringArray(str2));
        } else if (str.equals("connectionId")) {
            setConnectionId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("connectionTag")) {
            setConnectionTag(JsonSerializer.deserializeString(str2));
        } else if (str.equals("connectionType")) {
            setConnectionType(JsonSerializer.deserializeString(str2));
        } else if (str.equals("mediaId")) {
            setMediaId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("token")) {
            setToken(JsonSerializer.deserializeString(str2));
        } else if (str.equals("type")) {
            setType(JsonSerializer.deserializeString(str2));
        } else if (str.equals("payload")) {
            setPayload(JsonSerializer.deserializeString(str2));
        } else if (str.equals("remoteUserId")) {
            setRemoteUserId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("remoteDeviceId")) {
            setRemoteDeviceId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("remoteClientId")) {
            setRemoteClientId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("remoteConnectionId")) {
            setRemoteConnectionId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("remoteMediaId")) {
            setRemoteMediaId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("region")) {
            setRegion(JsonSerializer.deserializeString(str2));
        } else if (str.equals("apiVersion")) {
            setApiVersion(JsonSerializer.deserializeString(str2));
        } else if (str.equals(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP)) {
            setTimestamp(JsonSerializer.deserializeLong(str2).getValue());
        }
    }

    private void setApiVersion(String str) {
        this._apiVersion = str;
    }

    public void setApplicationId(String str) {
        this._applicationId = str;
    }

    public void setChannelId(String str) {
        this._channelId = str;
    }

    public void setChannelReports(ChannelReport[] channelReportArr) {
        this._channelReports = channelReportArr;
    }

    public void setClientId(String str) {
        this._clientId = str;
    }

    public void setClientRoles(String[] strArr) {
        this._clientRoles = strArr;
    }

    public void setClientTag(String str) {
        this._clientTag = str;
    }

    public void setConnectionId(String str) {
        this._connectionId = str;
    }

    public void setConnectionTag(String str) {
        this._connectionTag = str;
    }

    public void setConnectionType(String str) {
        this._connectionType = str;
    }

    public void setConnectorId(String str) {
        this._connectorId = str;
    }

    public void setDeviceAlias(String str) {
        this._deviceAlias = str;
    }

    public void setDeviceId(String str) {
        this._deviceId = str;
    }

    public void setMediaId(String str) {
        this._mediaId = str;
    }

    public void setMediaServerId(String str) {
        this._mediaServerId = str;
    }

    public void setPayload(String str) {
        this._payload = str;
    }

    public void setRegion(String str) {
        this._region = str;
    }

    public void setRemoteClientId(String str) {
        this._remoteClientId = str;
    }

    public void setRemoteConnectionId(String str) {
        this._remoteConnectionId = str;
    }

    public void setRemoteDeviceId(String str) {
        this._remoteDeviceId = str;
    }

    public void setRemoteMediaId(String str) {
        this._remoteMediaId = str;
    }

    public void setRemoteUserId(String str) {
        this._remoteUserId = str;
    }

    public void setTimestamp(long j) {
        this._timestamp = j;
    }

    public void setToken(String str) {
        this._token = str;
    }

    public void setType(String str) {
        this._type = str;
    }

    public void setUserAlias(String str) {
        this._userAlias = str;
    }

    public void setUserId(String str) {
        this._userId = str;
    }

    public static String toJson(Message message) {
        return JsonSerializer.serializeObject(message, new IAction2<Message, HashMap<String, String>>() {
            public void invoke(Message message, HashMap<String, String> hashMap) {
                message.propertiesToJson(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(Message[] messageArr) {
        return JsonSerializer.serializeObjectArray(messageArr, new IFunctionDelegate1<Message, String>() {
            public String getId() {
                return "fm.liveswitch.Message.toJson";
            }

            public String invoke(Message message) {
                return Message.toJson(message);
            }
        });
    }
}
