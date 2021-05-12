package com.forasoft.homewavvisitor.model;

import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lcom/forasoft/homewavvisitor/model/Image;", "it", "", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: S3MediaLoader.kt */
final class S3MediaLoader$getImages$2<T, R> implements Function<T, R> {
    public static final S3MediaLoader$getImages$2 INSTANCE = new S3MediaLoader$getImages$2();

    S3MediaLoader$getImages$2() {
    }

    public final List<Image> apply(List<String> list) {
        Image image;
        Intrinsics.checkParameterIsNotNull(list, "it");
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String str : iterable) {
            if (StringsKt.endsWith$default(str, ".gif", false, 2, (Object) null)) {
                image = new Image(str, "image/gif");
            } else {
                image = new Image(str, (String) null, 2, (DefaultConstructorMarker) null);
            }
            arrayList.add(image);
        }
        return (List) arrayList;
    }
}
