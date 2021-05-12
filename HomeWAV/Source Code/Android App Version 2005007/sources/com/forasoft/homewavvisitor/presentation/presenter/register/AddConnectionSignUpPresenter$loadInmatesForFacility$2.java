package com.forasoft.homewavvisitor.presentation.presenter.register;

import android.os.Handler;
import com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView;
import com.twilio.video.TestUtils;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddConnectionSignUpPresenter.kt */
final class AddConnectionSignUpPresenter$loadInmatesForFacility$2<T> implements Consumer<Throwable> {
    final /* synthetic */ AddConnectionSignUpPresenter this$0;

    AddConnectionSignUpPresenter$loadInmatesForFacility$2(AddConnectionSignUpPresenter addConnectionSignUpPresenter) {
        this.this$0 = addConnectionSignUpPresenter;
    }

    public final void accept(Throwable th) {
        Intrinsics.checkExpressionValueIsNotNull(th, "it");
        String localizedMessage = th.getLocalizedMessage();
        Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "it.localizedMessage");
        ((AddConnectionView) this.this$0.getViewState()).showMessage(localizedMessage);
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ AddConnectionSignUpPresenter$loadInmatesForFacility$2 this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                this.this$0.this$0.loadInmatesForFacility();
            }
        }, TestUtils.THREE_SECONDS);
    }
}
