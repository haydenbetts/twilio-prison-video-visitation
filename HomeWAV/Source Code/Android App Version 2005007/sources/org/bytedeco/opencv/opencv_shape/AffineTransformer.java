package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
public class AffineTransformer extends ShapeTransformer {
    @Cast({"bool"})
    public native boolean getFullAffine();

    public native void setFullAffine(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public AffineTransformer(Pointer pointer) {
        super(pointer);
    }
}
