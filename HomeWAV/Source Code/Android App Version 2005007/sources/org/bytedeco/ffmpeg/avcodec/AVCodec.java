package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVCodec extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"close"})
    public native Close_AVCodecContext _close();

    public native AVCodec _close(Close_AVCodecContext close_AVCodecContext);

    public native AVCodec bsfs(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer bsfs();

    public native int capabilities();

    public native AVCodec capabilities(int i);

    public native int caps_internal();

    public native AVCodec caps_internal(int i);

    public native AVCodec channel_layouts(LongPointer longPointer);

    @Cast({"const uint64_t*"})
    public native LongPointer channel_layouts();

    public native AVCodec codec_tags(IntPointer intPointer);

    @Cast({"const uint32_t*"})
    public native IntPointer codec_tags();

    public native Decode_AVCodecContext_Pointer_IntPointer_AVPacket decode();

    public native AVCodec decode(Decode_AVCodecContext_Pointer_IntPointer_AVPacket decode_AVCodecContext_Pointer_IntPointer_AVPacket);

    public native AVCodec defaults(AVCodecDefault aVCodecDefault);

    @Const
    public native AVCodecDefault defaults();

    public native Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer encode2();

    public native AVCodec encode2(Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer encode2_AVCodecContext_AVPacket_AVFrame_IntPointer);

    public native Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle encode_sub();

    public native AVCodec encode_sub(Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle encode_sub_AVCodecContext_BytePointer_int_AVSubtitle);

    public native Flush_AVCodecContext flush();

    public native AVCodec flush(Flush_AVCodecContext flush_AVCodecContext);

    public native AVCodec hw_configs(int i, Pointer pointer);

    @Cast({"const AVCodecHWConfigInternal*"})
    public native Pointer hw_configs(int i);

    @MemberGetter
    @Cast({"const AVCodecHWConfigInternal**"})
    public native PointerPointer hw_configs();

    @Cast({"AVCodecID"})
    public native int id();

    public native AVCodec id(int i);

    public native Init_AVCodecContext init();

    public native AVCodec init(Init_AVCodecContext init_AVCodecContext);

    public native Init_static_data_AVCodec init_static_data();

    public native AVCodec init_static_data(Init_static_data_AVCodec init_static_data_AVCodec);

    public native AVCodec long_name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer long_name();

    @Cast({"uint8_t"})
    public native byte max_lowres();

    public native AVCodec max_lowres(byte b);

    public native AVCodec name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    public native AVCodec next();

    public native AVCodec next(AVCodec aVCodec);

    public native AVCodec pix_fmts(IntPointer intPointer);

    @Cast({"const AVPixelFormat*"})
    public native IntPointer pix_fmts();

    public native AVCodec priv_class(AVClass aVClass);

    @Const
    public native AVClass priv_class();

    public native int priv_data_size();

    public native AVCodec priv_data_size(int i);

    public native AVCodec profiles(AVProfile aVProfile);

    @Const
    public native AVProfile profiles();

    public native Receive_frame_AVCodecContext_AVFrame receive_frame();

    public native AVCodec receive_frame(Receive_frame_AVCodecContext_AVFrame receive_frame_AVCodecContext_AVFrame);

    public native Receive_packet_AVCodecContext_AVPacket receive_packet();

    public native AVCodec receive_packet(Receive_packet_AVCodecContext_AVPacket receive_packet_AVCodecContext_AVPacket);

    public native AVCodec sample_fmts(IntPointer intPointer);

    @Cast({"const AVSampleFormat*"})
    public native IntPointer sample_fmts();

    public native Send_frame_AVCodecContext_AVFrame send_frame();

    public native AVCodec send_frame(Send_frame_AVCodecContext_AVFrame send_frame_AVCodecContext_AVFrame);

    public native AVCodec supported_framerates(AVRational aVRational);

    @Const
    public native AVRational supported_framerates();

    public native AVCodec supported_samplerates(IntPointer intPointer);

    @Const
    public native IntPointer supported_samplerates();

    @Cast({"AVMediaType"})
    public native int type();

    public native AVCodec type(int i);

    public native Update_thread_context_AVCodecContext_AVCodecContext update_thread_context();

    public native AVCodec update_thread_context(Update_thread_context_AVCodecContext_AVCodecContext update_thread_context_AVCodecContext_AVCodecContext);

    public native AVCodec wrapper_name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer wrapper_name();

    static {
        Loader.load();
    }

    public AVCodec() {
        super((Pointer) null);
        allocate();
    }

    public AVCodec(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVCodec(Pointer pointer) {
        super(pointer);
    }

    public AVCodec position(long j) {
        return (AVCodec) super.position(j);
    }

    public AVCodec getPointer(long j) {
        return new AVCodec((Pointer) this).position(this.position + j);
    }

    public static class Update_thread_context_AVCodecContext_AVCodecContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, @Const AVCodecContext aVCodecContext2);

        static {
            Loader.load();
        }

        public Update_thread_context_AVCodecContext_AVCodecContext(Pointer pointer) {
            super(pointer);
        }

        protected Update_thread_context_AVCodecContext_AVCodecContext() {
            allocate();
        }
    }

    public static class Init_static_data_AVCodec extends FunctionPointer {
        private native void allocate();

        public native void call(AVCodec aVCodec);

        static {
            Loader.load();
        }

        public Init_static_data_AVCodec(Pointer pointer) {
            super(pointer);
        }

        protected Init_static_data_AVCodec() {
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

    public static class Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, @Cast({"uint8_t*"}) BytePointer bytePointer, int i, @Const AVSubtitle aVSubtitle);

        static {
            Loader.load();
        }

        public Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle(Pointer pointer) {
            super(pointer);
        }

        protected Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle() {
            allocate();
        }
    }

    public static class Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, AVPacket aVPacket, @Const AVFrame aVFrame, IntPointer intPointer);

        static {
            Loader.load();
        }

        public Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer(Pointer pointer) {
            super(pointer);
        }

        protected Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer() {
            allocate();
        }
    }

    public static class Decode_AVCodecContext_Pointer_IntPointer_AVPacket extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, Pointer pointer, IntPointer intPointer, AVPacket aVPacket);

        static {
            Loader.load();
        }

        public Decode_AVCodecContext_Pointer_IntPointer_AVPacket(Pointer pointer) {
            super(pointer);
        }

        protected Decode_AVCodecContext_Pointer_IntPointer_AVPacket() {
            allocate();
        }
    }

    public static class Close_AVCodecContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext);

        static {
            Loader.load();
        }

        public Close_AVCodecContext(Pointer pointer) {
            super(pointer);
        }

        protected Close_AVCodecContext() {
            allocate();
        }
    }

    public static class Send_frame_AVCodecContext_AVFrame extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, @Const AVFrame aVFrame);

        static {
            Loader.load();
        }

        public Send_frame_AVCodecContext_AVFrame(Pointer pointer) {
            super(pointer);
        }

        protected Send_frame_AVCodecContext_AVFrame() {
            allocate();
        }
    }

    public static class Receive_packet_AVCodecContext_AVPacket extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, AVPacket aVPacket);

        static {
            Loader.load();
        }

        public Receive_packet_AVCodecContext_AVPacket(Pointer pointer) {
            super(pointer);
        }

        protected Receive_packet_AVCodecContext_AVPacket() {
            allocate();
        }
    }

    public static class Receive_frame_AVCodecContext_AVFrame extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, AVFrame aVFrame);

        static {
            Loader.load();
        }

        public Receive_frame_AVCodecContext_AVFrame(Pointer pointer) {
            super(pointer);
        }

        protected Receive_frame_AVCodecContext_AVFrame() {
            allocate();
        }
    }

    public static class Flush_AVCodecContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVCodecContext aVCodecContext);

        static {
            Loader.load();
        }

        public Flush_AVCodecContext(Pointer pointer) {
            super(pointer);
        }

        protected Flush_AVCodecContext() {
            allocate();
        }
    }
}
