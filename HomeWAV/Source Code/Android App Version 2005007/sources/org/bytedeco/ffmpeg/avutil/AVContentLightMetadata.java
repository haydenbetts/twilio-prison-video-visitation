package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVContentLightMetadata extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"unsigned"})
    public native int MaxCLL();

    public native AVContentLightMetadata MaxCLL(int i);

    @Cast({"unsigned"})
    public native int MaxFALL();

    public native AVContentLightMetadata MaxFALL(int i);

    static {
        Loader.load();
    }

    public AVContentLightMetadata() {
        super((Pointer) null);
        allocate();
    }

    public AVContentLightMetadata(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVContentLightMetadata(Pointer pointer) {
        super(pointer);
    }

    public AVContentLightMetadata position(long j) {
        return (AVContentLightMetadata) super.position(j);
    }

    public AVContentLightMetadata getPointer(long j) {
        return new AVContentLightMetadata((Pointer) this).position(this.position + j);
    }
}
