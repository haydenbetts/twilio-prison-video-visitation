package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.account.EditPhotosPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.EditPhotosView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.AvatarUpdaterDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0016J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\u0012\u0010 \u001a\u00020\u00182\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0012\u0010#\u001a\u00020\u00182\b\u0010$\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010%\u001a\u00020\u0018H\u0007J\b\u0010&\u001a\u00020\u0006H\u0016J$\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\u0018H\u0007J\u0010\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020\u0018H\u0016J-\u00104\u001a\u00020\u00182\u0006\u00105\u001a\u0002062\u000e\u00107\u001a\n\u0012\u0006\b\u0001\u0012\u00020\"082\u0006\u00109\u001a\u00020:H\u0016¢\u0006\u0002\u0010;J\b\u0010<\u001a\u00020\u0018H\u0016J\u001a\u0010=\u001a\u00020\u00182\u0006\u0010>\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010?\u001a\u00020\u0010H\u0007J\b\u0010@\u001a\u00020\u0018H\u0016J\u0012\u0010A\u001a\u00020\u00182\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\b\u0010D\u001a\u00020\u0018H\u0016J\u0012\u0010E\u001a\u00020\u00182\b\u0010F\u001a\u0004\u0018\u00010CH\u0016J\b\u0010G\u001a\u00020\u0018H\u0016J\b\u0010H\u001a\u00020\u0018H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/EditPhotosFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/EditPhotosView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "avatarRequested", "", "getAvatarRequested", "()Z", "setAvatarRequested", "(Z)V", "idRequested", "getIdRequested", "setIdRequested", "isNestedFragment", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/EditPhotosPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/EditPhotosPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/EditPhotosPresenter;)V", "progressDialog", "Landroid/app/ProgressDialog;", "activateNextButton", "", "blockNextButton", "hideIdPhotoProgress", "hideProfileProgress", "hideProgress", "installModules", "scope", "Ltoothpick/Scope;", "loadPhotoIdImage", "photoIdUrl", "", "loadProfileImage", "photoProfileUrl", "onAvatarFromCamera", "onBackPressed", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onIdFromCamera", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onViewCreated", "view", "providePresenter", "showApproveDialog", "showPhotoIdImage", "imageUri", "Landroid/net/Uri;", "showPhotoIdProgress", "showProfileImage", "profileImageUri", "showProfileProgress", "showProgress", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EditPhotosFragment.kt */
public final class EditPhotosFragment extends BaseFragment implements EditPhotosView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    private boolean avatarRequested;
    private boolean idRequested;
    private boolean isNestedFragment;
    @InjectPresenter
    public EditPhotosPresenter presenter;
    private ProgressDialog progressDialog;

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

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new EditPhotosFragment$installModules$1(this));
    }

    public final EditPhotosPresenter getPresenter() {
        EditPhotosPresenter editPhotosPresenter = this.presenter;
        if (editPhotosPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return editPhotosPresenter;
    }

    public final void setPresenter(EditPhotosPresenter editPhotosPresenter) {
        Intrinsics.checkParameterIsNotNull(editPhotosPresenter, "<set-?>");
        this.presenter = editPhotosPresenter;
    }

    @ProvidePresenter
    public final EditPhotosPresenter providePresenter() {
        Object instance = getScope().getInstance(EditPhotosPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(EditPhotosPresenter::class.java)");
        return (EditPhotosPresenter) instance;
    }

    public void showProgress() {
        this.progressDialog = ProgressDialog.show(getContext(), "Please, wait", "Uploading images", true, true);
    }

    public void hideProgress() {
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 != null) {
            progressDialog2.hide();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_sign_up1, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…gn_up1, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonNext");
        button.setText(getString(R.string.label_submit_for_approval));
        ViewParent parent = view.getParent();
        if (parent != null) {
            this.isNestedFragment = ((ViewGroup) parent).getId() == R.id.editPhotosContainer;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    public void onResume() {
        super.onResume();
        EditPhotosPresenter editPhotosPresenter = this.presenter;
        if (editPhotosPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        editPhotosPresenter.setExitOnSuccess(!this.isNestedFragment);
        if (!this.isNestedFragment) {
            ContextKt.setTitle((Fragment) this, "Edit Image & ID");
            ContextKt.showActionBack(this);
        }
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepHeader));
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textAccountInfoExplanations));
        CircleImageView circleImageView = (CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageProfilePhoto);
        Intrinsics.checkExpressionValueIsNotNull(circleImageView, "imageProfilePhoto");
        circleImageView.setOnClickListener(new EditPhotosFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditPhotosFragment$onResume$1(this)));
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imagePhotoId);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "imagePhotoId");
        imageView.setOnClickListener(new EditPhotosFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditPhotosFragment$onResume$2(this)));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonNext");
        button.setOnClickListener(new EditPhotosFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditPhotosFragment$onResume$3(this)));
    }

    public final void onAvatarFromCamera() {
        EditPhotosPresenter editPhotosPresenter = this.presenter;
        if (editPhotosPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        editPhotosPresenter.onProfileImageClicked(AvatarUpdaterDialog.CAMERA);
    }

    public final void onIdFromCamera() {
        EditPhotosPresenter editPhotosPresenter = this.presenter;
        if (editPhotosPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        editPhotosPresenter.onPhotoIdImageClicked(AvatarUpdaterDialog.CAMERA);
    }

    public final boolean getAvatarRequested() {
        return this.avatarRequested;
    }

    public final void setAvatarRequested(boolean z) {
        this.avatarRequested = z;
    }

    public final boolean getIdRequested() {
        return this.idRequested;
    }

    public final void setIdRequested(boolean z) {
        this.idRequested = z;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        EditPhotosFragmentPermissionsDispatcher.onRequestPermissionsResult(this, i, iArr);
    }

    public void onPause() {
        super.onPause();
        if (!this.isNestedFragment) {
            ContextKt.hideActionBack(this);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        EditPhotosPresenter editPhotosPresenter = this.presenter;
        if (editPhotosPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return editPhotosPresenter.onBackPressed();
    }

    public void showProfileImage(Uri uri) {
        if (uri == null) {
            Glide.clear((View) (CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageProfilePhoto));
            CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAddPhotoIcon));
            CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageReuploadUserPhoto));
            return;
        }
        Glide.with(getContext()).load(uri).error((int) R.drawable.background_photo_placeholder).listener(new EditPhotosFragment$showProfileImage$1(this)).into((CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageProfilePhoto));
    }

    public void activateNextButton() {
        CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext));
    }

    public void showPhotoIdImage(Uri uri) {
        if (uri == null) {
            Glide.with(getContext()).load(Integer.valueOf(R.drawable.background_photo_id_placeholder)).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imagePhotoId));
            CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageReuploadPhotoId));
            return;
        }
        Glide.with(getContext()).load(uri).override(Integer.MIN_VALUE, Integer.MIN_VALUE).error((int) R.drawable.background_photo_id_placeholder).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imagePhotoId));
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageReuploadPhotoId));
    }

    public void hideProfileProgress() {
        CommonKt.hide((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressProfileImage));
    }

    public void showProfileProgress() {
        CommonKt.show((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressProfileImage));
    }

    public void blockNextButton() {
        CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext));
    }

    public void hideIdPhotoProgress() {
        CommonKt.hide((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressIdImage));
    }

    public void showPhotoIdProgress() {
        CommonKt.show((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressIdImage));
    }

    public void loadPhotoIdImage(String str) {
        if (str != null) {
            Glide.with(getContext()).load(str).diskCacheStrategy(DiskCacheStrategy.RESULT).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imagePhotoId));
        }
    }

    public void loadProfileImage(String str) {
        if (str != null) {
            Glide.with(getContext()).load(str).diskCacheStrategy(DiskCacheStrategy.RESULT).into((CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageProfilePhoto));
        }
    }

    public void showApproveDialog() {
        new AlertDialog.Builder(getContext()).setTitle("Images Must Be Approved").setMessage("All account images will need to be approved before you can continue visiting with your connections.").setPositiveButton("OK", EditPhotosFragment$showApproveDialog$1.INSTANCE).setCancelable(false).create().show();
    }

    public boolean onBackPressed() {
        EditPhotosPresenter editPhotosPresenter = this.presenter;
        if (editPhotosPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return editPhotosPresenter.onBackPressed();
    }
}
