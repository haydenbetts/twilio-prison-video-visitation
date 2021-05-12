package fm.liveswitch;

public class SfuDownstreamConnection extends SfuConnection {
    private ConnectionInfo _remoteConnectionInfo;

    /* access modifiers changed from: protected */
    public Message doCreateOfferMessage(SessionDescription sessionDescription) {
        if (getRemoteConnectionInfo() != null) {
            return Message.createSfuOfferMessage(super.getTag(), sessionDescription.toJson(), getRemoteConnectionInfo().getUserId(), getRemoteConnectionInfo().getDeviceId(), getRemoteConnectionInfo().getClientId(), getRemoteConnectionInfo().getId());
        }
        if (super.getRemoteMediaId() != null) {
            return Message.createSfuOfferMessage(super.getTag(), sessionDescription.toJson(), super.getRemoteMediaId());
        }
        return null;
    }

    public ConnectionInfo getRemoteConnectionInfo() {
        return this._remoteConnectionInfo;
    }

    private void initialize(AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        super.setMediaId((String) null);
        if (audioStream != null) {
            if (Global.equals(audioStream.getLocalDirection(), StreamDirection.SendReceive)) {
                audioStream.setLocalDirection(StreamDirection.ReceiveOnly);
            } else if (Global.equals(audioStream.getLocalDirection(), StreamDirection.SendOnly)) {
                audioStream.setLocalDirection(StreamDirection.Inactive);
            }
        }
        if (videoStream == null) {
            return;
        }
        if (Global.equals(videoStream.getLocalDirection(), StreamDirection.SendReceive)) {
            videoStream.setLocalDirection(StreamDirection.ReceiveOnly);
        } else if (Global.equals(videoStream.getLocalDirection(), StreamDirection.SendOnly)) {
            videoStream.setLocalDirection(StreamDirection.Inactive);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isMediaDirectionAllowed(String str) {
        return StreamDirectionHelper.isSendDisabled(str);
    }

    private void setRemoteConnectionInfo(ConnectionInfo connectionInfo) {
        this._remoteConnectionInfo = connectionInfo;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SfuDownstreamConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, ConnectionInfo connectionInfo, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        super(obj, str, str2, str3, str4, str5, iFunction1, audioStream, videoStream, dataStream, (String) null);
        ConnectionInfo connectionInfo2 = connectionInfo;
        setRemoteConnectionInfo(connectionInfo2);
        if (connectionInfo2 != null) {
            super.setRemoteConnectionId(connectionInfo.getId());
        }
        initialize(audioStream, videoStream, dataStream);
    }

    SfuDownstreamConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, String str6, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        super(obj, str, str2, str3, str4, str5, iFunction1, audioStream, videoStream, dataStream, (String) null);
        super.setRemoteMediaId(str6);
        initialize(audioStream, videoStream, dataStream);
    }
}
