package com.forasoft.homewavvisitor.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics;

public final class UserAnalyticsDao_Impl implements UserAnalyticsDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfUserAnalytics;

    public UserAnalyticsDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfUserAnalytics = new EntityInsertionAdapter<UserAnalytics>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `UserAnalytics`(`id`,`user_id`,`first_call_done`,`call_day_timestamp`,`call_day_count`,`call_week_timestamp`,`call_week_count`,`no_money_reported`,`less_two_funds_reported`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, UserAnalytics userAnalytics) {
                supportSQLiteStatement.bindLong(1, (long) userAnalytics.getId());
                supportSQLiteStatement.bindLong(2, userAnalytics.getUser_id());
                supportSQLiteStatement.bindLong(3, userAnalytics.getFirst_call_done() ? 1 : 0);
                if (userAnalytics.getCall_day_timestamp() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, userAnalytics.getCall_day_timestamp().longValue());
                }
                supportSQLiteStatement.bindLong(5, (long) userAnalytics.getCall_day_count());
                if (userAnalytics.getCall_week_timestamp() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindLong(6, userAnalytics.getCall_week_timestamp().longValue());
                }
                supportSQLiteStatement.bindLong(7, (long) userAnalytics.getCall_week_count());
                supportSQLiteStatement.bindLong(8, userAnalytics.getNo_money_reported() ? 1 : 0);
                supportSQLiteStatement.bindLong(9, userAnalytics.getLess_two_funds_reported() ? 1 : 0);
            }
        };
    }

    public void saveAnalytics(UserAnalytics userAnalytics) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUserAnalytics.insert(userAnalytics);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics} */
    /* JADX WARNING: type inference failed for: r14v2, types: [java.lang.Long] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics getAnalyticsForUser(long r26) {
        /*
            r25 = this;
            java.lang.String r0 = "SELECT * FROM UserAnalytics WHERE user_id=? LIMIT 1"
            r1 = 1
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r0, r1)
            r3 = r26
            r2.bindLong(r1, r3)
            r3 = r25
            androidx.room.RoomDatabase r0 = r3.__db
            android.database.Cursor r4 = r0.query(r2)
            java.lang.String r0 = "id"
            int r0 = r4.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x00b9 }
            java.lang.String r5 = "user_id"
            int r5 = r4.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x00b9 }
            java.lang.String r6 = "first_call_done"
            int r6 = r4.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x00b9 }
            java.lang.String r7 = "call_day_timestamp"
            int r7 = r4.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x00b9 }
            java.lang.String r8 = "call_day_count"
            int r8 = r4.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x00b9 }
            java.lang.String r9 = "call_week_timestamp"
            int r9 = r4.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x00b9 }
            java.lang.String r10 = "call_week_count"
            int r10 = r4.getColumnIndexOrThrow(r10)     // Catch:{ all -> 0x00b9 }
            java.lang.String r11 = "no_money_reported"
            int r11 = r4.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x00b9 }
            java.lang.String r12 = "less_two_funds_reported"
            int r12 = r4.getColumnIndexOrThrow(r12)     // Catch:{ all -> 0x00b9 }
            boolean r13 = r4.moveToFirst()     // Catch:{ all -> 0x00b9 }
            r14 = 0
            if (r13 == 0) goto L_0x00b2
            long r16 = r4.getLong(r5)     // Catch:{ all -> 0x00b9 }
            int r5 = r4.getInt(r6)     // Catch:{ all -> 0x00b9 }
            r6 = 0
            if (r5 == 0) goto L_0x0060
            r18 = 1
            goto L_0x0062
        L_0x0060:
            r18 = 0
        L_0x0062:
            boolean r5 = r4.isNull(r7)     // Catch:{ all -> 0x00b9 }
            if (r5 == 0) goto L_0x006b
            r19 = r14
            goto L_0x0075
        L_0x006b:
            long r19 = r4.getLong(r7)     // Catch:{ all -> 0x00b9 }
            java.lang.Long r5 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x00b9 }
            r19 = r5
        L_0x0075:
            int r20 = r4.getInt(r8)     // Catch:{ all -> 0x00b9 }
            boolean r5 = r4.isNull(r9)     // Catch:{ all -> 0x00b9 }
            if (r5 == 0) goto L_0x0082
        L_0x007f:
            r21 = r14
            goto L_0x008b
        L_0x0082:
            long r7 = r4.getLong(r9)     // Catch:{ all -> 0x00b9 }
            java.lang.Long r14 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x00b9 }
            goto L_0x007f
        L_0x008b:
            int r22 = r4.getInt(r10)     // Catch:{ all -> 0x00b9 }
            int r5 = r4.getInt(r11)     // Catch:{ all -> 0x00b9 }
            if (r5 == 0) goto L_0x0098
            r23 = 1
            goto L_0x009a
        L_0x0098:
            r23 = 0
        L_0x009a:
            int r5 = r4.getInt(r12)     // Catch:{ all -> 0x00b9 }
            if (r5 == 0) goto L_0x00a3
            r24 = 1
            goto L_0x00a5
        L_0x00a3:
            r24 = 0
        L_0x00a5:
            com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics r14 = new com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics     // Catch:{ all -> 0x00b9 }
            r15 = r14
            r15.<init>(r16, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ all -> 0x00b9 }
            int r0 = r4.getInt(r0)     // Catch:{ all -> 0x00b9 }
            r14.setId(r0)     // Catch:{ all -> 0x00b9 }
        L_0x00b2:
            r4.close()
            r2.release()
            return r14
        L_0x00b9:
            r0 = move-exception
            r4.close()
            r2.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.dao.UserAnalyticsDao_Impl.getAnalyticsForUser(long):com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics");
    }
}
