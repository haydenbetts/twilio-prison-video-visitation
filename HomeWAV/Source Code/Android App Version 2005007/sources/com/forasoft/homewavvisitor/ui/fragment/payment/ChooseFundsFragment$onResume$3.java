package com.forasoft.homewavvisitor.ui.fragment.payment;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ToggleButton;
import com.forasoft.homewavvisitor.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsFragment.kt */
final class ChooseFundsFragment$onResume$3 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ ChooseFundsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChooseFundsFragment$onResume$3(ChooseFundsFragment chooseFundsFragment) {
        super(1);
        this.this$0 = chooseFundsFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        Spinner spinner = (Spinner) this.this$0._$_findCachedViewById(R.id.paymentMethodsSpinner);
        Intrinsics.checkExpressionValueIsNotNull(spinner, "paymentMethodsSpinner");
        if (spinner.getCount() != 0) {
            Spinner spinner2 = (Spinner) this.this$0._$_findCachedViewById(R.id.paymentMethodsSpinner);
            Intrinsics.checkExpressionValueIsNotNull(spinner2, "paymentMethodsSpinner");
            int selectedItemPosition = spinner2.getSelectedItemPosition();
            Spinner spinner3 = (Spinner) this.this$0._$_findCachedViewById(R.id.paymentMethodsSpinner);
            Intrinsics.checkExpressionValueIsNotNull(spinner3, "paymentMethodsSpinner");
            SpinnerAdapter adapter = spinner3.getAdapter();
            Intrinsics.checkExpressionValueIsNotNull(adapter, "paymentMethodsSpinner.adapter");
            if (selectedItemPosition == adapter.getCount() - 1) {
                Collection arrayList = new ArrayList();
                for (Object next : ChooseFundsFragment.access$getToggles$p(this.this$0)) {
                    if (((ToggleButton) next).isChecked()) {
                        arrayList.add(next);
                    }
                }
                Iterable<ToggleButton> iterable = (List) arrayList;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (ToggleButton indexOf : iterable) {
                    arrayList2.add(Integer.valueOf(ChooseFundsFragment.access$getToggles$p(this.this$0).indexOf(indexOf)));
                }
                int intValue = ((Number) CollectionsKt.first((List) arrayList2)).intValue();
                EditText editText = (EditText) this.this$0._$_findCachedViewById(R.id.editCustomAmount);
                Intrinsics.checkExpressionValueIsNotNull(editText, "editCustomAmount");
                this.this$0.getPresenter().onAddCardClicked(intValue, editText.getText().toString());
                return;
            }
        }
        this.this$0.getPresenter().onExecutePayment();
    }
}
