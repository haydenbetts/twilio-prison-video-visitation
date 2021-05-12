package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class DictValue extends Pointer {
    private native void allocate();

    private native void allocate(double d);

    private native void allocate(int i);

    private native void allocate(@Cast({"int64"}) long j);

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@ByRef @Const DictValue dictValue);

    private native void allocate(@Cast({"bool"}) boolean z);

    public native int getIntValue();

    public native int getIntValue(int i);

    public native double getRealValue();

    public native double getRealValue(int i);

    @opencv_core.Str
    public native BytePointer getStringValue();

    @opencv_core.Str
    public native BytePointer getStringValue(int i);

    @Cast({"bool"})
    public native boolean isInt();

    @Cast({"bool"})
    public native boolean isReal();

    @Cast({"bool"})
    public native boolean isString();

    @ByRef
    @Name({"operator ="})
    public native DictValue put(@ByRef @Const DictValue dictValue);

    public native int size();

    static {
        Loader.load();
    }

    public DictValue(Pointer pointer) {
        super(pointer);
    }

    public DictValue(@ByRef @Const DictValue dictValue) {
        super((Pointer) null);
        allocate(dictValue);
    }

    public DictValue(@Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(z);
    }

    public DictValue(@Cast({"int64"}) long j) {
        super((Pointer) null);
        allocate(j);
    }

    public DictValue() {
        super((Pointer) null);
        allocate();
    }

    public DictValue(int i) {
        super((Pointer) null);
        allocate(i);
    }

    public DictValue(double d) {
        super((Pointer) null);
        allocate(d);
    }

    public DictValue(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public DictValue(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
