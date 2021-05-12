package com.forasoft.homewavvisitor.ui.fragment.account;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.forasoft.homewavvisitor.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 16})
/* compiled from: ReportBugFragment.kt */
final class ReportBugFragment$onViewCreated$onCheckedChangeListener$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ ReportBugFragment this$0;

    ReportBugFragment$onViewCreated$onCheckedChangeListener$1(ReportBugFragment reportBugFragment) {
        this.this$0 = reportBugFragment;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        boolean z2;
        Button button = (Button) this.this$0._$_findCachedViewById(R.id.btn_report);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_report");
        CheckBox checkBox = (CheckBox) this.this$0._$_findCachedViewById(R.id.cb_mic);
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "cb_mic");
        if (!checkBox.isChecked()) {
            CheckBox checkBox2 = (CheckBox) this.this$0._$_findCachedViewById(R.id.cb_speaker);
            Intrinsics.checkExpressionValueIsNotNull(checkBox2, "cb_speaker");
            if (!checkBox2.isChecked()) {
                CheckBox checkBox3 = (CheckBox) this.this$0._$_findCachedViewById(R.id.cb_connectivity);
                Intrinsics.checkExpressionValueIsNotNull(checkBox3, "cb_connectivity");
                if (!checkBox3.isChecked()) {
                    CheckBox checkBox4 = (CheckBox) this.this$0._$_findCachedViewById(R.id.cb_video);
                    Intrinsics.checkExpressionValueIsNotNull(checkBox4, "cb_video");
                    if (!checkBox4.isChecked()) {
                        z2 = false;
                        button.setEnabled(z2);
                    }
                }
            }
        }
        z2 = true;
        button.setEnabled(z2);
    }
}
