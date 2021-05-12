package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.MessageInfo;
import com.forasoft.homewavvisitor.presentation.view.conversations.VideoRecordView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012:\u0010\u0002\u001a6\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0006 \u0005*\u001a\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<name for destructuring parameter 0>", "Lkotlin/Pair;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "Lcom/forasoft/homewavvisitor/model/data/MessageInfo;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoRecordPresenter.kt */
final class VideoRecordPresenter$onFirstViewAttach$3<T> implements Consumer<Pair<? extends Inmate, ? extends MessageInfo>> {
    final /* synthetic */ VideoRecordPresenter this$0;

    VideoRecordPresenter$onFirstViewAttach$3(VideoRecordPresenter videoRecordPresenter) {
        this.this$0 = videoRecordPresenter;
    }

    public final void accept(Pair<Inmate, MessageInfo> pair) {
        final Inmate component1 = pair.component1();
        MessageInfo component2 = pair.component2();
        this.this$0.canRecordVideo = component1.getCan_video_message();
        this.this$0.canTakePhoto = component1.getCan_image_message();
        VideoRecordPresenter videoRecordPresenter = this.this$0;
        if (component2 == null) {
            Intrinsics.throwNpe();
        }
        videoRecordPresenter.pubId = component2.getPubId();
        this.this$0.streamName = component2.getStreamName();
        this.this$0.protocol = component2.getProtocol();
        CommonKt.applyAsync(this.this$0.inmateDao.getInmate(this.this$0.inmateId)).subscribe(new Consumer<Inmate>(this) {
            final /* synthetic */ VideoRecordPresenter$onFirstViewAttach$3 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(Inmate inmate) {
                this.this$0.this$0.maxLength = Integer.valueOf(Integer.parseInt(inmate.getPrison_max_video_message_length()));
                ((VideoRecordView) this.this$0.this$0.getViewState()).recordMp4Video(this.this$0.this$0.cameraFacing, component1.getCan_video_message(), component1.getCan_image_message());
            }
        }, AnonymousClass2.INSTANCE);
    }
}
