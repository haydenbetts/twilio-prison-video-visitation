package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_dnn_superres", inherit = {opencv_dnn.class, opencv_quality.class}, target = "org.bytedeco.opencv.opencv_dnn_superres", value = {@Platform(include = {"<opencv2/dnn_superres.hpp>"}, link = {"opencv_dnn_superres@.4.4"}), @Platform(preload = {"libopencv_dnn_superres"}, value = {"ios"}), @Platform(link = {"opencv_dnn_superres440"}, value = {"windows"})})
public class opencv_dnn_superres implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
