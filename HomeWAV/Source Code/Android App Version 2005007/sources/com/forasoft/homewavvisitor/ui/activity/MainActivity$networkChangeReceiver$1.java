package com.forasoft.homewavvisitor.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.ui.fragment.NetworkErrorFragment;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/forasoft/homewavvisitor/ui/activity/MainActivity$networkChangeReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
public final class MainActivity$networkChangeReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ MainActivity this$0;

    MainActivity$networkChangeReceiver$1(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Object systemService = context.getSystemService("connectivity");
        if (systemService != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
            boolean z = true;
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                z = false;
            }
            Timber.d("Connected " + z, new Object[0]);
            if (z) {
                Fragment findFragmentByTag = this.this$0.getSupportFragmentManager().findFragmentByTag("NetworkErrorFragment");
                if (!(findFragmentByTag instanceof DialogFragment)) {
                    findFragmentByTag = null;
                }
                DialogFragment dialogFragment = (DialogFragment) findFragmentByTag;
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                    return;
                }
                return;
            }
            new NetworkErrorFragment().show(this.this$0.getSupportFragmentManager(), "NetworkErrorFragment");
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
    }
}
