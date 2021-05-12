package com.forasoft.homewavvisitor.model;

import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Marker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0010\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/model/DeepLinker;", "", "()V", "AUTHORITY", "", "KEY_ID", "KEY_LINK", "PATH_ID", "uriMatcher", "Landroid/content/UriMatcher;", "buildBundle", "Landroid/os/Bundle;", "uri", "Landroid/net/Uri;", "getIdFromBundle", "", "bundle", "getLinkFromBundle", "Lcom/forasoft/homewavvisitor/model/DeepLink;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DeepLinker.kt */
public final class DeepLinker {
    private final String AUTHORITY = Marker.ANY_MARKER;
    private final String KEY_ID = "id";
    private final String KEY_LINK = "link";
    private final String PATH_ID = "/*";
    private final UriMatcher uriMatcher = new UriMatcher(-1);

    public DeepLinker() {
        for (DeepLink deepLink : DeepLink.values()) {
            this.uriMatcher.addURI(this.AUTHORITY, deepLink.getPath(), deepLink.ordinal());
        }
    }

    public final DeepLink getLinkFromBundle(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        DeepLink deepLink = null;
        int i = bundle.getInt(this.KEY_LINK, -1);
        return (i <= -1 || i >= DeepLink.values().length) ? deepLink : DeepLink.values()[i];
    }

    public final long getIdFromBundle(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        return bundle.getLong(this.KEY_ID);
    }

    public final Bundle buildBundle(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        int match = this.uriMatcher.match(uri);
        DeepLink deepLink = DeepLink.EMPTY;
        if (match == -1) {
            match = deepLink.ordinal();
        }
        Bundle bundle = new Bundle();
        bundle.putInt(this.KEY_LINK, match);
        return bundle;
    }
}
