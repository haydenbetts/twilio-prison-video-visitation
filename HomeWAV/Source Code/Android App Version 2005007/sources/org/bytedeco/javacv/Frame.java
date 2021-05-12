package org.bytedeco.javacv;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.EnumSet;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.indexer.ByteIndexer;
import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.Indexable;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.IntIndexer;
import org.bytedeco.javacpp.indexer.LongIndexer;
import org.bytedeco.javacpp.indexer.ShortIndexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.javacpp.indexer.UShortIndexer;

public class Frame implements Indexable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEPTH_BYTE = -8;
    public static final int DEPTH_DOUBLE = 64;
    public static final int DEPTH_FLOAT = 32;
    public static final int DEPTH_INT = -32;
    public static final int DEPTH_LONG = -64;
    public static final int DEPTH_SHORT = -16;
    public static final int DEPTH_UBYTE = 8;
    public static final int DEPTH_USHORT = 16;
    public int audioChannels;
    public ByteBuffer data;
    public Buffer[] image;
    public int imageChannels;
    public int imageDepth;
    public int imageHeight;
    public int imageStride;
    public int imageWidth;
    public boolean keyFrame;
    public Object opaque;
    public int sampleRate;
    public Buffer[] samples;
    public int streamIndex;
    public long timestamp;

    public enum Type {
        VIDEO,
        AUDIO,
        DATA
    }

    public static int pixelSize(int i) {
        return Math.abs(i) / 8;
    }

    public Frame() {
    }

    public Frame(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, ((((i * i4) * pixelSize(i3)) + 7) & -8) / pixelSize(i3));
    }

    public Frame(int i, int i2, int i3, int i4, int i5) {
        this.imageWidth = i;
        this.imageHeight = i2;
        this.imageDepth = i3;
        this.imageChannels = i4;
        this.imageStride = i5;
        this.image = new Buffer[1];
        this.data = null;
        this.streamIndex = -1;
        BytePointer bytePointer = new BytePointer((long) (i2 * i5 * pixelSize(i3)));
        ByteBuffer asByteBuffer = bytePointer.asByteBuffer();
        int i6 = this.imageDepth;
        if (i6 == -64) {
            this.image[0] = asByteBuffer.asLongBuffer();
        } else if (i6 != -32) {
            if (i6 != -16) {
                if (i6 == -8 || i6 == 8) {
                    this.image[0] = asByteBuffer;
                } else if (i6 != 16) {
                    if (i6 == 32) {
                        this.image[0] = asByteBuffer.asFloatBuffer();
                    } else if (i6 == 64) {
                        this.image[0] = asByteBuffer.asDoubleBuffer();
                    } else {
                        throw new UnsupportedOperationException("Unsupported depth value: " + this.imageDepth);
                    }
                }
            }
            this.image[0] = asByteBuffer.asShortBuffer();
        } else {
            this.image[0] = asByteBuffer.asIntBuffer();
        }
        this.opaque = bytePointer;
    }

    public <I extends Indexer> I createIndexer() {
        return createIndexer(true, 0);
    }

    public <I extends Indexer> I createIndexer(boolean z) {
        return createIndexer(z, 0);
    }

    public <I extends Indexer> I createIndexer(boolean z, int i) {
        int i2 = this.imageChannels;
        long[] jArr = {(long) this.imageHeight, (long) this.imageWidth, (long) i2};
        long[] jArr2 = {(long) this.imageStride, (long) i2, 1};
        Buffer buffer = this.image[i];
        Object array = buffer.hasArray() ? buffer.array() : null;
        int i3 = this.imageDepth;
        if (i3 != -64) {
            if (i3 != -32) {
                if (i3 != -16) {
                    if (i3 != -8) {
                        if (i3 != 8) {
                            if (i3 != 16) {
                                if (i3 != 32) {
                                    if (i3 != 64) {
                                        return null;
                                    }
                                    if (array != null) {
                                        return DoubleIndexer.create((double[]) array, jArr, jArr2).indexable(this);
                                    }
                                    if (z) {
                                        return DoubleIndexer.create((DoubleBuffer) buffer, jArr, jArr2).indexable(this);
                                    }
                                    return DoubleIndexer.create(new DoublePointer((DoubleBuffer) buffer), jArr, jArr2, false).indexable(this);
                                } else if (array != null) {
                                    return FloatIndexer.create((float[]) array, jArr, jArr2).indexable(this);
                                } else {
                                    if (z) {
                                        return FloatIndexer.create((FloatBuffer) buffer, jArr, jArr2).indexable(this);
                                    }
                                    return FloatIndexer.create(new FloatPointer((FloatBuffer) buffer), jArr, jArr2, false).indexable(this);
                                }
                            } else if (array != null) {
                                return UShortIndexer.create((short[]) array, jArr, jArr2).indexable(this);
                            } else {
                                if (z) {
                                    return UShortIndexer.create((ShortBuffer) buffer, jArr, jArr2).indexable(this);
                                }
                                return UShortIndexer.create(new ShortPointer((ShortBuffer) buffer), jArr, jArr2, false).indexable(this);
                            }
                        } else if (array != null) {
                            return UByteIndexer.create((byte[]) array, jArr, jArr2).indexable(this);
                        } else {
                            if (z) {
                                return UByteIndexer.create((ByteBuffer) buffer, jArr, jArr2).indexable(this);
                            }
                            return UByteIndexer.create(new BytePointer((ByteBuffer) buffer), jArr, jArr2, false).indexable(this);
                        }
                    } else if (array != null) {
                        return ByteIndexer.create((byte[]) array, jArr, jArr2).indexable(this);
                    } else {
                        if (z) {
                            return ByteIndexer.create((ByteBuffer) buffer, jArr, jArr2).indexable(this);
                        }
                        return ByteIndexer.create(new BytePointer((ByteBuffer) buffer), jArr, jArr2, false).indexable(this);
                    }
                } else if (array != null) {
                    return ShortIndexer.create((short[]) array, jArr, jArr2).indexable(this);
                } else {
                    if (z) {
                        return ShortIndexer.create((ShortBuffer) buffer, jArr, jArr2).indexable(this);
                    }
                    return ShortIndexer.create(new ShortPointer((ShortBuffer) buffer), jArr, jArr2, false).indexable(this);
                }
            } else if (array != null) {
                return IntIndexer.create((int[]) array, jArr, jArr2).indexable(this);
            } else {
                if (z) {
                    return IntIndexer.create((IntBuffer) buffer, jArr, jArr2).indexable(this);
                }
                return IntIndexer.create(new IntPointer((IntBuffer) buffer), jArr, jArr2, false).indexable(this);
            }
        } else if (array != null) {
            return LongIndexer.create((long[]) array, jArr, jArr2).indexable(this);
        } else {
            if (z) {
                return LongIndexer.create((LongBuffer) buffer, jArr, jArr2).indexable(this);
            }
            return LongIndexer.create(new LongPointer((LongBuffer) buffer), jArr, jArr2, false).indexable(this);
        }
    }

    public Frame clone() {
        Frame frame = new Frame();
        frame.imageWidth = this.imageWidth;
        frame.imageHeight = this.imageHeight;
        frame.imageDepth = this.imageDepth;
        frame.imageChannels = this.imageChannels;
        frame.imageStride = this.imageStride;
        frame.keyFrame = this.keyFrame;
        frame.streamIndex = this.streamIndex;
        Pointer[] pointerArr = new Pointer[3];
        frame.opaque = pointerArr;
        Buffer[] bufferArr = this.image;
        if (bufferArr != null) {
            Buffer[] bufferArr2 = new Buffer[bufferArr.length];
            frame.image = bufferArr2;
            pointerArr[0] = cloneBufferArray(this.image, bufferArr2);
        }
        frame.audioChannels = this.audioChannels;
        frame.sampleRate = this.sampleRate;
        Buffer[] bufferArr3 = this.samples;
        if (bufferArr3 != null) {
            Buffer[] bufferArr4 = new Buffer[bufferArr3.length];
            frame.samples = bufferArr4;
            ((Pointer[]) frame.opaque)[1] = cloneBufferArray(this.samples, bufferArr4);
        }
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            ByteBuffer[] byteBufferArr = new ByteBuffer[1];
            ((Pointer[]) frame.opaque)[2] = cloneBufferArray(new ByteBuffer[]{byteBuffer}, byteBufferArr);
            frame.data = byteBufferArr[0];
        }
        frame.timestamp = this.timestamp;
        return frame;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: org.bytedeco.javacpp.FloatPointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: org.bytedeco.javacpp.LongPointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: org.bytedeco.javacpp.IntPointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: org.bytedeco.javacpp.ShortPointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: org.bytedeco.javacpp.BytePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: org.bytedeco.javacpp.DoublePointer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.bytedeco.javacpp.Pointer cloneBufferArray(java.nio.Buffer[] r7, java.nio.Buffer[] r8) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x017b
            int r1 = r7.length
            if (r1 <= 0) goto L_0x017b
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x0009:
            int r4 = r7.length
            if (r2 >= r4) goto L_0x001b
            r4 = r7[r2]
            r4.rewind()
            r4 = r7[r2]
            int r4 = r4.capacity()
            int r3 = r3 + r4
            int r2 = r2 + 1
            goto L_0x0009
        L_0x001b:
            r2 = r7[r1]
            boolean r2 = r2 instanceof java.nio.ByteBuffer
            if (r2 == 0) goto L_0x0053
            org.bytedeco.javacpp.BytePointer r0 = new org.bytedeco.javacpp.BytePointer
            long r2 = (long) r3
            r0.<init>((long) r2)
            r2 = 0
        L_0x0028:
            int r3 = r7.length
            if (r2 >= r3) goto L_0x016b
            long r3 = r0.position()
            r5 = r7[r2]
            int r5 = r5.limit()
            long r5 = (long) r5
            long r3 = r3 + r5
            org.bytedeco.javacpp.BytePointer r3 = r0.limit((long) r3)
            java.nio.ByteBuffer r3 = r3.asBuffer()
            r4 = r7[r2]
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            java.nio.ByteBuffer r3 = r3.put(r4)
            r8[r2] = r3
            long r3 = r0.limit()
            r0.position((long) r3)
            int r2 = r2 + 1
            goto L_0x0028
        L_0x0053:
            r2 = r7[r1]
            boolean r2 = r2 instanceof java.nio.ShortBuffer
            if (r2 == 0) goto L_0x008b
            org.bytedeco.javacpp.ShortPointer r0 = new org.bytedeco.javacpp.ShortPointer
            long r2 = (long) r3
            r0.<init>((long) r2)
            r2 = 0
        L_0x0060:
            int r3 = r7.length
            if (r2 >= r3) goto L_0x016b
            long r3 = r0.position()
            r5 = r7[r2]
            int r5 = r5.limit()
            long r5 = (long) r5
            long r3 = r3 + r5
            org.bytedeco.javacpp.ShortPointer r3 = r0.limit((long) r3)
            java.nio.ShortBuffer r3 = r3.asBuffer()
            r4 = r7[r2]
            java.nio.ShortBuffer r4 = (java.nio.ShortBuffer) r4
            java.nio.ShortBuffer r3 = r3.put(r4)
            r8[r2] = r3
            long r3 = r0.limit()
            r0.position((long) r3)
            int r2 = r2 + 1
            goto L_0x0060
        L_0x008b:
            r2 = r7[r1]
            boolean r2 = r2 instanceof java.nio.IntBuffer
            if (r2 == 0) goto L_0x00c3
            org.bytedeco.javacpp.IntPointer r0 = new org.bytedeco.javacpp.IntPointer
            long r2 = (long) r3
            r0.<init>((long) r2)
            r2 = 0
        L_0x0098:
            int r3 = r7.length
            if (r2 >= r3) goto L_0x016b
            long r3 = r0.position()
            r5 = r7[r2]
            int r5 = r5.limit()
            long r5 = (long) r5
            long r3 = r3 + r5
            org.bytedeco.javacpp.IntPointer r3 = r0.limit((long) r3)
            java.nio.IntBuffer r3 = r3.asBuffer()
            r4 = r7[r2]
            java.nio.IntBuffer r4 = (java.nio.IntBuffer) r4
            java.nio.IntBuffer r3 = r3.put(r4)
            r8[r2] = r3
            long r3 = r0.limit()
            r0.position((long) r3)
            int r2 = r2 + 1
            goto L_0x0098
        L_0x00c3:
            r2 = r7[r1]
            boolean r2 = r2 instanceof java.nio.LongBuffer
            if (r2 == 0) goto L_0x00fb
            org.bytedeco.javacpp.LongPointer r0 = new org.bytedeco.javacpp.LongPointer
            long r2 = (long) r3
            r0.<init>((long) r2)
            r2 = 0
        L_0x00d0:
            int r3 = r7.length
            if (r2 >= r3) goto L_0x016b
            long r3 = r0.position()
            r5 = r7[r2]
            int r5 = r5.limit()
            long r5 = (long) r5
            long r3 = r3 + r5
            org.bytedeco.javacpp.LongPointer r3 = r0.limit((long) r3)
            java.nio.LongBuffer r3 = r3.asBuffer()
            r4 = r7[r2]
            java.nio.LongBuffer r4 = (java.nio.LongBuffer) r4
            java.nio.LongBuffer r3 = r3.put(r4)
            r8[r2] = r3
            long r3 = r0.limit()
            r0.position((long) r3)
            int r2 = r2 + 1
            goto L_0x00d0
        L_0x00fb:
            r2 = r7[r1]
            boolean r2 = r2 instanceof java.nio.FloatBuffer
            if (r2 == 0) goto L_0x0133
            org.bytedeco.javacpp.FloatPointer r0 = new org.bytedeco.javacpp.FloatPointer
            long r2 = (long) r3
            r0.<init>((long) r2)
            r2 = 0
        L_0x0108:
            int r3 = r7.length
            if (r2 >= r3) goto L_0x016b
            long r3 = r0.position()
            r5 = r7[r2]
            int r5 = r5.limit()
            long r5 = (long) r5
            long r3 = r3 + r5
            org.bytedeco.javacpp.FloatPointer r3 = r0.limit((long) r3)
            java.nio.FloatBuffer r3 = r3.asBuffer()
            r4 = r7[r2]
            java.nio.FloatBuffer r4 = (java.nio.FloatBuffer) r4
            java.nio.FloatBuffer r3 = r3.put(r4)
            r8[r2] = r3
            long r3 = r0.limit()
            r0.position((long) r3)
            int r2 = r2 + 1
            goto L_0x0108
        L_0x0133:
            r2 = r7[r1]
            boolean r2 = r2 instanceof java.nio.DoubleBuffer
            if (r2 == 0) goto L_0x016b
            org.bytedeco.javacpp.DoublePointer r0 = new org.bytedeco.javacpp.DoublePointer
            long r2 = (long) r3
            r0.<init>((long) r2)
            r2 = 0
        L_0x0140:
            int r3 = r7.length
            if (r2 >= r3) goto L_0x016b
            long r3 = r0.position()
            r5 = r7[r2]
            int r5 = r5.limit()
            long r5 = (long) r5
            long r3 = r3 + r5
            org.bytedeco.javacpp.DoublePointer r3 = r0.limit((long) r3)
            java.nio.DoubleBuffer r3 = r3.asBuffer()
            r4 = r7[r2]
            java.nio.DoubleBuffer r4 = (java.nio.DoubleBuffer) r4
            java.nio.DoubleBuffer r3 = r3.put(r4)
            r8[r2] = r3
            long r3 = r0.limit()
            r0.position((long) r3)
            int r2 = r2 + 1
            goto L_0x0140
        L_0x016b:
            int r2 = r7.length
            if (r1 >= r2) goto L_0x017b
            r2 = r7[r1]
            r2.rewind()
            r2 = r8[r1]
            r2.rewind()
            int r1 = r1 + 1
            goto L_0x016b
        L_0x017b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacv.Frame.cloneBufferArray(java.nio.Buffer[], java.nio.Buffer[]):org.bytedeco.javacpp.Pointer");
    }

    public EnumSet<Type> getTypes() {
        EnumSet<Type> noneOf = EnumSet.noneOf(Type.class);
        if (this.image != null) {
            noneOf.add(Type.VIDEO);
        }
        if (this.samples != null) {
            noneOf.add(Type.AUDIO);
        }
        if (this.data != null) {
            noneOf.add(Type.DATA);
        }
        return noneOf;
    }
}
