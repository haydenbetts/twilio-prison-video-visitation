package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Contacts;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.account.HelpPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.HelpView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0016J\u001a\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u001e\u001a\u00020\u0006H\u0007J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\fH\u0016J\u0016\u0010$\u001a\u00020\u000e2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016J\b\u0010(\u001a\u00020\u000eH\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006)"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/HelpFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/HelpView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/HelpPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/HelpPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/HelpPresenter;)V", "onBackPressed", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "onViewCreated", "view", "providePresenter", "showContacts", "contacts", "Lcom/forasoft/homewavvisitor/model/data/Contacts;", "showProgress", "show", "showQuestions", "questions", "", "Lcom/forasoft/homewavvisitor/ui/fragment/account/Question;", "showServerError", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HelpFragment.kt */
public final class HelpFragment extends BaseFragment implements HelpView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public HelpPresenter presenter;

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

    public final HelpPresenter getPresenter() {
        HelpPresenter helpPresenter = this.presenter;
        if (helpPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return helpPresenter;
    }

    public final void setPresenter(HelpPresenter helpPresenter) {
        Intrinsics.checkParameterIsNotNull(helpPresenter, "<set-?>");
        this.presenter = helpPresenter;
    }

    @ProvidePresenter
    public final HelpPresenter providePresenter() {
        Object instance = getScope().getInstance(HelpPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(HelpPresenter::class.java)");
        return (HelpPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_help, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…t_help, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_questions);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_questions");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        HelpPresenter helpPresenter = this.presenter;
        if (helpPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        helpPresenter.getNotificationsCount();
    }

    public void onDestroyView() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onResume() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_help);
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(true);
            }
            Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_contact);
            Intrinsics.checkExpressionValueIsNotNull(button, "btn_contact");
            button.setEnabled(true);
            super.onResume();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            HelpPresenter helpPresenter = this.presenter;
            if (helpPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            helpPresenter.onBackPressed();
            return true;
        } else if (itemId != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            HelpPresenter helpPresenter2 = this.presenter;
            if (helpPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            helpPresenter2.onNotificationsClicked();
            return true;
        }
    }

    public void showQuestions(List<Question> list) {
        Intrinsics.checkParameterIsNotNull(list, "questions");
        Iterable<Question> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Question question : iterable) {
            String title = question.getTitle();
            Intrinsics.checkExpressionValueIsNotNull(title, "it.title");
            arrayList.add(new Question(title, question.getAnswer()));
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_questions);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_questions");
        recyclerView.setAdapter(new QuestionsAdapter((List) arrayList));
    }

    public void showContacts(Contacts contacts) {
        Intrinsics.checkParameterIsNotNull(contacts, "contacts");
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_contact);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_contact");
        button.setOnClickListener(new HelpFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HelpFragment$showContacts$1(this, contacts)));
    }

    public void showProgress(boolean z) {
        if (z) {
            CommonKt.hide((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_questions));
            CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_contact));
            CommonKt.show((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar));
            return;
        }
        CommonKt.show((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_questions));
        CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_contact));
        CommonKt.hide((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar));
    }

    public void showServerError() {
        super.showServerError();
        CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_contact));
    }

    public boolean onBackPressed() {
        HelpPresenter helpPresenter = this.presenter;
        if (helpPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return helpPresenter.onBackPressed();
    }
}
