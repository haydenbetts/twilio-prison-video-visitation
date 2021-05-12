package org.bytedeco.javacpp;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class FloatPointer extends Pointer {
    private static final Logger logger = Logger.create(FloatPointer.class);

    private native void allocateArray(long j);

    public native float get(long j);

    public native FloatPointer get(float[] fArr, int i, int i2);

    public native FloatPointer put(long j, float f);

    public native FloatPointer put(float[] fArr, int i, int i2);

    public int sizeof() {
        return 4;
    }

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load FloatPointer: " + th);
        }
    }

    public FloatPointer(float... fArr) {
        this((long) fArr.length);
        put(fArr);
    }

    public FloatPointer(FloatBuffer floatBuffer) {
        super((Buffer) floatBuffer);
        if (floatBuffer != null && !floatBuffer.isDirect() && floatBuffer.hasArray()) {
            float[] array = floatBuffer.array();
            allocateArray((long) (array.length - floatBuffer.arrayOffset()));
            put(array, floatBuffer.arrayOffset(), array.length - floatBuffer.arrayOffset());
            position((long) floatBuffer.position());
            limit((long) floatBuffer.limit());
        }
    }

    public FloatPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new FloatPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public FloatPointer() {
    }

    public FloatPointer(Pointer pointer) {
        super(pointer);
    }

    public FloatPointer position(long j) {
        return (FloatPointer) super.position(j);
    }

    public FloatPointer limit(long j) {
        return (FloatPointer) super.limit(j);
    }

    public FloatPointer capacity(long j) {
        return (FloatPointer) super.capacity(j);
    }

    public FloatPointer getPointer(long j) {
        return new FloatPointer((Pointer) this).position(this.position + j);
    }

    public float get() {
        return get(0);
    }

    public FloatPointer put(float f) {
        return put(0, f);
    }

    public FloatPointer get(float[] fArr) {
        return get(fArr, 0, fArr.length);
    }

    public FloatPointer put(float... fArr) {
        return put(fArr, 0, fArr.length);
    }

    public final FloatBuffer asBuffer() {
        return asByteBuffer().asFloatBuffer();
    }
}
