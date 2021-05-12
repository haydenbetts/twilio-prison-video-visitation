package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVSubtitleRect extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVSubtitleRect ass(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer ass();

    public native AVSubtitleRect data(int i, BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer data(int i);

    @MemberGetter
    @Cast({"uint8_t**"})
    public native PointerPointer data();

    public native int flags();

    public native AVSubtitleRect flags(int i);

    public native int h();

    public native AVSubtitleRect h(int i);

    public native int linesize(int i);

    public native AVSubtitleRect linesize(int i, int i2);

    @MemberGetter
    public native IntPointer linesize();

    public native int nb_colors();

    public native AVSubtitleRect nb_colors(int i);

    @ByRef
    @Deprecated
    public native AVPicture pict();

    public native AVSubtitleRect pict(AVPicture aVPicture);

    public native AVSubtitleRect text(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer text();

    @Cast({"AVSubtitleType"})
    public native int type();

    public native AVSubtitleRect type(int i);

    public native int w();

    public native AVSubtitleRect w(int i);

    public native int x();

    public native AVSubtitleRect x(int i);

    public native int y();

    public native AVSubtitleRect y(int i);

    static {
        Loader.load();
    }

    public AVSubtitleRect() {
        super((Pointer) null);
        allocate();
    }

    public AVSubtitleRect(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVSubtitleRect(Pointer pointer) {
        super(pointer);
    }

    public AVSubtitleRect position(long j) {
        return (AVSubtitleRect) super.position(j);
    }

    public AVSubtitleRect getPointer(long j) {
        return new AVSubtitleRect((Pointer) this).position(this.position + j);
    }
}
