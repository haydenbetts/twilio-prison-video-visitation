package org.opencv.videoio;

import org.opencv.core.Mat;

public class VideoCapture {
    protected final long nativeObj;

    private static native long VideoCapture_0(String str, int i);

    private static native long VideoCapture_1(String str);

    private static native long VideoCapture_2(int i, int i2);

    private static native long VideoCapture_3(int i);

    private static native long VideoCapture_4();

    private static native void delete(long j);

    private static native String getBackendName_0(long j);

    private static native boolean getExceptionMode_0(long j);

    private static native double get_0(long j, int i);

    private static native boolean grab_0(long j);

    private static native boolean isOpened_0(long j);

    private static native boolean open_0(long j, String str, int i);

    private static native boolean open_1(long j, String str);

    private static native boolean open_2(long j, int i, int i2);

    private static native boolean open_3(long j, int i);

    private static native boolean read_0(long j, long j2);

    private static native void release_0(long j);

    private static native boolean retrieve_0(long j, long j2, int i);

    private static native boolean retrieve_1(long j, long j2);

    private static native void setExceptionMode_0(long j, boolean z);

    private static native boolean set_0(long j, int i, double d);

    protected VideoCapture(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VideoCapture __fromPtr__(long j) {
        return new VideoCapture(j);
    }

    public VideoCapture(String str, int i) {
        this.nativeObj = VideoCapture_0(str, i);
    }

    public VideoCapture(String str) {
        this.nativeObj = VideoCapture_1(str);
    }

    public VideoCapture(int i, int i2) {
        this.nativeObj = VideoCapture_2(i, i2);
    }

    public VideoCapture(int i) {
        this.nativeObj = VideoCapture_3(i);
    }

    public VideoCapture() {
        this.nativeObj = VideoCapture_4();
    }

    public String getBackendName() {
        return getBackendName_0(this.nativeObj);
    }

    public boolean getExceptionMode() {
        return getExceptionMode_0(this.nativeObj);
    }

    public boolean grab() {
        return grab_0(this.nativeObj);
    }

    public boolean isOpened() {
        return isOpened_0(this.nativeObj);
    }

    public boolean open(String str, int i) {
        return open_0(this.nativeObj, str, i);
    }

    public boolean open(String str) {
        return open_1(this.nativeObj, str);
    }

    public boolean open(int i, int i2) {
        return open_2(this.nativeObj, i, i2);
    }

    public boolean open(int i) {
        return open_3(this.nativeObj, i);
    }

    public boolean read(Mat mat) {
        return read_0(this.nativeObj, mat.nativeObj);
    }

    public boolean retrieve(Mat mat, int i) {
        return retrieve_0(this.nativeObj, mat.nativeObj, i);
    }

    public boolean retrieve(Mat mat) {
        return retrieve_1(this.nativeObj, mat.nativeObj);
    }

    public boolean set(int i, double d) {
        return set_0(this.nativeObj, i, d);
    }

    public double get(int i) {
        return get_0(this.nativeObj, i);
    }

    public void release() {
        release_0(this.nativeObj);
    }

    public void setExceptionMode(boolean z) {
        setExceptionMode_0(this.nativeObj, z);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
