package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_cudaobjdetect", inherit = {opencv_calib3d.class, opencv_objdetect.class, opencv_video.class, opencv_videoio.class, opencv_cudaimgproc.class, opencv_cudawarping.class}, target = "org.bytedeco.opencv.opencv_cudaobjdetect", value = {@Platform(extension = {"-gpu"}, include = {"<opencv2/cudaobjdetect.hpp>"}, link = {"opencv_cudaobjdetect@.4.4"}, preload = {"opencv_cudalegacy@.4.4"}), @Platform(extension = {"-gpu"}, link = {"opencv_cudaobjdetect440"}, preload = {"opencv_cudalegacy440"}, value = {"windows"})})
public class opencv_cudaobjdetect implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("std::vector<double>").annotations("@Cast({\"double*\", \"std::vector<double>*\"}) @StdVector(\"double\")").pointerTypes("DoublePointer", "DoubleBuffer", "double[]")).put(new Info("cv::cuda::CascadeClassifier").pointerTypes("CudaCascadeClassifier"));
    }
}
