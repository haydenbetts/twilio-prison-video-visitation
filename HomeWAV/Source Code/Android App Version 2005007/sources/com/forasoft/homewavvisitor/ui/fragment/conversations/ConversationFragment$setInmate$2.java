package com.forasoft.homewavvisitor.ui.fragment.conversations;

import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "infos", "", "Landroidx/work/WorkInfo;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationFragment.kt */
final class ConversationFragment$setInmate$2<T> implements Observer<List<WorkInfo>> {
    final /* synthetic */ ConversationFragment this$0;

    ConversationFragment$setInmate$2(ConversationFragment conversationFragment) {
        this.this$0 = conversationFragment;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: androidx.work.WorkInfo$State} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: androidx.work.WorkInfo$State} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: androidx.work.WorkInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: androidx.work.WorkInfo$State} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: androidx.work.WorkInfo$State} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: androidx.work.WorkInfo$State} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onChanged(java.util.List<androidx.work.WorkInfo> r11) {
        /*
            r10 = this;
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r0 = r10.this$0
            java.util.List r0 = r0.runningWorks
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x000c:
            boolean r1 = r0.hasNext()
            r2 = 0
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0057
            java.lang.Object r1 = r0.next()
            r5 = r1
            androidx.work.WorkInfo r5 = (androidx.work.WorkInfo) r5
            if (r11 != 0) goto L_0x0021
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0021:
            r6 = r11
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L_0x0028:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0044
            java.lang.Object r7 = r6.next()
            r8 = r7
            androidx.work.WorkInfo r8 = (androidx.work.WorkInfo) r8
            java.util.UUID r8 = r8.getId()
            java.util.UUID r9 = r5.getId()
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)
            if (r8 == 0) goto L_0x0028
            goto L_0x0045
        L_0x0044:
            r7 = r3
        L_0x0045:
            androidx.work.WorkInfo r7 = (androidx.work.WorkInfo) r7
            if (r7 == 0) goto L_0x004d
            androidx.work.WorkInfo$State r3 = r7.getState()
        L_0x004d:
            androidx.work.WorkInfo$State r5 = androidx.work.WorkInfo.State.SUCCEEDED
            if (r3 != r5) goto L_0x0053
            r3 = 1
            goto L_0x0054
        L_0x0053:
            r3 = 0
        L_0x0054:
            if (r3 == 0) goto L_0x000c
            r3 = r1
        L_0x0057:
            androidx.work.WorkInfo r3 = (androidx.work.WorkInfo) r3
            java.lang.String r0 = "image"
            java.lang.String r1 = "video"
            if (r3 == 0) goto L_0x007c
            java.util.Set r5 = r3.getTags()
            boolean r5 = r5.contains(r1)
            if (r5 != 0) goto L_0x0074
            java.util.Set r3 = r3.getTags()
            boolean r3 = r3.contains(r0)
            if (r3 == 0) goto L_0x007c
        L_0x0074:
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r3 = r10.this$0
            r5 = 2131820867(0x7f110143, float:1.9274461E38)
            r3.showMessage((int) r5)
        L_0x007c:
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r3 = r10.this$0
            java.util.List r3 = r3.runningWorks
            r3.clear()
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r3 = r10.this$0
            java.util.List r3 = r3.runningWorks
            java.util.Collection r3 = (java.util.Collection) r3
            if (r11 != 0) goto L_0x0092
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0092:
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r11 = r11.iterator()
        L_0x009f:
            boolean r6 = r11.hasNext()
            if (r6 == 0) goto L_0x00d6
            java.lang.Object r6 = r11.next()
            r7 = r6
            androidx.work.WorkInfo r7 = (androidx.work.WorkInfo) r7
            java.util.Set r8 = r7.getTags()
            boolean r8 = r8.contains(r1)
            if (r8 != 0) goto L_0x00cf
            java.util.Set r8 = r7.getTags()
            boolean r8 = r8.contains(r0)
            if (r8 != 0) goto L_0x00cf
            java.util.Set r7 = r7.getTags()
            java.lang.String r8 = "resize"
            boolean r7 = r7.contains(r8)
            if (r7 == 0) goto L_0x00cd
            goto L_0x00cf
        L_0x00cd:
            r7 = 0
            goto L_0x00d0
        L_0x00cf:
            r7 = 1
        L_0x00d0:
            if (r7 == 0) goto L_0x009f
            r5.add(r6)
            goto L_0x009f
        L_0x00d6:
            java.util.List r5 = (java.util.List) r5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.Collection r11 = (java.util.Collection) r11
            java.util.Iterator r0 = r5.iterator()
        L_0x00e5:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0103
            java.lang.Object r1 = r0.next()
            r5 = r1
            androidx.work.WorkInfo r5 = (androidx.work.WorkInfo) r5
            androidx.work.WorkInfo$State r5 = r5.getState()
            androidx.work.WorkInfo$State r6 = androidx.work.WorkInfo.State.RUNNING
            if (r5 != r6) goto L_0x00fc
            r5 = 1
            goto L_0x00fd
        L_0x00fc:
            r5 = 0
        L_0x00fd:
            if (r5 == 0) goto L_0x00e5
            r11.add(r1)
            goto L_0x00e5
        L_0x0103:
            java.util.List r11 = (java.util.List) r11
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            kotlin.collections.CollectionsKt.addAll(r3, r11)
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r11 = r10.this$0
            java.util.List r11 = r11.runningWorks
            java.util.Collection r11 = (java.util.Collection) r11
            boolean r11 = r11.isEmpty()
            r11 = r11 ^ r4
            if (r11 == 0) goto L_0x0168
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r11 = r10.this$0
            int r0 = com.forasoft.homewavvisitor.R.id.layoutInfo
            android.view.View r11 = r11._$_findCachedViewById(r0)
            java.lang.String r0 = "layoutInfo"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r0)
            int r1 = com.forasoft.homewavvisitor.R.id.snackbar_text
            android.view.View r11 = r11.findViewById(r1)
            android.widget.TextView r11 = (android.widget.TextView) r11
            java.lang.String r1 = "layoutInfo.snackbar_text"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r1)
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r1 = r10.this$0
            r2 = 2131820953(0x7f110199, float:1.9274635E38)
            java.lang.String r1 = r1.getString(r2)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r11.setText(r1)
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r11 = r10.this$0
            int r1 = com.forasoft.homewavvisitor.R.id.layoutInfo
            android.view.View r11 = r11._$_findCachedViewById(r1)
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r0)
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r0 = r10.this$0
            android.content.Context r0 = r0.requireContext()
            r1 = 2131034233(0x7f050079, float:1.7678978E38)
            int r0 = androidx.core.content.ContextCompat.getColor(r0, r1)
            org.jetbrains.anko.Sdk27PropertiesKt.setBackgroundColor(r11, r0)
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r11 = r10.this$0
            int r0 = com.forasoft.homewavvisitor.R.id.layoutInfo
            android.view.View r11 = r11._$_findCachedViewById(r0)
            com.forasoft.homewavvisitor.extension.CommonKt.show(r11)
            goto L_0x0173
        L_0x0168:
            com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment r11 = r10.this$0
            int r0 = com.forasoft.homewavvisitor.R.id.layoutInfo
            android.view.View r11 = r11._$_findCachedViewById(r0)
            com.forasoft.homewavvisitor.extension.CommonKt.hide(r11)
        L_0x0173:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.conversations.ConversationFragment$setInmate$2.onChanged(java.util.List):void");
    }
}
