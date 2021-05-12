package com.urbanairship.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import androidx.core.content.ContextCompat;
import com.urbanairship.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DataManager {
    private static final String DATABASE_DIRECTORY_NAME = "com.urbanairship.databases";
    private static final int MAX_ATTEMPTS = 3;
    private final SQLiteOpenHelper openHelper;
    private final String path;

    /* access modifiers changed from: protected */
    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
    }

    /* access modifiers changed from: protected */
    public abstract void onCreate(SQLiteDatabase sQLiteDatabase);

    /* access modifiers changed from: protected */
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
    }

    public DataManager(Context context, String str, String str2, int i) {
        String migrateDatabase = migrateDatabase(context, str, str2);
        this.path = migrateDatabase;
        this.openHelper = new SQLiteOpenHelper(context, migrateDatabase, (SQLiteDatabase.CursorFactory) null, i) {
            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                DataManager.this.onCreate(sQLiteDatabase);
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                Logger.debug("DataManager - Upgrading database %s from version %s to %s", sQLiteDatabase, Integer.valueOf(i), Integer.valueOf(i2));
                DataManager.this.onUpgrade(sQLiteDatabase, i, i2);
            }

            public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                Logger.debug("DataManager - Downgrading database %s from version %s to %s", sQLiteDatabase, Integer.valueOf(i), Integer.valueOf(i2));
                DataManager.this.onDowngrade(sQLiteDatabase, i, i2);
            }

            public void onConfigure(SQLiteDatabase sQLiteDatabase) {
                super.onConfigure(sQLiteDatabase);
                DataManager.this.onConfigure(sQLiteDatabase);
            }

            public void onOpen(SQLiteDatabase sQLiteDatabase) {
                super.onOpen(sQLiteDatabase);
                DataManager.this.onOpen(sQLiteDatabase);
            }
        };
    }

    /* access modifiers changed from: protected */
    public SQLiteDatabase getWritableDatabase() {
        int i = 0;
        while (i < 3) {
            try {
                return this.openHelper.getWritableDatabase();
            } catch (SQLiteException e) {
                SystemClock.sleep(100);
                Logger.error(e, "DataManager - Error opening writable database. Retrying...", new Object[0]);
                i++;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public SQLiteDatabase getReadableDatabase() {
        int i = 0;
        while (i < 3) {
            try {
                return this.openHelper.getReadableDatabase();
            } catch (SQLiteException e) {
                SystemClock.sleep(100);
                Logger.error(e, "DataManager - Error opening readable database. Retrying...", new Object[0]);
                i++;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.debug("DataManager - onUpgrade not implemented yet.", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new SQLiteException("Unable to downgrade database");
    }

    public int delete(String str, String str2, String[] strArr) {
        if (str2 == null) {
            str2 = "1";
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase == null) {
            return -1;
        }
        int i = 0;
        while (i < 3) {
            try {
                return writableDatabase.delete(str, str2, strArr);
            } catch (Exception e) {
                Logger.error(e, "Unable to delete item from a database", new Object[0]);
                i++;
            }
        }
        return -1;
    }

    public List<ContentValues> bulkInsert(String str, ContentValues[] contentValuesArr) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ArrayList arrayList = new ArrayList();
        if (writableDatabase == null) {
            return arrayList;
        }
        writableDatabase.beginTransaction();
        int length = contentValuesArr.length;
        int i = 0;
        while (i < length) {
            try {
                writableDatabase.replaceOrThrow(str, (String) null, contentValuesArr[i]);
                i++;
            } catch (Exception e) {
                Logger.error(e, "Unable to insert into database", new Object[0]);
                writableDatabase.endTransaction();
                return Collections.emptyList();
            }
        }
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        return arrayList;
    }

    public long insert(String str, ContentValues contentValues) {
        if (getWritableDatabase() == null) {
            return -1;
        }
        int i = 0;
        while (i < 3) {
            try {
                return getWritableDatabase().replaceOrThrow(str, (String) null, contentValues);
            } catch (Exception e) {
                Logger.error(e, "Unable to insert into database", new Object[0]);
                i++;
            }
        }
        return -1;
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase == null) {
            return -1;
        }
        int i = 0;
        while (i < 3) {
            try {
                return writableDatabase.update(str, contentValues, str2, strArr);
            } catch (SQLException e) {
                Logger.error(e, "Update Failed", new Object[0]);
                i++;
            }
        }
        return -1;
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        return query(str, strArr, str2, strArr2, str3, (String) null);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase == null) {
            return null;
        }
        int i = 0;
        while (i < 3) {
            try {
                return readableDatabase.query(str, strArr, str2, strArr2, (String) null, (String) null, str3, str4);
            } catch (SQLException e) {
                Logger.error(e, "Query Failed", new Object[0]);
                i++;
            }
        }
        return null;
    }

    public Cursor rawQuery(String str, String[] strArr) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase == null) {
            return null;
        }
        int i = 0;
        while (i < 3) {
            try {
                return readableDatabase.rawQuery(str, strArr);
            } catch (SQLException e) {
                Logger.error(e, "Query failed", new Object[0]);
                i++;
            }
        }
        return null;
    }

    public void close() {
        try {
            this.openHelper.close();
        } catch (Exception e) {
            Logger.error(e, "Failed to close the database.", new Object[0]);
        }
    }

    public boolean databaseExists(Context context) {
        return context.getDatabasePath(this.path).exists();
    }

    public boolean deleteDatabase(Context context) {
        try {
            return context.getDatabasePath(this.path).delete();
        } catch (Exception e) {
            Logger.error(e, "Failed to delete database: " + this.path, new Object[0]);
            return false;
        }
    }

    protected static String migrateDatabase(Context context, String str, String str2) {
        String str3 = str + "_" + str2;
        File file = new File(ContextCompat.getNoBackupFilesDir(context), DATABASE_DIRECTORY_NAME);
        if (!file.exists() && !file.mkdirs()) {
            Logger.error("Failed to create UA no backup directory.", new Object[0]);
        }
        File file2 = new File(file, str3);
        File[] fileArr = {context.getDatabasePath(str3), new File(file, str2), context.getDatabasePath(str2)};
        if (file2.exists()) {
            return file2.getAbsolutePath();
        }
        for (int i = 0; i < 3; i++) {
            File file3 = fileArr[i];
            if (file3.exists()) {
                if (!file3.renameTo(file2)) {
                    return file3.getAbsolutePath();
                }
                File file4 = new File(file3.getAbsolutePath() + "-journal");
                if (file4.exists()) {
                    if (!file4.renameTo(new File(file2.getAbsolutePath() + "-journal"))) {
                        Logger.error("Failed to move the journal file: " + file4, new Object[0]);
                    }
                }
            }
        }
        return file2.getAbsolutePath();
    }
}
