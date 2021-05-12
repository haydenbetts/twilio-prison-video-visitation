package org.opencv.xfeatures2d;

import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Size;

public class Xfeatures2d {
    private static native void matchGMS_0(double d, double d2, double d3, double d4, long j, long j2, long j3, long j4, boolean z, boolean z2, double d5);

    private static native void matchGMS_1(double d, double d2, double d3, double d4, long j, long j2, long j3, long j4, boolean z, boolean z2);

    private static native void matchGMS_2(double d, double d2, double d3, double d4, long j, long j2, long j3, long j4, boolean z);

    private static native void matchGMS_3(double d, double d2, double d3, double d4, long j, long j2, long j3, long j4);

    private static native void matchLOGOS_0(long j, long j2, long j3, long j4, long j5);

    public static void matchGMS(Size size, Size size2, MatOfKeyPoint matOfKeyPoint, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, MatOfDMatch matOfDMatch2, boolean z, boolean z2, double d) {
        Size size3 = size;
        Size size4 = size2;
        matchGMS_0(size3.width, size3.height, size4.width, size4.height, matOfKeyPoint.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, matOfDMatch2.nativeObj, z, z2, d);
    }

    public static void matchGMS(Size size, Size size2, MatOfKeyPoint matOfKeyPoint, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, MatOfDMatch matOfDMatch2, boolean z, boolean z2) {
        Size size3 = size;
        Size size4 = size2;
        matchGMS_1(size3.width, size3.height, size4.width, size4.height, matOfKeyPoint.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, matOfDMatch2.nativeObj, z, z2);
    }

    public static void matchGMS(Size size, Size size2, MatOfKeyPoint matOfKeyPoint, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, MatOfDMatch matOfDMatch2, boolean z) {
        Size size3 = size;
        Size size4 = size2;
        matchGMS_2(size3.width, size3.height, size4.width, size4.height, matOfKeyPoint.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, matOfDMatch2.nativeObj, z);
    }

    public static void matchGMS(Size size, Size size2, MatOfKeyPoint matOfKeyPoint, MatOfKeyPoint matOfKeyPoint2, MatOfDMatch matOfDMatch, MatOfDMatch matOfDMatch2) {
        Size size3 = size;
        Size size4 = size2;
        matchGMS_3(size3.width, size3.height, size4.width, size4.height, matOfKeyPoint.nativeObj, matOfKeyPoint2.nativeObj, matOfDMatch.nativeObj, matOfDMatch2.nativeObj);
    }

    public static void matchLOGOS(MatOfKeyPoint matOfKeyPoint, MatOfKeyPoint matOfKeyPoint2, MatOfInt matOfInt, MatOfInt matOfInt2, MatOfDMatch matOfDMatch) {
        matchLOGOS_0(matOfKeyPoint.nativeObj, matOfKeyPoint2.nativeObj, matOfInt.nativeObj, matOfInt2.nativeObj, matOfDMatch.nativeObj);
    }
}
