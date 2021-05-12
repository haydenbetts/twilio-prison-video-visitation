package org.bytedeco.javacpp;

import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
@Name({"bool"})
public class BoolPointer extends Pointer {
    private static final Logger logger = Logger.create(BoolPointer.class);

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean get(long j);

    public native BoolPointer put(long j, boolean z);

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load BoolPointer: " + th);
        }
    }

    public BoolPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new BoolPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public BoolPointer() {
    }

    public BoolPointer(Pointer pointer) {
        super(pointer);
    }

    public BoolPointer position(long j) {
        return (BoolPointer) super.position(j);
    }

    public BoolPointer limit(long j) {
        return (BoolPointer) super.limit(j);
    }

    public BoolPointer capacity(long j) {
        return (BoolPointer) super.capacity(j);
    }

    public BoolPointer getPointer(long j) {
        return new BoolPointer((Pointer) this).position(this.position + j);
    }

    public boolean get() {
        return get(0);
    }

    public BoolPointer put(boolean z) {
        return put(0, z);
    }
}
