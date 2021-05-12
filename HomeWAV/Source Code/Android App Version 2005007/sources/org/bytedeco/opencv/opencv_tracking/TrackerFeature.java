package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerFeature extends Pointer {
    @opencv_core.Ptr
    @ByVal
    public static native TrackerFeature create(@opencv_core.Str String str);

    @opencv_core.Ptr
    @ByVal
    public static native TrackerFeature create(@opencv_core.Str BytePointer bytePointer);

    public native void compute(@ByRef @Const MatVector matVector, @ByRef Mat mat);

    @opencv_core.Str
    public native BytePointer getClassName();

    public native void selection(@ByRef Mat mat, int i);

    static {
        Loader.load();
    }

    public TrackerFeature(Pointer pointer) {
        super(pointer);
    }
}
