package com.forasoft.homewavvisitor.model.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/MediaWithCategory;", "", "category", "", "items", "", "(Ljava/lang/String;Ljava/util/List;)V", "getCategory", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MediaWithCategory.kt */
public final class MediaWithCategory {
    @SerializedName("categoryName")
    private final String category;
    @SerializedName("items")
    private final List<String> items;

    public static /* synthetic */ MediaWithCategory copy$default(MediaWithCategory mediaWithCategory, String str, List<String> list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaWithCategory.category;
        }
        if ((i & 2) != 0) {
            list = mediaWithCategory.items;
        }
        return mediaWithCategory.copy(str, list);
    }

    public final String component1() {
        return this.category;
    }

    public final List<String> component2() {
        return this.items;
    }

    public final MediaWithCategory copy(String str, List<String> list) {
        Intrinsics.checkParameterIsNotNull(str, "category");
        Intrinsics.checkParameterIsNotNull(list, "items");
        return new MediaWithCategory(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaWithCategory)) {
            return false;
        }
        MediaWithCategory mediaWithCategory = (MediaWithCategory) obj;
        return Intrinsics.areEqual((Object) this.category, (Object) mediaWithCategory.category) && Intrinsics.areEqual((Object) this.items, (Object) mediaWithCategory.items);
    }

    public int hashCode() {
        String str = this.category;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<String> list = this.items;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "MediaWithCategory(category=" + this.category + ", items=" + this.items + ")";
    }

    public MediaWithCategory(String str, List<String> list) {
        Intrinsics.checkParameterIsNotNull(str, "category");
        Intrinsics.checkParameterIsNotNull(list, "items");
        this.category = str;
        this.items = list;
    }

    public final String getCategory() {
        return this.category;
    }

    public final List<String> getItems() {
        return this.items;
    }
}
