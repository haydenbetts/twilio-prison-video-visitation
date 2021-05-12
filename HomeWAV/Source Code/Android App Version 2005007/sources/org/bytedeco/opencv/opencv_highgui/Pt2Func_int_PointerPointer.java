package org.bytedeco.opencv.opencv_highgui;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_highgui;

@Properties(inherit = {opencv_highgui.class})
public class Pt2Func_int_PointerPointer extends FunctionPointer {
    private native void allocate();

    public native int call(int i, @Cast({"char**"}) PointerPointer pointerPointer);

    static {
        Loader.load();
    }

    public Pt2Func_int_PointerPointer(Pointer pointer) {
        super(pointer);
    }

    protected Pt2Func_int_PointerPointer() {
        allocate();
    }
}
