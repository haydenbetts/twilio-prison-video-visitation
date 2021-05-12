package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_structured_light", inherit = {opencv_phase_unwrapping.class, opencv_calib3d.class}, target = "org.bytedeco.opencv.opencv_structured_light", value = {@Platform(include = {"<opencv2/structured_light.hpp>", "<opencv2/structured_light/structured_light.hpp>", "<opencv2/structured_light/graycodepattern.hpp>", "<opencv2/structured_light/sinusoidalpattern.hpp>"}, link = {"opencv_structured_light@.4.4"}), @Platform(preload = {"libopencv_structured_light"}, value = {"ios"}), @Platform(link = {"opencv_structured_light440"}, value = {"windows"})})
public class opencv_structured_light implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
