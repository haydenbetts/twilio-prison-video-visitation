package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ConnectionInfo extends Info {
    private boolean __serializeLegacyProperties = true;
    private String _applicationId;
    private MediaStreamInfo[] _audioStreams;
    private String _channelId;
    private String _clientId;
    private String[] _clientRoles;
    private String _clientTag;
    private DataStreamInfo _dataStream;
    private String _deviceAlias;
    private String _deviceId;
    private Error _error;
    private String _externalId;
    private String _mediaId;
    private boolean _recording;
    private String _remoteConnectionId;
    private String _remoteMediaId;
    private String _state;
    private String _tag;
    private TransportInfo[] _transports;
    private String _type;
    private String _userAlias;
    private String _userId;
    private MediaStreamInfo[] _videoStreams;

    public ConnectionInfo() {
    }

    ConnectionInfo(String str, String str2, String str3, String str4, String str5, String str6) {
        super.setId(str);
        setApplicationId(str2);
        setChannelId(str3);
        setUserId(str4);
        setDeviceId(str5);
        setClientId(str6);
    }

    public ConnectionInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z, boolean z2, String str8, String str9, String str10, FormatInfo[] formatInfoArr, FormatInfo[] formatInfoArr2) {
        setUserId(str);
        setDeviceId(str2);
        setClientId(str3);
        super.setId(str4);
        setTag(str5);
        setType(str6);
        setMediaId(str7);
        if (str8 != null) {
            setAudioDirection(str8);
        }
        if (str9 != null) {
            setVideoDirection(str9);
        }
        if (str10 != null) {
            setDataDirection(str10);
        }
        if (z) {
            setLocalAudioMuted(z);
        }
        if (z2) {
            setLocalVideoMuted(z2);
        }
        if (formatInfoArr != null) {
            setAudioFormats(formatInfoArr);
        }
        if (formatInfoArr2 != null) {
            setVideoFormats(formatInfoArr2);
        }
    }

    public ConnectionInfo(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr, String str7, String str8, String str9, String str10, boolean z, boolean z2, String str11, String str12, String str13, FormatInfo[] formatInfoArr, FormatInfo[] formatInfoArr2) {
        boolean z3 = z;
        boolean z4 = z2;
        String str14 = str11;
        String str15 = str12;
        String str16 = str13;
        FormatInfo[] formatInfoArr3 = formatInfoArr;
        FormatInfo[] formatInfoArr4 = formatInfoArr2;
        setUserId(str);
        String str17 = str2;
        setUserAlias(str2);
        String str18 = str3;
        setDeviceId(str3);
        String str19 = str4;
        setDeviceAlias(str4);
        String str20 = str5;
        setClientId(str5);
        String str21 = str6;
        setClientTag(str6);
        setClientRoles(strArr);
        super.setId(str7);
        setTag(str8);
        setType(str9);
        setMediaId(str10);
        if (str14 != null) {
            setAudioDirection(str14);
        }
        if (str15 != null) {
            setVideoDirection(str15);
        }
        if (str16 != null) {
            setDataDirection(str16);
        }
        if (z3) {
            setLocalAudioMuted(z3);
        }
        if (z4) {
            setLocalVideoMuted(z4);
        }
        if (formatInfoArr3 != null) {
            setAudioFormats(formatInfoArr3);
        }
        if (formatInfoArr4 != null) {
            setVideoFormats(formatInfoArr4);
        }
    }

    public ClientInfo createClientInfo() {
        return new ClientInfo(getUserId(), getUserAlias(), getDeviceId(), getDeviceAlias(), getClientId(), getClientTag(), getClientRoles());
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("applicationId")) {
            setApplicationId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("channelId")) {
            setChannelId(JsonSerializer.deserializeString(str2));
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
        } else if (str.equals("clientRoles")) {
            setClientRoles(JsonSerializer.deserializeStringArray(str2));
        } else if (str.equals("clientTag")) {
            setClientTag(JsonSerializer.deserializeString(str2));
        } else if (str.equals("externalId")) {
            setExternalId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("state")) {
            setState(JsonSerializer.deserializeString(str2));
        } else if (str.equals("tag")) {
            setTag(JsonSerializer.deserializeString(str2));
        } else if (str.equals("type")) {
            setType(JsonSerializer.deserializeString(str2));
        } else if (str.equals("recording")) {
            setRecording(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("error")) {
            setError(Error.fromJson(str2));
        } else if (str.equals("mediaId")) {
            setMediaId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("remoteConnectionId")) {
            setRemoteConnectionId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("remoteMediaId")) {
            setRemoteMediaId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("audioStreams")) {
            setAudioStreams(MediaStreamInfo.fromJsonArray(str2));
        } else if (str.equals("videoStreams")) {
            setVideoStreams(MediaStreamInfo.fromJsonArray(str2));
        } else if (str.equals("dataStream")) {
            setDataStream(DataStreamInfo.fromJson(str2));
        } else if (str.equals("transports")) {
            setTransports(TransportInfo.fromJsonArray(str2));
        } else if (str.equals("localAudioMuted")) {
            setLocalAudioMuted(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("localVideoMuted")) {
            setLocalVideoMuted(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("audioDirection")) {
            setAudioDirection(JsonSerializer.deserializeString(str2));
        } else if (str.equals("videoDirection")) {
            setVideoDirection(JsonSerializer.deserializeString(str2));
        } else if (str.equals("dataDirection")) {
            setDataDirection(JsonSerializer.deserializeString(str2));
        } else if (str.equals("audioFormats")) {
            setAudioFormats(FormatInfo.fromJsonArray(str2));
        } else if (str.equals("videoFormats")) {
            setVideoFormats(FormatInfo.fromJsonArray(str2));
        }
    }

    public static ConnectionInfo fromJson(String str) {
        return (ConnectionInfo) JsonSerializer.deserializeObject(str, new IFunction0<ConnectionInfo>() {
            public ConnectionInfo invoke() {
                return new ConnectionInfo();
            }
        }, new IAction3<ConnectionInfo, String, String>() {
            public void invoke(ConnectionInfo connectionInfo, String str, String str2) {
                connectionInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static ConnectionInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, ConnectionInfo>() {
            public String getId() {
                return "fm.liveswitch.ConnectionInfo.fromJson";
            }

            public ConnectionInfo invoke(String str) {
                return ConnectionInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (ConnectionInfo[]) deserializeObjectArray.toArray(new ConnectionInfo[0]);
    }

    public String getApplicationId() {
        return this._applicationId;
    }

    public String getAudioDirection() {
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            return null;
        }
        return audioStream.getDirection() != null ? audioStream.getDirection() : "sendrecv";
    }

    public FormatInfo[] getAudioFormats() {
        boolean z;
        boolean z2;
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        FormatInfo[] sendFormats = audioStream.getSendFormats();
        if (sendFormats != null) {
            for (FormatInfo formatInfo : sendFormats) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (formatInfo.isEquivalent((FormatInfo) it.next())) {
                            z2 = true;
                            break;
                        }
                    } else {
                        z2 = false;
                        break;
                    }
                }
                if (!z2) {
                    arrayList.add(formatInfo);
                }
            }
        }
        FormatInfo[] receiveFormats = audioStream.getReceiveFormats();
        if (receiveFormats != null) {
            for (FormatInfo formatInfo2 : receiveFormats) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (formatInfo2.isEquivalent((FormatInfo) it2.next())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    arrayList.add(formatInfo2);
                }
            }
        }
        return (FormatInfo[]) arrayList.toArray(new FormatInfo[0]);
    }

    public MediaStreamInfo getAudioStream() {
        return (MediaStreamInfo) Utility.firstOrDefault((T[]) getAudioStreams());
    }

    public MediaStreamInfo[] getAudioStreams() {
        return this._audioStreams;
    }

    public String getChannelId() {
        return this._channelId;
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

    public String getDataDirection() {
        if (getDataStream() == null) {
            return null;
        }
        return "sendrecv";
    }

    public DataStreamInfo getDataStream() {
        return this._dataStream;
    }

    public String getDeviceAlias() {
        return this._deviceAlias;
    }

    public String getDeviceId() {
        return this._deviceId;
    }

    public Error getError() {
        return this._error;
    }

    public String getExternalId() {
        return this._externalId;
    }

    public boolean getHasAudio() {
        return getAudioStream() != null;
    }

    public boolean getHasData() {
        return getDataStream() != null;
    }

    public boolean getHasVideo() {
        return getVideoStream() != null;
    }

    public boolean getLocalAudioDisabled() {
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            return false;
        }
        return audioStream.getSendDisabled();
    }

    public boolean getLocalAudioMuted() {
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            return false;
        }
        return audioStream.getSendMuted();
    }

    public boolean getLocalVideoDisabled() {
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            return false;
        }
        return videoStream.getSendDisabled();
    }

    public boolean getLocalVideoMuted() {
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            return false;
        }
        return videoStream.getSendMuted();
    }

    public String getMediaId() {
        return this._mediaId;
    }

    public boolean getRecording() {
        return this._recording;
    }

    public boolean getRemoteAudioDisabled() {
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            return false;
        }
        return audioStream.getReceiveDisabled();
    }

    public String getRemoteConnectionId() {
        return this._remoteConnectionId;
    }

    public String getRemoteMediaId() {
        return this._remoteMediaId;
    }

    public boolean getRemoteVideoDisabled() {
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            return false;
        }
        return videoStream.getReceiveDisabled();
    }

    /* access modifiers changed from: package-private */
    public boolean getSerializeLegacyProperties() {
        return this.__serializeLegacyProperties;
    }

    public String getState() {
        return this._state;
    }

    public String getTag() {
        return this._tag;
    }

    public TransportInfo[] getTransports() {
        return this._transports;
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

    public String getVideoDirection() {
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            return null;
        }
        return videoStream.getDirection() != null ? videoStream.getDirection() : "sendrecv";
    }

    public FormatInfo[] getVideoFormats() {
        boolean z;
        boolean z2;
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        FormatInfo[] sendFormats = videoStream.getSendFormats();
        if (sendFormats != null) {
            for (FormatInfo formatInfo : sendFormats) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (formatInfo.isEquivalent((FormatInfo) it.next())) {
                            z2 = true;
                            break;
                        }
                    } else {
                        z2 = false;
                        break;
                    }
                }
                if (!z2) {
                    arrayList.add(formatInfo);
                }
            }
        }
        FormatInfo[] receiveFormats = videoStream.getReceiveFormats();
        if (receiveFormats != null) {
            for (FormatInfo formatInfo2 : receiveFormats) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (formatInfo2.isEquivalent((FormatInfo) it2.next())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    arrayList.add(formatInfo2);
                }
            }
        }
        return (FormatInfo[]) arrayList.toArray(new FormatInfo[0]);
    }

    public MediaStreamInfo getVideoStream() {
        return (MediaStreamInfo) Utility.firstOrDefault((T[]) getVideoStreams());
    }

    public MediaStreamInfo[] getVideoStreams() {
        return this._videoStreams;
    }

    /* access modifiers changed from: package-private */
    public void inflate(String str, String str2, String str3, String[] strArr) {
        if (str != null) {
            setUserAlias(str);
        }
        if (str2 != null) {
            setDeviceAlias(str2);
        }
        if (str3 != null) {
            setClientTag(str3);
        }
        if (strArr != null) {
            setClientRoles(strArr);
        }
    }

    public boolean isEquivalent(ConnectionInfo connectionInfo) {
        return connectionInfo != null && isEquivalent(connectionInfo.getUserId(), connectionInfo.getDeviceId(), connectionInfo.getClientId(), connectionInfo.getId());
    }

    public boolean isEquivalent(String str, String str2, String str3, String str4) {
        return Global.equals(str, getUserId()) && Global.equals(str2, getDeviceId()) && Global.equals(str3, getClientId()) && Global.equals(str4, super.getId());
    }

    /* access modifiers changed from: package-private */
    public void populateInfo(ConnectionStats connectionStats, ConnectionStats connectionStats2, String str) {
        DataStreamStats dataStream;
        DataStreamStats dataStreamStats;
        MediaStreamStats[] videoStreams;
        MediaStreamStats[] mediaStreamStatsArr;
        MediaStreamStats[] audioStreams;
        MediaStreamStats[] mediaStreamStatsArr2;
        setSerializeLegacyProperties(false);
        if (shouldIncludeReport(str)) {
            if (!(getAudioStreams() == null || (audioStreams = connectionStats.getAudioStreams()) == null)) {
                if (connectionStats2 == null) {
                    mediaStreamStatsArr2 = null;
                } else {
                    mediaStreamStatsArr2 = connectionStats2.getAudioStreams();
                }
                int i = 0;
                while (i < MathAssistant.min(ArrayExtensions.getLength((Object[]) audioStreams), ArrayExtensions.getLength((Object[]) getAudioStreams()))) {
                    MediaStreamStats mediaStreamStats = (mediaStreamStatsArr2 == null || ArrayExtensions.getLength((Object[]) mediaStreamStatsArr2) <= i) ? null : mediaStreamStatsArr2[i];
                    getAudioStreams()[i].populateInfos(audioStreams[i], mediaStreamStats);
                    scrubTransport(audioStreams[i].getTransport(), mediaStreamStats == null ? null : mediaStreamStats.getTransport());
                    if (Global.equals(str, EventType.ConnectionStats)) {
                        getAudioStreams()[i].setId((String) null);
                        getAudioStreams()[i].setSendDisabled(false);
                        getAudioStreams()[i].setReceiveDisabled(false);
                        getAudioStreams()[i].setSendFormats((FormatInfo[]) null);
                        getAudioStreams()[i].setReceiveFormats((FormatInfo[]) null);
                        getAudioStreams()[i].setSendEncodings((EncodingInfo[]) null);
                        getAudioStreams()[i].setReceiveEncodings((EncodingInfo[]) null);
                    }
                    i++;
                }
            }
            if (!(getVideoStreams() == null || (videoStreams = connectionStats.getVideoStreams()) == null)) {
                if (connectionStats2 == null) {
                    mediaStreamStatsArr = null;
                } else {
                    mediaStreamStatsArr = connectionStats2.getVideoStreams();
                }
                int i2 = 0;
                while (i2 < MathAssistant.min(ArrayExtensions.getLength((Object[]) videoStreams), ArrayExtensions.getLength((Object[]) getVideoStreams()))) {
                    MediaStreamStats mediaStreamStats2 = (mediaStreamStatsArr == null || ArrayExtensions.getLength((Object[]) mediaStreamStatsArr) <= i2) ? null : mediaStreamStatsArr[i2];
                    getVideoStreams()[i2].populateInfos(videoStreams[i2], mediaStreamStats2);
                    scrubTransport(videoStreams[i2].getTransport(), mediaStreamStats2 == null ? null : mediaStreamStats2.getTransport());
                    if (Global.equals(str, EventType.ConnectionStats)) {
                        getVideoStreams()[i2].setId((String) null);
                        getVideoStreams()[i2].setSendDisabled(false);
                        getVideoStreams()[i2].setReceiveDisabled(false);
                        getVideoStreams()[i2].setSendFormats((FormatInfo[]) null);
                        getVideoStreams()[i2].setReceiveFormats((FormatInfo[]) null);
                        getVideoStreams()[i2].setSendEncodings((EncodingInfo[]) null);
                        getVideoStreams()[i2].setReceiveEncodings((EncodingInfo[]) null);
                    }
                    i2++;
                }
            }
            if (getDataStream() != null && (dataStream = connectionStats.getDataStream()) != null) {
                if (connectionStats2 == null) {
                    dataStreamStats = null;
                } else {
                    dataStreamStats = connectionStats2.getDataStream();
                }
                getDataStream().populateInfos(dataStream, dataStreamStats, str);
                scrubTransport(dataStream.getTransport(), dataStreamStats == null ? null : dataStreamStats.getTransport());
                if (Global.equals(str, EventType.ConnectionStats)) {
                    getDataStream().setId((String) null);
                }
            }
        }
    }

    private void scrubTransport(TransportStats transportStats, TransportStats transportStats2) {
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getTransports()); i++) {
            TransportInfo transportInfo = getTransports()[i];
            if (!(transportInfo == null || transportStats == null || !Global.equals(transportInfo.getId(), transportStats.getId()))) {
                getTransports()[i] = new TransportInfo(transportStats, transportStats2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getApplicationId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "applicationId", JsonSerializer.serializeString(getApplicationId()));
        }
        if (getChannelId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channelId", JsonSerializer.serializeString(getChannelId()));
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
        if (getClientRoles() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientRoles", JsonSerializer.serializeStringArray(getClientRoles()));
        }
        if (getClientTag() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "clientTag", JsonSerializer.serializeString(getClientTag()));
        }
        if (getExternalId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "externalId", JsonSerializer.serializeString(getExternalId()));
        }
        if (getState() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "state", JsonSerializer.serializeString(getState()));
        }
        if (getTag() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(getTag()));
        }
        if (getType() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", JsonSerializer.serializeString(getType()));
        }
        if (getRecording()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "recording", JsonSerializer.serializeBoolean(new NullableBoolean(getRecording())));
        }
        if (getError() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "error", Error.toJson(getError()));
        }
        if (getMediaId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "mediaId", JsonSerializer.serializeString(getMediaId()));
        }
        if (getRemoteConnectionId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteConnectionId", JsonSerializer.serializeString(getRemoteConnectionId()));
        }
        if (getRemoteMediaId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteMediaId", JsonSerializer.serializeString(getRemoteMediaId()));
        }
        if (getAudioStreams() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "audioStreams", MediaStreamInfo.toJsonArray(getAudioStreams()));
        }
        if (getVideoStreams() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "videoStreams", MediaStreamInfo.toJsonArray(getVideoStreams()));
        }
        if (getDataStream() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "dataStream", DataStreamInfo.toJson(getDataStream()));
        }
        if (getTransports() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "transports", TransportInfo.toJsonArray(getTransports()));
        }
        if (getSerializeLegacyProperties()) {
            if (getLocalAudioMuted()) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localAudioMuted", JsonSerializer.serializeBoolean(new NullableBoolean(getLocalAudioMuted())));
            }
            if (getLocalVideoMuted()) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localVideoMuted", JsonSerializer.serializeBoolean(new NullableBoolean(getLocalVideoMuted())));
            }
            if (getAudioDirection() != null) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "audioDirection", JsonSerializer.serializeString(getAudioDirection()));
            }
            if (getVideoDirection() != null) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "videoDirection", JsonSerializer.serializeString(getVideoDirection()));
            }
            if (getDataDirection() != null) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "dataDirection", JsonSerializer.serializeString(getDataDirection()));
            }
            if (getAudioFormats() != null) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "audioFormats", FormatInfo.toJsonArray(getAudioFormats()));
            }
            if (getVideoFormats() != null) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "videoFormats", FormatInfo.toJsonArray(getVideoFormats()));
            }
        }
    }

    public void setApplicationId(String str) {
        this._applicationId = str;
    }

    private void setAudioDirection(String str) {
        if (str == null) {
            setAudioStream((MediaStreamInfo) null);
            return;
        }
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            audioStream = new MediaStreamInfo();
            setAudioStream(audioStream);
        }
        audioStream.setDirection(str);
    }

    private void setAudioFormats(FormatInfo[] formatInfoArr) {
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            setAudioStream(new MediaStreamInfo());
            audioStream = getAudioStream();
        }
        audioStream.setSendFormats(formatInfoArr);
        audioStream.setReceiveFormats(formatInfoArr);
    }

    public void setAudioStream(MediaStreamInfo mediaStreamInfo) {
        MediaStreamInfo[] mediaStreamInfoArr;
        if (mediaStreamInfo == null) {
            mediaStreamInfoArr = null;
        } else {
            mediaStreamInfoArr = new MediaStreamInfo[]{mediaStreamInfo};
        }
        setAudioStreams(mediaStreamInfoArr);
    }

    public void setAudioStreams(MediaStreamInfo[] mediaStreamInfoArr) {
        this._audioStreams = mediaStreamInfoArr;
    }

    public void setChannelId(String str) {
        this._channelId = str;
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

    private void setDataDirection(String str) {
        if (str == null) {
            setDataStream((DataStreamInfo) null);
        } else if (getDataStream() == null) {
            setDataStream(new DataStreamInfo());
        }
    }

    public void setDataStream(DataStreamInfo dataStreamInfo) {
        this._dataStream = dataStreamInfo;
    }

    public void setDeviceAlias(String str) {
        this._deviceAlias = str;
    }

    public void setDeviceId(String str) {
        this._deviceId = str;
    }

    public void setError(Error error) {
        this._error = error;
    }

    public void setExternalId(String str) {
        this._externalId = str;
    }

    private void setLocalAudioDisabled(boolean z) {
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            setAudioStream(new MediaStreamInfo());
            audioStream = getAudioStream();
        }
        audioStream.setSendDisabled(z);
    }

    private void setLocalAudioMuted(boolean z) {
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            setAudioStream(new MediaStreamInfo());
            audioStream = getAudioStream();
        }
        audioStream.setSendMuted(z);
    }

    private void setLocalVideoDisabled(boolean z) {
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            setVideoStream(new MediaStreamInfo());
            videoStream = getVideoStream();
        }
        videoStream.setSendDisabled(z);
    }

    private void setLocalVideoMuted(boolean z) {
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            setVideoStream(new MediaStreamInfo());
            videoStream = getVideoStream();
        }
        videoStream.setSendMuted(z);
    }

    public void setMediaId(String str) {
        this._mediaId = str;
    }

    public void setRecording(boolean z) {
        this._recording = z;
    }

    private void setRemoteAudioDisabled(boolean z) {
        MediaStreamInfo audioStream = getAudioStream();
        if (audioStream == null) {
            setAudioStream(new MediaStreamInfo());
            audioStream = getAudioStream();
        }
        audioStream.setReceiveDisabled(z);
    }

    public void setRemoteConnectionId(String str) {
        this._remoteConnectionId = str;
    }

    public void setRemoteMediaId(String str) {
        this._remoteMediaId = str;
    }

    private void setRemoteVideoDisabled(boolean z) {
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            setVideoStream(new MediaStreamInfo());
            videoStream = getVideoStream();
        }
        videoStream.setReceiveDisabled(z);
    }

    /* access modifiers changed from: package-private */
    public void setSerializeLegacyProperties(boolean z) {
        this.__serializeLegacyProperties = z;
    }

    public void setState(String str) {
        this._state = str;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public void setTransports(TransportInfo[] transportInfoArr) {
        this._transports = transportInfoArr;
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

    private void setVideoDirection(String str) {
        if (str == null) {
            setVideoStream((MediaStreamInfo) null);
            return;
        }
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            videoStream = new MediaStreamInfo();
            setVideoStream(videoStream);
        }
        videoStream.setDirection(str);
    }

    private void setVideoFormats(FormatInfo[] formatInfoArr) {
        MediaStreamInfo videoStream = getVideoStream();
        if (videoStream == null) {
            setVideoStream(new MediaStreamInfo());
            videoStream = getVideoStream();
        }
        videoStream.setSendFormats(formatInfoArr);
        videoStream.setReceiveFormats(formatInfoArr);
    }

    public void setVideoStream(MediaStreamInfo mediaStreamInfo) {
        MediaStreamInfo[] mediaStreamInfoArr;
        if (mediaStreamInfo == null) {
            mediaStreamInfoArr = null;
        } else {
            mediaStreamInfoArr = new MediaStreamInfo[]{mediaStreamInfo};
        }
        setVideoStreams(mediaStreamInfoArr);
    }

    public void setVideoStreams(MediaStreamInfo[] mediaStreamInfoArr) {
        this._videoStreams = mediaStreamInfoArr;
    }

    private boolean shouldIncludeReport(String str) {
        return Global.equals(str, EventType.ConnectionStats) || Global.equals(str, EventType.ConnectionClosed) || Global.equals(str, EventType.ConnectionFailed);
    }

    public boolean shouldSerializeAudioDirection() {
        return getSerializeLegacyProperties();
    }

    public boolean shouldSerializeAudioFormats() {
        return getSerializeLegacyProperties();
    }

    public boolean shouldSerializeDataDirection() {
        return getSerializeLegacyProperties();
    }

    public boolean shouldSerializeLocalAudioMuted() {
        return getSerializeLegacyProperties();
    }

    public boolean shouldSerializeLocalVideoMuted() {
        return getSerializeLegacyProperties();
    }

    public boolean shouldSerializeVideoDirection() {
        return getSerializeLegacyProperties();
    }

    public boolean shouldSerializeVideoFormats() {
        return getSerializeLegacyProperties();
    }

    public static String toJson(ConnectionInfo connectionInfo) {
        return JsonSerializer.serializeObject(connectionInfo, new IAction2<ConnectionInfo, HashMap<String, String>>() {
            public void invoke(ConnectionInfo connectionInfo, HashMap<String, String> hashMap) {
                connectionInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(ConnectionInfo[] connectionInfoArr) {
        return JsonSerializer.serializeObjectArray(connectionInfoArr, new IFunctionDelegate1<ConnectionInfo, String>() {
            public String getId() {
                return "fm.liveswitch.ConnectionInfo.toJson";
            }

            public String invoke(ConnectionInfo connectionInfo) {
                return ConnectionInfo.toJson(connectionInfo);
            }
        });
    }
}
