package org.apache.cordova.camera;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.cordova.CordovaInterface;
import tvi.webrtc.MediaStreamTrack;

public class FileHelper {
    private static final String LOG_TAG = "FileUtils";
    private static final String _DATA = "_data";

    public static String getRealPath(Uri uri, CordovaInterface cordovaInterface) {
        if (Build.VERSION.SDK_INT < 11) {
            return getRealPathFromURI_BelowAPI11(cordovaInterface.getActivity(), uri);
        }
        return getRealPathFromURI_API11_And_Above(cordovaInterface.getActivity(), uri);
    }

    public static String getRealPath(String str, CordovaInterface cordovaInterface) {
        return getRealPath(Uri.parse(str), cordovaInterface);
    }

    @SuppressLint({"NewApi"})
    public static String getRealPathFromURI_API11_And_Above(Context context, Uri uri) {
        Uri uri2 = null;
        if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, (String) null, (String[]) null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), (String) null, (String[]) null);
        } else if (isMediaDocument(uri)) {
            String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
            String str = split2[0];
            if ("image".equals(str)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if (MediaStreamTrack.VIDEO_TRACK_KIND.equals(str)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if (MediaStreamTrack.AUDIO_TRACK_KIND.equals(str)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
        }
        return null;
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri uri) {
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{_DATA}, (String) null, (String[]) null, (String) null);
            int columnIndexOrThrow = query.getColumnIndexOrThrow(_DATA);
            query.moveToFirst();
            return query.getString(columnIndexOrThrow);
        } catch (Exception unused) {
            return null;
        }
    }

    public static InputStream getInputStreamFromUriString(String str, CordovaInterface cordovaInterface) throws IOException {
        InputStream inputStream;
        if (str.startsWith("content")) {
            return cordovaInterface.getActivity().getContentResolver().openInputStream(Uri.parse(str));
        } else if (!str.startsWith("file://")) {
            return new FileInputStream(str);
        } else {
            int indexOf = str.indexOf("?");
            if (indexOf > -1) {
                str = str.substring(0, indexOf);
            }
            if (str.startsWith("file:///android_asset/")) {
                return cordovaInterface.getActivity().getAssets().open(Uri.parse(str).getPath().substring(15));
            }
            try {
                inputStream = cordovaInterface.getActivity().getContentResolver().openInputStream(Uri.parse(str));
            } catch (Exception unused) {
                inputStream = null;
            }
            return inputStream == null ? new FileInputStream(getRealPath(str, cordovaInterface)) : inputStream;
        }
    }

    public static String stripFileProtocol(String str) {
        return str.startsWith("file://") ? str.substring(7) : str;
    }

    public static String getMimeTypeForExtension(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            str = str.substring(lastIndexOf + 1);
        }
        String lowerCase = str.toLowerCase(Locale.getDefault());
        if (lowerCase.equals("3ga")) {
            return "audio/3gpp";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase);
    }

    public static String getMimeType(String str, CordovaInterface cordovaInterface) {
        Uri parse = Uri.parse(str);
        if (str.startsWith("content://")) {
            return cordovaInterface.getActivity().getContentResolver().getType(parse);
        }
        return getMimeTypeForExtension(parse.getPath());
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDataColumn(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ Exception -> 0x003d, all -> 0x0035 }
            r6 = 0
            r2 = r8
            r4 = r9
            r5 = r10
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x003d, all -> 0x0035 }
            if (r7 == 0) goto L_0x002f
            boolean r8 = r7.moveToFirst()     // Catch:{ Exception -> 0x002d, all -> 0x002b }
            if (r8 == 0) goto L_0x002f
            java.lang.String r8 = "_data"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch:{ Exception -> 0x002d, all -> 0x002b }
            java.lang.String r8 = r7.getString(r8)     // Catch:{ Exception -> 0x002d, all -> 0x002b }
            if (r7 == 0) goto L_0x002a
            r7.close()
        L_0x002a:
            return r8
        L_0x002b:
            r8 = move-exception
            goto L_0x0037
        L_0x002d:
            goto L_0x003e
        L_0x002f:
            if (r7 == 0) goto L_0x0034
            r7.close()
        L_0x0034:
            return r0
        L_0x0035:
            r8 = move-exception
            r7 = r0
        L_0x0037:
            if (r7 == 0) goto L_0x003c
            r7.close()
        L_0x003c:
            throw r8
        L_0x003d:
            r7 = r0
        L_0x003e:
            if (r7 == 0) goto L_0x0043
            r7.close()
        L_0x0043:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.camera.FileHelper.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
