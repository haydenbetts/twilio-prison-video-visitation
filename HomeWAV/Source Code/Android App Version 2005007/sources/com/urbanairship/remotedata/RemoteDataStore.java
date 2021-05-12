package com.urbanairship.remotedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.DataManager;
import com.urbanairship.util.UAStringUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RemoteDataStore extends DataManager {
    private static final String COLUMN_NAME_DATA = "data";
    private static final String COLUMN_NAME_ID = "id";
    private static final String COLUMN_NAME_METADATA = "metadata";
    private static final String COLUMN_NAME_TIMESTAMP = "time";
    private static final String COLUMN_NAME_TYPE = "type";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "payloads";

    public RemoteDataStore(Context context, String str, String str2) {
        super(context, str, str2, 2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Logger.debug("RemoteDataStore - Creating database", new Object[0]);
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS payloads (id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT,time INTEGER,data TEXT,metadata TEXT);");
    }

    /* access modifiers changed from: protected */
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != 1) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS payloads");
            onCreate(sQLiteDatabase);
            return;
        }
        sQLiteDatabase.execSQL("ALTER TABLE payloads ADD COLUMN metadata TEXT;");
    }

    /* access modifiers changed from: protected */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        super.onDowngrade(sQLiteDatabase, i, i2);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS payloads");
        onCreate(sQLiteDatabase);
    }

    public boolean savePayloads(Set<RemoteDataPayload> set) {
        if (set.isEmpty()) {
            return true;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase == null) {
            Logger.error("RemoteDataStore - Unable to save remote data payloads.", new Object[0]);
            return false;
        }
        try {
            writableDatabase.beginTransaction();
            for (RemoteDataPayload next : set) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("type", next.getType());
                contentValues.put("time", Long.valueOf(next.getTimestamp()));
                contentValues.put("data", next.getData().toString());
                contentValues.put("metadata", next.getMetadata().toString());
                try {
                    if (writableDatabase.insert(TABLE_NAME, (String) null, contentValues) == -1) {
                        writableDatabase.endTransaction();
                        return false;
                    }
                } catch (SQLException e) {
                    Logger.error(e, "RemoteDataStore - Unable to save remote data payload.", new Object[0]);
                }
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            return true;
        } catch (SQLException e2) {
            Logger.error(e2, "RemoteDataStore - Unable to save remote data payloads.", new Object[0]);
            return false;
        }
    }

    public Set<RemoteDataPayload> getPayloads() {
        return getPayloads((Collection<String>) null);
    }

    /* access modifiers changed from: package-private */
    public Set<RemoteDataPayload> getPayloads(Collection<String> collection) {
        Cursor query;
        Cursor cursor = null;
        if (collection == null) {
            try {
                query = query(TABLE_NAME, (String[]) null, (String) null, (String[]) null, (String) null);
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            query = query(TABLE_NAME, (String[]) null, "type IN ( " + UAStringUtil.repeat("?", collection.size(), ", ") + " )", (String[]) collection.toArray(new String[0]), (String) null);
        }
        cursor = query;
        if (cursor == null) {
            Set<RemoteDataPayload> emptySet = Collections.emptySet();
            if (cursor != null) {
                cursor.close();
            }
            return emptySet;
        }
        Set<RemoteDataPayload> generatePayloadEntries = generatePayloadEntries(cursor);
        if (cursor != null) {
            cursor.close();
        }
        return generatePayloadEntries;
    }

    /* access modifiers changed from: package-private */
    public boolean deletePayloads() {
        boolean z = delete(TABLE_NAME, (String) null, (String[]) null) >= 0;
        if (!z) {
            Logger.error("RemoteDataStore - failed to delete payloads", new Object[0]);
        }
        return z;
    }

    private Set<RemoteDataPayload> generatePayloadEntries(Cursor cursor) {
        cursor.moveToFirst();
        HashSet hashSet = new HashSet();
        while (!cursor.isAfterLast()) {
            try {
                hashSet.add(RemoteDataPayload.newBuilder().setType(cursor.getString(cursor.getColumnIndex("type"))).setTimeStamp(cursor.getLong(cursor.getColumnIndex("time"))).setMetadata(JsonValue.parseString(cursor.getString(cursor.getColumnIndex("metadata"))).optMap()).setData(JsonValue.parseString(cursor.getString(cursor.getColumnIndex("data"))).optMap()).build());
            } catch (JsonException | IllegalArgumentException e) {
                Logger.error(e, "RemoteDataStore - failed to retrieve payload", new Object[0]);
            }
            cursor.moveToNext();
        }
        return hashSet;
    }
}
