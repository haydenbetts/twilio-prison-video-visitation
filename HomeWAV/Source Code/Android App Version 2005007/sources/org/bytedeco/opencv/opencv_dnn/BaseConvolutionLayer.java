package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class BaseConvolutionLayer extends Layer {
    @ByRef
    @Deprecated
    public native Size adjustPad();

    public native BaseConvolutionLayer adjustPad(Size size);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer adjust_pads();

    public native BaseConvolutionLayer adjust_pads(SizeTPointer sizeTPointer);

    @ByRef
    @Deprecated
    public native Size dilation();

    public native BaseConvolutionLayer dilation(Size size);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer dilations();

    public native BaseConvolutionLayer dilations(SizeTPointer sizeTPointer);

    @ByRef
    @Deprecated
    public native Size kernel();

    public native BaseConvolutionLayer kernel(Size size);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer kernel_size();

    public native BaseConvolutionLayer kernel_size(SizeTPointer sizeTPointer);

    public native int numOutput();

    public native BaseConvolutionLayer numOutput(int i);

    @ByRef
    @Deprecated
    public native Size pad();

    public native BaseConvolutionLayer pad(Size size);

    @opencv_core.Str
    public native BytePointer padMode();

    public native BaseConvolutionLayer padMode(BytePointer bytePointer);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer pads_begin();

    public native BaseConvolutionLayer pads_begin(SizeTPointer sizeTPointer);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer pads_end();

    public native BaseConvolutionLayer pads_end(SizeTPointer sizeTPointer);

    @ByRef
    @Deprecated
    public native Size stride();

    public native BaseConvolutionLayer stride(Size size);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer strides();

    public native BaseConvolutionLayer strides(SizeTPointer sizeTPointer);

    static {
        Loader.load();
    }

    public BaseConvolutionLayer(Pointer pointer) {
        super(pointer);
    }
}
