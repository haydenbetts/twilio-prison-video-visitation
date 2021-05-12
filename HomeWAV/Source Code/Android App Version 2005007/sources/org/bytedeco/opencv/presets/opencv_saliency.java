package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_saliency", inherit = {opencv_features2d.class}, target = "org.bytedeco.opencv.opencv_saliency", value = {@Platform(include = {"<opencv2/saliency.hpp>", "<opencv2/saliency/saliencyBaseClasses.hpp>", "<opencv2/saliency/saliencySpecializedClasses.hpp>"}, link = {"opencv_saliency@.4.4"}), @Platform(preload = {"libopencv_saliency"}, value = {"ios"}), @Platform(link = {"opencv_saliency440"}, value = {"windows"})})
public class opencv_saliency implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
