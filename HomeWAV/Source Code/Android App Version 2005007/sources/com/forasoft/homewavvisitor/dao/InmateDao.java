package com.forasoft.homewavvisitor.dao;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\u0006H'J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\r2\u0006\u0010\u000e\u001a\u00020\u0006H'J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\u0006H'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\u0006H'J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\u0006H'J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\nH'J\u0016\u0010\u0014\u001a\u00020\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\tH'J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\nH'¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/InmateDao;", "", "deleteAll", "", "deleteInmate", "id", "", "getApprovedInmates", "Lio/reactivex/Flowable;", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "visitorId", "getInmate", "Lio/reactivex/Single;", "inmateId", "getInmates", "getPendingInmate", "getPendingInmates", "saveInmate", "inmate", "saveInmates", "inmates", "updateInmate", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateDao.kt */
public interface InmateDao {
    void deleteAll();

    void deleteInmate(String str);

    Flowable<List<Inmate>> getApprovedInmates(String str);

    Single<Inmate> getInmate(String str);

    Flowable<List<Inmate>> getInmates(String str);

    Flowable<List<Inmate>> getPendingInmate(String str);

    Flowable<List<Inmate>> getPendingInmates(String str);

    void saveInmate(Inmate inmate);

    void saveInmates(List<Inmate> list);

    void updateInmate(Inmate inmate);
}
