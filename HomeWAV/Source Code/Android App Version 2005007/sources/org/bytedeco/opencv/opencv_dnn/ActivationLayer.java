package org.bytedeco.opencv.opencv_dnn;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class ActivationLayer extends Layer {
    public native void forwardSlice(@Const FloatBuffer floatBuffer, FloatBuffer floatBuffer2, int i, @Cast({"size_t"}) long j, int i2, int i3);

    public native void forwardSlice(@Const FloatPointer floatPointer, FloatPointer floatPointer2, int i, @Cast({"size_t"}) long j, int i2, int i3);

    public native void forwardSlice(@Const float[] fArr, float[] fArr2, int i, @Cast({"size_t"}) long j, int i2, int i3);

    static {
        Loader.load();
    }

    public ActivationLayer(Pointer pointer) {
        super(pointer);
    }
}
