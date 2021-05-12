package org.bytedeco.opencv.opencv_videoio;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videoio;

@Properties(inherit = {opencv_videoio.class})
@Name({"cv::DefaultDeleter<CvCapture>"})
public class CvCaptureDefaultDeleter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"operator ()"})
    public native void apply(CvCapture cvCapture);

    static {
        Loader.load();
    }

    public CvCaptureDefaultDeleter() {
        super((Pointer) null);
        allocate();
    }

    public CvCaptureDefaultDeleter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvCaptureDefaultDeleter(Pointer pointer) {
        super(pointer);
    }

    public CvCaptureDefaultDeleter position(long j) {
        return (CvCaptureDefaultDeleter) super.position(j);
    }

    public CvCaptureDefaultDeleter getPointer(long j) {
        return new CvCaptureDefaultDeleter((Pointer) this).position(this.position + j);
    }
}
