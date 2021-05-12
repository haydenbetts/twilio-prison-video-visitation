package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class CameraParams extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef @Const CameraParams cameraParams);

    private native void allocateArray(long j);

    @ByVal
    public native Mat K();

    @ByRef
    public native Mat R();

    public native CameraParams R(Mat mat);

    public native double aspect();

    public native CameraParams aspect(double d);

    public native double focal();

    public native CameraParams focal(double d);

    public native double ppx();

    public native CameraParams ppx(double d);

    public native double ppy();

    public native CameraParams ppy(double d);

    @ByRef
    @Name({"operator ="})
    public native CameraParams put(@ByRef @Const CameraParams cameraParams);

    @ByRef
    public native Mat t();

    public native CameraParams t(Mat mat);

    static {
        Loader.load();
    }

    public CameraParams(Pointer pointer) {
        super(pointer);
    }

    public CameraParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CameraParams position(long j) {
        return (CameraParams) super.position(j);
    }

    public CameraParams getPointer(long j) {
        return new CameraParams(this).position(this.position + j);
    }

    public CameraParams() {
        super((Pointer) null);
        allocate();
    }

    public CameraParams(@ByRef @Const CameraParams cameraParams) {
        super((Pointer) null);
        allocate(cameraParams);
    }
}
