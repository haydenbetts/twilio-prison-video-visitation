package com.forasoft.homewavvisitor.ui.fragment.conversations;

import air.HomeWAV.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.Media;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.gallery.MediaAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.conversations.MediaChooserPresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.MediaChooserView;
import com.forasoft.homewavvisitor.ui.GridSpacingItemDecoration;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 )2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001)B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0006H\u0016J\b\u0010\"\u001a\u00020\u000eH\u0016J\b\u0010#\u001a\u00020\u000eH\u0016J\b\u0010$\u001a\u00020\bH\u0007J\u0016\u0010%\u001a\u00020\u000e2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006*"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/MediaChooserFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/conversations/MediaChooserView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "btnSend", "Landroid/view/MenuItem;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/MediaChooserPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/MediaChooserPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/MediaChooserPresenter;)V", "installModules", "", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOptionsItemSelected", "item", "onPause", "onResume", "providePresenter", "showMedia", "media", "", "Lcom/forasoft/homewavvisitor/model/Media;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MediaChooserFragment.kt */
public final class MediaChooserFragment extends BaseFragment implements MediaChooserView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String INMATE_ID = "inmate id";
    private static final String MEDIA_LOADER = "media loader";
    public static final String MEDIA_STORE_MEDIA_LOADER = "MediaStoreMediaLoader";
    public static final String S3_MEDIA_LOADER = "S3MediaLoader";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public MenuItem btnSend;
    @InjectPresenter
    public MediaChooserPresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/MediaChooserFragment$Companion;", "", "()V", "INMATE_ID", "", "MEDIA_LOADER", "MEDIA_STORE_MEDIA_LOADER", "S3_MEDIA_LOADER", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/MediaChooserFragment;", "inmateId", "mediaLoader", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MediaChooserFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MediaChooserFragment newInstance(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "mediaLoader");
            MediaChooserFragment mediaChooserFragment = new MediaChooserFragment();
            Bundle bundle = new Bundle();
            bundle.putString(MediaChooserFragment.INMATE_ID, str);
            bundle.putString(MediaChooserFragment.MEDIA_LOADER, str2);
            mediaChooserFragment.setArguments(bundle);
            return mediaChooserFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new MediaChooserFragment$installModules$1(this));
    }

    public final MediaChooserPresenter getPresenter() {
        MediaChooserPresenter mediaChooserPresenter = this.presenter;
        if (mediaChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return mediaChooserPresenter;
    }

    public final void setPresenter(MediaChooserPresenter mediaChooserPresenter) {
        Intrinsics.checkParameterIsNotNull(mediaChooserPresenter, "<set-?>");
        this.presenter = mediaChooserPresenter;
    }

    @ProvidePresenter
    public final MediaChooserPresenter providePresenter() {
        Object instance = getScope().getInstance(MediaChooserPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(MediaC…serPresenter::class.java)");
        return (MediaChooserPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_media_chooser, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…hooser, container, false)");
        return inflate;
    }

    public void onResume() {
        super.onResume();
        ContextKt.setTitle((Fragment) this, (int) R.string.label_gallery);
        ContextKt.showActionBack(this);
        ContextKt.hideBottomNavigation(this);
    }

    public void onPause() {
        super.onPause();
        ContextKt.hideActionBack(this);
        ContextKt.showBottomNavigation(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        MenuItem add = menu.add(0, R.id.btn_send, 0, R.string.label_message_send);
        this.btnSend = add;
        if (add != null) {
            add.setShowAsAction(2);
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.icon_send);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        DrawableCompat.setTint(drawable, -1);
        MenuItem menuItem = this.btnSend;
        if (menuItem != null) {
            menuItem.setIcon(drawable);
        }
        MenuItem menuItem2 = this.btnSend;
        if (menuItem2 != null) {
            menuItem2.setVisible(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            MediaChooserPresenter mediaChooserPresenter = this.presenter;
            if (mediaChooserPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            mediaChooserPresenter.onBackPressed();
            return true;
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            MediaAdapter mediaAdapter = (MediaAdapter) adapter;
            Media media = (Media) ((List) mediaAdapter.getItems()).get(mediaAdapter.getSelectedItemPosition());
            MediaChooserPresenter mediaChooserPresenter2 = this.presenter;
            if (mediaChooserPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            mediaChooserPresenter2.onSendClicked(media);
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.gallery.MediaAdapter");
    }

    public void showMedia(List<? extends Media> list) {
        Intrinsics.checkParameterIsNotNull(list, "media");
        MediaAdapter mediaAdapter = new MediaAdapter();
        mediaAdapter.setOnItemSelectedListener(new MediaChooserFragment$showMedia$1(this, mediaAdapter));
        mediaAdapter.setItems(list);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView)).addItemDecoration(new GridSpacingItemDecoration(3, getResources().getDimensionPixelSize(R.dimen.small_offset), false, 0));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "recyclerView");
        recyclerView2.setAdapter(mediaAdapter);
    }

    public boolean onBackPressed() {
        MediaChooserPresenter mediaChooserPresenter = this.presenter;
        if (mediaChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        mediaChooserPresenter.onBackPressed();
        return true;
    }
}
