package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.content.DialogInterface;
import android.widget.CheckBox;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallFragment.kt */
final class CallFragment$showReportBugDialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ CheckBox $checkConnectivity;
    final /* synthetic */ CheckBox $checkMic;
    final /* synthetic */ CheckBox $checkSpeaker;
    final /* synthetic */ CheckBox $checkVideo;
    final /* synthetic */ Function1 $onReportBugClickListener;

    CallFragment$showReportBugDialog$1(CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, Function1 function1) {
        this.$checkMic = checkBox;
        this.$checkSpeaker = checkBox2;
        this.$checkConnectivity = checkBox3;
        this.$checkVideo = checkBox4;
        this.$onReportBugClickListener = function1;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        StringBuilder sb = new StringBuilder();
        CheckBox checkBox = this.$checkMic;
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkMic");
        if (checkBox.isChecked()) {
            sb.append("m");
        }
        CheckBox checkBox2 = this.$checkSpeaker;
        Intrinsics.checkExpressionValueIsNotNull(checkBox2, "checkSpeaker");
        if (checkBox2.isChecked()) {
            sb.append("s");
        }
        CheckBox checkBox3 = this.$checkConnectivity;
        Intrinsics.checkExpressionValueIsNotNull(checkBox3, "checkConnectivity");
        if (checkBox3.isChecked()) {
            sb.append("c");
        }
        CheckBox checkBox4 = this.$checkVideo;
        Intrinsics.checkExpressionValueIsNotNull(checkBox4, "checkVideo");
        if (checkBox4.isChecked()) {
            sb.append("v");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        this.$onReportBugClickListener.invoke(sb2);
    }
}
