package org.bytedeco.opencv.opencv_face;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
@NoOffset
public class FaceRecognizer extends Algorithm {
    @Cast({"bool"})
    public native boolean empty();

    @opencv_core.Str
    public native BytePointer getLabelInfo(int i);

    @StdVector
    public native IntBuffer getLabelsByString(@opencv_core.Str String str);

    @StdVector
    public native IntPointer getLabelsByString(@opencv_core.Str BytePointer bytePointer);

    public native double getThreshold();

    public native void predict(@ByVal GpuMat gpuMat, @ByRef IntBuffer intBuffer, @ByRef DoubleBuffer doubleBuffer);

    public native void predict(@ByVal GpuMat gpuMat, @ByRef IntPointer intPointer, @ByRef DoublePointer doublePointer);

    public native void predict(@ByVal GpuMat gpuMat, @ByRef int[] iArr, @ByRef double[] dArr);

    public native void predict(@ByVal Mat mat, @ByRef IntBuffer intBuffer, @ByRef DoubleBuffer doubleBuffer);

    public native void predict(@ByVal Mat mat, @ByRef IntPointer intPointer, @ByRef DoublePointer doublePointer);

    public native void predict(@ByVal Mat mat, @ByRef int[] iArr, @ByRef double[] dArr);

    public native void predict(@ByVal UMat uMat, @ByRef IntBuffer intBuffer, @ByRef DoubleBuffer doubleBuffer);

    public native void predict(@ByVal UMat uMat, @ByRef IntPointer intPointer, @ByRef DoublePointer doublePointer);

    public native void predict(@ByVal UMat uMat, @ByRef int[] iArr, @ByRef double[] dArr);

    @Name({"predict"})
    public native void predict_collect(@ByVal GpuMat gpuMat, @opencv_core.Ptr PredictCollector predictCollector);

    @Name({"predict"})
    public native void predict_collect(@ByVal Mat mat, @opencv_core.Ptr PredictCollector predictCollector);

    @Name({"predict"})
    public native void predict_collect(@ByVal UMat uMat, @opencv_core.Ptr PredictCollector predictCollector);

    @Name({"predict"})
    public native int predict_label(@ByVal GpuMat gpuMat);

    @Name({"predict"})
    public native int predict_label(@ByVal Mat mat);

    @Name({"predict"})
    public native int predict_label(@ByVal UMat uMat);

    public native void read(@opencv_core.Str String str);

    public native void read(@opencv_core.Str BytePointer bytePointer);

    public native void read(@ByRef @Const FileNode fileNode);

    public native void setLabelInfo(int i, @opencv_core.Str String str);

    public native void setLabelInfo(int i, @opencv_core.Str BytePointer bytePointer);

    public native void setThreshold(double d);

    public native void train(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    public native void train(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    public native void train(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    public native void train(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    public native void train(@ByVal MatVector matVector, @ByVal Mat mat);

    public native void train(@ByVal MatVector matVector, @ByVal UMat uMat);

    public native void train(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    public native void train(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    public native void train(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    public native void update(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    public native void update(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    public native void update(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    public native void update(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    public native void update(@ByVal MatVector matVector, @ByVal Mat mat);

    public native void update(@ByVal MatVector matVector, @ByVal UMat uMat);

    public native void update(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    public native void update(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    public native void update(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    public native void write(@opencv_core.Str String str);

    public native void write(@opencv_core.Str BytePointer bytePointer);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public FaceRecognizer(Pointer pointer) {
        super(pointer);
    }
}
