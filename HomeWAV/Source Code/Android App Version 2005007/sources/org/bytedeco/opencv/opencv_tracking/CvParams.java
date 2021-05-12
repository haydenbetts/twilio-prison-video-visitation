package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class CvParams extends Pointer {
    @StdString
    public native BytePointer name();

    public native CvParams name(BytePointer bytePointer);

    public native void printAttrs();

    public native void printDefaults();

    @Cast({"bool"})
    public native boolean read(@ByRef @Const FileNode fileNode);

    @Cast({"bool"})
    public native boolean scanAttr(@StdString String str, @StdString String str2);

    @Cast({"bool"})
    public native boolean scanAttr(@StdString BytePointer bytePointer, @StdString BytePointer bytePointer2);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public CvParams(Pointer pointer) {
        super(pointer);
    }
}
