package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$onSendClicked$1<T> implements Consumer<Inmate> {
    final /* synthetic */ String $message;
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$onSendClicked$1(ConversationPresenter conversationPresenter, String str) {
        this.this$0 = conversationPresenter;
        this.$message = str;
    }

    public final void accept(Inmate inmate) {
        String credit_balance = inmate.getCredit_balance();
        boolean z = true;
        if (this.this$0.getMessagePrice(this.$message.length() == 0) > (credit_balance != null ? Float.parseFloat(credit_balance) : 0.0f)) {
            ((ConversationView) this.this$0.getViewState()).showMessage((int) R.string.error_insufficient_funds);
        } else if (this.this$0.settings.getConfirmMessages()) {
            ConversationPresenter conversationPresenter = this.this$0;
            if (this.$message.length() != 0) {
                z = false;
            }
            conversationPresenter.showConfirmDialog(z);
        } else {
            this.this$0.onConfirmClicked(this.$message);
        }
    }
}
