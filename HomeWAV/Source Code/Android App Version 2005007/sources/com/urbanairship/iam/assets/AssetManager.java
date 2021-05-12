package com.urbanairship.iam.assets;

import android.content.Context;
import com.urbanairship.Logger;
import com.urbanairship.iam.InAppMessage;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Callable;

public class AssetManager {
    public static final int PREPARE_RESULT_CANCEL = 2;
    public static final int PREPARE_RESULT_OK = 0;
    public static final int PREPARE_RESULT_RETRY = 1;
    private final AssetCache assetCache;
    private PrepareAssetsDelegate assetsDelegate;
    private CachePolicyDelegate cachePolicyDelegate;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrepareResult {
    }

    public AssetManager(Context context) {
        this.assetCache = new AssetCache(context);
        this.assetsDelegate = new AirshipPrepareAssetsDelegate();
    }

    AssetManager(AssetCache assetCache2) {
        this.assetCache = assetCache2;
    }

    public void setPrepareAssetDelegate(PrepareAssetsDelegate prepareAssetsDelegate) {
        this.assetsDelegate = prepareAssetsDelegate;
    }

    public void setCachePolicyDelegate(CachePolicyDelegate cachePolicyDelegate2) {
        this.cachePolicyDelegate = cachePolicyDelegate2;
    }

    public void onSchedule(String str, Callable<InAppMessage> callable) {
        CachePolicyDelegate cachePolicyDelegate2 = this.cachePolicyDelegate;
        PrepareAssetsDelegate prepareAssetsDelegate = this.assetsDelegate;
        if (cachePolicyDelegate2 != null && prepareAssetsDelegate != null) {
            try {
                InAppMessage call = callable.call();
                if (cachePolicyDelegate2.shouldCacheOnSchedule(str, call)) {
                    prepareAssetsDelegate.onSchedule(str, call, this.assetCache.getAssets(str));
                    this.assetCache.releaseAssets(str, false);
                }
            } catch (Exception e) {
                Logger.error(e, "Unable to prepare assets for schedule: %s", str);
            }
        }
    }

    public int onPrepare(String str, InAppMessage inAppMessage) {
        PrepareAssetsDelegate prepareAssetsDelegate = this.assetsDelegate;
        if (prepareAssetsDelegate != null) {
            return prepareAssetsDelegate.onPrepare(str, inAppMessage, this.assetCache.getAssets(str));
        }
        return 0;
    }

    public void onDisplayFinished(String str, InAppMessage inAppMessage) {
        CachePolicyDelegate cachePolicyDelegate2 = this.cachePolicyDelegate;
        this.assetCache.releaseAssets(str, cachePolicyDelegate2 == null || !cachePolicyDelegate2.shouldPersistCacheAfterDisplay(str, inAppMessage));
    }

    public void onFinish(String str) {
        this.assetCache.releaseAssets(str, true);
    }

    public Assets getAssets(String str) {
        return this.assetCache.getAssets(str);
    }
}
