package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvSet extends CvSeq {
    public AbstractCvSet(Pointer pointer) {
        super(pointer);
    }

    public static CvSet create(int i, int i2, int i3, CvMemStorage cvMemStorage) {
        return org.bytedeco.opencv.global.opencv_core.cvCreateSet(i, i2, i3, cvMemStorage);
    }
}
