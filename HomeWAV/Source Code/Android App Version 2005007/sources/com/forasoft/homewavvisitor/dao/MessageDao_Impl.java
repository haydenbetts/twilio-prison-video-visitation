package com.forasoft.homewavvisitor.dao;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.forasoft.homewavvisitor.dao.converters.LocalDateTimeConverter;
import com.forasoft.homewavvisitor.dao.converters.MessageStatusConverter;
import com.forasoft.homewavvisitor.dao.converters.MessageTypeConverter;
import com.forasoft.homewavvisitor.dao.converters.SenderConverter;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.MessageStatus;
import com.forasoft.homewavvisitor.model.data.MessageType;
import com.forasoft.homewavvisitor.model.data.Sender;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.threeten.bp.LocalDateTime;

public final class MessageDao_Impl implements MessageDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfMessage;
    /* access modifiers changed from: private */
    public final LocalDateTimeConverter __localDateTimeConverter = new LocalDateTimeConverter();
    /* access modifiers changed from: private */
    public final MessageStatusConverter __messageStatusConverter = new MessageStatusConverter();
    /* access modifiers changed from: private */
    public final MessageTypeConverter __messageTypeConverter = new MessageTypeConverter();
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfReadDialog;
    /* access modifiers changed from: private */
    public final SenderConverter __senderConverter = new SenderConverter();

    public MessageDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `messages`(`id`,`pubId`,`created`,`inmateId`,`visitorId`,`body`,`sender`,`type`,`status`,`views`,`senderName`,`streamName`,`streamUrl`,`imageUrl`,`videoUrl`,`senderStatus`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Message message) {
                if (message.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, message.getId());
                }
                if (message.getPubId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, message.getPubId());
                }
                Long fromLocalDateTime = MessageDao_Impl.this.__localDateTimeConverter.fromLocalDateTime(message.getCreated());
                if (fromLocalDateTime == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindLong(3, fromLocalDateTime.longValue());
                }
                if (message.getInmateId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, message.getInmateId());
                }
                if (message.getVisitorId() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, message.getVisitorId());
                }
                if (message.getBody() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, message.getBody());
                }
                supportSQLiteStatement.bindLong(7, (long) MessageDao_Impl.this.__senderConverter.fromSender(message.getSender()));
                supportSQLiteStatement.bindLong(8, (long) MessageDao_Impl.this.__messageTypeConverter.fromMessageType(message.getType()));
                supportSQLiteStatement.bindLong(9, (long) MessageDao_Impl.this.__messageStatusConverter.fromStatus(message.getStatus()));
                if (message.getViews() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, message.getViews());
                }
                if (message.getSenderName() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, message.getSenderName());
                }
                if (message.getStreamName() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, message.getStreamName());
                }
                if (message.getStreamUrl() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, message.getStreamUrl());
                }
                if (message.getImageUrl() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, message.getImageUrl());
                }
                if (message.getVideoUrl() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, message.getVideoUrl());
                }
                if (message.getSenderStatus() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, message.getSenderStatus());
                }
            }
        };
        this.__preparedStmtOfReadDialog = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE messages SET 'views'='1' WHERE inmateId = ? AND visitorId = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM messages";
            }
        };
    }

    public void saveMessage(Message message) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMessage.insert(message);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void saveMessages(List<Message> list) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMessage.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void readDialog(String str, String str2) {
        SupportSQLiteStatement acquire = this.__preparedStmtOfReadDialog.acquire();
        this.__db.beginTransaction();
        if (str == null) {
            try {
                acquire.bindNull(1);
            } catch (Throwable th) {
                this.__db.endTransaction();
                this.__preparedStmtOfReadDialog.release(acquire);
                throw th;
            }
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        acquire.executeUpdateDelete();
        this.__db.setTransactionSuccessful();
        this.__db.endTransaction();
        this.__preparedStmtOfReadDialog.release(acquire);
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

    public Flowable<List<Message>> getAll(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE visitorId = ? ORDER BY created DESC", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"messages"}, new Callable<List<Message>>() {
            public List<Message> call() throws Exception {
                Long valueOf;
                Cursor query = MessageDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("pubId");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("inmateId");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("visitorId");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("body");
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("sender");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("type");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow("views");
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("senderName");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("streamName");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("streamUrl");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("imageUrl");
                    int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoUrl");
                    int columnIndexOrThrow16 = query.getColumnIndexOrThrow("senderStatus");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        if (query.isNull(columnIndexOrThrow3)) {
                            valueOf = null;
                        } else {
                            valueOf = Long.valueOf(query.getLong(columnIndexOrThrow3));
                        }
                        int i2 = columnIndexOrThrow;
                        LocalDateTime localDateTime = MessageDao_Impl.this.__localDateTimeConverter.toLocalDateTime(valueOf);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        Sender sender = MessageDao_Impl.this.__senderConverter.toSender(query.getInt(columnIndexOrThrow7));
                        MessageType messageType = MessageDao_Impl.this.__messageTypeConverter.toMessageType(query.getInt(columnIndexOrThrow8));
                        MessageStatus status = MessageDao_Impl.this.__messageStatusConverter.toStatus(query.getInt(columnIndexOrThrow9));
                        String string6 = query.getString(columnIndexOrThrow10);
                        String string7 = query.getString(columnIndexOrThrow11);
                        String string8 = query.getString(columnIndexOrThrow12);
                        int i3 = i;
                        String string9 = query.getString(i3);
                        int i4 = columnIndexOrThrow14;
                        String string10 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow15;
                        String string11 = query.getString(i5);
                        columnIndexOrThrow15 = i5;
                        int i6 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i6;
                        arrayList.add(new Message(string, string2, localDateTime, string3, string4, string5, sender, messageType, status, string6, string7, string8, string9, string10, string11, query.getString(i6)));
                        columnIndexOrThrow14 = i4;
                        columnIndexOrThrow = i2;
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

    public Flowable<List<Message>> getDialog(String str, String str2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE inmateId = ? AND visitorId = ? ORDER BY created DESC", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"messages"}, new Callable<List<Message>>() {
            public List<Message> call() throws Exception {
                Long valueOf;
                Cursor query = MessageDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("pubId");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("inmateId");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("visitorId");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("body");
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("sender");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("type");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow("views");
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("senderName");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("streamName");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("streamUrl");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("imageUrl");
                    int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoUrl");
                    int columnIndexOrThrow16 = query.getColumnIndexOrThrow("senderStatus");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        if (query.isNull(columnIndexOrThrow3)) {
                            valueOf = null;
                        } else {
                            valueOf = Long.valueOf(query.getLong(columnIndexOrThrow3));
                        }
                        int i2 = columnIndexOrThrow;
                        LocalDateTime localDateTime = MessageDao_Impl.this.__localDateTimeConverter.toLocalDateTime(valueOf);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        Sender sender = MessageDao_Impl.this.__senderConverter.toSender(query.getInt(columnIndexOrThrow7));
                        MessageType messageType = MessageDao_Impl.this.__messageTypeConverter.toMessageType(query.getInt(columnIndexOrThrow8));
                        MessageStatus status = MessageDao_Impl.this.__messageStatusConverter.toStatus(query.getInt(columnIndexOrThrow9));
                        String string6 = query.getString(columnIndexOrThrow10);
                        String string7 = query.getString(columnIndexOrThrow11);
                        String string8 = query.getString(columnIndexOrThrow12);
                        int i3 = i;
                        String string9 = query.getString(i3);
                        int i4 = columnIndexOrThrow14;
                        String string10 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow15;
                        String string11 = query.getString(i5);
                        columnIndexOrThrow15 = i5;
                        int i6 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i6;
                        arrayList.add(new Message(string, string2, localDateTime, string3, string4, string5, sender, messageType, status, string6, string7, string8, string9, string10, string11, query.getString(i6)));
                        columnIndexOrThrow14 = i4;
                        columnIndexOrThrow = i2;
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

    public Single<List<Message>> getAllUnread(String str, String str2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE inmateId = ? AND visitorId = ? AND views = '0' ORDER BY created ASC", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        return Single.fromCallable(new Callable<List<Message>>() {
            public List<Message> call() throws Exception {
                Long valueOf;
                Cursor query = MessageDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("pubId");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("inmateId");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("visitorId");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("body");
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("sender");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("type");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow("views");
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("senderName");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("streamName");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("streamUrl");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("imageUrl");
                    int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoUrl");
                    int columnIndexOrThrow16 = query.getColumnIndexOrThrow("senderStatus");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        if (query.isNull(columnIndexOrThrow3)) {
                            valueOf = null;
                        } else {
                            valueOf = Long.valueOf(query.getLong(columnIndexOrThrow3));
                        }
                        int i2 = columnIndexOrThrow;
                        LocalDateTime localDateTime = MessageDao_Impl.this.__localDateTimeConverter.toLocalDateTime(valueOf);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        Sender sender = MessageDao_Impl.this.__senderConverter.toSender(query.getInt(columnIndexOrThrow7));
                        MessageType messageType = MessageDao_Impl.this.__messageTypeConverter.toMessageType(query.getInt(columnIndexOrThrow8));
                        MessageStatus status = MessageDao_Impl.this.__messageStatusConverter.toStatus(query.getInt(columnIndexOrThrow9));
                        String string6 = query.getString(columnIndexOrThrow10);
                        String string7 = query.getString(columnIndexOrThrow11);
                        String string8 = query.getString(columnIndexOrThrow12);
                        int i3 = i;
                        String string9 = query.getString(i3);
                        int i4 = columnIndexOrThrow14;
                        String string10 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow15;
                        String string11 = query.getString(i5);
                        columnIndexOrThrow15 = i5;
                        int i6 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i6;
                        arrayList.add(new Message(string, string2, localDateTime, string3, string4, string5, sender, messageType, status, string6, string7, string8, string9, string10, string11, query.getString(i6)));
                        columnIndexOrThrow14 = i4;
                        columnIndexOrThrow = i2;
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

    public Flowable<List<Message>> getAllUnread(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE visitorId = ? AND views = '0'", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, new String[]{"messages"}, new Callable<List<Message>>() {
            public List<Message> call() throws Exception {
                Long valueOf;
                Cursor query = MessageDao_Impl.this.__db.query(acquire);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("pubId");
                    int columnIndexOrThrow3 = query.getColumnIndexOrThrow("created");
                    int columnIndexOrThrow4 = query.getColumnIndexOrThrow("inmateId");
                    int columnIndexOrThrow5 = query.getColumnIndexOrThrow("visitorId");
                    int columnIndexOrThrow6 = query.getColumnIndexOrThrow("body");
                    int columnIndexOrThrow7 = query.getColumnIndexOrThrow("sender");
                    int columnIndexOrThrow8 = query.getColumnIndexOrThrow("type");
                    int columnIndexOrThrow9 = query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow10 = query.getColumnIndexOrThrow("views");
                    int columnIndexOrThrow11 = query.getColumnIndexOrThrow("senderName");
                    int columnIndexOrThrow12 = query.getColumnIndexOrThrow("streamName");
                    int columnIndexOrThrow13 = query.getColumnIndexOrThrow("streamUrl");
                    int columnIndexOrThrow14 = query.getColumnIndexOrThrow("imageUrl");
                    int columnIndexOrThrow15 = query.getColumnIndexOrThrow("videoUrl");
                    int columnIndexOrThrow16 = query.getColumnIndexOrThrow("senderStatus");
                    int i = columnIndexOrThrow13;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        String string2 = query.getString(columnIndexOrThrow2);
                        if (query.isNull(columnIndexOrThrow3)) {
                            valueOf = null;
                        } else {
                            valueOf = Long.valueOf(query.getLong(columnIndexOrThrow3));
                        }
                        int i2 = columnIndexOrThrow;
                        LocalDateTime localDateTime = MessageDao_Impl.this.__localDateTimeConverter.toLocalDateTime(valueOf);
                        String string3 = query.getString(columnIndexOrThrow4);
                        String string4 = query.getString(columnIndexOrThrow5);
                        String string5 = query.getString(columnIndexOrThrow6);
                        Sender sender = MessageDao_Impl.this.__senderConverter.toSender(query.getInt(columnIndexOrThrow7));
                        MessageType messageType = MessageDao_Impl.this.__messageTypeConverter.toMessageType(query.getInt(columnIndexOrThrow8));
                        MessageStatus status = MessageDao_Impl.this.__messageStatusConverter.toStatus(query.getInt(columnIndexOrThrow9));
                        String string6 = query.getString(columnIndexOrThrow10);
                        String string7 = query.getString(columnIndexOrThrow11);
                        String string8 = query.getString(columnIndexOrThrow12);
                        int i3 = i;
                        String string9 = query.getString(i3);
                        int i4 = columnIndexOrThrow14;
                        String string10 = query.getString(i4);
                        i = i3;
                        int i5 = columnIndexOrThrow15;
                        String string11 = query.getString(i5);
                        columnIndexOrThrow15 = i5;
                        int i6 = columnIndexOrThrow16;
                        columnIndexOrThrow16 = i6;
                        arrayList.add(new Message(string, string2, localDateTime, string3, string4, string5, sender, messageType, status, string6, string7, string8, string9, string10, string11, query.getString(i6)));
                        columnIndexOrThrow14 = i4;
                        columnIndexOrThrow = i2;
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

    public void deleteMessages(List<String> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("DELETE FROM messages WHERE id in (");
        StringUtil.appendPlaceholders(newStringBuilder, list.size());
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        int i = 1;
        for (String next : list) {
            if (next == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindString(i, next);
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
