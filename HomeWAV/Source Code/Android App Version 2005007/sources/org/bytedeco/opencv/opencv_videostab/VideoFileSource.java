package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class VideoFileSource extends IFrameSource {
    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, @Cast({"bool"}) boolean z);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, @Cast({"bool"}) boolean z);

    public native int count();

    public native double fps();

    public native int height();

    @ByVal
    public native Mat nextFrame();

    public native void reset();

    public native int width();

    static {
        Loader.load();
    }

    public VideoFileSource(Pointer pointer) {
        super(pointer);
    }

    public VideoFileSource(@opencv_core.Str BytePointer bytePointer, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(bytePointer, z);
    }

    public VideoFileSource(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public VideoFileSource(@opencv_core.Str String str, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(str, z);
    }

    public VideoFileSource(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
