package com.forasoft.homewavvisitor.model;

import android.content.Context;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import wseemann.media.FFmpegMediaMetadataRetriever;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/ProcessVideoWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ProcessVideoWorker.kt */
public final class ProcessVideoWorker extends Worker {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProcessVideoWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParameters, "params");
    }

    public ListenableWorker.Result doWork() {
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        File filesDir = applicationContext.getFilesDir();
        String absolutePath = new File(filesDir, System.currentTimeMillis() + ".mp4").getAbsolutePath();
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(getInputData().getString(UploadWorker.KEY_GALLERY_URL));
        fFmpegFrameGrabber.start();
        FFmpegFrameRecorder fFmpegFrameRecorder = new FFmpegFrameRecorder(absolutePath, 640, (((fFmpegFrameGrabber.getImageHeight() * 640) / fFmpegFrameGrabber.getImageWidth()) / 2) * 2, 1);
        fFmpegFrameRecorder.setFrameRate(fFmpegFrameGrabber.getVideoFrameRate());
        fFmpegFrameRecorder.setVideoCodec(fFmpegFrameGrabber.getVideoCodec());
        fFmpegFrameRecorder.setVideoOption("preset", "superfast");
        String videoMetadata = fFmpegFrameGrabber.getVideoMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
        if (videoMetadata != null) {
            fFmpegFrameRecorder.setVideoMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION, videoMetadata);
        }
        fFmpegFrameRecorder.start();
        while (true) {
            Frame grabFrame = fFmpegFrameGrabber.grabFrame();
            if (grabFrame == null) {
                fFmpegFrameGrabber.stop();
                fFmpegFrameRecorder.stop();
                Data build = new Data.Builder().putString(UploadWorker.KEY_GALLERY_URL, absolutePath).build();
                Intrinsics.checkExpressionValueIsNotNull(build, "Data.Builder()\n         …\n                .build()");
                ListenableWorker.Result success = ListenableWorker.Result.success(build);
                Intrinsics.checkExpressionValueIsNotNull(success, "Result.success(outputData)");
                return success;
            }
            fFmpegFrameRecorder.record(grabFrame);
        }
    }
}
