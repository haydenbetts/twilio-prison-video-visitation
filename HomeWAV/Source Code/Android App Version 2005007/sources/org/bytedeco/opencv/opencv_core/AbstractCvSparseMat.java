package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvSparseMat extends CvArr {
    public AbstractCvSparseMat(Pointer pointer) {
        super(pointer);
    }

    public static CvSparseMat create(int i, int[] iArr, int i2) {
        CvSparseMat cvCreateSparseMat = org.bytedeco.opencv.global.opencv_core.cvCreateSparseMat(i, iArr, i2);
        if (cvCreateSparseMat != null) {
            cvCreateSparseMat.deallocator(new ReleaseDeallocator(cvCreateSparseMat));
        }
        return cvCreateSparseMat;
    }

    public CvSparseMat clone() {
        CvSparseMat cvCloneSparseMat = org.bytedeco.opencv.global.opencv_core.cvCloneSparseMat((CvSparseMat) this);
        if (cvCloneSparseMat != null) {
            cvCloneSparseMat.deallocator(new ReleaseDeallocator(cvCloneSparseMat));
        }
        return cvCloneSparseMat;
    }

    public void release() {
        deallocate();
    }

    protected static class ReleaseDeallocator extends CvSparseMat implements Pointer.Deallocator {
        ReleaseDeallocator(CvSparseMat cvSparseMat) {
            super((Pointer) cvSparseMat);
        }

        public void deallocate() {
            if (!isNull()) {
                org.bytedeco.opencv.global.opencv_core.cvReleaseSparseMat((CvSparseMat) this);
                setNull();
            }
        }
    }
}
