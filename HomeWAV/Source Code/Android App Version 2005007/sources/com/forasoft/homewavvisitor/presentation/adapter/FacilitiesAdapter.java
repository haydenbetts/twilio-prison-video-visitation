package com.forasoft.homewavvisitor.presentation.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import com.forasoft.homewavvisitor.model.data.Facility;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/FacilitiesAdapter;", "Landroid/widget/ArrayAdapter;", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "getFilter", "Landroid/widget/Filter;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: FacilitiesAdapter.kt */
public final class FacilitiesAdapter extends ArrayAdapter<Facility> {
    /* access modifiers changed from: private */
    public final List<Facility> data;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FacilitiesAdapter(Context context, List<Facility> list) {
        super(context, 17367043);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(list, "data");
        this.data = list;
        addAll(list);
    }

    public Filter getFilter() {
        return new FacilitiesAdapter$getFilter$1(this);
    }
}
