package fm.liveswitch;

public class SfuUpstreamConnection extends SfuConnection {
    /* access modifiers changed from: protected */
    public Message doCreateOfferMessage(SessionDescription sessionDescription) {
        return Message.createSfuOfferMessage(super.getTag(), sessionDescription.toJson());
    }

    /* access modifiers changed from: protected */
    public boolean isMediaDirectionAllowed(String str) {
        return StreamDirectionHelper.isReceiveDisabled(str);
    }

    SfuUpstreamConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str6) {
        super(obj, str, str2, str3, str4, str5, iFunction1, audioStream, videoStream, dataStream, str6);
        if (audioStream != null) {
            if (Global.equals(audioStream.getLocalDirection(), StreamDirection.SendReceive)) {
                audioStream.setLocalDirection(StreamDirection.SendOnly);
            } else if (Global.equals(audioStream.getLocalDirection(), StreamDirection.ReceiveOnly)) {
                audioStream.setLocalDirection(StreamDirection.Inactive);
            }
        }
        if (videoStream == null) {
            return;
        }
        if (Global.equals(videoStream.getLocalDirection(), StreamDirection.SendReceive)) {
            videoStream.setLocalDirection(StreamDirection.SendOnly);
        } else if (Global.equals(videoStream.getLocalDirection(), StreamDirection.ReceiveOnly)) {
            videoStream.setLocalDirection(StreamDirection.Inactive);
        }
    }
}
