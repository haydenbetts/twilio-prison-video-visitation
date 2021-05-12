package org.bytedeco.opencv.presets;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(global = "org.bytedeco.opencv.global.opencv_videostab", inherit = {opencv_objdetect.class, opencv_optflow.class, opencv_photo.class}, target = "org.bytedeco.opencv.opencv_videostab", value = {@Platform(include = {"<opencv2/videostab/frame_source.hpp>", "<opencv2/videostab/log.hpp>", "<opencv2/videostab/fast_marching.hpp>", "<opencv2/videostab/optical_flow.hpp>", "<opencv2/videostab/motion_core.hpp>", "<opencv2/videostab/outlier_rejection.hpp>", "<opencv2/videostab/global_motion.hpp>", "<opencv2/videostab/motion_stabilizing.hpp>", "<opencv2/videostab/inpainting.hpp>", "<opencv2/videostab/deblurring.hpp>", "<opencv2/videostab/wobble_suppression.hpp>", "<opencv2/videostab/stabilizer.hpp>", "<opencv2/videostab/ring_buffer.hpp>", "<opencv2/videostab.hpp>"}, link = {"opencv_videostab@.4.4"}, preload = {"opencv_cuda@.4.4", "opencv_cudaarithm@.4.4", "opencv_cudafilters@.4.4", "opencv_cudaimgproc@.4.4", "opencv_cudafeatures2d@.4.4", "opencv_cudalegacy@.4.4", "opencv_cudaoptflow@.4.4", "opencv_cudawarping@.4.4"}), @Platform(preload = {"libopencv_videostab"}, value = {"ios"}), @Platform(link = {"opencv_videostab440"}, preload = {"opencv_cuda440", "opencv_cudaarithm440", "opencv_cudafilters440", "opencv_cudaimgproc440", "opencv_cudafeatures2d440", "opencv_cudalegacy440", "opencv_cudaoptflow440", "opencv_cudawarping440"}, value = {"windows"})})
public class opencv_videostab implements InfoMapper {
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("override").annotations(new String[0])).put(new Info("std::function<void(Mat&)>").pointerTypes("MaskCallback")).put(new Info("cv::videostab::IFrameSource").virtualize());
    }

    public static class MaskCallback extends FunctionPointer {
        private native void allocate();

        public native void call(@ByRef @Cast({"cv::Mat*"}) Pointer pointer);

        static {
            Loader.load();
        }

        public MaskCallback(Pointer pointer) {
            super(pointer);
        }

        protected MaskCallback() {
            allocate();
        }
    }
}
