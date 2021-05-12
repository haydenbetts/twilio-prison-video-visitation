package org.bytedeco.opencv;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_aruco;
import org.bytedeco.opencv.presets.opencv_bgsegm;
import org.bytedeco.opencv.presets.opencv_bioinspired;
import org.bytedeco.opencv.presets.opencv_face;
import org.bytedeco.opencv.presets.opencv_img_hash;
import org.bytedeco.opencv.presets.opencv_structured_light;
import org.bytedeco.opencv.presets.opencv_text;
import org.bytedeco.opencv.presets.opencv_tracking;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;
import org.bytedeco.opencv.presets.opencv_ximgproc;
import org.bytedeco.opencv.presets.opencv_xphoto;

@Properties(inherit = {opencv_aruco.class, opencv_bgsegm.class, opencv_bioinspired.class, opencv_face.class, opencv_img_hash.class, opencv_structured_light.class, opencv_text.class, opencv_tracking.class, opencv_xfeatures2d.class, opencv_ximgproc.class, opencv_xphoto.class}, value = {@Platform(preload = {"opencv_cuda@.4.1", "opencv_cudaarithm@.4.1", "opencv_cudafilters@.4.1", "opencv_cudaimgproc@.4.1", "opencv_java"}), @Platform(preload = {"libopencv_java"}, value = {"ios"}), @Platform(preload = {"opencv_cuda412", "opencv_cudaarithm412", "opencv_cudafilters412", "opencv_cudaimgproc412", "opencv_java"}, value = {"windows"})})
public class opencv_java {
    static {
        Loader.load();
    }
}
