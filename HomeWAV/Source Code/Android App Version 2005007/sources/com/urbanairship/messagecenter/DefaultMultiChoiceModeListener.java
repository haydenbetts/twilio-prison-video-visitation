package com.urbanairship.messagecenter;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import java.util.HashSet;
import java.util.Set;

public class DefaultMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {
    private final MessageListFragment messageListFragment;

    public void onDestroyActionMode(ActionMode actionMode) {
    }

    public DefaultMultiChoiceModeListener(MessageListFragment messageListFragment2) {
        this.messageListFragment = messageListFragment2;
    }

    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
        if (this.messageListFragment.getAbsListView() != null) {
            int checkedItemCount = this.messageListFragment.getAbsListView().getCheckedItemCount();
            actionMode.setTitle(this.messageListFragment.getResources().getQuantityString(R.plurals.ua_selected_count, checkedItemCount, new Object[]{Integer.valueOf(checkedItemCount)}));
            if (this.messageListFragment.getAdapter() != null) {
                this.messageListFragment.getAdapter().notifyDataSetChanged();
            }
            actionMode.invalidate();
        }
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Message message;
        boolean z = false;
        if (this.messageListFragment.getAbsListView() == null) {
            return false;
        }
        actionMode.getMenuInflater().inflate(R.menu.ua_mc_action_mode, menu);
        int checkedItemCount = this.messageListFragment.getAbsListView().getCheckedItemCount();
        actionMode.setTitle(this.messageListFragment.getResources().getQuantityString(R.plurals.ua_selected_count, checkedItemCount, new Object[]{Integer.valueOf(checkedItemCount)}));
        SparseBooleanArray checkedItemPositions = this.messageListFragment.getAbsListView().getCheckedItemPositions();
        int i = 0;
        while (true) {
            if (i < checkedItemPositions.size()) {
                if (checkedItemPositions.valueAt(i) && (message = this.messageListFragment.getMessage(checkedItemPositions.keyAt(i))) != null && !message.isRead()) {
                    z = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        menu.findItem(R.id.mark_read).setVisible(z);
        return true;
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Message message;
        boolean z = false;
        if (this.messageListFragment.getAbsListView() == null) {
            return false;
        }
        SparseBooleanArray checkedItemPositions = this.messageListFragment.getAbsListView().getCheckedItemPositions();
        int i = 0;
        while (true) {
            if (i < checkedItemPositions.size()) {
                if (checkedItemPositions.valueAt(i) && (message = this.messageListFragment.getMessage(checkedItemPositions.keyAt(i))) != null && !message.isRead()) {
                    z = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        menu.findItem(R.id.mark_read).setVisible(z);
        return true;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (this.messageListFragment.getAbsListView() == null) {
            return false;
        }
        if (menuItem.getItemId() == R.id.mark_read) {
            MessageCenter.shared().getInbox().markMessagesRead(getCheckedMessageIds());
            actionMode.finish();
        } else if (menuItem.getItemId() == R.id.delete) {
            MessageCenter.shared().getInbox().deleteMessages(getCheckedMessageIds());
            actionMode.finish();
        } else if (menuItem.getItemId() == R.id.select_all) {
            int count = this.messageListFragment.getAbsListView().getCount();
            for (int i = 0; i < count; i++) {
                this.messageListFragment.getAbsListView().setItemChecked(i, true);
            }
        }
        return true;
    }

    private Set<String> getCheckedMessageIds() {
        Message message;
        HashSet hashSet = new HashSet();
        if (this.messageListFragment.getAbsListView() == null) {
            return hashSet;
        }
        SparseBooleanArray checkedItemPositions = this.messageListFragment.getAbsListView().getCheckedItemPositions();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            if (checkedItemPositions.valueAt(i) && (message = this.messageListFragment.getMessage(checkedItemPositions.keyAt(i))) != null) {
                hashSet.add(message.getMessageId());
            }
        }
        return hashSet;
    }
}
