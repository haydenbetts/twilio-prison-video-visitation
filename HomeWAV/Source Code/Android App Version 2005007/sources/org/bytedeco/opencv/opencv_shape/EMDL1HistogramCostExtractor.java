package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
@Opaque
public class EMDL1HistogramCostExtractor extends HistogramCostExtractor {
    public EMDL1HistogramCostExtractor() {
        super((Pointer) null);
    }

    public EMDL1HistogramCostExtractor(Pointer pointer) {
        super(pointer);
    }
}
