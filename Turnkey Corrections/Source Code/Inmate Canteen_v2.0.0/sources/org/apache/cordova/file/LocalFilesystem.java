package org.apache.cordova.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalFilesystem extends Filesystem {
    private final Context context;

    public LocalFilesystem(String str, Context context2, CordovaResourceApi cordovaResourceApi, File file) {
        super(Uri.fromFile(file).buildUpon().appendEncodedPath("").build(), str, cordovaResourceApi);
        this.context = context2;
    }

    public String filesystemPathForFullPath(String str) {
        return new File(this.rootUri.getPath(), str).toString();
    }

    public String filesystemPathForURL(LocalFilesystemURL localFilesystemURL) {
        return filesystemPathForFullPath(localFilesystemURL.path);
    }

    private String fullPathForFilesystemPath(String str) {
        if (str == null || !str.startsWith(this.rootUri.getPath())) {
            return null;
        }
        return str.substring(this.rootUri.getPath().length() - 1);
    }

    public Uri toNativeUri(LocalFilesystemURL localFilesystemURL) {
        return nativeUriForFullPath(localFilesystemURL.path);
    }

    public LocalFilesystemURL toLocalUri(Uri uri) {
        if (!"file".equals(uri.getScheme())) {
            return null;
        }
        File file = new File(uri.getPath());
        Uri fromFile = Uri.fromFile(file);
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
        if (file.isDirectory()) {
            path.appendEncodedPath("");
        }
        return LocalFilesystemURL.parse(path.build());
    }

    public LocalFilesystemURL URLforFilesystemPath(String str) {
        return localUrlforFullPath(fullPathForFilesystemPath(str));
    }

    public JSONObject getFileForLocalURL(LocalFilesystemURL localFilesystemURL, String str, JSONObject jSONObject, boolean z) throws FileExistsException, IOException, TypeMismatchException, EncodingException, JSONException {
        boolean z2;
        LocalFilesystemURL localFilesystemURL2;
        boolean z3 = false;
        if (jSONObject != null) {
            z2 = jSONObject.optBoolean("create");
            if (z2) {
                z3 = jSONObject.optBoolean("exclusive");
            }
        } else {
            z2 = false;
        }
        if (!str.contains(":")) {
            if (z && !str.endsWith("/")) {
                str = str + "/";
            }
            if (str.startsWith("/")) {
                localFilesystemURL2 = localUrlforFullPath(normalizePath(str));
            } else {
                localFilesystemURL2 = localUrlforFullPath(normalizePath(localFilesystemURL.path + "/" + str));
            }
            File file = new File(filesystemPathForURL(localFilesystemURL2));
            if (z2) {
                if (!z3 || !file.exists()) {
                    if (z) {
                        file.mkdir();
                    } else {
                        file.createNewFile();
                    }
                    if (!file.exists()) {
                        throw new FileExistsException("create fails");
                    }
                } else {
                    throw new FileExistsException("create/exclusive fails");
                }
            } else if (!file.exists()) {
                throw new FileNotFoundException("path does not exist");
            } else if (z) {
                if (file.isFile()) {
                    throw new TypeMismatchException("path doesn't exist or is file");
                }
            } else if (file.isDirectory()) {
                throw new TypeMismatchException("path doesn't exist or is directory");
            }
            return makeEntryForURL(localFilesystemURL2);
        }
        throw new EncodingException("This path has an invalid \":\" in it.");
    }

    public boolean removeFileAtLocalURL(LocalFilesystemURL localFilesystemURL) throws InvalidModificationException {
        File file = new File(filesystemPathForURL(localFilesystemURL));
        if (!file.isDirectory() || file.list().length <= 0) {
            return file.delete();
        }
        throw new InvalidModificationException("You can't delete a directory that is not empty.");
    }

    public boolean exists(LocalFilesystemURL localFilesystemURL) {
        return new File(filesystemPathForURL(localFilesystemURL)).exists();
    }

    public long getFreeSpaceInBytes() {
        return DirectoryManager.getFreeSpaceInBytes(this.rootUri.getPath());
    }

    public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL localFilesystemURL) throws FileExistsException {
        return removeDirRecursively(new File(filesystemPathForURL(localFilesystemURL)));
    }

    /* access modifiers changed from: protected */
    public boolean removeDirRecursively(File file) throws FileExistsException {
        if (file.isDirectory()) {
            for (File removeDirRecursively : file.listFiles()) {
                removeDirRecursively(removeDirRecursively);
            }
        }
        if (file.delete()) {
            return true;
        }
        throw new FileExistsException("could not delete: " + file.getName());
    }

    public LocalFilesystemURL[] listChildren(LocalFilesystemURL localFilesystemURL) throws FileNotFoundException {
        File file = new File(filesystemPathForURL(localFilesystemURL));
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return null;
            }
            LocalFilesystemURL[] localFilesystemURLArr = new LocalFilesystemURL[listFiles.length];
            for (int i = 0; i < listFiles.length; i++) {
                localFilesystemURLArr[i] = URLforFilesystemPath(listFiles[i].getPath());
            }
            return localFilesystemURLArr;
        }
        throw new FileNotFoundException();
    }

    public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL localFilesystemURL) throws FileNotFoundException {
        File file = new File(filesystemPathForURL(localFilesystemURL));
        if (file.exists()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("size", file.isDirectory() ? 0 : file.length());
                jSONObject.put("type", this.resourceApi.getMimeType(Uri.fromFile(file)));
                jSONObject.put("name", file.getName());
                jSONObject.put("fullPath", localFilesystemURL.path);
                jSONObject.put("lastModifiedDate", file.lastModified());
                return jSONObject;
            } catch (JSONException unused) {
                return null;
            }
        } else {
            throw new FileNotFoundException("File at " + localFilesystemURL.uri + " does not exist.");
        }
    }

    private void copyFile(Filesystem filesystem, LocalFilesystemURL localFilesystemURL, File file, boolean z) throws IOException, InvalidModificationException, NoModificationAllowedException {
        String filesystemPathForURL;
        if (!z || (filesystemPathForURL = filesystem.filesystemPathForURL(localFilesystemURL)) == null || !new File(filesystemPathForURL).renameTo(file)) {
            copyResource(this.resourceApi.openForRead(filesystem.toNativeUri(localFilesystemURL)), new FileOutputStream(file));
            if (z) {
                filesystem.removeFileAtLocalURL(localFilesystemURL);
            }
        }
    }

    private void copyDirectory(Filesystem filesystem, LocalFilesystemURL localFilesystemURL, File file, boolean z) throws IOException, NoModificationAllowedException, InvalidModificationException, FileExistsException {
        String filesystemPathForURL;
        if (z && (filesystemPathForURL = filesystem.filesystemPathForURL(localFilesystemURL)) != null) {
            File file2 = new File(filesystemPathForURL);
            if (file.exists()) {
                if (file.list().length <= 0) {
                    file.delete();
                } else {
                    throw new InvalidModificationException("directory is not empty");
                }
            }
            if (file2.renameTo(file)) {
                return;
            }
        }
        if (file.exists()) {
            if (file.list().length > 0) {
                throw new InvalidModificationException("directory is not empty");
            }
        } else if (!file.mkdir()) {
            throw new NoModificationAllowedException("Couldn't create the destination directory");
        }
        for (LocalFilesystemURL localFilesystemURL2 : filesystem.listChildren(localFilesystemURL)) {
            File file3 = new File(file, new File(localFilesystemURL2.path).getName());
            if (localFilesystemURL2.isDirectory) {
                copyDirectory(filesystem, localFilesystemURL2, file3, false);
            } else {
                copyFile(filesystem, localFilesystemURL2, file3, false);
            }
        }
        if (z) {
            filesystem.recursiveRemoveFileAtLocalURL(localFilesystemURL);
        }
    }

    public JSONObject copyFileToURL(LocalFilesystemURL localFilesystemURL, String str, Filesystem filesystem, LocalFilesystemURL localFilesystemURL2, boolean z) throws IOException, InvalidModificationException, JSONException, NoModificationAllowedException, FileExistsException {
        if (new File(filesystemPathForURL(localFilesystemURL)).exists()) {
            LocalFilesystemURL makeDestinationURL = makeDestinationURL(str, localFilesystemURL2, localFilesystemURL, localFilesystemURL2.isDirectory);
            Uri nativeUri = toNativeUri(makeDestinationURL);
            Uri nativeUri2 = filesystem.toNativeUri(localFilesystemURL2);
            if (nativeUri.equals(nativeUri2)) {
                throw new InvalidModificationException("Can't copy onto itself");
            } else if (!z || filesystem.canRemoveFileAtLocalURL(localFilesystemURL2)) {
                File file = new File(nativeUri.getPath());
                if (file.exists()) {
                    if (!localFilesystemURL2.isDirectory && file.isDirectory()) {
                        throw new InvalidModificationException("Can't copy/move a file to an existing directory");
                    } else if (localFilesystemURL2.isDirectory && file.isFile()) {
                        throw new InvalidModificationException("Can't copy/move a directory to an existing file");
                    }
                }
                if (localFilesystemURL2.isDirectory) {
                    String uri = nativeUri.toString();
                    if (!uri.startsWith(nativeUri2.toString() + '/')) {
                        copyDirectory(filesystem, localFilesystemURL2, file, z);
                    } else {
                        throw new InvalidModificationException("Can't copy directory into itself");
                    }
                } else {
                    copyFile(filesystem, localFilesystemURL2, file, z);
                }
                return makeEntryForURL(makeDestinationURL);
            } else {
                throw new InvalidModificationException("Source URL is read-only (cannot move)");
            }
        } else {
            throw new FileNotFoundException("The source does not exist");
        }
    }

    public long writeToFileAtURL(LocalFilesystemURL localFilesystemURL, String str, int i, boolean z) throws IOException, NoModificationAllowedException {
        boolean z2;
        byte[] bArr;
        FileOutputStream fileOutputStream;
        if (i > 0) {
            truncateFileAtURL(localFilesystemURL, (long) i);
            z2 = true;
        } else {
            z2 = false;
        }
        if (z) {
            bArr = Base64.decode(str, 0);
        } else {
            bArr = str.getBytes(Charset.defaultCharset());
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            byte[] bArr2 = new byte[bArr.length];
            String filesystemPathForURL = filesystemPathForURL(localFilesystemURL);
            fileOutputStream = new FileOutputStream(filesystemPathForURL, z2);
            byteArrayInputStream.read(bArr2, 0, bArr2.length);
            fileOutputStream.write(bArr2, 0, bArr.length);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (isPublicDirectory(filesystemPathForURL)) {
                broadcastNewFile(Uri.fromFile(new File(filesystemPathForURL)));
            }
            return (long) bArr.length;
        } catch (NullPointerException e) {
            NoModificationAllowedException noModificationAllowedException = new NoModificationAllowedException(localFilesystemURL.toString());
            noModificationAllowedException.initCause(e);
            throw noModificationAllowedException;
        } catch (Throwable th) {
            fileOutputStream.close();
            throw th;
        }
    }

    private boolean isPublicDirectory(String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            for (File file : this.context.getExternalMediaDirs()) {
                if (file != null && str.startsWith(file.getAbsolutePath())) {
                    return true;
                }
            }
        }
        return str.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private void broadcastNewFile(Uri uri) {
        this.context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", uri));
    }

    public long truncateFileAtURL(LocalFilesystemURL localFilesystemURL, long j) throws IOException {
        if (new File(filesystemPathForURL(localFilesystemURL)).exists()) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(filesystemPathForURL(localFilesystemURL), "rw");
            try {
                if (randomAccessFile.length() >= j) {
                    randomAccessFile.getChannel().truncate(j);
                    return j;
                }
                long length = randomAccessFile.length();
                randomAccessFile.close();
                return length;
            } finally {
                randomAccessFile.close();
            }
        } else {
            throw new FileNotFoundException("File at " + localFilesystemURL.uri + " does not exist.");
        }
    }

    public boolean canRemoveFileAtLocalURL(LocalFilesystemURL localFilesystemURL) {
        return new File(filesystemPathForURL(localFilesystemURL)).exists();
    }

    private static void copyResource(CordovaResourceApi.OpenForReadResult openForReadResult, OutputStream outputStream) throws IOException {
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
}
