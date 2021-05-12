package org.bytedeco.ffmpeg.global;

import org.bytedeco.ffmpeg.avdevice.AVDeviceCapabilitiesQuery;
import org.bytedeco.ffmpeg.avdevice.AVDeviceInfoList;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVInputFormat;
import org.bytedeco.ffmpeg.avformat.AVOutputFormat;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.NoException;

public class avdevice extends org.bytedeco.ffmpeg.presets.avdevice {
    public static final int AV_APP_TO_DEV_GET_MUTE = AV_APP_TO_DEV_GET_MUTE();
    public static final int AV_APP_TO_DEV_GET_VOLUME = AV_APP_TO_DEV_GET_VOLUME();
    public static final int AV_APP_TO_DEV_MUTE = AV_APP_TO_DEV_MUTE();
    public static final int AV_APP_TO_DEV_NONE = AV_APP_TO_DEV_NONE();
    public static final int AV_APP_TO_DEV_PAUSE = AV_APP_TO_DEV_PAUSE();
    public static final int AV_APP_TO_DEV_PLAY = AV_APP_TO_DEV_PLAY();
    public static final int AV_APP_TO_DEV_SET_VOLUME = AV_APP_TO_DEV_SET_VOLUME();
    public static final int AV_APP_TO_DEV_TOGGLE_MUTE = AV_APP_TO_DEV_TOGGLE_MUTE();
    public static final int AV_APP_TO_DEV_TOGGLE_PAUSE = AV_APP_TO_DEV_TOGGLE_PAUSE();
    public static final int AV_APP_TO_DEV_UNMUTE = AV_APP_TO_DEV_UNMUTE();
    public static final int AV_APP_TO_DEV_WINDOW_REPAINT = AV_APP_TO_DEV_WINDOW_REPAINT();
    public static final int AV_APP_TO_DEV_WINDOW_SIZE = AV_APP_TO_DEV_WINDOW_SIZE();
    public static final int AV_DEV_TO_APP_BUFFER_OVERFLOW = AV_DEV_TO_APP_BUFFER_OVERFLOW();
    public static final int AV_DEV_TO_APP_BUFFER_READABLE = AV_DEV_TO_APP_BUFFER_READABLE();
    public static final int AV_DEV_TO_APP_BUFFER_UNDERFLOW = AV_DEV_TO_APP_BUFFER_UNDERFLOW();
    public static final int AV_DEV_TO_APP_BUFFER_WRITABLE = AV_DEV_TO_APP_BUFFER_WRITABLE();
    public static final int AV_DEV_TO_APP_CREATE_WINDOW_BUFFER = AV_DEV_TO_APP_CREATE_WINDOW_BUFFER();
    public static final int AV_DEV_TO_APP_DESTROY_WINDOW_BUFFER = AV_DEV_TO_APP_DESTROY_WINDOW_BUFFER();
    public static final int AV_DEV_TO_APP_DISPLAY_WINDOW_BUFFER = AV_DEV_TO_APP_DISPLAY_WINDOW_BUFFER();
    public static final int AV_DEV_TO_APP_MUTE_STATE_CHANGED = AV_DEV_TO_APP_MUTE_STATE_CHANGED();
    public static final int AV_DEV_TO_APP_NONE = AV_DEV_TO_APP_NONE();
    public static final int AV_DEV_TO_APP_PREPARE_WINDOW_BUFFER = AV_DEV_TO_APP_PREPARE_WINDOW_BUFFER();
    public static final int AV_DEV_TO_APP_VOLUME_LEVEL_CHANGED = AV_DEV_TO_APP_VOLUME_LEVEL_CHANGED();

    @MemberGetter
    public static native int AV_APP_TO_DEV_GET_MUTE();

    @MemberGetter
    public static native int AV_APP_TO_DEV_GET_VOLUME();

    @MemberGetter
    public static native int AV_APP_TO_DEV_MUTE();

    @MemberGetter
    public static native int AV_APP_TO_DEV_NONE();

    @MemberGetter
    public static native int AV_APP_TO_DEV_PAUSE();

    @MemberGetter
    public static native int AV_APP_TO_DEV_PLAY();

    @MemberGetter
    public static native int AV_APP_TO_DEV_SET_VOLUME();

    @MemberGetter
    public static native int AV_APP_TO_DEV_TOGGLE_MUTE();

    @MemberGetter
    public static native int AV_APP_TO_DEV_TOGGLE_PAUSE();

    @MemberGetter
    public static native int AV_APP_TO_DEV_UNMUTE();

    @MemberGetter
    public static native int AV_APP_TO_DEV_WINDOW_REPAINT();

    @MemberGetter
    public static native int AV_APP_TO_DEV_WINDOW_SIZE();

    @MemberGetter
    public static native int AV_DEV_TO_APP_BUFFER_OVERFLOW();

    @MemberGetter
    public static native int AV_DEV_TO_APP_BUFFER_READABLE();

    @MemberGetter
    public static native int AV_DEV_TO_APP_BUFFER_UNDERFLOW();

    @MemberGetter
    public static native int AV_DEV_TO_APP_BUFFER_WRITABLE();

    @MemberGetter
    public static native int AV_DEV_TO_APP_CREATE_WINDOW_BUFFER();

    @MemberGetter
    public static native int AV_DEV_TO_APP_DESTROY_WINDOW_BUFFER();

    @MemberGetter
    public static native int AV_DEV_TO_APP_DISPLAY_WINDOW_BUFFER();

    @MemberGetter
    public static native int AV_DEV_TO_APP_MUTE_STATE_CHANGED();

    @MemberGetter
    public static native int AV_DEV_TO_APP_NONE();

    @MemberGetter
    public static native int AV_DEV_TO_APP_PREPARE_WINDOW_BUFFER();

    @MemberGetter
    public static native int AV_DEV_TO_APP_VOLUME_LEVEL_CHANGED();

    @NoException
    public static native AVInputFormat av_input_audio_device_next(AVInputFormat aVInputFormat);

    @NoException
    public static native AVInputFormat av_input_video_device_next(AVInputFormat aVInputFormat);

    @NoException
    public static native AVOutputFormat av_output_audio_device_next(AVOutputFormat aVOutputFormat);

    @NoException
    public static native AVOutputFormat av_output_video_device_next(AVOutputFormat aVOutputFormat);

    @NoException
    public static native int avdevice_app_to_dev_control_message(AVFormatContext aVFormatContext, @Cast({"AVAppToDevMessageType"}) int i, Pointer pointer, @Cast({"size_t"}) long j);

    @NoException
    public static native int avdevice_capabilities_create(@ByPtrPtr AVDeviceCapabilitiesQuery aVDeviceCapabilitiesQuery, AVFormatContext aVFormatContext, @ByPtrPtr AVDictionary aVDictionary);

    @NoException
    public static native int avdevice_capabilities_create(@Cast({"AVDeviceCapabilitiesQuery**"}) PointerPointer pointerPointer, AVFormatContext aVFormatContext, @Cast({"AVDictionary**"}) PointerPointer pointerPointer2);

    @NoException
    public static native void avdevice_capabilities_free(@ByPtrPtr AVDeviceCapabilitiesQuery aVDeviceCapabilitiesQuery, AVFormatContext aVFormatContext);

    @NoException
    public static native void avdevice_capabilities_free(@Cast({"AVDeviceCapabilitiesQuery**"}) PointerPointer pointerPointer, AVFormatContext aVFormatContext);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avdevice_configuration();

    @NoException
    public static native int avdevice_dev_to_app_control_message(AVFormatContext aVFormatContext, @Cast({"AVDevToAppMessageType"}) int i, Pointer pointer, @Cast({"size_t"}) long j);

    @NoException
    public static native void avdevice_free_list_devices(@ByPtrPtr AVDeviceInfoList aVDeviceInfoList);

    @NoException
    public static native void avdevice_free_list_devices(@Cast({"AVDeviceInfoList**"}) PointerPointer pointerPointer);

    @NoException
    @Cast({"const char*"})
    public static native BytePointer avdevice_license();

    @NoException
    public static native int avdevice_list_devices(AVFormatContext aVFormatContext, @ByPtrPtr AVDeviceInfoList aVDeviceInfoList);

    @NoException
    public static native int avdevice_list_devices(AVFormatContext aVFormatContext, @Cast({"AVDeviceInfoList**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avdevice_list_input_sources(AVInputFormat aVInputFormat, String str, AVDictionary aVDictionary, @ByPtrPtr AVDeviceInfoList aVDeviceInfoList);

    @NoException
    public static native int avdevice_list_input_sources(AVInputFormat aVInputFormat, @Cast({"const char*"}) BytePointer bytePointer, AVDictionary aVDictionary, @ByPtrPtr AVDeviceInfoList aVDeviceInfoList);

    @NoException
    public static native int avdevice_list_input_sources(AVInputFormat aVInputFormat, @Cast({"const char*"}) BytePointer bytePointer, AVDictionary aVDictionary, @Cast({"AVDeviceInfoList**"}) PointerPointer pointerPointer);

    @NoException
    public static native int avdevice_list_output_sinks(AVOutputFormat aVOutputFormat, String str, AVDictionary aVDictionary, @ByPtrPtr AVDeviceInfoList aVDeviceInfoList);

    @NoException
    public static native int avdevice_list_output_sinks(AVOutputFormat aVOutputFormat, @Cast({"const char*"}) BytePointer bytePointer, AVDictionary aVDictionary, @ByPtrPtr AVDeviceInfoList aVDeviceInfoList);

    @NoException
    public static native int avdevice_list_output_sinks(AVOutputFormat aVOutputFormat, @Cast({"const char*"}) BytePointer bytePointer, AVDictionary aVDictionary, @Cast({"AVDeviceInfoList**"}) PointerPointer pointerPointer);

    @NoException
    public static native void avdevice_register_all();

    @NoException
    @Cast({"unsigned"})
    public static native int avdevice_version();

    static {
        Loader.load();
    }
}
