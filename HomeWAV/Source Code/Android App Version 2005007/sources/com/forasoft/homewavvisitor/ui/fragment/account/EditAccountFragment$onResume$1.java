package com.forasoft.homewavvisitor.ui.fragment.account;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.presentation.presenter.account.EditAccountPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: EditAccountFragment.kt */
final class EditAccountFragment$onResume$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ EditAccountFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EditAccountFragment$onResume$1(EditAccountFragment editAccountFragment) {
        super(1);
        this.this$0 = editAccountFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        Editable editable;
        EditAccountPresenter presenter = this.this$0.getPresenter();
        EditText editText = (EditText) this.this$0._$_findCachedViewById(R.id.editEmail);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editEmail");
        Editable text = editText.getText();
        TextInputLayout textInputLayout = (TextInputLayout) this.this$0._$_findCachedViewById(R.id.editPasswordWrapper);
        Intrinsics.checkExpressionValueIsNotNull(textInputLayout, "editPasswordWrapper");
        if (CommonKt.isVisible(textInputLayout)) {
            TextInputEditText textInputEditText = (TextInputEditText) this.this$0._$_findCachedViewById(R.id.editPassword);
            Intrinsics.checkExpressionValueIsNotNull(textInputEditText, "editPassword");
            editable = textInputEditText.getText();
        } else {
            editable = null;
        }
        Editable editable2 = editable;
        TextInputEditText textInputEditText2 = (TextInputEditText) this.this$0._$_findCachedViewById(R.id.editNewPassword);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText2, "editNewPassword");
        Editable text2 = textInputEditText2.getText();
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editDateOfBirth");
        CharSequence text3 = textView.getText();
        EditText editText2 = (EditText) this.this$0._$_findCachedViewById(R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editPhone");
        Editable text4 = editText2.getText();
        EditText editText3 = (EditText) this.this$0._$_findCachedViewById(R.id.editStreet);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "editStreet");
        Editable text5 = editText3.getText();
        EditText editText4 = (EditText) this.this$0._$_findCachedViewById(R.id.editCity);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "editCity");
        Editable text6 = editText4.getText();
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.editState);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "editState");
        CharSequence text7 = textView2.getText();
        Intrinsics.checkExpressionValueIsNotNull(text7, "editState.text");
        EditText editText5 = (EditText) this.this$0._$_findCachedViewById(R.id.editZip);
        Intrinsics.checkExpressionValueIsNotNull(editText5, "editZip");
        presenter.onNextClicked(text, editable2, text2, text3, text4, text5, text6, text7, editText5.getText());
    }
}
