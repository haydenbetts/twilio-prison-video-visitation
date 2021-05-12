package org.opencv.aruco;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Size;

public class CharucoBoard extends Board {
    private static native long create_0(int i, int i2, float f, float f2, long j);

    private static native void delete(long j);

    private static native void draw_0(long j, double d, double d2, long j2, int i, int i2);

    private static native void draw_1(long j, double d, double d2, long j2, int i);

    private static native void draw_2(long j, double d, double d2, long j2);

    private static native double[] getChessboardSize_0(long j);

    private static native float getMarkerLength_0(long j);

    private static native float getSquareLength_0(long j);

    private static native long get_chessboardCorners_0(long j);

    protected CharucoBoard(long j) {
        super(j);
    }

    public static CharucoBoard __fromPtr__(long j) {
        return new CharucoBoard(j);
    }

    public static CharucoBoard create(int i, int i2, float f, float f2, Dictionary dictionary) {
        return __fromPtr__(create_0(i, i2, f, f2, dictionary.getNativeObjAddr()));
    }

    public Size getChessboardSize() {
        return new Size(getChessboardSize_0(this.nativeObj));
    }

    public float getMarkerLength() {
        return getMarkerLength_0(this.nativeObj);
    }

    public float getSquareLength() {
        return getSquareLength_0(this.nativeObj);
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

    public MatOfPoint3f get_chessboardCorners() {
        return MatOfPoint3f.fromNativeAddr(get_chessboardCorners_0(this.nativeObj));
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
