package org.opencv.text;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfRect;

public class TextDetector {
    protected final long nativeObj;

    private static native void delete(long j);

    private static native void detect_0(long j, long j2, long j3, long j4);

    protected TextDetector(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static TextDetector __fromPtr__(long j) {
        return new TextDetector(j);
    }

    public void detect(Mat mat, MatOfRect matOfRect, MatOfFloat matOfFloat) {
        detect_0(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, matOfFloat.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
