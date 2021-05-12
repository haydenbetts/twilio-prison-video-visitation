package org.opencv.tracking;

import org.opencv.core.Mat;

public class TrackerCSRT extends Tracker {
    private static native long create_0();

    private static native void delete(long j);

    private static native void setInitialMask_0(long j, long j2);

    protected TrackerCSRT(long j) {
        super(j);
    }

    public static TrackerCSRT __fromPtr__(long j) {
        return new TrackerCSRT(j);
    }

    public static TrackerCSRT create() {
        return __fromPtr__(create_0());
    }

    public void setInitialMask(Mat mat) {
        setInitialMask_0(this.nativeObj, mat.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
