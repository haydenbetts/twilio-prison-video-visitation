package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class LRNLayer extends Layer {
    @opencv_core.Ptr
    public static native LRNLayer create(@ByRef @Const LayerParams layerParams);

    public native float alpha();

    public native LRNLayer alpha(float f);

    public native float beta();

    public native LRNLayer beta(float f);

    public native float bias();

    public native LRNLayer bias(float f);

    @Name({"type"})
    public native int lrnType();

    public native LRNLayer lrnType(int i);

    public native LRNLayer normBySize(boolean z);

    @Cast({"bool"})
    public native boolean normBySize();

    public native int size();

    public native LRNLayer size(int i);

    static {
        Loader.load();
    }

    public LRNLayer(Pointer pointer) {
        super(pointer);
    }
}
