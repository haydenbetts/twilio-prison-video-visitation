package org.bytedeco.opencv.opencv_img_hash;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_img_hash;

@Properties(inherit = {opencv_img_hash.class})
@Namespace("cv::img_hash")
@NoOffset
public class ImgHashBase extends Algorithm {
    public native double compare(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native double compare(@ByVal Mat mat, @ByVal Mat mat2);

    public native double compare(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public ImgHashBase(Pointer pointer) {
        super(pointer);
    }

    @Opaque
    public static class ImgHashImpl extends Pointer {
        public ImgHashImpl() {
            super((Pointer) null);
        }

        public ImgHashImpl(Pointer pointer) {
            super(pointer);
        }
    }
}
