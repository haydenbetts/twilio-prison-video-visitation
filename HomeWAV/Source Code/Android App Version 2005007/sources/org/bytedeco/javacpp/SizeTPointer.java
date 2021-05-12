package org.bytedeco.javacpp;

import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
@Name({"size_t"})
public class SizeTPointer extends Pointer {
    private static final Logger logger = Logger.create(SizeTPointer.class);

    private native void allocateArray(long j);

    @Cast({"size_t"})
    public native long get(long j);

    public native SizeTPointer put(long j, long j2);

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load SizeTPointer: " + th);
        }
    }

    public SizeTPointer(long... jArr) {
        this((long) jArr.length);
        put(jArr);
    }

    public SizeTPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new SizeTPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public SizeTPointer() {
    }

    public SizeTPointer(Pointer pointer) {
        super(pointer);
    }

    public SizeTPointer position(long j) {
        return (SizeTPointer) super.position(j);
    }

    public SizeTPointer limit(long j) {
        return (SizeTPointer) super.limit(j);
    }

    public SizeTPointer capacity(long j) {
        return (SizeTPointer) super.capacity(j);
    }

    public SizeTPointer getPointer(long j) {
        return new SizeTPointer((Pointer) this).position(this.position + j);
    }

    public long get() {
        return get(0);
    }

    public SizeTPointer put(long j) {
        return put(0, j);
    }

    public SizeTPointer get(long[] jArr) {
        return get(jArr, 0, jArr.length);
    }

    public SizeTPointer put(long... jArr) {
        return put(jArr, 0, jArr.length);
    }

    public SizeTPointer get(long[] jArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            jArr[i3] = get((long) i3);
        }
        return this;
    }

    public SizeTPointer put(long[] jArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            put((long) i3, jArr[i3]);
        }
        return this;
    }
}
