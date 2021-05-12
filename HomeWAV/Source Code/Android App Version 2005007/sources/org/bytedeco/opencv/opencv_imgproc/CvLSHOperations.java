package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
@Opaque
public class CvLSHOperations extends Pointer {
    public CvLSHOperations() {
        super((Pointer) null);
    }

    public CvLSHOperations(Pointer pointer) {
        super(pointer);
    }
}
