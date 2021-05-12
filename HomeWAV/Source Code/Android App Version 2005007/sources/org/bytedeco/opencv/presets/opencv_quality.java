package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_quality", inherit = {opencv_imgproc.class, opencv_ml.class}, target = "org.bytedeco.opencv.opencv_quality", value = {@Platform(include = {"<opencv2/quality.hpp>", "<opencv2/quality/qualitybase.hpp>", "<opencv2/quality/quality_utils.hpp>", "<opencv2/quality/qualitymse.hpp>", "<opencv2/quality/qualitypsnr.hpp>", "<opencv2/quality/qualityssim.hpp>", "<opencv2/quality/qualitygmsd.hpp>", "<opencv2/quality/qualitybrisque.hpp>"}, link = {"opencv_quality@.4.4"}), @Platform(preload = {"libopencv_quality"}, value = {"ios"}), @Platform(link = {"opencv_quality440"}, value = {"windows"})})
public class opencv_quality implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
