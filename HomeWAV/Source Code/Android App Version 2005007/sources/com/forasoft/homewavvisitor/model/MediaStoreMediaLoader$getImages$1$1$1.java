package com.forasoft.homewavvisitor.model;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Landroid/database/Cursor;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: MediaStoreMediaLoader.kt */
final class MediaStoreMediaLoader$getImages$1$1$1 extends Lambda implements Function1<Cursor, String> {
    public static final MediaStoreMediaLoader$getImages$1$1$1 INSTANCE = new MediaStoreMediaLoader$getImages$1$1$1();

    MediaStoreMediaLoader$getImages$1$1$1() {
        super(1);
    }

    public final String invoke(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "$receiver");
        return cursor.getString(cursor.getColumnIndex("_data"));
    }
}
