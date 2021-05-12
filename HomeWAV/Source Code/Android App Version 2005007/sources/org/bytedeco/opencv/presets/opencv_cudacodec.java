package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_cudacodec", inherit = {opencv_videoio.class}, target = "org.bytedeco.opencv.opencv_cudacodec", value = {@Platform(extension = {"-gpu"}, include = {"<opencv2/cudacodec.hpp>"}, link = {"opencv_cudacodec@.4.4"}, not = {"macosx"}), @Platform(extension = {"-gpu"}, link = {"opencv_cudacodec440"}, value = {"windows"})})
public class opencv_cudacodec implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::cudacodec::VideoWriter").pointerTypes("org.bytedeco.opencv.opencv_cudacodec.VideoWriter")).put(new Info("cv::cudacodec::VideoReader").pointerTypes("org.bytedeco.opencv.opencv_cudacodec.VideoReader"));
    }
}
