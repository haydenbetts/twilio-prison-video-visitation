package org.bytedeco.javacpp;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class IntPointer extends Pointer {
    private static final Logger logger = Logger.create(IntPointer.class);

    private native void allocateArray(long j);

    public native int get(long j);

    public native IntPointer get(int[] iArr, int i, int i2);

    public native IntPointer put(long j, int i);

    public native IntPointer put(int[] iArr, int i, int i2);

    public int sizeof() {
        return 4;
    }

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load IntPointer: " + th);
        }
    }

    public IntPointer(String str) {
        this((long) (str.length() + 1));
        putString(str);
    }

    public IntPointer(int... iArr) {
        this((long) iArr.length);
        put(iArr);
    }

    public IntPointer(IntBuffer intBuffer) {
        super((Buffer) intBuffer);
        if (intBuffer != null && !intBuffer.isDirect() && intBuffer.hasArray()) {
            int[] array = intBuffer.array();
            allocateArray((long) (array.length - intBuffer.arrayOffset()));
            put(array, intBuffer.arrayOffset(), array.length - intBuffer.arrayOffset());
            position((long) intBuffer.position());
            limit((long) intBuffer.limit());
        }
    }

    public IntPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new IntPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public IntPointer() {
    }

    public IntPointer(Pointer pointer) {
        super(pointer);
    }

    public IntPointer position(long j) {
        return (IntPointer) super.position(j);
    }

    public IntPointer limit(long j) {
        return (IntPointer) super.limit(j);
    }

    public IntPointer capacity(long j) {
        return (IntPointer) super.capacity(j);
    }

    public IntPointer getPointer(long j) {
        return new IntPointer((Pointer) this).position(this.position + j);
    }

    public int[] getStringCodePoints() {
        if (this.limit > this.position) {
            int[] iArr = new int[((int) Math.min(this.limit - this.position, 2147483647L))];
            get(iArr);
            return iArr;
        }
        int[] iArr2 = new int[16];
        int i = 0;
        while (true) {
            int i2 = get((long) i);
            iArr2[i] = i2;
            if (i2 != 0) {
                i++;
                if (i >= iArr2.length) {
                    int[] iArr3 = new int[(iArr2.length * 2)];
                    System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
                    iArr2 = iArr3;
                }
            } else {
                int[] iArr4 = new int[i];
                System.arraycopy(iArr2, 0, iArr4, 0, i);
                return iArr4;
            }
        }
    }

    public String getString() {
        int[] stringCodePoints = getStringCodePoints();
        return new String(stringCodePoints, 0, stringCodePoints.length);
    }

    public IntPointer putString(String str) {
        int length = str.length();
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = str.codePointAt(i);
        }
        long j = (long) length;
        return put(iArr).put(j, 0).limit(j);
    }

    public int get() {
        return get(0);
    }

    public IntPointer put(int i) {
        return put(0, i);
    }

    public IntPointer get(int[] iArr) {
        return get(iArr, 0, iArr.length);
    }

    public IntPointer put(int... iArr) {
        return put(iArr, 0, iArr.length);
    }

    public final IntBuffer asBuffer() {
        return asByteBuffer().asIntBuffer();
    }
}
