package com.forasoft.homewavvisitor.ui.fragment.account;

import android.view.View;
import android.widget.EditText;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.presentation.presenter.account.RefundsPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: RefundsFragment.kt */
final class RefundsFragment$onViewCreated$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ RefundsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RefundsFragment$onViewCreated$1(RefundsFragment refundsFragment) {
        super(1);
        this.this$0 = refundsFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        RefundsPresenter presenter = this.this$0.getPresenter();
        EditText editText = (EditText) this.this$0._$_findCachedViewById(R.id.et_amount);
        Intrinsics.checkExpressionValueIsNotNull(editText, "et_amount");
        String obj = editText.getText().toString();
        EditText editText2 = (EditText) this.this$0._$_findCachedViewById(R.id.et_comments);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "et_comments");
        presenter.onRequestClicked(obj, editText2.getText().toString());
    }
}
