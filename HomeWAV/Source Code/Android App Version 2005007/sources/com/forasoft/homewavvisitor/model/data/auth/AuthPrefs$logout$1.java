package com.forasoft.homewavvisitor.model.data.auth;

import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "call"}, k = 3, mv = {1, 1, 16})
/* compiled from: AuthPrefs.kt */
final class AuthPrefs$logout$1<V> implements Callable<T> {
    final /* synthetic */ AuthPrefs this$0;

    AuthPrefs$logout$1(AuthPrefs authPrefs) {
        this.this$0 = authPrefs;
    }

    public final void call() {
        this.this$0.userDao.clearUser();
    }
}
