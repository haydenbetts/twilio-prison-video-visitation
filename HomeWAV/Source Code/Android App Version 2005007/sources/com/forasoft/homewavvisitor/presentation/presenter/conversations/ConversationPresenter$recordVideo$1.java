package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$recordVideo$1<T> implements Consumer<Inmate> {
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$recordVideo$1(ConversationPresenter conversationPresenter) {
        this.this$0 = conversationPresenter;
    }

    public final void accept(Inmate inmate) {
        if (inmate.getCan_image_message() || inmate.getCan_video_message()) {
            this.this$0.router.navigateTo(new Screens.VideoRecordScreen(this.this$0.inmateId));
        } else {
            ((ConversationView) this.this$0.getViewState()).showMessage((int) R.string.option_is_on_available);
        }
    }
}
