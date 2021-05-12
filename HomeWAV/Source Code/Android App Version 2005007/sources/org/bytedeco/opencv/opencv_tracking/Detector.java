package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class Detector extends Pointer {
    private native void allocate(StrongClassifierDirectSelection strongClassifierDirectSelection);

    public native void classifySmooth(@ByRef @Const MatVector matVector);

    public native void classifySmooth(@ByRef @Const MatVector matVector, float f);

    @ByRef
    @Const
    public native Mat getConfImageDisplay();

    public native float getConfidence(int i);

    public native float getConfidenceOfBestDetection();

    public native float getConfidenceOfDetection(int i);

    @StdVector
    public native FloatPointer getConfidences();

    @StdVector
    public native IntPointer getIdxDetections();

    public native int getNumDetections();

    public native int getPatchIdxOfBestDetection();

    public native int getPatchIdxOfDetection(int i);

    static {
        Loader.load();
    }

    public Detector(Pointer pointer) {
        super(pointer);
    }

    public Detector(StrongClassifierDirectSelection strongClassifierDirectSelection) {
        super((Pointer) null);
        allocate(strongClassifierDirectSelection);
    }
}
