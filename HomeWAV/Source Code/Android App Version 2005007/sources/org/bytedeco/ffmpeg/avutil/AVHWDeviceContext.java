package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVHWDeviceContext extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"free"})
    public native Free_AVHWDeviceContext _free();

    public native AVHWDeviceContext _free(Free_AVHWDeviceContext free_AVHWDeviceContext);

    @Const
    public native AVClass av_class();

    public native AVHWDeviceContext av_class(AVClass aVClass);

    public native AVHWDeviceContext hwctx(Pointer pointer);

    public native Pointer hwctx();

    public native AVHWDeviceContext internal(AVHWDeviceInternal aVHWDeviceInternal);

    public native AVHWDeviceInternal internal();

    @Cast({"AVHWDeviceType"})
    public native int type();

    public native AVHWDeviceContext type(int i);

    public native AVHWDeviceContext user_opaque(Pointer pointer);

    public native Pointer user_opaque();

    static {
        Loader.load();
    }

    public AVHWDeviceContext() {
        super((Pointer) null);
        allocate();
    }

    public AVHWDeviceContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVHWDeviceContext(Pointer pointer) {
        super(pointer);
    }

    public AVHWDeviceContext position(long j) {
        return (AVHWDeviceContext) super.position(j);
    }

    public AVHWDeviceContext getPointer(long j) {
        return new AVHWDeviceContext((Pointer) this).position(this.position + j);
    }

    public static class Free_AVHWDeviceContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVHWDeviceContext aVHWDeviceContext);

        static {
            Loader.load();
        }

        public Free_AVHWDeviceContext(Pointer pointer) {
            super(pointer);
        }

        protected Free_AVHWDeviceContext() {
            allocate();
        }
    }
}
