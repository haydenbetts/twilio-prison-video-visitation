package org.opencv.text;

import org.opencv.core.Mat;

public class OCRTesseract extends BaseOCR {
    private static native long create_0(String str, String str2, String str3, int i, int i2);

    private static native long create_1(String str, String str2, String str3, int i);

    private static native long create_2(String str, String str2, String str3);

    private static native long create_3(String str, String str2);

    private static native long create_4(String str);

    private static native long create_5();

    private static native void delete(long j);

    private static native String run_0(long j, long j2, long j3, int i, int i2);

    private static native String run_1(long j, long j2, long j3, int i);

    private static native String run_2(long j, long j2, int i, int i2);

    private static native String run_3(long j, long j2, int i);

    private static native void setWhiteList_0(long j, String str);

    protected OCRTesseract(long j) {
        super(j);
    }

    public static OCRTesseract __fromPtr__(long j) {
        return new OCRTesseract(j);
    }

    public static OCRTesseract create(String str, String str2, String str3, int i, int i2) {
        return __fromPtr__(create_0(str, str2, str3, i, i2));
    }

    public static OCRTesseract create(String str, String str2, String str3, int i) {
        return __fromPtr__(create_1(str, str2, str3, i));
    }

    public static OCRTesseract create(String str, String str2, String str3) {
        return __fromPtr__(create_2(str, str2, str3));
    }

    public static OCRTesseract create(String str, String str2) {
        return __fromPtr__(create_3(str, str2));
    }

    public static OCRTesseract create(String str) {
        return __fromPtr__(create_4(str));
    }

    public static OCRTesseract create() {
        return __fromPtr__(create_5());
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

    public void setWhiteList(String str) {
        setWhiteList_0(this.nativeObj, str);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
