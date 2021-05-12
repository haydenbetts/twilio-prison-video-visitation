package com.forasoft.homewavvisitor.model.data.register;

import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.views.AddInmateView;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010T\u001a\u00020/J\u0006\u0010U\u001a\u00020VR\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\f\"\u0004\b&\u0010\u000eR4\u0010)\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010(2\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010(@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\f\"\u0004\b5\u0010\u000eR\u001a\u00106\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\f\"\u0004\b8\u0010\u000eR\u001c\u00109\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\f\"\u0004\b;\u0010\u000eR\u001e\u0010<\u001a\u0004\u0018\u00010\n8FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\f\"\u0004\b>\u0010\u000eR\u001c\u0010?\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\f\"\u0004\bA\u0010\u000eR\u001a\u0010B\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0018\"\u0004\bD\u0010\u001aR\u001a\u0010E\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0018\"\u0004\bG\u0010\u001aR\u001c\u0010H\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\f\"\u0004\bJ\u0010\u000eR\u001a\u0010K\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\f\"\u0004\bM\u0010\u000eR(\u0010O\u001a\u0004\u0018\u00010N2\b\u0010'\u001a\u0004\u0018\u00010N@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010S¨\u0006W"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/register/Connection;", "", "()V", "constants", "Lcom/forasoft/homewavvisitor/model/Constants;", "getConstants", "()Lcom/forasoft/homewavvisitor/model/Constants;", "setConstants", "(Lcom/forasoft/homewavvisitor/model/Constants;)V", "dateOfBirth", "", "getDateOfBirth", "()Ljava/lang/String;", "setDateOfBirth", "(Ljava/lang/String;)V", "facilityId", "getFacilityId", "setFacilityId", "facilityName", "getFacilityName", "setFacilityName", "facilityPosition", "", "getFacilityPosition", "()I", "setFacilityPosition", "(I)V", "firstName", "getFirstName", "setFirstName", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "getInmate", "()Lcom/forasoft/homewavvisitor/model/data/Inmate;", "setInmate", "(Lcom/forasoft/homewavvisitor/model/data/Inmate;)V", "inmateId", "getInmateId", "setInmateId", "value", "", "inmatesList", "getInmatesList", "()Ljava/util/List;", "setInmatesList", "(Ljava/util/List;)V", "isCreating", "", "()Z", "setCreating", "(Z)V", "lastName", "getLastName", "setLastName", "middleName", "getMiddleName", "setMiddleName", "name", "getName", "setName", "relationship", "getRelationship", "setRelationship", "relationshipExplanation", "getRelationshipExplanation", "setRelationshipExplanation", "relationshipId", "getRelationshipId", "setRelationshipId", "state", "getState", "setState", "stateName", "getStateName", "setStateName", "suffix", "getSuffix", "setSuffix", "Lcom/forasoft/homewavvisitor/ui/views/AddInmateView;", "view", "getView", "()Lcom/forasoft/homewavvisitor/ui/views/AddInmateView;", "setView", "(Lcom/forasoft/homewavvisitor/ui/views/AddInmateView;)V", "isComplete", "toShortInmate", "Lcom/forasoft/homewavvisitor/model/data/register/InmateShort;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Connection.kt */
public final class Connection {
    @Inject
    public Constants constants;
    private String dateOfBirth = "";
    private String facilityId = "";
    private String facilityName;
    private int facilityPosition;
    private String firstName = "";
    private Inmate inmate;
    private String inmateId = "";
    private List<Inmate> inmatesList;
    private boolean isCreating;
    private String lastName = "";
    private String middleName = "";
    private String name;
    private String relationship;
    private String relationshipExplanation;
    private int relationshipId;
    private int state;
    private String stateName;
    private String suffix = "";
    private AddInmateView view;

    public Connection() {
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE));
        Constants constants2 = this.constants;
        if (constants2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("constants");
        }
        this.relationship = (String) CollectionsKt.first(constants2.getRelationshipList());
    }

    public final Constants getConstants() {
        Constants constants2 = this.constants;
        if (constants2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("constants");
        }
        return constants2;
    }

    public final void setConstants(Constants constants2) {
        Intrinsics.checkParameterIsNotNull(constants2, "<set-?>");
        this.constants = constants2;
    }

    public final int getState() {
        return this.state;
    }

    public final void setState(int i) {
        this.state = i;
    }

    public final int getFacilityPosition() {
        return this.facilityPosition;
    }

    public final void setFacilityPosition(int i) {
        this.facilityPosition = i;
    }

    public final String getFacilityId() {
        return this.facilityId;
    }

    public final void setFacilityId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.facilityId = str;
    }

    public final int getRelationshipId() {
        return this.relationshipId;
    }

    public final void setRelationshipId(int i) {
        this.relationshipId = i;
    }

    public final Inmate getInmate() {
        return this.inmate;
    }

    public final void setInmate(Inmate inmate2) {
        this.inmate = inmate2;
    }

    public final String getStateName() {
        return this.stateName;
    }

    public final void setStateName(String str) {
        this.stateName = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getFacilityName() {
        return this.facilityName;
    }

    public final void setFacilityName(String str) {
        this.facilityName = str;
    }

    public final void setRelationship(String str) {
        this.relationship = str;
    }

    public final String getRelationship() {
        String str = this.relationship;
        return str != null ? str : this.relationshipExplanation;
    }

    public final String getRelationshipExplanation() {
        return this.relationshipExplanation;
    }

    public final void setRelationshipExplanation(String str) {
        this.relationshipExplanation = str;
    }

    public final List<Inmate> getInmatesList() {
        return this.inmatesList;
    }

    public final void setInmatesList(List<Inmate> list) {
        this.inmatesList = list;
        StringBuilder sb = new StringBuilder();
        sb.append("setInmates to connection: view=");
        sb.append(this.view);
        sb.append("; list size=");
        List<Inmate> list2 = this.inmatesList;
        sb.append(list2 != null ? Integer.valueOf(list2.size()) : null);
        sb.append(' ');
        Timber.d(sb.toString(), new Object[0]);
        AddInmateView addInmateView = this.view;
        if (addInmateView != null) {
            addInmateView.setInmates(this.inmatesList);
        }
    }

    public final AddInmateView getView() {
        return this.view;
    }

    public final void setView(AddInmateView addInmateView) {
        this.view = addInmateView;
        StringBuilder sb = new StringBuilder();
        sb.append("setView  to connection: view=");
        sb.append(this.view);
        sb.append("; list size=");
        List<Inmate> list = this.inmatesList;
        sb.append(list != null ? Integer.valueOf(list.size()) : null);
        Timber.d(sb.toString(), new Object[0]);
        AddInmateView addInmateView2 = this.view;
        if (addInmateView2 != null) {
            addInmateView2.setInmates(this.inmatesList);
        }
    }

    public final boolean isCreating() {
        return this.isCreating;
    }

    public final void setCreating(boolean z) {
        this.isCreating = z;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.firstName = str;
    }

    public final String getLastName() {
        return this.lastName;
    }

    public final void setLastName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.lastName = str;
    }

    public final String getMiddleName() {
        return this.middleName;
    }

    public final void setMiddleName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.middleName = str;
    }

    public final String getSuffix() {
        return this.suffix;
    }

    public final void setSuffix(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.suffix = str;
    }

    public final String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public final void setDateOfBirth(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.dateOfBirth = str;
    }

    public final String getInmateId() {
        return this.inmateId;
    }

    public final void setInmateId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.inmateId = str;
    }

    public final boolean isComplete() {
        AddInmateView addInmateView = this.view;
        if (addInmateView != null) {
            return addInmateView.isComplete();
        }
        return false;
    }

    public final InmateShort toShortInmate() {
        String str = "";
        if (this.isCreating) {
            String str2 = this.dateOfBirth;
            String str3 = this.firstName;
            String str4 = this.inmateId;
            String str5 = this.lastName;
            String str6 = this.middleName;
            String str7 = this.facilityId;
            String str8 = this.suffix;
            String relationship2 = getRelationship();
            String str9 = relationship2 != null ? relationship2 : str;
            String str10 = this.relationshipExplanation;
            return new InmateShort(str2, str3, str4, str5, str6, str7, str8, str9, str10 != null ? str10 : str);
        }
        Inmate inmate2 = this.inmate;
        if (inmate2 == null) {
            Intrinsics.throwNpe();
        }
        String relationship3 = getRelationship();
        if (relationship3 == null) {
            relationship3 = str;
        }
        String str11 = this.relationshipExplanation;
        if (str11 != null) {
            str = str11;
        }
        return inmate2.toShortForm(relationship3, str);
    }
}
