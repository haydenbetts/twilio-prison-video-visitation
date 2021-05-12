package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_imgcodecs", helper = "org.bytedeco.opencv.helper.opencv_imgcodecs", inherit = {opencv_imgproc.class}, value = {@Platform(include = {"<opencv2/imgcodecs.hpp>"}, link = {"opencv_imgcodecs@.4.4"}), @Platform(preload = {"libopencv_imgcodecs"}, value = {"ios"}), @Platform(link = {"opencv_imgcodecs440"}, value = {"windows"})})
public class opencv_imgcodecs implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("cvvLoadImage").cppTypes("IplImage*", "const char*")).put(new Info("cvvSaveImage").cppTypes("int", "const char*", "CvArr*", "int*")).put(new Info("cvvConvertImage").cppTypes("void", "CvArr*", "CvArr*", "int"));
    }
}
