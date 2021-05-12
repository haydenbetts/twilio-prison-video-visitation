package com.forasoft.homewavvisitor.model.repository.auth;

import com.forasoft.homewavvisitor.model.AirshipAnalytics;
import com.forasoft.homewavvisitor.model.server.response.AirshipTags;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.urbanairship.UAirship;
import io.reactivex.functions.Consumer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/AirshipTags;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AuthRepository.kt */
final class AuthRepository$logout$3<T> implements Consumer<ApiResponse<? extends AirshipTags>> {
    final /* synthetic */ Function0 $doOnComplete;
    final /* synthetic */ AuthRepository this$0;

    AuthRepository$logout$3(AuthRepository authRepository, Function0 function0) {
        this.this$0 = authRepository;
        this.$doOnComplete = function0;
    }

    public final void accept(ApiResponse<AirshipTags> apiResponse) {
        AirshipTags body = apiResponse.getBody();
        Set<String> tags = body != null ? body.getTags() : null;
        if (tags != null) {
            UAirship shared = UAirship.shared();
            Intrinsics.checkExpressionValueIsNotNull(shared, "UAirship.shared()");
            shared.getNamedUser().editTagGroups().removeTags(AirshipAnalytics.TAG_GROUP, tags).apply();
        }
        this.this$0.authHolder.logout();
        this.$doOnComplete.invoke();
    }
}
