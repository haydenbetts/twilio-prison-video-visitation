package org.opencv.xphoto;

public class GrayworldWB extends WhiteBalancer {
    private static native void delete(long j);

    private static native float getSaturationThreshold_0(long j);

    private static native void setSaturationThreshold_0(long j, float f);

    protected GrayworldWB(long j) {
        super(j);
    }

    public static GrayworldWB __fromPtr__(long j) {
        return new GrayworldWB(j);
    }

    public float getSaturationThreshold() {
        return getSaturationThreshold_0(this.nativeObj);
    }

    public void setSaturationThreshold(float f) {
        setSaturationThreshold_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
