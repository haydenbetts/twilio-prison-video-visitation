package org.opencv.text;

import org.opencv.core.Algorithm;

public class ERFilter extends Algorithm {
    private static native void delete(long j);

    protected ERFilter(long j) {
        super(j);
    }

    public static ERFilter __fromPtr__(long j) {
        return new ERFilter(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
