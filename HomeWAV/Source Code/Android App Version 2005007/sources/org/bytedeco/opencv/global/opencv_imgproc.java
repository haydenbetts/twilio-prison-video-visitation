package org.bytedeco.opencv.global;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvBox2D;
import org.bytedeco.opencv.opencv_core.CvChain;
import org.bytedeco.opencv.opencv_core.CvContour;
import org.bytedeco.opencv.opencv_core.CvHistogram;
import org.bytedeco.opencv.opencv_core.CvLineIterator;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvMemStorage;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvPoint2D32f;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.CvSeq;
import org.bytedeco.opencv.opencv_core.CvSeqBlock;
import org.bytedeco.opencv.opencv_core.CvSize;
import org.bytedeco.opencv.opencv_core.CvSlice;
import org.bytedeco.opencv.opencv_core.CvTermCriteria;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.IplConvKernel;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Moments;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2d;
import org.bytedeco.opencv.opencv_core.Point2dVector;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RotatedRect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Size2d;
import org.bytedeco.opencv.opencv_core.SparseMat;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_imgproc.CLAHE;
import org.bytedeco.opencv.opencv_imgproc.CvChainPtReader;
import org.bytedeco.opencv.opencv_imgproc.CvConnectedComp;
import org.bytedeco.opencv.opencv_imgproc.CvContourScanner;
import org.bytedeco.opencv.opencv_imgproc.CvDistanceFunction;
import org.bytedeco.opencv.opencv_imgproc.CvFont;
import org.bytedeco.opencv.opencv_imgproc.CvHuMoments;
import org.bytedeco.opencv.opencv_imgproc.CvMoments;
import org.bytedeco.opencv.opencv_imgproc.GeneralizedHoughBallard;
import org.bytedeco.opencv.opencv_imgproc.GeneralizedHoughGuil;
import org.bytedeco.opencv.opencv_imgproc.LineSegmentDetector;
import org.bytedeco.opencv.opencv_imgproc.Vec2fVector;
import org.bytedeco.opencv.opencv_imgproc.Vec3fVector;
import org.bytedeco.opencv.opencv_imgproc.Vec4fVector;
import org.bytedeco.opencv.opencv_imgproc.Vec4iVector;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_imgproc extends org.bytedeco.opencv.helper.opencv_imgproc {
    public static final int ADAPTIVE_THRESH_GAUSSIAN_C = 1;
    public static final int ADAPTIVE_THRESH_MEAN_C = 0;
    public static final int CCL_DEFAULT = -1;
    public static final int CCL_GRANA = 1;
    public static final int CCL_WU = 0;
    public static final int CC_STAT_AREA = 4;
    public static final int CC_STAT_HEIGHT = 3;
    public static final int CC_STAT_LEFT = 0;
    public static final int CC_STAT_MAX = 5;
    public static final int CC_STAT_TOP = 1;
    public static final int CC_STAT_WIDTH = 2;
    public static final int CHAIN_APPROX_NONE = 1;
    public static final int CHAIN_APPROX_SIMPLE = 2;
    public static final int CHAIN_APPROX_TC89_KCOS = 4;
    public static final int CHAIN_APPROX_TC89_L1 = 3;
    public static final int COLORMAP_AUTUMN = 0;
    public static final int COLORMAP_BONE = 1;
    public static final int COLORMAP_CIVIDIS = 17;
    public static final int COLORMAP_COOL = 8;
    public static final int COLORMAP_DEEPGREEN = 21;
    public static final int COLORMAP_HOT = 11;
    public static final int COLORMAP_HSV = 9;
    public static final int COLORMAP_INFERNO = 14;
    public static final int COLORMAP_JET = 2;
    public static final int COLORMAP_MAGMA = 13;
    public static final int COLORMAP_OCEAN = 5;
    public static final int COLORMAP_PARULA = 12;
    public static final int COLORMAP_PINK = 10;
    public static final int COLORMAP_PLASMA = 15;
    public static final int COLORMAP_RAINBOW = 4;
    public static final int COLORMAP_SPRING = 7;
    public static final int COLORMAP_SUMMER = 6;
    public static final int COLORMAP_TURBO = 20;
    public static final int COLORMAP_TWILIGHT = 18;
    public static final int COLORMAP_TWILIGHT_SHIFTED = 19;
    public static final int COLORMAP_VIRIDIS = 16;
    public static final int COLORMAP_WINTER = 3;
    public static final int COLOR_BGR2BGR555 = 22;
    public static final int COLOR_BGR2BGR565 = 12;
    public static final int COLOR_BGR2BGRA = 0;
    public static final int COLOR_BGR2GRAY = 6;
    public static final int COLOR_BGR2HLS = 52;
    public static final int COLOR_BGR2HLS_FULL = 68;
    public static final int COLOR_BGR2HSV = 40;
    public static final int COLOR_BGR2HSV_FULL = 66;
    public static final int COLOR_BGR2Lab = 44;
    public static final int COLOR_BGR2Luv = 50;
    public static final int COLOR_BGR2RGB = 4;
    public static final int COLOR_BGR2RGBA = 2;
    public static final int COLOR_BGR2XYZ = 32;
    public static final int COLOR_BGR2YCrCb = 36;
    public static final int COLOR_BGR2YUV = 82;
    public static final int COLOR_BGR2YUV_I420 = 128;
    public static final int COLOR_BGR2YUV_IYUV = 128;
    public static final int COLOR_BGR2YUV_YV12 = 132;
    public static final int COLOR_BGR5552BGR = 24;
    public static final int COLOR_BGR5552BGRA = 28;
    public static final int COLOR_BGR5552GRAY = 31;
    public static final int COLOR_BGR5552RGB = 25;
    public static final int COLOR_BGR5552RGBA = 29;
    public static final int COLOR_BGR5652BGR = 14;
    public static final int COLOR_BGR5652BGRA = 18;
    public static final int COLOR_BGR5652GRAY = 21;
    public static final int COLOR_BGR5652RGB = 15;
    public static final int COLOR_BGR5652RGBA = 19;
    public static final int COLOR_BGRA2BGR = 1;
    public static final int COLOR_BGRA2BGR555 = 26;
    public static final int COLOR_BGRA2BGR565 = 16;
    public static final int COLOR_BGRA2GRAY = 10;
    public static final int COLOR_BGRA2RGB = 3;
    public static final int COLOR_BGRA2RGBA = 5;
    public static final int COLOR_BGRA2YUV_I420 = 130;
    public static final int COLOR_BGRA2YUV_IYUV = 130;
    public static final int COLOR_BGRA2YUV_YV12 = 134;
    public static final int COLOR_BayerBG2BGR = 46;
    public static final int COLOR_BayerBG2BGRA = 139;
    public static final int COLOR_BayerBG2BGR_EA = 135;
    public static final int COLOR_BayerBG2BGR_VNG = 62;
    public static final int COLOR_BayerBG2GRAY = 86;
    public static final int COLOR_BayerBG2RGB = 48;
    public static final int COLOR_BayerBG2RGBA = 141;
    public static final int COLOR_BayerBG2RGB_EA = 137;
    public static final int COLOR_BayerBG2RGB_VNG = 64;
    public static final int COLOR_BayerGB2BGR = 47;
    public static final int COLOR_BayerGB2BGRA = 140;
    public static final int COLOR_BayerGB2BGR_EA = 136;
    public static final int COLOR_BayerGB2BGR_VNG = 63;
    public static final int COLOR_BayerGB2GRAY = 87;
    public static final int COLOR_BayerGB2RGB = 49;
    public static final int COLOR_BayerGB2RGBA = 142;
    public static final int COLOR_BayerGB2RGB_EA = 138;
    public static final int COLOR_BayerGB2RGB_VNG = 65;
    public static final int COLOR_BayerGR2BGR = 49;
    public static final int COLOR_BayerGR2BGRA = 142;
    public static final int COLOR_BayerGR2BGR_EA = 138;
    public static final int COLOR_BayerGR2BGR_VNG = 65;
    public static final int COLOR_BayerGR2GRAY = 89;
    public static final int COLOR_BayerGR2RGB = 47;
    public static final int COLOR_BayerGR2RGBA = 140;
    public static final int COLOR_BayerGR2RGB_EA = 136;
    public static final int COLOR_BayerGR2RGB_VNG = 63;
    public static final int COLOR_BayerRG2BGR = 48;
    public static final int COLOR_BayerRG2BGRA = 141;
    public static final int COLOR_BayerRG2BGR_EA = 137;
    public static final int COLOR_BayerRG2BGR_VNG = 64;
    public static final int COLOR_BayerRG2GRAY = 88;
    public static final int COLOR_BayerRG2RGB = 46;
    public static final int COLOR_BayerRG2RGBA = 139;
    public static final int COLOR_BayerRG2RGB_EA = 135;
    public static final int COLOR_BayerRG2RGB_VNG = 62;
    public static final int COLOR_COLORCVT_MAX = 143;
    public static final int COLOR_GRAY2BGR = 8;
    public static final int COLOR_GRAY2BGR555 = 30;
    public static final int COLOR_GRAY2BGR565 = 20;
    public static final int COLOR_GRAY2BGRA = 9;
    public static final int COLOR_GRAY2RGB = 8;
    public static final int COLOR_GRAY2RGBA = 9;
    public static final int COLOR_HLS2BGR = 60;
    public static final int COLOR_HLS2BGR_FULL = 72;
    public static final int COLOR_HLS2RGB = 61;
    public static final int COLOR_HLS2RGB_FULL = 73;
    public static final int COLOR_HSV2BGR = 54;
    public static final int COLOR_HSV2BGR_FULL = 70;
    public static final int COLOR_HSV2RGB = 55;
    public static final int COLOR_HSV2RGB_FULL = 71;
    public static final int COLOR_LBGR2Lab = 74;
    public static final int COLOR_LBGR2Luv = 76;
    public static final int COLOR_LRGB2Lab = 75;
    public static final int COLOR_LRGB2Luv = 77;
    public static final int COLOR_Lab2BGR = 56;
    public static final int COLOR_Lab2LBGR = 78;
    public static final int COLOR_Lab2LRGB = 79;
    public static final int COLOR_Lab2RGB = 57;
    public static final int COLOR_Luv2BGR = 58;
    public static final int COLOR_Luv2LBGR = 80;
    public static final int COLOR_Luv2LRGB = 81;
    public static final int COLOR_Luv2RGB = 59;
    public static final int COLOR_RGB2BGR = 4;
    public static final int COLOR_RGB2BGR555 = 23;
    public static final int COLOR_RGB2BGR565 = 13;
    public static final int COLOR_RGB2BGRA = 2;
    public static final int COLOR_RGB2GRAY = 7;
    public static final int COLOR_RGB2HLS = 53;
    public static final int COLOR_RGB2HLS_FULL = 69;
    public static final int COLOR_RGB2HSV = 41;
    public static final int COLOR_RGB2HSV_FULL = 67;
    public static final int COLOR_RGB2Lab = 45;
    public static final int COLOR_RGB2Luv = 51;
    public static final int COLOR_RGB2RGBA = 0;
    public static final int COLOR_RGB2XYZ = 33;
    public static final int COLOR_RGB2YCrCb = 37;
    public static final int COLOR_RGB2YUV = 83;
    public static final int COLOR_RGB2YUV_I420 = 127;
    public static final int COLOR_RGB2YUV_IYUV = 127;
    public static final int COLOR_RGB2YUV_YV12 = 131;
    public static final int COLOR_RGBA2BGR = 3;
    public static final int COLOR_RGBA2BGR555 = 27;
    public static final int COLOR_RGBA2BGR565 = 17;
    public static final int COLOR_RGBA2BGRA = 5;
    public static final int COLOR_RGBA2GRAY = 11;
    public static final int COLOR_RGBA2RGB = 1;
    public static final int COLOR_RGBA2YUV_I420 = 129;
    public static final int COLOR_RGBA2YUV_IYUV = 129;
    public static final int COLOR_RGBA2YUV_YV12 = 133;
    public static final int COLOR_RGBA2mRGBA = 125;
    public static final int COLOR_XYZ2BGR = 34;
    public static final int COLOR_XYZ2RGB = 35;
    public static final int COLOR_YCrCb2BGR = 38;
    public static final int COLOR_YCrCb2RGB = 39;
    public static final int COLOR_YUV2BGR = 84;
    public static final int COLOR_YUV2BGRA_I420 = 105;
    public static final int COLOR_YUV2BGRA_IYUV = 105;
    public static final int COLOR_YUV2BGRA_NV12 = 95;
    public static final int COLOR_YUV2BGRA_NV21 = 97;
    public static final int COLOR_YUV2BGRA_UYNV = 112;
    public static final int COLOR_YUV2BGRA_UYVY = 112;
    public static final int COLOR_YUV2BGRA_Y422 = 112;
    public static final int COLOR_YUV2BGRA_YUNV = 120;
    public static final int COLOR_YUV2BGRA_YUY2 = 120;
    public static final int COLOR_YUV2BGRA_YUYV = 120;
    public static final int COLOR_YUV2BGRA_YV12 = 103;
    public static final int COLOR_YUV2BGRA_YVYU = 122;
    public static final int COLOR_YUV2BGR_I420 = 101;
    public static final int COLOR_YUV2BGR_IYUV = 101;
    public static final int COLOR_YUV2BGR_NV12 = 91;
    public static final int COLOR_YUV2BGR_NV21 = 93;
    public static final int COLOR_YUV2BGR_UYNV = 108;
    public static final int COLOR_YUV2BGR_UYVY = 108;
    public static final int COLOR_YUV2BGR_Y422 = 108;
    public static final int COLOR_YUV2BGR_YUNV = 116;
    public static final int COLOR_YUV2BGR_YUY2 = 116;
    public static final int COLOR_YUV2BGR_YUYV = 116;
    public static final int COLOR_YUV2BGR_YV12 = 99;
    public static final int COLOR_YUV2BGR_YVYU = 118;
    public static final int COLOR_YUV2GRAY_420 = 106;
    public static final int COLOR_YUV2GRAY_I420 = 106;
    public static final int COLOR_YUV2GRAY_IYUV = 106;
    public static final int COLOR_YUV2GRAY_NV12 = 106;
    public static final int COLOR_YUV2GRAY_NV21 = 106;
    public static final int COLOR_YUV2GRAY_UYNV = 123;
    public static final int COLOR_YUV2GRAY_UYVY = 123;
    public static final int COLOR_YUV2GRAY_Y422 = 123;
    public static final int COLOR_YUV2GRAY_YUNV = 124;
    public static final int COLOR_YUV2GRAY_YUY2 = 124;
    public static final int COLOR_YUV2GRAY_YUYV = 124;
    public static final int COLOR_YUV2GRAY_YV12 = 106;
    public static final int COLOR_YUV2GRAY_YVYU = 124;
    public static final int COLOR_YUV2RGB = 85;
    public static final int COLOR_YUV2RGBA_I420 = 104;
    public static final int COLOR_YUV2RGBA_IYUV = 104;
    public static final int COLOR_YUV2RGBA_NV12 = 94;
    public static final int COLOR_YUV2RGBA_NV21 = 96;
    public static final int COLOR_YUV2RGBA_UYNV = 111;
    public static final int COLOR_YUV2RGBA_UYVY = 111;
    public static final int COLOR_YUV2RGBA_Y422 = 111;
    public static final int COLOR_YUV2RGBA_YUNV = 119;
    public static final int COLOR_YUV2RGBA_YUY2 = 119;
    public static final int COLOR_YUV2RGBA_YUYV = 119;
    public static final int COLOR_YUV2RGBA_YV12 = 102;
    public static final int COLOR_YUV2RGBA_YVYU = 121;
    public static final int COLOR_YUV2RGB_I420 = 100;
    public static final int COLOR_YUV2RGB_IYUV = 100;
    public static final int COLOR_YUV2RGB_NV12 = 90;
    public static final int COLOR_YUV2RGB_NV21 = 92;
    public static final int COLOR_YUV2RGB_UYNV = 107;
    public static final int COLOR_YUV2RGB_UYVY = 107;
    public static final int COLOR_YUV2RGB_Y422 = 107;
    public static final int COLOR_YUV2RGB_YUNV = 115;
    public static final int COLOR_YUV2RGB_YUY2 = 115;
    public static final int COLOR_YUV2RGB_YUYV = 115;
    public static final int COLOR_YUV2RGB_YV12 = 98;
    public static final int COLOR_YUV2RGB_YVYU = 117;
    public static final int COLOR_YUV420p2BGR = 99;
    public static final int COLOR_YUV420p2BGRA = 103;
    public static final int COLOR_YUV420p2GRAY = 106;
    public static final int COLOR_YUV420p2RGB = 98;
    public static final int COLOR_YUV420p2RGBA = 102;
    public static final int COLOR_YUV420sp2BGR = 93;
    public static final int COLOR_YUV420sp2BGRA = 97;
    public static final int COLOR_YUV420sp2GRAY = 106;
    public static final int COLOR_YUV420sp2RGB = 92;
    public static final int COLOR_YUV420sp2RGBA = 96;
    public static final int COLOR_mRGBA2RGBA = 126;
    public static final int CONTOURS_MATCH_I1 = 1;
    public static final int CONTOURS_MATCH_I2 = 2;
    public static final int CONTOURS_MATCH_I3 = 3;
    public static final int CV_AA = 16;
    public static final int CV_ADAPTIVE_THRESH_GAUSSIAN_C = 1;
    public static final int CV_ADAPTIVE_THRESH_MEAN_C = 0;
    public static final int CV_BGR2BGR555 = 22;
    public static final int CV_BGR2BGR565 = 12;
    public static final int CV_BGR2BGRA = 0;
    public static final int CV_BGR2GRAY = 6;
    public static final int CV_BGR2HLS = 52;
    public static final int CV_BGR2HLS_FULL = 68;
    public static final int CV_BGR2HSV = 40;
    public static final int CV_BGR2HSV_FULL = 66;
    public static final int CV_BGR2Lab = 44;
    public static final int CV_BGR2Luv = 50;
    public static final int CV_BGR2RGB = 4;
    public static final int CV_BGR2RGBA = 2;
    public static final int CV_BGR2XYZ = 32;
    public static final int CV_BGR2YCrCb = 36;
    public static final int CV_BGR2YUV = 82;
    public static final int CV_BGR2YUV_I420 = 128;
    public static final int CV_BGR2YUV_IYUV = 128;
    public static final int CV_BGR2YUV_YV12 = 132;
    public static final int CV_BGR5552BGR = 24;
    public static final int CV_BGR5552BGRA = 28;
    public static final int CV_BGR5552GRAY = 31;
    public static final int CV_BGR5552RGB = 25;
    public static final int CV_BGR5552RGBA = 29;
    public static final int CV_BGR5652BGR = 14;
    public static final int CV_BGR5652BGRA = 18;
    public static final int CV_BGR5652GRAY = 21;
    public static final int CV_BGR5652RGB = 15;
    public static final int CV_BGR5652RGBA = 19;
    public static final int CV_BGRA2BGR = 1;
    public static final int CV_BGRA2BGR555 = 26;
    public static final int CV_BGRA2BGR565 = 16;
    public static final int CV_BGRA2GRAY = 10;
    public static final int CV_BGRA2RGB = 3;
    public static final int CV_BGRA2RGBA = 5;
    public static final int CV_BGRA2YUV_I420 = 130;
    public static final int CV_BGRA2YUV_IYUV = 130;
    public static final int CV_BGRA2YUV_YV12 = 134;
    public static final int CV_BILATERAL = 4;
    public static final int CV_BLUR = 1;
    public static final int CV_BLUR_NO_SCALE = 0;
    public static final int CV_BayerBG2BGR = 46;
    public static final int CV_BayerBG2BGRA = 139;
    public static final int CV_BayerBG2BGR_EA = 135;
    public static final int CV_BayerBG2BGR_VNG = 62;
    public static final int CV_BayerBG2GRAY = 86;
    public static final int CV_BayerBG2RGB = 48;
    public static final int CV_BayerBG2RGBA = 141;
    public static final int CV_BayerBG2RGB_EA = 137;
    public static final int CV_BayerBG2RGB_VNG = 64;
    public static final int CV_BayerGB2BGR = 47;
    public static final int CV_BayerGB2BGRA = 140;
    public static final int CV_BayerGB2BGR_EA = 136;
    public static final int CV_BayerGB2BGR_VNG = 63;
    public static final int CV_BayerGB2GRAY = 87;
    public static final int CV_BayerGB2RGB = 49;
    public static final int CV_BayerGB2RGBA = 142;
    public static final int CV_BayerGB2RGB_EA = 138;
    public static final int CV_BayerGB2RGB_VNG = 65;
    public static final int CV_BayerGR2BGR = 49;
    public static final int CV_BayerGR2BGRA = 142;
    public static final int CV_BayerGR2BGR_EA = 138;
    public static final int CV_BayerGR2BGR_VNG = 65;
    public static final int CV_BayerGR2GRAY = 89;
    public static final int CV_BayerGR2RGB = 47;
    public static final int CV_BayerGR2RGBA = 140;
    public static final int CV_BayerGR2RGB_EA = 136;
    public static final int CV_BayerGR2RGB_VNG = 63;
    public static final int CV_BayerRG2BGR = 48;
    public static final int CV_BayerRG2BGRA = 141;
    public static final int CV_BayerRG2BGR_EA = 137;
    public static final int CV_BayerRG2BGR_VNG = 64;
    public static final int CV_BayerRG2GRAY = 88;
    public static final int CV_BayerRG2RGB = 46;
    public static final int CV_BayerRG2RGBA = 139;
    public static final int CV_BayerRG2RGB_EA = 135;
    public static final int CV_BayerRG2RGB_VNG = 62;
    public static final int CV_CANNY_L2_GRADIENT = Integer.MIN_VALUE;
    public static final int CV_CHAIN_APPROX_NONE = 1;
    public static final int CV_CHAIN_APPROX_SIMPLE = 2;
    public static final int CV_CHAIN_APPROX_TC89_KCOS = 4;
    public static final int CV_CHAIN_APPROX_TC89_L1 = 3;
    public static final int CV_CHAIN_CODE = 0;
    public static final int CV_CLOCKWISE = 1;
    public static final int CV_COLORCVT_MAX = 143;
    public static final int CV_COMP_BHATTACHARYYA = 3;
    public static final int CV_COMP_CHISQR = 1;
    public static final int CV_COMP_CHISQR_ALT = 4;
    public static final int CV_COMP_CORREL = 0;
    public static final int CV_COMP_HELLINGER = 3;
    public static final int CV_COMP_INTERSECT = 2;
    public static final int CV_COMP_KL_DIV = 5;
    public static final int CV_CONTOURS_MATCH_I1 = 1;
    public static final int CV_CONTOURS_MATCH_I2 = 2;
    public static final int CV_CONTOURS_MATCH_I3 = 3;
    public static final int CV_COUNTER_CLOCKWISE = 2;
    public static final int CV_DIST_C = 3;
    public static final int CV_DIST_FAIR = 5;
    public static final int CV_DIST_HUBER = 7;
    public static final int CV_DIST_L1 = 1;
    public static final int CV_DIST_L12 = 4;
    public static final int CV_DIST_L2 = 2;
    public static final int CV_DIST_LABEL_CCOMP = 0;
    public static final int CV_DIST_LABEL_PIXEL = 1;
    public static final int CV_DIST_MASK_3 = 3;
    public static final int CV_DIST_MASK_5 = 5;
    public static final int CV_DIST_MASK_PRECISE = 0;
    public static final int CV_DIST_USER = -1;
    public static final int CV_DIST_WELSCH = 6;
    public static final int CV_FILLED = -1;
    public static final int CV_FLOODFILL_FIXED_RANGE = 65536;
    public static final int CV_FLOODFILL_MASK_ONLY = 131072;
    public static final int CV_FONT_HERSHEY_COMPLEX = 3;
    public static final int CV_FONT_HERSHEY_COMPLEX_SMALL = 5;
    public static final int CV_FONT_HERSHEY_DUPLEX = 2;
    public static final int CV_FONT_HERSHEY_PLAIN = 1;
    public static final int CV_FONT_HERSHEY_SCRIPT_COMPLEX = 7;
    public static final int CV_FONT_HERSHEY_SCRIPT_SIMPLEX = 6;
    public static final int CV_FONT_HERSHEY_SIMPLEX = 0;
    public static final int CV_FONT_HERSHEY_TRIPLEX = 4;
    public static final int CV_FONT_ITALIC = 16;
    public static final int CV_FONT_VECTOR0 = 0;
    public static final int CV_GAUSSIAN = 2;
    public static final int CV_GAUSSIAN_5x5 = 7;
    public static final int CV_GRAY2BGR = 8;
    public static final int CV_GRAY2BGR555 = 30;
    public static final int CV_GRAY2BGR565 = 20;
    public static final int CV_GRAY2BGRA = 9;
    public static final int CV_GRAY2RGB = 8;
    public static final int CV_GRAY2RGBA = 9;
    public static final int CV_HLS2BGR = 60;
    public static final int CV_HLS2BGR_FULL = 72;
    public static final int CV_HLS2RGB = 61;
    public static final int CV_HLS2RGB_FULL = 73;
    public static final int CV_HOUGH_GRADIENT = 3;
    public static final int CV_HOUGH_MULTI_SCALE = 2;
    public static final int CV_HOUGH_PROBABILISTIC = 1;
    public static final int CV_HOUGH_STANDARD = 0;
    public static final int CV_HSV2BGR = 54;
    public static final int CV_HSV2BGR_FULL = 70;
    public static final int CV_HSV2RGB = 55;
    public static final int CV_HSV2RGB_FULL = 71;
    public static final int CV_INTER_AREA = 3;
    public static final int CV_INTER_CUBIC = 2;
    public static final int CV_INTER_LANCZOS4 = 4;
    public static final int CV_INTER_LINEAR = 1;
    public static final int CV_INTER_NN = 0;
    public static final int CV_LBGR2Lab = 74;
    public static final int CV_LBGR2Luv = 76;
    public static final int CV_LINK_RUNS = 5;
    public static final int CV_LRGB2Lab = 75;
    public static final int CV_LRGB2Luv = 77;
    public static final int CV_Lab2BGR = 56;
    public static final int CV_Lab2LBGR = 78;
    public static final int CV_Lab2LRGB = 79;
    public static final int CV_Lab2RGB = 57;
    public static final int CV_Luv2BGR = 58;
    public static final int CV_Luv2LBGR = 80;
    public static final int CV_Luv2LRGB = 81;
    public static final int CV_Luv2RGB = 59;
    public static final int CV_MAX_SOBEL_KSIZE = 7;
    public static final int CV_MEDIAN = 3;
    public static final int CV_MOP_BLACKHAT = 6;
    public static final int CV_MOP_CLOSE = 3;
    public static final int CV_MOP_DILATE = 1;
    public static final int CV_MOP_ERODE = 0;
    public static final int CV_MOP_GRADIENT = 4;
    public static final int CV_MOP_OPEN = 2;
    public static final int CV_MOP_TOPHAT = 5;
    public static final int CV_POLY_APPROX_DP = 0;
    public static final int CV_RETR_CCOMP = 2;
    public static final int CV_RETR_EXTERNAL = 0;
    public static final int CV_RETR_FLOODFILL = 4;
    public static final int CV_RETR_LIST = 1;
    public static final int CV_RETR_TREE = 3;
    public static final int CV_RGB2BGR = 4;
    public static final int CV_RGB2BGR555 = 23;
    public static final int CV_RGB2BGR565 = 13;
    public static final int CV_RGB2BGRA = 2;
    public static final int CV_RGB2GRAY = 7;
    public static final int CV_RGB2HLS = 53;
    public static final int CV_RGB2HLS_FULL = 69;
    public static final int CV_RGB2HSV = 41;
    public static final int CV_RGB2HSV_FULL = 67;
    public static final int CV_RGB2Lab = 45;
    public static final int CV_RGB2Luv = 51;
    public static final int CV_RGB2RGBA = 0;
    public static final int CV_RGB2XYZ = 33;
    public static final int CV_RGB2YCrCb = 37;
    public static final int CV_RGB2YUV = 83;
    public static final int CV_RGB2YUV_I420 = 127;
    public static final int CV_RGB2YUV_IYUV = 127;
    public static final int CV_RGB2YUV_YV12 = 131;
    public static final int CV_RGBA2BGR = 3;
    public static final int CV_RGBA2BGR555 = 27;
    public static final int CV_RGBA2BGR565 = 17;
    public static final int CV_RGBA2BGRA = 5;
    public static final int CV_RGBA2GRAY = 11;
    public static final int CV_RGBA2RGB = 1;
    public static final int CV_RGBA2YUV_I420 = 129;
    public static final int CV_RGBA2YUV_IYUV = 129;
    public static final int CV_RGBA2YUV_YV12 = 133;
    public static final int CV_RGBA2mRGBA = 125;
    public static final int CV_SCHARR = -1;
    public static final int CV_SHAPE_CROSS = 1;
    public static final int CV_SHAPE_CUSTOM = 100;
    public static final int CV_SHAPE_ELLIPSE = 2;
    public static final int CV_SHAPE_RECT = 0;
    public static final int CV_THRESH_BINARY = 0;
    public static final int CV_THRESH_BINARY_INV = 1;
    public static final int CV_THRESH_MASK = 7;
    public static final int CV_THRESH_OTSU = 8;
    public static final int CV_THRESH_TOZERO = 3;
    public static final int CV_THRESH_TOZERO_INV = 4;
    public static final int CV_THRESH_TRIANGLE = 16;
    public static final int CV_THRESH_TRUNC = 2;
    public static final int CV_TM_CCOEFF = 4;
    public static final int CV_TM_CCOEFF_NORMED = 5;
    public static final int CV_TM_CCORR = 2;
    public static final int CV_TM_CCORR_NORMED = 3;
    public static final int CV_TM_SQDIFF = 0;
    public static final int CV_TM_SQDIFF_NORMED = 1;
    public static final int CV_WARP_FILL_OUTLIERS = 8;
    public static final int CV_WARP_INVERSE_MAP = 16;
    public static final int CV_XYZ2BGR = 34;
    public static final int CV_XYZ2RGB = 35;
    public static final int CV_YCrCb2BGR = 38;
    public static final int CV_YCrCb2RGB = 39;
    public static final int CV_YUV2BGR = 84;
    public static final int CV_YUV2BGRA_I420 = 105;
    public static final int CV_YUV2BGRA_IYUV = 105;
    public static final int CV_YUV2BGRA_NV12 = 95;
    public static final int CV_YUV2BGRA_NV21 = 97;
    public static final int CV_YUV2BGRA_UYNV = 112;
    public static final int CV_YUV2BGRA_UYVY = 112;
    public static final int CV_YUV2BGRA_Y422 = 112;
    public static final int CV_YUV2BGRA_YUNV = 120;
    public static final int CV_YUV2BGRA_YUY2 = 120;
    public static final int CV_YUV2BGRA_YUYV = 120;
    public static final int CV_YUV2BGRA_YV12 = 103;
    public static final int CV_YUV2BGRA_YVYU = 122;
    public static final int CV_YUV2BGR_I420 = 101;
    public static final int CV_YUV2BGR_IYUV = 101;
    public static final int CV_YUV2BGR_NV12 = 91;
    public static final int CV_YUV2BGR_NV21 = 93;
    public static final int CV_YUV2BGR_UYNV = 108;
    public static final int CV_YUV2BGR_UYVY = 108;
    public static final int CV_YUV2BGR_Y422 = 108;
    public static final int CV_YUV2BGR_YUNV = 116;
    public static final int CV_YUV2BGR_YUY2 = 116;
    public static final int CV_YUV2BGR_YUYV = 116;
    public static final int CV_YUV2BGR_YV12 = 99;
    public static final int CV_YUV2BGR_YVYU = 118;
    public static final int CV_YUV2GRAY_420 = 106;
    public static final int CV_YUV2GRAY_I420 = 106;
    public static final int CV_YUV2GRAY_IYUV = 106;
    public static final int CV_YUV2GRAY_NV12 = 106;
    public static final int CV_YUV2GRAY_NV21 = 106;
    public static final int CV_YUV2GRAY_UYNV = 123;
    public static final int CV_YUV2GRAY_UYVY = 123;
    public static final int CV_YUV2GRAY_Y422 = 123;
    public static final int CV_YUV2GRAY_YUNV = 124;
    public static final int CV_YUV2GRAY_YUY2 = 124;
    public static final int CV_YUV2GRAY_YUYV = 124;
    public static final int CV_YUV2GRAY_YV12 = 106;
    public static final int CV_YUV2GRAY_YVYU = 124;
    public static final int CV_YUV2RGB = 85;
    public static final int CV_YUV2RGBA_I420 = 104;
    public static final int CV_YUV2RGBA_IYUV = 104;
    public static final int CV_YUV2RGBA_NV12 = 94;
    public static final int CV_YUV2RGBA_NV21 = 96;
    public static final int CV_YUV2RGBA_UYNV = 111;
    public static final int CV_YUV2RGBA_UYVY = 111;
    public static final int CV_YUV2RGBA_Y422 = 111;
    public static final int CV_YUV2RGBA_YUNV = 119;
    public static final int CV_YUV2RGBA_YUY2 = 119;
    public static final int CV_YUV2RGBA_YUYV = 119;
    public static final int CV_YUV2RGBA_YV12 = 102;
    public static final int CV_YUV2RGBA_YVYU = 121;
    public static final int CV_YUV2RGB_I420 = 100;
    public static final int CV_YUV2RGB_IYUV = 100;
    public static final int CV_YUV2RGB_NV12 = 90;
    public static final int CV_YUV2RGB_NV21 = 92;
    public static final int CV_YUV2RGB_UYNV = 107;
    public static final int CV_YUV2RGB_UYVY = 107;
    public static final int CV_YUV2RGB_Y422 = 107;
    public static final int CV_YUV2RGB_YUNV = 115;
    public static final int CV_YUV2RGB_YUY2 = 115;
    public static final int CV_YUV2RGB_YUYV = 115;
    public static final int CV_YUV2RGB_YV12 = 98;
    public static final int CV_YUV2RGB_YVYU = 117;
    public static final int CV_YUV420p2BGR = 99;
    public static final int CV_YUV420p2BGRA = 103;
    public static final int CV_YUV420p2GRAY = 106;
    public static final int CV_YUV420p2RGB = 98;
    public static final int CV_YUV420p2RGBA = 102;
    public static final int CV_YUV420sp2BGR = 93;
    public static final int CV_YUV420sp2BGRA = 97;
    public static final int CV_YUV420sp2GRAY = 106;
    public static final int CV_YUV420sp2RGB = 92;
    public static final int CV_YUV420sp2RGBA = 96;
    public static final int CV_mRGBA2RGBA = 126;
    public static final int DIST_C = 3;
    public static final int DIST_FAIR = 5;
    public static final int DIST_HUBER = 7;
    public static final int DIST_L1 = 1;
    public static final int DIST_L12 = 4;
    public static final int DIST_L2 = 2;
    public static final int DIST_LABEL_CCOMP = 0;
    public static final int DIST_LABEL_PIXEL = 1;
    public static final int DIST_MASK_3 = 3;
    public static final int DIST_MASK_5 = 5;
    public static final int DIST_MASK_PRECISE = 0;
    public static final int DIST_USER = -1;
    public static final int DIST_WELSCH = 6;
    public static final int FILLED = -1;
    public static final int FILTER_SCHARR = -1;
    public static final int FLOODFILL_FIXED_RANGE = 65536;
    public static final int FLOODFILL_MASK_ONLY = 131072;
    public static final int FONT_HERSHEY_COMPLEX = 3;
    public static final int FONT_HERSHEY_COMPLEX_SMALL = 5;
    public static final int FONT_HERSHEY_DUPLEX = 2;
    public static final int FONT_HERSHEY_PLAIN = 1;
    public static final int FONT_HERSHEY_SCRIPT_COMPLEX = 7;
    public static final int FONT_HERSHEY_SCRIPT_SIMPLEX = 6;
    public static final int FONT_HERSHEY_SIMPLEX = 0;
    public static final int FONT_HERSHEY_TRIPLEX = 4;
    public static final int FONT_ITALIC = 16;
    public static final int GC_BGD = 0;
    public static final int GC_EVAL = 2;
    public static final int GC_EVAL_FREEZE_MODEL = 3;
    public static final int GC_FGD = 1;
    public static final int GC_INIT_WITH_MASK = 1;
    public static final int GC_INIT_WITH_RECT = 0;
    public static final int GC_PR_BGD = 2;
    public static final int GC_PR_FGD = 3;
    public static final int HISTCMP_BHATTACHARYYA = 3;
    public static final int HISTCMP_CHISQR = 1;
    public static final int HISTCMP_CHISQR_ALT = 4;
    public static final int HISTCMP_CORREL = 0;
    public static final int HISTCMP_HELLINGER = 3;
    public static final int HISTCMP_INTERSECT = 2;
    public static final int HISTCMP_KL_DIV = 5;
    public static final int HOUGH_GRADIENT = 3;
    public static final int HOUGH_GRADIENT_ALT = 4;
    public static final int HOUGH_MULTI_SCALE = 2;
    public static final int HOUGH_PROBABILISTIC = 1;
    public static final int HOUGH_STANDARD = 0;
    public static final int INTERSECT_FULL = 2;
    public static final int INTERSECT_NONE = 0;
    public static final int INTERSECT_PARTIAL = 1;
    public static final int INTER_AREA = 3;
    public static final int INTER_BITS = 5;
    public static final int INTER_BITS2 = 10;
    public static final int INTER_CUBIC = 2;
    public static final int INTER_LANCZOS4 = 4;
    public static final int INTER_LINEAR = 1;
    public static final int INTER_LINEAR_EXACT = 5;
    public static final int INTER_MAX = 7;
    public static final int INTER_NEAREST = 0;
    public static final int INTER_TAB_SIZE = 32;
    public static final int INTER_TAB_SIZE2 = 1024;
    public static final int LINE_4 = 4;
    public static final int LINE_8 = 8;
    public static final int LINE_AA = 16;
    public static final int LSD_REFINE_ADV = 2;
    public static final int LSD_REFINE_NONE = 0;
    public static final int LSD_REFINE_STD = 1;
    public static final int MARKER_CROSS = 0;
    public static final int MARKER_DIAMOND = 3;
    public static final int MARKER_SQUARE = 4;
    public static final int MARKER_STAR = 2;
    public static final int MARKER_TILTED_CROSS = 1;
    public static final int MARKER_TRIANGLE_DOWN = 6;
    public static final int MARKER_TRIANGLE_UP = 5;
    public static final int MORPH_BLACKHAT = 6;
    public static final int MORPH_CLOSE = 3;
    public static final int MORPH_CROSS = 1;
    public static final int MORPH_DILATE = 1;
    public static final int MORPH_ELLIPSE = 2;
    public static final int MORPH_ERODE = 0;
    public static final int MORPH_GRADIENT = 4;
    public static final int MORPH_HITMISS = 7;
    public static final int MORPH_OPEN = 2;
    public static final int MORPH_RECT = 0;
    public static final int MORPH_TOPHAT = 5;
    public static final int RETR_CCOMP = 2;
    public static final int RETR_EXTERNAL = 0;
    public static final int RETR_FLOODFILL = 4;
    public static final int RETR_LIST = 1;
    public static final int RETR_TREE = 3;
    public static final int THRESH_BINARY = 0;
    public static final int THRESH_BINARY_INV = 1;
    public static final int THRESH_MASK = 7;
    public static final int THRESH_OTSU = 8;
    public static final int THRESH_TOZERO = 3;
    public static final int THRESH_TOZERO_INV = 4;
    public static final int THRESH_TRIANGLE = 16;
    public static final int THRESH_TRUNC = 2;
    public static final int TM_CCOEFF = 4;
    public static final int TM_CCOEFF_NORMED = 5;
    public static final int TM_CCORR = 2;
    public static final int TM_CCORR_NORMED = 3;
    public static final int TM_SQDIFF = 0;
    public static final int TM_SQDIFF_NORMED = 1;
    public static final int WARP_FILL_OUTLIERS = 8;
    public static final int WARP_INVERSE_MAP = 16;
    public static final int WARP_POLAR_LINEAR = 0;
    public static final int WARP_POLAR_LOG = 256;

    @Namespace("cv")
    public static native void Canny(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2);

    @Namespace("cv")
    public static native void Canny(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void Canny(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2);

    @Namespace("cv")
    public static native void Canny(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, double d2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void Canny(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2);

    @Namespace("cv")
    public static native void Canny(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void Canny(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2);

    @Namespace("cv")
    public static native void Canny(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, double d2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void Canny(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2);

    @Namespace("cv")
    public static native void Canny(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void Canny(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2);

    @Namespace("cv")
    public static native void Canny(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, double d2, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native float EMD(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native float EMD(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, FloatBuffer floatBuffer, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native float EMD(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, FloatPointer floatPointer, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native float EMD(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, float[] fArr, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native float EMD(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native float EMD(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, FloatBuffer floatBuffer, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native float EMD(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, FloatPointer floatPointer, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native float EMD(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, float[] fArr, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native float EMD(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native float EMD(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, FloatBuffer floatBuffer, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native float EMD(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, FloatPointer floatPointer, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native float EMD(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, float[] fArr, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native void GaussianBlur(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, double d);

    @Namespace("cv")
    public static native void GaussianBlur(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, double d, double d2, int i);

    @Namespace("cv")
    public static native void GaussianBlur(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, double d);

    @Namespace("cv")
    public static native void GaussianBlur(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, double d, double d2, int i);

    @Namespace("cv")
    public static native void GaussianBlur(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, double d);

    @Namespace("cv")
    public static native void GaussianBlur(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, double d, double d2, int i);

    @Namespace("cv")
    public static native void HoughCircles(@ByVal Mat mat, @ByVal Vec3fVector vec3fVector, int i, double d, double d2, double d3, double d4, int i2, int i3);

    @Namespace("cv")
    public static native void HoughCircles(@ByVal Mat mat, @ByVal Vec4fVector vec4fVector, int i, double d, double d2, double d3, double d4, int i2, int i3);

    @Namespace("cv")
    public static native void HoughLines(@ByVal Mat mat, @ByVal Vec2fVector vec2fVector, double d, double d2, int i, double d3, double d4, double d5, double d6);

    @Namespace("cv")
    public static native void HoughLines(@ByVal Mat mat, @ByVal Vec3fVector vec3fVector, double d, double d2, int i, double d3, double d4, double d5, double d6);

    @Namespace("cv")
    public static native void HoughLinesP(@ByVal Mat mat, @ByVal Vec4iVector vec4iVector, double d, double d2, int i, double d3, double d4);

    @Namespace("cv")
    public static native void HoughLinesPointSet(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, double d, double d2, double d3, double d4, double d5, double d6);

    @Namespace("cv")
    public static native void HoughLinesPointSet(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, double d, double d2, double d3, double d4, double d5, double d6);

    @Namespace("cv")
    public static native void HoughLinesPointSet(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, double d, double d2, double d3, double d4, double d5, double d6);

    @Namespace("cv")
    public static native void HuMoments(@ByRef @Const Moments moments, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    public static native void HuMoments(@ByRef @Const Moments moments, DoublePointer doublePointer);

    @Namespace("cv")
    public static native void HuMoments(@ByRef @Const Moments moments, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void HuMoments(@ByRef @Const Moments moments, @ByVal Mat mat);

    @Namespace("cv")
    public static native void HuMoments(@ByRef @Const Moments moments, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void HuMoments(@ByRef @Const Moments moments, double[] dArr);

    @Namespace("cv")
    public static native void Laplacian(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void Laplacian(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, double d, double d2, int i3);

    @Namespace("cv")
    public static native void Laplacian(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void Laplacian(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, double d, double d2, int i3);

    @Namespace("cv")
    public static native void Laplacian(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void Laplacian(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, double d, double d2, int i3);

    @Namespace("cv")
    public static native void Scharr(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void Scharr(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, double d, double d2, int i4);

    @Namespace("cv")
    public static native void Scharr(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void Scharr(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, double d, double d2, int i4);

    @Namespace("cv")
    public static native void Scharr(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void Scharr(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, double d, double d2, int i4);

    @Namespace("cv")
    public static native void Sobel(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void Sobel(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, int i4, double d, double d2, int i5);

    @Namespace("cv")
    public static native void Sobel(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void Sobel(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, int i4, double d, double d2, int i5);

    @Namespace("cv")
    public static native void Sobel(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void Sobel(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, int i4, double d, double d2, int i5);

    @Namespace("cv")
    public static native void accumulate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void accumulate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native void accumulate(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void accumulate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native void accumulate(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void accumulate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    public static native void accumulateProduct(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void accumulateProduct(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void accumulateProduct(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void accumulateProduct(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void accumulateProduct(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void accumulateProduct(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native void accumulateSquare(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void accumulateSquare(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native void accumulateSquare(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void accumulateSquare(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native void accumulateSquare(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void accumulateSquare(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    public static native void accumulateWeighted(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d);

    @Namespace("cv")
    public static native void accumulateWeighted(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv")
    public static native void accumulateWeighted(@ByVal Mat mat, @ByVal Mat mat2, double d);

    @Namespace("cv")
    public static native void accumulateWeighted(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    @Namespace("cv")
    public static native void accumulateWeighted(@ByVal UMat uMat, @ByVal UMat uMat2, double d);

    @Namespace("cv")
    public static native void accumulateWeighted(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv")
    public static native void adaptiveThreshold(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, int i, int i2, int i3, double d2);

    @Namespace("cv")
    public static native void adaptiveThreshold(@ByVal Mat mat, @ByVal Mat mat2, double d, int i, int i2, int i3, double d2);

    @Namespace("cv")
    public static native void adaptiveThreshold(@ByVal UMat uMat, @ByVal UMat uMat2, double d, int i, int i2, int i3, double d2);

    @Namespace("cv")
    public static native void applyColorMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void applyColorMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void applyColorMap(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void applyColorMap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void applyColorMap(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void applyColorMap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void approxPolyDP(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void approxPolyDP(@ByVal Mat mat, @ByVal Mat mat2, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void approxPolyDP(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native double arcLength(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native double arcLength(@ByVal Mat mat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native double arcLength(@ByVal UMat uMat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void arrowedLine(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void arrowedLine(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3, double d);

    @Namespace("cv")
    public static native void arrowedLine(@ByVal Mat mat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void arrowedLine(@ByVal Mat mat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3, double d);

    @Namespace("cv")
    public static native void arrowedLine(@ByVal UMat uMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void arrowedLine(@ByVal UMat uMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3, double d);

    @Namespace("cv")
    public static native void bilateralFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2);

    @Namespace("cv")
    public static native void bilateralFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, int i2);

    @Namespace("cv")
    public static native void bilateralFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2);

    @Namespace("cv")
    public static native void bilateralFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, int i2);

    @Namespace("cv")
    public static native void bilateralFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2);

    @Namespace("cv")
    public static native void bilateralFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, int i2);

    @Namespace("cv")
    public static native void blendLinear(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @Namespace("cv")
    public static native void blendLinear(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @Namespace("cv")
    public static native void blendLinear(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @Namespace("cv")
    public static native void blur(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size);

    @Namespace("cv")
    public static native void blur(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i);

    @Namespace("cv")
    public static native void blur(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size);

    @Namespace("cv")
    public static native void blur(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i);

    @Namespace("cv")
    public static native void blur(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size);

    @Namespace("cv")
    public static native void blur(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i);

    @Namespace("cv")
    @ByVal
    public static native Rect boundingRect(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native Rect boundingRect(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native Rect boundingRect(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void boxFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal Size size);

    @Namespace("cv")
    public static native void boxFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, @Cast({"bool"}) boolean z, int i2);

    @Namespace("cv")
    public static native void boxFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Size size);

    @Namespace("cv")
    public static native void boxFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, @Cast({"bool"}) boolean z, int i2);

    @Namespace("cv")
    public static native void boxFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal Size size);

    @Namespace("cv")
    public static native void boxFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, @Cast({"bool"}) boolean z, int i2);

    @Namespace("cv")
    public static native void boxPoints(@ByVal RotatedRect rotatedRect, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native void boxPoints(@ByVal RotatedRect rotatedRect, @ByVal Mat mat);

    @Namespace("cv")
    public static native void boxPoints(@ByVal RotatedRect rotatedRect, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, int i, int i2);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, int i, int i2);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, int i, int i2);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, int i, int i2);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal Mat mat, @ByVal MatVector matVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal Mat mat, @ByVal MatVector matVector, int i, int i2);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal Mat mat, @ByVal UMatVector uMatVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal Mat mat, @ByVal UMatVector uMatVector, int i, int i2);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, int i, int i2);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal UMat uMat, @ByVal MatVector matVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal UMat uMat, @ByVal MatVector matVector, int i, int i2);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal UMat uMat, @ByVal UMatVector uMatVector, int i);

    @Namespace("cv")
    public static native void buildPyramid(@ByVal UMat uMat, @ByVal UMatVector uMatVector, int i, int i2);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector float[] fArr, double d);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal Mat mat, @ByVal Mat mat2, @StdVector float[] fArr, double d);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector float[] fArr, double d);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByPtrPtr @Const FloatBuffer floatBuffer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal Mat mat2, @ByVal Mat mat3, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal Mat mat2, @ByVal Mat mat3, @ByPtrPtr @Const FloatBuffer floatBuffer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByRef @Const SparseMat sparseMat, @ByVal GpuMat gpuMat, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByRef @Const SparseMat sparseMat, @ByVal GpuMat gpuMat, @ByPtrPtr @Const FloatBuffer floatBuffer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByRef @Const SparseMat sparseMat, @ByVal Mat mat2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByRef @Const SparseMat sparseMat, @ByVal Mat mat2, @ByPtrPtr @Const FloatBuffer floatBuffer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByRef @Const SparseMat sparseMat, @ByVal UMat uMat, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByRef @Const SparseMat sparseMat, @ByVal UMat uMat, @ByPtrPtr @Const FloatBuffer floatBuffer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal UMat uMat, @ByVal UMat uMat2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal UMat uMat, @ByVal UMat uMat2, @ByPtrPtr @Const FloatBuffer floatBuffer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByPtrPtr @Const FloatPointer floatPointer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByVal Mat mat3, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByVal Mat mat3, @ByPtrPtr @Const FloatPointer floatPointer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByVal Mat mat3, @Cast({"const float**"}) PointerPointer pointerPointer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByRef @Const SparseMat sparseMat, @ByVal GpuMat gpuMat, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByRef @Const SparseMat sparseMat, @ByVal GpuMat gpuMat, @ByPtrPtr @Const FloatPointer floatPointer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByRef @Const SparseMat sparseMat, @ByVal Mat mat2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByRef @Const SparseMat sparseMat, @ByVal Mat mat2, @ByPtrPtr @Const FloatPointer floatPointer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByRef @Const SparseMat sparseMat, @ByVal Mat mat2, @Cast({"const float**"}) PointerPointer pointerPointer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByRef @Const SparseMat sparseMat, @ByVal UMat uMat, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByRef @Const SparseMat sparseMat, @ByVal UMat uMat, @ByPtrPtr @Const FloatPointer floatPointer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal UMat uMat, @ByVal UMat uMat2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal UMat uMat, @ByVal UMat uMat2, @ByPtrPtr @Const FloatPointer floatPointer, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByPtrPtr @Const float[] fArr, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByVal Mat mat2, @ByVal Mat mat3, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByVal Mat mat2, @ByVal Mat mat3, @ByPtrPtr @Const float[] fArr, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByRef @Const SparseMat sparseMat, @ByVal GpuMat gpuMat, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByRef @Const SparseMat sparseMat, @ByVal GpuMat gpuMat, @ByPtrPtr @Const float[] fArr, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByRef @Const SparseMat sparseMat, @ByVal Mat mat2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByRef @Const SparseMat sparseMat, @ByVal Mat mat2, @ByPtrPtr @Const float[] fArr, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByRef @Const SparseMat sparseMat, @ByVal UMat uMat, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByRef @Const SparseMat sparseMat, @ByVal UMat uMat, @ByPtrPtr @Const float[] fArr, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByVal UMat uMat, @ByVal UMat uMat2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcBackProject(@Const Mat mat, int i, @Const int[] iArr, @ByVal UMat uMat, @ByVal UMat uMat2, @ByPtrPtr @Const float[] fArr, double d, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector FloatPointer floatPointer, double d);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal Mat mat, @ByVal Mat mat2, @StdVector FloatPointer floatPointer, double d);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector FloatPointer floatPointer, double d);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector FloatBuffer floatBuffer, double d);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal Mat mat, @ByVal Mat mat2, @StdVector FloatBuffer floatBuffer, double d);

    @Namespace("cv")
    public static native void calcBackProject(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector FloatBuffer floatBuffer, double d);

    @Namespace("cv")
    public static native void calcHist(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector int[] iArr2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector int[] iArr2, @StdVector float[] fArr, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcHist(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal Mat mat, @ByVal Mat mat2, @StdVector int[] iArr2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal Mat mat, @ByVal Mat mat2, @StdVector int[] iArr2, @StdVector float[] fArr, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcHist(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector int[] iArr2, @StdVector float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@ByVal GpuMatVector gpuMatVector, @StdVector int[] iArr, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector int[] iArr2, @StdVector float[] fArr, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByRef SparseMat sparseMat, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByRef SparseMat sparseMat, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal Mat mat2, @ByVal Mat mat3, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal Mat mat2, @ByVal Mat mat3, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal Mat mat2, @ByRef SparseMat sparseMat, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal Mat mat2, @ByRef SparseMat sparseMat, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal UMat uMat, @ByRef SparseMat sparseMat, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal UMat uMat, @ByRef SparseMat sparseMat, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal UMat uMat, @ByVal UMat uMat2, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntBuffer intBuffer, @ByVal UMat uMat, @ByVal UMat uMat2, int i2, @Const IntBuffer intBuffer2, @ByPtrPtr @Const FloatBuffer floatBuffer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal GpuMat gpuMat, @ByRef SparseMat sparseMat, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal GpuMat gpuMat, @ByRef SparseMat sparseMat, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByVal Mat mat3, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByVal Mat mat3, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByVal Mat mat3, int i2, @Const IntPointer intPointer2, @Cast({"const float**"}) PointerPointer pointerPointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByRef SparseMat sparseMat, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByRef SparseMat sparseMat, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal Mat mat2, @ByRef SparseMat sparseMat, int i2, @Const IntPointer intPointer2, @Cast({"const float**"}) PointerPointer pointerPointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal UMat uMat, @ByRef SparseMat sparseMat, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal UMat uMat, @ByRef SparseMat sparseMat, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal UMat uMat, @ByVal UMat uMat2, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const IntPointer intPointer, @ByVal UMat uMat, @ByVal UMat uMat2, int i2, @Const IntPointer intPointer2, @ByPtrPtr @Const FloatPointer floatPointer, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal GpuMat gpuMat, @ByRef SparseMat sparseMat, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal GpuMat gpuMat, @ByRef SparseMat sparseMat, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal Mat mat2, @ByVal Mat mat3, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal Mat mat2, @ByVal Mat mat3, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal Mat mat2, @ByRef SparseMat sparseMat, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal Mat mat2, @ByRef SparseMat sparseMat, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal UMat uMat, @ByRef SparseMat sparseMat, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal UMat uMat, @ByRef SparseMat sparseMat, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal UMat uMat, @ByVal UMat uMat2, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr);

    @Namespace("cv")
    public static native void calcHist(@Const Mat mat, int i, @Const int[] iArr, @ByVal UMat uMat, @ByVal UMat uMat2, int i2, @Const int[] iArr2, @ByPtrPtr @Const float[] fArr, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void calcHist(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector IntPointer intPointer2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector IntPointer intPointer2, @StdVector FloatPointer floatPointer, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcHist(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal Mat mat, @ByVal Mat mat2, @StdVector IntPointer intPointer2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal Mat mat, @ByVal Mat mat2, @StdVector IntPointer intPointer2, @StdVector FloatPointer floatPointer, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcHist(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector IntPointer intPointer2, @StdVector FloatPointer floatPointer);

    @Namespace("cv")
    public static native void calcHist(@ByVal MatVector matVector, @StdVector IntPointer intPointer, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector IntPointer intPointer2, @StdVector FloatPointer floatPointer, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcHist(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector IntBuffer intBuffer2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector IntBuffer intBuffer2, @StdVector FloatBuffer floatBuffer, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcHist(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal Mat mat, @ByVal Mat mat2, @StdVector IntBuffer intBuffer2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal Mat mat, @ByVal Mat mat2, @StdVector IntBuffer intBuffer2, @StdVector FloatBuffer floatBuffer, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void calcHist(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector IntBuffer intBuffer2, @StdVector FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void calcHist(@ByVal UMatVector uMatVector, @StdVector IntBuffer intBuffer, @ByVal UMat uMat, @ByVal UMat uMat2, @StdVector IntBuffer intBuffer2, @StdVector FloatBuffer floatBuffer, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void circle(@ByVal GpuMat gpuMat, @ByVal Point point, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void circle(@ByVal GpuMat gpuMat, @ByVal Point point, int i, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void circle(@ByVal Mat mat, @ByVal Point point, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void circle(@ByVal Mat mat, @ByVal Point point, int i, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void circle(@ByVal UMat uMat, @ByVal Point point, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void circle(@ByVal UMat uMat, @ByVal Point point, int i, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean clipLine(@ByVal Rect rect, @ByRef Point point, @ByRef Point point2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean clipLine(@ByVal Size size, @ByRef Point point, @ByRef Point point2);

    @Namespace("cv")
    public static native double compareHist(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native double compareHist(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native double compareHist(@ByRef @Const SparseMat sparseMat, @ByRef @Const SparseMat sparseMat2, int i);

    @Namespace("cv")
    public static native double compareHist(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native int connectedComponents(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native int connectedComponents(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native int connectedComponents(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native int connectedComponents(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native int connectedComponents(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native int connectedComponents(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    @Name({"connectedComponents"})
    public static native int connectedComponentsWithAlgorithm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    @Name({"connectedComponents"})
    public static native int connectedComponentsWithAlgorithm(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    @Name({"connectedComponents"})
    public static native int connectedComponentsWithAlgorithm(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native int connectedComponentsWithStats(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native int connectedComponentsWithStats(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i, int i2);

    @Namespace("cv")
    public static native int connectedComponentsWithStats(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native int connectedComponentsWithStats(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i, int i2);

    @Namespace("cv")
    public static native int connectedComponentsWithStats(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native int connectedComponentsWithStats(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i, int i2);

    @Namespace("cv")
    @Name({"connectedComponentsWithStats"})
    public static native int connectedComponentsWithStatsWithAlgorithm(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i, int i2, int i3);

    @Namespace("cv")
    @Name({"connectedComponentsWithStats"})
    public static native int connectedComponentsWithStatsWithAlgorithm(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i, int i2, int i3);

    @Namespace("cv")
    @Name({"connectedComponentsWithStats"})
    public static native int connectedComponentsWithStatsWithAlgorithm(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i, int i2, int i3);

    @Namespace("cv")
    public static native double contourArea(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native double contourArea(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native double contourArea(@ByVal Mat mat);

    @Namespace("cv")
    public static native double contourArea(@ByVal Mat mat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native double contourArea(@ByVal UMat uMat);

    @Namespace("cv")
    public static native double contourArea(@ByVal UMat uMat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void convertMaps(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i);

    @Namespace("cv")
    public static native void convertMaps(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void convertMaps(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i);

    @Namespace("cv")
    public static native void convertMaps(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void convertMaps(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i);

    @Namespace("cv")
    public static native void convertMaps(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void convexHull(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void convexHull(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void convexHull(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void convexHull(@ByVal Mat mat, @ByVal Mat mat2, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void convexHull(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void convexHull(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    @Namespace("cv")
    public static native void convexityDefects(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void convexityDefects(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void convexityDefects(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void cornerEigenValsAndVecs(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void cornerEigenValsAndVecs(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void cornerEigenValsAndVecs(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void cornerEigenValsAndVecs(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void cornerEigenValsAndVecs(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void cornerEigenValsAndVecs(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void cornerHarris(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, double d);

    @Namespace("cv")
    public static native void cornerHarris(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, double d, int i3);

    @Namespace("cv")
    public static native void cornerHarris(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, double d);

    @Namespace("cv")
    public static native void cornerHarris(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, double d, int i3);

    @Namespace("cv")
    public static native void cornerHarris(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, double d);

    @Namespace("cv")
    public static native void cornerHarris(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, double d, int i3);

    @Namespace("cv")
    public static native void cornerMinEigenVal(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void cornerMinEigenVal(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void cornerMinEigenVal(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void cornerMinEigenVal(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void cornerMinEigenVal(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void cornerMinEigenVal(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void cornerSubPix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, @ByVal Size size2, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    public static native void cornerSubPix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, @ByVal Size size2, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    public static native void cornerSubPix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, @ByVal Size size2, @ByVal TermCriteria termCriteria);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native CLAHE createCLAHE();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native CLAHE createCLAHE(double d, @ByVal(nullValue = "cv::Size(8, 8)") Size size);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native GeneralizedHoughBallard createGeneralizedHoughBallard();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native GeneralizedHoughGuil createGeneralizedHoughGuil();

    @Namespace("cv")
    public static native void createHanningWindow(@ByVal GpuMat gpuMat, @ByVal Size size, int i);

    @Namespace("cv")
    public static native void createHanningWindow(@ByVal Mat mat, @ByVal Size size, int i);

    @Namespace("cv")
    public static native void createHanningWindow(@ByVal UMat uMat, @ByVal Size size, int i);

    @Namespace("cv")
    @opencv_core.Ptr
    public static native LineSegmentDetector createLineSegmentDetector();

    @Namespace("cv")
    @opencv_core.Ptr
    public static native LineSegmentDetector createLineSegmentDetector(int i, double d, double d2, double d3, double d4, double d5, double d6, int i2);

    public static native CvMat cv2DRotationMatrix(@Cast({"CvPoint2D32f*"}) @ByVal FloatBuffer floatBuffer, double d, double d2, CvMat cvMat);

    public static native CvMat cv2DRotationMatrix(@ByVal CvPoint2D32f cvPoint2D32f, double d, double d2, CvMat cvMat);

    public static native CvMat cv2DRotationMatrix(@Cast({"CvPoint2D32f*"}) @ByVal float[] fArr, double d, double d2, CvMat cvMat);

    public static native void cvAcc(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvAcc(@Const CvArr cvArr, CvArr cvArr2, @Const CvArr cvArr3);

    public static native void cvAdaptiveThreshold(@Const CvArr cvArr, CvArr cvArr2, double d);

    public static native void cvAdaptiveThreshold(@Const CvArr cvArr, CvArr cvArr2, double d, int i, int i2, int i3, double d2);

    public static native CvSeq cvApproxChains(CvSeq cvSeq, CvMemStorage cvMemStorage);

    public static native CvSeq cvApproxChains(CvSeq cvSeq, CvMemStorage cvMemStorage, int i, double d, int i2, int i3);

    public static native CvSeq cvApproxPoly(@Const Pointer pointer, int i, CvMemStorage cvMemStorage, int i2, double d);

    public static native CvSeq cvApproxPoly(@Const Pointer pointer, int i, CvMemStorage cvMemStorage, int i2, double d, int i3);

    public static native double cvArcLength(@Const Pointer pointer);

    public static native double cvArcLength(@Const Pointer pointer, @ByVal(nullValue = "CvSlice(CV_WHOLE_SEQ)") CvSlice cvSlice, int i);

    @ByVal
    public static native CvRect cvBoundingRect(CvArr cvArr);

    @ByVal
    public static native CvRect cvBoundingRect(CvArr cvArr, int i);

    public static native void cvBoxPoints(@ByVal CvBox2D cvBox2D, @Cast({"CvPoint2D32f*"}) FloatBuffer floatBuffer);

    public static native void cvBoxPoints(@ByVal CvBox2D cvBox2D, CvPoint2D32f cvPoint2D32f);

    public static native void cvBoxPoints(@ByVal CvBox2D cvBox2D, @Cast({"CvPoint2D32f*"}) float[] fArr);

    public static native void cvCalcArrBackProject(@Cast({"CvArr**"}) PointerPointer pointerPointer, CvArr cvArr, @Const CvHistogram cvHistogram);

    public static native void cvCalcArrBackProject(@ByPtrPtr CvArr cvArr, CvArr cvArr2, @Const CvHistogram cvHistogram);

    public static native void cvCalcArrBackProjectPatch(@Cast({"CvArr**"}) PointerPointer pointerPointer, CvArr cvArr, @ByVal CvSize cvSize, CvHistogram cvHistogram, int i, double d);

    public static native void cvCalcArrBackProjectPatch(@ByPtrPtr CvArr cvArr, CvArr cvArr2, @ByVal CvSize cvSize, CvHistogram cvHistogram, int i, double d);

    public static native void cvCalcArrHist(@Cast({"CvArr**"}) PointerPointer pointerPointer, CvHistogram cvHistogram, int i, @Const CvArr cvArr);

    public static native void cvCalcArrHist(@ByPtrPtr CvArr cvArr, CvHistogram cvHistogram);

    public static native void cvCalcArrHist(@ByPtrPtr CvArr cvArr, CvHistogram cvHistogram, int i, @Const CvArr cvArr2);

    public static native void cvCalcBackProject(@Cast({"IplImage**"}) PointerPointer pointerPointer, CvArr cvArr, CvHistogram cvHistogram);

    public static native void cvCalcBackProject(@ByPtrPtr IplImage iplImage, CvArr cvArr, CvHistogram cvHistogram);

    public static native void cvCalcBackProjectPatch(@Cast({"IplImage**"}) PointerPointer pointerPointer, CvArr cvArr, @ByVal CvSize cvSize, CvHistogram cvHistogram, int i, double d);

    public static native void cvCalcBackProjectPatch(@ByPtrPtr IplImage iplImage, CvArr cvArr, @ByVal CvSize cvSize, CvHistogram cvHistogram, int i, double d);

    public static native void cvCalcBayesianProb(@Cast({"CvHistogram**"}) PointerPointer pointerPointer, int i, @Cast({"CvHistogram**"}) PointerPointer pointerPointer2);

    public static native void cvCalcBayesianProb(@ByPtrPtr CvHistogram cvHistogram, int i, @ByPtrPtr CvHistogram cvHistogram2);

    public static native float cvCalcEMD2(@Const CvArr cvArr, @Const CvArr cvArr2, int i);

    public static native float cvCalcEMD2(@Const CvArr cvArr, @Const CvArr cvArr2, int i, CvDistanceFunction cvDistanceFunction, @Const CvArr cvArr3, CvArr cvArr4, FloatBuffer floatBuffer, Pointer pointer);

    public static native float cvCalcEMD2(@Const CvArr cvArr, @Const CvArr cvArr2, int i, CvDistanceFunction cvDistanceFunction, @Const CvArr cvArr3, CvArr cvArr4, FloatPointer floatPointer, Pointer pointer);

    public static native float cvCalcEMD2(@Const CvArr cvArr, @Const CvArr cvArr2, int i, CvDistanceFunction cvDistanceFunction, @Const CvArr cvArr3, CvArr cvArr4, float[] fArr, Pointer pointer);

    public static native void cvCalcHist(@Cast({"IplImage**"}) PointerPointer pointerPointer, CvHistogram cvHistogram, int i, @Const CvArr cvArr);

    public static native void cvCalcHist(@ByPtrPtr IplImage iplImage, CvHistogram cvHistogram);

    public static native void cvCalcHist(@ByPtrPtr IplImage iplImage, CvHistogram cvHistogram, int i, @Const CvArr cvArr);

    public static native void cvCalcProbDensity(@Const CvHistogram cvHistogram, @Const CvHistogram cvHistogram2, CvHistogram cvHistogram3);

    public static native void cvCalcProbDensity(@Const CvHistogram cvHistogram, @Const CvHistogram cvHistogram2, CvHistogram cvHistogram3, double d);

    public static native void cvCanny(@Const CvArr cvArr, CvArr cvArr2, double d, double d2);

    public static native void cvCanny(@Const CvArr cvArr, CvArr cvArr2, double d, double d2, int i);

    public static native int cvCheckContourConvexity(@Const CvArr cvArr);

    public static native void cvCircle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, int i, @ByVal CvScalar cvScalar);

    public static native void cvCircle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, int i, @ByVal CvScalar cvScalar, int i2, int i3, int i4);

    public static native void cvCircle(CvArr cvArr, @ByVal CvPoint cvPoint, int i, @ByVal CvScalar cvScalar);

    public static native void cvCircle(CvArr cvArr, @ByVal CvPoint cvPoint, int i, @ByVal CvScalar cvScalar, int i2, int i3, int i4);

    public static native void cvCircle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, int i, @ByVal CvScalar cvScalar);

    public static native void cvCircle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, int i, @ByVal CvScalar cvScalar, int i2, int i3, int i4);

    public static native void cvClearHist(CvHistogram cvHistogram);

    public static native int cvClipLine(@ByVal CvSize cvSize, @Cast({"CvPoint*"}) IntBuffer intBuffer, @Cast({"CvPoint*"}) IntBuffer intBuffer2);

    public static native int cvClipLine(@ByVal CvSize cvSize, CvPoint cvPoint, CvPoint cvPoint2);

    public static native int cvClipLine(@ByVal CvSize cvSize, @Cast({"CvPoint*"}) int[] iArr, @Cast({"CvPoint*"}) int[] iArr2);

    @ByVal
    public static native CvScalar cvColorToScalar(double d, int i);

    public static native double cvCompareHist(@Const CvHistogram cvHistogram, @Const CvHistogram cvHistogram2, int i);

    public static native double cvContourArea(@Const CvArr cvArr);

    public static native double cvContourArea(@Const CvArr cvArr, @ByVal(nullValue = "CvSlice(CV_WHOLE_SEQ)") CvSlice cvSlice, int i);

    public static native double cvContourPerimeter(@Const Pointer pointer);

    public static native void cvConvertMaps(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, CvArr cvArr4);

    public static native CvSeq cvConvexHull2(@Const CvArr cvArr);

    public static native CvSeq cvConvexHull2(@Const CvArr cvArr, Pointer pointer, int i, int i2);

    public static native CvSeq cvConvexityDefects(@Const CvArr cvArr, @Const CvArr cvArr2);

    public static native CvSeq cvConvexityDefects(@Const CvArr cvArr, @Const CvArr cvArr2, CvMemStorage cvMemStorage);

    public static native void cvCopyHist(@Const CvHistogram cvHistogram, @Cast({"CvHistogram**"}) PointerPointer pointerPointer);

    public static native void cvCopyHist(@Const CvHistogram cvHistogram, @ByPtrPtr CvHistogram cvHistogram2);

    public static native void cvCopyMakeBorder(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, int i);

    public static native void cvCopyMakeBorder(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, int i, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar);

    public static native void cvCopyMakeBorder(@Const CvArr cvArr, CvArr cvArr2, @ByVal CvPoint cvPoint, int i);

    public static native void cvCopyMakeBorder(@Const CvArr cvArr, CvArr cvArr2, @ByVal CvPoint cvPoint, int i, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar);

    public static native void cvCopyMakeBorder(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint*"}) @ByVal int[] iArr, int i);

    public static native void cvCopyMakeBorder(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint*"}) @ByVal int[] iArr, int i, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar);

    public static native void cvCornerEigenValsAndVecs(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvCornerEigenValsAndVecs(@Const CvArr cvArr, CvArr cvArr2, int i, int i2);

    public static native void cvCornerHarris(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvCornerHarris(@Const CvArr cvArr, CvArr cvArr2, int i, int i2, double d);

    public static native void cvCornerMinEigenVal(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvCornerMinEigenVal(@Const CvArr cvArr, CvArr cvArr2, int i, int i2);

    public static native CvHistogram cvCreateHist(int i, IntBuffer intBuffer, int i2);

    public static native CvHistogram cvCreateHist(int i, IntBuffer intBuffer, int i2, @ByPtrPtr FloatBuffer floatBuffer, int i3);

    public static native CvHistogram cvCreateHist(int i, IntPointer intPointer, int i2);

    public static native CvHistogram cvCreateHist(int i, IntPointer intPointer, int i2, @ByPtrPtr FloatPointer floatPointer, int i3);

    public static native CvHistogram cvCreateHist(int i, IntPointer intPointer, int i2, @Cast({"float**"}) PointerPointer pointerPointer, int i3);

    public static native CvHistogram cvCreateHist(int i, int[] iArr, int i2);

    public static native CvHistogram cvCreateHist(int i, int[] iArr, int i2, @ByPtrPtr float[] fArr, int i3);

    @Cast({"CvMat**"})
    public static native PointerPointer cvCreatePyramid(@Const CvArr cvArr, int i, double d, @Const CvSize cvSize, CvArr cvArr2, int i2, int i3);

    @ByPtrPtr
    public static native CvMat cvCreatePyramid(@Const CvArr cvArr, int i, double d);

    public static native IplConvKernel cvCreateStructuringElementEx(int i, int i2, int i3, int i4, int i5);

    public static native IplConvKernel cvCreateStructuringElementEx(int i, int i2, int i3, int i4, int i5, IntBuffer intBuffer);

    public static native IplConvKernel cvCreateStructuringElementEx(int i, int i2, int i3, int i4, int i5, IntPointer intPointer);

    public static native IplConvKernel cvCreateStructuringElementEx(int i, int i2, int i3, int i4, int i5, int[] iArr);

    public static native void cvCvtColor(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvDilate(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvDilate(@Const CvArr cvArr, CvArr cvArr2, IplConvKernel iplConvKernel, int i);

    public static native void cvDistTransform(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvDistTransform(@Const CvArr cvArr, CvArr cvArr2, int i, int i2, @Const FloatBuffer floatBuffer, CvArr cvArr3, int i3);

    public static native void cvDistTransform(@Const CvArr cvArr, CvArr cvArr2, int i, int i2, @Const FloatPointer floatPointer, CvArr cvArr3, int i3);

    public static native void cvDistTransform(@Const CvArr cvArr, CvArr cvArr2, int i, int i2, @Const float[] fArr, CvArr cvArr3, int i3);

    public static native void cvDrawCircle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, int i, @ByVal CvScalar cvScalar, int i2, int i3, int i4);

    public static native void cvDrawCircle(CvArr cvArr, @ByVal CvPoint cvPoint, int i, @ByVal CvScalar cvScalar, int i2, int i3, int i4);

    public static native void cvDrawCircle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, int i, @ByVal CvScalar cvScalar, int i2, int i3, int i4);

    public static native void cvDrawContours(CvArr cvArr, CvSeq cvSeq, @ByVal CvScalar cvScalar, @ByVal CvScalar cvScalar2, int i);

    public static native void cvDrawContours(CvArr cvArr, CvSeq cvSeq, @ByVal CvScalar cvScalar, @ByVal CvScalar cvScalar2, int i, int i2, int i3, @Cast({"CvPoint*"}) @ByVal(nullValue = "CvPoint(cvPoint(0,0))") IntBuffer intBuffer);

    public static native void cvDrawContours(CvArr cvArr, CvSeq cvSeq, @ByVal CvScalar cvScalar, @ByVal CvScalar cvScalar2, int i, int i2, int i3, @ByVal(nullValue = "CvPoint(cvPoint(0,0))") CvPoint cvPoint);

    public static native void cvDrawContours(CvArr cvArr, CvSeq cvSeq, @ByVal CvScalar cvScalar, @ByVal CvScalar cvScalar2, int i, int i2, int i3, @Cast({"CvPoint*"}) @ByVal(nullValue = "CvPoint(cvPoint(0,0))") int[] iArr);

    public static native void cvDrawEllipse(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvDrawEllipse(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvDrawEllipse(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvDrawLine(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvDrawLine(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvDrawLine(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvDrawPolyLine(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) IntBuffer intBuffer, IntBuffer intBuffer2, int i, int i2, @ByVal CvScalar cvScalar, int i3, int i4, int i5);

    public static native void cvDrawPolyLine(CvArr cvArr, @Cast({"CvPoint**"}) PointerPointer pointerPointer, IntPointer intPointer, int i, int i2, @ByVal CvScalar cvScalar, int i3, int i4, int i5);

    public static native void cvDrawPolyLine(CvArr cvArr, @ByPtrPtr CvPoint cvPoint, IntPointer intPointer, int i, int i2, @ByVal CvScalar cvScalar, int i3, int i4, int i5);

    public static native void cvDrawPolyLine(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) int[] iArr, int[] iArr2, int i, int i2, @ByVal CvScalar cvScalar, int i3, int i4, int i5);

    public static native void cvDrawRect(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvDrawRect(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvDrawRect(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvEllipse(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar);

    public static native void cvEllipse(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvEllipse(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar);

    public static native void cvEllipse(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvEllipse(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar);

    public static native void cvEllipse(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @ByVal CvSize cvSize, double d, double d2, double d3, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native int cvEllipse2Poly(@Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @ByVal CvSize cvSize, int i, int i2, int i3, @Cast({"CvPoint*"}) IntBuffer intBuffer2, int i4);

    public static native int cvEllipse2Poly(@ByVal CvPoint cvPoint, @ByVal CvSize cvSize, int i, int i2, int i3, CvPoint cvPoint2, int i4);

    public static native int cvEllipse2Poly(@Cast({"CvPoint*"}) @ByVal int[] iArr, @ByVal CvSize cvSize, int i, int i2, int i3, @Cast({"CvPoint*"}) int[] iArr2, int i4);

    public static native void cvEllipseBox(CvArr cvArr, @ByVal CvBox2D cvBox2D, @ByVal CvScalar cvScalar);

    public static native void cvEllipseBox(CvArr cvArr, @ByVal CvBox2D cvBox2D, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native CvSeq cvEndFindContours(@ByPtrPtr CvContourScanner cvContourScanner);

    public static native void cvEqualizeHist(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvErode(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvErode(@Const CvArr cvArr, CvArr cvArr2, IplConvKernel iplConvKernel, int i);

    public static native void cvFillConvexPoly(CvArr cvArr, @Cast({"const CvPoint*"}) IntBuffer intBuffer, int i, @ByVal CvScalar cvScalar);

    public static native void cvFillConvexPoly(CvArr cvArr, @Cast({"const CvPoint*"}) IntBuffer intBuffer, int i, @ByVal CvScalar cvScalar, int i2, int i3);

    public static native void cvFillConvexPoly(CvArr cvArr, @Const CvPoint cvPoint, int i, @ByVal CvScalar cvScalar);

    public static native void cvFillConvexPoly(CvArr cvArr, @Const CvPoint cvPoint, int i, @ByVal CvScalar cvScalar, int i2, int i3);

    public static native void cvFillConvexPoly(CvArr cvArr, @Cast({"const CvPoint*"}) int[] iArr, int i, @ByVal CvScalar cvScalar);

    public static native void cvFillConvexPoly(CvArr cvArr, @Cast({"const CvPoint*"}) int[] iArr, int i, @ByVal CvScalar cvScalar, int i2, int i3);

    public static native void cvFillPoly(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) IntBuffer intBuffer, @Const IntBuffer intBuffer2, int i, @ByVal CvScalar cvScalar);

    public static native void cvFillPoly(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) IntBuffer intBuffer, @Const IntBuffer intBuffer2, int i, @ByVal CvScalar cvScalar, int i2, int i3);

    public static native void cvFillPoly(CvArr cvArr, @Cast({"CvPoint**"}) PointerPointer pointerPointer, @Const IntPointer intPointer, int i, @ByVal CvScalar cvScalar, int i2, int i3);

    public static native void cvFillPoly(CvArr cvArr, @ByPtrPtr CvPoint cvPoint, @Const IntPointer intPointer, int i, @ByVal CvScalar cvScalar);

    public static native void cvFillPoly(CvArr cvArr, @ByPtrPtr CvPoint cvPoint, @Const IntPointer intPointer, int i, @ByVal CvScalar cvScalar, int i2, int i3);

    public static native void cvFillPoly(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) int[] iArr, @Const int[] iArr2, int i, @ByVal CvScalar cvScalar);

    public static native void cvFillPoly(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) int[] iArr, @Const int[] iArr2, int i, @ByVal CvScalar cvScalar, int i2, int i3);

    public static native void cvFilter2D(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat);

    public static native void cvFilter2D(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat, @Cast({"CvPoint*"}) @ByVal(nullValue = "CvPoint(cvPoint(-1,-1))") IntBuffer intBuffer);

    public static native void cvFilter2D(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat, @ByVal(nullValue = "CvPoint(cvPoint(-1,-1))") CvPoint cvPoint);

    public static native void cvFilter2D(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat, @Cast({"CvPoint*"}) @ByVal(nullValue = "CvPoint(cvPoint(-1,-1))") int[] iArr);

    public static native int cvFindContours(CvArr cvArr, CvMemStorage cvMemStorage, @Cast({"CvSeq**"}) PointerPointer pointerPointer, int i, int i2, int i3, @ByVal(nullValue = "CvPoint(cvPoint(0,0))") CvPoint cvPoint);

    public static native int cvFindContours(CvArr cvArr, CvMemStorage cvMemStorage, @ByPtrPtr CvSeq cvSeq);

    public static native int cvFindContours(CvArr cvArr, CvMemStorage cvMemStorage, @ByPtrPtr CvSeq cvSeq, int i, int i2, int i3, @Cast({"CvPoint*"}) @ByVal(nullValue = "CvPoint(cvPoint(0,0))") IntBuffer intBuffer);

    public static native int cvFindContours(CvArr cvArr, CvMemStorage cvMemStorage, @ByPtrPtr CvSeq cvSeq, int i, int i2, int i3, @ByVal(nullValue = "CvPoint(cvPoint(0,0))") CvPoint cvPoint);

    public static native int cvFindContours(CvArr cvArr, CvMemStorage cvMemStorage, @ByPtrPtr CvSeq cvSeq, int i, int i2, int i3, @Cast({"CvPoint*"}) @ByVal(nullValue = "CvPoint(cvPoint(0,0))") int[] iArr);

    public static native void cvFindCornerSubPix(@Const CvArr cvArr, @Cast({"CvPoint2D32f*"}) FloatBuffer floatBuffer, int i, @ByVal CvSize cvSize, @ByVal CvSize cvSize2, @ByVal CvTermCriteria cvTermCriteria);

    public static native void cvFindCornerSubPix(@Const CvArr cvArr, CvPoint2D32f cvPoint2D32f, int i, @ByVal CvSize cvSize, @ByVal CvSize cvSize2, @ByVal CvTermCriteria cvTermCriteria);

    public static native void cvFindCornerSubPix(@Const CvArr cvArr, @Cast({"CvPoint2D32f*"}) float[] fArr, int i, @ByVal CvSize cvSize, @ByVal CvSize cvSize2, @ByVal CvTermCriteria cvTermCriteria);

    public static native CvSeq cvFindNextContour(CvContourScanner cvContourScanner);

    @ByVal
    public static native CvBox2D cvFitEllipse2(@Const CvArr cvArr);

    public static native void cvFitLine(@Const CvArr cvArr, int i, double d, double d2, double d3, FloatBuffer floatBuffer);

    public static native void cvFitLine(@Const CvArr cvArr, int i, double d, double d2, double d3, FloatPointer floatPointer);

    public static native void cvFitLine(@Const CvArr cvArr, int i, double d, double d2, double d3, float[] fArr);

    public static native void cvFloodFill(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @ByVal CvScalar cvScalar);

    public static native void cvFloodFill(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @ByVal CvScalar cvScalar, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar2, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar3, CvConnectedComp cvConnectedComp, int i, CvArr cvArr2);

    public static native void cvFloodFill(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvScalar cvScalar);

    public static native void cvFloodFill(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvScalar cvScalar, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar2, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar3, CvConnectedComp cvConnectedComp, int i, CvArr cvArr2);

    public static native void cvFloodFill(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @ByVal CvScalar cvScalar);

    public static native void cvFloodFill(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @ByVal CvScalar cvScalar, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar2, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar3, CvConnectedComp cvConnectedComp, int i, CvArr cvArr2);

    @ByVal
    public static native CvFont cvFont(double d);

    @ByVal
    public static native CvFont cvFont(double d, int i);

    public static native CvMat cvGetAffineTransform(@Cast({"const CvPoint2D32f*"}) FloatBuffer floatBuffer, @Cast({"const CvPoint2D32f*"}) FloatBuffer floatBuffer2, CvMat cvMat);

    public static native CvMat cvGetAffineTransform(@Const CvPoint2D32f cvPoint2D32f, @Const CvPoint2D32f cvPoint2D32f2, CvMat cvMat);

    public static native CvMat cvGetAffineTransform(@Cast({"const CvPoint2D32f*"}) float[] fArr, @Cast({"const CvPoint2D32f*"}) float[] fArr2, CvMat cvMat);

    public static native double cvGetCentralMoment(CvMoments cvMoments, int i, int i2);

    public static native void cvGetHuMoments(CvMoments cvMoments, CvHuMoments cvHuMoments);

    public static native void cvGetMinMaxHistValue(@Const CvHistogram cvHistogram, FloatBuffer floatBuffer, FloatBuffer floatBuffer2);

    public static native void cvGetMinMaxHistValue(@Const CvHistogram cvHistogram, FloatBuffer floatBuffer, FloatBuffer floatBuffer2, IntBuffer intBuffer, IntBuffer intBuffer2);

    public static native void cvGetMinMaxHistValue(@Const CvHistogram cvHistogram, FloatPointer floatPointer, FloatPointer floatPointer2);

    public static native void cvGetMinMaxHistValue(@Const CvHistogram cvHistogram, FloatPointer floatPointer, FloatPointer floatPointer2, IntPointer intPointer, IntPointer intPointer2);

    public static native void cvGetMinMaxHistValue(@Const CvHistogram cvHistogram, float[] fArr, float[] fArr2);

    public static native void cvGetMinMaxHistValue(@Const CvHistogram cvHistogram, float[] fArr, float[] fArr2, int[] iArr, int[] iArr2);

    public static native double cvGetNormalizedCentralMoment(CvMoments cvMoments, int i, int i2);

    public static native CvMat cvGetPerspectiveTransform(@Cast({"const CvPoint2D32f*"}) FloatBuffer floatBuffer, @Cast({"const CvPoint2D32f*"}) FloatBuffer floatBuffer2, CvMat cvMat);

    public static native CvMat cvGetPerspectiveTransform(@Const CvPoint2D32f cvPoint2D32f, @Const CvPoint2D32f cvPoint2D32f2, CvMat cvMat);

    public static native CvMat cvGetPerspectiveTransform(@Cast({"const CvPoint2D32f*"}) float[] fArr, @Cast({"const CvPoint2D32f*"}) float[] fArr2, CvMat cvMat);

    public static native void cvGetQuadrangleSubPix(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat);

    public static native void cvGetRectSubPix(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal FloatBuffer floatBuffer);

    public static native void cvGetRectSubPix(@Const CvArr cvArr, CvArr cvArr2, @ByVal CvPoint2D32f cvPoint2D32f);

    public static native void cvGetRectSubPix(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal float[] fArr);

    public static native double cvGetSpatialMoment(CvMoments cvMoments, int i, int i2);

    public static native void cvGetTextSize(String str, @Const CvFont cvFont, CvSize cvSize, IntBuffer intBuffer);

    public static native void cvGetTextSize(String str, @Const CvFont cvFont, CvSize cvSize, IntPointer intPointer);

    public static native void cvGetTextSize(String str, @Const CvFont cvFont, CvSize cvSize, int[] iArr);

    public static native void cvGetTextSize(@Cast({"const char*"}) BytePointer bytePointer, @Const CvFont cvFont, CvSize cvSize, IntBuffer intBuffer);

    public static native void cvGetTextSize(@Cast({"const char*"}) BytePointer bytePointer, @Const CvFont cvFont, CvSize cvSize, IntPointer intPointer);

    public static native void cvGetTextSize(@Cast({"const char*"}) BytePointer bytePointer, @Const CvFont cvFont, CvSize cvSize, int[] iArr);

    public static native void cvGoodFeaturesToTrack(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, @Cast({"CvPoint2D32f*"}) FloatBuffer floatBuffer, IntBuffer intBuffer, double d, double d2);

    public static native void cvGoodFeaturesToTrack(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, @Cast({"CvPoint2D32f*"}) FloatBuffer floatBuffer, IntBuffer intBuffer, double d, double d2, @Const CvArr cvArr4, int i, int i2, double d3);

    public static native void cvGoodFeaturesToTrack(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, CvPoint2D32f cvPoint2D32f, IntPointer intPointer, double d, double d2);

    public static native void cvGoodFeaturesToTrack(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, CvPoint2D32f cvPoint2D32f, IntPointer intPointer, double d, double d2, @Const CvArr cvArr4, int i, int i2, double d3);

    public static native void cvGoodFeaturesToTrack(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, @Cast({"CvPoint2D32f*"}) float[] fArr, int[] iArr, double d, double d2);

    public static native void cvGoodFeaturesToTrack(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, @Cast({"CvPoint2D32f*"}) float[] fArr, int[] iArr, double d, double d2, @Const CvArr cvArr4, int i, int i2, double d3);

    public static native CvSeq cvHoughCircles(CvArr cvArr, Pointer pointer, int i, double d, double d2);

    public static native CvSeq cvHoughCircles(CvArr cvArr, Pointer pointer, int i, double d, double d2, double d3, double d4, int i2, int i3);

    public static native CvSeq cvHoughLines2(CvArr cvArr, Pointer pointer, int i, double d, double d2, int i2);

    public static native CvSeq cvHoughLines2(CvArr cvArr, Pointer pointer, int i, double d, double d2, int i2, double d3, double d4, double d5, double d6);

    public static native void cvInitFont(CvFont cvFont, int i, double d, double d2);

    public static native void cvInitFont(CvFont cvFont, int i, double d, double d2, double d3, int i2, int i3);

    public static native int cvInitLineIterator(@Const CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, CvLineIterator cvLineIterator);

    public static native int cvInitLineIterator(@Const CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, CvLineIterator cvLineIterator, int i, int i2);

    public static native int cvInitLineIterator(@Const CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, CvLineIterator cvLineIterator);

    public static native int cvInitLineIterator(@Const CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, CvLineIterator cvLineIterator, int i, int i2);

    public static native int cvInitLineIterator(@Const CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, CvLineIterator cvLineIterator);

    public static native int cvInitLineIterator(@Const CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, CvLineIterator cvLineIterator, int i, int i2);

    public static native void cvIntegral(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvIntegral(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, CvArr cvArr4);

    public static native void cvLaplace(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvLaplace(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvLine(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, @ByVal CvScalar cvScalar);

    public static native void cvLine(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvLine(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, @ByVal CvScalar cvScalar);

    public static native void cvLine(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvLine(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, @ByVal CvScalar cvScalar);

    public static native void cvLine(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvLinearPolar(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal FloatBuffer floatBuffer, double d);

    public static native void cvLinearPolar(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal FloatBuffer floatBuffer, double d, int i);

    public static native void cvLinearPolar(@Const CvArr cvArr, CvArr cvArr2, @ByVal CvPoint2D32f cvPoint2D32f, double d);

    public static native void cvLinearPolar(@Const CvArr cvArr, CvArr cvArr2, @ByVal CvPoint2D32f cvPoint2D32f, double d, int i);

    public static native void cvLinearPolar(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal float[] fArr, double d);

    public static native void cvLinearPolar(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal float[] fArr, double d, int i);

    public static native void cvLogPolar(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal FloatBuffer floatBuffer, double d);

    public static native void cvLogPolar(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal FloatBuffer floatBuffer, double d, int i);

    public static native void cvLogPolar(@Const CvArr cvArr, CvArr cvArr2, @ByVal CvPoint2D32f cvPoint2D32f, double d);

    public static native void cvLogPolar(@Const CvArr cvArr, CvArr cvArr2, @ByVal CvPoint2D32f cvPoint2D32f, double d, int i);

    public static native void cvLogPolar(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal float[] fArr, double d);

    public static native void cvLogPolar(@Const CvArr cvArr, CvArr cvArr2, @Cast({"CvPoint2D32f*"}) @ByVal float[] fArr, double d, int i);

    public static native CvHistogram cvMakeHistHeaderForArray(int i, IntBuffer intBuffer, CvHistogram cvHistogram, FloatBuffer floatBuffer);

    public static native CvHistogram cvMakeHistHeaderForArray(int i, IntBuffer intBuffer, CvHistogram cvHistogram, FloatBuffer floatBuffer, @ByPtrPtr FloatBuffer floatBuffer2, int i2);

    public static native CvHistogram cvMakeHistHeaderForArray(int i, IntPointer intPointer, CvHistogram cvHistogram, FloatPointer floatPointer);

    public static native CvHistogram cvMakeHistHeaderForArray(int i, IntPointer intPointer, CvHistogram cvHistogram, FloatPointer floatPointer, @ByPtrPtr FloatPointer floatPointer2, int i2);

    public static native CvHistogram cvMakeHistHeaderForArray(int i, IntPointer intPointer, CvHistogram cvHistogram, FloatPointer floatPointer, @Cast({"float**"}) PointerPointer pointerPointer, int i2);

    public static native CvHistogram cvMakeHistHeaderForArray(int i, int[] iArr, CvHistogram cvHistogram, float[] fArr);

    public static native CvHistogram cvMakeHistHeaderForArray(int i, int[] iArr, CvHistogram cvHistogram, float[] fArr, @ByPtrPtr float[] fArr2, int i2);

    public static native double cvMatchShapes(@Const Pointer pointer, @Const Pointer pointer2, int i);

    public static native double cvMatchShapes(@Const Pointer pointer, @Const Pointer pointer2, int i, double d);

    public static native void cvMatchTemplate(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, int i);

    @ByVal
    public static native CvRect cvMaxRect(@Const CvRect cvRect, @Const CvRect cvRect2);

    @ByVal
    public static native CvBox2D cvMinAreaRect2(@Const CvArr cvArr);

    @ByVal
    public static native CvBox2D cvMinAreaRect2(@Const CvArr cvArr, CvMemStorage cvMemStorage);

    public static native int cvMinEnclosingCircle(@Const CvArr cvArr, @Cast({"CvPoint2D32f*"}) FloatBuffer floatBuffer, FloatBuffer floatBuffer2);

    public static native int cvMinEnclosingCircle(@Const CvArr cvArr, CvPoint2D32f cvPoint2D32f, FloatPointer floatPointer);

    public static native int cvMinEnclosingCircle(@Const CvArr cvArr, @Cast({"CvPoint2D32f*"}) float[] fArr, float[] fArr2);

    @ByVal
    public static native CvMoments cvMoments();

    @ByVal
    public static native CvMoments cvMoments(@ByRef @Const Moments moments);

    public static native void cvMoments(@Const CvArr cvArr, CvMoments cvMoments);

    public static native void cvMoments(@Const CvArr cvArr, CvMoments cvMoments, int i);

    public static native void cvMorphologyEx(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, IplConvKernel iplConvKernel, int i);

    public static native void cvMorphologyEx(@Const CvArr cvArr, CvArr cvArr2, CvArr cvArr3, IplConvKernel iplConvKernel, int i, int i2);

    public static native void cvMultiplyAcc(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3);

    public static native void cvMultiplyAcc(@Const CvArr cvArr, @Const CvArr cvArr2, CvArr cvArr3, @Const CvArr cvArr4);

    public static native void cvNormalizeHist(CvHistogram cvHistogram, double d);

    public static native double cvPointPolygonTest(@Const CvArr cvArr, @Cast({"CvPoint2D32f*"}) @ByVal FloatBuffer floatBuffer, int i);

    public static native double cvPointPolygonTest(@Const CvArr cvArr, @ByVal CvPoint2D32f cvPoint2D32f, int i);

    public static native double cvPointPolygonTest(@Const CvArr cvArr, @Cast({"CvPoint2D32f*"}) @ByVal float[] fArr, int i);

    public static native CvSeq cvPointSeqFromMat(int i, @Const CvArr cvArr, CvContour cvContour, CvSeqBlock cvSeqBlock);

    public static native void cvPolyLine(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) IntBuffer intBuffer, @Const IntBuffer intBuffer2, int i, int i2, @ByVal CvScalar cvScalar);

    public static native void cvPolyLine(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) IntBuffer intBuffer, @Const IntBuffer intBuffer2, int i, int i2, @ByVal CvScalar cvScalar, int i3, int i4, int i5);

    public static native void cvPolyLine(CvArr cvArr, @Cast({"CvPoint**"}) PointerPointer pointerPointer, @Const IntPointer intPointer, int i, int i2, @ByVal CvScalar cvScalar, int i3, int i4, int i5);

    public static native void cvPolyLine(CvArr cvArr, @ByPtrPtr CvPoint cvPoint, @Const IntPointer intPointer, int i, int i2, @ByVal CvScalar cvScalar);

    public static native void cvPolyLine(CvArr cvArr, @ByPtrPtr CvPoint cvPoint, @Const IntPointer intPointer, int i, int i2, @ByVal CvScalar cvScalar, int i3, int i4, int i5);

    public static native void cvPolyLine(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) int[] iArr, @Const int[] iArr2, int i, int i2, @ByVal CvScalar cvScalar);

    public static native void cvPolyLine(CvArr cvArr, @ByPtrPtr @Cast({"CvPoint**"}) int[] iArr, @Const int[] iArr2, int i, int i2, @ByVal CvScalar cvScalar, int i3, int i4, int i5);

    public static native void cvPreCornerDetect(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvPreCornerDetect(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvPutText(CvArr cvArr, String str, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Const CvFont cvFont, @ByVal CvScalar cvScalar);

    public static native void cvPutText(CvArr cvArr, String str, @ByVal CvPoint cvPoint, @Const CvFont cvFont, @ByVal CvScalar cvScalar);

    public static native void cvPutText(CvArr cvArr, String str, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Const CvFont cvFont, @ByVal CvScalar cvScalar);

    public static native void cvPutText(CvArr cvArr, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Const CvFont cvFont, @ByVal CvScalar cvScalar);

    public static native void cvPutText(CvArr cvArr, @Cast({"const char*"}) BytePointer bytePointer, @ByVal CvPoint cvPoint, @Const CvFont cvFont, @ByVal CvScalar cvScalar);

    public static native void cvPutText(CvArr cvArr, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Const CvFont cvFont, @ByVal CvScalar cvScalar);

    public static native void cvPyrDown(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvPyrDown(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvPyrMeanShiftFiltering(@Const CvArr cvArr, CvArr cvArr2, double d, double d2);

    public static native void cvPyrMeanShiftFiltering(@Const CvArr cvArr, CvArr cvArr2, double d, double d2, int i, @ByVal(nullValue = "CvTermCriteria(cvTermCriteria(CV_TERMCRIT_ITER+CV_TERMCRIT_EPS,5,1))") CvTermCriteria cvTermCriteria);

    public static native void cvPyrUp(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvPyrUp(@Const CvArr cvArr, CvArr cvArr2, int i);

    @ByVal
    public static native CvPoint cvReadChainPoint(CvChainPtReader cvChainPtReader);

    public static native void cvRectangle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, @ByVal CvScalar cvScalar);

    public static native void cvRectangle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvRectangle(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, @ByVal CvScalar cvScalar);

    public static native void cvRectangle(CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvRectangle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, @ByVal CvScalar cvScalar);

    public static native void cvRectangle(CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvRectangleR(CvArr cvArr, @ByVal CvRect cvRect, @ByVal CvScalar cvScalar);

    public static native void cvRectangleR(CvArr cvArr, @ByVal CvRect cvRect, @ByVal CvScalar cvScalar, int i, int i2, int i3);

    public static native void cvReleaseHist(@Cast({"CvHistogram**"}) PointerPointer pointerPointer);

    public static native void cvReleaseHist(@ByPtrPtr CvHistogram cvHistogram);

    public static native void cvReleasePyramid(@ByPtrPtr @Cast({"CvMat***"}) PointerPointer pointerPointer, int i);

    public static native void cvReleaseStructuringElement(@Cast({"IplConvKernel**"}) PointerPointer pointerPointer);

    public static native void cvReleaseStructuringElement(@ByPtrPtr IplConvKernel iplConvKernel);

    public static native void cvRemap(@Const CvArr cvArr, CvArr cvArr2, @Const CvArr cvArr3, @Const CvArr cvArr4);

    public static native void cvRemap(@Const CvArr cvArr, CvArr cvArr2, @Const CvArr cvArr3, @Const CvArr cvArr4, int i, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar);

    public static native void cvResize(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvResize(@Const CvArr cvArr, CvArr cvArr2, int i);

    public static native void cvRunningAvg(@Const CvArr cvArr, CvArr cvArr2, double d);

    public static native void cvRunningAvg(@Const CvArr cvArr, CvArr cvArr2, double d, @Const CvArr cvArr3);

    public static native int cvSampleLine(@Const CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, Pointer pointer);

    public static native int cvSampleLine(@Const CvArr cvArr, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer, @Cast({"CvPoint*"}) @ByVal IntBuffer intBuffer2, Pointer pointer, int i);

    public static native int cvSampleLine(@Const CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, Pointer pointer);

    public static native int cvSampleLine(@Const CvArr cvArr, @ByVal CvPoint cvPoint, @ByVal CvPoint cvPoint2, Pointer pointer, int i);

    public static native int cvSampleLine(@Const CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, Pointer pointer);

    public static native int cvSampleLine(@Const CvArr cvArr, @Cast({"CvPoint*"}) @ByVal int[] iArr, @Cast({"CvPoint*"}) @ByVal int[] iArr2, Pointer pointer, int i);

    public static native void cvSetHistBinRanges(CvHistogram cvHistogram, @ByPtrPtr FloatBuffer floatBuffer);

    public static native void cvSetHistBinRanges(CvHistogram cvHistogram, @ByPtrPtr FloatBuffer floatBuffer, int i);

    public static native void cvSetHistBinRanges(CvHistogram cvHistogram, @ByPtrPtr FloatPointer floatPointer);

    public static native void cvSetHistBinRanges(CvHistogram cvHistogram, @ByPtrPtr FloatPointer floatPointer, int i);

    public static native void cvSetHistBinRanges(CvHistogram cvHistogram, @Cast({"float**"}) PointerPointer pointerPointer, int i);

    public static native void cvSetHistBinRanges(CvHistogram cvHistogram, @ByPtrPtr float[] fArr);

    public static native void cvSetHistBinRanges(CvHistogram cvHistogram, @ByPtrPtr float[] fArr, int i);

    public static native void cvSmooth(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvSmooth(@Const CvArr cvArr, CvArr cvArr2, int i, int i2, int i3, double d, double d2);

    public static native void cvSobel(@Const CvArr cvArr, CvArr cvArr2, int i, int i2);

    public static native void cvSobel(@Const CvArr cvArr, CvArr cvArr2, int i, int i2, int i3);

    public static native void cvSquareAcc(@Const CvArr cvArr, CvArr cvArr2);

    public static native void cvSquareAcc(@Const CvArr cvArr, CvArr cvArr2, @Const CvArr cvArr3);

    public static native CvContourScanner cvStartFindContours(CvArr cvArr, CvMemStorage cvMemStorage);

    public static native CvContourScanner cvStartFindContours(CvArr cvArr, CvMemStorage cvMemStorage, int i, int i2, int i3, @Cast({"CvPoint*"}) @ByVal(nullValue = "CvPoint(cvPoint(0,0))") IntBuffer intBuffer);

    public static native CvContourScanner cvStartFindContours(CvArr cvArr, CvMemStorage cvMemStorage, int i, int i2, int i3, @ByVal(nullValue = "CvPoint(cvPoint(0,0))") CvPoint cvPoint);

    public static native CvContourScanner cvStartFindContours(CvArr cvArr, CvMemStorage cvMemStorage, int i, int i2, int i3, @Cast({"CvPoint*"}) @ByVal(nullValue = "CvPoint(cvPoint(0,0))") int[] iArr);

    public static native void cvStartReadChainPoints(CvChain cvChain, CvChainPtReader cvChainPtReader);

    public static native void cvSubstituteContour(CvContourScanner cvContourScanner, CvSeq cvSeq);

    public static native void cvThreshHist(CvHistogram cvHistogram, double d);

    public static native double cvThreshold(@Const CvArr cvArr, CvArr cvArr2, double d, double d2, int i);

    public static native void cvWarpAffine(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat);

    public static native void cvWarpAffine(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat, int i, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar);

    public static native void cvWarpPerspective(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat);

    public static native void cvWarpPerspective(@Const CvArr cvArr, CvArr cvArr2, @Const CvMat cvMat, int i, @ByVal(nullValue = "CvScalar(cvScalarAll(0))") CvScalar cvScalar);

    public static native void cvWatershed(@Const CvArr cvArr, CvArr cvArr2);

    @Namespace("cv")
    public static native void cvtColor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void cvtColor(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void cvtColor(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void cvtColor(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void cvtColor(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void cvtColor(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void cvtColorTwoPlane(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv")
    public static native void cvtColorTwoPlane(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv")
    public static native void cvtColorTwoPlane(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv")
    public static native void demosaicing(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void demosaicing(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void demosaicing(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void demosaicing(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void demosaicing(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void demosaicing(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void dilate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void dilate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i, int i2, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    public static native void dilate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void dilate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i, int i2, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    public static native void dilate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void dilate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i, int i2, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    public static native void distanceTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void distanceTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void distanceTransform(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void distanceTransform(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void distanceTransform(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void distanceTransform(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv")
    @Name({"distanceTransform"})
    public static native void distanceTransformWithLabels(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2);

    @Namespace("cv")
    @Name({"distanceTransform"})
    public static native void distanceTransformWithLabels(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, int i3);

    @Namespace("cv")
    @Name({"distanceTransform"})
    public static native void distanceTransformWithLabels(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2);

    @Namespace("cv")
    @Name({"distanceTransform"})
    public static native void distanceTransformWithLabels(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, int i3);

    @Namespace("cv")
    @Name({"distanceTransform"})
    public static native void distanceTransformWithLabels(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2);

    @Namespace("cv")
    @Name({"distanceTransform"})
    public static native void distanceTransformWithLabels(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, int i3);

    @Namespace("cv")
    public static native void drawContours(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawContours(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawContours(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawContours(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawContours(@ByVal Mat mat, @ByVal MatVector matVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal Mat mat, @ByVal MatVector matVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawContours(@ByVal Mat mat, @ByVal UMatVector uMatVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal Mat mat, @ByVal UMatVector uMatVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawContours(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawContours(@ByVal UMat uMat, @ByVal MatVector matVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal UMat uMat, @ByVal MatVector matVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawContours(@ByVal UMat uMat, @ByVal UMatVector uMatVector, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawContours(@ByVal UMat uMat, @ByVal UMatVector uMatVector, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2, int i4, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void drawMarker(@ByVal GpuMat gpuMat, @ByVal Point point, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawMarker(@ByVal GpuMat gpuMat, @ByVal Point point, @ByRef @Const Scalar scalar, int i, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void drawMarker(@ByVal Mat mat, @ByVal Point point, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawMarker(@ByVal Mat mat, @ByVal Point point, @ByRef @Const Scalar scalar, int i, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void drawMarker(@ByVal UMat uMat, @ByVal Point point, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void drawMarker(@ByVal UMat uMat, @ByVal Point point, @ByRef @Const Scalar scalar, int i, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void ellipse(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Size size, double d, double d2, double d3, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void ellipse(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Size size, double d, double d2, double d3, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void ellipse(@ByVal GpuMat gpuMat, @ByRef @Const RotatedRect rotatedRect, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void ellipse(@ByVal GpuMat gpuMat, @ByRef @Const RotatedRect rotatedRect, @ByRef @Const Scalar scalar, int i, int i2);

    @Namespace("cv")
    public static native void ellipse(@ByVal Mat mat, @ByVal Point point, @ByVal Size size, double d, double d2, double d3, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void ellipse(@ByVal Mat mat, @ByVal Point point, @ByVal Size size, double d, double d2, double d3, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void ellipse(@ByVal Mat mat, @ByRef @Const RotatedRect rotatedRect, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void ellipse(@ByVal Mat mat, @ByRef @Const RotatedRect rotatedRect, @ByRef @Const Scalar scalar, int i, int i2);

    @Namespace("cv")
    public static native void ellipse(@ByVal UMat uMat, @ByVal Point point, @ByVal Size size, double d, double d2, double d3, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void ellipse(@ByVal UMat uMat, @ByVal Point point, @ByVal Size size, double d, double d2, double d3, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void ellipse(@ByVal UMat uMat, @ByRef @Const RotatedRect rotatedRect, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void ellipse(@ByVal UMat uMat, @ByRef @Const RotatedRect rotatedRect, @ByRef @Const Scalar scalar, int i, int i2);

    @Namespace("cv")
    public static native void ellipse2Poly(@ByVal Point2d point2d, @ByVal Size2d size2d, int i, int i2, int i3, int i4, @ByRef Point2dVector point2dVector);

    @Namespace("cv")
    public static native void ellipse2Poly(@ByVal Point point, @ByVal Size size, int i, int i2, int i3, int i4, @ByRef PointVector pointVector);

    @Namespace("cv")
    public static native void equalizeHist(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void equalizeHist(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void equalizeHist(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void erode(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void erode(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i, int i2, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    public static native void erode(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void erode(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i, int i2, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    public static native void erode(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void erode(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i, int i2, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef @Const Scalar scalar, int i, int i2);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal GpuMat gpuMat, @Const Point point, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal GpuMat gpuMat, @Const Point point, int i, @ByRef @Const Scalar scalar, int i2, int i3);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal Mat mat, @ByVal Mat mat2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal Mat mat, @ByVal Mat mat2, @ByRef @Const Scalar scalar, int i, int i2);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal Mat mat, @Const Point point, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal Mat mat, @Const Point point, int i, @ByRef @Const Scalar scalar, int i2, int i3);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal UMat uMat, @Const Point point, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal UMat uMat, @Const Point point, int i, @ByRef @Const Scalar scalar, int i2, int i3);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillConvexPoly(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef @Const Scalar scalar, int i, int i2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @Cast({"const cv::Point**"}) PointerPointer pointerPointer, @Const IntPointer intPointer, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByVal MatVector matVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByVal MatVector matVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByVal MatVector matVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByVal MatVector matVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @ByRef @Const Scalar scalar, int i2, int i3, @ByVal(nullValue = "cv::Point()") Point point2);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void fillPoly(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByRef @Const Scalar scalar, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void filter2D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void filter2D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, double d, int i2);

    @Namespace("cv")
    public static native void filter2D(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void filter2D(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Mat mat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, double d, int i2);

    @Namespace("cv")
    public static native void filter2D(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void filter2D(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal UMat uMat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, double d, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByVal GpuMat gpuMat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByVal Mat mat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal MatVector matVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal MatVector matVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal MatVector matVector, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal MatVector matVector, @ByVal Mat mat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal UMatVector uMatVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal UMatVector uMatVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByVal Mat mat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal MatVector matVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal MatVector matVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal MatVector matVector, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal MatVector matVector, @ByVal UMat uMat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal UMatVector uMatVector, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal UMatVector uMatVector, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void findContours(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByVal UMat uMat2, int i, int i2, @ByVal(nullValue = "cv::Point()") Point point);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipse(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipse(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipse(@ByVal UMat uMat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipseAMS(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipseAMS(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipseAMS(@ByVal UMat uMat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipseDirect(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipseDirect(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect fitEllipseDirect(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void fitLine(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, double d3);

    @Namespace("cv")
    public static native void fitLine(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, double d3);

    @Namespace("cv")
    public static native void fitLine(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, double d3);

    @Namespace("cv")
    public static native int floodFill(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Point point, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native int floodFill(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Point point, @ByVal Scalar scalar, Rect rect, @ByVal(nullValue = "cv::Scalar()") Scalar scalar2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar3, int i);

    @Namespace("cv")
    public static native int floodFill(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native int floodFill(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Scalar scalar, Rect rect, @ByVal(nullValue = "cv::Scalar()") Scalar scalar2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar3, int i);

    @Namespace("cv")
    public static native int floodFill(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Point point, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native int floodFill(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Point point, @ByVal Scalar scalar, Rect rect, @ByVal(nullValue = "cv::Scalar()") Scalar scalar2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar3, int i);

    @Namespace("cv")
    public static native int floodFill(@ByVal Mat mat, @ByVal Point point, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native int floodFill(@ByVal Mat mat, @ByVal Point point, @ByVal Scalar scalar, Rect rect, @ByVal(nullValue = "cv::Scalar()") Scalar scalar2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar3, int i);

    @Namespace("cv")
    public static native int floodFill(@ByVal UMat uMat, @ByVal Point point, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native int floodFill(@ByVal UMat uMat, @ByVal Point point, @ByVal Scalar scalar, Rect rect, @ByVal(nullValue = "cv::Scalar()") Scalar scalar2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar3, int i);

    @Namespace("cv")
    public static native int floodFill(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Point point, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native int floodFill(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Point point, @ByVal Scalar scalar, Rect rect, @ByVal(nullValue = "cv::Scalar()") Scalar scalar2, @ByVal(nullValue = "cv::Scalar()") Scalar scalar3, int i);

    @Namespace("cv")
    @ByVal
    public static native Mat getAffineTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat getAffineTransform(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Mat getAffineTransform(@Const Point2f point2f, @Const Point2f point2f2);

    @Namespace("cv")
    @ByVal
    public static native Mat getAffineTransform(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void getDerivKernels(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void getDerivKernels(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, int i3, @Cast({"bool"}) boolean z, int i4);

    @Namespace("cv")
    public static native void getDerivKernels(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void getDerivKernels(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, int i3, @Cast({"bool"}) boolean z, int i4);

    @Namespace("cv")
    public static native void getDerivKernels(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3);

    @Namespace("cv")
    public static native void getDerivKernels(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, int i3, @Cast({"bool"}) boolean z, int i4);

    @Namespace("cv")
    public static native double getFontScaleFromHeight(int i, int i2);

    @Namespace("cv")
    public static native double getFontScaleFromHeight(int i, int i2, int i3);

    @Namespace("cv")
    @ByVal
    public static native Mat getGaborKernel(@ByVal Size size, double d, double d2, double d3, double d4);

    @Namespace("cv")
    @ByVal
    public static native Mat getGaborKernel(@ByVal Size size, double d, double d2, double d3, double d4, double d5, int i);

    @Namespace("cv")
    @ByVal
    public static native Mat getGaussianKernel(int i, double d);

    @Namespace("cv")
    @ByVal
    public static native Mat getGaussianKernel(int i, double d, int i2);

    @Namespace("cv")
    @ByVal
    public static native Mat getPerspectiveTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat getPerspectiveTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    @ByVal
    public static native Mat getPerspectiveTransform(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Mat getPerspectiveTransform(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    @ByVal
    public static native Mat getPerspectiveTransform(@Const Point2f point2f, @Const Point2f point2f2);

    @Namespace("cv")
    @ByVal
    public static native Mat getPerspectiveTransform(@Const Point2f point2f, @Const Point2f point2f2, int i);

    @Namespace("cv")
    @ByVal
    public static native Mat getPerspectiveTransform(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Mat getPerspectiveTransform(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void getRectSubPix(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal Point2f point2f, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void getRectSubPix(@ByVal GpuMat gpuMat, @ByVal Size size, @ByVal Point2f point2f, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void getRectSubPix(@ByVal Mat mat, @ByVal Size size, @ByVal Point2f point2f, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void getRectSubPix(@ByVal Mat mat, @ByVal Size size, @ByVal Point2f point2f, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void getRectSubPix(@ByVal UMat uMat, @ByVal Size size, @ByVal Point2f point2f, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void getRectSubPix(@ByVal UMat uMat, @ByVal Size size, @ByVal Point2f point2f, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    @ByVal
    public static native Mat getRotationMatrix2D(@ByVal Point2f point2f, double d, double d2);

    @Namespace("cv")
    @Cast({"cv::Matx23d*"})
    @ByVal
    public static native DoublePointer getRotationMatrix2D_(@ByVal Point2f point2f, double d, double d2);

    @Namespace("cv")
    @ByVal
    public static native Mat getStructuringElement(int i, @ByVal Size size);

    @Namespace("cv")
    @ByVal
    public static native Mat getStructuringElement(int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1,-1)") Point point);

    @Namespace("cv")
    @ByVal
    public static native Size getTextSize(@opencv_core.Str String str, int i, double d, int i2, IntBuffer intBuffer);

    @Namespace("cv")
    @ByVal
    public static native Size getTextSize(@opencv_core.Str String str, int i, double d, int i2, IntPointer intPointer);

    @Namespace("cv")
    @ByVal
    public static native Size getTextSize(@opencv_core.Str String str, int i, double d, int i2, int[] iArr);

    @Namespace("cv")
    @ByVal
    public static native Size getTextSize(@opencv_core.Str BytePointer bytePointer, int i, double d, int i2, IntBuffer intBuffer);

    @Namespace("cv")
    @ByVal
    public static native Size getTextSize(@opencv_core.Str BytePointer bytePointer, int i, double d, int i2, IntPointer intPointer);

    @Namespace("cv")
    @ByVal
    public static native Size getTextSize(@opencv_core.Str BytePointer bytePointer, int i, double d, int i2, int[] iArr);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, @ByVal GpuMat gpuMat3, int i2, int i3);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, @ByVal GpuMat gpuMat3, int i2, int i3, @Cast({"bool"}) boolean z, double d3);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d, double d2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, int i2, @Cast({"bool"}) boolean z, double d3);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, @ByVal Mat mat3, int i2, int i3);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, @ByVal Mat mat3, int i2, int i3, @Cast({"bool"}) boolean z, double d3);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal Mat mat, @ByVal Mat mat2, int i, double d, double d2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, int i2, @Cast({"bool"}) boolean z, double d3);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, @ByVal UMat uMat3, int i2, int i3);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, @ByVal UMat uMat3, int i2, int i3, @Cast({"bool"}) boolean z, double d3);

    @Namespace("cv")
    public static native void goodFeaturesToTrack(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d, double d2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, int i2, @Cast({"bool"}) boolean z, double d3);

    @Namespace("cv")
    public static native void grabCut(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Rect rect, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i);

    @Namespace("cv")
    public static native void grabCut(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Rect rect, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i, int i2);

    @Namespace("cv")
    public static native void grabCut(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Rect rect, @ByVal Mat mat3, @ByVal Mat mat4, int i);

    @Namespace("cv")
    public static native void grabCut(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Rect rect, @ByVal Mat mat3, @ByVal Mat mat4, int i, int i2);

    @Namespace("cv")
    public static native void grabCut(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Rect rect, @ByVal UMat uMat3, @ByVal UMat uMat4, int i);

    @Namespace("cv")
    public static native void grabCut(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Rect rect, @ByVal UMat uMat3, @ByVal UMat uMat4, int i, int i2);

    @Namespace("cv")
    public static native void integral(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void integral(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void integral(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void integral(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void integral(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void integral(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral2(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral2(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral2(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral2(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral2(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral2(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral3(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral3(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i, int i2);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral3(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral3(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i, int i2);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral3(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    @Name({"integral"})
    public static native void integral3(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i, int i2);

    @Namespace("cv")
    public static native float intersectConvexConvex(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native float intersectConvexConvex(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native float intersectConvexConvex(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native float intersectConvexConvex(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native float intersectConvexConvex(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native float intersectConvexConvex(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void invertAffineTransform(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void invertAffineTransform(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void invertAffineTransform(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean isContourConvex(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean isContourConvex(@ByVal Mat mat);

    @Namespace("cv")
    @Cast({"bool"})
    public static native boolean isContourConvex(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void line(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void line(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void line(@ByVal Mat mat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void line(@ByVal Mat mat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void line(@ByVal UMat uMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void line(@ByVal UMat uMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void linearPolar(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native void linearPolar(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native void linearPolar(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native void logPolar(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native void logPolar(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native void logPolar(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native double matchShapes(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d);

    @Namespace("cv")
    public static native double matchShapes(@ByVal Mat mat, @ByVal Mat mat2, int i, double d);

    @Namespace("cv")
    public static native double matchShapes(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d);

    @Namespace("cv")
    public static native void matchTemplate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    @Namespace("cv")
    public static native void matchTemplate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4);

    @Namespace("cv")
    public static native void matchTemplate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    @Namespace("cv")
    public static native void matchTemplate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4);

    @Namespace("cv")
    public static native void matchTemplate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    @Namespace("cv")
    public static native void matchTemplate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4);

    @Namespace("cv")
    public static native void medianBlur(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void medianBlur(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void medianBlur(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect minAreaRect(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect minAreaRect(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native RotatedRect minAreaRect(@ByVal UMat uMat);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal GpuMat gpuMat, @ByRef Point2f point2f, @ByRef FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal GpuMat gpuMat, @ByRef Point2f point2f, @ByRef FloatPointer floatPointer);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal GpuMat gpuMat, @ByRef Point2f point2f, @ByRef float[] fArr);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal Mat mat, @ByRef Point2f point2f, @ByRef FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal Mat mat, @ByRef Point2f point2f, @ByRef FloatPointer floatPointer);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal Mat mat, @ByRef Point2f point2f, @ByRef float[] fArr);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal UMat uMat, @ByRef Point2f point2f, @ByRef FloatBuffer floatBuffer);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal UMat uMat, @ByRef Point2f point2f, @ByRef FloatPointer floatPointer);

    @Namespace("cv")
    public static native void minEnclosingCircle(@ByVal UMat uMat, @ByRef Point2f point2f, @ByRef float[] fArr);

    @Namespace("cv")
    public static native double minEnclosingTriangle(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native double minEnclosingTriangle(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native double minEnclosingTriangle(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Moments moments(@ByVal GpuMat gpuMat);

    @Namespace("cv")
    @ByVal
    public static native Moments moments(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Moments moments(@ByVal Mat mat);

    @Namespace("cv")
    @ByVal
    public static native Moments moments(@ByVal Mat mat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Moments moments(@ByVal UMat uMat);

    @Namespace("cv")
    @ByVal
    public static native Moments moments(@ByVal UMat uMat, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    @ByVal
    public static native Scalar morphologyDefaultBorderValue();

    @Namespace("cv")
    public static native void morphologyEx(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void morphologyEx(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i2, int i3, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    public static native void morphologyEx(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void morphologyEx(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Mat mat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i2, int i3, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    public static native void morphologyEx(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void morphologyEx(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal UMat uMat3, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, int i2, int i3, @ByRef(nullValue = "cv::Scalar(cv::morphologyDefaultBorderValue())") @Const Scalar scalar);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, DoublePointer doublePointer);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, double[] dArr);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, DoublePointer doublePointer);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, double[] dArr);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, DoubleBuffer doubleBuffer);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, DoublePointer doublePointer);

    @Namespace("cv")
    @ByVal
    public static native Point2d phaseCorrelate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, double[] dArr);

    @Namespace("cv")
    public static native double pointPolygonTest(@ByVal GpuMat gpuMat, @ByVal Point2f point2f, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native double pointPolygonTest(@ByVal Mat mat, @ByVal Point2f point2f, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native double pointPolygonTest(@ByVal UMat uMat, @ByVal Point2f point2f, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @Cast({"const cv::Point*const*"}) PointerPointer pointerPointer, @Const IntPointer intPointer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByVal MatVector matVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByVal MatVector matVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByVal UMatVector uMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal Mat mat, @ByVal UMatVector uMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByVal MatVector matVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByVal MatVector matVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const IntBuffer intBuffer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const IntPointer intPointer, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByPtrPtr @Const Point point, @Const int[] iArr, int i, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i2, int i3, int i4);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void polylines(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @Cast({"bool"}) boolean z, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void preCornerDetect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @Namespace("cv")
    public static native void preCornerDetect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @Namespace("cv")
    public static native void preCornerDetect(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @Namespace("cv")
    public static native void preCornerDetect(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @Namespace("cv")
    public static native void preCornerDetect(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @Namespace("cv")
    public static native void preCornerDetect(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @Namespace("cv")
    public static native void putText(@ByVal GpuMat gpuMat, @opencv_core.Str String str, @ByVal Point point, int i, double d, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native void putText(@ByVal GpuMat gpuMat, @opencv_core.Str String str, @ByVal Point point, int i, double d, @ByVal Scalar scalar, int i2, int i3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void putText(@ByVal GpuMat gpuMat, @opencv_core.Str BytePointer bytePointer, @ByVal Point point, int i, double d, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native void putText(@ByVal GpuMat gpuMat, @opencv_core.Str BytePointer bytePointer, @ByVal Point point, int i, double d, @ByVal Scalar scalar, int i2, int i3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void putText(@ByVal Mat mat, @opencv_core.Str String str, @ByVal Point point, int i, double d, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native void putText(@ByVal Mat mat, @opencv_core.Str String str, @ByVal Point point, int i, double d, @ByVal Scalar scalar, int i2, int i3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void putText(@ByVal Mat mat, @opencv_core.Str BytePointer bytePointer, @ByVal Point point, int i, double d, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native void putText(@ByVal Mat mat, @opencv_core.Str BytePointer bytePointer, @ByVal Point point, int i, double d, @ByVal Scalar scalar, int i2, int i3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void putText(@ByVal UMat uMat, @opencv_core.Str String str, @ByVal Point point, int i, double d, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native void putText(@ByVal UMat uMat, @opencv_core.Str String str, @ByVal Point point, int i, double d, @ByVal Scalar scalar, int i2, int i3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void putText(@ByVal UMat uMat, @opencv_core.Str BytePointer bytePointer, @ByVal Point point, int i, double d, @ByVal Scalar scalar);

    @Namespace("cv")
    public static native void putText(@ByVal UMat uMat, @opencv_core.Str BytePointer bytePointer, @ByVal Point point, int i, double d, @ByVal Scalar scalar, int i2, int i3, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void pyrDown(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void pyrDown(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::Size()") @Const Size size, int i);

    @Namespace("cv")
    public static native void pyrDown(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void pyrDown(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::Size()") @Const Size size, int i);

    @Namespace("cv")
    public static native void pyrDown(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void pyrDown(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::Size()") @Const Size size, int i);

    @Namespace("cv")
    public static native void pyrMeanShiftFiltering(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2);

    @Namespace("cv")
    public static native void pyrMeanShiftFiltering(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER+cv::TermCriteria::EPS,5,1)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native void pyrMeanShiftFiltering(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2);

    @Namespace("cv")
    public static native void pyrMeanShiftFiltering(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER+cv::TermCriteria::EPS,5,1)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native void pyrMeanShiftFiltering(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2);

    @Namespace("cv")
    public static native void pyrMeanShiftFiltering(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER+cv::TermCriteria::EPS,5,1)") TermCriteria termCriteria);

    @Namespace("cv")
    public static native void pyrUp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void pyrUp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::Size()") @Const Size size, int i);

    @Namespace("cv")
    public static native void pyrUp(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void pyrUp(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::Size()") @Const Size size, int i);

    @Namespace("cv")
    public static native void pyrUp(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void pyrUp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::Size()") @Const Size size, int i);

    @Namespace("cv")
    public static native void rectangle(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void rectangle(@ByVal GpuMat gpuMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void rectangle(@ByVal GpuMat gpuMat, @ByVal Rect rect, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void rectangle(@ByVal GpuMat gpuMat, @ByVal Rect rect, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void rectangle(@ByVal Mat mat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void rectangle(@ByVal Mat mat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void rectangle(@ByVal Mat mat, @ByVal Rect rect, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void rectangle(@ByVal Mat mat, @ByVal Rect rect, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void rectangle(@ByVal UMat uMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void rectangle(@ByVal UMat uMat, @ByVal Point point, @ByVal Point point2, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void rectangle(@ByVal UMat uMat, @ByVal Rect rect, @ByRef @Const Scalar scalar);

    @Namespace("cv")
    public static native void rectangle(@ByVal UMat uMat, @ByVal Rect rect, @ByRef @Const Scalar scalar, int i, int i2, int i3);

    @Namespace("cv")
    public static native void remap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i);

    @Namespace("cv")
    public static native void remap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void remap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i);

    @Namespace("cv")
    public static native void remap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void remap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i);

    @Namespace("cv")
    public static native void remap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void resize(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size);

    @Namespace("cv")
    public static native void resize(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, double d, double d2, int i);

    @Namespace("cv")
    public static native void resize(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size);

    @Namespace("cv")
    public static native void resize(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, double d, double d2, int i);

    @Namespace("cv")
    public static native void resize(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size);

    @Namespace("cv")
    public static native void resize(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, double d, double d2, int i);

    @Namespace("cv")
    public static native int rotatedRectangleIntersection(@ByRef @Const RotatedRect rotatedRect, @ByRef @Const RotatedRect rotatedRect2, @ByVal GpuMat gpuMat);

    @Namespace("cv")
    public static native int rotatedRectangleIntersection(@ByRef @Const RotatedRect rotatedRect, @ByRef @Const RotatedRect rotatedRect2, @ByVal Mat mat);

    @Namespace("cv")
    public static native int rotatedRectangleIntersection(@ByRef @Const RotatedRect rotatedRect, @ByRef @Const RotatedRect rotatedRect2, @ByVal UMat uMat);

    @Namespace("cv")
    public static native void sepFilter2D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv")
    public static native void sepFilter2D(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, double d, int i2);

    @Namespace("cv")
    public static native void sepFilter2D(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv")
    public static native void sepFilter2D(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, double d, int i2);

    @Namespace("cv")
    public static native void sepFilter2D(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv")
    public static native void sepFilter2D(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::Point(-1,-1)") Point point, double d, int i2);

    @Namespace("cv")
    public static native void spatialGradient(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void spatialGradient(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2);

    @Namespace("cv")
    public static native void spatialGradient(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void spatialGradient(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2);

    @Namespace("cv")
    public static native void spatialGradient(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void spatialGradient(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2);

    @Namespace("cv")
    public static native void sqrBoxFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal Size size);

    @Namespace("cv")
    public static native void sqrBoxFilter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, @Cast({"bool"}) boolean z, int i2);

    @Namespace("cv")
    public static native void sqrBoxFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Size size);

    @Namespace("cv")
    public static native void sqrBoxFilter(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, @Cast({"bool"}) boolean z, int i2);

    @Namespace("cv")
    public static native void sqrBoxFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal Size size);

    @Namespace("cv")
    public static native void sqrBoxFilter(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal Size size, @ByVal(nullValue = "cv::Point(-1, -1)") Point point, @Cast({"bool"}) boolean z, int i2);

    @Namespace("cv")
    public static native double threshold(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, int i);

    @Namespace("cv")
    public static native double threshold(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, int i);

    @Namespace("cv")
    public static native double threshold(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, int i);

    @Namespace("cv")
    public static native void warpAffine(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Size size);

    @Namespace("cv")
    public static native void warpAffine(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Size size, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void warpAffine(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Size size);

    @Namespace("cv")
    public static native void warpAffine(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Size size, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void warpAffine(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Size size);

    @Namespace("cv")
    public static native void warpAffine(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Size size, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void warpPerspective(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Size size);

    @Namespace("cv")
    public static native void warpPerspective(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal Size size, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void warpPerspective(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Size size);

    @Namespace("cv")
    public static native void warpPerspective(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Size size, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void warpPerspective(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Size size);

    @Namespace("cv")
    public static native void warpPerspective(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal Size size, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    @Namespace("cv")
    public static native void warpPolar(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Size size, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native void warpPolar(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Size size, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native void warpPolar(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Size size, @ByVal Point2f point2f, double d, int i);

    @Namespace("cv")
    public static native void watershed(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void watershed(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void watershed(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }
}
