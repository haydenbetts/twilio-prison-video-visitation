package fm.liveswitch.vp8;

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
        super(VideoFormat.getVp8Name());
    }

    public Format(int i) {
        super(VideoFormat.getVp8Name(), i);
    }
}
