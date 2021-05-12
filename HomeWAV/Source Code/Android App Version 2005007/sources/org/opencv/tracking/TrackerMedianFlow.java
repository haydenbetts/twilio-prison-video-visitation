package org.opencv.tracking;

public class TrackerMedianFlow extends Tracker {
    private static native long create_0();

    private static native void delete(long j);

    protected TrackerMedianFlow(long j) {
        super(j);
    }

    public static TrackerMedianFlow __fromPtr__(long j) {
        return new TrackerMedianFlow(j);
    }

    public static TrackerMedianFlow create() {
        return __fromPtr__(create_0());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
