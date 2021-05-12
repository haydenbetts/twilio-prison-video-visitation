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
public class AVInputFormat extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @MemberGetter
    @Const
    public native AVCodecTag codec_tag(int i);

    @MemberGetter
    @Cast({"const AVCodecTag*const*"})
    public native PointerPointer codec_tag();

    public native Create_device_capabilities_AVFormatContext_Pointer create_device_capabilities();

    public native AVInputFormat create_device_capabilities(Create_device_capabilities_AVFormatContext_Pointer create_device_capabilities_AVFormatContext_Pointer);

    public native AVInputFormat extensions(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer extensions();

    public native int flags();

    public native AVInputFormat flags(int i);

    public native Free_device_capabilities_AVFormatContext_Pointer free_device_capabilities();

    public native AVInputFormat free_device_capabilities(Free_device_capabilities_AVFormatContext_Pointer free_device_capabilities_AVFormatContext_Pointer);

    public native Get_device_list_AVFormatContext_Pointer get_device_list();

    public native AVInputFormat get_device_list(Get_device_list_AVFormatContext_Pointer get_device_list_AVFormatContext_Pointer);

    public native AVInputFormat long_name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer long_name();

    public native AVInputFormat mime_type(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer mime_type();

    public native AVInputFormat name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    public native AVInputFormat next();

    public native AVInputFormat next(AVInputFormat aVInputFormat);

    public native AVInputFormat priv_class(AVClass aVClass);

    @Const
    public native AVClass priv_class();

    public native int priv_data_size();

    public native AVInputFormat priv_data_size(int i);

    public native int raw_codec_id();

    public native AVInputFormat raw_codec_id(int i);

    public native Read_close_AVFormatContext read_close();

    public native AVInputFormat read_close(Read_close_AVFormatContext read_close_AVFormatContext);

    public native Read_header_AVFormatContext read_header();

    public native AVInputFormat read_header(Read_header_AVFormatContext read_header_AVFormatContext);

    public native Read_packet_AVFormatContext_AVPacket read_packet();

    public native AVInputFormat read_packet(Read_packet_AVFormatContext_AVPacket read_packet_AVFormatContext_AVPacket);

    public native Read_pause_AVFormatContext read_pause();

    public native AVInputFormat read_pause(Read_pause_AVFormatContext read_pause_AVFormatContext);

    public native Read_play_AVFormatContext read_play();

    public native AVInputFormat read_play(Read_play_AVFormatContext read_play_AVFormatContext);

    public native Read_probe_AVProbeData read_probe();

    public native AVInputFormat read_probe(Read_probe_AVProbeData read_probe_AVProbeData);

    public native Read_seek_AVFormatContext_int_long_int read_seek();

    public native AVInputFormat read_seek(Read_seek_AVFormatContext_int_long_int read_seek_AVFormatContext_int_long_int);

    public native Read_seek2_AVFormatContext_int_long_long_long_int read_seek2();

    public native AVInputFormat read_seek2(Read_seek2_AVFormatContext_int_long_long_long_int read_seek2_AVFormatContext_int_long_long_long_int);

    public native Read_timestamp_AVFormatContext_int_LongPointer_long read_timestamp();

    public native AVInputFormat read_timestamp(Read_timestamp_AVFormatContext_int_LongPointer_long read_timestamp_AVFormatContext_int_LongPointer_long);

    static {
        Loader.load();
    }

    public AVInputFormat() {
        super((Pointer) null);
        allocate();
    }

    public AVInputFormat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVInputFormat(Pointer pointer) {
        super(pointer);
    }

    public AVInputFormat position(long j) {
        return (AVInputFormat) super.position(j);
    }

    public AVInputFormat getPointer(long j) {
        return new AVInputFormat((Pointer) this).position(this.position + j);
    }

    public static class Read_probe_AVProbeData extends FunctionPointer {
        private native void allocate();

        public native int call(@Const AVProbeData aVProbeData);

        static {
            Loader.load();
        }

        public Read_probe_AVProbeData(Pointer pointer) {
            super(pointer);
        }

        protected Read_probe_AVProbeData() {
            allocate();
        }
    }

    public static class Read_header_AVFormatContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext);

        static {
            Loader.load();
        }

        public Read_header_AVFormatContext(Pointer pointer) {
            super(pointer);
        }

        protected Read_header_AVFormatContext() {
            allocate();
        }
    }

    public static class Read_packet_AVFormatContext_AVPacket extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, AVPacket aVPacket);

        static {
            Loader.load();
        }

        public Read_packet_AVFormatContext_AVPacket(Pointer pointer) {
            super(pointer);
        }

        protected Read_packet_AVFormatContext_AVPacket() {
            allocate();
        }
    }

    public static class Read_close_AVFormatContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext);

        static {
            Loader.load();
        }

        public Read_close_AVFormatContext(Pointer pointer) {
            super(pointer);
        }

        protected Read_close_AVFormatContext() {
            allocate();
        }
    }

    public static class Read_seek_AVFormatContext_int_long_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, int i, @Cast({"int64_t"}) long j, int i2);

        static {
            Loader.load();
        }

        public Read_seek_AVFormatContext_int_long_int(Pointer pointer) {
            super(pointer);
        }

        protected Read_seek_AVFormatContext_int_long_int() {
            allocate();
        }
    }

    public static class Read_timestamp_AVFormatContext_int_LongPointer_long extends FunctionPointer {
        private native void allocate();

        @Cast({"int64_t"})
        public native long call(AVFormatContext aVFormatContext, int i, @Cast({"int64_t*"}) LongPointer longPointer, @Cast({"int64_t"}) long j);

        static {
            Loader.load();
        }

        public Read_timestamp_AVFormatContext_int_LongPointer_long(Pointer pointer) {
            super(pointer);
        }

        protected Read_timestamp_AVFormatContext_int_LongPointer_long() {
            allocate();
        }
    }

    public static class Read_play_AVFormatContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext);

        static {
            Loader.load();
        }

        public Read_play_AVFormatContext(Pointer pointer) {
            super(pointer);
        }

        protected Read_play_AVFormatContext() {
            allocate();
        }
    }

    public static class Read_pause_AVFormatContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext);

        static {
            Loader.load();
        }

        public Read_pause_AVFormatContext(Pointer pointer) {
            super(pointer);
        }

        protected Read_pause_AVFormatContext() {
            allocate();
        }
    }

    public static class Read_seek2_AVFormatContext_int_long_long_long_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVFormatContext aVFormatContext, int i, @Cast({"int64_t"}) long j, @Cast({"int64_t"}) long j2, @Cast({"int64_t"}) long j3, int i2);

        static {
            Loader.load();
        }

        public Read_seek2_AVFormatContext_int_long_long_long_int(Pointer pointer) {
            super(pointer);
        }

        protected Read_seek2_AVFormatContext_int_long_long_long_int() {
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
}
