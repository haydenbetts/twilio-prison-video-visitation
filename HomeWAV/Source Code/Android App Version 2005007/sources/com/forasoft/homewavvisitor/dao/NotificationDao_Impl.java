package com.forasoft.homewavvisitor.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.forasoft.homewavvisitor.dao.converters.LocalDateTimeConverter;
import com.forasoft.homewavvisitor.model.data.Notification;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class NotificationDao_Impl extends NotificationDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfNotification;
    /* access modifiers changed from: private */
    public final LocalDateTimeConverter __localDateTimeConverter = new LocalDateTimeConverter();
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteNotification;

    public NotificationDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfNotification = new EntityInsertionAdapter<Notification>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `notifications`(`id`,`type`,`body`,`created`) VALUES (?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Notification notification) {
                supportSQLiteStatement.bindLong(1, (long) notification.getId());
                if (notification.getType() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, notification.getType());
                }
                if (notification.getBody() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, notification.getBody());
                }
                Long fromLocalDateTime = NotificationDao_Impl.this.__localDateTimeConverter.fromLocalDateTime(notification.getCreated());
                if (fromLocalDateTime == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, fromLocalDateTime.longValue());
                }
            }
        };
        this.__preparedStmtOfDeleteNotification = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM notifications WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM notifications";
            }
        };
    }

    public void saveNotification(Notification notification) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfNotification.insert(notification);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteNotification(int i) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteNotification.acquire();
        this.__db.beginTransaction();
        try {
            acquire.bindLong(1, (long) i);
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteNotification.release(acquire);
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

    public Flowable<List<Notification>> getAll() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM notifications ORDER BY created DESC", 0);
        return RxRoom.createFlowable(this.__db, new String[]{"notifications"}, new Callable<List<Notification>>() {
            public List<Notification> call() throws Exception {
                Long l;
                Cursor query = NotificationDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("type");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("body");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("created");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i = query.getInt(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        if (query.isNull(columnIndexOrThrow4)) {
                            l = null;
                        } else {
                            l = Long.valueOf(query.getLong(columnIndexOrThrow4));
                        }
                        arrayList.add(new Notification(i, string, string2, NotificationDao_Impl.this.__localDateTimeConverter.toLocalDateTime(l)));
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

    public Flowable<List<Notification>> getByType(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM notifications WHERE type = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"notifications"}, new Callable<List<Notification>>() {
            public List<Notification> call() throws Exception {
                Long l;
                Cursor query = NotificationDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("type");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("body");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("created");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        int i = query.getInt(columnIndexOrThrow);
                        String string = query.getString(columnIndexOrThrow2);
                        String string2 = query.getString(columnIndexOrThrow3);
                        if (query.isNull(columnIndexOrThrow4)) {
                            l = null;
                        } else {
                            l = Long.valueOf(query.getLong(columnIndexOrThrow4));
                        }
                        arrayList.add(new Notification(i, string, string2, NotificationDao_Impl.this.__localDateTimeConverter.toLocalDateTime(l)));
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

    public Flowable<Integer> countAll() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM notifications", 0);
        return RxRoom.createFlowable(this.__db, new String[]{"notifications"}, new Callable<Integer>() {
            public Integer call() throws Exception {
                Cursor query = NotificationDao_Impl.this.__db.query(acquire);
                try {
                    Integer num = null;
                    if (query.moveToFirst()) {
                        if (!query.isNull(0)) {
                            num = Integer.valueOf(query.getInt(0));
                        }
                    }
                    return num;
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
