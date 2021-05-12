package com.forasoft.homewavvisitor.dao;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.urbanairship.MessageCenterDataManager;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class VisitDao_Impl extends VisitDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfScheduledVisit;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteVisitById;

    public VisitDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfScheduledVisit = new EntityInsertionAdapter<ScheduledVisit>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `visits`(`slotId`,`inmateId`,`inmate`,`facility`,`station`,`timestamp`,`timezone`,`status`) VALUES (?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ScheduledVisit scheduledVisit) {
                if (scheduledVisit.getSlotId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, scheduledVisit.getSlotId());
                }
                if (scheduledVisit.getInmateId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, scheduledVisit.getInmateId());
                }
                if (scheduledVisit.getInmate() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, scheduledVisit.getInmate());
                }
                if (scheduledVisit.getFacility() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, scheduledVisit.getFacility());
                }
                if (scheduledVisit.getStation() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, scheduledVisit.getStation());
                }
                supportSQLiteStatement.bindLong(6, scheduledVisit.getTimestamp());
                if (scheduledVisit.getTimezone() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, scheduledVisit.getTimezone());
                }
                if (scheduledVisit.getStatus() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, scheduledVisit.getStatus());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM visits";
            }
        };
        this.__preparedStmtOfDeleteVisitById = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM visits WHERE slotId = ?";
            }
        };
    }

    public void save(List<ScheduledVisit> list) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfScheduledVisit.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void saveVisits(List<ScheduledVisit> list) {
        this.__db.beginTransaction();
        try {
            super.saveVisits(list);
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

    public void deleteVisitById(String str) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteVisitById.acquire();
        this.__db.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.__db.endTransaction();
                this.__preparedStmtOfDeleteVisitById.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        acquire.executeUpdateDelete();
        this.__db.setTransactionSuccessful();
        this.__db.endTransaction();
        this.__preparedStmtOfDeleteVisitById.release(acquire);
    }

    public Single<ScheduledVisit> getVisitById(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM visits WHERE slotId = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return Single.fromCallable(new Callable<ScheduledVisit>() {
            public ScheduledVisit call() throws Exception {
                Cursor query = VisitDao_Impl.this.__db.query(acquire);
                try {
                    ScheduledVisit scheduledVisit = query.moveToFirst() ? new ScheduledVisit(query.getString(query.getColumnIndexOrThrow("slotId")), query.getString(query.getColumnIndexOrThrow("inmateId")), query.getString(query.getColumnIndexOrThrow("inmate")), query.getString(query.getColumnIndexOrThrow("facility")), query.getString(query.getColumnIndexOrThrow("station")), query.getLong(query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP)), query.getString(query.getColumnIndexOrThrow("timezone")), query.getString(query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS))) : null;
                    if (scheduledVisit != null) {
                        return scheduledVisit;
                    }
                    throw new EmptyResultSetException("Query returned empty result set: " + acquire.getSql());
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<ScheduledVisit>> getScheduledVisits(long j) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM visits WHERE status = 'confirmed' AND timestamp > ?", 1);
        acquire.bindLong(1, j);
        return RxRoom.createFlowable(this.__db, new String[]{"visits"}, new Callable<List<ScheduledVisit>>() {
            public List<ScheduledVisit> call() throws Exception {
                Cursor query = VisitDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("slotId");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("inmateId");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("inmate");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("facility");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("station");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("timezone");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new ScheduledVisit(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<ScheduledVisit>> getPendingVisits(long j) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM visits WHERE status = 'requested' AND timestamp > ?", 1);
        acquire.bindLong(1, j);
        return RxRoom.createFlowable(this.__db, new String[]{"visits"}, new Callable<List<ScheduledVisit>>() {
            public List<ScheduledVisit> call() throws Exception {
                Cursor query = VisitDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("slotId");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("inmateId");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("inmate");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("facility");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("station");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("timezone");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new ScheduledVisit(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<ScheduledVisit>> getScheduledVisits(long j, String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM visits WHERE status = 'confirmed' AND timestamp > ? AND facility = ?", 2);
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"visits"}, new Callable<List<ScheduledVisit>>() {
            public List<ScheduledVisit> call() throws Exception {
                Cursor query = VisitDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("slotId");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("inmateId");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("inmate");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("facility");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("station");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("timezone");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new ScheduledVisit(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<ScheduledVisit>> getPendingVisits(long j, String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM visits WHERE status = 'requested' AND timestamp > ? AND facility = ?", 2);
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"visits"}, new Callable<List<ScheduledVisit>>() {
            public List<ScheduledVisit> call() throws Exception {
                Cursor query = VisitDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("slotId");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("inmateId");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("inmate");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("facility");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("station");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP);
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("timezone");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new ScheduledVisit(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6), query.getString(columnIndexOrThrow7), query.getString(columnIndexOrThrow8)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }
}
