package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerSamplerAlgorithm extends Pointer {
    @opencv_core.Ptr
    @ByVal
    public static native TrackerSamplerAlgorithm create(@opencv_core.Str String str);

    @opencv_core.Ptr
    @ByVal
    public static native TrackerSamplerAlgorithm create(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Str
    public native BytePointer getClassName();

    @Cast({"bool"})
    public native boolean sampling(@ByRef @Const Mat mat, @ByVal Rect rect, @ByRef MatVector matVector);

    static {
        Loader.load();
    }

    public TrackerSamplerAlgorithm(Pointer pointer) {
        super(pointer);
    }
}
