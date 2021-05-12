package org.bytedeco.javacpp;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class LongPointer extends Pointer {
    private static final Logger logger = Logger.create(LongPointer.class);

    private native void allocateArray(long j);

    public native long get(long j);

    public native LongPointer get(long[] jArr, int i, int i2);

    public native LongPointer put(long j, long j2);

    public native LongPointer put(long[] jArr, int i, int i2);

    public int sizeof() {
        return 8;
    }

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load LongPointer: " + th);
        }
    }

    public LongPointer(long... jArr) {
        this((long) jArr.length);
        put(jArr);
    }

    public LongPointer(LongBuffer longBuffer) {
        super((Buffer) longBuffer);
        if (longBuffer != null && !longBuffer.isDirect() && longBuffer.hasArray()) {
            long[] array = longBuffer.array();
            allocateArray((long) (array.length - longBuffer.arrayOffset()));
            put(array, longBuffer.arrayOffset(), array.length - longBuffer.arrayOffset());
            position((long) longBuffer.position());
            limit((long) longBuffer.limit());
        }
    }

    public LongPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new LongPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public LongPointer() {
    }

    public LongPointer(Pointer pointer) {
        super(pointer);
    }

    public LongPointer position(long j) {
        return (LongPointer) super.position(j);
    }

    public LongPointer limit(long j) {
        return (LongPointer) super.limit(j);
    }

    public LongPointer capacity(long j) {
        return (LongPointer) super.capacity(j);
    }

    public LongPointer getPointer(long j) {
        return new LongPointer((Pointer) this).position(this.position + j);
    }

    public long get() {
        return get(0);
    }

    public LongPointer put(long j) {
        return put(0, j);
    }

    public LongPointer get(long[] jArr) {
        return get(jArr, 0, jArr.length);
    }

    public LongPointer put(long... jArr) {
        return put(jArr, 0, jArr.length);
    }

    public final LongBuffer asBuffer() {
        return asByteBuffer().asLongBuffer();
    }
}
