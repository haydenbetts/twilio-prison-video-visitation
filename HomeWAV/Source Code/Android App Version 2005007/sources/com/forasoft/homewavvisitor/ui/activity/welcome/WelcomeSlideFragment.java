package com.forasoft.homewavvisitor.ui.activity.welcome;

import air.HomeWAV.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\t\u001a\u00020\u0007H\u0002J\b\u0010\n\u001a\u00020\u0007H\u0002J\b\u0010\u000b\u001a\u00020\u0007H\u0002J\b\u0010\f\u001a\u00020\u0007H\u0002J\b\u0010\r\u001a\u00020\u0007H\u0002J\b\u0010\u000e\u001a\u00020\u0007H\u0002J\b\u0010\u000f\u001a\u00020\u0007H\u0002J\u0012\u0010\u0010\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001a\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeSlideFragment;", "Landroidx/fragment/app/Fragment;", "()V", "slide", "", "Ljava/lang/Integer;", "initAccentStripe", "", "initBackground", "initCenterImage", "initGotIt", "initHandLogo", "initSlide", "initStepper", "initSubtitle", "initTitle", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: WelcomeSlideFragment.kt */
public final class WelcomeSlideFragment extends Fragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String SLIDE = "position";
    private HashMap _$_findViewCache;
    private Integer slide;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeSlideFragment$Companion;", "", "()V", "SLIDE", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeSlideFragment;", "position", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: WelcomeSlideFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WelcomeSlideFragment newInstance(int i) {
            WelcomeSlideFragment welcomeSlideFragment = new WelcomeSlideFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(WelcomeSlideFragment.SLIDE, i + 1);
            welcomeSlideFragment.setArguments(bundle);
            return welcomeSlideFragment;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.slide = Integer.valueOf(arguments.getInt(SLIDE));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_welcome_slide, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        initSlide();
    }

    private final void initSlide() {
        initBackground();
        initCenterImage();
        initTitle();
        initSubtitle();
        initHandLogo();
        initAccentStripe();
        initStepper();
        initGotIt();
    }

    private final void initBackground() {
        Integer num = this.slide;
        if ((num != null && num.intValue() == 1) || (num != null && num.intValue() == 3)) {
            CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.background_bottom));
            CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.background_top));
        } else if (num != null && num.intValue() == 2) {
            CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.background_top));
            CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.background_bottom));
        }
    }

    private final void initCenterImage() {
        Integer num = this.slide;
        if (num != null && num.intValue() == 1) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.phone_image)).setImageResource(R.drawable.step_1_phone);
        } else if (num != null && num.intValue() == 2) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.phone_image)).setImageResource(R.drawable.step_2_phone);
        } else if (num != null && num.intValue() == 3) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.phone_image)).setImageResource(R.drawable.step_3_phone);
        }
    }

    private final void initTitle() {
        Integer num = this.slide;
        if (num != null && num.intValue() == 1) {
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(textView, "title");
            textView.setText(getString(R.string.welcome_title_1));
            TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "title");
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            Sdk27PropertiesKt.setTextColor(textView2, ContextExtensionsKt.getColorResource(requireContext, R.color.welcomeTitleGray));
        } else if (num != null && num.intValue() == 2) {
            TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "title");
            textView3.setText(getString(R.string.welcome_title_2));
            TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "title");
            Context requireContext2 = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
            Sdk27PropertiesKt.setTextColor(textView4, ContextExtensionsKt.getColorResource(requireContext2, R.color.white));
        } else if (num != null && num.intValue() == 3) {
            TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(textView5, "title");
            textView5.setText(getString(R.string.welcome_title_3));
            TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(textView6, "title");
            Context requireContext3 = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
            Sdk27PropertiesKt.setTextColor(textView6, ContextExtensionsKt.getColorResource(requireContext3, R.color.welcomeTitleGray));
        }
    }

    private final void initSubtitle() {
        Integer num = this.slide;
        if (num != null && num.intValue() == 1) {
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.subtitle);
            Intrinsics.checkExpressionValueIsNotNull(textView, "subtitle");
            textView.setText(getString(R.string.welcome_subtitle_1));
            TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.subtitle);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "subtitle");
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            Sdk27PropertiesKt.setTextColor(textView2, ContextExtensionsKt.getColorResource(requireContext, R.color.welcomeTitleGray));
        } else if (num != null && num.intValue() == 2) {
            TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.subtitle);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "subtitle");
            textView3.setText(getString(R.string.welcome_subtitle_2));
            TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.subtitle);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "subtitle");
            Context requireContext2 = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
            Sdk27PropertiesKt.setTextColor(textView4, ContextExtensionsKt.getColorResource(requireContext2, R.color.white));
        } else if (num != null && num.intValue() == 3) {
            TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.subtitle);
            Intrinsics.checkExpressionValueIsNotNull(textView5, "subtitle");
            textView5.setText(getString(R.string.welcome_subtitle_3));
            TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.subtitle);
            Intrinsics.checkExpressionValueIsNotNull(textView6, "subtitle");
            Context requireContext3 = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
            Sdk27PropertiesKt.setTextColor(textView6, ContextExtensionsKt.getColorResource(requireContext3, R.color.welcomeTitleGray));
        }
    }

    private final void initHandLogo() {
        Integer num = this.slide;
        if ((num != null && num.intValue() == 1) || (num != null && num.intValue() == 3)) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.hand_logo)).setImageResource(R.drawable.hand_icon_grey);
        } else if (num != null && num.intValue() == 2) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.hand_logo)).setImageResource(R.drawable.hand_icon_white);
        }
    }

    private final void initAccentStripe() {
        Integer num = this.slide;
        if ((num != null && num.intValue() == 1) || (num != null && num.intValue() == 3)) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.accent_stripe)).setImageResource(R.drawable.bg_green);
        } else if (num != null && num.intValue() == 2) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.accent_stripe)).setImageResource(R.drawable.bg_white);
        }
    }

    private final void initStepper() {
        WelcomeStepper welcomeStepper = (WelcomeStepper) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper);
        Integer num = this.slide;
        welcomeStepper.setStep(num != null ? num.intValue() : 1);
    }

    private final void initGotIt() {
        Integer num = this.slide;
        if (num != null && num.intValue() == 1) {
            Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.got_it_button);
            Intrinsics.checkExpressionValueIsNotNull(button, "got_it_button");
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            Sdk27PropertiesKt.setTextColor(button, ContextExtensionsKt.getColorResource(requireContext, R.color.white));
            ((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.got_it_button)).setText(R.string.skip_label);
        } else if (num != null && num.intValue() == 2) {
            Button button2 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.got_it_button);
            Intrinsics.checkExpressionValueIsNotNull(button2, "got_it_button");
            Context requireContext2 = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
            Sdk27PropertiesKt.setTextColor(button2, ContextExtensionsKt.getColorResource(requireContext2, R.color.colorAccent));
            ((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.got_it_button)).setText(R.string.skip_label);
        } else if (num != null && num.intValue() == 3) {
            Button button3 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.got_it_button);
            Intrinsics.checkExpressionValueIsNotNull(button3, "got_it_button");
            Context requireContext3 = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
            Sdk27PropertiesKt.setTextColor(button3, ContextExtensionsKt.getColorResource(requireContext3, R.color.white));
            ((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.got_it_button)).setText(R.string.start_label);
        }
        Button button4 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.got_it_button);
        Intrinsics.checkExpressionValueIsNotNull(button4, "got_it_button");
        button4.setOnClickListener(new WelcomeSlideFragment$inlined$sam$i$android_view_View_OnClickListener$0(new WelcomeSlideFragment$initGotIt$1(this)));
    }
}
