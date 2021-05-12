package com.forasoft.homewavvisitor.ui.fragment.register;

import air.HomeWAV.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.adapter.AddConnectionAdapter;
import com.forasoft.homewavvisitor.presentation.adapter.DividerItemDecoration;
import com.forasoft.homewavvisitor.presentation.presenter.register.AddConnectionSignUpPresenter;
import com.forasoft.homewavvisitor.presentation.view.register.AddConnectionView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.AddInmateView;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.jetbrains.anko.support.v4.SupportIntentsKt;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u00132\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J&\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0018\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u0013H\u0016J\b\u0010*\u001a\u00020\u0013H\u0016J\u001a\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020(H\u0002J\b\u0010/\u001a\u00020\rH\u0017J\u0010\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u000202H\u0016J\u001e\u00103\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00182\f\u00104\u001a\b\u0012\u0004\u0012\u00020605H\u0016J\u0010\u00107\u001a\u00020\u00132\u0006\u0010.\u001a\u00020(H\u0016J\b\u00108\u001a\u00020\u0013H\u0016J\b\u00109\u001a\u00020\u0013H\u0016R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\r8\u0016@\u0016X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006:"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/AddConnectionSignUpFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/register/AddConnectionView;", "Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$Observer;", "Landroid/content/DialogInterface$OnClickListener;", "()V", "constants", "Lcom/forasoft/homewavvisitor/model/Constants;", "getConstants", "()Lcom/forasoft/homewavvisitor/model/Constants;", "setConstants", "(Lcom/forasoft/homewavvisitor/model/Constants;)V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/register/AddConnectionSignUpPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/register/AddConnectionSignUpPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/register/AddConnectionSignUpPresenter;)V", "hideAddConnectionLink", "", "onClick", "dialog", "Landroid/content/DialogInterface;", "which", "", "onCompletedStateChange", "completed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onFacilityChosen", "connectionPosition", "facilityId", "", "onPause", "onResume", "onViewCreated", "view", "openOrDownloadPdf", "agreementUrl", "providePresenter", "setConnectionsAdapter", "connectionAdapter", "Lcom/forasoft/homewavvisitor/presentation/adapter/AddConnectionAdapter;", "setInmatesAutocomplete", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "showFacilityAgreement", "showNextButton", "showSkipButton", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddConnectionSignUpFragment.kt */
public class AddConnectionSignUpFragment extends BaseFragment implements AddConnectionView, AddInmateView.Observer, DialogInterface.OnClickListener {
    private HashMap _$_findViewCache;
    @Inject
    public Constants constants;
    @InjectPresenter
    public AddConnectionSignUpPresenter presenter;

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

    public void showEditPhoto(boolean z, boolean z2) {
        AddConnectionView.DefaultImpls.showEditPhoto(this, z, z2);
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

    public AddConnectionSignUpPresenter getPresenter() {
        AddConnectionSignUpPresenter addConnectionSignUpPresenter = this.presenter;
        if (addConnectionSignUpPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return addConnectionSignUpPresenter;
    }

    public void setPresenter(AddConnectionSignUpPresenter addConnectionSignUpPresenter) {
        Intrinsics.checkParameterIsNotNull(addConnectionSignUpPresenter, "<set-?>");
        this.presenter = addConnectionSignUpPresenter;
    }

    @ProvidePresenter
    public AddConnectionSignUpPresenter providePresenter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(AddConnectionSignUpPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …nUpPresenter::class.java)");
        return (AddConnectionSignUpPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Toothpick.inject(this, Toothpick.openScope(DI.SERVER_SCOPE));
        StringBuilder sb = new StringBuilder();
        sb.append("onCreate: ");
        Constants constants2 = this.constants;
        if (constants2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("constants");
        }
        sb.append(constants2.getStatesList().get(0));
        Timber.d(sb.toString(), new Object[0]);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_add_connection, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.listContacts);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "listContacts");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.listContacts);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "listContacts");
        recyclerView2.setNestedScrollingEnabled(false);
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.listContacts)).addItemDecoration(new DividerItemDecoration(getContext(), 1));
    }

    public void onResume() {
        super.onResume();
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSkip);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonSkip");
        button.setOnClickListener(new AddConnectionSignUpFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AddConnectionSignUpFragment$onResume$1(this)));
        Button button2 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button2, "buttonNext");
        button2.setOnClickListener(new AddConnectionSignUpFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AddConnectionSignUpFragment$onResume$2(this)));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkAddConnection);
        Intrinsics.checkExpressionValueIsNotNull(textView, "linkAddConnection");
        textView.setOnClickListener(new AddConnectionSignUpFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AddConnectionSignUpFragment$onResume$3(this)));
    }

    public void hideAddConnectionLink() {
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkAddConnection));
    }

    public void onPause() {
        super.onPause();
        ContextKt.hideKeyboard((Fragment) this);
    }

    public void setConnectionsAdapter(AddConnectionAdapter addConnectionAdapter) {
        Intrinsics.checkParameterIsNotNull(addConnectionAdapter, "connectionAdapter");
        addConnectionAdapter.setObserver(this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.listContacts);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "listContacts");
        recyclerView.setAdapter(addConnectionAdapter);
    }

    public void onFacilityChosen(int i, String str) {
        Intrinsics.checkParameterIsNotNull(str, "facilityId");
        getPresenter().onFacilitySelected(i, str);
    }

    public void setInmatesAutocomplete(int i, List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.listContacts);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "listContacts");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((AddConnectionAdapter) adapter).setInamtesList(i, list);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.AddConnectionAdapter");
    }

    public void onCompletedStateChange(boolean z) {
        getPresenter().onCompletedStateChanged();
    }

    public void showFacilityAgreement(String str) {
        Intrinsics.checkParameterIsNotNull(str, "agreementUrl");
        String string = getString(R.string.dialog_confirm_agreement_message);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.dialo…onfirm_agreement_message)");
        CharSequence charSequence = string;
        SpannableString spannableString = new SpannableString(charSequence);
        int indexOf$default = StringsKt.indexOf$default(charSequence, "visitor agreement", 0, false, 6, (Object) null);
        spannableString.setSpan(new AddConnectionSignUpFragment$showFacilityAgreement$1(this, str), indexOf$default, indexOf$default + 17, 18);
        TextView textView = (TextView) new AlertDialog.Builder(requireContext()).setTitle((int) R.string.dialog_confirm_agreement).setPositiveButton(17039379, (DialogInterface.OnClickListener) null).setNegativeButton(17039369, (DialogInterface.OnClickListener) this).setMessage((CharSequence) spannableString).setCancelable(false).show().findViewById(16908299);
        if (textView == null) {
            Intrinsics.throwNpe();
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: private */
    public final void openOrDownloadPdf(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse("http:/" + str), "application/pdf");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        if (intent.resolveActivity(requireActivity.getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Open"));
            return;
        }
        SupportIntentsKt.browse$default(this, "https://docs.google.com/gview?url=https:/" + str, false, 2, (Object) null);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        getPresenter().onSkipClicked();
    }

    public void showNextButton() {
        CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSkip));
        CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext));
    }

    public void showSkipButton() {
        CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext));
        CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSkip));
    }
}
