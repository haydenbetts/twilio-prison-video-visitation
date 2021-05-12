package org.opencv.img_hash;

public class PHash extends ImgHashBase {
    private static native long create_0();

    private static native void delete(long j);

    protected PHash(long j) {
        super(j);
    }

    public static PHash __fromPtr__(long j) {
        return new PHash(j);
    }

    public static PHash create() {
        return __fromPtr__(create_0());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
