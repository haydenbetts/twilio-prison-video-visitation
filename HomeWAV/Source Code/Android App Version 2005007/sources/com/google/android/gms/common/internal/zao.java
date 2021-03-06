package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
final class zao implements PendingResultUtil.ResultConverter<R, T> {
    private final /* synthetic */ Response zaa;

    zao(Response response) {
        this.zaa = response;
    }

    public final /* synthetic */ Object convert(Result result) {
        this.zaa.setResult(result);
        return this.zaa;
    }
}
