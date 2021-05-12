package com.forasoft.homewavvisitor.ui.fragment.conversations;

import com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class ConversationFragment$$PresentersBinder extends moxy.PresenterBinder<ConversationFragment> {
    public List<PresenterField<? super ConversationFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: ConversationFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<ConversationFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, ConversationPresenter.class);
        }

        public void bind(ConversationFragment conversationFragment, MvpPresenter mvpPresenter) {
            conversationFragment.presenter = (ConversationPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(ConversationFragment conversationFragment) {
            return conversationFragment.providePresenter();
        }
    }
}
