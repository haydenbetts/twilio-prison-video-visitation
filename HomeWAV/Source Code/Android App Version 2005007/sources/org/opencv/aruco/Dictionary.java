package org.opencv.aruco;

import org.opencv.core.Mat;

public class Dictionary {
    protected final long nativeObj;

    private static native long create_0(int i, int i2, int i3);

    private static native long create_1(int i, int i2);

    private static native long create_from_0(int i, int i2, long j, int i3);

    private static native long create_from_1(int i, int i2, long j);

    private static native void delete(long j);

    private static native void drawMarker_0(long j, int i, int i2, long j2, int i3);

    private static native void drawMarker_1(long j, int i, int i2, long j2);

    private static native long getBitsFromByteList_0(long j, int i);

    private static native long getByteListFromBits_0(long j);

    private static native long get_0(int i);

    private static native long get_bytesList_0(long j);

    private static native int get_markerSize_0(long j);

    private static native int get_maxCorrectionBits_0(long j);

    private static native void set_bytesList_0(long j, long j2);

    private static native void set_markerSize_0(long j, int i);

    private static native void set_maxCorrectionBits_0(long j, int i);

    protected Dictionary(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static Dictionary __fromPtr__(long j) {
        return new Dictionary(j);
    }

    public static Mat getBitsFromByteList(Mat mat, int i) {
        return new Mat(getBitsFromByteList_0(mat.nativeObj, i));
    }

    public static Mat getByteListFromBits(Mat mat) {
        return new Mat(getByteListFromBits_0(mat.nativeObj));
    }

    public static Dictionary create_from(int i, int i2, Dictionary dictionary, int i3) {
        return __fromPtr__(create_from_0(i, i2, dictionary.getNativeObjAddr(), i3));
    }

    public static Dictionary create_from(int i, int i2, Dictionary dictionary) {
        return __fromPtr__(create_from_1(i, i2, dictionary.getNativeObjAddr()));
    }

    public static Dictionary create(int i, int i2, int i3) {
        return __fromPtr__(create_0(i, i2, i3));
    }

    public static Dictionary create(int i, int i2) {
        return __fromPtr__(create_1(i, i2));
    }

    public static Dictionary get(int i) {
        return __fromPtr__(get_0(i));
    }

    public void drawMarker(int i, int i2, Mat mat, int i3) {
        drawMarker_0(this.nativeObj, i, i2, mat.nativeObj, i3);
    }

    public void drawMarker(int i, int i2, Mat mat) {
        drawMarker_1(this.nativeObj, i, i2, mat.nativeObj);
    }

    public Mat get_bytesList() {
        return new Mat(get_bytesList_0(this.nativeObj));
    }

    public void set_bytesList(Mat mat) {
        set_bytesList_0(this.nativeObj, mat.nativeObj);
    }

    public int get_markerSize() {
        return get_markerSize_0(this.nativeObj);
    }

    public void set_markerSize(int i) {
        set_markerSize_0(this.nativeObj, i);
    }

    public int get_maxCorrectionBits() {
        return get_maxCorrectionBits_0(this.nativeObj);
    }

    public void set_maxCorrectionBits(int i) {
        set_maxCorrectionBits_0(this.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
