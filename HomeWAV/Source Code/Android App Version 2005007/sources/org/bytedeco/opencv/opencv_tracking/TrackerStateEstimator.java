package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerStateEstimator extends Pointer {
    @opencv_core.Ptr
    public static native TrackerStateEstimator create(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native TrackerStateEstimator create(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    @ByVal
    public native TrackerTargetState estimate(@ByRef @Const ConfidenceMapVector confidenceMapVector);

    @opencv_core.Str
    public native BytePointer getClassName();

    public native void update(@ByRef ConfidenceMapVector confidenceMapVector);

    static {
        Loader.load();
    }

    public TrackerStateEstimator(Pointer pointer) {
        super(pointer);
    }
}
