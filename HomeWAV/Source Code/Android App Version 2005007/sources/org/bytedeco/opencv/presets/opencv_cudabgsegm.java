package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_cudabgsegm", inherit = {opencv_video.class}, target = "org.bytedeco.opencv.opencv_cudabgsegm", value = {@Platform(extension = {"-gpu"}, include = {"<opencv2/cudabgsegm.hpp>"}, link = {"opencv_cudabgsegm@.4.4"}), @Platform(extension = {"-gpu"}, link = {"opencv_cudabgsegm440"}, value = {"windows"})})
public class opencv_cudabgsegm implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::BackgroundSubtractorMOG2").pointerTypes("org.bytedeco.opencv.opencv_video.BackgroundSubtractorMOG2")).put(new Info("cv::cuda::BackgroundSubtractorMOG2").pointerTypes("org.bytedeco.opencv.opencv_cudabgsegm.BackgroundSubtractorMOG2"));
    }
}
