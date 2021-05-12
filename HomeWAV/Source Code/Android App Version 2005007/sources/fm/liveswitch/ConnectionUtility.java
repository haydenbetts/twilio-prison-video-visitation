package fm.liveswitch;

import java.util.ArrayList;

class ConnectionUtility {
    public static Connection createConnection(Object obj, String str, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        ArrayList arrayList = new ArrayList();
        if (audioStream != null) {
            arrayList.add(audioStream);
        }
        if (videoStream != null) {
            arrayList.add(videoStream);
        }
        if (dataStream != null) {
            arrayList.add(dataStream);
        }
        return new Connection(obj, (Stream[]) arrayList.toArray(new Stream[0]));
    }
}
