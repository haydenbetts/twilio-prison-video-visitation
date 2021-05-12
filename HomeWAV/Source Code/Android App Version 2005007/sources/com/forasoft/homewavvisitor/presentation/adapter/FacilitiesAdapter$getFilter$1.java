package com.forasoft.homewavvisitor.presentation.adapter;

import android.widget.Filter;
import com.forasoft.homewavvisitor.model.data.Facility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0014J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0014¨\u0006\t"}, d2 = {"com/forasoft/homewavvisitor/presentation/adapter/FacilitiesAdapter$getFilter$1", "Landroid/widget/Filter;", "performFiltering", "Landroid/widget/Filter$FilterResults;", "constraint", "", "publishResults", "", "results", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: FacilitiesAdapter.kt */
public final class FacilitiesAdapter$getFilter$1 extends Filter {
    final /* synthetic */ FacilitiesAdapter this$0;

    FacilitiesAdapter$getFilter$1(FacilitiesAdapter facilitiesAdapter) {
        this.this$0 = facilitiesAdapter;
    }

    /* access modifiers changed from: protected */
    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        Collection arrayList = new ArrayList();
        for (Object next : this.this$0.data) {
            if (Intrinsics.areEqual((Object) ((Facility) next).getUs_state_id(), (Object) charSequence)) {
                arrayList.add(next);
            }
        }
        List list = (List) arrayList;
        Filter.FilterResults filterResults = new Filter.FilterResults();
        filterResults.count = list.size();
        filterResults.values = list;
        return filterResults;
    }

    /* access modifiers changed from: protected */
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        if (filterResults == null) {
            Intrinsics.throwNpe();
        }
        if (filterResults.count == 0) {
            this.this$0.notifyDataSetInvalidated();
            return;
        }
        this.this$0.clear();
        FacilitiesAdapter facilitiesAdapter = this.this$0;
        Object obj = filterResults.values;
        if (obj != null) {
            facilitiesAdapter.addAll(TypeIntrinsics.asMutableCollection(obj));
            this.this$0.notifyDataSetChanged();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<out com.forasoft.homewavvisitor.model.data.Facility>");
    }
}
