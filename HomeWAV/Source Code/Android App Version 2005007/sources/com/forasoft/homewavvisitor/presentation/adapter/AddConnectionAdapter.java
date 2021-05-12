package com.forasoft.homewavvisitor.presentation.adapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.register.Connection;
import com.forasoft.homewavvisitor.ui.views.AddInmateView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0006\u0010\u0016\u001a\u00020\u0017J\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0015H\u0016J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0015H\u0016J\u001c\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00152\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006%"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/AddConnectionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/forasoft/homewavvisitor/presentation/adapter/AddConnectionAdapter$ViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "data", "", "Lcom/forasoft/homewavvisitor/model/data/register/Connection;", "getData", "()Ljava/util/List;", "observer", "Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$Observer;", "getObserver", "()Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$Observer;", "setObserver", "(Lcom/forasoft/homewavvisitor/ui/views/AddInmateView$Observer;)V", "addConnection", "", "connection", "getItemCount", "", "isComplete", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setInamtesList", "connectionPosition", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddConnectionAdapter.kt */
public final class AddConnectionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final List<Connection> data = new ArrayList(0);
    private AddInmateView.Observer observer;

    public AddConnectionAdapter(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public final List<Connection> getData() {
        return this.data;
    }

    public final AddInmateView.Observer getObserver() {
        return this.observer;
    }

    public final void setObserver(AddInmateView.Observer observer2) {
        this.observer = observer2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new ViewHolder(new AddInmateView(this.context));
    }

    public int getItemCount() {
        return this.data.size();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        viewHolder.getView().setConnection(this.data.get(i));
        viewHolder.getView().setPosition(i);
        viewHolder.getView().setObserver(this.observer);
    }

    public final void addConnection(Connection connection) {
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        this.data.add(connection);
        notifyItemInserted(this.data.size() - 1);
    }

    public final void setInamtesList(int i, List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        Timber.d("setInamtesList: " + i + ": " + list.size(), new Object[0]);
        this.data.get(i).setInmatesList(list);
    }

    public final boolean isComplete() {
        for (Connection isComplete : this.data) {
            if (isComplete.isComplete()) {
                return true;
            }
        }
        return false;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/AddConnectionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Lcom/forasoft/homewavvisitor/ui/views/AddInmateView;", "(Lcom/forasoft/homewavvisitor/ui/views/AddInmateView;)V", "getView", "()Lcom/forasoft/homewavvisitor/ui/views/AddInmateView;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AddConnectionAdapter.kt */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final AddInmateView view;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(AddInmateView addInmateView) {
            super(addInmateView);
            Intrinsics.checkParameterIsNotNull(addInmateView, "view");
            this.view = addInmateView;
        }

        public final AddInmateView getView() {
            return this.view;
        }
    }
}
