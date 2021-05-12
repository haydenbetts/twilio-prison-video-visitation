package org.opencv.text;

import org.opencv.core.Mat;

public class OCRHMMDecoder extends BaseOCR {
    private static native long create_0(String str, String str2, long j, long j2, int i, int i2);

    private static native long create_1(String str, String str2, long j, long j2, int i);

    private static native long create_2(String str, String str2, long j, long j2);

    private static native void delete(long j);

    private static native String run_0(long j, long j2, long j3, int i, int i2);

    private static native String run_1(long j, long j2, long j3, int i);

    private static native String run_2(long j, long j2, int i, int i2);

    private static native String run_3(long j, long j2, int i);

    protected OCRHMMDecoder(long j) {
        super(j);
    }

    public static OCRHMMDecoder __fromPtr__(long j) {
        return new OCRHMMDecoder(j);
    }

    public static OCRHMMDecoder create(String str, String str2, Mat mat, Mat mat2, int i, int i2) {
        return __fromPtr__(create_0(str, str2, mat.nativeObj, mat2.nativeObj, i, i2));
    }

    public static OCRHMMDecoder create(String str, String str2, Mat mat, Mat mat2, int i) {
        return __fromPtr__(create_1(str, str2, mat.nativeObj, mat2.nativeObj, i));
    }

    public static OCRHMMDecoder create(String str, String str2, Mat mat, Mat mat2) {
        return __fromPtr__(create_2(str, str2, mat.nativeObj, mat2.nativeObj));
    }

    public String run(Mat mat, Mat mat2, int i, int i2) {
        return run_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, i, i2);
    }

    public String run(Mat mat, Mat mat2, int i) {
        return run_1(this.nativeObj, mat.nativeObj, mat2.nativeObj, i);
    }

    public String run(Mat mat, int i, int i2) {
        return run_2(this.nativeObj, mat.nativeObj, i, i2);
    }

    public String run(Mat mat, int i) {
        return run_3(this.nativeObj, mat.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
