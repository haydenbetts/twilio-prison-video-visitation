package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Convention("CV_STDCALL")
public class Cv_iplCreateROI extends FunctionPointer {
    private native void allocate();

    public native IplROI call(int i, int i2, int i3, int i4, int i5);

    static {
        Loader.load();
    }

    public Cv_iplCreateROI(Pointer pointer) {
        super(pointer);
    }

    protected Cv_iplCreateROI() {
        allocate();
    }
}
