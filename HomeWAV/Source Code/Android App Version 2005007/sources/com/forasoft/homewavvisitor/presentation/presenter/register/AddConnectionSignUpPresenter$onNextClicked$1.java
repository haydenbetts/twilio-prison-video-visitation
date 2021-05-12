package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.OnInmateAdded;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.ReplaySubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "response", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddConnectionSignUpPresenter.kt */
final class AddConnectionSignUpPresenter$onNextClicked$1<T> implements Consumer<ApiResponse<? extends Inmate>> {
    final /* synthetic */ AddConnectionSignUpPresenter this$0;

    AddConnectionSignUpPresenter$onNextClicked$1(AddConnectionSignUpPresenter addConnectionSignUpPresenter) {
        this.this$0 = addConnectionSignUpPresenter;
    }

    public final void accept(ApiResponse<Inmate> apiResponse) {
        Object[] objArr = new Object[1];
        String str = null;
        objArr[0] = apiResponse != null ? apiResponse.getBody() : null;
        Timber.d("onInmateAdded: %s", objArr);
        Inmate body = apiResponse != null ? apiResponse.getBody() : null;
        if (body != null) {
            this.this$0.notifier.notify(new OnInmateAdded(body));
            ReplaySubject<InmateByVisitor> connectionsSubject = this.this$0.registerDataInteractor.getConnectionsSubject();
            String approved = body.getApproved();
            if (approved == null) {
                Intrinsics.throwNpe();
            }
            String credit_balance = body.getCredit_balance();
            if (credit_balance == null) {
                Intrinsics.throwNpe();
            }
            String first_name = body.getFirst_name();
            if (first_name == null) {
                Intrinsics.throwNpe();
            }
            String identifier = body.getIdentifier();
            if (identifier == null) {
                Intrinsics.throwNpe();
            }
            String last_name = body.getLast_name();
            if (last_name == null) {
                Intrinsics.throwNpe();
            }
            String occupant_id = body.getOccupant_id();
            String prison_id = body.getPrison_id();
            if (prison_id == null) {
                Intrinsics.throwNpe();
            }
            String prison_payment_gateway = body.getPrison_payment_gateway();
            if (prison_payment_gateway == null) {
                Intrinsics.throwNpe();
            }
            boolean isFraud = body.isFraud();
            User user = this.this$0.authHolder.getUser();
            if (user != null) {
                str = user.getEmail();
            }
            String str2 = str;
            InmateByVisitor inmateByVisitor = r4;
            InmateByVisitor inmateByVisitor2 = new InmateByVisitor(approved, credit_balance, first_name, identifier, last_name, occupant_id, prison_id, prison_payment_gateway, isFraud, str2, (String) null, 1024, (DefaultConstructorMarker) null);
            connectionsSubject.onNext(inmateByVisitor);
        }
    }
}
