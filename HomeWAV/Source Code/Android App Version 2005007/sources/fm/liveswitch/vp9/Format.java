package fm.liveswitch.vp9;

import fm.liveswitch.VideoFormat;

public class Format extends VideoFormat {
    public int getMaxBitrate() {
        return 100000;
    }

    public int getMinBitrate() {
        return 100;
    }

    /* access modifiers changed from: protected */
    public VideoFormat createInstance() {
        return new Format();
    }

    public Format() {
        super(VideoFormat.getVp9Name());
    }

    public Format(int i) {
        super(VideoFormat.getVp9Name(), i);
    }
}
