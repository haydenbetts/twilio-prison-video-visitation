package com.stripe.android.view;

import android.content.Context;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.stripe.android.R;
import com.stripe.android.model.Address;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.view.CountryAutoCompleteTextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShippingInfoWidget extends LinearLayout {
    public static final String ADDRESS_LINE_ONE_FIELD = "address_line_one";
    public static final String ADDRESS_LINE_TWO_FIELD = "address_line_two";
    public static final String CITY_FIELD = "city";
    public static final String PHONE_FIELD = "phone";
    public static final String POSTAL_CODE_FIELD = "postal_code";
    public static final String STATE_FIELD = "state";
    private StripeEditText mAddressEditText;
    private StripeEditText mAddressEditText2;
    private TextInputLayout mAddressLine1TextInputLayout;
    private TextInputLayout mAddressLine2TextInputLayout;
    private StripeEditText mCityEditText;
    private TextInputLayout mCityTextInputLayout;
    /* access modifiers changed from: private */
    public CountryAutoCompleteTextView mCountryAutoCompleteTextView;
    private List<String> mHiddenShippingInfoFields = new ArrayList();
    private StripeEditText mNameEditText;
    private TextInputLayout mNameTextInputLayout;
    private List<String> mOptionalShippingInfoFields = new ArrayList();
    private StripeEditText mPhoneNumberEditText;
    private TextInputLayout mPhoneNumberTextInputLayout;
    private StripeEditText mPostalCodeEditText;
    private TextInputLayout mPostalCodeTextInputLayout;
    private StripeEditText mStateEditText;
    private TextInputLayout mStateTextInputLayout;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CustomizableShippingField {
    }

    public ShippingInfoWidget(Context context) {
        super(context);
        initView();
    }

    public ShippingInfoWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public ShippingInfoWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    public void setOptionalFields(List<String> list) {
        if (list != null) {
            this.mOptionalShippingInfoFields = list;
        } else {
            this.mOptionalShippingInfoFields = new ArrayList();
        }
        renderLabels();
        renderCountrySpecificLabels(this.mCountryAutoCompleteTextView.getSelectedCountryCode());
    }

    public void setHiddenFields(List<String> list) {
        if (list != null) {
            this.mHiddenShippingInfoFields = list;
        } else {
            this.mHiddenShippingInfoFields = new ArrayList();
        }
        renderLabels();
        renderCountrySpecificLabels(this.mCountryAutoCompleteTextView.getSelectedCountryCode());
    }

    public ShippingInformation getShippingInformation() {
        if (!validateAllFields()) {
            return null;
        }
        return new ShippingInformation(new Address.Builder().setCity(this.mCityEditText.getText().toString()).setCountry(this.mCountryAutoCompleteTextView.getSelectedCountryCode()).setLine1(this.mAddressEditText.getText().toString()).setLine2(this.mAddressEditText2.getText().toString()).setPostalCode(this.mPostalCodeEditText.getText().toString()).setState(this.mStateEditText.getText().toString()).build(), this.mNameEditText.getText().toString(), this.mPhoneNumberEditText.getText().toString());
    }

    public void populateShippingInfo(ShippingInformation shippingInformation) {
        if (shippingInformation != null) {
            Address address = shippingInformation.getAddress();
            if (address != null) {
                this.mCityEditText.setText(address.getCity());
                if (address.getCountry() != null && !address.getCountry().isEmpty()) {
                    this.mCountryAutoCompleteTextView.setCountrySelected(address.getCountry());
                }
                this.mAddressEditText.setText(address.getLine1());
                this.mAddressEditText2.setText(address.getLine2());
                this.mPostalCodeEditText.setText(address.getPostalCode());
                this.mStateEditText.setText(address.getState());
            }
            this.mNameEditText.setText(shippingInformation.getName());
            this.mPhoneNumberEditText.setText(shippingInformation.getPhone());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x015a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean validateAllFields() {
        /*
            r9 = this;
            com.stripe.android.view.CountryAutoCompleteTextView r0 = r9.mCountryAutoCompleteTextView
            java.lang.String r0 = r0.getSelectedCountryCode()
            com.stripe.android.view.StripeEditText r1 = r9.mPostalCodeEditText
            android.text.Editable r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            boolean r1 = r1.isEmpty()
            r2 = 1
            if (r1 == 0) goto L_0x002b
            java.util.List<java.lang.String> r1 = r9.mOptionalShippingInfoFields
            java.lang.String r3 = "postal_code"
            boolean r1 = r1.contains(r3)
            if (r1 != 0) goto L_0x0029
            java.util.List<java.lang.String> r1 = r9.mHiddenShippingInfoFields
            boolean r1 = r1.contains(r3)
            if (r1 == 0) goto L_0x002b
        L_0x0029:
            r0 = 1
            goto L_0x009d
        L_0x002b:
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r1 = r1.getCountry()
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x004a
            com.stripe.android.view.StripeEditText r0 = r9.mPostalCodeEditText
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r0 = com.stripe.android.view.CountryUtils.isUSZipCodeValid(r0)
            goto L_0x009d
        L_0x004a:
            java.util.Locale r1 = java.util.Locale.UK
            java.lang.String r1 = r1.getCountry()
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0069
            com.stripe.android.view.StripeEditText r0 = r9.mPostalCodeEditText
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r0 = com.stripe.android.view.CountryUtils.isUKPostcodeValid(r0)
            goto L_0x009d
        L_0x0069:
            java.util.Locale r1 = java.util.Locale.CANADA
            java.lang.String r1 = r1.getCountry()
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0088
            com.stripe.android.view.StripeEditText r0 = r9.mPostalCodeEditText
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.trim()
            boolean r0 = com.stripe.android.view.CountryUtils.isCanadianPostalCodeValid(r0)
            goto L_0x009d
        L_0x0088:
            boolean r0 = com.stripe.android.view.CountryUtils.doesCountryUsePostalCode(r0)
            if (r0 == 0) goto L_0x0029
            com.stripe.android.view.StripeEditText r0 = r9.mPostalCodeEditText
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ r2
        L_0x009d:
            com.stripe.android.view.StripeEditText r1 = r9.mPostalCodeEditText
            r3 = r0 ^ 1
            r1.setShouldShowError(r3)
            com.stripe.android.view.StripeEditText r1 = r9.mAddressEditText
            android.text.Editable r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            boolean r1 = r1.isEmpty()
            r3 = 0
            if (r1 == 0) goto L_0x00c9
            java.util.List<java.lang.String> r1 = r9.mOptionalShippingInfoFields
            java.lang.String r4 = "address_line_one"
            boolean r1 = r1.contains(r4)
            if (r1 != 0) goto L_0x00c9
            java.util.List<java.lang.String> r1 = r9.mHiddenShippingInfoFields
            boolean r1 = r1.contains(r4)
            if (r1 != 0) goto L_0x00c9
            r1 = 1
            goto L_0x00ca
        L_0x00c9:
            r1 = 0
        L_0x00ca:
            com.stripe.android.view.StripeEditText r4 = r9.mAddressEditText
            r4.setShouldShowError(r1)
            com.stripe.android.view.StripeEditText r4 = r9.mCityEditText
            android.text.Editable r4 = r4.getText()
            java.lang.String r4 = r4.toString()
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x00f3
            java.util.List<java.lang.String> r4 = r9.mOptionalShippingInfoFields
            java.lang.String r5 = "city"
            boolean r4 = r4.contains(r5)
            if (r4 != 0) goto L_0x00f3
            java.util.List<java.lang.String> r4 = r9.mHiddenShippingInfoFields
            boolean r4 = r4.contains(r5)
            if (r4 != 0) goto L_0x00f3
            r4 = 1
            goto L_0x00f4
        L_0x00f3:
            r4 = 0
        L_0x00f4:
            com.stripe.android.view.StripeEditText r5 = r9.mCityEditText
            r5.setShouldShowError(r4)
            com.stripe.android.view.StripeEditText r5 = r9.mNameEditText
            android.text.Editable r5 = r5.getText()
            java.lang.String r5 = r5.toString()
            boolean r5 = r5.isEmpty()
            com.stripe.android.view.StripeEditText r6 = r9.mNameEditText
            r6.setShouldShowError(r5)
            com.stripe.android.view.StripeEditText r6 = r9.mStateEditText
            android.text.Editable r6 = r6.getText()
            java.lang.String r6 = r6.toString()
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x0130
            java.util.List<java.lang.String> r6 = r9.mOptionalShippingInfoFields
            java.lang.String r7 = "state"
            boolean r6 = r6.contains(r7)
            if (r6 != 0) goto L_0x0130
            java.util.List<java.lang.String> r6 = r9.mHiddenShippingInfoFields
            boolean r6 = r6.contains(r7)
            if (r6 != 0) goto L_0x0130
            r6 = 1
            goto L_0x0131
        L_0x0130:
            r6 = 0
        L_0x0131:
            com.stripe.android.view.StripeEditText r7 = r9.mStateEditText
            r7.setShouldShowError(r6)
            com.stripe.android.view.StripeEditText r7 = r9.mPhoneNumberEditText
            android.text.Editable r7 = r7.getText()
            java.lang.String r7 = r7.toString()
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x015a
            java.util.List<java.lang.String> r7 = r9.mOptionalShippingInfoFields
            java.lang.String r8 = "phone"
            boolean r7 = r7.contains(r8)
            if (r7 != 0) goto L_0x015a
            java.util.List<java.lang.String> r7 = r9.mHiddenShippingInfoFields
            boolean r7 = r7.contains(r8)
            if (r7 != 0) goto L_0x015a
            r7 = 1
            goto L_0x015b
        L_0x015a:
            r7 = 0
        L_0x015b:
            com.stripe.android.view.StripeEditText r8 = r9.mPhoneNumberEditText
            r8.setShouldShowError(r7)
            if (r0 == 0) goto L_0x016d
            if (r1 != 0) goto L_0x016d
            if (r4 != 0) goto L_0x016d
            if (r6 != 0) goto L_0x016d
            if (r5 != 0) goto L_0x016d
            if (r7 != 0) goto L_0x016d
            goto L_0x016e
        L_0x016d:
            r2 = 0
        L_0x016e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.view.ShippingInfoWidget.validateAllFields():boolean");
    }

    private void initView() {
        setOrientation(1);
        inflate(getContext(), R.layout.add_address_widget, this);
        this.mCountryAutoCompleteTextView = (CountryAutoCompleteTextView) findViewById(R.id.country_autocomplete_aaw);
        this.mAddressLine1TextInputLayout = (TextInputLayout) findViewById(R.id.tl_address_line1_aaw);
        this.mAddressLine2TextInputLayout = (TextInputLayout) findViewById(R.id.tl_address_line2_aaw);
        this.mCityTextInputLayout = (TextInputLayout) findViewById(R.id.tl_city_aaw);
        this.mNameTextInputLayout = (TextInputLayout) findViewById(R.id.tl_name_aaw);
        this.mPostalCodeTextInputLayout = (TextInputLayout) findViewById(R.id.tl_postal_code_aaw);
        this.mStateTextInputLayout = (TextInputLayout) findViewById(R.id.tl_state_aaw);
        this.mAddressEditText = (StripeEditText) findViewById(R.id.et_address_line_one_aaw);
        this.mAddressEditText2 = (StripeEditText) findViewById(R.id.et_address_line_two_aaw);
        this.mCityEditText = (StripeEditText) findViewById(R.id.et_city_aaw);
        this.mNameEditText = (StripeEditText) findViewById(R.id.et_name_aaw);
        this.mPostalCodeEditText = (StripeEditText) findViewById(R.id.et_postal_code_aaw);
        this.mStateEditText = (StripeEditText) findViewById(R.id.et_state_aaw);
        this.mPhoneNumberEditText = (StripeEditText) findViewById(R.id.et_phone_number_aaw);
        this.mPhoneNumberTextInputLayout = (TextInputLayout) findViewById(R.id.tl_phone_number_aaw);
        this.mCountryAutoCompleteTextView.setCountryChangeListener(new CountryAutoCompleteTextView.CountryChangeListener() {
            public void onCountryChanged(String str) {
                ShippingInfoWidget shippingInfoWidget = ShippingInfoWidget.this;
                shippingInfoWidget.renderCountrySpecificLabels(shippingInfoWidget.mCountryAutoCompleteTextView.getSelectedCountryCode());
            }
        });
        this.mPhoneNumberEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        setupErrorHandling();
        renderLabels();
        renderCountrySpecificLabels(this.mCountryAutoCompleteTextView.getSelectedCountryCode());
    }

    private void setupErrorHandling() {
        this.mAddressEditText.setErrorMessageListener(new ErrorListener(this.mAddressLine1TextInputLayout));
        this.mCityEditText.setErrorMessageListener(new ErrorListener(this.mCityTextInputLayout));
        this.mNameEditText.setErrorMessageListener(new ErrorListener(this.mNameTextInputLayout));
        this.mPostalCodeEditText.setErrorMessageListener(new ErrorListener(this.mPostalCodeTextInputLayout));
        this.mStateEditText.setErrorMessageListener(new ErrorListener(this.mStateTextInputLayout));
        this.mPhoneNumberEditText.setErrorMessageListener(new ErrorListener(this.mPhoneNumberTextInputLayout));
        this.mAddressEditText.setErrorMessage(getResources().getString(R.string.address_required));
        this.mCityEditText.setErrorMessage(getResources().getString(R.string.address_city_required));
        this.mNameEditText.setErrorMessage(getResources().getString(R.string.address_name_required));
        this.mPhoneNumberEditText.setErrorMessage(getResources().getString(R.string.address_phone_number_required));
    }

    private void renderLabels() {
        this.mNameTextInputLayout.setHint(getResources().getString(R.string.address_label_name));
        if (this.mOptionalShippingInfoFields.contains("city")) {
            this.mCityTextInputLayout.setHint(getResources().getString(R.string.address_label_city_optional));
        } else {
            this.mCityTextInputLayout.setHint(getResources().getString(R.string.address_label_city));
        }
        if (this.mOptionalShippingInfoFields.contains(PHONE_FIELD)) {
            this.mPhoneNumberTextInputLayout.setHint(getResources().getString(R.string.address_label_phone_number_optional));
        } else {
            this.mPhoneNumberTextInputLayout.setHint(getResources().getString(R.string.address_label_phone_number));
        }
        hideHiddenFields();
    }

    private void hideHiddenFields() {
        if (this.mHiddenShippingInfoFields.contains(ADDRESS_LINE_ONE_FIELD)) {
            this.mAddressLine1TextInputLayout.setVisibility(8);
        }
        if (this.mHiddenShippingInfoFields.contains(ADDRESS_LINE_TWO_FIELD)) {
            this.mAddressLine2TextInputLayout.setVisibility(8);
        }
        if (this.mHiddenShippingInfoFields.contains("state")) {
            this.mStateTextInputLayout.setVisibility(8);
        }
        if (this.mHiddenShippingInfoFields.contains("city")) {
            this.mCityTextInputLayout.setVisibility(8);
        }
        if (this.mHiddenShippingInfoFields.contains("postal_code")) {
            this.mPostalCodeTextInputLayout.setVisibility(8);
        }
        if (this.mHiddenShippingInfoFields.contains(PHONE_FIELD)) {
            this.mPhoneNumberTextInputLayout.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void renderCountrySpecificLabels(String str) {
        if (str.equals(Locale.US.getCountry())) {
            renderUSForm();
        } else if (str.equals(Locale.UK.getCountry())) {
            renderGreatBritainForm();
        } else if (str.equals(Locale.CANADA.getCountry())) {
            renderCanadianForm();
        } else {
            renderInternationalForm();
        }
        if (!CountryUtils.doesCountryUsePostalCode(str) || this.mHiddenShippingInfoFields.contains("postal_code")) {
            this.mPostalCodeTextInputLayout.setVisibility(8);
        } else {
            this.mPostalCodeTextInputLayout.setVisibility(0);
        }
    }

    private void renderUSForm() {
        if (this.mOptionalShippingInfoFields.contains(ADDRESS_LINE_ONE_FIELD)) {
            this.mAddressLine1TextInputLayout.setHint(getResources().getString(R.string.address_label_address_optional));
        } else {
            this.mAddressLine1TextInputLayout.setHint(getResources().getString(R.string.address_label_address));
        }
        this.mAddressLine2TextInputLayout.setHint(getResources().getString(R.string.address_label_apt_optional));
        if (this.mOptionalShippingInfoFields.contains("postal_code")) {
            this.mPostalCodeTextInputLayout.setHint(getResources().getString(R.string.address_label_zip_code_optional));
        } else {
            this.mPostalCodeTextInputLayout.setHint(getResources().getString(R.string.address_label_zip_code));
        }
        if (this.mOptionalShippingInfoFields.contains("state")) {
            this.mStateTextInputLayout.setHint(getResources().getString(R.string.address_label_state_optional));
        } else {
            this.mStateTextInputLayout.setHint(getResources().getString(R.string.address_label_state));
        }
        this.mPostalCodeEditText.setErrorMessage(getResources().getString(R.string.address_zip_invalid));
        this.mStateEditText.setErrorMessage(getResources().getString(R.string.address_state_required));
    }

    private void renderGreatBritainForm() {
        if (this.mOptionalShippingInfoFields.contains(ADDRESS_LINE_ONE_FIELD)) {
            this.mAddressLine1TextInputLayout.setHint(getResources().getString(R.string.address_label_address_line1_optional));
        } else {
            this.mAddressLine1TextInputLayout.setHint(getResources().getString(R.string.address_label_address_line1));
        }
        this.mAddressLine2TextInputLayout.setHint(getResources().getString(R.string.address_label_address_line2_optional));
        if (this.mOptionalShippingInfoFields.contains("postal_code")) {
            this.mPostalCodeTextInputLayout.setHint(getResources().getString(R.string.address_label_postcode_optional));
        } else {
            this.mPostalCodeTextInputLayout.setHint(getResources().getString(R.string.address_label_postcode));
        }
        if (this.mOptionalShippingInfoFields.contains("state")) {
            this.mStateTextInputLayout.setHint(getResources().getString(R.string.address_label_county_optional));
        } else {
            this.mStateTextInputLayout.setHint(getResources().getString(R.string.address_label_county));
        }
        this.mPostalCodeEditText.setErrorMessage(getResources().getString(R.string.address_postcode_invalid));
        this.mStateEditText.setErrorMessage(getResources().getString(R.string.address_county_required));
    }

    private void renderCanadianForm() {
        if (this.mOptionalShippingInfoFields.contains(ADDRESS_LINE_ONE_FIELD)) {
            this.mAddressLine1TextInputLayout.setHint(getResources().getString(R.string.address_label_address_optional));
        } else {
            this.mAddressLine1TextInputLayout.setHint(getResources().getString(R.string.address_label_address));
        }
        this.mAddressLine2TextInputLayout.setHint(getResources().getString(R.string.address_label_apt_optional));
        if (this.mOptionalShippingInfoFields.contains("postal_code")) {
            this.mPostalCodeTextInputLayout.setHint(getResources().getString(R.string.address_label_postal_code_optional));
        } else {
            this.mPostalCodeTextInputLayout.setHint(getResources().getString(R.string.address_label_postal_code));
        }
        if (this.mOptionalShippingInfoFields.contains("state")) {
            this.mStateTextInputLayout.setHint(getResources().getString(R.string.address_label_province_optional));
        } else {
            this.mStateTextInputLayout.setHint(getResources().getString(R.string.address_label_province));
        }
        this.mPostalCodeEditText.setErrorMessage(getResources().getString(R.string.address_postal_code_invalid));
        this.mStateEditText.setErrorMessage(getResources().getString(R.string.address_province_required));
    }

    private void renderInternationalForm() {
        if (this.mOptionalShippingInfoFields.contains(ADDRESS_LINE_ONE_FIELD)) {
            this.mAddressLine1TextInputLayout.setHint(getResources().getString(R.string.address_label_address_line1_optional));
        } else {
            this.mAddressLine1TextInputLayout.setHint(getResources().getString(R.string.address_label_address_line1));
        }
        this.mAddressLine2TextInputLayout.setHint(getResources().getString(R.string.address_label_address_line2_optional));
        if (this.mOptionalShippingInfoFields.contains("postal_code")) {
            this.mPostalCodeTextInputLayout.setHint(getResources().getString(R.string.address_label_zip_postal_code_optional));
        } else {
            this.mPostalCodeTextInputLayout.setHint(getResources().getString(R.string.address_label_zip_postal_code));
        }
        if (this.mOptionalShippingInfoFields.contains("state")) {
            this.mStateTextInputLayout.setHint(getResources().getString(R.string.address_label_region_generic_optional));
        } else {
            this.mStateTextInputLayout.setHint(getResources().getString(R.string.address_label_region_generic));
        }
        this.mPostalCodeEditText.setErrorMessage(getResources().getString(R.string.address_zip_postal_invalid));
        this.mStateEditText.setErrorMessage(getResources().getString(R.string.address_region_generic_required));
    }
}
