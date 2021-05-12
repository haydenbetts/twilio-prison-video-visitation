package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class Hamming extends Pointer {
    public static final int normType = normType();

    private native void allocate();

    private native void allocateArray(long j);

    @MemberGetter
    @Cast({"const cv::NormTypes"})
    public static native int normType();

    @Cast({"cv::Hamming::ResultType"})
    @Name({"operator ()"})
    public native int apply(@Cast({"const unsigned char*"}) ByteBuffer byteBuffer, @Cast({"const unsigned char*"}) ByteBuffer byteBuffer2, int i);

    @Cast({"cv::Hamming::ResultType"})
    @Name({"operator ()"})
    public native int apply(@Cast({"const unsigned char*"}) BytePointer bytePointer, @Cast({"const unsigned char*"}) BytePointer bytePointer2, int i);

    @Cast({"cv::Hamming::ResultType"})
    @Name({"operator ()"})
    public native int apply(@Cast({"const unsigned char*"}) byte[] bArr, @Cast({"const unsigned char*"}) byte[] bArr2, int i);

    static {
        Loader.load();
    }

    public Hamming() {
        super((Pointer) null);
        allocate();
    }

    public Hamming(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Hamming(Pointer pointer) {
        super(pointer);
    }

    public Hamming position(long j) {
        return (Hamming) super.position(j);
    }

    public Hamming getPointer(long j) {
        return new Hamming((Pointer) this).position(this.position + j);
    }
}
