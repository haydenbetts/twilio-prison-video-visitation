package org.opencv.text;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfRect;

public class TextDetectorCNN extends TextDetector {
    private static native long create_0(String str, String str2);

    private static native void delete(long j);

    private static native void detect_0(long j, long j2, long j3, long j4);

    protected TextDetectorCNN(long j) {
        super(j);
    }

    public static TextDetectorCNN __fromPtr__(long j) {
        return new TextDetectorCNN(j);
    }

    public static TextDetectorCNN create(String str, String str2) {
        return __fromPtr__(create_0(str, str2));
    }

    public void detect(Mat mat, MatOfRect matOfRect, MatOfFloat matOfFloat) {
        detect_0(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, matOfFloat.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
