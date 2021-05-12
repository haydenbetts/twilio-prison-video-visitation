package com.forasoft.homewavvisitor.ui.fragment.conversations;

import air.HomeWAV.R;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.VideoView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.toothpick.qualifier.StreamUrl;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import ru.terrakok.cicerone.Router;
import toothpick.Scope;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 #2\u00020\u00012\u00020\u0002:\u0001#B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0005H\u0016J\u0012\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J&\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010 \u001a\u00020\u0013H\u0016J\u001a\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u001b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006$"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/VideoViewFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "isShownVideoIssues", "", "router", "Lru/terrakok/cicerone/Router;", "getRouter", "()Lru/terrakok/cicerone/Router;", "setRouter", "(Lru/terrakok/cicerone/Router;)V", "streamUrl", "", "getStreamUrl", "()Ljava/lang/String;", "setStreamUrl", "(Ljava/lang/String;)V", "installModules", "", "scope", "Ltoothpick/Scope;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VideoViewFragment.kt */
public final class VideoViewFragment extends BaseFragment implements OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String STREAM_URL = "stream url";
    private HashMap _$_findViewCache;
    private boolean isShownVideoIssues;
    @Inject
    public Router router;
    @Inject
    @StreamUrl
    public String streamUrl;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/VideoViewFragment$Companion;", "", "()V", "STREAM_URL", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/VideoViewFragment;", "streamUrl", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: VideoViewFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VideoViewFragment newInstance(String str) {
            Intrinsics.checkParameterIsNotNull(str, "streamUrl");
            VideoViewFragment videoViewFragment = new VideoViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(VideoViewFragment.STREAM_URL, str);
            videoViewFragment.setArguments(bundle);
            return videoViewFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new VideoViewFragment$installModules$1(this));
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

    public final String getStreamUrl() {
        String str = this.streamUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamUrl");
        }
        return str;
    }

    public final void setStreamUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.streamUrl = str;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Toothpick.inject(this, getScope());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_video_view, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        String str;
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
            ImageButton imageButton = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.back_button);
            Intrinsics.checkExpressionValueIsNotNull(imageButton, "back_button");
            imageButton.setOnClickListener(new VideoViewFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VideoViewFragment$onViewCreated$2(this)));
            String str2 = this.streamUrl;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("streamUrl");
            }
            if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "https", false, 2, (Object) null)) {
                str = this.streamUrl;
                if (str == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("streamUrl");
                }
            } else {
                String str3 = this.streamUrl;
                if (str3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("streamUrl");
                }
                str = StringsKt.replace$default(str3, "http", "https", false, 4, (Object) null);
            }
            ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).setVideoURI(Uri.parse(str));
            ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).setOnCompletionListener(new VideoViewFragment$onViewCreated$3(this));
            ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).setOnPreparedListener(new VideoViewFragment$onViewCreated$4(this));
            ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).setOnErrorListener(new VideoViewFragment$onViewCreated$5(this));
            ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).start();
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
