package org.opencv.bioinspired;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.Size;

public class TransientAreasSegmentationModule extends Algorithm {
    private static native void clearAllBuffers_0(long j);

    private static native long create_0(double d, double d2);

    private static native void delete(long j);

    private static native void getSegmentationPicture_0(long j, long j2);

    private static native double[] getSize_0(long j);

    private static native String printSetup_0(long j);

    private static native void run_0(long j, long j2, int i);

    private static native void run_1(long j, long j2);

    private static native void setup_0(long j, String str, boolean z);

    private static native void setup_1(long j, String str);

    private static native void setup_2(long j);

    private static native void write_0(long j, String str);

    protected TransientAreasSegmentationModule(long j) {
        super(j);
    }

    public static TransientAreasSegmentationModule __fromPtr__(long j) {
        return new TransientAreasSegmentationModule(j);
    }

    public static TransientAreasSegmentationModule create(Size size) {
        return __fromPtr__(create_0(size.width, size.height));
    }

    public Size getSize() {
        return new Size(getSize_0(this.nativeObj));
    }

    public String printSetup() {
        return printSetup_0(this.nativeObj);
    }

    public void clearAllBuffers() {
        clearAllBuffers_0(this.nativeObj);
    }

    public void getSegmentationPicture(Mat mat) {
        getSegmentationPicture_0(this.nativeObj, mat.nativeObj);
    }

    public void run(Mat mat, int i) {
        run_0(this.nativeObj, mat.nativeObj, i);
    }

    public void run(Mat mat) {
        run_1(this.nativeObj, mat.nativeObj);
    }

    public void setup(String str, boolean z) {
        setup_0(this.nativeObj, str, z);
    }

    public void setup(String str) {
        setup_1(this.nativeObj, str);
    }

    public void setup() {
        setup_2(this.nativeObj);
    }

    public void write(String str) {
        write_0(this.nativeObj, str);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
