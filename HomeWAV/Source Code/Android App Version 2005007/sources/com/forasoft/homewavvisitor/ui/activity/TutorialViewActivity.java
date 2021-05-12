package com.forasoft.homewavvisitor.ui.activity;

import air.HomeWAV.R;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0014¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/TutorialViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TutorialViewActivity.kt */
public final class TutorialViewActivity extends AppCompatActivity {
    public static final String ARGUMENT_VIDEO_URL = "argumentVideoId";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/TutorialViewActivity$Companion;", "", "()V", "ARGUMENT_VIDEO_URL", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: TutorialViewActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        setContentView((int) R.layout.activity_tutorial);
        ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).setVideoURI(Uri.parse(getIntent().getStringExtra(ARGUMENT_VIDEO_URL)));
        ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).start();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).pause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_player)).start();
    }
}
