package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVClass extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"AVClassCategory"})
    public native int category();

    public native AVClass category(int i);

    public native Child_class_next_AVClass child_class_next();

    public native AVClass child_class_next(Child_class_next_AVClass child_class_next_AVClass);

    public native Child_next_Pointer_Pointer child_next();

    public native AVClass child_next(Child_next_Pointer_Pointer child_next_Pointer_Pointer);

    public native AVClass class_name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer class_name();

    public native Get_category_Pointer get_category();

    public native AVClass get_category(Get_category_Pointer get_category_Pointer);

    public native Item_name_Pointer item_name();

    public native AVClass item_name(Item_name_Pointer item_name_Pointer);

    public native int log_level_offset_offset();

    public native AVClass log_level_offset_offset(int i);

    public native AVClass option(AVOption aVOption);

    @Const
    public native AVOption option();

    public native int parent_log_context_offset();

    public native AVClass parent_log_context_offset(int i);

    public native Query_ranges_PointerPointer_Pointer_BytePointer_int query_ranges();

    public native AVClass query_ranges(Query_ranges_PointerPointer_Pointer_BytePointer_int query_ranges_PointerPointer_Pointer_BytePointer_int);

    public native int version();

    public native AVClass version(int i);

    static {
        Loader.load();
    }

    public AVClass() {
        super((Pointer) null);
        allocate();
    }

    public AVClass(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVClass(Pointer pointer) {
        super(pointer);
    }

    public AVClass position(long j) {
        return (AVClass) super.position(j);
    }

    public AVClass getPointer(long j) {
        return new AVClass((Pointer) this).position(this.position + j);
    }

    public static class Item_name_Pointer extends FunctionPointer {
        private native void allocate();

        @Cast({"const char*"})
        public native BytePointer call(Pointer pointer);

        static {
            Loader.load();
        }

        public Item_name_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Item_name_Pointer() {
            allocate();
        }
    }

    public static class Child_next_Pointer_Pointer extends FunctionPointer {
        private native void allocate();

        public native Pointer call(Pointer pointer, Pointer pointer2);

        static {
            Loader.load();
        }

        public Child_next_Pointer_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Child_next_Pointer_Pointer() {
            allocate();
        }
    }

    public static class Child_class_next_AVClass extends FunctionPointer {
        private native void allocate();

        @Const
        public native AVClass call(@Const AVClass aVClass);

        static {
            Loader.load();
        }

        public Child_class_next_AVClass(Pointer pointer) {
            super(pointer);
        }

        protected Child_class_next_AVClass() {
            allocate();
        }
    }

    public static class Get_category_Pointer extends FunctionPointer {
        private native void allocate();

        @Cast({"AVClassCategory"})
        public native int call(Pointer pointer);

        static {
            Loader.load();
        }

        public Get_category_Pointer(Pointer pointer) {
            super(pointer);
        }

        protected Get_category_Pointer() {
            allocate();
        }
    }

    public static class Query_ranges_PointerPointer_Pointer_BytePointer_int extends FunctionPointer {
        private native void allocate();

        public native int call(@Cast({"AVOptionRanges**"}) PointerPointer pointerPointer, Pointer pointer, @Cast({"const char*"}) BytePointer bytePointer, int i);

        static {
            Loader.load();
        }

        public Query_ranges_PointerPointer_Pointer_BytePointer_int(Pointer pointer) {
            super(pointer);
        }

        protected Query_ranges_PointerPointer_Pointer_BytePointer_int() {
            allocate();
        }
    }
}
