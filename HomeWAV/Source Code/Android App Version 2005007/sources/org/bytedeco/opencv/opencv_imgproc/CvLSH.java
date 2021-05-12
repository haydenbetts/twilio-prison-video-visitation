package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
@Opaque
public class CvLSH extends Pointer {
    public CvLSH() {
        super((Pointer) null);
    }

    public CvLSH(Pointer pointer) {
        super(pointer);
    }
}
