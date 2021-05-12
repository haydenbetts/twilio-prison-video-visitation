package com.urbanairship.iam;

class ImmediateDisplayCoordinator extends DisplayCoordinator {
    public boolean isReady() {
        return true;
    }

    public void onDisplayFinished(InAppMessage inAppMessage) {
    }

    public void onDisplayStarted(InAppMessage inAppMessage) {
    }

    ImmediateDisplayCoordinator() {
    }
}
