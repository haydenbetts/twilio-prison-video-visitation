package com.stripe.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import com.stripe.android.R;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class CountryAutoCompleteTextView extends FrameLayout {
    /* access modifiers changed from: private */
    public AutoCompleteTextView mCountryAutocomplete;
    private CountryChangeListener mCountryChangeListener;
    private Map<String, String> mCountryNameToCode;
    protected String mCountrySelected;

    interface CountryChangeListener {
        void onCountryChanged(String str);
    }

    public CountryAutoCompleteTextView(Context context) {
        super(context);
        initView();
    }

    public CountryAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public CountryAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    /* access modifiers changed from: package-private */
    public String getSelectedCountryCode() {
        return this.mCountrySelected;
    }

    /* access modifiers changed from: package-private */
    public void setCountrySelected(String str) {
        if (str != null) {
            updateUIForCountryEntered(new Locale("", str).getDisplayCountry());
        }
    }

    /* access modifiers changed from: package-private */
    public void setCountryChangeListener(CountryChangeListener countryChangeListener) {
        this.mCountryChangeListener = countryChangeListener;
    }

    private void initView() {
        inflate(getContext(), R.layout.country_autocomplete_textview, this);
        this.mCountryAutocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete_country_cat);
        this.mCountryNameToCode = CountryUtils.getCountryNameToCodeMap();
        CountryAdapter countryAdapter = new CountryAdapter(getContext(), new ArrayList(this.mCountryNameToCode.keySet()));
        this.mCountryAutocomplete.setThreshold(0);
        this.mCountryAutocomplete.setAdapter(countryAdapter);
        this.mCountryAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                CountryAutoCompleteTextView.this.updateUIForCountryEntered(CountryAutoCompleteTextView.this.mCountryAutocomplete.getText().toString());
            }
        });
        String str = (String) countryAdapter.getItem(0);
        updateUIForCountryEntered(str);
        this.mCountryAutocomplete.setText(str);
        this.mCountryAutocomplete.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                String obj = CountryAutoCompleteTextView.this.mCountryAutocomplete.getText().toString();
                if (z) {
                    CountryAutoCompleteTextView.this.mCountryAutocomplete.showDropDown();
                } else {
                    CountryAutoCompleteTextView.this.updateUIForCountryEntered(obj);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void updateUIForCountryEntered(String str) {
        String str2 = this.mCountryNameToCode.get(str);
        if (str2 != null) {
            String str3 = this.mCountrySelected;
            if (str3 == null || !str3.equals(str2)) {
                this.mCountrySelected = str2;
                CountryChangeListener countryChangeListener = this.mCountryChangeListener;
                if (countryChangeListener != null) {
                    countryChangeListener.onCountryChanged(str2);
                }
            }
            this.mCountryAutocomplete.setText(str);
        } else if (this.mCountrySelected != null) {
            this.mCountryAutocomplete.setText(new Locale("", this.mCountrySelected).getDisplayCountry());
        }
    }
}
