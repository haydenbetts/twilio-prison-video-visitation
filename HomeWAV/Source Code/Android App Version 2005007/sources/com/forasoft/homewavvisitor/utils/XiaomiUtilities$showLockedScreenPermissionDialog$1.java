package com.forasoft.homewavvisitor.utils;

import android.content.Context;
import android.content.DialogInterface;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: XiaomiUtilities.kt */
final class XiaomiUtilities$showLockedScreenPermissionDialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ Context $context;

    XiaomiUtilities$showLockedScreenPermissionDialog$1(Context context) {
        this.$context = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.content.DialogInterface r2, int r3) {
        /*
            r1 = this;
            com.forasoft.homewavvisitor.utils.XiaomiUtilities r2 = com.forasoft.homewavvisitor.utils.XiaomiUtilities.INSTANCE
            android.content.Context r3 = r1.$context
            android.content.Intent r2 = r2.getPermissionManagerIntent(r3)
            if (r2 == 0) goto L_0x003a
            android.content.Context r3 = r1.$context     // Catch:{ Exception -> 0x0010 }
            r3.startActivity(r2)     // Catch:{ Exception -> 0x0010 }
            goto L_0x003a
        L_0x0010:
            android.content.Intent r2 = new android.content.Intent     // Catch:{ Exception -> 0x003a }
            java.lang.String r3 = "android.settings.APPLICATION_DETAILS_SETTINGS"
            r2.<init>(r3)     // Catch:{ Exception -> 0x003a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003a }
            r3.<init>()     // Catch:{ Exception -> 0x003a }
            java.lang.String r0 = "package:"
            r3.append(r0)     // Catch:{ Exception -> 0x003a }
            android.content.Context r0 = r1.$context     // Catch:{ Exception -> 0x003a }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ Exception -> 0x003a }
            r3.append(r0)     // Catch:{ Exception -> 0x003a }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x003a }
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x003a }
            r2.setData(r3)     // Catch:{ Exception -> 0x003a }
            android.content.Context r3 = r1.$context     // Catch:{ Exception -> 0x003a }
            r3.startActivity(r2)     // Catch:{ Exception -> 0x003a }
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.utils.XiaomiUtilities$showLockedScreenPermissionDialog$1.onClick(android.content.DialogInterface, int):void");
    }
}
