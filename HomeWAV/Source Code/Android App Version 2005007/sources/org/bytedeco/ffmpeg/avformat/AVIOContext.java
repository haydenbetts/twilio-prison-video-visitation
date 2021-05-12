package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVIOContext extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVIOContext av_class(AVClass aVClass);

    @Const
    public native AVClass av_class();

    public native AVIOContext buf_end(BytePointer bytePointer);

    @Cast({"unsigned char*"})
    public native BytePointer buf_end();

    public native AVIOContext buf_ptr(BytePointer bytePointer);

    @Cast({"unsigned char*"})
    public native BytePointer buf_ptr();

    public native AVIOContext buf_ptr_max(BytePointer bytePointer);

    @Cast({"unsigned char*"})
    public native BytePointer buf_ptr_max();

    public native AVIOContext buffer(BytePointer bytePointer);

    @Cast({"unsigned char*"})
    public native BytePointer buffer();

    public native int buffer_size();

    public native AVIOContext buffer_size(int i);

    @Cast({"int64_t"})
    public native long bytes_read();

    public native AVIOContext bytes_read(long j);

    @Cast({"unsigned long"})
    public native long checksum();

    public native AVIOContext checksum(long j);

    public native AVIOContext checksum_ptr(BytePointer bytePointer);

    @Cast({"unsigned char*"})
    public native BytePointer checksum_ptr();

    @Cast({"AVIODataMarkerType"})
    public native int current_type();

    public native AVIOContext current_type(int i);

    public native int direct();

    public native AVIOContext direct(int i);

    public native int eof_reached();

    public native AVIOContext eof_reached(int i);

    public native int error();

    public native AVIOContext error(int i);

    public native int ignore_boundary_point();

    public native AVIOContext ignore_boundary_point(int i);

    @Cast({"int64_t"})
    public native long last_time();

    public native AVIOContext last_time(long j);

    public native int max_packet_size();

    public native AVIOContext max_packet_size(int i);

    @Cast({"int64_t"})
    public native long maxsize();

    public native AVIOContext maxsize(long j);

    public native int min_packet_size();

    public native AVIOContext min_packet_size(int i);

    public native AVIOContext opaque(Pointer pointer);

    public native Pointer opaque();

    public native int orig_buffer_size();

    public native AVIOContext orig_buffer_size(int i);

    @Cast({"int64_t"})
    public native long pos();

    public native AVIOContext pos(long j);

    public native AVIOContext protocol_blacklist(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer protocol_blacklist();

    public native AVIOContext protocol_whitelist(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer protocol_whitelist();

    public native Read_packet_Pointer_BytePointer_int read_packet();

    public native AVIOContext read_packet(Read_packet_Pointer_BytePointer_int read_packet_Pointer_BytePointer_int);

    public native Read_pause_Pointer_int read_pause();

    public native AVIOContext read_pause(Read_pause_Pointer_int read_pause_Pointer_int);

    public native Read_seek_Pointer_int_long_int read_seek();

    public native AVIOContext read_seek(Read_seek_Pointer_int_long_int read_seek_Pointer_int_long_int);

    public native Seek_Pointer_long_int seek();

    public native AVIOContext seek(Seek_Pointer_long_int seek_Pointer_long_int);

    public native int seek_count();

    public native AVIOContext seek_count(int i);

    public native int seekable();

    public native AVIOContext seekable(int i);

    public native Short_seek_get_Pointer short_seek_get();

    public native AVIOContext short_seek_get(Short_seek_get_Pointer short_seek_get_Pointer);

    public native int short_seek_threshold();

    public native AVIOContext short_seek_threshold(int i);

    public native Update_checksum_long_BytePointer_int update_checksum();

    public native AVIOContext update_checksum(Update_checksum_long_BytePointer_int update_checksum_long_BytePointer_int);

    public native Write_data_type_Pointer_BytePointer_int_int_long write_data_type();

    public native AVIOContext write_data_type(Write_data_type_Pointer_BytePointer_int_int_long write_data_type_Pointer_BytePointer_int_int_long);

    public native int write_flag();

    public native AVIOContext write_flag(int i);

    public native Write_packet_Pointer_BytePointer_int write_packet();

    public native AVIOContext write_packet(Write_packet_Pointer_BytePointer_int write_packet_Pointer_BytePointer_int);

    public native int writeout_count();

    public native AVIOContext writeout_count(int i);

    @Cast({"int64_t"})
    public native long written();

    public native AVIOContext written(long j);

    static {
        Loader.load();
    }

    public AVIOContext() {
        super((Pointer) null);
        allocate();
    }

    public AVIOContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVIOContext(Pointer pointer) {
        super(pointer);
    }

    public AVIOContext position(long j) {
        return (AVIOContext) super.position(j);
    }

    public AVIOContext getPointer(long j) {
        return new AVIOContext((Pointer) this).position(this.position + j);
    }

    public static class Read_packet_Pointer_BytePointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(Pointer pointer, @Cast({"uint8_t*"}) BytePointer bytePointer, int i);

        static {
            Loader.load();
        }

        public Read_packet_Pointer_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Read_packet_Pointer_BytePointer_int() {
            allocate();
        }
    }

    public static class Write_packet_Pointer_BytePointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(Pointer pointer, @Cast({"uint8_t*"}) BytePointer bytePointer, int i);

        static {
            Loader.load();
        }

        public Write_packet_Pointer_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Write_packet_Pointer_BytePointer_int() {
            allocate();
        }
    }

    public static class Seek_Pointer_long_int extends FunctionPointer {
        private native void allocate();

        @Cast({"int64_t"})
        public native long call(Pointer pointer, @Cast({"int64_t"}) long j, int i);

        static {
            Loader.load();
        }

        public Seek_Pointer_long_int(Pointer pointer) {
            super(pointer);
        }

        protected Seek_Pointer_long_int() {
            allocate();
        }
    }

    public static class Update_checksum_long_BytePointer_int extends FunctionPointer {
        private native void allocate();

        @Cast({"unsigned long"})
        public native long call(@Cast({"unsigned long"}) long j, @Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"unsigned int"}) int i);

        static {
            Loader.load();
        }

        public Update_checksum_long_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Update_checksum_long_BytePointer_int() {
            allocate();
        }
    }

    public static class Read_pause_Pointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(Pointer pointer, int i);

        static {
            Loader.load();
        }

        public Read_pause_Pointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Read_pause_Pointer_int() {
            allocate();
        }
    }

    public static class Read_seek_Pointer_int_long_int extends FunctionPointer {
        private native void allocate();

        @Cast({"int64_t"})
        public native long call(Pointer pointer, int i, @Cast({"int64_t"}) long j, int i2);

        static {
            Loader.load();
        }

        public Read_seek_Pointer_int_long_int(Pointer pointer) {
            super(pointer);
        }

        protected Read_seek_Pointer_int_long_int() {
            allocate();
        }
    }

    public static class Write_data_type_Pointer_BytePointer_int_int_long extends FunctionPointer {
        private native void allocate();

        public native int call(Pointer pointer, @Cast({"uint8_t*"}) BytePointer bytePointer, int i, @Cast({"AVIODataMarkerType"}) int i2, @Cast({"int64_t"}) long j);

        static {
            Loader.load();
        }

        public Write_data_type_Pointer_BytePointer_int_int_long(Pointer pointer) {
            super(pointer);
        }

        protected Write_data_type_Pointer_BytePointer_int_int_long() {
            allocate();
        }
    }

    public static class Short_seek_get_Pointer extends FunctionPointer {
        private native void allocate();

        public native int call(Pointer pointer);

        static {
            Loader.load();
        }

        public Short_seek_get_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Short_seek_get_Pointer() {
            allocate();
        }
    }
}
