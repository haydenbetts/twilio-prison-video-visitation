package org.opencv.tracking;

public class TrackerGOTURN extends Tracker {
    private static native long create_0();

    private static native void delete(long j);

    protected TrackerGOTURN(long j) {
        super(j);
    }

    public static TrackerGOTURN __fromPtr__(long j) {
        return new TrackerGOTURN(j);
    }

    public static TrackerGOTURN create() {
        return __fromPtr__(create_0());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
