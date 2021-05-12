package com.forasoft.homewavvisitor.ui.fragment.register;

import android.text.style.ClickableSpan;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/register/AddConnectionSignUpFragment$showFacilityAgreement$1", "Landroid/text/style/ClickableSpan;", "onClick", "", "widget", "Landroid/view/View;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddConnectionSignUpFragment.kt */
public final class AddConnectionSignUpFragment$showFacilityAgreement$1 extends ClickableSpan {
    final /* synthetic */ String $agreementUrl;
    final /* synthetic */ AddConnectionSignUpFragment this$0;

    AddConnectionSignUpFragment$showFacilityAgreement$1(AddConnectionSignUpFragment addConnectionSignUpFragment, String str) {
        this.this$0 = addConnectionSignUpFragment;
        this.$agreementUrl = str;
    }

    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "widget");
        this.this$0.openOrDownloadPdf(this.$agreementUrl);
    }
}
