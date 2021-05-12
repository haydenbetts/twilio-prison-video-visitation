package com.forasoft.homewavvisitor.presentation.view.conversations;

import kotlin.Metadata;
import moxy.MvpView;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0005H&¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/conversations/VideoRecordView;", "Lmoxy/MvpView;", "recordMp4Video", "", "facing", "", "canRecordVideo", "", "canTakePhoto", "showMessage", "message", "", "updateRemainingTime", "remaining", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VideoRecordView.kt */
public interface VideoRecordView extends MvpView {
    void recordMp4Video(int i, boolean z, boolean z2);

    void showMessage(String str);

    void updateRemainingTime(int i);
}
