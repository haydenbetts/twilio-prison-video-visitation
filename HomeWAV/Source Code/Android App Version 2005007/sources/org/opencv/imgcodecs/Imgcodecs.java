package org.opencv.imgcodecs;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.utils.Converters;

public class Imgcodecs {
    public static final int IMREAD_ANYCOLOR = 4;
    public static final int IMREAD_ANYDEPTH = 2;
    public static final int IMREAD_COLOR = 1;
    public static final int IMREAD_GRAYSCALE = 0;
    public static final int IMREAD_IGNORE_ORIENTATION = 128;
    public static final int IMREAD_LOAD_GDAL = 8;
    public static final int IMREAD_REDUCED_COLOR_2 = 17;
    public static final int IMREAD_REDUCED_COLOR_4 = 33;
    public static final int IMREAD_REDUCED_COLOR_8 = 65;
    public static final int IMREAD_REDUCED_GRAYSCALE_2 = 16;
    public static final int IMREAD_REDUCED_GRAYSCALE_4 = 32;
    public static final int IMREAD_REDUCED_GRAYSCALE_8 = 64;
    public static final int IMREAD_UNCHANGED = -1;
    public static final int IMWRITE_EXR_TYPE = 48;
    public static final int IMWRITE_EXR_TYPE_FLOAT = 2;
    public static final int IMWRITE_EXR_TYPE_HALF = 1;
    public static final int IMWRITE_JPEG2000_COMPRESSION_X1000 = 272;
    public static final int IMWRITE_JPEG_CHROMA_QUALITY = 6;
    public static final int IMWRITE_JPEG_LUMA_QUALITY = 5;
    public static final int IMWRITE_JPEG_OPTIMIZE = 3;
    public static final int IMWRITE_JPEG_PROGRESSIVE = 2;
    public static final int IMWRITE_JPEG_QUALITY = 1;
    public static final int IMWRITE_JPEG_RST_INTERVAL = 4;
    public static final int IMWRITE_PAM_FORMAT_BLACKANDWHITE = 1;
    public static final int IMWRITE_PAM_FORMAT_GRAYSCALE = 2;
    public static final int IMWRITE_PAM_FORMAT_GRAYSCALE_ALPHA = 3;
    public static final int IMWRITE_PAM_FORMAT_NULL = 0;
    public static final int IMWRITE_PAM_FORMAT_RGB = 4;
    public static final int IMWRITE_PAM_FORMAT_RGB_ALPHA = 5;
    public static final int IMWRITE_PAM_TUPLETYPE = 128;
    public static final int IMWRITE_PNG_BILEVEL = 18;
    public static final int IMWRITE_PNG_COMPRESSION = 16;
    public static final int IMWRITE_PNG_STRATEGY = 17;
    public static final int IMWRITE_PNG_STRATEGY_DEFAULT = 0;
    public static final int IMWRITE_PNG_STRATEGY_FILTERED = 1;
    public static final int IMWRITE_PNG_STRATEGY_FIXED = 4;
    public static final int IMWRITE_PNG_STRATEGY_HUFFMAN_ONLY = 2;
    public static final int IMWRITE_PNG_STRATEGY_RLE = 3;
    public static final int IMWRITE_PXM_BINARY = 32;
    public static final int IMWRITE_TIFF_COMPRESSION = 259;
    public static final int IMWRITE_TIFF_RESUNIT = 256;
    public static final int IMWRITE_TIFF_XDPI = 257;
    public static final int IMWRITE_TIFF_YDPI = 258;
    public static final int IMWRITE_WEBP_QUALITY = 64;

    private static native boolean haveImageReader_0(String str);

    private static native boolean haveImageWriter_0(String str);

    private static native long imdecode_0(long j, int i);

    private static native boolean imencode_0(String str, long j, long j2, long j3);

    private static native boolean imencode_1(String str, long j, long j2);

    private static native long imread_0(String str, int i);

    private static native long imread_1(String str);

    private static native boolean imreadmulti_0(String str, long j, int i);

    private static native boolean imreadmulti_1(String str, long j);

    private static native boolean imwrite_0(String str, long j, long j2);

    private static native boolean imwrite_1(String str, long j);

    public static Mat imdecode(Mat mat, int i) {
        return new Mat(imdecode_0(mat.nativeObj, i));
    }

    public static Mat imread(String str, int i) {
        return new Mat(imread_0(str, i));
    }

    public static Mat imread(String str) {
        return new Mat(imread_1(str));
    }

    public static boolean haveImageReader(String str) {
        return haveImageReader_0(str);
    }

    public static boolean haveImageWriter(String str) {
        return haveImageWriter_0(str);
    }

    public static boolean imencode(String str, Mat mat, MatOfByte matOfByte, MatOfInt matOfInt) {
        return imencode_0(str, mat.nativeObj, matOfByte.nativeObj, matOfInt.nativeObj);
    }

    public static boolean imencode(String str, Mat mat, MatOfByte matOfByte) {
        return imencode_1(str, mat.nativeObj, matOfByte.nativeObj);
    }

    public static boolean imreadmulti(String str, List<Mat> list, int i) {
        Mat mat = new Mat();
        boolean imreadmulti_0 = imreadmulti_0(str, mat.nativeObj, i);
        Converters.Mat_to_vector_Mat(mat, list);
        mat.release();
        return imreadmulti_0;
    }

    public static boolean imreadmulti(String str, List<Mat> list) {
        Mat mat = new Mat();
        boolean imreadmulti_1 = imreadmulti_1(str, mat.nativeObj);
        Converters.Mat_to_vector_Mat(mat, list);
        mat.release();
        return imreadmulti_1;
    }

    public static boolean imwrite(String str, Mat mat, MatOfInt matOfInt) {
        return imwrite_0(str, mat.nativeObj, matOfInt.nativeObj);
    }

    public static boolean imwrite(String str, Mat mat) {
        return imwrite_1(str, mat.nativeObj);
    }
}
