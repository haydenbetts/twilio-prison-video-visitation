package com.forasoft.homewavvisitor.model.repository.analytics;

import com.forasoft.homewavvisitor.dao.UserAnalyticsDao;
import com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics;
import io.reactivex.Observable;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/model/repository/analytics/AnalyticsRepository;", "", "analyticsDao", "Lcom/forasoft/homewavvisitor/dao/UserAnalyticsDao;", "(Lcom/forasoft/homewavvisitor/dao/UserAnalyticsDao;)V", "createEmptyAnalytics", "Lcom/forasoft/homewavvisitor/model/data/analytics/UserAnalytics;", "userId", "", "getAnalytics", "Lio/reactivex/Observable;", "saveAnalytics", "analytics", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AnalyticsRepository.kt */
public final class AnalyticsRepository {
    /* access modifiers changed from: private */
    public final UserAnalyticsDao analyticsDao;

    @Inject
    public AnalyticsRepository(UserAnalyticsDao userAnalyticsDao) {
        Intrinsics.checkParameterIsNotNull(userAnalyticsDao, "analyticsDao");
        this.analyticsDao = userAnalyticsDao;
    }

    public final Observable<UserAnalytics> getAnalytics(String str) {
        Intrinsics.checkParameterIsNotNull(str, "userId");
        Observable<UserAnalytics> create = Observable.create(new AnalyticsRepository$getAnalytics$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(create, "Observable.create {\n    …Next(analytics)\n        }");
        return create;
    }

    public final UserAnalytics saveAnalytics(UserAnalytics userAnalytics) {
        Intrinsics.checkParameterIsNotNull(userAnalytics, Modules.ANALYTICS_MODULE);
        this.analyticsDao.saveAnalytics(userAnalytics);
        return userAnalytics;
    }

    public final UserAnalytics createEmptyAnalytics(String str) {
        Intrinsics.checkParameterIsNotNull(str, "userId");
        return saveAnalytics(new UserAnalytics(Long.parseLong(str), false, (Long) null, 0, 0L, 0, false, false));
    }
}
