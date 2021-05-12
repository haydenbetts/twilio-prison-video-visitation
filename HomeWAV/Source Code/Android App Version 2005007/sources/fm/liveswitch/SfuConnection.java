package fm.liveswitch;

public abstract class SfuConnection extends ServerConnection {
    SfuConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str6) {
        super(obj, str, str2, str3, str4, str5, iFunction1, ConnectionType.getSfu(), audioStream, videoStream, dataStream, str6);
    }
}
