package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_cudastereo", inherit = {opencv_calib3d.class}, target = "org.bytedeco.opencv.opencv_cudastereo", value = {@Platform(extension = {"-gpu"}, include = {"<opencv2/cudastereo.hpp>"}, link = {"opencv_cudastereo@.4.4"}), @Platform(extension = {"-gpu"}, link = {"opencv_cudastereo440"}, value = {"windows"})})
public class opencv_cudastereo implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::StereoBM").pointerTypes("org.bytedeco.opencv.opencv_calib3d.StereoBM")).put(new Info("cv::cuda::StereoBM").pointerTypes("org.bytedeco.opencv.opencv_cudastereo.StereoBM"));
    }
}
