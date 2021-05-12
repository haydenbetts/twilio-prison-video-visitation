package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.IplConvKernel;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public abstract class AbstractIplConvKernel extends Pointer {
    public AbstractIplConvKernel(Pointer pointer) {
        super(pointer);
    }

    public static IplConvKernel create(int i, int i2, int i3, int i4, int i5, int[] iArr) {
        IplConvKernel cvCreateStructuringElementEx = org.bytedeco.opencv.global.opencv_imgproc.cvCreateStructuringElementEx(i, i2, i3, i4, i5, iArr);
        if (cvCreateStructuringElementEx != null) {
            cvCreateStructuringElementEx.deallocator(new ReleaseDeallocator(cvCreateStructuringElementEx));
        }
        return cvCreateStructuringElementEx;
    }

    public void release() {
        deallocate();
    }

    static class ReleaseDeallocator extends IplConvKernel implements Pointer.Deallocator {
        ReleaseDeallocator(IplConvKernel iplConvKernel) {
            super((Pointer) iplConvKernel);
        }

        public void deallocate() {
            org.bytedeco.opencv.global.opencv_imgproc.cvReleaseStructuringElement((IplConvKernel) this);
        }
    }
}
