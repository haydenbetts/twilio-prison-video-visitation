package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
@Opaque
@Name({"_CvContourScanner"})
public class CvContourScanner extends Pointer {
    public CvContourScanner() {
        super((Pointer) null);
    }

    public CvContourScanner(Pointer pointer) {
        super(pointer);
    }
}
