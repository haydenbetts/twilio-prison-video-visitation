package com.forasoft.homewavvisitor.ui.fragment.conversations;

import air.HomeWAV.R;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.ActionMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ActionModeCallback;", "Landroidx/appcompat/view/ActionMode$Callback;", "actionsListener", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ActionModeCallback$ActionsListener;", "(Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ActionModeCallback$ActionsListener;)V", "onActionItemClicked", "", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onDestroyActionMode", "", "onPrepareActionMode", "ActionsListener", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ActionModeCallback.kt */
public final class ActionModeCallback implements ActionMode.Callback {
    private ActionsListener actionsListener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ActionModeCallback$ActionsListener;", "", "onActionModeDestroyed", "", "onDeleteClicked", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ActionModeCallback.kt */
    public interface ActionsListener {
        void onActionModeDestroyed();

        void onDeleteClicked();
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkParameterIsNotNull(actionMode, "mode");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        return false;
    }

    public ActionModeCallback(ActionsListener actionsListener2) {
        Intrinsics.checkParameterIsNotNull(actionsListener2, "actionsListener");
        this.actionsListener = actionsListener2;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkParameterIsNotNull(actionMode, "mode");
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        actionMode.getMenuInflater().inflate(R.menu.menu_selection, menu);
        return true;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(actionMode, "mode");
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != R.id.action_delete) {
            return false;
        }
        this.actionsListener.onDeleteClicked();
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        Intrinsics.checkParameterIsNotNull(actionMode, "mode");
        this.actionsListener.onActionModeDestroyed();
    }
}
