package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
public class HausdorffDistanceExtractor extends ShapeDistanceExtractor {
    public native int getDistanceFlag();

    public native float getRankProportion();

    public native void setDistanceFlag(int i);

    public native void setRankProportion(float f);

    static {
        Loader.load();
    }

    public HausdorffDistanceExtractor(Pointer pointer) {
        super(pointer);
    }
}
