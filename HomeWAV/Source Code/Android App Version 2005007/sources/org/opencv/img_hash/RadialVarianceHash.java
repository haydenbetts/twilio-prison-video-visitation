package org.opencv.img_hash;

public class RadialVarianceHash extends ImgHashBase {
    private static native long create_0(double d, int i);

    private static native long create_1(double d);

    private static native long create_2();

    private static native void delete(long j);

    private static native int getNumOfAngleLine_0(long j);

    private static native double getSigma_0(long j);

    private static native void setNumOfAngleLine_0(long j, int i);

    private static native void setSigma_0(long j, double d);

    protected RadialVarianceHash(long j) {
        super(j);
    }

    public static RadialVarianceHash __fromPtr__(long j) {
        return new RadialVarianceHash(j);
    }

    public static RadialVarianceHash create(double d, int i) {
        return __fromPtr__(create_0(d, i));
    }

    public static RadialVarianceHash create(double d) {
        return __fromPtr__(create_1(d));
    }

    public static RadialVarianceHash create() {
        return __fromPtr__(create_2());
    }

    public double getSigma() {
        return getSigma_0(this.nativeObj);
    }

    public int getNumOfAngleLine() {
        return getNumOfAngleLine_0(this.nativeObj);
    }

    public void setNumOfAngleLine(int i) {
        setNumOfAngleLine_0(this.nativeObj, i);
    }

    public void setSigma(double d) {
        setSigma_0(this.nativeObj, d);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
