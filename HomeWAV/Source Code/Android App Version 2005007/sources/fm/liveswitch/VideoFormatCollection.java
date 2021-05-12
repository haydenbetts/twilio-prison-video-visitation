package fm.liveswitch;

import java.util.ArrayList;

public class VideoFormatCollection extends MediaFormatCollection<VideoFormat, VideoFormatCollection> {
    /* access modifiers changed from: protected */
    public VideoFormat[] arrayFromList(ArrayList<VideoFormat> arrayList) {
        return (VideoFormat[]) arrayList.toArray(new VideoFormat[0]);
    }

    /* access modifiers changed from: protected */
    public VideoFormatCollection createCollection() {
        return new VideoFormatCollection();
    }
}
