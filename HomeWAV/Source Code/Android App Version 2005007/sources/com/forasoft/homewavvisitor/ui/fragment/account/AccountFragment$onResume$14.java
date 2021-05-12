package com.forasoft.homewavvisitor.ui.fragment.account;

import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.presentation.presenter.account.AccountPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: AccountFragment.kt */
final class AccountFragment$onResume$14 implements View.OnClickListener {
    final /* synthetic */ AccountFragment this$0;

    AccountFragment$onResume$14(AccountFragment accountFragment) {
        this.this$0 = accountFragment;
    }

    public final void onClick(View view) {
        AccountPresenter presenter = this.this$0.getPresenter();
        SwitchCompat switchCompat = (SwitchCompat) this.this$0._$_findCachedViewById(R.id.swEmailSubscription);
        Intrinsics.checkExpressionValueIsNotNull(switchCompat, "swEmailSubscription");
        presenter.onEmailSubscriptionChanged(switchCompat.isChecked());
    }
}
