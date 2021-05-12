package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2fVectorVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
public class Facemark extends Algorithm {
    @Cast({"bool"})
    public native boolean fit(@ByVal Mat mat, @ByRef RectVector rectVector, @ByRef Point2fVectorVector point2fVectorVector);

    public native void loadModel(@opencv_core.Str String str);

    public native void loadModel(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public Facemark(Pointer pointer) {
        super(pointer);
    }
}
