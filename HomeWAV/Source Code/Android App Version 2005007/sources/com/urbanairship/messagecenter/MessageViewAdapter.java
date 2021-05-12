package com.urbanairship.messagecenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MessageViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<Message> items = new ArrayList();
    private final int layout;

    /* access modifiers changed from: protected */
    public abstract void bindView(View view, Message message, int i);

    public boolean hasStableIds() {
        return true;
    }

    public MessageViewAdapter(Context context2, int i) {
        this.context = context2;
        this.layout = i;
    }

    public int getCount() {
        return this.items.size();
    }

    public Object getItem(int i) {
        if (i >= this.items.size() || i < 0) {
            return null;
        }
        return this.items.get(i);
    }

    public long getItemId(int i) {
        if (i >= this.items.size() || i < 0) {
            return -1;
        }
        return (long) this.items.get(i).getMessageId().hashCode();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(this.layout, viewGroup, false);
        }
        if (i < this.items.size() && i >= 0) {
            bindView(view, this.items.get(i), i);
        }
        return view;
    }

    public void set(Collection<Message> collection) {
        synchronized (this.items) {
            this.items.clear();
            this.items.addAll(collection);
        }
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }
}
