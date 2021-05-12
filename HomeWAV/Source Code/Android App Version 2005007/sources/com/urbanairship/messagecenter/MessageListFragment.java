package com.urbanairship.messagecenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.urbanairship.Cancelable;
import com.urbanairship.Predicate;
import com.urbanairship.messagecenter.Inbox;
import com.urbanairship.util.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageListFragment extends Fragment {
    private AbsListView absListView;
    private MessageViewAdapter adapter;
    /* access modifiers changed from: private */
    public String currentMessageId;
    private Cancelable fetchMessagesOperation;
    private Inbox inbox;
    private final InboxListener inboxListener = new InboxListener() {
        public void onInboxUpdated() {
            MessageListFragment.this.updateAdapterMessages();
        }
    };
    private final List<OnListViewReadyCallback> pendingCallbacks = new ArrayList();
    /* access modifiers changed from: private */
    public int placeHolder = R.drawable.ua_ic_image_placeholder;
    private Predicate<Message> predicate;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout refreshLayout;

    public interface OnListViewReadyCallback {
        void onListViewReady(AbsListView absListView);
    }

    private List<Message> getMessages() {
        return this.inbox.getMessages(this.predicate);
    }

    /* access modifiers changed from: private */
    public void updateAdapterMessages() {
        if (getAdapter() != null) {
            getAdapter().set(getMessages());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.inbox = MessageCenter.shared().getInbox();
        updateAdapterMessages();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ua_fragment_message_list, viewGroup, false);
        ensureList(inflate);
        if (getAbsListView() == null) {
            return inflate;
        }
        getAbsListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Message message = MessageListFragment.this.getMessage(i);
                if (message != null) {
                    MessageCenter.shared().showMessageCenter(message.getMessageId());
                }
            }
        });
        View findViewById = inflate.findViewById(16908292);
        if (findViewById != null) {
            this.absListView.setEmptyView(findViewById);
        }
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ensureList(view);
        Iterator it = new ArrayList(this.pendingCallbacks).iterator();
        while (it.hasNext()) {
            ((OnListViewReadyCallback) it.next()).onListViewReady(this.absListView);
        }
        this.pendingCallbacks.clear();
    }

    private void ensureList(View view) {
        if (getContext() != null && this.absListView == null) {
            if (view instanceof AbsListView) {
                this.absListView = (AbsListView) view;
            } else {
                this.absListView = (AbsListView) view.findViewById(16908298);
            }
            if (this.absListView != null) {
                if (getAdapter() != null) {
                    this.absListView.setAdapter(getAdapter());
                }
                SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
                this.refreshLayout = swipeRefreshLayout;
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        public void onRefresh() {
                            MessageListFragment.this.onRefreshMessages();
                        }
                    });
                }
                View findViewById = view.findViewById(16908292);
                TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes((AttributeSet) null, R.styleable.MessageCenter, R.attr.messageCenterStyle, R.style.MessageCenter);
                if (findViewById instanceof TextView) {
                    TextView textView = (TextView) findViewById;
                    ViewUtils.applyTextStyle(getContext(), textView, obtainStyledAttributes.getResourceId(R.styleable.MessageCenter_messageCenterEmptyMessageTextAppearance, -1));
                    textView.setText(obtainStyledAttributes.getString(R.styleable.MessageCenter_messageCenterEmptyMessageText));
                }
                AbsListView absListView2 = this.absListView;
                if (absListView2 instanceof ListView) {
                    ListView listView = (ListView) absListView2;
                    if (obtainStyledAttributes.hasValue(R.styleable.MessageCenter_messageCenterDividerColor) && listView.getDivider() != null) {
                        DrawableCompat.setTint(listView.getDivider(), obtainStyledAttributes.getColor(R.styleable.MessageCenter_messageCenterDividerColor, -16777216));
                        DrawableCompat.setTintMode(listView.getDivider(), PorterDuff.Mode.SRC);
                    }
                }
                this.placeHolder = obtainStyledAttributes.getResourceId(R.styleable.MessageCenter_messageCenterItemIconPlaceholder, this.placeHolder);
                obtainStyledAttributes.recycle();
                return;
            }
            throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
        }
    }

    /* access modifiers changed from: protected */
    public MessageViewAdapter createMessageViewAdapter(Context context) {
        return new MessageViewAdapter(context, R.layout.ua_item_mc) {
            /* access modifiers changed from: protected */
            public void bindView(View view, Message message, final int i) {
                if (view instanceof MessageItemView) {
                    MessageItemView messageItemView = (MessageItemView) view;
                    messageItemView.updateMessage(message, MessageListFragment.this.placeHolder);
                    messageItemView.setHighlighted(message.getMessageId().equals(MessageListFragment.this.currentMessageId));
                    messageItemView.setSelectionListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (MessageListFragment.this.getAbsListView() != null) {
                                MessageListFragment.this.getAbsListView().setItemChecked(i, !MessageListFragment.this.getAbsListView().isItemChecked(i));
                            }
                        }
                    });
                }
            }
        };
    }

    public void onResume() {
        super.onResume();
        this.inbox.addListener(this.inboxListener);
        updateAdapterMessages();
        this.inbox.fetchMessages();
        if (getAbsListView() != null) {
            getAbsListView().invalidate();
        }
    }

    public void onPause() {
        super.onPause();
        this.inbox.removeListener(this.inboxListener);
        Cancelable cancelable = this.fetchMessagesOperation;
        if (cancelable != null) {
            cancelable.cancel();
        }
    }

    /* access modifiers changed from: private */
    public void onRefreshMessages() {
        Cancelable cancelable = this.fetchMessagesOperation;
        if (cancelable != null) {
            cancelable.cancel();
        }
        this.fetchMessagesOperation = this.inbox.fetchMessages(new Inbox.FetchMessagesCallback() {
            public void onFinished(boolean z) {
                if (MessageListFragment.this.refreshLayout != null) {
                    MessageListFragment.this.refreshLayout.setRefreshing(false);
                }
            }
        });
        SwipeRefreshLayout swipeRefreshLayout = this.refreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public AbsListView getAbsListView() {
        return this.absListView;
    }

    public void getAbsListViewAsync(OnListViewReadyCallback onListViewReadyCallback) {
        AbsListView absListView2 = this.absListView;
        if (absListView2 != null) {
            onListViewReadyCallback.onListViewReady(absListView2);
        } else {
            this.pendingCallbacks.add(onListViewReadyCallback);
        }
    }

    public Message getMessage(int i) {
        MessageViewAdapter messageViewAdapter = this.adapter;
        if (messageViewAdapter == null || messageViewAdapter.getCount() <= i) {
            return null;
        }
        return (Message) this.adapter.getItem(i);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.absListView.setChoiceMode(0);
        this.absListView = null;
        this.refreshLayout = null;
    }

    public void onDestroy() {
        super.onDestroy();
        this.pendingCallbacks.clear();
    }

    public MessageViewAdapter getAdapter() {
        if (this.adapter == null) {
            if (getContext() == null) {
                return null;
            }
            this.adapter = createMessageViewAdapter(getContext());
        }
        return this.adapter;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentMessage(String str) {
        String str2 = this.currentMessageId;
        if (str2 != null || str != null) {
            if (str2 == null || !str2.equals(str)) {
                this.currentMessageId = str;
                if (getAdapter() != null) {
                    getAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setPredicate(Predicate<Message> predicate2) {
        this.predicate = predicate2;
        if (getAdapter() != null) {
            updateAdapterMessages();
        }
    }
}
