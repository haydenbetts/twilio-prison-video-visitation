package org.bytedeco.opencv.opencv_objdetect;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_objdetect;

@Namespace("cv")
@Properties(inherit = {opencv_objdetect.class})
@NoOffset
public class QRCodeDetector extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @StdString
    public native String decode(@ByVal UMat uMat, @ByVal UMat uMat2);

    @StdString
    public native String decode(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @StdString
    public native BytePointer decode(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @StdString
    public native BytePointer decode(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @StdString
    public native BytePointer decode(@ByVal Mat mat, @ByVal Mat mat2);

    @StdString
    public native BytePointer decode(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef StringVector stringVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal Mat mat, @ByVal Mat mat2, @ByRef StringVector stringVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal Mat mat, @ByVal Mat mat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal Mat mat, @ByVal Mat mat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal Mat mat, @ByVal Mat mat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef StringVector stringVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector);

    @Cast({"bool"})
    public native boolean decodeMulti(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    @Cast({"bool"})
    public native boolean detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean detect(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean detect(@ByVal UMat uMat, @ByVal UMat uMat2);

    @StdString
    public native String detectAndDecode(@ByVal UMat uMat);

    @StdString
    public native String detectAndDecode(@ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @StdString
    public native BytePointer detectAndDecode(@ByVal GpuMat gpuMat);

    @StdString
    public native BytePointer detectAndDecode(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @StdString
    public native BytePointer detectAndDecode(@ByVal Mat mat);

    @StdString
    public native BytePointer detectAndDecode(@ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal GpuMat gpuMat, @ByRef StringVector stringVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal GpuMat gpuMat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal GpuMat gpuMat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal GpuMat gpuMat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal Mat mat, @ByRef StringVector stringVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal Mat mat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal Mat mat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal Mat mat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal UMat uMat, @ByRef StringVector stringVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal UMat uMat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal UMat uMat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") MatVector matVector);

    @Cast({"bool"})
    public native boolean detectAndDecodeMulti(@ByVal UMat uMat, @ByRef StringVector stringVector, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, @ByVal(nullValue = "cv::OutputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    @Cast({"bool"})
    public native boolean detectMulti(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean detectMulti(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean detectMulti(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void setEpsX(double d);

    public native void setEpsY(double d);

    static {
        Loader.load();
    }

    public QRCodeDetector(Pointer pointer) {
        super(pointer);
    }

    public QRCodeDetector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public QRCodeDetector position(long j) {
        return (QRCodeDetector) super.position(j);
    }

    public QRCodeDetector getPointer(long j) {
        return new QRCodeDetector((Pointer) this).position(this.position + j);
    }

    public QRCodeDetector() {
        super((Pointer) null);
        allocate();
    }
}
