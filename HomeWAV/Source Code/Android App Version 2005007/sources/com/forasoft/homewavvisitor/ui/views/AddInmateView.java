package com.forasoft.homewavvisitor.ui.views;

import air.HomeWAV.R;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.register.Connection;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.toothpick.FacilitiesSubjectWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.anko.sdk15.listeners.Sdk15ListenersListenersKt;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0002klB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010^\u001a\u00020_H\u0002J\b\u0010`\u001a\u00020_H\u0002J\b\u0010a\u001a\u00020_H\u0002J\n\u0010b\u001a\u0004\u0018\u000107H\u0002J\b\u0010c\u001a\u00020_H\u0002J\b\u0010d\u001a\u00020_H\u0002J\b\u0010e\u001a\u00020_H\u0002J\b\u0010f\u001a\u00020_H\u0002J\b\u0010g\u001a\u00020_H\u0002J\b\u0010h\u001a\u00020_H\u0002J\u0018\u0010i\u001a\u00020_2\u000e\u0010j\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'H\u0002R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00198\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\"\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010-\u001a\u00020.8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0011\u00103\u001a\u000204¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R4\u00108\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010'2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010'@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010*\"\u0004\b:\u0010,R\u001a\u0010;\u001a\u00020<X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010=\"\u0004\b>\u0010?R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001a\u0010F\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001a\u0010K\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010H\"\u0004\bM\u0010JR\u0011\u0010N\u001a\u00020O¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u0011\u0010R\u001a\u00020O¢\u0006\b\n\u0000\u001a\u0004\bS\u0010QR\u0011\u0010T\u001a\u00020O¢\u0006\b\n\u0000\u001a\u0004\bU\u0010QR\u0011\u0010V\u001a\u00020W¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0011\u0010Z\u001a\u00020[¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010]¨\u0006m"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/AddInmateView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "value", "Lcom/forasoft/homewavvisitor/model/data/register/Connection;", "connection", "getConnection", "()Lcom/forasoft/homewavvisitor/model/data/register/Connection;", "setConnection", "(Lcom/forasoft/homewavvisitor/model/data/register/Connection;)V", "constants", "Lcom/forasoft/homewavvisitor/model/Constants;", "getConstants", "()Lcom/forasoft/homewavvisitor/model/Constants;", "setConstants", "(Lcom/forasoft/homewavvisitor/model/Constants;)V", "editExplanation", "Landroid/widget/EditText;", "getEditExplanation", "()Landroid/widget/EditText;", "editInmate", "Lcom/forasoft/homewavvisitor/ui/views/InstantAutoCompleteTextView;", "getEditInmate", "()Lcom/forasoft/homewavvisitor/ui/views/InstantAutoCompleteTextView;", "facilities", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "getFacilities", "()Ljava/util/List;", "setFacilities", "(Ljava/util/List;)V", "facilitiesSubjectWrapper", "Lcom/forasoft/homewavvisitor/toothpick/FacilitiesSubjectWrapper;", "getFacilitiesSubjectWrapper", "()Lcom/forasoft/homewavvisitor/toothpick/FacilitiesSubjectWrapper;", "setFacilitiesSubjectWrapper", "(Lcom/forasoft/homewavvisitor/toothpick/FacilitiesSubjectWrapper;)V", "groupExplain", "Landroidx/constraintlayout/widget/Group;", "getGroupExplain", "()Landroidx/constraintlayout/widget/Group;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "inmates", "getInmates", "setInmates", "isComplete", "", "()Z", "setComplete", "(Z)V", "observer", "Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$Observer;", "getObserver", "()Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$Observer;", "setObserver", "(Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$Observer;)V", "position", "getPosition", "()I", "setPosition", "(I)V", "selectedState", "getSelectedState", "setSelectedState", "spinnerFacility", "Landroid/widget/Spinner;", "getSpinnerFacility", "()Landroid/widget/Spinner;", "spinnerRelationship", "getSpinnerRelationship", "spinnerState", "getSpinnerState", "uuid", "", "getUuid", "()Ljava/lang/String;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "checkCompleteness", "", "clearEditInmate", "filterFacilities", "getInmateFromText", "initAddInmateViews", "initEditInmateName", "initExplanation", "initFacilitySpinner", "initRelationshipSpinner", "initStateSpinner", "onFacilitiesLoaded", "newList", "Observer", "SpinnerAdapter", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddInmateView.kt */
public final class AddInmateView extends FrameLayout {
    private HashMap _$_findViewCache;
    @Inject
    public Activity activity;
    private Connection connection = new Connection();
    @Inject
    public Constants constants;
    private final EditText editExplanation;
    private final InstantAutoCompleteTextView editInmate;
    private List<Facility> facilities;
    @Inject
    public FacilitiesSubjectWrapper facilitiesSubjectWrapper;
    private final Group groupExplain;
    private List<Inmate> inmates;
    private boolean isComplete;
    private Observer observer;
    private int position = -1;
    private int selectedState = -1;
    private final Spinner spinnerFacility;
    private final Spinner spinnerRelationship;
    private final Spinner spinnerState;
    private final String uuid;
    private final View view;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$Observer;", "", "onCompletedStateChange", "", "completed", "", "onFacilityChosen", "connectionPosition", "", "facilityId", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AddInmateView.kt */
    public interface Observer {
        void onCompletedStateChange(boolean z);

        void onFacilityChosen(int i, String str);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view2 = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view2 != null) {
            return view2;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddInmateView(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        String uuid2 = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid2, "UUID.randomUUID().toString()");
        this.uuid = uuid2;
        Toothpick.inject(this, Toothpick.openScope(DI.ADD_CONNECTION_SCOPE));
        Object systemService = getContext().getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.item_contact_inmate, this, true);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ntact_inmate, this, true)");
            this.view = inflate;
            View findViewById = inflate.findViewById(R.id.spinnerState);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.spinnerState)");
            this.spinnerState = (Spinner) findViewById;
            View findViewById2 = inflate.findViewById(R.id.spinnerRelationship);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.spinnerRelationship)");
            this.spinnerRelationship = (Spinner) findViewById2;
            View findViewById3 = inflate.findViewById(R.id.spinnerFacility);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.spinnerFacility)");
            this.spinnerFacility = (Spinner) findViewById3;
            View findViewById4 = inflate.findViewById(R.id.editInmateName);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.editInmateName)");
            this.editInmate = (InstantAutoCompleteTextView) findViewById4;
            View findViewById5 = inflate.findViewById(R.id.editExplanation);
            Intrinsics.checkExpressionValueIsNotNull(findViewById5, "view.findViewById(R.id.editExplanation)");
            this.editExplanation = (EditText) findViewById5;
            View findViewById6 = inflate.findViewById(R.id.groupExplain);
            Intrinsics.checkExpressionValueIsNotNull(findViewById6, "view.findViewById(R.id.groupExplain)");
            this.groupExplain = (Group) findViewById6;
            initStateSpinner();
            initFacilitySpinner();
            initEditInmateName();
            initRelationshipSpinner();
            initExplanation();
            initAddInmateViews();
            FacilitiesSubjectWrapper facilitiesSubjectWrapper2 = this.facilitiesSubjectWrapper;
            if (facilitiesSubjectWrapper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("facilitiesSubjectWrapper");
            }
            facilitiesSubjectWrapper2.getSubject().subscribe(new AddInmateView$sam$io_reactivex_functions_Consumer$0(new Function1<List<? extends Facility>, Unit>(this) {
                public final String getName() {
                    return "onFacilitiesLoaded";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(AddInmateView.class);
                }

                public final String getSignature() {
                    return "onFacilitiesLoaded(Ljava/util/List;)V";
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((List<Facility>) (List) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(List<Facility> list) {
                    ((AddInmateView) this.receiver).onFacilitiesLoaded(list);
                }
            }));
            setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddInmateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        String uuid2 = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid2, "UUID.randomUUID().toString()");
        this.uuid = uuid2;
        Toothpick.inject(this, Toothpick.openScope(DI.ADD_CONNECTION_SCOPE));
        Object systemService = getContext().getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.item_contact_inmate, this, true);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ntact_inmate, this, true)");
            this.view = inflate;
            View findViewById = inflate.findViewById(R.id.spinnerState);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.spinnerState)");
            this.spinnerState = (Spinner) findViewById;
            View findViewById2 = inflate.findViewById(R.id.spinnerRelationship);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.spinnerRelationship)");
            this.spinnerRelationship = (Spinner) findViewById2;
            View findViewById3 = inflate.findViewById(R.id.spinnerFacility);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.spinnerFacility)");
            this.spinnerFacility = (Spinner) findViewById3;
            View findViewById4 = inflate.findViewById(R.id.editInmateName);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.editInmateName)");
            this.editInmate = (InstantAutoCompleteTextView) findViewById4;
            View findViewById5 = inflate.findViewById(R.id.editExplanation);
            Intrinsics.checkExpressionValueIsNotNull(findViewById5, "view.findViewById(R.id.editExplanation)");
            this.editExplanation = (EditText) findViewById5;
            View findViewById6 = inflate.findViewById(R.id.groupExplain);
            Intrinsics.checkExpressionValueIsNotNull(findViewById6, "view.findViewById(R.id.groupExplain)");
            this.groupExplain = (Group) findViewById6;
            initStateSpinner();
            initFacilitySpinner();
            initEditInmateName();
            initRelationshipSpinner();
            initExplanation();
            initAddInmateViews();
            FacilitiesSubjectWrapper facilitiesSubjectWrapper2 = this.facilitiesSubjectWrapper;
            if (facilitiesSubjectWrapper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("facilitiesSubjectWrapper");
            }
            facilitiesSubjectWrapper2.getSubject().subscribe(new AddInmateView$sam$io_reactivex_functions_Consumer$0(new Function1<List<? extends Facility>, Unit>(this) {
                public final String getName() {
                    return "onFacilitiesLoaded";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(AddInmateView.class);
                }

                public final String getSignature() {
                    return "onFacilitiesLoaded(Ljava/util/List;)V";
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((List<Facility>) (List) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(List<Facility> list) {
                    ((AddInmateView) this.receiver).onFacilitiesLoaded(list);
                }
            }));
            setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AddInmateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        String uuid2 = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid2, "UUID.randomUUID().toString()");
        this.uuid = uuid2;
        Toothpick.inject(this, Toothpick.openScope(DI.ADD_CONNECTION_SCOPE));
        Object systemService = getContext().getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.item_contact_inmate, this, true);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ntact_inmate, this, true)");
            this.view = inflate;
            View findViewById = inflate.findViewById(R.id.spinnerState);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.spinnerState)");
            this.spinnerState = (Spinner) findViewById;
            View findViewById2 = inflate.findViewById(R.id.spinnerRelationship);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.spinnerRelationship)");
            this.spinnerRelationship = (Spinner) findViewById2;
            View findViewById3 = inflate.findViewById(R.id.spinnerFacility);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.spinnerFacility)");
            this.spinnerFacility = (Spinner) findViewById3;
            View findViewById4 = inflate.findViewById(R.id.editInmateName);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.editInmateName)");
            this.editInmate = (InstantAutoCompleteTextView) findViewById4;
            View findViewById5 = inflate.findViewById(R.id.editExplanation);
            Intrinsics.checkExpressionValueIsNotNull(findViewById5, "view.findViewById(R.id.editExplanation)");
            this.editExplanation = (EditText) findViewById5;
            View findViewById6 = inflate.findViewById(R.id.groupExplain);
            Intrinsics.checkExpressionValueIsNotNull(findViewById6, "view.findViewById(R.id.groupExplain)");
            this.groupExplain = (Group) findViewById6;
            initStateSpinner();
            initFacilitySpinner();
            initEditInmateName();
            initRelationshipSpinner();
            initExplanation();
            initAddInmateViews();
            FacilitiesSubjectWrapper facilitiesSubjectWrapper2 = this.facilitiesSubjectWrapper;
            if (facilitiesSubjectWrapper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("facilitiesSubjectWrapper");
            }
            facilitiesSubjectWrapper2.getSubject().subscribe(new AddInmateView$sam$io_reactivex_functions_Consumer$0(new Function1<List<? extends Facility>, Unit>(this) {
                public final String getName() {
                    return "onFacilitiesLoaded";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(AddInmateView.class);
                }

                public final String getSignature() {
                    return "onFacilitiesLoaded(Ljava/util/List;)V";
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((List<Facility>) (List) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(List<Facility> list) {
                    ((AddInmateView) this.receiver).onFacilitiesLoaded(list);
                }
            }));
            setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
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

    public final FacilitiesSubjectWrapper getFacilitiesSubjectWrapper() {
        FacilitiesSubjectWrapper facilitiesSubjectWrapper2 = this.facilitiesSubjectWrapper;
        if (facilitiesSubjectWrapper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("facilitiesSubjectWrapper");
        }
        return facilitiesSubjectWrapper2;
    }

    public final void setFacilitiesSubjectWrapper(FacilitiesSubjectWrapper facilitiesSubjectWrapper2) {
        Intrinsics.checkParameterIsNotNull(facilitiesSubjectWrapper2, "<set-?>");
        this.facilitiesSubjectWrapper = facilitiesSubjectWrapper2;
    }

    public final Activity getActivity() {
        Activity activity2 = this.activity;
        if (activity2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activity");
        }
        return activity2;
    }

    public final void setActivity(Activity activity2) {
        Intrinsics.checkParameterIsNotNull(activity2, "<set-?>");
        this.activity = activity2;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public final View getView() {
        return this.view;
    }

    public final Spinner getSpinnerState() {
        return this.spinnerState;
    }

    public final Spinner getSpinnerRelationship() {
        return this.spinnerRelationship;
    }

    public final Spinner getSpinnerFacility() {
        return this.spinnerFacility;
    }

    public final InstantAutoCompleteTextView getEditInmate() {
        return this.editInmate;
    }

    public final EditText getEditExplanation() {
        return this.editExplanation;
    }

    public final Group getGroupExplain() {
        return this.groupExplain;
    }

    public final Connection getConnection() {
        return this.connection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x001c, code lost:
        r0 = r0.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setConnection(com.forasoft.homewavvisitor.model.data.register.Connection r3) {
        /*
            r2 = this;
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            r2.connection = r3
            android.widget.Spinner r0 = r2.spinnerRelationship
            int r3 = r3.getRelationshipId()
            r0.setSelection(r3)
            com.forasoft.homewavvisitor.ui.views.InstantAutoCompleteTextView r3 = r2.editInmate
            com.forasoft.homewavvisitor.model.data.register.Connection r0 = r2.connection
            com.forasoft.homewavvisitor.model.data.Inmate r0 = r0.getInmate()
            r1 = 0
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L_0x0027
            android.text.Editable r0 = com.forasoft.homewavvisitor.extension.CommonKt.toEditable(r0)
            goto L_0x0028
        L_0x0027:
            r0 = r1
        L_0x0028:
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r3.setText(r0)
            android.widget.EditText r3 = r2.editExplanation
            com.forasoft.homewavvisitor.model.data.register.Connection r0 = r2.connection
            java.lang.String r0 = r0.getRelationshipExplanation()
            if (r0 == 0) goto L_0x003b
            android.text.Editable r1 = com.forasoft.homewavvisitor.extension.CommonKt.toEditable(r0)
        L_0x003b:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r3.setText(r1)
            com.forasoft.homewavvisitor.model.data.register.Connection r3 = r2.connection
            java.util.List r3 = r3.getInmatesList()
            r2.setInmates(r3)
            com.forasoft.homewavvisitor.model.data.register.Connection r3 = r2.connection
            r0 = r2
            com.forasoft.homewavvisitor.ui.views.AddInmateView r0 = (com.forasoft.homewavvisitor.ui.views.AddInmateView) r0
            r3.setView(r0)
            r2.checkCompleteness()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.views.AddInmateView.setConnection(com.forasoft.homewavvisitor.model.data.register.Connection):void");
    }

    public final Observer getObserver() {
        return this.observer;
    }

    public final void setObserver(Observer observer2) {
        this.observer = observer2;
    }

    public final List<Facility> getFacilities() {
        return this.facilities;
    }

    public final void setFacilities(List<Facility> list) {
        this.facilities = list;
    }

    public final int getSelectedState() {
        return this.selectedState;
    }

    public final void setSelectedState(int i) {
        this.selectedState = i;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    public final List<Inmate> getInmates() {
        return this.inmates;
    }

    public final void setInmates(List<Inmate> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("setting inmates to view; list:");
        sb.append(list != null ? Integer.valueOf(list.size()) : null);
        sb.append(" for ");
        sb.append(this.uuid);
        Timber.d(sb.toString(), new Object[0]);
        if (list != null) {
            this.inmates = list;
            this.editInmate.setEnabled(true);
            this.editInmate.setAdapter(new ArrayAdapter(getContext(), R.layout.item_simple_list, list));
            this.editInmate.showDropDownIfFocused();
        }
    }

    public final boolean isComplete() {
        return this.isComplete;
    }

    public final void setComplete(boolean z) {
        this.isComplete = z;
    }

    private final void initStateSpinner() {
        this.spinnerState.setEnabled(false);
        this.spinnerState.setAdapter(new ArrayAdapter(getContext(), R.layout.item_simple_list, CollectionsKt.listOf(getContext().getString(R.string.placeholder_select_state))));
        CommonKt.onItemSelected(this.spinnerState, new AddInmateView$initStateSpinner$1(this));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007a, code lost:
        if ((r0 == null || r0.length() == 0) == false) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b8, code lost:
        if ((r0 == null || r0.length() == 0) == false) goto L_0x00bc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void checkCompleteness() {
        /*
            r5 = this;
            com.forasoft.homewavvisitor.model.data.register.Connection r0 = r5.connection
            boolean r0 = r0.isCreating()
            java.lang.String r1 = "constants"
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x007d
            android.widget.Spinner r0 = r5.spinnerFacility
            android.widget.SpinnerAdapter r0 = r0.getAdapter()
            if (r0 == 0) goto L_0x00bb
            com.forasoft.homewavvisitor.model.data.register.Connection r0 = r5.connection
            java.lang.String r0 = r0.getFirstName()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0024
            r0 = 1
            goto L_0x0025
        L_0x0024:
            r0 = 0
        L_0x0025:
            if (r0 == 0) goto L_0x00bb
            com.forasoft.homewavvisitor.model.data.register.Connection r0 = r5.connection
            java.lang.String r0 = r0.getLastName()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0037
            r0 = 1
            goto L_0x0038
        L_0x0037:
            r0 = 0
        L_0x0038:
            if (r0 == 0) goto L_0x00bb
            com.forasoft.homewavvisitor.model.data.register.Connection r0 = r5.connection
            java.lang.String r0 = r0.getInmateId()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x004a
            r0 = 1
            goto L_0x004b
        L_0x004a:
            r0 = 0
        L_0x004b:
            if (r0 == 0) goto L_0x00bb
            android.widget.Spinner r0 = r5.spinnerRelationship
            int r0 = r0.getSelectedItemPosition()
            com.forasoft.homewavvisitor.model.Constants r4 = r5.constants
            if (r4 != 0) goto L_0x005a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x005a:
            java.util.ArrayList r1 = r4.getRelationshipList()
            java.util.List r1 = (java.util.List) r1
            int r1 = kotlin.collections.CollectionsKt.getLastIndex(r1)
            if (r0 != r1) goto L_0x00bc
            android.widget.EditText r0 = r5.editExplanation
            android.text.Editable r0 = r0.getText()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0079
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r0 = 0
            goto L_0x007a
        L_0x0079:
            r0 = 1
        L_0x007a:
            if (r0 != 0) goto L_0x00bb
            goto L_0x00bc
        L_0x007d:
            android.widget.Spinner r0 = r5.spinnerFacility
            android.widget.SpinnerAdapter r0 = r0.getAdapter()
            if (r0 == 0) goto L_0x00bb
            com.forasoft.homewavvisitor.model.data.Inmate r0 = r5.getInmateFromText()
            if (r0 == 0) goto L_0x00bb
            android.widget.Spinner r0 = r5.spinnerRelationship
            int r0 = r0.getSelectedItemPosition()
            com.forasoft.homewavvisitor.model.Constants r4 = r5.constants
            if (r4 != 0) goto L_0x0098
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x0098:
            java.util.ArrayList r1 = r4.getRelationshipList()
            java.util.List r1 = (java.util.List) r1
            int r1 = kotlin.collections.CollectionsKt.getLastIndex(r1)
            if (r0 != r1) goto L_0x00bc
            android.widget.EditText r0 = r5.editExplanation
            android.text.Editable r0 = r0.getText()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x00b7
            int r0 = r0.length()
            if (r0 != 0) goto L_0x00b5
            goto L_0x00b7
        L_0x00b5:
            r0 = 0
            goto L_0x00b8
        L_0x00b7:
            r0 = 1
        L_0x00b8:
            if (r0 != 0) goto L_0x00bb
            goto L_0x00bc
        L_0x00bb:
            r2 = 0
        L_0x00bc:
            boolean r0 = r5.isComplete
            if (r0 == r2) goto L_0x00c9
            r5.isComplete = r2
            com.forasoft.homewavvisitor.ui.views.AddInmateView$Observer r0 = r5.observer
            if (r0 == 0) goto L_0x00c9
            r0.onCompletedStateChange(r2)
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.views.AddInmateView.checkCompleteness():void");
    }

    private final Inmate getInmateFromText() {
        List<Inmate> list = this.inmates;
        if (list != null) {
            if (list == null) {
                Intrinsics.throwNpe();
            }
            Collection arrayList = new ArrayList();
            for (Object next : list) {
                if (Intrinsics.areEqual((Object) this.editInmate.getText().toString(), (Object) ((Inmate) next).toString())) {
                    arrayList.add(next);
                }
            }
            List list2 = (List) arrayList;
            if (!list2.isEmpty()) {
                return (Inmate) list2.get(0);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final void filterFacilities() {
        if (this.facilities != null) {
            Constants constants2 = this.constants;
            if (constants2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constants");
            }
            Iterator<String> it = constants2.getStatesList().iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual((Object) it.next(), this.spinnerState.getSelectedItem())) {
                    break;
                } else {
                    i++;
                }
            }
            int i2 = i + 1;
            List<Facility> list = this.facilities;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            Object[] array = list.toArray(new Facility[0]);
            if (array != null) {
                Collection arrayList = new ArrayList();
                for (Object obj : array) {
                    if (Intrinsics.areEqual((Object) ((Facility) obj).getUs_state_id(), (Object) String.valueOf(i2))) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                Iterable<Facility> iterable = (List) arrayList;
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Facility facility : iterable) {
                    arrayList3.add(facility.toString());
                }
                arrayList2.addAll((List) arrayList3);
                if (!arrayList2.isEmpty()) {
                    this.spinnerFacility.setEnabled(true);
                    arrayList2.add(getContext().getString(R.string.placeholder_select_facility));
                    Spinner spinner = this.spinnerFacility;
                    Context context = getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "context");
                    List list2 = arrayList2;
                    spinner.setAdapter(new SpinnerAdapter(context, list2));
                    this.spinnerFacility.setSelection(CollectionsKt.getLastIndex(list2));
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    /* access modifiers changed from: private */
    public final void clearEditInmate() {
        this.editInmate.setText("");
        this.editInmate.setEnabled(false);
    }

    private final void initFacilitySpinner() {
        this.spinnerFacility.setEnabled(false);
        this.spinnerFacility.setAdapter(new ArrayAdapter(getContext(), R.layout.item_simple_list, CollectionsKt.listOf(getContext().getString(R.string.placeholder_select_facility))));
    }

    private final void initEditInmateName() {
        this.editInmate.setEnabled(false);
        this.editInmate.setOnFocusChangeListener(new AddInmateView$inlined$sam$i$android_view_View_OnFocusChangeListener$0(new AddInmateView$initEditInmateName$1(this)));
        Sdk15ListenersListenersKt.textChangedListener(this.editInmate, new AddInmateView$initEditInmateName$2(this));
        this.editInmate.setOnItemClickListener(new AddInmateView$initEditInmateName$3(this));
        this.editInmate.setShowAlways(true);
    }

    private final void initRelationshipSpinner() {
        Spinner spinner = this.spinnerRelationship;
        Context context = getContext();
        Constants constants2 = this.constants;
        if (constants2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("constants");
        }
        spinner.setAdapter(new ArrayAdapter(context, R.layout.item_simple_list, constants2.getRelationshipList()));
        CommonKt.onItemSelected(this.spinnerRelationship, new AddInmateView$initRelationshipSpinner$1(this));
    }

    private final void initExplanation() {
        CommonKt.hide(this.groupExplain);
        Sdk15ListenersListenersKt.textChangedListener(this.editExplanation, new AddInmateView$initExplanation$1(this));
    }

    private final void initAddInmateViews() {
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editInmateFirstName);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editInmateFirstName");
        Sdk15ListenersListenersKt.textChangedListener(editText, new AddInmateView$initAddInmateViews$1(this));
        EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editInmateLastName);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editInmateLastName");
        Sdk15ListenersListenersKt.textChangedListener(editText2, new AddInmateView$initAddInmateViews$2(this));
        EditText editText3 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editInmateMiddleName);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "editInmateMiddleName");
        Sdk15ListenersListenersKt.textChangedListener(editText3, new AddInmateView$initAddInmateViews$3(this));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editDateOfBirth");
        Sdk15ListenersListenersKt.textChangedListener(textView, new AddInmateView$initAddInmateViews$4(this));
        EditText editText4 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editSuffix);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "editSuffix");
        Sdk15ListenersListenersKt.textChangedListener(editText4, new AddInmateView$initAddInmateViews$5(this));
        EditText editText5 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editInmateId);
        Intrinsics.checkExpressionValueIsNotNull(editText5, "editInmateId");
        Sdk15ListenersListenersKt.textChangedListener(editText5, new AddInmateView$initAddInmateViews$6(this));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "editDateOfBirth");
        textView2.setOnClickListener(new AddInmateView$inlined$sam$i$android_view_View_OnClickListener$0(new AddInmateView$initAddInmateViews$7(this)));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0082 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onFacilitiesLoaded(java.util.List<com.forasoft.homewavvisitor.model.data.Facility> r10) {
        /*
            r9 = this;
            java.util.List<com.forasoft.homewavvisitor.model.data.Facility> r0 = r9.facilities
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r10)
            if (r0 == 0) goto L_0x0009
            return
        L_0x0009:
            r9.facilities = r10
            android.widget.Spinner r10 = r9.spinnerState
            r0 = 1
            r10.setEnabled(r0)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            com.forasoft.homewavvisitor.model.Constants r1 = r9.constants
            if (r1 != 0) goto L_0x001f
            java.lang.String r2 = "constants"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x001f:
            java.util.List r1 = r1.getStatesList()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r1 = r1.iterator()
            r3 = 0
            r4 = 0
        L_0x0032:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0084
            java.lang.Object r5 = r1.next()
            int r6 = r4 + 1
            if (r4 >= 0) goto L_0x0043
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x0043:
            r4 = r5
            java.lang.String r4 = (java.lang.String) r4
            java.util.List<com.forasoft.homewavvisitor.model.data.Facility> r4 = r9.facilities
            if (r4 != 0) goto L_0x004d
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x004d:
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            boolean r7 = r4 instanceof java.util.Collection
            if (r7 == 0) goto L_0x005e
            r7 = r4
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x005e
        L_0x005c:
            r4 = 0
            goto L_0x007d
        L_0x005e:
            java.util.Iterator r4 = r4.iterator()
        L_0x0062:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x005c
            java.lang.Object r7 = r4.next()
            com.forasoft.homewavvisitor.model.data.Facility r7 = (com.forasoft.homewavvisitor.model.data.Facility) r7
            java.lang.String r7 = r7.getUs_state_id()
            java.lang.String r8 = java.lang.String.valueOf(r6)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
            if (r7 == 0) goto L_0x0062
            r4 = 1
        L_0x007d:
            if (r4 == 0) goto L_0x0082
            r2.add(r5)
        L_0x0082:
            r4 = r6
            goto L_0x0032
        L_0x0084:
            java.util.List r2 = (java.util.List) r2
            java.util.Collection r2 = (java.util.Collection) r2
            r10.addAll(r2)
            android.content.Context r0 = r9.getContext()
            r1 = 2131821037(0x7f1101ed, float:1.9274806E38)
            java.lang.String r0 = r0.getString(r1)
            r10.add(r0)
            android.widget.Spinner r0 = r9.spinnerState
            com.forasoft.homewavvisitor.ui.views.AddInmateView$SpinnerAdapter r1 = new com.forasoft.homewavvisitor.ui.views.AddInmateView$SpinnerAdapter
            android.content.Context r2 = r9.getContext()
            java.lang.String r3 = "context"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.util.List r10 = (java.util.List) r10
            r1.<init>(r2, r10)
            android.widget.SpinnerAdapter r1 = (android.widget.SpinnerAdapter) r1
            r0.setAdapter(r1)
            android.widget.Spinner r0 = r9.spinnerState
            int r10 = kotlin.collections.CollectionsKt.getLastIndex(r10)
            r0.setSelection(r10)
            android.widget.Spinner r10 = r9.spinnerFacility
            com.forasoft.homewavvisitor.ui.views.AddInmateView$onFacilitiesLoaded$2 r0 = new com.forasoft.homewavvisitor.ui.views.AddInmateView$onFacilitiesLoaded$2
            r0.<init>(r9)
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            com.forasoft.homewavvisitor.extension.CommonKt.onItemSelected(r10, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.views.AddInmateView.onFacilitiesLoaded(java.util.List):void");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$SpinnerAdapter;", "T", "Landroid/widget/ArrayAdapter;", "context", "Landroid/content/Context;", "objects", "", "(Landroid/content/Context;Ljava/util/List;)V", "getCount", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AddInmateView.kt */
    public static final class SpinnerAdapter<T> extends ArrayAdapter<T> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SpinnerAdapter(Context context, List<? extends T> list) {
            super(context, R.layout.item_simple_list, list);
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(list, "objects");
        }

        public int getCount() {
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }
    }
}
