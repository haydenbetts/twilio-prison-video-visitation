package com.forasoft.homewavvisitor.presentation.presenter.account;

import air.HomeWAV.R;
import android.util.MalformedJsonException;
import androidx.room.EmptyResultSetException;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ReportBugPresenter.kt */
final class ReportBugPresenter$onReportClicked$3<T> implements Consumer<Throwable> {
    final /* synthetic */ ReportBugPresenter this$0;

    ReportBugPresenter$onReportClicked$3(ReportBugPresenter reportBugPresenter) {
        this.this$0 = reportBugPresenter;
    }

    public final void accept(Throwable th) {
        if (th instanceof EmptyResultSetException) {
            ((BaseView) this.this$0.getViewState()).showMessage((int) R.string.error_no_calls);
        } else if (th instanceof MalformedJsonException) {
            ((BaseView) this.this$0.getViewState()).showMessage((int) R.string.success_report);
        }
        this.this$0.router.exit();
    }
}
