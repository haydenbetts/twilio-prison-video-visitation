package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.CallEntity;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.ReportResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/ReportResponse;", "it", "Lcom/forasoft/homewavvisitor/model/data/CallEntity;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: ReportBugPresenter.kt */
final class ReportBugPresenter$onReportClicked$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ String $problems;
    final /* synthetic */ ReportBugPresenter this$0;

    ReportBugPresenter$onReportClicked$1(ReportBugPresenter reportBugPresenter, String str) {
        this.this$0 = reportBugPresenter;
        this.$problems = str;
    }

    public final Single<ApiResponse<ReportResponse>> apply(CallEntity callEntity) {
        Intrinsics.checkParameterIsNotNull(callEntity, "it");
        return this.this$0.api.reportError(this.$problems, callEntity.getId());
    }
}
