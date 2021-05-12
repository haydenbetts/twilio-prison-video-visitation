package com.forasoft.homewavvisitor.model;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&J\b\u0010\f\u001a\u00020\u0003H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\nH&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&J\b\u0010\u0015\u001a\u00020\u0003H&J\b\u0010\u0016\u001a\u00020\u0003H&¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/model/Analytics;", "", "onAccountCreated", "", "onAddConnection", "onAddFunds", "onCall", "onClose", "onDeleteInmate", "prison", "", "state", "onDroppedCall", "onLogin", "pin", "onLogout", "onLowBalance", "onMoney", "onNoMoney", "onNormalBalance", "onOpen", "onSendMessage", "onViewingMessageCenter", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Analytics.kt */
public interface Analytics {
    void onAccountCreated();

    void onAddConnection();

    void onAddFunds();

    void onCall();

    void onClose();

    void onDeleteInmate(String str, String str2);

    void onDroppedCall();

    void onLogin(String str);

    void onLogout();

    void onLowBalance();

    void onMoney();

    void onNoMoney();

    void onNormalBalance();

    void onOpen();

    void onSendMessage();

    void onViewingMessageCenter();
}
