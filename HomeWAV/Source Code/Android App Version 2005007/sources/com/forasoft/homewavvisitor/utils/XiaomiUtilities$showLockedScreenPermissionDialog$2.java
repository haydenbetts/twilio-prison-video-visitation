package com.forasoft.homewavvisitor.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.PreferenceManager;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: XiaomiUtilities.kt */
final class XiaomiUtilities$showLockedScreenPermissionDialog$2 implements DialogInterface.OnClickListener {
    final /* synthetic */ Context $context;

    XiaomiUtilities$showLockedScreenPermissionDialog$2(Context context) {
        this.$context = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        PreferenceManager.getDefaultSharedPreferences(this.$context).edit().putBoolean("asked_about_lock_screen", true).apply();
        dialogInterface.dismiss();
    }
}
