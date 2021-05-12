package fm.liveswitch;

import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.MediaType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PeerConnectionOffer {
    private ArrayList<Message> __earlyCandidateMessages = new ArrayList<>();
    private Object __lock = new Object();
    /* access modifiers changed from: private */
    public List<IAction0> __onCancel = new ArrayList();
    private Channel _channel;
    private String _connectionTag;
    private boolean _hasAudio;
    private boolean _hasData;
    private boolean _hasVideo;
    private SessionDescription _offer;
    private IAction0 _onCancel = null;
    private ClientInfo _remoteClientInfo;
    private boolean _remoteClosed;
    private String _remoteConnectionId;
    private boolean _remoteFailed;

    public PeerConnection accept(AudioStream audioStream) {
        PeerConnection createPeerConnection;
        synchronized (this.__lock) {
            createPeerConnection = getChannel().createPeerConnection(this, audioStream);
        }
        return createPeerConnection;
    }

    public PeerConnection accept(AudioStream audioStream, DataStream dataStream) {
        PeerConnection createPeerConnection;
        synchronized (this.__lock) {
            createPeerConnection = getChannel().createPeerConnection(this, audioStream, dataStream);
        }
        return createPeerConnection;
    }

    public PeerConnection accept(AudioStream audioStream, VideoStream videoStream) {
        PeerConnection createPeerConnection;
        synchronized (this.__lock) {
            createPeerConnection = getChannel().createPeerConnection(this, audioStream, videoStream);
        }
        return createPeerConnection;
    }

    public PeerConnection accept(AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        PeerConnection createPeerConnection;
        synchronized (this.__lock) {
            createPeerConnection = getChannel().createPeerConnection(this, audioStream, videoStream, dataStream);
        }
        return createPeerConnection;
    }

    public PeerConnection accept(DataStream dataStream) {
        PeerConnection createPeerConnection;
        synchronized (this.__lock) {
            createPeerConnection = getChannel().createPeerConnection(this, dataStream);
        }
        return createPeerConnection;
    }

    public PeerConnection accept(VideoStream videoStream) {
        PeerConnection createPeerConnection;
        synchronized (this.__lock) {
            createPeerConnection = getChannel().createPeerConnection(this, videoStream);
        }
        return createPeerConnection;
    }

    public PeerConnection accept(VideoStream videoStream, DataStream dataStream) {
        PeerConnection createPeerConnection;
        synchronized (this.__lock) {
            createPeerConnection = getChannel().createPeerConnection(this, videoStream, dataStream);
        }
        return createPeerConnection;
    }

    public void addOnCancel(IAction0 iAction0) {
        if (iAction0 != null) {
            if (this._onCancel == null) {
                this._onCancel = new IAction0() {
                    public void invoke() {
                        Iterator it = new ArrayList(PeerConnectionOffer.this.__onCancel).iterator();
                        while (it.hasNext()) {
                            ((IAction0) it.next()).invoke();
                        }
                    }
                };
            }
            this.__onCancel.add(iAction0);
        }
    }

    public Channel getChannel() {
        return this._channel;
    }

    public String getConnectionTag() {
        return this._connectionTag;
    }

    /* access modifiers changed from: package-private */
    public Message[] getEarlyCandidateMessages() {
        return (Message[]) this.__earlyCandidateMessages.toArray(new Message[0]);
    }

    public boolean getHasAudio() {
        return this._hasAudio;
    }

    public boolean getHasData() {
        return this._hasData;
    }

    public boolean getHasVideo() {
        return this._hasVideo;
    }

    public SessionDescription getOffer() {
        return this._offer;
    }

    public ClientInfo getRemoteClientInfo() {
        return this._remoteClientInfo;
    }

    public boolean getRemoteClosed() {
        return this._remoteClosed;
    }

    public String getRemoteConnectionId() {
        return this._remoteConnectionId;
    }

    public boolean getRemoteFailed() {
        return this._remoteFailed;
    }

    PeerConnectionOffer(Channel channel, ClientInfo clientInfo, String str, String str2, SessionDescription sessionDescription) {
        setChannel(channel);
        setRemoteClientInfo(clientInfo);
        setRemoteConnectionId(str);
        setConnectionTag(str2);
        setOffer(sessionDescription);
        for (MediaDescription mediaDescription : getOffer().getSdpMessage().getMediaDescriptions()) {
            if (Global.equals(mediaDescription.getMedia().getMediaType(), MediaType.getAudio())) {
                setHasAudio(true);
            } else if (Global.equals(mediaDescription.getMedia().getMediaType(), MediaType.getVideo())) {
                setHasVideo(true);
            } else if (Global.equals(mediaDescription.getMedia().getMediaType(), MediaType.getApplication())) {
                setHasData(true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void processMessage(Message message) {
        synchronized (this.__lock) {
            if (Global.equals(message.getType(), MessageType.getCandidate())) {
                this.__earlyCandidateMessages.add(message);
            } else if (Global.equals(message.getType(), MessageType.getClose())) {
                setRemoteClosed(true);
                IAction0 iAction0 = this._onCancel;
                if (iAction0 != null) {
                    iAction0.invoke();
                }
            } else if (Global.equals(message.getType(), MessageType.getFail())) {
                setRemoteFailed(true);
                IAction0 iAction02 = this._onCancel;
                if (iAction02 != null) {
                    iAction02.invoke();
                }
            }
        }
    }

    public Future<Object> reject() {
        Future<Object> rejectPeerConnection;
        synchronized (this.__lock) {
            rejectPeerConnection = getChannel().rejectPeerConnection(this);
        }
        return rejectPeerConnection;
    }

    public void removeOnCancel(IAction0 iAction0) {
        IAction0 findIActionDelegate0WithId;
        if ((iAction0 instanceof IActionDelegate0) && (findIActionDelegate0WithId = Global.findIActionDelegate0WithId(this.__onCancel, ((IActionDelegate0) iAction0).getId())) != null) {
            iAction0 = findIActionDelegate0WithId;
        }
        this.__onCancel.remove(iAction0);
        if (this.__onCancel.size() == 0) {
            this._onCancel = null;
        }
    }

    private void setChannel(Channel channel) {
        this._channel = channel;
    }

    private void setConnectionTag(String str) {
        this._connectionTag = str;
    }

    private void setHasAudio(boolean z) {
        this._hasAudio = z;
    }

    private void setHasData(boolean z) {
        this._hasData = z;
    }

    private void setHasVideo(boolean z) {
        this._hasVideo = z;
    }

    private void setOffer(SessionDescription sessionDescription) {
        this._offer = sessionDescription;
    }

    private void setRemoteClientInfo(ClientInfo clientInfo) {
        this._remoteClientInfo = clientInfo;
    }

    private void setRemoteClosed(boolean z) {
        this._remoteClosed = z;
    }

    private void setRemoteConnectionId(String str) {
        this._remoteConnectionId = str;
    }

    private void setRemoteFailed(boolean z) {
        this._remoteFailed = z;
    }
}
