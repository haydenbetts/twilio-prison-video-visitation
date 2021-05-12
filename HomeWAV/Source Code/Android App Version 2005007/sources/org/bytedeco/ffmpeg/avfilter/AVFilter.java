package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class AVFilter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native Activate_AVFilterContext activate();

    public native AVFilter activate(Activate_AVFilterContext activate_AVFilterContext);

    public native AVFilter description(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer description();

    public native int flags();

    public native AVFilter flags(int i);

    public native int flags_internal();

    public native AVFilter flags_internal(int i);

    public native Init_AVFilterContext init();

    public native AVFilter init(Init_AVFilterContext init_AVFilterContext);

    public native Init_dict_AVFilterContext_PointerPointer init_dict();

    public native AVFilter init_dict(Init_dict_AVFilterContext_PointerPointer init_dict_AVFilterContext_PointerPointer);

    public native Init_opaque_AVFilterContext_Pointer init_opaque();

    public native AVFilter init_opaque(Init_opaque_AVFilterContext_Pointer init_opaque_AVFilterContext_Pointer);

    public native AVFilter inputs(AVFilterPad aVFilterPad);

    @Const
    public native AVFilterPad inputs();

    public native AVFilter name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    public native AVFilter next();

    public native AVFilter next(AVFilter aVFilter);

    public native AVFilter outputs(AVFilterPad aVFilterPad);

    @Const
    public native AVFilterPad outputs();

    public native Preinit_AVFilterContext preinit();

    public native AVFilter preinit(Preinit_AVFilterContext preinit_AVFilterContext);

    public native AVFilter priv_class(AVClass aVClass);

    @Const
    public native AVClass priv_class();

    public native int priv_size();

    public native AVFilter priv_size(int i);

    public native Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int process_command();

    public native AVFilter process_command(Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int);

    public native Query_formats_AVFilterContext query_formats();

    public native AVFilter query_formats(Query_formats_AVFilterContext query_formats_AVFilterContext);

    public native Uninit_AVFilterContext uninit();

    public native AVFilter uninit(Uninit_AVFilterContext uninit_AVFilterContext);

    static {
        Loader.load();
    }

    public AVFilter() {
        super((Pointer) null);
        allocate();
    }

    public AVFilter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFilter(Pointer pointer) {
        super(pointer);
    }

    public AVFilter position(long j) {
        return (AVFilter) super.position(j);
    }

    public AVFilter getPointer(long j) {
        return new AVFilter((Pointer) this).position(this.position + j);
    }

    public static class Preinit_AVFilterContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFilterContext aVFilterContext);

        static {
            Loader.load();
        }

        public Preinit_AVFilterContext(Pointer pointer) {
            super(pointer);
        }

        protected Preinit_AVFilterContext() {
            allocate();
        }
    }

    public static class Init_AVFilterContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFilterContext aVFilterContext);

        static {
            Loader.load();
        }

        public Init_AVFilterContext(Pointer pointer) {
            super(pointer);
        }

        protected Init_AVFilterContext() {
            allocate();
        }
    }

    public static class Init_dict_AVFilterContext_PointerPointer extends FunctionPointer {
        private native void allocate();

        public native int call(AVFilterContext aVFilterContext, @Cast({"AVDictionary**"}) PointerPointer pointerPointer);

        static {
            Loader.load();
        }

        public Init_dict_AVFilterContext_PointerPointer(Pointer pointer) {
            super(pointer);
        }

        protected Init_dict_AVFilterContext_PointerPointer() {
            allocate();
        }
    }

    public static class Uninit_AVFilterContext extends FunctionPointer {
        private native void allocate();

        public native void call(AVFilterContext aVFilterContext);

        static {
            Loader.load();
        }

        public Uninit_AVFilterContext(Pointer pointer) {
            super(pointer);
        }

        protected Uninit_AVFilterContext() {
            allocate();
        }
    }

    public static class Query_formats_AVFilterContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFilterContext aVFilterContext);

        static {
            Loader.load();
        }

        public Query_formats_AVFilterContext(Pointer pointer) {
            super(pointer);
        }

        protected Query_formats_AVFilterContext() {
            allocate();
        }
    }

    public static class Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int extends FunctionPointer {
        private native void allocate();

        public native int call(AVFilterContext aVFilterContext, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"char*"}) BytePointer bytePointer3, int i, int i2);

        static {
            Loader.load();
        }

        public Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int(Pointer pointer) {
            super(pointer);
        }

        protected Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int() {
            allocate();
        }
    }

    public static class Init_opaque_AVFilterContext_Pointer extends FunctionPointer {
        private native void allocate();

        public native int call(AVFilterContext aVFilterContext, Pointer pointer);

        static {
            Loader.load();
        }

        public Init_opaque_AVFilterContext_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Init_opaque_AVFilterContext_Pointer() {
            allocate();
        }
    }

    public static class Activate_AVFilterContext extends FunctionPointer {
        private native void allocate();

        public native int call(AVFilterContext aVFilterContext);

        static {
            Loader.load();
        }

        public Activate_AVFilterContext(Pointer pointer) {
            super(pointer);
        }

        protected Activate_AVFilterContext() {
            allocate();
        }
    }
}
