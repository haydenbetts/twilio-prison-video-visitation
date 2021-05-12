package com.google.android.exoplayer2.upstream.cache;

import android.util.SparseArray;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

class CachedContentIndex {
    public static final String FILE_NAME = "cached_content_index.exi";
    private static final int FLAG_ENCRYPTED_INDEX = 1;
    private static final String TAG = "CachedContentIndex";
    private static final int VERSION = 1;
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final boolean encrypt;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File file) {
        this(file, (byte[]) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CachedContentIndex(File file, byte[] bArr) {
        this(file, bArr, bArr != null);
    }

    public CachedContentIndex(File file, byte[] bArr, boolean z) {
        this.encrypt = z;
        boolean z2 = true;
        if (bArr != null) {
            Assertions.checkArgument(bArr.length != 16 ? false : z2);
            try {
                this.cipher = getCipher();
                this.secretKeySpec = new SecretKeySpec(bArr, "AES");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new IllegalStateException(e);
            }
        } else {
            Assertions.checkState(!z);
            this.cipher = null;
            this.secretKeySpec = null;
        }
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.atomicFile = new AtomicFile(new File(file, FILE_NAME));
    }

    public void load() {
        Assertions.checkState(!this.changed);
        if (!readFile()) {
            this.atomicFile.delete();
            this.keyToContent.clear();
            this.idToKey.clear();
        }
    }

    public void store() throws Cache.CacheException {
        if (this.changed) {
            writeFile();
            this.changed = false;
        }
    }

    public CachedContent getOrAdd(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str, -1) : cachedContent;
    }

    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return this.keyToContent.values();
    }

    public int assignIdForKey(String str) {
        return getOrAdd(str).id;
    }

    public String getKeyForId(int i) {
        return this.idToKey.get(i);
    }

    public void maybeRemove(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        if (cachedContent != null && cachedContent.isEmpty() && !cachedContent.isLocked()) {
            this.keyToContent.remove(str);
            this.idToKey.remove(cachedContent.id);
            this.changed = true;
        }
    }

    public void removeEmpty() {
        int size = this.keyToContent.size();
        String[] strArr = new String[size];
        this.keyToContent.keySet().toArray(strArr);
        for (int i = 0; i < size; i++) {
            maybeRemove(strArr[i]);
        }
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public void setContentLength(String str, long j) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            addNew(str, j);
        } else if (cachedContent.getLength() != j) {
            cachedContent.setLength(j);
            this.changed = true;
        }
    }

    public long getContentLength(String str) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            return -1;
        }
        return cachedContent.getLength();
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readFile() {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            com.google.android.exoplayer2.util.AtomicFile r3 = r8.atomicFile     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            java.io.InputStream r3 = r3.openRead()     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            r3.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            int r1 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            r4 = 1
            if (r1 == r4) goto L_0x001d
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
            return r0
        L_0x001d:
            int r1 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            r1 = r1 & r4
            if (r1 == 0) goto L_0x0056
            javax.crypto.Cipher r1 = r8.cipher     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            if (r1 != 0) goto L_0x002c
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
            return r0
        L_0x002c:
            r1 = 16
            byte[] r1 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            r3.readFully(r1)     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            javax.crypto.spec.IvParameterSpec r5 = new javax.crypto.spec.IvParameterSpec     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            r5.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            javax.crypto.Cipher r1 = r8.cipher     // Catch:{ InvalidKeyException -> 0x004f, InvalidAlgorithmParameterException -> 0x004d }
            r6 = 2
            javax.crypto.spec.SecretKeySpec r7 = r8.secretKeySpec     // Catch:{ InvalidKeyException -> 0x004f, InvalidAlgorithmParameterException -> 0x004d }
            r1.init(r6, r7, r5)     // Catch:{ InvalidKeyException -> 0x004f, InvalidAlgorithmParameterException -> 0x004d }
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            javax.crypto.CipherInputStream r5 = new javax.crypto.CipherInputStream     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            javax.crypto.Cipher r6 = r8.cipher     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            r5.<init>(r2, r6)     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            r1.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            goto L_0x005d
        L_0x004d:
            r1 = move-exception
            goto L_0x0050
        L_0x004f:
            r1 = move-exception
        L_0x0050:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            throw r2     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
        L_0x0056:
            boolean r1 = r8.encrypt     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
            if (r1 == 0) goto L_0x005c
            r8.changed = r4     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0083 }
        L_0x005c:
            r1 = r3
        L_0x005d:
            int r2 = r1.readInt()     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            r3 = 0
            r5 = 0
        L_0x0063:
            if (r3 >= r2) goto L_0x0075
            com.google.android.exoplayer2.upstream.cache.CachedContent r6 = new com.google.android.exoplayer2.upstream.cache.CachedContent     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            r6.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            r8.add(r6)     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            int r6 = r6.headerHashCode()     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            int r5 = r5 + r6
            int r3 = r3 + 1
            goto L_0x0063
        L_0x0075:
            int r2 = r1.readInt()     // Catch:{ FileNotFoundException -> 0x00a1, IOException -> 0x0089, all -> 0x0087 }
            if (r2 == r5) goto L_0x007f
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r1)
            return r0
        L_0x007f:
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r1)
            return r4
        L_0x0083:
            r1 = move-exception
            goto L_0x008c
        L_0x0085:
            r1 = r3
            goto L_0x00a2
        L_0x0087:
            r0 = move-exception
            goto L_0x009b
        L_0x0089:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x008c:
            java.lang.String r2 = "CachedContentIndex"
            java.lang.String r4 = "Error reading cache content index file."
            android.util.Log.e(r2, r4, r1)     // Catch:{ all -> 0x0099 }
            if (r3 == 0) goto L_0x0098
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
        L_0x0098:
            return r0
        L_0x0099:
            r0 = move-exception
            r1 = r3
        L_0x009b:
            if (r1 == 0) goto L_0x00a0
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r1)
        L_0x00a0:
            throw r0
        L_0x00a1:
        L_0x00a2:
            if (r1 == 0) goto L_0x00a7
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r1)
        L_0x00a7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedContentIndex.readFile():boolean");
    }

    private void writeFile() throws Cache.CacheException {
        DataOutputStream dataOutputStream = null;
        try {
            OutputStream startWrite = this.atomicFile.startWrite();
            ReusableBufferedOutputStream reusableBufferedOutputStream = this.bufferedOutputStream;
            if (reusableBufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(startWrite);
            } else {
                reusableBufferedOutputStream.reset(startWrite);
            }
            DataOutputStream dataOutputStream2 = new DataOutputStream(this.bufferedOutputStream);
            try {
                dataOutputStream2.writeInt(1);
                int i = 0;
                dataOutputStream2.writeInt(this.encrypt ? 1 : 0);
                if (this.encrypt) {
                    byte[] bArr = new byte[16];
                    new Random().nextBytes(bArr);
                    dataOutputStream2.write(bArr);
                    try {
                        this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                        dataOutputStream2.flush();
                        dataOutputStream2 = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
                    } catch (InvalidKeyException e) {
                        e = e;
                        throw new IllegalStateException(e);
                    } catch (InvalidAlgorithmParameterException e2) {
                        e = e2;
                        throw new IllegalStateException(e);
                    }
                }
                dataOutputStream2.writeInt(this.keyToContent.size());
                for (CachedContent next : this.keyToContent.values()) {
                    next.writeToStream(dataOutputStream2);
                    i += next.headerHashCode();
                }
                dataOutputStream2.writeInt(i);
                this.atomicFile.endWrite(dataOutputStream2);
                Util.closeQuietly((Closeable) null);
            } catch (IOException e3) {
                DataOutputStream dataOutputStream3 = dataOutputStream2;
                e = e3;
                dataOutputStream = dataOutputStream3;
                try {
                    throw new Cache.CacheException((Throwable) e);
                } catch (Throwable th) {
                    th = th;
                    Util.closeQuietly((Closeable) dataOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                DataOutputStream dataOutputStream4 = dataOutputStream2;
                th = th2;
                dataOutputStream = dataOutputStream4;
                Util.closeQuietly((Closeable) dataOutputStream);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            throw new Cache.CacheException((Throwable) e);
        }
    }

    private void add(CachedContent cachedContent) {
        this.keyToContent.put(cachedContent.key, cachedContent);
        this.idToKey.put(cachedContent.id, cachedContent.key);
    }

    /* access modifiers changed from: package-private */
    public void addNew(CachedContent cachedContent) {
        add(cachedContent);
        this.changed = true;
    }

    private CachedContent addNew(String str, long j) {
        CachedContent cachedContent = new CachedContent(getNewId(this.idToKey), str, j);
        addNew(cachedContent);
        return cachedContent;
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", BouncyCastleProvider.PROVIDER_NAME);
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public static int getNewId(SparseArray<String> sparseArray) {
        int i;
        int size = sparseArray.size();
        int i2 = 0;
        if (size == 0) {
            i = 0;
        } else {
            i = sparseArray.keyAt(size - 1) + 1;
        }
        if (i >= 0) {
            return i;
        }
        while (i2 < size && i2 == sparseArray.keyAt(i2)) {
            i2++;
        }
        return i2;
    }
}
