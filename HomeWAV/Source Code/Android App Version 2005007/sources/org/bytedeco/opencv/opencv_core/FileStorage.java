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
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class FileStorage extends Pointer {
    public static final int APPEND = 2;
    public static final int BASE64 = 64;
    public static final int FORMAT_AUTO = 0;
    public static final int FORMAT_JSON = 24;
    public static final int FORMAT_MASK = 56;
    public static final int FORMAT_XML = 8;
    public static final int FORMAT_YAML = 16;
    public static final int INSIDE_MAP = 4;
    public static final int MEMORY = 4;
    public static final int NAME_EXPECTED = 2;
    public static final int READ = 0;
    public static final int UNDEFINED = 0;
    public static final int VALUE_EXPECTED = 1;
    public static final int WRITE = 1;
    public static final int WRITE_BASE64 = 65;

    private native void allocate();

    private native void allocate(@opencv_core.Str String str, int i);

    private native void allocate(@opencv_core.Str String str, int i, @opencv_core.Str String str2);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, int i, @opencv_core.Str BytePointer bytePointer2);

    private native void allocateArray(long j);

    @opencv_core.Str
    public static native String getDefaultObjectName(@opencv_core.Str String str);

    @opencv_core.Str
    public static native BytePointer getDefaultObjectName(@opencv_core.Str BytePointer bytePointer);

    @StdString
    public native BytePointer elname();

    public native FileStorage elname(BytePointer bytePointer);

    public native void endWriteStruct();

    @ByVal
    @Name({"operator []"})
    public native FileNode get(@opencv_core.Str String str);

    @ByVal
    @Name({"operator []"})
    public native FileNode get(@opencv_core.Str BytePointer bytePointer);

    @ByVal
    public native FileNode getFirstTopLevelNode();

    public native int getFormat();

    @ByVal
    @Name({"operator []"})
    public native FileNode getNode(String str);

    @ByVal
    @Name({"operator []"})
    public native FileNode getNode(@Cast({"const char*"}) BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean isOpened();

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str String str, int i, @opencv_core.Str String str2);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i);

    @Cast({"bool"})
    public native boolean open(@opencv_core.Str BytePointer bytePointer, int i, @opencv_core.Str BytePointer bytePointer2);

    public native void release();

    @opencv_core.Str
    public native BytePointer releaseAndGetString();

    @ByVal
    public native FileNode root();

    @ByVal
    public native FileNode root(int i);

    public native void startWriteStruct(@opencv_core.Str String str, int i);

    public native void startWriteStruct(@opencv_core.Str String str, int i, @opencv_core.Str String str2);

    public native void startWriteStruct(@opencv_core.Str BytePointer bytePointer, int i);

    public native void startWriteStruct(@opencv_core.Str BytePointer bytePointer, int i, @opencv_core.Str BytePointer bytePointer2);

    public native int state();

    public native FileStorage state(int i);

    public native void write(@opencv_core.Str String str, double d);

    public native void write(@opencv_core.Str String str, int i);

    public native void write(@opencv_core.Str String str, @opencv_core.Str String str2);

    public native void write(@opencv_core.Str String str, @ByRef @Const Mat mat);

    public native void write(@opencv_core.Str String str, @ByRef @Const StringVector stringVector);

    public native void write(@opencv_core.Str BytePointer bytePointer, double d);

    public native void write(@opencv_core.Str BytePointer bytePointer, int i);

    public native void write(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native void write(@opencv_core.Str BytePointer bytePointer, @ByRef @Const Mat mat);

    public native void write(@opencv_core.Str BytePointer bytePointer, @ByRef @Const StringVector stringVector);

    public native void writeComment(@opencv_core.Str String str);

    public native void writeComment(@opencv_core.Str String str, @Cast({"bool"}) boolean z);

    public native void writeComment(@opencv_core.Str BytePointer bytePointer);

    public native void writeComment(@opencv_core.Str BytePointer bytePointer, @Cast({"bool"}) boolean z);

    public native void writeRaw(@opencv_core.Str String str, @Const Pointer pointer, @Cast({"size_t"}) long j);

    public native void writeRaw(@opencv_core.Str BytePointer bytePointer, @Const Pointer pointer, @Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public FileStorage(Pointer pointer) {
        super(pointer);
    }

    public FileStorage(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FileStorage position(long j) {
        return (FileStorage) super.position(j);
    }

    public FileStorage getPointer(long j) {
        return new FileStorage((Pointer) this).position(this.position + j);
    }

    public FileStorage() {
        super((Pointer) null);
        allocate();
    }

    public FileStorage(@opencv_core.Str BytePointer bytePointer, int i, @opencv_core.Str BytePointer bytePointer2) {
        super((Pointer) null);
        allocate(bytePointer, i, bytePointer2);
    }

    public FileStorage(@opencv_core.Str BytePointer bytePointer, int i) {
        super((Pointer) null);
        allocate(bytePointer, i);
    }

    public FileStorage(@opencv_core.Str String str, int i, @opencv_core.Str String str2) {
        super((Pointer) null);
        allocate(str, i, str2);
    }

    public FileStorage(@opencv_core.Str String str, int i) {
        super((Pointer) null);
        allocate(str, i);
    }
}
