package com.hannesdorfmann.adapterdelegates4;

import java.util.List;

public class ListDelegationAdapter<T extends List<?>> extends AbsDelegationAdapter<T> {
    public ListDelegationAdapter() {
    }

    public ListDelegationAdapter(AdapterDelegatesManager<T> adapterDelegatesManager) {
        super(adapterDelegatesManager);
    }

    public ListDelegationAdapter(AdapterDelegate<T>... adapterDelegateArr) {
        super(adapterDelegateArr);
    }

    public int getItemCount() {
        if (this.items == null) {
            return 0;
        }
        return ((List) this.items).size();
    }
}
