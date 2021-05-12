package com.forasoft.homewavvisitor.ui.fragment.register;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.presentation.presenter.AddCardPresenter;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Token;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/register/AddCardFragment$tokenizeStripe$1", "Lcom/stripe/android/TokenCallback;", "onError", "", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "token", "Lcom/stripe/android/model/Token;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddCardFragment.kt */
public final class AddCardFragment$tokenizeStripe$1 implements TokenCallback {
    final /* synthetic */ AddCardFragment this$0;

    AddCardFragment$tokenizeStripe$1(AddCardFragment addCardFragment) {
        this.this$0 = addCardFragment;
    }

    public void onSuccess(Token token) {
        Intrinsics.checkParameterIsNotNull(token, "token");
        AddCardPresenter presenter = this.this$0.getPresenter();
        String id = token.getId();
        Intrinsics.checkExpressionValueIsNotNull(id, "token.id");
        presenter.onReceivePaymentMethod(id);
    }

    public void onError(Exception exc) {
        Intrinsics.checkParameterIsNotNull(exc, "error");
        Timber.e(exc);
        this.this$0.showMessage((int) R.string.card_error);
        this.this$0.showProgress(false);
    }
}
