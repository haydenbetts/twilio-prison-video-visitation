package org.apache.cordova.camera;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import androidx.core.content.FileProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.cordova.BuildHelper;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class CameraLauncher extends CordovaPlugin implements MediaScannerConnection.MediaScannerConnectionClient {
    private static final int ALLMEDIA = 2;
    private static final int CAMERA = 1;
    private static final int CROP_CAMERA = 100;
    private static final int DATA_URL = 0;
    private static final int FILE_URI = 1;
    private static final String GET_All = "Get All";
    private static final String GET_PICTURE = "Get Picture";
    private static final String GET_VIDEO = "Get Video";
    private static final int JPEG = 0;
    private static final String LOG_TAG = "CameraLauncher";
    private static final int NATIVE_URI = 2;
    public static final int PERMISSION_DENIED_ERROR = 20;
    private static final int PHOTOLIBRARY = 0;
    private static final int PICTURE = 0;
    private static final int PNG = 1;
    private static final int SAVEDPHOTOALBUM = 2;
    public static final int SAVE_TO_ALBUM_SEC = 1;
    public static final int TAKE_PIC_SEC = 0;
    private static final int VIDEO = 1;
    protected static final String[] permissions = {"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private boolean allowEdit;
    private String applicationId;
    public CallbackContext callbackContext;
    private MediaScannerConnection conn;
    private boolean correctOrientation;
    private Uri croppedUri;
    private int destType;
    private int encodingType;
    private ExifHelper exifData;
    private CordovaUri imageUri;
    private int mQuality;
    private int mediaType;
    private int numPics;
    private boolean orientationCorrected;
    private boolean saveToPhotoAlbum;
    private Uri scanMe;
    private int srcType;
    private int targetHeight;
    private int targetWidth;

    private int exifToDegrees(int i) {
        if (i == 6) {
            return 90;
        }
        if (i == 3) {
            return 180;
        }
        return i == 8 ? 270 : 0;
    }

    private String getMimetypeForFormat(int i) {
        return i == 1 ? "image/png" : i == 0 ? "image/jpeg" : "";
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext2) throws JSONException {
        this.callbackContext = callbackContext2;
        this.applicationId = (String) BuildHelper.getBuildConfigValue(this.cordova.getActivity(), "APPLICATION_ID");
        this.applicationId = this.preferences.getString("applicationId", this.applicationId);
        if (!str.equals("takePicture")) {
            return false;
        }
        this.srcType = 1;
        this.destType = 1;
        this.saveToPhotoAlbum = false;
        this.targetHeight = 0;
        this.targetWidth = 0;
        this.encodingType = 0;
        this.mediaType = 0;
        this.mQuality = 50;
        this.destType = jSONArray.getInt(1);
        this.srcType = jSONArray.getInt(2);
        this.mQuality = jSONArray.getInt(0);
        this.targetWidth = jSONArray.getInt(3);
        this.targetHeight = jSONArray.getInt(4);
        this.encodingType = jSONArray.getInt(5);
        this.mediaType = jSONArray.getInt(6);
        this.allowEdit = jSONArray.getBoolean(7);
        this.correctOrientation = jSONArray.getBoolean(8);
        this.saveToPhotoAlbum = jSONArray.getBoolean(9);
        if (this.targetWidth < 1) {
            this.targetWidth = -1;
        }
        if (this.targetHeight < 1) {
            this.targetHeight = -1;
        }
        if (this.targetHeight == -1 && this.targetWidth == -1 && this.mQuality == 100 && !this.correctOrientation && this.encodingType == 1 && this.srcType == 1) {
            this.encodingType = 0;
        }
        try {
            if (this.srcType == 1) {
                callTakePicture(this.destType, this.encodingType);
            } else if (this.srcType == 0 || this.srcType == 2) {
                if (!PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE")) {
                    PermissionHelper.requestPermission(this, 1, "android.permission.READ_EXTERNAL_STORAGE");
                } else {
                    getImage(this.srcType, this.destType, this.encodingType);
                }
            }
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            callbackContext2.sendPluginResult(pluginResult);
            return true;
        } catch (IllegalArgumentException unused) {
            callbackContext2.error("Illegal Argument Exception");
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
            return true;
        }
    }

    private String getTempDirectoryPath() {
        File file;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = this.cordova.getActivity().getExternalCacheDir();
        } else {
            file = this.cordova.getActivity().getCacheDir();
        }
        file.mkdirs();
        return file.getAbsolutePath();
    }

    public void callTakePicture(int i, int i2) {
        boolean z = true;
        boolean z2 = PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE") && PermissionHelper.hasPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        boolean hasPermission = PermissionHelper.hasPermission(this, "android.permission.CAMERA");
        if (!hasPermission) {
            try {
                String[] strArr = this.cordova.getActivity().getPackageManager().getPackageInfo(this.cordova.getActivity().getPackageName(), 4096).requestedPermissions;
                if (strArr != null) {
                    int length = strArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            break;
                        } else if (strArr[i3].equals("android.permission.CAMERA")) {
                            z = false;
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } else {
            z = hasPermission;
        }
        if (z && z2) {
            takePicture(i, i2);
        } else if (z2 && !z) {
            PermissionHelper.requestPermission(this, 0, "android.permission.CAMERA");
        } else if (z2 || !z) {
            PermissionHelper.requestPermissions(this, 0, permissions);
        } else {
            PermissionHelper.requestPermissions(this, 0, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"});
        }
    }

    public void takePicture(int i, int i2) {
        this.numPics = queryImgDB(whichContentStore()).getCount();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File createCaptureFile = createCaptureFile(i2);
        Activity activity = this.cordova.getActivity();
        this.imageUri = new CordovaUri(FileProvider.getUriForFile(activity, this.applicationId + ".provider", createCaptureFile));
        intent.putExtra("output", this.imageUri.getCorrectUri());
        intent.addFlags(2);
        if (this.cordova == null) {
            return;
        }
        if (intent.resolveActivity(this.cordova.getActivity().getPackageManager()) != null) {
            this.cordova.startActivityForResult(this, intent, i + 32 + 1);
        } else {
            LOG.d(LOG_TAG, "Error: You don't have a default camera.  Your device may not be CTS complaint.");
        }
    }

    private File createCaptureFile(int i) {
        return createCaptureFile(i, "");
    }

    private File createCaptureFile(int i, String str) {
        String str2;
        if (str.isEmpty()) {
            str = ".Pic";
        }
        if (i == 0) {
            str2 = str + ".jpg";
        } else if (i == 1) {
            str2 = str + ".png";
        } else {
            throw new IllegalArgumentException("Invalid Encoding Type: " + i);
        }
        return new File(getTempDirectoryPath(), str2);
    }

    public void getImage(int i, int i2, int i3) {
        int i4;
        Intent intent = new Intent();
        String str = GET_PICTURE;
        this.croppedUri = null;
        int i5 = this.mediaType;
        if (i5 == 0) {
            intent.setType("image/*");
            if (this.allowEdit) {
                intent.setAction("android.intent.action.PICK");
                intent.putExtra("crop", "true");
                int i6 = this.targetWidth;
                if (i6 > 0) {
                    intent.putExtra("outputX", i6);
                }
                int i7 = this.targetHeight;
                if (i7 > 0) {
                    intent.putExtra("outputY", i7);
                }
                int i8 = this.targetHeight;
                if (i8 > 0 && (i4 = this.targetWidth) > 0 && i4 == i8) {
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                }
                this.croppedUri = Uri.fromFile(createCaptureFile(0));
                intent.putExtra("output", this.croppedUri);
            } else {
                intent.setAction("android.intent.action.GET_CONTENT");
                intent.addCategory("android.intent.category.OPENABLE");
            }
        } else if (i5 == 1) {
            intent.setType("video/*");
            str = GET_VIDEO;
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        } else if (i5 == 2) {
            intent.setType("*/*");
            str = GET_All;
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        }
        if (this.cordova != null) {
            this.cordova.startActivityForResult(this, Intent.createChooser(intent, new String(str)), ((i + 1) * 16) + i2 + 1);
        }
    }

    private void performCrop(Uri uri, int i, Intent intent) {
        try {
            Intent intent2 = new Intent("com.android.camera.action.CROP");
            intent2.setDataAndType(uri, "image/*");
            intent2.putExtra("crop", "true");
            if (this.targetWidth > 0) {
                intent2.putExtra("outputX", this.targetWidth);
            }
            if (this.targetHeight > 0) {
                intent2.putExtra("outputY", this.targetHeight);
            }
            if (this.targetHeight > 0 && this.targetWidth > 0 && this.targetWidth == this.targetHeight) {
                intent2.putExtra("aspectX", 1);
                intent2.putExtra("aspectY", 1);
            }
            int i2 = this.encodingType;
            this.croppedUri = Uri.fromFile(createCaptureFile(i2, System.currentTimeMillis() + ""));
            intent2.addFlags(1);
            intent2.addFlags(2);
            intent2.putExtra("output", this.croppedUri);
            if (this.cordova != null) {
                this.cordova.startActivityForResult(this, intent2, i + 100);
            }
        } catch (ActivityNotFoundException unused) {
            LOG.e(LOG_TAG, "Crop operation not supported on this device");
            try {
                processResultFromCamera(i, intent);
            } catch (IOException e) {
                e.printStackTrace();
                LOG.e(LOG_TAG, "Unable to write to file");
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processResultFromCamera(int r10, android.content.Intent r11) throws java.io.IOException {
        /*
            r9 = this;
            org.apache.cordova.camera.ExifHelper r0 = new org.apache.cordova.camera.ExifHelper
            r0.<init>()
            boolean r1 = r9.allowEdit
            if (r1 == 0) goto L_0x0016
            android.net.Uri r1 = r9.croppedUri
            if (r1 == 0) goto L_0x0016
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = org.apache.cordova.camera.FileHelper.stripFileProtocol(r1)
            goto L_0x001c
        L_0x0016:
            org.apache.cordova.camera.CordovaUri r1 = r9.imageUri
            java.lang.String r1 = r1.getFilePath()
        L_0x001c:
            int r2 = r9.encodingType
            r3 = 0
            if (r2 != 0) goto L_0x0030
            r0.createInFile(r1)     // Catch:{ IOException -> 0x002c }
            r0.readExifData()     // Catch:{ IOException -> 0x002c }
            int r2 = r0.getOrientation()     // Catch:{ IOException -> 0x002c }
            goto L_0x0031
        L_0x002c:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0030:
            r2 = 0
        L_0x0031:
            boolean r4 = r9.saveToPhotoAlbum
            r5 = 0
            if (r4 == 0) goto L_0x005c
            java.io.File r4 = new java.io.File
            java.lang.String r6 = r9.getPicturesPath()
            r4.<init>(r6)
            android.net.Uri r4 = android.net.Uri.fromFile(r4)
            boolean r6 = r9.allowEdit
            if (r6 == 0) goto L_0x004f
            android.net.Uri r6 = r9.croppedUri
            if (r6 == 0) goto L_0x004f
            r9.writeUncompressedImage((android.net.Uri) r6, (android.net.Uri) r4)
            goto L_0x0058
        L_0x004f:
            org.apache.cordova.camera.CordovaUri r6 = r9.imageUri
            android.net.Uri r6 = r6.getFileUri()
            r9.writeUncompressedImage((android.net.Uri) r6, (android.net.Uri) r4)
        L_0x0058:
            r9.refreshGallery(r4)
            goto L_0x005d
        L_0x005c:
            r4 = r5
        L_0x005d:
            r6 = 1
            if (r10 != 0) goto L_0x0092
            android.graphics.Bitmap r10 = r9.getScaledAndRotatedBitmap(r1)
            if (r10 != 0) goto L_0x0074
            android.os.Bundle r10 = r11.getExtras()
            java.lang.String r11 = "data"
            java.lang.Object r10 = r10.get(r11)
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            r5 = r10
            goto L_0x0075
        L_0x0074:
            r5 = r10
        L_0x0075:
            if (r5 != 0) goto L_0x0084
            java.lang.String r10 = "CameraLauncher"
            java.lang.String r11 = "I either have a null image path or bitmap"
            org.apache.cordova.LOG.d(r10, r11)
            java.lang.String r10 = "Unable to create bitmap!"
            r9.failPicture(r10)
            return
        L_0x0084:
            int r10 = r9.encodingType
            r9.processPicture(r5, r10)
            boolean r10 = r9.saveToPhotoAlbum
            if (r10 != 0) goto L_0x0178
            r9.checkForDuplicateImage(r3)
            goto L_0x0178
        L_0x0092:
            if (r10 == r6) goto L_0x009e
            r11 = 2
            if (r10 != r11) goto L_0x0098
            goto L_0x009e
        L_0x0098:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>()
            throw r10
        L_0x009e:
            int r10 = r9.targetHeight
            r11 = -1
            if (r10 != r11) goto L_0x010b
            int r10 = r9.targetWidth
            if (r10 != r11) goto L_0x010b
            int r10 = r9.mQuality
            r11 = 100
            if (r10 != r11) goto L_0x010b
            boolean r10 = r9.correctOrientation
            if (r10 != 0) goto L_0x010b
            boolean r10 = r9.saveToPhotoAlbum
            if (r10 == 0) goto L_0x00c0
            org.apache.cordova.CallbackContext r10 = r9.callbackContext
            java.lang.String r11 = r4.toString()
            r10.success((java.lang.String) r11)
            goto L_0x0178
        L_0x00c0:
            int r10 = r9.encodingType
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            long r0 = java.lang.System.currentTimeMillis()
            r11.append(r0)
            java.lang.String r0 = ""
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            java.io.File r10 = r9.createCaptureFile(r10, r11)
            android.net.Uri r10 = android.net.Uri.fromFile(r10)
            boolean r11 = r9.allowEdit
            if (r11 == 0) goto L_0x00f8
            android.net.Uri r11 = r9.croppedUri
            if (r11 == 0) goto L_0x00f8
            java.io.File r0 = new java.io.File
            java.lang.String r11 = r9.getFileNameFromUri(r11)
            r0.<init>(r11)
            android.net.Uri r11 = android.net.Uri.fromFile(r0)
            r9.writeUncompressedImage((android.net.Uri) r11, (android.net.Uri) r10)
            goto L_0x0101
        L_0x00f8:
            org.apache.cordova.camera.CordovaUri r11 = r9.imageUri
            android.net.Uri r11 = r11.getFileUri()
            r9.writeUncompressedImage((android.net.Uri) r11, (android.net.Uri) r10)
        L_0x0101:
            org.apache.cordova.CallbackContext r11 = r9.callbackContext
            java.lang.String r10 = r10.toString()
            r11.success((java.lang.String) r10)
            goto L_0x0178
        L_0x010b:
            int r10 = r9.encodingType
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            long r7 = java.lang.System.currentTimeMillis()
            r11.append(r7)
            java.lang.String r3 = ""
            r11.append(r3)
            java.lang.String r11 = r11.toString()
            java.io.File r10 = r9.createCaptureFile(r10, r11)
            android.net.Uri r10 = android.net.Uri.fromFile(r10)
            android.graphics.Bitmap r5 = r9.getScaledAndRotatedBitmap(r1)
            if (r5 != 0) goto L_0x013d
            java.lang.String r10 = "CameraLauncher"
            java.lang.String r11 = "I either have a null image path or bitmap"
            org.apache.cordova.LOG.d(r10, r11)
            java.lang.String r10 = "Unable to create bitmap!"
            r9.failPicture(r10)
            return
        L_0x013d:
            org.apache.cordova.CordovaInterface r11 = r9.cordova
            android.app.Activity r11 = r11.getActivity()
            android.content.ContentResolver r11 = r11.getContentResolver()
            java.io.OutputStream r11 = r11.openOutputStream(r10)
            int r1 = r9.encodingType
            if (r1 != 0) goto L_0x0152
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            goto L_0x0154
        L_0x0152:
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG
        L_0x0154:
            int r3 = r9.mQuality
            r5.compress(r1, r3, r11)
            r11.close()
            int r11 = r9.encodingType
            if (r11 != 0) goto L_0x016f
            java.lang.String r11 = r10.getPath()
            if (r2 == r6) goto L_0x0169
            r0.resetOrientation()
        L_0x0169:
            r0.createOutFile(r11)
            r0.writeExifData()
        L_0x016f:
            org.apache.cordova.CallbackContext r11 = r9.callbackContext
            java.lang.String r10 = r10.toString()
            r11.success((java.lang.String) r10)
        L_0x0178:
            org.apache.cordova.camera.CordovaUri r10 = r9.imageUri
            android.net.Uri r10 = r10.getFileUri()
            r9.cleanup(r6, r10, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.camera.CameraLauncher.processResultFromCamera(int, android.content.Intent):void");
    }

    private String getPicturesPath() {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("IMG_");
        sb.append(format);
        sb.append(this.encodingType == 0 ? ".jpg" : ".png");
        String sb2 = sb.toString();
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        externalStoragePublicDirectory.mkdirs();
        return externalStoragePublicDirectory.getAbsolutePath() + "/" + sb2;
    }

    private void refreshGallery(Uri uri) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(uri);
        this.cordova.getActivity().sendBroadcast(intent);
    }

    private String outputModifiedBitmap(Bitmap bitmap, Uri uri) throws IOException {
        String str;
        String realPath = FileHelper.getRealPath(uri, this.cordova);
        if (realPath != null) {
            str = realPath.substring(realPath.lastIndexOf(47) + 1);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("modified.");
            sb.append(this.encodingType == 0 ? "jpg" : "png");
            str = sb.toString();
        }
        new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String str2 = getTempDirectoryPath() + "/" + str;
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        bitmap.compress(this.encodingType == 0 ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, this.mQuality, fileOutputStream);
        fileOutputStream.close();
        ExifHelper exifHelper = this.exifData;
        if (exifHelper != null && this.encodingType == 0) {
            try {
                if (this.correctOrientation && this.orientationCorrected) {
                    exifHelper.resetOrientation();
                }
                this.exifData.createOutFile(str2);
                this.exifData.writeExifData();
                this.exifData = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    /* access modifiers changed from: private */
    public void processResultFromGallery(int i, Intent intent) {
        Uri data = intent.getData();
        if (data == null && (data = this.croppedUri) == null) {
            failPicture("null data from photo library");
            return;
        }
        String realPath = FileHelper.getRealPath(data, this.cordova);
        LOG.d(LOG_TAG, "File locaton is: " + realPath);
        if (this.mediaType != 0) {
            this.callbackContext.success(realPath);
            return;
        }
        String uri = data.toString();
        String mimeType = FileHelper.getMimeType(uri, this.cordova);
        if (this.targetHeight == -1 && this.targetWidth == -1 && ((i == 1 || i == 2) && !this.correctOrientation && mimeType.equalsIgnoreCase(getMimetypeForFormat(this.encodingType)))) {
            this.callbackContext.success(uri);
        } else if ("image/jpeg".equalsIgnoreCase(mimeType) || "image/png".equalsIgnoreCase(mimeType)) {
            Bitmap bitmap = null;
            try {
                bitmap = getScaledAndRotatedBitmap(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap == null) {
                LOG.d(LOG_TAG, "I either have a null image path or bitmap");
                failPicture("Unable to create bitmap!");
                return;
            }
            if (i == 0) {
                processPicture(bitmap, this.encodingType);
            } else if (i == 1 || i == 2) {
                if ((this.targetHeight <= 0 || this.targetWidth <= 0) && ((!this.correctOrientation || !this.orientationCorrected) && mimeType.equalsIgnoreCase(getMimetypeForFormat(this.encodingType)))) {
                    this.callbackContext.success(realPath);
                } else {
                    try {
                        String outputModifiedBitmap = outputModifiedBitmap(bitmap, data);
                        CallbackContext callbackContext2 = this.callbackContext;
                        callbackContext2.success("file://" + outputModifiedBitmap + "?" + System.currentTimeMillis());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        failPicture("Error retrieving image.");
                    }
                }
            }
            if (bitmap != null) {
                bitmap.recycle();
            }
            System.gc();
        } else {
            LOG.d(LOG_TAG, "I either have a null image path or bitmap");
            failPicture("Unable to retrieve path to picture!");
        }
    }

    public void onActivityResult(int i, int i2, final Intent intent) {
        int i3 = (i / 16) - 1;
        final int i4 = (i % 16) - 1;
        if (i >= 100) {
            if (i2 == -1) {
                try {
                    processResultFromCamera(i - 100, intent);
                } catch (IOException e) {
                    e.printStackTrace();
                    LOG.e(LOG_TAG, "Unable to write to file");
                }
            } else if (i2 == 0) {
                failPicture("No Image Selected");
            } else {
                failPicture("Did not complete!");
            }
        } else if (i3 == 1) {
            if (i2 == -1) {
                try {
                    if (this.allowEdit) {
                        Activity activity = this.cordova.getActivity();
                        performCrop(FileProvider.getUriForFile(activity, this.applicationId + ".provider", createCaptureFile(this.encodingType)), i4, intent);
                        return;
                    }
                    processResultFromCamera(i4, intent);
                } catch (IOException e2) {
                    e2.printStackTrace();
                    failPicture("Error capturing image.");
                }
            } else if (i2 == 0) {
                failPicture("No Image Selected");
            } else {
                failPicture("Did not complete!");
            }
        } else if (i3 != 0 && i3 != 2) {
        } else {
            if (i2 == -1 && intent != null) {
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        CameraLauncher.this.processResultFromGallery(i4, intent);
                    }
                });
            } else if (i2 == 0) {
                failPicture("No Image Selected");
            } else {
                failPicture("Selection did not complete!");
            }
        }
    }

    private void writeUncompressedImage(InputStream inputStream, Uri uri) throws FileNotFoundException, IOException {
        String str;
        String str2;
        OutputStream outputStream = null;
        try {
            outputStream = this.cordova.getActivity().getContentResolver().openOutputStream(uri);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException unused) {
                    str2 = "Exception while closing output stream.";
                    LOG.d(LOG_TAG, str2);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                    str = "Exception while closing file input stream.";
                    LOG.d(LOG_TAG, str);
                }
            }
        }
    }

    private void writeUncompressedImage(Uri uri, Uri uri2) throws FileNotFoundException, IOException {
        writeUncompressedImage((InputStream) new FileInputStream(FileHelper.stripFileProtocol(uri.toString())), uri2);
    }

    private Uri getUriFromMediaStore() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mime_type", "image/jpeg");
        try {
            return this.cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } catch (RuntimeException unused) {
            LOG.d(LOG_TAG, "Can't write to external media storage.");
            try {
                return this.cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, contentValues);
            } catch (RuntimeException unused2) {
                LOG.d(LOG_TAG, "Can't write to internal media storage.");
                return null;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v27, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v31, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v36, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v39, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r14v25, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r14v32 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r14v37 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r14 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0045, code lost:
        if (r14 != null) goto L_0x0019;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:119:0x01c9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x01ec A[SYNTHETIC, Splitter:B:140:0x01ec] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0039 A[Catch:{ OutOfMemoryError -> 0x003a, Exception -> 0x002c, all -> 0x0029, all -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004c A[SYNTHETIC, Splitter:B:32:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x017d A[SYNTHETIC, Splitter:B:92:0x017d] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x018a  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap getScaledAndRotatedBitmap(java.lang.String r14) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r13.targetWidth
            r1 = 0
            if (r0 > 0) goto L_0x0058
            int r0 = r13.targetHeight
            if (r0 > 0) goto L_0x0058
            boolean r0 = r13.correctOrientation
            if (r0 != 0) goto L_0x0058
            org.apache.cordova.CordovaInterface r0 = r13.cordova     // Catch:{ OutOfMemoryError -> 0x003a, Exception -> 0x002c, all -> 0x0029 }
            java.io.InputStream r14 = org.apache.cordova.camera.FileHelper.getInputStreamFromUriString(r14, r0)     // Catch:{ OutOfMemoryError -> 0x003a, Exception -> 0x002c, all -> 0x0029 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r14)     // Catch:{ OutOfMemoryError -> 0x0027, Exception -> 0x0025 }
            if (r14 == 0) goto L_0x0048
        L_0x0019:
            r14.close()     // Catch:{ IOException -> 0x001d }
            goto L_0x0048
        L_0x001d:
            java.lang.String r14 = "CameraLauncher"
            java.lang.String r0 = "Exception while closing file input stream."
            org.apache.cordova.LOG.d(r14, r0)
            goto L_0x0048
        L_0x0025:
            r0 = move-exception
            goto L_0x002e
        L_0x0027:
            r0 = move-exception
            goto L_0x003c
        L_0x0029:
            r0 = move-exception
            r14 = r1
            goto L_0x004a
        L_0x002c:
            r0 = move-exception
            r14 = r1
        L_0x002e:
            org.apache.cordova.CallbackContext r2 = r13.callbackContext     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0049 }
            r2.error((java.lang.String) r0)     // Catch:{ all -> 0x0049 }
            if (r14 == 0) goto L_0x0048
            goto L_0x0019
        L_0x003a:
            r0 = move-exception
            r14 = r1
        L_0x003c:
            org.apache.cordova.CallbackContext r2 = r13.callbackContext     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x0049 }
            r2.error((java.lang.String) r0)     // Catch:{ all -> 0x0049 }
            if (r14 == 0) goto L_0x0048
            goto L_0x0019
        L_0x0048:
            return r1
        L_0x0049:
            r0 = move-exception
        L_0x004a:
            if (r14 == 0) goto L_0x0057
            r14.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0057
        L_0x0050:
            java.lang.String r14 = "CameraLauncher"
            java.lang.String r1 = "Exception while closing file input stream."
            org.apache.cordova.LOG.d(r14, r1)
        L_0x0057:
            throw r0
        L_0x0058:
            org.apache.cordova.CordovaInterface r0 = r13.cordova     // Catch:{ Exception -> 0x01ff }
            java.io.InputStream r0 = org.apache.cordova.camera.FileHelper.getInputStreamFromUriString(r14, r0)     // Catch:{ Exception -> 0x01ff }
            r2 = 0
            if (r0 == 0) goto L_0x010a
            java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x01ff }
            java.lang.String r4 = "yyyyMMdd_HHmmss"
            r3.<init>(r4)     // Catch:{ Exception -> 0x01ff }
            java.util.Date r4 = new java.util.Date     // Catch:{ Exception -> 0x01ff }
            r4.<init>()     // Catch:{ Exception -> 0x01ff }
            java.lang.String r3 = r3.format(r4)     // Catch:{ Exception -> 0x01ff }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ff }
            r4.<init>()     // Catch:{ Exception -> 0x01ff }
            java.lang.String r5 = "IMG_"
            r4.append(r5)     // Catch:{ Exception -> 0x01ff }
            r4.append(r3)     // Catch:{ Exception -> 0x01ff }
            int r3 = r13.encodingType     // Catch:{ Exception -> 0x01ff }
            if (r3 != 0) goto L_0x0085
            java.lang.String r3 = ".jpg"
            goto L_0x0087
        L_0x0085:
            java.lang.String r3 = ".png"
        L_0x0087:
            r4.append(r3)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x01ff }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x01ff }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ff }
            r5.<init>()     // Catch:{ Exception -> 0x01ff }
            java.lang.String r6 = r13.getTempDirectoryPath()     // Catch:{ Exception -> 0x01ff }
            r5.append(r6)     // Catch:{ Exception -> 0x01ff }
            r5.append(r3)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r3 = r5.toString()     // Catch:{ Exception -> 0x01ff }
            r4.<init>(r3)     // Catch:{ Exception -> 0x01ff }
            android.net.Uri r3 = android.net.Uri.fromFile(r4)     // Catch:{ Exception -> 0x01ff }
            r13.writeUncompressedImage((java.io.InputStream) r0, (android.net.Uri) r3)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x00ed }
            org.apache.cordova.CordovaInterface r0 = r13.cordova     // Catch:{ Exception -> 0x00ed }
            java.lang.String r14 = org.apache.cordova.camera.FileHelper.getMimeType(r14, r0)     // Catch:{ Exception -> 0x00ed }
            java.lang.String r0 = "image/jpeg"
            boolean r14 = r0.equalsIgnoreCase(r14)     // Catch:{ Exception -> 0x00ed }
            if (r14 == 0) goto L_0x00eb
            java.lang.String r14 = r3.toString()     // Catch:{ Exception -> 0x00ed }
            java.lang.String r0 = "file://"
            java.lang.String r5 = ""
            java.lang.String r14 = r14.replace(r0, r5)     // Catch:{ Exception -> 0x00ed }
            org.apache.cordova.camera.ExifHelper r0 = new org.apache.cordova.camera.ExifHelper     // Catch:{ Exception -> 0x00ed }
            r0.<init>()     // Catch:{ Exception -> 0x00ed }
            r13.exifData = r0     // Catch:{ Exception -> 0x00ed }
            org.apache.cordova.camera.ExifHelper r0 = r13.exifData     // Catch:{ Exception -> 0x00ed }
            r0.createInFile(r14)     // Catch:{ Exception -> 0x00ed }
            boolean r0 = r13.correctOrientation     // Catch:{ Exception -> 0x00ed }
            if (r0 == 0) goto L_0x00eb
            android.media.ExifInterface r0 = new android.media.ExifInterface     // Catch:{ Exception -> 0x00ed }
            r0.<init>(r14)     // Catch:{ Exception -> 0x00ed }
            java.lang.String r14 = "Orientation"
            int r14 = r0.getAttributeInt(r14, r2)     // Catch:{ Exception -> 0x00ed }
            int r14 = r13.exifToDegrees(r14)     // Catch:{ Exception -> 0x00ed }
            goto L_0x010d
        L_0x00eb:
            r14 = 0
            goto L_0x010d
        L_0x00ed:
            r14 = move-exception
            java.lang.String r0 = "CameraLauncher"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ff }
            r5.<init>()     // Catch:{ Exception -> 0x01ff }
            java.lang.String r6 = "Unable to read Exif data: "
            r5.append(r6)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x01ff }
            r5.append(r14)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r14 = r5.toString()     // Catch:{ Exception -> 0x01ff }
            org.apache.cordova.LOG.w((java.lang.String) r0, (java.lang.String) r14)     // Catch:{ Exception -> 0x01ff }
            r14 = 0
            goto L_0x010d
        L_0x010a:
            r3 = r1
            r4 = r3
            r14 = 0
        L_0x010d:
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options     // Catch:{ all -> 0x01f8 }
            r0.<init>()     // Catch:{ all -> 0x01f8 }
            r5 = 1
            r0.inJustDecodeBounds = r5     // Catch:{ all -> 0x01f8 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x01e9 }
            org.apache.cordova.CordovaInterface r7 = r13.cordova     // Catch:{ all -> 0x01e9 }
            java.io.InputStream r6 = org.apache.cordova.camera.FileHelper.getInputStreamFromUriString(r6, r7)     // Catch:{ all -> 0x01e9 }
            android.graphics.BitmapFactory.decodeStream(r6, r1, r0)     // Catch:{ all -> 0x01e6 }
            if (r6 == 0) goto L_0x012f
            r6.close()     // Catch:{ IOException -> 0x0128 }
            goto L_0x012f
        L_0x0128:
            java.lang.String r7 = "CameraLauncher"
            java.lang.String r8 = "Exception while closing file input stream."
            org.apache.cordova.LOG.d(r7, r8)     // Catch:{ all -> 0x01f8 }
        L_0x012f:
            int r7 = r0.outWidth     // Catch:{ all -> 0x01f8 }
            if (r7 == 0) goto L_0x01e0
            int r7 = r0.outHeight     // Catch:{ all -> 0x01f8 }
            if (r7 != 0) goto L_0x0139
            goto L_0x01e0
        L_0x0139:
            int r7 = r13.targetWidth     // Catch:{ all -> 0x01f8 }
            if (r7 > 0) goto L_0x0149
            int r7 = r13.targetHeight     // Catch:{ all -> 0x01f8 }
            if (r7 > 0) goto L_0x0149
            int r7 = r0.outWidth     // Catch:{ all -> 0x01f8 }
            r13.targetWidth = r7     // Catch:{ all -> 0x01f8 }
            int r7 = r0.outHeight     // Catch:{ all -> 0x01f8 }
            r13.targetHeight = r7     // Catch:{ all -> 0x01f8 }
        L_0x0149:
            r7 = 90
            if (r14 == r7) goto L_0x0158
            r7 = 270(0x10e, float:3.78E-43)
            if (r14 != r7) goto L_0x0152
            goto L_0x0158
        L_0x0152:
            int r7 = r0.outWidth     // Catch:{ all -> 0x01f8 }
            int r8 = r0.outHeight     // Catch:{ all -> 0x01f8 }
            r9 = 0
            goto L_0x015d
        L_0x0158:
            int r7 = r0.outHeight     // Catch:{ all -> 0x01f8 }
            int r8 = r0.outWidth     // Catch:{ all -> 0x01f8 }
            r9 = 1
        L_0x015d:
            int[] r10 = r13.calculateAspectRatio(r7, r8)     // Catch:{ all -> 0x01f8 }
            r0.inJustDecodeBounds = r2     // Catch:{ all -> 0x01f8 }
            r11 = r10[r2]     // Catch:{ all -> 0x01f8 }
            r12 = r10[r5]     // Catch:{ all -> 0x01f8 }
            int r7 = calculateSampleSize(r7, r8, r11, r12)     // Catch:{ all -> 0x01f8 }
            r0.inSampleSize = r7     // Catch:{ all -> 0x01f8 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01d1 }
            org.apache.cordova.CordovaInterface r7 = r13.cordova     // Catch:{ all -> 0x01d1 }
            java.io.InputStream r6 = org.apache.cordova.camera.FileHelper.getInputStreamFromUriString(r3, r7)     // Catch:{ all -> 0x01d1 }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r6, r1, r0)     // Catch:{ all -> 0x01d1 }
            if (r6 == 0) goto L_0x0188
            r6.close()     // Catch:{ IOException -> 0x0181 }
            goto L_0x0188
        L_0x0181:
            java.lang.String r3 = "CameraLauncher"
            java.lang.String r6 = "Exception while closing file input stream."
            org.apache.cordova.LOG.d(r3, r6)     // Catch:{ all -> 0x01f8 }
        L_0x0188:
            if (r0 != 0) goto L_0x0190
            if (r4 == 0) goto L_0x018f
            r4.delete()
        L_0x018f:
            return r1
        L_0x0190:
            if (r9 != 0) goto L_0x0195
            r1 = r10[r2]     // Catch:{ all -> 0x01f8 }
            goto L_0x0197
        L_0x0195:
            r1 = r10[r5]     // Catch:{ all -> 0x01f8 }
        L_0x0197:
            if (r9 != 0) goto L_0x019c
            r3 = r10[r5]     // Catch:{ all -> 0x01f8 }
            goto L_0x019e
        L_0x019c:
            r3 = r10[r2]     // Catch:{ all -> 0x01f8 }
        L_0x019e:
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createScaledBitmap(r0, r1, r3, r5)     // Catch:{ all -> 0x01f8 }
            if (r1 == r0) goto L_0x01a7
            r0.recycle()     // Catch:{ all -> 0x01f8 }
        L_0x01a7:
            boolean r0 = r13.correctOrientation     // Catch:{ all -> 0x01f8 }
            if (r0 == 0) goto L_0x01cb
            if (r14 == 0) goto L_0x01cb
            android.graphics.Matrix r11 = new android.graphics.Matrix     // Catch:{ all -> 0x01f8 }
            r11.<init>()     // Catch:{ all -> 0x01f8 }
            float r14 = (float) r14     // Catch:{ all -> 0x01f8 }
            r11.setRotate(r14)     // Catch:{ all -> 0x01f8 }
            r7 = 0
            r8 = 0
            int r9 = r1.getWidth()     // Catch:{ OutOfMemoryError -> 0x01c9 }
            int r10 = r1.getHeight()     // Catch:{ OutOfMemoryError -> 0x01c9 }
            r12 = 1
            r6 = r1
            android.graphics.Bitmap r1 = android.graphics.Bitmap.createBitmap(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ OutOfMemoryError -> 0x01c9 }
            r13.orientationCorrected = r5     // Catch:{ OutOfMemoryError -> 0x01c9 }
            goto L_0x01cb
        L_0x01c9:
            r13.orientationCorrected = r2     // Catch:{ all -> 0x01f8 }
        L_0x01cb:
            if (r4 == 0) goto L_0x01d0
            r4.delete()
        L_0x01d0:
            return r1
        L_0x01d1:
            r14 = move-exception
            if (r6 == 0) goto L_0x01df
            r6.close()     // Catch:{ IOException -> 0x01d8 }
            goto L_0x01df
        L_0x01d8:
            java.lang.String r0 = "CameraLauncher"
            java.lang.String r1 = "Exception while closing file input stream."
            org.apache.cordova.LOG.d(r0, r1)     // Catch:{ all -> 0x01f8 }
        L_0x01df:
            throw r14     // Catch:{ all -> 0x01f8 }
        L_0x01e0:
            if (r4 == 0) goto L_0x01e5
            r4.delete()
        L_0x01e5:
            return r1
        L_0x01e6:
            r14 = move-exception
            r1 = r6
            goto L_0x01ea
        L_0x01e9:
            r14 = move-exception
        L_0x01ea:
            if (r1 == 0) goto L_0x01f7
            r1.close()     // Catch:{ IOException -> 0x01f0 }
            goto L_0x01f7
        L_0x01f0:
            java.lang.String r0 = "CameraLauncher"
            java.lang.String r1 = "Exception while closing file input stream."
            org.apache.cordova.LOG.d(r0, r1)     // Catch:{ all -> 0x01f8 }
        L_0x01f7:
            throw r14     // Catch:{ all -> 0x01f8 }
        L_0x01f8:
            r14 = move-exception
            if (r4 == 0) goto L_0x01fe
            r4.delete()
        L_0x01fe:
            throw r14
        L_0x01ff:
            r14 = move-exception
            java.lang.String r0 = "CameraLauncher"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Exception while getting input stream: "
            r2.append(r3)
            java.lang.String r14 = r14.toString()
            r2.append(r14)
            java.lang.String r14 = r2.toString()
            org.apache.cordova.LOG.e(r0, r14)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.camera.CameraLauncher.getScaledAndRotatedBitmap(java.lang.String):android.graphics.Bitmap");
    }

    public int[] calculateAspectRatio(int i, int i2) {
        int i3 = this.targetWidth;
        int i4 = this.targetHeight;
        if (i3 > 0 || i4 > 0) {
            if (i3 > 0 && i4 <= 0) {
                double d = (double) i3;
                double d2 = (double) i;
                Double.isNaN(d);
                Double.isNaN(d2);
                double d3 = (double) i2;
                Double.isNaN(d3);
                i2 = (int) ((d / d2) * d3);
                i = i3;
            } else if (i3 > 0 || i4 <= 0) {
                double d4 = (double) i3;
                double d5 = (double) i4;
                Double.isNaN(d4);
                Double.isNaN(d5);
                double d6 = d4 / d5;
                double d7 = (double) i;
                double d8 = (double) i2;
                Double.isNaN(d7);
                Double.isNaN(d8);
                double d9 = d7 / d8;
                if (d9 > d6) {
                    i2 = (i2 * i3) / i;
                    i = i3;
                } else if (d9 < d6) {
                    i = (i * i4) / i2;
                    i2 = i4;
                } else {
                    i = i3;
                    i2 = i4;
                }
            } else {
                double d10 = (double) i4;
                double d11 = (double) i2;
                Double.isNaN(d10);
                Double.isNaN(d11);
                double d12 = (double) i;
                Double.isNaN(d12);
                i = (int) ((d10 / d11) * d12);
                i2 = i4;
            }
        }
        return new int[]{i, i2};
    }

    public static int calculateSampleSize(int i, int i2, int i3, int i4) {
        if (((float) i) / ((float) i2) > ((float) i3) / ((float) i4)) {
            return i / i3;
        }
        return i2 / i4;
    }

    private Cursor queryImgDB(Uri uri) {
        return this.cordova.getActivity().getContentResolver().query(uri, new String[]{"_id"}, (String) null, (String[]) null, (String) null);
    }

    private void cleanup(int i, Uri uri, Uri uri2, Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
        }
        new File(FileHelper.stripFileProtocol(uri.toString())).delete();
        checkForDuplicateImage(i);
        if (this.saveToPhotoAlbum && uri2 != null) {
            scanForGallery(uri2);
        }
        System.gc();
    }

    private void checkForDuplicateImage(int i) {
        Uri whichContentStore = whichContentStore();
        Cursor queryImgDB = queryImgDB(whichContentStore);
        int count = queryImgDB.getCount();
        int i2 = 1;
        if (i == 1 && this.saveToPhotoAlbum) {
            i2 = 2;
        }
        if (count - this.numPics == i2) {
            queryImgDB.moveToLast();
            int intValue = Integer.valueOf(queryImgDB.getString(queryImgDB.getColumnIndex("_id"))).intValue();
            if (i2 == 2) {
                intValue--;
            }
            this.cordova.getActivity().getContentResolver().delete(Uri.parse(whichContentStore + "/" + intValue), (String) null, (String[]) null);
            queryImgDB.close();
        }
    }

    private Uri whichContentStore() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        return MediaStore.Images.Media.INTERNAL_CONTENT_URI;
    }

    public void processPicture(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            if (bitmap.compress(i == 0 ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, this.mQuality, byteArrayOutputStream)) {
                this.callbackContext.success(new String(Base64.encode(byteArrayOutputStream.toByteArray(), 2)));
            }
        } catch (Exception unused) {
            failPicture("Error compressing image.");
        }
    }

    public void failPicture(String str) {
        this.callbackContext.error(str);
    }

    private void scanForGallery(Uri uri) {
        this.scanMe = uri;
        MediaScannerConnection mediaScannerConnection = this.conn;
        if (mediaScannerConnection != null) {
            mediaScannerConnection.disconnect();
        }
        this.conn = new MediaScannerConnection(this.cordova.getActivity().getApplicationContext(), this);
        this.conn.connect();
    }

    public void onMediaScannerConnected() {
        try {
            this.conn.scanFile(this.scanMe.toString(), "image/*");
        } catch (IllegalStateException unused) {
            LOG.e(LOG_TAG, "Can't scan file in MediaScanner after taking picture");
        }
    }

    public void onScanCompleted(String str, Uri uri) {
        this.conn.disconnect();
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) throws JSONException {
        for (int i2 : iArr) {
            if (i2 == -1) {
                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
                return;
            }
        }
        switch (i) {
            case 0:
                takePicture(this.destType, this.encodingType);
                return;
            case 1:
                getImage(this.srcType, this.destType, this.encodingType);
                return;
            default:
                return;
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt("destType", this.destType);
        bundle.putInt("srcType", this.srcType);
        bundle.putInt("mQuality", this.mQuality);
        bundle.putInt("targetWidth", this.targetWidth);
        bundle.putInt("targetHeight", this.targetHeight);
        bundle.putInt("encodingType", this.encodingType);
        bundle.putInt("mediaType", this.mediaType);
        bundle.putInt("numPics", this.numPics);
        bundle.putBoolean("allowEdit", this.allowEdit);
        bundle.putBoolean("correctOrientation", this.correctOrientation);
        bundle.putBoolean("saveToPhotoAlbum", this.saveToPhotoAlbum);
        Uri uri = this.croppedUri;
        if (uri != null) {
            bundle.putString("croppedUri", uri.toString());
        }
        CordovaUri cordovaUri = this.imageUri;
        if (cordovaUri != null) {
            bundle.putString("imageUri", cordovaUri.getFileUri().toString());
        }
        return bundle;
    }

    public void onRestoreStateForActivityResult(Bundle bundle, CallbackContext callbackContext2) {
        this.destType = bundle.getInt("destType");
        this.srcType = bundle.getInt("srcType");
        this.mQuality = bundle.getInt("mQuality");
        this.targetWidth = bundle.getInt("targetWidth");
        this.targetHeight = bundle.getInt("targetHeight");
        this.encodingType = bundle.getInt("encodingType");
        this.mediaType = bundle.getInt("mediaType");
        this.numPics = bundle.getInt("numPics");
        this.allowEdit = bundle.getBoolean("allowEdit");
        this.correctOrientation = bundle.getBoolean("correctOrientation");
        this.saveToPhotoAlbum = bundle.getBoolean("saveToPhotoAlbum");
        if (bundle.containsKey("croppedUri")) {
            this.croppedUri = Uri.parse(bundle.getString("croppedUri"));
        }
        if (bundle.containsKey("imageUri")) {
            this.imageUri = new CordovaUri(Uri.parse(bundle.getString("imageUri")));
        }
        this.callbackContext = callbackContext2;
    }

    private String getFileNameFromUri(Uri uri) {
        String str = uri.toString().split("external_files")[1];
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        return externalStorageDirectory.getAbsolutePath() + str;
    }
}
