package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_cudafeatures2d", inherit = {opencv_features2d.class, opencv_cudafilters.class, opencv_cudawarping.class}, target = "org.bytedeco.opencv.opencv_cudafeatures2d", value = {@Platform(extension = {"-gpu"}, include = {"<opencv2/cudafeatures2d.hpp>"}, link = {"opencv_cudafeatures2d@.4.4"}), @Platform(extension = {"-gpu"}, link = {"opencv_cudafeatures2d440"}, value = {"windows"})})
public class opencv_cudafeatures2d implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::cuda::DescriptorMatcher::matchAsync").skipDefaults());
    }
}
