package com.urbanairship.automation.storage;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class AutomationDao_Impl extends AutomationDao {
    /* access modifiers changed from: private */
    public final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<ScheduleEntity> __deletionAdapterOfScheduleEntity;
    private final EntityInsertionAdapter<ScheduleEntity> __insertionAdapterOfScheduleEntity;
    private final EntityInsertionAdapter<TriggerEntity> __insertionAdapterOfTriggerEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteSchedulesByType;
    private final EntityDeletionOrUpdateAdapter<ScheduleEntity> __updateAdapterOfScheduleEntity;
    private final EntityDeletionOrUpdateAdapter<TriggerEntity> __updateAdapterOfTriggerEntity;

    public AutomationDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfScheduleEntity = new EntityInsertionAdapter<ScheduleEntity>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `schedules` (`id`,`scheduleId`,`group`,`metadata`,`limit`,`priority`,`scheduleStart`,`scheduleEnd`,`editGracePeriod`,`interval`,`scheduleType`,`data`,`count`,`executionState`,`executionStateChangeDate`,`triggerContext`,`appState`,`screens`,`seconds`,`regionId`,`audience`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ScheduleEntity scheduleEntity) {
                supportSQLiteStatement.bindLong(1, (long) scheduleEntity.id);
                if (scheduleEntity.scheduleId == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, scheduleEntity.scheduleId);
                }
                if (scheduleEntity.group == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, scheduleEntity.group);
                }
                String jsonMapToString = AutomationDao_Impl.this.__converters.jsonMapToString(scheduleEntity.metadata);
                if (jsonMapToString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, jsonMapToString);
                }
                supportSQLiteStatement.bindLong(5, (long) scheduleEntity.limit);
                supportSQLiteStatement.bindLong(6, (long) scheduleEntity.priority);
                supportSQLiteStatement.bindLong(7, scheduleEntity.scheduleStart);
                supportSQLiteStatement.bindLong(8, scheduleEntity.scheduleEnd);
                supportSQLiteStatement.bindLong(9, scheduleEntity.editGracePeriod);
                supportSQLiteStatement.bindLong(10, scheduleEntity.interval);
                if (scheduleEntity.scheduleType == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, scheduleEntity.scheduleType);
                }
                String jsonValueToString = AutomationDao_Impl.this.__converters.jsonValueToString(scheduleEntity.data);
                if (jsonValueToString == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, jsonValueToString);
                }
                supportSQLiteStatement.bindLong(13, (long) scheduleEntity.count);
                supportSQLiteStatement.bindLong(14, (long) scheduleEntity.executionState);
                supportSQLiteStatement.bindLong(15, scheduleEntity.executionStateChangeDate);
                String triggerContextToString = AutomationDao_Impl.this.__converters.triggerContextToString(scheduleEntity.triggerContext);
                if (triggerContextToString == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, triggerContextToString);
                }
                supportSQLiteStatement.bindLong(17, (long) scheduleEntity.appState);
                String fromArrayList = Converters.fromArrayList(scheduleEntity.screens);
                if (fromArrayList == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, fromArrayList);
                }
                supportSQLiteStatement.bindLong(19, scheduleEntity.seconds);
                if (scheduleEntity.regionId == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, scheduleEntity.regionId);
                }
                String audienceToString = AutomationDao_Impl.this.__converters.audienceToString(scheduleEntity.audience);
                if (audienceToString == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, audienceToString);
                }
            }
        };
        this.__insertionAdapterOfTriggerEntity = new EntityInsertionAdapter<TriggerEntity>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `triggers` (`id`,`triggerType`,`goal`,`jsonPredicate`,`isCancellation`,`progress`,`parentScheduleId`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, TriggerEntity triggerEntity) {
                supportSQLiteStatement.bindLong(1, (long) triggerEntity.id);
                supportSQLiteStatement.bindLong(2, (long) triggerEntity.triggerType);
                supportSQLiteStatement.bindDouble(3, triggerEntity.goal);
                String jsonPredicateToString = AutomationDao_Impl.this.__converters.jsonPredicateToString(triggerEntity.jsonPredicate);
                if (jsonPredicateToString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, jsonPredicateToString);
                }
                supportSQLiteStatement.bindLong(5, triggerEntity.isCancellation ? 1 : 0);
                supportSQLiteStatement.bindDouble(6, triggerEntity.progress);
                if (triggerEntity.parentScheduleId == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, triggerEntity.parentScheduleId);
                }
            }
        };
        this.__deletionAdapterOfScheduleEntity = new EntityDeletionOrUpdateAdapter<ScheduleEntity>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `schedules` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ScheduleEntity scheduleEntity) {
                supportSQLiteStatement.bindLong(1, (long) scheduleEntity.id);
            }
        };
        this.__updateAdapterOfScheduleEntity = new EntityDeletionOrUpdateAdapter<ScheduleEntity>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `schedules` SET `id` = ?,`scheduleId` = ?,`group` = ?,`metadata` = ?,`limit` = ?,`priority` = ?,`scheduleStart` = ?,`scheduleEnd` = ?,`editGracePeriod` = ?,`interval` = ?,`scheduleType` = ?,`data` = ?,`count` = ?,`executionState` = ?,`executionStateChangeDate` = ?,`triggerContext` = ?,`appState` = ?,`screens` = ?,`seconds` = ?,`regionId` = ?,`audience` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ScheduleEntity scheduleEntity) {
                supportSQLiteStatement.bindLong(1, (long) scheduleEntity.id);
                if (scheduleEntity.scheduleId == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, scheduleEntity.scheduleId);
                }
                if (scheduleEntity.group == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, scheduleEntity.group);
                }
                String jsonMapToString = AutomationDao_Impl.this.__converters.jsonMapToString(scheduleEntity.metadata);
                if (jsonMapToString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, jsonMapToString);
                }
                supportSQLiteStatement.bindLong(5, (long) scheduleEntity.limit);
                supportSQLiteStatement.bindLong(6, (long) scheduleEntity.priority);
                supportSQLiteStatement.bindLong(7, scheduleEntity.scheduleStart);
                supportSQLiteStatement.bindLong(8, scheduleEntity.scheduleEnd);
                supportSQLiteStatement.bindLong(9, scheduleEntity.editGracePeriod);
                supportSQLiteStatement.bindLong(10, scheduleEntity.interval);
                if (scheduleEntity.scheduleType == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, scheduleEntity.scheduleType);
                }
                String jsonValueToString = AutomationDao_Impl.this.__converters.jsonValueToString(scheduleEntity.data);
                if (jsonValueToString == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, jsonValueToString);
                }
                supportSQLiteStatement.bindLong(13, (long) scheduleEntity.count);
                supportSQLiteStatement.bindLong(14, (long) scheduleEntity.executionState);
                supportSQLiteStatement.bindLong(15, scheduleEntity.executionStateChangeDate);
                String triggerContextToString = AutomationDao_Impl.this.__converters.triggerContextToString(scheduleEntity.triggerContext);
                if (triggerContextToString == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, triggerContextToString);
                }
                supportSQLiteStatement.bindLong(17, (long) scheduleEntity.appState);
                String fromArrayList = Converters.fromArrayList(scheduleEntity.screens);
                if (fromArrayList == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, fromArrayList);
                }
                supportSQLiteStatement.bindLong(19, scheduleEntity.seconds);
                if (scheduleEntity.regionId == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, scheduleEntity.regionId);
                }
                String audienceToString = AutomationDao_Impl.this.__converters.audienceToString(scheduleEntity.audience);
                if (audienceToString == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, audienceToString);
                }
                supportSQLiteStatement.bindLong(22, (long) scheduleEntity.id);
            }
        };
        this.__updateAdapterOfTriggerEntity = new EntityDeletionOrUpdateAdapter<TriggerEntity>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `triggers` SET `id` = ?,`triggerType` = ?,`goal` = ?,`jsonPredicate` = ?,`isCancellation` = ?,`progress` = ?,`parentScheduleId` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, TriggerEntity triggerEntity) {
                supportSQLiteStatement.bindLong(1, (long) triggerEntity.id);
                supportSQLiteStatement.bindLong(2, (long) triggerEntity.triggerType);
                supportSQLiteStatement.bindDouble(3, triggerEntity.goal);
                String jsonPredicateToString = AutomationDao_Impl.this.__converters.jsonPredicateToString(triggerEntity.jsonPredicate);
                if (jsonPredicateToString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, jsonPredicateToString);
                }
                supportSQLiteStatement.bindLong(5, triggerEntity.isCancellation ? 1 : 0);
                supportSQLiteStatement.bindDouble(6, triggerEntity.progress);
                if (triggerEntity.parentScheduleId == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, triggerEntity.parentScheduleId);
                }
                supportSQLiteStatement.bindLong(8, (long) triggerEntity.id);
            }
        };
        this.__preparedStmtOfDeleteSchedulesByType = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM schedules WHERE scheduleType = ?";
            }
        };
    }

    public void insert(ScheduleEntity scheduleEntity, List<TriggerEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfScheduleEntity.insert(scheduleEntity);
            this.__insertionAdapterOfTriggerEntity.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(ScheduleEntity scheduleEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfScheduleEntity.handle(scheduleEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(ScheduleEntity scheduleEntity, List<TriggerEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfScheduleEntity.handle(scheduleEntity);
            this.__updateAdapterOfTriggerEntity.handleMultiple(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void updateTriggers(List<TriggerEntity> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfTriggerEntity.handleMultiple(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(Collection<FullSchedule> collection) {
        this.__db.beginTransaction();
        try {
            super.insert(collection);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    public void deleteSchedulesByType(int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteSchedulesByType.acquire();
        acquire.bindLong(1, (long) i);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteSchedulesByType.release(acquire);
        }
    }

    public int getScheduleCount() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM schedules", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x02bc A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x02a5 A[Catch:{ all -> 0x02fd }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02b2 A[Catch:{ all -> 0x02fd }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x02b7 A[Catch:{ all -> 0x02fd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.urbanairship.automation.storage.FullSchedule> getSchedules() {
        /*
            r31 = this;
            r1 = r31
            java.lang.String r0 = "SELECT * FROM schedules"
            r2 = 0
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r0, r2)
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x0309 }
            r3 = 1
            r4 = 0
            android.database.Cursor r3 = androidx.room.util.DBUtil.query(r0, r2, r3, r4)     // Catch:{ all -> 0x0309 }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r0)     // Catch:{ all -> 0x02ff }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r5)     // Catch:{ all -> 0x02ff }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r6)     // Catch:{ all -> 0x02ff }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r7)     // Catch:{ all -> 0x02ff }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r8)     // Catch:{ all -> 0x02ff }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r9)     // Catch:{ all -> 0x02ff }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r10)     // Catch:{ all -> 0x02ff }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r11)     // Catch:{ all -> 0x02ff }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r12)     // Catch:{ all -> 0x02ff }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r13)     // Catch:{ all -> 0x02ff }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r14)     // Catch:{ all -> 0x02ff }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r15)     // Catch:{ all -> 0x02ff }
            java.lang.String r4 = "count"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r4)     // Catch:{ all -> 0x02ff }
            r16 = r2
            java.lang.String r2 = "executionState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r17 = r2
            java.lang.String r2 = "executionStateChangeDate"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r18 = r2
            java.lang.String r2 = "triggerContext"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r19 = r2
            java.lang.String r2 = "appState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r20 = r2
            java.lang.String r2 = "screens"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r21 = r2
            java.lang.String r2 = "seconds"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r22 = r2
            java.lang.String r2 = "regionId"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r23 = r2
            java.lang.String r2 = "audience"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r24 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x02fd }
            r2.<init>()     // Catch:{ all -> 0x02fd }
        L_0x00b0:
            boolean r25 = r3.moveToNext()     // Catch:{ all -> 0x02fd }
            if (r25 == 0) goto L_0x00dc
            boolean r25 = r3.isNull(r5)     // Catch:{ all -> 0x02fd }
            if (r25 != 0) goto L_0x00b0
            r25 = r4
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x02fd }
            java.lang.Object r26 = r2.get(r4)     // Catch:{ all -> 0x02fd }
            java.util.ArrayList r26 = (java.util.ArrayList) r26     // Catch:{ all -> 0x02fd }
            if (r26 != 0) goto L_0x00d5
            r26 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x02fd }
            r15.<init>()     // Catch:{ all -> 0x02fd }
            r2.put(r4, r15)     // Catch:{ all -> 0x02fd }
            goto L_0x00d7
        L_0x00d5:
            r26 = r15
        L_0x00d7:
            r4 = r25
            r15 = r26
            goto L_0x00b0
        L_0x00dc:
            r25 = r4
            r26 = r15
            r4 = -1
            r3.moveToPosition(r4)     // Catch:{ all -> 0x02fd }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r2)     // Catch:{ all -> 0x02fd }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x02fd }
            int r15 = r3.getCount()     // Catch:{ all -> 0x02fd }
            r4.<init>(r15)     // Catch:{ all -> 0x02fd }
        L_0x00f0:
            boolean r15 = r3.moveToNext()     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x02eb
            boolean r15 = r3.isNull(r0)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r5)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r6)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r7)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r8)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r9)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r10)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r11)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r12)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r13)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r14)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            r15 = r26
            boolean r26 = r3.isNull(r15)     // Catch:{ all -> 0x02fd }
            if (r26 == 0) goto L_0x01d0
            r26 = r4
            r4 = r25
            boolean r25 = r3.isNull(r4)     // Catch:{ all -> 0x02fd }
            if (r25 == 0) goto L_0x01d4
            r25 = r2
            r2 = r17
            boolean r17 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r17 == 0) goto L_0x01cb
            r17 = r2
            r2 = r18
            boolean r18 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r18 == 0) goto L_0x01c8
            r18 = r2
            r2 = r19
            boolean r19 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r19 == 0) goto L_0x01c5
            r19 = r2
            r2 = r20
            boolean r20 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r20 == 0) goto L_0x01c2
            r20 = r2
            r2 = r21
            boolean r21 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r21 == 0) goto L_0x01bf
            r21 = r2
            r2 = r22
            boolean r22 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r22 == 0) goto L_0x01bc
            r22 = r2
            r2 = r23
            boolean r23 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r23 == 0) goto L_0x01b9
            r23 = r2
            r2 = r24
            boolean r24 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r24 != 0) goto L_0x019d
            r24 = r2
            goto L_0x01d6
        L_0x019d:
            r28 = r0
            r27 = r4
            r24 = r7
            r7 = r2
            r2 = 0
            r29 = r17
            r17 = r6
            r6 = r23
            r23 = r22
            r22 = r21
            r21 = r20
            r20 = r19
            r19 = r18
            r18 = r29
            goto L_0x029f
        L_0x01b9:
            r23 = r2
            goto L_0x01d6
        L_0x01bc:
            r22 = r2
            goto L_0x01d6
        L_0x01bf:
            r21 = r2
            goto L_0x01d6
        L_0x01c2:
            r20 = r2
            goto L_0x01d6
        L_0x01c5:
            r19 = r2
            goto L_0x01d6
        L_0x01c8:
            r18 = r2
            goto L_0x01d6
        L_0x01cb:
            r17 = r2
            goto L_0x01d6
        L_0x01ce:
            r15 = r26
        L_0x01d0:
            r26 = r4
            r4 = r25
        L_0x01d4:
            r25 = r2
        L_0x01d6:
            com.urbanairship.automation.storage.ScheduleEntity r2 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x02fd }
            r2.<init>()     // Catch:{ all -> 0x02fd }
            r27 = r4
            int r4 = r3.getInt(r0)     // Catch:{ all -> 0x02fd }
            r2.id = r4     // Catch:{ all -> 0x02fd }
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x02fd }
            r2.scheduleId = r4     // Catch:{ all -> 0x02fd }
            java.lang.String r4 = r3.getString(r6)     // Catch:{ all -> 0x02fd }
            r2.group = r4     // Catch:{ all -> 0x02fd }
            java.lang.String r4 = r3.getString(r7)     // Catch:{ all -> 0x02fd }
            r28 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x02fd }
            com.urbanairship.json.JsonMap r0 = r0.jsonMapFromString(r4)     // Catch:{ all -> 0x02fd }
            r2.metadata = r0     // Catch:{ all -> 0x02fd }
            int r0 = r3.getInt(r8)     // Catch:{ all -> 0x02fd }
            r2.limit = r0     // Catch:{ all -> 0x02fd }
            int r0 = r3.getInt(r9)     // Catch:{ all -> 0x02fd }
            r2.priority = r0     // Catch:{ all -> 0x02fd }
            r0 = r6
            r4 = r7
            long r6 = r3.getLong(r10)     // Catch:{ all -> 0x02fd }
            r2.scheduleStart = r6     // Catch:{ all -> 0x02fd }
            long r6 = r3.getLong(r11)     // Catch:{ all -> 0x02fd }
            r2.scheduleEnd = r6     // Catch:{ all -> 0x02fd }
            long r6 = r3.getLong(r12)     // Catch:{ all -> 0x02fd }
            r2.editGracePeriod = r6     // Catch:{ all -> 0x02fd }
            long r6 = r3.getLong(r13)     // Catch:{ all -> 0x02fd }
            r2.interval = r6     // Catch:{ all -> 0x02fd }
            java.lang.String r6 = r3.getString(r14)     // Catch:{ all -> 0x02fd }
            r2.scheduleType = r6     // Catch:{ all -> 0x02fd }
            java.lang.String r6 = r3.getString(r15)     // Catch:{ all -> 0x02fd }
            com.urbanairship.automation.storage.Converters r7 = r1.__converters     // Catch:{ all -> 0x02fd }
            com.urbanairship.json.JsonValue r6 = r7.jsonValueFromString(r6)     // Catch:{ all -> 0x02fd }
            r2.data = r6     // Catch:{ all -> 0x02fd }
            r6 = r27
            int r7 = r3.getInt(r6)     // Catch:{ all -> 0x02fd }
            r2.count = r7     // Catch:{ all -> 0x02fd }
            r7 = r17
            r17 = r0
            int r0 = r3.getInt(r7)     // Catch:{ all -> 0x02fd }
            r2.executionState = r0     // Catch:{ all -> 0x02fd }
            r27 = r6
            r0 = r18
            r18 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x02fd }
            r2.executionStateChangeDate = r6     // Catch:{ all -> 0x02fd }
            r6 = r19
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x02fd }
            r19 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x02fd }
            com.urbanairship.automation.TriggerContext r0 = r0.triggerContextFromString(r7)     // Catch:{ all -> 0x02fd }
            r2.triggerContext = r0     // Catch:{ all -> 0x02fd }
            r0 = r20
            int r7 = r3.getInt(r0)     // Catch:{ all -> 0x02fd }
            r2.appState = r7     // Catch:{ all -> 0x02fd }
            r7 = r21
            java.lang.String r20 = r3.getString(r7)     // Catch:{ all -> 0x02fd }
            r21 = r0
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r20)     // Catch:{ all -> 0x02fd }
            r2.screens = r0     // Catch:{ all -> 0x02fd }
            r20 = r6
            r0 = r22
            r22 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x02fd }
            r2.seconds = r6     // Catch:{ all -> 0x02fd }
            r6 = r23
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x02fd }
            r2.regionId = r7     // Catch:{ all -> 0x02fd }
            r23 = r0
            r7 = r24
            java.lang.String r0 = r3.getString(r7)     // Catch:{ all -> 0x02fd }
            r24 = r4
            com.urbanairship.automation.storage.Converters r4 = r1.__converters     // Catch:{ all -> 0x02fd }
            com.urbanairship.automation.Audience r0 = r4.audienceFromString(r0)     // Catch:{ all -> 0x02fd }
            r2.audience = r0     // Catch:{ all -> 0x02fd }
        L_0x029f:
            boolean r0 = r3.isNull(r5)     // Catch:{ all -> 0x02fd }
            if (r0 != 0) goto L_0x02b2
            java.lang.String r0 = r3.getString(r5)     // Catch:{ all -> 0x02fd }
            r4 = r25
            java.lang.Object r0 = r4.get(r0)     // Catch:{ all -> 0x02fd }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x02fd }
            goto L_0x02b5
        L_0x02b2:
            r4 = r25
            r0 = 0
        L_0x02b5:
            if (r0 != 0) goto L_0x02bc
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x02fd }
            r0.<init>()     // Catch:{ all -> 0x02fd }
        L_0x02bc:
            r25 = r4
            com.urbanairship.automation.storage.FullSchedule r4 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x02fd }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x02fd }
            r0 = r26
            r0.add(r4)     // Catch:{ all -> 0x02fd }
            r4 = r0
            r26 = r15
            r2 = r25
            r25 = r27
            r0 = r28
            r29 = r23
            r23 = r6
            r6 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r29
            r30 = r24
            r24 = r7
            r7 = r30
            goto L_0x00f0
        L_0x02eb:
            r0 = r4
            androidx.room.RoomDatabase r2 = r1.__db     // Catch:{ all -> 0x02fd }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x02fd }
            r3.close()     // Catch:{ all -> 0x0309 }
            r16.release()     // Catch:{ all -> 0x0309 }
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            return r0
        L_0x02fd:
            r0 = move-exception
            goto L_0x0302
        L_0x02ff:
            r0 = move-exception
            r16 = r2
        L_0x0302:
            r3.close()     // Catch:{ all -> 0x0309 }
            r16.release()     // Catch:{ all -> 0x0309 }
            throw r0     // Catch:{ all -> 0x0309 }
        L_0x0309:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedules():java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x02c6 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02af A[Catch:{ all -> 0x0307 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x02bc A[Catch:{ all -> 0x0307 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02c1 A[Catch:{ all -> 0x0307 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.urbanairship.automation.storage.FullSchedule> getSchedulesByType(java.lang.String r32) {
        /*
            r31 = this;
            r1 = r31
            r0 = r32
            java.lang.String r2 = "SELECT * FROM schedules WHERE (scheduleType = ?)"
            r3 = 1
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r2, r3)
            if (r0 != 0) goto L_0x0011
            r2.bindNull(r3)
            goto L_0x0014
        L_0x0011:
            r2.bindString(r3, r0)
        L_0x0014:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x0313 }
            r4 = 0
            android.database.Cursor r3 = androidx.room.util.DBUtil.query(r0, r2, r3, r4)     // Catch:{ all -> 0x0313 }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r0)     // Catch:{ all -> 0x0309 }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r5)     // Catch:{ all -> 0x0309 }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r6)     // Catch:{ all -> 0x0309 }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r7)     // Catch:{ all -> 0x0309 }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r8)     // Catch:{ all -> 0x0309 }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r9)     // Catch:{ all -> 0x0309 }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r10)     // Catch:{ all -> 0x0309 }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r11)     // Catch:{ all -> 0x0309 }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r12)     // Catch:{ all -> 0x0309 }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r13)     // Catch:{ all -> 0x0309 }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r14)     // Catch:{ all -> 0x0309 }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r15)     // Catch:{ all -> 0x0309 }
            java.lang.String r4 = "count"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r4)     // Catch:{ all -> 0x0309 }
            r16 = r2
            java.lang.String r2 = "executionState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r17 = r2
            java.lang.String r2 = "executionStateChangeDate"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r18 = r2
            java.lang.String r2 = "triggerContext"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r19 = r2
            java.lang.String r2 = "appState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r20 = r2
            java.lang.String r2 = "screens"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r21 = r2
            java.lang.String r2 = "seconds"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r22 = r2
            java.lang.String r2 = "regionId"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r23 = r2
            java.lang.String r2 = "audience"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r24 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x0307 }
            r2.<init>()     // Catch:{ all -> 0x0307 }
        L_0x00ba:
            boolean r25 = r3.moveToNext()     // Catch:{ all -> 0x0307 }
            if (r25 == 0) goto L_0x00e6
            boolean r25 = r3.isNull(r5)     // Catch:{ all -> 0x0307 }
            if (r25 != 0) goto L_0x00ba
            r25 = r4
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x0307 }
            java.lang.Object r26 = r2.get(r4)     // Catch:{ all -> 0x0307 }
            java.util.ArrayList r26 = (java.util.ArrayList) r26     // Catch:{ all -> 0x0307 }
            if (r26 != 0) goto L_0x00df
            r26 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0307 }
            r15.<init>()     // Catch:{ all -> 0x0307 }
            r2.put(r4, r15)     // Catch:{ all -> 0x0307 }
            goto L_0x00e1
        L_0x00df:
            r26 = r15
        L_0x00e1:
            r4 = r25
            r15 = r26
            goto L_0x00ba
        L_0x00e6:
            r25 = r4
            r26 = r15
            r4 = -1
            r3.moveToPosition(r4)     // Catch:{ all -> 0x0307 }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r2)     // Catch:{ all -> 0x0307 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0307 }
            int r15 = r3.getCount()     // Catch:{ all -> 0x0307 }
            r4.<init>(r15)     // Catch:{ all -> 0x0307 }
        L_0x00fa:
            boolean r15 = r3.moveToNext()     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x02f5
            boolean r15 = r3.isNull(r0)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r5)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r6)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r7)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r8)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r9)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r10)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r11)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r12)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r13)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r14)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            r15 = r26
            boolean r26 = r3.isNull(r15)     // Catch:{ all -> 0x0307 }
            if (r26 == 0) goto L_0x01da
            r26 = r4
            r4 = r25
            boolean r25 = r3.isNull(r4)     // Catch:{ all -> 0x0307 }
            if (r25 == 0) goto L_0x01de
            r25 = r2
            r2 = r17
            boolean r17 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r17 == 0) goto L_0x01d5
            r17 = r2
            r2 = r18
            boolean r18 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r18 == 0) goto L_0x01d2
            r18 = r2
            r2 = r19
            boolean r19 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r19 == 0) goto L_0x01cf
            r19 = r2
            r2 = r20
            boolean r20 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r20 == 0) goto L_0x01cc
            r20 = r2
            r2 = r21
            boolean r21 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r21 == 0) goto L_0x01c9
            r21 = r2
            r2 = r22
            boolean r22 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r22 == 0) goto L_0x01c6
            r22 = r2
            r2 = r23
            boolean r23 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r23 == 0) goto L_0x01c3
            r23 = r2
            r2 = r24
            boolean r24 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r24 != 0) goto L_0x01a7
            r24 = r2
            goto L_0x01e0
        L_0x01a7:
            r28 = r0
            r27 = r4
            r24 = r7
            r7 = r2
            r2 = 0
            r29 = r17
            r17 = r6
            r6 = r23
            r23 = r22
            r22 = r21
            r21 = r20
            r20 = r19
            r19 = r18
            r18 = r29
            goto L_0x02a9
        L_0x01c3:
            r23 = r2
            goto L_0x01e0
        L_0x01c6:
            r22 = r2
            goto L_0x01e0
        L_0x01c9:
            r21 = r2
            goto L_0x01e0
        L_0x01cc:
            r20 = r2
            goto L_0x01e0
        L_0x01cf:
            r19 = r2
            goto L_0x01e0
        L_0x01d2:
            r18 = r2
            goto L_0x01e0
        L_0x01d5:
            r17 = r2
            goto L_0x01e0
        L_0x01d8:
            r15 = r26
        L_0x01da:
            r26 = r4
            r4 = r25
        L_0x01de:
            r25 = r2
        L_0x01e0:
            com.urbanairship.automation.storage.ScheduleEntity r2 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x0307 }
            r2.<init>()     // Catch:{ all -> 0x0307 }
            r27 = r4
            int r4 = r3.getInt(r0)     // Catch:{ all -> 0x0307 }
            r2.id = r4     // Catch:{ all -> 0x0307 }
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x0307 }
            r2.scheduleId = r4     // Catch:{ all -> 0x0307 }
            java.lang.String r4 = r3.getString(r6)     // Catch:{ all -> 0x0307 }
            r2.group = r4     // Catch:{ all -> 0x0307 }
            java.lang.String r4 = r3.getString(r7)     // Catch:{ all -> 0x0307 }
            r28 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x0307 }
            com.urbanairship.json.JsonMap r0 = r0.jsonMapFromString(r4)     // Catch:{ all -> 0x0307 }
            r2.metadata = r0     // Catch:{ all -> 0x0307 }
            int r0 = r3.getInt(r8)     // Catch:{ all -> 0x0307 }
            r2.limit = r0     // Catch:{ all -> 0x0307 }
            int r0 = r3.getInt(r9)     // Catch:{ all -> 0x0307 }
            r2.priority = r0     // Catch:{ all -> 0x0307 }
            r0 = r6
            r4 = r7
            long r6 = r3.getLong(r10)     // Catch:{ all -> 0x0307 }
            r2.scheduleStart = r6     // Catch:{ all -> 0x0307 }
            long r6 = r3.getLong(r11)     // Catch:{ all -> 0x0307 }
            r2.scheduleEnd = r6     // Catch:{ all -> 0x0307 }
            long r6 = r3.getLong(r12)     // Catch:{ all -> 0x0307 }
            r2.editGracePeriod = r6     // Catch:{ all -> 0x0307 }
            long r6 = r3.getLong(r13)     // Catch:{ all -> 0x0307 }
            r2.interval = r6     // Catch:{ all -> 0x0307 }
            java.lang.String r6 = r3.getString(r14)     // Catch:{ all -> 0x0307 }
            r2.scheduleType = r6     // Catch:{ all -> 0x0307 }
            java.lang.String r6 = r3.getString(r15)     // Catch:{ all -> 0x0307 }
            com.urbanairship.automation.storage.Converters r7 = r1.__converters     // Catch:{ all -> 0x0307 }
            com.urbanairship.json.JsonValue r6 = r7.jsonValueFromString(r6)     // Catch:{ all -> 0x0307 }
            r2.data = r6     // Catch:{ all -> 0x0307 }
            r6 = r27
            int r7 = r3.getInt(r6)     // Catch:{ all -> 0x0307 }
            r2.count = r7     // Catch:{ all -> 0x0307 }
            r7 = r17
            r17 = r0
            int r0 = r3.getInt(r7)     // Catch:{ all -> 0x0307 }
            r2.executionState = r0     // Catch:{ all -> 0x0307 }
            r27 = r6
            r0 = r18
            r18 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x0307 }
            r2.executionStateChangeDate = r6     // Catch:{ all -> 0x0307 }
            r6 = r19
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x0307 }
            r19 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x0307 }
            com.urbanairship.automation.TriggerContext r0 = r0.triggerContextFromString(r7)     // Catch:{ all -> 0x0307 }
            r2.triggerContext = r0     // Catch:{ all -> 0x0307 }
            r0 = r20
            int r7 = r3.getInt(r0)     // Catch:{ all -> 0x0307 }
            r2.appState = r7     // Catch:{ all -> 0x0307 }
            r7 = r21
            java.lang.String r20 = r3.getString(r7)     // Catch:{ all -> 0x0307 }
            r21 = r0
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r20)     // Catch:{ all -> 0x0307 }
            r2.screens = r0     // Catch:{ all -> 0x0307 }
            r20 = r6
            r0 = r22
            r22 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x0307 }
            r2.seconds = r6     // Catch:{ all -> 0x0307 }
            r6 = r23
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x0307 }
            r2.regionId = r7     // Catch:{ all -> 0x0307 }
            r23 = r0
            r7 = r24
            java.lang.String r0 = r3.getString(r7)     // Catch:{ all -> 0x0307 }
            r24 = r4
            com.urbanairship.automation.storage.Converters r4 = r1.__converters     // Catch:{ all -> 0x0307 }
            com.urbanairship.automation.Audience r0 = r4.audienceFromString(r0)     // Catch:{ all -> 0x0307 }
            r2.audience = r0     // Catch:{ all -> 0x0307 }
        L_0x02a9:
            boolean r0 = r3.isNull(r5)     // Catch:{ all -> 0x0307 }
            if (r0 != 0) goto L_0x02bc
            java.lang.String r0 = r3.getString(r5)     // Catch:{ all -> 0x0307 }
            r4 = r25
            java.lang.Object r0 = r4.get(r0)     // Catch:{ all -> 0x0307 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0307 }
            goto L_0x02bf
        L_0x02bc:
            r4 = r25
            r0 = 0
        L_0x02bf:
            if (r0 != 0) goto L_0x02c6
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0307 }
            r0.<init>()     // Catch:{ all -> 0x0307 }
        L_0x02c6:
            r25 = r4
            com.urbanairship.automation.storage.FullSchedule r4 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x0307 }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x0307 }
            r0 = r26
            r0.add(r4)     // Catch:{ all -> 0x0307 }
            r4 = r0
            r26 = r15
            r2 = r25
            r25 = r27
            r0 = r28
            r29 = r23
            r23 = r6
            r6 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r29
            r30 = r24
            r24 = r7
            r7 = r30
            goto L_0x00fa
        L_0x02f5:
            r0 = r4
            androidx.room.RoomDatabase r2 = r1.__db     // Catch:{ all -> 0x0307 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0307 }
            r3.close()     // Catch:{ all -> 0x0313 }
            r16.release()     // Catch:{ all -> 0x0313 }
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            return r0
        L_0x0307:
            r0 = move-exception
            goto L_0x030c
        L_0x0309:
            r0 = move-exception
            r16 = r2
        L_0x030c:
            r3.close()     // Catch:{ all -> 0x0313 }
            r16.release()     // Catch:{ all -> 0x0313 }
            throw r0     // Catch:{ all -> 0x0313 }
        L_0x0313:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedulesByType(java.lang.String):java.util.List");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: java.util.ArrayList} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0272 A[Catch:{ all -> 0x02a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0280 A[Catch:{ all -> 0x02a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0283 A[Catch:{ all -> 0x02a1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.urbanairship.automation.storage.FullSchedule getSchedule(java.lang.String r28) {
        /*
            r27 = this;
            r1 = r27
            r0 = r28
            java.lang.String r2 = "SELECT * FROM schedules WHERE (scheduleId == ?)"
            r3 = 1
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r2, r3)
            if (r0 != 0) goto L_0x0011
            r2.bindNull(r3)
            goto L_0x0014
        L_0x0011:
            r2.bindString(r3, r0)
        L_0x0014:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x02ad }
            r4 = 0
            android.database.Cursor r3 = androidx.room.util.DBUtil.query(r0, r2, r3, r4)     // Catch:{ all -> 0x02ad }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r0)     // Catch:{ all -> 0x02a3 }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r5)     // Catch:{ all -> 0x02a3 }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r6)     // Catch:{ all -> 0x02a3 }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r7)     // Catch:{ all -> 0x02a3 }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r8)     // Catch:{ all -> 0x02a3 }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r9)     // Catch:{ all -> 0x02a3 }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r10)     // Catch:{ all -> 0x02a3 }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r11)     // Catch:{ all -> 0x02a3 }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r12)     // Catch:{ all -> 0x02a3 }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r13)     // Catch:{ all -> 0x02a3 }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r14)     // Catch:{ all -> 0x02a3 }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r15)     // Catch:{ all -> 0x02a3 }
            java.lang.String r4 = "count"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r4)     // Catch:{ all -> 0x02a3 }
            r16 = r2
            java.lang.String r2 = "executionState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02a1 }
            r17 = r2
            java.lang.String r2 = "executionStateChangeDate"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02a1 }
            r18 = r2
            java.lang.String r2 = "triggerContext"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02a1 }
            r19 = r2
            java.lang.String r2 = "appState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02a1 }
            r20 = r2
            java.lang.String r2 = "screens"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02a1 }
            r21 = r2
            java.lang.String r2 = "seconds"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02a1 }
            r22 = r2
            java.lang.String r2 = "regionId"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02a1 }
            r23 = r2
            java.lang.String r2 = "audience"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02a1 }
            r24 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x02a1 }
            r2.<init>()     // Catch:{ all -> 0x02a1 }
        L_0x00ba:
            boolean r25 = r3.moveToNext()     // Catch:{ all -> 0x02a1 }
            if (r25 == 0) goto L_0x00e6
            boolean r25 = r3.isNull(r5)     // Catch:{ all -> 0x02a1 }
            if (r25 != 0) goto L_0x00ba
            r25 = r4
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x02a1 }
            java.lang.Object r26 = r2.get(r4)     // Catch:{ all -> 0x02a1 }
            java.util.ArrayList r26 = (java.util.ArrayList) r26     // Catch:{ all -> 0x02a1 }
            if (r26 != 0) goto L_0x00df
            r26 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x02a1 }
            r15.<init>()     // Catch:{ all -> 0x02a1 }
            r2.put(r4, r15)     // Catch:{ all -> 0x02a1 }
            goto L_0x00e1
        L_0x00df:
            r26 = r15
        L_0x00e1:
            r4 = r25
            r15 = r26
            goto L_0x00ba
        L_0x00e6:
            r25 = r4
            r26 = r15
            r4 = -1
            r3.moveToPosition(r4)     // Catch:{ all -> 0x02a1 }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r2)     // Catch:{ all -> 0x02a1 }
            boolean r4 = r3.moveToFirst()     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x028f
            boolean r4 = r3.isNull(r0)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r5)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r6)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r7)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r8)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r9)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r10)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r11)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r12)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r13)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            boolean r4 = r3.isNull(r14)     // Catch:{ all -> 0x02a1 }
            if (r4 == 0) goto L_0x01b7
            r4 = r26
            boolean r15 = r3.isNull(r4)     // Catch:{ all -> 0x02a1 }
            if (r15 == 0) goto L_0x01b4
            r15 = r25
            boolean r25 = r3.isNull(r15)     // Catch:{ all -> 0x02a1 }
            if (r25 == 0) goto L_0x01bb
            r25 = r2
            r2 = r17
            boolean r17 = r3.isNull(r2)     // Catch:{ all -> 0x02a1 }
            if (r17 == 0) goto L_0x01b1
            r17 = r2
            r2 = r18
            boolean r18 = r3.isNull(r2)     // Catch:{ all -> 0x02a1 }
            if (r18 == 0) goto L_0x01ae
            r18 = r2
            r2 = r19
            boolean r19 = r3.isNull(r2)     // Catch:{ all -> 0x02a1 }
            if (r19 == 0) goto L_0x01ab
            r19 = r2
            r2 = r20
            boolean r20 = r3.isNull(r2)     // Catch:{ all -> 0x02a1 }
            if (r20 == 0) goto L_0x01a8
            r20 = r2
            r2 = r21
            boolean r21 = r3.isNull(r2)     // Catch:{ all -> 0x02a1 }
            if (r21 == 0) goto L_0x01a5
            r21 = r2
            r2 = r22
            boolean r22 = r3.isNull(r2)     // Catch:{ all -> 0x02a1 }
            if (r22 == 0) goto L_0x01a2
            r22 = r2
            r2 = r23
            boolean r23 = r3.isNull(r2)     // Catch:{ all -> 0x02a1 }
            if (r23 == 0) goto L_0x019f
            r23 = r2
            r2 = r24
            boolean r24 = r3.isNull(r2)     // Catch:{ all -> 0x02a1 }
            if (r24 != 0) goto L_0x019c
            r24 = r2
            goto L_0x01bd
        L_0x019c:
            r2 = 0
            goto L_0x026c
        L_0x019f:
            r23 = r2
            goto L_0x01bd
        L_0x01a2:
            r22 = r2
            goto L_0x01bd
        L_0x01a5:
            r21 = r2
            goto L_0x01bd
        L_0x01a8:
            r20 = r2
            goto L_0x01bd
        L_0x01ab:
            r19 = r2
            goto L_0x01bd
        L_0x01ae:
            r18 = r2
            goto L_0x01bd
        L_0x01b1:
            r17 = r2
            goto L_0x01bd
        L_0x01b4:
            r15 = r25
            goto L_0x01bb
        L_0x01b7:
            r15 = r25
            r4 = r26
        L_0x01bb:
            r25 = r2
        L_0x01bd:
            com.urbanairship.automation.storage.ScheduleEntity r2 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x02a1 }
            r2.<init>()     // Catch:{ all -> 0x02a1 }
            int r0 = r3.getInt(r0)     // Catch:{ all -> 0x02a1 }
            r2.id = r0     // Catch:{ all -> 0x02a1 }
            java.lang.String r0 = r3.getString(r5)     // Catch:{ all -> 0x02a1 }
            r2.scheduleId = r0     // Catch:{ all -> 0x02a1 }
            java.lang.String r0 = r3.getString(r6)     // Catch:{ all -> 0x02a1 }
            r2.group = r0     // Catch:{ all -> 0x02a1 }
            java.lang.String r0 = r3.getString(r7)     // Catch:{ all -> 0x02a1 }
            com.urbanairship.automation.storage.Converters r6 = r1.__converters     // Catch:{ all -> 0x02a1 }
            com.urbanairship.json.JsonMap r0 = r6.jsonMapFromString(r0)     // Catch:{ all -> 0x02a1 }
            r2.metadata = r0     // Catch:{ all -> 0x02a1 }
            int r0 = r3.getInt(r8)     // Catch:{ all -> 0x02a1 }
            r2.limit = r0     // Catch:{ all -> 0x02a1 }
            int r0 = r3.getInt(r9)     // Catch:{ all -> 0x02a1 }
            r2.priority = r0     // Catch:{ all -> 0x02a1 }
            long r6 = r3.getLong(r10)     // Catch:{ all -> 0x02a1 }
            r2.scheduleStart = r6     // Catch:{ all -> 0x02a1 }
            long r6 = r3.getLong(r11)     // Catch:{ all -> 0x02a1 }
            r2.scheduleEnd = r6     // Catch:{ all -> 0x02a1 }
            long r6 = r3.getLong(r12)     // Catch:{ all -> 0x02a1 }
            r2.editGracePeriod = r6     // Catch:{ all -> 0x02a1 }
            long r6 = r3.getLong(r13)     // Catch:{ all -> 0x02a1 }
            r2.interval = r6     // Catch:{ all -> 0x02a1 }
            java.lang.String r0 = r3.getString(r14)     // Catch:{ all -> 0x02a1 }
            r2.scheduleType = r0     // Catch:{ all -> 0x02a1 }
            java.lang.String r0 = r3.getString(r4)     // Catch:{ all -> 0x02a1 }
            com.urbanairship.automation.storage.Converters r4 = r1.__converters     // Catch:{ all -> 0x02a1 }
            com.urbanairship.json.JsonValue r0 = r4.jsonValueFromString(r0)     // Catch:{ all -> 0x02a1 }
            r2.data = r0     // Catch:{ all -> 0x02a1 }
            int r0 = r3.getInt(r15)     // Catch:{ all -> 0x02a1 }
            r2.count = r0     // Catch:{ all -> 0x02a1 }
            r0 = r17
            int r0 = r3.getInt(r0)     // Catch:{ all -> 0x02a1 }
            r2.executionState = r0     // Catch:{ all -> 0x02a1 }
            r0 = r18
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x02a1 }
            r2.executionStateChangeDate = r6     // Catch:{ all -> 0x02a1 }
            r0 = r19
            java.lang.String r0 = r3.getString(r0)     // Catch:{ all -> 0x02a1 }
            com.urbanairship.automation.storage.Converters r4 = r1.__converters     // Catch:{ all -> 0x02a1 }
            com.urbanairship.automation.TriggerContext r0 = r4.triggerContextFromString(r0)     // Catch:{ all -> 0x02a1 }
            r2.triggerContext = r0     // Catch:{ all -> 0x02a1 }
            r0 = r20
            int r0 = r3.getInt(r0)     // Catch:{ all -> 0x02a1 }
            r2.appState = r0     // Catch:{ all -> 0x02a1 }
            r0 = r21
            java.lang.String r0 = r3.getString(r0)     // Catch:{ all -> 0x02a1 }
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r0)     // Catch:{ all -> 0x02a1 }
            r2.screens = r0     // Catch:{ all -> 0x02a1 }
            r0 = r22
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x02a1 }
            r2.seconds = r6     // Catch:{ all -> 0x02a1 }
            r0 = r23
            java.lang.String r0 = r3.getString(r0)     // Catch:{ all -> 0x02a1 }
            r2.regionId = r0     // Catch:{ all -> 0x02a1 }
            r0 = r24
            java.lang.String r0 = r3.getString(r0)     // Catch:{ all -> 0x02a1 }
            com.urbanairship.automation.storage.Converters r4 = r1.__converters     // Catch:{ all -> 0x02a1 }
            com.urbanairship.automation.Audience r0 = r4.audienceFromString(r0)     // Catch:{ all -> 0x02a1 }
            r2.audience = r0     // Catch:{ all -> 0x02a1 }
        L_0x026c:
            boolean r0 = r3.isNull(r5)     // Catch:{ all -> 0x02a1 }
            if (r0 != 0) goto L_0x0280
            java.lang.String r0 = r3.getString(r5)     // Catch:{ all -> 0x02a1 }
            r4 = r25
            java.lang.Object r0 = r4.get(r0)     // Catch:{ all -> 0x02a1 }
            r4 = r0
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ all -> 0x02a1 }
            goto L_0x0281
        L_0x0280:
            r4 = 0
        L_0x0281:
            if (r4 != 0) goto L_0x0288
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x02a1 }
            r4.<init>()     // Catch:{ all -> 0x02a1 }
        L_0x0288:
            com.urbanairship.automation.storage.FullSchedule r0 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x02a1 }
            r0.<init>(r2, r4)     // Catch:{ all -> 0x02a1 }
            r4 = r0
            goto L_0x0290
        L_0x028f:
            r4 = 0
        L_0x0290:
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x02a1 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x02a1 }
            r3.close()     // Catch:{ all -> 0x02ad }
            r16.release()     // Catch:{ all -> 0x02ad }
            androidx.room.RoomDatabase r0 = r1.__db
            r0.endTransaction()
            return r4
        L_0x02a1:
            r0 = move-exception
            goto L_0x02a6
        L_0x02a3:
            r0 = move-exception
            r16 = r2
        L_0x02a6:
            r3.close()     // Catch:{ all -> 0x02ad }
            r16.release()     // Catch:{ all -> 0x02ad }
            throw r0     // Catch:{ all -> 0x02ad }
        L_0x02ad:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedule(java.lang.String):com.urbanairship.automation.storage.FullSchedule");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: java.util.ArrayList} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x027e A[Catch:{ all -> 0x02ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x028c A[Catch:{ all -> 0x02ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x028f A[Catch:{ all -> 0x02ad }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.urbanairship.automation.storage.FullSchedule getSchedule(java.lang.String r27, java.lang.String r28) {
        /*
            r26 = this;
            r1 = r26
            r0 = r27
            r2 = r28
            java.lang.String r3 = "SELECT * FROM schedules WHERE (scheduleId == ?) AND (scheduleType = ?)"
            r4 = 2
            androidx.room.RoomSQLiteQuery r3 = androidx.room.RoomSQLiteQuery.acquire(r3, r4)
            r5 = 1
            if (r0 != 0) goto L_0x0014
            r3.bindNull(r5)
            goto L_0x0017
        L_0x0014:
            r3.bindString(r5, r0)
        L_0x0017:
            if (r2 != 0) goto L_0x001d
            r3.bindNull(r4)
            goto L_0x0020
        L_0x001d:
            r3.bindString(r4, r2)
        L_0x0020:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x02b9 }
            r2 = 0
            android.database.Cursor r4 = androidx.room.util.DBUtil.query(r0, r3, r5, r2)     // Catch:{ all -> 0x02b9 }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r0)     // Catch:{ all -> 0x02af }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r5)     // Catch:{ all -> 0x02af }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r6)     // Catch:{ all -> 0x02af }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r7)     // Catch:{ all -> 0x02af }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r8)     // Catch:{ all -> 0x02af }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r9)     // Catch:{ all -> 0x02af }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r10)     // Catch:{ all -> 0x02af }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r11)     // Catch:{ all -> 0x02af }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r12)     // Catch:{ all -> 0x02af }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r13)     // Catch:{ all -> 0x02af }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r14)     // Catch:{ all -> 0x02af }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r15)     // Catch:{ all -> 0x02af }
            java.lang.String r2 = "count"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x02af }
            r16 = r3
            java.lang.String r3 = "executionState"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x02ad }
            r28 = r3
            java.lang.String r3 = "executionStateChangeDate"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x02ad }
            r17 = r3
            java.lang.String r3 = "triggerContext"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x02ad }
            r18 = r3
            java.lang.String r3 = "appState"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x02ad }
            r19 = r3
            java.lang.String r3 = "screens"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x02ad }
            r20 = r3
            java.lang.String r3 = "seconds"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x02ad }
            r21 = r3
            java.lang.String r3 = "regionId"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x02ad }
            r22 = r3
            java.lang.String r3 = "audience"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x02ad }
            r23 = r3
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x02ad }
            r3.<init>()     // Catch:{ all -> 0x02ad }
        L_0x00c6:
            boolean r24 = r4.moveToNext()     // Catch:{ all -> 0x02ad }
            if (r24 == 0) goto L_0x00f2
            boolean r24 = r4.isNull(r5)     // Catch:{ all -> 0x02ad }
            if (r24 != 0) goto L_0x00c6
            r24 = r2
            java.lang.String r2 = r4.getString(r5)     // Catch:{ all -> 0x02ad }
            java.lang.Object r25 = r3.get(r2)     // Catch:{ all -> 0x02ad }
            java.util.ArrayList r25 = (java.util.ArrayList) r25     // Catch:{ all -> 0x02ad }
            if (r25 != 0) goto L_0x00eb
            r25 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x02ad }
            r15.<init>()     // Catch:{ all -> 0x02ad }
            r3.put(r2, r15)     // Catch:{ all -> 0x02ad }
            goto L_0x00ed
        L_0x00eb:
            r25 = r15
        L_0x00ed:
            r2 = r24
            r15 = r25
            goto L_0x00c6
        L_0x00f2:
            r24 = r2
            r25 = r15
            r2 = -1
            r4.moveToPosition(r2)     // Catch:{ all -> 0x02ad }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r3)     // Catch:{ all -> 0x02ad }
            boolean r2 = r4.moveToFirst()     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x029b
            boolean r2 = r4.isNull(r0)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r5)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r6)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r7)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r8)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r9)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r10)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r11)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r12)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r13)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            boolean r2 = r4.isNull(r14)     // Catch:{ all -> 0x02ad }
            if (r2 == 0) goto L_0x01c3
            r2 = r25
            boolean r15 = r4.isNull(r2)     // Catch:{ all -> 0x02ad }
            if (r15 == 0) goto L_0x01c0
            r15 = r24
            boolean r24 = r4.isNull(r15)     // Catch:{ all -> 0x02ad }
            if (r24 == 0) goto L_0x01c7
            r24 = r3
            r3 = r28
            boolean r25 = r4.isNull(r3)     // Catch:{ all -> 0x02ad }
            if (r25 == 0) goto L_0x01bd
            r28 = r3
            r3 = r17
            boolean r17 = r4.isNull(r3)     // Catch:{ all -> 0x02ad }
            if (r17 == 0) goto L_0x01ba
            r17 = r3
            r3 = r18
            boolean r18 = r4.isNull(r3)     // Catch:{ all -> 0x02ad }
            if (r18 == 0) goto L_0x01b7
            r18 = r3
            r3 = r19
            boolean r19 = r4.isNull(r3)     // Catch:{ all -> 0x02ad }
            if (r19 == 0) goto L_0x01b4
            r19 = r3
            r3 = r20
            boolean r20 = r4.isNull(r3)     // Catch:{ all -> 0x02ad }
            if (r20 == 0) goto L_0x01b1
            r20 = r3
            r3 = r21
            boolean r21 = r4.isNull(r3)     // Catch:{ all -> 0x02ad }
            if (r21 == 0) goto L_0x01ae
            r21 = r3
            r3 = r22
            boolean r22 = r4.isNull(r3)     // Catch:{ all -> 0x02ad }
            if (r22 == 0) goto L_0x01ab
            r22 = r3
            r3 = r23
            boolean r23 = r4.isNull(r3)     // Catch:{ all -> 0x02ad }
            if (r23 != 0) goto L_0x01a8
            r23 = r3
            goto L_0x01c9
        L_0x01a8:
            r3 = 0
            goto L_0x0278
        L_0x01ab:
            r22 = r3
            goto L_0x01c9
        L_0x01ae:
            r21 = r3
            goto L_0x01c9
        L_0x01b1:
            r20 = r3
            goto L_0x01c9
        L_0x01b4:
            r19 = r3
            goto L_0x01c9
        L_0x01b7:
            r18 = r3
            goto L_0x01c9
        L_0x01ba:
            r17 = r3
            goto L_0x01c9
        L_0x01bd:
            r28 = r3
            goto L_0x01c9
        L_0x01c0:
            r15 = r24
            goto L_0x01c7
        L_0x01c3:
            r15 = r24
            r2 = r25
        L_0x01c7:
            r24 = r3
        L_0x01c9:
            com.urbanairship.automation.storage.ScheduleEntity r3 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x02ad }
            r3.<init>()     // Catch:{ all -> 0x02ad }
            int r0 = r4.getInt(r0)     // Catch:{ all -> 0x02ad }
            r3.id = r0     // Catch:{ all -> 0x02ad }
            java.lang.String r0 = r4.getString(r5)     // Catch:{ all -> 0x02ad }
            r3.scheduleId = r0     // Catch:{ all -> 0x02ad }
            java.lang.String r0 = r4.getString(r6)     // Catch:{ all -> 0x02ad }
            r3.group = r0     // Catch:{ all -> 0x02ad }
            java.lang.String r0 = r4.getString(r7)     // Catch:{ all -> 0x02ad }
            com.urbanairship.automation.storage.Converters r6 = r1.__converters     // Catch:{ all -> 0x02ad }
            com.urbanairship.json.JsonMap r0 = r6.jsonMapFromString(r0)     // Catch:{ all -> 0x02ad }
            r3.metadata = r0     // Catch:{ all -> 0x02ad }
            int r0 = r4.getInt(r8)     // Catch:{ all -> 0x02ad }
            r3.limit = r0     // Catch:{ all -> 0x02ad }
            int r0 = r4.getInt(r9)     // Catch:{ all -> 0x02ad }
            r3.priority = r0     // Catch:{ all -> 0x02ad }
            long r6 = r4.getLong(r10)     // Catch:{ all -> 0x02ad }
            r3.scheduleStart = r6     // Catch:{ all -> 0x02ad }
            long r6 = r4.getLong(r11)     // Catch:{ all -> 0x02ad }
            r3.scheduleEnd = r6     // Catch:{ all -> 0x02ad }
            long r6 = r4.getLong(r12)     // Catch:{ all -> 0x02ad }
            r3.editGracePeriod = r6     // Catch:{ all -> 0x02ad }
            long r6 = r4.getLong(r13)     // Catch:{ all -> 0x02ad }
            r3.interval = r6     // Catch:{ all -> 0x02ad }
            java.lang.String r0 = r4.getString(r14)     // Catch:{ all -> 0x02ad }
            r3.scheduleType = r0     // Catch:{ all -> 0x02ad }
            java.lang.String r0 = r4.getString(r2)     // Catch:{ all -> 0x02ad }
            com.urbanairship.automation.storage.Converters r2 = r1.__converters     // Catch:{ all -> 0x02ad }
            com.urbanairship.json.JsonValue r0 = r2.jsonValueFromString(r0)     // Catch:{ all -> 0x02ad }
            r3.data = r0     // Catch:{ all -> 0x02ad }
            int r0 = r4.getInt(r15)     // Catch:{ all -> 0x02ad }
            r3.count = r0     // Catch:{ all -> 0x02ad }
            r0 = r28
            int r0 = r4.getInt(r0)     // Catch:{ all -> 0x02ad }
            r3.executionState = r0     // Catch:{ all -> 0x02ad }
            r0 = r17
            long r6 = r4.getLong(r0)     // Catch:{ all -> 0x02ad }
            r3.executionStateChangeDate = r6     // Catch:{ all -> 0x02ad }
            r0 = r18
            java.lang.String r0 = r4.getString(r0)     // Catch:{ all -> 0x02ad }
            com.urbanairship.automation.storage.Converters r2 = r1.__converters     // Catch:{ all -> 0x02ad }
            com.urbanairship.automation.TriggerContext r0 = r2.triggerContextFromString(r0)     // Catch:{ all -> 0x02ad }
            r3.triggerContext = r0     // Catch:{ all -> 0x02ad }
            r0 = r19
            int r0 = r4.getInt(r0)     // Catch:{ all -> 0x02ad }
            r3.appState = r0     // Catch:{ all -> 0x02ad }
            r0 = r20
            java.lang.String r0 = r4.getString(r0)     // Catch:{ all -> 0x02ad }
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r0)     // Catch:{ all -> 0x02ad }
            r3.screens = r0     // Catch:{ all -> 0x02ad }
            r0 = r21
            long r6 = r4.getLong(r0)     // Catch:{ all -> 0x02ad }
            r3.seconds = r6     // Catch:{ all -> 0x02ad }
            r0 = r22
            java.lang.String r0 = r4.getString(r0)     // Catch:{ all -> 0x02ad }
            r3.regionId = r0     // Catch:{ all -> 0x02ad }
            r0 = r23
            java.lang.String r0 = r4.getString(r0)     // Catch:{ all -> 0x02ad }
            com.urbanairship.automation.storage.Converters r2 = r1.__converters     // Catch:{ all -> 0x02ad }
            com.urbanairship.automation.Audience r0 = r2.audienceFromString(r0)     // Catch:{ all -> 0x02ad }
            r3.audience = r0     // Catch:{ all -> 0x02ad }
        L_0x0278:
            boolean r0 = r4.isNull(r5)     // Catch:{ all -> 0x02ad }
            if (r0 != 0) goto L_0x028c
            java.lang.String r0 = r4.getString(r5)     // Catch:{ all -> 0x02ad }
            r2 = r24
            java.lang.Object r0 = r2.get(r0)     // Catch:{ all -> 0x02ad }
            r2 = r0
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x02ad }
            goto L_0x028d
        L_0x028c:
            r2 = 0
        L_0x028d:
            if (r2 != 0) goto L_0x0294
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x02ad }
            r2.<init>()     // Catch:{ all -> 0x02ad }
        L_0x0294:
            com.urbanairship.automation.storage.FullSchedule r0 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x02ad }
            r0.<init>(r3, r2)     // Catch:{ all -> 0x02ad }
            r2 = r0
            goto L_0x029c
        L_0x029b:
            r2 = 0
        L_0x029c:
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x02ad }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x02ad }
            r4.close()     // Catch:{ all -> 0x02b9 }
            r16.release()     // Catch:{ all -> 0x02b9 }
            androidx.room.RoomDatabase r0 = r1.__db
            r0.endTransaction()
            return r2
        L_0x02ad:
            r0 = move-exception
            goto L_0x02b2
        L_0x02af:
            r0 = move-exception
            r16 = r3
        L_0x02b2:
            r4.close()     // Catch:{ all -> 0x02b9 }
            r16.release()     // Catch:{ all -> 0x02b9 }
            throw r0     // Catch:{ all -> 0x02b9 }
        L_0x02b9:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedule(java.lang.String, java.lang.String):com.urbanairship.automation.storage.FullSchedule");
    }

    /* JADX WARNING: Removed duplicated region for block: B:114:0x02fb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x02e4 A[Catch:{ all -> 0x033c }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02f1 A[Catch:{ all -> 0x033c }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02f6 A[Catch:{ all -> 0x033c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.urbanairship.automation.storage.FullSchedule> getSchedules(java.util.Collection<java.lang.String> r32) {
        /*
            r31 = this;
            r1 = r31
            java.lang.StringBuilder r0 = androidx.room.util.StringUtil.newStringBuilder()
            java.lang.String r2 = "SELECT "
            r0.append(r2)
            java.lang.String r2 = "*"
            r0.append(r2)
            java.lang.String r2 = " FROM schedules WHERE (scheduleId IN ("
            r0.append(r2)
            int r2 = r32.size()
            androidx.room.util.StringUtil.appendPlaceholders(r0, r2)
            java.lang.String r3 = "))"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            int r2 = r2 + 0
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r0, r2)
            java.util.Iterator r0 = r32.iterator()
            r3 = 1
            r4 = 1
        L_0x0031:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0049
            java.lang.Object r5 = r0.next()
            java.lang.String r5 = (java.lang.String) r5
            if (r5 != 0) goto L_0x0043
            r2.bindNull(r4)
            goto L_0x0046
        L_0x0043:
            r2.bindString(r4, r5)
        L_0x0046:
            int r4 = r4 + 1
            goto L_0x0031
        L_0x0049:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x0348 }
            r4 = 0
            android.database.Cursor r3 = androidx.room.util.DBUtil.query(r0, r2, r3, r4)     // Catch:{ all -> 0x0348 }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r0)     // Catch:{ all -> 0x033e }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r5)     // Catch:{ all -> 0x033e }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r6)     // Catch:{ all -> 0x033e }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r7)     // Catch:{ all -> 0x033e }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r8)     // Catch:{ all -> 0x033e }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r9)     // Catch:{ all -> 0x033e }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r10)     // Catch:{ all -> 0x033e }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r11)     // Catch:{ all -> 0x033e }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r12)     // Catch:{ all -> 0x033e }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r13)     // Catch:{ all -> 0x033e }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r14)     // Catch:{ all -> 0x033e }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r15)     // Catch:{ all -> 0x033e }
            java.lang.String r4 = "count"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r4)     // Catch:{ all -> 0x033e }
            r16 = r2
            java.lang.String r2 = "executionState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x033c }
            r17 = r2
            java.lang.String r2 = "executionStateChangeDate"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x033c }
            r18 = r2
            java.lang.String r2 = "triggerContext"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x033c }
            r19 = r2
            java.lang.String r2 = "appState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x033c }
            r20 = r2
            java.lang.String r2 = "screens"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x033c }
            r21 = r2
            java.lang.String r2 = "seconds"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x033c }
            r22 = r2
            java.lang.String r2 = "regionId"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x033c }
            r23 = r2
            java.lang.String r2 = "audience"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x033c }
            r24 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x033c }
            r2.<init>()     // Catch:{ all -> 0x033c }
        L_0x00ef:
            boolean r25 = r3.moveToNext()     // Catch:{ all -> 0x033c }
            if (r25 == 0) goto L_0x011b
            boolean r25 = r3.isNull(r5)     // Catch:{ all -> 0x033c }
            if (r25 != 0) goto L_0x00ef
            r25 = r4
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x033c }
            java.lang.Object r26 = r2.get(r4)     // Catch:{ all -> 0x033c }
            java.util.ArrayList r26 = (java.util.ArrayList) r26     // Catch:{ all -> 0x033c }
            if (r26 != 0) goto L_0x0114
            r26 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x033c }
            r15.<init>()     // Catch:{ all -> 0x033c }
            r2.put(r4, r15)     // Catch:{ all -> 0x033c }
            goto L_0x0116
        L_0x0114:
            r26 = r15
        L_0x0116:
            r4 = r25
            r15 = r26
            goto L_0x00ef
        L_0x011b:
            r25 = r4
            r26 = r15
            r4 = -1
            r3.moveToPosition(r4)     // Catch:{ all -> 0x033c }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r2)     // Catch:{ all -> 0x033c }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x033c }
            int r15 = r3.getCount()     // Catch:{ all -> 0x033c }
            r4.<init>(r15)     // Catch:{ all -> 0x033c }
        L_0x012f:
            boolean r15 = r3.moveToNext()     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x032a
            boolean r15 = r3.isNull(r0)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r5)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r6)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r7)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r8)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r9)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r10)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r11)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r12)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r13)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            boolean r15 = r3.isNull(r14)     // Catch:{ all -> 0x033c }
            if (r15 == 0) goto L_0x020d
            r15 = r26
            boolean r26 = r3.isNull(r15)     // Catch:{ all -> 0x033c }
            if (r26 == 0) goto L_0x020f
            r26 = r4
            r4 = r25
            boolean r25 = r3.isNull(r4)     // Catch:{ all -> 0x033c }
            if (r25 == 0) goto L_0x0213
            r25 = r2
            r2 = r17
            boolean r17 = r3.isNull(r2)     // Catch:{ all -> 0x033c }
            if (r17 == 0) goto L_0x020a
            r17 = r2
            r2 = r18
            boolean r18 = r3.isNull(r2)     // Catch:{ all -> 0x033c }
            if (r18 == 0) goto L_0x0207
            r18 = r2
            r2 = r19
            boolean r19 = r3.isNull(r2)     // Catch:{ all -> 0x033c }
            if (r19 == 0) goto L_0x0204
            r19 = r2
            r2 = r20
            boolean r20 = r3.isNull(r2)     // Catch:{ all -> 0x033c }
            if (r20 == 0) goto L_0x0201
            r20 = r2
            r2 = r21
            boolean r21 = r3.isNull(r2)     // Catch:{ all -> 0x033c }
            if (r21 == 0) goto L_0x01fe
            r21 = r2
            r2 = r22
            boolean r22 = r3.isNull(r2)     // Catch:{ all -> 0x033c }
            if (r22 == 0) goto L_0x01fb
            r22 = r2
            r2 = r23
            boolean r23 = r3.isNull(r2)     // Catch:{ all -> 0x033c }
            if (r23 == 0) goto L_0x01f8
            r23 = r2
            r2 = r24
            boolean r24 = r3.isNull(r2)     // Catch:{ all -> 0x033c }
            if (r24 != 0) goto L_0x01dc
            r24 = r2
            goto L_0x0215
        L_0x01dc:
            r28 = r0
            r27 = r4
            r24 = r7
            r7 = r2
            r2 = 0
            r29 = r17
            r17 = r6
            r6 = r23
            r23 = r22
            r22 = r21
            r21 = r20
            r20 = r19
            r19 = r18
            r18 = r29
            goto L_0x02de
        L_0x01f8:
            r23 = r2
            goto L_0x0215
        L_0x01fb:
            r22 = r2
            goto L_0x0215
        L_0x01fe:
            r21 = r2
            goto L_0x0215
        L_0x0201:
            r20 = r2
            goto L_0x0215
        L_0x0204:
            r19 = r2
            goto L_0x0215
        L_0x0207:
            r18 = r2
            goto L_0x0215
        L_0x020a:
            r17 = r2
            goto L_0x0215
        L_0x020d:
            r15 = r26
        L_0x020f:
            r26 = r4
            r4 = r25
        L_0x0213:
            r25 = r2
        L_0x0215:
            com.urbanairship.automation.storage.ScheduleEntity r2 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x033c }
            r2.<init>()     // Catch:{ all -> 0x033c }
            r27 = r4
            int r4 = r3.getInt(r0)     // Catch:{ all -> 0x033c }
            r2.id = r4     // Catch:{ all -> 0x033c }
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x033c }
            r2.scheduleId = r4     // Catch:{ all -> 0x033c }
            java.lang.String r4 = r3.getString(r6)     // Catch:{ all -> 0x033c }
            r2.group = r4     // Catch:{ all -> 0x033c }
            java.lang.String r4 = r3.getString(r7)     // Catch:{ all -> 0x033c }
            r28 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x033c }
            com.urbanairship.json.JsonMap r0 = r0.jsonMapFromString(r4)     // Catch:{ all -> 0x033c }
            r2.metadata = r0     // Catch:{ all -> 0x033c }
            int r0 = r3.getInt(r8)     // Catch:{ all -> 0x033c }
            r2.limit = r0     // Catch:{ all -> 0x033c }
            int r0 = r3.getInt(r9)     // Catch:{ all -> 0x033c }
            r2.priority = r0     // Catch:{ all -> 0x033c }
            r0 = r6
            r4 = r7
            long r6 = r3.getLong(r10)     // Catch:{ all -> 0x033c }
            r2.scheduleStart = r6     // Catch:{ all -> 0x033c }
            long r6 = r3.getLong(r11)     // Catch:{ all -> 0x033c }
            r2.scheduleEnd = r6     // Catch:{ all -> 0x033c }
            long r6 = r3.getLong(r12)     // Catch:{ all -> 0x033c }
            r2.editGracePeriod = r6     // Catch:{ all -> 0x033c }
            long r6 = r3.getLong(r13)     // Catch:{ all -> 0x033c }
            r2.interval = r6     // Catch:{ all -> 0x033c }
            java.lang.String r6 = r3.getString(r14)     // Catch:{ all -> 0x033c }
            r2.scheduleType = r6     // Catch:{ all -> 0x033c }
            java.lang.String r6 = r3.getString(r15)     // Catch:{ all -> 0x033c }
            com.urbanairship.automation.storage.Converters r7 = r1.__converters     // Catch:{ all -> 0x033c }
            com.urbanairship.json.JsonValue r6 = r7.jsonValueFromString(r6)     // Catch:{ all -> 0x033c }
            r2.data = r6     // Catch:{ all -> 0x033c }
            r6 = r27
            int r7 = r3.getInt(r6)     // Catch:{ all -> 0x033c }
            r2.count = r7     // Catch:{ all -> 0x033c }
            r7 = r17
            r17 = r0
            int r0 = r3.getInt(r7)     // Catch:{ all -> 0x033c }
            r2.executionState = r0     // Catch:{ all -> 0x033c }
            r27 = r6
            r0 = r18
            r18 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x033c }
            r2.executionStateChangeDate = r6     // Catch:{ all -> 0x033c }
            r6 = r19
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x033c }
            r19 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x033c }
            com.urbanairship.automation.TriggerContext r0 = r0.triggerContextFromString(r7)     // Catch:{ all -> 0x033c }
            r2.triggerContext = r0     // Catch:{ all -> 0x033c }
            r0 = r20
            int r7 = r3.getInt(r0)     // Catch:{ all -> 0x033c }
            r2.appState = r7     // Catch:{ all -> 0x033c }
            r7 = r21
            java.lang.String r20 = r3.getString(r7)     // Catch:{ all -> 0x033c }
            r21 = r0
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r20)     // Catch:{ all -> 0x033c }
            r2.screens = r0     // Catch:{ all -> 0x033c }
            r20 = r6
            r0 = r22
            r22 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x033c }
            r2.seconds = r6     // Catch:{ all -> 0x033c }
            r6 = r23
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x033c }
            r2.regionId = r7     // Catch:{ all -> 0x033c }
            r23 = r0
            r7 = r24
            java.lang.String r0 = r3.getString(r7)     // Catch:{ all -> 0x033c }
            r24 = r4
            com.urbanairship.automation.storage.Converters r4 = r1.__converters     // Catch:{ all -> 0x033c }
            com.urbanairship.automation.Audience r0 = r4.audienceFromString(r0)     // Catch:{ all -> 0x033c }
            r2.audience = r0     // Catch:{ all -> 0x033c }
        L_0x02de:
            boolean r0 = r3.isNull(r5)     // Catch:{ all -> 0x033c }
            if (r0 != 0) goto L_0x02f1
            java.lang.String r0 = r3.getString(r5)     // Catch:{ all -> 0x033c }
            r4 = r25
            java.lang.Object r0 = r4.get(r0)     // Catch:{ all -> 0x033c }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x033c }
            goto L_0x02f4
        L_0x02f1:
            r4 = r25
            r0 = 0
        L_0x02f4:
            if (r0 != 0) goto L_0x02fb
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x033c }
            r0.<init>()     // Catch:{ all -> 0x033c }
        L_0x02fb:
            r25 = r4
            com.urbanairship.automation.storage.FullSchedule r4 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x033c }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x033c }
            r0 = r26
            r0.add(r4)     // Catch:{ all -> 0x033c }
            r4 = r0
            r26 = r15
            r2 = r25
            r25 = r27
            r0 = r28
            r29 = r23
            r23 = r6
            r6 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r29
            r30 = r24
            r24 = r7
            r7 = r30
            goto L_0x012f
        L_0x032a:
            r0 = r4
            androidx.room.RoomDatabase r2 = r1.__db     // Catch:{ all -> 0x033c }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x033c }
            r3.close()     // Catch:{ all -> 0x0348 }
            r16.release()     // Catch:{ all -> 0x0348 }
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            return r0
        L_0x033c:
            r0 = move-exception
            goto L_0x0341
        L_0x033e:
            r0 = move-exception
            r16 = r2
        L_0x0341:
            r3.close()     // Catch:{ all -> 0x0348 }
            r16.release()     // Catch:{ all -> 0x0348 }
            throw r0     // Catch:{ all -> 0x0348 }
        L_0x0348:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedules(java.util.Collection):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x030f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02f8 A[Catch:{ all -> 0x0350 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0305 A[Catch:{ all -> 0x0350 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x030a A[Catch:{ all -> 0x0350 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.urbanairship.automation.storage.FullSchedule> getSchedules(java.util.Collection<java.lang.String> r31, java.lang.String r32) {
        /*
            r30 = this;
            r1 = r30
            r0 = r32
            java.lang.StringBuilder r2 = androidx.room.util.StringUtil.newStringBuilder()
            java.lang.String r3 = "SELECT "
            r2.append(r3)
            java.lang.String r3 = "*"
            r2.append(r3)
            java.lang.String r3 = " FROM schedules WHERE (scheduleId IN ("
            r2.append(r3)
            int r3 = r31.size()
            androidx.room.util.StringUtil.appendPlaceholders(r2, r3)
            java.lang.String r4 = ")) AND (scheduleType = "
            r2.append(r4)
            java.lang.String r4 = "?"
            r2.append(r4)
            java.lang.String r4 = ")"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r4 = 1
            int r3 = r3 + r4
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r2, r3)
            java.util.Iterator r5 = r31.iterator()
            r6 = 1
        L_0x003c:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x0054
            java.lang.Object r7 = r5.next()
            java.lang.String r7 = (java.lang.String) r7
            if (r7 != 0) goto L_0x004e
            r2.bindNull(r6)
            goto L_0x0051
        L_0x004e:
            r2.bindString(r6, r7)
        L_0x0051:
            int r6 = r6 + 1
            goto L_0x003c
        L_0x0054:
            if (r0 != 0) goto L_0x005a
            r2.bindNull(r3)
            goto L_0x005d
        L_0x005a:
            r2.bindString(r3, r0)
        L_0x005d:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x035c }
            r3 = 0
            android.database.Cursor r4 = androidx.room.util.DBUtil.query(r0, r2, r4, r3)     // Catch:{ all -> 0x035c }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r0)     // Catch:{ all -> 0x0352 }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r5)     // Catch:{ all -> 0x0352 }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r6)     // Catch:{ all -> 0x0352 }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r7)     // Catch:{ all -> 0x0352 }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r8)     // Catch:{ all -> 0x0352 }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r9)     // Catch:{ all -> 0x0352 }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r10)     // Catch:{ all -> 0x0352 }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r11)     // Catch:{ all -> 0x0352 }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r12)     // Catch:{ all -> 0x0352 }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r13)     // Catch:{ all -> 0x0352 }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r14)     // Catch:{ all -> 0x0352 }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r15)     // Catch:{ all -> 0x0352 }
            java.lang.String r3 = "count"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0352 }
            r16 = r2
            java.lang.String r2 = "executionState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0350 }
            r32 = r2
            java.lang.String r2 = "executionStateChangeDate"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0350 }
            r17 = r2
            java.lang.String r2 = "triggerContext"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0350 }
            r18 = r2
            java.lang.String r2 = "appState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0350 }
            r19 = r2
            java.lang.String r2 = "screens"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0350 }
            r20 = r2
            java.lang.String r2 = "seconds"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0350 }
            r21 = r2
            java.lang.String r2 = "regionId"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0350 }
            r22 = r2
            java.lang.String r2 = "audience"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0350 }
            r23 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x0350 }
            r2.<init>()     // Catch:{ all -> 0x0350 }
        L_0x0103:
            boolean r24 = r4.moveToNext()     // Catch:{ all -> 0x0350 }
            if (r24 == 0) goto L_0x012f
            boolean r24 = r4.isNull(r5)     // Catch:{ all -> 0x0350 }
            if (r24 != 0) goto L_0x0103
            r24 = r3
            java.lang.String r3 = r4.getString(r5)     // Catch:{ all -> 0x0350 }
            java.lang.Object r25 = r2.get(r3)     // Catch:{ all -> 0x0350 }
            java.util.ArrayList r25 = (java.util.ArrayList) r25     // Catch:{ all -> 0x0350 }
            if (r25 != 0) goto L_0x0128
            r25 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0350 }
            r15.<init>()     // Catch:{ all -> 0x0350 }
            r2.put(r3, r15)     // Catch:{ all -> 0x0350 }
            goto L_0x012a
        L_0x0128:
            r25 = r15
        L_0x012a:
            r3 = r24
            r15 = r25
            goto L_0x0103
        L_0x012f:
            r24 = r3
            r25 = r15
            r3 = -1
            r4.moveToPosition(r3)     // Catch:{ all -> 0x0350 }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r2)     // Catch:{ all -> 0x0350 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0350 }
            int r15 = r4.getCount()     // Catch:{ all -> 0x0350 }
            r3.<init>(r15)     // Catch:{ all -> 0x0350 }
        L_0x0143:
            boolean r15 = r4.moveToNext()     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x033e
            boolean r15 = r4.isNull(r0)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r5)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r6)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r7)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r8)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r9)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r10)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r11)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r12)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r13)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            boolean r15 = r4.isNull(r14)     // Catch:{ all -> 0x0350 }
            if (r15 == 0) goto L_0x0221
            r15 = r25
            boolean r25 = r4.isNull(r15)     // Catch:{ all -> 0x0350 }
            if (r25 == 0) goto L_0x0223
            r25 = r3
            r3 = r24
            boolean r24 = r4.isNull(r3)     // Catch:{ all -> 0x0350 }
            if (r24 == 0) goto L_0x0227
            r24 = r2
            r2 = r32
            boolean r26 = r4.isNull(r2)     // Catch:{ all -> 0x0350 }
            if (r26 == 0) goto L_0x021e
            r32 = r2
            r2 = r17
            boolean r17 = r4.isNull(r2)     // Catch:{ all -> 0x0350 }
            if (r17 == 0) goto L_0x021b
            r17 = r2
            r2 = r18
            boolean r18 = r4.isNull(r2)     // Catch:{ all -> 0x0350 }
            if (r18 == 0) goto L_0x0218
            r18 = r2
            r2 = r19
            boolean r19 = r4.isNull(r2)     // Catch:{ all -> 0x0350 }
            if (r19 == 0) goto L_0x0215
            r19 = r2
            r2 = r20
            boolean r20 = r4.isNull(r2)     // Catch:{ all -> 0x0350 }
            if (r20 == 0) goto L_0x0212
            r20 = r2
            r2 = r21
            boolean r21 = r4.isNull(r2)     // Catch:{ all -> 0x0350 }
            if (r21 == 0) goto L_0x020f
            r21 = r2
            r2 = r22
            boolean r22 = r4.isNull(r2)     // Catch:{ all -> 0x0350 }
            if (r22 == 0) goto L_0x020c
            r22 = r2
            r2 = r23
            boolean r23 = r4.isNull(r2)     // Catch:{ all -> 0x0350 }
            if (r23 != 0) goto L_0x01f0
            r23 = r2
            goto L_0x0229
        L_0x01f0:
            r27 = r0
            r26 = r3
            r23 = r7
            r7 = r2
            r2 = 0
            r28 = r17
            r17 = r32
            r32 = r6
            r6 = r22
            r22 = r21
            r21 = r20
            r20 = r19
            r19 = r18
            r18 = r28
            goto L_0x02f2
        L_0x020c:
            r22 = r2
            goto L_0x0229
        L_0x020f:
            r21 = r2
            goto L_0x0229
        L_0x0212:
            r20 = r2
            goto L_0x0229
        L_0x0215:
            r19 = r2
            goto L_0x0229
        L_0x0218:
            r18 = r2
            goto L_0x0229
        L_0x021b:
            r17 = r2
            goto L_0x0229
        L_0x021e:
            r32 = r2
            goto L_0x0229
        L_0x0221:
            r15 = r25
        L_0x0223:
            r25 = r3
            r3 = r24
        L_0x0227:
            r24 = r2
        L_0x0229:
            com.urbanairship.automation.storage.ScheduleEntity r2 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x0350 }
            r2.<init>()     // Catch:{ all -> 0x0350 }
            r26 = r3
            int r3 = r4.getInt(r0)     // Catch:{ all -> 0x0350 }
            r2.id = r3     // Catch:{ all -> 0x0350 }
            java.lang.String r3 = r4.getString(r5)     // Catch:{ all -> 0x0350 }
            r2.scheduleId = r3     // Catch:{ all -> 0x0350 }
            java.lang.String r3 = r4.getString(r6)     // Catch:{ all -> 0x0350 }
            r2.group = r3     // Catch:{ all -> 0x0350 }
            java.lang.String r3 = r4.getString(r7)     // Catch:{ all -> 0x0350 }
            r27 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x0350 }
            com.urbanairship.json.JsonMap r0 = r0.jsonMapFromString(r3)     // Catch:{ all -> 0x0350 }
            r2.metadata = r0     // Catch:{ all -> 0x0350 }
            int r0 = r4.getInt(r8)     // Catch:{ all -> 0x0350 }
            r2.limit = r0     // Catch:{ all -> 0x0350 }
            int r0 = r4.getInt(r9)     // Catch:{ all -> 0x0350 }
            r2.priority = r0     // Catch:{ all -> 0x0350 }
            r0 = r6
            r3 = r7
            long r6 = r4.getLong(r10)     // Catch:{ all -> 0x0350 }
            r2.scheduleStart = r6     // Catch:{ all -> 0x0350 }
            long r6 = r4.getLong(r11)     // Catch:{ all -> 0x0350 }
            r2.scheduleEnd = r6     // Catch:{ all -> 0x0350 }
            long r6 = r4.getLong(r12)     // Catch:{ all -> 0x0350 }
            r2.editGracePeriod = r6     // Catch:{ all -> 0x0350 }
            long r6 = r4.getLong(r13)     // Catch:{ all -> 0x0350 }
            r2.interval = r6     // Catch:{ all -> 0x0350 }
            java.lang.String r6 = r4.getString(r14)     // Catch:{ all -> 0x0350 }
            r2.scheduleType = r6     // Catch:{ all -> 0x0350 }
            java.lang.String r6 = r4.getString(r15)     // Catch:{ all -> 0x0350 }
            com.urbanairship.automation.storage.Converters r7 = r1.__converters     // Catch:{ all -> 0x0350 }
            com.urbanairship.json.JsonValue r6 = r7.jsonValueFromString(r6)     // Catch:{ all -> 0x0350 }
            r2.data = r6     // Catch:{ all -> 0x0350 }
            r6 = r26
            int r7 = r4.getInt(r6)     // Catch:{ all -> 0x0350 }
            r2.count = r7     // Catch:{ all -> 0x0350 }
            r7 = r32
            r32 = r0
            int r0 = r4.getInt(r7)     // Catch:{ all -> 0x0350 }
            r2.executionState = r0     // Catch:{ all -> 0x0350 }
            r26 = r6
            r0 = r17
            r17 = r7
            long r6 = r4.getLong(r0)     // Catch:{ all -> 0x0350 }
            r2.executionStateChangeDate = r6     // Catch:{ all -> 0x0350 }
            r6 = r18
            java.lang.String r7 = r4.getString(r6)     // Catch:{ all -> 0x0350 }
            r18 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x0350 }
            com.urbanairship.automation.TriggerContext r0 = r0.triggerContextFromString(r7)     // Catch:{ all -> 0x0350 }
            r2.triggerContext = r0     // Catch:{ all -> 0x0350 }
            r0 = r19
            int r7 = r4.getInt(r0)     // Catch:{ all -> 0x0350 }
            r2.appState = r7     // Catch:{ all -> 0x0350 }
            r7 = r20
            java.lang.String r19 = r4.getString(r7)     // Catch:{ all -> 0x0350 }
            r20 = r0
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r19)     // Catch:{ all -> 0x0350 }
            r2.screens = r0     // Catch:{ all -> 0x0350 }
            r19 = r6
            r0 = r21
            r21 = r7
            long r6 = r4.getLong(r0)     // Catch:{ all -> 0x0350 }
            r2.seconds = r6     // Catch:{ all -> 0x0350 }
            r6 = r22
            java.lang.String r7 = r4.getString(r6)     // Catch:{ all -> 0x0350 }
            r2.regionId = r7     // Catch:{ all -> 0x0350 }
            r22 = r0
            r7 = r23
            java.lang.String r0 = r4.getString(r7)     // Catch:{ all -> 0x0350 }
            r23 = r3
            com.urbanairship.automation.storage.Converters r3 = r1.__converters     // Catch:{ all -> 0x0350 }
            com.urbanairship.automation.Audience r0 = r3.audienceFromString(r0)     // Catch:{ all -> 0x0350 }
            r2.audience = r0     // Catch:{ all -> 0x0350 }
        L_0x02f2:
            boolean r0 = r4.isNull(r5)     // Catch:{ all -> 0x0350 }
            if (r0 != 0) goto L_0x0305
            java.lang.String r0 = r4.getString(r5)     // Catch:{ all -> 0x0350 }
            r3 = r24
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x0350 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0350 }
            goto L_0x0308
        L_0x0305:
            r3 = r24
            r0 = 0
        L_0x0308:
            if (r0 != 0) goto L_0x030f
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0350 }
            r0.<init>()     // Catch:{ all -> 0x0350 }
        L_0x030f:
            r24 = r3
            com.urbanairship.automation.storage.FullSchedule r3 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x0350 }
            r3.<init>(r2, r0)     // Catch:{ all -> 0x0350 }
            r0 = r25
            r0.add(r3)     // Catch:{ all -> 0x0350 }
            r3 = r0
            r25 = r15
            r2 = r24
            r24 = r26
            r0 = r27
            r28 = r6
            r6 = r32
            r32 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r28
            r29 = r23
            r23 = r7
            r7 = r29
            goto L_0x0143
        L_0x033e:
            r0 = r3
            androidx.room.RoomDatabase r2 = r1.__db     // Catch:{ all -> 0x0350 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0350 }
            r4.close()     // Catch:{ all -> 0x035c }
            r16.release()     // Catch:{ all -> 0x035c }
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            return r0
        L_0x0350:
            r0 = move-exception
            goto L_0x0355
        L_0x0352:
            r0 = move-exception
            r16 = r2
        L_0x0355:
            r4.close()     // Catch:{ all -> 0x035c }
            r16.release()     // Catch:{ all -> 0x035c }
            throw r0     // Catch:{ all -> 0x035c }
        L_0x035c:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedules(java.util.Collection, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x02d2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02bb A[Catch:{ all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x02c8 A[Catch:{ all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x02cd A[Catch:{ all -> 0x0313 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.urbanairship.automation.storage.FullSchedule> getSchedulesWithGroup(java.lang.String r31, java.lang.String r32) {
        /*
            r30 = this;
            r1 = r30
            r0 = r31
            r2 = r32
            java.lang.String r3 = "SELECT * FROM schedules WHERE (`group` == ?) AND (scheduleType = ?)"
            r4 = 2
            androidx.room.RoomSQLiteQuery r3 = androidx.room.RoomSQLiteQuery.acquire(r3, r4)
            r5 = 1
            if (r0 != 0) goto L_0x0014
            r3.bindNull(r5)
            goto L_0x0017
        L_0x0014:
            r3.bindString(r5, r0)
        L_0x0017:
            if (r2 != 0) goto L_0x001d
            r3.bindNull(r4)
            goto L_0x0020
        L_0x001d:
            r3.bindString(r4, r2)
        L_0x0020:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x031f }
            r2 = 0
            android.database.Cursor r4 = androidx.room.util.DBUtil.query(r0, r3, r5, r2)     // Catch:{ all -> 0x031f }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r0)     // Catch:{ all -> 0x0315 }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r5)     // Catch:{ all -> 0x0315 }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r6)     // Catch:{ all -> 0x0315 }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r7)     // Catch:{ all -> 0x0315 }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r8)     // Catch:{ all -> 0x0315 }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r9)     // Catch:{ all -> 0x0315 }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r10)     // Catch:{ all -> 0x0315 }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r11)     // Catch:{ all -> 0x0315 }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r12)     // Catch:{ all -> 0x0315 }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r13)     // Catch:{ all -> 0x0315 }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r14)     // Catch:{ all -> 0x0315 }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r15)     // Catch:{ all -> 0x0315 }
            java.lang.String r2 = "count"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x0315 }
            r16 = r3
            java.lang.String r3 = "executionState"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0313 }
            r32 = r3
            java.lang.String r3 = "executionStateChangeDate"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0313 }
            r17 = r3
            java.lang.String r3 = "triggerContext"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0313 }
            r18 = r3
            java.lang.String r3 = "appState"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0313 }
            r19 = r3
            java.lang.String r3 = "screens"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0313 }
            r20 = r3
            java.lang.String r3 = "seconds"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0313 }
            r21 = r3
            java.lang.String r3 = "regionId"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0313 }
            r22 = r3
            java.lang.String r3 = "audience"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x0313 }
            r23 = r3
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x0313 }
            r3.<init>()     // Catch:{ all -> 0x0313 }
        L_0x00c6:
            boolean r24 = r4.moveToNext()     // Catch:{ all -> 0x0313 }
            if (r24 == 0) goto L_0x00f2
            boolean r24 = r4.isNull(r5)     // Catch:{ all -> 0x0313 }
            if (r24 != 0) goto L_0x00c6
            r24 = r2
            java.lang.String r2 = r4.getString(r5)     // Catch:{ all -> 0x0313 }
            java.lang.Object r25 = r3.get(r2)     // Catch:{ all -> 0x0313 }
            java.util.ArrayList r25 = (java.util.ArrayList) r25     // Catch:{ all -> 0x0313 }
            if (r25 != 0) goto L_0x00eb
            r25 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0313 }
            r15.<init>()     // Catch:{ all -> 0x0313 }
            r3.put(r2, r15)     // Catch:{ all -> 0x0313 }
            goto L_0x00ed
        L_0x00eb:
            r25 = r15
        L_0x00ed:
            r2 = r24
            r15 = r25
            goto L_0x00c6
        L_0x00f2:
            r24 = r2
            r25 = r15
            r2 = -1
            r4.moveToPosition(r2)     // Catch:{ all -> 0x0313 }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r3)     // Catch:{ all -> 0x0313 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0313 }
            int r15 = r4.getCount()     // Catch:{ all -> 0x0313 }
            r2.<init>(r15)     // Catch:{ all -> 0x0313 }
        L_0x0106:
            boolean r15 = r4.moveToNext()     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x0301
            boolean r15 = r4.isNull(r0)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r5)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r6)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r7)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r8)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r9)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r10)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r11)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r12)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r13)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            boolean r15 = r4.isNull(r14)     // Catch:{ all -> 0x0313 }
            if (r15 == 0) goto L_0x01e4
            r15 = r25
            boolean r25 = r4.isNull(r15)     // Catch:{ all -> 0x0313 }
            if (r25 == 0) goto L_0x01e6
            r25 = r2
            r2 = r24
            boolean r24 = r4.isNull(r2)     // Catch:{ all -> 0x0313 }
            if (r24 == 0) goto L_0x01ea
            r24 = r3
            r3 = r32
            boolean r26 = r4.isNull(r3)     // Catch:{ all -> 0x0313 }
            if (r26 == 0) goto L_0x01e1
            r32 = r3
            r3 = r17
            boolean r17 = r4.isNull(r3)     // Catch:{ all -> 0x0313 }
            if (r17 == 0) goto L_0x01de
            r17 = r3
            r3 = r18
            boolean r18 = r4.isNull(r3)     // Catch:{ all -> 0x0313 }
            if (r18 == 0) goto L_0x01db
            r18 = r3
            r3 = r19
            boolean r19 = r4.isNull(r3)     // Catch:{ all -> 0x0313 }
            if (r19 == 0) goto L_0x01d8
            r19 = r3
            r3 = r20
            boolean r20 = r4.isNull(r3)     // Catch:{ all -> 0x0313 }
            if (r20 == 0) goto L_0x01d5
            r20 = r3
            r3 = r21
            boolean r21 = r4.isNull(r3)     // Catch:{ all -> 0x0313 }
            if (r21 == 0) goto L_0x01d2
            r21 = r3
            r3 = r22
            boolean r22 = r4.isNull(r3)     // Catch:{ all -> 0x0313 }
            if (r22 == 0) goto L_0x01cf
            r22 = r3
            r3 = r23
            boolean r23 = r4.isNull(r3)     // Catch:{ all -> 0x0313 }
            if (r23 != 0) goto L_0x01b3
            r23 = r3
            goto L_0x01ec
        L_0x01b3:
            r27 = r0
            r26 = r2
            r23 = r7
            r7 = r3
            r3 = 0
            r28 = r17
            r17 = r32
            r32 = r6
            r6 = r22
            r22 = r21
            r21 = r20
            r20 = r19
            r19 = r18
            r18 = r28
            goto L_0x02b5
        L_0x01cf:
            r22 = r3
            goto L_0x01ec
        L_0x01d2:
            r21 = r3
            goto L_0x01ec
        L_0x01d5:
            r20 = r3
            goto L_0x01ec
        L_0x01d8:
            r19 = r3
            goto L_0x01ec
        L_0x01db:
            r18 = r3
            goto L_0x01ec
        L_0x01de:
            r17 = r3
            goto L_0x01ec
        L_0x01e1:
            r32 = r3
            goto L_0x01ec
        L_0x01e4:
            r15 = r25
        L_0x01e6:
            r25 = r2
            r2 = r24
        L_0x01ea:
            r24 = r3
        L_0x01ec:
            com.urbanairship.automation.storage.ScheduleEntity r3 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x0313 }
            r3.<init>()     // Catch:{ all -> 0x0313 }
            r26 = r2
            int r2 = r4.getInt(r0)     // Catch:{ all -> 0x0313 }
            r3.id = r2     // Catch:{ all -> 0x0313 }
            java.lang.String r2 = r4.getString(r5)     // Catch:{ all -> 0x0313 }
            r3.scheduleId = r2     // Catch:{ all -> 0x0313 }
            java.lang.String r2 = r4.getString(r6)     // Catch:{ all -> 0x0313 }
            r3.group = r2     // Catch:{ all -> 0x0313 }
            java.lang.String r2 = r4.getString(r7)     // Catch:{ all -> 0x0313 }
            r27 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x0313 }
            com.urbanairship.json.JsonMap r0 = r0.jsonMapFromString(r2)     // Catch:{ all -> 0x0313 }
            r3.metadata = r0     // Catch:{ all -> 0x0313 }
            int r0 = r4.getInt(r8)     // Catch:{ all -> 0x0313 }
            r3.limit = r0     // Catch:{ all -> 0x0313 }
            int r0 = r4.getInt(r9)     // Catch:{ all -> 0x0313 }
            r3.priority = r0     // Catch:{ all -> 0x0313 }
            r0 = r6
            r2 = r7
            long r6 = r4.getLong(r10)     // Catch:{ all -> 0x0313 }
            r3.scheduleStart = r6     // Catch:{ all -> 0x0313 }
            long r6 = r4.getLong(r11)     // Catch:{ all -> 0x0313 }
            r3.scheduleEnd = r6     // Catch:{ all -> 0x0313 }
            long r6 = r4.getLong(r12)     // Catch:{ all -> 0x0313 }
            r3.editGracePeriod = r6     // Catch:{ all -> 0x0313 }
            long r6 = r4.getLong(r13)     // Catch:{ all -> 0x0313 }
            r3.interval = r6     // Catch:{ all -> 0x0313 }
            java.lang.String r6 = r4.getString(r14)     // Catch:{ all -> 0x0313 }
            r3.scheduleType = r6     // Catch:{ all -> 0x0313 }
            java.lang.String r6 = r4.getString(r15)     // Catch:{ all -> 0x0313 }
            com.urbanairship.automation.storage.Converters r7 = r1.__converters     // Catch:{ all -> 0x0313 }
            com.urbanairship.json.JsonValue r6 = r7.jsonValueFromString(r6)     // Catch:{ all -> 0x0313 }
            r3.data = r6     // Catch:{ all -> 0x0313 }
            r6 = r26
            int r7 = r4.getInt(r6)     // Catch:{ all -> 0x0313 }
            r3.count = r7     // Catch:{ all -> 0x0313 }
            r7 = r32
            r32 = r0
            int r0 = r4.getInt(r7)     // Catch:{ all -> 0x0313 }
            r3.executionState = r0     // Catch:{ all -> 0x0313 }
            r26 = r6
            r0 = r17
            r17 = r7
            long r6 = r4.getLong(r0)     // Catch:{ all -> 0x0313 }
            r3.executionStateChangeDate = r6     // Catch:{ all -> 0x0313 }
            r6 = r18
            java.lang.String r7 = r4.getString(r6)     // Catch:{ all -> 0x0313 }
            r18 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x0313 }
            com.urbanairship.automation.TriggerContext r0 = r0.triggerContextFromString(r7)     // Catch:{ all -> 0x0313 }
            r3.triggerContext = r0     // Catch:{ all -> 0x0313 }
            r0 = r19
            int r7 = r4.getInt(r0)     // Catch:{ all -> 0x0313 }
            r3.appState = r7     // Catch:{ all -> 0x0313 }
            r7 = r20
            java.lang.String r19 = r4.getString(r7)     // Catch:{ all -> 0x0313 }
            r20 = r0
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r19)     // Catch:{ all -> 0x0313 }
            r3.screens = r0     // Catch:{ all -> 0x0313 }
            r19 = r6
            r0 = r21
            r21 = r7
            long r6 = r4.getLong(r0)     // Catch:{ all -> 0x0313 }
            r3.seconds = r6     // Catch:{ all -> 0x0313 }
            r6 = r22
            java.lang.String r7 = r4.getString(r6)     // Catch:{ all -> 0x0313 }
            r3.regionId = r7     // Catch:{ all -> 0x0313 }
            r22 = r0
            r7 = r23
            java.lang.String r0 = r4.getString(r7)     // Catch:{ all -> 0x0313 }
            r23 = r2
            com.urbanairship.automation.storage.Converters r2 = r1.__converters     // Catch:{ all -> 0x0313 }
            com.urbanairship.automation.Audience r0 = r2.audienceFromString(r0)     // Catch:{ all -> 0x0313 }
            r3.audience = r0     // Catch:{ all -> 0x0313 }
        L_0x02b5:
            boolean r0 = r4.isNull(r5)     // Catch:{ all -> 0x0313 }
            if (r0 != 0) goto L_0x02c8
            java.lang.String r0 = r4.getString(r5)     // Catch:{ all -> 0x0313 }
            r2 = r24
            java.lang.Object r0 = r2.get(r0)     // Catch:{ all -> 0x0313 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0313 }
            goto L_0x02cb
        L_0x02c8:
            r2 = r24
            r0 = 0
        L_0x02cb:
            if (r0 != 0) goto L_0x02d2
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0313 }
            r0.<init>()     // Catch:{ all -> 0x0313 }
        L_0x02d2:
            r24 = r2
            com.urbanairship.automation.storage.FullSchedule r2 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x0313 }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0313 }
            r0 = r25
            r0.add(r2)     // Catch:{ all -> 0x0313 }
            r2 = r0
            r25 = r15
            r3 = r24
            r24 = r26
            r0 = r27
            r28 = r6
            r6 = r32
            r32 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r28
            r29 = r23
            r23 = r7
            r7 = r29
            goto L_0x0106
        L_0x0301:
            r0 = r2
            androidx.room.RoomDatabase r2 = r1.__db     // Catch:{ all -> 0x0313 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0313 }
            r4.close()     // Catch:{ all -> 0x031f }
            r16.release()     // Catch:{ all -> 0x031f }
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            return r0
        L_0x0313:
            r0 = move-exception
            goto L_0x0318
        L_0x0315:
            r0 = move-exception
            r16 = r3
        L_0x0318:
            r4.close()     // Catch:{ all -> 0x031f }
            r16.release()     // Catch:{ all -> 0x031f }
            throw r0     // Catch:{ all -> 0x031f }
        L_0x031f:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedulesWithGroup(java.lang.String, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x02c6 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02af A[Catch:{ all -> 0x0307 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x02bc A[Catch:{ all -> 0x0307 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02c1 A[Catch:{ all -> 0x0307 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.urbanairship.automation.storage.FullSchedule> getSchedulesWithGroup(java.lang.String r32) {
        /*
            r31 = this;
            r1 = r31
            r0 = r32
            java.lang.String r2 = "SELECT * FROM schedules WHERE (`group` == ?)"
            r3 = 1
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r2, r3)
            if (r0 != 0) goto L_0x0011
            r2.bindNull(r3)
            goto L_0x0014
        L_0x0011:
            r2.bindString(r3, r0)
        L_0x0014:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x0313 }
            r4 = 0
            android.database.Cursor r3 = androidx.room.util.DBUtil.query(r0, r2, r3, r4)     // Catch:{ all -> 0x0313 }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r0)     // Catch:{ all -> 0x0309 }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r5)     // Catch:{ all -> 0x0309 }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r6)     // Catch:{ all -> 0x0309 }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r7)     // Catch:{ all -> 0x0309 }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r8)     // Catch:{ all -> 0x0309 }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r9)     // Catch:{ all -> 0x0309 }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r10)     // Catch:{ all -> 0x0309 }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r11)     // Catch:{ all -> 0x0309 }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r12)     // Catch:{ all -> 0x0309 }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r13)     // Catch:{ all -> 0x0309 }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r14)     // Catch:{ all -> 0x0309 }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r15)     // Catch:{ all -> 0x0309 }
            java.lang.String r4 = "count"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r4)     // Catch:{ all -> 0x0309 }
            r16 = r2
            java.lang.String r2 = "executionState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r17 = r2
            java.lang.String r2 = "executionStateChangeDate"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r18 = r2
            java.lang.String r2 = "triggerContext"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r19 = r2
            java.lang.String r2 = "appState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r20 = r2
            java.lang.String r2 = "screens"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r21 = r2
            java.lang.String r2 = "seconds"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r22 = r2
            java.lang.String r2 = "regionId"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r23 = r2
            java.lang.String r2 = "audience"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x0307 }
            r24 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x0307 }
            r2.<init>()     // Catch:{ all -> 0x0307 }
        L_0x00ba:
            boolean r25 = r3.moveToNext()     // Catch:{ all -> 0x0307 }
            if (r25 == 0) goto L_0x00e6
            boolean r25 = r3.isNull(r5)     // Catch:{ all -> 0x0307 }
            if (r25 != 0) goto L_0x00ba
            r25 = r4
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x0307 }
            java.lang.Object r26 = r2.get(r4)     // Catch:{ all -> 0x0307 }
            java.util.ArrayList r26 = (java.util.ArrayList) r26     // Catch:{ all -> 0x0307 }
            if (r26 != 0) goto L_0x00df
            r26 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0307 }
            r15.<init>()     // Catch:{ all -> 0x0307 }
            r2.put(r4, r15)     // Catch:{ all -> 0x0307 }
            goto L_0x00e1
        L_0x00df:
            r26 = r15
        L_0x00e1:
            r4 = r25
            r15 = r26
            goto L_0x00ba
        L_0x00e6:
            r25 = r4
            r26 = r15
            r4 = -1
            r3.moveToPosition(r4)     // Catch:{ all -> 0x0307 }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r2)     // Catch:{ all -> 0x0307 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0307 }
            int r15 = r3.getCount()     // Catch:{ all -> 0x0307 }
            r4.<init>(r15)     // Catch:{ all -> 0x0307 }
        L_0x00fa:
            boolean r15 = r3.moveToNext()     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x02f5
            boolean r15 = r3.isNull(r0)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r5)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r6)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r7)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r8)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r9)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r10)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r11)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r12)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r13)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            boolean r15 = r3.isNull(r14)     // Catch:{ all -> 0x0307 }
            if (r15 == 0) goto L_0x01d8
            r15 = r26
            boolean r26 = r3.isNull(r15)     // Catch:{ all -> 0x0307 }
            if (r26 == 0) goto L_0x01da
            r26 = r4
            r4 = r25
            boolean r25 = r3.isNull(r4)     // Catch:{ all -> 0x0307 }
            if (r25 == 0) goto L_0x01de
            r25 = r2
            r2 = r17
            boolean r17 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r17 == 0) goto L_0x01d5
            r17 = r2
            r2 = r18
            boolean r18 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r18 == 0) goto L_0x01d2
            r18 = r2
            r2 = r19
            boolean r19 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r19 == 0) goto L_0x01cf
            r19 = r2
            r2 = r20
            boolean r20 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r20 == 0) goto L_0x01cc
            r20 = r2
            r2 = r21
            boolean r21 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r21 == 0) goto L_0x01c9
            r21 = r2
            r2 = r22
            boolean r22 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r22 == 0) goto L_0x01c6
            r22 = r2
            r2 = r23
            boolean r23 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r23 == 0) goto L_0x01c3
            r23 = r2
            r2 = r24
            boolean r24 = r3.isNull(r2)     // Catch:{ all -> 0x0307 }
            if (r24 != 0) goto L_0x01a7
            r24 = r2
            goto L_0x01e0
        L_0x01a7:
            r28 = r0
            r27 = r4
            r24 = r7
            r7 = r2
            r2 = 0
            r29 = r17
            r17 = r6
            r6 = r23
            r23 = r22
            r22 = r21
            r21 = r20
            r20 = r19
            r19 = r18
            r18 = r29
            goto L_0x02a9
        L_0x01c3:
            r23 = r2
            goto L_0x01e0
        L_0x01c6:
            r22 = r2
            goto L_0x01e0
        L_0x01c9:
            r21 = r2
            goto L_0x01e0
        L_0x01cc:
            r20 = r2
            goto L_0x01e0
        L_0x01cf:
            r19 = r2
            goto L_0x01e0
        L_0x01d2:
            r18 = r2
            goto L_0x01e0
        L_0x01d5:
            r17 = r2
            goto L_0x01e0
        L_0x01d8:
            r15 = r26
        L_0x01da:
            r26 = r4
            r4 = r25
        L_0x01de:
            r25 = r2
        L_0x01e0:
            com.urbanairship.automation.storage.ScheduleEntity r2 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x0307 }
            r2.<init>()     // Catch:{ all -> 0x0307 }
            r27 = r4
            int r4 = r3.getInt(r0)     // Catch:{ all -> 0x0307 }
            r2.id = r4     // Catch:{ all -> 0x0307 }
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x0307 }
            r2.scheduleId = r4     // Catch:{ all -> 0x0307 }
            java.lang.String r4 = r3.getString(r6)     // Catch:{ all -> 0x0307 }
            r2.group = r4     // Catch:{ all -> 0x0307 }
            java.lang.String r4 = r3.getString(r7)     // Catch:{ all -> 0x0307 }
            r28 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x0307 }
            com.urbanairship.json.JsonMap r0 = r0.jsonMapFromString(r4)     // Catch:{ all -> 0x0307 }
            r2.metadata = r0     // Catch:{ all -> 0x0307 }
            int r0 = r3.getInt(r8)     // Catch:{ all -> 0x0307 }
            r2.limit = r0     // Catch:{ all -> 0x0307 }
            int r0 = r3.getInt(r9)     // Catch:{ all -> 0x0307 }
            r2.priority = r0     // Catch:{ all -> 0x0307 }
            r0 = r6
            r4 = r7
            long r6 = r3.getLong(r10)     // Catch:{ all -> 0x0307 }
            r2.scheduleStart = r6     // Catch:{ all -> 0x0307 }
            long r6 = r3.getLong(r11)     // Catch:{ all -> 0x0307 }
            r2.scheduleEnd = r6     // Catch:{ all -> 0x0307 }
            long r6 = r3.getLong(r12)     // Catch:{ all -> 0x0307 }
            r2.editGracePeriod = r6     // Catch:{ all -> 0x0307 }
            long r6 = r3.getLong(r13)     // Catch:{ all -> 0x0307 }
            r2.interval = r6     // Catch:{ all -> 0x0307 }
            java.lang.String r6 = r3.getString(r14)     // Catch:{ all -> 0x0307 }
            r2.scheduleType = r6     // Catch:{ all -> 0x0307 }
            java.lang.String r6 = r3.getString(r15)     // Catch:{ all -> 0x0307 }
            com.urbanairship.automation.storage.Converters r7 = r1.__converters     // Catch:{ all -> 0x0307 }
            com.urbanairship.json.JsonValue r6 = r7.jsonValueFromString(r6)     // Catch:{ all -> 0x0307 }
            r2.data = r6     // Catch:{ all -> 0x0307 }
            r6 = r27
            int r7 = r3.getInt(r6)     // Catch:{ all -> 0x0307 }
            r2.count = r7     // Catch:{ all -> 0x0307 }
            r7 = r17
            r17 = r0
            int r0 = r3.getInt(r7)     // Catch:{ all -> 0x0307 }
            r2.executionState = r0     // Catch:{ all -> 0x0307 }
            r27 = r6
            r0 = r18
            r18 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x0307 }
            r2.executionStateChangeDate = r6     // Catch:{ all -> 0x0307 }
            r6 = r19
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x0307 }
            r19 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x0307 }
            com.urbanairship.automation.TriggerContext r0 = r0.triggerContextFromString(r7)     // Catch:{ all -> 0x0307 }
            r2.triggerContext = r0     // Catch:{ all -> 0x0307 }
            r0 = r20
            int r7 = r3.getInt(r0)     // Catch:{ all -> 0x0307 }
            r2.appState = r7     // Catch:{ all -> 0x0307 }
            r7 = r21
            java.lang.String r20 = r3.getString(r7)     // Catch:{ all -> 0x0307 }
            r21 = r0
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r20)     // Catch:{ all -> 0x0307 }
            r2.screens = r0     // Catch:{ all -> 0x0307 }
            r20 = r6
            r0 = r22
            r22 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x0307 }
            r2.seconds = r6     // Catch:{ all -> 0x0307 }
            r6 = r23
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x0307 }
            r2.regionId = r7     // Catch:{ all -> 0x0307 }
            r23 = r0
            r7 = r24
            java.lang.String r0 = r3.getString(r7)     // Catch:{ all -> 0x0307 }
            r24 = r4
            com.urbanairship.automation.storage.Converters r4 = r1.__converters     // Catch:{ all -> 0x0307 }
            com.urbanairship.automation.Audience r0 = r4.audienceFromString(r0)     // Catch:{ all -> 0x0307 }
            r2.audience = r0     // Catch:{ all -> 0x0307 }
        L_0x02a9:
            boolean r0 = r3.isNull(r5)     // Catch:{ all -> 0x0307 }
            if (r0 != 0) goto L_0x02bc
            java.lang.String r0 = r3.getString(r5)     // Catch:{ all -> 0x0307 }
            r4 = r25
            java.lang.Object r0 = r4.get(r0)     // Catch:{ all -> 0x0307 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0307 }
            goto L_0x02bf
        L_0x02bc:
            r4 = r25
            r0 = 0
        L_0x02bf:
            if (r0 != 0) goto L_0x02c6
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0307 }
            r0.<init>()     // Catch:{ all -> 0x0307 }
        L_0x02c6:
            r25 = r4
            com.urbanairship.automation.storage.FullSchedule r4 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x0307 }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x0307 }
            r0 = r26
            r0.add(r4)     // Catch:{ all -> 0x0307 }
            r4 = r0
            r26 = r15
            r2 = r25
            r25 = r27
            r0 = r28
            r29 = r23
            r23 = r6
            r6 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r29
            r30 = r24
            r24 = r7
            r7 = r30
            goto L_0x00fa
        L_0x02f5:
            r0 = r4
            androidx.room.RoomDatabase r2 = r1.__db     // Catch:{ all -> 0x0307 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0307 }
            r3.close()     // Catch:{ all -> 0x0313 }
            r16.release()     // Catch:{ all -> 0x0313 }
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            return r0
        L_0x0307:
            r0 = move-exception
            goto L_0x030c
        L_0x0309:
            r0 = move-exception
            r16 = r2
        L_0x030c:
            r3.close()     // Catch:{ all -> 0x0313 }
            r16.release()     // Catch:{ all -> 0x0313 }
            throw r0     // Catch:{ all -> 0x0313 }
        L_0x0313:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedulesWithGroup(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x02eb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x02d4 A[Catch:{ all -> 0x032c }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02e1 A[Catch:{ all -> 0x032c }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02e6 A[Catch:{ all -> 0x032c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.urbanairship.automation.storage.FullSchedule> getSchedulesWithStates(int... r32) {
        /*
            r31 = this;
            r1 = r31
            r0 = r32
            java.lang.StringBuilder r2 = androidx.room.util.StringUtil.newStringBuilder()
            java.lang.String r3 = "SELECT "
            r2.append(r3)
            java.lang.String r3 = "*"
            r2.append(r3)
            java.lang.String r3 = " FROM schedules WHERE (executionState IN ("
            r2.append(r3)
            int r3 = r0.length
            androidx.room.util.StringUtil.appendPlaceholders(r2, r3)
            java.lang.String r4 = "))"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r4 = 0
            int r3 = r3 + r4
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r2, r3)
            int r3 = r0.length
            r5 = 1
            r6 = 1
        L_0x002d:
            if (r4 >= r3) goto L_0x0039
            r7 = r0[r4]
            long r7 = (long) r7
            r2.bindLong(r6, r7)
            int r6 = r6 + r5
            int r4 = r4 + 1
            goto L_0x002d
        L_0x0039:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x0338 }
            r3 = 0
            android.database.Cursor r4 = androidx.room.util.DBUtil.query(r0, r2, r5, r3)     // Catch:{ all -> 0x0338 }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r0)     // Catch:{ all -> 0x032e }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r5)     // Catch:{ all -> 0x032e }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r6)     // Catch:{ all -> 0x032e }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r7)     // Catch:{ all -> 0x032e }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r8)     // Catch:{ all -> 0x032e }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r9)     // Catch:{ all -> 0x032e }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r10)     // Catch:{ all -> 0x032e }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r11)     // Catch:{ all -> 0x032e }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r12)     // Catch:{ all -> 0x032e }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r13)     // Catch:{ all -> 0x032e }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r14)     // Catch:{ all -> 0x032e }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r15)     // Catch:{ all -> 0x032e }
            java.lang.String r3 = "count"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r3)     // Catch:{ all -> 0x032e }
            r16 = r2
            java.lang.String r2 = "executionState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x032c }
            r17 = r2
            java.lang.String r2 = "executionStateChangeDate"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x032c }
            r18 = r2
            java.lang.String r2 = "triggerContext"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x032c }
            r19 = r2
            java.lang.String r2 = "appState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x032c }
            r20 = r2
            java.lang.String r2 = "screens"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x032c }
            r21 = r2
            java.lang.String r2 = "seconds"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x032c }
            r22 = r2
            java.lang.String r2 = "regionId"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x032c }
            r23 = r2
            java.lang.String r2 = "audience"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r4, r2)     // Catch:{ all -> 0x032c }
            r24 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x032c }
            r2.<init>()     // Catch:{ all -> 0x032c }
        L_0x00df:
            boolean r25 = r4.moveToNext()     // Catch:{ all -> 0x032c }
            if (r25 == 0) goto L_0x010b
            boolean r25 = r4.isNull(r5)     // Catch:{ all -> 0x032c }
            if (r25 != 0) goto L_0x00df
            r25 = r3
            java.lang.String r3 = r4.getString(r5)     // Catch:{ all -> 0x032c }
            java.lang.Object r26 = r2.get(r3)     // Catch:{ all -> 0x032c }
            java.util.ArrayList r26 = (java.util.ArrayList) r26     // Catch:{ all -> 0x032c }
            if (r26 != 0) goto L_0x0104
            r26 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x032c }
            r15.<init>()     // Catch:{ all -> 0x032c }
            r2.put(r3, r15)     // Catch:{ all -> 0x032c }
            goto L_0x0106
        L_0x0104:
            r26 = r15
        L_0x0106:
            r3 = r25
            r15 = r26
            goto L_0x00df
        L_0x010b:
            r25 = r3
            r26 = r15
            r3 = -1
            r4.moveToPosition(r3)     // Catch:{ all -> 0x032c }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r2)     // Catch:{ all -> 0x032c }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x032c }
            int r15 = r4.getCount()     // Catch:{ all -> 0x032c }
            r3.<init>(r15)     // Catch:{ all -> 0x032c }
        L_0x011f:
            boolean r15 = r4.moveToNext()     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x031a
            boolean r15 = r4.isNull(r0)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r5)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r6)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r7)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r8)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r9)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r10)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r11)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r12)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r13)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            boolean r15 = r4.isNull(r14)     // Catch:{ all -> 0x032c }
            if (r15 == 0) goto L_0x01fd
            r15 = r26
            boolean r26 = r4.isNull(r15)     // Catch:{ all -> 0x032c }
            if (r26 == 0) goto L_0x01ff
            r26 = r3
            r3 = r25
            boolean r25 = r4.isNull(r3)     // Catch:{ all -> 0x032c }
            if (r25 == 0) goto L_0x0203
            r25 = r2
            r2 = r17
            boolean r17 = r4.isNull(r2)     // Catch:{ all -> 0x032c }
            if (r17 == 0) goto L_0x01fa
            r17 = r2
            r2 = r18
            boolean r18 = r4.isNull(r2)     // Catch:{ all -> 0x032c }
            if (r18 == 0) goto L_0x01f7
            r18 = r2
            r2 = r19
            boolean r19 = r4.isNull(r2)     // Catch:{ all -> 0x032c }
            if (r19 == 0) goto L_0x01f4
            r19 = r2
            r2 = r20
            boolean r20 = r4.isNull(r2)     // Catch:{ all -> 0x032c }
            if (r20 == 0) goto L_0x01f1
            r20 = r2
            r2 = r21
            boolean r21 = r4.isNull(r2)     // Catch:{ all -> 0x032c }
            if (r21 == 0) goto L_0x01ee
            r21 = r2
            r2 = r22
            boolean r22 = r4.isNull(r2)     // Catch:{ all -> 0x032c }
            if (r22 == 0) goto L_0x01eb
            r22 = r2
            r2 = r23
            boolean r23 = r4.isNull(r2)     // Catch:{ all -> 0x032c }
            if (r23 == 0) goto L_0x01e8
            r23 = r2
            r2 = r24
            boolean r24 = r4.isNull(r2)     // Catch:{ all -> 0x032c }
            if (r24 != 0) goto L_0x01cc
            r24 = r2
            goto L_0x0205
        L_0x01cc:
            r28 = r0
            r27 = r3
            r24 = r7
            r7 = r2
            r2 = 0
            r29 = r17
            r17 = r6
            r6 = r23
            r23 = r22
            r22 = r21
            r21 = r20
            r20 = r19
            r19 = r18
            r18 = r29
            goto L_0x02ce
        L_0x01e8:
            r23 = r2
            goto L_0x0205
        L_0x01eb:
            r22 = r2
            goto L_0x0205
        L_0x01ee:
            r21 = r2
            goto L_0x0205
        L_0x01f1:
            r20 = r2
            goto L_0x0205
        L_0x01f4:
            r19 = r2
            goto L_0x0205
        L_0x01f7:
            r18 = r2
            goto L_0x0205
        L_0x01fa:
            r17 = r2
            goto L_0x0205
        L_0x01fd:
            r15 = r26
        L_0x01ff:
            r26 = r3
            r3 = r25
        L_0x0203:
            r25 = r2
        L_0x0205:
            com.urbanairship.automation.storage.ScheduleEntity r2 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x032c }
            r2.<init>()     // Catch:{ all -> 0x032c }
            r27 = r3
            int r3 = r4.getInt(r0)     // Catch:{ all -> 0x032c }
            r2.id = r3     // Catch:{ all -> 0x032c }
            java.lang.String r3 = r4.getString(r5)     // Catch:{ all -> 0x032c }
            r2.scheduleId = r3     // Catch:{ all -> 0x032c }
            java.lang.String r3 = r4.getString(r6)     // Catch:{ all -> 0x032c }
            r2.group = r3     // Catch:{ all -> 0x032c }
            java.lang.String r3 = r4.getString(r7)     // Catch:{ all -> 0x032c }
            r28 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x032c }
            com.urbanairship.json.JsonMap r0 = r0.jsonMapFromString(r3)     // Catch:{ all -> 0x032c }
            r2.metadata = r0     // Catch:{ all -> 0x032c }
            int r0 = r4.getInt(r8)     // Catch:{ all -> 0x032c }
            r2.limit = r0     // Catch:{ all -> 0x032c }
            int r0 = r4.getInt(r9)     // Catch:{ all -> 0x032c }
            r2.priority = r0     // Catch:{ all -> 0x032c }
            r0 = r6
            r3 = r7
            long r6 = r4.getLong(r10)     // Catch:{ all -> 0x032c }
            r2.scheduleStart = r6     // Catch:{ all -> 0x032c }
            long r6 = r4.getLong(r11)     // Catch:{ all -> 0x032c }
            r2.scheduleEnd = r6     // Catch:{ all -> 0x032c }
            long r6 = r4.getLong(r12)     // Catch:{ all -> 0x032c }
            r2.editGracePeriod = r6     // Catch:{ all -> 0x032c }
            long r6 = r4.getLong(r13)     // Catch:{ all -> 0x032c }
            r2.interval = r6     // Catch:{ all -> 0x032c }
            java.lang.String r6 = r4.getString(r14)     // Catch:{ all -> 0x032c }
            r2.scheduleType = r6     // Catch:{ all -> 0x032c }
            java.lang.String r6 = r4.getString(r15)     // Catch:{ all -> 0x032c }
            com.urbanairship.automation.storage.Converters r7 = r1.__converters     // Catch:{ all -> 0x032c }
            com.urbanairship.json.JsonValue r6 = r7.jsonValueFromString(r6)     // Catch:{ all -> 0x032c }
            r2.data = r6     // Catch:{ all -> 0x032c }
            r6 = r27
            int r7 = r4.getInt(r6)     // Catch:{ all -> 0x032c }
            r2.count = r7     // Catch:{ all -> 0x032c }
            r7 = r17
            r17 = r0
            int r0 = r4.getInt(r7)     // Catch:{ all -> 0x032c }
            r2.executionState = r0     // Catch:{ all -> 0x032c }
            r27 = r6
            r0 = r18
            r18 = r7
            long r6 = r4.getLong(r0)     // Catch:{ all -> 0x032c }
            r2.executionStateChangeDate = r6     // Catch:{ all -> 0x032c }
            r6 = r19
            java.lang.String r7 = r4.getString(r6)     // Catch:{ all -> 0x032c }
            r19 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x032c }
            com.urbanairship.automation.TriggerContext r0 = r0.triggerContextFromString(r7)     // Catch:{ all -> 0x032c }
            r2.triggerContext = r0     // Catch:{ all -> 0x032c }
            r0 = r20
            int r7 = r4.getInt(r0)     // Catch:{ all -> 0x032c }
            r2.appState = r7     // Catch:{ all -> 0x032c }
            r7 = r21
            java.lang.String r20 = r4.getString(r7)     // Catch:{ all -> 0x032c }
            r21 = r0
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r20)     // Catch:{ all -> 0x032c }
            r2.screens = r0     // Catch:{ all -> 0x032c }
            r20 = r6
            r0 = r22
            r22 = r7
            long r6 = r4.getLong(r0)     // Catch:{ all -> 0x032c }
            r2.seconds = r6     // Catch:{ all -> 0x032c }
            r6 = r23
            java.lang.String r7 = r4.getString(r6)     // Catch:{ all -> 0x032c }
            r2.regionId = r7     // Catch:{ all -> 0x032c }
            r23 = r0
            r7 = r24
            java.lang.String r0 = r4.getString(r7)     // Catch:{ all -> 0x032c }
            r24 = r3
            com.urbanairship.automation.storage.Converters r3 = r1.__converters     // Catch:{ all -> 0x032c }
            com.urbanairship.automation.Audience r0 = r3.audienceFromString(r0)     // Catch:{ all -> 0x032c }
            r2.audience = r0     // Catch:{ all -> 0x032c }
        L_0x02ce:
            boolean r0 = r4.isNull(r5)     // Catch:{ all -> 0x032c }
            if (r0 != 0) goto L_0x02e1
            java.lang.String r0 = r4.getString(r5)     // Catch:{ all -> 0x032c }
            r3 = r25
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x032c }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x032c }
            goto L_0x02e4
        L_0x02e1:
            r3 = r25
            r0 = 0
        L_0x02e4:
            if (r0 != 0) goto L_0x02eb
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x032c }
            r0.<init>()     // Catch:{ all -> 0x032c }
        L_0x02eb:
            r25 = r3
            com.urbanairship.automation.storage.FullSchedule r3 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x032c }
            r3.<init>(r2, r0)     // Catch:{ all -> 0x032c }
            r0 = r26
            r0.add(r3)     // Catch:{ all -> 0x032c }
            r3 = r0
            r26 = r15
            r2 = r25
            r25 = r27
            r0 = r28
            r29 = r23
            r23 = r6
            r6 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r29
            r30 = r24
            r24 = r7
            r7 = r30
            goto L_0x011f
        L_0x031a:
            r0 = r3
            androidx.room.RoomDatabase r2 = r1.__db     // Catch:{ all -> 0x032c }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x032c }
            r4.close()     // Catch:{ all -> 0x0338 }
            r16.release()     // Catch:{ all -> 0x0338 }
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            return r0
        L_0x032c:
            r0 = move-exception
            goto L_0x0331
        L_0x032e:
            r0 = move-exception
            r16 = r2
        L_0x0331:
            r4.close()     // Catch:{ all -> 0x0338 }
            r16.release()     // Catch:{ all -> 0x0338 }
            throw r0     // Catch:{ all -> 0x0338 }
        L_0x0338:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getSchedulesWithStates(int[]):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x02bc A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x02a5 A[Catch:{ all -> 0x02fd }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02b2 A[Catch:{ all -> 0x02fd }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x02b7 A[Catch:{ all -> 0x02fd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.urbanairship.automation.storage.FullSchedule> getActiveExpiredSchedules() {
        /*
            r31 = this;
            r1 = r31
            java.lang.String r0 = "SELECT * FROM schedules WHERE (executionState != 4) AND (scheduleEnd >= 0) AND (scheduleEnd <= strftime('%s', 'now') * 1000)"
            r2 = 0
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r0, r2)
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r0.beginTransaction()
            androidx.room.RoomDatabase r0 = r1.__db     // Catch:{ all -> 0x0309 }
            r3 = 1
            r4 = 0
            android.database.Cursor r3 = androidx.room.util.DBUtil.query(r0, r2, r3, r4)     // Catch:{ all -> 0x0309 }
            java.lang.String r0 = "id"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r0)     // Catch:{ all -> 0x02ff }
            java.lang.String r5 = "scheduleId"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r5)     // Catch:{ all -> 0x02ff }
            java.lang.String r6 = "group"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r6)     // Catch:{ all -> 0x02ff }
            java.lang.String r7 = "metadata"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r7)     // Catch:{ all -> 0x02ff }
            java.lang.String r8 = "limit"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r8)     // Catch:{ all -> 0x02ff }
            java.lang.String r9 = "priority"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r9)     // Catch:{ all -> 0x02ff }
            java.lang.String r10 = "scheduleStart"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r10)     // Catch:{ all -> 0x02ff }
            java.lang.String r11 = "scheduleEnd"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r11)     // Catch:{ all -> 0x02ff }
            java.lang.String r12 = "editGracePeriod"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r12)     // Catch:{ all -> 0x02ff }
            java.lang.String r13 = "interval"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r13)     // Catch:{ all -> 0x02ff }
            java.lang.String r14 = "scheduleType"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r14)     // Catch:{ all -> 0x02ff }
            java.lang.String r15 = "data"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r15)     // Catch:{ all -> 0x02ff }
            java.lang.String r4 = "count"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r4)     // Catch:{ all -> 0x02ff }
            r16 = r2
            java.lang.String r2 = "executionState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r17 = r2
            java.lang.String r2 = "executionStateChangeDate"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r18 = r2
            java.lang.String r2 = "triggerContext"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r19 = r2
            java.lang.String r2 = "appState"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r20 = r2
            java.lang.String r2 = "screens"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r21 = r2
            java.lang.String r2 = "seconds"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r22 = r2
            java.lang.String r2 = "regionId"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r23 = r2
            java.lang.String r2 = "audience"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r3, r2)     // Catch:{ all -> 0x02fd }
            r24 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x02fd }
            r2.<init>()     // Catch:{ all -> 0x02fd }
        L_0x00b0:
            boolean r25 = r3.moveToNext()     // Catch:{ all -> 0x02fd }
            if (r25 == 0) goto L_0x00dc
            boolean r25 = r3.isNull(r5)     // Catch:{ all -> 0x02fd }
            if (r25 != 0) goto L_0x00b0
            r25 = r4
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x02fd }
            java.lang.Object r26 = r2.get(r4)     // Catch:{ all -> 0x02fd }
            java.util.ArrayList r26 = (java.util.ArrayList) r26     // Catch:{ all -> 0x02fd }
            if (r26 != 0) goto L_0x00d5
            r26 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x02fd }
            r15.<init>()     // Catch:{ all -> 0x02fd }
            r2.put(r4, r15)     // Catch:{ all -> 0x02fd }
            goto L_0x00d7
        L_0x00d5:
            r26 = r15
        L_0x00d7:
            r4 = r25
            r15 = r26
            goto L_0x00b0
        L_0x00dc:
            r25 = r4
            r26 = r15
            r4 = -1
            r3.moveToPosition(r4)     // Catch:{ all -> 0x02fd }
            r1.__fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(r2)     // Catch:{ all -> 0x02fd }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x02fd }
            int r15 = r3.getCount()     // Catch:{ all -> 0x02fd }
            r4.<init>(r15)     // Catch:{ all -> 0x02fd }
        L_0x00f0:
            boolean r15 = r3.moveToNext()     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x02eb
            boolean r15 = r3.isNull(r0)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r5)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r6)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r7)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r8)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r9)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r10)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r11)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r12)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r13)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            boolean r15 = r3.isNull(r14)     // Catch:{ all -> 0x02fd }
            if (r15 == 0) goto L_0x01ce
            r15 = r26
            boolean r26 = r3.isNull(r15)     // Catch:{ all -> 0x02fd }
            if (r26 == 0) goto L_0x01d0
            r26 = r4
            r4 = r25
            boolean r25 = r3.isNull(r4)     // Catch:{ all -> 0x02fd }
            if (r25 == 0) goto L_0x01d4
            r25 = r2
            r2 = r17
            boolean r17 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r17 == 0) goto L_0x01cb
            r17 = r2
            r2 = r18
            boolean r18 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r18 == 0) goto L_0x01c8
            r18 = r2
            r2 = r19
            boolean r19 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r19 == 0) goto L_0x01c5
            r19 = r2
            r2 = r20
            boolean r20 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r20 == 0) goto L_0x01c2
            r20 = r2
            r2 = r21
            boolean r21 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r21 == 0) goto L_0x01bf
            r21 = r2
            r2 = r22
            boolean r22 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r22 == 0) goto L_0x01bc
            r22 = r2
            r2 = r23
            boolean r23 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r23 == 0) goto L_0x01b9
            r23 = r2
            r2 = r24
            boolean r24 = r3.isNull(r2)     // Catch:{ all -> 0x02fd }
            if (r24 != 0) goto L_0x019d
            r24 = r2
            goto L_0x01d6
        L_0x019d:
            r28 = r0
            r27 = r4
            r24 = r7
            r7 = r2
            r2 = 0
            r29 = r17
            r17 = r6
            r6 = r23
            r23 = r22
            r22 = r21
            r21 = r20
            r20 = r19
            r19 = r18
            r18 = r29
            goto L_0x029f
        L_0x01b9:
            r23 = r2
            goto L_0x01d6
        L_0x01bc:
            r22 = r2
            goto L_0x01d6
        L_0x01bf:
            r21 = r2
            goto L_0x01d6
        L_0x01c2:
            r20 = r2
            goto L_0x01d6
        L_0x01c5:
            r19 = r2
            goto L_0x01d6
        L_0x01c8:
            r18 = r2
            goto L_0x01d6
        L_0x01cb:
            r17 = r2
            goto L_0x01d6
        L_0x01ce:
            r15 = r26
        L_0x01d0:
            r26 = r4
            r4 = r25
        L_0x01d4:
            r25 = r2
        L_0x01d6:
            com.urbanairship.automation.storage.ScheduleEntity r2 = new com.urbanairship.automation.storage.ScheduleEntity     // Catch:{ all -> 0x02fd }
            r2.<init>()     // Catch:{ all -> 0x02fd }
            r27 = r4
            int r4 = r3.getInt(r0)     // Catch:{ all -> 0x02fd }
            r2.id = r4     // Catch:{ all -> 0x02fd }
            java.lang.String r4 = r3.getString(r5)     // Catch:{ all -> 0x02fd }
            r2.scheduleId = r4     // Catch:{ all -> 0x02fd }
            java.lang.String r4 = r3.getString(r6)     // Catch:{ all -> 0x02fd }
            r2.group = r4     // Catch:{ all -> 0x02fd }
            java.lang.String r4 = r3.getString(r7)     // Catch:{ all -> 0x02fd }
            r28 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x02fd }
            com.urbanairship.json.JsonMap r0 = r0.jsonMapFromString(r4)     // Catch:{ all -> 0x02fd }
            r2.metadata = r0     // Catch:{ all -> 0x02fd }
            int r0 = r3.getInt(r8)     // Catch:{ all -> 0x02fd }
            r2.limit = r0     // Catch:{ all -> 0x02fd }
            int r0 = r3.getInt(r9)     // Catch:{ all -> 0x02fd }
            r2.priority = r0     // Catch:{ all -> 0x02fd }
            r0 = r6
            r4 = r7
            long r6 = r3.getLong(r10)     // Catch:{ all -> 0x02fd }
            r2.scheduleStart = r6     // Catch:{ all -> 0x02fd }
            long r6 = r3.getLong(r11)     // Catch:{ all -> 0x02fd }
            r2.scheduleEnd = r6     // Catch:{ all -> 0x02fd }
            long r6 = r3.getLong(r12)     // Catch:{ all -> 0x02fd }
            r2.editGracePeriod = r6     // Catch:{ all -> 0x02fd }
            long r6 = r3.getLong(r13)     // Catch:{ all -> 0x02fd }
            r2.interval = r6     // Catch:{ all -> 0x02fd }
            java.lang.String r6 = r3.getString(r14)     // Catch:{ all -> 0x02fd }
            r2.scheduleType = r6     // Catch:{ all -> 0x02fd }
            java.lang.String r6 = r3.getString(r15)     // Catch:{ all -> 0x02fd }
            com.urbanairship.automation.storage.Converters r7 = r1.__converters     // Catch:{ all -> 0x02fd }
            com.urbanairship.json.JsonValue r6 = r7.jsonValueFromString(r6)     // Catch:{ all -> 0x02fd }
            r2.data = r6     // Catch:{ all -> 0x02fd }
            r6 = r27
            int r7 = r3.getInt(r6)     // Catch:{ all -> 0x02fd }
            r2.count = r7     // Catch:{ all -> 0x02fd }
            r7 = r17
            r17 = r0
            int r0 = r3.getInt(r7)     // Catch:{ all -> 0x02fd }
            r2.executionState = r0     // Catch:{ all -> 0x02fd }
            r27 = r6
            r0 = r18
            r18 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x02fd }
            r2.executionStateChangeDate = r6     // Catch:{ all -> 0x02fd }
            r6 = r19
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x02fd }
            r19 = r0
            com.urbanairship.automation.storage.Converters r0 = r1.__converters     // Catch:{ all -> 0x02fd }
            com.urbanairship.automation.TriggerContext r0 = r0.triggerContextFromString(r7)     // Catch:{ all -> 0x02fd }
            r2.triggerContext = r0     // Catch:{ all -> 0x02fd }
            r0 = r20
            int r7 = r3.getInt(r0)     // Catch:{ all -> 0x02fd }
            r2.appState = r7     // Catch:{ all -> 0x02fd }
            r7 = r21
            java.lang.String r20 = r3.getString(r7)     // Catch:{ all -> 0x02fd }
            r21 = r0
            java.util.List r0 = com.urbanairship.automation.storage.Converters.stringArrayFromString(r20)     // Catch:{ all -> 0x02fd }
            r2.screens = r0     // Catch:{ all -> 0x02fd }
            r20 = r6
            r0 = r22
            r22 = r7
            long r6 = r3.getLong(r0)     // Catch:{ all -> 0x02fd }
            r2.seconds = r6     // Catch:{ all -> 0x02fd }
            r6 = r23
            java.lang.String r7 = r3.getString(r6)     // Catch:{ all -> 0x02fd }
            r2.regionId = r7     // Catch:{ all -> 0x02fd }
            r23 = r0
            r7 = r24
            java.lang.String r0 = r3.getString(r7)     // Catch:{ all -> 0x02fd }
            r24 = r4
            com.urbanairship.automation.storage.Converters r4 = r1.__converters     // Catch:{ all -> 0x02fd }
            com.urbanairship.automation.Audience r0 = r4.audienceFromString(r0)     // Catch:{ all -> 0x02fd }
            r2.audience = r0     // Catch:{ all -> 0x02fd }
        L_0x029f:
            boolean r0 = r3.isNull(r5)     // Catch:{ all -> 0x02fd }
            if (r0 != 0) goto L_0x02b2
            java.lang.String r0 = r3.getString(r5)     // Catch:{ all -> 0x02fd }
            r4 = r25
            java.lang.Object r0 = r4.get(r0)     // Catch:{ all -> 0x02fd }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x02fd }
            goto L_0x02b5
        L_0x02b2:
            r4 = r25
            r0 = 0
        L_0x02b5:
            if (r0 != 0) goto L_0x02bc
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x02fd }
            r0.<init>()     // Catch:{ all -> 0x02fd }
        L_0x02bc:
            r25 = r4
            com.urbanairship.automation.storage.FullSchedule r4 = new com.urbanairship.automation.storage.FullSchedule     // Catch:{ all -> 0x02fd }
            r4.<init>(r2, r0)     // Catch:{ all -> 0x02fd }
            r0 = r26
            r0.add(r4)     // Catch:{ all -> 0x02fd }
            r4 = r0
            r26 = r15
            r2 = r25
            r25 = r27
            r0 = r28
            r29 = r23
            r23 = r6
            r6 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r22
            r22 = r29
            r30 = r24
            r24 = r7
            r7 = r30
            goto L_0x00f0
        L_0x02eb:
            r0 = r4
            androidx.room.RoomDatabase r2 = r1.__db     // Catch:{ all -> 0x02fd }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x02fd }
            r3.close()     // Catch:{ all -> 0x0309 }
            r16.release()     // Catch:{ all -> 0x0309 }
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            return r0
        L_0x02fd:
            r0 = move-exception
            goto L_0x0302
        L_0x02ff:
            r0 = move-exception
            r16 = r2
        L_0x0302:
            r3.close()     // Catch:{ all -> 0x0309 }
            r16.release()     // Catch:{ all -> 0x0309 }
            throw r0     // Catch:{ all -> 0x0309 }
        L_0x0309:
            r0 = move-exception
            androidx.room.RoomDatabase r2 = r1.__db
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.storage.AutomationDao_Impl.getActiveExpiredSchedules():java.util.List");
    }

    public List<TriggerEntity> getActiveTriggers(int i, String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT triggers.* FROM triggers JOIN schedules ON schedules.scheduleId = triggers.parentScheduleId WHERE (schedules.scheduleId = ?)AND (triggers.triggerType = ?) AND ((triggers.isCancellation = 1 AND + schedules.executionState IN (1,5,6))OR (triggers.isCancellation = 0 AND + schedules.executionState = 0))AND (schedules.scheduleStart < 0 OR schedules.scheduleStart <= strftime('%s', 'now') * 1000)", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "triggerType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "goal");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "jsonPredicate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isCancellation");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_PROGRESS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "parentScheduleId");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TriggerEntity triggerEntity = new TriggerEntity();
                triggerEntity.id = query.getInt(columnIndexOrThrow);
                triggerEntity.triggerType = query.getInt(columnIndexOrThrow2);
                triggerEntity.goal = query.getDouble(columnIndexOrThrow3);
                triggerEntity.jsonPredicate = this.__converters.jsonPredicateFromString(query.getString(columnIndexOrThrow4));
                triggerEntity.isCancellation = query.getInt(columnIndexOrThrow5) != 0;
                triggerEntity.progress = query.getDouble(columnIndexOrThrow6);
                triggerEntity.parentScheduleId = query.getString(columnIndexOrThrow7);
                arrayList.add(triggerEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<TriggerEntity> getActiveTriggers(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT triggers.* FROM triggers JOIN schedules ON schedules.scheduleId = triggers.parentScheduleId AND (triggers.triggerType = ?) AND ((triggers.isCancellation = 1 AND + schedules.executionState IN (1,5,6))OR (triggers.isCancellation = 0 AND + schedules.executionState = 0))AND (schedules.scheduleStart < 0 OR schedules.scheduleStart <= strftime('%s', 'now') * 1000)", 1);
        acquire.bindLong(1, (long) i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "triggerType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "goal");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "jsonPredicate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isCancellation");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_PROGRESS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "parentScheduleId");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TriggerEntity triggerEntity = new TriggerEntity();
                triggerEntity.id = query.getInt(columnIndexOrThrow);
                triggerEntity.triggerType = query.getInt(columnIndexOrThrow2);
                triggerEntity.goal = query.getDouble(columnIndexOrThrow3);
                triggerEntity.jsonPredicate = this.__converters.jsonPredicateFromString(query.getString(columnIndexOrThrow4));
                triggerEntity.isCancellation = query.getInt(columnIndexOrThrow5) != 0;
                triggerEntity.progress = query.getDouble(columnIndexOrThrow6);
                triggerEntity.parentScheduleId = query.getString(columnIndexOrThrow7);
                arrayList.add(triggerEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    private void __fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(ArrayMap<String, ArrayList<TriggerEntity>> arrayMap) {
        int i;
        ArrayMap<String, ArrayList<TriggerEntity>> arrayMap2 = arrayMap;
        Set<String> keySet = arrayMap.keySet();
        if (!keySet.isEmpty()) {
            if (arrayMap.size() > 999) {
                ArrayMap arrayMap3 = new ArrayMap((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                int size = arrayMap.size();
                int i2 = 0;
                loop0:
                while (true) {
                    i = 0;
                    while (i2 < size) {
                        arrayMap3.put(arrayMap2.keyAt(i2), arrayMap2.valueAt(i2));
                        i2++;
                        i++;
                        if (i == 999) {
                            __fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(arrayMap3);
                            arrayMap3 = new ArrayMap((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                        }
                    }
                    break loop0;
                }
                if (i > 0) {
                    __fetchRelationshiptriggersAscomUrbanairshipAutomationStorageTriggerEntity(arrayMap3);
                    return;
                }
                return;
            }
            StringBuilder newStringBuilder = StringUtil.newStringBuilder();
            newStringBuilder.append("SELECT `id`,`triggerType`,`goal`,`jsonPredicate`,`isCancellation`,`progress`,`parentScheduleId` FROM `triggers` WHERE `parentScheduleId` IN (");
            int size2 = keySet.size();
            StringUtil.appendPlaceholders(newStringBuilder, size2);
            newStringBuilder.append(")");
            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
            int i3 = 1;
            for (String next : keySet) {
                if (next == null) {
                    acquire.bindNull(i3);
                } else {
                    acquire.bindString(i3, next);
                }
                i3++;
            }
            Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
            try {
                int columnIndex = CursorUtil.getColumnIndex(query, "parentScheduleId");
                int i4 = -1;
                if (columnIndex != -1) {
                    int columnIndex2 = CursorUtil.getColumnIndex(query, "id");
                    int columnIndex3 = CursorUtil.getColumnIndex(query, "triggerType");
                    int columnIndex4 = CursorUtil.getColumnIndex(query, "goal");
                    int columnIndex5 = CursorUtil.getColumnIndex(query, "jsonPredicate");
                    int columnIndex6 = CursorUtil.getColumnIndex(query, "isCancellation");
                    int columnIndex7 = CursorUtil.getColumnIndex(query, NotificationCompat.CATEGORY_PROGRESS);
                    int columnIndex8 = CursorUtil.getColumnIndex(query, "parentScheduleId");
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndex)) {
                            ArrayList arrayList = arrayMap2.get(query.getString(columnIndex));
                            if (arrayList != null) {
                                TriggerEntity triggerEntity = new TriggerEntity();
                                if (columnIndex2 != i4) {
                                    triggerEntity.id = query.getInt(columnIndex2);
                                }
                                if (columnIndex3 != i4) {
                                    triggerEntity.triggerType = query.getInt(columnIndex3);
                                }
                                if (columnIndex4 != i4) {
                                    triggerEntity.goal = query.getDouble(columnIndex4);
                                }
                                int i5 = -1;
                                if (columnIndex5 != -1) {
                                    triggerEntity.jsonPredicate = this.__converters.jsonPredicateFromString(query.getString(columnIndex5));
                                    i5 = -1;
                                }
                                if (columnIndex6 != i5) {
                                    triggerEntity.isCancellation = query.getInt(columnIndex6) != 0;
                                    i5 = -1;
                                }
                                if (columnIndex7 != i5) {
                                    triggerEntity.progress = query.getDouble(columnIndex7);
                                }
                                if (columnIndex8 != -1) {
                                    triggerEntity.parentScheduleId = query.getString(columnIndex8);
                                }
                                arrayList.add(triggerEntity);
                            }
                        }
                        i4 = -1;
                    }
                    query.close();
                }
            } finally {
                query.close();
            }
        }
    }
}
