package com.forasoft.homewavvisitor.ui.fragment.visits;

import air.HomeWAV.R;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.visits.MonthAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.visits.DateChooserPresenter;
import com.forasoft.homewavvisitor.presentation.view.visits.DateChooserView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.TextStyle;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 22\u00020\u00012\u00020\u00022\u00020\u0003:\u00012B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011H\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J&\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001f\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u0006H\u0016J\u001a\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\"2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010*\u001a\u00020\bH\u0007J\b\u0010+\u001a\u00020\u000eH\u0016J\u0016\u0010,\u001a\u00020\u000e2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00130.H\u0016J\u0010\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u000201H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u00063"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/DateChooserFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/DateChooserView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "menuItem", "Landroid/view/MenuItem;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/visits/DateChooserPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/visits/DateChooserPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/visits/DateChooserPresenter;)V", "hideProgress", "", "initCalendar", "schedule", "", "Lorg/threeten/bp/LocalDate;", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "installModules", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOptionsItemSelected", "item", "onViewCreated", "view", "providePresenter", "showProgress", "updateCalendar", "slots", "", "updateToolbar", "title", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DateChooserFragment.kt */
public final class DateChooserFragment extends BaseFragment implements DateChooserView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String INMATE = "inmate";
    private HashMap _$_findViewCache;
    private MenuItem menuItem;
    @InjectPresenter
    public DateChooserPresenter presenter;

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

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/DateChooserFragment$Companion;", "", "()V", "INMATE", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/visits/DateChooserFragment;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DateChooserFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DateChooserFragment newInstance(Inmate inmate) {
            Intrinsics.checkParameterIsNotNull(inmate, DateChooserFragment.INMATE);
            DateChooserFragment dateChooserFragment = new DateChooserFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(DateChooserFragment.INMATE, inmate);
            dateChooserFragment.setArguments(bundle);
            return dateChooserFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new DateChooserFragment$installModules$1(this));
    }

    public final DateChooserPresenter getPresenter() {
        DateChooserPresenter dateChooserPresenter = this.presenter;
        if (dateChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return dateChooserPresenter;
    }

    public final void setPresenter(DateChooserPresenter dateChooserPresenter) {
        Intrinsics.checkParameterIsNotNull(dateChooserPresenter, "<set-?>");
        this.presenter = dateChooserPresenter;
    }

    @ProvidePresenter
    public final DateChooserPresenter providePresenter() {
        Object instance = getScope().getInstance(DateChooserPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(DateCh…serPresenter::class.java)");
        return (DateChooserPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_date_chooser, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_calendar);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_calendar");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_calendar)).addOnScrollListener(new DateChooserFragment$onViewCreated$1(this));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_month);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_month");
        LocalDateTime now = LocalDateTime.now();
        Intrinsics.checkExpressionValueIsNotNull(now, "LocalDateTime.now()");
        textView.setText(now.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        new GravitySnapHelper(GravityCompat.START).attachToRecyclerView((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_calendar));
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        MenuItem add = menu.add(0, R.id.action_refresh, 0, R.string.label_loading);
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        add.setIcon(ContextExtensionsKt.getDrawableResource(requireContext, R.drawable.ic_refresh));
        add.setShowAsAction(2);
        this.menuItem = add;
        Drawable drawable = null;
        Drawable icon = add != null ? add.getIcon() : null;
        if (icon instanceof Animatable) {
            drawable = icon;
        }
        Animatable animatable = (Animatable) drawable;
        if (animatable != null) {
            animatable.start();
        }
        MenuItem menuItem2 = this.menuItem;
        if (menuItem2 != null) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.swipeHint);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "swipeHint");
            menuItem2.setVisible(!CommonKt.isVisible(linearLayout));
        }
        MenuItem add2 = menu.add(0, R.id.action_close, 0, R.string.label_close);
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        add2.setIcon(ContextExtensionsKt.getDrawableResource(requireContext2, R.drawable.ic_close));
        add2.setShowAsAction(2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem2) {
        Intrinsics.checkParameterIsNotNull(menuItem2, "item");
        if (menuItem2.getItemId() == R.id.action_close) {
            return onBackPressed();
        }
        return true;
    }

    public void updateToolbar(String str) {
        Intrinsics.checkParameterIsNotNull(str, "title");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Toolbar toolbar = (Toolbar) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.toolbar);
        Intrinsics.checkExpressionValueIsNotNull(toolbar, "requireActivity().toolbar");
        toolbar.setTitle((CharSequence) str);
    }

    public void initCalendar(Map<LocalDate, DaySlot> map) {
        Intrinsics.checkParameterIsNotNull(map, "schedule");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_calendar);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_calendar");
        recyclerView.setAdapter(new MonthAdapter(map, new DateChooserFragment$initCalendar$1(this)));
    }

    public void updateCalendar(List<DaySlot> list) {
        Intrinsics.checkParameterIsNotNull(list, "slots");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_calendar);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_calendar");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((MonthAdapter) adapter).addSlots(list);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.visits.MonthAdapter");
    }

    public void showProgress() {
        MenuItem menuItem2 = this.menuItem;
        Drawable drawable = null;
        Drawable icon = menuItem2 != null ? menuItem2.getIcon() : null;
        if (icon instanceof Animatable) {
            drawable = icon;
        }
        Animatable animatable = (Animatable) drawable;
        if (animatable != null) {
            animatable.start();
        }
        MenuItem menuItem3 = this.menuItem;
        if (menuItem3 != null) {
            menuItem3.setVisible(true);
        }
        CommonKt.hide((LinearLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.swipeHint));
    }

    public void hideProgress() {
        MenuItem menuItem2 = this.menuItem;
        Drawable drawable = null;
        Drawable icon = menuItem2 != null ? menuItem2.getIcon() : null;
        if (icon instanceof Animatable) {
            drawable = icon;
        }
        Animatable animatable = (Animatable) drawable;
        if (animatable != null) {
            animatable.stop();
        }
        MenuItem menuItem3 = this.menuItem;
        if (menuItem3 != null) {
            menuItem3.setVisible(false);
        }
        CommonKt.show((LinearLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.swipeHint));
    }

    public boolean onBackPressed() {
        DateChooserPresenter dateChooserPresenter = this.presenter;
        if (dateChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        dateChooserPresenter.onBackPressed();
        return true;
    }
}
