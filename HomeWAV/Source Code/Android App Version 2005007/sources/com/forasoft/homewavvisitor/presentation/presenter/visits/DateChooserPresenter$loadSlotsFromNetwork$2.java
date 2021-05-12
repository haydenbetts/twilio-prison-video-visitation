package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.presentation.view.visits.DateChooserView;
import com.google.gson.stream.MalformedJsonException;
import io.reactivex.functions.Consumer;
import java.net.SocketTimeoutException;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: DateChooserPresenter.kt */
final class DateChooserPresenter$loadSlotsFromNetwork$2<T> implements Consumer<Throwable> {
    final /* synthetic */ DateChooserPresenter this$0;

    DateChooserPresenter$loadSlotsFromNetwork$2(DateChooserPresenter dateChooserPresenter) {
        this.this$0 = dateChooserPresenter;
    }

    public final void accept(Throwable th) {
        ((DateChooserView) this.this$0.getViewState()).hideProgress();
        if ((th instanceof MalformedJsonException) || (th instanceof SocketTimeoutException)) {
            ((DateChooserView) this.this$0.getViewState()).showServerError();
        }
    }
}
