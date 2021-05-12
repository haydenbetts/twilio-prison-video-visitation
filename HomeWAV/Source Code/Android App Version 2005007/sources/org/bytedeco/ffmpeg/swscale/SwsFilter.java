package org.bytedeco.ffmpeg.swscale;

import org.bytedeco.ffmpeg.presets.swscale;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {swscale.class})
public class SwsFilter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native SwsFilter chrH(SwsVector swsVector);

    public native SwsVector chrH();

    public native SwsFilter chrV(SwsVector swsVector);

    public native SwsVector chrV();

    public native SwsFilter lumH(SwsVector swsVector);

    public native SwsVector lumH();

    public native SwsFilter lumV(SwsVector swsVector);

    public native SwsVector lumV();

    static {
        Loader.load();
    }

    public SwsFilter() {
        super((Pointer) null);
        allocate();
    }

    public SwsFilter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SwsFilter(Pointer pointer) {
        super(pointer);
    }

    public SwsFilter position(long j) {
        return (SwsFilter) super.position(j);
    }

    public SwsFilter getPointer(long j) {
        return new SwsFilter((Pointer) this).position(this.position + j);
    }
}
