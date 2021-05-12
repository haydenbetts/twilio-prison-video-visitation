package com.thoughtbot.expandablerecyclerview.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class ExpandableGroup<T extends Parcelable> implements Parcelable {
    public static final Parcelable.Creator<ExpandableGroup> CREATOR = new Parcelable.Creator<ExpandableGroup>() {
        public ExpandableGroup createFromParcel(Parcel parcel) {
            return new ExpandableGroup(parcel);
        }

        public ExpandableGroup[] newArray(int i) {
            return new ExpandableGroup[i];
        }
    };
    private List<T> items;
    private String title;

    public int describeContents() {
        return 0;
    }

    public ExpandableGroup(String str, List<T> list) {
        this.title = str;
        this.items = list;
    }

    public String getTitle() {
        return this.title;
    }

    public List<T> getItems() {
        return this.items;
    }

    public int getItemCount() {
        List<T> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public String toString() {
        return "ExpandableGroup{title='" + this.title + '\'' + ", items=" + this.items + '}';
    }

    protected ExpandableGroup(Parcel parcel) {
        this.title = parcel.readString();
        byte readByte = parcel.readByte();
        int readInt = parcel.readInt();
        if (readByte == 1) {
            this.items = new ArrayList(readInt);
            parcel.readList(this.items, ((Class) parcel.readSerializable()).getClassLoader());
            return;
        }
        this.items = null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        if (this.items == null) {
            parcel.writeByte((byte) 0);
            parcel.writeInt(0);
            return;
        }
        parcel.writeByte((byte) 1);
        parcel.writeInt(this.items.size());
        parcel.writeSerializable(((Parcelable) this.items.get(0)).getClass());
        parcel.writeList(this.items);
    }
}
