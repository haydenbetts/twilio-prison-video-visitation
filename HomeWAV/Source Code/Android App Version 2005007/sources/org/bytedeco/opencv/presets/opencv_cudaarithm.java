package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_cudaarithm", inherit = {opencv_core.class}, target = "org.bytedeco.opencv.opencv_cudaarithm", value = {@Platform(extension = {"-gpu"}, include = {"<opencv2/cudaarithm.hpp>"}, link = {"opencv_cudaarithm@.4.4"}), @Platform(extension = {"-gpu"}, link = {"opencv_cudaarithm440"}, value = {"windows"})})
public class opencv_cudaarithm implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
