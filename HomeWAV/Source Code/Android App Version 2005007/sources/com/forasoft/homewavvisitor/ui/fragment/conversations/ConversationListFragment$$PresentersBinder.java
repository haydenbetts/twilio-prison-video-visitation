package com.forasoft.homewavvisitor.ui.fragment.conversations;

import com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationsPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class ConversationListFragment$$PresentersBinder extends moxy.PresenterBinder<ConversationListFragment> {
    public List<PresenterField<? super ConversationListFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: ConversationListFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<ConversationListFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, ConversationsPresenter.class);
        }

        public void bind(ConversationListFragment conversationListFragment, MvpPresenter mvpPresenter) {
            conversationListFragment.presenter = (ConversationsPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(ConversationListFragment conversationListFragment) {
            return conversationListFragment.providePresenter();
        }
    }
}
