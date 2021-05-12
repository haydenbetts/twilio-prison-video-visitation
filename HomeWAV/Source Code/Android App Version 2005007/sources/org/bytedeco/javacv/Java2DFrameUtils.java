package org.bytedeco.javacv;

import java.awt.image.BufferedImage;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;

public class Java2DFrameUtils {
    private static Java2DFrameConverter biConv = new Java2DFrameConverter();
    private static OpenCVFrameConverter.ToIplImage iplConv = new OpenCVFrameConverter.ToIplImage();
    private static OpenCVFrameConverter.ToMat matConv = new OpenCVFrameConverter.ToMat();

    public static BufferedImage deepCopy(BufferedImage bufferedImage) {
        return Java2DFrameConverter.cloneBufferedImage(bufferedImage);
    }

    public static synchronized BufferedImage toBufferedImage(IplImage iplImage) {
        BufferedImage deepCopy;
        synchronized (Java2DFrameUtils.class) {
            deepCopy = deepCopy(biConv.getBufferedImage(iplConv.convert(iplImage).clone()));
        }
        return deepCopy;
    }

    public static synchronized BufferedImage toBufferedImage(Mat mat) {
        BufferedImage deepCopy;
        synchronized (Java2DFrameUtils.class) {
            deepCopy = deepCopy(biConv.getBufferedImage(matConv.convert(mat).clone()));
        }
        return deepCopy;
    }

    public static synchronized BufferedImage toBufferedImage(Frame frame) {
        BufferedImage deepCopy;
        synchronized (Java2DFrameUtils.class) {
            deepCopy = deepCopy(biConv.getBufferedImage(frame.clone()));
        }
        return deepCopy;
    }

    public static synchronized IplImage toIplImage(Mat mat) {
        IplImage clone;
        synchronized (Java2DFrameUtils.class) {
            clone = iplConv.convertToIplImage(matConv.convert(mat)).clone();
        }
        return clone;
    }

    public static synchronized IplImage toIplImage(Frame frame) {
        IplImage clone;
        synchronized (Java2DFrameUtils.class) {
            clone = iplConv.convertToIplImage(frame).clone();
        }
        return clone;
    }

    public static synchronized IplImage toIplImage(BufferedImage bufferedImage) {
        IplImage clone;
        synchronized (Java2DFrameUtils.class) {
            clone = iplConv.convertToIplImage(biConv.convert(bufferedImage)).clone();
        }
        return clone;
    }

    public static synchronized Mat toMat(IplImage iplImage) {
        Mat convertToMat;
        synchronized (Java2DFrameUtils.class) {
            convertToMat = matConv.convertToMat(iplConv.convert(iplImage).clone());
        }
        return convertToMat;
    }

    public static synchronized Mat toMat(Frame frame) {
        Mat clone;
        synchronized (Java2DFrameUtils.class) {
            clone = matConv.convertToMat(frame).clone();
        }
        return clone;
    }

    public static synchronized Mat toMat(BufferedImage bufferedImage) {
        Mat clone;
        synchronized (Java2DFrameUtils.class) {
            clone = matConv.convertToMat(biConv.convert(bufferedImage)).clone();
        }
        return clone;
    }

    public static synchronized Frame toFrame(IplImage iplImage) {
        Frame clone;
        synchronized (Java2DFrameUtils.class) {
            clone = iplConv.convert(iplImage).clone();
        }
        return clone;
    }

    public static synchronized Frame toFrame(Mat mat) {
        Frame clone;
        synchronized (Java2DFrameUtils.class) {
            clone = matConv.convert(mat).clone();
        }
        return clone;
    }

    public static synchronized Frame toFrame(BufferedImage bufferedImage) {
        Frame clone;
        synchronized (Java2DFrameUtils.class) {
            clone = biConv.convert(bufferedImage).clone();
        }
        return clone;
    }
}
