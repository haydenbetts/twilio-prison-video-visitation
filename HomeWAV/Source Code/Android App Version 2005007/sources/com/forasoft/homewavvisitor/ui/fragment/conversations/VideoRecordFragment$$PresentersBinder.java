package com.forasoft.homewavvisitor.ui.fragment.conversations;

import com.forasoft.homewavvisitor.presentation.presenter.conversations.VideoRecordPresenter;
import java.util.ArrayList;
import java.util.List;
import moxy.MvpPresenter;
import moxy.presenter.PresenterField;

public class VideoRecordFragment$$PresentersBinder extends moxy.PresenterBinder<VideoRecordFragment> {
    public List<PresenterField<? super VideoRecordFragment>> getPresenterFields() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new PresenterBinder());
        return arrayList;
    }

    /* compiled from: VideoRecordFragment$$PresentersBinder */
    public class PresenterBinder extends PresenterField<VideoRecordFragment> {
        public PresenterBinder() {
            super("presenter", (String) null, VideoRecordPresenter.class);
        }

        public void bind(VideoRecordFragment videoRecordFragment, MvpPresenter mvpPresenter) {
            videoRecordFragment.presenter = (VideoRecordPresenter) mvpPresenter;
        }

        public MvpPresenter<?> providePresenter(VideoRecordFragment videoRecordFragment) {
            return videoRecordFragment.providePresenter();
        }
    }
}
