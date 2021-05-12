package com.forasoft.homewavvisitor.dao;

import com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H'¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/UserAnalyticsDao;", "", "getAnalyticsForUser", "Lcom/forasoft/homewavvisitor/model/data/analytics/UserAnalytics;", "userId", "", "saveAnalytics", "", "analytics", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: UserAnalyticsDao.kt */
public interface UserAnalyticsDao {
    UserAnalytics getAnalyticsForUser(long j);

    void saveAnalytics(UserAnalytics userAnalytics);
}
