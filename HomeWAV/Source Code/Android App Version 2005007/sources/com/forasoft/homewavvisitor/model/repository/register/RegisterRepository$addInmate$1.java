package com.forasoft.homewavvisitor.model.repository.register;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.register.InmateShort;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "it", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: RegisterRepository.kt */
final class RegisterRepository$addInmate$1<T, R> implements Function<T, ObservableSource<? extends R>> {
    final /* synthetic */ InmateShort $inmate;
    final /* synthetic */ RegisterRepository this$0;

    RegisterRepository$addInmate$1(RegisterRepository registerRepository, InmateShort inmateShort) {
        this.this$0 = registerRepository;
        this.$inmate = inmateShort;
    }

    public final Observable<ApiResponse<Inmate>> apply(User user) {
        Intrinsics.checkParameterIsNotNull(user, "it");
        return SignUpApi.DefaultImpls.addInmate$default(this.this$0.api, this.$inmate.getPrison_id(), this.$inmate.getIdentifier(), this.$inmate.getFirst_name(), this.$inmate.getMiddle_name(), this.$inmate.getLast_name(), this.$inmate.getDob(), this.$inmate.getRelationship(), this.$inmate.getRelationshipExplanation(), user.getPhotoProfile(), user.getPhotoId(), false, 1024, (Object) null);
    }
}
