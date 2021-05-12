package com.paypal.android.sdk.onetouch.core.config;

import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OtcConfiguration {
    private final ArrayList<BillingAgreementRecipe> mBillingAgreementRecipesInDecreasingPriorityOrder = new ArrayList<>();
    private final ArrayList<CheckoutRecipe> mCheckoutRecipesInDecreasingPriorityOrder = new ArrayList<>();
    private String mFileTimestamp;
    private final ArrayList<OAuth2Recipe> mOauth2RecipesInDecreasingPriorityOrder = new ArrayList<>();

    public OtcConfiguration withOauth2Recipe(OAuth2Recipe oAuth2Recipe) {
        this.mOauth2RecipesInDecreasingPriorityOrder.add(oAuth2Recipe);
        return this;
    }

    public OtcConfiguration fileTimestamp(String str) {
        this.mFileTimestamp = str;
        return this;
    }

    public String getFileTimestamp() {
        return this.mFileTimestamp;
    }

    public CheckoutRecipe getBrowserCheckoutConfig() {
        Iterator<CheckoutRecipe> it = this.mCheckoutRecipesInDecreasingPriorityOrder.iterator();
        while (it.hasNext()) {
            CheckoutRecipe next = it.next();
            if (next.getTarget() == RequestTarget.browser) {
                return next;
            }
        }
        return null;
    }

    public BillingAgreementRecipe getBrowserBillingAgreementConfig() {
        Iterator<BillingAgreementRecipe> it = this.mBillingAgreementRecipesInDecreasingPriorityOrder.iterator();
        while (it.hasNext()) {
            BillingAgreementRecipe next = it.next();
            if (next.getTarget() == RequestTarget.browser) {
                return next;
            }
        }
        return null;
    }

    public List<OAuth2Recipe> getOauth2Recipes() {
        return new ArrayList(this.mOauth2RecipesInDecreasingPriorityOrder);
    }

    public void withCheckoutRecipe(CheckoutRecipe checkoutRecipe) {
        this.mCheckoutRecipesInDecreasingPriorityOrder.add(checkoutRecipe);
    }

    public List<CheckoutRecipe> getCheckoutRecipes() {
        return new ArrayList(this.mCheckoutRecipesInDecreasingPriorityOrder);
    }

    public void withBillingAgreementRecipe(BillingAgreementRecipe billingAgreementRecipe) {
        this.mBillingAgreementRecipesInDecreasingPriorityOrder.add(billingAgreementRecipe);
    }

    public List<BillingAgreementRecipe> getBillingAgreementRecipes() {
        return new ArrayList(this.mBillingAgreementRecipesInDecreasingPriorityOrder);
    }
}
