package com.forasoft.homewavvisitor.dao;

import androidx.room.RoomDatabase;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "callDao", "Lcom/forasoft/homewavvisitor/dao/CallDao;", "creditDao", "Lcom/forasoft/homewavvisitor/dao/CreditDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "messageDao", "Lcom/forasoft/homewavvisitor/dao/MessageDao;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "userAnalyticsDao", "Lcom/forasoft/homewavvisitor/dao/UserAnalyticsDao;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "visitDao", "Lcom/forasoft/homewavvisitor/dao/VisitDao;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AppDatabase.kt */
public abstract class AppDatabase extends RoomDatabase {
    public abstract CallDao callDao();

    public abstract CreditDao creditDao();

    public abstract InmateDao inmateDao();

    public abstract MessageDao messageDao();

    public abstract NotificationDao notificationDao();

    public abstract UserAnalyticsDao userAnalyticsDao();

    public abstract UserDao userDao();

    public abstract VisitDao visitDao();
}
