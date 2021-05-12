package com.forasoft.homewavvisitor.ui.fragment.visits;

import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.presentation.presenter.visits.DateChooserPresenter;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.TextStyle;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/visits/DateChooserFragment$onViewCreated$1", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DateChooserFragment.kt */
public final class DateChooserFragment$onViewCreated$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ DateChooserFragment this$0;

    DateChooserFragment$onViewCreated$1(DateChooserFragment dateChooserFragment) {
        this.this$0 = dateChooserFragment;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            LocalDateTime plusMonths = LocalDateTime.now().plusMonths((long) ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition());
            DateChooserPresenter presenter = this.this$0.getPresenter();
            Intrinsics.checkExpressionValueIsNotNull(plusMonths, "current");
            presenter.onCalendarScrolled(plusMonths);
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tv_month);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_month");
            textView.setText(plusMonths.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
    }
}
