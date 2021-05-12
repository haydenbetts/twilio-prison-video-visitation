package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvSeq extends CvArr {
    public AbstractCvSeq(Pointer pointer) {
        super(pointer);
    }

    public static CvSeq create(int i, int i2, int i3, CvMemStorage cvMemStorage) {
        return org.bytedeco.opencv.global.opencv_core.cvCreateSeq(i, (long) i2, (long) i3, cvMemStorage);
    }
}
