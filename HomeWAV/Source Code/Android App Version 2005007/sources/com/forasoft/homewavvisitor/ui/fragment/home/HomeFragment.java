package com.forasoft.homewavvisitor.ui.fragment.home;

import air.HomeWAV.R;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.home.HomePresenter;
import com.forasoft.homewavvisitor.presentation.view.home.HomeView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.urbanairship.messagecenter.Message;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 52\u00020\u00012\u00020\u00022\u00020\u0003:\u00015B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\u001a\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010 \u001a\u00020\bH\u0007J\u0016\u0010!\u001a\u00020\u00102\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016J\b\u0010%\u001a\u00020\u0010H\u0016J\u0010\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u000eH\u0016J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u000eH\u0016J\u0016\u0010)\u001a\u00020\u00102\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016J \u0010*\u001a\u00020\u00102\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u000eH\u0016J\u0010\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u000201H\u0016J\u0018\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020$2\u0006\u00104\u001a\u00020\u000eH\u0016R\u0012\u0010\u0005\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u00066"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/home/HomeFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/home/HomeView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "currentLayout", "", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/home/HomePresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/home/HomePresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/home/HomePresenter;)V", "onBackPressed", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "onViewCreated", "view", "providePresenter", "showApprovedInmates", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "showEmptyMessageCenter", "showNextVisit", "show", "showPendingInmate", "showWarningDialog", "updateMessageCenterView", "message", "Lcom/urbanairship/messagecenter/Message;", "count", "isNeedFullRerender", "updateNextVisit", "visit", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "updatePendingInmate", "inmate", "displayLink", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HomeFragment.kt */
public final class HomeFragment extends BaseFragment implements HomeView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "HomeFragment";
    private HashMap _$_findViewCache;
    private int currentLayout = R.layout.fragment_home_3;
    @InjectPresenter
    public HomePresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/home/HomeFragment$Companion;", "", "()V", "TAG", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/home/HomeFragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: HomeFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HomeFragment newInstance() {
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(new Bundle());
            return homeFragment;
        }
    }

    public final HomePresenter getPresenter() {
        HomePresenter homePresenter = this.presenter;
        if (homePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return homePresenter;
    }

    public final void setPresenter(HomePresenter homePresenter) {
        Intrinsics.checkParameterIsNotNull(homePresenter, "<set-?>");
        this.presenter = homePresenter;
    }

    @ProvidePresenter
    public final HomePresenter providePresenter() {
        Object instance = Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE).getInstance(HomePresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …omePresenter::class.java)");
        return (HomePresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(this.currentLayout, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        HomePresenter homePresenter = this.presenter;
        if (homePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        homePresenter.getNotificationsCount();
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.inmatesList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "inmatesList");
        recyclerView.setNestedScrollingEnabled(false);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkViewPending);
        Intrinsics.checkExpressionValueIsNotNull(textView, "linkViewPending");
        textView.setOnClickListener(new HomeFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HomeFragment$onViewCreated$1(this)));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkViewTutorials);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "linkViewTutorials");
        textView2.setOnClickListener(new HomeFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HomeFragment$onViewCreated$2(this)));
        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkViewFaq);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "linkViewFaq");
        textView3.setOnClickListener(new HomeFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HomeFragment$onViewCreated$3(this)));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.inmatesList);
        recyclerView2.setItemAnimator((RecyclerView.ItemAnimator) null);
        recyclerView2.setLayoutManager(new LinearLayoutManager(recyclerView2.getContext(), 1, false));
        recyclerView2.addItemDecoration(new DividerItemDecoration(recyclerView2.getContext(), 1));
        HomePresenter homePresenter2 = this.presenter;
        if (homePresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Function1 homeFragment$onViewCreated$4$1 = new HomeFragment$onViewCreated$4$1(homePresenter2);
        HomePresenter homePresenter3 = this.presenter;
        if (homePresenter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView2.setAdapter(new InmatesAdapter(homeFragment$onViewCreated$4$1, new HomeFragment$onViewCreated$4$2(homePresenter3)));
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.containerNextVisit);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "containerNextVisit");
        constraintLayout.setOnClickListener(new HomeFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HomeFragment$onViewCreated$5(this)));
    }

    public void onResume() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_home);
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(false);
            }
            HomePresenter homePresenter = this.presenter;
            if (homePresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            homePresenter.refresh();
            super.onResume();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        }
        HomePresenter homePresenter = this.presenter;
        if (homePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        homePresenter.onNotificationsClicked();
        return true;
    }

    public void updatePendingInmate(Inmate inmate, boolean z) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        if (z) {
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkViewPending));
        } else {
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkViewPending));
        }
        String full_name = inmate.getFull_name();
        if (full_name == null) {
            Intrinsics.throwNpe();
        }
        String asInitials = StringExtensionsKt.getAsInitials(full_name);
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.pendingAvatar)).setImageDrawable(ContextExtensionsKt.createTextDrawable$default(requireContext, asInitials, R.dimen.initials_size_small, 0, 0, 12, (Object) null));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.pendingName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "pendingName");
        textView.setText(inmate.getFull_name());
    }

    public void showPendingInmate(boolean z) {
        ConstraintSet constraintSet = new ConstraintSet();
        switch (this.currentLayout) {
            case R.layout.fragment_home /*2131492954*/:
                if (!z) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_1, (NestedScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scroll_root), false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate, TtmlNode.TAG_LAYOUT);
                    ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(com.forasoft.homewavvisitor.R.id.root);
                    if (constraintLayout != null) {
                        constraintSet.clone(constraintLayout);
                        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar, "progressBar");
                        constraintSet.setVisibility(R.id.progressBar, progressBar.getVisibility());
                        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty);
                        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "fl_empty");
                        constraintSet.setVisibility(R.id.fl_empty, frameLayout.getVisibility());
                        this.currentLayout = R.layout.fragment_home_1;
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                    }
                }
                break;
            case R.layout.fragment_home_1 /*2131492955*/:
                if (z) {
                    View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, (NestedScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scroll_root), false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate2, TtmlNode.TAG_LAYOUT);
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) inflate2.findViewById(com.forasoft.homewavvisitor.R.id.root);
                    if (constraintLayout2 != null) {
                        constraintSet.clone(constraintLayout2);
                        ProgressBar progressBar2 = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar2, "progressBar");
                        constraintSet.setVisibility(R.id.progressBar, progressBar2.getVisibility());
                        FrameLayout frameLayout2 = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty);
                        Intrinsics.checkExpressionValueIsNotNull(frameLayout2, "fl_empty");
                        constraintSet.setVisibility(R.id.fl_empty, frameLayout2.getVisibility());
                        this.currentLayout = R.layout.fragment_home;
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                    }
                }
                break;
            case R.layout.fragment_home_2 /*2131492956*/:
                if (!z) {
                    View inflate3 = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_3, (NestedScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scroll_root), false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate3, TtmlNode.TAG_LAYOUT);
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) inflate3.findViewById(com.forasoft.homewavvisitor.R.id.root);
                    if (constraintLayout3 != null) {
                        constraintSet.clone(constraintLayout3);
                        ProgressBar progressBar3 = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar3, "progressBar");
                        constraintSet.setVisibility(R.id.progressBar, progressBar3.getVisibility());
                        FrameLayout frameLayout3 = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty);
                        Intrinsics.checkExpressionValueIsNotNull(frameLayout3, "fl_empty");
                        constraintSet.setVisibility(R.id.fl_empty, frameLayout3.getVisibility());
                        this.currentLayout = R.layout.fragment_home_3;
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                    }
                }
                break;
            case R.layout.fragment_home_3 /*2131492957*/:
                if (z) {
                    View inflate4 = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_2, (NestedScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scroll_root), false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate4, TtmlNode.TAG_LAYOUT);
                    ConstraintLayout constraintLayout4 = (ConstraintLayout) inflate4.findViewById(com.forasoft.homewavvisitor.R.id.root);
                    if (constraintLayout4 != null) {
                        constraintSet.clone(constraintLayout4);
                        ProgressBar progressBar4 = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar4, "progressBar");
                        constraintSet.setVisibility(R.id.progressBar, progressBar4.getVisibility());
                        FrameLayout frameLayout4 = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty);
                        Intrinsics.checkExpressionValueIsNotNull(frameLayout4, "fl_empty");
                        constraintSet.setVisibility(R.id.fl_empty, frameLayout4.getVisibility());
                        this.currentLayout = R.layout.fragment_home_2;
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                    }
                }
                break;
        }
        TransitionManager.beginDelayedTransition((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root));
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root));
    }

    public void showApprovedInmates(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        CommonKt.hide((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar));
        if (list.isEmpty()) {
            CommonKt.show((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textOnline));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textOffline));
        } else {
            CommonKt.hide((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textOnline));
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textOffline));
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.inmatesList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "inmatesList");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            new Handler(Looper.getMainLooper()).post(new HomeFragment$showApprovedInmates$1((InmatesAdapter) adapter, list));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter");
    }

    public void showNextVisit(boolean z) {
        ConstraintSet constraintSet = new ConstraintSet();
        switch (this.currentLayout) {
            case R.layout.fragment_home /*2131492954*/:
                if (!z) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_2, (NestedScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scroll_root), false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate, TtmlNode.TAG_LAYOUT);
                    ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(com.forasoft.homewavvisitor.R.id.root);
                    if (constraintLayout != null) {
                        constraintSet.clone(constraintLayout);
                        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar, "progressBar");
                        constraintSet.setVisibility(R.id.progressBar, progressBar.getVisibility());
                        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty);
                        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "fl_empty");
                        constraintSet.setVisibility(R.id.fl_empty, frameLayout.getVisibility());
                        this.currentLayout = R.layout.fragment_home_2;
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                    }
                }
                break;
            case R.layout.fragment_home_1 /*2131492955*/:
                if (!z) {
                    View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_3, (NestedScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scroll_root), false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate2, TtmlNode.TAG_LAYOUT);
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) inflate2.findViewById(com.forasoft.homewavvisitor.R.id.root);
                    if (constraintLayout2 != null) {
                        constraintSet.clone(constraintLayout2);
                        ProgressBar progressBar2 = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar2, "progressBar");
                        constraintSet.setVisibility(R.id.progressBar, progressBar2.getVisibility());
                        FrameLayout frameLayout2 = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty);
                        Intrinsics.checkExpressionValueIsNotNull(frameLayout2, "fl_empty");
                        constraintSet.setVisibility(R.id.fl_empty, frameLayout2.getVisibility());
                        this.currentLayout = R.layout.fragment_home_3;
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                    }
                }
                break;
            case R.layout.fragment_home_2 /*2131492956*/:
                if (z) {
                    View inflate3 = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, (NestedScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scroll_root), false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate3, TtmlNode.TAG_LAYOUT);
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) inflate3.findViewById(com.forasoft.homewavvisitor.R.id.root);
                    if (constraintLayout3 != null) {
                        constraintSet.clone(constraintLayout3);
                        ProgressBar progressBar3 = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar3, "progressBar");
                        constraintSet.setVisibility(R.id.progressBar, progressBar3.getVisibility());
                        FrameLayout frameLayout3 = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty);
                        Intrinsics.checkExpressionValueIsNotNull(frameLayout3, "fl_empty");
                        constraintSet.setVisibility(R.id.fl_empty, frameLayout3.getVisibility());
                        this.currentLayout = R.layout.fragment_home;
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                    }
                }
                break;
            case R.layout.fragment_home_3 /*2131492957*/:
                if (z) {
                    View inflate4 = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_1, (NestedScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scroll_root), false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate4, TtmlNode.TAG_LAYOUT);
                    ConstraintLayout constraintLayout4 = (ConstraintLayout) inflate4.findViewById(com.forasoft.homewavvisitor.R.id.root);
                    if (constraintLayout4 != null) {
                        constraintSet.clone(constraintLayout4);
                        ProgressBar progressBar4 = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar4, "progressBar");
                        constraintSet.setVisibility(R.id.progressBar, progressBar4.getVisibility());
                        FrameLayout frameLayout4 = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty);
                        Intrinsics.checkExpressionValueIsNotNull(frameLayout4, "fl_empty");
                        constraintSet.setVisibility(R.id.fl_empty, frameLayout4.getVisibility());
                        this.currentLayout = R.layout.fragment_home_1;
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                    }
                }
                break;
        }
        TransitionManager.beginDelayedTransition((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root));
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root));
    }

    public void updateNextVisit(ScheduledVisit scheduledVisit) {
        Intrinsics.checkParameterIsNotNull(scheduledVisit, "visit");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_visit_date);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_visit_date");
        textView.setText(DateExtensionsKt.getAsFullTime(new Date(scheduledVisit.getTimestamp() * ((long) 1000))));
        String asInitials = StringExtensionsKt.getAsInitials(scheduledVisit.getInmate());
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_visit_inmate)).setImageDrawable(ContextExtensionsKt.createTextDrawable$default(requireContext, asInitials, R.dimen.initials_size_small, 0, 0, 12, (Object) null));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_visit_inmate);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_visit_inmate");
        textView2.setText(scheduledVisit.getInmate());
        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_place_hint);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tv_place_hint");
        textView3.setText(getResources().getString(R.string.hint_place, new Object[]{scheduledVisit.getStation(), scheduledVisit.getFacility()}));
    }

    public void showWarningDialog(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        new AlertDialog.Builder(requireContext()).setTitle((int) R.string.dialog_visibility_title).setMessage((CharSequence) getResources().getString(R.string.dialog_visibility_message, new Object[]{CollectionsKt.joinToString$default(list, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, HomeFragment$showWarningDialog$1.INSTANCE, 31, (Object) null)})).setPositiveButton((int) R.string.button_yes, (DialogInterface.OnClickListener) new HomeFragment$showWarningDialog$2(this, list)).setNegativeButton((int) R.string.button_no, (DialogInterface.OnClickListener) HomeFragment$showWarningDialog$3.INSTANCE).show();
    }

    public boolean onBackPressed() {
        HomePresenter homePresenter = this.presenter;
        if (homePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return homePresenter.onBackPressed();
    }

    public void showEmptyMessageCenter() {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.message_center_empty);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "message_center_empty");
        linearLayout.setVisibility(0);
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.message_center_view);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "message_center_view");
        constraintLayout.setVisibility(8);
    }

    public void updateMessageCenterView(Message message, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.message_center_empty);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "message_center_empty");
        linearLayout.setVisibility(8);
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.message_center_view);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "message_center_view");
        constraintLayout.setVisibility(0);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.messages_unread_count);
        Intrinsics.checkExpressionValueIsNotNull(textView, "messages_unread_count");
        textView.setText(String.valueOf(i));
        if (i == 0) {
            TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.messages_unread_count);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "messages_unread_count");
            textView2.setVisibility(8);
        } else {
            TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.messages_unread_count);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "messages_unread_count");
            textView3.setVisibility(0);
        }
        if (z) {
            ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.message_first_line);
            Intrinsics.checkExpressionValueIsNotNull(constraintLayout2, "message_first_line");
            constraintLayout2.setOnClickListener(new HomeFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HomeFragment$updateMessageCenterView$1(this)));
        }
    }

    public void onDestroyView() {
        TransitionManager.endTransitions((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root));
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
