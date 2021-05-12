package com.forasoft.homewavvisitor.presentation.adapter.messages;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.MessageStatus;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/VisitorTextMessageVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "user", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "(Landroid/view/View;Lcom/forasoft/homewavvisitor/model/data/auth/User;)V", "bind", "", "message", "Lcom/forasoft/homewavvisitor/model/data/Message;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitorTextMessageVH.kt */
public final class VisitorTextMessageVH extends RecyclerView.ViewHolder {
    private final User user;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VisitorTextMessageVH(View view, User user2) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        Intrinsics.checkParameterIsNotNull(user2, "user");
        this.user = user2;
    }

    public final void bind(Message message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        View view = this.itemView;
        if (this.user.getPhotoProfileUrl() == null) {
            String asInitials = StringExtensionsKt.getAsInitials(message.getSenderName());
            Context context = view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            TextDrawable createTextDrawable$default = ContextExtensionsKt.createTextDrawable$default(context, asInitials, 0, 0, 0, 14, (Object) null);
            CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.iv_avatar);
            Intrinsics.checkExpressionValueIsNotNull(circleImageView, "iv_avatar");
            circleImageView.setBackground(createTextDrawable$default);
        } else {
            Glide.with(view.getContext()).load(this.user.getPhotoProfileUrl()).into((CircleImageView) view.findViewById(R.id.iv_avatar));
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_message);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_message");
        textView.setText(message.getBody());
        if (message.getStatus() == MessageStatus.PENDING) {
            CommonKt.show((TextView) view.findViewById(R.id.tv_pending));
        } else {
            CommonKt.hide((TextView) view.findViewById(R.id.tv_pending));
        }
        TextView textView2 = (TextView) view.findViewById(R.id.tv_date);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_date");
        textView2.setText(DateExtensionsKt.getAsShortTime(message.getCreated()));
    }
}
