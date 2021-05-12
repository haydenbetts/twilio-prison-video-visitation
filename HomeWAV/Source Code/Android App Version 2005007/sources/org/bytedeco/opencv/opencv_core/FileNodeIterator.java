package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class FileNodeIterator extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef @Const FileNode fileNode, @Cast({"bool"}) boolean z);

    private native void allocate(@ByRef @Const FileNodeIterator fileNodeIterator);

    private native void allocateArray(long j);

    @ByRef
    @Name({"operator +="})
    public native FileNodeIterator addPut(int i);

    @Cast({"bool"})
    public native boolean equalTo(@ByRef @Const FileNodeIterator fileNodeIterator);

    @ByRef
    @Name({"operator ++"})
    public native FileNodeIterator increment();

    @ByVal
    @Name({"operator ++"})
    public native FileNodeIterator increment(int i);

    @ByVal
    @Name({"operator *"})
    public native FileNode multiply();

    @ByRef
    @Name({"operator ="})
    public native FileNodeIterator put(@ByRef @Const FileNodeIterator fileNodeIterator);

    @ByRef
    public native FileNodeIterator readRaw(@opencv_core.Str String str, Pointer pointer);

    @ByRef
    public native FileNodeIterator readRaw(@opencv_core.Str String str, Pointer pointer, @Cast({"size_t"}) long j);

    @ByRef
    public native FileNodeIterator readRaw(@opencv_core.Str BytePointer bytePointer, Pointer pointer);

    @ByRef
    public native FileNodeIterator readRaw(@opencv_core.Str BytePointer bytePointer, Pointer pointer, @Cast({"size_t"}) long j);

    @Cast({"size_t"})
    public native long remaining();

    static {
        Loader.load();
    }

    public FileNodeIterator(Pointer pointer) {
        super(pointer);
    }

    public FileNodeIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FileNodeIterator position(long j) {
        return (FileNodeIterator) super.position(j);
    }

    public FileNodeIterator getPointer(long j) {
        return new FileNodeIterator(this).position(this.position + j);
    }

    public FileNodeIterator() {
        super((Pointer) null);
        allocate();
    }

    public FileNodeIterator(@ByRef @Const FileNode fileNode, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(fileNode, z);
    }

    public FileNodeIterator(@ByRef @Const FileNodeIterator fileNodeIterator) {
        super((Pointer) null);
        allocate(fileNodeIterator);
    }
}
