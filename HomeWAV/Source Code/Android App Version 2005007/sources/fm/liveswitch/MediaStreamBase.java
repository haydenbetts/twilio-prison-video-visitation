package fm.liveswitch;

import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.rtp.SsrcAttribute;
import fm.liveswitch.sdp.rtp.SsrcAttributeName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MediaStreamBase extends Stream {
    private HashMap<String, Object> __disabledCodecs = new HashMap<>();
    private String __localCanonicalName;
    private StreamDirection __localDirection;
    private int __maxReceiveBitrate = -1;
    private int __maxSendBitrate = -1;
    /* access modifiers changed from: private */
    public List<IAction1<EncodingInfo>> __onLocalEncodingDisabled = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<EncodingInfo>> __onLocalEncodingEnabled = new ArrayList();
    private StreamDirection __pendingLocalDirection = StreamDirection.Inactive;
    private StreamDirection __remoteDirection;
    private int _localBandwidth;
    private IAction1<EncodingInfo> _onLocalEncodingDisabled = null;
    private IAction1<EncodingInfo> _onLocalEncodingEnabled = null;
    private String[] _preferredCodecs;
    private int _remoteBandwidth;
    private String _remoteCanonicalName;
    private EncodingInfo _remoteEncoding;
    private Object _renegotiationLock = new Object();
    private boolean _renegotiationPending;

    public abstract TransportInfo getControlTransportInfo();

    public abstract boolean getInputMuted();

    public abstract boolean getOutputMuted();

    public abstract SimulcastMode getSimulcastMode();

    /* access modifiers changed from: protected */
    public abstract void populateInfo(MediaStreamInfo mediaStreamInfo);

    public abstract void setInputMuted(boolean z);

    public abstract void setOutputMuted(boolean z);

    public abstract void setSimulcastMode(SimulcastMode simulcastMode);

    public void addOnLocalEncodingDisabled(IAction1<EncodingInfo> iAction1) {
        if (iAction1 != null) {
            if (this._onLocalEncodingDisabled == null) {
                this._onLocalEncodingDisabled = new IAction1<EncodingInfo>() {
                    public void invoke(EncodingInfo encodingInfo) {
                        Iterator it = new ArrayList(MediaStreamBase.this.__onLocalEncodingDisabled).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(encodingInfo);
                        }
                    }
                };
            }
            this.__onLocalEncodingDisabled.add(iAction1);
        }
    }

    public void addOnLocalEncodingEnabled(IAction1<EncodingInfo> iAction1) {
        if (iAction1 != null) {
            if (this._onLocalEncodingEnabled == null) {
                this._onLocalEncodingEnabled = new IAction1<EncodingInfo>() {
                    public void invoke(EncodingInfo encodingInfo) {
                        Iterator it = new ArrayList(MediaStreamBase.this.__onLocalEncodingEnabled).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(encodingInfo);
                        }
                    }
                };
            }
            this.__onLocalEncodingEnabled.add(iAction1);
        }
    }

    public Error changeDirection(StreamDirection streamDirection) {
        if (Global.equals(getDirectionCapabilities(), StreamDirection.Inactive)) {
            if (!Global.equals(streamDirection, StreamDirection.Inactive)) {
                return new Error(ErrorCode.InvalidStreamDirectionChange, new Exception("An attempt has been made to modify exisiting media stream direction; however, existing stream capabilities do not support a new setting."));
            }
        } else if (Global.equals(getDirectionCapabilities(), StreamDirection.SendOnly)) {
            if (!Global.equals(streamDirection, StreamDirection.Inactive) && !Global.equals(streamDirection, StreamDirection.SendOnly)) {
                return new Error(ErrorCode.InvalidStreamDirectionChange, new Exception("An attempt has been made to modify exisiting media stream direction; however, existing stream capabilities do not support a new setting."));
            }
        } else if (Global.equals(getDirectionCapabilities(), StreamDirection.ReceiveOnly) && !Global.equals(streamDirection, StreamDirection.Inactive) && !Global.equals(streamDirection, StreamDirection.ReceiveOnly)) {
            return new Error(ErrorCode.InvalidStreamDirectionChange, new Exception("An attempt has been made to modify exisiting media stream direction; however, existing stream capabilities do not support a new setting."));
        }
        synchronized (this._renegotiationLock) {
            setPendingLocalDirection(streamDirection);
            setRenegotiationPending(true);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void extractCanonicalName(MediaDescription mediaDescription, boolean z) {
        String str;
        SsrcAttribute[] ssrcAttributes = mediaDescription.getSsrcAttributes();
        int length = ssrcAttributes.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str = null;
                break;
            }
            SsrcAttribute ssrcAttribute = ssrcAttributes[i];
            if (Global.equals(ssrcAttribute.getName(), SsrcAttributeName.getCName())) {
                str = ssrcAttribute.getValue();
                break;
            }
            i++;
        }
        if (z) {
            setLocalCanonicalName(str);
        } else {
            setRemoteCanonicalName(str);
        }
    }

    public String getCanonicalName() {
        Log.warn("Getting the value of MediaStream.CanonicalName is deprecated. Get the value of MediaStream.LocalCanonicalName instead.");
        return getLocalCanonicalName();
    }

    public boolean getCodecDisabled(String str) {
        return this.__disabledCodecs.containsKey(StringExtensions.toLower(str));
    }

    public StreamDirection getDirection() {
        if (Global.equals(getLocalDirection(), StreamDirection.SendReceive)) {
            if (Global.equals(getRemoteDirection(), StreamDirection.ReceiveOnly)) {
                return StreamDirection.SendOnly;
            }
            if (Global.equals(getRemoteDirection(), StreamDirection.SendOnly)) {
                return StreamDirection.ReceiveOnly;
            }
            return getRemoteDirection();
        } else if (Global.equals(getLocalDirection(), StreamDirection.SendOnly)) {
            if (Global.equals(getRemoteDirection(), StreamDirection.Inactive) || Global.equals(getRemoteDirection(), StreamDirection.SendOnly)) {
                return StreamDirection.Inactive;
            }
            return getLocalDirection();
        } else if (!Global.equals(getLocalDirection(), StreamDirection.ReceiveOnly)) {
            return StreamDirection.Inactive;
        } else {
            if (Global.equals(getRemoteDirection(), StreamDirection.Inactive) || Global.equals(getRemoteDirection(), StreamDirection.ReceiveOnly)) {
                return StreamDirection.Inactive;
            }
            return getLocalDirection();
        }
    }

    public MediaStreamInfo getInfo() {
        MediaStreamInfo mediaStreamInfo = new MediaStreamInfo();
        mediaStreamInfo.setId(super.getId());
        mediaStreamInfo.setTag(super.getTag());
        mediaStreamInfo.setSendMuted(getInputMuted());
        mediaStreamInfo.setSendDisabled(StreamDirectionHelper.isSendDisabled(getDirection()));
        mediaStreamInfo.setReceiveDisabled(StreamDirectionHelper.isReceiveDisabled(getDirection()));
        populateInfo(mediaStreamInfo);
        return mediaStreamInfo;
    }

    public String getLabel() {
        StreamType type = super.getType();
        if (type == StreamType.Audio) {
            return "Audio Stream";
        }
        if (type == StreamType.Video) {
            return "Video Stream";
        }
        if (type == StreamType.Application) {
            return "Application Stream";
        }
        if (type == StreamType.Message) {
            return "Message Stream";
        }
        return type == StreamType.Text ? "Text Stream" : "Unknown Stream";
    }

    public int getLocalBandwidth() {
        return this._localBandwidth;
    }

    public String getLocalCanonicalName() {
        return this.__localCanonicalName;
    }

    public StreamDirection getLocalDirection() {
        return this.__localDirection;
    }

    public int getMaxReceiveBitrate() {
        return this.__maxReceiveBitrate;
    }

    public int getMaxSendBitrate() {
        return this.__maxSendBitrate;
    }

    public boolean getMuted() {
        return getInputMuted();
    }

    /* access modifiers changed from: protected */
    public StreamDirection getPendingLocalDirection() {
        return this.__pendingLocalDirection;
    }

    public String[] getPreferredCodecs() {
        return this._preferredCodecs;
    }

    public int getRemoteBandwidth() {
        return this._remoteBandwidth;
    }

    public String getRemoteCanonicalName() {
        return this._remoteCanonicalName;
    }

    public StreamDirection getRemoteDirection() {
        return this.__remoteDirection;
    }

    public EncodingInfo getRemoteEncoding() {
        return this._remoteEncoding;
    }

    /* access modifiers changed from: protected */
    public boolean getRenegotiationPending() {
        return this._renegotiationPending;
    }

    public MediaStreamBase(StreamType streamType) {
        super(streamType);
        setLocalDirection(StreamDirection.Unset);
        setRemoteDirection(StreamDirection.Unset);
        setLocalBandwidth(-1);
        setRemoteBandwidth(-1);
        if (!Global.equals(Platform.getInstance().getSourceLanguage(), SourceLanguage.TypeScript)) {
            this.__localCanonicalName = Utility.generateId();
        }
    }

    /* access modifiers changed from: protected */
    public void raiseLocalEncodingDisabled(EncodingInfo encodingInfo) {
        IAction1<EncodingInfo> iAction1 = this._onLocalEncodingDisabled;
        if (iAction1 != null) {
            iAction1.invoke(encodingInfo);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseLocalEncodingEnabled(EncodingInfo encodingInfo) {
        IAction1<EncodingInfo> iAction1 = this._onLocalEncodingEnabled;
        if (iAction1 != null) {
            iAction1.invoke(encodingInfo);
        }
    }

    public void removeOnLocalEncodingDisabled(IAction1<EncodingInfo> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onLocalEncodingDisabled, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onLocalEncodingDisabled.remove(iAction1);
        if (this.__onLocalEncodingDisabled.size() == 0) {
            this._onLocalEncodingDisabled = null;
        }
    }

    public void removeOnLocalEncodingEnabled(IAction1<EncodingInfo> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onLocalEncodingEnabled, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onLocalEncodingEnabled.remove(iAction1);
        if (this.__onLocalEncodingEnabled.size() == 0) {
            this._onLocalEncodingEnabled = null;
        }
    }

    public void setCodecDisabled(String str, boolean z) {
        String lower = StringExtensions.toLower(str);
        if (z) {
            HashMapExtensions.set(HashMapExtensions.getItem(this.__disabledCodecs), lower, new Object());
        } else {
            HashMapExtensions.remove(this.__disabledCodecs, lower);
        }
    }

    public void setLocalBandwidth(int i) {
        this._localBandwidth = i;
    }

    /* access modifiers changed from: package-private */
    public void setLocalCanonicalName(String str) {
        this.__localCanonicalName = str;
    }

    public void setLocalDirection(StreamDirection streamDirection) {
        StreamDirection direction = getDirection();
        this.__localDirection = streamDirection;
        if (!Global.equals(direction, getDirection())) {
            super.raiseDirectionChange();
        }
    }

    public void setMaxReceiveBitrate(int i) {
        int localBandwidth = getLocalBandwidth();
        int i2 = -1;
        if (localBandwidth != -1 && i > localBandwidth) {
            i = localBandwidth;
        }
        if (i >= 0) {
            i2 = i;
        }
        this.__maxReceiveBitrate = i2;
    }

    public void setMaxSendBitrate(int i) {
        int remoteBandwidth = getRemoteBandwidth();
        int i2 = -1;
        if (remoteBandwidth != -1 && i > remoteBandwidth) {
            i = remoteBandwidth;
        }
        if (i >= 0) {
            i2 = i;
        }
        this.__maxSendBitrate = i2;
    }

    public void setMuted(boolean z) {
        setInputMuted(z);
    }

    /* access modifiers changed from: protected */
    public void setPendingLocalDirection(StreamDirection streamDirection) {
        this.__pendingLocalDirection = streamDirection;
    }

    public void setPreferredCodecs(String[] strArr) {
        this._preferredCodecs = strArr;
    }

    /* access modifiers changed from: protected */
    public void setRemoteBandwidth(int i) {
        this._remoteBandwidth = i;
    }

    /* access modifiers changed from: package-private */
    public void setRemoteCanonicalName(String str) {
        this._remoteCanonicalName = str;
    }

    /* access modifiers changed from: package-private */
    public void setRemoteDirection(StreamDirection streamDirection) {
        StreamDirection direction = getDirection();
        this.__remoteDirection = streamDirection;
        if (!Global.equals(direction, getDirection())) {
            super.raiseDirectionChange();
        }
    }

    public void setRemoteEncoding(EncodingInfo encodingInfo) {
        this._remoteEncoding = encodingInfo;
    }

    /* access modifiers changed from: protected */
    public void setRenegotiationPending(boolean z) {
        this._renegotiationPending = z;
    }
}
