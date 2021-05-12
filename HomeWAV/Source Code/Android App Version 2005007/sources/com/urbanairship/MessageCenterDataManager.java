package com.urbanairship;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.urbanairship.util.DataManager;

public class MessageCenterDataManager extends DataManager {
    private static final String DATABASE_NAME = "ua_richpush.db";
    private static final int DATABASE_VERSION = 3;

    public interface MessageTable {
        public static final String COLUMN_NAME_DELETED = "deleted";
        public static final String COLUMN_NAME_EXPIRATION_TIMESTAMP = "expiration_timestamp";
        public static final String COLUMN_NAME_EXTRA = "extra";
        public static final String COLUMN_NAME_KEY = "_id";
        public static final String COLUMN_NAME_MESSAGE_BODY_URL = "message_body_url";
        public static final String COLUMN_NAME_MESSAGE_ID = "message_id";
        public static final String COLUMN_NAME_MESSAGE_READ_URL = "message_read_url";
        public static final String COLUMN_NAME_MESSAGE_URL = "message_url";
        public static final String COLUMN_NAME_RAW_MESSAGE_OBJECT = "raw_message_object";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_UNREAD = "unread";
        public static final String COLUMN_NAME_UNREAD_ORIG = "unread_orig";
        public static final String TABLE_NAME = "richpush";
    }

    public MessageCenterDataManager(Context context, String str) {
        super(context, str, DATABASE_NAME, 3);
    }

    /* access modifiers changed from: protected */
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS richpush (_id INTEGER PRIMARY KEY AUTOINCREMENT, message_id TEXT UNIQUE, message_url TEXT, message_body_url TEXT, message_read_url TEXT, title TEXT, extra TEXT, unread INTEGER, unread_orig INTEGER, deleted INTEGER, timestamp TEXT, raw_message_object TEXT,expiration_timestamp TEXT);");
    }

    /* access modifiers changed from: protected */
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 1) {
            sQLiteDatabase.execSQL("ALTER TABLE richpush ADD COLUMN raw_message_object TEXT;");
        } else if (i != 2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS richpush");
            onCreate(sQLiteDatabase);
            return;
        }
        sQLiteDatabase.execSQL("ALTER TABLE richpush ADD COLUMN expiration_timestamp TEXT;");
    }

    /* access modifiers changed from: protected */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS richpush");
        onCreate(sQLiteDatabase);
    }
}
