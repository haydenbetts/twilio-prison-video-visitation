package fm.liveswitch;

public class McuConnection extends ServerConnection {
    /* access modifiers changed from: protected */
    public boolean isMediaDirectionAllowed(String str) {
        return true;
    }

    /* access modifiers changed from: protected */
    public Message doCreateOfferMessage(SessionDescription sessionDescription) {
        return Message.createMcuOfferMessage(super.getTag(), sessionDescription.toJson());
    }

    McuConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str6) {
        super(obj, str, str2, str3, str4, str5, iFunction1, ConnectionType.getMcu(), audioStream, videoStream, dataStream, str6);
    }
}
