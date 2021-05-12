package com.forasoft.homewavvisitor.presentation.presenter.calls;

import air.HomeWAV.R;
import android.util.MalformedJsonException;
import com.forasoft.homewavvisitor.presentation.view.calls.CallView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "V", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallPresenter.kt */
final class CallPresenter$onBugReportClicked$2<T> implements Consumer<Throwable> {
    final /* synthetic */ CallPresenter this$0;

    CallPresenter$onBugReportClicked$2(CallPresenter callPresenter) {
        this.this$0 = callPresenter;
    }

    public final void accept(Throwable th) {
        if (th instanceof MalformedJsonException) {
            ((CallView) this.this$0.getViewState()).showMessage((int) R.string.success_report);
        }
    }
}
