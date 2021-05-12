package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaStreamInfo extends StreamInfo {
    private int __localBandwidth = -1;
    private int __remoteBandwidth = -1;
    private String _controlTransportId;
    private int _maxFrameHeight;
    private int _maxFrameWidth;
    private boolean _receiveDisabled;
    private EncodingInfo[] _receiveEncodings;
    private FormatInfo[] _receiveFormats;
    private MediaReceiverInfo[] _receivers;
    private boolean _sendDisabled;
    private EncodingInfo[] _sendEncodings;
    private FormatInfo[] _sendFormats;
    private boolean _sendMuted;
    private MediaSenderInfo[] _senders;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("sendMuted")) {
            setSendMuted(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("sendDisabled")) {
            setSendDisabled(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("receiveDisabled")) {
            setReceiveDisabled(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("sendFormats")) {
            setSendFormats(FormatInfo.fromJsonArray(str2));
        } else if (str.equals("receiveFormats")) {
            setReceiveFormats(FormatInfo.fromJsonArray(str2));
        } else if (str.equals("sendEncodings")) {
            setSendEncodings(EncodingInfo.fromJsonArray(str2));
        } else if (str.equals("receiveEncodings")) {
            setReceiveEncodings(EncodingInfo.fromJsonArray(str2));
        } else if (str.equals("localBandwidth")) {
            setLocalBandwidth(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("remoteBandwidth")) {
            setRemoteBandwidth(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("maxFrameWidth")) {
            setMaxFrameWidth(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("maxFrameHeight")) {
            setMaxFrameHeight(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("senders")) {
            setSenders(MediaSenderInfo.fromJsonArray(str2));
        } else if (str.equals("receivers")) {
            setReceivers(MediaReceiverInfo.fromJsonArray(str2));
        } else if (str.equals("controlTransportId")) {
            setControlTransportId(JsonSerializer.deserializeString(str2));
        }
    }

    private MediaReceiverStats findMatchingReceiver(MediaReceiverStats[] mediaReceiverStatsArr, String str) {
        if (mediaReceiverStatsArr == null) {
            return null;
        }
        for (MediaReceiverStats mediaReceiverStats : mediaReceiverStatsArr) {
            if (Global.equals(mediaReceiverStats.getId(), str)) {
                return mediaReceiverStats;
            }
        }
        return null;
    }

    private MediaSenderStats findMatchingSender(MediaSenderStats[] mediaSenderStatsArr, String str) {
        if (mediaSenderStatsArr == null) {
            return null;
        }
        for (MediaSenderStats mediaSenderStats : mediaSenderStatsArr) {
            if (Global.equals(mediaSenderStats.getId(), str)) {
                return mediaSenderStats;
            }
        }
        return null;
    }

    public static MediaStreamInfo fromJson(String str) {
        return (MediaStreamInfo) JsonSerializer.deserializeObject(str, new IFunction0<MediaStreamInfo>() {
            public MediaStreamInfo invoke() {
                return new MediaStreamInfo();
            }
        }, new IAction3<MediaStreamInfo, String, String>() {
            public void invoke(MediaStreamInfo mediaStreamInfo, String str, String str2) {
                mediaStreamInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaStreamInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaStreamInfo>() {
            public String getId() {
                return "fm.liveswitch.MediaStreamInfo.fromJson";
            }

            public MediaStreamInfo invoke(String str) {
                return MediaStreamInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaStreamInfo[]) deserializeObjectArray.toArray(new MediaStreamInfo[0]);
    }

    public String getControlTransportId() {
        return this._controlTransportId;
    }

    public String getDirection() {
        return StreamDirectionHelper.setReceiveDisabled(StreamDirectionHelper.setSendDisabled("inactive", getSendDisabled()), getReceiveDisabled());
    }

    public int getLocalBandwidth() {
        return this.__localBandwidth;
    }

    public int getMaxFrameHeight() {
        return this._maxFrameHeight;
    }

    public int getMaxFrameWidth() {
        return this._maxFrameWidth;
    }

    public boolean getReceiveDisabled() {
        return this._receiveDisabled;
    }

    public EncodingInfo[] getReceiveEncodings() {
        return this._receiveEncodings;
    }

    public FormatInfo[] getReceiveFormats() {
        return this._receiveFormats;
    }

    public MediaReceiverInfo getReceiver() {
        return (MediaReceiverInfo) Utility.firstOrDefault((T[]) getReceivers());
    }

    public MediaReceiverInfo[] getReceivers() {
        return this._receivers;
    }

    public int getRemoteBandwidth() {
        return this.__remoteBandwidth;
    }

    public boolean getSendDisabled() {
        return this._sendDisabled;
    }

    public EncodingInfo[] getSendEncodings() {
        return this._sendEncodings;
    }

    public MediaSenderInfo getSender() {
        return (MediaSenderInfo) Utility.firstOrDefault((T[]) getSenders());
    }

    public MediaSenderInfo[] getSenders() {
        return this._senders;
    }

    public FormatInfo[] getSendFormats() {
        return this._sendFormats;
    }

    public boolean getSendMuted() {
        return this._sendMuted;
    }

    /* access modifiers changed from: package-private */
    public void populateInfos(MediaStreamStats mediaStreamStats, MediaStreamStats mediaStreamStats2) {
        MediaReceiverStats mediaReceiverStats;
        MediaSenderStats mediaSenderStats;
        boolean z = mediaStreamStats2 == null;
        TransportStats transport = mediaStreamStats.getTransport();
        if (transport != null) {
            super.setTransportId(transport.getId());
            TransportStats rtcpTransport = transport.getRtcpTransport();
            if (rtcpTransport == null || Global.equals(rtcpTransport, transport)) {
                setControlTransportId(super.getTransportId());
            } else {
                setControlTransportId(rtcpTransport.getId());
            }
        }
        MediaSenderStats[] senders = mediaStreamStats.getSenders();
        if (!z) {
            senders = (MediaSenderStats[]) Info.processArray(senders, mediaStreamStats2.getSenders());
        }
        if (senders != null) {
            setSenders(new MediaSenderInfo[ArrayExtensions.getLength((Object[]) senders)]);
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) senders); i++) {
                MediaSenderStats mediaSenderStats2 = mediaStreamStats.getSenders()[i];
                if (z) {
                    mediaSenderStats = null;
                } else {
                    mediaSenderStats = findMatchingSender(mediaStreamStats2.getSenders(), mediaSenderStats2.getId());
                }
                getSenders()[i] = new MediaSenderInfo(mediaSenderStats2, mediaSenderStats);
            }
        }
        MediaReceiverStats[] receivers = mediaStreamStats.getReceivers();
        if (!z) {
            receivers = (MediaReceiverStats[]) Info.processArray(receivers, mediaStreamStats2.getReceivers());
        }
        if (receivers != null) {
            setReceivers(new MediaReceiverInfo[ArrayExtensions.getLength((Object[]) receivers)]);
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) receivers); i2++) {
                MediaReceiverStats mediaReceiverStats2 = mediaStreamStats.getReceivers()[i2];
                if (z) {
                    mediaReceiverStats = null;
                } else {
                    mediaReceiverStats = findMatchingReceiver(mediaStreamStats2.getReceivers(), mediaReceiverStats2.getId());
                }
                getReceivers()[i2] = new MediaReceiverInfo(mediaReceiverStats2, mediaReceiverStats);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getSendMuted()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sendMuted", JsonSerializer.serializeBoolean(new NullableBoolean(getSendMuted())));
        }
        if (getSendDisabled()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sendDisabled", JsonSerializer.serializeBoolean(new NullableBoolean(getSendDisabled())));
        }
        if (getReceiveDisabled()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "receiveDisabled", JsonSerializer.serializeBoolean(new NullableBoolean(getReceiveDisabled())));
        }
        if (getSendFormats() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sendFormats", FormatInfo.toJsonArray(getSendFormats()));
        }
        if (getReceiveFormats() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "receiveFormats", FormatInfo.toJsonArray(getReceiveFormats()));
        }
        if (getSendEncodings() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sendEncodings", EncodingInfo.toJsonArray(getSendEncodings()));
        }
        if (getReceiveEncodings() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "receiveEncodings", EncodingInfo.toJsonArray(getReceiveEncodings()));
        }
        if (getLocalBandwidth() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localBandwidth", JsonSerializer.serializeInteger(new NullableInteger(getLocalBandwidth())));
        }
        if (getRemoteBandwidth() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteBandwidth", JsonSerializer.serializeInteger(new NullableInteger(getRemoteBandwidth())));
        }
        if (getMaxFrameWidth() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "maxFrameWidth", JsonSerializer.serializeInteger(new NullableInteger(getMaxFrameWidth())));
        }
        if (getMaxFrameHeight() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "maxFrameHeight", JsonSerializer.serializeInteger(new NullableInteger(getMaxFrameHeight())));
        }
        if (getSenders() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "senders", MediaSenderInfo.toJsonArray(getSenders()));
        }
        if (getReceivers() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "receivers", MediaReceiverInfo.toJsonArray(getReceivers()));
        }
        if (getControlTransportId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "controlTransportId", JsonSerializer.serializeString(getControlTransportId()));
        }
    }

    public void setControlTransportId(String str) {
        this._controlTransportId = str;
    }

    public void setDirection(String str) {
        setSendDisabled(StreamDirectionHelper.isSendDisabled(str));
        setReceiveDisabled(StreamDirectionHelper.isReceiveDisabled(str));
    }

    public void setLocalBandwidth(int i) {
        this.__localBandwidth = i;
    }

    public void setMaxFrameHeight(int i) {
        this._maxFrameHeight = i;
    }

    public void setMaxFrameWidth(int i) {
        this._maxFrameWidth = i;
    }

    public void setReceiveDisabled(boolean z) {
        this._receiveDisabled = z;
    }

    public void setReceiveEncodings(EncodingInfo[] encodingInfoArr) {
        this._receiveEncodings = encodingInfoArr;
    }

    public void setReceiveFormats(FormatInfo[] formatInfoArr) {
        this._receiveFormats = formatInfoArr;
    }

    public void setReceivers(MediaReceiverInfo[] mediaReceiverInfoArr) {
        this._receivers = mediaReceiverInfoArr;
    }

    public void setRemoteBandwidth(int i) {
        this.__remoteBandwidth = i;
    }

    public void setSendDisabled(boolean z) {
        this._sendDisabled = z;
    }

    public void setSendEncodings(EncodingInfo[] encodingInfoArr) {
        this._sendEncodings = encodingInfoArr;
    }

    public void setSenders(MediaSenderInfo[] mediaSenderInfoArr) {
        this._senders = mediaSenderInfoArr;
    }

    public void setSendFormats(FormatInfo[] formatInfoArr) {
        this._sendFormats = formatInfoArr;
    }

    public void setSendMuted(boolean z) {
        this._sendMuted = z;
    }

    public static String toJson(MediaStreamInfo mediaStreamInfo) {
        return JsonSerializer.serializeObject(mediaStreamInfo, new IAction2<MediaStreamInfo, HashMap<String, String>>() {
            public void invoke(MediaStreamInfo mediaStreamInfo, HashMap<String, String> hashMap) {
                mediaStreamInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaStreamInfo[] mediaStreamInfoArr) {
        return JsonSerializer.serializeObjectArray(mediaStreamInfoArr, new IFunctionDelegate1<MediaStreamInfo, String>() {
            public String getId() {
                return "fm.liveswitch.MediaStreamInfo.toJson";
            }

            public String invoke(MediaStreamInfo mediaStreamInfo) {
                return MediaStreamInfo.toJson(mediaStreamInfo);
            }
        });
    }
}
