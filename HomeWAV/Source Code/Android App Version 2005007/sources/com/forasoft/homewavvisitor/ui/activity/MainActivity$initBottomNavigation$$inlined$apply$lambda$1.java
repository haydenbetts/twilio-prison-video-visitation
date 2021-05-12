package com.forasoft.homewavvisitor.ui.activity;

import android.content.res.ColorStateList;
import android.view.MenuItem;
import com.forasoft.homewavvisitor.navigation.OnBottomNavigationClicked;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "item", "Landroid/view/MenuItem;", "onNavigationItemSelected", "com/forasoft/homewavvisitor/ui/activity/MainActivity$initBottomNavigation$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
final class MainActivity$initBottomNavigation$$inlined$apply$lambda$1 implements BottomNavigationView.OnNavigationItemSelectedListener {
    final /* synthetic */ ColorStateList $tintList$inlined;
    final /* synthetic */ MainActivity this$0;

    MainActivity$initBottomNavigation$$inlined$apply$lambda$1(MainActivity mainActivity, ColorStateList colorStateList) {
        this.this$0 = mainActivity;
        this.$tintList$inlined = colorStateList;
    }

    public final boolean onNavigationItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == this.this$0.currentSelectedAction) {
            return true;
        }
        this.this$0.getNotifier().notify(new OnBottomNavigationClicked(menuItem.getItemId()));
        return this.this$0.showTab(menuItem.getItemId());
    }
}
