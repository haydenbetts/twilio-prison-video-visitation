package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_intensity_transform", inherit = {opencv_core.class}, value = {@Platform(include = {"<opencv2/intensity_transform.hpp>"}, link = {"opencv_intensity_transform@.4.4"}), @Platform(preload = {"libopencv_intensity_transform"}, value = {"ios"}), @Platform(link = {"opencv_intensity_transform440"}, value = {"windows"})})
public class opencv_intensity_transform implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
