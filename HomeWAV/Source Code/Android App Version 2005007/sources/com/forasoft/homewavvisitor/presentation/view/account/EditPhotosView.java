package com.forasoft.homewavvisitor.presentation.view.account;

import android.net.Uri;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\nH&J\b\u0010\r\u001a\u00020\u0003H'J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\b\u0010\u0011\u001a\u00020\u0003H&J\u0012\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0010H&J\b\u0010\u0014\u001a\u00020\u0003H&J\b\u0010\u0015\u001a\u00020\u0003H&¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/EditPhotosView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "activateNextButton", "", "blockNextButton", "hideIdPhotoProgress", "hideProfileProgress", "hideProgress", "loadPhotoIdImage", "photoIdUrl", "", "loadProfileImage", "photoProfileUrl", "showApproveDialog", "showPhotoIdImage", "imageUri", "Landroid/net/Uri;", "showPhotoIdProgress", "showProfileImage", "profileImageUri", "showProfileProgress", "showProgress", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: EditPhotosView.kt */
public interface EditPhotosView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: EditPhotosView.kt */
    public static final class DefaultImpls {
        public static void showProgress(EditPhotosView editPhotosView, boolean z) {
            BaseView.DefaultImpls.showProgress(editPhotosView, z);
        }

        public static void updateNotificationMenu(EditPhotosView editPhotosView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(editPhotosView, i);
        }
    }

    void activateNextButton();

    void blockNextButton();

    void hideIdPhotoProgress();

    void hideProfileProgress();

    void hideProgress();

    void loadPhotoIdImage(String str);

    void loadProfileImage(String str);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showApproveDialog();

    void showPhotoIdImage(Uri uri);

    void showPhotoIdProgress();

    void showProfileImage(Uri uri);

    void showProfileProgress();

    void showProgress();
}
