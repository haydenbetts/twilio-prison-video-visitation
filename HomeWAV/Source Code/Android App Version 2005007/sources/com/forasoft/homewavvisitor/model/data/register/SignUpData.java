package com.forasoft.homewavvisitor.model.data.register;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B£\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0011¢\u0006\u0002\u0010\u0015J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0011HÆ\u0003J\t\u0010/\u001a\u00020\u0013HÆ\u0003J\t\u00100\u001a\u00020\u0011HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÃ\u0001\u00109\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0011HÆ\u0001J\u0013\u0010:\u001a\u00020\u00112\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010<\u001a\u00020\u0013HÖ\u0001J\u0006\u0010=\u001a\u00020>J\b\u0010?\u001a\u00020\u0003H\u0016R\u0016\u0010\u0012\u001a\u00020\u00138\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0016\u0010\u0014\u001a\u00020\u00118\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019¨\u0006@"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/register/SignUpData;", "", "username", "", "email", "password", "password_confirmation", "first_name", "last_name", "dob", "phone", "city", "street1", "us_state_id", "zipcode", "voipToken", "notification_subscription_enabled", "", "agree_to_terms", "", "two_factor_required", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZIZ)V", "getAgree_to_terms", "()I", "getCity", "()Ljava/lang/String;", "getDob", "getEmail", "getFirst_name", "getLast_name", "getNotification_subscription_enabled", "()Z", "getPassword", "getPassword_confirmation", "getPhone", "getStreet1", "getTwo_factor_required", "getUs_state_id", "getUsername", "getVoipToken", "getZipcode", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toJson", "Lcom/google/gson/JsonObject;", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignUpData.kt */
public final class SignUpData {
    @Expose
    private final int agree_to_terms;
    @Expose
    private final String city;
    @Expose
    private final String dob;
    @Expose
    private final String email;
    @Expose
    private final String first_name;
    @Expose
    private final String last_name;
    @Expose
    private final boolean notification_subscription_enabled;
    @Expose
    private final String password;
    @Expose
    private final String password_confirmation;
    @Expose
    private final String phone;
    @Expose
    private final String street1;
    @Expose
    private final boolean two_factor_required;
    @Expose
    private final String us_state_id;
    @Expose
    private final String username;
    @Expose
    private final String voipToken;
    @Expose
    private final String zipcode;

    public static /* synthetic */ SignUpData copy$default(SignUpData signUpData, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, boolean z, int i, boolean z2, int i2, Object obj) {
        SignUpData signUpData2 = signUpData;
        int i3 = i2;
        return signUpData.copy((i3 & 1) != 0 ? signUpData2.username : str, (i3 & 2) != 0 ? signUpData2.email : str2, (i3 & 4) != 0 ? signUpData2.password : str3, (i3 & 8) != 0 ? signUpData2.password_confirmation : str4, (i3 & 16) != 0 ? signUpData2.first_name : str5, (i3 & 32) != 0 ? signUpData2.last_name : str6, (i3 & 64) != 0 ? signUpData2.dob : str7, (i3 & 128) != 0 ? signUpData2.phone : str8, (i3 & 256) != 0 ? signUpData2.city : str9, (i3 & 512) != 0 ? signUpData2.street1 : str10, (i3 & 1024) != 0 ? signUpData2.us_state_id : str11, (i3 & 2048) != 0 ? signUpData2.zipcode : str12, (i3 & 4096) != 0 ? signUpData2.voipToken : str13, (i3 & 8192) != 0 ? signUpData2.notification_subscription_enabled : z, (i3 & 16384) != 0 ? signUpData2.agree_to_terms : i, (i3 & 32768) != 0 ? signUpData2.two_factor_required : z2);
    }

    public final String component1() {
        return this.username;
    }

    public final String component10() {
        return this.street1;
    }

    public final String component11() {
        return this.us_state_id;
    }

    public final String component12() {
        return this.zipcode;
    }

    public final String component13() {
        return this.voipToken;
    }

    public final boolean component14() {
        return this.notification_subscription_enabled;
    }

    public final int component15() {
        return this.agree_to_terms;
    }

    public final boolean component16() {
        return this.two_factor_required;
    }

    public final String component2() {
        return this.email;
    }

    public final String component3() {
        return this.password;
    }

    public final String component4() {
        return this.password_confirmation;
    }

    public final String component5() {
        return this.first_name;
    }

    public final String component6() {
        return this.last_name;
    }

    public final String component7() {
        return this.dob;
    }

    public final String component8() {
        return this.phone;
    }

    public final String component9() {
        return this.city;
    }

    public final SignUpData copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, boolean z, int i, boolean z2) {
        return new SignUpData(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, z, i, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignUpData)) {
            return false;
        }
        SignUpData signUpData = (SignUpData) obj;
        return Intrinsics.areEqual((Object) this.username, (Object) signUpData.username) && Intrinsics.areEqual((Object) this.email, (Object) signUpData.email) && Intrinsics.areEqual((Object) this.password, (Object) signUpData.password) && Intrinsics.areEqual((Object) this.password_confirmation, (Object) signUpData.password_confirmation) && Intrinsics.areEqual((Object) this.first_name, (Object) signUpData.first_name) && Intrinsics.areEqual((Object) this.last_name, (Object) signUpData.last_name) && Intrinsics.areEqual((Object) this.dob, (Object) signUpData.dob) && Intrinsics.areEqual((Object) this.phone, (Object) signUpData.phone) && Intrinsics.areEqual((Object) this.city, (Object) signUpData.city) && Intrinsics.areEqual((Object) this.street1, (Object) signUpData.street1) && Intrinsics.areEqual((Object) this.us_state_id, (Object) signUpData.us_state_id) && Intrinsics.areEqual((Object) this.zipcode, (Object) signUpData.zipcode) && Intrinsics.areEqual((Object) this.voipToken, (Object) signUpData.voipToken) && this.notification_subscription_enabled == signUpData.notification_subscription_enabled && this.agree_to_terms == signUpData.agree_to_terms && this.two_factor_required == signUpData.two_factor_required;
    }

    public int hashCode() {
        String str = this.username;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.email;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.password;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.password_confirmation;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.first_name;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.last_name;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.dob;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.phone;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.city;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.street1;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.us_state_id;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.zipcode;
        int hashCode12 = (hashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.voipToken;
        if (str13 != null) {
            i = str13.hashCode();
        }
        int i2 = (hashCode12 + i) * 31;
        boolean z = this.notification_subscription_enabled;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i3 = (((i2 + (z ? 1 : 0)) * 31) + this.agree_to_terms) * 31;
        boolean z3 = this.two_factor_required;
        if (!z3) {
            z2 = z3;
        }
        return i3 + (z2 ? 1 : 0);
    }

    public SignUpData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, boolean z, int i, boolean z2) {
        this.username = str;
        this.email = str2;
        this.password = str3;
        this.password_confirmation = str4;
        this.first_name = str5;
        this.last_name = str6;
        this.dob = str7;
        this.phone = str8;
        this.city = str9;
        this.street1 = str10;
        this.us_state_id = str11;
        this.zipcode = str12;
        this.voipToken = str13;
        this.notification_subscription_enabled = z;
        this.agree_to_terms = i;
        this.two_factor_required = z2;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getPassword() {
        return this.password;
    }

    public final String getPassword_confirmation() {
        return this.password_confirmation;
    }

    public final String getFirst_name() {
        return this.first_name;
    }

    public final String getLast_name() {
        return this.last_name;
    }

    public final String getDob() {
        return this.dob;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getStreet1() {
        return this.street1;
    }

    public final String getUs_state_id() {
        return this.us_state_id;
    }

    public final String getZipcode() {
        return this.zipcode;
    }

    public final String getVoipToken() {
        return this.voipToken;
    }

    public final boolean getNotification_subscription_enabled() {
        return this.notification_subscription_enabled;
    }

    public final int getAgree_to_terms() {
        return this.agree_to_terms;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SignUpData(java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, java.lang.String r31, java.lang.String r32, java.lang.String r33, boolean r34, int r35, boolean r36, int r37, kotlin.jvm.internal.DefaultConstructorMarker r38) {
        /*
            r20 = this;
            r0 = r37
            r1 = r0 & 16384(0x4000, float:2.2959E-41)
            r2 = 1
            if (r1 == 0) goto L_0x000a
            r18 = 1
            goto L_0x000c
        L_0x000a:
            r18 = r35
        L_0x000c:
            r1 = 32768(0x8000, float:4.5918E-41)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x0015
            r19 = 1
            goto L_0x0017
        L_0x0015:
            r19 = r36
        L_0x0017:
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r23
            r7 = r24
            r8 = r25
            r9 = r26
            r10 = r27
            r11 = r28
            r12 = r29
            r13 = r30
            r14 = r31
            r15 = r32
            r16 = r33
            r17 = r34
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.data.register.SignUpData.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, int, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final boolean getTwo_factor_required() {
        return this.two_factor_required;
    }

    public String toString() {
        String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson((Object) this);
        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(this)");
        return json;
    }

    public final JsonObject toJson() {
        JsonElement jsonTree = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJsonTree(this);
        Intrinsics.checkExpressionValueIsNotNull(jsonTree, "gson.toJsonTree(this)");
        JsonObject asJsonObject = jsonTree.getAsJsonObject();
        Intrinsics.checkExpressionValueIsNotNull(asJsonObject, "gson.toJsonTree(this).asJsonObject");
        return asJsonObject;
    }
}
