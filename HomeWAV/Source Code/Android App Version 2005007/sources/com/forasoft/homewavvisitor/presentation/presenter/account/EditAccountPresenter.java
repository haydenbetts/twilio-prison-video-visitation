package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.braintreepayments.api.models.PostalAddressParser;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.PhoneWatcher;
import com.forasoft.homewavvisitor.presentation.view.account.EditAccountView;
import com.stripe.android.view.ShippingInfoWidget;
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
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rH\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fJ\u001e\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"J\b\u0010%\u001a\u00020\u001cH\u0014J^\u0010&\u001a\u00020\u001c2\b\u0010\u0011\u001a\u0004\u0018\u00010'2\b\u0010\u0013\u001a\u0004\u0018\u00010'2\b\u0010\u0012\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0014\u001a\u0004\u0018\u00010'2\b\u0010\u0016\u001a\u0004\u0018\u00010'2\b\u0010\u000e\u001a\u0004\u0018\u00010'2\u0006\u0010\u0015\u001a\u00020\u001f2\b\u0010\u0017\u001a\u0004\u0018\u00010'J\u0016\u0010)\u001a\u00020\u001c2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002J\u0012\u0010-\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010.H\u0002J\u0006\u0010/\u001a\u00020\u001cJ\u000e\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\"J\u0006\u00102\u001a\u00020\u001cJ\u0006\u00103\u001a\u00020\u001cR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/EditAccountPresenter;", "Lmoxy/MvpPresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/EditAccountView;", "router", "Lru/terrakok/cicerone/Router;", "accountInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "constants", "Lcom/forasoft/homewavvisitor/model/Constants;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/Constants;)V", "birthDate", "", "city", "dateFormat", "Ljava/text/SimpleDateFormat;", "email", "newPassword", "oldPassword", "phone", "state", "street", "zip", "formatPhone", "onBackPressed", "", "onChangePasswordClicked", "", "onDateClicked", "text", "", "onDatePicked", "dayOfMonth", "", "month", "year", "onFirstViewAttach", "onNextClicked", "Landroid/text/Editable;", "dob", "onRegisterSuccess", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onRegistrationFailed", "", "onStateClicked", "onStateSelected", "position", "onTermsClicked", "sendStepData", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: EditAccountPresenter.kt */
public final class EditAccountPresenter extends MvpPresenter<EditAccountView> {
    private final AccountInteractor accountInteractor;
    private final AuthHolder authHolder;
    private String birthDate;
    private String city;
    private final Constants constants;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    private String email;
    private String newPassword;
    private String oldPassword;
    private String phone;
    private final Router router;
    private String state;
    private String street;
    private String zip;

    @Inject
    public EditAccountPresenter(Router router2, AccountInteractor accountInteractor2, AuthHolder authHolder2, Constants constants2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(accountInteractor2, "accountInteractor");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(constants2, "constants");
        this.router = router2;
        this.accountInteractor = accountInteractor2;
        this.authHolder = authHolder2;
        this.constants = constants2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        User user = this.authHolder.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        ((EditAccountView) getViewState()).setEmail(user.getEmail());
        ((EditAccountView) getViewState()).setBirthDate(CommonKt.format(user.getBirthDate()));
        ((EditAccountView) getViewState()).setPhone(formatPhone(user.getPhone()));
        ((EditAccountView) getViewState()).setStreet(user.getStreet());
        ((EditAccountView) getViewState()).setCity(user.getCity());
        ((EditAccountView) getViewState()).setState(user.getState());
        ((EditAccountView) getViewState()).setZip(user.getZip());
        String pin = user.getPin();
        if (pin != null) {
            ((EditAccountView) getViewState()).setPin(pin);
        }
    }

    private final String formatPhone(String str) {
        PhoneWatcher.Companion companion = PhoneWatcher.Companion;
        CharSequence charSequence = str;
        Appendable sb = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if ('0' <= charAt && '9' >= charAt) {
                sb.append(charAt);
            }
        }
        String sb2 = ((StringBuilder) sb).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterTo(StringBuilder(), predicate).toString()");
        return companion.formatPhone(sb2);
    }

    public final void sendStepData() {
        EditAccountPresenter editAccountPresenter = this;
        AccountInteractor.editAccount$default(this.accountInteractor, this.oldPassword, this.newPassword, this.birthDate, this.email, this.phone, this.street, this.city, this.state, this.zip, (Boolean) null, 512, (Object) null).subscribe(new EditAccountPresenter$sam$io_reactivex_functions_Consumer$0(new EditAccountPresenter$sendStepData$1(editAccountPresenter)), new EditAccountPresenter$sam$io_reactivex_functions_Consumer$0(new EditAccountPresenter$sendStepData$2(editAccountPresenter)));
    }

    /* access modifiers changed from: private */
    public final void onRegisterSuccess(ApiResponse<User> apiResponse) {
        String str;
        ((EditAccountView) getViewState()).hideProgress();
        if (apiResponse.getOk()) {
            ((EditAccountView) getViewState()).showMessage("Profile changed successfully");
            this.router.exit();
            return;
        }
        ErrorCause errorCause = apiResponse.getErrorCause();
        Map<String, String> errorList = errorCause != null ? errorCause.getErrorList() : null;
        if (errorList == null || errorList.isEmpty()) {
            EditAccountView editAccountView = (EditAccountView) getViewState();
            ErrorCause errorCause2 = apiResponse.getErrorCause();
            if (errorCause2 == null || (str = errorCause2.getMessage()) == null) {
                str = "Couldn't update info, try later";
            }
            editAccountView.showMessage(str);
            return;
        }
        for (Map.Entry next : errorList.entrySet()) {
            ((EditAccountView) getViewState()).showFieldError((String) next.getKey(), StringsKt.removeSuffix(StringsKt.removePrefix((String) next.getValue(), (CharSequence) "\""), (CharSequence) "\""));
        }
        Collection arrayList = new ArrayList();
        for (Object next2 : CollectionsKt.listOf("email", "password", PostalAddressParser.STREET_ADDRESS_KEY, "city", ShippingInfoWidget.PHONE_FIELD, "zipcode")) {
            if (errorList.keySet().contains((String) next2)) {
                arrayList.add(next2);
            }
        }
        String str2 = (String) CollectionsKt.firstOrNull((List) arrayList);
        if (str2 != null) {
            ((EditAccountView) getViewState()).scrollToError(str2);
        }
    }

    /* access modifiers changed from: private */
    public final void onRegistrationFailed(Throwable th) {
        String str;
        ((EditAccountView) getViewState()).hideProgress();
        EditAccountView editAccountView = (EditAccountView) getViewState();
        if (th == null || (str = th.getMessage()) == null) {
            str = "some error";
        }
        editAccountView.showMessage(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:70:0x011f, code lost:
        if (r4 != null) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x013f, code lost:
        if (r3 != null) goto L_0x0149;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038 A[Catch:{ all -> 0x0160 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x003b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onNextClicked(android.text.Editable r12, android.text.Editable r13, android.text.Editable r14, java.lang.CharSequence r15, android.text.Editable r16, android.text.Editable r17, android.text.Editable r18, java.lang.CharSequence r19, android.text.Editable r20) {
        /*
            r11 = this;
            r0 = r11
            java.lang.String r1 = "state"
            r2 = r19
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1)
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.EditAccountView r1 = (com.forasoft.homewavvisitor.presentation.view.account.EditAccountView) r1
            r1.clearErrors()
            java.lang.String r1 = java.lang.String.valueOf(r16)     // Catch:{ all -> 0x0160 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0160 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0160 }
            r3.<init>()     // Catch:{ all -> 0x0160 }
            java.lang.Appendable r3 = (java.lang.Appendable) r3     // Catch:{ all -> 0x0160 }
            int r4 = r1.length()     // Catch:{ all -> 0x0160 }
            r5 = 0
            r6 = 0
        L_0x0024:
            r7 = 1
            if (r6 >= r4) goto L_0x003e
            char r8 = r1.charAt(r6)     // Catch:{ all -> 0x0160 }
            r9 = 57
            r10 = 48
            if (r10 <= r8) goto L_0x0032
            goto L_0x0035
        L_0x0032:
            if (r9 < r8) goto L_0x0035
            goto L_0x0036
        L_0x0035:
            r7 = 0
        L_0x0036:
            if (r7 == 0) goto L_0x003b
            r3.append(r8)     // Catch:{ all -> 0x0160 }
        L_0x003b:
            int r6 = r6 + 1
            goto L_0x0024
        L_0x003e:
            java.lang.StringBuilder r3 = (java.lang.StringBuilder) r3     // Catch:{ all -> 0x0160 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0160 }
            java.lang.String r3 = "filterTo(StringBuilder(), predicate).toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)     // Catch:{ all -> 0x0160 }
            r0.phone = r1     // Catch:{ all -> 0x0160 }
            r1 = r18
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x005a
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L_0x0058
            goto L_0x005a
        L_0x0058:
            r1 = 0
            goto L_0x005b
        L_0x005a:
            r1 = 1
        L_0x005b:
            if (r1 == 0) goto L_0x006b
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.EditAccountView r1 = (com.forasoft.homewavvisitor.presentation.view.account.EditAccountView) r1
            java.lang.String r2 = "city"
            java.lang.String r3 = "City is required"
            r1.showFieldError(r2, r3)
            return
        L_0x006b:
            r1 = r17
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x007a
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            if (r1 == 0) goto L_0x0078
            goto L_0x007a
        L_0x0078:
            r1 = 0
            goto L_0x007b
        L_0x007a:
            r1 = 1
        L_0x007b:
            if (r1 == 0) goto L_0x008b
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.EditAccountView r1 = (com.forasoft.homewavvisitor.presentation.view.account.EditAccountView) r1
            java.lang.String r2 = "street1"
            java.lang.String r3 = "Street is required"
            r1.showFieldError(r2, r3)
            return
        L_0x008b:
            com.forasoft.homewavvisitor.model.Constants r1 = r0.constants
            java.util.List r1 = r1.getStatesAbbreviationsList()
            java.lang.String r2 = r19.toString()
            int r1 = r1.indexOf(r2)
            r2 = -1
            if (r1 != r2) goto L_0x00a8
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.EditAccountView r1 = (com.forasoft.homewavvisitor.presentation.view.account.EditAccountView) r1
            java.lang.String r2 = "Choose state"
            r1.showMessage((java.lang.String) r2)
            return
        L_0x00a8:
            if (r13 == 0) goto L_0x00cd
            r2 = r13
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00b4
            r5 = 1
        L_0x00b4:
            if (r5 == 0) goto L_0x00cd
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.EditAccountView r1 = (com.forasoft.homewavvisitor.presentation.view.account.EditAccountView) r1
            java.lang.String r2 = "password"
            java.lang.String r3 = "Current password is empty"
            r1.showFieldError(r2, r3)
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.EditAccountView r1 = (com.forasoft.homewavvisitor.presentation.view.account.EditAccountView) r1
            r1.scrollToError(r2)
            return
        L_0x00cd:
            moxy.MvpView r2 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.EditAccountView r2 = (com.forasoft.homewavvisitor.presentation.view.account.EditAccountView) r2
            r2.showProgress()
            int r1 = r1 + r7
            java.lang.String r2 = ""
            if (r12 == 0) goto L_0x00e2
            java.lang.String r3 = r12.toString()
            if (r3 == 0) goto L_0x00e2
            goto L_0x00e3
        L_0x00e2:
            r3 = r2
        L_0x00e3:
            r0.email = r3
            if (r13 == 0) goto L_0x00ee
            java.lang.String r3 = r13.toString()
            if (r3 == 0) goto L_0x00ee
            goto L_0x00ef
        L_0x00ee:
            r3 = r2
        L_0x00ef:
            r0.oldPassword = r3
            if (r14 == 0) goto L_0x00fa
            java.lang.String r3 = r14.toString()
            if (r3 == 0) goto L_0x00fa
            goto L_0x00fb
        L_0x00fa:
            r3 = r2
        L_0x00fb:
            r0.newPassword = r3
            if (r15 == 0) goto L_0x0106
            java.lang.String r3 = r15.toString()
            if (r3 == 0) goto L_0x0106
            goto L_0x0107
        L_0x0106:
            r3 = r2
        L_0x0107:
            r0.birthDate = r3
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.CharSequence"
            if (r17 == 0) goto L_0x0128
            java.lang.String r4 = r17.toString()
            if (r4 == 0) goto L_0x0128
            if (r4 == 0) goto L_0x0122
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.CharSequence r4 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r4)
            java.lang.String r4 = r4.toString()
            if (r4 == 0) goto L_0x0128
            goto L_0x0129
        L_0x0122:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            r1.<init>(r3)
            throw r1
        L_0x0128:
            r4 = r2
        L_0x0129:
            r0.street = r4
            if (r18 == 0) goto L_0x0148
            java.lang.String r4 = r18.toString()
            if (r4 == 0) goto L_0x0148
            if (r4 == 0) goto L_0x0142
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r4)
            java.lang.String r3 = r3.toString()
            if (r3 == 0) goto L_0x0148
            goto L_0x0149
        L_0x0142:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            r1.<init>(r3)
            throw r1
        L_0x0148:
            r3 = r2
        L_0x0149:
            r0.city = r3
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r0.state = r1
            if (r20 == 0) goto L_0x015a
            java.lang.String r1 = r20.toString()
            if (r1 == 0) goto L_0x015a
            r2 = r1
        L_0x015a:
            r0.zip = r2
            r11.sendStepData()
            return
        L_0x0160:
            moxy.MvpView r1 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.EditAccountView r1 = (com.forasoft.homewavvisitor.presentation.view.account.EditAccountView) r1
            java.lang.String r2 = "Phone is invalid"
            r1.showMessage((java.lang.String) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.account.EditAccountPresenter.onNextClicked(android.text.Editable, android.text.Editable, android.text.Editable, java.lang.CharSequence, android.text.Editable, android.text.Editable, android.text.Editable, java.lang.CharSequence, android.text.Editable):void");
    }

    public final void onStateSelected(int i) {
        ((EditAccountView) getViewState()).showSateAbbreviation(this.constants.getStatesAbbreviationsList().get(i));
    }

    public final void onStateClicked() {
        ((EditAccountView) getViewState()).showPickStateDialog(this.constants.getStatesList());
    }

    public final void onChangePasswordClicked() {
        ((EditAccountView) getViewState()).showPasswordField();
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }

    public final void onTermsClicked() {
        this.router.navigateTo(new Screens.TermsAndConditionsScreen(false, 1, (DefaultConstructorMarker) null));
    }

    public final void onDateClicked(CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "text");
        if (charSequence.length() == 0) {
            ((EditAccountView) getViewState()).showDatePickDialog(0, 0, 2000);
            return;
        }
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "calendar");
        instance.setTime(this.dateFormat.parse(charSequence.toString()));
        int i = instance.get(1);
        ((EditAccountView) getViewState()).showDatePickDialog(instance.get(5), instance.get(2), i);
    }

    public final void onDatePicked(int i, int i2, int i3) {
        ((EditAccountView) getViewState()).showDate(this.dateFormat.format(new GregorianCalendar(i3, i2, i).getTime()));
    }
}
