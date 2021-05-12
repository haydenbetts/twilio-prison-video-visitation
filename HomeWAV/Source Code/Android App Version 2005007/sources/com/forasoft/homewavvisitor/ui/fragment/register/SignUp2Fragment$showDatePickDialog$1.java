package com.forasoft.homewavvisitor.ui.fragment.register;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "view", "Landroid/widget/DatePicker;", "kotlin.jvm.PlatformType", "year", "", "month", "dayOfMonth", "onDateSet"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignUp2Fragment.kt */
final class SignUp2Fragment$showDatePickDialog$1 implements DatePickerDialog.OnDateSetListener {
    final /* synthetic */ SignUp2Fragment this$0;

    SignUp2Fragment$showDatePickDialog$1(SignUp2Fragment signUp2Fragment) {
        this.this$0 = signUp2Fragment;
    }

    public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        this.this$0.getPresenter().onDatePicked(i3, i2, i);
    }
}
