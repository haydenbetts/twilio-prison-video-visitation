package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect2d;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class Tracker extends Algorithm {
    @Cast({"bool"})
    public native boolean init(@ByVal GpuMat gpuMat, @ByRef @Const Rect2d rect2d);

    @Cast({"bool"})
    public native boolean init(@ByVal Mat mat, @ByRef @Const Rect2d rect2d);

    @Cast({"bool"})
    public native boolean init(@ByVal UMat uMat, @ByRef @Const Rect2d rect2d);

    public native void read(@ByRef @Const FileNode fileNode);

    @Cast({"bool"})
    public native boolean update(@ByVal GpuMat gpuMat, @ByRef Rect2d rect2d);

    @Cast({"bool"})
    public native boolean update(@ByVal Mat mat, @ByRef Rect2d rect2d);

    @Cast({"bool"})
    public native boolean update(@ByVal UMat uMat, @ByRef Rect2d rect2d);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public Tracker(Pointer pointer) {
        super(pointer);
    }
}
