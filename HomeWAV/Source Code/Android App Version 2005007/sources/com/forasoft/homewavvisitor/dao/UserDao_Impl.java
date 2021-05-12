package com.forasoft.homewavvisitor.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.forasoft.homewavvisitor.dao.converters.DateConverter;
import com.forasoft.homewavvisitor.model.data.auth.User;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.concurrent.Callable;

public final class UserDao_Impl implements UserDao {
    /* access modifiers changed from: private */
    public final DateConverter __dateConverter = new DateConverter();
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfUser;
    private final SharedSQLiteStatement __preparedStmtOfClearUser;

    public UserDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `User`(`id`,`visitor_id`,`created`,`pin`,`birthDate`,`username`,`firstName`,`lastName`,`fullName`,`phone`,`email`,`state`,`city`,`street`,`zip`,`photoProfileUrl`,`photoIdUrl`,`photoProfile`,`photoId`,`verified`,`isAdmin`,`isNotificationSubscriptionEnabled`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, User user) {
                if (user.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, user.getId());
                }
                if (user.getVisitor_id() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, user.getVisitor_id());
                }
                if (user.getCreated() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, user.getCreated());
                }
                if (user.getPin() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, user.getPin());
                }
                Long fromDate = UserDao_Impl.this.__dateConverter.fromDate(user.getBirthDate());
                if (fromDate == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, fromDate.longValue());
                }
                if (user.getUsername() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, user.getUsername());
                }
                if (user.getFirstName() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, user.getFirstName());
                }
                if (user.getLastName() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, user.getLastName());
                }
                if (user.getFullName() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, user.getFullName());
                }
                if (user.getPhone() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, user.getPhone());
                }
                if (user.getEmail() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, user.getEmail());
                }
                if (user.getState() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, user.getState());
                }
                if (user.getCity() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, user.getCity());
                }
                if (user.getStreet() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, user.getStreet());
                }
                if (user.getZip() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, user.getZip());
                }
                if (user.getPhotoProfileUrl() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, user.getPhotoProfileUrl());
                }
                if (user.getPhotoIdUrl() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, user.getPhotoIdUrl());
                }
                if (user.getPhotoProfile() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, user.getPhotoProfile());
                }
                if (user.getPhotoId() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, user.getPhotoId());
                }
                supportSQLiteStatement.bindLong(20, user.getVerified() ? 1 : 0);
                supportSQLiteStatement.bindLong(21, user.isAdmin() ? 1 : 0);
                supportSQLiteStatement.bindLong(22, user.isNotificationSubscriptionEnabled() ? 1 : 0);
            }
        };
        this.__preparedStmtOfClearUser = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM User";
            }
        };
    }

    public void saveUser(User user) {
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUser.insert(user);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void clearUser() {
        SupportSQLiteStatement acquire = this.__preparedStmtOfClearUser.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfClearUser.release(acquire);
        }
    }

    public Flowable<User> getUser() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM user LIMIT 1", 0);
        return RxRoom.createFlowable(this.__db, new String[]{"user"}, new Callable<User>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v0, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v1, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v3, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Long} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v4, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v1, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX WARNING: type inference failed for: r25v2, types: [java.lang.Long] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.forasoft.homewavvisitor.model.data.auth.User call() throws java.lang.Exception {
                /*
                    r49 = this;
                    r1 = r49
                    com.forasoft.homewavvisitor.dao.UserDao_Impl r0 = com.forasoft.homewavvisitor.dao.UserDao_Impl.this
                    androidx.room.RoomDatabase r0 = r0.__db
                    androidx.room.RoomSQLiteQuery r2 = r0
                    android.database.Cursor r2 = r0.query(r2)
                    java.lang.String r0 = "id"
                    int r0 = r2.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x0152 }
                    java.lang.String r3 = "visitor_id"
                    int r3 = r2.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x0152 }
                    java.lang.String r4 = "created"
                    int r4 = r2.getColumnIndexOrThrow(r4)     // Catch:{ all -> 0x0152 }
                    java.lang.String r5 = "pin"
                    int r5 = r2.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x0152 }
                    java.lang.String r6 = "birthDate"
                    int r6 = r2.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x0152 }
                    java.lang.String r7 = "username"
                    int r7 = r2.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x0152 }
                    java.lang.String r8 = "firstName"
                    int r8 = r2.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x0152 }
                    java.lang.String r9 = "lastName"
                    int r9 = r2.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x0152 }
                    java.lang.String r10 = "fullName"
                    int r10 = r2.getColumnIndexOrThrow(r10)     // Catch:{ all -> 0x0152 }
                    java.lang.String r11 = "phone"
                    int r11 = r2.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x0152 }
                    java.lang.String r12 = "email"
                    int r12 = r2.getColumnIndexOrThrow(r12)     // Catch:{ all -> 0x0152 }
                    java.lang.String r13 = "state"
                    int r13 = r2.getColumnIndexOrThrow(r13)     // Catch:{ all -> 0x0152 }
                    java.lang.String r14 = "city"
                    int r14 = r2.getColumnIndexOrThrow(r14)     // Catch:{ all -> 0x0152 }
                    java.lang.String r15 = "street"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    r16 = r15
                    java.lang.String r15 = "zip"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    r17 = r15
                    java.lang.String r15 = "photoProfileUrl"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    r18 = r15
                    java.lang.String r15 = "photoIdUrl"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    r19 = r15
                    java.lang.String r15 = "photoProfile"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    r20 = r15
                    java.lang.String r15 = "photoId"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    r21 = r15
                    java.lang.String r15 = "verified"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    r22 = r15
                    java.lang.String r15 = "isAdmin"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    r23 = r15
                    java.lang.String r15 = "isNotificationSubscriptionEnabled"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0152 }
                    boolean r24 = r2.moveToFirst()     // Catch:{ all -> 0x0152 }
                    r25 = 0
                    if (r24 == 0) goto L_0x014e
                    java.lang.String r27 = r2.getString(r0)     // Catch:{ all -> 0x0152 }
                    java.lang.String r28 = r2.getString(r3)     // Catch:{ all -> 0x0152 }
                    java.lang.String r29 = r2.getString(r4)     // Catch:{ all -> 0x0152 }
                    java.lang.String r30 = r2.getString(r5)     // Catch:{ all -> 0x0152 }
                    boolean r0 = r2.isNull(r6)     // Catch:{ all -> 0x0152 }
                    if (r0 == 0) goto L_0x00c7
                L_0x00c4:
                    r0 = r25
                    goto L_0x00d0
                L_0x00c7:
                    long r3 = r2.getLong(r6)     // Catch:{ all -> 0x0152 }
                    java.lang.Long r25 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0152 }
                    goto L_0x00c4
                L_0x00d0:
                    com.forasoft.homewavvisitor.dao.UserDao_Impl r3 = com.forasoft.homewavvisitor.dao.UserDao_Impl.this     // Catch:{ all -> 0x0152 }
                    com.forasoft.homewavvisitor.dao.converters.DateConverter r3 = r3.__dateConverter     // Catch:{ all -> 0x0152 }
                    java.util.Date r31 = r3.toDate(r0)     // Catch:{ all -> 0x0152 }
                    java.lang.String r32 = r2.getString(r7)     // Catch:{ all -> 0x0152 }
                    java.lang.String r33 = r2.getString(r8)     // Catch:{ all -> 0x0152 }
                    java.lang.String r34 = r2.getString(r9)     // Catch:{ all -> 0x0152 }
                    java.lang.String r35 = r2.getString(r10)     // Catch:{ all -> 0x0152 }
                    java.lang.String r36 = r2.getString(r11)     // Catch:{ all -> 0x0152 }
                    java.lang.String r37 = r2.getString(r12)     // Catch:{ all -> 0x0152 }
                    java.lang.String r38 = r2.getString(r13)     // Catch:{ all -> 0x0152 }
                    java.lang.String r39 = r2.getString(r14)     // Catch:{ all -> 0x0152 }
                    r0 = r16
                    java.lang.String r40 = r2.getString(r0)     // Catch:{ all -> 0x0152 }
                    r0 = r17
                    java.lang.String r41 = r2.getString(r0)     // Catch:{ all -> 0x0152 }
                    r0 = r18
                    java.lang.String r42 = r2.getString(r0)     // Catch:{ all -> 0x0152 }
                    r0 = r19
                    java.lang.String r43 = r2.getString(r0)     // Catch:{ all -> 0x0152 }
                    r0 = r20
                    java.lang.String r44 = r2.getString(r0)     // Catch:{ all -> 0x0152 }
                    r0 = r21
                    java.lang.String r45 = r2.getString(r0)     // Catch:{ all -> 0x0152 }
                    r0 = r22
                    int r0 = r2.getInt(r0)     // Catch:{ all -> 0x0152 }
                    r3 = 1
                    r4 = 0
                    if (r0 == 0) goto L_0x012d
                    r0 = r23
                    r46 = 1
                    goto L_0x0131
                L_0x012d:
                    r0 = r23
                    r46 = 0
                L_0x0131:
                    int r0 = r2.getInt(r0)     // Catch:{ all -> 0x0152 }
                    if (r0 == 0) goto L_0x013a
                    r47 = 1
                    goto L_0x013c
                L_0x013a:
                    r47 = 0
                L_0x013c:
                    int r0 = r2.getInt(r15)     // Catch:{ all -> 0x0152 }
                    if (r0 == 0) goto L_0x0145
                    r48 = 1
                    goto L_0x0147
                L_0x0145:
                    r48 = 0
                L_0x0147:
                    com.forasoft.homewavvisitor.model.data.auth.User r25 = new com.forasoft.homewavvisitor.model.data.auth.User     // Catch:{ all -> 0x0152 }
                    r26 = r25
                    r26.<init>(r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48)     // Catch:{ all -> 0x0152 }
                L_0x014e:
                    r2.close()
                    return r25
                L_0x0152:
                    r0 = move-exception
                    r2.close()
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.dao.UserDao_Impl.AnonymousClass3.call():com.forasoft.homewavvisitor.model.data.auth.User");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Single<User> getSingleUser() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM user LIMIT 1", 0);
        return Single.fromCallable(new Callable<User>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v0, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v1, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v3, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Long} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v4, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v1, resolved type: com.forasoft.homewavvisitor.model.data.auth.User} */
            /* JADX WARNING: type inference failed for: r25v2, types: [java.lang.Long] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.forasoft.homewavvisitor.model.data.auth.User call() throws java.lang.Exception {
                /*
                    r49 = this;
                    r1 = r49
                    com.forasoft.homewavvisitor.dao.UserDao_Impl r0 = com.forasoft.homewavvisitor.dao.UserDao_Impl.this
                    androidx.room.RoomDatabase r0 = r0.__db
                    androidx.room.RoomSQLiteQuery r2 = r0
                    android.database.Cursor r2 = r0.query(r2)
                    java.lang.String r0 = "id"
                    int r0 = r2.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x0171 }
                    java.lang.String r3 = "visitor_id"
                    int r3 = r2.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x0171 }
                    java.lang.String r4 = "created"
                    int r4 = r2.getColumnIndexOrThrow(r4)     // Catch:{ all -> 0x0171 }
                    java.lang.String r5 = "pin"
                    int r5 = r2.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x0171 }
                    java.lang.String r6 = "birthDate"
                    int r6 = r2.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x0171 }
                    java.lang.String r7 = "username"
                    int r7 = r2.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x0171 }
                    java.lang.String r8 = "firstName"
                    int r8 = r2.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x0171 }
                    java.lang.String r9 = "lastName"
                    int r9 = r2.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x0171 }
                    java.lang.String r10 = "fullName"
                    int r10 = r2.getColumnIndexOrThrow(r10)     // Catch:{ all -> 0x0171 }
                    java.lang.String r11 = "phone"
                    int r11 = r2.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x0171 }
                    java.lang.String r12 = "email"
                    int r12 = r2.getColumnIndexOrThrow(r12)     // Catch:{ all -> 0x0171 }
                    java.lang.String r13 = "state"
                    int r13 = r2.getColumnIndexOrThrow(r13)     // Catch:{ all -> 0x0171 }
                    java.lang.String r14 = "city"
                    int r14 = r2.getColumnIndexOrThrow(r14)     // Catch:{ all -> 0x0171 }
                    java.lang.String r15 = "street"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    r16 = r15
                    java.lang.String r15 = "zip"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    r17 = r15
                    java.lang.String r15 = "photoProfileUrl"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    r18 = r15
                    java.lang.String r15 = "photoIdUrl"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    r19 = r15
                    java.lang.String r15 = "photoProfile"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    r20 = r15
                    java.lang.String r15 = "photoId"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    r21 = r15
                    java.lang.String r15 = "verified"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    r22 = r15
                    java.lang.String r15 = "isAdmin"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    r23 = r15
                    java.lang.String r15 = "isNotificationSubscriptionEnabled"
                    int r15 = r2.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x0171 }
                    boolean r24 = r2.moveToFirst()     // Catch:{ all -> 0x0171 }
                    r25 = 0
                    if (r24 == 0) goto L_0x014e
                    java.lang.String r27 = r2.getString(r0)     // Catch:{ all -> 0x0171 }
                    java.lang.String r28 = r2.getString(r3)     // Catch:{ all -> 0x0171 }
                    java.lang.String r29 = r2.getString(r4)     // Catch:{ all -> 0x0171 }
                    java.lang.String r30 = r2.getString(r5)     // Catch:{ all -> 0x0171 }
                    boolean r0 = r2.isNull(r6)     // Catch:{ all -> 0x0171 }
                    if (r0 == 0) goto L_0x00c7
                L_0x00c4:
                    r0 = r25
                    goto L_0x00d0
                L_0x00c7:
                    long r3 = r2.getLong(r6)     // Catch:{ all -> 0x0171 }
                    java.lang.Long r25 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0171 }
                    goto L_0x00c4
                L_0x00d0:
                    com.forasoft.homewavvisitor.dao.UserDao_Impl r3 = com.forasoft.homewavvisitor.dao.UserDao_Impl.this     // Catch:{ all -> 0x0171 }
                    com.forasoft.homewavvisitor.dao.converters.DateConverter r3 = r3.__dateConverter     // Catch:{ all -> 0x0171 }
                    java.util.Date r31 = r3.toDate(r0)     // Catch:{ all -> 0x0171 }
                    java.lang.String r32 = r2.getString(r7)     // Catch:{ all -> 0x0171 }
                    java.lang.String r33 = r2.getString(r8)     // Catch:{ all -> 0x0171 }
                    java.lang.String r34 = r2.getString(r9)     // Catch:{ all -> 0x0171 }
                    java.lang.String r35 = r2.getString(r10)     // Catch:{ all -> 0x0171 }
                    java.lang.String r36 = r2.getString(r11)     // Catch:{ all -> 0x0171 }
                    java.lang.String r37 = r2.getString(r12)     // Catch:{ all -> 0x0171 }
                    java.lang.String r38 = r2.getString(r13)     // Catch:{ all -> 0x0171 }
                    java.lang.String r39 = r2.getString(r14)     // Catch:{ all -> 0x0171 }
                    r0 = r16
                    java.lang.String r40 = r2.getString(r0)     // Catch:{ all -> 0x0171 }
                    r0 = r17
                    java.lang.String r41 = r2.getString(r0)     // Catch:{ all -> 0x0171 }
                    r0 = r18
                    java.lang.String r42 = r2.getString(r0)     // Catch:{ all -> 0x0171 }
                    r0 = r19
                    java.lang.String r43 = r2.getString(r0)     // Catch:{ all -> 0x0171 }
                    r0 = r20
                    java.lang.String r44 = r2.getString(r0)     // Catch:{ all -> 0x0171 }
                    r0 = r21
                    java.lang.String r45 = r2.getString(r0)     // Catch:{ all -> 0x0171 }
                    r0 = r22
                    int r0 = r2.getInt(r0)     // Catch:{ all -> 0x0171 }
                    r3 = 1
                    r4 = 0
                    if (r0 == 0) goto L_0x012d
                    r0 = r23
                    r46 = 1
                    goto L_0x0131
                L_0x012d:
                    r0 = r23
                    r46 = 0
                L_0x0131:
                    int r0 = r2.getInt(r0)     // Catch:{ all -> 0x0171 }
                    if (r0 == 0) goto L_0x013a
                    r47 = 1
                    goto L_0x013c
                L_0x013a:
                    r47 = 0
                L_0x013c:
                    int r0 = r2.getInt(r15)     // Catch:{ all -> 0x0171 }
                    if (r0 == 0) goto L_0x0145
                    r48 = 1
                    goto L_0x0147
                L_0x0145:
                    r48 = 0
                L_0x0147:
                    com.forasoft.homewavvisitor.model.data.auth.User r25 = new com.forasoft.homewavvisitor.model.data.auth.User     // Catch:{ all -> 0x0171 }
                    r26 = r25
                    r26.<init>(r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48)     // Catch:{ all -> 0x0171 }
                L_0x014e:
                    if (r25 == 0) goto L_0x0154
                    r2.close()
                    return r25
                L_0x0154:
                    androidx.room.EmptyResultSetException r0 = new androidx.room.EmptyResultSetException     // Catch:{ all -> 0x0171 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0171 }
                    r3.<init>()     // Catch:{ all -> 0x0171 }
                    java.lang.String r4 = "Query returned empty result set: "
                    r3.append(r4)     // Catch:{ all -> 0x0171 }
                    androidx.room.RoomSQLiteQuery r4 = r0     // Catch:{ all -> 0x0171 }
                    java.lang.String r4 = r4.getSql()     // Catch:{ all -> 0x0171 }
                    r3.append(r4)     // Catch:{ all -> 0x0171 }
                    java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0171 }
                    r0.<init>(r3)     // Catch:{ all -> 0x0171 }
                    throw r0     // Catch:{ all -> 0x0171 }
                L_0x0171:
                    r0 = move-exception
                    r2.close()
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.dao.UserDao_Impl.AnonymousClass4.call():com.forasoft.homewavvisitor.model.data.auth.User");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }
}
