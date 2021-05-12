package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
@Opaque
public class CvFeatureTree extends Pointer {
    public CvFeatureTree() {
        super((Pointer) null);
    }

    public CvFeatureTree(Pointer pointer) {
        super(pointer);
    }
}
