package com.forasoft.homewavvisitor.presentation.adapter.account;

import android.widget.CompoundButton;
import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.presentation.adapter.account.EditCardsAdapter;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged", "com/forasoft/homewavvisitor/presentation/adapter/account/EditCardsAdapter$PaymentMethodVH$bind$1$2"}, k = 3, mv = {1, 1, 16})
/* compiled from: EditCardsAdapter.kt */
final class EditCardsAdapter$PaymentMethodVH$bind$$inlined$with$lambda$2 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ Card $card$inlined;
    final /* synthetic */ EditCardsAdapter.PaymentMethodVH this$0;

    EditCardsAdapter$PaymentMethodVH$bind$$inlined$with$lambda$2(EditCardsAdapter.PaymentMethodVH paymentMethodVH, Card card) {
        this.this$0 = paymentMethodVH;
        this.$card$inlined = card;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.this$0.this$0.onChange.invoke(this.$card$inlined, Boolean.valueOf(z));
    }
}
