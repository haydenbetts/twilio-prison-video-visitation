package org.opencv.face;

import java.util.List;
import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.MatOfRect;
import org.opencv.utils.Converters;

public class Facemark extends Algorithm {
    private static native void delete(long j);

    private static native boolean fit_0(long j, long j2, long j3, long j4);

    private static native void loadModel_0(long j, String str);

    protected Facemark(long j) {
        super(j);
    }

    public static Facemark __fromPtr__(long j) {
        return new Facemark(j);
    }

    public boolean fit(Mat mat, MatOfRect matOfRect, List<MatOfPoint2f> list) {
        Mat mat2 = new Mat();
        boolean fit_0 = fit_0(this.nativeObj, mat.nativeObj, matOfRect.nativeObj, mat2.nativeObj);
        Converters.Mat_to_vector_vector_Point2f(mat2, list);
        mat2.release();
        return fit_0;
    }

    public void loadModel(String str) {
        loadModel_0(this.nativeObj, str);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
