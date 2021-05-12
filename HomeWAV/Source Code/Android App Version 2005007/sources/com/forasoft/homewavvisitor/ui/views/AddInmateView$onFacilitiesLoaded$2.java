package com.forasoft.homewavvisitor.ui.views;

import air.HomeWAV.R;
import androidx.constraintlayout.widget.Group;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.ui.views.AddInmateView;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "facilityPosition", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddInmateView.kt */
final class AddInmateView$onFacilitiesLoaded$2 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ AddInmateView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddInmateView$onFacilitiesLoaded$2(AddInmateView addInmateView) {
        super(1);
        this.this$0 = addInmateView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        if (!Intrinsics.areEqual(this.this$0.getSpinnerFacility().getSelectedItem(), (Object) this.this$0.getContext().getString(R.string.placeholder_select_facility))) {
            List<Facility> facilities = this.this$0.getFacilities();
            if (facilities == null) {
                Intrinsics.throwNpe();
            }
            Iterator<Facility> it = facilities.iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                } else if (Intrinsics.areEqual((Object) it.next().getName(), this.this$0.getSpinnerFacility().getSelectedItem())) {
                    break;
                } else {
                    i2++;
                }
            }
            List<Facility> facilities2 = this.this$0.getFacilities();
            if (facilities2 == null) {
                Intrinsics.throwNpe();
            }
            Facility facility = facilities2.get(i2);
            AddInmateView.Observer observer = this.this$0.getObserver();
            if (observer != null) {
                observer.onFacilityChosen(this.this$0.getPosition(), facility.getId());
            }
            this.this$0.getConnection().setFacilityName(facility.getName());
            this.this$0.getConnection().setFacilityPosition(i);
            this.this$0.getConnection().setFacilityId(facility.getId());
            if (Intrinsics.areEqual((Object) facility.getRequire_resident(), (Object) "0")) {
                this.this$0.getConnection().setCreating(true);
                CommonKt.show((Group) this.this$0.getView().findViewById(com.forasoft.homewavvisitor.R.id.groupAddInmate));
                CommonKt.hide((Group) this.this$0.getView().findViewById(com.forasoft.homewavvisitor.R.id.groupInmateAutocomplete));
            } else {
                this.this$0.getConnection().setCreating(false);
                CommonKt.hide((Group) this.this$0.getView().findViewById(com.forasoft.homewavvisitor.R.id.groupAddInmate));
                CommonKt.show((Group) this.this$0.getView().findViewById(com.forasoft.homewavvisitor.R.id.groupInmateAutocomplete));
            }
            this.this$0.clearEditInmate();
        }
    }
}
