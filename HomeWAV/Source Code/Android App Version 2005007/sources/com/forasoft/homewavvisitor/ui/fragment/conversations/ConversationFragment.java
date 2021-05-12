package com.forasoft.homewavvisitor.ui.fragment.conversations;

import air.HomeWAV.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.Protocol;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.messages.ChatAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationPresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.fragment.ConfirmDialog;
import com.forasoft.homewavvisitor.ui.fragment.MessageProcessingErrorFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.ActionModeCallback;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.threeten.bp.Duration;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u0000 m2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001mB\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0016J\b\u0010 \u001a\u00020\u0016H\u0016J\u0016\u0010!\u001a\u00020\u00162\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016J$\u0010%\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u001c2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0010\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020*H\u0016J\u0010\u0010-\u001a\u00020\u00162\u0006\u0010,\u001a\u00020*H\u0016J\b\u0010.\u001a\u00020\u0016H\u0016J\u0010\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u000201H\u0014J\b\u00102\u001a\u00020\u0016H\u0016J\b\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u00020\u0016H\u0016J\b\u00106\u001a\u00020\u0016H\u0016J\u0012\u00107\u001a\u00020\u00162\b\u00108\u001a\u0004\u0018\u000109H\u0016J$\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010?2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010@\u001a\u00020\u0016H\u0016J\b\u0010A\u001a\u00020\u0016H\u0016J\u0010\u0010B\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010C\u001a\u0002042\u0006\u0010D\u001a\u00020EH\u0016J-\u0010F\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u001c2\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020*0I2\u0006\u0010J\u001a\u00020KH\u0016¢\u0006\u0002\u0010LJ(\u0010M\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010N\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J\u001a\u0010O\u001a\u00020\u00162\u0006\u0010P\u001a\u00020;2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010Q\u001a\u00020\rH\u0007J\b\u0010R\u001a\u00020\u0016H\u0007J\u0010\u0010S\u001a\u00020\u00162\u0006\u0010T\u001a\u00020UH\u0016J\u0010\u0010V\u001a\u00020\u00162\u0006\u0010W\u001a\u00020\u001cH\u0016J\u0010\u0010X\u001a\u00020\u00162\u0006\u0010Y\u001a\u00020ZH\u0016J)\u0010[\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u001c2\u0012\u0010\\\u001a\n\u0012\u0006\b\u0001\u0012\u00020*0I\"\u00020*H\u0016¢\u0006\u0002\u0010]J\r\u0010^\u001a\u00020\u0016H\u0001¢\u0006\u0002\b_J\r\u0010`\u001a\u00020\u0016H\u0001¢\u0006\u0002\baJ\u0010\u0010b\u001a\u00020\u00162\u0006\u0010c\u001a\u00020\u001cH\u0002J\b\u0010d\u001a\u00020\u0016H\u0016J\r\u0010e\u001a\u00020\u0016H\u0001¢\u0006\u0002\bfJ\r\u0010g\u001a\u00020\u0016H\u0001¢\u0006\u0002\bhJ\b\u0010i\u001a\u00020\u0016H\u0016J\b\u0010j\u001a\u00020\u0016H\u0007J\u0010\u0010k\u001a\u00020\u00162\u0006\u0010l\u001a\u000204H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000¨\u0006n"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ConversationFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/conversations/ConversationView;", "Landroid/text/TextWatcher;", "Lcom/forasoft/homewavvisitor/ui/fragment/ConfirmDialog$ConfirmDialogListener;", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ActionModeCallback$ActionsListener;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "actionMode", "Landroidx/appcompat/view/ActionMode;", "dialog", "Landroidx/appcompat/app/AlertDialog;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationPresenter;)V", "runningWorks", "", "Landroidx/work/WorkInfo;", "afterTextChanged", "", "message", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "clearInputs", "clearSelection", "displayMessages", "messages", "", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "displayRecordedVideo", "protocol", "Lcom/forasoft/homewavvisitor/model/data/Protocol;", "videoLength", "streamName", "", "displayTakenPicture", "uri", "displayVideoFromGallery", "hideWarningMessage", "installModules", "scope", "Ltoothpick/Scope;", "onActionModeDestroyed", "onBackPressed", "", "onCanceled", "onConfirmed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDeleteClicked", "onDestroyView", "onMessageSelected", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onTextChanged", "before", "onViewCreated", "view", "providePresenter", "recordVideo", "setInmate", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "setMessageFilter", "maxLength", "setupList", "user", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "showConfirmDialog", "args", "(I[Ljava/lang/String;)V", "showDeniedFoTakeImage", "showDeniedFoTakeImage$app_release", "showDeniedForRecordVideo", "showDeniedForRecordVideo$app_release", "showDeniedPermissionsDialog", "messageId", "showMessageProcessingError", "showNeverAskForRecordVideo", "showNeverAskForRecordVideo$app_release", "showNeverAskForTakeImage", "showNeverAskForTakeImage$app_release", "showWarningMessage", "takeImage", "updateSendButton", "isEnabled", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationFragment.kt */
public final class ConversationFragment extends BaseFragment implements ConversationView, TextWatcher, ConfirmDialog.ConfirmDialogListener, ActionModeCallback.ActionsListener, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String INMATE_ID = "inmate id";
    private HashMap _$_findViewCache;
    private ActionMode actionMode;
    private AlertDialog dialog;
    @InjectPresenter
    public ConversationPresenter presenter;
    /* access modifiers changed from: private */
    public final List<WorkInfo> runningWorks = new ArrayList();

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public void afterTextChanged(Editable editable) {
        Intrinsics.checkParameterIsNotNull(editable, "message");
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ConversationFragment$Companion;", "", "()V", "INMATE_ID", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ConversationFragment;", "inmateId", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ConversationFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConversationFragment newInstance(String str) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            ConversationFragment conversationFragment = new ConversationFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ConversationFragment.INMATE_ID, str);
            conversationFragment.setArguments(bundle);
            return conversationFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new ConversationFragment$installModules$1(this));
    }

    public final ConversationPresenter getPresenter() {
        ConversationPresenter conversationPresenter = this.presenter;
        if (conversationPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return conversationPresenter;
    }

    public final void setPresenter(ConversationPresenter conversationPresenter) {
        Intrinsics.checkParameterIsNotNull(conversationPresenter, "<set-?>");
        this.presenter = conversationPresenter;
    }

    @ProvidePresenter
    public final ConversationPresenter providePresenter() {
        Object instance = getScope().getInstance(ConversationPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Conver…ionPresenter::class.java)");
        return (ConversationPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_conversation, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…sation, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.et_message)).addTextChangedListener(this);
        ImageButton imageButton = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_send);
        Intrinsics.checkExpressionValueIsNotNull(imageButton, "btn_send");
        imageButton.setEnabled(false);
        ImageButton imageButton2 = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_send);
        Intrinsics.checkExpressionValueIsNotNull(imageButton2, "btn_send");
        imageButton2.setOnClickListener(new ConversationFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ConversationFragment$onViewCreated$1(this)));
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_close);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "iv_close");
        imageView.setOnClickListener(new ConversationFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ConversationFragment$onViewCreated$2(this)));
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_preview);
        Intrinsics.checkExpressionValueIsNotNull(imageView2, "iv_preview");
        imageView2.setOnClickListener(new ConversationFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ConversationFragment$onViewCreated$3(this)));
        ImageButton imageButton3 = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_add);
        Intrinsics.checkExpressionValueIsNotNull(imageButton3, "btn_add");
        imageButton3.setOnClickListener(new ConversationFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ConversationFragment$onViewCreated$4(this)));
        ImageView imageView3 = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_funds);
        Intrinsics.checkExpressionValueIsNotNull(imageView3, "iv_funds");
        imageView3.setOnClickListener(new ConversationFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ConversationFragment$onViewCreated$5(this)));
        ImageView imageView4 = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_camera);
        Intrinsics.checkExpressionValueIsNotNull(imageView4, "iv_camera");
        imageView4.setOnClickListener(new ConversationFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ConversationFragment$onViewCreated$6(this)));
        ImageView imageView5 = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_cloud);
        Intrinsics.checkExpressionValueIsNotNull(imageView5, "iv_cloud");
        imageView5.setOnClickListener(new ConversationFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ConversationFragment$onViewCreated$7(this)));
        ImageView imageView6 = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_folder);
        Intrinsics.checkExpressionValueIsNotNull(imageView6, "iv_folder");
        imageView6.setOnClickListener(new ConversationFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ConversationFragment$onViewCreated$8(this)));
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages)).addOnScrollListener(new ConversationFragment$onViewCreated$9(this));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        ConversationPresenter conversationPresenter = this.presenter;
        if (conversationPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationPresenter.getNotificationsCount();
        ConversationPresenter conversationPresenter2 = this.presenter;
        if (conversationPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationPresenter2.init();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            ConversationPresenter conversationPresenter = this.presenter;
            if (conversationPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            conversationPresenter.onBackPressed();
            return true;
        } else if (itemId != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            ConversationPresenter conversationPresenter2 = this.presenter;
            if (conversationPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            conversationPresenter2.onNotificationsClicked();
            return true;
        }
    }

    public void onDestroyView() {
        ContextExtensionsKt.hideKeyboard(this);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public boolean onBackPressed() {
        ConversationPresenter conversationPresenter = this.presenter;
        if (conversationPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationPresenter.onBackPressed();
        return true;
    }

    public void setInmate(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) inmate.getFull_name());
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(true);
            }
            WorkManager.getInstance().getWorkInfosByTagLiveData(inmate.getId()).observe(this, new ConversationFragment$setInmate$2(this));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void setupList(User user) {
        Intrinsics.checkParameterIsNotNull(user, "user");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages);
        Context context = recyclerView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Function1 conversationFragment$setupList$1$1 = new ConversationFragment$setupList$1$1(this);
        ConversationPresenter conversationPresenter = this.presenter;
        if (conversationPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Function1 conversationFragment$setupList$1$2 = new ConversationFragment$setupList$1$2(conversationPresenter);
        ConversationPresenter conversationPresenter2 = this.presenter;
        if (conversationPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Function1 conversationFragment$setupList$1$3 = new ConversationFragment$setupList$1$3(conversationPresenter2);
        ConversationPresenter conversationPresenter3 = this.presenter;
        if (conversationPresenter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView.setAdapter(new ChatAdapter(context, user, conversationFragment$setupList$1$1, conversationFragment$setupList$1$2, conversationFragment$setupList$1$3, new ConversationFragment$setupList$1$4(conversationPresenter3)));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, true));
    }

    /* access modifiers changed from: private */
    public final void onMessageSelected(int i) {
        if (this.actionMode == null) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                this.actionMode = ((AppCompatActivity) activity).startSupportActionMode(new ActionModeCallback(this));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            }
        }
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.setTitle((CharSequence) String.valueOf(i));
        }
        if (i == 0) {
            clearSelection();
        }
    }

    public void clearSelection() {
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.finish();
        }
    }

    public void onDeleteClicked() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_messages");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            List<String> selectedMessages = ((ChatAdapter) adapter).getSelectedMessages();
            new AlertDialog.Builder(requireContext()).setTitle((CharSequence) getString(R.string.dialog_delete_messages_title, Integer.valueOf(selectedMessages.size()))).setMessage((CharSequence) getString(R.string.dialog_delete_messages_message)).setPositiveButton((CharSequence) getString(R.string.dialog_delete_button), (DialogInterface.OnClickListener) new ConversationFragment$onDeleteClicked$1(this, selectedMessages)).setNegativeButton((CharSequence) getString(R.string.dialog_cancel_button), (DialogInterface.OnClickListener) ConversationFragment$onDeleteClicked$2.INSTANCE).setCancelable(false).show();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.messages.ChatAdapter");
    }

    public void onActionModeDestroyed() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_messages");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((ChatAdapter) adapter).unselectMessages();
            this.actionMode = null;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.messages.ChatAdapter");
    }

    public void setMessageFilter(int i) {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_messages");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((ChatAdapter) adapter).setTextMessagesEnabled(i != 0);
            EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.et_message);
            Intrinsics.checkExpressionValueIsNotNull(editText, "et_message");
            editText.setFilters((InputFilter[]) new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(i)});
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.messages.ChatAdapter");
    }

    public void displayMessages(List<? extends ChatItem> list) {
        Message message;
        Object obj;
        Object obj2;
        Intrinsics.checkParameterIsNotNull(list, "messages");
        CommonKt.hide((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
        if (list.isEmpty()) {
            CommonKt.show((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
        }
        CommonKt.hide((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_messages");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ChatAdapter chatAdapter = (ChatAdapter) adapter;
            List items = chatAdapter.getItems();
            Intrinsics.checkExpressionValueIsNotNull(items, "adapter.items");
            Iterator it = items.iterator();
            while (true) {
                message = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((ChatItem) obj) instanceof Message) {
                    break;
                }
            }
            if (!(obj instanceof Message)) {
                obj = null;
            }
            Message message2 = (Message) obj;
            Iterator it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj2 = null;
                    break;
                }
                obj2 = it2.next();
                if (((ChatItem) obj2) instanceof Message) {
                    break;
                }
            }
            if (obj2 instanceof Message) {
                message = obj2;
            }
            Message message3 = message;
            chatAdapter.setItems(list);
            if (message2 != null && message3 != null && (!Intrinsics.areEqual((Object) message2.getId(), (Object) message3.getId())) && message2.getId().compareTo(message3.getId()) < 0) {
                ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages)).smoothScrollToPosition(0);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.messages.ChatAdapter");
    }

    public void updateSendButton(boolean z) {
        ImageButton imageButton = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_send);
        Intrinsics.checkExpressionValueIsNotNull(imageButton, "btn_send");
        imageButton.setEnabled(z);
    }

    public void displayRecordedVideo(Protocol protocol, int i, String str) {
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages)).setPadding(0, 0, 0, (int) (resources.getDisplayMetrics().density * ((float) 92)));
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_preview));
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_close));
        CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_duration));
        if (protocol == Protocol.WEBRTC || protocol == Protocol.LIVESWITCH) {
            RequestManager with = Glide.with(getContext());
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            with.load(Uri.fromFile(new File(requireContext.getFilesDir(), "message.mp4"))).diskCacheStrategy(DiskCacheStrategy.NONE).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_preview));
        }
        Duration ofSeconds = Duration.ofSeconds((long) i);
        long minutes = ofSeconds.toMinutes();
        Duration minusMinutes = ofSeconds.minusMinutes(minutes);
        Intrinsics.checkExpressionValueIsNotNull(minusMinutes, "duration.minusMinutes(minutes)");
        long seconds = minusMinutes.getSeconds();
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_duration);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_duration");
        String format = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)}, 2));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        textView.setText(format);
    }

    public void displayVideoFromGallery(String str) {
        Intrinsics.checkParameterIsNotNull(str, "uri");
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages)).setPadding(0, 0, 0, (int) (resources.getDisplayMetrics().density * ((float) 92)));
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_preview));
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_close));
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_duration));
        Glide.with(getContext()).using(ConversationFragment$displayVideoFromGallery$1.INSTANCE).load(str).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_preview));
    }

    public void displayTakenPicture(String str) {
        Intrinsics.checkParameterIsNotNull(str, "uri");
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages)).setPadding(0, 0, 0, (int) (resources.getDisplayMetrics().density * ((float) 92)));
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_preview));
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_close));
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_duration));
        Glide.with(getContext()).load(Uri.parse(str)).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_preview));
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "message");
        ConversationPresenter conversationPresenter = this.presenter;
        if (conversationPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationPresenter.onTextChanged(charSequence.toString());
    }

    public void showConfirmDialog(int i, String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "args");
        ConfirmDialog.Companion companion = ConfirmDialog.Companion;
        String string = getString(i, Arrays.copyOf(strArr, strArr.length));
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(message, *args)");
        companion.newInstance(string).show(getChildFragmentManager(), (String) null);
    }

    public void onConfirmed() {
        ConversationPresenter conversationPresenter = this.presenter;
        if (conversationPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.et_message);
        Intrinsics.checkExpressionValueIsNotNull(editText, "et_message");
        conversationPresenter.onConfirmClicked(editText.getText().toString());
    }

    public void onCanceled() {
        updateSendButton(true);
    }

    public void clearInputs() {
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.et_message);
        Intrinsics.checkExpressionValueIsNotNull(editText, "et_message");
        editText.getText().clear();
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_messages)).setPadding(0, 0, 0, 0);
        CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_preview));
        CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_close));
        CommonKt.hide((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_attach));
        updateSendButton(false);
    }

    public void showMessageProcessingError() {
        new MessageProcessingErrorFragment().show(getChildFragmentManager(), (String) null);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        ConversationFragmentPermissionsDispatcher.onRequestPermissionsResult(this, i, iArr);
    }

    public final void recordVideo() {
        ConversationPresenter conversationPresenter = this.presenter;
        if (conversationPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationPresenter.recordVideo();
    }

    public final void showDeniedForRecordVideo$app_release() {
        showDeniedPermissionsDialog(R.string.allow_camera_and_microphone);
    }

    public final void showNeverAskForRecordVideo$app_release() {
        showDeniedPermissionsDialog(R.string.allow_camera_and_microphone);
    }

    public final void takeImage() {
        ConversationPresenter conversationPresenter = this.presenter;
        if (conversationPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationPresenter.onFolderClicked();
    }

    public final void showDeniedFoTakeImage$app_release() {
        showDeniedPermissionsDialog(R.string.allow_storage);
    }

    public final void showNeverAskForTakeImage$app_release() {
        showDeniedPermissionsDialog(R.string.allow_storage);
    }

    private final void showDeniedPermissionsDialog(int i) {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.dialog = new AlertDialog.Builder(requireContext()).setMessage(i).setPositiveButton((int) R.string.label_ok, (DialogInterface.OnClickListener) ConversationFragment$showDeniedPermissionsDialog$1.INSTANCE).show();
    }

    public void showWarningMessage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new ConversationFragment$showWarningMessage$1(this));
        }
    }

    public void hideWarningMessage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new ConversationFragment$hideWarningMessage$1(this));
        }
    }
}
