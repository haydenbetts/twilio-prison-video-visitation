package com.forasoft.homewavvisitor.model.repository.register;

import com.braintreepayments.api.models.PostalAddressParser;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.stripe.android.view.ShippingInfoWidget;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002 \u0004*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "kotlin.jvm.PlatformType", "user", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: RegisterRepository.kt */
final class RegisterRepository$editProfile$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ String $birthDate;
    final /* synthetic */ String $city;
    final /* synthetic */ String $email;
    final /* synthetic */ Boolean $isNotificationSubscriptionEnabled;
    final /* synthetic */ String $newPassword;
    final /* synthetic */ String $oldPassword;
    final /* synthetic */ String $phone;
    final /* synthetic */ String $stateId;
    final /* synthetic */ String $street;
    final /* synthetic */ String $zip;
    final /* synthetic */ RegisterRepository this$0;

    RegisterRepository$editProfile$1(RegisterRepository registerRepository, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Boolean bool) {
        this.this$0 = registerRepository;
        this.$newPassword = str;
        this.$oldPassword = str2;
        this.$email = str3;
        this.$phone = str4;
        this.$street = str5;
        this.$city = str6;
        this.$birthDate = str7;
        this.$stateId = str8;
        this.$zip = str9;
        this.$isNotificationSubscriptionEnabled = bool;
    }

    public final Single<ApiResponse<User>> apply(final User user) {
        String str;
        Intrinsics.checkParameterIsNotNull(user, "user");
        HashMap hashMap = new HashMap();
        String str2 = this.$newPassword;
        boolean z = false;
        if (str2 != null) {
            if (str2.length() > 0) {
                hashMap.put("password", str2);
                hashMap.put("password_confirmation", str2);
            }
        }
        String str3 = this.$oldPassword;
        if (str3 != null) {
            if ((str3.length() > 0) && (str = this.$newPassword) != null) {
                if (str.length() > 0) {
                    z = true;
                }
                if (z) {
                    hashMap.put("current_password", str3);
                }
            }
        }
        String str4 = this.$email;
        if (str4 != null && (!Intrinsics.areEqual((Object) str4, (Object) user.getEmail()))) {
            hashMap.put("email", str4);
        }
        String str5 = this.$phone;
        if (str5 != null && (!Intrinsics.areEqual((Object) str5, (Object) user.getPhone()))) {
            hashMap.put(ShippingInfoWidget.PHONE_FIELD, str5);
        }
        String str6 = this.$street;
        if (str6 != null && (!Intrinsics.areEqual((Object) str6, (Object) user.getStreet()))) {
            hashMap.put(PostalAddressParser.STREET_ADDRESS_KEY, str6);
        }
        String str7 = this.$city;
        if (str7 != null && (!Intrinsics.areEqual((Object) str7, (Object) user.getCity()))) {
            hashMap.put("city", str7);
        }
        String str8 = this.$birthDate;
        if (str8 != null && (!Intrinsics.areEqual((Object) str8, (Object) new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(user.getBirthDate())))) {
            hashMap.put("dob", str8);
        }
        String str9 = this.$stateId;
        if (!(str9 == null || Integer.parseInt(str9) - 1 == this.this$0.constants.getStatesAbbreviationsList().indexOf(user.getState()))) {
            hashMap.put("us_state_id", str9);
        }
        String str10 = this.$zip;
        if (str10 != null && (!Intrinsics.areEqual((Object) str10, (Object) user.getZip()))) {
            hashMap.put("zipcode", str10);
        }
        if (!hashMap.isEmpty() || this.$isNotificationSubscriptionEnabled != null) {
            return this.this$0.api.editUser(hashMap, this.$isNotificationSubscriptionEnabled);
        }
        return Single.fromCallable(new Callable<T>() {
            public final ApiResponse<User> call() {
                return new ApiResponse<>(true, (ErrorCause) null, user);
            }
        });
    }
}
