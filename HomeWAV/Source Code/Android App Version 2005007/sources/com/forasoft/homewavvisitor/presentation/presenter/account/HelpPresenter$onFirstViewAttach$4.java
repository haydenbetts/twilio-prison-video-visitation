package com.forasoft.homewavvisitor.presentation.presenter.account;

import android.util.MalformedJsonException;
import com.forasoft.homewavvisitor.presentation.view.account.HelpView;
import io.reactivex.functions.Consumer;
import java.net.SocketTimeoutException;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: HelpPresenter.kt */
final class HelpPresenter$onFirstViewAttach$4<T> implements Consumer<Throwable> {
    final /* synthetic */ HelpPresenter this$0;

    HelpPresenter$onFirstViewAttach$4(HelpPresenter helpPresenter) {
        this.this$0 = helpPresenter;
    }

    public final void accept(Throwable th) {
        if ((th instanceof MalformedJsonException) || (th instanceof SocketTimeoutException)) {
            ((HelpView) this.this$0.getViewState()).showServerError();
        }
    }
}
