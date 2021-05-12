package org.opencv.img_hash;

import org.opencv.core.MatOfDouble;

public class BlockMeanHash extends ImgHashBase {
    private static native long create_0(int i);

    private static native long create_1();

    private static native void delete(long j);

    private static native long getMean_0(long j);

    private static native void setMode_0(long j, int i);

    protected BlockMeanHash(long j) {
        super(j);
    }

    public static BlockMeanHash __fromPtr__(long j) {
        return new BlockMeanHash(j);
    }

    public static BlockMeanHash create(int i) {
        return __fromPtr__(create_0(i));
    }

    public static BlockMeanHash create() {
        return __fromPtr__(create_1());
    }

    public MatOfDouble getMean() {
        return MatOfDouble.fromNativeAddr(getMean_0(this.nativeObj));
    }

    public void setMode(int i) {
        setMode_0(this.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
