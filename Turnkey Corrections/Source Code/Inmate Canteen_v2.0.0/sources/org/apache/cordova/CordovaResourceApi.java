package org.apache.cordova;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import com.github.kevinsawicki.http.HttpRequest;
import com.ionicframework.cordova.webview.WebViewLocalServer;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Locale;

public class CordovaResourceApi {
    private static final String[] LOCAL_FILE_PROJECTION = {"_data"};
    private static final String LOG_TAG = "CordovaResourceApi";
    public static final String PLUGIN_URI_SCHEME = "cdvplugin";
    public static final int URI_TYPE_ASSET = 1;
    public static final int URI_TYPE_CONTENT = 2;
    public static final int URI_TYPE_DATA = 4;
    public static final int URI_TYPE_FILE = 0;
    public static final int URI_TYPE_HTTP = 5;
    public static final int URI_TYPE_HTTPS = 6;
    public static final int URI_TYPE_PLUGIN = 7;
    public static final int URI_TYPE_RESOURCE = 3;
    public static final int URI_TYPE_UNKNOWN = -1;
    public static Thread jsThread;
    private final AssetManager assetManager;
    private final ContentResolver contentResolver;
    private final PluginManager pluginManager;
    private boolean threadCheckingEnabled = true;

    public CordovaResourceApi(Context context, PluginManager pluginManager2) {
        this.contentResolver = context.getContentResolver();
        this.assetManager = context.getAssets();
        this.pluginManager = pluginManager2;
    }

    public void setThreadCheckingEnabled(boolean z) {
        this.threadCheckingEnabled = z;
    }

    public boolean isThreadCheckingEnabled() {
        return this.threadCheckingEnabled;
    }

    public static int getUriType(Uri uri) {
        assertNonRelative(uri);
        String scheme = uri.getScheme();
        if ("content".equalsIgnoreCase(scheme)) {
            return 2;
        }
        if ("android.resource".equalsIgnoreCase(scheme)) {
            return 3;
        }
        if ("file".equalsIgnoreCase(scheme)) {
            return uri.getPath().startsWith("/android_asset/") ? 1 : 0;
        }
        if ("data".equalsIgnoreCase(scheme)) {
            return 4;
        }
        if (WebViewLocalServer.httpScheme.equalsIgnoreCase(scheme)) {
            return 5;
        }
        if (WebViewLocalServer.httpsScheme.equalsIgnoreCase(scheme)) {
            return 6;
        }
        return PLUGIN_URI_SCHEME.equalsIgnoreCase(scheme) ? 7 : -1;
    }

    public Uri remapUri(Uri uri) {
        assertNonRelative(uri);
        Uri remapUri = this.pluginManager.remapUri(uri);
        return remapUri != null ? remapUri : uri;
    }

    public String remapPath(String str) {
        return remapUri(Uri.fromFile(new File(str))).getPath();
    }

    public File mapUriToFile(Uri uri) {
        Cursor query;
        assertBackgroundThread();
        int uriType = getUriType(uri);
        if (uriType == 0) {
            return new File(uri.getPath());
        }
        if (uriType != 2 || (query = this.contentResolver.query(uri, LOCAL_FILE_PROJECTION, (String) null, (String[]) null, (String) null)) == null) {
            return null;
        }
        try {
            int columnIndex = query.getColumnIndex(LOCAL_FILE_PROJECTION[0]);
            if (columnIndex != -1 && query.getCount() > 0) {
                query.moveToFirst();
                String string = query.getString(columnIndex);
                if (string != null) {
                    return new File(string);
                }
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    public String getMimeType(Uri uri) {
        switch (getUriType(uri)) {
            case 0:
            case 1:
                return getMimeTypeFromPath(uri.getPath());
            case 2:
            case 3:
                return this.contentResolver.getType(uri);
            case 4:
                return getDataUriMimeType(uri);
            case 5:
            case 6:
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
                    httpURLConnection.setDoInput(false);
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_HEAD);
                    String headerField = httpURLConnection.getHeaderField(HttpRequest.HEADER_CONTENT_TYPE);
                    return headerField != null ? headerField.split(";")[0] : headerField;
                } catch (IOException unused) {
                    return null;
                }
            default:
                return null;
        }
    }

    private String getMimeTypeFromPath(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            str = str.substring(lastIndexOf + 1);
        }
        String lowerCase = str.toLowerCase(Locale.getDefault());
        if (lowerCase.equals("3ga")) {
            return "audio/3gpp";
        }
        if (lowerCase.equals("js")) {
            return "text/javascript";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase);
    }

    public OpenForReadResult openForRead(Uri uri) throws IOException {
        return openForRead(uri, false);
    }

    public OpenForReadResult openForRead(Uri uri, boolean z) throws IOException {
        AssetFileDescriptor assetFileDescriptor;
        long j;
        FileInputStream fileInputStream;
        if (!z) {
            assertBackgroundThread();
        }
        switch (getUriType(uri)) {
            case 0:
                FileInputStream fileInputStream2 = new FileInputStream(uri.getPath());
                return new OpenForReadResult(uri, fileInputStream2, getMimeTypeFromPath(uri.getPath()), fileInputStream2.getChannel().size(), (AssetFileDescriptor) null);
            case 1:
                String substring = uri.getPath().substring(15);
                try {
                    AssetFileDescriptor openFd = this.assetManager.openFd(substring);
                    FileInputStream createInputStream = openFd.createInputStream();
                    j = openFd.getLength();
                    assetFileDescriptor = openFd;
                    fileInputStream = createInputStream;
                } catch (FileNotFoundException unused) {
                    InputStream open = this.assetManager.open(substring);
                    j = (long) open.available();
                    assetFileDescriptor = null;
                    fileInputStream = open;
                }
                return new OpenForReadResult(uri, fileInputStream, getMimeTypeFromPath(substring), j, assetFileDescriptor);
            case 2:
            case 3:
                String type = this.contentResolver.getType(uri);
                AssetFileDescriptor openAssetFileDescriptor = this.contentResolver.openAssetFileDescriptor(uri, "r");
                return new OpenForReadResult(uri, openAssetFileDescriptor.createInputStream(), type, openAssetFileDescriptor.getLength(), openAssetFileDescriptor);
            case 4:
                OpenForReadResult readDataUri = readDataUri(uri);
                if (readDataUri != null) {
                    return readDataUri;
                }
                break;
            case 5:
            case 6:
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
                httpURLConnection.setDoInput(true);
                String headerField = httpURLConnection.getHeaderField(HttpRequest.HEADER_CONTENT_TYPE);
                return new OpenForReadResult(uri, httpURLConnection.getInputStream(), headerField != null ? headerField.split(";")[0] : headerField, (long) httpURLConnection.getContentLength(), (AssetFileDescriptor) null);
            case 7:
                CordovaPlugin plugin = this.pluginManager.getPlugin(uri.getHost());
                if (plugin != null) {
                    return plugin.handleOpenForRead(uri);
                }
                throw new FileNotFoundException("Invalid plugin ID in URI: " + uri);
        }
        throw new FileNotFoundException("URI not supported by CordovaResourceApi: " + uri);
    }

    public OutputStream openOutputStream(Uri uri) throws IOException {
        return openOutputStream(uri, false);
    }

    public OutputStream openOutputStream(Uri uri, boolean z) throws IOException {
        assertBackgroundThread();
        int uriType = getUriType(uri);
        if (uriType != 0) {
            switch (uriType) {
                case 2:
                case 3:
                    return this.contentResolver.openAssetFileDescriptor(uri, z ? "wa" : "w").createOutputStream();
                default:
                    throw new FileNotFoundException("URI not supported by CordovaResourceApi: " + uri);
            }
        } else {
            File file = new File(uri.getPath());
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            return new FileOutputStream(file, z);
        }
    }

    public HttpURLConnection createHttpConnection(Uri uri) throws IOException {
        assertBackgroundThread();
        return (HttpURLConnection) new URL(uri.toString()).openConnection();
    }

    public void copyResource(OpenForReadResult openForReadResult, OutputStream outputStream) throws IOException {
        assertBackgroundThread();
        try {
            InputStream inputStream = openForReadResult.inputStream;
            if (!(inputStream instanceof FileInputStream) || !(outputStream instanceof FileOutputStream)) {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr, 0, 8192);
                    if (read <= 0) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                }
            } else {
                FileChannel channel = ((FileInputStream) openForReadResult.inputStream).getChannel();
                FileChannel channel2 = ((FileOutputStream) outputStream).getChannel();
                long j = 0;
                long j2 = openForReadResult.length;
                if (openForReadResult.assetFd != null) {
                    j = openForReadResult.assetFd.getStartOffset();
                }
                channel.position(j);
                channel2.transferFrom(channel, 0, j2);
            }
        } finally {
            openForReadResult.inputStream.close();
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public void copyResource(Uri uri, OutputStream outputStream) throws IOException {
        copyResource(openForRead(uri), outputStream);
    }

    public void copyResource(Uri uri, Uri uri2) throws IOException {
        copyResource(openForRead(uri), openOutputStream(uri2));
    }

    private void assertBackgroundThread() {
        if (this.threadCheckingEnabled) {
            Thread currentThread = Thread.currentThread();
            if (currentThread == Looper.getMainLooper().getThread()) {
                throw new IllegalStateException("Do not perform IO operations on the UI thread. Use CordovaInterface.getThreadPool() instead.");
            } else if (currentThread == jsThread) {
                throw new IllegalStateException("Tried to perform an IO operation on the WebCore thread. Use CordovaInterface.getThreadPool() instead.");
            }
        }
    }

    private String getDataUriMimeType(Uri uri) {
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        int indexOf = schemeSpecificPart.indexOf(44);
        if (indexOf == -1) {
            return null;
        }
        String[] split = schemeSpecificPart.substring(0, indexOf).split(";");
        if (split.length > 0) {
            return split[0];
        }
        return null;
    }

    private OpenForReadResult readDataUri(Uri uri) {
        byte[] bArr;
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        int indexOf = schemeSpecificPart.indexOf(44);
        if (indexOf == -1) {
            return null;
        }
        String[] split = schemeSpecificPart.substring(0, indexOf).split(";");
        String str = split.length > 0 ? split[0] : null;
        boolean z = false;
        for (int i = 1; i < split.length; i++) {
            if ("base64".equalsIgnoreCase(split[i])) {
                z = true;
            }
        }
        String substring = schemeSpecificPart.substring(indexOf + 1);
        if (z) {
            bArr = Base64.decode(substring, 0);
        } else {
            try {
                bArr = substring.getBytes(HttpRequest.CHARSET_UTF8);
            } catch (UnsupportedEncodingException unused) {
                bArr = substring.getBytes();
            }
        }
        return new OpenForReadResult(uri, new ByteArrayInputStream(bArr), str, (long) bArr.length, (AssetFileDescriptor) null);
    }

    private static void assertNonRelative(Uri uri) {
        if (!uri.isAbsolute()) {
            throw new IllegalArgumentException("Relative URIs are not supported.");
        }
    }

    public static final class OpenForReadResult {
        public final AssetFileDescriptor assetFd;
        public final InputStream inputStream;
        public final long length;
        public final String mimeType;
        public final Uri uri;

        public OpenForReadResult(Uri uri2, InputStream inputStream2, String str, long j, AssetFileDescriptor assetFileDescriptor) {
            this.uri = uri2;
            this.inputStream = inputStream2;
            this.mimeType = str;
            this.length = j;
            this.assetFd = assetFileDescriptor;
        }
    }
}
