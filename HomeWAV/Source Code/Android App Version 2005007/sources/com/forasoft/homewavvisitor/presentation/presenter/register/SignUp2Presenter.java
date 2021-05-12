package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.braintreepayments.api.models.PostalAddressParser;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterDataInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.register.SignUp2View;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import com.urbanairship.util.Attributes;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u001e\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$Jz\u0010'\u001a\u00020\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010(2\b\u0010\u0013\u001a\u0004\u0018\u00010(2\b\u0010\u0018\u001a\u0004\u0018\u00010(2\b\u0010\u0014\u001a\u0004\u0018\u00010(2\b\u0010\u0017\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010!2\b\u0010\u0019\u001a\u0004\u0018\u00010(2\b\u0010\u001b\u001a\u0004\u0018\u00010(2\b\u0010\u0010\u001a\u0004\u0018\u00010(2\u0006\u0010\u001a\u001a\u00020!2\b\u0010\u001d\u001a\u0004\u0018\u00010(2\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010*\u001a\u00020\u001f2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,H\u0002J\u0012\u0010.\u001a\u00020\u001f2\b\u0010+\u001a\u0004\u0018\u00010/H\u0002J\u0006\u00100\u001a\u00020\u001fJ\u000e\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u00020$J\u0006\u00103\u001a\u00020\u001fJ\u0006\u00104\u001a\u00020\u001fR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp2Presenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp2View;", "router", "Lru/terrakok/cicerone/Router;", "registerStepsInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;", "registerDataInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterDataInteractor;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "constants", "Lcom/forasoft/homewavvisitor/model/Constants;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterDataInteractor;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/Constants;)V", "birthDate", "", "city", "dateFormat", "Ljava/text/SimpleDateFormat;", "email", "firstName", "isNotificationSubscriptionEnabled", "", "lastName", "password", "phone", "state", "street", "username", "zip", "onDateClicked", "", "text", "", "onDatePicked", "dayOfMonth", "", "month", "year", "onNextClicked", "Landroid/text/Editable;", "dob", "onRegisterSuccess", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onRegistrationFailed", "", "onStateClicked", "onStateSelected", "position", "onTermsClicked", "sendStepData", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: SignUp2Presenter.kt */
public final class SignUp2Presenter extends BasePresenter<SignUp2View> {
    private final AuthHolder authHolder;
    private String birthDate;
    private String city;
    private final Constants constants;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    private String email;
    private String firstName;
    private boolean isNotificationSubscriptionEnabled = true;
    private String lastName;
    private String password;
    private String phone;
    private final RegisterDataInteractor registerDataInteractor;
    private final RegisterStepsInteractor registerStepsInteractor;
    private final Router router;
    private String state;
    private String street;
    private String username;
    private String zip;

    @Inject
    public SignUp2Presenter(@Global Router router2, RegisterStepsInteractor registerStepsInteractor2, RegisterDataInteractor registerDataInteractor2, AuthHolder authHolder2, Constants constants2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(registerStepsInteractor2, "registerStepsInteractor");
        Intrinsics.checkParameterIsNotNull(registerDataInteractor2, "registerDataInteractor");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(constants2, "constants");
        this.router = router2;
        this.registerStepsInteractor = registerStepsInteractor2;
        this.registerDataInteractor = registerDataInteractor2;
        this.authHolder = authHolder2;
        this.constants = constants2;
    }

    public final void sendStepData() {
        CompositeDisposable disposables = getDisposables();
        RegisterDataInteractor registerDataInteractor2 = this.registerDataInteractor;
        String str = this.username;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        String str2 = this.email;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        String str3 = this.password;
        if (str3 == null) {
            Intrinsics.throwNpe();
        }
        String str4 = this.firstName;
        if (str4 == null) {
            Intrinsics.throwNpe();
        }
        String str5 = this.lastName;
        if (str5 == null) {
            Intrinsics.throwNpe();
        }
        String str6 = this.birthDate;
        if (str6 == null) {
            Intrinsics.throwNpe();
        }
        String str7 = this.phone;
        if (str7 == null) {
            Intrinsics.throwNpe();
        }
        String str8 = this.street;
        if (str8 == null) {
            Intrinsics.throwNpe();
        }
        String str9 = this.city;
        if (str9 == null) {
            Intrinsics.throwNpe();
        }
        String str10 = this.state;
        if (str10 == null) {
            Intrinsics.throwNpe();
        }
        String str11 = this.zip;
        if (str11 == null) {
            Intrinsics.throwNpe();
        }
        Single<ApiResponse<User>> sendStep2Data = registerDataInteractor2.sendStep2Data(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, this.isNotificationSubscriptionEnabled);
        SignUp2Presenter signUp2Presenter = this;
        Disposable subscribe = sendStep2Data.subscribe(new SignUp2Presenter$sam$io_reactivex_functions_Consumer$0(new SignUp2Presenter$sendStepData$1(signUp2Presenter)), new SignUp2Presenter$sam$io_reactivex_functions_Consumer$0(new SignUp2Presenter$sendStepData$2(signUp2Presenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "registerDataInteractor.s…is::onRegistrationFailed)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onRegisterSuccess(ApiResponse<User> apiResponse) {
        String str;
        ((SignUp2View) getViewState()).hideProgress();
        if (apiResponse.getOk()) {
            this.authHolder.setUser(apiResponse.getBody());
            this.registerStepsInteractor.moveToNextStep();
            return;
        }
        ErrorCause errorCause = apiResponse.getErrorCause();
        Map<String, String> errorList = errorCause != null ? errorCause.getErrorList() : null;
        if (errorList == null || errorList.isEmpty()) {
            SignUp2View signUp2View = (SignUp2View) getViewState();
            ErrorCause errorCause2 = apiResponse.getErrorCause();
            if (errorCause2 == null || (str = errorCause2.getMessage()) == null) {
                str = "Couldn't register, try later";
            }
            signUp2View.showMessage(str);
            return;
        }
        for (Map.Entry next : errorList.entrySet()) {
            ((SignUp2View) getViewState()).showFieldError((String) next.getKey(), StringsKt.removeSuffix(StringsKt.removePrefix((String) next.getValue(), (CharSequence) "\""), (CharSequence) "\""));
        }
        Collection arrayList = new ArrayList();
        for (Object next2 : CollectionsKt.listOf(Attributes.USERNAME, "email", "password", Attributes.FIRST_NAME, Attributes.LAST_NAME, PostalAddressParser.STREET_ADDRESS_KEY, "city", "zipcode")) {
            if (errorList.keySet().contains((String) next2)) {
                arrayList.add(next2);
            }
        }
        String str2 = (String) CollectionsKt.firstOrNull((List) arrayList);
        if (str2 != null) {
            ((SignUp2View) getViewState()).scrollToError(str2);
        }
    }

    /* access modifiers changed from: private */
    public final void onRegistrationFailed(Throwable th) {
        String str;
        ((SignUp2View) getViewState()).hideProgress();
        Timber.e(th);
        SignUp2View signUp2View = (SignUp2View) getViewState();
        if (th == null || (str = th.getMessage()) == null) {
            str = "some error";
        }
        signUp2View.showMessage(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x00cd A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ca A[Catch:{ all -> 0x01dd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onNextClicked(android.text.Editable r12, android.text.Editable r13, android.text.Editable r14, android.text.Editable r15, android.text.Editable r16, java.lang.CharSequence r17, android.text.Editable r18, android.text.Editable r19, android.text.Editable r20, java.lang.CharSequence r21, android.text.Editable r22, boolean r23) {
        /*
            r11 = this;
            r0 = r11
            java.lang.String r1 = "state"
            r2 = r21
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1)
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            r1.clearErrors()
            r1 = r15
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0021
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L_0x001f
            goto L_0x0021
        L_0x001f:
            r1 = 0
            goto L_0x0022
        L_0x0021:
            r1 = 1
        L_0x0022:
            if (r1 == 0) goto L_0x003b
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            java.lang.String r2 = "first_name"
            java.lang.String r3 = "First name is required"
            r1.showFieldError(r2, r3)
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            r1.scrollToError(r2)
            return
        L_0x003b:
            r1 = r16
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x004a
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L_0x0048
            goto L_0x004a
        L_0x0048:
            r1 = 0
            goto L_0x004b
        L_0x004a:
            r1 = 1
        L_0x004b:
            if (r1 == 0) goto L_0x0064
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            java.lang.String r2 = "last_name"
            java.lang.String r3 = "Last name is required"
            r1.showFieldError(r2, r3)
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            r1.scrollToError(r2)
            return
        L_0x0064:
            r1 = r20
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x0073
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L_0x0071
            goto L_0x0073
        L_0x0071:
            r1 = 0
            goto L_0x0074
        L_0x0073:
            r1 = 1
        L_0x0074:
            if (r1 == 0) goto L_0x0084
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            java.lang.String r2 = "city"
            java.lang.String r3 = "City is required"
            r1.showFieldError(r2, r3)
            return
        L_0x0084:
            r1 = r19
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x0093
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L_0x0091
            goto L_0x0093
        L_0x0091:
            r1 = 0
            goto L_0x0094
        L_0x0093:
            r1 = 1
        L_0x0094:
            if (r1 == 0) goto L_0x00a4
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            java.lang.String r2 = "street1"
            java.lang.String r3 = "Street is required"
            r1.showFieldError(r2, r3)
            return
        L_0x00a4:
            java.lang.String r1 = java.lang.String.valueOf(r18)     // Catch:{ all -> 0x01dd }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x01dd }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01dd }
            r5.<init>()     // Catch:{ all -> 0x01dd }
            java.lang.Appendable r5 = (java.lang.Appendable) r5     // Catch:{ all -> 0x01dd }
            int r6 = r1.length()     // Catch:{ all -> 0x01dd }
            r7 = 0
        L_0x00b6:
            if (r7 >= r6) goto L_0x00d0
            char r8 = r1.charAt(r7)     // Catch:{ all -> 0x01dd }
            r9 = 57
            r10 = 48
            if (r10 <= r8) goto L_0x00c3
            goto L_0x00c7
        L_0x00c3:
            if (r9 < r8) goto L_0x00c7
            r9 = 1
            goto L_0x00c8
        L_0x00c7:
            r9 = 0
        L_0x00c8:
            if (r9 == 0) goto L_0x00cd
            r5.append(r8)     // Catch:{ all -> 0x01dd }
        L_0x00cd:
            int r7 = r7 + 1
            goto L_0x00b6
        L_0x00d0:
            java.lang.StringBuilder r5 = (java.lang.StringBuilder) r5     // Catch:{ all -> 0x01dd }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x01dd }
            java.lang.String r5 = "filterTo(StringBuilder(), predicate).toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r5)     // Catch:{ all -> 0x01dd }
            r0.phone = r1     // Catch:{ all -> 0x01dd }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01dd }
            r1.<init>()     // Catch:{ all -> 0x01dd }
            java.lang.String r5 = "phone parsed: "
            r1.append(r5)     // Catch:{ all -> 0x01dd }
            java.lang.String r5 = r0.phone     // Catch:{ all -> 0x01dd }
            r1.append(r5)     // Catch:{ all -> 0x01dd }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01dd }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x01dd }
            timber.log.Timber.d(r1, r3)     // Catch:{ all -> 0x01dd }
            com.forasoft.homewavvisitor.model.Constants r1 = r0.constants
            java.util.List r1 = r1.getStatesAbbreviationsList()
            java.lang.String r2 = r21.toString()
            int r1 = r1.indexOf(r2)
            r2 = -1
            if (r1 != r2) goto L_0x0112
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            java.lang.String r2 = "Choose state"
            r1.showMessage((java.lang.String) r2)
            return
        L_0x0112:
            if (r17 == 0) goto L_0x0126
            int r2 = r17.length()
            if (r2 != 0) goto L_0x0126
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            java.lang.String r2 = "Date of Birth is required"
            r1.showMessage((java.lang.String) r2)
            return
        L_0x0126:
            moxy.MvpView r2 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r2 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r2
            r2.showProgress()
            int r1 = r1 + r4
            java.lang.String r2 = ""
            if (r12 == 0) goto L_0x013b
            java.lang.String r3 = r12.toString()
            if (r3 == 0) goto L_0x013b
            goto L_0x013c
        L_0x013b:
            r3 = r2
        L_0x013c:
            r0.username = r3
            if (r13 == 0) goto L_0x0147
            java.lang.String r3 = r13.toString()
            if (r3 == 0) goto L_0x0147
            goto L_0x0148
        L_0x0147:
            r3 = r2
        L_0x0148:
            r0.email = r3
            if (r14 == 0) goto L_0x0153
            java.lang.String r3 = r14.toString()
            if (r3 == 0) goto L_0x0153
            goto L_0x0154
        L_0x0153:
            r3 = r2
        L_0x0154:
            r0.password = r3
            java.lang.String r3 = r15.toString()
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.CharSequence"
            if (r3 == 0) goto L_0x01d7
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r3)
            java.lang.String r3 = r3.toString()
            r0.firstName = r3
            java.lang.String r3 = r16.toString()
            if (r3 == 0) goto L_0x01d1
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r3)
            java.lang.String r3 = r3.toString()
            r0.lastName = r3
            if (r17 == 0) goto L_0x0185
            java.lang.String r3 = r17.toString()
            if (r3 == 0) goto L_0x0185
            goto L_0x0186
        L_0x0185:
            r3 = r2
        L_0x0186:
            r0.birthDate = r3
            java.lang.String r3 = r19.toString()
            if (r3 == 0) goto L_0x01cb
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r3)
            java.lang.String r3 = r3.toString()
            r0.street = r3
            java.lang.String r3 = r20.toString()
            if (r3 == 0) goto L_0x01c5
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r3)
            java.lang.String r3 = r3.toString()
            r0.city = r3
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r0.state = r1
            if (r22 == 0) goto L_0x01bb
            java.lang.String r1 = r22.toString()
            if (r1 == 0) goto L_0x01bb
            r2 = r1
        L_0x01bb:
            r0.zip = r2
            r1 = r23
            r0.isNotificationSubscriptionEnabled = r1
            r11.sendStepData()
            return
        L_0x01c5:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            r1.<init>(r4)
            throw r1
        L_0x01cb:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            r1.<init>(r4)
            throw r1
        L_0x01d1:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            r1.<init>(r4)
            throw r1
        L_0x01d7:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            r1.<init>(r4)
            throw r1
        L_0x01dd:
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp2View r1 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp2View) r1
            java.lang.String r2 = "Phone is invalid"
            r1.showMessage((java.lang.String) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.register.SignUp2Presenter.onNextClicked(android.text.Editable, android.text.Editable, android.text.Editable, android.text.Editable, android.text.Editable, java.lang.CharSequence, android.text.Editable, android.text.Editable, android.text.Editable, java.lang.CharSequence, android.text.Editable, boolean):void");
    }

    public final void onStateSelected(int i) {
        ((SignUp2View) getViewState()).showSateAbbreviation(this.constants.getStatesAbbreviationsList().get(i));
    }

    public final void onStateClicked() {
        ((SignUp2View) getViewState()).showPickStateDialog(this.constants.getStatesList());
    }

    public final void onTermsClicked() {
        this.router.navigateTo(new Screens.TermsAndConditionsScreen(false, 1, (DefaultConstructorMarker) null));
    }

    public final void onDateClicked(CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "text");
        if (charSequence.length() == 0) {
            ((SignUp2View) getViewState()).showDatePickDialog(0, 0, 2000);
            return;
        }
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "calendar");
        instance.setTime(this.dateFormat.parse(charSequence.toString()));
        int i = instance.get(1);
        ((SignUp2View) getViewState()).showDatePickDialog(instance.get(5), instance.get(2), i);
    }

    public final void onDatePicked(int i, int i2, int i3) {
        ((SignUp2View) getViewState()).showDate(this.dateFormat.format(new GregorianCalendar(i3, i2, i).getTime()));
    }
}
