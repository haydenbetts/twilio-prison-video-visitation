package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVFrameSideData extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVBufferRef buf();

    public native AVFrameSideData buf(AVBufferRef aVBufferRef);

    public native AVFrameSideData data(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer data();

    public native AVDictionary metadata();

    public native AVFrameSideData metadata(AVDictionary aVDictionary);

    public native int size();

    public native AVFrameSideData size(int i);

    @Cast({"AVFrameSideDataType"})
    public native int type();

    public native AVFrameSideData type(int i);

    static {
        Loader.load();
    }

    public AVFrameSideData() {
        super((Pointer) null);
        allocate();
    }

    public AVFrameSideData(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFrameSideData(Pointer pointer) {
        super(pointer);
    }

    public AVFrameSideData position(long j) {
        return (AVFrameSideData) super.position(j);
    }

    public AVFrameSideData getPointer(long j) {
        return new AVFrameSideData((Pointer) this).position(this.position + j);
    }
}
