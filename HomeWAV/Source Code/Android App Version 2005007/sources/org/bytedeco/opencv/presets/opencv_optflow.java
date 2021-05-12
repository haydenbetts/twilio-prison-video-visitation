package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_optflow", inherit = {opencv_ximgproc.class, opencv_video.class}, target = "org.bytedeco.opencv.opencv_optflow", value = {@Platform(include = {"<opencv2/optflow.hpp>", "<opencv2/optflow/motempl.hpp>"}, link = {"opencv_optflow@.4.4"}), @Platform(preload = {"libopencv_optflow"}, value = {"ios"}), @Platform(link = {"opencv_optflow440"}, value = {"windows"})})
public class opencv_optflow implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
