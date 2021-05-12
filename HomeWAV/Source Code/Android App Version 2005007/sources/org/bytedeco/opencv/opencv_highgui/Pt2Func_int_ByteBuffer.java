package org.bytedeco.opencv.opencv_highgui;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_highgui;

@Properties(inherit = {opencv_highgui.class})
public class Pt2Func_int_ByteBuffer extends FunctionPointer {
    private native void allocate();

    public native int call(int i, @ByPtrPtr @Cast({"char**"}) ByteBuffer byteBuffer);

    static {
        Loader.load();
    }

    public Pt2Func_int_ByteBuffer(Pointer pointer) {
        super(pointer);
    }

    protected Pt2Func_int_ByteBuffer() {
        allocate();
    }
}
