package com.urbanairship.messagecenter;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.urbanairship.Cancelable;
import com.urbanairship.Logger;
import com.urbanairship.messagecenter.Inbox;
import com.urbanairship.messagecenter.webkit.MessageWebView;
import com.urbanairship.messagecenter.webkit.MessageWebViewClient;
import com.urbanairship.webkit.AirshipWebChromeClient;

public class MessageFragment extends Fragment {
    protected static final int ERROR_DISPLAYING_MESSAGE = 2;
    protected static final int ERROR_FETCHING_MESSAGES = 1;
    protected static final int ERROR_MESSAGE_UNAVAILABLE = 3;
    public static final String MESSAGE_ID = "messageId";
    /* access modifiers changed from: private */
    public Integer error = null;
    private TextView errorMessage;
    private View errorPage;
    private Cancelable fetchMessageRequest;
    /* access modifiers changed from: private */
    public Message message;
    private View progressBar;
    private Button retryButton;
    /* access modifiers changed from: private */
    public MessageWebView webView;

    public static MessageFragment newInstance(String str) {
        MessageFragment messageFragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("messageId", str);
        messageFragment.setArguments(bundle);
        return messageFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ua_fragment_message, viewGroup, false);
        ensureView(inflate);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ensureView(view);
    }

    private void ensureView(View view) {
        if (this.webView == null) {
            View findViewById = view.findViewById(16908301);
            this.progressBar = findViewById;
            if (findViewById != null) {
                MessageWebView messageWebView = (MessageWebView) view.findViewById(16908299);
                this.webView = messageWebView;
                if (messageWebView != null) {
                    this.errorPage = view.findViewById(R.id.error);
                    this.webView.setAlpha(0.0f);
                    this.webView.setWebViewClient(new MessageWebViewClient() {
                        public void onPageFinished(WebView webView, String str) {
                            super.onPageFinished(webView, str);
                            if (MessageFragment.this.error != null) {
                                MessageFragment.this.showErrorPage(2);
                            } else if (MessageFragment.this.message != null) {
                                MessageFragment.this.message.markRead();
                                MessageFragment.this.showMessage();
                            }
                        }

                        public void onReceivedError(WebView webView, int i, String str, String str2) {
                            if (MessageFragment.this.message != null && str2 != null && str2.equals(MessageFragment.this.message.getMessageBodyUrl())) {
                                Integer unused = MessageFragment.this.error = Integer.valueOf(i);
                            }
                        }
                    });
                    this.webView.getSettings().setSupportMultipleWindows(true);
                    this.webView.setWebChromeClient(new AirshipWebChromeClient(getActivity()) {
                        public Bitmap getDefaultVideoPoster() {
                            if (Build.VERSION.SDK_INT < 21) {
                                MessageFragment.this.webView.setLayerType(2, (Paint) null);
                            }
                            return super.getDefaultVideoPoster();
                        }
                    });
                    if (Build.VERSION.SDK_INT < 21) {
                        this.webView.setLayerType(1, (Paint) null);
                    }
                    Button button = (Button) view.findViewById(R.id.retry_button);
                    this.retryButton = button;
                    if (button != null) {
                        button.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                MessageFragment.this.retry();
                            }
                        });
                    }
                    this.errorMessage = (TextView) view.findViewById(R.id.error_message);
                    return;
                }
                throw new RuntimeException("Your content must have a MessageWebView whose id attribute is 'android.R.id.message'");
            }
            throw new RuntimeException("Your content must have a progress View whose id attribute is 'android.R.id.progress'");
        }
    }

    public void onStart() {
        super.onStart();
        loadMessage();
    }

    public void onResume() {
        super.onResume();
        this.webView.onResume();
    }

    public void onPause() {
        super.onPause();
        this.webView.onPause();
    }

    public void onStop() {
        super.onStop();
        Cancelable cancelable = this.fetchMessageRequest;
        if (cancelable != null) {
            cancelable.cancel();
            this.fetchMessageRequest = null;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.webView = null;
        this.progressBar = null;
    }

    /* access modifiers changed from: protected */
    public void retry() {
        if (this.webView != null) {
            loadMessage();
        }
    }

    /* access modifiers changed from: protected */
    public void showProgress() {
        View view = this.errorPage;
        if (view != null && view.getVisibility() == 0) {
            this.errorPage.animate().alpha(0.0f).setDuration(200).setListener((Animator.AnimatorListener) null);
        }
        MessageWebView messageWebView = this.webView;
        if (messageWebView != null) {
            messageWebView.animate().alpha(0.0f).setDuration(200).setListener((Animator.AnimatorListener) null);
        }
        View view2 = this.progressBar;
        if (view2 != null) {
            view2.animate().alpha(1.0f).setDuration(200).setListener((Animator.AnimatorListener) null);
        }
    }

    /* access modifiers changed from: protected */
    public void showMessage() {
        MessageWebView messageWebView = this.webView;
        if (messageWebView != null) {
            messageWebView.animate().alpha(1.0f).setDuration(200).setListener((Animator.AnimatorListener) null);
        }
        View view = this.progressBar;
        if (view != null) {
            view.animate().alpha(0.0f).setDuration(200).setListener((Animator.AnimatorListener) null);
        }
    }

    /* access modifiers changed from: protected */
    public void showErrorPage(int i) {
        if (this.errorPage != null) {
            if (i == 1 || i == 2) {
                Button button = this.retryButton;
                if (button != null) {
                    button.setVisibility(0);
                }
                TextView textView = this.errorMessage;
                if (textView != null) {
                    textView.setText(R.string.ua_mc_failed_to_load);
                }
            } else if (i == 3) {
                Button button2 = this.retryButton;
                if (button2 != null) {
                    button2.setVisibility(8);
                }
                TextView textView2 = this.errorMessage;
                if (textView2 != null) {
                    textView2.setText(R.string.ua_mc_no_longer_available);
                }
            }
            if (this.errorPage.getVisibility() == 8) {
                this.errorPage.setAlpha(0.0f);
                this.errorPage.setVisibility(0);
            }
            this.errorPage.animate().alpha(1.0f).setDuration(200).setListener((Animator.AnimatorListener) null);
        }
        View view = this.progressBar;
        if (view != null) {
            view.animate().alpha(0.0f).setDuration(200).setListener((Animator.AnimatorListener) null);
        }
    }

    public String getMessageId() {
        if (getArguments() == null) {
            return null;
        }
        return getArguments().getString("messageId");
    }

    private void loadMessage() {
        showProgress();
        this.error = null;
        Message message2 = MessageCenter.shared().getInbox().getMessage(getMessageId());
        this.message = message2;
        if (message2 == null) {
            Logger.debug("MessageFragment - Fetching messages.", new Object[0]);
            this.fetchMessageRequest = MessageCenter.shared().getInbox().fetchMessages(new Inbox.FetchMessagesCallback() {
                public void onFinished(boolean z) {
                    Message unused = MessageFragment.this.message = MessageCenter.shared().getInbox().getMessage(MessageFragment.this.getMessageId());
                    if (!z) {
                        MessageFragment.this.showErrorPage(1);
                    } else if (MessageFragment.this.message == null || MessageFragment.this.message.isExpired()) {
                        MessageFragment.this.showErrorPage(3);
                    } else {
                        Logger.info("Loading message: " + MessageFragment.this.message.getMessageId(), new Object[0]);
                        MessageFragment.this.webView.loadMessage(MessageFragment.this.message);
                    }
                }
            });
        } else if (message2.isExpired()) {
            showErrorPage(3);
        } else {
            Logger.info("Loading message: %s", this.message.getMessageId());
            this.webView.loadMessage(this.message);
        }
    }
}
