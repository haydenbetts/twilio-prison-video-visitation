package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVHWAccel extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native Alloc_frame_AVCodecContext_AVFrame alloc_frame();

    public native AVHWAccel alloc_frame(Alloc_frame_AVCodecContext_AVFrame alloc_frame_AVCodecContext_AVFrame);

    public native int capabilities();

    public native AVHWAccel capabilities(int i);

    public native int caps_internal();

    public native AVHWAccel caps_internal(int i);

    public native Decode_mb_MpegEncContext decode_mb();

    public native AVHWAccel decode_mb(Decode_mb_MpegEncContext decode_mb_MpegEncContext);

    public native Decode_params_AVCodecContext_int_BytePointer_int decode_params();

    public native AVHWAccel decode_params(Decode_params_AVCodecContext_int_BytePointer_int decode_params_AVCodecContext_int_BytePointer_int);

    public native Decode_slice_AVCodecContext_BytePointer_int decode_slice();

    public native AVHWAccel decode_slice(Decode_slice_AVCodecContext_BytePointer_int decode_slice_AVCodecContext_BytePointer_int);

    public native End_frame_AVCodecContext end_frame();

    public native AVHWAccel end_frame(End_frame_AVCodecContext end_frame_AVCodecContext);

    public native Frame_params_AVCodecContext_AVBufferRef frame_params();

    public native AVHWAccel frame_params(Frame_params_AVCodecContext_AVBufferRef frame_params_AVCodecContext_AVBufferRef);

    public native int frame_priv_data_size();

    public native AVHWAccel frame_priv_data_size(int i);

    @Cast({"AVCodecID"})
    public native int id();

    public native AVHWAccel id(int i);

    public native Init_AVCodecContext init();

    public native AVHWAccel init(Init_AVCodecContext init_AVCodecContext);

    public native AVHWAccel name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    @Cast({"AVPixelFormat"})
    public native int pix_fmt();

    public native AVHWAccel pix_fmt(int i);

    public native int priv_data_size();

    public native AVHWAccel priv_data_size(int i);

    public native Start_frame_AVCodecContext_BytePointer_int start_frame();

    public native AVHWAccel start_frame(Start_frame_AVCodecContext_BytePointer_int start_frame_AVCodecContext_BytePointer_int);

    @Cast({"AVMediaType"})
    public native int type();

    public native AVHWAccel type(int i);

    public native Uninit_AVCodecContext uninit();

    public native AVHWAccel uninit(Uninit_AVCodecContext uninit_AVCodecContext);

    static {
        Loader.load();
    }

    public AVHWAccel() {
        super((Pointer) null);
        allocate();
    }

    public AVHWAccel(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVHWAccel(Pointer pointer) {
        super(pointer);
    }

    public AVHWAccel position(long j) {
        return (AVHWAccel) super.position(j);
    }

    public AVHWAccel getPointer(long j) {
        return new AVHWAccel((Pointer) this).position(this.position + j);
    }

    public static class Alloc_frame_AVCodecContext_AVFrame extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, AVFrame aVFrame);

        static {
            Loader.load();
        }

        public Alloc_frame_AVCodecContext_AVFrame(Pointer pointer) {
            super(pointer);
        }

        protected Alloc_frame_AVCodecContext_AVFrame() {
            allocate();
        }
    }

    public static class Start_frame_AVCodecContext_BytePointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"uint32_t"}) int i);

        static {
            Loader.load();
        }

        public Start_frame_AVCodecContext_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Start_frame_AVCodecContext_BytePointer_int() {
            allocate();
        }
    }

    public static class Decode_params_AVCodecContext_int_BytePointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, int i, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"uint32_t"}) int i2);

        static {
            Loader.load();
        }

        public Decode_params_AVCodecContext_int_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Decode_params_AVCodecContext_int_BytePointer_int() {
            allocate();
        }
    }

    public static class Decode_slice_AVCodecContext_BytePointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"uint32_t"}) int i);

        static {
            Loader.load();
        }

        public Decode_slice_AVCodecContext_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Decode_slice_AVCodecContext_BytePointer_int() {
            allocate();
        }
    }

    public static class End_frame_AVCodecContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext);

        static {
            Loader.load();
        }

        public End_frame_AVCodecContext(Pointer pointer) {
            super(pointer);
        }

        protected End_frame_AVCodecContext() {
            allocate();
        }
    }

    public static class Decode_mb_MpegEncContext extends FunctionPointer {
        private native void allocate();

        public native void call(MpegEncContext mpegEncContext);

        static {
            Loader.load();
        }

        public Decode_mb_MpegEncContext(Pointer pointer) {
            super(pointer);
        }

        protected Decode_mb_MpegEncContext() {
            allocate();
        }
    }

    public static class Init_AVCodecContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext);

        static {
            Loader.load();
        }

        public Init_AVCodecContext(Pointer pointer) {
            super(pointer);
        }

        protected Init_AVCodecContext() {
            allocate();
        }
    }

    public static class Uninit_AVCodecContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext);

        static {
            Loader.load();
        }

        public Uninit_AVCodecContext(Pointer pointer) {
            super(pointer);
        }

        protected Uninit_AVCodecContext() {
            allocate();
        }
    }

    public static class Frame_params_AVCodecContext_AVBufferRef extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, AVBufferRef aVBufferRef);

        static {
            Loader.load();
        }

        public Frame_params_AVCodecContext_AVBufferRef(Pointer pointer) {
            super(pointer);
        }

        protected Frame_params_AVCodecContext_AVBufferRef() {
            allocate();
        }
    }
}
