package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::internal")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class WriteStructContext extends Pointer {
    private native void allocate(@ByRef FileStorage fileStorage, @opencv_core.Str String str, int i);

    private native void allocate(@ByRef FileStorage fileStorage, @opencv_core.Str String str, int i, @opencv_core.Str String str2);

    private native void allocate(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, int i);

    private native void allocate(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, int i, @opencv_core.Str BytePointer bytePointer2);

    static {
        Loader.load();
    }

    public WriteStructContext(Pointer pointer) {
        super(pointer);
    }

    public WriteStructContext(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, int i, @opencv_core.Str BytePointer bytePointer2) {
        super((Pointer) null);
        allocate(fileStorage, bytePointer, i, bytePointer2);
    }

    public WriteStructContext(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer, int i) {
        super((Pointer) null);
        allocate(fileStorage, bytePointer, i);
    }

    public WriteStructContext(@ByRef FileStorage fileStorage, @opencv_core.Str String str, int i, @opencv_core.Str String str2) {
        super((Pointer) null);
        allocate(fileStorage, str, i, str2);
    }

    public WriteStructContext(@ByRef FileStorage fileStorage, @opencv_core.Str String str, int i) {
        super((Pointer) null);
        allocate(fileStorage, str, i);
    }
}
