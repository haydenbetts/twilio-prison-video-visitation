package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_cudawarping", inherit = {opencv_imgproc.class}, value = {@Platform(extension = {"-gpu"}, include = {"<opencv2/cudawarping.hpp>"}, link = {"opencv_cudawarping@.4.4"}), @Platform(extension = {"-gpu"}, link = {"opencv_cudawarping440"}, value = {"windows"})})
public class opencv_cudawarping implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::cuda::warpAffine").javaText("@Namespace(\"cv::cuda\") public static native void warpAffine(@ByVal GpuMat src, @ByVal GpuMat dst, @ByVal Mat M, @ByVal Size dsize, int flags/*=cv::INTER_LINEAR*/,\n    int borderMode/*=cv::BORDER_CONSTANT*/, @ByVal(nullValue = \"cv::Scalar()\") Scalar borderValue, @ByRef(nullValue = \"cv::cuda::Stream::Null()\") Stream stream);\n@Namespace(\"cv::cuda\") public static native void warpAffine(@ByVal GpuMat src, @ByVal GpuMat dst, @ByVal Mat M, @ByVal Size dsize);\n")).put(new Info("cv::cuda::warpPerspective").javaText("@Namespace(\"cv::cuda\") public static native void warpPerspective(@ByVal GpuMat src, @ByVal GpuMat dst, @ByVal Mat M, @ByVal Size dsize, int flags/*=cv::INTER_LINEAR*/,\n    int borderMode/*=cv::BORDER_CONSTANT*/, @ByVal(nullValue = \"cv::Scalar()\") Scalar borderValue, @ByRef(nullValue = \"cv::cuda::Stream::Null()\") Stream stream);\n@Namespace(\"cv::cuda\") public static native void warpPerspective(@ByVal GpuMat src, @ByVal GpuMat dst, @ByVal Mat M, @ByVal Size dsize);\n"));
    }
}
