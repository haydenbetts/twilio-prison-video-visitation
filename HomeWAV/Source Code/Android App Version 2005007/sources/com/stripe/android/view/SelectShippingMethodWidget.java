package com.stripe.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stripe.android.R;
import com.stripe.android.model.ShippingMethod;
import java.util.List;

public class SelectShippingMethodWidget extends FrameLayout {
    ShippingMethodAdapter mShippingMethodAdapter;
    RecyclerView mShippingMethodRecyclerView;

    public SelectShippingMethodWidget(Context context) {
        super(context);
        initView();
    }

    public SelectShippingMethodWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public SelectShippingMethodWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    public ShippingMethod getSelectedShippingMethod() {
        return this.mShippingMethodAdapter.getSelectedShippingMethod();
    }

    public void setShippingMethods(List<ShippingMethod> list, ShippingMethod shippingMethod) {
        this.mShippingMethodAdapter.setShippingMethods(list, shippingMethod);
    }

    private void initView() {
        inflate(getContext(), R.layout.select_shipping_method_widget, this);
        this.mShippingMethodRecyclerView = (RecyclerView) findViewById(R.id.rv_shipping_methods_ssmw);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.mShippingMethodAdapter = new ShippingMethodAdapter();
        this.mShippingMethodRecyclerView.setHasFixedSize(true);
        this.mShippingMethodRecyclerView.setAdapter(this.mShippingMethodAdapter);
        this.mShippingMethodRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
