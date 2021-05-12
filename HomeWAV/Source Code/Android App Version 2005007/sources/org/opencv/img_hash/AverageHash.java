package org.opencv.img_hash;

public class AverageHash extends ImgHashBase {
    private static native long create_0();

    private static native void delete(long j);

    protected AverageHash(long j) {
        super(j);
    }

    public static AverageHash __fromPtr__(long j) {
        return new AverageHash(j);
    }

    public static AverageHash create() {
        return __fromPtr__(create_0());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
