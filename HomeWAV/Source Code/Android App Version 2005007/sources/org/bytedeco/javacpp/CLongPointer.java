package org.bytedeco.javacpp;

import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
@Name({"long"})
public class CLongPointer extends Pointer {
    private static final Logger logger = Logger.create(CLongPointer.class);

    private native void allocateArray(long j);

    @Cast({"long"})
    public native long get(long j);

    public native CLongPointer put(long j, long j2);

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load CLongPointer: " + th);
        }
    }

    public CLongPointer(long... jArr) {
        this((long) jArr.length);
        put(jArr);
    }

    public CLongPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new CLongPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public CLongPointer() {
    }

    public CLongPointer(Pointer pointer) {
        super(pointer);
    }

    public CLongPointer position(long j) {
        return (CLongPointer) super.position(j);
    }

    public CLongPointer limit(long j) {
        return (CLongPointer) super.limit(j);
    }

    public CLongPointer capacity(long j) {
        return (CLongPointer) super.capacity(j);
    }

    public CLongPointer getPointer(long j) {
        return new CLongPointer((Pointer) this).position(this.position + j);
    }

    public long get() {
        return get(0);
    }

    public CLongPointer put(long j) {
        return put(0, j);
    }

    public CLongPointer get(long[] jArr) {
        return get(jArr, 0, jArr.length);
    }

    public CLongPointer put(long... jArr) {
        return put(jArr, 0, jArr.length);
    }

    public CLongPointer get(long[] jArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            jArr[i3] = get((long) i3);
        }
        return this;
    }

    public CLongPointer put(long[] jArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            put((long) i3, jArr[i3]);
        }
        return this;
    }
}
