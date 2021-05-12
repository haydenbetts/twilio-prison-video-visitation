package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Callback_Pointer_int_BytePointer_Pointer extends FunctionPointer {
    private native void allocate();

    public native void call(Pointer pointer, int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"va_list*"}) @ByVal Pointer pointer2);

    static {
        Loader.load();
    }

    public Callback_Pointer_int_BytePointer_Pointer(Pointer pointer) {
        super(pointer);
    }

    protected Callback_Pointer_int_BytePointer_Pointer() {
        allocate();
    }
}
