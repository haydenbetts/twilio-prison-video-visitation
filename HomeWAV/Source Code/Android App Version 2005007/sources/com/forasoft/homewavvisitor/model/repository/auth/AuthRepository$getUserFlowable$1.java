package com.forasoft.homewavvisitor.model.repository.auth;

import com.forasoft.homewavvisitor.model.data.auth.User;
import io.reactivex.Notification;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/Notification;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AuthRepository.kt */
final class AuthRepository$getUserFlowable$1<T> implements Consumer<Notification<User>> {
    public static final AuthRepository$getUserFlowable$1 INSTANCE = new AuthRepository$getUserFlowable$1();

    AuthRepository$getUserFlowable$1() {
    }

    public final void accept(Notification<User> notification) {
        Timber.d("getUserFlowable: ", new Object[0]);
    }
}
