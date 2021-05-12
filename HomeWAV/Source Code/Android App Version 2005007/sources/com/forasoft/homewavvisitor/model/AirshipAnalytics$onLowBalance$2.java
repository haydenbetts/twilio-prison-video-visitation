package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.analytics.UserAnalytics;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AirshipAnalytics.kt */
final class AirshipAnalytics$onLowBalance$2<T> implements Consumer<Throwable> {
    final /* synthetic */ String $userId;
    final /* synthetic */ AirshipAnalytics this$0;

    AirshipAnalytics$onLowBalance$2(AirshipAnalytics airshipAnalytics, String str) {
        this.this$0 = airshipAnalytics;
        this.$userId = str;
    }

    public final void accept(Throwable th) {
        Observable create = Observable.create(new ObservableOnSubscribe<T>(this) {
            final /* synthetic */ AirshipAnalytics$onLowBalance$2 this$0;

            {
                this.this$0 = r1;
            }

            public final void subscribe(ObservableEmitter<UserAnalytics> observableEmitter) {
                Intrinsics.checkParameterIsNotNull(observableEmitter, "it");
                observableEmitter.onNext(this.this$0.this$0.repository.createEmptyAnalytics(this.this$0.$userId));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(create, "Observable.create<UserAn…cs)\n                    }");
        CommonKt.applyAsync(create).subscribe(new Consumer<UserAnalytics>(this) {
            final /* synthetic */ AirshipAnalytics$onLowBalance$2 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(UserAnalytics userAnalytics) {
                AirshipAnalytics airshipAnalytics = this.this$0.this$0;
                Intrinsics.checkExpressionValueIsNotNull(userAnalytics, "it");
                airshipAnalytics.sendLowBalanceEvent(userAnalytics);
            }
        });
    }
}
