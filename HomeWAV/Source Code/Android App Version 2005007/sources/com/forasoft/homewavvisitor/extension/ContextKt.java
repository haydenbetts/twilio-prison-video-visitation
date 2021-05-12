package com.forasoft.homewavvisitor.extension;

import air.HomeWAV.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.google.android.material.snackbar.Snackbar;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u001e\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003\u001a\u0012\u0010\r\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\n\u0010\u0012\u001a\u00020\u0001*\u00020\u000b\u001a\n\u0010\u0013\u001a\u00020\u0001*\u00020\u000b\u001a\f\u0010\u0014\u001a\u00020\u0001*\u0004\u0018\u00010\u0015\u001a\f\u0010\u0014\u001a\u00020\u0001*\u0004\u0018\u00010\u000b\u001a \u0010\u0016\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u001a\u001a\f\u0010\u001b\u001a\u00020\u0001*\u0004\u0018\u00010\u000b\u001a\u0012\u0010\u001c\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u0011\u001a\u0012\u0010\u001c\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0003\u001a\n\u0010\u001f\u001a\u00020\u0001*\u00020\u000b\u001a\n\u0010 \u001a\u00020\u0001*\u00020\u000b\u001a\u0014\u0010!\u001a\u00020\u0001*\u00020\u00152\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u001a\u0014\u0010!\u001a\u00020\u0001*\u00020\u000b2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u001a\u0010\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110#*\u00020\u000f¨\u0006$"}, d2 = {"showGreenSnackbar", "", "message", "", "view", "Landroid/view/View;", "resources", "Landroid/content/res/Resources;", "showRedSnackbar", "dial", "", "Landroidx/fragment/app/Fragment;", "number", "getBitmapFromVectorDrawable", "Landroid/graphics/Bitmap;", "Landroid/content/Context;", "drawableId", "", "hideActionBack", "hideBottomNavigation", "hideKeyboard", "Landroid/app/Activity;", "initActionBack", "tollbar", "Landroidx/appcompat/widget/Toolbar;", "itemListener", "Lkotlin/Function0;", "setAjustPan", "setTitle", "stringId", "title", "showActionBack", "showBottomNavigation", "showSnackbar", "volumeObservable", "Lio/reactivex/Observable;", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: context.kt */
public final class ContextKt {
    public static final void hideKeyboard(Activity activity) {
        View view;
        if (activity != null) {
            Object systemService = activity.getSystemService("input_method");
            if (systemService != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) systemService;
                View findViewById = activity.findViewById(16908290);
                IBinder iBinder = null;
                if (!(findViewById instanceof ViewGroup)) {
                    findViewById = null;
                }
                ViewGroup viewGroup = (ViewGroup) findViewById;
                if (viewGroup != null) {
                    view = viewGroup.getChildAt(0);
                } else {
                    view = null;
                }
                if (view != null) {
                    iBinder = view.getWindowToken();
                }
                inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
    }

    public static final void setAjustPan(Fragment fragment) {
        FragmentActivity activity;
        Window window;
        if (fragment != null && (activity = fragment.getActivity()) != null && (window = activity.getWindow()) != null) {
            window.setSoftInputMode(16);
        }
    }

    public static final void hideKeyboard(Fragment fragment) {
        if (fragment != null) {
            Context context = fragment.getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Object systemService = context.getSystemService("input_method");
            if (systemService != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) systemService;
                View view = fragment.getView();
                if (view == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(view, "view!!");
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
    }

    public static final void showSnackbar(Fragment fragment, String str) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$showSnackbar");
        if (str != null) {
            View view = fragment.getView();
            if (view == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(view, "view!!");
            Resources resources = fragment.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            showGreenSnackbar(str, view, resources);
        }
    }

    public static final void showGreenSnackbar(String str, View view, Resources resources) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(resources, "resources");
        Snackbar make = Snackbar.make(view, (CharSequence) str, -1);
        Intrinsics.checkExpressionValueIsNotNull(make, "Snackbar.make(view, mess…e, Snackbar.LENGTH_SHORT)");
        View view2 = make.getView();
        Intrinsics.checkExpressionValueIsNotNull(view2, "snackbar.view");
        Sdk27PropertiesKt.setBackgroundColor(view2, resources.getColor(R.color.highlightGreenLight));
        make.show();
    }

    public static final void showRedSnackbar(String str, View view, Resources resources) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(resources, "resources");
        Snackbar make = Snackbar.make(view, (CharSequence) str, -1);
        Intrinsics.checkExpressionValueIsNotNull(make, "Snackbar.make(view, mess…e, Snackbar.LENGTH_SHORT)");
        View view2 = make.getView();
        Intrinsics.checkExpressionValueIsNotNull(view2, "snackbar.view");
        Sdk27PropertiesKt.setBackgroundColor(view2, resources.getColor(R.color.errorRed));
        make.show();
    }

    public static final void showActionBack(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$showActionBack");
        fragment.setHasOptionsMenu(true);
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayShowHomeEnabled(true);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public static final Observable<Integer> volumeObservable(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "$this$volumeObservable");
        Object systemService = context.getSystemService("audio");
        if (systemService != null) {
            Observable<Integer> create = Observable.create(new ContextKt$volumeObservable$1(context, (AudioManager) systemService));
            Intrinsics.checkExpressionValueIsNotNull(create, "Observable.create<Int> {…server(observer) })\n    }");
            return create;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
    }

    public static final boolean dial(Fragment fragment, String str) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$dial");
        Intrinsics.checkParameterIsNotNull(str, "number");
        try {
            fragment.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final void initActionBack(Fragment fragment, Toolbar toolbar, Function0<Boolean> function0) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$initActionBack");
        Intrinsics.checkParameterIsNotNull(toolbar, "tollbar");
        Intrinsics.checkParameterIsNotNull(function0, "itemListener");
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (!(supportActionBar instanceof Toolbar)) {
                supportActionBar = null;
            }
            Toolbar toolbar2 = (Toolbar) supportActionBar;
            if (toolbar2 != null) {
                toolbar2.setOnMenuItemClickListener(new ContextKt$initActionBack$$inlined$apply$lambda$1(function0));
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(true);
            }
            ActionBar supportActionBar3 = appCompatActivity.getSupportActionBar();
            if (supportActionBar3 != null) {
                supportActionBar3.setDisplayShowHomeEnabled(true);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public static final void hideActionBack(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$hideActionBack");
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayShowHomeEnabled(false);
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(false);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public static final void setTitle(Fragment fragment, int i) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$setTitle");
        String string = fragment.getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(stringId)");
        setTitle(fragment, string);
    }

    public static final void setTitle(Fragment fragment, String str) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$setTitle");
        Intrinsics.checkParameterIsNotNull(str, "title");
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) str);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public static final void showBottomNavigation(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$showBottomNavigation");
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        CommonKt.show((BottomNavigationViewEx) activity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
    }

    public static final void hideBottomNavigation(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$hideBottomNavigation");
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        CommonKt.hide((BottomNavigationViewEx) activity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
    }

    public static final Bitmap getBitmapFromVectorDrawable(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "$this$getBitmapFromVectorDrawable");
        Drawable drawableResource = ContextExtensionsKt.getDrawableResource(context, i);
        if (Build.VERSION.SDK_INT < 21) {
            drawableResource = DrawableCompat.wrap(drawableResource).mutate();
            Intrinsics.checkExpressionValueIsNotNull(drawableResource, "DrawableCompat.wrap(drawable).mutate()");
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawableResource.getIntrinsicWidth(), drawableResource.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawableResource.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawableResource.draw(canvas);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "bitmap");
        return createBitmap;
    }

    public static final void showSnackbar(Activity activity, String str) {
        Intrinsics.checkParameterIsNotNull(activity, "$this$showSnackbar");
        if (str != null) {
            View findViewById = activity.findViewById(16908290);
            View view = null;
            if (!(findViewById instanceof ViewGroup)) {
                findViewById = null;
            }
            ViewGroup viewGroup = (ViewGroup) findViewById;
            if (viewGroup != null) {
                view = viewGroup.getChildAt(0);
            }
            if (view == null) {
                Intrinsics.throwNpe();
            }
            Resources resources = activity.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            showGreenSnackbar(str, view, resources);
        }
    }
}
