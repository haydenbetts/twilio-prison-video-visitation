package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
public class ThinPlateSplineShapeTransformer extends ShapeTransformer {
    public native double getRegularizationParameter();

    public native void setRegularizationParameter(double d);

    static {
        Loader.load();
    }

    public ThinPlateSplineShapeTransformer(Pointer pointer) {
        super(pointer);
    }
}
