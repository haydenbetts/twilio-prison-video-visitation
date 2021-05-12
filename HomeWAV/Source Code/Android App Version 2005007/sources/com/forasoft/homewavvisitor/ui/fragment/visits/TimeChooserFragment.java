package com.forasoft.homewavvisitor.ui.fragment.visits;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.TimeSlot;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.TimeSlotsAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.visits.TimeChooserPresenter;
import com.forasoft.homewavvisitor.presentation.view.visits.TimeChooserView;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Scope;
import wseemann.media.FFmpegMediaMetadataRetriever;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 02\u00020\u00012\u00020\u00022\u00020\u0003:\u00010B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0016\u0010\u000f\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J&\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u001e\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010%\u001a\u00020\fH\u0016J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(H\u0016J\u001a\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020!2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010+\u001a\u00020\u0006H\u0007J\u0018\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\u000eH\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u00061"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/TimeChooserFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/TimeChooserView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/visits/TimeChooserPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/visits/TimeChooserPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/visits/TimeChooserPresenter;)V", "displaySelectedDay", "", "date", "", "displaySlots", "slots", "", "Lcom/forasoft/homewavvisitor/model/data/TimeSlot;", "installModules", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "showConfirmDialog", "inmate", "updateToolbar", "title", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TimeChooserFragment.kt */
public final class TimeChooserFragment extends BaseFragment implements TimeChooserView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DAY_SLOT = "day slot";
    private static final String INMATE = "inmate";
    private HashMap _$_findViewCache;
    @InjectPresenter
    public TimeChooserPresenter presenter;

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
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/TimeChooserFragment$Companion;", "", "()V", "DAY_SLOT", "", "INMATE", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/visits/TimeChooserFragment;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "daySlot", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: TimeChooserFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TimeChooserFragment newInstance(Inmate inmate, DaySlot daySlot) {
            Intrinsics.checkParameterIsNotNull(inmate, TimeChooserFragment.INMATE);
            TimeChooserFragment timeChooserFragment = new TimeChooserFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(TimeChooserFragment.INMATE, inmate);
            bundle.putParcelable(TimeChooserFragment.DAY_SLOT, daySlot);
            timeChooserFragment.setArguments(bundle);
            return timeChooserFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new TimeChooserFragment$installModules$1(this));
    }

    public final TimeChooserPresenter getPresenter() {
        TimeChooserPresenter timeChooserPresenter = this.presenter;
        if (timeChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return timeChooserPresenter;
    }

    public final void setPresenter(TimeChooserPresenter timeChooserPresenter) {
        Intrinsics.checkParameterIsNotNull(timeChooserPresenter, "<set-?>");
        this.presenter = timeChooserPresenter;
    }

    @ProvidePresenter
    public final TimeChooserPresenter providePresenter() {
        Object instance = getScope().getInstance(TimeChooserPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(TimeCh…serPresenter::class.java)");
        return (TimeChooserPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        if (viewGroup != null) {
            return ContextExtensionsKt.inflate$default(viewGroup, R.layout.fragment_time_chooser, false, 2, (Object) null);
        }
        return null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_slots);
        TimeChooserPresenter timeChooserPresenter = this.presenter;
        if (timeChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView.setAdapter(new TimeSlotsAdapter(new TimeChooserFragment$onViewCreated$1$1(timeChooserPresenter)));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_invite);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_invite");
        button.setOnClickListener(new TimeChooserFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TimeChooserFragment$onViewCreated$2(this)));
    }

    public void onDestroyView() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        MenuItem add = menu.add(R.string.label_close);
        add.setIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_close));
        add.setShowAsAction(2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        return onBackPressed();
    }

    public void updateToolbar(String str) {
        Intrinsics.checkParameterIsNotNull(str, "title");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((MainActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) str);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
    }

    public void displaySelectedDay(String str) {
        Intrinsics.checkParameterIsNotNull(str, FFmpegMediaMetadataRetriever.METADATA_KEY_DATE);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_date");
        textView.setText(str);
    }

    public void displaySlots(List<TimeSlot> list) {
        Intrinsics.checkParameterIsNotNull(list, "slots");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_slots);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_slots");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((TimeSlotsAdapter) adapter).submitList(list);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.TimeSlotsAdapter");
    }

    public void showConfirmDialog(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, INMATE);
        Intrinsics.checkParameterIsNotNull(str2, FFmpegMediaMetadataRetriever.METADATA_KEY_DATE);
        new MaterialDialog.Builder(requireContext()).title((int) R.string.label_invite_title).content((int) R.string.label_invite_body, str, str2).positiveText((int) R.string.label_send).negativeText((int) R.string.label_cancel).cancelable(false).onPositive(new TimeChooserFragment$showConfirmDialog$1(this)).build().show();
    }

    public boolean onBackPressed() {
        TimeChooserPresenter timeChooserPresenter = this.presenter;
        if (timeChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        timeChooserPresenter.onBackPressed();
        return true;
    }
}
