package fm.liveswitch;

import java.util.HashMap;

public class ConnectionConfig {
    private String _audioDirection;
    private String _dataDirection;
    private boolean _localAudioMuted;
    private boolean _localVideoMuted;
    private EncodingInfo _remoteAudioEncoding;
    private EncodingInfo _remoteVideoEncoding;
    private String _tag;
    private String _videoDirection;

    ConnectionConfig() {
        setAudioDirection("sendrecv");
        setVideoDirection("sendrecv");
        setDataDirection("sendrecv");
    }

    public static ConnectionConfig fromJson(String str) {
        return (ConnectionConfig) JsonSerializer.deserializeObject(str, new IFunction0<ConnectionConfig>() {
            public ConnectionConfig invoke() {
                return new ConnectionConfig();
            }
        }, new IAction3<ConnectionConfig, String, String>() {
            public void invoke(ConnectionConfig connectionConfig, String str, String str2) {
                if (str.equals("localAudioMuted")) {
                    connectionConfig.setLocalAudioMuted(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("localVideoMuted")) {
                    connectionConfig.setLocalVideoMuted(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("audioDirection")) {
                    connectionConfig.setAudioDirection(JsonSerializer.deserializeString(str2));
                } else if (str.equals("videoDirection")) {
                    connectionConfig.setVideoDirection(JsonSerializer.deserializeString(str2));
                } else if (str.equals("dataDirection")) {
                    connectionConfig.setDataDirection(JsonSerializer.deserializeString(str2));
                } else if (str.equals("tag")) {
                    connectionConfig.setTag(JsonSerializer.deserializeString(str2));
                } else if (str.equals("remoteAudioEncoding")) {
                    connectionConfig.setRemoteAudioEncoding(EncodingInfo.fromJson(str2));
                } else if (str.equals("remoteVideoEncoding")) {
                    connectionConfig.setRemoteVideoEncoding(EncodingInfo.fromJson(str2));
                }
            }
        });
    }

    public String getAudioDirection() {
        return this._audioDirection;
    }

    public String getDataDirection() {
        return this._dataDirection;
    }

    public boolean getLocalAudioDisabled() {
        return StreamDirectionHelper.isSendDisabled(getAudioDirection());
    }

    public boolean getLocalAudioMuted() {
        return this._localAudioMuted;
    }

    public boolean getLocalDataDisabled() {
        return StreamDirectionHelper.isSendDisabled(getDataDirection());
    }

    public boolean getLocalVideoDisabled() {
        return StreamDirectionHelper.isSendDisabled(getVideoDirection());
    }

    public boolean getLocalVideoMuted() {
        return this._localVideoMuted;
    }

    public boolean getRemoteAudioDisabled() {
        return StreamDirectionHelper.isReceiveDisabled(getAudioDirection());
    }

    public EncodingInfo getRemoteAudioEncoding() {
        return this._remoteAudioEncoding;
    }

    public boolean getRemoteDataDisabled() {
        return StreamDirectionHelper.isReceiveDisabled(getDataDirection());
    }

    public boolean getRemoteVideoDisabled() {
        return StreamDirectionHelper.isReceiveDisabled(getVideoDirection());
    }

    public EncodingInfo getRemoteVideoEncoding() {
        return this._remoteVideoEncoding;
    }

    public String getTag() {
        return this._tag;
    }

    public String getVideoDirection() {
        return this._videoDirection;
    }

    public void setAudioDirection(String str) {
        this._audioDirection = str;
    }

    public void setDataDirection(String str) {
        this._dataDirection = str;
    }

    public void setLocalAudioDisabled(boolean z) {
        setAudioDirection(StreamDirectionHelper.setSendDisabled(getAudioDirection(), z));
    }

    public void setLocalAudioMuted(boolean z) {
        this._localAudioMuted = z;
    }

    public void setLocalDataDisabled(boolean z) {
        setDataDirection(StreamDirectionHelper.setSendDisabled(getDataDirection(), z));
    }

    public void setLocalVideoDisabled(boolean z) {
        setVideoDirection(StreamDirectionHelper.setSendDisabled(getVideoDirection(), z));
    }

    public void setLocalVideoMuted(boolean z) {
        this._localVideoMuted = z;
    }

    public void setRemoteAudioDisabled(boolean z) {
        setAudioDirection(StreamDirectionHelper.setReceiveDisabled(getAudioDirection(), z));
    }

    public void setRemoteAudioEncoding(EncodingInfo encodingInfo) {
        this._remoteAudioEncoding = encodingInfo;
    }

    public void setRemoteDataDisabled(boolean z) {
        setDataDirection(StreamDirectionHelper.setReceiveDisabled(getDataDirection(), z));
    }

    public void setRemoteVideoDisabled(boolean z) {
        setVideoDirection(StreamDirectionHelper.setReceiveDisabled(getVideoDirection(), z));
    }

    public void setRemoteVideoEncoding(EncodingInfo encodingInfo) {
        this._remoteVideoEncoding = encodingInfo;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public void setVideoDirection(String str) {
        this._videoDirection = str;
    }

    public static String toJson(ConnectionConfig connectionConfig) {
        return JsonSerializer.serializeObject(connectionConfig, new IAction2<ConnectionConfig, HashMap<String, String>>() {
            public void invoke(ConnectionConfig connectionConfig, HashMap<String, String> hashMap) {
                if (connectionConfig.getLocalAudioMuted()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localAudioMuted", JsonSerializer.serializeBoolean(new NullableBoolean(connectionConfig.getLocalAudioMuted())));
                }
                if (connectionConfig.getLocalVideoMuted()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localVideoMuted", JsonSerializer.serializeBoolean(new NullableBoolean(connectionConfig.getLocalVideoMuted())));
                }
                if (connectionConfig.getAudioDirection() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "audioDirection", JsonSerializer.serializeString(connectionConfig.getAudioDirection()));
                }
                if (connectionConfig.getVideoDirection() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "videoDirection", JsonSerializer.serializeString(connectionConfig.getVideoDirection()));
                }
                if (connectionConfig.getDataDirection() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "dataDirection", JsonSerializer.serializeString(connectionConfig.getDataDirection()));
                }
                if (connectionConfig.getTag() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(connectionConfig.getTag()));
                }
                if (connectionConfig.getRemoteAudioEncoding() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteAudioEncoding", EncodingInfo.toJson(connectionConfig.getRemoteAudioEncoding()));
                }
                if (connectionConfig.getRemoteVideoEncoding() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteVideoEncoding", EncodingInfo.toJson(connectionConfig.getRemoteVideoEncoding()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
