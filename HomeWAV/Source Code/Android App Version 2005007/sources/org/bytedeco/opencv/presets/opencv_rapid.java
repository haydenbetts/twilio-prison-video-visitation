package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_rapid", inherit = {opencv_calib3d.class}, value = {@Platform(include = {"<opencv2/rapid.hpp>"}, link = {"opencv_rapid@.4.4"}), @Platform(preload = {"libopencv_rapid"}, value = {"ios"}), @Platform(link = {"opencv_rapid440"}, value = {"windows"})})
public class opencv_rapid implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::rapid::Rapid", "cv::rapid::OLSTracker").purify());
    }
}
