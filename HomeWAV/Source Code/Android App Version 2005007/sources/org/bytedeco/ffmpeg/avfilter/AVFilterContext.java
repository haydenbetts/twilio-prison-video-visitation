package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class AVFilterContext extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVFilterContext av_class(AVClass aVClass);

    @Const
    public native AVClass av_class();

    public native AVFilterContext command_queue(Pointer pointer);

    @Cast({"AVFilterCommand*"})
    public native Pointer command_queue();

    public native AVFilterContext enable(Pointer pointer);

    public native Pointer enable();

    public native AVFilterContext enable_str(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer enable_str();

    public native int extra_hw_frames();

    public native AVFilterContext extra_hw_frames(int i);

    @Const
    public native AVFilter filter();

    public native AVFilterContext filter(AVFilter aVFilter);

    public native AVFilterContext graph(AVFilterGraph aVFilterGraph);

    public native AVFilterGraph graph();

    public native AVFilterContext hw_device_ctx(AVBufferRef aVBufferRef);

    public native AVBufferRef hw_device_ctx();

    public native AVFilterContext input_pads(AVFilterPad aVFilterPad);

    public native AVFilterPad input_pads();

    public native AVFilterContext inputs(int i, AVFilterLink aVFilterLink);

    public native AVFilterContext inputs(PointerPointer pointerPointer);

    public native AVFilterLink inputs(int i);

    @Cast({"AVFilterLink**"})
    public native PointerPointer inputs();

    public native AVFilterContext internal(AVFilterInternal aVFilterInternal);

    public native AVFilterInternal internal();

    public native int is_disabled();

    public native AVFilterContext is_disabled(int i);

    public native AVFilterContext name(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer name();

    @Cast({"unsigned"})
    public native int nb_inputs();

    public native AVFilterContext nb_inputs(int i);

    @Cast({"unsigned"})
    public native int nb_outputs();

    public native AVFilterContext nb_outputs(int i);

    public native int nb_threads();

    public native AVFilterContext nb_threads(int i);

    public native AVFilterContext output_pads(AVFilterPad aVFilterPad);

    public native AVFilterPad output_pads();

    public native AVFilterContext outputs(int i, AVFilterLink aVFilterLink);

    public native AVFilterContext outputs(PointerPointer pointerPointer);

    public native AVFilterLink outputs(int i);

    @Cast({"AVFilterLink**"})
    public native PointerPointer outputs();

    public native AVFilterContext priv(Pointer pointer);

    public native Pointer priv();

    @Cast({"unsigned"})
    public native int ready();

    public native AVFilterContext ready(int i);

    public native int thread_type();

    public native AVFilterContext thread_type(int i);

    public native AVFilterContext var_values(DoublePointer doublePointer);

    public native DoublePointer var_values();

    static {
        Loader.load();
    }

    public AVFilterContext() {
        super((Pointer) null);
        allocate();
    }

    public AVFilterContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFilterContext(Pointer pointer) {
        super(pointer);
    }

    public AVFilterContext position(long j) {
        return (AVFilterContext) super.position(j);
    }

    public AVFilterContext getPointer(long j) {
        return new AVFilterContext((Pointer) this).position(this.position + j);
    }
}
