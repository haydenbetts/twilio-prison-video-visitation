package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.account.AccountPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.AccountView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.stripe.android.view.ShippingInfoWidget;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u000eH\u0016J\u001a\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010&\u001a\u00020\bH\u0007J\u0010\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u0010H\u0016J\u0010\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u0010H\u0016J\u0010\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u0013H\u0016J\u0010\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u0010H\u0016J\u0010\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u0010H\u0016J\u0010\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u0010H\u0016J\u0010\u00103\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\u0010H\u0016J\b\u00105\u001a\u00020\u000eH\u0016J\b\u00106\u001a\u00020\u000eH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u00067"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/AccountFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/AccountView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "dialog", "Landroidx/appcompat/app/AlertDialog;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/AccountPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/AccountPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/AccountPresenter;)V", "createAvatarFromName", "", "name", "", "enableEditPhoto", "enable", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "kotlin.jvm.PlatformType", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "onViewCreated", "view", "providePresenter", "setAvatarUrl", "avatarUrl", "setEmail", "email", "setEmailSubscription", "isEnabled", "setLocation", "location", "setName", "fullName", "setPhone", "phone", "setPin", "pin", "showSubscribeDialog", "showUnsubscribeDialog", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AccountFragment.kt */
public final class AccountFragment extends BaseFragment implements AccountView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    private AlertDialog dialog;
    @InjectPresenter
    public AccountPresenter presenter;

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

    public final AccountPresenter getPresenter() {
        AccountPresenter accountPresenter = this.presenter;
        if (accountPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return accountPresenter;
    }

    public final void setPresenter(AccountPresenter accountPresenter) {
        Intrinsics.checkParameterIsNotNull(accountPresenter, "<set-?>");
        this.presenter = accountPresenter;
    }

    @ProvidePresenter
    public final AccountPresenter providePresenter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(AccountPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …untPresenter::class.java)");
        return (AccountPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_account, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        AccountPresenter accountPresenter = this.presenter;
        if (accountPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        accountPresenter.getNotificationsCount();
    }

    public void onResume() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_account);
            }
            super.onResume();
            AccountPresenter accountPresenter = this.presenter;
            if (accountPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            accountPresenter.onResume();
            ContextKt.hideActionBack(this);
            CircleImageView circleImageView = (CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle);
            Intrinsics.checkExpressionValueIsNotNull(circleImageView, "imageAvatarCircle");
            circleImageView.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$1(this)));
            ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarText);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "imageAvatarText");
            imageView.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$2(this)));
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonLogOut);
            Intrinsics.checkExpressionValueIsNotNull(textView, "buttonLogOut");
            textView.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$3(this)));
            TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonManageFunds);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "buttonManageFunds");
            textView2.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$4(this)));
            TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkEditAccount);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "linkEditAccount");
            textView3.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$5(this)));
            TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonPaymentMethods);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "buttonPaymentMethods");
            textView4.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$6(this)));
            TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNotifications);
            Intrinsics.checkExpressionValueIsNotNull(textView5, "buttonNotifications");
            textView5.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$7(this)));
            TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonRequestRefund);
            Intrinsics.checkExpressionValueIsNotNull(textView6, "buttonRequestRefund");
            textView6.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$8(this)));
            TextView textView7 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonHistory);
            Intrinsics.checkExpressionValueIsNotNull(textView7, "buttonHistory");
            textView7.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$9(this)));
            TextView textView8 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonReportBug);
            Intrinsics.checkExpressionValueIsNotNull(textView8, "buttonReportBug");
            textView8.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$10(this)));
            TextView textView9 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonHelp);
            Intrinsics.checkExpressionValueIsNotNull(textView9, "buttonHelp");
            textView9.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$11(this)));
            TextView textView10 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonTerms);
            Intrinsics.checkExpressionValueIsNotNull(textView10, "buttonTerms");
            textView10.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$12(this)));
            TextView textView11 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonTest);
            Intrinsics.checkExpressionValueIsNotNull(textView11, "buttonTest");
            textView11.setOnClickListener(new AccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AccountFragment$onResume$13(this)));
            ((SwitchCompat) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.swEmailSubscription)).setOnClickListener(new AccountFragment$onResume$14(this));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void onDestroyView() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        }
        AccountPresenter accountPresenter = this.presenter;
        if (accountPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        accountPresenter.onNotificationsClicked();
        return true;
    }

    public boolean onBackPressed() {
        AccountPresenter accountPresenter = this.presenter;
        if (accountPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return accountPresenter.onBackPressed();
    }

    public void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "fullName");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textName");
        textView.setText(str);
    }

    public void setPhone(String str) {
        Intrinsics.checkParameterIsNotNull(str, ShippingInfoWidget.PHONE_FIELD);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhone);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textPhone");
        textView.setText(str);
    }

    public void setEmail(String str) {
        Intrinsics.checkParameterIsNotNull(str, "email");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmail);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textEmail");
        textView.setText(str);
    }

    public void setPin(String str) {
        Intrinsics.checkParameterIsNotNull(str, "pin");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPin);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textPin");
        textView.setText("PIN: " + str);
    }

    public void setAvatarUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "avatarUrl");
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarText);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "imageAvatarText");
        imageView.setVisibility(4);
        CommonKt.show((CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle));
        Glide.with(getContext()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL).into((CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle));
    }

    public void setEmailSubscription(boolean z) {
        SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.swEmailSubscription);
        Intrinsics.checkExpressionValueIsNotNull(switchCompat, "swEmailSubscription");
        switchCompat.setChecked(z);
    }

    public void setLocation(String str) {
        Intrinsics.checkParameterIsNotNull(str, "location");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textLocation);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textLocation");
        textView.setText(str);
    }

    public void createAvatarFromName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        CircleImageView circleImageView = (CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle);
        Intrinsics.checkExpressionValueIsNotNull(circleImageView, "imageAvatarCircle");
        circleImageView.setVisibility(4);
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarText));
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarText)).setImageDrawable(ContextExtensionsKt.createTextDrawable$default(requireContext, StringExtensionsKt.getAsInitials(str), 0, 0, 0, 14, (Object) null));
    }

    public void enableEditPhoto(boolean z) {
        if (z) {
            CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageNewAvatar));
        } else {
            CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageNewAvatar));
        }
        CircleImageView circleImageView = (CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle);
        Intrinsics.checkExpressionValueIsNotNull(circleImageView, "imageAvatarCircle");
        circleImageView.setEnabled(z);
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarText);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "imageAvatarText");
        imageView.setEnabled(z);
    }

    public void showSubscribeDialog() {
        this.dialog = new AlertDialog.Builder(requireContext()).setTitle((int) R.string.mailchimp_subscribe_dialog_title).setMessage((int) R.string.mailchimp_subscribe_dialog_message).setPositiveButton((int) R.string.label_ok, (DialogInterface.OnClickListener) new AccountFragment$showSubscribeDialog$1(this)).setCancelable(false).show();
    }

    public void showUnsubscribeDialog() {
        this.dialog = new AlertDialog.Builder(requireContext()).setTitle((int) R.string.mailchimp_unsubscribe_dialog_title).setMessage((int) R.string.mailchimp_unsubscribe_dialog_message).setPositiveButton((int) R.string.button_yes, (DialogInterface.OnClickListener) new AccountFragment$showUnsubscribeDialog$1(this)).setNegativeButton((int) R.string.button_no, (DialogInterface.OnClickListener) new AccountFragment$showUnsubscribeDialog$2(this)).setCancelable(false).show();
    }
}
