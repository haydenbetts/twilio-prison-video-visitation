package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
@Opaque
public class ChiHistogramCostExtractor extends HistogramCostExtractor {
    public ChiHistogramCostExtractor() {
        super((Pointer) null);
    }

    public ChiHistogramCostExtractor(Pointer pointer) {
        super(pointer);
    }
}
