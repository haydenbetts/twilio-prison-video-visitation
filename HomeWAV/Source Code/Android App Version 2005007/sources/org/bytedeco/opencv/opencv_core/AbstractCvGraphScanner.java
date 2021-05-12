package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvGraphScanner extends Pointer {
    public AbstractCvGraphScanner(Pointer pointer) {
        super(pointer);
    }

    public static CvGraphScanner create(CvGraph cvGraph, CvGraphVtx cvGraphVtx, int i) {
        CvGraphScanner cvCreateGraphScanner = org.bytedeco.opencv.global.opencv_core.cvCreateGraphScanner(cvGraph, cvGraphVtx, i);
        if (cvCreateGraphScanner != null) {
            cvCreateGraphScanner.deallocator(new ReleaseDeallocator(cvCreateGraphScanner));
        }
        return cvCreateGraphScanner;
    }

    public void release() {
        deallocate();
    }

    protected static class ReleaseDeallocator extends CvGraphScanner implements Pointer.Deallocator {
        ReleaseDeallocator(CvGraphScanner cvGraphScanner) {
            super((Pointer) cvGraphScanner);
        }

        public void deallocate() {
            org.bytedeco.opencv.global.opencv_core.cvReleaseGraphScanner((CvGraphScanner) this);
        }
    }
}
