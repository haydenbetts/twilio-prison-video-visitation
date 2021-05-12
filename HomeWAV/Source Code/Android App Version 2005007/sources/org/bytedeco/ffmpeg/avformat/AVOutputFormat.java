package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVOutputFormat extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"AVCodecID"})
    public native int audio_codec();

    public native AVOutputFormat audio_codec(int i);

    public native Check_bitstream_AVFormatContext_AVPacket check_bitstream();

    public native AVOutputFormat check_bitstream(Check_bitstream_AVFormatContext_AVPacket check_bitstream_AVFormatContext_AVPacket);

    @MemberGetter
    @Const
    public native AVCodecTag codec_tag(int i);

    @MemberGetter
    @Cast({"const AVCodecTag*const*"})
    public native PointerPointer codec_tag();

    public native Control_message_AVFormatContext_int_Pointer_long control_message();

    public native AVOutputFormat control_message(Control_message_AVFormatContext_int_Pointer_long control_message_AVFormatContext_int_Pointer_long);

    public native Create_device_capabilities_AVFormatContext_Pointer create_device_capabilities();

    public native AVOutputFormat create_device_capabilities(Create_device_capabilities_AVFormatContext_Pointer create_device_capabilities_AVFormatContext_Pointer);

    @Cast({"AVCodecID"})
    public native int data_codec();

    public native AVOutputFormat data_codec(int i);

    public native Deinit_AVFormatContext deinit();

    public native AVOutputFormat deinit(Deinit_AVFormatContext deinit_AVFormatContext);

    public native AVOutputFormat extensions(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer extensions();

    public native int flags();

    public native AVOutputFormat flags(int i);

    public native Free_device_capabilities_AVFormatContext_Pointer free_device_capabilities();

    public native AVOutputFormat free_device_capabilities(Free_device_capabilities_AVFormatContext_Pointer free_device_capabilities_AVFormatContext_Pointer);

    public native Get_device_list_AVFormatContext_Pointer get_device_list();

    public native AVOutputFormat get_device_list(Get_device_list_AVFormatContext_Pointer get_device_list_AVFormatContext_Pointer);

    public native Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer get_output_timestamp();

    public native AVOutputFormat get_output_timestamp(Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer);

    public native Init_AVFormatContext init();

    public native AVOutputFormat init(Init_AVFormatContext init_AVFormatContext);

    public native Interleave_packet_AVFormatContext_AVPacket_AVPacket_int interleave_packet();

    public native AVOutputFormat interleave_packet(Interleave_packet_AVFormatContext_AVPacket_AVPacket_int interleave_packet_AVFormatContext_AVPacket_AVPacket_int);

    public native AVOutputFormat long_name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer long_name();

    public native AVOutputFormat mime_type(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer mime_type();

    public native AVOutputFormat name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    public native AVOutputFormat next();

    public native AVOutputFormat next(AVOutputFormat aVOutputFormat);

    public native AVOutputFormat priv_class(AVClass aVClass);

    @Const
    public native AVClass priv_class();

    public native int priv_data_size();

    public native AVOutputFormat priv_data_size(int i);

    public native Query_codec_int_int query_codec();

    public native AVOutputFormat query_codec(Query_codec_int_int query_codec_int_int);

    @Cast({"AVCodecID"})
    public native int subtitle_codec();

    public native AVOutputFormat subtitle_codec(int i);

    @Cast({"AVCodecID"})
    public native int video_codec();

    public native AVOutputFormat video_codec(int i);

    public native Write_header_AVFormatContext write_header();

    public native AVOutputFormat write_header(Write_header_AVFormatContext write_header_AVFormatContext);

    public native Write_packet_AVFormatContext_AVPacket write_packet();

    public native AVOutputFormat write_packet(Write_packet_AVFormatContext_AVPacket write_packet_AVFormatContext_AVPacket);

    public native Write_trailer_AVFormatContext write_trailer();

    public native AVOutputFormat write_trailer(Write_trailer_AVFormatContext write_trailer_AVFormatContext);

    public native Write_uncoded_frame_AVFormatContext_int_PointerPointer_int write_uncoded_frame();

    public native AVOutputFormat write_uncoded_frame(Write_uncoded_frame_AVFormatContext_int_PointerPointer_int write_uncoded_frame_AVFormatContext_int_PointerPointer_int);

    static {
        Loader.load();
    }

    public AVOutputFormat() {
        super((Pointer) null);
        allocate();
    }

    public AVOutputFormat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVOutputFormat(Pointer pointer) {
        super(pointer);
    }

    public AVOutputFormat position(long j) {
        return (AVOutputFormat) super.position(j);
    }

    public AVOutputFormat getPointer(long j) {
        return new AVOutputFormat((Pointer) this).position(this.position + j);
    }

    public static class Write_header_AVFormatContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext);

        static {
            Loader.load();
        }

        public Write_header_AVFormatContext(Pointer pointer) {
            super(pointer);
        }

        protected Write_header_AVFormatContext() {
            allocate();
        }
    }

    public static class Write_packet_AVFormatContext_AVPacket extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, AVPacket aVPacket);

        static {
            Loader.load();
        }

        public Write_packet_AVFormatContext_AVPacket(Pointer pointer) {
            super(pointer);
        }

        protected Write_packet_AVFormatContext_AVPacket() {
            allocate();
        }
    }

    public static class Write_trailer_AVFormatContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext);

        static {
            Loader.load();
        }

        public Write_trailer_AVFormatContext(Pointer pointer) {
            super(pointer);
        }

        protected Write_trailer_AVFormatContext() {
            allocate();
        }
    }

    public static class Interleave_packet_AVFormatContext_AVPacket_AVPacket_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, AVPacket aVPacket, AVPacket aVPacket2, int i);

        static {
            Loader.load();
        }

        public Interleave_packet_AVFormatContext_AVPacket_AVPacket_int(Pointer pointer) {
            super(pointer);
        }

        protected Interleave_packet_AVFormatContext_AVPacket_AVPacket_int() {
            allocate();
        }
    }

    public static class Query_codec_int_int extends FunctionPointer {
        private native void allocate();

        public native int call(@Cast({"AVCodecID"}) int i, int i2);

        static {
            Loader.load();
        }

        public Query_codec_int_int(Pointer pointer) {
            super(pointer);
        }

        protected Query_codec_int_int() {
            allocate();
        }
    }

    public static class Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer extends FunctionPointer {
        private native void allocate();

        public native void call(AVFormatContext aVFormatContext, int i, @Cast({"int64_t*"}) LongPointer longPointer, @Cast({"int64_t*"}) LongPointer longPointer2);

        static {
            Loader.load();
        }

        public Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer(Pointer pointer) {
            super(pointer);
        }

        protected Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer() {
            allocate();
        }
    }

    public static class Control_message_AVFormatContext_int_Pointer_long extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, int i, Pointer pointer, @Cast({"size_t"}) long j);

        static {
            Loader.load();
        }

        public Control_message_AVFormatContext_int_Pointer_long(Pointer pointer) {
            super(pointer);
        }

        protected Control_message_AVFormatContext_int_Pointer_long() {
            allocate();
        }
    }

    public static class Write_uncoded_frame_AVFormatContext_int_PointerPointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, int i, @Cast({"AVFrame**"}) PointerPointer pointerPointer, @Cast({"unsigned"}) int i2);

        static {
            Loader.load();
        }

        public Write_uncoded_frame_AVFormatContext_int_PointerPointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Write_uncoded_frame_AVFormatContext_int_PointerPointer_int() {
            allocate();
        }
    }

    public static class Get_device_list_AVFormatContext_Pointer extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, @Cast({"AVDeviceInfoList*"}) Pointer pointer);

        static {
            Loader.load();
        }

        public Get_device_list_AVFormatContext_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Get_device_list_AVFormatContext_Pointer() {
            allocate();
        }
    }

    public static class Create_device_capabilities_AVFormatContext_Pointer extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, @Cast({"AVDeviceCapabilitiesQuery*"}) Pointer pointer);

        static {
            Loader.load();
        }

        public Create_device_capabilities_AVFormatContext_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Create_device_capabilities_AVFormatContext_Pointer() {
            allocate();
        }
    }

    public static class Free_device_capabilities_AVFormatContext_Pointer extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, @Cast({"AVDeviceCapabilitiesQuery*"}) Pointer pointer);

        static {
            Loader.load();
        }

        public Free_device_capabilities_AVFormatContext_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Free_device_capabilities_AVFormatContext_Pointer() {
            allocate();
        }
    }

    public static class Init_AVFormatContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext);

        static {
            Loader.load();
        }

        public Init_AVFormatContext(Pointer pointer) {
            super(pointer);
        }

        protected Init_AVFormatContext() {
            allocate();
        }
    }

    public static class Deinit_AVFormatContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVFormatContext aVFormatContext);

        static {
            Loader.load();
        }

        public Deinit_AVFormatContext(Pointer pointer) {
            super(pointer);
        }

        protected Deinit_AVFormatContext() {
            allocate();
        }
    }

    public static class Check_bitstream_AVFormatContext_AVPacket extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, @Const AVPacket aVPacket);

        static {
            Loader.load();
        }

        public Check_bitstream_AVFormatContext_AVPacket(Pointer pointer) {
            super(pointer);
        }

        protected Check_bitstream_AVFormatContext_AVPacket() {
            allocate();
        }
    }
}
