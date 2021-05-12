package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
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
public class PoolingLayer extends Layer {
    @opencv_core.Ptr
    public static native PoolingLayer create(@ByRef @Const LayerParams layerParams);

    public native PoolingLayer avePoolPaddedArea(boolean z);

    @Cast({"bool"})
    public native boolean avePoolPaddedArea();

    public native PoolingLayer ceilMode(boolean z);

    @Cast({"bool"})
    public native boolean ceilMode();

    public native PoolingLayer computeMaxIdx(boolean z);

    @Cast({"bool"})
    public native boolean computeMaxIdx();

    public native PoolingLayer globalPooling(boolean z);

    @Cast({"bool"})
    public native boolean globalPooling();

    @Cast({"bool*"})
    @StdVector
    public native BoolPointer isGlobalPooling();

    public native PoolingLayer isGlobalPooling(BoolPointer boolPointer);

    @ByRef
    @Deprecated
    public native Size kernel();

    public native PoolingLayer kernel(Size size);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer kernel_size();

    public native PoolingLayer kernel_size(SizeTPointer sizeTPointer);

    @ByRef
    @Deprecated
    public native Size pad();

    public native PoolingLayer pad(Size size);

    @opencv_core.Str
    public native BytePointer padMode();

    public native PoolingLayer padMode(BytePointer bytePointer);

    @Deprecated
    public native int pad_b();

    public native PoolingLayer pad_b(int i);

    @Deprecated
    public native int pad_l();

    public native PoolingLayer pad_l(int i);

    @Deprecated
    public native int pad_r();

    public native PoolingLayer pad_r(int i);

    @Deprecated
    public native int pad_t();

    public native PoolingLayer pad_t(int i);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer pads_begin();

    public native PoolingLayer pads_begin(SizeTPointer sizeTPointer);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer pads_end();

    public native PoolingLayer pads_end(SizeTPointer sizeTPointer);

    @ByRef
    public native Size pooledSize();

    public native PoolingLayer pooledSize(Size size);

    @Name({"type"})
    public native int poolingType();

    public native PoolingLayer poolingType(int i);

    public native int psRoiOutChannels();

    public native PoolingLayer psRoiOutChannels(int i);

    public native float spatialScale();

    public native PoolingLayer spatialScale(float f);

    @ByRef
    @Deprecated
    public native Size stride();

    public native PoolingLayer stride(Size size);

    @Cast({"size_t*"})
    @StdVector
    public native SizeTPointer strides();

    public native PoolingLayer strides(SizeTPointer sizeTPointer);

    static {
        Loader.load();
    }

    public PoolingLayer(Pointer pointer) {
        super(pointer);
    }
}
