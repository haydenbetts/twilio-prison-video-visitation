package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
public class FacemarkTrain extends Facemark {
    @Cast({"bool"})
    public native boolean addTrainingSample(@ByVal Mat mat, @ByRef Point2fVector point2fVector);

    @Cast({"bool"})
    public native boolean getData();

    @Cast({"bool"})
    public native boolean getData(Pointer pointer);

    @Cast({"bool"})
    public native boolean getFaces(@ByVal Mat mat, @ByRef RectVector rectVector);

    @Cast({"bool"})
    public native boolean setFaceDetector(@Cast({"cv::face::FN_FaceDetector"}) Pointer pointer);

    @Cast({"bool"})
    public native boolean setFaceDetector(@Cast({"cv::face::FN_FaceDetector"}) Pointer pointer, Pointer pointer2);

    public native void training();

    public native void training(Pointer pointer);

    static {
        Loader.load();
    }

    public FacemarkTrain(Pointer pointer) {
        super(pointer);
    }
}
