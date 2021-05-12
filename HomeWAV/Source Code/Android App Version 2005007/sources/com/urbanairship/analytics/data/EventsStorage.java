package com.urbanairship.analytics.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.urbanairship.Logger;
import com.urbanairship.util.DataManager;

public class EventsStorage extends DataManager {
    private static final String DATABASE_NAME = "ua_analytics.db";
    private static final int DATABASE_VERSION = 1;

    public interface Events extends BaseColumns {
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";
        public static final String COLUMN_NAME_EVENT_SIZE = "event_size";
        public static final String COLUMN_NAME_SESSION_ID = "session_id";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String TABLE_NAME = "events";
    }

    public EventsStorage(Context context, String str) {
        super(context, str, DATABASE_NAME, 1);
    }

    /* access modifiers changed from: protected */
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.debug("EventsStorage - Upgrading analytics database from version %s to %s, which will destroy all old data", Integer.valueOf(i), Integer.valueOf(i2));
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: protected */
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (_id INTEGER PRIMARY KEY AUTOINCREMENT,type TEXT,event_id TEXT,time INTEGER,data TEXT,session_id TEXT,event_size INTEGER);");
    }

    /* access modifiers changed from: protected */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.debug("EventsStorage - Downgrading analytics database from version %s to %s, which will destroy all data.", Integer.valueOf(i), Integer.valueOf(i2));
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        onCreate(sQLiteDatabase);
    }
}
