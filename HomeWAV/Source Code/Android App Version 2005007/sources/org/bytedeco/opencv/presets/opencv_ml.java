package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_ml", inherit = {opencv_core.class}, target = "org.bytedeco.opencv.opencv_ml", value = {@Platform(include = {"<opencv2/ml.hpp>"}, link = {"opencv_ml@.4.4"}), @Platform(preload = {"libopencv_ml"}, value = {"ios"}), @Platform(link = {"opencv_ml440"}, value = {"windows"})})
public class opencv_ml implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("CV_DOXYGEN").define(false)).put(new Info("cv::ml::StatModel").base("AbstractStatModel")).put(new Info("cv::ml::SVM::getDefaultGrid").javaNames("getDefaultGrid")).put(new Info("cv::ml::randGaussMixture").skip());
    }
}
