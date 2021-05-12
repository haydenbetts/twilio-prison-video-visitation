package com.urbanairship.iam.assets;

import android.content.Context;
import android.os.Build;
import android.os.storage.StorageManager;
import com.urbanairship.Logger;
import com.urbanairship.util.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class AssetCache {
    private static final String CACHE_DIRECTORY = "com.urbanairship.iam.assets";
    private final Map<String, Assets> activeAssets = new HashMap();
    private final File storageDirectory;
    private final StorageManager storageManager;

    AssetCache(Context context) {
        this.storageDirectory = new File(context.getCacheDir(), CACHE_DIRECTORY);
        this.storageManager = findStorageManager(context);
    }

    /* access modifiers changed from: package-private */
    public Assets getAssets(String str) {
        Assets assets;
        synchronized (this.activeAssets) {
            assets = this.activeAssets.get(str);
            if (assets == null) {
                assets = Assets.load(getAssetsDirectory(str));
                this.activeAssets.put(str, assets);
            }
        }
        return assets;
    }

    /* access modifiers changed from: package-private */
    public void releaseAssets(String str, boolean z) {
        synchronized (this.activeAssets) {
            if (z) {
                FileUtils.deleteRecursively(getAssetsDirectory(str));
            }
            this.activeAssets.remove(str);
        }
    }

    private File getAssetsDirectory(String str) {
        if (!this.storageDirectory.exists() && !this.storageDirectory.mkdirs()) {
            Logger.error("Failed to create asset storage directory.", new Object[0]);
        }
        File file = new File(this.storageDirectory, str);
        if (!file.exists() && !file.mkdirs()) {
            Logger.error("Failed to create assets directory.", new Object[0]);
        }
        if (this.storageManager != null && Build.VERSION.SDK_INT >= 26 && file.exists()) {
            try {
                this.storageManager.setCacheBehaviorGroup(file, true);
            } catch (IOException e) {
                Logger.error(e, "Failed to set cache behavior on directory: %s", file.getAbsoluteFile());
            }
        }
        return file;
    }

    private static StorageManager findStorageManager(Context context) {
        try {
            return (StorageManager) context.getSystemService("storage");
        } catch (Exception unused) {
            return null;
        }
    }
}
