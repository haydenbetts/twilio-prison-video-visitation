package org.bytedeco.javacpp;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class DoublePointer extends Pointer {
    private static final Logger logger = Logger.create(DoublePointer.class);

    private native void allocateArray(long j);

    public native double get(long j);

    public native DoublePointer get(double[] dArr, int i, int i2);

    public native DoublePointer put(long j, double d);

    public native DoublePointer put(double[] dArr, int i, int i2);

    public int sizeof() {
        return 8;
    }

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load DoublePointer: " + th);
        }
    }

    public DoublePointer(double... dArr) {
        this((long) dArr.length);
        put(dArr);
    }

    public DoublePointer(DoubleBuffer doubleBuffer) {
        super((Buffer) doubleBuffer);
        if (doubleBuffer != null && !doubleBuffer.isDirect() && doubleBuffer.hasArray()) {
            double[] array = doubleBuffer.array();
            allocateArray((long) (array.length - doubleBuffer.arrayOffset()));
            put(array, doubleBuffer.arrayOffset(), array.length - doubleBuffer.arrayOffset());
            position((long) doubleBuffer.position());
            limit((long) doubleBuffer.limit());
        }
    }

    public DoublePointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new DoublePointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public DoublePointer() {
    }

    public DoublePointer(Pointer pointer) {
        super(pointer);
    }

    public DoublePointer position(long j) {
        return (DoublePointer) super.position(j);
    }

    public DoublePointer limit(long j) {
        return (DoublePointer) super.limit(j);
    }

    public DoublePointer capacity(long j) {
        return (DoublePointer) super.capacity(j);
    }

    public DoublePointer getPointer(long j) {
        return new DoublePointer((Pointer) this).position(this.position + j);
    }

    public double get() {
        return get(0);
    }

    public DoublePointer put(double d) {
        return put(0, d);
    }

    public DoublePointer get(double[] dArr) {
        return get(dArr, 0, dArr.length);
    }

    public DoublePointer put(double... dArr) {
        return put(dArr, 0, dArr.length);
    }

    public final DoubleBuffer asBuffer() {
        return asByteBuffer().asDoubleBuffer();
    }
}
