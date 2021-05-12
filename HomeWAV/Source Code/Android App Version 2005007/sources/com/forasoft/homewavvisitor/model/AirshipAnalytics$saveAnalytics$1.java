package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics;
import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/model/data/analytics/UserAnalytics;", "call"}, k = 3, mv = {1, 1, 16})
/* compiled from: AirshipAnalytics.kt */
final class AirshipAnalytics$saveAnalytics$1<V> implements Callable<Object> {
    final /* synthetic */ UserAnalytics $userAnalytics;
    final /* synthetic */ AirshipAnalytics this$0;

    AirshipAnalytics$saveAnalytics$1(AirshipAnalytics airshipAnalytics, UserAnalytics userAnalytics) {
        this.this$0 = airshipAnalytics;
        this.$userAnalytics = userAnalytics;
    }

    public final UserAnalytics call() {
        return this.this$0.repository.saveAnalytics(this.$userAnalytics);
    }
}
