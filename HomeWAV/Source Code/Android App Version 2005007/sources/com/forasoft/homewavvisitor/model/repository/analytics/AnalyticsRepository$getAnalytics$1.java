package com.forasoft.homewavvisitor.model.repository.analytics;

import com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/ObservableEmitter;", "Lcom/forasoft/homewavvisitor/model/data/analytics/UserAnalytics;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
/* compiled from: AnalyticsRepository.kt */
final class AnalyticsRepository$getAnalytics$1<T> implements ObservableOnSubscribe<T> {
    final /* synthetic */ String $userId;
    final /* synthetic */ AnalyticsRepository this$0;

    AnalyticsRepository$getAnalytics$1(AnalyticsRepository analyticsRepository, String str) {
        this.this$0 = analyticsRepository;
        this.$userId = str;
    }

    public final void subscribe(ObservableEmitter<UserAnalytics> observableEmitter) {
        Intrinsics.checkParameterIsNotNull(observableEmitter, "it");
        observableEmitter.onNext(this.this$0.analyticsDao.getAnalyticsForUser(Long.parseLong(this.$userId)));
    }
}
