package com.forasoft.homewavvisitor.ui.fragment.conversations;

import air.HomeWAV.R;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Chat;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationsPresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationsView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.google.android.material.snackbar.Snackbar;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.jetbrains.anko.Sdk27PropertiesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010 \u001a\u00020\u0006H\u0007J\b\u0010!\u001a\u00020\fH\u0002J\b\u0010\"\u001a\u00020\fH\u0002J\u0010\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\fH\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006'"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ConversationListFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/conversations/ConversationsView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationsPresenter;)V", "displayConversations", "", "chats", "", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "setupList", "setupToolbar", "showDeleteDialog", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "showSuccessfulDeletionMessage", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationListFragment.kt */
public final class ConversationListFragment extends BaseFragment implements ConversationsView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public ConversationsPresenter presenter;

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

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final ConversationsPresenter getPresenter() {
        ConversationsPresenter conversationsPresenter = this.presenter;
        if (conversationsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return conversationsPresenter;
    }

    public final void setPresenter(ConversationsPresenter conversationsPresenter) {
        Intrinsics.checkParameterIsNotNull(conversationsPresenter, "<set-?>");
        this.presenter = conversationsPresenter;
    }

    @ProvidePresenter
    public final ConversationsPresenter providePresenter() {
        Object instance = getScope().getInstance(ConversationsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Conver…onsPresenter::class.java)");
        return (ConversationsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_conversation_list, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…n_list, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        setupList();
        setupToolbar();
        ConversationsPresenter conversationsPresenter = this.presenter;
        if (conversationsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationsPresenter.getNotificationsCount();
    }

    private final void setupList() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_conversations);
        recyclerView.setAdapter(new ConversationsAdapter(new ConversationListFragment$setupList$$inlined$apply$lambda$1(this)));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        Context context = recyclerView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "this");
        new ItemTouchHelper(new ConversationListFragment$setupList$$inlined$apply$lambda$2(recyclerView, context, recyclerView, this)).attachToRecyclerView(recyclerView);
    }

    /* access modifiers changed from: private */
    public final void showDeleteDialog(Inmate inmate) {
        new AlertDialog.Builder(getContext()).setTitle(getString(R.string.dialog_delete_chat_title, inmate.getFull_name())).setMessage(getString(R.string.dialog_delete_chat_message)).setPositiveButton(getString(R.string.dialog_delete_button), new ConversationListFragment$showDeleteDialog$1(this, inmate)).setNegativeButton(getString(R.string.dialog_cancel_button), ConversationListFragment$showDeleteDialog$2.INSTANCE).show();
    }

    private final void setupToolbar() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) requireContext().getString(R.string.label_messaging));
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(false);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void displayConversations(List<Chat> list) {
        Intrinsics.checkParameterIsNotNull(list, "chats");
        CommonKt.hide((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar));
        if (list.isEmpty()) {
            CommonKt.show((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
        } else {
            CommonKt.hide((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_conversations);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_conversations");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((ConversationsAdapter) adapter).submitList(CollectionsKt.sortedWith(list, new ConversationListFragment$displayConversations$$inlined$sortedByDescending$1()));
            ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_conversations)).smoothScrollToPosition(0);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.ConversationsAdapter");
    }

    public void showSuccessfulDeletionMessage() {
        Snackbar make = Snackbar.make(requireView(), (CharSequence) getString(R.string.chat_deleted), 0);
        Intrinsics.checkExpressionValueIsNotNull(make, "Snackbar.make(requireVie…d), Snackbar.LENGTH_LONG)");
        View view = make.getView();
        Intrinsics.checkExpressionValueIsNotNull(view, "snackbar.view");
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        Sdk27PropertiesKt.setBackgroundColor(view, ContextExtensionsKt.getColorResource(requireContext, R.color.highlightGreenLight));
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        make.setActionTextColor(ContextExtensionsKt.getColorResource(requireContext2, R.color.white));
        make.setAction((CharSequence) getString(R.string.dialog_undo_button), (View.OnClickListener) new ConversationListFragment$showSuccessfulDeletionMessage$1(this));
        make.addCallback(new ConversationListFragment$showSuccessfulDeletionMessage$2(this));
        make.show();
    }

    public boolean onBackPressed() {
        ConversationsPresenter conversationsPresenter = this.presenter;
        if (conversationsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationsPresenter.onBackPressed();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        }
        ConversationsPresenter conversationsPresenter = this.presenter;
        if (conversationsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        conversationsPresenter.onNotificationsClicked();
        return true;
    }
}
