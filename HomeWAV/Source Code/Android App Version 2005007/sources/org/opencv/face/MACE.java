package org.opencv.face;

import java.util.List;
import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;

public class MACE extends Algorithm {
    private static native long create_0(int i);

    private static native long create_1();

    private static native void delete(long j);

    private static native long load_0(String str, String str2);

    private static native long load_1(String str);

    private static native void salt_0(long j, String str);

    private static native boolean same_0(long j, long j2);

    private static native void train_0(long j, long j2);

    protected MACE(long j) {
        super(j);
    }

    public static MACE __fromPtr__(long j) {
        return new MACE(j);
    }

    public static MACE create(int i) {
        return __fromPtr__(create_0(i));
    }

    public static MACE create() {
        return __fromPtr__(create_1());
    }

    public static MACE load(String str, String str2) {
        return __fromPtr__(load_0(str, str2));
    }

    public static MACE load(String str) {
        return __fromPtr__(load_1(str));
    }

    public boolean same(Mat mat) {
        return same_0(this.nativeObj, mat.nativeObj);
    }

    public void salt(String str) {
        salt_0(this.nativeObj, str);
    }

    public void train(List<Mat> list) {
        train_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
