package org.apache.cordova.file;

import android.net.Uri;

public class LocalFilesystemURL {
    public static final String FILESYSTEM_PROTOCOL = "cdvfile";
    public final String fsName;
    public final boolean isDirectory;
    public final String path;
    public final Uri uri;

    private LocalFilesystemURL(Uri uri2, String str, String str2, boolean z) {
        this.uri = uri2;
        this.fsName = str;
        this.path = str2;
        this.isDirectory = z;
    }

    public static LocalFilesystemURL parse(Uri uri2) {
        int indexOf;
        if (!FILESYSTEM_PROTOCOL.equals(uri2.getScheme())) {
            return null;
        }
        String path2 = uri2.getPath();
        boolean z = true;
        if (path2.length() < 1 || (indexOf = path2.indexOf(47, 1)) < 0) {
            return null;
        }
        String substring = path2.substring(1, indexOf);
        String substring2 = path2.substring(indexOf);
        if (substring2.charAt(substring2.length() - 1) != '/') {
            z = false;
        }
        return new LocalFilesystemURL(uri2, substring, substring2, z);
    }

    public static LocalFilesystemURL parse(String str) {
        return parse(Uri.parse(str));
    }

    public String toString() {
        return this.uri.toString();
    }
}
