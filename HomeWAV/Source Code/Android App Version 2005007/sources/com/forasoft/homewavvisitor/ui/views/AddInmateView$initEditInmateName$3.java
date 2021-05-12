package com.forasoft.homewavvisitor.ui.views;

import android.widget.AdapterView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "", "parent", "Landroid/widget/AdapterView;", "kotlin.jvm.PlatformType", "view", "Landroid/view/View;", "position", "", "id", "", "onItemClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddInmateView.kt */
final class AddInmateView$initEditInmateName$3 implements AdapterView.OnItemClickListener {
    final /* synthetic */ AddInmateView this$0;

    AddInmateView$initEditInmateName$3(AddInmateView addInmateView) {
        this.this$0 = addInmateView;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.widget.AdapterView<?>, java.lang.Object, android.widget.AdapterView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onItemClick(android.widget.AdapterView<?> r3, android.view.View r4, int r5, long r6) {
        /*
            r2 = this;
            com.forasoft.homewavvisitor.ui.views.AddInmateView r4 = r2.this$0
            java.util.List r4 = r4.getInmates()
            if (r4 != 0) goto L_0x000b
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x000b:
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r4 = r4.iterator()
        L_0x0018:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x0044
            java.lang.Object r7 = r4.next()
            r0 = r7
            com.forasoft.homewavvisitor.model.data.Inmate r0 = (com.forasoft.homewavvisitor.model.data.Inmate) r0
            java.lang.String r1 = "parent"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r1)
            android.widget.Adapter r1 = r3.getAdapter()
            java.lang.Object r1 = r1.getItem(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = r0.toString()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r0)
            if (r0 == 0) goto L_0x0018
            r6.add(r7)
            goto L_0x0018
        L_0x0044:
            java.util.List r6 = (java.util.List) r6
            r3 = 0
            java.lang.Object r3 = r6.get(r3)
            com.forasoft.homewavvisitor.model.data.Inmate r3 = (com.forasoft.homewavvisitor.model.data.Inmate) r3
            com.forasoft.homewavvisitor.ui.views.AddInmateView r4 = r2.this$0
            com.forasoft.homewavvisitor.model.data.register.Connection r4 = r4.getConnection()
            r4.setInmate(r3)
            com.forasoft.homewavvisitor.ui.views.AddInmateView r3 = r2.this$0
            r3.checkCompleteness()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.views.AddInmateView$initEditInmateName$3.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
    }
}
