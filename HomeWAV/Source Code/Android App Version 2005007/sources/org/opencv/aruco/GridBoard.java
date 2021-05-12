package org.opencv.aruco;

import org.opencv.core.Mat;
import org.opencv.core.Size;

public class GridBoard extends Board {
    private static native long create_0(int i, int i2, float f, float f2, long j, int i3);

    private static native long create_1(int i, int i2, float f, float f2, long j);

    private static native void delete(long j);

    private static native void draw_0(long j, double d, double d2, long j2, int i, int i2);

    private static native void draw_1(long j, double d, double d2, long j2, int i);

    private static native void draw_2(long j, double d, double d2, long j2);

    private static native double[] getGridSize_0(long j);

    private static native float getMarkerLength_0(long j);

    private static native float getMarkerSeparation_0(long j);

    protected GridBoard(long j) {
        super(j);
    }

    public static GridBoard __fromPtr__(long j) {
        return new GridBoard(j);
    }

    public static GridBoard create(int i, int i2, float f, float f2, Dictionary dictionary, int i3) {
        return __fromPtr__(create_0(i, i2, f, f2, dictionary.getNativeObjAddr(), i3));
    }

    public static GridBoard create(int i, int i2, float f, float f2, Dictionary dictionary) {
        return __fromPtr__(create_1(i, i2, f, f2, dictionary.getNativeObjAddr()));
    }

    public Size getGridSize() {
        return new Size(getGridSize_0(this.nativeObj));
    }

    public float getMarkerLength() {
        return getMarkerLength_0(this.nativeObj);
    }

    public float getMarkerSeparation() {
        return getMarkerSeparation_0(this.nativeObj);
    }

    public void draw(Size size, Mat mat, int i, int i2) {
        draw_0(this.nativeObj, size.width, size.height, mat.nativeObj, i, i2);
    }

    public void draw(Size size, Mat mat, int i) {
        draw_1(this.nativeObj, size.width, size.height, mat.nativeObj, i);
    }

    public void draw(Size size, Mat mat) {
        draw_2(this.nativeObj, size.width, size.height, mat.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
