package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.Virtual;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class MinProblemSolver extends Algorithm {
    @opencv_core.Ptr
    public native Function getFunction();

    @ByVal
    public native TermCriteria getTermCriteria();

    public native double minimize(@ByVal GpuMat gpuMat);

    public native double minimize(@ByVal Mat mat);

    public native double minimize(@ByVal UMat uMat);

    public native void setFunction(@opencv_core.Ptr Function function);

    public native void setTermCriteria(@ByRef @Const TermCriteria termCriteria);

    static {
        Loader.load();
    }

    public MinProblemSolver(Pointer pointer) {
        super(pointer);
    }

    public static class Function extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        @Virtual(true)
        @Const({false, false, true})
        public native double calc(@Const DoublePointer doublePointer);

        @Virtual(true)
        @Const({false, false, true})
        public native int getDims();

        @Virtual
        public native void getGradient(@Const DoublePointer doublePointer, DoublePointer doublePointer2);

        @Virtual
        @Const({false, false, true})
        public native double getGradientEps();

        static {
            Loader.load();
        }

        public Function() {
            super((Pointer) null);
            allocate();
        }

        public Function(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Function(Pointer pointer) {
            super(pointer);
        }

        public Function position(long j) {
            return (Function) super.position(j);
        }

        public Function getPointer(long j) {
            return new Function((Pointer) this).position(this.position + j);
        }
    }
}
