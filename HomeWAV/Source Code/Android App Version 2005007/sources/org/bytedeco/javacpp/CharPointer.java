package org.bytedeco.javacpp;

import java.nio.Buffer;
import java.nio.CharBuffer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class CharPointer extends Pointer {
    private static final Logger logger = Logger.create(CharPointer.class);

    private native void allocateArray(long j);

    public native char get(long j);

    public native CharPointer get(char[] cArr, int i, int i2);

    public native CharPointer put(long j, char c);

    public native CharPointer put(char[] cArr, int i, int i2);

    public int sizeof() {
        return 2;
    }

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load CharPointer: " + th);
        }
    }

    public CharPointer(String str) {
        this((long) (str.toCharArray().length + 1));
        putString(str);
    }

    public CharPointer(char... cArr) {
        this((long) cArr.length);
        put(cArr);
    }

    public CharPointer(CharBuffer charBuffer) {
        super((Buffer) charBuffer);
        if (charBuffer != null && !charBuffer.isDirect() && charBuffer.hasArray()) {
            char[] array = charBuffer.array();
            allocateArray((long) (array.length - charBuffer.arrayOffset()));
            put(array, charBuffer.arrayOffset(), array.length - charBuffer.arrayOffset());
            position((long) charBuffer.position());
            limit((long) charBuffer.limit());
        }
    }

    public CharPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new CharPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public CharPointer() {
    }

    public CharPointer(Pointer pointer) {
        super(pointer);
    }

    public CharPointer position(long j) {
        return (CharPointer) super.position(j);
    }

    public CharPointer limit(long j) {
        return (CharPointer) super.limit(j);
    }

    public CharPointer capacity(long j) {
        return (CharPointer) super.capacity(j);
    }

    public CharPointer getPointer(long j) {
        return new CharPointer((Pointer) this).position(this.position + j);
    }

    public char[] getStringChars() {
        if (this.limit > this.position) {
            char[] cArr = new char[((int) Math.min(this.limit - this.position, 2147483647L))];
            get(cArr);
            return cArr;
        }
        char[] cArr2 = new char[16];
        int i = 0;
        while (true) {
            char c = get((long) i);
            cArr2[i] = c;
            if (c != 0) {
                i++;
                if (i >= cArr2.length) {
                    char[] cArr3 = new char[(cArr2.length * 2)];
                    System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
                    cArr2 = cArr3;
                }
            } else {
                char[] cArr4 = new char[i];
                System.arraycopy(cArr2, 0, cArr4, 0, i);
                return cArr4;
            }
        }
    }

    public String getString() {
        return new String(getStringChars());
    }

    public CharPointer putString(String str) {
        char[] charArray = str.toCharArray();
        return put(charArray).put((long) charArray.length, 0).limit((long) charArray.length);
    }

    public char get() {
        return get(0);
    }

    public CharPointer put(char c) {
        return put(0, c);
    }

    public CharPointer get(char[] cArr) {
        return get(cArr, 0, cArr.length);
    }

    public CharPointer put(char... cArr) {
        return put(cArr, 0, cArr.length);
    }

    public final CharBuffer asBuffer() {
        return asByteBuffer().asCharBuffer();
    }
}
