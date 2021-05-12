package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.subjects.BehaviorSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$onFirstViewAttach$7<T> implements Consumer<Inmate> {
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$onFirstViewAttach$7(ConversationPresenter conversationPresenter) {
        this.this$0 = conversationPresenter;
    }

    public final void accept(Inmate inmate) {
        CompositeDisposable access$getDisposables$p = this.this$0.getDisposables();
        HomewavApi access$getApi$p = this.this$0.api;
        String prison_id = inmate.getPrison_id();
        if (prison_id == null) {
            Intrinsics.throwNpe();
        }
        Disposable subscribe = CommonKt.applyAsync(access$getApi$p.getFacility(prison_id)).subscribe(new Consumer<ApiResponse<? extends Facility>>(this) {
            final /* synthetic */ ConversationPresenter$onFirstViewAttach$7 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(ApiResponse<Facility> apiResponse) {
                BehaviorSubject access$getFacility$p = this.this$0.this$0.facility;
                Facility body = apiResponse.getBody();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                access$getFacility$p.onNext(body);
            }
        }, AnonymousClass2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getFacility(it.priso….onNext(it.body!!) }, {})");
        DisposableKt.plusAssign(access$getDisposables$p, subscribe);
    }
}
