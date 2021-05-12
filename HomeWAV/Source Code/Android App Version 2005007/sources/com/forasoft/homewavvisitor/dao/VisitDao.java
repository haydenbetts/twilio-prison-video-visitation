package com.forasoft.homewavvisitor.dao;

import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H'J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\rH'J$\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H'J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\rH'J$\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0016\u0010\u0012\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH'J\u0016\u0010\u0014\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0017¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/VisitDao;", "", "()V", "deleteAll", "", "deleteVisitById", "slotId", "", "getPendingVisits", "Lio/reactivex/Flowable;", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "timestamp", "", "prison", "getScheduledVisits", "getVisitById", "Lio/reactivex/Single;", "save", "visits", "saveVisits", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitDao.kt */
public abstract class VisitDao {
    public abstract void deleteAll();

    public abstract void deleteVisitById(String str);

    public abstract Flowable<List<ScheduledVisit>> getPendingVisits(long j);

    public abstract Flowable<List<ScheduledVisit>> getPendingVisits(long j, String str);

    public abstract Flowable<List<ScheduledVisit>> getScheduledVisits(long j);

    public abstract Flowable<List<ScheduledVisit>> getScheduledVisits(long j, String str);

    public abstract Single<ScheduledVisit> getVisitById(String str);

    public abstract void save(List<ScheduledVisit> list);

    public void saveVisits(List<ScheduledVisit> list) {
        Intrinsics.checkParameterIsNotNull(list, "visits");
        deleteAll();
        save(list);
    }
}
