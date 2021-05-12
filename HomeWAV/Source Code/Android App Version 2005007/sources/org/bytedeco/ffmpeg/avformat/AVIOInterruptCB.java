package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVIOInterruptCB extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native Callback_Pointer callback();

    public native AVIOInterruptCB callback(Callback_Pointer callback_Pointer);

    public native AVIOInterruptCB opaque(Pointer pointer);

    public native Pointer opaque();

    static {
        Loader.load();
    }

    public AVIOInterruptCB() {
        super((Pointer) null);
        allocate();
    }

    public AVIOInterruptCB(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVIOInterruptCB(Pointer pointer) {
        super(pointer);
    }

    public AVIOInterruptCB position(long j) {
        return (AVIOInterruptCB) super.position(j);
    }

    public AVIOInterruptCB getPointer(long j) {
        return new AVIOInterruptCB((Pointer) this).position(this.position + j);
    }

    public static class Callback_Pointer extends FunctionPointer {
        private native void allocate();

        public native int call(Pointer pointer);

        static {
            Loader.load();
        }

        public Callback_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Callback_Pointer() {
            allocate();
        }
    }
}
