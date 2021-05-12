package com.forasoft.homewavvisitor.presentation.presenter.account;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.ReportResponse;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/ReportResponse;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ReportBugPresenter.kt */
final class ReportBugPresenter$onReportClicked$2<T> implements Consumer<ApiResponse<? extends ReportResponse>> {
    final /* synthetic */ ReportBugPresenter this$0;

    ReportBugPresenter$onReportClicked$2(ReportBugPresenter reportBugPresenter) {
        this.this$0 = reportBugPresenter;
    }

    public final void accept(ApiResponse<ReportResponse> apiResponse) {
        ReportResponse body = apiResponse.getBody();
        Integer valueOf = body != null ? Integer.valueOf(body.getOk()) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            ((BaseView) this.this$0.getViewState()).showMessage((int) R.string.error_report);
        } else if (valueOf != null && valueOf.intValue() == 1) {
            ((BaseView) this.this$0.getViewState()).showMessage((int) R.string.success_report);
        } else if (valueOf != null && valueOf.intValue() == 2) {
            ((BaseView) this.this$0.getViewState()).showMessage((int) R.string.error_report_again);
        }
        this.this$0.router.exit();
    }
}
