package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Convention("CV_STDCALL")
public class Cv_iplCloneImage extends FunctionPointer {
    private native void allocate();

    public native IplImage call(@Const IplImage iplImage);

    static {
        Loader.load();
    }

    public Cv_iplCloneImage(Pointer pointer) {
        super(pointer);
    }

    protected Cv_iplCloneImage() {
        allocate();
    }
}
