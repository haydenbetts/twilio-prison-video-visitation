package org.apache.cordova.file;

import android.content.res.AssetManager;
import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.LOG;
import org.json.JSONException;
import org.json.JSONObject;

public class AssetFilesystem extends Filesystem {
    private static final String LOG_TAG = "AssetFilesystem";
    private static Map<String, Long> lengthCache;
    private static Map<String, String[]> listCache;
    private static boolean listCacheFromFile;
    private static Object listCacheLock = new Object();
    private final AssetManager assetManager;

    /* access modifiers changed from: package-private */
    public LocalFilesystemURL URLforFilesystemPath(String str) {
        return null;
    }

    public boolean canRemoveFileAtLocalURL(LocalFilesystemURL localFilesystemURL) {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0043 A[SYNTHETIC, Splitter:B:24:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0058 A[SYNTHETIC, Splitter:B:35:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0068 A[Catch:{ IOException -> 0x002c }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x007a A[SYNTHETIC, Splitter:B:46:0x007a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void lazyInitCaches() {
        /*
            r6 = this;
            java.lang.Object r0 = listCacheLock
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.String[]> r1 = listCache     // Catch:{ all -> 0x008b }
            if (r1 != 0) goto L_0x0089
            r1 = 0
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ ClassNotFoundException -> 0x004f, IOException -> 0x0040, all -> 0x003b }
            android.content.res.AssetManager r3 = r6.assetManager     // Catch:{ ClassNotFoundException -> 0x004f, IOException -> 0x0040, all -> 0x003b }
            java.lang.String r4 = "cdvasset.manifest"
            java.io.InputStream r3 = r3.open(r4)     // Catch:{ ClassNotFoundException -> 0x004f, IOException -> 0x0040, all -> 0x003b }
            r2.<init>(r3)     // Catch:{ ClassNotFoundException -> 0x004f, IOException -> 0x0040, all -> 0x003b }
            java.lang.Object r1 = r2.readObject()     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0037 }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0037 }
            listCache = r1     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0037 }
            java.lang.Object r1 = r2.readObject()     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0037 }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0037 }
            lengthCache = r1     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0037 }
            r1 = 1
            listCacheFromFile = r1     // Catch:{ ClassNotFoundException -> 0x0039, IOException -> 0x0037 }
            r2.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0064
        L_0x002c:
            r1 = move-exception
            java.lang.String r2 = "AssetFilesystem"
            java.lang.String r1 = r1.getLocalizedMessage()     // Catch:{ all -> 0x008b }
        L_0x0033:
            org.apache.cordova.LOG.d(r2, r1)     // Catch:{ all -> 0x008b }
            goto L_0x0064
        L_0x0037:
            goto L_0x0041
        L_0x0039:
            r1 = move-exception
            goto L_0x0053
        L_0x003b:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x0078
        L_0x0040:
            r2 = r1
        L_0x0041:
            if (r2 == 0) goto L_0x0064
            r2.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x0064
        L_0x0047:
            r1 = move-exception
            java.lang.String r2 = "AssetFilesystem"
            java.lang.String r1 = r1.getLocalizedMessage()     // Catch:{ all -> 0x008b }
            goto L_0x0033
        L_0x004f:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x0053:
            r1.printStackTrace()     // Catch:{ all -> 0x0077 }
            if (r2 == 0) goto L_0x0064
            r2.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0064
        L_0x005c:
            r1 = move-exception
            java.lang.String r2 = "AssetFilesystem"
            java.lang.String r1 = r1.getLocalizedMessage()     // Catch:{ all -> 0x008b }
            goto L_0x0033
        L_0x0064:
            java.util.Map<java.lang.String, java.lang.String[]> r1 = listCache     // Catch:{ all -> 0x008b }
            if (r1 != 0) goto L_0x0089
            java.lang.String r1 = "AssetFilesystem"
            java.lang.String r2 = "Asset manifest not found. Recursive copies and directory listing will be slow."
            org.apache.cordova.LOG.w((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x008b }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x008b }
            r1.<init>()     // Catch:{ all -> 0x008b }
            listCache = r1     // Catch:{ all -> 0x008b }
            goto L_0x0089
        L_0x0077:
            r1 = move-exception
        L_0x0078:
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ IOException -> 0x007e }
            goto L_0x0088
        L_0x007e:
            r2 = move-exception
            java.lang.String r3 = "AssetFilesystem"
            java.lang.String r2 = r2.getLocalizedMessage()     // Catch:{ all -> 0x008b }
            org.apache.cordova.LOG.d(r3, r2)     // Catch:{ all -> 0x008b }
        L_0x0088:
            throw r1     // Catch:{ all -> 0x008b }
        L_0x0089:
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            return
        L_0x008b:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            throw r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.file.AssetFilesystem.lazyInitCaches():void");
    }

    private String[] listAssets(String str) throws IOException {
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        lazyInitCaches();
        String[] strArr = listCache.get(str);
        if (strArr != null) {
            return strArr;
        }
        if (listCacheFromFile) {
            return new String[0];
        }
        String[] list = this.assetManager.list(str);
        listCache.put(str, list);
        return list;
    }

    private long getAssetSize(String str) throws FileNotFoundException {
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        lazyInitCaches();
        Map<String, Long> map = lengthCache;
        if (map != null) {
            Long l = map.get(str);
            if (l != null) {
                return l.longValue();
            }
            throw new FileNotFoundException("Asset not found: " + str);
        }
        try {
            CordovaResourceApi.OpenForReadResult openForRead = this.resourceApi.openForRead(nativeUriForFullPath(str));
            long j = openForRead.length;
            if (j < 0) {
                j = (long) openForRead.inputStream.available();
            }
            if (openForRead != null) {
                try {
                    openForRead.inputStream.close();
                } catch (IOException e) {
                    LOG.d(LOG_TAG, e.getLocalizedMessage());
                }
            }
            return j;
        } catch (IOException e2) {
            FileNotFoundException fileNotFoundException = new FileNotFoundException("File not found: " + str);
            fileNotFoundException.initCause(e2);
            throw fileNotFoundException;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    null.inputStream.close();
                } catch (IOException e3) {
                    LOG.d(LOG_TAG, e3.getLocalizedMessage());
                }
            }
            throw th;
        }
    }

    public AssetFilesystem(AssetManager assetManager2, CordovaResourceApi cordovaResourceApi) {
        super(Uri.parse("file:///android_asset/"), "assets", cordovaResourceApi);
        this.assetManager = assetManager2;
    }

    public Uri toNativeUri(LocalFilesystemURL localFilesystemURL) {
        return nativeUriForFullPath(localFilesystemURL.path);
    }

    public LocalFilesystemURL toLocalUri(Uri uri) {
        if (!"file".equals(uri.getScheme())) {
            return null;
        }
        Uri fromFile = Uri.fromFile(new File(uri.getPath()));
        String encodedPath = this.rootUri.getEncodedPath();
        String substring = encodedPath.substring(0, encodedPath.length() - 1);
        if (!fromFile.getEncodedPath().startsWith(substring)) {
            return null;
        }
        String substring2 = fromFile.getEncodedPath().substring(substring.length());
        if (!substring2.isEmpty()) {
            substring2 = substring2.substring(1);
        }
        Uri.Builder path = new Uri.Builder().scheme(LocalFilesystemURL.FILESYSTEM_PROTOCOL).authority("localhost").path(this.name);
        if (!substring2.isEmpty()) {
            path.appendEncodedPath(substring2);
        }
        if (isDirectory(substring2) || uri.getPath().endsWith("/")) {
            path.appendEncodedPath("");
        }
        return LocalFilesystemURL.parse(path.build());
    }

    private boolean isDirectory(String str) {
        try {
            return listAssets(str).length != 0;
        } catch (IOException unused) {
            return false;
        }
    }

    public LocalFilesystemURL[] listChildren(LocalFilesystemURL localFilesystemURL) throws FileNotFoundException {
        String substring = localFilesystemURL.path.substring(1);
        if (substring.endsWith("/")) {
            substring = substring.substring(0, substring.length() - 1);
        }
        try {
            String[] listAssets = listAssets(substring);
            LocalFilesystemURL[] localFilesystemURLArr = new LocalFilesystemURL[listAssets.length];
            for (int i = 0; i < listAssets.length; i++) {
                localFilesystemURLArr[i] = localUrlforFullPath(new File(localFilesystemURL.path, listAssets[i]).getPath());
            }
            return localFilesystemURLArr;
        } catch (IOException e) {
            FileNotFoundException fileNotFoundException = new FileNotFoundException();
            fileNotFoundException.initCause(e);
            throw fileNotFoundException;
        }
    }

    public JSONObject getFileForLocalURL(LocalFilesystemURL localFilesystemURL, String str, JSONObject jSONObject, boolean z) throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException {
        LocalFilesystemURL localFilesystemURL2;
        if (jSONObject == null || !jSONObject.optBoolean("create")) {
            if (z && !str.endsWith("/")) {
                str = str + "/";
            }
            if (str.startsWith("/")) {
                localFilesystemURL2 = localUrlforFullPath(normalizePath(str));
            } else {
                localFilesystemURL2 = localUrlforFullPath(normalizePath(localFilesystemURL.path + "/" + str));
            }
            getFileMetadataForLocalURL(localFilesystemURL2);
            boolean isDirectory = isDirectory(localFilesystemURL2.path);
            if (z && !isDirectory) {
                throw new TypeMismatchException("path doesn't exist or is file");
            } else if (z || !isDirectory) {
                return makeEntryForURL(localFilesystemURL2);
            } else {
                throw new TypeMismatchException("path doesn't exist or is directory");
            }
        } else {
            throw new UnsupportedOperationException("Assets are read-only");
        }
    }

    public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL localFilesystemURL) throws FileNotFoundException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("size", localFilesystemURL.isDirectory ? 0 : getAssetSize(localFilesystemURL.path));
            jSONObject.put("type", localFilesystemURL.isDirectory ? "text/directory" : this.resourceApi.getMimeType(toNativeUri(localFilesystemURL)));
            jSONObject.put("name", new File(localFilesystemURL.path).getName());
            jSONObject.put("fullPath", localFilesystemURL.path);
            jSONObject.put("lastModifiedDate", 0);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public long writeToFileAtURL(LocalFilesystemURL localFilesystemURL, String str, int i, boolean z) throws NoModificationAllowedException, IOException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    /* access modifiers changed from: package-private */
    public long truncateFileAtURL(LocalFilesystemURL localFilesystemURL, long j) throws IOException, NoModificationAllowedException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    /* access modifiers changed from: package-private */
    public String filesystemPathForURL(LocalFilesystemURL localFilesystemURL) {
        return new File(this.rootUri.getPath(), localFilesystemURL.path).toString();
    }

    /* access modifiers changed from: package-private */
    public boolean removeFileAtLocalURL(LocalFilesystemURL localFilesystemURL) throws InvalidModificationException, NoModificationAllowedException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    /* access modifiers changed from: package-private */
    public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL localFilesystemURL) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Assets are read-only");
    }
}
