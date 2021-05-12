package com.forasoft.homewavvisitor.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.PaymentRequestData;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.activity.SplashActivity;
import com.forasoft.homewavvisitor.ui.activity.TutorialViewActivity;
import com.forasoft.homewavvisitor.ui.activity.auth.LoginActivity;
import com.forasoft.homewavvisitor.ui.activity.register.CreateAccountActivity;
import com.forasoft.homewavvisitor.ui.activity.welcome.WelcomeActivity;
import com.forasoft.homewavvisitor.ui.fragment.account.AccountFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.AccountTabFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.AddConnectionFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.EditAccountFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.EditPhotosFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.HelpFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.HistoryFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.InmateChooserAccountFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.ManageCardsFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.NotificationListFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.NotificationSettingsFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.PaymentMethodsFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.RefundsFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.ReportBugFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.TermConditionsFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.TestVideoFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.TransferFundsFragment;
import com.forasoft.homewavvisitor.ui.fragment.auth.ForgotPasswordFragment;
import com.forasoft.homewavvisitor.ui.fragment.auth.SignInFragment;
import com.forasoft.homewavvisitor.ui.fragment.auth.VerifyCodeFragment;
import com.forasoft.homewavvisitor.ui.fragment.auth.VerifyMethodFragment;
import com.forasoft.homewavvisitor.ui.fragment.calls.IncomingCallDialogFragment;
import com.forasoft.homewavvisitor.ui.fragment.calls.LiveswitchCallFragment;
import com.forasoft.homewavvisitor.ui.fragment.calls.TwilioCallFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationListFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationTabFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.ImageViewFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.MediaChooserFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.VideoRecordFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.VideoViewFragment;
import com.forasoft.homewavvisitor.ui.fragment.home.HomeFragment;
import com.forasoft.homewavvisitor.ui.fragment.home.HomeTabFragment;
import com.forasoft.homewavvisitor.ui.fragment.home.InmateDetailFragment;
import com.forasoft.homewavvisitor.ui.fragment.home.PendingInmatesFragment;
import com.forasoft.homewavvisitor.ui.fragment.home.TutorialsFragment;
import com.forasoft.homewavvisitor.ui.fragment.payment.ChooseFundsFragment;
import com.forasoft.homewavvisitor.ui.fragment.payment.PayNearMeFragment;
import com.forasoft.homewavvisitor.ui.fragment.payment.PayNearMeViewerFragment;
import com.forasoft.homewavvisitor.ui.fragment.register.AddCardFragment;
import com.forasoft.homewavvisitor.ui.fragment.register.AddConnectionSignUpFragment;
import com.forasoft.homewavvisitor.ui.fragment.register.SignUp1Fragment;
import com.forasoft.homewavvisitor.ui.fragment.register.SignUp2Fragment;
import com.forasoft.homewavvisitor.ui.fragment.register.SignUp4Fragment;
import com.forasoft.homewavvisitor.ui.fragment.visits.DateChooserFragment;
import com.forasoft.homewavvisitor.ui.fragment.visits.InmateChooserVisitsFragment;
import com.forasoft.homewavvisitor.ui.fragment.visits.TimeChooserFragment;
import com.forasoft.homewavvisitor.ui.fragment.visits.VisitDetailsFragment;
import com.forasoft.homewavvisitor.ui.fragment.visits.VisitsFragment;
import com.forasoft.homewavvisitor.ui.fragment.visits.VisitsTabFragment;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Screen;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b;\bÆ\u0002\u0018\u00002\u00020\u0001:8\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?@B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006A"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens;", "", "()V", "SignUpSteps", "Ljava/util/ArrayList;", "Lru/terrakok/cicerone/Screen;", "Lkotlin/collections/ArrayList;", "getSignUpSteps", "()Ljava/util/ArrayList;", "AccountHistoryScreen", "AccountScreen", "AccountTabScreen", "AddCardScreen", "AddConnectionScreen", "ChooseFundsScreen", "ConversationListScreen", "ConversationScreen", "ConversationsTabScreen", "DateChooseScreen", "EditAccountScreen", "EditPhotosScreen", "ForgotPasswordScreen", "HelpAndContactScreen", "HomeScreen", "HomeTabScreen", "ImageViewScreen", "IncomingCallScreen", "InmateChooseAccountScreen", "InmateChooseVisitsScreen", "InmateDetailScreen", "LiveswitchCallScreen", "LoginScreen", "MainScreen", "ManageCardsScreen", "MediaChooserScreen", "NotificationSettingsScreen", "NotificationsScreen", "PayNearMeOrderScreen", "PayNearMeScreen", "PaymentMethodsScreen", "PendingScreen", "RefundsScreen", "ReportBugScreen", "SignInScreen", "SignUp1Screen", "SignUp2Screen", "SignUp3Screen", "SignUp4Screen", "SignUpScreen", "SplashScreen", "TermsAndConditionsScreen", "TestVideoScreen", "TimeChooseScreen", "TransferFundsScreen", "TutorialScreen", "TutorialsScreen", "TwilioCallScreen", "VerifyCodeScreen", "VerifyMethodScreen", "VideoRecordScreen", "VideoViewScreen", "VisitDetailsScreen", "VisitsScreen", "VisitsTabScreen", "WelcomeScreen", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Screens.kt */
public final class Screens {
    public static final Screens INSTANCE = new Screens();
    private static final ArrayList<Screen> SignUpSteps = CollectionsKt.arrayListOf(SignUp1Screen.INSTANCE, SignUp2Screen.INSTANCE, SignUp3Screen.INSTANCE, new SignUp4Screen((Boolean) null, 1, (DefaultConstructorMarker) null));

    private Screens() {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$SplashScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "data", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "getData", "()Landroid/net/Uri;", "component1", "copy", "equals", "", "other", "", "getActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class SplashScreen extends SupportAppScreen {
        private final Uri data;

        public SplashScreen() {
            this((Uri) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ SplashScreen copy$default(SplashScreen splashScreen, Uri uri, int i, Object obj) {
            if ((i & 1) != 0) {
                uri = splashScreen.data;
            }
            return splashScreen.copy(uri);
        }

        public final Uri component1() {
            return this.data;
        }

        public final SplashScreen copy(Uri uri) {
            return new SplashScreen(uri);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof SplashScreen) && Intrinsics.areEqual((Object) this.data, (Object) ((SplashScreen) obj).data);
            }
            return true;
        }

        public int hashCode() {
            Uri uri = this.data;
            if (uri != null) {
                return uri.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "SplashScreen(data=" + this.data + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SplashScreen(Uri uri, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : uri);
        }

        public final Uri getData() {
            return this.data;
        }

        public SplashScreen(Uri uri) {
            this.data = uri;
        }

        public Intent getActivityIntent(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent intent = new Intent(context, SplashActivity.class);
            Uri uri = this.data;
            if (uri != null) {
                intent.setData(uri);
            }
            return intent;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$MainScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "targetScreen", "", "(Ljava/lang/String;)V", "getTargetScreen", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class MainScreen extends SupportAppScreen {
        private final String targetScreen;

        public MainScreen() {
            this((String) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ MainScreen copy$default(MainScreen mainScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mainScreen.targetScreen;
            }
            return mainScreen.copy(str);
        }

        public final String component1() {
            return this.targetScreen;
        }

        public final MainScreen copy(String str) {
            return new MainScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof MainScreen) && Intrinsics.areEqual((Object) this.targetScreen, (Object) ((MainScreen) obj).targetScreen);
            }
            return true;
        }

        public int hashCode() {
            String str = this.targetScreen;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "MainScreen(targetScreen=" + this.targetScreen + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ MainScreen(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final String getTargetScreen() {
            return this.targetScreen;
        }

        public MainScreen(String str) {
            this.targetScreen = str;
        }

        public Intent getActivityIntent(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(335544320);
            String str = this.targetScreen;
            if (str != null) {
                intent.putExtra(MainActivity.SCREEN_KEY, str);
            }
            return intent;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$LoginScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "showLogoutDialog", "", "(Z)V", "getShowLogoutDialog", "()Z", "component1", "copy", "equals", "other", "", "getActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class LoginScreen extends SupportAppScreen {
        private final boolean showLogoutDialog;

        public LoginScreen() {
            this(false, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ LoginScreen copy$default(LoginScreen loginScreen, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = loginScreen.showLogoutDialog;
            }
            return loginScreen.copy(z);
        }

        public final boolean component1() {
            return this.showLogoutDialog;
        }

        public final LoginScreen copy(boolean z) {
            return new LoginScreen(z);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof LoginScreen) && this.showLogoutDialog == ((LoginScreen) obj).showLogoutDialog;
            }
            return true;
        }

        public int hashCode() {
            boolean z = this.showLogoutDialog;
            if (z) {
                return 1;
            }
            return z ? 1 : 0;
        }

        public String toString() {
            return "LoginScreen(showLogoutDialog=" + this.showLogoutDialog + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LoginScreen(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z);
        }

        public final boolean getShowLogoutDialog() {
            return this.showLogoutDialog;
        }

        public LoginScreen(boolean z) {
            this.showLogoutDialog = z;
        }

        public Intent getActivityIntent(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent putExtra = new Intent(context, LoginActivity.class).addFlags(335544320).putExtra(LoginActivity.SHOW_LOGOUT_DIALOG, this.showLogoutDialog);
            Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(context, LoginAct…DIALOG, showLogoutDialog)");
            return putExtra;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$SignInScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class SignInScreen extends SupportAppScreen {
        public static final SignInScreen INSTANCE = new SignInScreen();

        private SignInScreen() {
        }

        public Fragment getFragment() {
            return new SignInFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$SignUpScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class SignUpScreen extends SupportAppScreen {
        public static final SignUpScreen INSTANCE = new SignUpScreen();

        private SignUpScreen() {
        }

        public Intent getActivityIntent(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new Intent(context, CreateAccountActivity.class);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$WelcomeScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class WelcomeScreen extends SupportAppScreen {
        public static final WelcomeScreen INSTANCE = new WelcomeScreen();

        private WelcomeScreen() {
        }

        public Intent getActivityIntent(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new Intent(context, WelcomeActivity.class);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$ForgotPasswordScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class ForgotPasswordScreen extends SupportAppScreen {
        public static final ForgotPasswordScreen INSTANCE = new ForgotPasswordScreen();

        private ForgotPasswordScreen() {
        }

        public Fragment getFragment() {
            return new ForgotPasswordFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$SignUp1Screen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class SignUp1Screen extends SupportAppScreen {
        public static final SignUp1Screen INSTANCE = new SignUp1Screen();

        private SignUp1Screen() {
        }

        public Fragment getFragment() {
            return new SignUp1Fragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$SignUp2Screen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class SignUp2Screen extends SupportAppScreen {
        public static final SignUp2Screen INSTANCE = new SignUp2Screen();

        private SignUp2Screen() {
        }

        public Fragment getFragment() {
            return new SignUp2Fragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$SignUp3Screen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class SignUp3Screen extends SupportAppScreen {
        public static final SignUp3Screen INSTANCE = new SignUp3Screen();

        private SignUp3Screen() {
        }

        public Fragment getFragment() {
            return new AddConnectionSignUpFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$SignUp4Screen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "showExtraTransfer", "", "(Ljava/lang/Boolean;)V", "getShowExtraTransfer", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "copy", "(Ljava/lang/Boolean;)Lcom/forasoft/homewavvisitor/navigation/Screens$SignUp4Screen;", "equals", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class SignUp4Screen extends SupportAppScreen {
        private final Boolean showExtraTransfer;

        public SignUp4Screen() {
            this((Boolean) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ SignUp4Screen copy$default(SignUp4Screen signUp4Screen, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                bool = signUp4Screen.showExtraTransfer;
            }
            return signUp4Screen.copy(bool);
        }

        public final Boolean component1() {
            return this.showExtraTransfer;
        }

        public final SignUp4Screen copy(Boolean bool) {
            return new SignUp4Screen(bool);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof SignUp4Screen) && Intrinsics.areEqual((Object) this.showExtraTransfer, (Object) ((SignUp4Screen) obj).showExtraTransfer);
            }
            return true;
        }

        public int hashCode() {
            Boolean bool = this.showExtraTransfer;
            if (bool != null) {
                return bool.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "SignUp4Screen(showExtraTransfer=" + this.showExtraTransfer + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SignUp4Screen(Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : bool);
        }

        public final Boolean getShowExtraTransfer() {
            return this.showExtraTransfer;
        }

        public SignUp4Screen(Boolean bool) {
            this.showExtraTransfer = bool;
        }

        public Fragment getFragment() {
            return SignUp4Fragment.Companion.newInstance(this.showExtraTransfer);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$TransferFundsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class TransferFundsScreen extends SupportAppScreen {
        public static final TransferFundsScreen INSTANCE = new TransferFundsScreen();

        private TransferFundsScreen() {
        }

        public Fragment getFragment() {
            return new TransferFundsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$ChooseFundsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class ChooseFundsScreen extends SupportAppScreen {
        public static final ChooseFundsScreen INSTANCE = new ChooseFundsScreen();

        private ChooseFundsScreen() {
        }

        public Fragment getFragment() {
            return new ChooseFundsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$AddCardScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "paymentScope", "", "(Ljava/lang/String;)V", "getPaymentScope", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class AddCardScreen extends SupportAppScreen {
        private final String paymentScope;

        public AddCardScreen() {
            this((String) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ AddCardScreen copy$default(AddCardScreen addCardScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = addCardScreen.paymentScope;
            }
            return addCardScreen.copy(str);
        }

        public final String component1() {
            return this.paymentScope;
        }

        public final AddCardScreen copy(String str) {
            return new AddCardScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof AddCardScreen) && Intrinsics.areEqual((Object) this.paymentScope, (Object) ((AddCardScreen) obj).paymentScope);
            }
            return true;
        }

        public int hashCode() {
            String str = this.paymentScope;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "AddCardScreen(paymentScope=" + this.paymentScope + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AddCardScreen(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final String getPaymentScope() {
            return this.paymentScope;
        }

        public AddCardScreen(String str) {
            this.paymentScope = str;
        }

        public Fragment getFragment() {
            return AddCardFragment.Companion.newInstance(this.paymentScope);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$VerifyMethodScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class VerifyMethodScreen extends SupportAppScreen {
        public static final VerifyMethodScreen INSTANCE = new VerifyMethodScreen();

        private VerifyMethodScreen() {
        }

        public Fragment getFragment() {
            return new VerifyMethodFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$VerifyCodeScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "channel", "", "(Ljava/lang/String;)V", "getChannel", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class VerifyCodeScreen extends SupportAppScreen {
        private final String channel;

        public static /* synthetic */ VerifyCodeScreen copy$default(VerifyCodeScreen verifyCodeScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = verifyCodeScreen.channel;
            }
            return verifyCodeScreen.copy(str);
        }

        public final String component1() {
            return this.channel;
        }

        public final VerifyCodeScreen copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, Modules.CHANNEL_MODULE);
            return new VerifyCodeScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof VerifyCodeScreen) && Intrinsics.areEqual((Object) this.channel, (Object) ((VerifyCodeScreen) obj).channel);
            }
            return true;
        }

        public int hashCode() {
            String str = this.channel;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "VerifyCodeScreen(channel=" + this.channel + ")";
        }

        public final String getChannel() {
            return this.channel;
        }

        public VerifyCodeScreen(String str) {
            Intrinsics.checkParameterIsNotNull(str, Modules.CHANNEL_MODULE);
            this.channel = str;
        }

        public Fragment getFragment() {
            return VerifyCodeFragment.Companion.newInstance(this.channel);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$HomeTabScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "screen", "", "(Ljava/lang/String;)V", "getScreen", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class HomeTabScreen extends SupportAppScreen {
        private final String screen;

        public HomeTabScreen() {
            this((String) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ HomeTabScreen copy$default(HomeTabScreen homeTabScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = homeTabScreen.screen;
            }
            return homeTabScreen.copy(str);
        }

        public final String component1() {
            return this.screen;
        }

        public final HomeTabScreen copy(String str) {
            return new HomeTabScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof HomeTabScreen) && Intrinsics.areEqual((Object) this.screen, (Object) ((HomeTabScreen) obj).screen);
            }
            return true;
        }

        public int hashCode() {
            String str = this.screen;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "HomeTabScreen(screen=" + this.screen + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ HomeTabScreen(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final String getScreen() {
            return this.screen;
        }

        public HomeTabScreen(String str) {
            this.screen = str;
        }

        public Fragment getFragment() {
            return HomeTabFragment.Companion.newInstance(this.screen);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$VisitsTabScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "scheduleNewVisit", "", "(Ljava/lang/Boolean;)V", "getScheduleNewVisit", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "copy", "(Ljava/lang/Boolean;)Lcom/forasoft/homewavvisitor/navigation/Screens$VisitsTabScreen;", "equals", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class VisitsTabScreen extends SupportAppScreen {
        private final Boolean scheduleNewVisit;

        public VisitsTabScreen() {
            this((Boolean) null, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ VisitsTabScreen copy$default(VisitsTabScreen visitsTabScreen, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                bool = visitsTabScreen.scheduleNewVisit;
            }
            return visitsTabScreen.copy(bool);
        }

        public final Boolean component1() {
            return this.scheduleNewVisit;
        }

        public final VisitsTabScreen copy(Boolean bool) {
            return new VisitsTabScreen(bool);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof VisitsTabScreen) && Intrinsics.areEqual((Object) this.scheduleNewVisit, (Object) ((VisitsTabScreen) obj).scheduleNewVisit);
            }
            return true;
        }

        public int hashCode() {
            Boolean bool = this.scheduleNewVisit;
            if (bool != null) {
                return bool.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "VisitsTabScreen(scheduleNewVisit=" + this.scheduleNewVisit + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ VisitsTabScreen(Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : bool);
        }

        public final Boolean getScheduleNewVisit() {
            return this.scheduleNewVisit;
        }

        public VisitsTabScreen(Boolean bool) {
            this.scheduleNewVisit = bool;
        }

        public Fragment getFragment() {
            return VisitsTabFragment.Companion.newInstance(this.scheduleNewVisit);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$ConversationsTabScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class ConversationsTabScreen extends SupportAppScreen {
        public static final ConversationsTabScreen INSTANCE = new ConversationsTabScreen();

        private ConversationsTabScreen() {
        }

        public Fragment getFragment() {
            return new ConversationTabFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$AccountTabScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class AccountTabScreen extends SupportAppScreen {
        public static final AccountTabScreen INSTANCE = new AccountTabScreen();

        private AccountTabScreen() {
        }

        public Fragment getFragment() {
            return new AccountTabFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$HomeScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class HomeScreen extends SupportAppScreen {
        public static final HomeScreen INSTANCE = new HomeScreen();

        private HomeScreen() {
        }

        public Fragment getFragment() {
            return new HomeFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$InmateDetailScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "(Lcom/forasoft/homewavvisitor/model/data/Inmate;)V", "getInmate", "()Lcom/forasoft/homewavvisitor/model/data/Inmate;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class InmateDetailScreen extends SupportAppScreen {
        private final Inmate inmate;

        public static /* synthetic */ InmateDetailScreen copy$default(InmateDetailScreen inmateDetailScreen, Inmate inmate2, int i, Object obj) {
            if ((i & 1) != 0) {
                inmate2 = inmateDetailScreen.inmate;
            }
            return inmateDetailScreen.copy(inmate2);
        }

        public final Inmate component1() {
            return this.inmate;
        }

        public final InmateDetailScreen copy(Inmate inmate2) {
            Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
            return new InmateDetailScreen(inmate2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof InmateDetailScreen) && Intrinsics.areEqual((Object) this.inmate, (Object) ((InmateDetailScreen) obj).inmate);
            }
            return true;
        }

        public int hashCode() {
            Inmate inmate2 = this.inmate;
            if (inmate2 != null) {
                return inmate2.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "InmateDetailScreen(inmate=" + this.inmate + ")";
        }

        public final Inmate getInmate() {
            return this.inmate;
        }

        public InmateDetailScreen(Inmate inmate2) {
            Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
            this.inmate = inmate2;
        }

        public Fragment getFragment() {
            return InmateDetailFragment.Companion.newInstance(this.inmate);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$PendingScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class PendingScreen extends SupportAppScreen {
        public static final PendingScreen INSTANCE = new PendingScreen();

        private PendingScreen() {
        }

        public Fragment getFragment() {
            return new PendingInmatesFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$TutorialsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class TutorialsScreen extends SupportAppScreen {
        public static final TutorialsScreen INSTANCE = new TutorialsScreen();

        private TutorialsScreen() {
        }

        public Fragment getFragment() {
            return new TutorialsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$TutorialScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "videoUrl", "", "(Ljava/lang/String;)V", "getVideoUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class TutorialScreen extends SupportAppScreen {
        private final String videoUrl;

        public static /* synthetic */ TutorialScreen copy$default(TutorialScreen tutorialScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tutorialScreen.videoUrl;
            }
            return tutorialScreen.copy(str);
        }

        public final String component1() {
            return this.videoUrl;
        }

        public final TutorialScreen copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, "videoUrl");
            return new TutorialScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof TutorialScreen) && Intrinsics.areEqual((Object) this.videoUrl, (Object) ((TutorialScreen) obj).videoUrl);
            }
            return true;
        }

        public int hashCode() {
            String str = this.videoUrl;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "TutorialScreen(videoUrl=" + this.videoUrl + ")";
        }

        public final String getVideoUrl() {
            return this.videoUrl;
        }

        public TutorialScreen(String str) {
            Intrinsics.checkParameterIsNotNull(str, "videoUrl");
            this.videoUrl = str;
        }

        public Intent getActivityIntent(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent putExtra = new Intent(context, TutorialViewActivity.class).putExtra(TutorialViewActivity.ARGUMENT_VIDEO_URL, this.videoUrl);
            Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(context, Tutorial…MENT_VIDEO_URL, videoUrl)");
            return putExtra;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$AccountScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class AccountScreen extends SupportAppScreen {
        public static final AccountScreen INSTANCE = new AccountScreen();

        private AccountScreen() {
        }

        public Fragment getFragment() {
            return new AccountFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$EditPhotosScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class EditPhotosScreen extends SupportAppScreen {
        public static final EditPhotosScreen INSTANCE = new EditPhotosScreen();

        private EditPhotosScreen() {
        }

        public Fragment getFragment() {
            return new EditPhotosFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$EditAccountScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class EditAccountScreen extends SupportAppScreen {
        public static final EditAccountScreen INSTANCE = new EditAccountScreen();

        private EditAccountScreen() {
        }

        public Fragment getFragment() {
            return new EditAccountFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$NotificationsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class NotificationsScreen extends SupportAppScreen {
        public static final NotificationsScreen INSTANCE = new NotificationsScreen();

        private NotificationsScreen() {
        }

        public Fragment getFragment() {
            return new NotificationListFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$AccountHistoryScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class AccountHistoryScreen extends SupportAppScreen {
        public static final AccountHistoryScreen INSTANCE = new AccountHistoryScreen();

        private AccountHistoryScreen() {
        }

        public Fragment getFragment() {
            return new HistoryFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$RefundsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class RefundsScreen extends SupportAppScreen {
        public static final RefundsScreen INSTANCE = new RefundsScreen();

        private RefundsScreen() {
        }

        public Fragment getFragment() {
            return new RefundsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\b\u0010\u000b\u001a\u00020\fH\u0016J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$TermsAndConditionsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "isFromTermsDialog", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class TermsAndConditionsScreen extends SupportAppScreen {
        private final boolean isFromTermsDialog;

        public TermsAndConditionsScreen() {
            this(false, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ TermsAndConditionsScreen copy$default(TermsAndConditionsScreen termsAndConditionsScreen, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = termsAndConditionsScreen.isFromTermsDialog;
            }
            return termsAndConditionsScreen.copy(z);
        }

        public final boolean component1() {
            return this.isFromTermsDialog;
        }

        public final TermsAndConditionsScreen copy(boolean z) {
            return new TermsAndConditionsScreen(z);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof TermsAndConditionsScreen) && this.isFromTermsDialog == ((TermsAndConditionsScreen) obj).isFromTermsDialog;
            }
            return true;
        }

        public int hashCode() {
            boolean z = this.isFromTermsDialog;
            if (z) {
                return 1;
            }
            return z ? 1 : 0;
        }

        public String toString() {
            return "TermsAndConditionsScreen(isFromTermsDialog=" + this.isFromTermsDialog + ")";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ TermsAndConditionsScreen(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z);
        }

        public final boolean isFromTermsDialog() {
            return this.isFromTermsDialog;
        }

        public TermsAndConditionsScreen(boolean z) {
            this.isFromTermsDialog = z;
        }

        public Fragment getFragment() {
            return TermConditionsFragment.Companion.newInstance(this.isFromTermsDialog);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$HelpAndContactScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class HelpAndContactScreen extends SupportAppScreen {
        public static final HelpAndContactScreen INSTANCE = new HelpAndContactScreen();

        private HelpAndContactScreen() {
        }

        public Fragment getFragment() {
            return new HelpFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$NotificationSettingsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class NotificationSettingsScreen extends SupportAppScreen {
        public static final NotificationSettingsScreen INSTANCE = new NotificationSettingsScreen();

        private NotificationSettingsScreen() {
        }

        public Fragment getFragment() {
            return new NotificationSettingsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$ReportBugScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class ReportBugScreen extends SupportAppScreen {
        public static final ReportBugScreen INSTANCE = new ReportBugScreen();

        private ReportBugScreen() {
        }

        public Fragment getFragment() {
            return new ReportBugFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$PaymentMethodsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class PaymentMethodsScreen extends SupportAppScreen {
        public static final PaymentMethodsScreen INSTANCE = new PaymentMethodsScreen();

        private PaymentMethodsScreen() {
        }

        public Fragment getFragment() {
            return new PaymentMethodsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$ManageCardsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class ManageCardsScreen extends SupportAppScreen {
        public static final ManageCardsScreen INSTANCE = new ManageCardsScreen();

        private ManageCardsScreen() {
        }

        public Fragment getFragment() {
            return new ManageCardsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$VisitsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class VisitsScreen extends SupportAppScreen {
        public static final VisitsScreen INSTANCE = new VisitsScreen();

        private VisitsScreen() {
        }

        public Fragment getFragment() {
            return new VisitsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$VisitDetailsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "visitId", "", "(Ljava/lang/String;)V", "getVisitId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class VisitDetailsScreen extends SupportAppScreen {
        private final String visitId;

        public static /* synthetic */ VisitDetailsScreen copy$default(VisitDetailsScreen visitDetailsScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = visitDetailsScreen.visitId;
            }
            return visitDetailsScreen.copy(str);
        }

        public final String component1() {
            return this.visitId;
        }

        public final VisitDetailsScreen copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, "visitId");
            return new VisitDetailsScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof VisitDetailsScreen) && Intrinsics.areEqual((Object) this.visitId, (Object) ((VisitDetailsScreen) obj).visitId);
            }
            return true;
        }

        public int hashCode() {
            String str = this.visitId;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "VisitDetailsScreen(visitId=" + this.visitId + ")";
        }

        public final String getVisitId() {
            return this.visitId;
        }

        public VisitDetailsScreen(String str) {
            Intrinsics.checkParameterIsNotNull(str, "visitId");
            this.visitId = str;
        }

        public Fragment getFragment() {
            return VisitDetailsFragment.Companion.newInstance(this.visitId);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$InmateChooseAccountScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "title", "", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "(Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/Inmate;)V", "getInmate", "()Lcom/forasoft/homewavvisitor/model/data/Inmate;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class InmateChooseAccountScreen extends SupportAppScreen {
        private final Inmate inmate;
        private final String title;

        public static /* synthetic */ InmateChooseAccountScreen copy$default(InmateChooseAccountScreen inmateChooseAccountScreen, String str, Inmate inmate2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = inmateChooseAccountScreen.title;
            }
            if ((i & 2) != 0) {
                inmate2 = inmateChooseAccountScreen.inmate;
            }
            return inmateChooseAccountScreen.copy(str, inmate2);
        }

        public final String component1() {
            return this.title;
        }

        public final Inmate component2() {
            return this.inmate;
        }

        public final InmateChooseAccountScreen copy(String str, Inmate inmate2) {
            Intrinsics.checkParameterIsNotNull(str, "title");
            return new InmateChooseAccountScreen(str, inmate2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InmateChooseAccountScreen)) {
                return false;
            }
            InmateChooseAccountScreen inmateChooseAccountScreen = (InmateChooseAccountScreen) obj;
            return Intrinsics.areEqual((Object) this.title, (Object) inmateChooseAccountScreen.title) && Intrinsics.areEqual((Object) this.inmate, (Object) inmateChooseAccountScreen.inmate);
        }

        public int hashCode() {
            String str = this.title;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            Inmate inmate2 = this.inmate;
            if (inmate2 != null) {
                i = inmate2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return "InmateChooseAccountScreen(title=" + this.title + ", inmate=" + this.inmate + ")";
        }

        public final String getTitle() {
            return this.title;
        }

        public final Inmate getInmate() {
            return this.inmate;
        }

        public InmateChooseAccountScreen(String str, Inmate inmate2) {
            Intrinsics.checkParameterIsNotNull(str, "title");
            this.title = str;
            this.inmate = inmate2;
        }

        public Fragment getFragment() {
            return InmateChooserAccountFragment.Companion.newInstance(this.title, this.inmate);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$InmateChooseVisitsScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class InmateChooseVisitsScreen extends SupportAppScreen {
        public static final InmateChooseVisitsScreen INSTANCE = new InmateChooseVisitsScreen();

        private InmateChooseVisitsScreen() {
        }

        public Fragment getFragment() {
            return new InmateChooserVisitsFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$DateChooseScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "(Lcom/forasoft/homewavvisitor/model/data/Inmate;)V", "getInmate", "()Lcom/forasoft/homewavvisitor/model/data/Inmate;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class DateChooseScreen extends SupportAppScreen {
        private final Inmate inmate;

        public static /* synthetic */ DateChooseScreen copy$default(DateChooseScreen dateChooseScreen, Inmate inmate2, int i, Object obj) {
            if ((i & 1) != 0) {
                inmate2 = dateChooseScreen.inmate;
            }
            return dateChooseScreen.copy(inmate2);
        }

        public final Inmate component1() {
            return this.inmate;
        }

        public final DateChooseScreen copy(Inmate inmate2) {
            Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
            return new DateChooseScreen(inmate2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof DateChooseScreen) && Intrinsics.areEqual((Object) this.inmate, (Object) ((DateChooseScreen) obj).inmate);
            }
            return true;
        }

        public int hashCode() {
            Inmate inmate2 = this.inmate;
            if (inmate2 != null) {
                return inmate2.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "DateChooseScreen(inmate=" + this.inmate + ")";
        }

        public final Inmate getInmate() {
            return this.inmate;
        }

        public DateChooseScreen(Inmate inmate2) {
            Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
            this.inmate = inmate2;
        }

        public Fragment getFragment() {
            return DateChooserFragment.Companion.newInstance(this.inmate);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$TimeChooseScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "daySlot", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "(Lcom/forasoft/homewavvisitor/model/data/Inmate;Lcom/forasoft/homewavvisitor/model/data/DaySlot;)V", "getDaySlot", "()Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "getInmate", "()Lcom/forasoft/homewavvisitor/model/data/Inmate;", "component1", "component2", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class TimeChooseScreen extends SupportAppScreen {
        private final DaySlot daySlot;
        private final Inmate inmate;

        public static /* synthetic */ TimeChooseScreen copy$default(TimeChooseScreen timeChooseScreen, Inmate inmate2, DaySlot daySlot2, int i, Object obj) {
            if ((i & 1) != 0) {
                inmate2 = timeChooseScreen.inmate;
            }
            if ((i & 2) != 0) {
                daySlot2 = timeChooseScreen.daySlot;
            }
            return timeChooseScreen.copy(inmate2, daySlot2);
        }

        public final Inmate component1() {
            return this.inmate;
        }

        public final DaySlot component2() {
            return this.daySlot;
        }

        public final TimeChooseScreen copy(Inmate inmate2, DaySlot daySlot2) {
            Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
            return new TimeChooseScreen(inmate2, daySlot2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TimeChooseScreen)) {
                return false;
            }
            TimeChooseScreen timeChooseScreen = (TimeChooseScreen) obj;
            return Intrinsics.areEqual((Object) this.inmate, (Object) timeChooseScreen.inmate) && Intrinsics.areEqual((Object) this.daySlot, (Object) timeChooseScreen.daySlot);
        }

        public int hashCode() {
            Inmate inmate2 = this.inmate;
            int i = 0;
            int hashCode = (inmate2 != null ? inmate2.hashCode() : 0) * 31;
            DaySlot daySlot2 = this.daySlot;
            if (daySlot2 != null) {
                i = daySlot2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return "TimeChooseScreen(inmate=" + this.inmate + ", daySlot=" + this.daySlot + ")";
        }

        public final Inmate getInmate() {
            return this.inmate;
        }

        public final DaySlot getDaySlot() {
            return this.daySlot;
        }

        public TimeChooseScreen(Inmate inmate2, DaySlot daySlot2) {
            Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
            this.inmate = inmate2;
            this.daySlot = daySlot2;
        }

        public Fragment getFragment() {
            return TimeChooserFragment.Companion.newInstance(this.inmate, this.daySlot);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$AddConnectionScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class AddConnectionScreen extends SupportAppScreen {
        public static final AddConnectionScreen INSTANCE = new AddConnectionScreen();

        private AddConnectionScreen() {
        }

        public Fragment getFragment() {
            return new AddConnectionFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$ConversationListScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class ConversationListScreen extends SupportAppScreen {
        public static final ConversationListScreen INSTANCE = new ConversationListScreen();

        private ConversationListScreen() {
        }

        public Fragment getFragment() {
            return new ConversationListFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$ConversationScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "inmateId", "", "(Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class ConversationScreen extends SupportAppScreen {
        private final String inmateId;

        public static /* synthetic */ ConversationScreen copy$default(ConversationScreen conversationScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = conversationScreen.inmateId;
            }
            return conversationScreen.copy(str);
        }

        public final String component1() {
            return this.inmateId;
        }

        public final ConversationScreen copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            return new ConversationScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof ConversationScreen) && Intrinsics.areEqual((Object) this.inmateId, (Object) ((ConversationScreen) obj).inmateId);
            }
            return true;
        }

        public int hashCode() {
            String str = this.inmateId;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "ConversationScreen(inmateId=" + this.inmateId + ")";
        }

        public final String getInmateId() {
            return this.inmateId;
        }

        public ConversationScreen(String str) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            this.inmateId = str;
        }

        public Fragment getFragment() {
            return ConversationFragment.Companion.newInstance(this.inmateId);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$VideoRecordScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "inmateId", "", "(Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class VideoRecordScreen extends SupportAppScreen {
        private final String inmateId;

        public static /* synthetic */ VideoRecordScreen copy$default(VideoRecordScreen videoRecordScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoRecordScreen.inmateId;
            }
            return videoRecordScreen.copy(str);
        }

        public final String component1() {
            return this.inmateId;
        }

        public final VideoRecordScreen copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            return new VideoRecordScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof VideoRecordScreen) && Intrinsics.areEqual((Object) this.inmateId, (Object) ((VideoRecordScreen) obj).inmateId);
            }
            return true;
        }

        public int hashCode() {
            String str = this.inmateId;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "VideoRecordScreen(inmateId=" + this.inmateId + ")";
        }

        public final String getInmateId() {
            return this.inmateId;
        }

        public VideoRecordScreen(String str) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            this.inmateId = str;
        }

        public Fragment getFragment() {
            return VideoRecordFragment.Companion.newInstance(this.inmateId);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$VideoViewScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "streamUrl", "", "(Ljava/lang/String;)V", "getStreamUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class VideoViewScreen extends SupportAppScreen {
        private final String streamUrl;

        public static /* synthetic */ VideoViewScreen copy$default(VideoViewScreen videoViewScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoViewScreen.streamUrl;
            }
            return videoViewScreen.copy(str);
        }

        public final String component1() {
            return this.streamUrl;
        }

        public final VideoViewScreen copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, "streamUrl");
            return new VideoViewScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof VideoViewScreen) && Intrinsics.areEqual((Object) this.streamUrl, (Object) ((VideoViewScreen) obj).streamUrl);
            }
            return true;
        }

        public int hashCode() {
            String str = this.streamUrl;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "VideoViewScreen(streamUrl=" + this.streamUrl + ")";
        }

        public final String getStreamUrl() {
            return this.streamUrl;
        }

        public VideoViewScreen(String str) {
            Intrinsics.checkParameterIsNotNull(str, "streamUrl");
            this.streamUrl = str;
        }

        public Fragment getFragment() {
            return VideoViewFragment.Companion.newInstance(this.streamUrl);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$ImageViewScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "imageUri", "", "(Ljava/lang/String;)V", "getImageUri", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class ImageViewScreen extends SupportAppScreen {
        private final String imageUri;

        public static /* synthetic */ ImageViewScreen copy$default(ImageViewScreen imageViewScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = imageViewScreen.imageUri;
            }
            return imageViewScreen.copy(str);
        }

        public final String component1() {
            return this.imageUri;
        }

        public final ImageViewScreen copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, "imageUri");
            return new ImageViewScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof ImageViewScreen) && Intrinsics.areEqual((Object) this.imageUri, (Object) ((ImageViewScreen) obj).imageUri);
            }
            return true;
        }

        public int hashCode() {
            String str = this.imageUri;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "ImageViewScreen(imageUri=" + this.imageUri + ")";
        }

        public final String getImageUri() {
            return this.imageUri;
        }

        public ImageViewScreen(String str) {
            Intrinsics.checkParameterIsNotNull(str, "imageUri");
            this.imageUri = str;
        }

        public Fragment getFragment() {
            return ImageViewFragment.Companion.newInstance(this.imageUri);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$IncomingCallScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "call", "Lcom/forasoft/homewavvisitor/model/data/Call;", "(Lcom/forasoft/homewavvisitor/model/data/Call;)V", "getCall", "()Lcom/forasoft/homewavvisitor/model/data/Call;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class IncomingCallScreen extends SupportAppScreen {
        private final Call call;

        public static /* synthetic */ IncomingCallScreen copy$default(IncomingCallScreen incomingCallScreen, Call call2, int i, Object obj) {
            if ((i & 1) != 0) {
                call2 = incomingCallScreen.call;
            }
            return incomingCallScreen.copy(call2);
        }

        public final Call component1() {
            return this.call;
        }

        public final IncomingCallScreen copy(Call call2) {
            Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
            return new IncomingCallScreen(call2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof IncomingCallScreen) && Intrinsics.areEqual((Object) this.call, (Object) ((IncomingCallScreen) obj).call);
            }
            return true;
        }

        public int hashCode() {
            Call call2 = this.call;
            if (call2 != null) {
                return call2.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "IncomingCallScreen(call=" + this.call + ")";
        }

        public final Call getCall() {
            return this.call;
        }

        public IncomingCallScreen(Call call2) {
            Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
            this.call = call2;
        }

        public Fragment getFragment() {
            return IncomingCallDialogFragment.Companion.newInstance(this.call);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$TwilioCallScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "callWrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "(Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;)V", "getCallWrapper", "()Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class TwilioCallScreen extends SupportAppScreen {
        private final CallWrapper callWrapper;

        public static /* synthetic */ TwilioCallScreen copy$default(TwilioCallScreen twilioCallScreen, CallWrapper callWrapper2, int i, Object obj) {
            if ((i & 1) != 0) {
                callWrapper2 = twilioCallScreen.callWrapper;
            }
            return twilioCallScreen.copy(callWrapper2);
        }

        public final CallWrapper component1() {
            return this.callWrapper;
        }

        public final TwilioCallScreen copy(CallWrapper callWrapper2) {
            Intrinsics.checkParameterIsNotNull(callWrapper2, "callWrapper");
            return new TwilioCallScreen(callWrapper2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof TwilioCallScreen) && Intrinsics.areEqual((Object) this.callWrapper, (Object) ((TwilioCallScreen) obj).callWrapper);
            }
            return true;
        }

        public int hashCode() {
            CallWrapper callWrapper2 = this.callWrapper;
            if (callWrapper2 != null) {
                return callWrapper2.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "TwilioCallScreen(callWrapper=" + this.callWrapper + ")";
        }

        public final CallWrapper getCallWrapper() {
            return this.callWrapper;
        }

        public TwilioCallScreen(CallWrapper callWrapper2) {
            Intrinsics.checkParameterIsNotNull(callWrapper2, "callWrapper");
            this.callWrapper = callWrapper2;
        }

        public Fragment getFragment() {
            return TwilioCallFragment.Companion.newInstance(this.callWrapper);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$LiveswitchCallScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "callWrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "callData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "(Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;)V", "getCallData", "()Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "getCallWrapper", "()Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "component1", "component2", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class LiveswitchCallScreen extends SupportAppScreen {
        private final LiveswitchCallDataResponse callData;
        private final CallWrapper callWrapper;

        public static /* synthetic */ LiveswitchCallScreen copy$default(LiveswitchCallScreen liveswitchCallScreen, CallWrapper callWrapper2, LiveswitchCallDataResponse liveswitchCallDataResponse, int i, Object obj) {
            if ((i & 1) != 0) {
                callWrapper2 = liveswitchCallScreen.callWrapper;
            }
            if ((i & 2) != 0) {
                liveswitchCallDataResponse = liveswitchCallScreen.callData;
            }
            return liveswitchCallScreen.copy(callWrapper2, liveswitchCallDataResponse);
        }

        public final CallWrapper component1() {
            return this.callWrapper;
        }

        public final LiveswitchCallDataResponse component2() {
            return this.callData;
        }

        public final LiveswitchCallScreen copy(CallWrapper callWrapper2, LiveswitchCallDataResponse liveswitchCallDataResponse) {
            Intrinsics.checkParameterIsNotNull(callWrapper2, "callWrapper");
            Intrinsics.checkParameterIsNotNull(liveswitchCallDataResponse, "callData");
            return new LiveswitchCallScreen(callWrapper2, liveswitchCallDataResponse);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LiveswitchCallScreen)) {
                return false;
            }
            LiveswitchCallScreen liveswitchCallScreen = (LiveswitchCallScreen) obj;
            return Intrinsics.areEqual((Object) this.callWrapper, (Object) liveswitchCallScreen.callWrapper) && Intrinsics.areEqual((Object) this.callData, (Object) liveswitchCallScreen.callData);
        }

        public int hashCode() {
            CallWrapper callWrapper2 = this.callWrapper;
            int i = 0;
            int hashCode = (callWrapper2 != null ? callWrapper2.hashCode() : 0) * 31;
            LiveswitchCallDataResponse liveswitchCallDataResponse = this.callData;
            if (liveswitchCallDataResponse != null) {
                i = liveswitchCallDataResponse.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return "LiveswitchCallScreen(callWrapper=" + this.callWrapper + ", callData=" + this.callData + ")";
        }

        public final CallWrapper getCallWrapper() {
            return this.callWrapper;
        }

        public final LiveswitchCallDataResponse getCallData() {
            return this.callData;
        }

        public LiveswitchCallScreen(CallWrapper callWrapper2, LiveswitchCallDataResponse liveswitchCallDataResponse) {
            Intrinsics.checkParameterIsNotNull(callWrapper2, "callWrapper");
            Intrinsics.checkParameterIsNotNull(liveswitchCallDataResponse, "callData");
            this.callWrapper = callWrapper2;
            this.callData = liveswitchCallDataResponse;
        }

        public Fragment getFragment() {
            return LiveswitchCallFragment.Companion.newInstance(this.callWrapper, this.callData);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$MediaChooserScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "inmateId", "", "mediaLoader", "(Ljava/lang/String;Ljava/lang/String;)V", "getInmateId", "()Ljava/lang/String;", "getMediaLoader", "component1", "component2", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class MediaChooserScreen extends SupportAppScreen {
        private final String inmateId;
        private final String mediaLoader;

        public static /* synthetic */ MediaChooserScreen copy$default(MediaChooserScreen mediaChooserScreen, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mediaChooserScreen.inmateId;
            }
            if ((i & 2) != 0) {
                str2 = mediaChooserScreen.mediaLoader;
            }
            return mediaChooserScreen.copy(str, str2);
        }

        public final String component1() {
            return this.inmateId;
        }

        public final String component2() {
            return this.mediaLoader;
        }

        public final MediaChooserScreen copy(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "mediaLoader");
            return new MediaChooserScreen(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaChooserScreen)) {
                return false;
            }
            MediaChooserScreen mediaChooserScreen = (MediaChooserScreen) obj;
            return Intrinsics.areEqual((Object) this.inmateId, (Object) mediaChooserScreen.inmateId) && Intrinsics.areEqual((Object) this.mediaLoader, (Object) mediaChooserScreen.mediaLoader);
        }

        public int hashCode() {
            String str = this.inmateId;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.mediaLoader;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return "MediaChooserScreen(inmateId=" + this.inmateId + ", mediaLoader=" + this.mediaLoader + ")";
        }

        public final String getInmateId() {
            return this.inmateId;
        }

        public final String getMediaLoader() {
            return this.mediaLoader;
        }

        public MediaChooserScreen(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            Intrinsics.checkParameterIsNotNull(str2, "mediaLoader");
            this.inmateId = str;
            this.mediaLoader = str2;
        }

        public Fragment getFragment() {
            return MediaChooserFragment.Companion.newInstance(this.inmateId, this.mediaLoader);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$TestVideoScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "()V", "getFragment", "Landroidx/fragment/app/Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class TestVideoScreen extends SupportAppScreen {
        public static final TestVideoScreen INSTANCE = new TestVideoScreen();

        private TestVideoScreen() {
        }

        public Fragment getFragment() {
            return new TestVideoFragment();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$PayNearMeScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "paymentRequestData", "Lcom/forasoft/homewavvisitor/model/data/PaymentRequestData;", "(Lcom/forasoft/homewavvisitor/model/data/PaymentRequestData;)V", "getPaymentRequestData", "()Lcom/forasoft/homewavvisitor/model/data/PaymentRequestData;", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class PayNearMeScreen extends SupportAppScreen {
        private final PaymentRequestData paymentRequestData;

        public static /* synthetic */ PayNearMeScreen copy$default(PayNearMeScreen payNearMeScreen, PaymentRequestData paymentRequestData2, int i, Object obj) {
            if ((i & 1) != 0) {
                paymentRequestData2 = payNearMeScreen.paymentRequestData;
            }
            return payNearMeScreen.copy(paymentRequestData2);
        }

        public final PaymentRequestData component1() {
            return this.paymentRequestData;
        }

        public final PayNearMeScreen copy(PaymentRequestData paymentRequestData2) {
            Intrinsics.checkParameterIsNotNull(paymentRequestData2, "paymentRequestData");
            return new PayNearMeScreen(paymentRequestData2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof PayNearMeScreen) && Intrinsics.areEqual((Object) this.paymentRequestData, (Object) ((PayNearMeScreen) obj).paymentRequestData);
            }
            return true;
        }

        public int hashCode() {
            PaymentRequestData paymentRequestData2 = this.paymentRequestData;
            if (paymentRequestData2 != null) {
                return paymentRequestData2.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "PayNearMeScreen(paymentRequestData=" + this.paymentRequestData + ")";
        }

        public final PaymentRequestData getPaymentRequestData() {
            return this.paymentRequestData;
        }

        public PayNearMeScreen(PaymentRequestData paymentRequestData2) {
            Intrinsics.checkParameterIsNotNull(paymentRequestData2, "paymentRequestData");
            this.paymentRequestData = paymentRequestData2;
        }

        public Fragment getFragment() {
            return PayNearMeFragment.Companion.newInstance(this.paymentRequestData);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\b\u0010\u000b\u001a\u00020\fH\u0016J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/Screens$PayNearMeOrderScreen;", "Lru/terrakok/cicerone/android/support/SupportAppScreen;", "trackingUrl", "", "(Ljava/lang/String;)V", "component1", "copy", "equals", "", "other", "", "getFragment", "Landroidx/fragment/app/Fragment;", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Screens.kt */
    public static final class PayNearMeOrderScreen extends SupportAppScreen {
        private final String trackingUrl;

        private final String component1() {
            return this.trackingUrl;
        }

        public static /* synthetic */ PayNearMeOrderScreen copy$default(PayNearMeOrderScreen payNearMeOrderScreen, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = payNearMeOrderScreen.trackingUrl;
            }
            return payNearMeOrderScreen.copy(str);
        }

        public final PayNearMeOrderScreen copy(String str) {
            Intrinsics.checkParameterIsNotNull(str, "trackingUrl");
            return new PayNearMeOrderScreen(str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                return (obj instanceof PayNearMeOrderScreen) && Intrinsics.areEqual((Object) this.trackingUrl, (Object) ((PayNearMeOrderScreen) obj).trackingUrl);
            }
            return true;
        }

        public int hashCode() {
            String str = this.trackingUrl;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "PayNearMeOrderScreen(trackingUrl=" + this.trackingUrl + ")";
        }

        public PayNearMeOrderScreen(String str) {
            Intrinsics.checkParameterIsNotNull(str, "trackingUrl");
            this.trackingUrl = str;
        }

        public Fragment getFragment() {
            return PayNearMeViewerFragment.Companion.newInstance(this.trackingUrl);
        }
    }

    public final ArrayList<Screen> getSignUpSteps() {
        return SignUpSteps;
    }
}
