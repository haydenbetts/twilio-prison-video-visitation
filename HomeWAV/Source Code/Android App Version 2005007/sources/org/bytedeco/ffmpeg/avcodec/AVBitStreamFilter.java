package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVBitStreamFilter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"close"})
    public native Close_AVBSFContext _close();

    public native AVBitStreamFilter _close(Close_AVBSFContext close_AVBSFContext);

    public native AVBitStreamFilter codec_ids(IntPointer intPointer);

    @Cast({"const AVCodecID*"})
    public native IntPointer codec_ids();

    public native Filter_AVBSFContext_AVPacket filter();

    public native AVBitStreamFilter filter(Filter_AVBSFContext_AVPacket filter_AVBSFContext_AVPacket);

    public native Flush_AVBSFContext flush();

    public native AVBitStreamFilter flush(Flush_AVBSFContext flush_AVBSFContext);

    public native Init_AVBSFContext init();

    public native AVBitStreamFilter init(Init_AVBSFContext init_AVBSFContext);

    public native AVBitStreamFilter name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    public native AVBitStreamFilter priv_class(AVClass aVClass);

    @Const
    public native AVClass priv_class();

    public native int priv_data_size();

    public native AVBitStreamFilter priv_data_size(int i);

    static {
        Loader.load();
    }

    public AVBitStreamFilter() {
        super((Pointer) null);
        allocate();
    }

    public AVBitStreamFilter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVBitStreamFilter(Pointer pointer) {
        super(pointer);
    }

    public AVBitStreamFilter position(long j) {
        return (AVBitStreamFilter) super.position(j);
    }

    public AVBitStreamFilter getPointer(long j) {
        return new AVBitStreamFilter((Pointer) this).position(this.position + j);
    }

    public static class Init_AVBSFContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVBSFContext aVBSFContext);

        static {
            Loader.load();
        }

        public Init_AVBSFContext(Pointer pointer) {
            super(pointer);
        }

        protected Init_AVBSFContext() {
            allocate();
        }
    }

    public static class Filter_AVBSFContext_AVPacket extends FunctionPointer {
        private native void allocate();

        public native int call(AVBSFContext aVBSFContext, AVPacket aVPacket);

        static {
            Loader.load();
        }

        public Filter_AVBSFContext_AVPacket(Pointer pointer) {
            super(pointer);
        }

        protected Filter_AVBSFContext_AVPacket() {
            allocate();
        }
    }

    public static class Close_AVBSFContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVBSFContext aVBSFContext);

        static {
            Loader.load();
        }

        public Close_AVBSFContext(Pointer pointer) {
            super(pointer);
        }

        protected Close_AVBSFContext() {
            allocate();
        }
    }

    public static class Flush_AVBSFContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVBSFContext aVBSFContext);

        static {
            Loader.load();
        }

        public Flush_AVBSFContext(Pointer pointer) {
            super(pointer);
        }

        protected Flush_AVBSFContext() {
            allocate();
        }
    }
}
