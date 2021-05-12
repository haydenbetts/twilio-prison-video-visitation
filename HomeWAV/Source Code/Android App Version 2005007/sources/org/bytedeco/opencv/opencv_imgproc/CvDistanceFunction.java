package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
@Convention("CV_CDECL")
public class CvDistanceFunction extends FunctionPointer {
    private native void allocate();

    public native float call(@Const FloatPointer floatPointer, @Const FloatPointer floatPointer2, Pointer pointer);

    static {
        Loader.load();
    }

    public CvDistanceFunction(Pointer pointer) {
        super(pointer);
    }

    protected CvDistanceFunction() {
        allocate();
    }
}
