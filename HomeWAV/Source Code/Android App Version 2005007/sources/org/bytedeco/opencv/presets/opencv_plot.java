package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_plot", inherit = {opencv_imgproc.class}, target = "org.bytedeco.opencv.opencv_plot", value = {@Platform(include = {"opencv2/plot.hpp"}, link = {"opencv_plot@.4.4"}), @Platform(preload = {"libopencv_plot"}, value = {"ios"}), @Platform(link = {"opencv_plot440"}, value = {"windows"})})
public class opencv_plot implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
