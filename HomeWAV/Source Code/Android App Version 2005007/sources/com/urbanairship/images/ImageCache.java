package com.urbanairship.images;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.http.HttpResponseCache;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.LruCache;
import com.urbanairship.Logger;
import java.io.File;
import java.io.IOException;

class ImageCache {
    private static final String CACHE_DIR = "urbanairship-cache";
    private static final int DISK_CACHE_SIZE = 52428800;
    private static final int MAX_MEM_CACHE_FILE_SIZE = 1048576;
    private static final int MAX_MEM_CACHE_SIZE = 10485760;
    private Context context;
    private final LruCache<String, CacheEntry> memoryCache = new LruCache<String, CacheEntry>((int) Math.min(10485760, Runtime.getRuntime().maxMemory() / 8)) {
        /* access modifiers changed from: protected */
        public int sizeOf(String str, CacheEntry cacheEntry) {
            if (cacheEntry.byteCount > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) cacheEntry.byteCount;
        }
    };

    ImageCache(Context context2) {
        this.context = context2.getApplicationContext();
    }

    /* access modifiers changed from: package-private */
    public void cacheDrawable(String str, Drawable drawable, long j) {
        if (j <= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            this.memoryCache.put(str, new CacheEntry(drawable, j));
        }
    }

    /* access modifiers changed from: package-private */
    public Drawable getDrawable(String str) {
        CacheEntry cacheEntry = this.memoryCache.get(str);
        if (cacheEntry == null) {
            return null;
        }
        return cacheEntry.drawable;
    }

    /* access modifiers changed from: package-private */
    public void installHttpCache() {
        File file = new File(this.context.getApplicationContext().getCacheDir(), CACHE_DIR);
        if (!file.exists() && !file.mkdirs()) {
            Logger.error("Failed to create the cache.", new Object[0]);
        }
        if (HttpResponseCache.getInstalled() == null) {
            try {
                HttpResponseCache.install(file, 52428800);
            } catch (IOException unused) {
                Logger.error("Unable to install image loader cache", new Object[0]);
            }
        }
    }

    private static class CacheEntry {
        /* access modifiers changed from: private */
        public long byteCount;
        /* access modifiers changed from: private */
        public Drawable drawable;

        CacheEntry(Drawable drawable2, long j) {
            this.drawable = drawable2;
            this.byteCount = j;
        }
    }
}
