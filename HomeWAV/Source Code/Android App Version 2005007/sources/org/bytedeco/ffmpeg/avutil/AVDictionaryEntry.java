package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVDictionaryEntry extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVDictionaryEntry key(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer key();

    public native AVDictionaryEntry value(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer value();

    static {
        Loader.load();
    }

    public AVDictionaryEntry() {
        super((Pointer) null);
        allocate();
    }

    public AVDictionaryEntry(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVDictionaryEntry(Pointer pointer) {
        super(pointer);
    }

    public AVDictionaryEntry position(long j) {
        return (AVDictionaryEntry) super.position(j);
    }

    public AVDictionaryEntry getPointer(long j) {
        return new AVDictionaryEntry((Pointer) this).position(this.position + j);
    }
}
