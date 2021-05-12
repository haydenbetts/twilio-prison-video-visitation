package com.thoughtbot.expandablerecyclerview;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import java.util.List;

public abstract class MultiTypeExpandableRecyclerViewAdapter<GVH extends GroupViewHolder, CVH extends ChildViewHolder> extends ExpandableRecyclerViewAdapter<GVH, CVH> {
    public boolean isChild(int i) {
        return i == 1;
    }

    public boolean isGroup(int i) {
        return i == 2;
    }

    public MultiTypeExpandableRecyclerViewAdapter(List<? extends ExpandableGroup> list) {
        super(list);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (isGroup(i)) {
            GroupViewHolder onCreateGroupViewHolder = onCreateGroupViewHolder(viewGroup, i);
            onCreateGroupViewHolder.setOnGroupClickListener(this);
            return onCreateGroupViewHolder;
        } else if (isChild(i)) {
            return onCreateChildViewHolder(viewGroup, i);
        } else {
            throw new IllegalArgumentException("viewType is not valid");
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ExpandableListPosition unflattenedPosition = this.expandableList.getUnflattenedPosition(i);
        ExpandableGroup expandableGroup = this.expandableList.getExpandableGroup(unflattenedPosition);
        if (isGroup(getItemViewType(i))) {
            onBindGroupViewHolder((GroupViewHolder) viewHolder, i, expandableGroup);
        } else if (isChild(getItemViewType(i))) {
            onBindChildViewHolder((ChildViewHolder) viewHolder, i, expandableGroup, unflattenedPosition.childPos);
        }
    }

    public int getItemViewType(int i) {
        ExpandableListPosition unflattenedPosition = this.expandableList.getUnflattenedPosition(i);
        ExpandableGroup expandableGroup = this.expandableList.getExpandableGroup(unflattenedPosition);
        int i2 = unflattenedPosition.type;
        if (i2 == 1) {
            return getChildViewType(i, expandableGroup, unflattenedPosition.childPos);
        }
        if (i2 != 2) {
            return i2;
        }
        return getGroupViewType(i, expandableGroup);
    }

    public int getChildViewType(int i, ExpandableGroup expandableGroup, int i2) {
        return super.getItemViewType(i);
    }

    public int getGroupViewType(int i, ExpandableGroup expandableGroup) {
        return super.getItemViewType(i);
    }
}
