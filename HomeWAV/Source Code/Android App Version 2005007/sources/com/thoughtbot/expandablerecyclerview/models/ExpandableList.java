package com.thoughtbot.expandablerecyclerview.models;

import java.util.List;

public class ExpandableList {
    public boolean[] expandedGroupIndexes;
    public List<? extends ExpandableGroup> groups;

    public ExpandableList(List<? extends ExpandableGroup> list) {
        this.groups = list;
        this.expandedGroupIndexes = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.expandedGroupIndexes[i] = false;
        }
    }

    private int numberOfVisibleItemsInGroup(int i) {
        if (this.expandedGroupIndexes[i]) {
            return ((ExpandableGroup) this.groups.get(i)).getItemCount() + 1;
        }
        return 1;
    }

    public int getVisibleItemCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.groups.size(); i2++) {
            i += numberOfVisibleItemsInGroup(i2);
        }
        return i;
    }

    public ExpandableListPosition getUnflattenedPosition(int i) {
        int i2 = i;
        for (int i3 = 0; i3 < this.groups.size(); i3++) {
            int numberOfVisibleItemsInGroup = numberOfVisibleItemsInGroup(i3);
            if (i2 == 0) {
                return ExpandableListPosition.obtain(2, i3, -1, i);
            }
            if (i2 < numberOfVisibleItemsInGroup) {
                return ExpandableListPosition.obtain(1, i3, i2 - 1, i);
            }
            i2 -= numberOfVisibleItemsInGroup;
        }
        throw new RuntimeException("Unknown state");
    }

    public int getFlattenedGroupIndex(ExpandableListPosition expandableListPosition) {
        int i = expandableListPosition.groupPos;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += numberOfVisibleItemsInGroup(i3);
        }
        return i2;
    }

    public int getFlattenedGroupIndex(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += numberOfVisibleItemsInGroup(i3);
        }
        return i2;
    }

    public int getFlattenedGroupIndex(ExpandableGroup expandableGroup) {
        int indexOf = this.groups.indexOf(expandableGroup);
        int i = 0;
        for (int i2 = 0; i2 < indexOf; i2++) {
            i += numberOfVisibleItemsInGroup(i2);
        }
        return i;
    }

    public int getFlattenedChildIndex(long j) {
        return getFlattenedChildIndex(ExpandableListPosition.obtainPosition(j));
    }

    public int getFlattenedChildIndex(ExpandableListPosition expandableListPosition) {
        int i = expandableListPosition.groupPos;
        int i2 = expandableListPosition.childPos;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            i3 += numberOfVisibleItemsInGroup(i4);
        }
        return i3 + i2 + 1;
    }

    public int getFlattenedChildIndex(int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            i3 += numberOfVisibleItemsInGroup(i4);
        }
        return i3 + i2 + 1;
    }

    public int getFlattenedFirstChildIndex(int i) {
        return getFlattenedGroupIndex(i) + 1;
    }

    public int getFlattenedFirstChildIndex(ExpandableListPosition expandableListPosition) {
        return getFlattenedGroupIndex(expandableListPosition) + 1;
    }

    public int getExpandableGroupItemCount(ExpandableListPosition expandableListPosition) {
        return ((ExpandableGroup) this.groups.get(expandableListPosition.groupPos)).getItemCount();
    }

    public ExpandableGroup getExpandableGroup(ExpandableListPosition expandableListPosition) {
        return (ExpandableGroup) this.groups.get(expandableListPosition.groupPos);
    }
}
