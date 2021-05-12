package com.forasoft.homewavvisitor.ui.fragment.register;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import com.forasoft.homewavvisitor.R;
import com.google.android.material.badge.BadgeDrawable;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignUp4Fragment.kt */
final class SignUp4Fragment$onViewCreated$4 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ SignUp4Fragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SignUp4Fragment$onViewCreated$4(SignUp4Fragment signUp4Fragment) {
        super(1);
        this.this$0 = signUp4Fragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        PopupMenu popupMenu = new PopupMenu(this.this$0.requireContext(), (ImageView) this.this$0._$_findCachedViewById(R.id.iv_more), BadgeDrawable.BOTTOM_END);
        popupMenu.getMenu().add(air.HomeWAV.R.string.label_transfer_funds).setIcon(air.HomeWAV.R.drawable.ic_transfer);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(this) {
            final /* synthetic */ SignUp4Fragment$onViewCreated$4 this$0;

            {
                this.this$0 = r1;
            }

            public final boolean onMenuItemClick(MenuItem menuItem) {
                this.this$0.this$0.getPresenter().onTransferClicked();
                return true;
            }
        });
        Field declaredField = popupMenu.getClass().getDeclaredField("mPopup");
        Intrinsics.checkExpressionValueIsNotNull(declaredField, "menu.javaClass.getDeclaredField(\"mPopup\")");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(popupMenu);
        if (obj != null) {
            ((MenuPopupHelper) obj).setForceShowIcon(true);
            Context requireContext = this.this$0.requireContext();
            Menu menu = popupMenu.getMenu();
            if (menu != null) {
                new MenuPopupHelper(requireContext, (MenuBuilder) menu);
                popupMenu.show();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.view.menu.MenuBuilder");
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.view.menu.MenuPopupHelper");
    }
}
