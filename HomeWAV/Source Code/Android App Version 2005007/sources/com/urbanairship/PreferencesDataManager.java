package com.urbanairship;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.urbanairship.util.DataManager;

class PreferencesDataManager extends DataManager {
    static final String COLUMN_NAME_KEY = "_id";
    static final String COLUMN_NAME_VALUE = "value";
    static final String DATABASE_NAME = "ua_preferences.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "preferences";

    public PreferencesDataManager(Context context, String str) {
        super(context, str, DATABASE_NAME, 1);
    }

    /* access modifiers changed from: protected */
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS preferences (_id TEXT PRIMARY KEY, value TEXT);");
    }

    /* access modifiers changed from: protected */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS preferences");
        onCreate(sQLiteDatabase);
    }
}
