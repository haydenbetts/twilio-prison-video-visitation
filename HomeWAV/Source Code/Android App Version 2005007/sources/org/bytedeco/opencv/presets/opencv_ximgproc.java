package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_ximgproc", inherit = {opencv_video.class}, target = "org.bytedeco.opencv.opencv_ximgproc", value = {@Platform(include = {"<opencv2/ximgproc.hpp>", "opencv2/ximgproc/edge_filter.hpp", "opencv2/ximgproc/disparity_filter.hpp", "opencv2/ximgproc/sparse_match_interpolator.hpp", "opencv2/ximgproc/structured_edge_detection.hpp", "opencv2/ximgproc/seeds.hpp", "opencv2/ximgproc/segmentation.hpp", "opencv2/ximgproc/fast_hough_transform.hpp", "opencv2/ximgproc/estimated_covariance.hpp", "opencv2/ximgproc/slic.hpp", "opencv2/ximgproc/lsc.hpp"}, link = {"opencv_ximgproc@.4.4"}), @Platform(preload = {"libopencv_ximgproc"}, value = {"ios"}), @Platform(link = {"opencv_ximgproc440"}, value = {"windows"})})
public class opencv_ximgproc implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::ximgproc::segmentation::PointSet").skip()).put(new Info("cv::ximgproc::segmentation::SelectiveSearchSegmentationStrategyColor", "cv::ximgproc::segmentation::SelectiveSearchSegmentationStrategySize", "cv::ximgproc::segmentation::SelectiveSearchSegmentationStrategyTexture", "cv::ximgproc::segmentation::SelectiveSearchSegmentationStrategyFill").purify());
    }
}
