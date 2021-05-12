package wseemann.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class FFmpegMediaMetadataRetriever {
    public static Bitmap.Config IN_PREFERRED_CONFIG = null;
    private static final String[] JNI_LIBRARIES = {"avutil", "swscale", "avcodec", "avformat", "ffmpeg_mediametadataretriever_jni"};
    public static final String METADATA_CHAPTER_COUNT = "chapter_count";
    public static final String METADATA_KEY_ALBUM = "album";
    public static final String METADATA_KEY_ALBUM_ARTIST = "album_artist";
    public static final String METADATA_KEY_ARTIST = "artist";
    public static final String METADATA_KEY_AUDIO_CODEC = "audio_codec";
    public static final String METADATA_KEY_CHAPTER_END_TIME = "chapter_end_time";
    public static final String METADATA_KEY_CHAPTER_START_TIME = "chapter_start_time";
    public static final String METADATA_KEY_COMMENT = "comment";
    public static final String METADATA_KEY_COMPOSER = "composer";
    public static final String METADATA_KEY_COPYRIGHT = "copyright";
    public static final String METADATA_KEY_CREATION_TIME = "creation_time";
    public static final String METADATA_KEY_DATE = "date";
    public static final String METADATA_KEY_DISC = "disc";
    public static final String METADATA_KEY_DURATION = "duration";
    public static final String METADATA_KEY_ENCODED_BY = "encoded_by";
    public static final String METADATA_KEY_ENCODER = "encoder";
    public static final String METADATA_KEY_FILENAME = "filename";
    public static final String METADATA_KEY_FILESIZE = "filesize";
    public static final String METADATA_KEY_FRAMERATE = "framerate";
    public static final String METADATA_KEY_GENRE = "genre";
    public static final String METADATA_KEY_ICY_METADATA = "icy_metadata";
    public static final String METADATA_KEY_LANGUAGE = "language";
    public static final String METADATA_KEY_PERFORMER = "performer";
    public static final String METADATA_KEY_PUBLISHER = "publisher";
    public static final String METADATA_KEY_SERVICE_NAME = "service_name";
    public static final String METADATA_KEY_SERVICE_PROVIDER = "service_provider";
    public static final String METADATA_KEY_TITLE = "title";
    public static final String METADATA_KEY_TRACK = "track";
    public static final String METADATA_KEY_VARIANT_BITRATE = "bitrate";
    public static final String METADATA_KEY_VIDEO_CODEC = "video_codec";
    public static final String METADATA_KEY_VIDEO_HEIGHT = "video_height";
    public static final String METADATA_KEY_VIDEO_ROTATION = "rotate";
    public static final String METADATA_KEY_VIDEO_WIDTH = "video_width";
    public static final int OPTION_CLOSEST = 3;
    public static final int OPTION_CLOSEST_SYNC = 2;
    public static final int OPTION_NEXT_SYNC = 1;
    public static final int OPTION_PREVIOUS_SYNC = 0;
    private static final String TAG = "FFmpegMediaMetadataRetriever";
    private long mNativeContext;

    private native byte[] _getFrameAtTime(long j, int i);

    private native byte[] _getScaledFrameAtTime(long j, int i, int i2, int i3);

    private native void _setDataSource(String str, String[] strArr, String[] strArr2) throws IllegalArgumentException;

    private final native void native_finalize();

    private final native HashMap<String, String> native_getMetadata(boolean z, boolean z2, HashMap<String, String> hashMap);

    private static native void native_init();

    private native void native_setup();

    public native String extractMetadata(String str);

    public native String extractMetadataFromChapter(String str, int i);

    public native byte[] getEmbeddedPicture();

    public native void release();

    public native void setDataSource(FileDescriptor fileDescriptor, long j, long j2) throws IllegalArgumentException;

    public native void setDataSource(String str) throws IllegalArgumentException;

    public native void setSurface(Object obj);

    static {
        int i = 0;
        while (true) {
            String[] strArr = JNI_LIBRARIES;
            if (i < strArr.length) {
                System.loadLibrary(strArr[i]);
                i++;
            } else {
                native_init();
                return;
            }
        }
    }

    public FFmpegMediaMetadataRetriever() {
        native_setup();
    }

    public void setDataSource(String str, Map<String, String> map) throws IllegalArgumentException {
        String[] strArr = new String[map.size()];
        String[] strArr2 = new String[map.size()];
        int i = 0;
        for (Map.Entry next : map.entrySet()) {
            strArr[i] = (String) next.getKey();
            strArr2[i] = (String) next.getValue();
            i++;
        }
        _setDataSource(str, strArr, strArr2);
    }

    public void setDataSource(FileDescriptor fileDescriptor) throws IllegalArgumentException {
        setDataSource(fileDescriptor, 0, 576460752303423487L);
    }

    public void setDataSource(Context context, Uri uri) throws IllegalArgumentException, SecurityException {
        if (uri != null) {
            String scheme = uri.getScheme();
            if (scheme == null || scheme.equals("file")) {
                setDataSource(uri.getPath());
                return;
            }
            AssetFileDescriptor assetFileDescriptor = null;
            try {
                assetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
                if (assetFileDescriptor != null) {
                    FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
                    if (fileDescriptor.valid()) {
                        if (assetFileDescriptor.getDeclaredLength() < 0) {
                            setDataSource(fileDescriptor);
                        } else {
                            setDataSource(fileDescriptor, assetFileDescriptor.getStartOffset(), assetFileDescriptor.getDeclaredLength());
                        }
                        if (assetFileDescriptor != null) {
                            try {
                                assetFileDescriptor.close();
                            } catch (IOException unused) {
                            }
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (FileNotFoundException unused2) {
                throw new IllegalArgumentException();
            } catch (SecurityException unused3) {
                if (assetFileDescriptor != null) {
                    try {
                        assetFileDescriptor.close();
                    } catch (IOException unused4) {
                    }
                }
                setDataSource(uri.toString());
            } catch (Throwable th) {
                if (assetFileDescriptor != null) {
                    try {
                        assetFileDescriptor.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Metadata getMetadata() {
        Metadata metadata = new Metadata();
        HashMap<String, String> native_getMetadata = native_getMetadata(false, false, (HashMap<String, String>) null);
        if (native_getMetadata != null && metadata.parse(native_getMetadata)) {
            return metadata;
        }
        return null;
    }

    public Bitmap getFrameAtTime(long j, int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported option: " + i);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        byte[] _getFrameAtTime = _getFrameAtTime(j, i);
        if (_getFrameAtTime != null) {
            return BitmapFactory.decodeByteArray(_getFrameAtTime, 0, _getFrameAtTime.length, options);
        }
        return null;
    }

    public Bitmap getFrameAtTime(long j) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        byte[] _getFrameAtTime = _getFrameAtTime(j, 2);
        if (_getFrameAtTime != null) {
            return BitmapFactory.decodeByteArray(_getFrameAtTime, 0, _getFrameAtTime.length, options);
        }
        return null;
    }

    public Bitmap getFrameAtTime() {
        return getFrameAtTime(-1, 2);
    }

    public Bitmap getScaledFrameAtTime(long j, int i, int i2, int i3) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported option: " + i);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        byte[] _getScaledFrameAtTime = _getScaledFrameAtTime(j, i, i2, i3);
        if (_getScaledFrameAtTime != null) {
            return BitmapFactory.decodeByteArray(_getScaledFrameAtTime, 0, _getScaledFrameAtTime.length, options);
        }
        return null;
    }

    public Bitmap getScaledFrameAtTime(long j, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        byte[] _getScaledFrameAtTime = _getScaledFrameAtTime(j, 2, i, i2);
        if (_getScaledFrameAtTime != null) {
            return BitmapFactory.decodeByteArray(_getScaledFrameAtTime, 0, _getScaledFrameAtTime.length, options);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            native_finalize();
        } finally {
            super.finalize();
        }
    }

    public class Metadata {
        public static final int BOOLEAN_VAL = 3;
        public static final int BYTE_ARRAY_VAL = 7;
        public static final int DATE_VAL = 6;
        public static final int DOUBLE_VAL = 5;
        public static final int INTEGER_VAL = 2;
        public static final int LONG_VAL = 4;
        public static final int STRING_VAL = 1;
        private HashMap<String, String> mParcel;

        private boolean checkMetadataId(String str) {
            return true;
        }

        public Metadata() {
        }

        public boolean parse(HashMap<String, String> hashMap) {
            if (hashMap == null) {
                return false;
            }
            this.mParcel = hashMap;
            return true;
        }

        public boolean has(String str) {
            if (checkMetadataId(str)) {
                return this.mParcel.containsKey(str);
            }
            throw new IllegalArgumentException("Invalid key: " + str);
        }

        public HashMap<String, String> getAll() {
            return this.mParcel;
        }

        public String getString(String str) {
            checkType(str, 1);
            return String.valueOf(this.mParcel.get(str));
        }

        public int getInt(String str) {
            checkType(str, 2);
            return Integer.valueOf(this.mParcel.get(str)).intValue();
        }

        public boolean getBoolean(String str) {
            checkType(str, 3);
            return Integer.valueOf(this.mParcel.get(str)).intValue() == 1;
        }

        public long getLong(String str) {
            checkType(str, 4);
            return Long.valueOf(this.mParcel.get(str)).longValue();
        }

        public double getDouble(String str) {
            checkType(str, 5);
            return Double.valueOf(this.mParcel.get(str)).doubleValue();
        }

        public byte[] getByteArray(String str) {
            checkType(str, 7);
            return this.mParcel.get(str).getBytes();
        }

        public Date getDate(String str) {
            checkType(str, 6);
            long longValue = Long.valueOf(this.mParcel.get(str)).longValue();
            String str2 = this.mParcel.get(str);
            if (str2.length() == 0) {
                return new Date(longValue);
            }
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(str2));
            instance.setTimeInMillis(longValue);
            return instance.getTime();
        }

        private void checkType(String str, int i) {
            String str2 = this.mParcel.get(str);
            if (str2 == null) {
                throw new IllegalStateException("Wrong type " + i + " but got " + str2);
            }
        }
    }
}
