package org.bytedeco.javacpp;

import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.ValueGetter;
import org.bytedeco.javacpp.annotation.ValueSetter;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit = {javacpp.class})
public class BytePointer extends Pointer {
    private static final Logger logger = Logger.create(BytePointer.class);

    private native void allocateArray(long j);

    @Cast({"char*"})
    public static native BytePointer strcat(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    @Cast({"char*"})
    public static native BytePointer strchr(@Cast({"char*"}) BytePointer bytePointer, int i);

    public static native int strcmp(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    public static native int strcoll(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    @Cast({"char*"})
    public static native BytePointer strcpy(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    @Cast({"size_t"})
    public static native long strcspn(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    @Cast({"char*"})
    public static native BytePointer strerror(int i);

    @Cast({"size_t"})
    public static native long strlen(@Cast({"char*"}) BytePointer bytePointer);

    @Cast({"char*"})
    public static native BytePointer strncat(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    public static native int strncmp(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    @Cast({"char*"})
    public static native BytePointer strncpy(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    @Cast({"char*"})
    public static native BytePointer strpbrk(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    @Cast({"char*"})
    public static native BytePointer strrchr(@Cast({"char*"}) BytePointer bytePointer, int i);

    @Cast({"size_t"})
    public static native long strspn(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    @Cast({"char*"})
    public static native BytePointer strstr(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    @Cast({"char*"})
    public static native BytePointer strtok(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2);

    @Cast({"size_t"})
    public static native long strxfrm(@Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2, @Cast({"size_t"}) long j);

    public native byte get(long j);

    public native BytePointer get(byte[] bArr, int i, int i2);

    @Cast({"bool"})
    @ValueGetter
    public native boolean getBool(long j);

    @Cast({"short"})
    @ValueGetter
    public native char getChar(long j);

    @Cast({"double"})
    @ValueGetter
    public native double getDouble(long j);

    @Cast({"float"})
    @ValueGetter
    public native float getFloat(long j);

    @Cast({"int"})
    @ValueGetter
    public native int getInt(long j);

    @Cast({"long long"})
    @ValueGetter
    public native long getLong(long j);

    @Cast({"void*"})
    @ValueGetter
    public native Pointer getPointerValue(long j);

    @Cast({"short"})
    @ValueGetter
    public native short getShort(long j);

    public native BytePointer put(long j, byte b);

    public native BytePointer put(byte[] bArr, int i, int i2);

    @ValueSetter
    @Cast({"bool"})
    public native BytePointer putBool(long j, boolean z);

    @ValueSetter
    @Cast({"short"})
    public native BytePointer putChar(long j, char c);

    @ValueSetter
    @Cast({"double"})
    public native BytePointer putDouble(long j, double d);

    @ValueSetter
    @Cast({"float"})
    public native BytePointer putFloat(long j, float f);

    @ValueSetter
    @Cast({"int"})
    public native BytePointer putInt(long j, int i);

    @ValueSetter
    @Cast({"long long"})
    public native BytePointer putLong(long j, long j2);

    @ValueSetter
    @Cast({"void*"})
    public native BytePointer putPointerValue(long j, Pointer pointer);

    @ValueSetter
    @Cast({"short"})
    public native BytePointer putShort(long j, short s);

    public int sizeof() {
        return 1;
    }

    static {
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load BytePointer: " + th);
        }
    }

    public BytePointer(String str, String str2) throws UnsupportedEncodingException {
        this((long) (str.getBytes(str2).length + 1));
        putString(str, str2);
    }

    public BytePointer(String str, Charset charset) {
        this((long) (str.getBytes(charset).length + 1));
        putString(str, charset);
    }

    public BytePointer(String str) {
        this((long) (str.getBytes().length + 1));
        putString(str);
    }

    public BytePointer(byte... bArr) {
        this((long) bArr.length);
        put(bArr);
    }

    public BytePointer(ByteBuffer byteBuffer) {
        super((Buffer) byteBuffer);
        if (byteBuffer != null && !byteBuffer.isDirect() && byteBuffer.hasArray()) {
            byte[] array = byteBuffer.array();
            allocateArray((long) (array.length - byteBuffer.arrayOffset()));
            put(array, byteBuffer.arrayOffset(), array.length - byteBuffer.arrayOffset());
            position((long) byteBuffer.position());
            limit((long) byteBuffer.limit());
        }
    }

    public BytePointer(long j) {
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
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Cannot allocate new BytePointer(" + j + "): totalBytes = " + formatBytes(totalBytes()) + ", physicalBytes = " + formatBytes(physicalBytes()));
            outOfMemoryError.initCause(e2);
            throw outOfMemoryError;
        }
    }

    public BytePointer() {
    }

    public BytePointer(Pointer pointer) {
        super(pointer);
    }

    public BytePointer position(long j) {
        return (BytePointer) super.position(j);
    }

    public BytePointer limit(long j) {
        return (BytePointer) super.limit(j);
    }

    public BytePointer capacity(long j) {
        return (BytePointer) super.capacity(j);
    }

    public BytePointer getPointer(long j) {
        return new BytePointer((Pointer) this).position(this.position + j);
    }

    public byte[] getStringBytes() {
        long j = this.limit - this.position;
        if (j <= 0) {
            j = strlen(this);
        }
        byte[] bArr = new byte[((int) Math.min(j, 2147483647L))];
        get(bArr);
        return bArr;
    }

    public String getString(String str) throws UnsupportedEncodingException {
        return new String(getStringBytes(), str);
    }

    public String getString(Charset charset) {
        return new String(getStringBytes(), charset);
    }

    public String getString() {
        return new String(getStringBytes());
    }

    public BytePointer putString(String str, String str2) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes(str2);
        return put(bytes).put((long) bytes.length, (byte) 0).limit((long) bytes.length);
    }

    public BytePointer putString(String str, Charset charset) {
        byte[] bytes = str.getBytes(charset);
        return put(bytes).put((long) bytes.length, (byte) 0).limit((long) bytes.length);
    }

    public BytePointer putString(String str) {
        byte[] bytes = str.getBytes();
        return put(bytes).put((long) bytes.length, (byte) 0).limit((long) bytes.length);
    }

    public byte get() {
        return get(0);
    }

    public BytePointer put(byte b) {
        return put(0, b);
    }

    public BytePointer get(byte[] bArr) {
        return get(bArr, 0, bArr.length);
    }

    public BytePointer put(byte... bArr) {
        return put(bArr, 0, bArr.length);
    }

    public final ByteBuffer asBuffer() {
        return asByteBuffer();
    }

    public short getShort() {
        return getShort(0);
    }

    public BytePointer putShort(short s) {
        return putShort(0, s);
    }

    public int getInt() {
        return getInt(0);
    }

    public BytePointer putInt(int i) {
        return putInt(0, i);
    }

    public long getLong() {
        return getLong(0);
    }

    public BytePointer putLong(long j) {
        return putLong(0, j);
    }

    public float getFloat() {
        return getFloat(0);
    }

    public BytePointer putFloat(float f) {
        return putFloat(0, f);
    }

    public double getDouble() {
        return getDouble(0);
    }

    public BytePointer putDouble(double d) {
        return putDouble(0, d);
    }

    public boolean getBool() {
        return getBool(0);
    }

    public BytePointer putBool(boolean z) {
        return putBool(0, z);
    }

    public char getChar() {
        return getChar(0);
    }

    public BytePointer putChar(char c) {
        return putChar(0, c);
    }

    public Pointer getPointerValue() {
        return getPointerValue(0);
    }

    public BytePointer putPointerValue(Pointer pointer) {
        return putPointerValue(0, pointer);
    }
}
