package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvGraph extends CvSet {
    public AbstractCvGraph(Pointer pointer) {
        super(pointer);
    }

    public static CvGraph create(int i, int i2, int i3, int i4, CvMemStorage cvMemStorage) {
        return org.bytedeco.opencv.global.opencv_core.cvCreateGraph(i, i2, i3, i4, cvMemStorage);
    }
}
