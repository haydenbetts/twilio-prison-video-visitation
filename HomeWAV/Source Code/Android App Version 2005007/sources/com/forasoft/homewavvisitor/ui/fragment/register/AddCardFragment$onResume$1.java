package com.forasoft.homewavvisitor.ui.fragment.register;

import com.redmadrobot.inputmask.MaskedTextChangedListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/register/AddCardFragment$onResume$1", "Lcom/redmadrobot/inputmask/MaskedTextChangedListener$ValueListener;", "onTextChanged", "", "maskFilled", "", "extractedValue", "", "formattedValue", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddCardFragment.kt */
public final class AddCardFragment$onResume$1 implements MaskedTextChangedListener.ValueListener {
    final /* synthetic */ AddCardFragment this$0;

    AddCardFragment$onResume$1(AddCardFragment addCardFragment) {
        this.this$0 = addCardFragment;
    }

    public void onTextChanged(boolean z, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "extractedValue");
        Intrinsics.checkParameterIsNotNull(str2, "formattedValue");
        this.this$0.getPresenter().onCardNumberChanged(str);
    }
}
