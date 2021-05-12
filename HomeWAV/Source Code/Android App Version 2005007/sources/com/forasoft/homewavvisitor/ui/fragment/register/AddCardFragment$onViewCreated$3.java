package com.forasoft.homewavvisitor.ui.fragment.register;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.ui.views.ProgressButton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "isChecked", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddCardFragment.kt */
final class AddCardFragment$onViewCreated$3 extends Lambda implements Function2<CompoundButton, Boolean, Unit> {
    final /* synthetic */ AddCardFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddCardFragment$onViewCreated$3(AddCardFragment addCardFragment) {
        super(2);
        this.this$0 = addCardFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((CompoundButton) obj, ((Boolean) obj2).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(CompoundButton compoundButton, boolean z) {
        CharSequence charSequence;
        ProgressButton progressButton = (ProgressButton) this.this$0._$_findCachedViewById(R.id.buttonContinue);
        if (this.this$0.getShouldInitializeBraintree() || z) {
            String string = this.this$0.getResources().getString(air.HomeWAV.R.string.label_add_card);
            Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.label_add_card)");
            charSequence = string;
        } else {
            String string2 = this.this$0.getResources().getString(air.HomeWAV.R.string.label_add_funds);
            Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.string.label_add_funds)");
            charSequence = string2;
        }
        progressButton.setText(charSequence);
        if (z) {
            CommonKt.show((CheckBox) this.this$0._$_findCachedViewById(R.id.checkMakeDefault));
            CommonKt.show((TextView) this.this$0._$_findCachedViewById(R.id.labelMakeDefault));
            return;
        }
        CheckBox checkBox = (CheckBox) this.this$0._$_findCachedViewById(R.id.checkMakeDefault);
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkMakeDefault");
        checkBox.setChecked(false);
        CommonKt.hide((CheckBox) this.this$0._$_findCachedViewById(R.id.checkMakeDefault));
        CommonKt.hide((TextView) this.this$0._$_findCachedViewById(R.id.labelMakeDefault));
    }
}
