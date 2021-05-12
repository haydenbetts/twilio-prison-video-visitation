package com.forasoft.homewavvisitor.model.repository.auth;

import com.google.firebase.iid.FirebaseInstanceId;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AuthRepository.kt */
final class AuthRepository$logout$5 extends Lambda implements Function0<Unit> {
    final /* synthetic */ AuthRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AuthRepository$logout$5(AuthRepository authRepository) {
        super(0);
        this.this$0 = authRepository;
    }

    public final void invoke() {
        this.this$0.notificationDao.deleteAll();
        this.this$0.messageDao.deleteAll();
        this.this$0.inmateDao.deleteAll();
        this.this$0.visitDao.deleteAll();
        this.this$0.callDao.deleteAll();
        this.this$0.creditDao.deleteAll();
        FirebaseInstanceId.getInstance().deleteInstanceId();
    }
}
