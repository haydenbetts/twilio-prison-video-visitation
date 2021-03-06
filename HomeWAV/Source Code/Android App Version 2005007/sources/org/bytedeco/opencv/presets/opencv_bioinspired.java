package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_bioinspired", inherit = {opencv_highgui.class}, target = "org.bytedeco.opencv.opencv_bioinspired", value = {@Platform(include = {"<opencv2/bioinspired.hpp>", "opencv2/bioinspired/bioinspired.hpp", "opencv2/bioinspired/retina.hpp", "opencv2/bioinspired/retinafasttonemapping.hpp", "opencv2/bioinspired/transientareassegmentationmodule.hpp"}, link = {"opencv_bioinspired@.4.4"}), @Platform(preload = {"libopencv_bioinspired"}, value = {"ios"}), @Platform(link = {"opencv_bioinspired440"}, value = {"windows"})})
public class opencv_bioinspired implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cv::bioinspired::createRetina_OCL").skip());
    }
}
