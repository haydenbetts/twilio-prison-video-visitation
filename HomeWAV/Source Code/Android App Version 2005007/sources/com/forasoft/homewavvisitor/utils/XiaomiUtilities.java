package com.forasoft.homewavvisitor.utils;

import air.HomeWAV.R;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0003J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u0011\u001a\u00020\u0010J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/utils/XiaomiUtilities;", "", "()V", "KEY_ASKED_ABOUT_LOCK_SCREEN", "", "OP_SHOW_WHEN_LOCKED", "", "checkLockedScreenPermission", "", "context", "Landroid/content/Context;", "getPermissionManagerIntent", "Landroid/content/Intent;", "getSystemProperty", "key", "isLockedScreenPermissionGranted", "", "isMIUI", "showLockedScreenPermissionDialog", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: XiaomiUtilities.kt */
public final class XiaomiUtilities {
    public static final XiaomiUtilities INSTANCE = new XiaomiUtilities();
    private static final String KEY_ASKED_ABOUT_LOCK_SCREEN = "asked_about_lock_screen";
    private static final int OP_SHOW_WHEN_LOCKED = 10020;

    private XiaomiUtilities() {
    }

    public final boolean isMIUI() {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
    }

    private final String getSystemProperty(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Intrinsics.checkExpressionValueIsNotNull(cls, "Class.forName(\"android.os.SystemProperties\")");
            Object invoke = cls.getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
            if (invoke != null) {
                return (String) invoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception unused) {
            return null;
        }
    }

    public final void checkLockedScreenPermission(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (!isLockedScreenPermissionGranted(context) && !PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_ASKED_ABOUT_LOCK_SCREEN, false)) {
            showLockedScreenPermissionDialog(context);
        }
    }

    private final boolean isLockedScreenPermissionGranted(Context context) {
        try {
            Object systemService = context.getSystemService("appops");
            Method method = AppOpsManager.class.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class});
            Intrinsics.checkExpressionValueIsNotNull(method, "AppOpsManager::class.jav…Type, String::class.java)");
            return Intrinsics.areEqual(method.invoke(systemService, new Object[]{Integer.valueOf(OP_SHOW_WHEN_LOCKED), Integer.valueOf(Process.myUid()), context.getPackageName()}), (Object) 0);
        } catch (Exception unused) {
            return true;
        }
    }

    private final void showLockedScreenPermissionDialog(Context context) {
        new AlertDialog.Builder(context).setTitle((int) R.string.dialog_xiaomi_title).setMessage((int) R.string.dialog_xiaomi_message).setPositiveButton((int) R.string.dialog_xiaomi_positive_button, (DialogInterface.OnClickListener) new XiaomiUtilities$showLockedScreenPermissionDialog$1(context)).setNegativeButton((int) R.string.dialog_xiaomi_negative_button, (DialogInterface.OnClickListener) new XiaomiUtilities$showLockedScreenPermissionDialog$2(context)).setCancelable(false).show();
    }

    /* access modifiers changed from: private */
    public final Intent getPermissionManagerIntent(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_package_uid", Process.myUid());
        intent.putExtra("extra_pkgname", context.getPackageName());
        return intent;
    }
}
