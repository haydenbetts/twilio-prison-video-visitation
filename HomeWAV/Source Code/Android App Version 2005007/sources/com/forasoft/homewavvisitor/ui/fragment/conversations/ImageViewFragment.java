package com.forasoft.homewavvisitor.ui.fragment.conversations;

import air.HomeWAV.R;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Router;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u00012\u00020\u0002:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\u001a\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ImageViewFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "router", "Lru/terrakok/cicerone/Router;", "getRouter", "()Lru/terrakok/cicerone/Router;", "setRouter", "(Lru/terrakok/cicerone/Router;)V", "onBackPressed", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImageViewFragment.kt */
public final class ImageViewFragment extends BaseFragment implements OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String IMAGE_URI = "image_uri";
    private HashMap _$_findViewCache;
    @Inject
    public Router router;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ImageViewFragment$Companion;", "", "()V", "IMAGE_URI", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ImageViewFragment;", "imageUri", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ImageViewFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ImageViewFragment newInstance(String str) {
            Intrinsics.checkParameterIsNotNull(str, "imageUri");
            ImageViewFragment imageViewFragment = new ImageViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ImageViewFragment.IMAGE_URI, str);
            imageViewFragment.setArguments(bundle);
            return imageViewFragment;
        }
    }

    public final Router getRouter() {
        Router router2 = this.router;
        if (router2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        return router2;
    }

    public final void setRouter(Router router2) {
        Intrinsics.checkParameterIsNotNull(router2, "<set-?>");
        this.router = router2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Toothpick.inject(this, getScope());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_image_view, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        CommonKt.hide((Toolbar) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.toolbar));
        FrameLayout frameLayout = (FrameLayout) requireActivity().findViewById(R.id.container);
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "container");
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (layoutParams != null) {
            ((ConstraintLayout.LayoutParams) layoutParams).topMargin = 0;
            ImageButton imageButton = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.backButton);
            Intrinsics.checkExpressionValueIsNotNull(imageButton, "backButton");
            imageButton.setOnClickListener(new ImageViewFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ImageViewFragment$onViewCreated$2(this)));
            Bundle arguments = getArguments();
            Glide.with(getContext()).load(arguments != null ? arguments.getString(IMAGE_URI) : null).listener(new ImageViewFragment$onViewCreated$3(this)).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageView));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    public void onDestroyView() {
        FrameLayout frameLayout = (FrameLayout) requireActivity().findViewById(R.id.container);
        TypedValue typedValue = new TypedValue();
        FragmentActivity requireActivity = requireActivity();
        CommonKt.show((Toolbar) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.toolbar));
        requireActivity.getTheme().resolveAttribute(16843499, typedValue, true);
        int i = typedValue.data;
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        int complexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(i, resources.getDisplayMetrics());
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "container");
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (layoutParams != null) {
            ((ConstraintLayout.LayoutParams) layoutParams).topMargin = complexToDimensionPixelSize;
            super.onDestroyView();
            _$_clearFindViewByIdCache();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    public boolean onBackPressed() {
        Router router2 = this.router;
        if (router2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        router2.exit();
        return true;
    }
}
