package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_superres", inherit = {opencv_objdetect.class, opencv_optflow.class}, target = "org.bytedeco.opencv.opencv_superres", value = {@Platform(include = {"<opencv2/superres.hpp>", "<opencv2/superres/optical_flow.hpp>"}, link = {"opencv_superres@.4.4"}, not = {"ios"}, preload = {"opencv_cuda@.4.4", "opencv_cudacodec@.4.4", "opencv_cudaarithm@.4.4", "opencv_cudafilters@.4.4", "opencv_cudaimgproc@.4.4", "opencv_cudafeatures2d@.4.4", "opencv_cudalegacy@.4.4", "opencv_cudaoptflow@.4.4", "opencv_cudawarping@.4.4"}), @Platform(link = {"opencv_superres440"}, preload = {"opencv_cuda440", "opencv_cudacodec440", "opencv_cudaarithm440", "opencv_cudafilters440", "opencv_cudaimgproc440", "opencv_cudafeatures2d440", "opencv_cudalegacy440", "opencv_cudaoptflow440", "opencv_cudawarping440"}, value = {"windows"})})
public class opencv_superres implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("override").annotations(new String[0])).put(new Info("cv::superres::FarnebackOpticalFlow").pointerTypes("SuperResFarnebackOpticalFlow")).put(new Info("cv::superres::DualTVL1OpticalFlow").pointerTypes("SuperResDualTVL1OpticalFlow"));
    }
}
