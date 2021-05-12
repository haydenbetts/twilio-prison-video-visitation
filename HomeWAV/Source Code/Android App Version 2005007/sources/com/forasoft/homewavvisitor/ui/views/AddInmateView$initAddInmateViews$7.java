package com.forasoft.homewavvisitor.ui.views;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddInmateView.kt */
final class AddInmateView$initAddInmateViews$7 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ AddInmateView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddInmateView$initAddInmateViews$7(AddInmateView addInmateView) {
        super(1);
        this.this$0 = addInmateView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        int i;
        int i2;
        int i3;
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editDateOfBirth");
        CharSequence text = textView.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "text");
        if (text.length() == 0) {
            i3 = 2000;
            i2 = 0;
            i = 0;
        } else {
            Calendar instance = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(instance, "calendar");
            instance.setTime(new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(text.toString()));
            int i4 = instance.get(1);
            int i5 = instance.get(2);
            i3 = i4;
            i = instance.get(5);
            i2 = i5;
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(this.this$0.getActivity(), 3, new DatePickerDialog.OnDateSetListener(this) {
            final /* synthetic */ AddInmateView$initAddInmateViews$7 this$0;

            {
                this.this$0 = r1;
            }

            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                String format = new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(new GregorianCalendar(i, i2, i3).getTime());
                TextView textView = (TextView) this.this$0.this$0._$_findCachedViewById(R.id.editDateOfBirth);
                Intrinsics.checkExpressionValueIsNotNull(textView, "editDateOfBirth");
                textView.setText(format);
            }
        }, i3, i2, i);
        DatePicker datePicker = datePickerDialog.getDatePicker();
        Intrinsics.checkExpressionValueIsNotNull(datePicker, "datePicker");
        datePicker.setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }
}
