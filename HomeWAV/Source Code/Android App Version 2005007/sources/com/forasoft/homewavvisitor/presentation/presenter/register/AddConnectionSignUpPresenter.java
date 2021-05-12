package com.forasoft.homewavvisitor.presentation.presenter.register;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.register.Connection;
import com.forasoft.homewavvisitor.model.interactor.register.AddConnectionInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.adapter.AddConnectionAdapter;
import com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView;
import com.forasoft.homewavvisitor.toothpick.FacilitiesSubjectWrapper;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BQ\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020 H\u0016J\u0006\u0010#\u001a\u00020 J\u0006\u0010$\u001a\u00020 J\u0016\u0010%\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010&\u001a\u00020 H\u0014J$\u0010'\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0)H\u0002J\u0006\u0010,\u001a\u00020 J\u0006\u0010-\u001a\u00020 R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001dX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/register/AddConnectionSignUpPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/register/AddConnectionView;", "context", "Landroid/content/Context;", "router", "Lru/terrakok/cicerone/Router;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "notifier", "Lcom/forasoft/homewavvisitor/navigation/BusNotifier;", "facilitiesSubjectWrapper", "Lcom/forasoft/homewavvisitor/toothpick/FacilitiesSubjectWrapper;", "registerDataInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/AddConnectionInteractor;", "registerStepsInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;", "(Landroid/content/Context;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/UserDao;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/navigation/BusNotifier;Lcom/forasoft/homewavvisitor/toothpick/FacilitiesSubjectWrapper;Lcom/forasoft/homewavvisitor/model/interactor/register/AddConnectionInteractor;Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;)V", "connectionAdapter", "Lcom/forasoft/homewavvisitor/presentation/adapter/AddConnectionAdapter;", "connectionPosition", "", "facilityId", "", "user", "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "loadFacilities", "", "loadInmatesForFacility", "nextStep", "onAddConnectionClicked", "onCompletedStateChanged", "onFacilitySelected", "onFirstViewAttach", "onInmatesLoaded", "response", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "onNextClicked", "onSkipClicked", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: AddConnectionSignUpPresenter.kt */
public class AddConnectionSignUpPresenter extends BasePresenter<AddConnectionView> {
    private final HomewavApi api;
    /* access modifiers changed from: private */
    public final AuthHolder authHolder;
    private final AddConnectionAdapter connectionAdapter;
    /* access modifiers changed from: private */
    public int connectionPosition = -1;
    /* access modifiers changed from: private */
    public final FacilitiesSubjectWrapper facilitiesSubjectWrapper;
    private String facilityId = "";
    /* access modifiers changed from: private */
    public final BusNotifier notifier;
    /* access modifiers changed from: private */
    public final AddConnectionInteractor registerDataInteractor;
    private final RegisterStepsInteractor registerStepsInteractor;
    private final Router router;
    private final BehaviorSubject<User> user;
    private final UserDao userDao;

    @Inject
    public AddConnectionSignUpPresenter(Context context, @Global Router router2, AuthHolder authHolder2, UserDao userDao2, HomewavApi homewavApi, BusNotifier busNotifier, FacilitiesSubjectWrapper facilitiesSubjectWrapper2, AddConnectionInteractor addConnectionInteractor, RegisterStepsInteractor registerStepsInteractor2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(userDao2, "userDao");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(busNotifier, "notifier");
        Intrinsics.checkParameterIsNotNull(facilitiesSubjectWrapper2, "facilitiesSubjectWrapper");
        Intrinsics.checkParameterIsNotNull(addConnectionInteractor, "registerDataInteractor");
        Intrinsics.checkParameterIsNotNull(registerStepsInteractor2, "registerStepsInteractor");
        this.router = router2;
        this.authHolder = authHolder2;
        this.userDao = userDao2;
        this.api = homewavApi;
        this.notifier = busNotifier;
        this.facilitiesSubjectWrapper = facilitiesSubjectWrapper2;
        this.registerDataInteractor = addConnectionInteractor;
        this.registerStepsInteractor = registerStepsInteractor2;
        this.connectionAdapter = new AddConnectionAdapter(context);
        BehaviorSubject<User> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.user = create;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        this.connectionAdapter.addConnection(new Connection());
        ((AddConnectionView) getViewState()).setConnectionsAdapter(this.connectionAdapter);
        loadFacilities();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.userDao.getUser()).subscribe(new AddConnectionSignUpPresenter$sam$io_reactivex_functions_Consumer$0(new AddConnectionSignUpPresenter$onFirstViewAttach$1(this.user)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "userDao.getUser()\n      … .subscribe(user::onNext)");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Disposable subscribe2 = this.user.subscribe(new AddConnectionSignUpPresenter$onFirstViewAttach$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "user.subscribe {\n       …dStateChanged()\n        }");
        DisposableKt.plusAssign(disposables2, subscribe2);
    }

    /* access modifiers changed from: private */
    public final void loadFacilities() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.registerDataInteractor.loadFacilities().subscribe(new AddConnectionSignUpPresenter$loadFacilities$1(this), new AddConnectionSignUpPresenter$loadFacilities$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "registerDataInteractor.l…      }\n                )");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onSkipClicked() {
        this.router.newRootScreen(Screens.VerifyMethodScreen.INSTANCE);
    }

    public final void onFacilitySelected(int i, String str) {
        Intrinsics.checkParameterIsNotNull(str, "facilityId");
        this.connectionPosition = i;
        this.facilityId = str;
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.getVisitorAgreementUrl(str)).subscribe(new AddConnectionSignUpPresenter$onFacilitySelected$1(this), AddConnectionSignUpPresenter$onFacilitySelected$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getVisitorAgreementU…}\n                }, { })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void loadInmatesForFacility() {
        Object obj;
        List<Facility> value = this.facilitiesSubjectWrapper.getSubject().getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "facilitiesSubjectWrapper.subject.value");
        Iterator it = value.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((Facility) obj).getId(), (Object) this.facilityId)) {
                break;
            }
        }
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.registerDataInteractor.loadInmatesForPrison(((Facility) obj).getId()).subscribe(new AddConnectionSignUpPresenter$loadInmatesForFacility$1(this), new AddConnectionSignUpPresenter$loadInmatesForFacility$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "registerDataInteractor.l… 3000)\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onInmatesLoaded(int i, ApiResponse<? extends List<Inmate>> apiResponse) {
        AddConnectionView addConnectionView = (AddConnectionView) getViewState();
        Object body = apiResponse.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        addConnectionView.setInmatesAutocomplete(i, (List) body);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCompletedStateChanged() {
        /*
            r9 = this;
            com.forasoft.homewavvisitor.toothpick.FacilitiesSubjectWrapper r0 = r9.facilitiesSubjectWrapper
            io.reactivex.subjects.BehaviorSubject r0 = r0.getSubject()
            java.lang.Object r0 = r0.getValue()
            java.util.List r0 = (java.util.List) r0
            com.forasoft.homewavvisitor.presentation.adapter.AddConnectionAdapter r1 = r9.connectionAdapter
            java.util.List r1 = r1.getData()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r1 = r1.iterator()
        L_0x001f:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0036
            java.lang.Object r3 = r1.next()
            r4 = r3
            com.forasoft.homewavvisitor.model.data.register.Connection r4 = (com.forasoft.homewavvisitor.model.data.register.Connection) r4
            boolean r4 = r4.isComplete()
            if (r4 == 0) goto L_0x001f
            r2.add(r3)
            goto L_0x001f
        L_0x0036:
            java.util.List r2 = (java.util.List) r2
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r2 = r2.iterator()
        L_0x0045:
            boolean r3 = r2.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x0082
            java.lang.Object r3 = r2.next()
            com.forasoft.homewavvisitor.model.data.register.Connection r3 = (com.forasoft.homewavvisitor.model.data.register.Connection) r3
            java.lang.String r5 = "facilities"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r5)
            r5 = r0
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x005e:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x007a
            java.lang.Object r6 = r5.next()
            r7 = r6
            com.forasoft.homewavvisitor.model.data.Facility r7 = (com.forasoft.homewavvisitor.model.data.Facility) r7
            java.lang.String r7 = r7.getId()
            java.lang.String r8 = r3.getFacilityId()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
            if (r7 == 0) goto L_0x005e
            r4 = r6
        L_0x007a:
            com.forasoft.homewavvisitor.model.data.Facility r4 = (com.forasoft.homewavvisitor.model.data.Facility) r4
            if (r4 == 0) goto L_0x0045
            r1.add(r4)
            goto L_0x0045
        L_0x0082:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            boolean r0 = r1 instanceof java.util.Collection
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0097
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0097
        L_0x0095:
            r0 = 0
            goto L_0x00b4
        L_0x0097:
            java.util.Iterator r0 = r1.iterator()
        L_0x009b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0095
            java.lang.Object r1 = r0.next()
            com.forasoft.homewavvisitor.model.data.Facility r1 = (com.forasoft.homewavvisitor.model.data.Facility) r1
            java.lang.String r1 = r1.getRequire_photo_id()
            java.lang.String r5 = "1"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r5)
            if (r1 == 0) goto L_0x009b
            r0 = 1
        L_0x00b4:
            io.reactivex.subjects.BehaviorSubject<com.forasoft.homewavvisitor.model.data.auth.User> r1 = r9.user
            java.lang.Object r1 = r1.getValue()
            com.forasoft.homewavvisitor.model.data.auth.User r1 = (com.forasoft.homewavvisitor.model.data.auth.User) r1
            if (r1 == 0) goto L_0x00c2
            java.lang.String r4 = r1.getPhotoIdUrl()
        L_0x00c2:
            if (r4 == 0) goto L_0x00c5
            goto L_0x00c6
        L_0x00c5:
            r2 = 0
        L_0x00c6:
            moxy.MvpView r1 = r9.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView r1 = (com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView) r1
            r1.showEditPhoto(r0, r2)
            if (r0 == 0) goto L_0x00d3
            if (r2 == 0) goto L_0x00e5
        L_0x00d3:
            com.forasoft.homewavvisitor.presentation.adapter.AddConnectionAdapter r0 = r9.connectionAdapter
            boolean r0 = r0.isComplete()
            if (r0 == 0) goto L_0x00e5
            moxy.MvpView r0 = r9.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView r0 = (com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView) r0
            r0.showNextButton()
            goto L_0x00ee
        L_0x00e5:
            moxy.MvpView r0 = r9.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView r0 = (com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView) r0
            r0.showSkipButton()
        L_0x00ee:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.register.AddConnectionSignUpPresenter.onCompletedStateChanged():void");
    }

    public final void onNextClicked() {
        Collection arrayList = new ArrayList();
        for (Object next : this.connectionAdapter.getData()) {
            if (((Connection) next).isComplete()) {
                arrayList.add(next);
            }
        }
        Iterable<Connection> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Connection shortInmate : iterable) {
            arrayList2.add(shortInmate.toShortInmate());
        }
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.registerDataInteractor.addConnections((List) arrayList2).subscribe(new AddConnectionSignUpPresenter$onNextClicked$1(this), new AddConnectionSignUpPresenter$onNextClicked$2(this), new AddConnectionSignUpPresenter$onNextClicked$3(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "registerDataInteractor.a…      }\n                )");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public void nextStep() {
        this.registerStepsInteractor.moveToNextStep();
    }

    public final void onAddConnectionClicked() {
        this.connectionAdapter.addConnection(new Connection());
    }
}
