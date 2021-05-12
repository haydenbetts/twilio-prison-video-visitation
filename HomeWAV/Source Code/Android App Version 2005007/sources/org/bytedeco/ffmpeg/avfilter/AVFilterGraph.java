package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class AVFilterGraph extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVFilterGraph aresample_swr_opts(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer aresample_swr_opts();

    public native AVFilterGraph av_class(AVClass aVClass);

    @Const
    public native AVClass av_class();

    @Cast({"unsigned"})
    public native int disable_auto_convert();

    public native AVFilterGraph disable_auto_convert(int i);

    public native AVFilterGraph execute(avfilter_execute_func avfilter_execute_func);

    public native avfilter_execute_func execute();

    public native AVFilterContext filters(int i);

    public native AVFilterGraph filters(int i, AVFilterContext aVFilterContext);

    public native AVFilterGraph filters(PointerPointer pointerPointer);

    @Cast({"AVFilterContext**"})
    public native PointerPointer filters();

    public native AVFilterGraph internal(AVFilterGraphInternal aVFilterGraphInternal);

    public native AVFilterGraphInternal internal();

    @Cast({"unsigned"})
    public native int nb_filters();

    public native AVFilterGraph nb_filters(int i);

    public native int nb_threads();

    public native AVFilterGraph nb_threads(int i);

    public native AVFilterGraph opaque(Pointer pointer);

    public native Pointer opaque();

    public native AVFilterGraph resample_lavr_opts(BytePointer bytePointer);

    @Deprecated
    @Cast({"char*"})
    public native BytePointer resample_lavr_opts();

    public native AVFilterGraph scale_sws_opts(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer scale_sws_opts();

    public native AVFilterGraph sink_links(int i, AVFilterLink aVFilterLink);

    public native AVFilterGraph sink_links(PointerPointer pointerPointer);

    public native AVFilterLink sink_links(int i);

    @Cast({"AVFilterLink**"})
    public native PointerPointer sink_links();

    public native int sink_links_count();

    public native AVFilterGraph sink_links_count(int i);

    public native int thread_type();

    public native AVFilterGraph thread_type(int i);

    static {
        Loader.load();
    }

    public AVFilterGraph() {
        super((Pointer) null);
        allocate();
    }

    public AVFilterGraph(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFilterGraph(Pointer pointer) {
        super(pointer);
    }

    public AVFilterGraph position(long j) {
        return (AVFilterGraph) super.position(j);
    }

    public AVFilterGraph getPointer(long j) {
        return new AVFilterGraph((Pointer) this).position(this.position + j);
    }
}
