package org.bytedeco.opencv.opencv_xfeatures2d;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
public class PCTSignatures extends Algorithm {
    public static final int GAUSSIAN = 1;
    public static final int HEURISTIC = 2;
    public static final int L0_25 = 0;
    public static final int L0_5 = 1;
    public static final int L1 = 2;
    public static final int L2 = 3;
    public static final int L2SQUARED = 4;
    public static final int L5 = 5;
    public static final int L_INFINITY = 6;
    public static final int MINUS = 0;
    public static final int NORMAL = 2;
    public static final int REGULAR = 1;
    public static final int UNIFORM = 0;

    @opencv_core.Ptr
    public static native PCTSignatures create();

    @opencv_core.Ptr
    public static native PCTSignatures create(int i, int i2, int i3);

    @opencv_core.Ptr
    public static native PCTSignatures create(@ByRef @Const Point2fVector point2fVector, int i);

    @opencv_core.Ptr
    public static native PCTSignatures create(@ByRef @Const Point2fVector point2fVector, @StdVector IntBuffer intBuffer);

    @opencv_core.Ptr
    public static native PCTSignatures create(@ByRef @Const Point2fVector point2fVector, @StdVector IntPointer intPointer);

    @opencv_core.Ptr
    public static native PCTSignatures create(@ByRef @Const Point2fVector point2fVector, @StdVector int[] iArr);

    public static native void drawSignature(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public static native void drawSignature(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, float f, int i);

    public static native void drawSignature(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public static native void drawSignature(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, float f, int i);

    public static native void drawSignature(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public static native void drawSignature(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, float f, int i);

    public static native void generateInitPoints(@ByRef Point2fVector point2fVector, int i, int i2);

    public native void computeSignature(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void computeSignature(@ByVal Mat mat, @ByVal Mat mat2);

    public native void computeSignature(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void computeSignatures(@ByRef @Const MatVector matVector, @ByRef MatVector matVector2);

    public native int getClusterMinSize();

    public native int getDistanceFunction();

    public native float getDropThreshold();

    public native int getGrayscaleBits();

    public native int getInitSeedCount();

    @StdVector
    public native IntPointer getInitSeedIndexes();

    public native int getIterationCount();

    public native float getJoiningDistance();

    public native int getMaxClustersCount();

    public native int getSampleCount();

    @ByVal
    public native Point2fVector getSamplingPoints();

    public native float getWeightA();

    public native float getWeightB();

    public native float getWeightContrast();

    public native float getWeightEntropy();

    public native float getWeightL();

    public native float getWeightX();

    public native float getWeightY();

    public native int getWindowRadius();

    public native void setClusterMinSize(int i);

    public native void setDistanceFunction(int i);

    public native void setDropThreshold(float f);

    public native void setGrayscaleBits(int i);

    public native void setInitSeedIndexes(@StdVector IntBuffer intBuffer);

    public native void setInitSeedIndexes(@StdVector IntPointer intPointer);

    public native void setInitSeedIndexes(@StdVector int[] iArr);

    public native void setIterationCount(int i);

    public native void setJoiningDistance(float f);

    public native void setMaxClustersCount(int i);

    public native void setSamplingPoints(@ByVal Point2fVector point2fVector);

    public native void setTranslation(int i, float f);

    public native void setTranslations(@StdVector FloatBuffer floatBuffer);

    public native void setTranslations(@StdVector FloatPointer floatPointer);

    public native void setTranslations(@StdVector float[] fArr);

    public native void setWeight(int i, float f);

    public native void setWeightA(float f);

    public native void setWeightB(float f);

    public native void setWeightContrast(float f);

    public native void setWeightEntropy(float f);

    public native void setWeightL(float f);

    public native void setWeightX(float f);

    public native void setWeightY(float f);

    public native void setWeights(@StdVector FloatBuffer floatBuffer);

    public native void setWeights(@StdVector FloatPointer floatPointer);

    public native void setWeights(@StdVector float[] fArr);

    public native void setWindowRadius(int i);

    static {
        Loader.load();
    }

    public PCTSignatures(Pointer pointer) {
        super(pointer);
    }
}
