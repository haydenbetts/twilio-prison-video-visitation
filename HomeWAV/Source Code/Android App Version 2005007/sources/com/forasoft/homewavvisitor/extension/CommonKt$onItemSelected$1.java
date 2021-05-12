package com.forasoft.homewavvisitor.extension;

import android.view.View;
import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0016¨\u0006\r"}, d2 = {"com/forasoft/homewavvisitor/extension/CommonKt$onItemSelected$1", "Landroid/widget/AdapterView$OnItemSelectedListener;", "onItemSelected", "", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "position", "", "id", "", "onNothingSelected", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: common.kt */
public final class CommonKt$onItemSelected$1 implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ Function1 $l;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    CommonKt$onItemSelected$1(Function1 function1) {
        this.$l = function1;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.$l.invoke(Integer.valueOf(i));
    }
}
