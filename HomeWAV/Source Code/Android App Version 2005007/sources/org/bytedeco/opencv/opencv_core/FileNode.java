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
public class FileNode extends Pointer {
    public static final int EMPTY = 16;
    public static final int FLOAT = 2;
    public static final int FLOW = 8;
    public static final int INT = 1;
    public static final int MAP = 5;
    public static final int NAMED = 32;
    public static final int NONE = 0;
    public static final int REAL = 2;
    public static final int SEQ = 4;
    public static final int STR = 3;
    public static final int STRING = 3;
    public static final int TYPE_MASK = 7;
    public static final int UNIFORM = 8;

    private native void allocate();

    private native void allocate(@ByRef @Const FileNode fileNode);

    private native void allocate(@Const FileStorage fileStorage, @Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    private native void allocateArray(long j);

    @Cast({"bool"})
    public static native boolean isCollection(int i);

    @Cast({"bool"})
    public static native boolean isEmptyCollection(int i);

    @Cast({"bool"})
    public static native boolean isFlow(int i);

    @Cast({"bool"})
    public static native boolean isMap(int i);

    @Cast({"bool"})
    public static native boolean isSeq(int i);

    @StdString
    @Name({"operator std::string"})
    public native BytePointer asBytePointer();

    @Name({"operator double"})
    public native double asDouble();

    @Name({"operator float"})
    public native float asFloat();

    @Name({"operator int"})
    public native int asInt();

    @ByVal
    @Name({"operator []"})
    public native FileNode at(int i);

    @ByVal
    public native FileNodeIterator begin();

    @Cast({"size_t"})
    public native long blockIdx();

    public native FileNode blockIdx(long j);

    @Cast({"bool"})
    public native boolean empty();

    @ByVal
    public native FileNodeIterator end();

    @ByVal
    @Name({"operator []"})
    public native FileNode get(@opencv_core.Str String str);

    @ByVal
    @Name({"operator []"})
    public native FileNode get(@opencv_core.Str BytePointer bytePointer);

    @ByVal
    @Name({"operator []"})
    public native FileNode getNode(String str);

    @ByVal
    @Name({"operator []"})
    public native FileNode getNode(@Cast({"const char*"}) BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean isInt();

    @Cast({"bool"})
    public native boolean isMap();

    @Cast({"bool"})
    public native boolean isNamed();

    @Cast({"bool"})
    public native boolean isNone();

    @Cast({"bool"})
    public native boolean isReal();

    @Cast({"bool"})
    public native boolean isSeq();

    @Cast({"bool"})
    public native boolean isString();

    @ByVal
    public native StringVector keys();

    @ByVal
    public native Mat mat();

    @StdString
    public native BytePointer name();

    @Cast({"size_t"})
    public native long ofs();

    public native FileNode ofs(long j);

    @Cast({"uchar*"})
    public native BytePointer ptr();

    @ByRef
    @Name({"operator ="})
    public native FileNode put(@ByRef @Const FileNode fileNode);

    @Cast({"size_t"})
    public native long rawSize();

    public native void readRaw(@opencv_core.Str String str, Pointer pointer, @Cast({"size_t"}) long j);

    public native void readRaw(@opencv_core.Str BytePointer bytePointer, Pointer pointer, @Cast({"size_t"}) long j);

    public native double real();

    public native void setValue(int i, @Const Pointer pointer);

    public native void setValue(int i, @Const Pointer pointer, int i2);

    @Cast({"size_t"})
    public native long size();

    @StdString
    public native BytePointer string();

    public native int type();

    static {
        Loader.load();
    }

    public FileNode(Pointer pointer) {
        super(pointer);
    }

    public FileNode(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FileNode position(long j) {
        return (FileNode) super.position(j);
    }

    public FileNode getPointer(long j) {
        return new FileNode(this).position(this.position + j);
    }

    public FileNode() {
        super((Pointer) null);
        allocate();
    }

    public FileNode(@Const FileStorage fileStorage, @Cast({"size_t"}) long j, @Cast({"size_t"}) long j2) {
        super((Pointer) null);
        allocate(fileStorage, j, j2);
    }

    public FileNode(@ByRef @Const FileNode fileNode) {
        super((Pointer) null);
        allocate(fileNode);
    }
}
