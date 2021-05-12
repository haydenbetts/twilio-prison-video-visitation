package com.stripe.android.view.i18n;

import com.stripe.android.view.i18n.ErrorMessageTranslator;

public class TranslatorManager {
    private static final ErrorMessageTranslator DEFAULT_ERROR_MESSAGE_TRANSLATOR = new ErrorMessageTranslator.Default();
    private static ErrorMessageTranslator mErrorMessageTranslator;

    private TranslatorManager() {
    }

    public static ErrorMessageTranslator getErrorMessageTranslator() {
        ErrorMessageTranslator errorMessageTranslator = mErrorMessageTranslator;
        return errorMessageTranslator != null ? errorMessageTranslator : DEFAULT_ERROR_MESSAGE_TRANSLATOR;
    }

    public static void setErrorMessageTranslator(ErrorMessageTranslator errorMessageTranslator) {
        mErrorMessageTranslator = errorMessageTranslator;
    }
}
