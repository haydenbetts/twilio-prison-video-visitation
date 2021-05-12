package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class Algorithm extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native void clear();

    @Cast({"bool"})
    public native boolean empty();

    @opencv_core.Str
    public native BytePointer getDefaultName();

    public native void read(@ByRef @Const FileNode fileNode);

    public native void save(@opencv_core.Str String str);

    public native void save(@opencv_core.Str BytePointer bytePointer);

    public native void write(@ByRef FileStorage fileStorage);

    public native void write(@opencv_core.Ptr FileStorage fileStorage, @opencv_core.Str String str);

    public native void write(@opencv_core.Ptr FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public Algorithm(Pointer pointer) {
        super(pointer);
    }

    public Algorithm(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Algorithm position(long j) {
        return (Algorithm) super.position(j);
    }

    public Algorithm getPointer(long j) {
        return new Algorithm((Pointer) this).position(this.position + j);
    }

    public Algorithm() {
        super((Pointer) null);
        allocate();
    }
}
