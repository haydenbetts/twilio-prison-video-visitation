package com.forasoft.homewavvisitor.model.data;

import com.forasoft.homewavvisitor.ui.fragment.account.Question;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/SupportInfo;", "", "contacts", "Lcom/forasoft/homewavvisitor/model/data/Contacts;", "questions", "", "Lcom/forasoft/homewavvisitor/ui/fragment/account/Question;", "(Lcom/forasoft/homewavvisitor/model/data/Contacts;Ljava/util/List;)V", "getContacts", "()Lcom/forasoft/homewavvisitor/model/data/Contacts;", "getQuestions", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SupportInfo.kt */
public final class SupportInfo {
    private final Contacts contacts;
    private final List<Question> questions;

    public static /* synthetic */ SupportInfo copy$default(SupportInfo supportInfo, Contacts contacts2, List<Question> list, int i, Object obj) {
        if ((i & 1) != 0) {
            contacts2 = supportInfo.contacts;
        }
        if ((i & 2) != 0) {
            list = supportInfo.questions;
        }
        return supportInfo.copy(contacts2, list);
    }

    public final Contacts component1() {
        return this.contacts;
    }

    public final List<Question> component2() {
        return this.questions;
    }

    public final SupportInfo copy(Contacts contacts2, List<Question> list) {
        Intrinsics.checkParameterIsNotNull(contacts2, "contacts");
        Intrinsics.checkParameterIsNotNull(list, "questions");
        return new SupportInfo(contacts2, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SupportInfo)) {
            return false;
        }
        SupportInfo supportInfo = (SupportInfo) obj;
        return Intrinsics.areEqual((Object) this.contacts, (Object) supportInfo.contacts) && Intrinsics.areEqual((Object) this.questions, (Object) supportInfo.questions);
    }

    public int hashCode() {
        Contacts contacts2 = this.contacts;
        int i = 0;
        int hashCode = (contacts2 != null ? contacts2.hashCode() : 0) * 31;
        List<Question> list = this.questions;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "SupportInfo(contacts=" + this.contacts + ", questions=" + this.questions + ")";
    }

    public SupportInfo(Contacts contacts2, List<Question> list) {
        Intrinsics.checkParameterIsNotNull(contacts2, "contacts");
        Intrinsics.checkParameterIsNotNull(list, "questions");
        this.contacts = contacts2;
        this.questions = list;
    }

    public final Contacts getContacts() {
        return this.contacts;
    }

    public final List<Question> getQuestions() {
        return this.questions;
    }
}
