package org.bytedeco.opencv.opencv_objdetect;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_objdetect;

@Properties(inherit = {opencv_objdetect.class})
@Opaque
public class CvHaarClassifierCascade extends Pointer {
    public CvHaarClassifierCascade() {
        super((Pointer) null);
    }

    public CvHaarClassifierCascade(Pointer pointer) {
        super(pointer);
    }
}
