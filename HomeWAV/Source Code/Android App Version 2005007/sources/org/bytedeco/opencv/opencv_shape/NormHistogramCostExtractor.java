package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
public class NormHistogramCostExtractor extends HistogramCostExtractor {
    public native int getNormFlag();

    public native void setNormFlag(int i);

    static {
        Loader.load();
    }

    public NormHistogramCostExtractor(Pointer pointer) {
        super(pointer);
    }
}
