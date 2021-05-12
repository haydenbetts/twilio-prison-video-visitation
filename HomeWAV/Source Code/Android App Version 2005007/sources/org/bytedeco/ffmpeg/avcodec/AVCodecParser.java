package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVCodecParser extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int codec_ids(int i);

    public native AVCodecParser codec_ids(int i, int i2);

    @MemberGetter
    public native IntPointer codec_ids();

    public native AVCodecParser next();

    public native AVCodecParser next(AVCodecParser aVCodecParser);

    public native Parser_close_AVCodecParserContext parser_close();

    public native AVCodecParser parser_close(Parser_close_AVCodecParserContext parser_close_AVCodecParserContext);

    public native Parser_init_AVCodecParserContext parser_init();

    public native AVCodecParser parser_init(Parser_init_AVCodecParserContext parser_init_AVCodecParserContext);

    public native Parser_parse_AVCodecParserContext_AVCodecContext_PointerPointer_IntPointer_BytePointer_int parser_parse();

    public native AVCodecParser parser_parse(Parser_parse_AVCodecParserContext_AVCodecContext_PointerPointer_IntPointer_BytePointer_int parser_parse_AVCodecParserContext_AVCodecContext_PointerPointer_IntPointer_BytePointer_int);

    public native int priv_data_size();

    public native AVCodecParser priv_data_size(int i);

    public native Split_AVCodecContext_BytePointer_int split();

    public native AVCodecParser split(Split_AVCodecContext_BytePointer_int split_AVCodecContext_BytePointer_int);

    static {
        Loader.load();
    }

    public AVCodecParser() {
        super((Pointer) null);
        allocate();
    }

    public AVCodecParser(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVCodecParser(Pointer pointer) {
        super(pointer);
    }

    public AVCodecParser position(long j) {
        return (AVCodecParser) super.position(j);
    }

    public AVCodecParser getPointer(long j) {
        return new AVCodecParser((Pointer) this).position(this.position + j);
    }

    public static class Parser_init_AVCodecParserContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecParserContext aVCodecParserContext);

        static {
            Loader.load();
        }

        public Parser_init_AVCodecParserContext(Pointer pointer) {
            super(pointer);
        }

        protected Parser_init_AVCodecParserContext() {
            allocate();
        }
    }

    public static class Parser_parse_AVCodecParserContext_AVCodecContext_PointerPointer_IntPointer_BytePointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecParserContext aVCodecParserContext, AVCodecContext aVCodecContext, @Cast({"const uint8_t**"}) PointerPointer pointerPointer, IntPointer intPointer, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

        static {
            Loader.load();
        }

        public Parser_parse_AVCodecParserContext_AVCodecContext_PointerPointer_IntPointer_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Parser_parse_AVCodecParserContext_AVCodecContext_PointerPointer_IntPointer_BytePointer_int() {
            allocate();
        }
    }

    public static class Parser_close_AVCodecParserContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVCodecParserContext aVCodecParserContext);

        static {
            Loader.load();
        }

        public Parser_close_AVCodecParserContext(Pointer pointer) {
            super(pointer);
        }

        protected Parser_close_AVCodecParserContext() {
            allocate();
        }
    }

    public static class Split_AVCodecContext_BytePointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVCodecContext aVCodecContext, @Cast({"const uint8_t*"}) BytePointer bytePointer, int i);

        static {
            Loader.load();
        }

        public Split_AVCodecContext_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Split_AVCodecContext_BytePointer_int() {
            allocate();
        }
    }
}
