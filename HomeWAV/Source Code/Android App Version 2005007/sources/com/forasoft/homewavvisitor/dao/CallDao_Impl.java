package com.forasoft.homewavvisitor.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.forasoft.homewavvisitor.dao.converters.LocalDateTimeConverter;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.forasoft.homewavvisitor.model.data.CallEntity;
import com.urbanairship.actions.FetchDeviceInfoAction;
import com.urbanairship.util.Attributes;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.threeten.bp.LocalDateTime;

public final class CallDao_Impl implements CallDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfCallEntity;
    /* access modifiers changed from: private */
    public final LocalDateTimeConverter __localDateTimeConverter = new LocalDateTimeConverter();
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public CallDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfCallEntity = new EntityInsertionAdapter<CallEntity>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `calls`(`id`,`protocol`,`disconnect_cause`,`visitor_username`,`notes`,`splicing_priority`,`timezone`,`splicing_finished`,`visitor_id`,`visitor_checkin`,`beta_splicer`,`splicing_outcome`,`visitor_location`,`prison_name`,`splicing_hints`,`disconnected`,`ui`,`internal_notes`,`zone`,`station`,`reviewed`,`etype`,`purged`,`splicing_started`,`inmate_id`,`extension_count`,`inmate_name`,`recorded`,`visitor_email`,`visitor_station_id`,`visitor_name`,`tags`,`connected`,`warning_message`,`pubid`,`free_seconds`,`invalidated`,`inmate_checkin`,`disconnect_required`,`account`,`age`,`prison_id`,`inmate_station_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, CallEntity callEntity) {
                if (callEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, callEntity.getId());
                }
                if (callEntity.getProtocol() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, callEntity.getProtocol());
                }
                if (callEntity.getDisconnect_cause() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, callEntity.getDisconnect_cause());
                }
                if (callEntity.getVisitor_username() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, callEntity.getVisitor_username());
                }
                if (callEntity.getNotes() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, callEntity.getNotes());
                }
                if (callEntity.getSplicing_priority() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, callEntity.getSplicing_priority());
                }
                if (callEntity.getTimezone() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, callEntity.getTimezone());
                }
                if (callEntity.getSplicing_finished() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, callEntity.getSplicing_finished());
                }
                if (callEntity.getVisitor_id() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, callEntity.getVisitor_id());
                }
                if (callEntity.getVisitor_checkin() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, callEntity.getVisitor_checkin());
                }
                if (callEntity.getBeta_splicer() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, callEntity.getBeta_splicer());
                }
                if (callEntity.getSplicing_outcome() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, callEntity.getSplicing_outcome());
                }
                if (callEntity.getVisitor_location() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, callEntity.getVisitor_location());
                }
                if (callEntity.getPrison_name() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, callEntity.getPrison_name());
                }
                if (callEntity.getSplicing_hints() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, callEntity.getSplicing_hints());
                }
                Long fromLocalDateTime = CallDao_Impl.this.__localDateTimeConverter.fromLocalDateTime(callEntity.getDisconnected());
                if (fromLocalDateTime == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindLong(16, fromLocalDateTime.longValue());
                }
                if (callEntity.getUi() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, callEntity.getUi());
                }
                if (callEntity.getInternal_notes() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, callEntity.getInternal_notes());
                }
                if (callEntity.getZone() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, callEntity.getZone());
                }
                if (callEntity.getStation() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, callEntity.getStation());
                }
                if (callEntity.getReviewed() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, callEntity.getReviewed());
                }
                if (callEntity.getEtype() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, callEntity.getEtype());
                }
                if (callEntity.getPurged() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, callEntity.getPurged());
                }
                if (callEntity.getSplicing_started() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, callEntity.getSplicing_started());
                }
                if (callEntity.getInmate_id() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, callEntity.getInmate_id());
                }
                if (callEntity.getExtension_count() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, callEntity.getExtension_count());
                }
                if (callEntity.getInmate_name() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, callEntity.getInmate_name());
                }
                if (callEntity.getRecorded() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, callEntity.getRecorded());
                }
                if (callEntity.getVisitor_email() == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, callEntity.getVisitor_email());
                }
                if (callEntity.getVisitor_station_id() == null) {
                    supportSQLiteStatement.bindNull(30);
                } else {
                    supportSQLiteStatement.bindString(30, callEntity.getVisitor_station_id());
                }
                if (callEntity.getVisitor_name() == null) {
                    supportSQLiteStatement.bindNull(31);
                } else {
                    supportSQLiteStatement.bindString(31, callEntity.getVisitor_name());
                }
                if (callEntity.getTags() == null) {
                    supportSQLiteStatement.bindNull(32);
                } else {
                    supportSQLiteStatement.bindString(32, callEntity.getTags());
                }
                Long fromLocalDateTime2 = CallDao_Impl.this.__localDateTimeConverter.fromLocalDateTime(callEntity.getConnected());
                if (fromLocalDateTime2 == null) {
                    supportSQLiteStatement.bindNull(33);
                } else {
                    supportSQLiteStatement.bindLong(33, fromLocalDateTime2.longValue());
                }
                if (callEntity.getWarning_message() == null) {
                    supportSQLiteStatement.bindNull(34);
                } else {
                    supportSQLiteStatement.bindString(34, callEntity.getWarning_message());
                }
                if (callEntity.getPubid() == null) {
                    supportSQLiteStatement.bindNull(35);
                } else {
                    supportSQLiteStatement.bindString(35, callEntity.getPubid());
                }
                if (callEntity.getFree_seconds() == null) {
                    supportSQLiteStatement.bindNull(36);
                } else {
                    supportSQLiteStatement.bindString(36, callEntity.getFree_seconds());
                }
                if (callEntity.getInvalidated() == null) {
                    supportSQLiteStatement.bindNull(37);
                } else {
                    supportSQLiteStatement.bindString(37, callEntity.getInvalidated());
                }
                if (callEntity.getInmate_checkin() == null) {
                    supportSQLiteStatement.bindNull(38);
                } else {
                    supportSQLiteStatement.bindString(38, callEntity.getInmate_checkin());
                }
                if (callEntity.getDisconnect_required() == null) {
                    supportSQLiteStatement.bindNull(39);
                } else {
                    supportSQLiteStatement.bindString(39, callEntity.getDisconnect_required());
                }
                if (callEntity.getAccount() == null) {
                    supportSQLiteStatement.bindNull(40);
                } else {
                    supportSQLiteStatement.bindString(40, callEntity.getAccount());
                }
                if (callEntity.getAge() == null) {
                    supportSQLiteStatement.bindNull(41);
                } else {
                    supportSQLiteStatement.bindString(41, callEntity.getAge());
                }
                if (callEntity.getPrison_id() == null) {
                    supportSQLiteStatement.bindNull(42);
                } else {
                    supportSQLiteStatement.bindString(42, callEntity.getPrison_id());
                }
                if (callEntity.getInmate_station_id() == null) {
                    supportSQLiteStatement.bindNull(43);
                } else {
                    supportSQLiteStatement.bindString(43, callEntity.getInmate_station_id());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM calls";
            }
        };
    }

    public void saveCall(CallEntity callEntity) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCallEntity.insert(callEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteAll() {
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    public Single<CallEntity> getLatestCall() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM calls ORDER BY id DESC LIMIT 1", 0);
        return Single.fromCallable(new Callable<CallEntity>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r45v0, resolved type: com.forasoft.homewavvisitor.model.data.CallEntity} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r45v1, resolved type: com.forasoft.homewavvisitor.model.data.CallEntity} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r45v3, resolved type: com.forasoft.homewavvisitor.model.data.CallEntity} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: java.lang.Long} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r45v4, resolved type: com.forasoft.homewavvisitor.model.data.CallEntity} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r46v1, resolved type: com.forasoft.homewavvisitor.model.data.CallEntity} */
            /* JADX WARNING: type inference failed for: r45v2, types: [java.lang.Long] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.forasoft.homewavvisitor.model.data.CallEntity call() throws java.lang.Exception {
                /*
                    r90 = this;
                    r1 = r90
                    com.forasoft.homewavvisitor.dao.CallDao_Impl r0 = com.forasoft.homewavvisitor.dao.CallDao_Impl.this
                    androidx.room.RoomDatabase r0 = r0.__db
                    androidx.room.RoomSQLiteQuery r2 = r0
                    android.database.Cursor r2 = r0.query(r2)
                    java.lang.String r0 = "id"
                    int r0 = r2.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r3 = "protocol"
                    int r3 = r2.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r4 = "disconnect_cause"
                    int r4 = r2.getColumnIndexOrThrow(r4)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r5 = "visitor_username"
                    int r5 = r2.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r6 = "notes"
                    int r6 = r2.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r7 = "splicing_priority"
                    int r7 = r2.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r8 = "timezone"
                    int r8 = r2.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r9 = "splicing_finished"
                    int r9 = r2.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r10 = "visitor_id"
                    int r10 = r2.getColumnIndexOrThrow(r10)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r11 = "visitor_checkin"
                    int r11 = r2.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r12 = "beta_splicer"
                    int r12 = r2.getColumnIndexOrThrow(r12)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r13 = "splicing_outcome"
                    int r13 = r2.getColumnIndexOrThrow(r13)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r14 = "visitor_location"
                    int r14 = r2.getColumnIndexOrThrow(r14)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r15 = "prison_name"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x02a4 }
                    java.lang.String r1 = "splicing_hints"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r16 = r1
                    java.lang.String r1 = "disconnected"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r17 = r1
                    java.lang.String r1 = "ui"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r18 = r1
                    java.lang.String r1 = "internal_notes"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r19 = r1
                    java.lang.String r1 = "zone"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r20 = r1
                    java.lang.String r1 = "station"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r21 = r1
                    java.lang.String r1 = "reviewed"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r22 = r1
                    java.lang.String r1 = "etype"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r23 = r1
                    java.lang.String r1 = "purged"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r24 = r1
                    java.lang.String r1 = "splicing_started"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r25 = r1
                    java.lang.String r1 = "inmate_id"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r26 = r1
                    java.lang.String r1 = "extension_count"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r27 = r1
                    java.lang.String r1 = "inmate_name"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r28 = r1
                    java.lang.String r1 = "recorded"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r29 = r1
                    java.lang.String r1 = "visitor_email"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r30 = r1
                    java.lang.String r1 = "visitor_station_id"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r31 = r1
                    java.lang.String r1 = "visitor_name"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r32 = r1
                    java.lang.String r1 = "tags"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r33 = r1
                    java.lang.String r1 = "connected"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r34 = r1
                    java.lang.String r1 = "warning_message"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r35 = r1
                    java.lang.String r1 = "pubid"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r36 = r1
                    java.lang.String r1 = "free_seconds"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r37 = r1
                    java.lang.String r1 = "invalidated"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r38 = r1
                    java.lang.String r1 = "inmate_checkin"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r39 = r1
                    java.lang.String r1 = "disconnect_required"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r40 = r1
                    java.lang.String r1 = "account"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r41 = r1
                    java.lang.String r1 = "age"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r42 = r1
                    java.lang.String r1 = "prison_id"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    r43 = r1
                    java.lang.String r1 = "inmate_station_id"
                    int r1 = r2.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x02a0 }
                    boolean r44 = r2.moveToFirst()     // Catch:{ all -> 0x02a0 }
                    r45 = 0
                    if (r44 == 0) goto L_0x0279
                    java.lang.String r47 = r2.getString(r0)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r48 = r2.getString(r3)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r49 = r2.getString(r4)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r50 = r2.getString(r5)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r51 = r2.getString(r6)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r52 = r2.getString(r7)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r53 = r2.getString(r8)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r54 = r2.getString(r9)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r55 = r2.getString(r10)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r56 = r2.getString(r11)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r57 = r2.getString(r12)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r58 = r2.getString(r13)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r59 = r2.getString(r14)     // Catch:{ all -> 0x02a0 }
                    java.lang.String r60 = r2.getString(r15)     // Catch:{ all -> 0x02a0 }
                    r0 = r16
                    java.lang.String r61 = r2.getString(r0)     // Catch:{ all -> 0x02a0 }
                    r0 = r17
                    boolean r3 = r2.isNull(r0)     // Catch:{ all -> 0x02a0 }
                    if (r3 == 0) goto L_0x01a5
                    r3 = r90
                    r0 = r45
                    goto L_0x01af
                L_0x01a5:
                    long r3 = r2.getLong(r0)     // Catch:{ all -> 0x02a0 }
                    java.lang.Long r0 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x02a0 }
                    r3 = r90
                L_0x01af:
                    com.forasoft.homewavvisitor.dao.CallDao_Impl r4 = com.forasoft.homewavvisitor.dao.CallDao_Impl.this     // Catch:{ all -> 0x029e }
                    com.forasoft.homewavvisitor.dao.converters.LocalDateTimeConverter r4 = r4.__localDateTimeConverter     // Catch:{ all -> 0x029e }
                    org.threeten.bp.LocalDateTime r62 = r4.toLocalDateTime(r0)     // Catch:{ all -> 0x029e }
                    r0 = r18
                    java.lang.String r63 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r19
                    java.lang.String r64 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r20
                    java.lang.String r65 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r21
                    java.lang.String r66 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r22
                    java.lang.String r67 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r23
                    java.lang.String r68 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r24
                    java.lang.String r69 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r25
                    java.lang.String r70 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r26
                    java.lang.String r71 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r27
                    java.lang.String r72 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r28
                    java.lang.String r73 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r29
                    java.lang.String r74 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r30
                    java.lang.String r75 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r31
                    java.lang.String r76 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r32
                    java.lang.String r77 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r33
                    java.lang.String r78 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r34
                    boolean r4 = r2.isNull(r0)     // Catch:{ all -> 0x029e }
                    if (r4 == 0) goto L_0x0224
                L_0x0221:
                    r0 = r45
                    goto L_0x022d
                L_0x0224:
                    long r4 = r2.getLong(r0)     // Catch:{ all -> 0x029e }
                    java.lang.Long r45 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x029e }
                    goto L_0x0221
                L_0x022d:
                    com.forasoft.homewavvisitor.dao.CallDao_Impl r4 = com.forasoft.homewavvisitor.dao.CallDao_Impl.this     // Catch:{ all -> 0x029e }
                    com.forasoft.homewavvisitor.dao.converters.LocalDateTimeConverter r4 = r4.__localDateTimeConverter     // Catch:{ all -> 0x029e }
                    org.threeten.bp.LocalDateTime r79 = r4.toLocalDateTime(r0)     // Catch:{ all -> 0x029e }
                    r0 = r35
                    java.lang.String r80 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r36
                    java.lang.String r81 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r37
                    java.lang.String r82 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r38
                    java.lang.String r83 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r39
                    java.lang.String r84 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r40
                    java.lang.String r85 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r41
                    java.lang.String r86 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r42
                    java.lang.String r87 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    r0 = r43
                    java.lang.String r88 = r2.getString(r0)     // Catch:{ all -> 0x029e }
                    java.lang.String r89 = r2.getString(r1)     // Catch:{ all -> 0x029e }
                    com.forasoft.homewavvisitor.model.data.CallEntity r45 = new com.forasoft.homewavvisitor.model.data.CallEntity     // Catch:{ all -> 0x029e }
                    r46 = r45
                    r46.<init>(r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89)     // Catch:{ all -> 0x029e }
                    goto L_0x027b
                L_0x0279:
                    r3 = r90
                L_0x027b:
                    if (r45 == 0) goto L_0x0281
                    r2.close()
                    return r45
                L_0x0281:
                    androidx.room.EmptyResultSetException r0 = new androidx.room.EmptyResultSetException     // Catch:{ all -> 0x029e }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x029e }
                    r1.<init>()     // Catch:{ all -> 0x029e }
                    java.lang.String r4 = "Query returned empty result set: "
                    r1.append(r4)     // Catch:{ all -> 0x029e }
                    androidx.room.RoomSQLiteQuery r4 = r0     // Catch:{ all -> 0x029e }
                    java.lang.String r4 = r4.getSql()     // Catch:{ all -> 0x029e }
                    r1.append(r4)     // Catch:{ all -> 0x029e }
                    java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x029e }
                    r0.<init>(r1)     // Catch:{ all -> 0x029e }
                    throw r0     // Catch:{ all -> 0x029e }
                L_0x029e:
                    r0 = move-exception
                    goto L_0x02a6
                L_0x02a0:
                    r0 = move-exception
                    r3 = r90
                    goto L_0x02a6
                L_0x02a4:
                    r0 = move-exception
                    r3 = r1
                L_0x02a6:
                    r2.close()
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.dao.CallDao_Impl.AnonymousClass3.call():com.forasoft.homewavvisitor.model.data.CallEntity");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<CallEntity>> getCallsWithInmate(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM calls WHERE inmate_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"calls"}, new Callable<List<CallEntity>>() {
            public List<CallEntity> call() throws Exception {
                int i;
                int i2;
                Long valueOf;
                int i3;
                Cursor query = CallDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("protocol");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("disconnect_cause");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("visitor_username");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("notes");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("splicing_priority");
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("timezone");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("splicing_finished");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(UploadWorker.KEY_VISITOR_ID);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow("visitor_checkin");
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("beta_splicer");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("splicing_outcome");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("visitor_location");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("prison_name");
                    try {
                        int columnIndexOrThrow15 = query.getColumnIndexOrThrow("splicing_hints");
                        int columnIndexOrThrow16 = query.getColumnIndexOrThrow("disconnected");
                        int columnIndexOrThrow17 = query.getColumnIndexOrThrow("ui");
                        int columnIndexOrThrow18 = query.getColumnIndexOrThrow("internal_notes");
                        int columnIndexOrThrow19 = query.getColumnIndexOrThrow("zone");
                        int columnIndexOrThrow20 = query.getColumnIndexOrThrow("station");
                        int columnIndexOrThrow21 = query.getColumnIndexOrThrow("reviewed");
                        int columnIndexOrThrow22 = query.getColumnIndexOrThrow("etype");
                        int columnIndexOrThrow23 = query.getColumnIndexOrThrow("purged");
                        int columnIndexOrThrow24 = query.getColumnIndexOrThrow("splicing_started");
                        int columnIndexOrThrow25 = query.getColumnIndexOrThrow(UploadWorker.KEY_INMATE_ID);
                        int columnIndexOrThrow26 = query.getColumnIndexOrThrow("extension_count");
                        int columnIndexOrThrow27 = query.getColumnIndexOrThrow("inmate_name");
                        int columnIndexOrThrow28 = query.getColumnIndexOrThrow("recorded");
                        int columnIndexOrThrow29 = query.getColumnIndexOrThrow("visitor_email");
                        int columnIndexOrThrow30 = query.getColumnIndexOrThrow("visitor_station_id");
                        int columnIndexOrThrow31 = query.getColumnIndexOrThrow("visitor_name");
                        int columnIndexOrThrow32 = query.getColumnIndexOrThrow(FetchDeviceInfoAction.TAGS_KEY);
                        int columnIndexOrThrow33 = query.getColumnIndexOrThrow("connected");
                        int columnIndexOrThrow34 = query.getColumnIndexOrThrow("warning_message");
                        int columnIndexOrThrow35 = query.getColumnIndexOrThrow(UploadWorker.KEY_PUB_ID);
                        int columnIndexOrThrow36 = query.getColumnIndexOrThrow("free_seconds");
                        int columnIndexOrThrow37 = query.getColumnIndexOrThrow("invalidated");
                        int columnIndexOrThrow38 = query.getColumnIndexOrThrow("inmate_checkin");
                        int columnIndexOrThrow39 = query.getColumnIndexOrThrow("disconnect_required");
                        int columnIndexOrThrow40 = query.getColumnIndexOrThrow("account");
                        int columnIndexOrThrow41 = query.getColumnIndexOrThrow(Attributes.AGE);
                        int columnIndexOrThrow42 = query.getColumnIndexOrThrow("prison_id");
                        int columnIndexOrThrow43 = query.getColumnIndexOrThrow("inmate_station_id");
                        int i4 = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(query.getCount());
                        while (query.moveToNext()) {
                            String string = query.getString(columnIndexOrThrow);
                            String string2 = query.getString(columnIndexOrThrow2);
                            String string3 = query.getString(columnIndexOrThrow3);
                            String string4 = query.getString(columnIndexOrThrow4);
                            String string5 = query.getString(columnIndexOrThrow5);
                            String string6 = query.getString(columnIndexOrThrow6);
                            String string7 = query.getString(columnIndexOrThrow7);
                            String string8 = query.getString(columnIndexOrThrow8);
                            String string9 = query.getString(columnIndexOrThrow9);
                            String string10 = query.getString(columnIndexOrThrow10);
                            String string11 = query.getString(columnIndexOrThrow11);
                            String string12 = query.getString(columnIndexOrThrow12);
                            String string13 = query.getString(columnIndexOrThrow13);
                            int i5 = i4;
                            String string14 = query.getString(i5);
                            int i6 = columnIndexOrThrow;
                            int i7 = columnIndexOrThrow15;
                            String string15 = query.getString(i7);
                            columnIndexOrThrow15 = i7;
                            int i8 = columnIndexOrThrow16;
                            Long l = null;
                            if (query.isNull(i8)) {
                                i = i8;
                                i3 = columnIndexOrThrow2;
                                i2 = columnIndexOrThrow3;
                                valueOf = null;
                            } else {
                                i = i8;
                                i2 = columnIndexOrThrow3;
                                valueOf = Long.valueOf(query.getLong(i8));
                                i3 = columnIndexOrThrow2;
                            }
                            try {
                                LocalDateTime localDateTime = CallDao_Impl.this.__localDateTimeConverter.toLocalDateTime(valueOf);
                                int i9 = columnIndexOrThrow17;
                                String string16 = query.getString(i9);
                                int i10 = columnIndexOrThrow18;
                                String string17 = query.getString(i10);
                                columnIndexOrThrow17 = i9;
                                int i11 = columnIndexOrThrow19;
                                String string18 = query.getString(i11);
                                columnIndexOrThrow19 = i11;
                                int i12 = columnIndexOrThrow20;
                                String string19 = query.getString(i12);
                                columnIndexOrThrow20 = i12;
                                int i13 = columnIndexOrThrow21;
                                String string20 = query.getString(i13);
                                columnIndexOrThrow21 = i13;
                                int i14 = columnIndexOrThrow22;
                                String string21 = query.getString(i14);
                                columnIndexOrThrow22 = i14;
                                int i15 = columnIndexOrThrow23;
                                String string22 = query.getString(i15);
                                columnIndexOrThrow23 = i15;
                                int i16 = columnIndexOrThrow24;
                                String string23 = query.getString(i16);
                                columnIndexOrThrow24 = i16;
                                int i17 = columnIndexOrThrow25;
                                String string24 = query.getString(i17);
                                columnIndexOrThrow25 = i17;
                                int i18 = columnIndexOrThrow26;
                                String string25 = query.getString(i18);
                                columnIndexOrThrow26 = i18;
                                int i19 = columnIndexOrThrow27;
                                String string26 = query.getString(i19);
                                columnIndexOrThrow27 = i19;
                                int i20 = columnIndexOrThrow28;
                                String string27 = query.getString(i20);
                                columnIndexOrThrow28 = i20;
                                int i21 = columnIndexOrThrow29;
                                String string28 = query.getString(i21);
                                columnIndexOrThrow29 = i21;
                                int i22 = columnIndexOrThrow30;
                                String string29 = query.getString(i22);
                                columnIndexOrThrow30 = i22;
                                int i23 = columnIndexOrThrow31;
                                String string30 = query.getString(i23);
                                columnIndexOrThrow31 = i23;
                                int i24 = columnIndexOrThrow32;
                                String string31 = query.getString(i24);
                                columnIndexOrThrow32 = i24;
                                int i25 = columnIndexOrThrow33;
                                if (!query.isNull(i25)) {
                                    l = Long.valueOf(query.getLong(i25));
                                }
                                columnIndexOrThrow33 = i25;
                                columnIndexOrThrow18 = i10;
                                LocalDateTime localDateTime2 = CallDao_Impl.this.__localDateTimeConverter.toLocalDateTime(l);
                                int i26 = columnIndexOrThrow34;
                                String string32 = query.getString(i26);
                                int i27 = columnIndexOrThrow35;
                                String string33 = query.getString(i27);
                                columnIndexOrThrow34 = i26;
                                int i28 = columnIndexOrThrow36;
                                String string34 = query.getString(i28);
                                columnIndexOrThrow36 = i28;
                                int i29 = columnIndexOrThrow37;
                                String string35 = query.getString(i29);
                                columnIndexOrThrow37 = i29;
                                int i30 = columnIndexOrThrow38;
                                String string36 = query.getString(i30);
                                columnIndexOrThrow38 = i30;
                                int i31 = columnIndexOrThrow39;
                                String string37 = query.getString(i31);
                                columnIndexOrThrow39 = i31;
                                int i32 = columnIndexOrThrow40;
                                String string38 = query.getString(i32);
                                columnIndexOrThrow40 = i32;
                                int i33 = columnIndexOrThrow41;
                                String string39 = query.getString(i33);
                                columnIndexOrThrow41 = i33;
                                int i34 = columnIndexOrThrow42;
                                String string40 = query.getString(i34);
                                columnIndexOrThrow42 = i34;
                                int i35 = columnIndexOrThrow43;
                                columnIndexOrThrow43 = i35;
                                arrayList.add(new CallEntity(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, localDateTime, string16, string17, string18, string19, string20, string21, string22, string23, string24, string25, string26, string27, string28, string29, string30, string31, localDateTime2, string32, string33, string34, string35, string36, string37, string38, string39, string40, query.getString(i35)));
                                columnIndexOrThrow35 = i27;
                                columnIndexOrThrow2 = i3;
                                columnIndexOrThrow = i6;
                                columnIndexOrThrow16 = i;
                                columnIndexOrThrow3 = i2;
                                i4 = i5;
                            } catch (Throwable th) {
                                th = th;
                                query.close();
                                throw th;
                            }
                        }
                        query.close();
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        query.close();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query.close();
                    throw th;
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }
}
