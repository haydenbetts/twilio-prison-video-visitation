package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::hal")
@Properties(inherit = {opencv_core.class})
public class DFT1D extends Pointer {
    @opencv_core.Ptr
    public static native DFT1D create(int i, int i2, int i3, int i4);

    @opencv_core.Ptr
    public static native DFT1D create(int i, int i2, int i3, int i4, @Cast({"bool*"}) BoolPointer boolPointer);

    @opencv_core.Ptr
    public static native DFT1D create(int i, int i2, int i3, int i4, @Cast({"bool*"}) boolean[] zArr);

    public native void apply(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"uchar*"}) ByteBuffer byteBuffer2);

    public native void apply(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"uchar*"}) BytePointer bytePointer2);

    public native void apply(@Cast({"const uchar*"}) byte[] bArr, @Cast({"uchar*"}) byte[] bArr2);

    static {
        Loader.load();
    }

    public DFT1D(Pointer pointer) {
        super(pointer);
    }
}
