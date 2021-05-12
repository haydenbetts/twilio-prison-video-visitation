package com.forasoft.homewavvisitor.ui.views;

import air.HomeWAV.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J)\u0010\u0014\u001a\u00020\t2!\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004R)\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/AvatarUpdaterDialog;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "receiveResult", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "code", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setResultReceiver", "f", "Factory", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AvatarUpdaterDialog.kt */
public final class AvatarUpdaterDialog extends AppCompatDialogFragment {
    public static final String CAMERA = "camera";
    public static final Factory Factory = new Factory((DefaultConstructorMarker) null);
    public static final String GALLERY = "gallery";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public Function1<? super String, Unit> receiveResult;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/AvatarUpdaterDialog$Factory;", "", "()V", "CAMERA", "", "GALLERY", "show", "Lcom/forasoft/homewavvisitor/ui/views/AvatarUpdaterDialog;", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AvatarUpdaterDialog.kt */
    public static final class Factory {
        private Factory() {
        }

        public /* synthetic */ Factory(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AvatarUpdaterDialog show(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            AvatarUpdaterDialog avatarUpdaterDialog = new AvatarUpdaterDialog();
            avatarUpdaterDialog.setStyle(1, 0);
            avatarUpdaterDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), avatarUpdaterDialog.getTag());
            return avatarUpdaterDialog;
        }
    }

    public static final /* synthetic */ Function1 access$getReceiveResult$p(AvatarUpdaterDialog avatarUpdaterDialog) {
        Function1<? super String, Unit> function1 = avatarUpdaterDialog.receiveResult;
        if (function1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("receiveResult");
        }
        return function1;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_avatar_updater, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.galleryButton)).setOnClickListener(new AvatarUpdaterDialog$onViewCreated$1(this));
        ((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cameraButton)).setOnClickListener(new AvatarUpdaterDialog$onViewCreated$2(this));
    }

    public final void setResultReceiver(Function1<? super String, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "f");
        this.receiveResult = function1;
    }
}
