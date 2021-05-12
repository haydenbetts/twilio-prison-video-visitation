package com.forasoft.homewavvisitor.dao;

import com.forasoft.homewavvisitor.model.data.CallEntity;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u001c\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0006\u0010\b\u001a\u00020\tH'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH'J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0007H'¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/CallDao;", "", "deleteAll", "", "getCallsWithInmate", "Lio/reactivex/Flowable;", "", "Lcom/forasoft/homewavvisitor/model/data/CallEntity;", "inmateId", "", "getLatestCall", "Lio/reactivex/Single;", "saveCall", "call", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallDao.kt */
public interface CallDao {
    void deleteAll();

    Flowable<List<CallEntity>> getCallsWithInmate(String str);

    Single<CallEntity> getLatestCall();

    void saveCall(CallEntity callEntity);
}
