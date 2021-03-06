package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

class SafeKeyGenerator {
    private final LruCache<Key, String> loadIdToSafeHash = new LruCache<>(1000);

    SafeKeyGenerator() {
    }

    public String getSafeKey(Key key) {
        String str;
        synchronized (this.loadIdToSafeHash) {
            str = this.loadIdToSafeHash.get(key);
        }
        if (str == null) {
            try {
                MessageDigest instance = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA256);
                key.updateDiskCacheKey(instance);
                str = Util.sha256BytesToHex(instance.digest());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
            synchronized (this.loadIdToSafeHash) {
                this.loadIdToSafeHash.put(key, str);
            }
        }
        return str;
    }
}
