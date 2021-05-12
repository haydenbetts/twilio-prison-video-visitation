package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public abstract class AbstractCvMoments extends Pointer {
    public AbstractCvMoments(Pointer pointer) {
        super(pointer);
    }

    public static ThreadLocal<CvMoments> createThreadLocal() {
        return new ThreadLocal<CvMoments>() {
            /* access modifiers changed from: protected */
            public CvMoments initialValue() {
                return new CvMoments();
            }
        };
    }
}
