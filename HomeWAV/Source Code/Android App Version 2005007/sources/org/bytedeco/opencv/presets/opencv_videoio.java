package org.bytedeco.opencv.presets;

import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_videoio", inherit = {opencv_imgcodecs.class}, target = "org.bytedeco.opencv.opencv_videoio", value = {@Platform(include = {"<opencv2/videoio.hpp>"}, link = {"opencv_videoio@.4.4"}), @Platform(preload = {"native_camera_r2.2.0", "native_camera_r2.3.4", "native_camera_r3.0.1", "native_camera_r4.0.0", "native_camera_r4.0.3", "native_camera_r4.1.1", "native_camera_r4.2.0", "native_camera_r4.3.0", "native_camera_r4.4.0"}, value = {"android"}), @Platform(preload = {"libopencv_videoio"}, value = {"ios"}), @Platform(link = {"opencv_videoio440"}, preload = {"opencv_ffmpeg440", "opencv_ffmpeg440_64"}, value = {"windows"})})
public class opencv_videoio implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("CV_FOURCC_DEFAULT").cppTypes("int")).put(new Info("cvCaptureFromFile", "cvCaptureFromAVI").cppTypes("CvCapture*", "const char*")).put(new Info("cvCaptureFromCAM").cppTypes("CvCapture*", "int")).put(new Info("cvCreateAVIWriter").cppTypes("CvVideoWriter*", "const char*", "int", DoubleTypedProperty.TYPE, "CvSize", "int")).put(new Info("cvWriteToAVI").cppTypes("int", "CvVideoWriter*", "IplImage*")).put(new Info("std::vector<int>").annotations("@StdVector").valueTypes("@Cast({\"int*\", \"std::vector<int>&\"}) IntPointer", "@Cast({\"int*\", \"std::vector<int>&\"}) IntBuffer", "@Cast({\"int*\", \"std::vector<int>&\"}) int[]").pointerTypes("IntPointer", "IntBuffer", "int[]")).put(new Info("cv::DefaultDeleter<CvCapture>").pointerTypes("CvCaptureDefaultDeleter")).put(new Info("cv::DefaultDeleter<CvVideoWriter>").pointerTypes("CvVideoWriterDefaultDeleter"));
    }
}
