package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_shape", inherit = {opencv_calib3d.class}, target = "org.bytedeco.opencv.opencv_shape", value = {@Platform(include = {"<opencv2/shape.hpp>", "<opencv2/shape/emdL1.hpp>", "<opencv2/shape/shape_transformer.hpp>", "<opencv2/shape/hist_cost.hpp>", "<opencv2/shape/shape_distance.hpp>"}, link = {"opencv_shape@.4.4"}), @Platform(preload = {"libopencv_shape"}, value = {"ios"}), @Platform(link = {"opencv_shape440"}, value = {"windows"})})
public class opencv_shape implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::ChiHistogramCostExtractor", "cv::EMDL1HistogramCostExtractor").purify());
    }
}
