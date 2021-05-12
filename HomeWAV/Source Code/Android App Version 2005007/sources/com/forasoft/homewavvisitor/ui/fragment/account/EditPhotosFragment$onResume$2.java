package com.forasoft.homewavvisitor.ui.fragment.account;

import android.content.Context;
import android.view.View;
import com.forasoft.homewavvisitor.ui.views.AvatarUpdaterDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: EditPhotosFragment.kt */
final class EditPhotosFragment$onResume$2 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ EditPhotosFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EditPhotosFragment$onResume$2(EditPhotosFragment editPhotosFragment) {
        super(1);
        this.this$0 = editPhotosFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        AvatarUpdaterDialog.Factory factory = AvatarUpdaterDialog.Factory;
        Context requireContext = this.this$0.requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        factory.show(requireContext).setResultReceiver(new Function1<String, Unit>(this) {
            final /* synthetic */ EditPhotosFragment$onResume$2 this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((String) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(String str) {
                Intrinsics.checkParameterIsNotNull(str, "it");
                if (Intrinsics.areEqual((Object) str, (Object) AvatarUpdaterDialog.CAMERA)) {
                    EditPhotosFragmentPermissionsDispatcher.onIdFromCameraWithPermissionCheck(this.this$0.this$0);
                    this.this$0.this$0.setIdRequested(true);
                    return;
                }
                this.this$0.this$0.getPresenter().onPhotoIdImageClicked(str);
            }
        });
    }
}
