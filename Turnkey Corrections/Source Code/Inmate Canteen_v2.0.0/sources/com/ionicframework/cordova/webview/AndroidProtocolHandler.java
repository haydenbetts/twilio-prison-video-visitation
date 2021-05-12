package com.ionicframework.cordova.webview;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AndroidProtocolHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "AndroidProtocolHandler";
    private Context context;

    public AndroidProtocolHandler(Context context2) {
        this.context = context2;
    }

    public InputStream openAsset(String str) throws IOException {
        return this.context.getAssets().open(str, 2);
    }

    public InputStream openResource(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        String str = pathSegments.get(pathSegments.size() - 2);
        String str2 = pathSegments.get(pathSegments.size() - 1).split("\\.")[0];
        try {
            if (this.context.getApplicationContext() != null) {
                this.context = this.context.getApplicationContext();
            }
            int fieldId = getFieldId(this.context, str, str2);
            if (getValueType(this.context, fieldId) == 3) {
                return this.context.getResources().openRawResource(fieldId);
            }
            Log.e(TAG, "Asset not of type string: " + uri);
            return null;
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Unable to open resource URL: " + uri, e);
            return null;
        } catch (NoSuchFieldException e2) {
            Log.e(TAG, "Unable to open resource URL: " + uri, e2);
            return null;
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "Unable to open resource URL: " + uri, e3);
            return null;
        }
    }

    public InputStream openFile(String str) throws IOException {
        return new FileInputStream(new File(str.replace(WebViewLocalServer.fileStart, "")));
    }

    public InputStream openContentUrl(Uri uri) throws IOException {
        String str;
        Integer valueOf = Integer.valueOf(uri.getPort());
        if (valueOf.intValue() == -1) {
            String uri2 = uri.toString();
            str = uri2.replace(uri.getScheme() + "://" + uri.getHost() + WebViewLocalServer.contentStart, "content:/");
        } else {
            String uri3 = uri.toString();
            str = uri3.replace(uri.getScheme() + "://" + uri.getHost() + ":" + valueOf + WebViewLocalServer.contentStart, "content:/");
        }
        try {
            return this.context.getContentResolver().openInputStream(Uri.parse(str));
        } catch (SecurityException e) {
            Log.e(TAG, "Unable to open content URL: " + uri, e);
            return null;
        }
    }

    private static int getFieldId(Context context2, String str, String str2) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        ClassLoader classLoader = context2.getClassLoader();
        return classLoader.loadClass(context2.getPackageName() + ".R$" + str).getField(str2).getInt((Object) null);
    }

    private static int getValueType(Context context2, int i) {
        TypedValue typedValue = new TypedValue();
        context2.getResources().getValue(i, typedValue, true);
        return typedValue.type;
    }
}
