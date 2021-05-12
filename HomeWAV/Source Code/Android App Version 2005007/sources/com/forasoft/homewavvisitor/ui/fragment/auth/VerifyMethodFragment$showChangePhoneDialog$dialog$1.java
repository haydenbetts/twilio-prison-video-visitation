package com.forasoft.homewavvisitor.ui.fragment.auth;

import android.content.DialogInterface;
import android.view.View;
import com.forasoft.homewavvisitor.R;
import com.google.android.material.textfield.TextInputEditText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyMethodFragment.kt */
final class VerifyMethodFragment$showChangePhoneDialog$dialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ View $contentView;
    final /* synthetic */ VerifyMethodFragment this$0;

    VerifyMethodFragment$showChangePhoneDialog$dialog$1(VerifyMethodFragment verifyMethodFragment, View view) {
        this.this$0 = verifyMethodFragment;
        this.$contentView = view;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        View view = this.$contentView;
        Intrinsics.checkExpressionValueIsNotNull(view, "contentView");
        TextInputEditText textInputEditText = (TextInputEditText) view.findViewById(R.id.et_input);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText, "contentView.et_input");
        CharSequence valueOf = String.valueOf(textInputEditText.getText());
        Appendable sb = new StringBuilder();
        int length = valueOf.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = valueOf.charAt(i2);
            if ('0' <= charAt && '9' >= charAt) {
                sb.append(charAt);
            }
        }
        String sb2 = ((StringBuilder) sb).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterTo(StringBuilder(), predicate).toString()");
        this.this$0.getPresenter().onPhoneChanged(sb2);
    }
}
