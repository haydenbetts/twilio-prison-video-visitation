package com.github.karczews.rxbroadcastreceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import io.reactivex.Observable;

public final class RxBroadcastReceivers {
    private RxBroadcastReceivers() {
        throw new AssertionError("No util class instances for you!");
    }

    public static Observable<Intent> fromIntentFilter(Context context, IntentFilter intentFilter) {
        return new RxBroadcastReceiver(context, intentFilter);
    }
}
