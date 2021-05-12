package org.opencv.face;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;

public class BasicFaceRecognizer extends FaceRecognizer {
    private static native void delete(long j);

    private static native long getEigenValues_0(long j);

    private static native long getEigenVectors_0(long j);

    private static native long getLabels_0(long j);

    private static native long getMean_0(long j);

    private static native int getNumComponents_0(long j);

    private static native long getProjections_0(long j);

    private static native double getThreshold_0(long j);

    private static native void setNumComponents_0(long j, int i);

    private static native void setThreshold_0(long j, double d);

    protected BasicFaceRecognizer(long j) {
        super(j);
    }

    public static BasicFaceRecognizer __fromPtr__(long j) {
        return new BasicFaceRecognizer(j);
    }

    public Mat getEigenValues() {
        return new Mat(getEigenValues_0(this.nativeObj));
    }

    public Mat getEigenVectors() {
        return new Mat(getEigenVectors_0(this.nativeObj));
    }

    public Mat getLabels() {
        return new Mat(getLabels_0(this.nativeObj));
    }

    public Mat getMean() {
        return new Mat(getMean_0(this.nativeObj));
    }

    public double getThreshold() {
        return getThreshold_0(this.nativeObj);
    }

    public int getNumComponents() {
        return getNumComponents_0(this.nativeObj);
    }

    public List<Mat> getProjections() {
        ArrayList arrayList = new ArrayList();
        Converters.Mat_to_vector_Mat(new Mat(getProjections_0(this.nativeObj)), arrayList);
        return arrayList;
    }

    public void setNumComponents(int i) {
        setNumComponents_0(this.nativeObj, i);
    }

    public void setThreshold(double d) {
        setThreshold_0(this.nativeObj, d);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
