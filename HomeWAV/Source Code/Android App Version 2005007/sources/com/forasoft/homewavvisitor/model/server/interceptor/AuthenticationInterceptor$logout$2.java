package com.forasoft.homewavvisitor.model.server.interceptor;

import com.forasoft.homewavvisitor.navigation.Screens;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: AuthenticationInterceptor.kt */
final class AuthenticationInterceptor$logout$2 implements Runnable {
    final /* synthetic */ AuthenticationInterceptor this$0;

    AuthenticationInterceptor$logout$2(AuthenticationInterceptor authenticationInterceptor) {
        this.this$0 = authenticationInterceptor;
    }

    public final void run() {
        this.this$0.router.newRootScreen(new Screens.LoginScreen(true));
    }
}
