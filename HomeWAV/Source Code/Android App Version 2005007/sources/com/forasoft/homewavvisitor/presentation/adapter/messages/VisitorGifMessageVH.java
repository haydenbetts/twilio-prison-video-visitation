package com.forasoft.homewavvisitor.presentation.adapter.messages;

import air.HomeWAV.R;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.MessageStatus;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\bR\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/VisitorGifMessageVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "user", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onClickListener", "Lkotlin/Function1;", "Lcom/forasoft/homewavvisitor/model/data/Message;", "", "(Landroid/view/View;Lcom/forasoft/homewavvisitor/model/data/auth/User;Lkotlin/jvm/functions/Function1;)V", "bind", "message", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitorGifMessageVH.kt */
public final class VisitorGifMessageVH extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public final Function1<Message, Unit> onClickListener;
    private final User user;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VisitorGifMessageVH(View view, User user2, Function1<? super Message, Unit> function1) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(user2, "user");
        Intrinsics.checkParameterIsNotNull(function1, "onClickListener");
        this.user = user2;
        this.onClickListener = function1;
    }

    public final void bind(Message message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        View view = this.itemView;
        Glide.with(view.getContext()).load(message.getImageUrl()).error((int) R.drawable.background_photo_placeholder).listener(new VisitorGifMessageVH$bind$1$1(view)).into((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_preview));
        if (this.user.getPhotoProfileUrl() == null) {
            String asInitials = StringExtensionsKt.getAsInitials(message.getSenderName());
            Context context = view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            TextDrawable createTextDrawable$default = ContextExtensionsKt.createTextDrawable$default(context, asInitials, 0, 0, 0, 14, (Object) null);
            CircleImageView circleImageView = (CircleImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_avatar);
            Intrinsics.checkExpressionValueIsNotNull(circleImageView, "iv_avatar");
            circleImageView.setBackground(createTextDrawable$default);
        } else {
            Glide.with(view.getContext()).load(this.user.getPhotoProfileUrl()).into((CircleImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_avatar));
        }
        ((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_preview)).setOnClickListener(new VisitorGifMessageVH$bind$$inlined$with$lambda$1(this, message));
        if (message.getStatus() == MessageStatus.PENDING) {
            CommonKt.show((TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_pending));
        } else {
            CommonKt.hide((TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_pending));
        }
        TextView textView = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_date");
        textView.setText(DateExtensionsKt.getAsShortTime(message.getCreated()));
    }
}
