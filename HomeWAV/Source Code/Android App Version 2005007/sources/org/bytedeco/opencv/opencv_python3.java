package org.bytedeco.opencv;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.bytedeco.cpython.global.python;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.numpy.global.numpy;
import org.bytedeco.opencv.presets.opencv_aruco;
import org.bytedeco.opencv.presets.opencv_bgsegm;
import org.bytedeco.opencv.presets.opencv_bioinspired;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;
import org.bytedeco.opencv.presets.opencv_img_hash;
import org.bytedeco.opencv.presets.opencv_quality;
import org.bytedeco.opencv.presets.opencv_saliency;
import org.bytedeco.opencv.presets.opencv_stitching;
import org.bytedeco.opencv.presets.opencv_structured_light;
import org.bytedeco.opencv.presets.opencv_superres;
import org.bytedeco.opencv.presets.opencv_text;
import org.bytedeco.opencv.presets.opencv_tracking;
import org.bytedeco.opencv.presets.opencv_videostab;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;
import org.bytedeco.opencv.presets.opencv_ximgproc;
import org.bytedeco.opencv.presets.opencv_xphoto;

@Properties(inherit = {opencv_aruco.class, opencv_bgsegm.class, opencv_bioinspired.class, opencv_face.class, opencv_img_hash.class, opencv_structured_light.class, opencv_text.class, opencv_tracking.class, opencv_xfeatures2d.class, opencv_ximgproc.class, opencv_xphoto.class, opencv_videostab.class, opencv_superres.class, opencv_stitching.class, opencv_saliency.class, opencv_quality.class}, value = {@Platform(preload = {"opencv_cuda@.4.1", "opencv_cudaarithm@.4.1", "opencv_cudafilters@.4.1", "opencv_cudaimgproc@.4.1", "opencv_cudacodec@.4.1", "opencv_cudaobjdetect@.4.1", "opencv_cudabgsegm@4.1", "opencv_cudastereo@.4.1", "opencv_cudaoptflow@4.1", "opencv_cudawarping@.4.1", "opencv_cudalegacy@.4.1"}), @Platform(preload = {"opencv_cuda412", "opencv_cudaarithm412", "opencv_cudafilters412", "opencv_cudaimgproc412", "opencv_cudacodec412", "opencv_cudaobjdetect412", "opencv_cudabgsegm412", "opencv_cudastereo412", "opencv_cudaoptflow412", "opencv_cudawarping412", "opencv_cudalegacy412"}, value = {"windows"})})
public class opencv_python3 {
    static {
        Loader.load();
    }

    public static File cachePackage() throws IOException {
        Loader.load(python.class);
        String load = Loader.load(opencv_core.class);
        if (load == null) {
            return null;
        }
        String replace = load.replace(File.separatorChar, '/');
        int indexOf = replace.indexOf("/org/bytedeco/opencv/" + Loader.getPlatform());
        int lastIndexOf = replace.lastIndexOf("/");
        return Loader.cacheResource(replace.substring(indexOf, lastIndexOf) + "/python/");
    }

    public static File[] cachePackages() throws IOException {
        File[] cachePackages = numpy.cachePackages();
        File[] fileArr = (File[]) Arrays.copyOf(cachePackages, cachePackages.length + 1);
        fileArr[fileArr.length - 1] = cachePackage();
        return fileArr;
    }
}
