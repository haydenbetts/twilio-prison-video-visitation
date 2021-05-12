package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_video", inherit = {opencv_calib3d.class}, target = "org.bytedeco.opencv.opencv_video", value = {@Platform(include = {"<opencv2/video.hpp>", "<opencv2/video/tracking.hpp>", "<opencv2/video/background_segm.hpp>"}, link = {"opencv_video@.4.4"}), @Platform(preload = {"libopencv_video"}, value = {"ios"}), @Platform(link = {"opencv_video440"}, value = {"windows"})})
public class opencv_video implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cvSegmentMotion", "cvCalcGlobalOrientation", "cvCalcMotionGradient", "cvUpdateMotionHistory", "cvCalcAffineFlowPyrLK").skip()).put(new Info("CvKalman").base("AbstractCvKalman")).put(new Info("cvKalmanUpdateByTime", "cvKalmanUpdateByMeasurement").cppTypes("const CvMat*", "CvKalman*", "CvMat*"));
    }
}
