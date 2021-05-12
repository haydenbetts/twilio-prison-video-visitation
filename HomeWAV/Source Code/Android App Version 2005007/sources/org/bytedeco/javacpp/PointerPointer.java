package org.bytedeco.javacpp;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class PointerPointer<P extends Pointer> extends Pointer {
    private static final Logger logger = Logger.create(PointerPointer.class);
    private P[] pointerArray;

    private native void allocateArray(long j);

    public native P get(Class<P> cls, long j);

    public native PointerPointer<P> put(long j, Pointer pointer);

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load PointerPointer: " + th);
        }
    }

    public PointerPointer(String... strArr) {
        this((long) strArr.length);
        putString(strArr);
    }

    public PointerPointer(String[] strArr, String str) throws UnsupportedEncodingException {
        this((long) strArr.length);
        putString(strArr, str);
    }

    public PointerPointer(String[] strArr, Charset charset) {
        this((long) strArr.length);
        putString(strArr, charset);
    }

    public PointerPointer(P... pArr) {
        this((long) pArr.length);
        put(pArr);
    }

    public PointerPointer(byte[]... bArr) {
        this((long) bArr.length);
        put(bArr);
    }

    public PointerPointer(short[]... sArr) {
        this((long) sArr.length);
        put(sArr);
    }

    public PointerPointer(int[]... iArr) {
        this((long) iArr.length);
        put(iArr);
    }

    public PointerPointer(long[]... jArr) {
        this((long) jArr.length);
        put(jArr);
    }

    public PointerPointer(float[]... fArr) {
        this((long) fArr.length);
        put(fArr);
    }

    public PointerPointer(double[]... dArr) {
        this((long) dArr.length);
        put(dArr);
    }

    public PointerPointer(char[]... cArr) {
        this((long) cArr.length);
        put(cArr);
    }

    public PointerPointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new PointerPointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public PointerPointer() {
    }

    public PointerPointer(Pointer pointer) {
        super(pointer);
    }

    public PointerPointer<P> position(long j) {
        return (PointerPointer) super.position(j);
    }

    public PointerPointer<P> limit(long j) {
        return (PointerPointer) super.limit(j);
    }

    public PointerPointer<P> capacity(long j) {
        return (PointerPointer) super.capacity(j);
    }

    public PointerPointer<P> getPointer(long j) {
        return new PointerPointer((Pointer) this).position(this.position + j);
    }

    public String getString(long j) {
        BytePointer bytePointer = (BytePointer) get(BytePointer.class, j);
        if (bytePointer != null) {
            return bytePointer.getString();
        }
        return null;
    }

    public String getString(long j, String str) throws UnsupportedEncodingException {
        BytePointer bytePointer = (BytePointer) get(BytePointer.class, j);
        if (bytePointer != null) {
            return bytePointer.getString(str);
        }
        return null;
    }

    public String getString(long j, Charset charset) {
        BytePointer bytePointer = (BytePointer) get(BytePointer.class, j);
        if (bytePointer != null) {
            return bytePointer.getString(charset);
        }
        return null;
    }

    public PointerPointer<P> putString(String... strArr) {
        this.pointerArray = (Pointer[]) new BytePointer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            this.pointerArray[i] = strArr[i] != null ? new BytePointer(strArr[i]) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> putString(String[] strArr, String str) throws UnsupportedEncodingException {
        this.pointerArray = (Pointer[]) new BytePointer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            this.pointerArray[i] = strArr[i] != null ? new BytePointer(strArr[i], str) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> putString(String[] strArr, Charset charset) {
        this.pointerArray = (Pointer[]) new BytePointer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            this.pointerArray[i] = strArr[i] != null ? new BytePointer(strArr[i], charset) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> put(P... pArr) {
        this.pointerArray = pArr;
        for (int i = 0; i < pArr.length; i++) {
            put((long) i, pArr[i]);
        }
        return this;
    }

    public PointerPointer<P> put(byte[]... bArr) {
        this.pointerArray = (Pointer[]) new BytePointer[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            this.pointerArray[i] = bArr[i] != null ? new BytePointer(bArr[i]) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> put(short[]... sArr) {
        this.pointerArray = (Pointer[]) new ShortPointer[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            this.pointerArray[i] = sArr[i] != null ? new ShortPointer(sArr[i]) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> put(int[]... iArr) {
        this.pointerArray = (Pointer[]) new IntPointer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            this.pointerArray[i] = iArr[i] != null ? new IntPointer(iArr[i]) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> put(long[]... jArr) {
        this.pointerArray = (Pointer[]) new LongPointer[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            this.pointerArray[i] = jArr[i] != null ? new LongPointer(jArr[i]) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> put(float[]... fArr) {
        this.pointerArray = (Pointer[]) new FloatPointer[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            this.pointerArray[i] = fArr[i] != null ? new FloatPointer(fArr[i]) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> put(double[]... dArr) {
        this.pointerArray = (Pointer[]) new DoublePointer[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            this.pointerArray[i] = dArr[i] != null ? new DoublePointer(dArr[i]) : null;
        }
        return put(this.pointerArray);
    }

    public PointerPointer<P> put(char[]... cArr) {
        this.pointerArray = (Pointer[]) new CharPointer[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            this.pointerArray[i] = cArr[i] != null ? new CharPointer(cArr[i]) : null;
        }
        return put(this.pointerArray);
    }

    public Pointer get() {
        return get(0);
    }

    public P get(Class<P> cls) {
        return get(cls, 0);
    }

    public Pointer get(long j) {
        return get(Pointer.class, j);
    }

    public PointerPointer<P> put(Pointer pointer) {
        return put(0, pointer);
    }
}
