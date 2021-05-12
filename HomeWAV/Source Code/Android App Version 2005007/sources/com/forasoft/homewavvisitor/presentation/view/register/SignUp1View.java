package com.forasoft.homewavvisitor.presentation.view.register;

import android.net.Uri;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\nH&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp1View;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "activateNextButton", "", "blockNextButton", "hideIdPhotoProgress", "hideProfileProgress", "hideProgress", "showPhotoIdImage", "imageUri", "Landroid/net/Uri;", "showPhotoIdProgress", "showProfileImage", "profileImageUri", "showProfileProgress", "showProgress", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignUp1View.kt */
public interface SignUp1View extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: SignUp1View.kt */
    public static final class DefaultImpls {
        public static void showProgress(SignUp1View signUp1View, boolean z) {
            BaseView.DefaultImpls.showProgress(signUp1View, z);
        }

        public static void updateNotificationMenu(SignUp1View signUp1View, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(signUp1View, i);
        }
    }

    void activateNextButton();

    void blockNextButton();

    void hideIdPhotoProgress();

    void hideProfileProgress();

    void hideProgress();

    void showPhotoIdImage(Uri uri);

    void showPhotoIdProgress();

    void showProfileImage(Uri uri);

    void showProfileProgress();

    void showProgress();
}
