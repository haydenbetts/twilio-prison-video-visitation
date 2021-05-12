package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::hal")
@Properties(inherit = {opencv_core.class})
public class DFT2D extends Pointer {
    @opencv_core.Ptr
    public static native DFT2D create(int i, int i2, int i3, int i4, int i5, int i6);

    @opencv_core.Ptr
    public static native DFT2D create(int i, int i2, int i3, int i4, int i5, int i6, int i7);

    public native void apply(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2);

    public native void apply(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2);

    public native void apply(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2);

    static {
        Loader.load();
    }

    public DFT2D(Pointer pointer) {
        super(pointer);
    }
}
