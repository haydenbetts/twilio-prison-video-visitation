package org.bytedeco.opencv.helper;

import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.IplImage;

public class opencv_imgcodecs extends org.bytedeco.opencv.presets.opencv_imgcodecs {
    public static IplImage cvLoadImage(String str) {
        return cvLoadImage(str, 1);
    }

    public static IplImage cvLoadImage(String str, int i) {
        return new IplImage(opencv_core.cvClone(opencv_core.cvIplImage(org.bytedeco.opencv.global.opencv_imgcodecs.imread(str, i))));
    }

    public static int cvSaveImage(String str, CvArr cvArr) {
        return cvSaveImage(str, cvArr, (int[]) null);
    }

    public static int cvSaveImage(String str, CvArr cvArr, int[] iArr) {
        return org.bytedeco.opencv.global.opencv_imgcodecs.imwrite(str, opencv_core.cvarrToMat(cvArr), iArr) ? 1 : 0;
    }

    public static IplImage cvLoadImageBGRA(String str) {
        IplImage cvLoadImage = cvLoadImage(str, 1);
        if (cvLoadImage == null) {
            return null;
        }
        IplImage cvCreateImage = opencv_core.cvCreateImage(opencv_core.cvGetSize(cvLoadImage), cvLoadImage.depth(), 4);
        opencv_imgproc.cvCvtColor(cvLoadImage, cvCreateImage, 0);
        opencv_core.cvReleaseImage(cvLoadImage);
        return cvCreateImage;
    }

    public static IplImage cvLoadImageRGBA(String str) {
        IplImage cvLoadImage = cvLoadImage(str, 1);
        if (cvLoadImage == null) {
            return null;
        }
        IplImage cvCreateImage = opencv_core.cvCreateImage(opencv_core.cvGetSize(cvLoadImage), cvLoadImage.depth(), 4);
        opencv_imgproc.cvCvtColor(cvLoadImage, cvCreateImage, 2);
        opencv_core.cvReleaseImage(cvLoadImage);
        return cvCreateImage;
    }
}
