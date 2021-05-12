package com.forasoft.homewavvisitor.ui.fragment.register;

import air.HomeWAV.R;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.presentation.presenter.register.SignUp1Presenter;
import com.forasoft.homewavvisitor.presentation.view.register.SignUp1View;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.AvatarUpdaterDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0016J\u0010\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!H\u0014J\b\u0010\"\u001a\u00020\u001aH\u0007J$\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\u001aH\u0007J-\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.2\u000e\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u000201002\u0006\u00102\u001a\u000203H\u0016¢\u0006\u0002\u00104J\b\u00105\u001a\u00020\u001aH\u0016J\b\u00106\u001a\u00020\u000eH\u0007J\u0012\u00107\u001a\u00020\u001a2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010:\u001a\u00020\u001aH\u0016J\u0012\u0010;\u001a\u00020\u001a2\b\u0010<\u001a\u0004\u0018\u000109H\u0016J\b\u0010=\u001a\u00020\u001aH\u0016J\b\u0010>\u001a\u00020\u001aH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006?"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/SignUp1Fragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp1View;", "()V", "avatarRequested", "", "getAvatarRequested", "()Z", "setAvatarRequested", "(Z)V", "idRequested", "getIdRequested", "setIdRequested", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp1Presenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp1Presenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp1Presenter;)V", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "setProgressDialog", "(Landroid/app/ProgressDialog;)V", "activateNextButton", "", "blockNextButton", "hideIdPhotoProgress", "hideProfileProgress", "hideProgress", "installModules", "scope", "Ltoothpick/Scope;", "onAvatarFromCamera", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onIdFromCamera", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "providePresenter", "showPhotoIdImage", "imageUri", "Landroid/net/Uri;", "showPhotoIdProgress", "showProfileImage", "profileImageUri", "showProfileProgress", "showProgress", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignUp1Fragment.kt */
public final class SignUp1Fragment extends BaseFragment implements SignUp1View {
    private HashMap _$_findViewCache;
    private boolean avatarRequested;
    private boolean idRequested;
    @InjectPresenter
    public SignUp1Presenter presenter;
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
        scope.installModules(new SignUp1Fragment$installModules$1(this));
    }

    public final SignUp1Presenter getPresenter() {
        SignUp1Presenter signUp1Presenter = this.presenter;
        if (signUp1Presenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return signUp1Presenter;
    }

    public final void setPresenter(SignUp1Presenter signUp1Presenter) {
        Intrinsics.checkParameterIsNotNull(signUp1Presenter, "<set-?>");
        this.presenter = signUp1Presenter;
    }

    @ProvidePresenter
    public final SignUp1Presenter providePresenter() {
        Object instance = getScope().getInstance(SignUp1Presenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(SignUp1Presenter::class.java)");
        return (SignUp1Presenter) instance;
    }

    public final ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    public final void setProgressDialog(ProgressDialog progressDialog2) {
        this.progressDialog = progressDialog2;
    }

    public void showProgress() {
        this.progressDialog = ProgressDialog.show(getContext(), "Please, wait", "Uploading images", true, false);
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

    public void onResume() {
        super.onResume();
        CircleImageView circleImageView = (CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageProfilePhoto);
        Intrinsics.checkExpressionValueIsNotNull(circleImageView, "imageProfilePhoto");
        circleImageView.setOnClickListener(new SignUp1Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp1Fragment$onResume$1(this)));
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imagePhotoId);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "imagePhotoId");
        imageView.setOnClickListener(new SignUp1Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp1Fragment$onResume$2(this)));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonNext");
        button.setOnClickListener(new SignUp1Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp1Fragment$onResume$3(this)));
    }

    public final void onAvatarFromCamera() {
        SignUp1Presenter signUp1Presenter = this.presenter;
        if (signUp1Presenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        signUp1Presenter.onProfileImageClicked(AvatarUpdaterDialog.CAMERA);
    }

    public final void onIdFromCamera() {
        SignUp1Presenter signUp1Presenter = this.presenter;
        if (signUp1Presenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        signUp1Presenter.onPhotoIdImageClicked(AvatarUpdaterDialog.CAMERA);
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
        SignUp1FragmentPermissionsDispatcher.onRequestPermissionsResult(this, i, iArr);
    }

    public void showProfileImage(Uri uri) {
        if (uri == null) {
            Glide.clear((View) (CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageProfilePhoto));
            CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAddPhotoIcon));
            CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageReuploadUserPhoto));
            return;
        }
        Glide.with(getContext()).load(uri).into((CircleImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageProfilePhoto));
        CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAddPhotoIcon));
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageReuploadUserPhoto));
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
        Glide.with(getContext()).load(uri).override(Integer.MIN_VALUE, Integer.MIN_VALUE).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imagePhotoId));
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
}
