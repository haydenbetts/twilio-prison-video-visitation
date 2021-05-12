package org.bytedeco.opencv.opencv_videoio;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videoio;

@Properties(inherit = {opencv_videoio.class})
@Name({"cv::DefaultDeleter<CvVideoWriter>"})
public class CvVideoWriterDefaultDeleter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"operator ()"})
    public native void apply(CvVideoWriter cvVideoWriter);

    static {
        Loader.load();
    }

    public CvVideoWriterDefaultDeleter() {
        super((Pointer) null);
        allocate();
    }

    public CvVideoWriterDefaultDeleter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvVideoWriterDefaultDeleter(Pointer pointer) {
        super(pointer);
    }

    public CvVideoWriterDefaultDeleter position(long j) {
        return (CvVideoWriterDefaultDeleter) super.position(j);
    }

    public CvVideoWriterDefaultDeleter getPointer(long j) {
        return new CvVideoWriterDefaultDeleter((Pointer) this).position(this.position + j);
    }
}
