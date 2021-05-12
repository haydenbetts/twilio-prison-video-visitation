package com.forasoft.homewavvisitor.ui.fragment.calls;

import air.HomeWAV.R;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
final class IncomingCallDialogFragment$onViewCreated$7<T> implements Consumer<Inmate> {
    final /* synthetic */ ImageView $avatarView;
    final /* synthetic */ String $balance;
    final /* synthetic */ Call $call;
    final /* synthetic */ TextView $nameView;
    final /* synthetic */ TextView $remainingView;
    final /* synthetic */ IncomingCallDialogFragment this$0;

    IncomingCallDialogFragment$onViewCreated$7(IncomingCallDialogFragment incomingCallDialogFragment, TextView textView, String str, TextView textView2, ImageView imageView, Call call) {
        this.this$0 = incomingCallDialogFragment;
        this.$remainingView = textView;
        this.$balance = str;
        this.$nameView = textView2;
        this.$avatarView = imageView;
        this.$call = call;
    }

    public final void accept(Inmate inmate) {
        TextView textView = this.$remainingView;
        Intrinsics.checkExpressionValueIsNotNull(textView, "remainingView");
        textView.setText(this.$balance);
        TextView textView2 = this.$nameView;
        Intrinsics.checkExpressionValueIsNotNull(textView2, "nameView");
        String full_name = inmate.getFull_name();
        if (full_name == null) {
            full_name = "";
        }
        textView2.setText(full_name);
        ImageView imageView = this.$avatarView;
        Context requireContext = this.this$0.requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        imageView.setImageDrawable(ContextExtensionsKt.createTextDrawable(requireContext, StringExtensionsKt.getAsInitials(this.$call.getInmate_name()), R.dimen.initials_size_large, 17170444, R.color.white));
    }
}
