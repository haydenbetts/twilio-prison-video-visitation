package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvMemStorage extends Pointer {
    static {
        Loader.load();
    }

    public AbstractCvMemStorage(Pointer pointer) {
        super(pointer);
    }

    public static CvMemStorage create(int i) {
        CvMemStorage cvCreateMemStorage = org.bytedeco.opencv.global.opencv_core.cvCreateMemStorage(i);
        if (cvCreateMemStorage != null) {
            cvCreateMemStorage.deallocator(new ReleaseDeallocator(cvCreateMemStorage));
        }
        return cvCreateMemStorage;
    }

    public static CvMemStorage create() {
        return create(0);
    }

    public void release() {
        deallocate();
    }

    protected static class ReleaseDeallocator extends CvMemStorage implements Pointer.Deallocator {
        ReleaseDeallocator(CvMemStorage cvMemStorage) {
            super((Pointer) cvMemStorage);
        }

        public void deallocate() {
            org.bytedeco.opencv.global.opencv_core.cvReleaseMemStorage((CvMemStorage) this);
        }
    }
}
