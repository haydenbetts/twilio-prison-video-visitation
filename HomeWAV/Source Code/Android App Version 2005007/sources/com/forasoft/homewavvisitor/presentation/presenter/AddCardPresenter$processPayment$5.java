package com.forasoft.homewavvisitor.presentation.presenter;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.presentation.view.AddCardView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddCardPresenter.kt */
final class AddCardPresenter$processPayment$5<T> implements Consumer<Throwable> {
    final /* synthetic */ AddCardPresenter this$0;

    AddCardPresenter$processPayment$5(AddCardPresenter addCardPresenter) {
        this.this$0 = addCardPresenter;
    }

    public final void accept(Throwable th) {
        ((AddCardView) this.this$0.getViewState()).showMessage((int) R.string.funds_not_added);
        this.this$0.closeScreen();
    }
}
