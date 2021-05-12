package com.forasoft.homewavvisitor.presentation.presenter.register;

import androidx.core.view.PointerIconCompat;
import com.forasoft.homewavvisitor.model.HeartbeatService;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.toothpick.DI;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Router;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignUp4Presenter.kt */
final class SignUp4Presenter$subscribe$2<T> implements Consumer<List<? extends Inmate>> {
    final /* synthetic */ boolean $isMainActivity;
    final /* synthetic */ SignUp4Presenter this$0;

    SignUp4Presenter$subscribe$2(SignUp4Presenter signUp4Presenter, boolean z) {
        this.this$0 = signUp4Presenter;
        this.$isMainActivity = z;
    }

    public final void accept(List<Inmate> list) {
        List<Inmate> list2 = list;
        Intrinsics.checkExpressionValueIsNotNull(list2, "it");
        Iterable iterable = list2;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Inmate inmate = (Inmate) it.next();
            String approved = inmate.getApproved();
            if (approved == null) {
                Intrinsics.throwNpe();
            }
            String credit_balance = inmate.getCredit_balance();
            if (credit_balance == null) {
                Intrinsics.throwNpe();
            }
            String first_name = inmate.getFirst_name();
            if (first_name == null) {
                Intrinsics.throwNpe();
            }
            String id = inmate.getId();
            String last_name = inmate.getLast_name();
            if (last_name == null) {
                Intrinsics.throwNpe();
            }
            String occupant_id = inmate.getOccupant_id();
            String prison_id = inmate.getPrison_id();
            if (prison_id == null) {
                Intrinsics.throwNpe();
            }
            String prison_payment_gateway = inmate.getPrison_payment_gateway();
            if (prison_payment_gateway == null) {
                Intrinsics.throwNpe();
            }
            boolean isFraud = inmate.isFraud();
            User user = this.this$0.authHolder.getUser();
            Iterator it2 = it;
            InmateByVisitor inmateByVisitor = r4;
            InmateByVisitor inmateByVisitor2 = new InmateByVisitor(approved, credit_balance, first_name, id, last_name, occupant_id, prison_id, prison_payment_gateway, isFraud, user != null ? user.getEmail() : null, inmate.getStatus());
            arrayList.add(inmateByVisitor);
            it = it2;
        }
        final List list3 = (List) arrayList;
        if (this.$isMainActivity) {
            CompositeDisposable access$getDisposables$p = this.this$0.getDisposables();
            Disposable subscribe = this.this$0.heartbeatRepository.getHeartbeatState().subscribe(new Consumer<State>(this) {
                final /* synthetic */ SignUp4Presenter$subscribe$2 this$0;

                {
                    this.this$0 = r1;
                }

                public final void accept(State state) {
                    Map<String, String> status = state != null ? state.getStatus() : null;
                    Map<String, String> balances = state != null ? state.getBalances() : null;
                    if (balances != null && (!balances.isEmpty())) {
                        Iterable<InmateByVisitor> iterable = list3;
                        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                        for (InmateByVisitor inmateByVisitor : iterable) {
                            String str = status != null ? status.get(inmateByVisitor.getId()) : null;
                            String str2 = balances.get(inmateByVisitor.getId());
                            if (str2 == null) {
                                Intrinsics.throwNpe();
                            }
                            arrayList.add(InmateByVisitor.copy$default(inmateByVisitor, (String) null, str2, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, (String) null, str, PointerIconCompat.TYPE_GRABBING, (Object) null));
                        }
                        this.this$0.this$0.processInmates((List) arrayList);
                    }
                }
            }, AnonymousClass2.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "heartbeatRepository.hear…                   }, {})");
            DisposableKt.plusAssign(access$getDisposables$p, subscribe);
            return;
        }
        SignUp4Presenter signUp4Presenter = this.this$0;
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global");
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScope(DI.S…thpick.qualifier.Global\")");
        signUp4Presenter.router = (Router) instance;
        CompositeDisposable access$getDisposables$p2 = this.this$0.getDisposables();
        Disposable subscribe2 = HeartbeatService.Companion.getHeartbeatState().subscribe(new Consumer<State>(this) {
            final /* synthetic */ SignUp4Presenter$subscribe$2 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(State state) {
                Map<String, String> status = state != null ? state.getStatus() : null;
                Map<String, String> balances = state.getBalances();
                if (!balances.isEmpty()) {
                    Iterable<InmateByVisitor> iterable = list3;
                    Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                    for (InmateByVisitor inmateByVisitor : iterable) {
                        String str = status != null ? status.get(inmateByVisitor.getId()) : null;
                        String str2 = balances.get(inmateByVisitor.getId());
                        if (str2 == null) {
                            Intrinsics.throwNpe();
                        }
                        arrayList.add(InmateByVisitor.copy$default(inmateByVisitor, (String) null, str2, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, (String) null, str, PointerIconCompat.TYPE_GRABBING, (Object) null));
                    }
                    this.this$0.this$0.processInmates((List) arrayList);
                }
            }
        }, AnonymousClass4.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "HeartbeatService.heartbe…                   }, {})");
        DisposableKt.plusAssign(access$getDisposables$p2, subscribe2);
    }
}
