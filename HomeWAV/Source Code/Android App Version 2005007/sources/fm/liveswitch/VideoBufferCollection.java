package fm.liveswitch;

import java.util.ArrayList;

public class VideoBufferCollection extends MediaBufferCollection<VideoBuffer, VideoBufferCollection, VideoFormat> {
    /* access modifiers changed from: protected */
    public VideoBuffer[] arrayFromList(ArrayList<VideoBuffer> arrayList) {
        return (VideoBuffer[]) arrayList.toArray(new VideoBuffer[0]);
    }

    /* access modifiers changed from: protected */
    public VideoBufferCollection createCollection() {
        return new VideoBufferCollection();
    }
}
