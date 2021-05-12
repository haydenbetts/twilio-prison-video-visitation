package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_phase_unwrapping", inherit = {opencv_imgproc.class}, target = "org.bytedeco.opencv.opencv_phase_unwrapping", value = {@Platform(include = {"<opencv2/phase_unwrapping.hpp>", "<opencv2/phase_unwrapping/phase_unwrapping.hpp>", "<opencv2/phase_unwrapping/histogramphaseunwrapping.hpp>"}, link = {"opencv_phase_unwrapping@.4.4"}), @Platform(preload = {"libopencv_phase_unwrapping"}, value = {"ios"}), @Platform(link = {"opencv_phase_unwrapping440"}, value = {"windows"})})
public class opencv_phase_unwrapping implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
