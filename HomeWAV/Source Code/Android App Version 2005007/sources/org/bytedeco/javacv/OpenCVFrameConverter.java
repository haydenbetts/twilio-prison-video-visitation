package org.bytedeco.javacv;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;

public abstract class OpenCVFrameConverter<F> extends FrameConverter<F> {
    IplImage img;
    Mat mat;
    org.opencv.core.Mat orgOpenCvCoreMat;

    public static int getFrameDepth(int i) {
        if (i == -2147483640) {
            return -8;
        }
        if (i == -2147483632) {
            return -16;
        }
        if (i == -2147483616) {
            return -32;
        }
        if (i != 8) {
            if (i != 16) {
                if (i != 32) {
                    if (i != 64) {
                        switch (i) {
                            case 0:
                                break;
                            case 1:
                                return -8;
                            case 2:
                                break;
                            case 3:
                                return -16;
                            case 4:
                                return -32;
                            case 5:
                                break;
                            case 6:
                                break;
                            default:
                                return -1;
                        }
                    }
                    return 64;
                }
                return 32;
            }
            return 16;
        }
        return 8;
    }

    public static int getIplImageDepth(int i) {
        if (i == -32) {
            return opencv_core.IPL_DEPTH_32S;
        }
        if (i == -16) {
            return opencv_core.IPL_DEPTH_16S;
        }
        if (i == -8) {
            return opencv_core.IPL_DEPTH_8S;
        }
        int i2 = 8;
        if (i != 8) {
            i2 = 16;
            if (i != 16) {
                i2 = 32;
                if (i != 32) {
                    i2 = 64;
                    if (i != 64) {
                        return -1;
                    }
                }
            }
        }
        return i2;
    }

    public static int getMatDepth(int i) {
        if (i == -32) {
            return 4;
        }
        if (i == -16) {
            return 3;
        }
        if (i == -8) {
            return 1;
        }
        if (i == 8) {
            return 0;
        }
        if (i == 16) {
            return 2;
        }
        if (i != 32) {
            return i != 64 ? -1 : 6;
        }
        return 5;
    }

    static {
        Loader.load(opencv_core.class);
    }

    public static class ToIplImage extends OpenCVFrameConverter<IplImage> {
        public Frame convert(IplImage iplImage) {
            return OpenCVFrameConverter.super.convert(iplImage);
        }

        public IplImage convert(Frame frame) {
            return convertToIplImage(frame);
        }
    }

    public static class ToMat extends OpenCVFrameConverter<Mat> {
        public Frame convert(Mat mat) {
            return OpenCVFrameConverter.super.convert(mat);
        }

        public Mat convert(Frame frame) {
            return convertToMat(frame);
        }
    }

    public static class ToOrgOpenCvCoreMat extends OpenCVFrameConverter<org.opencv.core.Mat> {
        public Frame convert(org.opencv.core.Mat mat) {
            return OpenCVFrameConverter.super.convert(mat);
        }

        public org.opencv.core.Mat convert(Frame frame) {
            return convertToOrgOpenCvCoreMat(frame);
        }
    }

    static boolean isEqual(Frame frame, IplImage iplImage) {
        if (iplImage != null && frame != null && frame.image != null && frame.image.length > 0 && frame.imageWidth == iplImage.width() && frame.imageHeight == iplImage.height() && frame.imageChannels == iplImage.nChannels() && getIplImageDepth(frame.imageDepth) == iplImage.depth() && new Pointer(frame.image[0].position(0)).address() == iplImage.imageData().address() && (frame.imageStride * Math.abs(frame.imageDepth)) / 8 == iplImage.widthStep()) {
            return true;
        }
        return false;
    }

    public IplImage convertToIplImage(Frame frame) {
        IplImage iplImage = null;
        if (frame == null || frame.image == null) {
            return null;
        }
        if (frame.opaque instanceof IplImage) {
            return (IplImage) frame.opaque;
        }
        if (!isEqual(frame, this.img)) {
            int iplImageDepth = getIplImageDepth(frame.imageDepth);
            if (iplImageDepth >= 0) {
                iplImage = IplImage.create(frame.imageWidth, frame.imageHeight, iplImageDepth, frame.imageChannels, new Pointer(frame.image[0].position(0))).widthStep((frame.imageStride * Math.abs(frame.imageDepth)) / 8).imageSize((frame.image[0].capacity() * Math.abs(frame.imageDepth)) / 8);
            }
            this.img = iplImage;
        }
        return this.img;
    }

    public Frame convert(IplImage iplImage) {
        if (iplImage == null) {
            return null;
        }
        if (!isEqual(this.frame, iplImage)) {
            this.frame = new Frame();
            this.frame.imageWidth = iplImage.width();
            this.frame.imageHeight = iplImage.height();
            this.frame.imageDepth = getFrameDepth(iplImage.depth());
            this.frame.imageChannels = iplImage.nChannels();
            this.frame.imageStride = (iplImage.widthStep() * 8) / Math.abs(this.frame.imageDepth);
            Frame frame = this.frame;
            frame.image = new Buffer[]{iplImage.createBuffer()};
        }
        this.frame.opaque = iplImage;
        return this.frame;
    }

    static boolean isEqual(Frame frame, Mat mat2) {
        if (mat2 != null && frame != null && frame.image != null && frame.image.length > 0 && frame.imageWidth == mat2.cols() && frame.imageHeight == mat2.rows() && frame.imageChannels == mat2.channels() && getMatDepth(frame.imageDepth) == mat2.depth() && new Pointer(frame.image[0].position(0)).address() == mat2.data().address() && (frame.imageStride * Math.abs(frame.imageDepth)) / 8 == ((int) mat2.step())) {
            return true;
        }
        return false;
    }

    public Mat convertToMat(Frame frame) {
        Mat mat2 = null;
        if (frame == null || frame.image == null) {
            return null;
        }
        if (frame.opaque instanceof Mat) {
            return (Mat) frame.opaque;
        }
        if (!isEqual(frame, this.mat)) {
            int matDepth = getMatDepth(frame.imageDepth);
            if (matDepth >= 0) {
                mat2 = new Mat(frame.imageHeight, frame.imageWidth, opencv_core.CV_MAKETYPE(matDepth, frame.imageChannels), new Pointer(frame.image[0].position(0)), (long) ((frame.imageStride * Math.abs(frame.imageDepth)) / 8));
            }
            this.mat = mat2;
        }
        return this.mat;
    }

    public Frame convert(Mat mat2) {
        if (mat2 == null) {
            return null;
        }
        if (!isEqual(this.frame, mat2)) {
            this.frame = new Frame();
            this.frame.imageWidth = mat2.cols();
            this.frame.imageHeight = mat2.rows();
            this.frame.imageDepth = getFrameDepth(mat2.depth());
            this.frame.imageChannels = mat2.channels();
            this.frame.imageStride = (((int) mat2.step()) * 8) / Math.abs(this.frame.imageDepth);
            Frame frame = this.frame;
            frame.image = new Buffer[]{mat2.createBuffer()};
        }
        this.frame.opaque = mat2;
        return this.frame;
    }

    static boolean isEqual(Frame frame, org.opencv.core.Mat mat2) {
        if (mat2 != null && frame != null && frame.image != null && frame.image.length > 0 && frame.imageWidth == mat2.cols() && frame.imageHeight == mat2.rows() && frame.imageChannels == mat2.channels() && getMatDepth(frame.imageDepth) == mat2.depth() && new Pointer(frame.image[0].position(0)).address() == mat2.dataAddr()) {
            return true;
        }
        return false;
    }

    public org.opencv.core.Mat convertToOrgOpenCvCoreMat(Frame frame) {
        org.opencv.core.Mat mat2 = null;
        if (frame == null || frame.image == null) {
            return null;
        }
        if (frame.opaque instanceof org.opencv.core.Mat) {
            return (org.opencv.core.Mat) frame.opaque;
        }
        if (!isEqual(frame, this.mat)) {
            int matDepth = getMatDepth(frame.imageDepth);
            if (matDepth >= 0) {
                mat2 = new org.opencv.core.Mat(frame.imageHeight, frame.imageWidth, opencv_core.CV_MAKETYPE(matDepth, frame.imageChannels), new BytePointer(new Pointer(frame.image[0].position(0))).capacity((long) ((frame.image[0].capacity() * Math.abs(frame.imageDepth)) / 8)).asByteBuffer(), (long) ((frame.imageStride * Math.abs(frame.imageDepth)) / 8));
            }
            this.orgOpenCvCoreMat = mat2;
        }
        return this.orgOpenCvCoreMat;
    }

    public Frame convert(org.opencv.core.Mat mat2) {
        if (mat2 == null) {
            return null;
        }
        if (!isEqual(this.frame, mat2)) {
            this.frame = new Frame();
            this.frame.imageWidth = mat2.cols();
            this.frame.imageHeight = mat2.rows();
            this.frame.imageDepth = getFrameDepth(mat2.depth());
            this.frame.imageChannels = mat2.channels();
            this.frame.imageStride = (int) mat2.step1();
            ByteBuffer asByteBuffer = new BytePointer(mat2) {
                final /* synthetic */ org.opencv.core.Mat val$mat;

                {
                    this.val$mat = r2;
                    this.address = r2.dataAddr();
                }
            }.capacity(((long) mat2.rows()) * mat2.step1() * mat2.elemSize1()).asByteBuffer();
            switch (mat2.depth()) {
                case 0:
                case 1:
                    Buffer[] bufferArr = {asByteBuffer};
                    this.frame.image = bufferArr;
                    break;
                case 2:
                case 3:
                    Frame frame = this.frame;
                    frame.image = new Buffer[]{asByteBuffer.asShortBuffer()};
                    break;
                case 4:
                    Frame frame2 = this.frame;
                    frame2.image = new Buffer[]{asByteBuffer.asIntBuffer()};
                    break;
                case 5:
                    Frame frame3 = this.frame;
                    frame3.image = new Buffer[]{asByteBuffer.asFloatBuffer()};
                    break;
                case 6:
                    Frame frame4 = this.frame;
                    frame4.image = new Buffer[]{asByteBuffer.asDoubleBuffer()};
                    break;
                default:
                    this.frame.image = null;
                    break;
            }
        }
        this.frame.opaque = mat2;
        return this.frame;
    }
}
