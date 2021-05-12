package com.urbanairship.automation.storage;

import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.urbanairship.UrbanAirshipProvider;
import com.urbanairship.automation.storage.LegacyDataManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public final class AutomationDatabase_Impl extends AutomationDatabase {
    private volatile AutomationDao _automationDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) {
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `schedules` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `scheduleId` TEXT, `group` TEXT, `metadata` TEXT, `limit` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `scheduleStart` INTEGER NOT NULL, `scheduleEnd` INTEGER NOT NULL, `editGracePeriod` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `scheduleType` TEXT, `data` TEXT, `count` INTEGER NOT NULL, `executionState` INTEGER NOT NULL, `executionStateChangeDate` INTEGER NOT NULL, `triggerContext` TEXT, `appState` INTEGER NOT NULL, `screens` TEXT, `seconds` INTEGER NOT NULL, `regionId` TEXT, `audience` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_schedules_scheduleId` ON `schedules` (`scheduleId`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `triggers` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `triggerType` INTEGER NOT NULL, `goal` REAL NOT NULL, `jsonPredicate` TEXT, `isCancellation` INTEGER NOT NULL, `progress` REAL NOT NULL, `parentScheduleId` TEXT, FOREIGN KEY(`parentScheduleId`) REFERENCES `schedules`(`scheduleId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_triggers_parentScheduleId` ON `triggers` (`parentScheduleId`)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd0ffbb232f3f93464902796445684886')");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `schedules`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `triggers`");
                if (AutomationDatabase_Impl.this.mCallbacks != null) {
                    int size = AutomationDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AutomationDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (AutomationDatabase_Impl.this.mCallbacks != null) {
                    int size = AutomationDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AutomationDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase unused = AutomationDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                supportSQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
                AutomationDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (AutomationDatabase_Impl.this.mCallbacks != null) {
                    int size = AutomationDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AutomationDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            /* access modifiers changed from: protected */
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(21);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, (String) null, 1));
                hashMap.put("scheduleId", new TableInfo.Column("scheduleId", "TEXT", false, 0, (String) null, 1));
                hashMap.put("group", new TableInfo.Column("group", "TEXT", false, 0, (String) null, 1));
                hashMap.put(TtmlNode.TAG_METADATA, new TableInfo.Column(TtmlNode.TAG_METADATA, "TEXT", false, 0, (String) null, 1));
                hashMap.put(UrbanAirshipProvider.QUERY_PARAMETER_LIMIT, new TableInfo.Column(UrbanAirshipProvider.QUERY_PARAMETER_LIMIT, "INTEGER", true, 0, (String) null, 1));
                hashMap.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("scheduleStart", new TableInfo.Column("scheduleStart", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("scheduleEnd", new TableInfo.Column("scheduleEnd", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("editGracePeriod", new TableInfo.Column("editGracePeriod", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("scheduleType", new TableInfo.Column("scheduleType", "TEXT", false, 0, (String) null, 1));
                hashMap.put("data", new TableInfo.Column("data", "TEXT", false, 0, (String) null, 1));
                hashMap.put("count", new TableInfo.Column("count", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("executionState", new TableInfo.Column("executionState", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("executionStateChangeDate", new TableInfo.Column("executionStateChangeDate", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("triggerContext", new TableInfo.Column("triggerContext", "TEXT", false, 0, (String) null, 1));
                hashMap.put("appState", new TableInfo.Column("appState", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("screens", new TableInfo.Column("screens", "TEXT", false, 0, (String) null, 1));
                hashMap.put("seconds", new TableInfo.Column("seconds", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("regionId", new TableInfo.Column("regionId", "TEXT", false, 0, (String) null, 1));
                hashMap.put("audience", new TableInfo.Column("audience", "TEXT", false, 0, (String) null, 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(1);
                hashSet2.add(new TableInfo.Index("index_schedules_scheduleId", true, Arrays.asList(new String[]{"scheduleId"})));
                TableInfo tableInfo = new TableInfo("schedules", hashMap, hashSet, hashSet2);
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, "schedules");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "schedules(com.urbanairship.automation.storage.ScheduleEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(7);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, (String) null, 1));
                hashMap2.put("triggerType", new TableInfo.Column("triggerType", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("goal", new TableInfo.Column("goal", "REAL", true, 0, (String) null, 1));
                hashMap2.put("jsonPredicate", new TableInfo.Column("jsonPredicate", "TEXT", false, 0, (String) null, 1));
                hashMap2.put("isCancellation", new TableInfo.Column("isCancellation", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put(NotificationCompat.CATEGORY_PROGRESS, new TableInfo.Column(NotificationCompat.CATEGORY_PROGRESS, "REAL", true, 0, (String) null, 1));
                hashMap2.put("parentScheduleId", new TableInfo.Column("parentScheduleId", "TEXT", false, 0, (String) null, 1));
                HashSet hashSet3 = new HashSet(1);
                hashSet3.add(new TableInfo.ForeignKey("schedules", "CASCADE", "NO ACTION", Arrays.asList(new String[]{"parentScheduleId"}), Arrays.asList(new String[]{"scheduleId"})));
                HashSet hashSet4 = new HashSet(1);
                hashSet4.add(new TableInfo.Index("index_triggers_parentScheduleId", false, Arrays.asList(new String[]{"parentScheduleId"})));
                TableInfo tableInfo2 = new TableInfo(LegacyDataManager.TriggerTable.TABLE_NAME, hashMap2, hashSet3, hashSet4);
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, LegacyDataManager.TriggerTable.TABLE_NAME);
                if (tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(true, (String) null);
                }
                return new RoomOpenHelper.ValidationResult(false, "triggers(com.urbanairship.automation.storage.TriggerEntity).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
        }, "d0ffbb232f3f93464902796445684886", "7d66cc87e245c7dbe2a16904ddbdc4ac")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "schedules", LegacyDataManager.TriggerTable.TABLE_NAME);
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        boolean z = Build.VERSION.SDK_INT >= 21;
        if (!z) {
            try {
                writableDatabase.execSQL("PRAGMA foreign_keys = FALSE");
            } catch (Throwable th) {
                super.endTransaction();
                if (!z) {
                    writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
                }
                writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
                if (!writableDatabase.inTransaction()) {
                    writableDatabase.execSQL("VACUUM");
                }
                throw th;
            }
        }
        super.beginTransaction();
        if (z) {
            writableDatabase.execSQL("PRAGMA defer_foreign_keys = TRUE");
        }
        writableDatabase.execSQL("DELETE FROM `schedules`");
        writableDatabase.execSQL("DELETE FROM `triggers`");
        super.setTransactionSuccessful();
        super.endTransaction();
        if (!z) {
            writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
        }
        writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
        if (!writableDatabase.inTransaction()) {
            writableDatabase.execSQL("VACUUM");
        }
    }

    public AutomationDao getScheduleDao() {
        AutomationDao automationDao;
        if (this._automationDao != null) {
            return this._automationDao;
        }
        synchronized (this) {
            if (this._automationDao == null) {
                this._automationDao = new AutomationDao_Impl(this);
            }
            automationDao = this._automationDao;
        }
        return automationDao;
    }
}
