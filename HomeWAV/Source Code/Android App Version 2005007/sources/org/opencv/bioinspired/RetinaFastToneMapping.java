package org.opencv.bioinspired;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.Size;

public class RetinaFastToneMapping extends Algorithm {
    private static native void applyFastToneMapping_0(long j, long j2, long j3);

    private static native long create_0(double d, double d2);

    private static native void delete(long j);

    private static native void setup_0(long j, float f, float f2, float f3);

    private static native void setup_1(long j, float f, float f2);

    private static native void setup_2(long j, float f);

    private static native void setup_3(long j);

    protected RetinaFastToneMapping(long j) {
        super(j);
    }

    public static RetinaFastToneMapping __fromPtr__(long j) {
        return new RetinaFastToneMapping(j);
    }

    public static RetinaFastToneMapping create(Size size) {
        return __fromPtr__(create_0(size.width, size.height));
    }

    public void applyFastToneMapping(Mat mat, Mat mat2) {
        applyFastToneMapping_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void setup(float f, float f2, float f3) {
        setup_0(this.nativeObj, f, f2, f3);
    }

    public void setup(float f, float f2) {
        setup_1(this.nativeObj, f, f2);
    }

    public void setup(float f) {
        setup_2(this.nativeObj, f);
    }

    public void setup() {
        setup_3(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
