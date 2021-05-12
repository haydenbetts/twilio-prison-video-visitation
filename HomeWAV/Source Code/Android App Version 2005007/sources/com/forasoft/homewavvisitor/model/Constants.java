package com.forasoft.homewavvisitor.model;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/model/Constants;", "", "()V", "historyOrderList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getHistoryOrderList", "()Ljava/util/ArrayList;", "historyPeriodList", "getHistoryPeriodList", "relationshipList", "getRelationshipList", "statesAbbreviationsList", "", "getStatesAbbreviationsList", "()Ljava/util/List;", "statesList", "getStatesList", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Constants.kt */
public final class Constants {
    private final ArrayList<String> historyOrderList = CollectionsKt.arrayListOf("Date - Newest first", "Date - Oldest first");
    private final ArrayList<String> historyPeriodList = CollectionsKt.arrayListOf("This Week", "Last Week", "This Month", "Last 3 Months", "Last 6 Months", "This Year", "All Time");
    private final ArrayList<String> relationshipList = CollectionsKt.arrayListOf("Mother", "Father", "Sister", "Brother", "Husband", "Wife", "Friend", "Clergy", "Legal Counsel", "Grandparents", "Medical", "Other");
    private final List<String> statesAbbreviationsList = CollectionsKt.listOf("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "DC", "WV", "WI", "WY");
    private final List<String> statesList = CollectionsKt.listOf("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "Washington D.C.", "West Virginia", "Wisconsin", "Wyoming");

    public Constants() {
    }

    public final List<String> getStatesList() {
        return this.statesList;
    }

    public final List<String> getStatesAbbreviationsList() {
        return this.statesAbbreviationsList;
    }

    public final ArrayList<String> getRelationshipList() {
        return this.relationshipList;
    }

    public final ArrayList<String> getHistoryPeriodList() {
        return this.historyPeriodList;
    }

    public final ArrayList<String> getHistoryOrderList() {
        return this.historyOrderList;
    }
}
