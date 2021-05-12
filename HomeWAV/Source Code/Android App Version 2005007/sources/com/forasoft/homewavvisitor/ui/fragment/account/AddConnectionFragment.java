package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.account.AddConnectionPresenter;
import com.forasoft.homewavvisitor.presentation.presenter.register.AddConnectionSignUpPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.AcView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.register.AddConnectionSignUpFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.ProvidePresenter;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J&\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u00162\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010 \u001a\u00020!H\u0017J\u0018\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0016J\b\u0010%\u001a\u00020\bH\u0016¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/AddConnectionFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/register/AddConnectionSignUpFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/AcView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "onBackPressed", "", "onClick", "", "dialog", "Landroid/content/DialogInterface;", "which", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/AddConnectionPresenter;", "showEditPhoto", "requirePhotoId", "hasPhotoId", "showSkipButton", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddConnectionFragment.kt */
public final class AddConnectionFragment extends AddConnectionSignUpFragment implements AcView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;

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

    @ProvidePresenter
    public AddConnectionPresenter providePresenter() {
        Object instance = Toothpick.openScopes(DI.SERVER_SCOPE, DI.ADD_CONNECTION_SCOPE).getInstance(AddConnectionPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …ionPresenter::class.java)");
        return (AddConnectionPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_add_connection, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        BottomNavigationViewEx bottomNavigationViewEx;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        String string = getString(R.string.label_add_inmate);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.label_add_inmate)");
        ContextKt.setTitle((Fragment) this, string);
        FragmentActivity activity = getActivity();
        if (!(activity instanceof MainActivity)) {
            activity = null;
        }
        MainActivity mainActivity = (MainActivity) activity;
        if (!(mainActivity == null || (bottomNavigationViewEx = (BottomNavigationViewEx) mainActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main)) == null)) {
            CommonKt.hide(bottomNavigationViewEx);
        }
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepHeader));
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPageExplanations));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonNext");
        button.setText(getString(R.string.label_add_connection));
        CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSkip));
    }

    public void onDestroyView() {
        BottomNavigationViewEx bottomNavigationViewEx;
        super.onDestroyView();
        FragmentActivity activity = getActivity();
        if (!(activity instanceof MainActivity)) {
            activity = null;
        }
        MainActivity mainActivity = (MainActivity) activity;
        if (!(mainActivity == null || (bottomNavigationViewEx = (BottomNavigationViewEx) mainActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main)) == null)) {
            CommonKt.show(bottomNavigationViewEx);
        }
        _$_clearFindViewByIdCache();
    }

    public void showSkipButton() {
        CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext));
        CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSkip));
    }

    public void showEditPhoto(boolean z, boolean z2) {
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.editPhotosContainer);
        if (!z || z2) {
            if (findFragmentById != null) {
                getChildFragmentManager().beginTransaction().hide(findFragmentById).commitNow();
            }
        } else if (findFragmentById == null) {
            getChildFragmentManager().beginTransaction().add((int) R.id.editPhotosContainer, (Fragment) new EditPhotosFragment()).commitNow();
        } else {
            getChildFragmentManager().beginTransaction().show(findFragmentById).commitNow();
        }
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonNext");
        button.setEnabled(!z || z2);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        getPresenter().nextStep();
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
        AddConnectionSignUpPresenter presenter = getPresenter();
        if (presenter != null) {
            ((AddConnectionPresenter) presenter).onBackPressed();
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.presenter.account.AddConnectionPresenter");
    }

    public boolean onBackPressed() {
        AddConnectionSignUpPresenter presenter = getPresenter();
        if (presenter != null) {
            ((AddConnectionPresenter) presenter).onBackPressed();
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.presenter.account.AddConnectionPresenter");
    }
}
