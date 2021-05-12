package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_xfeatures2d", inherit = {opencv_ml.class, opencv_shape.class}, target = "org.bytedeco.opencv.opencv_xfeatures2d", value = {@Platform(include = {"<opencv2/xfeatures2d.hpp>", "<opencv2/xfeatures2d/nonfree.hpp>"}, link = {"opencv_xfeatures2d@.4.4"}, preload = {"opencv_cuda@.4.4", "opencv_cudaarithm@.4.4"}), @Platform(preload = {"libopencv_xfeatures2d"}, value = {"ios"}), @Platform(link = {"opencv_xfeatures2d440"}, preload = {"opencv_cuda440", "opencv_cudaarithm440"}, value = {"windows"})})
public class opencv_xfeatures2d implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::Matx23f").cast().pointerTypes("FloatPointer"));
    }
}
