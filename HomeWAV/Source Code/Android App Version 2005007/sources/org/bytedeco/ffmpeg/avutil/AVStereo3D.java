package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVStereo3D extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int flags();

    public native AVStereo3D flags(int i);

    @Cast({"AVStereo3DType"})
    public native int type();

    public native AVStereo3D type(int i);

    @Cast({"AVStereo3DView"})
    public native int view();

    public native AVStereo3D view(int i);

    static {
        Loader.load();
    }

    public AVStereo3D() {
        super((Pointer) null);
        allocate();
    }

    public AVStereo3D(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVStereo3D(Pointer pointer) {
        super(pointer);
    }

    public AVStereo3D position(long j) {
        return (AVStereo3D) super.position(j);
    }

    public AVStereo3D getPointer(long j) {
        return new AVStereo3D((Pointer) this).position(this.position + j);
    }
}
