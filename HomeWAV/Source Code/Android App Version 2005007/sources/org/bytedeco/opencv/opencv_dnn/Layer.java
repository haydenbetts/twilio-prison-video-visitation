package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class Layer extends Algorithm {
    private native void allocate();

    private native void allocate(@ByRef @Const LayerParams layerParams);

    private native void allocateArray(long j);

    public native void applyHalideScheduler(@opencv_core.Ptr BackendNode backendNode, @ByRef @Const MatPointerVector matPointerVector, @ByRef @Const MatVector matVector, int i);

    @ByRef
    public native MatVector blobs();

    public native Layer blobs(MatVector matVector);

    @Deprecated
    @ByVal
    public native MatVector finalize(@ByRef @Const MatVector matVector);

    public native void finalize(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2);

    public native void finalize(@ByVal MatVector matVector, @ByVal MatVector matVector2);

    public native void finalize(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2);

    @Deprecated
    public native void finalize(@ByRef @Const MatPointerVector matPointerVector, @ByRef MatVector matVector);

    public native void forward(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3);

    public native void forward(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3);

    public native void forward(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3);

    @Deprecated
    public native void forward(@ByRef MatPointerVector matPointerVector, @ByRef MatVector matVector, @ByRef MatVector matVector2);

    public native void forward_fallback(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMatVector gpuMatVector3);

    public native void forward_fallback(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal MatVector matVector3);

    public native void forward_fallback(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMatVector uMatVector3);

    @Cast({"int64"})
    public native long getFLOPS(@ByRef @Const MatShapeVector matShapeVector, @ByRef @Const MatShapeVector matShapeVector2);

    @Cast({"bool"})
    public native boolean getMemoryShapes(@ByRef @Const MatShapeVector matShapeVector, int i, @ByRef MatShapeVector matShapeVector2, @ByRef MatShapeVector matShapeVector3);

    public native void getScaleShift(@ByRef Mat mat, @ByRef Mat mat2);

    public native int inputNameToIndex(@opencv_core.Str String str);

    public native int inputNameToIndex(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Str
    public native BytePointer name();

    public native Layer name(BytePointer bytePointer);

    public native int outputNameToIndex(@opencv_core.Str String str);

    public native int outputNameToIndex(@opencv_core.Str BytePointer bytePointer);

    public native int preferableTarget();

    public native Layer preferableTarget(int i);

    @Deprecated
    public native void run(@ByRef @Const MatVector matVector, @ByRef MatVector matVector2, @ByRef MatVector matVector3);

    @Cast({"bool"})
    public native boolean setActivation(@opencv_core.Ptr ActivationLayer activationLayer);

    public native void setParamsFrom(@ByRef @Const LayerParams layerParams);

    @Cast({"bool"})
    public native boolean supportBackend(int i);

    @opencv_core.Ptr
    public native BackendNode tryAttach(@opencv_core.Ptr BackendNode backendNode);

    @Cast({"bool"})
    public native boolean tryFuse(@opencv_core.Ptr Layer layer);

    @opencv_core.Str
    public native BytePointer type();

    public native Layer type(BytePointer bytePointer);

    public native void unsetAttached();

    static {
        Loader.load();
    }

    public Layer(Pointer pointer) {
        super(pointer);
    }

    public Layer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Layer position(long j) {
        return (Layer) super.position(j);
    }

    public Layer getPointer(long j) {
        return new Layer((Pointer) this).position(this.position + j);
    }

    public Layer() {
        super((Pointer) null);
        allocate();
    }

    public Layer(@ByRef @Const LayerParams layerParams) {
        super((Pointer) null);
        allocate(layerParams);
    }
}
