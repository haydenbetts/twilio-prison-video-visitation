package com.forasoft.homewavvisitor.ui.fragment.register;

import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/TextView;", "actionId", "", "<anonymous parameter 2>", "Landroid/view/KeyEvent;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddCardFragment.kt */
final class AddCardFragment$onResume$3 extends Lambda implements Function3<TextView, Integer, KeyEvent, Boolean> {
    final /* synthetic */ AddCardFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddCardFragment$onResume$3(AddCardFragment addCardFragment) {
        super(3);
        this.this$0 = addCardFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return Boolean.valueOf(invoke((TextView) obj, ((Number) obj2).intValue(), (KeyEvent) obj3));
    }

    public final boolean invoke(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 5) {
            ((EditText) this.this$0._$_findCachedViewById(R.id.editZip)).requestFocus();
            return true;
        } else if (i != 6) {
            return true;
        } else {
            ContextExtensionsKt.hideKeyboard(this.this$0);
            return true;
        }
    }
}
