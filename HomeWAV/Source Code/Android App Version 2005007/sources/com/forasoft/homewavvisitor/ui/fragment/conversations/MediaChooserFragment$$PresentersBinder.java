package com.forasoft.homewavvisitor.ui.fragment.conversations;

import com.forasoft.homewavvisitor.presentation.presenter.conversations.MediaChooserPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class MediaChooserFragment$$PresentersBinder extends moxy.PresenterBinder<MediaChooserFragment> {
    public List<PresenterField<? super MediaChooserFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: MediaChooserFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<MediaChooserFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, MediaChooserPresenter.class);
        }

        public void bind(MediaChooserFragment mediaChooserFragment, MvpPresenter mvpPresenter) {
            mediaChooserFragment.presenter = (MediaChooserPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(MediaChooserFragment mediaChooserFragment) {
            return mediaChooserFragment.providePresenter();
        }
    }
}
