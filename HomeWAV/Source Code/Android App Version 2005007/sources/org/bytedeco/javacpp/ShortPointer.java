package org.bytedeco.javacpp;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class ShortPointer extends Pointer {
    private static final Logger logger = Logger.create(ShortPointer.class);

    private native void allocateArray(long j);

    public native ShortPointer get(short[] sArr, int i, int i2);

    public native short get(long j);

    public native ShortPointer put(long j, short s);

    public native ShortPointer put(short[] sArr, int i, int i2);

    public int sizeof() {
        return 2;
    }

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load ShortPointer: " + th);
        }
    }

    public ShortPointer(short... sArr) {
        this((long) sArr.length);
        put(sArr);
    }

    public ShortPointer(ShortBuffer shortBuffer) {
        super((Buffer) shortBuffer);
        if (shortBuffer != null && !shortBuffer.isDirect() && shortBuffer.hasArray()) {
            short[] array = shortBuffer.array();
            allocateArray((long) (array.length - shortBuffer.arrayOffset()));
            put(array, shortBuffer.arrayOffset(), array.length - shortBuffer.arrayOffset());
            position((long) shortBuffer.position());
            limit((long) shortBuffer.limit());
        }
    }

    public ShortPointer(long j) {
        try {
            allocateArray(j);
            if (j <= 0) {
                return;
            }
            if (this.address == 0) {
                throw new OutOfMemoryError("Native allocator returned address == 0");
            }
        } catch (UnsatisfiedLinkError e) {
            throw new RuntimeException("No native JavaCPP library in memory. (Has Loader.load() been called?)", e);
        } catch (OutOfMemoryError e2) {
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new ShortPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public ShortPointer() {
    }

    public ShortPointer(Pointer pointer) {
        super(pointer);
    }

    public ShortPointer position(long j) {
        return (ShortPointer) super.position(j);
    }

    public ShortPointer limit(long j) {
        return (ShortPointer) super.limit(j);
    }

    public ShortPointer capacity(long j) {
        return (ShortPointer) super.capacity(j);
    }

    public ShortPointer getPointer(long j) {
        return new ShortPointer((Pointer) this).position(this.position + j);
    }

    public short get() {
        return get(0);
    }

    public ShortPointer put(short s) {
        return put(0, s);
    }

    public ShortPointer get(short[] sArr) {
        return get(sArr, 0, sArr.length);
    }

    public ShortPointer put(short... sArr) {
        return put(sArr, 0, sArr.length);
    }

    public final ShortBuffer asBuffer() {
        return asByteBuffer().asShortBuffer();
    }
}
