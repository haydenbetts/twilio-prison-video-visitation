package com.forasoft.homewavvisitor.ui.fragment.register;

import android.view.View;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.presentation.presenter.register.SignUp2Presenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignUp2Fragment.kt */
final class SignUp2Fragment$onResume$4 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ SignUp2Fragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SignUp2Fragment$onResume$4(SignUp2Fragment signUp2Fragment) {
        super(1);
        this.this$0 = signUp2Fragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        SignUp2Presenter presenter = this.this$0.getPresenter();
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editDateOfBirth");
        CharSequence text = textView.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "editDateOfBirth.text");
        presenter.onDateClicked(text);
    }
}
