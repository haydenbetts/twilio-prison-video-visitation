package com.forasoft.homewavvisitor.model.server.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/Terms;", "", "agreed", "", "terms_text", "", "(ZLjava/lang/String;)V", "getAgreed", "()Z", "getTerms_text", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Terms.kt */
public final class Terms {
    private final boolean agreed;
    private final String terms_text;

    public static /* synthetic */ Terms copy$default(Terms terms, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = terms.agreed;
        }
        if ((i & 2) != 0) {
            str = terms.terms_text;
        }
        return terms.copy(z, str);
    }

    public final boolean component1() {
        return this.agreed;
    }

    public final String component2() {
        return this.terms_text;
    }

    public final Terms copy(boolean z, String str) {
        Intrinsics.checkParameterIsNotNull(str, "terms_text");
        return new Terms(z, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Terms)) {
            return false;
        }
        Terms terms = (Terms) obj;
        return this.agreed == terms.agreed && Intrinsics.areEqual((Object) this.terms_text, (Object) terms.terms_text);
    }

    public int hashCode() {
        boolean z = this.agreed;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        String str = this.terms_text;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "Terms(agreed=" + this.agreed + ", terms_text=" + this.terms_text + ")";
    }

    public Terms(boolean z, String str) {
        Intrinsics.checkParameterIsNotNull(str, "terms_text");
        this.agreed = z;
        this.terms_text = str;
    }

    public final boolean getAgreed() {
        return this.agreed;
    }

    public final String getTerms_text() {
        return this.terms_text;
    }
}
