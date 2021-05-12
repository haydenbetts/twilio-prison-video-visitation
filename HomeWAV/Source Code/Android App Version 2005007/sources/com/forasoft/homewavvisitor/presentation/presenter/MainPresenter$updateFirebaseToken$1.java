package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.UserInfo;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.Response;
import com.forasoft.homewavvisitor.presentation.view.MainView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.InstanceIdResult;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxkotlin.DisposableKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "instanceIdResult", "Lcom/google/firebase/iid/InstanceIdResult;", "kotlin.jvm.PlatformType", "onSuccess"}, k = 3, mv = {1, 1, 16})
/* compiled from: MainPresenter.kt */
final class MainPresenter$updateFirebaseToken$1<TResult> implements OnSuccessListener<InstanceIdResult> {
    final /* synthetic */ MainPresenter this$0;

    MainPresenter$updateFirebaseToken$1(MainPresenter mainPresenter) {
        this.this$0 = mainPresenter;
    }

    public final void onSuccess(InstanceIdResult instanceIdResult) {
        String id;
        Intrinsics.checkExpressionValueIsNotNull(instanceIdResult, "instanceIdResult");
        String token = instanceIdResult.getToken();
        Intrinsics.checkExpressionValueIsNotNull(token, "instanceIdResult.token");
        this.this$0.authHolder.setFcmToken(token);
        User user = this.this$0.authHolder.getUser();
        if (user != null && (id = user.getId()) != null) {
            CompositeDisposable access$getDisposables$p = this.this$0.getDisposables();
            Disposable subscribe = CommonKt.applyAsync(HomewavApi.DefaultImpls.saveToken$default(this.this$0.homewavApi, id, token, (String) null, 4, (Object) null)).subscribe(new Consumer<Response<? extends UserInfo>>(this) {
                final /* synthetic */ MainPresenter$updateFirebaseToken$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void accept(Response<UserInfo> response) {
                    if (response.getOk()) {
                        Timber.d("Token refresh successfully", new Object[0]);
                        UserInfo body = response.getBody();
                        if (body == null) {
                            Intrinsics.throwNpe();
                        }
                        if (body.isDisapproved()) {
                            ((MainView) this.this$0.this$0.getViewState()).showDisapprovedDialog();
                            return;
                        }
                        return;
                    }
                    Timber.e(response.getError(), new Object[0]);
                }
            }, AnonymousClass2.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "homewavApi.saveToken(use…                       })");
            DisposableKt.plusAssign(access$getDisposables$p, subscribe);
        }
    }
}
