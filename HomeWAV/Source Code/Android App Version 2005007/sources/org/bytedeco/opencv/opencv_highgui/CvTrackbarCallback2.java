package org.bytedeco.opencv.opencv_highgui;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_highgui;

@Properties(inherit = {opencv_highgui.class})
@Convention("CV_CDECL")
public class CvTrackbarCallback2 extends FunctionPointer {
    private native void allocate();

    public native void call(int i, Pointer pointer);

    static {
        Loader.load();
    }

    public CvTrackbarCallback2(Pointer pointer) {
        super(pointer);
    }

    protected CvTrackbarCallback2() {
        allocate();
    }
}
