package org.opencv.features2d;

public class GFTTDetector extends Feature2D {
    private static native long create_0(int i, double d, double d2, int i2, int i3, boolean z, double d3);

    private static native long create_1(int i, double d, double d2, int i2, int i3, boolean z);

    private static native long create_2(int i, double d, double d2, int i2, int i3);

    private static native long create_3(int i, double d, double d2, int i2, boolean z, double d3);

    private static native long create_4(int i, double d, double d2, int i2, boolean z);

    private static native long create_5(int i, double d, double d2, int i2);

    private static native long create_6(int i, double d, double d2);

    private static native long create_7(int i, double d);

    private static native long create_8(int i);

    private static native long create_9();

    private static native void delete(long j);

    private static native int getBlockSize_0(long j);

    private static native String getDefaultName_0(long j);

    private static native boolean getHarrisDetector_0(long j);

    private static native double getK_0(long j);

    private static native int getMaxFeatures_0(long j);

    private static native double getMinDistance_0(long j);

    private static native double getQualityLevel_0(long j);

    private static native void setBlockSize_0(long j, int i);

    private static native void setHarrisDetector_0(long j, boolean z);

    private static native void setK_0(long j, double d);

    private static native void setMaxFeatures_0(long j, int i);

    private static native void setMinDistance_0(long j, double d);

    private static native void setQualityLevel_0(long j, double d);

    protected GFTTDetector(long j) {
        super(j);
    }

    public static GFTTDetector __fromPtr__(long j) {
        return new GFTTDetector(j);
    }

    public static GFTTDetector create(int i, double d, double d2, int i2, int i3, boolean z, double d3) {
        return __fromPtr__(create_0(i, d, d2, i2, i3, z, d3));
    }

    public static GFTTDetector create(int i, double d, double d2, int i2, int i3, boolean z) {
        return __fromPtr__(create_1(i, d, d2, i2, i3, z));
    }

    public static GFTTDetector create(int i, double d, double d2, int i2, int i3) {
        return __fromPtr__(create_2(i, d, d2, i2, i3));
    }

    public static GFTTDetector create(int i, double d, double d2, int i2, boolean z, double d3) {
        return __fromPtr__(create_3(i, d, d2, i2, z, d3));
    }

    public static GFTTDetector create(int i, double d, double d2, int i2, boolean z) {
        return __fromPtr__(create_4(i, d, d2, i2, z));
    }

    public static GFTTDetector create(int i, double d, double d2, int i2) {
        return __fromPtr__(create_5(i, d, d2, i2));
    }

    public static GFTTDetector create(int i, double d, double d2) {
        return __fromPtr__(create_6(i, d, d2));
    }

    public static GFTTDetector create(int i, double d) {
        return __fromPtr__(create_7(i, d));
    }

    public static GFTTDetector create(int i) {
        return __fromPtr__(create_8(i));
    }

    public static GFTTDetector create() {
        return __fromPtr__(create_9());
    }

    public String getDefaultName() {
        return getDefaultName_0(this.nativeObj);
    }

    public boolean getHarrisDetector() {
        return getHarrisDetector_0(this.nativeObj);
    }

    public double getK() {
        return getK_0(this.nativeObj);
    }

    public double getMinDistance() {
        return getMinDistance_0(this.nativeObj);
    }

    public double getQualityLevel() {
        return getQualityLevel_0(this.nativeObj);
    }

    public int getBlockSize() {
        return getBlockSize_0(this.nativeObj);
    }

    public int getMaxFeatures() {
        return getMaxFeatures_0(this.nativeObj);
    }

    public void setBlockSize(int i) {
        setBlockSize_0(this.nativeObj, i);
    }

    public void setHarrisDetector(boolean z) {
        setHarrisDetector_0(this.nativeObj, z);
    }

    public void setK(double d) {
        setK_0(this.nativeObj, d);
    }

    public void setMaxFeatures(int i) {
        setMaxFeatures_0(this.nativeObj, i);
    }

    public void setMinDistance(double d) {
        setMinDistance_0(this.nativeObj, d);
    }

    public void setQualityLevel(double d) {
        setQualityLevel_0(this.nativeObj, d);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
