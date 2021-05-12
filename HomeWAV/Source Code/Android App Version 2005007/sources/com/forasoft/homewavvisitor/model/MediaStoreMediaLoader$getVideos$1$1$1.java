package com.forasoft.homewavvisitor.model;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/model/Video;", "Landroid/database/Cursor;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: MediaStoreMediaLoader.kt */
final class MediaStoreMediaLoader$getVideos$1$1$1 extends Lambda implements Function1<Cursor, Video> {
    public static final MediaStoreMediaLoader$getVideos$1$1$1 INSTANCE = new MediaStoreMediaLoader$getVideos$1$1$1();

    MediaStoreMediaLoader$getVideos$1$1$1() {
        super(1);
    }

    public final Video invoke(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "$receiver");
        String string = cursor.getString(cursor.getColumnIndex("_data"));
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(getColumnIndex…aStore.Video.Media.DATA))");
        String string2 = cursor.getString(cursor.getColumnIndex("mime_type"));
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(getColumnIndex…e.Video.Media.MIME_TYPE))");
        return new Video(string, string2, cursor.getLong(cursor.getColumnIndex("duration")));
    }
}
