package org.apache.cordova.camera;

import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import java.io.File;

public class CordovaUri {
    private Uri androidUri;
    private String fileName;
    private Uri fileUri;

    CordovaUri(Uri uri) {
        if (uri.getScheme().equals("content")) {
            this.androidUri = uri;
            this.fileName = getFileNameFromUri(this.androidUri);
            this.fileUri = Uri.parse("file://" + this.fileName);
            return;
        }
        this.fileUri = uri;
        this.fileName = FileHelper.stripFileProtocol(uri.toString());
    }

    public Uri getFileUri() {
        return this.fileUri;
    }

    public String getFilePath() {
        return this.fileName;
    }

    public Uri getCorrectUri() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.androidUri;
        }
        return this.fileUri;
    }

    private String getFileNameFromUri(Uri uri) {
        String str = uri.toString().split("external_files")[1];
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        return externalStorageDirectory.getAbsolutePath() + str;
    }
}
