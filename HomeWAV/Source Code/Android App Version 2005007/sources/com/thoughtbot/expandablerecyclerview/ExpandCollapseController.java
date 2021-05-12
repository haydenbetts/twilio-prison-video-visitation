package com.thoughtbot.expandablerecyclerview;

import com.thoughtbot.expandablerecyclerview.listeners.ExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableList;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

public class ExpandCollapseController {
    private ExpandableList expandableList;
    private ExpandCollapseListener listener;

    public ExpandCollapseController(ExpandableList expandableList2, ExpandCollapseListener expandCollapseListener) {
        this.expandableList = expandableList2;
        this.listener = expandCollapseListener;
    }

    private void collapseGroup(ExpandableListPosition expandableListPosition) {
        this.expandableList.expandedGroupIndexes[expandableListPosition.groupPos] = false;
        ExpandCollapseListener expandCollapseListener = this.listener;
        if (expandCollapseListener != null) {
            expandCollapseListener.onGroupCollapsed(this.expandableList.getFlattenedGroupIndex(expandableListPosition) + 1, ((ExpandableGroup) this.expandableList.groups.get(expandableListPosition.groupPos)).getItemCount());
        }
    }

    private void expandGroup(ExpandableListPosition expandableListPosition) {
        this.expandableList.expandedGroupIndexes[expandableListPosition.groupPos] = true;
        ExpandCollapseListener expandCollapseListener = this.listener;
        if (expandCollapseListener != null) {
            expandCollapseListener.onGroupExpanded(this.expandableList.getFlattenedGroupIndex(expandableListPosition) + 1, ((ExpandableGroup) this.expandableList.groups.get(expandableListPosition.groupPos)).getItemCount());
        }
    }

    public boolean isGroupExpanded(ExpandableGroup expandableGroup) {
        return this.expandableList.expandedGroupIndexes[this.expandableList.groups.indexOf(expandableGroup)];
    }

    public boolean isGroupExpanded(int i) {
        return this.expandableList.expandedGroupIndexes[this.expandableList.getUnflattenedPosition(i).groupPos];
    }

    public boolean toggleGroup(int i) {
        ExpandableListPosition unflattenedPosition = this.expandableList.getUnflattenedPosition(i);
        boolean z = this.expandableList.expandedGroupIndexes[unflattenedPosition.groupPos];
        if (z) {
            collapseGroup(unflattenedPosition);
        } else {
            expandGroup(unflattenedPosition);
        }
        return z;
    }

    public boolean toggleGroup(ExpandableGroup expandableGroup) {
        ExpandableList expandableList2 = this.expandableList;
        ExpandableListPosition unflattenedPosition = expandableList2.getUnflattenedPosition(expandableList2.getFlattenedGroupIndex(expandableGroup));
        boolean isGroupExpanded = isGroupExpanded(unflattenedPosition.groupPos);
        if (isGroupExpanded) {
            collapseGroup(unflattenedPosition);
        } else {
            expandGroup(unflattenedPosition);
        }
        return isGroupExpanded;
    }
}
