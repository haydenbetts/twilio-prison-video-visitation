package org.bytedeco.opencv.opencv_dnn;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.AsyncArray;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.MatVectorVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class Net extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @ByVal
    public static native Net readFromModelOptimizer(@opencv_core.Str String str, @opencv_core.Str String str2);

    @ByVal
    public static native Net readFromModelOptimizer(@Cast({"const uchar*"}) ByteBuffer byteBuffer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) ByteBuffer byteBuffer2, @Cast({"size_t"}) long j2);

    @ByVal
    public static native Net readFromModelOptimizer(@Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer, @Cast({"uchar*"}) @StdVector ByteBuffer byteBuffer2);

    @ByVal
    public static native Net readFromModelOptimizer(@Cast({"const uchar*"}) BytePointer bytePointer, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j2);

    @ByVal
    public static native Net readFromModelOptimizer(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @ByVal
    public static native Net readFromModelOptimizer(@Cast({"const uchar*"}) byte[] bArr, @Cast({"size_t"}) long j, @Cast({"const uchar*"}) byte[] bArr2, @Cast({"size_t"}) long j2);

    @ByVal
    public static native Net readFromModelOptimizer(@Cast({"uchar*"}) @StdVector byte[] bArr, @Cast({"uchar*"}) @StdVector byte[] bArr2);

    public native int addLayer(@opencv_core.Str String str, @opencv_core.Str String str2, @ByRef LayerParams layerParams);

    public native int addLayer(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByRef LayerParams layerParams);

    public native int addLayerToPrev(@opencv_core.Str String str, @opencv_core.Str String str2, @ByRef LayerParams layerParams);

    public native int addLayerToPrev(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByRef LayerParams layerParams);

    public native void connect(int i, int i2, int i3, int i4);

    public native void connect(@opencv_core.Str String str, @opencv_core.Str String str2);

    public native void connect(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Str
    public native BytePointer dump();

    public native void dumpToFile(@opencv_core.Str String str);

    public native void dumpToFile(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean empty();

    public native void enableFusion(@Cast({"bool"}) boolean z);

    @ByVal
    public native Mat forward();

    @ByVal
    public native Mat forward(@opencv_core.Str String str);

    @ByVal
    public native Mat forward(@opencv_core.Str BytePointer bytePointer);

    public native void forward(@ByVal GpuMatVector gpuMatVector);

    public native void forward(@ByVal GpuMatVector gpuMatVector, @opencv_core.Str String str);

    public native void forward(@ByVal GpuMatVector gpuMatVector, @opencv_core.Str BytePointer bytePointer);

    public native void forward(@ByVal GpuMatVector gpuMatVector, @ByRef @Const StringVector stringVector);

    public native void forward(@ByVal MatVector matVector);

    public native void forward(@ByVal MatVector matVector, @opencv_core.Str String str);

    public native void forward(@ByVal MatVector matVector, @opencv_core.Str BytePointer bytePointer);

    public native void forward(@ByVal MatVector matVector, @ByRef @Const StringVector stringVector);

    public native void forward(@ByVal UMatVector uMatVector);

    public native void forward(@ByVal UMatVector uMatVector, @opencv_core.Str String str);

    public native void forward(@ByVal UMatVector uMatVector, @opencv_core.Str BytePointer bytePointer);

    public native void forward(@ByVal UMatVector uMatVector, @ByRef @Const StringVector stringVector);

    @Name({"forward"})
    public native void forwardAndRetrieve(@ByRef MatVectorVector matVectorVector, @ByRef @Const StringVector stringVector);

    @ByVal
    public native AsyncArray forwardAsync();

    @ByVal
    public native AsyncArray forwardAsync(@opencv_core.Str String str);

    @ByVal
    public native AsyncArray forwardAsync(@opencv_core.Str BytePointer bytePointer);

    @Cast({"int64"})
    public native long getFLOPS(int i, @ByRef @Const @StdVector IntPointer intPointer);

    @Cast({"int64"})
    public native long getFLOPS(int i, @ByRef @Const MatShapeVector matShapeVector);

    @Cast({"int64"})
    public native long getFLOPS(@ByRef @Const @StdVector IntPointer intPointer);

    @Cast({"int64"})
    public native long getFLOPS(@ByRef @Const MatShapeVector matShapeVector);

    @opencv_core.Ptr
    public native Layer getLayer(@Cast({"cv::dnn::Net::LayerId*"}) @ByVal DictValue dictValue);

    public native int getLayerId(@opencv_core.Str String str);

    public native int getLayerId(@opencv_core.Str BytePointer bytePointer);

    @ByVal
    public native StringVector getLayerNames();

    public native void getLayerShapes(@ByRef @Const @StdVector IntPointer intPointer, int i, @ByRef MatShapeVector matShapeVector, @ByRef MatShapeVector matShapeVector2);

    public native void getLayerShapes(@ByRef @Const MatShapeVector matShapeVector, int i, @ByRef MatShapeVector matShapeVector2, @ByRef MatShapeVector matShapeVector3);

    public native void getLayerTypes(@ByRef StringVector stringVector);

    public native int getLayersCount(@opencv_core.Str String str);

    public native int getLayersCount(@opencv_core.Str BytePointer bytePointer);

    public native void getLayersShapes(@ByRef @Const @StdVector IntPointer intPointer, @StdVector IntBuffer intBuffer, @ByRef MatShapeVectorVector matShapeVectorVector, @ByRef MatShapeVectorVector matShapeVectorVector2);

    public native void getLayersShapes(@ByRef @Const @StdVector IntPointer intPointer, @StdVector IntPointer intPointer2, @ByRef MatShapeVectorVector matShapeVectorVector, @ByRef MatShapeVectorVector matShapeVectorVector2);

    public native void getLayersShapes(@ByRef @Const @StdVector IntPointer intPointer, @StdVector int[] iArr, @ByRef MatShapeVectorVector matShapeVectorVector, @ByRef MatShapeVectorVector matShapeVectorVector2);

    public native void getLayersShapes(@ByRef @Const MatShapeVector matShapeVector, @StdVector IntBuffer intBuffer, @ByRef MatShapeVectorVector matShapeVectorVector, @ByRef MatShapeVectorVector matShapeVectorVector2);

    public native void getLayersShapes(@ByRef @Const MatShapeVector matShapeVector, @StdVector IntPointer intPointer, @ByRef MatShapeVectorVector matShapeVectorVector, @ByRef MatShapeVectorVector matShapeVectorVector2);

    public native void getLayersShapes(@ByRef @Const MatShapeVector matShapeVector, @StdVector int[] iArr, @ByRef MatShapeVectorVector matShapeVectorVector, @ByRef MatShapeVectorVector matShapeVectorVector2);

    public native void getMemoryConsumption(int i, @ByRef @Const @StdVector IntPointer intPointer, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(int i, @ByRef @Const MatShapeVector matShapeVector, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(@ByRef @Const @StdVector IntPointer intPointer, @StdVector IntBuffer intBuffer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(@ByRef @Const @StdVector IntPointer intPointer, @StdVector IntPointer intPointer2, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(@ByRef @Const @StdVector IntPointer intPointer, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(@ByRef @Const @StdVector IntPointer intPointer, @StdVector int[] iArr, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(@ByRef @Const MatShapeVector matShapeVector, @StdVector IntBuffer intBuffer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(@ByRef @Const MatShapeVector matShapeVector, @StdVector IntPointer intPointer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(@ByRef @Const MatShapeVector matShapeVector, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer2);

    public native void getMemoryConsumption(@ByRef @Const MatShapeVector matShapeVector, @StdVector int[] iArr, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer, @Cast({"size_t*"}) @StdVector SizeTPointer sizeTPointer2);

    @ByVal
    public native Mat getParam(@Cast({"cv::dnn::Net::LayerId*"}) @ByVal DictValue dictValue);

    @ByVal
    public native Mat getParam(@Cast({"cv::dnn::Net::LayerId*"}) @ByVal DictValue dictValue, int i);

    @Cast({"int64"})
    public native long getPerfProfile(@StdVector DoubleBuffer doubleBuffer);

    @Cast({"int64"})
    public native long getPerfProfile(@StdVector DoublePointer doublePointer);

    @Cast({"int64"})
    public native long getPerfProfile(@StdVector double[] dArr);

    @StdVector
    public native IntPointer getUnconnectedOutLayers();

    @ByVal
    public native StringVector getUnconnectedOutLayersNames();

    public native void setHalideScheduler(@opencv_core.Str String str);

    public native void setHalideScheduler(@opencv_core.Str BytePointer bytePointer);

    public native void setInput(@ByVal GpuMat gpuMat);

    public native void setInput(@ByVal GpuMat gpuMat, @opencv_core.Str String str, double d, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    public native void setInput(@ByVal GpuMat gpuMat, @opencv_core.Str BytePointer bytePointer, double d, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    public native void setInput(@ByVal Mat mat);

    public native void setInput(@ByVal Mat mat, @opencv_core.Str String str, double d, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    public native void setInput(@ByVal Mat mat, @opencv_core.Str BytePointer bytePointer, double d, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    public native void setInput(@ByVal UMat uMat);

    public native void setInput(@ByVal UMat uMat, @opencv_core.Str String str, double d, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    public native void setInput(@ByVal UMat uMat, @opencv_core.Str BytePointer bytePointer, double d, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    public native void setInputShape(@opencv_core.Str String str, @ByRef @Const @StdVector IntPointer intPointer);

    public native void setInputShape(@opencv_core.Str BytePointer bytePointer, @ByRef @Const @StdVector IntPointer intPointer);

    public native void setInputsNames(@ByRef @Const StringVector stringVector);

    public native void setParam(@Cast({"cv::dnn::Net::LayerId*"}) @ByVal DictValue dictValue, int i, @ByRef @Const Mat mat);

    public native void setPreferableBackend(int i);

    public native void setPreferableTarget(int i);

    static {
        Loader.load();
    }

    public Net(Pointer pointer) {
        super(pointer);
    }

    public Net(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Net position(long j) {
        return (Net) super.position(j);
    }

    public Net getPointer(long j) {
        return new Net((Pointer) this).position(this.position + j);
    }

    public Net() {
        super((Pointer) null);
        allocate();
    }
}
