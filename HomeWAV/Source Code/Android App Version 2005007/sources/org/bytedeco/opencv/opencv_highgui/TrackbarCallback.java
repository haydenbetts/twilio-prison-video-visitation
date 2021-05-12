package org.bytedeco.opencv.opencv_highgui;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_highgui;

@Properties(inherit = {opencv_highgui.class})
public class TrackbarCallback extends FunctionPointer {
    private native void allocate();

    public native void call(int i, Pointer pointer);

    static {
        Loader.load();
    }

    public TrackbarCallback(Pointer pointer) {
        super(pointer);
    }

    protected TrackbarCallback() {
        allocate();
    }
}
