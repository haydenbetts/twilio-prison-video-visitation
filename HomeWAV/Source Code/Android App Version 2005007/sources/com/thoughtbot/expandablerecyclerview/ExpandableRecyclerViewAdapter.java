package com.thoughtbot.expandablerecyclerview;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.thoughtbot.expandablerecyclerview.listeners.ExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableList;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import java.util.List;

public abstract class ExpandableRecyclerViewAdapter<GVH extends GroupViewHolder, CVH extends ChildViewHolder> extends RecyclerView.Adapter implements ExpandCollapseListener, OnGroupClickListener {
    private static final String EXPAND_STATE_MAP = "expandable_recyclerview_adapter_expand_state_map";
    private ExpandCollapseController expandCollapseController;
    private GroupExpandCollapseListener expandCollapseListener;
    protected ExpandableList expandableList;
    private OnGroupClickListener groupClickListener;

    public abstract void onBindChildViewHolder(CVH cvh, int i, ExpandableGroup expandableGroup, int i2);

    public abstract void onBindGroupViewHolder(GVH gvh, int i, ExpandableGroup expandableGroup);

    public abstract CVH onCreateChildViewHolder(ViewGroup viewGroup, int i);

    public abstract GVH onCreateGroupViewHolder(ViewGroup viewGroup, int i);

    public ExpandableRecyclerViewAdapter(List<? extends ExpandableGroup> list) {
        ExpandableList expandableList2 = new ExpandableList(list);
        this.expandableList = expandableList2;
        this.expandCollapseController = new ExpandCollapseController(expandableList2, this);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return onCreateChildViewHolder(viewGroup, i);
        }
        if (i == 2) {
            GroupViewHolder onCreateGroupViewHolder = onCreateGroupViewHolder(viewGroup, i);
            onCreateGroupViewHolder.setOnGroupClickListener(this);
            return onCreateGroupViewHolder;
        }
        throw new IllegalArgumentException("viewType is not valid");
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ExpandableListPosition unflattenedPosition = this.expandableList.getUnflattenedPosition(i);
        ExpandableGroup expandableGroup = this.expandableList.getExpandableGroup(unflattenedPosition);
        int i2 = unflattenedPosition.type;
        if (i2 == 1) {
            onBindChildViewHolder((ChildViewHolder) viewHolder, i, expandableGroup, unflattenedPosition.childPos);
        } else if (i2 == 2) {
            onBindGroupViewHolder((GroupViewHolder) viewHolder, i, expandableGroup);
        }
    }

    public int getItemCount() {
        return this.expandableList.getVisibleItemCount();
    }

    public int getItemViewType(int i) {
        return this.expandableList.getUnflattenedPosition(i).type;
    }

    public void onGroupExpanded(int i, int i2) {
        if (i2 > 0) {
            notifyItemRangeInserted(i, i2);
            if (this.expandCollapseListener != null) {
                this.expandCollapseListener.onGroupExpanded((ExpandableGroup) getGroups().get(this.expandableList.getUnflattenedPosition(i).groupPos));
            }
        }
    }

    public void onGroupCollapsed(int i, int i2) {
        if (i2 > 0) {
            notifyItemRangeRemoved(i, i2);
            if (this.expandCollapseListener != null) {
                this.expandCollapseListener.onGroupCollapsed((ExpandableGroup) getGroups().get(this.expandableList.getUnflattenedPosition(i - 1).groupPos));
            }
        }
    }

    public boolean onGroupClick(int i) {
        OnGroupClickListener onGroupClickListener = this.groupClickListener;
        if (onGroupClickListener != null) {
            onGroupClickListener.onGroupClick(i);
        }
        return this.expandCollapseController.toggleGroup(i);
    }

    public boolean toggleGroup(int i) {
        return this.expandCollapseController.toggleGroup(i);
    }

    public boolean toggleGroup(ExpandableGroup expandableGroup) {
        return this.expandCollapseController.toggleGroup(expandableGroup);
    }

    public boolean isGroupExpanded(int i) {
        return this.expandCollapseController.isGroupExpanded(i);
    }

    public boolean isGroupExpanded(ExpandableGroup expandableGroup) {
        return this.expandCollapseController.isGroupExpanded(expandableGroup);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBooleanArray(EXPAND_STATE_MAP, this.expandableList.expandedGroupIndexes);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null && bundle.containsKey(EXPAND_STATE_MAP)) {
            this.expandableList.expandedGroupIndexes = bundle.getBooleanArray(EXPAND_STATE_MAP);
            notifyDataSetChanged();
        }
    }

    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.groupClickListener = onGroupClickListener;
    }

    public void setOnGroupExpandCollapseListener(GroupExpandCollapseListener groupExpandCollapseListener) {
        this.expandCollapseListener = groupExpandCollapseListener;
    }

    public List<? extends ExpandableGroup> getGroups() {
        return this.expandableList.groups;
    }
}
