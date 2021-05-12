package org.bytedeco.javacpp;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class BooleanPointer extends Pointer {
    private static final Logger logger = Logger.create(BooleanPointer.class);

    private native void allocateArray(long j);

    public native BooleanPointer get(boolean[] zArr, int i, int i2);

    public native boolean get(long j);

    public native BooleanPointer put(long j, boolean z);

    public native BooleanPointer put(boolean[] zArr, int i, int i2);

    public int sizeof() {
        return 1;
    }

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load BooleanPointer: " + th);
        }
    }

    public BooleanPointer(boolean... zArr) {
        this((long) zArr.length);
        put(zArr);
    }

    public BooleanPointer(ByteBuffer byteBuffer) {
        super((Buffer) byteBuffer);
        if (byteBuffer != null && !byteBuffer.isDirect() && byteBuffer.hasArray()) {
            byte[] array = byteBuffer.array();
            allocateArray((long) (array.length - byteBuffer.arrayOffset()));
            for (int arrayOffset = byteBuffer.arrayOffset(); arrayOffset < array.length; arrayOffset++) {
                put((long) (arrayOffset - byteBuffer.arrayOffset()), array[arrayOffset] != 0);
            }
            position((long) byteBuffer.position());
            limit((long) byteBuffer.limit());
        }
    }

    public BooleanPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new BooleanPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public BooleanPointer() {
    }

    public BooleanPointer(Pointer pointer) {
        super(pointer);
    }

    public BooleanPointer position(long j) {
        return (BooleanPointer) super.position(j);
    }

    public BooleanPointer limit(long j) {
        return (BooleanPointer) super.limit(j);
    }

    public BooleanPointer capacity(long j) {
        return (BooleanPointer) super.capacity(j);
    }

    public BooleanPointer getPointer(long j) {
        return new BooleanPointer((Pointer) this).position(this.position + j);
    }

    public boolean get() {
        return get(0);
    }

    public BooleanPointer put(boolean z) {
        return put(0, z);
    }

    public BooleanPointer get(boolean[] zArr) {
        return get(zArr, 0, zArr.length);
    }

    public BooleanPointer put(boolean... zArr) {
        return put(zArr, 0, zArr.length);
    }
}
