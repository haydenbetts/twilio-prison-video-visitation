package com.forasoft.homewavvisitor.model.interactor.register;

import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.data.register.InmateShort;
import com.forasoft.homewavvisitor.model.repository.register.RegisterRepository;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00110\u000e0\u0014J \u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00110\u000e0\u00142\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/forasoft/homewavvisitor/model/interactor/register/AddConnectionInteractor;", "", "registerRepository", "Lcom/forasoft/homewavvisitor/model/repository/register/RegisterRepository;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "(Lcom/forasoft/homewavvisitor/model/repository/register/RegisterRepository;Lcom/forasoft/homewavvisitor/model/Analytics;)V", "connectionsSubject", "Lio/reactivex/subjects/ReplaySubject;", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "getConnectionsSubject", "()Lio/reactivex/subjects/ReplaySubject;", "addConnections", "Lio/reactivex/Observable;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/register/InmateShort;", "loadFacilities", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "loadInmatesForPrison", "facilityId", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddConnectionInteractor.kt */
public final class AddConnectionInteractor {
    /* access modifiers changed from: private */
    public final Analytics analytics;
    private final ReplaySubject<InmateByVisitor> connectionsSubject;
    /* access modifiers changed from: private */
    public final RegisterRepository registerRepository;

    @Inject
    public AddConnectionInteractor(RegisterRepository registerRepository2, Analytics analytics2) {
        Intrinsics.checkParameterIsNotNull(registerRepository2, "registerRepository");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        this.registerRepository = registerRepository2;
        this.analytics = analytics2;
        ReplaySubject<InmateByVisitor> create = ReplaySubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "ReplaySubject.create()");
        this.connectionsSubject = create;
    }

    public final ReplaySubject<InmateByVisitor> getConnectionsSubject() {
        return this.connectionsSubject;
    }

    public final Single<ApiResponse<List<Facility>>> loadFacilities() {
        Single<ApiResponse<List<Facility>>> observeOn = this.registerRepository.loadFacilities().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkExpressionValueIsNotNull(observeOn, "registerRepository.loadF…dSchedulers.mainThread())");
        return observeOn;
    }

    public final Single<ApiResponse<List<Inmate>>> loadInmatesForPrison(String str) {
        Intrinsics.checkParameterIsNotNull(str, "facilityId");
        Single<ApiResponse<List<Inmate>>> observeOn = this.registerRepository.loadInmatesForFacility(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkExpressionValueIsNotNull(observeOn, "registerRepository.loadI…dSchedulers.mainThread())");
        return observeOn;
    }

    public final synchronized Observable<ApiResponse<Inmate>> addConnections(List<InmateShort> list) {
        Observable<ApiResponse<Inmate>> observeOn;
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        Observable concatMap = Observable.fromIterable(CollectionsKt.getIndices(list)).concatMap(new AddConnectionInteractor$addConnections$addInmateObservable$1(this, list));
        concatMap.doOnNext(AddConnectionInteractor$addConnections$1.INSTANCE).doOnSubscribe(AddConnectionInteractor$addConnections$2.INSTANCE);
        observeOn = concatMap.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        if (observeOn == null) {
            Intrinsics.throwNpe();
        }
        return observeOn;
    }
}
