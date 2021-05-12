package com.forasoft.homewavvisitor.model.data.payment;

import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003Ji\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000f¨\u0006,"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/Credit;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "id", "", "inmate_id", "braintree_transaction_id", "stripe_transaction_id", "created", "Lorg/threeten/bp/LocalDateTime;", "creator", "notes", "type", "value", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/threeten/bp/LocalDateTime;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBraintree_transaction_id", "()Ljava/lang/String;", "getCreated", "()Lorg/threeten/bp/LocalDateTime;", "getCreator", "getId", "getInmate_id", "setInmate_id", "(Ljava/lang/String;)V", "getNotes", "getStripe_transaction_id", "getType", "getValue", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentResponce.kt */
public final class Credit implements ChatItem {
    private final String braintree_transaction_id;
    private final LocalDateTime created;
    private final String creator;
    private final String id;
    private String inmate_id;
    private final String notes;
    private final String stripe_transaction_id;
    private final String type;
    private final String value;

    public static /* synthetic */ Credit copy$default(Credit credit, String str, String str2, String str3, String str4, LocalDateTime localDateTime, String str5, String str6, String str7, String str8, int i, Object obj) {
        Credit credit2 = credit;
        int i2 = i;
        return credit.copy((i2 & 1) != 0 ? credit2.id : str, (i2 & 2) != 0 ? credit2.inmate_id : str2, (i2 & 4) != 0 ? credit2.braintree_transaction_id : str3, (i2 & 8) != 0 ? credit2.stripe_transaction_id : str4, (i2 & 16) != 0 ? credit.getCreated() : localDateTime, (i2 & 32) != 0 ? credit2.creator : str5, (i2 & 64) != 0 ? credit2.notes : str6, (i2 & 128) != 0 ? credit2.type : str7, (i2 & 256) != 0 ? credit2.value : str8);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.inmate_id;
    }

    public final String component3() {
        return this.braintree_transaction_id;
    }

    public final String component4() {
        return this.stripe_transaction_id;
    }

    public final LocalDateTime component5() {
        return getCreated();
    }

    public final String component6() {
        return this.creator;
    }

    public final String component7() {
        return this.notes;
    }

    public final String component8() {
        return this.type;
    }

    public final String component9() {
        return this.value;
    }

    public final Credit copy(String str, String str2, String str3, String str4, LocalDateTime localDateTime, String str5, String str6, String str7, String str8) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        LocalDateTime localDateTime2 = localDateTime;
        Intrinsics.checkParameterIsNotNull(localDateTime2, "created");
        String str9 = str5;
        Intrinsics.checkParameterIsNotNull(str9, "creator");
        String str10 = str6;
        Intrinsics.checkParameterIsNotNull(str10, "notes");
        String str11 = str7;
        Intrinsics.checkParameterIsNotNull(str11, "type");
        String str12 = str8;
        Intrinsics.checkParameterIsNotNull(str12, CommonProperties.VALUE);
        return new Credit(str, str2, str3, str4, localDateTime2, str9, str10, str11, str12);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Credit)) {
            return false;
        }
        Credit credit = (Credit) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) credit.id) && Intrinsics.areEqual((Object) this.inmate_id, (Object) credit.inmate_id) && Intrinsics.areEqual((Object) this.braintree_transaction_id, (Object) credit.braintree_transaction_id) && Intrinsics.areEqual((Object) this.stripe_transaction_id, (Object) credit.stripe_transaction_id) && Intrinsics.areEqual((Object) getCreated(), (Object) credit.getCreated()) && Intrinsics.areEqual((Object) this.creator, (Object) credit.creator) && Intrinsics.areEqual((Object) this.notes, (Object) credit.notes) && Intrinsics.areEqual((Object) this.type, (Object) credit.type) && Intrinsics.areEqual((Object) this.value, (Object) credit.value);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.inmate_id;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.braintree_transaction_id;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.stripe_transaction_id;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        LocalDateTime created2 = getCreated();
        int hashCode5 = (hashCode4 + (created2 != null ? created2.hashCode() : 0)) * 31;
        String str5 = this.creator;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.notes;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.type;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.value;
        if (str8 != null) {
            i = str8.hashCode();
        }
        return hashCode8 + i;
    }

    public String toString() {
        return "Credit(id=" + this.id + ", inmate_id=" + this.inmate_id + ", braintree_transaction_id=" + this.braintree_transaction_id + ", stripe_transaction_id=" + this.stripe_transaction_id + ", created=" + getCreated() + ", creator=" + this.creator + ", notes=" + this.notes + ", type=" + this.type + ", value=" + this.value + ")";
    }

    public Credit(String str, String str2, String str3, String str4, LocalDateTime localDateTime, String str5, String str6, String str7, String str8) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(localDateTime, "created");
        Intrinsics.checkParameterIsNotNull(str5, "creator");
        Intrinsics.checkParameterIsNotNull(str6, "notes");
        Intrinsics.checkParameterIsNotNull(str7, "type");
        Intrinsics.checkParameterIsNotNull(str8, CommonProperties.VALUE);
        this.id = str;
        this.inmate_id = str2;
        this.braintree_transaction_id = str3;
        this.stripe_transaction_id = str4;
        this.created = localDateTime;
        this.creator = str5;
        this.notes = str6;
        this.type = str7;
        this.value = str8;
    }

    public int compareTo(ChatItem chatItem) {
        Intrinsics.checkParameterIsNotNull(chatItem, "other");
        return ChatItem.DefaultImpls.compareTo(this, chatItem);
    }

    public final String getId() {
        return this.id;
    }

    public final String getInmate_id() {
        return this.inmate_id;
    }

    public final void setInmate_id(String str) {
        this.inmate_id = str;
    }

    public final String getBraintree_transaction_id() {
        return this.braintree_transaction_id;
    }

    public final String getStripe_transaction_id() {
        return this.stripe_transaction_id;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public final String getCreator() {
        return this.creator;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getType() {
        return this.type;
    }

    public final String getValue() {
        return this.value;
    }
}
