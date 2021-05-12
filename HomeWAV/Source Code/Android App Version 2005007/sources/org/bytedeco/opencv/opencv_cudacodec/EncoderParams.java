package org.bytedeco.opencv.opencv_cudacodec;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudacodec;

@Namespace("cv::cudacodec")
@Properties(inherit = {opencv_cudacodec.class})
@NoOffset
public class EncoderParams extends Pointer {
    private native void allocate();

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocateArray(long j);

    public native int AvgBitrate();

    public native EncoderParams AvgBitrate(int i);

    public native int ClearStat();

    public native EncoderParams ClearStat(int i);

    public native int DIMode();

    public native EncoderParams DIMode(int i);

    public native int DeblockMode();

    public native EncoderParams DeblockMode(int i);

    public native int DisableCabac();

    public native EncoderParams DisableCabac(int i);

    public native int DisableSPSPPS();

    public native EncoderParams DisableSPSPPS(int i);

    public native int DynamicGOP();

    public native EncoderParams DynamicGOP(int i);

    public native int ForceIDR();

    public native EncoderParams ForceIDR(int i);

    public native int ForceIntra();

    public native EncoderParams ForceIntra(int i);

    public native int IDR_Period();

    public native EncoderParams IDR_Period(int i);

    public native int NaluFramingType();

    public native EncoderParams NaluFramingType(int i);

    public native int P_Interval();

    public native EncoderParams P_Interval(int i);

    public native int PeakBitrate();

    public native EncoderParams PeakBitrate(int i);

    public native int Presets();

    public native EncoderParams Presets(int i);

    public native int ProfileLevel();

    public native EncoderParams ProfileLevel(int i);

    public native int QP_Level_InterB();

    public native EncoderParams QP_Level_InterB(int i);

    public native int QP_Level_InterP();

    public native EncoderParams QP_Level_InterP(int i);

    public native int QP_Level_Intra();

    public native EncoderParams QP_Level_Intra(int i);

    public native int RCType();

    public native EncoderParams RCType(int i);

    public native void load(@opencv_core.Str String str);

    public native void load(@opencv_core.Str BytePointer bytePointer);

    public native void save(@opencv_core.Str String str);

    public native void save(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public EncoderParams(Pointer pointer) {
        super(pointer);
    }

    public EncoderParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public EncoderParams position(long j) {
        return (EncoderParams) super.position(j);
    }

    public EncoderParams getPointer(long j) {
        return new EncoderParams((Pointer) this).position(this.position + j);
    }

    public EncoderParams() {
        super((Pointer) null);
        allocate();
    }

    public EncoderParams(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public EncoderParams(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
