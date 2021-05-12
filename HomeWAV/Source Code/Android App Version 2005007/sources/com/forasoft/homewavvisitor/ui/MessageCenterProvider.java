package com.forasoft.homewavvisitor.ui;

import air.HomeWAV.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ActionProvider;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/MessageCenterProvider;", "Landroidx/core/view/ActionProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "notifications", "", "onClickListener", "Lkotlin/Function0;", "", "getOnClickListener", "()Lkotlin/jvm/functions/Function0;", "setOnClickListener", "(Lkotlin/jvm/functions/Function0;)V", "view", "Landroid/view/View;", "onCreateActionView", "updateSize", "size", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MessageCenterProvider.kt */
public final class MessageCenterProvider extends ActionProvider {
    private int notifications;
    private Function0<Unit> onClickListener;
    private View view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MessageCenterProvider(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final Function0<Unit> getOnClickListener() {
        return this.onClickListener;
    }

    public final void setOnClickListener(Function0<Unit> function0) {
        this.onClickListener = function0;
    }

    public View onCreateActionView() {
        if (this.view == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_message_center_icon, (ViewGroup) null);
            this.view = inflate;
            if (inflate == null) {
                Intrinsics.throwNpe();
            }
            inflate.setOnClickListener(new MessageCenterProvider$onCreateActionView$1(this));
        }
        updateSize(this.notifications);
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwNpe();
        }
        return view2;
    }

    public final void updateSize(int i) {
        this.notifications = i;
        View view2 = this.view;
        if (!(view2 instanceof ViewGroup)) {
            view2 = null;
        }
        ViewGroup viewGroup = (ViewGroup) view2;
        if (viewGroup != null) {
            int color = ContextCompat.getColor(viewGroup.getContext(), i == 0 ? R.color.white : R.color.highlightGreenLight);
            View view3 = viewGroup;
            ImageView imageView = (ImageView) view3.findViewById(com.forasoft.homewavvisitor.R.id.iv_notification);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "iv_notification");
            DrawableCompat.setTint(imageView.getDrawable(), color);
            if (i == 0) {
                CommonKt.hide((TextView) view3.findViewById(com.forasoft.homewavvisitor.R.id.tv_notification));
            } else {
                CommonKt.show((TextView) view3.findViewById(com.forasoft.homewavvisitor.R.id.tv_notification));
            }
            TextView textView = (TextView) view3.findViewById(com.forasoft.homewavvisitor.R.id.tv_notification);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_notification");
            textView.setText(String.valueOf(i));
        }
    }
}
