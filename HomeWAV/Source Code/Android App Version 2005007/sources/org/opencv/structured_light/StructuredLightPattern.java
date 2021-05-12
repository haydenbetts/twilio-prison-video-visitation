package org.opencv.structured_light;

import java.util.List;
import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;

public class StructuredLightPattern extends Algorithm {
    private static native void delete(long j);

    private static native boolean generate_0(long j, long j2);

    protected StructuredLightPattern(long j) {
        super(j);
    }

    public static StructuredLightPattern __fromPtr__(long j) {
        return new StructuredLightPattern(j);
    }

    public boolean generate(List<Mat> list) {
        Mat mat = new Mat();
        boolean generate_0 = generate_0(this.nativeObj, mat.nativeObj);
        Converters.Mat_to_vector_Mat(mat, list);
        mat.release();
        return generate_0;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
