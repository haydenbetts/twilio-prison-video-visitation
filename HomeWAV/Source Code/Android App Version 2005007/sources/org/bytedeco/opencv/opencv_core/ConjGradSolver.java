package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.MinProblemSolver;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class ConjGradSolver extends MinProblemSolver {
    @opencv_core.Ptr
    public static native ConjGradSolver create();

    @opencv_core.Ptr
    public static native ConjGradSolver create(@opencv_core.Ptr MinProblemSolver.Function function, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER+cv::TermCriteria::EPS,5000,0.000001)") TermCriteria termCriteria);

    static {
        Loader.load();
    }

    public ConjGradSolver(Pointer pointer) {
        super(pointer);
    }
}
