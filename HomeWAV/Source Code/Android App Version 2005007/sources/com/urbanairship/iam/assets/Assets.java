package com.urbanairship.iam.assets;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.storage.StorageManager;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class Assets implements Parcelable {
    public static final Parcelable.Creator<Assets> CREATOR = new Parcelable.Creator<Assets>() {
        public Assets createFromParcel(Parcel parcel) {
            JsonMap jsonMap;
            try {
                jsonMap = JsonValue.parseString(parcel.readString()).optMap();
            } catch (JsonException e) {
                Logger.error(e, "Failed to parse metadata", new Object[0]);
                jsonMap = JsonMap.EMPTY_MAP;
            }
            return new Assets(new File(parcel.readString()), jsonMap);
        }

        public Assets[] newArray(int i) {
            return new Assets[i];
        }
    };
    private static final String FILES_DIRECTORY = "files";
    private static final String METADATA_FILE = "metadata";
    private final Executor executor;
    private final File filesDirectory;
    /* access modifiers changed from: private */
    public final Map<String, JsonValue> metadata;
    /* access modifiers changed from: private */
    public final File metadataFile;
    private final Object metadataLock;
    private final File rootDirectory;

    public int describeContents() {
        return 0;
    }

    static Assets load(File file) {
        return new Assets(file, readJson(new File(file, "metadata")).optMap());
    }

    private Assets(File file, JsonMap jsonMap) {
        this.metadataLock = new Object();
        this.rootDirectory = file;
        this.filesDirectory = new File(file, FILES_DIRECTORY);
        this.metadataFile = new File(file, "metadata");
        this.metadata = new HashMap(jsonMap.getMap());
        this.executor = AirshipExecutors.newSerialExecutor();
    }

    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this.metadataLock) {
            parcel.writeString(JsonValue.wrapOpt(this.metadata).toString());
        }
        parcel.writeString(this.rootDirectory.getAbsolutePath());
    }

    public File file(String str) {
        prepareDirectory();
        return new File(this.filesDirectory, UAStringUtil.sha256(str));
    }

    public JsonValue getMetadata(String str) {
        JsonValue jsonValue;
        synchronized (this.metadataLock) {
            jsonValue = this.metadata.get(str);
            if (jsonValue == null) {
                jsonValue = JsonValue.NULL;
            }
        }
        return jsonValue;
    }

    public void setMetadata(String str, JsonSerializable jsonSerializable) {
        synchronized (this.metadataLock) {
            this.metadata.put(str, jsonSerializable.toJsonValue());
            this.executor.execute(new Runnable() {
                public void run() {
                    Assets assets = Assets.this;
                    assets.writeJson(assets.metadataFile, JsonValue.wrapOpt(Assets.this.metadata));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void writeJson(File file, JsonValue jsonValue) {
        prepareDirectory();
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                fileOutputStream2.write(jsonValue.toString().getBytes());
                fileOutputStream2.close();
                closeQuietly(fileOutputStream2);
            } catch (Exception e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    Logger.error(e, "Failed to write metadata.", new Object[0]);
                    closeQuietly(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            Logger.error(e, "Failed to write metadata.", new Object[0]);
            closeQuietly(fileOutputStream);
        }
    }

    private static JsonValue readJson(File file) {
        if (!file.exists()) {
            return JsonValue.NULL;
        }
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            try {
                StringWriter stringWriter = new StringWriter();
                char[] cArr = new char[1024];
                while (true) {
                    int read = bufferedReader2.read(cArr);
                    if (read != -1) {
                        stringWriter.write(cArr, 0, read);
                    } else {
                        JsonValue parseString = JsonValue.parseString(stringWriter.toString());
                        closeQuietly(bufferedReader2);
                        return parseString;
                    }
                }
            } catch (IOException e) {
                e = e;
                bufferedReader = bufferedReader2;
                Logger.error(e, "Error reading file", new Object[0]);
                closeQuietly(bufferedReader);
                return JsonValue.NULL;
            } catch (JsonException e2) {
                e = e2;
                bufferedReader = bufferedReader2;
                Logger.error(e, "Error parsing file as JSON.", new Object[0]);
                closeQuietly(bufferedReader);
                return JsonValue.NULL;
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                closeQuietly(bufferedReader);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            Logger.error(e, "Error reading file", new Object[0]);
            closeQuietly(bufferedReader);
            return JsonValue.NULL;
        } catch (JsonException e4) {
            e = e4;
            Logger.error(e, "Error parsing file as JSON.", new Object[0]);
            closeQuietly(bufferedReader);
            return JsonValue.NULL;
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(bufferedReader);
            throw th;
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Logger.error(e);
            }
        }
    }

    private void prepareDirectory() {
        if (!this.rootDirectory.exists()) {
            if (!this.rootDirectory.mkdirs()) {
                Logger.error("Failed to create assets directory.", new Object[0]);
            } else if (Build.VERSION.SDK_INT >= 26) {
                try {
                    ((StorageManager) UAirship.getApplicationContext().getSystemService("storage")).setCacheBehaviorGroup(this.rootDirectory, true);
                } catch (IOException e) {
                    Logger.error(e, "Failed to set cache behavior on directory: %s", this.rootDirectory.getAbsoluteFile());
                }
            }
        }
        if (!this.filesDirectory.exists() && !this.filesDirectory.mkdirs()) {
            Logger.error("Failed to create directory: %s", this.filesDirectory.getAbsoluteFile());
        }
    }
}
