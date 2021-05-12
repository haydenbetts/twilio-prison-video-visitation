package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class ImageFeatures extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @ByRef
    public native UMat descriptors();

    public native ImageFeatures descriptors(UMat uMat);

    @ByVal
    public native KeyPointVector getKeypoints();

    public native int img_idx();

    public native ImageFeatures img_idx(int i);

    @ByRef
    public native Size img_size();

    public native ImageFeatures img_size(Size size);

    @ByRef
    public native KeyPointVector keypoints();

    public native ImageFeatures keypoints(KeyPointVector keyPointVector);

    static {
        Loader.load();
    }

    public ImageFeatures() {
        super((Pointer) null);
        allocate();
    }

    public ImageFeatures(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ImageFeatures(Pointer pointer) {
        super(pointer);
    }

    public ImageFeatures position(long j) {
        return (ImageFeatures) super.position(j);
    }

    public ImageFeatures getPointer(long j) {
        return new ImageFeatures((Pointer) this).position(this.position + j);
    }
}
