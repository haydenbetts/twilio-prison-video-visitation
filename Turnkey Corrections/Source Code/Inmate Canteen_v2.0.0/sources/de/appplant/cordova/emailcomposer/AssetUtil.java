package de.appplant.cordova.emailcomposer;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

final class AssetUtil {
    private static final String ATTACHMENT_FOLDER = "/email_composer";
    private final Context ctx;

    AssetUtil(Context context) {
        this.ctx = context;
    }

    /* access modifiers changed from: package-private */
    public void cleanupAttachmentFolder() {
        try {
            File file = new File(this.ctx.getExternalCacheDir() + ATTACHMENT_FOLDER);
            if (file.isDirectory()) {
                for (File delete : file.listFiles()) {
                    delete.delete();
                }
            }
        } catch (Exception unused) {
            Log.w("EmailComposer", "Missing external cache dir");
        }
    }

    /* access modifiers changed from: package-private */
    public Uri parse(String str) {
        if (str.startsWith("res:")) {
            return getUriForResourcePath(str);
        }
        if (str.startsWith("app://")) {
            return getUriForAppInternalPath(str);
        }
        if (str.startsWith("file:///")) {
            return getUriForAbsolutePath(str);
        }
        if (str.startsWith("file://")) {
            return getUriForAssetPath(str);
        }
        if (str.startsWith("base64:")) {
            return getUriForBase64Content(str);
        }
        return Uri.parse(str);
    }

    private Uri getUriForAbsolutePath(String str) {
        String replaceFirst = str.replaceFirst("file://", "");
        File file = new File(replaceFirst);
        if (!file.exists()) {
            Log.e("EmailComposer", "File not found: " + replaceFirst);
        }
        return getUriForFile(this.ctx, file);
    }

    private Uri getUriForAssetPath(String str) {
        String replaceFirst = str.replaceFirst("file:/", "www");
        String substring = replaceFirst.substring(replaceFirst.lastIndexOf(47) + 1);
        File externalCacheDir = this.ctx.getExternalCacheDir();
        if (externalCacheDir == null) {
            Log.e("EmailComposer", "Missing external cache dir");
            return Uri.EMPTY;
        }
        String str2 = externalCacheDir.toString() + ATTACHMENT_FOLDER;
        File file = new File(str2, substring);
        new File(str2).mkdir();
        try {
            copyFile(this.ctx.getAssets().open(replaceFirst), new FileOutputStream(file));
        } catch (Exception e) {
            Log.e("EmailComposer", "File not found: " + replaceFirst);
            e.printStackTrace();
        }
        return getUriForFile(this.ctx, file);
    }

    private Uri getUriForAppInternalPath(String str) {
        String replaceFirst = str.replaceFirst("app:/", "");
        String substring = replaceFirst.substring(replaceFirst.lastIndexOf(47) + 1);
        File externalCacheDir = this.ctx.getExternalCacheDir();
        if (externalCacheDir == null) {
            Log.e("EmailComposer", "Missing external cache dir");
            return Uri.EMPTY;
        }
        String str2 = externalCacheDir.toString() + ATTACHMENT_FOLDER;
        File file = new File(str2, substring);
        new File(str2).mkdir();
        File filesDir = this.ctx.getFilesDir();
        String str3 = filesDir.getAbsolutePath() + "/.." + replaceFirst;
        try {
            copyFile(new FileInputStream(str3), new FileOutputStream(file));
        } catch (Exception e) {
            Log.e("EmailComposer", "File not found: " + str3);
            e.printStackTrace();
        }
        return getUriForFile(this.ctx, file);
    }

    private Uri getUriForResourcePath(String str) {
        String replaceFirst = str.replaceFirst("res://", "");
        String substring = replaceFirst.substring(replaceFirst.lastIndexOf(47) + 1);
        String substring2 = substring.substring(0, substring.lastIndexOf(46));
        String substring3 = replaceFirst.substring(replaceFirst.lastIndexOf(46));
        File externalCacheDir = this.ctx.getExternalCacheDir();
        int resId = getResId(replaceFirst);
        if (externalCacheDir == null) {
            Log.e("EmailComposer", "Missing external cache dir");
            return Uri.EMPTY;
        }
        if (resId == 0) {
            Log.e("EmailComposer", "File not found: " + replaceFirst);
        }
        String str2 = externalCacheDir.toString() + ATTACHMENT_FOLDER;
        File file = new File(str2, substring2 + substring3);
        new File(str2).mkdir();
        try {
            copyFile(this.ctx.getResources().openRawResource(resId), new FileOutputStream(file));
        } catch (Exception e) {
            Log.e("EmailComposer", "File not found: " + replaceFirst);
            e.printStackTrace();
        }
        return getUriForFile(this.ctx, file);
    }

    private Uri getUriForBase64Content(String str) {
        String substring = str.substring(str.indexOf(":") + 1, str.indexOf("//"));
        String substring2 = str.substring(str.indexOf("//") + 2);
        File externalCacheDir = this.ctx.getExternalCacheDir();
        if (externalCacheDir == null) {
            Log.e("EmailComposer", "Missing external cache dir");
            return Uri.EMPTY;
        }
        String str2 = externalCacheDir.toString() + ATTACHMENT_FOLDER;
        File file = new File(str2, substring);
        new File(str2).mkdir();
        try {
            copyFile(new ByteArrayInputStream(Base64.decode(substring2, 0)), new FileOutputStream(file));
        } catch (Exception e) {
            Log.e("EmailComposer", "Invalid Base64 string");
            e.printStackTrace();
        }
        return getUriForFile(this.ctx, file);
    }

    private Uri getUriForFile(Context context, File file) {
        try {
            return Provider.getUriForFile(context, context.getPackageName() + ".provider", file);
        } catch (Exception e) {
            Log.e("EmailComposer", "Failed to get uri for file");
            e.printStackTrace();
            return Uri.EMPTY;
        }
    }

    private void copyFile(InputStream inputStream, FileOutputStream fileOutputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private int getResId(String str) {
        Resources resources = this.ctx.getResources();
        String packageName = this.ctx.getPackageName();
        String str2 = "drawable";
        if (str.contains("/")) {
            String substring = str.substring(0, str.lastIndexOf(47));
            str = str.substring(str.lastIndexOf(47) + 1);
            str2 = substring;
        }
        String substring2 = str.substring(0, str.lastIndexOf(46));
        int identifier = resources.getIdentifier(substring2, str2, packageName);
        if (identifier == 0) {
            identifier = resources.getIdentifier(substring2, "mipmap", packageName);
        }
        return identifier == 0 ? resources.getIdentifier(substring2, "drawable", packageName) : identifier;
    }
}
