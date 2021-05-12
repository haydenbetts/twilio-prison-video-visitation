package org.opencv.xfeatures2d;

import org.opencv.features2d.Feature2D;

public class MSDDetector extends Feature2D {
    private static native void delete(long j);

    protected MSDDetector(long j) {
        super(j);
    }

    public static MSDDetector __fromPtr__(long j) {
        return new MSDDetector(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
