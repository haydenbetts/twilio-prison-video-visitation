package org.bytedeco.opencv.opencv_bioinspired;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_bioinspired;

@Namespace("cv::bioinspired")
@Properties(inherit = {opencv_bioinspired.class})
public class RetinaParameters extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @ByRef
    public native IplMagnoParameters IplMagno();

    public native RetinaParameters IplMagno(IplMagnoParameters iplMagnoParameters);

    @ByRef
    public native OPLandIplParvoParameters OPLandIplParvo();

    public native RetinaParameters OPLandIplParvo(OPLandIplParvoParameters oPLandIplParvoParameters);

    static {
        Loader.load();
    }

    public RetinaParameters() {
        super((Pointer) null);
        allocate();
    }

    public RetinaParameters(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public RetinaParameters(Pointer pointer) {
        super(pointer);
    }

    public RetinaParameters position(long j) {
        return (RetinaParameters) super.position(j);
    }

    public RetinaParameters getPointer(long j) {
        return new RetinaParameters((Pointer) this).position(this.position + j);
    }

    @NoOffset
    public static class OPLandIplParvoParameters extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native OPLandIplParvoParameters colorMode(boolean z);

        @Cast({"bool"})
        public native boolean colorMode();

        public native float ganglionCellsSensitivity();

        public native OPLandIplParvoParameters ganglionCellsSensitivity(float f);

        public native float hcellsSpatialConstant();

        public native OPLandIplParvoParameters hcellsSpatialConstant(float f);

        public native float hcellsTemporalConstant();

        public native OPLandIplParvoParameters hcellsTemporalConstant(float f);

        public native float horizontalCellsGain();

        public native OPLandIplParvoParameters horizontalCellsGain(float f);

        public native OPLandIplParvoParameters normaliseOutput(boolean z);

        @Cast({"bool"})
        public native boolean normaliseOutput();

        public native float photoreceptorsLocalAdaptationSensitivity();

        public native OPLandIplParvoParameters photoreceptorsLocalAdaptationSensitivity(float f);

        public native float photoreceptorsSpatialConstant();

        public native OPLandIplParvoParameters photoreceptorsSpatialConstant(float f);

        public native float photoreceptorsTemporalConstant();

        public native OPLandIplParvoParameters photoreceptorsTemporalConstant(float f);

        static {
            Loader.load();
        }

        public OPLandIplParvoParameters(Pointer pointer) {
            super(pointer);
        }

        public OPLandIplParvoParameters(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public OPLandIplParvoParameters position(long j) {
            return (OPLandIplParvoParameters) super.position(j);
        }

        public OPLandIplParvoParameters getPointer(long j) {
            return new OPLandIplParvoParameters((Pointer) this).position(this.position + j);
        }

        public OPLandIplParvoParameters() {
            super((Pointer) null);
            allocate();
        }
    }

    @NoOffset
    public static class IplMagnoParameters extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native float V0CompressionParameter();

        public native IplMagnoParameters V0CompressionParameter(float f);

        public native float amacrinCellsTemporalCutFrequency();

        public native IplMagnoParameters amacrinCellsTemporalCutFrequency(float f);

        public native float localAdaptintegration_k();

        public native IplMagnoParameters localAdaptintegration_k(float f);

        public native float localAdaptintegration_tau();

        public native IplMagnoParameters localAdaptintegration_tau(float f);

        public native IplMagnoParameters normaliseOutput(boolean z);

        @Cast({"bool"})
        public native boolean normaliseOutput();

        public native float parasolCells_beta();

        public native IplMagnoParameters parasolCells_beta(float f);

        public native float parasolCells_k();

        public native IplMagnoParameters parasolCells_k(float f);

        public native float parasolCells_tau();

        public native IplMagnoParameters parasolCells_tau(float f);

        static {
            Loader.load();
        }

        public IplMagnoParameters(Pointer pointer) {
            super(pointer);
        }

        public IplMagnoParameters(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public IplMagnoParameters position(long j) {
            return (IplMagnoParameters) super.position(j);
        }

        public IplMagnoParameters getPointer(long j) {
            return new IplMagnoParameters((Pointer) this).position(this.position + j);
        }

        public IplMagnoParameters() {
            super((Pointer) null);
            allocate();
        }
    }
}
