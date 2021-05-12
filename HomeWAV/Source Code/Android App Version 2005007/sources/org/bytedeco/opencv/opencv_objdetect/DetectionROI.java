package org.bytedeco.opencv.opencv_objdetect;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.presets.opencv_objdetect;

@Namespace("cv")
@Properties(inherit = {opencv_objdetect.class})
public class DetectionROI extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @StdVector
    public native DoublePointer confidences();

    public native DetectionROI confidences(DoublePointer doublePointer);

    @ByRef
    public native PointVector locations();

    public native DetectionROI locations(PointVector pointVector);

    public native double scale();

    public native DetectionROI scale(double d);

    static {
        Loader.load();
    }

    public DetectionROI() {
        super((Pointer) null);
        allocate();
    }

    public DetectionROI(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public DetectionROI(Pointer pointer) {
        super(pointer);
    }

    public DetectionROI position(long j) {
        return (DetectionROI) super.position(j);
    }

    public DetectionROI getPointer(long j) {
        return new DetectionROI((Pointer) this).position(this.position + j);
    }
}
