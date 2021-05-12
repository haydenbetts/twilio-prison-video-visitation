package com.stripe.android.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.stripe.android.R;
import com.stripe.android.model.Customer;
import com.stripe.android.model.CustomerSource;
import com.stripe.android.model.Source;
import java.util.ArrayList;
import java.util.List;

class MaskedCardAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int NO_SELECTION = -1;
    private List<CustomerSource> mCustomerSourceList = new ArrayList();
    private int mSelectedIndex = -1;

    MaskedCardAdapter(List<CustomerSource> list) {
        setCustomerSourceList(list);
    }

    /* access modifiers changed from: package-private */
    public void setCustomerSourceList(List<CustomerSource> list) {
        this.mCustomerSourceList.clear();
        addCustomerSourceIfSupported((CustomerSource[]) list.toArray(new CustomerSource[list.size()]));
    }

    /* access modifiers changed from: package-private */
    public void updateCustomer(Customer customer) {
        this.mCustomerSourceList.clear();
        addCustomerSourceIfSupported((CustomerSource[]) customer.getSources().toArray(new CustomerSource[customer.getSources().size()]));
        String defaultSource = customer.getDefaultSource();
        if (defaultSource == null) {
            updateSelectedIndex(-1);
        } else {
            setSelectedSource(defaultSource);
        }
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.mCustomerSourceList.size();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setMaskedCardData(this.mCustomerSourceList.get(i));
        viewHolder.setIndex(i);
        viewHolder.setSelected(i == this.mSelectedIndex);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder((FrameLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.masked_card_row, viewGroup, false));
    }

    /* access modifiers changed from: package-private */
    public boolean setSelectedSource(String str) {
        for (int i = 0; i < this.mCustomerSourceList.size(); i++) {
            if (str.equals(this.mCustomerSourceList.get(i).getId())) {
                updateSelectedIndex(i);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public CustomerSource getSelectedSource() {
        int i = this.mSelectedIndex;
        if (i == -1) {
            return null;
        }
        return this.mCustomerSourceList.get(i);
    }

    /* access modifiers changed from: package-private */
    public void addCustomerSourceIfSupported(CustomerSource... customerSourceArr) {
        if (customerSourceArr != null) {
            for (CustomerSource customerSource : customerSourceArr) {
                if (customerSource.asCard() != null || canDisplaySource(customerSource.asSource())) {
                    this.mCustomerSourceList.add(customerSource);
                }
            }
            notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean canDisplaySource(Source source) {
        return source != null && "card".equals(source.getType());
    }

    /* access modifiers changed from: package-private */
    public void updateSelectedIndex(int i) {
        this.mSelectedIndex = i;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        int index;
        MaskedCardView maskedCardView;

        ViewHolder(FrameLayout frameLayout) {
            super(frameLayout);
            this.maskedCardView = (MaskedCardView) frameLayout.findViewById(R.id.masked_card_item);
            frameLayout.setOnClickListener(new View.OnClickListener(MaskedCardAdapter.this) {
                public void onClick(View view) {
                    if (!ViewHolder.this.maskedCardView.isSelected()) {
                        ViewHolder.this.maskedCardView.toggleSelected();
                        MaskedCardAdapter.this.updateSelectedIndex(ViewHolder.this.index);
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void setMaskedCardData(CustomerSource customerSource) {
            this.maskedCardView.setCustomerSource(customerSource);
        }

        /* access modifiers changed from: package-private */
        public void setIndex(int i) {
            this.index = i;
        }

        /* access modifiers changed from: package-private */
        public void setSelected(boolean z) {
            this.maskedCardView.setSelected(z);
        }
    }
}
