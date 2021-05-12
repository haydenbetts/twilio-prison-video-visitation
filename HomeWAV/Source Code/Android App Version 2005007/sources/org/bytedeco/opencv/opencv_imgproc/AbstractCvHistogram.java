package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.CvHistogram;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public abstract class AbstractCvHistogram extends Pointer {
    public AbstractCvHistogram(Pointer pointer) {
        super(pointer);
    }

    public static CvHistogram create(int i, int[] iArr, int i2, float[][] fArr, int i3) {
        CvHistogram cvCreateHist = org.bytedeco.opencv.global.opencv_imgproc.cvCreateHist(i, iArr, i2, fArr, i3);
        if (cvCreateHist != null) {
            cvCreateHist.deallocator(new ReleaseDeallocator(cvCreateHist));
        }
        return cvCreateHist;
    }

    public void release() {
        deallocate();
    }

    static class ReleaseDeallocator extends CvHistogram implements Pointer.Deallocator {
        ReleaseDeallocator(CvHistogram cvHistogram) {
            super((Pointer) cvHistogram);
        }

        public void deallocate() {
            org.bytedeco.opencv.global.opencv_imgproc.cvReleaseHist((CvHistogram) this);
        }
    }
}
