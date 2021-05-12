package com.forasoft.homewavvisitor.ui.fragment.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomeFragment.kt */
final class HomeFragment$showWarningDialog$1 extends Lambda implements Function1<Inmate, CharSequence> {
    public static final HomeFragment$showWarningDialog$1 INSTANCE = new HomeFragment$showWarningDialog$1();

    HomeFragment$showWarningDialog$1() {
        super(1);
    }

    public final CharSequence invoke(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "it");
        String full_name = inmate.getFull_name();
        if (full_name == null) {
            full_name = "";
        }
        return full_name;
    }
}
