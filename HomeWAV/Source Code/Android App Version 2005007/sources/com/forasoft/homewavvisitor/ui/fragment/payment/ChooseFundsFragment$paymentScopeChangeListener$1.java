package com.forasoft.homewavvisitor.ui.fragment.payment;

import android.widget.CompoundButton;
import android.widget.RadioButton;
import com.forasoft.homewavvisitor.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsFragment.kt */
final class ChooseFundsFragment$paymentScopeChangeListener$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ ChooseFundsFragment this$0;

    ChooseFundsFragment$paymentScopeChangeListener$1(ChooseFundsFragment chooseFundsFragment) {
        this.this$0 = chooseFundsFragment;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!z) {
            return;
        }
        if (Intrinsics.areEqual((Object) compoundButton, (Object) (RadioButton) this.this$0._$_findCachedViewById(R.id.btn_talk_to_me))) {
            this.this$0.getPresenter().onChoosePaymentScope("inmate");
            RadioButton radioButton = (RadioButton) this.this$0._$_findCachedViewById(R.id.btn_general_funds);
            Intrinsics.checkExpressionValueIsNotNull(radioButton, "btn_general_funds");
            radioButton.setChecked(false);
        } else if (Intrinsics.areEqual((Object) compoundButton, (Object) (RadioButton) this.this$0._$_findCachedViewById(R.id.btn_general_funds))) {
            this.this$0.getPresenter().onChoosePaymentScope("occupant");
            RadioButton radioButton2 = (RadioButton) this.this$0._$_findCachedViewById(R.id.btn_talk_to_me);
            Intrinsics.checkExpressionValueIsNotNull(radioButton2, "btn_talk_to_me");
            radioButton2.setChecked(false);
        }
    }
}
