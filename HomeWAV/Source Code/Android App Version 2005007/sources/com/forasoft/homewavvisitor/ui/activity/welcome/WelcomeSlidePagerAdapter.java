package com.forasoft.homewavvisitor.ui.activity.welcome;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeSlidePagerAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "(Landroidx/fragment/app/FragmentManager;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: WelcomeSlidePagerAdapter.kt */
public final class WelcomeSlidePagerAdapter extends FragmentStatePagerAdapter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int WELCOME_SLIDE_COUNT = 3;

    public int getCount() {
        return 3;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WelcomeSlidePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
    }

    public Fragment getItem(int i) {
        return WelcomeSlideFragment.Companion.newInstance(i);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeSlidePagerAdapter$Companion;", "", "()V", "WELCOME_SLIDE_COUNT", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: WelcomeSlidePagerAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
