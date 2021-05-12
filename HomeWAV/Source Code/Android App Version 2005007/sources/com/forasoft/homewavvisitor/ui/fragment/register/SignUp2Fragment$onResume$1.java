package com.forasoft.homewavvisitor.ui.fragment.register;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.presentation.presenter.register.SignUp2Presenter;
import com.google.android.material.textfield.TextInputEditText;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignUp2Fragment.kt */
final class SignUp2Fragment$onResume$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ SignUp2Fragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SignUp2Fragment$onResume$1(SignUp2Fragment signUp2Fragment) {
        super(1);
        this.this$0 = signUp2Fragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        SignUp2Presenter presenter = this.this$0.getPresenter();
        EditText editText = (EditText) this.this$0._$_findCachedViewById(R.id.editUsername);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editUsername");
        Editable text = editText.getText();
        EditText editText2 = (EditText) this.this$0._$_findCachedViewById(R.id.editEmail);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editEmail");
        Editable text2 = editText2.getText();
        TextInputEditText textInputEditText = (TextInputEditText) this.this$0._$_findCachedViewById(R.id.editPassword);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText, "editPassword");
        Editable text3 = textInputEditText.getText();
        EditText editText3 = (EditText) this.this$0._$_findCachedViewById(R.id.editFirstName);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "editFirstName");
        Editable text4 = editText3.getText();
        EditText editText4 = (EditText) this.this$0._$_findCachedViewById(R.id.editLastName);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "editLastName");
        Editable text5 = editText4.getText();
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editDateOfBirth");
        CharSequence text6 = textView.getText();
        EditText editText5 = (EditText) this.this$0._$_findCachedViewById(R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText5, "editPhone");
        Editable text7 = editText5.getText();
        EditText editText6 = (EditText) this.this$0._$_findCachedViewById(R.id.editStreet);
        Intrinsics.checkExpressionValueIsNotNull(editText6, "editStreet");
        Editable text8 = editText6.getText();
        EditText editText7 = (EditText) this.this$0._$_findCachedViewById(R.id.editCity);
        Intrinsics.checkExpressionValueIsNotNull(editText7, "editCity");
        Editable text9 = editText7.getText();
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.editState);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "editState");
        CharSequence text10 = textView2.getText();
        Intrinsics.checkExpressionValueIsNotNull(text10, "editState.text");
        EditText editText8 = (EditText) this.this$0._$_findCachedViewById(R.id.editZip);
        Intrinsics.checkExpressionValueIsNotNull(editText8, "editZip");
        Editable text11 = editText8.getText();
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) this.this$0._$_findCachedViewById(R.id.checkEmailSubscription);
        Intrinsics.checkExpressionValueIsNotNull(appCompatCheckBox, "checkEmailSubscription");
        presenter.onNextClicked(text, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, appCompatCheckBox.isChecked());
    }
}
