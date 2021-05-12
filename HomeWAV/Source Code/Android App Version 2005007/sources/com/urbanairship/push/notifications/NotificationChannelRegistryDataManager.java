package com.urbanairship.push.notifications;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.DataManager;
import java.util.HashSet;
import java.util.Set;

public class NotificationChannelRegistryDataManager extends DataManager {
    static final String COLUMN_NAME_CHANNEL_ID = "channel_id";
    static final String COLUMN_NAME_DATA = "data";
    static final String COLUMN_NAME_ID = "id";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "notification_channels";

    public NotificationChannelRegistryDataManager(Context context, String str, String str2) {
        super(context, str, str2, 1);
    }

    /* access modifiers changed from: protected */
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Logger.debug("NotificationChannelRegistryDataManager - Creating database", new Object[0]);
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notification_channels (id INTEGER PRIMARY KEY AUTOINCREMENT,channel_id TEXT,data TEXT);");
    }

    public boolean createChannel(NotificationChannelCompat notificationChannelCompat) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase == null) {
            Logger.error("NotificationChannelRegistryDataManager - Unable to save notification channel.", new Object[0]);
            return false;
        }
        saveChannel(notificationChannelCompat, writableDatabase);
        return true;
    }

    public Set<NotificationChannelCompat> getChannels() {
        Cursor query = query(TABLE_NAME, (String[]) null, (String) null, (String[]) null, (String) null);
        HashSet hashSet = new HashSet();
        if (query == null) {
            return hashSet;
        }
        query.moveToFirst();
        while (!query.isAfterLast()) {
            hashSet.add(getChannel(query));
            query.moveToNext();
        }
        return hashSet;
    }

    public NotificationChannelCompat getChannel(String str) {
        Cursor query = query(TABLE_NAME, (String[]) null, "channel_id = ?", new String[]{str}, (String) null);
        NotificationChannelCompat notificationChannelCompat = null;
        if (query == null) {
            return null;
        }
        query.moveToFirst();
        if (!query.isAfterLast()) {
            notificationChannelCompat = getChannel(query);
        }
        query.close();
        return notificationChannelCompat;
    }

    public boolean deleteChannel(String str) {
        if (delete(TABLE_NAME, "channel_id = ?", new String[]{str}) != -1) {
            return true;
        }
        Logger.error("Unable to remove notification channel: %s", str);
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean deleteChannels() {
        boolean z = delete(TABLE_NAME, (String) null, (String[]) null) >= 0;
        if (!z) {
            Logger.error("NotificationChannelRegistryDatamanager - failed to delete channels", new Object[0]);
        }
        return z;
    }

    private NotificationChannelCompat getChannel(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("data"));
        try {
            return NotificationChannelCompat.fromJson(JsonValue.parseString(string));
        } catch (JsonException unused) {
            Logger.error("Unable to parse notification channel: %s", string);
            return null;
        }
    }

    private void saveChannel(NotificationChannelCompat notificationChannelCompat, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("channel_id", notificationChannelCompat.getId());
        contentValues.put("data", notificationChannelCompat.toJsonValue().toString());
        sQLiteDatabase.insert(TABLE_NAME, (String) null, contentValues);
    }
}
