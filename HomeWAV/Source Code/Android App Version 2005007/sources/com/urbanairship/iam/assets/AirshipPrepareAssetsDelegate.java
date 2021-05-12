package com.urbanairship.iam.assets;

import android.graphics.BitmapFactory;
import com.urbanairship.Logger;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.iam.MediaInfo;
import com.urbanairship.iam.banner.BannerDisplayContent;
import com.urbanairship.iam.fullscreen.FullScreenDisplayContent;
import com.urbanairship.iam.modal.ModalDisplayContent;
import com.urbanairship.json.JsonMap;
import com.urbanairship.util.FileUtils;
import com.urbanairship.util.UAHttpStatusUtil;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AirshipPrepareAssetsDelegate implements PrepareAssetsDelegate {
    public static final String IMAGE_HEIGHT_CACHE_KEY = "height";
    public static final String IMAGE_WIDTH_CACHE_KEY = "width";

    public void onSchedule(String str, InAppMessage inAppMessage, Assets assets) {
        onPrepare(str, inAppMessage, assets);
    }

    public int onPrepare(String str, InAppMessage inAppMessage, Assets assets) {
        MediaInfo mediaInfo = getMediaInfo(inAppMessage);
        if (mediaInfo == null || !"image".equals(mediaInfo.getType()) || assets.file(mediaInfo.getUrl()).exists()) {
            return 0;
        }
        try {
            FileUtils.DownloadResult cacheImage = cacheImage(assets, mediaInfo.getUrl());
            if (cacheImage.isSuccess) {
                return 0;
            }
            if (UAHttpStatusUtil.inClientErrorRange(cacheImage.statusCode)) {
                return 2;
            }
            return 1;
        } catch (IOException e) {
            Logger.error(e, "Unable to download file: %s ", mediaInfo.getUrl());
            return 1;
        }
    }

    /* access modifiers changed from: protected */
    public FileUtils.DownloadResult cacheImage(Assets assets, String str) throws IOException {
        File file = assets.file(str);
        FileUtils.DownloadResult downloadFile = FileUtils.downloadFile(new URL(str), file);
        if (downloadFile.isSuccess) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            assets.setMetadata(str, JsonMap.newBuilder().putOpt("width", Integer.valueOf(options.outWidth)).putOpt("height", Integer.valueOf(options.outHeight)).build());
        }
        return downloadFile;
    }

    private MediaInfo getMediaInfo(InAppMessage inAppMessage) {
        String type = inAppMessage.getType();
        type.hashCode();
        char c = 65535;
        switch (type.hashCode()) {
            case -1396342996:
                if (type.equals(InAppMessage.TYPE_BANNER)) {
                    c = 0;
                    break;
                }
                break;
            case 104069805:
                if (type.equals(InAppMessage.TYPE_MODAL)) {
                    c = 1;
                    break;
                }
                break;
            case 110066619:
                if (type.equals(InAppMessage.TYPE_FULLSCREEN)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                BannerDisplayContent bannerDisplayContent = (BannerDisplayContent) inAppMessage.getDisplayContent();
                if (bannerDisplayContent != null) {
                    return bannerDisplayContent.getMedia();
                }
                return null;
            case 1:
                ModalDisplayContent modalDisplayContent = (ModalDisplayContent) inAppMessage.getDisplayContent();
                if (modalDisplayContent != null) {
                    return modalDisplayContent.getMedia();
                }
                return null;
            case 2:
                FullScreenDisplayContent fullScreenDisplayContent = (FullScreenDisplayContent) inAppMessage.getDisplayContent();
                if (fullScreenDisplayContent != null) {
                    return fullScreenDisplayContent.getMedia();
                }
                return null;
            default:
                return null;
        }
    }
}
