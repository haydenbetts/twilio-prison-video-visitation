package org.opencv.dnn;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;

public class KeypointsModel extends Model {
    private static native long KeypointsModel_0(long j);

    private static native long KeypointsModel_1(String str, String str2);

    private static native long KeypointsModel_2(String str);

    private static native void delete(long j);

    private static native long estimate_0(long j, long j2, float f);

    private static native long estimate_1(long j, long j2);

    protected KeypointsModel(long j) {
        super(j);
    }

    public static KeypointsModel __fromPtr__(long j) {
        return new KeypointsModel(j);
    }

    public KeypointsModel(Net net2) {
        super(KeypointsModel_0(net2.nativeObj));
    }

    public KeypointsModel(String str, String str2) {
        super(KeypointsModel_1(str, str2));
    }

    public KeypointsModel(String str) {
        super(KeypointsModel_2(str));
    }

    public MatOfPoint2f estimate(Mat mat, float f) {
        return MatOfPoint2f.fromNativeAddr(estimate_0(this.nativeObj, mat.nativeObj, f));
    }

    public MatOfPoint2f estimate(Mat mat) {
        return MatOfPoint2f.fromNativeAddr(estimate_1(this.nativeObj, mat.nativeObj));
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
