package com.forasoft.homewavvisitor.presentation.presenter.calls;

import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TwilioCallPresenter.kt */
final class TwilioCallPresenter$startCall$2<T> implements Consumer<Throwable> {
    final /* synthetic */ TwilioCallPresenter this$0;

    TwilioCallPresenter$startCall$2(TwilioCallPresenter twilioCallPresenter) {
        this.this$0 = twilioCallPresenter;
    }

    public final void accept(Throwable th) {
        TwilioCallPresenter.onBackPressed$default(this.this$0, "CannotFetchToken", (String) null, 2, (Object) null);
    }
}
