package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class ChannelClaim {
    private String _action;
    private String[] _audioReceiveWhitelist;
    private boolean _broadcast;
    private boolean _canKick;
    private boolean _canUpdate;
    private String[] _dataReceiveWhitelist;
    private boolean _disableMcu;
    private boolean _disablePeer;
    private boolean _disableRemoteClientEvents;
    private boolean _disableSendAudio;
    private boolean _disableSendData;
    private boolean _disableSendMessage;
    private boolean _disableSendVideo;
    private boolean _disableSfu;
    private String _id;
    private String[] _videoReceiveWhitelist;

    private ChannelClaim() {
        setAction(ClaimAction.Join);
    }

    public ChannelClaim(String str) {
        this();
        if (str != null) {
            setId(str);
            return;
        }
        throw new RuntimeException(new Exception("Channel ID must not be null."));
    }

    public static ChannelClaim fromJson(String str) {
        return (ChannelClaim) JsonSerializer.deserializeObject(str, new IFunction0<ChannelClaim>() {
            public ChannelClaim invoke() {
                return new ChannelClaim();
            }
        }, new IAction3<ChannelClaim, String, String>() {
            public void invoke(ChannelClaim channelClaim, String str, String str2) {
                if (str.equals("id")) {
                    channelClaim.setId(JsonSerializer.deserializeString(str2));
                } else if (str.equals("disableSendMessage")) {
                    channelClaim.setDisableSendMessage(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("disablePeer")) {
                    channelClaim.setDisablePeer(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("disableMcu")) {
                    channelClaim.setDisableMcu(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("disableSfu")) {
                    channelClaim.setDisableSfu(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("disableSendAudio")) {
                    channelClaim.setDisableSendAudio(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("disableSendVideo")) {
                    channelClaim.setDisableSendVideo(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("disableSendData")) {
                    channelClaim.setDisableSendData(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("disableRemoteClientEvents")) {
                    channelClaim.setDisableRemoteClientEvents(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("audioReceiveWhitelist")) {
                    channelClaim.setAudioReceiveWhitelist(JsonSerializer.deserializeStringArray(str2));
                } else if (str.equals("videoReceiveWhitelist")) {
                    channelClaim.setVideoReceiveWhitelist(JsonSerializer.deserializeStringArray(str2));
                } else if (str.equals("dataReceiveWhitelist")) {
                    channelClaim.setDataReceiveWhitelist(JsonSerializer.deserializeStringArray(str2));
                } else if (str.equals("action")) {
                    channelClaim.setAction(JsonSerializer.deserializeString(str2));
                } else if (str.equals("canKick")) {
                    channelClaim.setCanKick(JsonSerializer.deserializeBoolean(str2).getValue());
                } else if (str.equals("canUpdate")) {
                    channelClaim.setCanUpdate(JsonSerializer.deserializeBoolean(str2).getValue());
                }
            }
        });
    }

    public static ChannelClaim[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, ChannelClaim>() {
            public String getId() {
                return "fm.liveswitch.ChannelClaim.fromJson";
            }

            public ChannelClaim invoke(String str) {
                return ChannelClaim.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (ChannelClaim[]) deserializeObjectArray.toArray(new ChannelClaim[0]);
    }

    public String getAction() {
        return this._action;
    }

    public String[] getAudioReceiveWhitelist() {
        return this._audioReceiveWhitelist;
    }

    public boolean getBroadcast() {
        return this._broadcast;
    }

    public boolean getCanKick() {
        return this._canKick;
    }

    public boolean getCanUpdate() {
        return this._canUpdate;
    }

    public String[] getDataReceiveWhitelist() {
        return this._dataReceiveWhitelist;
    }

    public boolean getDisableMcu() {
        return this._disableMcu;
    }

    public boolean getDisablePeer() {
        return this._disablePeer;
    }

    public boolean getDisableRemoteClientEvents() {
        return this._disableRemoteClientEvents;
    }

    public boolean getDisableSendAudio() {
        return this._disableSendAudio;
    }

    public boolean getDisableSendData() {
        return this._disableSendData;
    }

    public boolean getDisableSendMessage() {
        return this._disableSendMessage;
    }

    public boolean getDisableSendVideo() {
        return this._disableSendVideo;
    }

    public boolean getDisableSfu() {
        return this._disableSfu;
    }

    public String getId() {
        return this._id;
    }

    public String[] getVideoReceiveWhitelist() {
        return this._videoReceiveWhitelist;
    }

    public void setAction(String str) {
        this._action = str;
    }

    public void setAudioReceiveWhitelist(String[] strArr) {
        this._audioReceiveWhitelist = strArr;
    }

    public void setBroadcast(boolean z) {
        this._broadcast = z;
    }

    public void setCanKick(boolean z) {
        this._canKick = z;
    }

    public void setCanUpdate(boolean z) {
        this._canUpdate = z;
    }

    public void setDataReceiveWhitelist(String[] strArr) {
        this._dataReceiveWhitelist = strArr;
    }

    public void setDisableMcu(boolean z) {
        this._disableMcu = z;
    }

    public void setDisablePeer(boolean z) {
        this._disablePeer = z;
    }

    public void setDisableRemoteClientEvents(boolean z) {
        this._disableRemoteClientEvents = z;
    }

    public void setDisableSendAudio(boolean z) {
        this._disableSendAudio = z;
    }

    public void setDisableSendData(boolean z) {
        this._disableSendData = z;
    }

    public void setDisableSendMessage(boolean z) {
        this._disableSendMessage = z;
    }

    public void setDisableSendVideo(boolean z) {
        this._disableSendVideo = z;
    }

    public void setDisableSfu(boolean z) {
        this._disableSfu = z;
    }

    public void setId(String str) {
        this._id = str;
    }

    public void setVideoReceiveWhitelist(String[] strArr) {
        this._videoReceiveWhitelist = strArr;
    }

    public static String toJson(ChannelClaim channelClaim) {
        return JsonSerializer.serializeObject(channelClaim, new IAction2<ChannelClaim, HashMap<String, String>>(channelClaim) {
            final /* synthetic */ ChannelClaim val$channelClaim;

            {
                this.val$channelClaim = r1;
            }

            public void invoke(ChannelClaim channelClaim, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "id", JsonSerializer.serializeString(this.val$channelClaim.getId()));
                if (this.val$channelClaim.getDisableSendMessage()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disableSendMessage", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$channelClaim.getDisableSendMessage())));
                }
                if (this.val$channelClaim.getDisablePeer()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disablePeer", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$channelClaim.getDisablePeer())));
                }
                if (this.val$channelClaim.getDisableSfu()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disableSfu", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$channelClaim.getDisableSfu())));
                }
                if (this.val$channelClaim.getDisableMcu()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disableMcu", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$channelClaim.getDisableMcu())));
                }
                if (this.val$channelClaim.getDisableSendAudio()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disableSendAudio", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$channelClaim.getDisableSendAudio())));
                }
                if (this.val$channelClaim.getDisableSendVideo()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disableSendVideo", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$channelClaim.getDisableSendVideo())));
                }
                if (this.val$channelClaim.getDisableSendData()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disableSendData", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$channelClaim.getDisableSendData())));
                }
                if (this.val$channelClaim.getDisableRemoteClientEvents()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "disableRemoteClientEvents", JsonSerializer.serializeBoolean(new NullableBoolean(this.val$channelClaim.getDisableRemoteClientEvents())));
                }
                if (channelClaim.getAudioReceiveWhitelist() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "audioReceiveWhitelist", JsonSerializer.serializeStringArray(channelClaim.getAudioReceiveWhitelist()));
                }
                if (channelClaim.getVideoReceiveWhitelist() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "videoReceiveWhitelist", JsonSerializer.serializeStringArray(channelClaim.getVideoReceiveWhitelist()));
                }
                if (channelClaim.getDataReceiveWhitelist() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "dataReceiveWhitelist", JsonSerializer.serializeStringArray(channelClaim.getDataReceiveWhitelist()));
                }
                if (channelClaim.getAction() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "action", JsonSerializer.serializeString(channelClaim.getAction()));
                }
                if (channelClaim.getCanKick()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "canKick", JsonSerializer.serializeBoolean(new NullableBoolean(channelClaim.getCanKick())));
                }
                if (channelClaim.getCanUpdate()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "canUpdate", JsonSerializer.serializeBoolean(new NullableBoolean(channelClaim.getCanUpdate())));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(ChannelClaim[] channelClaimArr) {
        return JsonSerializer.serializeObjectArray(channelClaimArr, new IFunctionDelegate1<ChannelClaim, String>() {
            public String getId() {
                return "fm.liveswitch.ChannelClaim.toJson";
            }

            public String invoke(ChannelClaim channelClaim) {
                return ChannelClaim.toJson(channelClaim);
            }
        });
    }
}
