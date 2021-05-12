package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVIODirContext extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVIODirContext url_context(Pointer pointer);

    @Cast({"URLContext*"})
    public native Pointer url_context();

    static {
        Loader.load();
    }

    public AVIODirContext() {
        super((Pointer) null);
        allocate();
    }

    public AVIODirContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVIODirContext(Pointer pointer) {
        super(pointer);
    }

    public AVIODirContext position(long j) {
        return (AVIODirContext) super.position(j);
    }

    public AVIODirContext getPointer(long j) {
        return new AVIODirContext((Pointer) this).position(this.position + j);
    }
}
