package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
public class LBPHFaceRecognizer extends FaceRecognizer {
    @opencv_core.Ptr
    public static native LBPHFaceRecognizer create();

    @opencv_core.Ptr
    public static native LBPHFaceRecognizer create(int i, int i2, int i3, int i4, double d);

    public native int getGridX();

    public native int getGridY();

    @ByVal
    public native MatVector getHistograms();

    @ByVal
    public native Mat getLabels();

    public native int getNeighbors();

    public native int getRadius();

    public native double getThreshold();

    public native void setGridX(int i);

    public native void setGridY(int i);

    public native void setNeighbors(int i);

    public native void setRadius(int i);

    public native void setThreshold(double d);

    static {
        Loader.load();
    }

    public LBPHFaceRecognizer(Pointer pointer) {
        super(pointer);
    }
}
