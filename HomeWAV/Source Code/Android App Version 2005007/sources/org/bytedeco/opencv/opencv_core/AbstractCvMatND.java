package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvMatND extends CvArr {
    public AbstractCvMatND(Pointer pointer) {
        super(pointer);
    }

    public static CvMatND create(int i, int[] iArr, int i2) {
        CvMatND cvCreateMatND = org.bytedeco.opencv.global.opencv_core.cvCreateMatND(i, iArr, i2);
        if (cvCreateMatND != null) {
            cvCreateMatND.deallocator(new ReleaseDeallocator(cvCreateMatND));
        }
        return cvCreateMatND;
    }

    public CvMatND clone() {
        CvMatND cvCloneMatND = org.bytedeco.opencv.global.opencv_core.cvCloneMatND((CvMatND) this);
        if (cvCloneMatND != null) {
            cvCloneMatND.deallocator(new ReleaseDeallocator(cvCloneMatND));
        }
        return cvCloneMatND;
    }

    public void release() {
        deallocate();
    }

    protected static class ReleaseDeallocator extends CvMatND implements Pointer.Deallocator {
        ReleaseDeallocator(CvMatND cvMatND) {
            super((Pointer) cvMatND);
        }

        public void deallocate() {
            if (!isNull()) {
                org.bytedeco.opencv.global.opencv_core.cvReleaseMatND((CvMatND) this);
                setNull();
            }
        }
    }
}
