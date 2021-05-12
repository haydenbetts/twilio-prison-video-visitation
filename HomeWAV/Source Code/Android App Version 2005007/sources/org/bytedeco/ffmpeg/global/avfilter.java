package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import org.bytedeco.ffmpeg.avfilter.AVABufferSinkParams;
import org.bytedeco.ffmpeg.avfilter.AVBufferSinkParams;
import org.bytedeco.ffmpeg.avfilter.AVBufferSrcParameters;
import org.bytedeco.ffmpeg.avfilter.AVFilter;
import org.bytedeco.ffmpeg.avfilter.AVFilterContext;
import org.bytedeco.ffmpeg.avfilter.AVFilterGraph;
import org.bytedeco.ffmpeg.avfilter.AVFilterInOut;
import org.bytedeco.ffmpeg.avfilter.AVFilterLink;
import org.bytedeco.ffmpeg.avfilter.AVFilterPad;
import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.NoException;

public class avfilter extends org.bytedeco.ffmpeg.presets.avfilter {
    public static final int AVFILTER_AUTO_CONVERT_ALL = 0;
    public static final int AVFILTER_AUTO_CONVERT_NONE = -1;
    public static final int AVFILTER_CMD_FLAG_FAST = 2;
    public static final int AVFILTER_CMD_FLAG_ONE = 1;
    public static final int AVFILTER_FLAG_DYNAMIC_INPUTS = 1;
    public static final int AVFILTER_FLAG_DYNAMIC_OUTPUTS = 2;
    public static final int AVFILTER_FLAG_SLICE_THREADS = 4;
    public static final int AVFILTER_FLAG_SUPPORT_TIMELINE = 196608;
    public static final int AVFILTER_FLAG_SUPPORT_TIMELINE_GENERIC = 65536;
    public static final int AVFILTER_FLAG_SUPPORT_TIMELINE_INTERNAL = 131072;
    public static final int AVFILTER_THREAD_SLICE = 1;
    public static final int AV_BUFFERSINK_FLAG_NO_REQUEST = 2;
    public static final int AV_BUFFERSINK_FLAG_PEEK = 1;
    public static final int AV_BUFFERSRC_FLAG_KEEP_REF = 8;
    public static final int AV_BUFFERSRC_FLAG_NO_CHECK_FORMAT = 1;
    public static final int AV_BUFFERSRC_FLAG_PUSH = 4;

    @NoException
    @Deprecated
    public static native AVABufferSinkParams av_abuffersink_params_alloc();

    @NoException
    @Cast({"uint64_t"})
    public static native long av_buffersink_get_channel_layout(@Const AVFilterContext aVFilterContext);

    @NoException
    public static native int av_buffersink_get_channels(@Const AVFilterContext aVFilterContext);

    @NoException
    public static native int av_buffersink_get_format(@Const AVFilterContext aVFilterContext);

    @NoException
    public static native int av_buffersink_get_frame(AVFilterContext aVFilterContext, AVFrame aVFrame);

    @NoException
    public static native int av_buffersink_get_frame_flags(AVFilterContext aVFilterContext, AVFrame aVFrame, int i);

    @NoException
    @ByVal
    public static native AVRational av_buffersink_get_frame_rate(@Const AVFilterContext aVFilterContext);

    @NoException
    public static native int av_buffersink_get_h(@Const AVFilterContext aVFilterContext);

    @NoException
    public static native AVBufferRef av_buffersink_get_hw_frames_ctx(@Const AVFilterContext aVFilterContext);

    @NoException
    @ByVal
    public static native AVRational av_buffersink_get_sample_aspect_ratio(@Const AVFilterContext aVFilterContext);

    @NoException
    public static native int av_buffersink_get_sample_rate(@Const AVFilterContext aVFilterContext);

    @NoException
    public static native int av_buffersink_get_samples(AVFilterContext aVFilterContext, AVFrame aVFrame, int i);

    @NoException
    @ByVal
    public static native AVRational av_buffersink_get_time_base(@Const AVFilterContext aVFilterContext);

    @NoException
    @Cast({"AVMediaType"})
    public static native int av_buffersink_get_type(@Const AVFilterContext aVFilterContext);

    @NoException
    public static native int av_buffersink_get_w(@Const AVFilterContext aVFilterContext);

    @NoException
    @Deprecated
    public static native AVBufferSinkParams av_buffersink_params_alloc();

    @NoException
    public static native void av_buffersink_set_frame_size(AVFilterContext aVFilterContext, @Cast({"unsigned"}) int i);

    @NoException
    public static native int av_buffersrc_add_frame(AVFilterContext aVFilterContext, AVFrame aVFrame);

    @NoException
    public static native int av_buffersrc_add_frame_flags(AVFilterContext aVFilterContext, AVFrame aVFrame, int i);

    @NoException
    public static native int av_buffersrc_close(AVFilterContext aVFilterContext, @Cast({"int64_t"}) long j, @Cast({"unsigned"}) int i);

    @NoException
    @Cast({"unsigned"})
    public static native int av_buffersrc_get_nb_failed_requests(AVFilterContext aVFilterContext);

    @NoException
    public static native AVBufferSrcParameters av_buffersrc_parameters_alloc();

    @NoException
    public static native int av_buffersrc_parameters_set(AVFilterContext aVFilterContext, AVBufferSrcParameters aVBufferSrcParameters);

    @NoException
    public static native int av_buffersrc_write_frame(AVFilterContext aVFilterContext, @Const AVFrame aVFrame);

    @NoException
    @Const
    public static native AVFilter av_filter_iterate(@ByPtrPtr @Cast({"void**"}) Pointer pointer);

    @NoException
    @Const
    public static native AVFilter av_filter_iterate(@Cast({"void**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avfilter_config_links(AVFilterContext aVFilterContext);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avfilter_configuration();

    @NoException
    public static native void avfilter_free(AVFilterContext aVFilterContext);

    @NoException
    @Const
    public static native AVFilter avfilter_get_by_name(String str);

    @NoException
    @Const
    public static native AVFilter avfilter_get_by_name(@Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    @Const
    public static native AVClass avfilter_get_class();

    @NoException
    public static native AVFilterGraph avfilter_graph_alloc();

    @NoException
    public static native AVFilterContext avfilter_graph_alloc_filter(AVFilterGraph aVFilterGraph, @Const AVFilter aVFilter, String str);

    @NoException
    public static native AVFilterContext avfilter_graph_alloc_filter(AVFilterGraph aVFilterGraph, @Const AVFilter aVFilter, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int avfilter_graph_config(AVFilterGraph aVFilterGraph, Pointer pointer);

    @NoException
    public static native int avfilter_graph_create_filter(@ByPtrPtr AVFilterContext aVFilterContext, @Const AVFilter aVFilter, String str, String str2, Pointer pointer, AVFilterGraph aVFilterGraph);

    @NoException
    public static native int avfilter_graph_create_filter(@ByPtrPtr AVFilterContext aVFilterContext, @Const AVFilter aVFilter, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, Pointer pointer, AVFilterGraph aVFilterGraph);

    @NoException
    public static native int avfilter_graph_create_filter(@Cast({"AVFilterContext**"}) PointerPointer pointerPointer, @Const AVFilter aVFilter, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, Pointer pointer, AVFilterGraph aVFilterGraph);

    @NoException
    @Cast({"char*"})
    public static native ByteBuffer avfilter_graph_dump(AVFilterGraph aVFilterGraph, String str);

    @NoException
    @Cast({"char*"})
    public static native BytePointer avfilter_graph_dump(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native void avfilter_graph_free(@ByPtrPtr AVFilterGraph aVFilterGraph);

    @NoException
    public static native void avfilter_graph_free(@Cast({"AVFilterGraph**"}) PointerPointer pointerPointer);

    @NoException
    public static native AVFilterContext avfilter_graph_get_filter(AVFilterGraph aVFilterGraph, String str);

    @NoException
    public static native AVFilterContext avfilter_graph_get_filter(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native int avfilter_graph_parse(AVFilterGraph aVFilterGraph, String str, AVFilterInOut aVFilterInOut, AVFilterInOut aVFilterInOut2, Pointer pointer);

    @NoException
    public static native int avfilter_graph_parse(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, AVFilterInOut aVFilterInOut, AVFilterInOut aVFilterInOut2, Pointer pointer);

    @NoException
    public static native int avfilter_graph_parse2(AVFilterGraph aVFilterGraph, String str, @ByPtrPtr AVFilterInOut aVFilterInOut, @ByPtrPtr AVFilterInOut aVFilterInOut2);

    @NoException
    public static native int avfilter_graph_parse2(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr AVFilterInOut aVFilterInOut, @ByPtrPtr AVFilterInOut aVFilterInOut2);

    @NoException
    public static native int avfilter_graph_parse2(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"AVFilterInOut**"}) PointerPointer pointerPointer, @Cast({"AVFilterInOut**"}) PointerPointer pointerPointer2);

    @NoException
    public static native int avfilter_graph_parse_ptr(AVFilterGraph aVFilterGraph, String str, @ByPtrPtr AVFilterInOut aVFilterInOut, @ByPtrPtr AVFilterInOut aVFilterInOut2, Pointer pointer);

    @NoException
    public static native int avfilter_graph_parse_ptr(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, @ByPtrPtr AVFilterInOut aVFilterInOut, @ByPtrPtr AVFilterInOut aVFilterInOut2, Pointer pointer);

    @NoException
    public static native int avfilter_graph_parse_ptr(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"AVFilterInOut**"}) PointerPointer pointerPointer, @Cast({"AVFilterInOut**"}) PointerPointer pointerPointer2, Pointer pointer);

    @NoException
    public static native int avfilter_graph_queue_command(AVFilterGraph aVFilterGraph, String str, String str2, String str3, int i, double d);

    @NoException
    public static native int avfilter_graph_queue_command(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i, double d);

    @NoException
    public static native int avfilter_graph_request_oldest(AVFilterGraph aVFilterGraph);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph aVFilterGraph, String str, String str2, String str3, @Cast({"char*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph aVFilterGraph, String str, String str2, String str3, @Cast({"char*"}) BytePointer bytePointer, int i, int i2);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph aVFilterGraph, String str, String str2, String str3, @Cast({"char*"}) byte[] bArr, int i, int i2);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, @Cast({"char*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, @Cast({"char*"}) BytePointer bytePointer4, int i, int i2);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph aVFilterGraph, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, @Cast({"char*"}) byte[] bArr, int i, int i2);

    @NoException
    public static native void avfilter_graph_set_auto_convert(AVFilterGraph aVFilterGraph, @Cast({"unsigned"}) int i);

    @NoException
    public static native int avfilter_init_dict(AVFilterContext aVFilterContext, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avfilter_init_dict(AVFilterContext aVFilterContext, @Cast({"AVDictionary**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avfilter_init_str(AVFilterContext aVFilterContext, String str);

    @NoException
    public static native int avfilter_init_str(AVFilterContext aVFilterContext, @Cast({"const char*"}) BytePointer bytePointer);

    @NoException
    public static native AVFilterInOut avfilter_inout_alloc();

    @NoException
    public static native void avfilter_inout_free(@ByPtrPtr AVFilterInOut aVFilterInOut);

    @NoException
    public static native void avfilter_inout_free(@Cast({"AVFilterInOut**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avfilter_insert_filter(AVFilterLink aVFilterLink, AVFilterContext aVFilterContext, @Cast({"unsigned"}) int i, @Cast({"unsigned"}) int i2);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avfilter_license();

    @NoException
    public static native int avfilter_link(AVFilterContext aVFilterContext, @Cast({"unsigned"}) int i, AVFilterContext aVFilterContext2, @Cast({"unsigned"}) int i2);

    @NoException
    public static native void avfilter_link_free(@ByPtrPtr AVFilterLink aVFilterLink);

    @NoException
    public static native void avfilter_link_free(@Cast({"AVFilterLink**"}) PointerPointer pointerPointer);

    @NoException
    @Deprecated
    public static native int avfilter_link_get_channels(AVFilterLink aVFilterLink);

    @NoException
    @Deprecated
    public static native void avfilter_link_set_closed(AVFilterLink aVFilterLink, int i);

    @NoException
    @Deprecated
    @Const
    public static native AVFilter avfilter_next(@Const AVFilter aVFilter);

    @NoException
    public static native int avfilter_pad_count(@Const AVFilterPad aVFilterPad);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avfilter_pad_get_name(@Const AVFilterPad aVFilterPad, int i);

    @NoException
    @Cast({"AVMediaType"})
    public static native int avfilter_pad_get_type(@Const AVFilterPad aVFilterPad, int i);

    @NoException
    public static native int avfilter_process_command(AVFilterContext aVFilterContext, String str, String str2, @Cast({"char*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int avfilter_process_command(AVFilterContext aVFilterContext, String str, String str2, @Cast({"char*"}) BytePointer bytePointer, int i, int i2);

    @NoException
    public static native int avfilter_process_command(AVFilterContext aVFilterContext, String str, String str2, @Cast({"char*"}) byte[] bArr, int i, int i2);

    @NoException
    public static native int avfilter_process_command(AVFilterContext aVFilterContext, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"char*"}) ByteBuffer byteBuffer, int i, int i2);

    @NoException
    public static native int avfilter_process_command(AVFilterContext aVFilterContext, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"char*"}) BytePointer bytePointer3, int i, int i2);

    @NoException
    public static native int avfilter_process_command(AVFilterContext aVFilterContext, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"char*"}) byte[] bArr, int i, int i2);

    @NoException
    @Deprecated
    public static native int avfilter_register(AVFilter aVFilter);

    @NoException
    @Deprecated
    public static native void avfilter_register_all();

    @NoException
    @Cast({"unsigned"})
    public static native int avfilter_version();

    static {
        Loader.load();
    }
}
