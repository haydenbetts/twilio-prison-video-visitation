package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_photo", inherit = {opencv_imgproc.class}, target = "org.bytedeco.opencv.opencv_photo", value = {@Platform(include = {"<opencv2/photo.hpp>", "<opencv2/photo/cuda.hpp>"}, link = {"opencv_photo@.4.4"}, preload = {"opencv_cuda@.4.4", "opencv_cudaarithm@.4.4", "opencv_cudafilters@.4.4", "opencv_cudaimgproc@.4.4"}), @Platform(preload = {"libopencv_photo"}, value = {"ios"}), @Platform(link = {"opencv_photo440"}, preload = {"opencv_cuda440", "opencv_cudaarithm440", "opencv_cudafilters440", "opencv_cudaimgproc440"}, value = {"windows"})})
public class opencv_photo implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
