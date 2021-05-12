package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Convention("CV_CDECL")
public class CvCmpFunc extends FunctionPointer {
    private native void allocate();

    public native int call(@Const Pointer pointer, @Const Pointer pointer2, Pointer pointer3);

    static {
        Loader.load();
    }

    public CvCmpFunc(Pointer pointer) {
        super(pointer);
    }

    protected CvCmpFunc() {
        allocate();
    }
}
