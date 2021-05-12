package org.opencv.tracking;

public class TrackerTLD extends Tracker {
    private static native long create_0();

    private static native void delete(long j);

    protected TrackerTLD(long j) {
        super(j);
    }

    public static TrackerTLD __fromPtr__(long j) {
        return new TrackerTLD(j);
    }

    public static TrackerTLD create() {
        return __fromPtr__(create_0());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
