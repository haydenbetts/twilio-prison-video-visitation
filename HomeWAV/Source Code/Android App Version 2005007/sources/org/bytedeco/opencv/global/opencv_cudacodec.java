package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_cudacodec.EncoderCallBack;
import org.bytedeco.opencv.opencv_cudacodec.EncoderParams;
import org.bytedeco.opencv.opencv_cudacodec.RawVideoSource;
import org.bytedeco.opencv.opencv_cudacodec.VideoReader;
import org.bytedeco.opencv.opencv_cudacodec.VideoWriter;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_cudacodec extends org.bytedeco.opencv.presets.opencv_cudacodec {
    public static final int H264 = 4;
    public static final int H264_MVC = 7;
    public static final int H264_SVC = 6;
    public static final int HEVC = 8;
    public static final int JPEG = 5;
    public static final int MPEG1 = 0;
    public static final int MPEG2 = 1;
    public static final int MPEG4 = 2;
    public static final int Monochrome = 0;
    public static final int NumCodecs = 11;
    public static final int NumFormats = 4;
    public static final int SF_BGR = 5;
    public static final int SF_GRAY = 5;
    public static final int SF_IYUV = 4;
    public static final int SF_NV12 = 3;
    public static final int SF_UYVY = 0;
    public static final int SF_YUY2 = 1;
    public static final int SF_YV12 = 2;
    public static final int Uncompressed_NV12 = 1314271538;
    public static final int Uncompressed_UYVY = 1431918169;
    public static final int Uncompressed_YUV420 = 1230591318;
    public static final int Uncompressed_YUYV = 1498765654;
    public static final int Uncompressed_YV12 = 1498820914;
    public static final int VC1 = 3;
    public static final int VP8 = 9;
    public static final int VP9 = 10;
    public static final int YUV420 = 1;
    public static final int YUV422 = 2;
    public static final int YUV444 = 3;

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoReader createVideoReader(@opencv_core.Str String str);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoReader createVideoReader(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoReader createVideoReader(@opencv_core.Ptr RawVideoSource rawVideoSource);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Str String str, @ByVal Size size, double d);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Str String str, @ByVal Size size, double d, @Cast({"cv::cudacodec::SurfaceFormat"}) int i);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Str String str, @ByVal Size size, double d, @ByRef @Const EncoderParams encoderParams);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Str String str, @ByVal Size size, double d, @ByRef @Const EncoderParams encoderParams, @Cast({"cv::cudacodec::SurfaceFormat"}) int i);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Str BytePointer bytePointer, @ByVal Size size, double d);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Str BytePointer bytePointer, @ByVal Size size, double d, @Cast({"cv::cudacodec::SurfaceFormat"}) int i);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Str BytePointer bytePointer, @ByVal Size size, double d, @ByRef @Const EncoderParams encoderParams);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Str BytePointer bytePointer, @ByVal Size size, double d, @ByRef @Const EncoderParams encoderParams, @Cast({"cv::cudacodec::SurfaceFormat"}) int i);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Ptr EncoderCallBack encoderCallBack, @ByVal Size size, double d);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Ptr EncoderCallBack encoderCallBack, @ByVal Size size, double d, @Cast({"cv::cudacodec::SurfaceFormat"}) int i);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Ptr EncoderCallBack encoderCallBack, @ByVal Size size, double d, @ByRef @Const EncoderParams encoderParams);

    @Namespace("cv::cudacodec")
    @opencv_core.Ptr
    public static native VideoWriter createVideoWriter(@opencv_core.Ptr EncoderCallBack encoderCallBack, @ByVal Size size, double d, @ByRef @Const EncoderParams encoderParams, @Cast({"cv::cudacodec::SurfaceFormat"}) int i);

    static {
        Loader.load();
    }
}
