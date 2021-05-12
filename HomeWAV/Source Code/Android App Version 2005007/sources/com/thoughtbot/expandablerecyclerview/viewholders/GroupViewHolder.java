package com.thoughtbot.expandablerecyclerview.viewholders;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;

public abstract class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private OnGroupClickListener listener;

    public void collapse() {
    }

    public void expand() {
    }

    public GroupViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
    }

    public void onClick(View view) {
        OnGroupClickListener onGroupClickListener = this.listener;
        if (onGroupClickListener == null) {
            return;
        }
        if (onGroupClickListener.onGroupClick(getAdapterPosition())) {
            collapse();
        } else {
            expand();
        }
    }

    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.listener = onGroupClickListener;
    }
}
