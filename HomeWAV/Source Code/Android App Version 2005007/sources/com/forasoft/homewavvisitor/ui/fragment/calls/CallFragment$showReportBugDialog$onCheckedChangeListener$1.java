package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.app.AlertDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallFragment.kt */
final class CallFragment$showReportBugDialog$onCheckedChangeListener$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ CheckBox $checkConnectivity;
    final /* synthetic */ CheckBox $checkMic;
    final /* synthetic */ CheckBox $checkSpeaker;
    final /* synthetic */ CheckBox $checkVideo;
    final /* synthetic */ CallFragment this$0;

    CallFragment$showReportBugDialog$onCheckedChangeListener$1(CallFragment callFragment, CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4) {
        this.this$0 = callFragment;
        this.$checkMic = checkBox;
        this.$checkSpeaker = checkBox2;
        this.$checkConnectivity = checkBox3;
        this.$checkVideo = checkBox4;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        boolean z2;
        AlertDialog access$getReportBugDialog$p = this.this$0.reportBugDialog;
        if (access$getReportBugDialog$p == null) {
            Intrinsics.throwNpe();
        }
        Button button = access$getReportBugDialog$p.getButton(-1);
        Intrinsics.checkExpressionValueIsNotNull(button, "reportBugDialog!!.getBut…rtDialog.BUTTON_POSITIVE)");
        CheckBox checkBox = this.$checkMic;
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkMic");
        if (!checkBox.isChecked()) {
            CheckBox checkBox2 = this.$checkSpeaker;
            Intrinsics.checkExpressionValueIsNotNull(checkBox2, "checkSpeaker");
            if (!checkBox2.isChecked()) {
                CheckBox checkBox3 = this.$checkConnectivity;
                Intrinsics.checkExpressionValueIsNotNull(checkBox3, "checkConnectivity");
                if (!checkBox3.isChecked()) {
                    CheckBox checkBox4 = this.$checkVideo;
                    Intrinsics.checkExpressionValueIsNotNull(checkBox4, "checkVideo");
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
