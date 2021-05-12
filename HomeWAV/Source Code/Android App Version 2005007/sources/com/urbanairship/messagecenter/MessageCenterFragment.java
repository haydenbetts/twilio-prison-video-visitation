package com.urbanairship.messagecenter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import com.urbanairship.Predicate;
import com.urbanairship.messagecenter.MessageListFragment;
import com.urbanairship.util.ViewUtils;
import java.util.List;

public class MessageCenterFragment extends Fragment {
    private static final String STATE_ABS_LIST_VIEW = "listView";
    private static final String STATE_CURRENT_MESSAGE_ID = "currentMessageId";
    private static final String STATE_CURRENT_MESSAGE_POSITION = "currentMessagePosition";
    private static final String STATE_PENDING_MESSAGE_ID = "pendingMessageId";
    private String currentMessageId;
    private int currentMessagePosition = -1;
    private final InboxListener inboxListener = new InboxListener() {
        public void onInboxUpdated() {
            MessageCenterFragment.this.updateCurrentMessage();
        }
    };
    private boolean isTwoPane;
    private boolean isViewConfigured;
    private MessageListFragment messageListFragment;
    private String pendingMessageId;
    private Predicate<Message> predicate;

    public static MessageCenterFragment newInstance(String str) {
        MessageCenterFragment messageCenterFragment = new MessageCenterFragment();
        Bundle bundle = new Bundle();
        bundle.putString("messageId", str);
        messageCenterFragment.setArguments(bundle);
        return messageCenterFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.currentMessagePosition = bundle.getInt(STATE_CURRENT_MESSAGE_POSITION, -1);
            this.currentMessageId = bundle.getString(STATE_CURRENT_MESSAGE_ID, (String) null);
            this.pendingMessageId = bundle.getString(STATE_PENDING_MESSAGE_ID, (String) null);
        } else if (getArguments() != null) {
            this.pendingMessageId = getArguments().getString("messageId");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ua_fragment_mc, viewGroup, false);
        configureView(inflate);
        return inflate;
    }

    public void onViewCreated(View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        configureView(view);
        this.messageListFragment.setPredicate(this.predicate);
        if (bundle != null && bundle.containsKey(STATE_ABS_LIST_VIEW)) {
            this.messageListFragment.getAbsListViewAsync(new MessageListFragment.OnListViewReadyCallback() {
                public void onListViewReady(AbsListView absListView) {
                    absListView.onRestoreInstanceState(bundle.getParcelable(MessageCenterFragment.STATE_ABS_LIST_VIEW));
                }
            });
        }
    }

    private void configureView(View view) {
        if (getActivity() != null && !this.isViewConfigured) {
            this.isViewConfigured = true;
            if (view.findViewById(R.id.message_list_container) != null) {
                this.messageListFragment = new MessageListFragment();
                getChildFragmentManager().beginTransaction().replace(R.id.message_list_container, (Fragment) this.messageListFragment, "messageList").commit();
                if (view.findViewById(R.id.message_container) != null) {
                    this.isTwoPane = true;
                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.container);
                    TypedArray obtainStyledAttributes = getActivity().getTheme().obtainStyledAttributes((AttributeSet) null, R.styleable.MessageCenter, R.attr.messageCenterStyle, R.style.MessageCenter);
                    if (obtainStyledAttributes.hasValue(R.styleable.MessageCenter_messageCenterDividerColor)) {
                        DrawableCompat.setTint(linearLayout.getDividerDrawable(), obtainStyledAttributes.getColor(R.styleable.MessageCenter_messageCenterDividerColor, -16777216));
                        DrawableCompat.setTintMode(linearLayout.getDividerDrawable(), PorterDuff.Mode.SRC);
                    }
                    obtainStyledAttributes.recycle();
                    String str = this.currentMessageId;
                    if (str != null) {
                        this.messageListFragment.setCurrentMessage(str);
                    }
                } else {
                    this.isTwoPane = false;
                }
                configureMessageListFragment(this.messageListFragment);
                return;
            }
            throw new RuntimeException("Content must have a place holder view whose id attribute is 'R.id.message_list_container'");
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.isViewConfigured = false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString(STATE_CURRENT_MESSAGE_ID, this.currentMessageId);
        bundle.putInt(STATE_CURRENT_MESSAGE_POSITION, this.currentMessagePosition);
        bundle.putString(STATE_PENDING_MESSAGE_ID, this.pendingMessageId);
        MessageListFragment messageListFragment2 = this.messageListFragment;
        if (!(messageListFragment2 == null || messageListFragment2.getAbsListView() == null)) {
            bundle.putParcelable(STATE_ABS_LIST_VIEW, this.messageListFragment.getAbsListView().onSaveInstanceState());
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void configureMessageListFragment(final MessageListFragment messageListFragment2) {
        messageListFragment2.getAbsListViewAsync(new MessageListFragment.OnListViewReadyCallback() {
            public void onListViewReady(AbsListView absListView) {
                absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Message message = messageListFragment2.getMessage(i);
                        if (message != null) {
                            MessageCenterFragment.this.showMessage(message.getMessageId());
                        }
                    }
                });
                absListView.setMultiChoiceModeListener(new DefaultMultiChoiceModeListener(messageListFragment2));
                absListView.setChoiceMode(3);
                absListView.setSaveEnabled(false);
            }
        });
    }

    public void onResume() {
        super.onResume();
        if (this.isTwoPane) {
            MessageCenter.shared().getInbox().addListener(this.inboxListener);
        }
        updateCurrentMessage();
        String str = this.pendingMessageId;
        if (str != null) {
            showMessage(str);
            this.pendingMessageId = null;
        }
    }

    public void onPause() {
        super.onPause();
        MessageCenter.shared().getInbox().removeListener(this.inboxListener);
    }

    private List<Message> getMessages() {
        return MessageCenter.shared().getInbox().getMessages(this.predicate);
    }

    public void setMessageID(String str) {
        if (isResumed()) {
            showMessage(str);
        } else {
            this.pendingMessageId = str;
        }
    }

    /* access modifiers changed from: protected */
    public void showMessage(String str) {
        if (getContext() != null) {
            Message message = MessageCenter.shared().getInbox().getMessage(str);
            if (message == null) {
                this.currentMessagePosition = -1;
            } else {
                this.currentMessagePosition = getMessages().indexOf(message);
            }
            this.currentMessageId = str;
            if (this.messageListFragment != null) {
                if (this.isTwoPane) {
                    String str2 = str == null ? "EMPTY_MESSAGE" : str;
                    if (getChildFragmentManager().findFragmentByTag(str2) == null) {
                        getChildFragmentManager().beginTransaction().replace(R.id.message_container, str == null ? new NoMessageSelectedFragment() : MessageFragment.newInstance(str), str2).commit();
                        this.messageListFragment.setCurrentMessage(str);
                    }
                } else if (str != null) {
                    showMessageExternally(getContext(), str);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showMessageExternally(Context context, String str) {
        Intent data = new Intent().setPackage(context.getPackageName()).addFlags(805306368).setData(Uri.fromParts("message", str, (String) null));
        data.setAction(MessageCenter.VIEW_MESSAGE_INTENT_ACTION);
        if (data.resolveActivity(context.getPackageManager()) == null) {
            data.setClass(context, MessageActivity.class);
        }
        context.startActivity(data);
    }

    /* access modifiers changed from: private */
    public void updateCurrentMessage() {
        Message message = MessageCenter.shared().getInbox().getMessage(this.currentMessageId);
        List<Message> messages = getMessages();
        if (this.isTwoPane && this.currentMessagePosition != -1 && !messages.contains(message)) {
            if (messages.size() == 0) {
                this.currentMessageId = null;
                this.currentMessagePosition = -1;
            } else {
                int min = Math.min(messages.size() - 1, this.currentMessagePosition);
                this.currentMessagePosition = min;
                this.currentMessageId = messages.get(min).getMessageId();
            }
            if (this.isTwoPane) {
                showMessage(this.currentMessageId);
            }
        }
    }

    public void setPredicate(Predicate<Message> predicate2) {
        this.predicate = predicate2;
    }

    public static class NoMessageSelectedFragment extends Fragment {
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            Context context = layoutInflater.getContext();
            View inflate = layoutInflater.inflate(R.layout.ua_fragment_no_message_selected, viewGroup, false);
            View findViewById = inflate.findViewById(16908292);
            if (findViewById instanceof TextView) {
                TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes((AttributeSet) null, R.styleable.MessageCenter, R.attr.messageCenterStyle, R.style.MessageCenter);
                TextView textView = (TextView) findViewById;
                ViewUtils.applyTextStyle(layoutInflater.getContext(), textView, obtainStyledAttributes.getResourceId(R.styleable.MessageCenter_messageNotSelectedTextAppearance, 0));
                textView.setText(obtainStyledAttributes.getString(R.styleable.MessageCenter_messageNotSelectedText));
                obtainStyledAttributes.recycle();
            }
            return inflate;
        }
    }
}
