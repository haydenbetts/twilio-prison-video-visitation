package com.urbanairship.iam;

public interface InAppMessageListener {
    void onMessageDisplayed(String str, InAppMessage inAppMessage);

    void onMessageFinished(String str, InAppMessage inAppMessage, ResolutionInfo resolutionInfo);
}
