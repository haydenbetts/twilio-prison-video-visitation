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
public class AVHWFramesContext extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"free"})
    public native Free_AVHWFramesContext _free();

    public native AVHWFramesContext _free(Free_AVHWFramesContext free_AVHWFramesContext);

    @Const
    public native AVClass av_class();

    public native AVHWFramesContext av_class(AVClass aVClass);

    public native AVHWDeviceContext device_ctx();

    public native AVHWFramesContext device_ctx(AVHWDeviceContext aVHWDeviceContext);

    public native AVBufferRef device_ref();

    public native AVHWFramesContext device_ref(AVBufferRef aVBufferRef);

    @Cast({"AVPixelFormat"})
    public native int format();

    public native AVHWFramesContext format(int i);

    public native int height();

    public native AVHWFramesContext height(int i);

    public native AVHWFramesContext hwctx(Pointer pointer);

    public native Pointer hwctx();

    public native int initial_pool_size();

    public native AVHWFramesContext initial_pool_size(int i);

    public native AVHWFramesContext internal(AVHWFramesInternal aVHWFramesInternal);

    public native AVHWFramesInternal internal();

    public native AVBufferPool pool();

    public native AVHWFramesContext pool(AVBufferPool aVBufferPool);

    @Cast({"AVPixelFormat"})
    public native int sw_format();

    public native AVHWFramesContext sw_format(int i);

    public native AVHWFramesContext user_opaque(Pointer pointer);

    public native Pointer user_opaque();

    public native int width();

    public native AVHWFramesContext width(int i);

    static {
        Loader.load();
    }

    public AVHWFramesContext() {
        super((Pointer) null);
        allocate();
    }

    public AVHWFramesContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVHWFramesContext(Pointer pointer) {
        super(pointer);
    }

    public AVHWFramesContext position(long j) {
        return (AVHWFramesContext) super.position(j);
    }

    public AVHWFramesContext getPointer(long j) {
        return new AVHWFramesContext((Pointer) this).position(this.position + j);
    }

    public static class Free_AVHWFramesContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVHWFramesContext aVHWFramesContext);

        static {
            Loader.load();
        }

        public Free_AVHWFramesContext(Pointer pointer) {
            super(pointer);
        }

        protected Free_AVHWFramesContext() {
            allocate();
        }
    }
}
