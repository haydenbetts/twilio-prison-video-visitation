package org.bytedeco.opencv.opencv_cudacodec;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_cudacodec;

@Namespace("cv::cudacodec")
@Properties(inherit = {opencv_cudacodec.class})
public class FormatInfo extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"cv::cudacodec::ChromaFormat"})
    public native int chromaFormat();

    public native FormatInfo chromaFormat(int i);

    @Cast({"cv::cudacodec::Codec"})
    public native int codec();

    public native FormatInfo codec(int i);

    public native int height();

    public native FormatInfo height(int i);

    public native int nBitDepthMinus8();

    public native FormatInfo nBitDepthMinus8(int i);

    public native int width();

    public native FormatInfo width(int i);

    static {
        Loader.load();
    }

    public FormatInfo() {
        super((Pointer) null);
        allocate();
    }

    public FormatInfo(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FormatInfo(Pointer pointer) {
        super(pointer);
    }

    public FormatInfo position(long j) {
        return (FormatInfo) super.position(j);
    }

    public FormatInfo getPointer(long j) {
        return new FormatInfo((Pointer) this).position(this.position + j);
    }
}
