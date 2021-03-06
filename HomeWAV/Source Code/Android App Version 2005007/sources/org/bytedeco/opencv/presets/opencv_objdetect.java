package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_objdetect", inherit = {opencv_calib3d.class}, target = "org.bytedeco.opencv.opencv_objdetect", value = {@Platform(include = {"<opencv2/objdetect.hpp>", "<opencv2/objdetect/detection_based_tracker.hpp>"}, link = {"opencv_objdetect@.4.4"}), @Platform(preload = {"libopencv_objdetect"}, value = {"ios"}), @Platform(link = {"opencv_objdetect440"}, value = {"windows"})})
public class opencv_objdetect implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::DefaultDeleter<CvHaarClassifierCascade>").skip()).put(new Info("cv::DefaultDeleter<CvVideoWriter>").pointerTypes("CvVideoWriterDefaultDeleter"));
    }
}
