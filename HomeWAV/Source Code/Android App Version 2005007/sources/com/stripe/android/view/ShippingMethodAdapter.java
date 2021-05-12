package com.stripe.android.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.stripe.android.model.ShippingMethod;
import java.util.ArrayList;
import java.util.List;

class ShippingMethodAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int mSelectedIndex = 0;
    private List<ShippingMethod> mShippingMethods = new ArrayList();

    ShippingMethodAdapter() {
    }

    public int getItemCount() {
        return this.mShippingMethods.size();
    }

    public long getItemId(int i) {
        return super.getItemId(i);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(new ShippingMethodView(viewGroup.getContext()));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setShippingMethod(this.mShippingMethods.get(i));
        viewHolder.setIndex(i);
        viewHolder.setUIAsSelected(i == this.mSelectedIndex);
    }

    /* access modifiers changed from: package-private */
    public ShippingMethod getSelectedShippingMethod() {
        return this.mShippingMethods.get(this.mSelectedIndex);
    }

    /* access modifiers changed from: package-private */
    public void setShippingMethods(List<ShippingMethod> list, ShippingMethod shippingMethod) {
        if (list != null) {
            this.mShippingMethods = list;
        }
        if (shippingMethod == null) {
            this.mSelectedIndex = 0;
        } else {
            this.mSelectedIndex = this.mShippingMethods.indexOf(shippingMethod);
        }
        notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    public void setSelectedIndex(int i) {
        this.mSelectedIndex = i;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        int index;
        ShippingMethodView shippingMethodView;

        ViewHolder(ShippingMethodView shippingMethodView2) {
            super(shippingMethodView2);
            this.shippingMethodView = shippingMethodView2;
            shippingMethodView2.setOnClickListener(new View.OnClickListener(ShippingMethodAdapter.this) {
                public void onClick(View view) {
                    ShippingMethodAdapter.this.setSelectedIndex(ViewHolder.this.index);
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void setShippingMethod(ShippingMethod shippingMethod) {
            this.shippingMethodView.setShippingMethod(shippingMethod);
        }

        /* access modifiers changed from: package-private */
        public void setUIAsSelected(boolean z) {
            this.shippingMethodView.setSelected(z);
        }

        /* access modifiers changed from: package-private */
        public void setIndex(int i) {
            this.index = i;
        }
    }
}
