package com.forasoft.homewavvisitor.ui.fragment.register;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0017J*\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0016J*\u0010\u0013\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/register/AddCardFragment$expiresTextWatcher$1", "Landroid/text/TextWatcher;", "deleting", "", "getDeleting", "()Z", "setDeleting", "(Z)V", "afterTextChanged", "", "text", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onTextChanged", "before", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddCardFragment.kt */
public final class AddCardFragment$expiresTextWatcher$1 implements TextWatcher {
    private boolean deleting;
    final /* synthetic */ AddCardFragment this$0;

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    AddCardFragment$expiresTextWatcher$1(AddCardFragment addCardFragment) {
        this.this$0 = addCardFragment;
    }

    public final boolean getDeleting() {
        return this.deleting;
    }

    public final void setDeleting(boolean z) {
        this.deleting = z;
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null && !this.deleting) {
            int length = editable.length();
            if (length == 1) {
                if (!new Regex("[01]").matches(editable)) {
                    EditText editText = (EditText) this.this$0._$_findCachedViewById(R.id.editExpires);
                    Intrinsics.checkExpressionValueIsNotNull(editText, "editExpires");
                    StringBuilder sb = new StringBuilder();
                    sb.append('0');
                    sb.append(editable);
                    editText.setText(CommonKt.toEditable(sb.toString()));
                }
            } else if (length == 2) {
                EditText editText2 = (EditText) this.this$0._$_findCachedViewById(R.id.editExpires);
                Intrinsics.checkExpressionValueIsNotNull(editText2, "editExpires");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(editable);
                sb2.append('/');
                editText2.setText(CommonKt.toEditable(sb2.toString()));
            }
            EditText editText3 = (EditText) this.this$0._$_findCachedViewById(R.id.editExpires);
            Intrinsics.checkExpressionValueIsNotNull(editText3, "editExpires");
            ((EditText) this.this$0._$_findCachedViewById(R.id.editExpires)).setSelection(editText3.getText().length());
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.deleting = i3 < i2;
    }
}
