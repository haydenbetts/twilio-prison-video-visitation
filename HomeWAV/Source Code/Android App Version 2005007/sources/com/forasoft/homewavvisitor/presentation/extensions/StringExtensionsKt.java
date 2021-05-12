package com.forasoft.homewavvisitor.presentation.extensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"asInitials", "", "getAsInitials", "(Ljava/lang/String;)Ljava/lang/String;", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: StringExtensions.kt */
public final class StringExtensionsKt {
    public static final String getAsInitials(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$asInitials");
        Collection arrayList = new ArrayList();
        for (Object next : StringsKt.split$default((CharSequence) str, new String[]{" "}, false, 0, 6, (Object) null)) {
            if (!(((String) next).length() == 0)) {
                arrayList.add(next);
            }
        }
        Iterable<String> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String first : iterable) {
            arrayList2.add(Character.valueOf(StringsKt.first(first)));
        }
        return CollectionsKt.joinToString$default(CollectionsKt.take((List) arrayList2, 2), "", "", "", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
    }
}
