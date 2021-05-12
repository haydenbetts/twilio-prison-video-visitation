package com.forasoft.homewavvisitor.dao;

import com.forasoft.homewavvisitor.model.data.auth.User;
import io.reactivex.Flowable;
import io.reactivex.Single;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0006H'¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/UserDao;", "", "clearUser", "", "getSingleUser", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "getUser", "Lio/reactivex/Flowable;", "saveUser", "user", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: UserDao.kt */
public interface UserDao {
    void clearUser();

    Single<User> getSingleUser();

    Flowable<User> getUser();

    void saveUser(User user);
}
