package org.bytedeco.javacpp;

import androidx.exifinterface.media.ExifInterface;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;
import org.bytedeco.javacpp.tools.PointerBufferPoolMXBean;

@Properties(inherit = {javacpp.class})
public class Pointer implements AutoCloseable {
    static final Thread deallocatorThread;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.create(Pointer.class);
    static final long maxBytes;
    static final long maxPhysicalBytes;
    static final int maxRetries;
    /* access modifiers changed from: private */
    public static final ReferenceQueue<Pointer> referenceQueue;
    protected long address = 0;
    protected long capacity = 0;
    private Deallocator deallocator = null;
    protected long limit = 0;
    protected long position = 0;

    protected interface Deallocator {
        void deallocate();
    }

    protected interface ReferenceCounter {
        int count();

        boolean release();

        void retain();
    }

    private native void allocate(Buffer buffer);

    private native ByteBuffer asDirectBuffer();

    @Name({"JavaCPP_availablePhysicalBytes"})
    public static native long availablePhysicalBytes();

    public static native Pointer calloc(long j, long j2);

    public static native void free(Pointer pointer);

    public static native Pointer malloc(long j);

    public static native Pointer memchr(Pointer pointer, int i, long j);

    public static native int memcmp(Pointer pointer, Pointer pointer2, long j);

    public static native Pointer memcpy(Pointer pointer, Pointer pointer2, long j);

    public static native Pointer memmove(Pointer pointer, Pointer pointer2, long j);

    public static native Pointer memset(Pointer pointer, int i, long j);

    @Name({"JavaCPP_physicalBytes"})
    public static native long physicalBytes();

    public static native Pointer realloc(Pointer pointer, long j);

    @Name({"JavaCPP_totalPhysicalBytes"})
    public static native long totalPhysicalBytes();

    @Name({"JavaCPP_trimMemory"})
    private static native boolean trimMemory();

    public Pointer() {
    }

    public Pointer(Pointer pointer) {
        if (pointer != null) {
            this.address = pointer.address;
            this.position = pointer.position;
            this.limit = pointer.limit;
            this.capacity = pointer.capacity;
            if (pointer.deallocator != null) {
                this.deallocator = new ProxyDeallocator(this, pointer);
            }
        }
    }

    public Pointer(Buffer buffer) {
        if (buffer != null) {
            allocate(buffer);
        }
        if (!isNull()) {
            this.address -= (long) (buffer.position() * sizeof());
            this.position = (long) buffer.position();
            this.limit = (long) buffer.limit();
            this.capacity = (long) buffer.capacity();
            this.deallocator = new ProxyDeallocator(this, buffer);
        }
    }

    /* access modifiers changed from: package-private */
    public void init(long j, long j2, long j3, long j4) {
        this.address = j;
        this.position = 0;
        this.limit = j2;
        this.capacity = j2;
        if (j3 != 0 && j4 != 0) {
            deallocator(new NativeDeallocator(this, j3, j4));
        }
    }

    protected static <P extends Pointer> P withDeallocator(P p) {
        return p.deallocator(new CustomDeallocator(p));
    }

    protected static class CustomDeallocator extends DeallocatorReference {
        Method method = null;
        Pointer pointer = null;

        public CustomDeallocator(Pointer pointer2) {
            super(pointer2, (Deallocator) null);
            this.deallocator = this;
            Class<?> cls = pointer2.getClass();
            Method[] declaredMethods = cls.getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Method method2 = declaredMethods[i];
                Class[] parameterTypes = method2.getParameterTypes();
                if (Modifier.isStatic(method2.getModifiers()) && method2.getReturnType().equals(Void.TYPE) && method2.getName().equals("deallocate") && parameterTypes.length == 1 && Pointer.class.isAssignableFrom(parameterTypes[0])) {
                    method2.setAccessible(true);
                    this.method = method2;
                    break;
                }
                i++;
            }
            if (this.method != null) {
                try {
                    Constructor<?> constructor = cls.getConstructor(new Class[]{Pointer.class});
                    constructor.setAccessible(true);
                    this.pointer = (Pointer) constructor.newInstance(new Object[]{pointer2});
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException(new NoSuchMethodException("static void " + cls.getCanonicalName() + ".deallocate(" + Pointer.class.getCanonicalName() + ")"));
            }
        }

        public void deallocate() {
            try {
                this.method.invoke((Object) null, new Object[]{this.pointer});
                this.pointer.setNull();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public String toString() {
            return getClass().getName() + "[pointer=" + this.pointer + ",method=" + this.method + "]";
        }
    }

    protected static class NativeDeallocator extends DeallocatorReference {
        private long deallocatorAddress;
        private long ownerAddress;

        private native void deallocate(long j, long j2);

        NativeDeallocator(Pointer pointer, long j, long j2) {
            super(pointer, (Deallocator) null);
            this.deallocator = this;
            this.ownerAddress = j;
            this.deallocatorAddress = j2;
        }

        public void deallocate() {
            long j = this.ownerAddress;
            if (j != 0) {
                long j2 = this.deallocatorAddress;
                if (j2 != 0) {
                    deallocate(j, j2);
                    this.deallocatorAddress = 0;
                    this.ownerAddress = 0;
                }
            }
        }

        public String toString() {
            return getClass().getName() + "[ownerAddress=0x" + Long.toHexString(this.ownerAddress) + ",deallocatorAddress=0x" + Long.toHexString(this.deallocatorAddress) + "]";
        }
    }

    static class ProxyDeallocator extends DeallocatorReference {
        Buffer buffer;
        Pointer pointer;

        public ProxyDeallocator(Pointer pointer2, Buffer buffer2) {
            super(pointer2, (Deallocator) null);
            this.deallocator = this;
            this.buffer = buffer2;
        }

        public ProxyDeallocator(Pointer pointer2, Pointer pointer3) {
            super(pointer2, (Deallocator) null);
            this.deallocator = this;
            this.pointer = pointer3;
        }

        public void deallocate() {
            this.buffer = null;
            Pointer pointer2 = this.pointer;
            if (pointer2 != null) {
                pointer2.deallocate();
            }
        }

        public void retain() {
            Pointer pointer2 = this.pointer;
            if (pointer2 != null) {
                pointer2.retainReference();
            }
        }

        public boolean release() {
            Pointer pointer2 = this.pointer;
            if (pointer2 != null) {
                return pointer2.releaseReference();
            }
            return false;
        }

        public int count() {
            Pointer pointer2 = this.pointer;
            if (pointer2 != null) {
                return pointer2.referenceCount();
            }
            return -1;
        }

        public String toString() {
            return getClass().getName() + "[buffer=" + this.buffer + ",pointer=" + this.pointer + "]";
        }
    }

    static class DeallocatorReference extends PhantomReference<Pointer> implements Deallocator, ReferenceCounter {
        static volatile DeallocatorReference head;
        static volatile long totalBytes;
        static volatile long totalCount;
        long bytes;
        AtomicInteger count;
        Deallocator deallocator;
        volatile DeallocatorReference next = null;
        volatile DeallocatorReference prev = null;

        DeallocatorReference(Pointer pointer, Deallocator deallocator2) {
            super(pointer, Pointer.referenceQueue);
            this.deallocator = deallocator2;
            this.bytes = pointer.capacity * ((long) pointer.sizeof());
            this.count = new AtomicInteger(0);
        }

        /* access modifiers changed from: package-private */
        public final void add() {
            synchronized (DeallocatorReference.class) {
                if (head == null) {
                    head = this;
                } else {
                    this.next = head;
                    DeallocatorReference deallocatorReference = this.next;
                    head = this;
                    deallocatorReference.prev = this;
                }
                totalBytes += this.bytes;
                totalCount++;
            }
        }

        /* access modifiers changed from: package-private */
        public final void remove() {
            synchronized (DeallocatorReference.class) {
                if (this.prev != this || this.next != this) {
                    if (this.prev == null) {
                        head = this.next;
                    } else {
                        this.prev.next = this.next;
                    }
                    if (this.next != null) {
                        this.next.prev = this.prev;
                    }
                    this.next = this;
                    this.prev = this;
                    totalBytes -= this.bytes;
                    totalCount--;
                }
            }
        }

        public void clear() {
            super.clear();
            if (this.deallocator != null) {
                if (Pointer.logger.isDebugEnabled()) {
                    Logger access$100 = Pointer.logger;
                    access$100.debug("Collecting " + this);
                }
                deallocate();
            }
        }

        public void deallocate() {
            Deallocator deallocator2 = this.deallocator;
            if (deallocator2 != null) {
                deallocator2.deallocate();
                this.deallocator = null;
            }
        }

        public void retain() {
            if (this.deallocator != null) {
                this.count.incrementAndGet();
            }
        }

        public boolean release() {
            if (this.deallocator == null || this.count.decrementAndGet() > 0) {
                return false;
            }
            if (Pointer.logger.isDebugEnabled()) {
                Logger access$100 = Pointer.logger;
                access$100.debug("Releasing " + this);
            }
            deallocate();
            return true;
        }

        public int count() {
            if (this.deallocator != null) {
                return this.count.get();
            }
            return -1;
        }

        public String toString() {
            return getClass().getName() + "[deallocator=" + this.deallocator + ",count=" + this.count + "]";
        }
    }

    static class DeallocatorThread extends Thread {
        DeallocatorThread() {
            super("JavaCPP Deallocator");
            setPriority(10);
            setDaemon(true);
            setContextClassLoader((ClassLoader) null);
            start();
        }

        public void run() {
            while (true) {
                try {
                    DeallocatorReference deallocatorReference = (DeallocatorReference) Pointer.referenceQueue.remove();
                    deallocatorReference.clear();
                    deallocatorReference.remove();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    static {
        long j;
        String lowerCase = System.getProperty("org.bytedeco.javacpp.noPointerGC", System.getProperty("org.bytedeco.javacpp.nopointergc", "false").toLowerCase()).toLowerCase();
        if (lowerCase.equals("true") || lowerCase.equals("t") || lowerCase.equals("")) {
            referenceQueue = null;
            deallocatorThread = null;
        } else {
            referenceQueue = new ReferenceQueue<>();
            deallocatorThread = new DeallocatorThread();
        }
        long maxMemory = Runtime.getRuntime().maxMemory();
        String property = System.getProperty("org.bytedeco.javacpp.maxBytes", System.getProperty("org.bytedeco.javacpp.maxbytes"));
        if (property == null || property.length() <= 0) {
            j = maxMemory;
        } else {
            try {
                j = parseBytes(property, maxMemory);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        maxBytes = j;
        long j2 = 0;
        if (j > 0) {
            j2 = j + maxMemory;
        }
        String property2 = System.getProperty("org.bytedeco.javacpp.maxPhysicalBytes", System.getProperty("org.bytedeco.javacpp.maxphysicalbytes"));
        if (property2 != null && property2.length() > 0) {
            try {
                j2 = parseBytes(property2, maxMemory);
            } catch (NumberFormatException e2) {
                throw new RuntimeException(e2);
            }
        }
        maxPhysicalBytes = j2;
        int i = 10;
        String property3 = System.getProperty("org.bytedeco.javacpp.maxRetries", System.getProperty("org.bytedeco.javacpp.maxretries"));
        if (property3 != null && property3.length() > 0) {
            try {
                i = Integer.parseInt(property3);
            } catch (NumberFormatException e3) {
                throw new RuntimeException(e3);
            }
        }
        maxRetries = i;
        try {
            Loader.load();
        } catch (Throwable th) {
            Logger logger2 = logger;
            logger2.warn("Could not load Pointer: " + th);
        }
        String lowerCase2 = System.getProperty("org.bytedeco.javacpp.mxbean", "false").toLowerCase();
        if (lowerCase2.equals("true") || lowerCase2.equals("t") || lowerCase2.equals("")) {
            PointerBufferPoolMXBean.register();
        }
    }

    public static String formatBytes(long j) {
        if (j < 102400) {
            return j + "";
        }
        long j2 = j / 1024;
        if (j2 < 102400) {
            return j2 + "K";
        }
        long j3 = j2 / 1024;
        if (j3 < 102400) {
            return j3 + "M";
        }
        long j4 = j3 / 1024;
        if (j4 < 102400) {
            return j4 + "G";
        }
        return (j4 / 1024) + ExifInterface.GPS_DIRECTION_TRUE;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a4, code lost:
        if (r1.equals("") == false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c5, code lost:
        r2 = r2 * 1024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c7, code lost:
        r2 = r2 * 1024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return r2 * 1024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long parseBytes(java.lang.String r6, long r7) throws java.lang.NumberFormatException {
        /*
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r6.length()
            if (r1 >= r2) goto L_0x0016
            char r2 = r6.charAt(r1)
            boolean r2 = java.lang.Character.isDigit(r2)
            if (r2 != 0) goto L_0x0013
            goto L_0x0016
        L_0x0013:
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0016:
            java.lang.String r2 = r6.substring(r0, r1)
            long r2 = java.lang.Long.parseLong(r2)
            java.lang.String r1 = r6.substring(r1)
            java.lang.String r1 = r1.trim()
            java.lang.String r1 = r1.toLowerCase()
            r1.hashCode()
            r4 = -1
            int r5 = r1.hashCode()
            switch(r5) {
                case 0: goto L_0x009e;
                case 37: goto L_0x0093;
                case 103: goto L_0x0088;
                case 107: goto L_0x007d;
                case 109: goto L_0x0072;
                case 116: goto L_0x0067;
                case 3291: goto L_0x005c;
                case 3415: goto L_0x0051;
                case 3477: goto L_0x0045;
                case 3694: goto L_0x0038;
                default: goto L_0x0035;
            }
        L_0x0035:
            r0 = -1
            goto L_0x00a7
        L_0x0038:
            java.lang.String r0 = "tb"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0041
            goto L_0x0035
        L_0x0041:
            r0 = 9
            goto L_0x00a7
        L_0x0045:
            java.lang.String r0 = "mb"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x004e
            goto L_0x0035
        L_0x004e:
            r0 = 8
            goto L_0x00a7
        L_0x0051:
            java.lang.String r0 = "kb"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x005a
            goto L_0x0035
        L_0x005a:
            r0 = 7
            goto L_0x00a7
        L_0x005c:
            java.lang.String r0 = "gb"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0065
            goto L_0x0035
        L_0x0065:
            r0 = 6
            goto L_0x00a7
        L_0x0067:
            java.lang.String r0 = "t"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0070
            goto L_0x0035
        L_0x0070:
            r0 = 5
            goto L_0x00a7
        L_0x0072:
            java.lang.String r0 = "m"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x007b
            goto L_0x0035
        L_0x007b:
            r0 = 4
            goto L_0x00a7
        L_0x007d:
            java.lang.String r0 = "k"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0086
            goto L_0x0035
        L_0x0086:
            r0 = 3
            goto L_0x00a7
        L_0x0088:
            java.lang.String r0 = "g"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0091
            goto L_0x0035
        L_0x0091:
            r0 = 2
            goto L_0x00a7
        L_0x0093:
            java.lang.String r0 = "%"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x009c
            goto L_0x0035
        L_0x009c:
            r0 = 1
            goto L_0x00a7
        L_0x009e:
            java.lang.String r5 = ""
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L_0x00a7
            goto L_0x0035
        L_0x00a7:
            r4 = 1024(0x400, double:5.06E-321)
            switch(r0) {
                case 0: goto L_0x00d1;
                case 1: goto L_0x00cc;
                case 2: goto L_0x00c5;
                case 3: goto L_0x00c9;
                case 4: goto L_0x00c7;
                case 5: goto L_0x00c3;
                case 6: goto L_0x00c5;
                case 7: goto L_0x00c9;
                case 8: goto L_0x00c7;
                case 9: goto L_0x00c3;
                default: goto L_0x00ac;
            }
        L_0x00ac:
            java.lang.NumberFormatException r7 = new java.lang.NumberFormatException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "Cannot parse into bytes: "
            r8.append(r0)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.<init>(r6)
            throw r7
        L_0x00c3:
            long r2 = r2 * r4
        L_0x00c5:
            long r2 = r2 * r4
        L_0x00c7:
            long r2 = r2 * r4
        L_0x00c9:
            long r2 = r2 * r4
            goto L_0x00d1
        L_0x00cc:
            long r2 = r2 * r7
            r6 = 100
            long r2 = r2 / r6
        L_0x00d1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.Pointer.parseBytes(java.lang.String, long):long");
    }

    public static void deallocateReferences() {
        DeallocatorReference deallocatorReference;
        while (true) {
            ReferenceQueue<Pointer> referenceQueue2 = referenceQueue;
            if (referenceQueue2 != null && (deallocatorReference = (DeallocatorReference) referenceQueue2.poll()) != null) {
                deallocatorReference.clear();
                deallocatorReference.remove();
            } else {
                return;
            }
        }
    }

    public static long maxBytes() {
        return maxBytes;
    }

    public static long totalBytes() {
        return DeallocatorReference.totalBytes;
    }

    public static long totalCount() {
        return DeallocatorReference.totalCount;
    }

    public static long maxPhysicalBytes() {
        return maxPhysicalBytes;
    }

    public static boolean isNull(Pointer pointer) {
        return pointer == null || pointer.address == 0;
    }

    public boolean isNull() {
        return this.address == 0;
    }

    public void setNull() {
        this.address = 0;
    }

    public long address() {
        return this.address;
    }

    public long position() {
        return this.position;
    }

    public <P extends Pointer> P position(long j) {
        this.position = j;
        return this;
    }

    public long limit() {
        return this.limit;
    }

    public <P extends Pointer> P limit(long j) {
        this.limit = j;
        return this;
    }

    public long capacity() {
        return this.capacity;
    }

    public <P extends Pointer> P capacity(long j) {
        this.limit = j;
        this.capacity = j;
        return this;
    }

    /* access modifiers changed from: protected */
    public Deallocator deallocator() {
        return this.deallocator;
    }

    /* access modifiers changed from: protected */
    public <P extends Pointer> P deallocator(Deallocator deallocator2) {
        if (this.deallocator != null) {
            Logger logger2 = logger;
            if (logger2.isDebugEnabled()) {
                logger2.debug("Predeallocating " + this);
            }
            this.deallocator.deallocate();
            this.deallocator = null;
        }
        if (deallocator2 != null && !deallocator2.equals((Object) null)) {
            DeallocatorReference deallocatorReference = deallocator2 instanceof DeallocatorReference ? (DeallocatorReference) deallocator2 : new DeallocatorReference(this, deallocator2);
            this.deallocator = deallocatorReference;
            int i = 0;
            long physicalBytes = maxPhysicalBytes > 0 ? physicalBytes() : 0;
            synchronized (DeallocatorThread.class) {
                while (true) {
                    int i2 = i + 1;
                    try {
                        if (i >= maxRetries) {
                            break;
                        }
                        long j = maxBytes;
                        if (j <= 0 || DeallocatorReference.totalBytes + deallocatorReference.bytes <= j) {
                            long j2 = maxPhysicalBytes;
                            if (j2 > 0) {
                                if (physicalBytes <= j2) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        Logger logger3 = logger;
                        if (logger3.isDebugEnabled()) {
                            logger3.debug("Calling System.gc() and Pointer.trimMemory() in " + this);
                        }
                        System.gc();
                        Thread.sleep(100);
                        trimMemory();
                        physicalBytes = maxPhysicalBytes > 0 ? physicalBytes() : 0;
                        i = i2;
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    } catch (UnsatisfiedLinkError e) {
                        Logger logger4 = logger;
                        if (logger4.isDebugEnabled()) {
                            logger4.debug(e.getMessage());
                        }
                    }
                }
                long j3 = maxBytes;
                if (j3 > 0) {
                    if (DeallocatorReference.totalBytes + deallocatorReference.bytes > j3) {
                        deallocate();
                        throw new OutOfMemoryError("Failed to allocate memory within limits: totalBytes (" + formatBytes(DeallocatorReference.totalBytes) + " + " + formatBytes(deallocatorReference.bytes) + ") > maxBytes (" + formatBytes(j3) + ")");
                    }
                }
                long j4 = maxPhysicalBytes;
                if (j4 > 0) {
                    if (physicalBytes > j4) {
                        deallocate();
                        throw new OutOfMemoryError("Physical memory usage is too high: physicalBytes (" + formatBytes(physicalBytes) + ") > maxPhysicalBytes (" + formatBytes(j4) + ")");
                    }
                }
                Logger logger5 = logger;
                if (logger5.isDebugEnabled()) {
                    logger5.debug("Registering " + this);
                }
                deallocatorReference.add();
                Iterator<PointerScope> scopeIterator = PointerScope.getScopeIterator();
                if (scopeIterator != null) {
                    while (scopeIterator.hasNext()) {
                        try {
                            scopeIterator.next().attach(this);
                            break;
                        } catch (IllegalArgumentException unused2) {
                        }
                    }
                }
            }
        }
        return this;
    }

    public void close() {
        releaseReference();
    }

    public void deallocate() {
        deallocate(true);
    }

    public void deallocate(boolean z) {
        Deallocator deallocator2 = this.deallocator;
        DeallocatorReference deallocatorReference = (DeallocatorReference) deallocator2;
        if (z && deallocator2 != null) {
            Logger logger2 = logger;
            if (logger2.isDebugEnabled()) {
                logger2.debug("Deallocating " + this);
            }
            this.deallocator.deallocate();
            this.deallocator = null;
            this.address = 0;
        }
        if ((!z || referenceQueue == null) && deallocatorReference != null) {
            Deallocator deallocator3 = deallocatorReference.deallocator;
            deallocatorReference.deallocator = null;
            deallocatorReference.clear();
            deallocatorReference.remove();
            deallocatorReference.deallocator = deallocator3;
        }
    }

    public <P extends Pointer> P retainReference() {
        ReferenceCounter referenceCounter = (ReferenceCounter) this.deallocator;
        if (referenceCounter != null) {
            referenceCounter.retain();
        }
        return this;
    }

    public boolean releaseReference() {
        ReferenceCounter referenceCounter = (ReferenceCounter) this.deallocator;
        if (referenceCounter == null || !referenceCounter.release()) {
            return false;
        }
        this.deallocator = null;
        this.address = 0;
        return true;
    }

    public int referenceCount() {
        ReferenceCounter referenceCounter = (ReferenceCounter) this.deallocator;
        if (referenceCounter != null) {
            return referenceCounter.count();
        }
        return -1;
    }

    public static int offsetof(Class<? extends Pointer> cls, String str) {
        return Loader.offsetof(cls, str);
    }

    public int offsetof(String str) {
        try {
            Class<?> cls = getClass();
            if (cls != Pointer.class) {
                return Loader.offsetof(cls, str);
            }
            return -1;
        } catch (ClassCastException | NullPointerException unused) {
            return -1;
        }
    }

    public static int sizeof(Class<? extends Pointer> cls) {
        return Loader.sizeof(cls);
    }

    public int sizeof() {
        Class<?> cls = getClass();
        if (cls == Pointer.class || cls == BytePointer.class) {
            return 1;
        }
        return offsetof("sizeof");
    }

    public ByteBuffer asByteBuffer() {
        if (isNull()) {
            return null;
        }
        long j = this.limit;
        if (j <= 0 || j >= this.position) {
            int sizeof = sizeof();
            Pointer pointer = new Pointer();
            pointer.address = this.address;
            long j2 = (long) sizeof;
            Pointer position2 = pointer.position(this.position * j2);
            long j3 = this.limit;
            if (j3 <= 0) {
                j3 = 1 + this.position;
            }
            return position2.capacity(j2 * j3).asDirectBuffer().order(ByteOrder.nativeOrder());
        }
        throw new IllegalArgumentException("limit < position: (" + this.limit + " < " + this.position + ")");
    }

    public Buffer asBuffer() {
        return asByteBuffer();
    }

    public <P extends Pointer> P getPointer() {
        return getPointer(0);
    }

    public <P extends Pointer> P getPointer(long j) {
        long sizeof = (long) sizeof();
        return new Pointer(this).position((this.position + j) * sizeof).capacity(this.capacity * sizeof).limit(this.limit * sizeof);
    }

    public <P extends Pointer> P getPointer(Class<P> cls) {
        return getPointer(cls, 0);
    }

    public <P extends Pointer> P getPointer(Class<P> cls, long j) {
        try {
            return ((Pointer) cls.getDeclaredConstructor(new Class[]{Pointer.class}).newInstance(new Object[]{this})).position(this.position + j);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public <P extends Pointer> P put(Pointer pointer) {
        long j = pointer.limit;
        if (j <= 0 || j >= pointer.position) {
            int sizeof = sizeof();
            long sizeof2 = (long) pointer.sizeof();
            long j2 = pointer.limit;
            long j3 = j2 <= 0 ? 1 : j2 - pointer.position;
            long j4 = (long) sizeof;
            this.position *= j4;
            pointer.position *= sizeof2;
            memcpy(this, pointer, j3 * sizeof2);
            this.position /= j4;
            pointer.position /= sizeof2;
            return this;
        }
        throw new IllegalArgumentException("limit < position: (" + pointer.limit + " < " + pointer.position + ")");
    }

    public <P extends Pointer> P fill(int i) {
        long j = this.limit;
        if (j <= 0 || j >= this.position) {
            long sizeof = (long) sizeof();
            long j2 = this.limit;
            long j3 = j2 <= 0 ? 1 : j2 - this.position;
            this.position *= sizeof;
            memset(this, i, j3 * sizeof);
            this.position /= sizeof;
            return this;
        }
        throw new IllegalArgumentException("limit < position: (" + this.limit + " < " + this.position + ")");
    }

    public <P extends Pointer> P zero() {
        return fill(0);
    }

    public boolean equals(Object obj) {
        Class<Pointer> cls = Pointer.class;
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return isNull();
        }
        if (obj.getClass() != getClass() && obj.getClass() != cls && getClass() != cls) {
            return false;
        }
        Pointer pointer = (Pointer) obj;
        if (this.address == pointer.address && this.position == pointer.position) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) this.address;
    }

    public String toString() {
        return getClass().getName() + "[address=0x" + Long.toHexString(this.address) + ",position=" + this.position + ",limit=" + this.limit + ",capacity=" + this.capacity + ",deallocator=" + this.deallocator + "]";
    }
}
