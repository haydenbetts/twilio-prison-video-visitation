package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class Dict extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native void erase(@opencv_core.Str String str);

    public native void erase(@opencv_core.Str BytePointer bytePointer);

    @ByRef
    @Const
    public native DictValue get(@opencv_core.Str String str);

    @ByRef
    @Const
    public native DictValue get(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean has(@opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean has(@opencv_core.Str BytePointer bytePointer);

    public native DictValue ptr(@opencv_core.Str String str);

    public native DictValue ptr(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public Dict() {
        super((Pointer) null);
        allocate();
    }

    public Dict(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Dict(Pointer pointer) {
        super(pointer);
    }

    public Dict position(long j) {
        return (Dict) super.position(j);
    }

    public Dict getPointer(long j) {
        return new Dict((Pointer) this).position(this.position + j);
    }
}
