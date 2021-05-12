package com.forasoft.homewavvisitor.presentation.presenter.auth;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.presentation.view.auth.VerifyMethodView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "com/forasoft/homewavvisitor/presentation/presenter/auth/VerifyMethodPresenter$onSendClicked$1$2"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyMethodPresenter.kt */
final class VerifyMethodPresenter$onSendClicked$$inlined$let$lambda$2<T> implements Consumer<Throwable> {
    final /* synthetic */ String $channel$inlined;
    final /* synthetic */ VerifyMethodPresenter this$0;

    VerifyMethodPresenter$onSendClicked$$inlined$let$lambda$2(VerifyMethodPresenter verifyMethodPresenter, String str) {
        this.this$0 = verifyMethodPresenter;
        this.$channel$inlined = str;
    }

    public final void accept(Throwable th) {
        ((VerifyMethodView) this.this$0.getViewState()).showMessage((int) R.string.label_server_error);
    }
}
