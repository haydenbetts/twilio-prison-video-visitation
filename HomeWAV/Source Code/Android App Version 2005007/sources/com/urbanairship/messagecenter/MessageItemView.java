package com.urbanairship.messagecenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.urbanairship.UAirship;
import com.urbanairship.images.ImageRequestOptions;
import com.urbanairship.util.ViewUtils;

public class MessageItemView extends FrameLayout {
    private static final int[] STATE_HIGHLIGHTED = {R.attr.ua_state_highlighted};
    private CheckBox checkBox;
    private TextView dateView;
    private ImageView iconView;
    private boolean isHighlighted;
    /* access modifiers changed from: private */
    public View.OnClickListener selectionListener;
    private TextView titleView;

    public MessageItemView(Context context) {
        this(context, (AttributeSet) null, R.attr.messageCenterStyle);
    }

    public MessageItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.messageCenterStyle);
    }

    public MessageItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, R.style.MessageCenter);
    }

    public MessageItemView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        int i3 = R.layout.ua_item_mc_content;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.MessageCenter, i, i2);
        if (obtainStyledAttributes.getBoolean(R.styleable.MessageCenter_messageCenterItemIconEnabled, false)) {
            i3 = R.layout.ua_item_mc_icon_content;
        }
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.MessageCenter_messageCenterItemDateTextAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.MessageCenter_messageCenterItemTitleTextAppearance, 0);
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.MessageCenter_messageCenterItemBackground, 0);
        if (resourceId3 != 0) {
            setBackgroundResource(resourceId3);
        }
        obtainStyledAttributes.recycle();
        View inflate = View.inflate(context, i3, this);
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        this.titleView = textView;
        ViewUtils.applyTextStyle(context, textView, resourceId2);
        TextView textView2 = (TextView) inflate.findViewById(R.id.date);
        this.dateView = textView2;
        ViewUtils.applyTextStyle(context, textView2, resourceId);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
        this.iconView = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MessageItemView.this.selectionListener != null) {
                        MessageItemView.this.selectionListener.onClick(MessageItemView.this);
                    }
                }
            });
        }
        CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.checkbox);
        this.checkBox = checkBox2;
        if (checkBox2 != null) {
            checkBox2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MessageItemView.this.selectionListener != null) {
                        MessageItemView.this.selectionListener.onClick(MessageItemView.this);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void updateMessage(Message message, int i) {
        this.dateView.setText(DateFormat.getDateFormat(getContext()).format(message.getSentDate()));
        if (message.isRead()) {
            this.titleView.setText(message.getTitle());
        } else {
            SpannableString spannableString = new SpannableString(message.getTitle());
            spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
            this.titleView.setText(spannableString, TextView.BufferType.SPANNABLE);
        }
        CheckBox checkBox2 = this.checkBox;
        if (checkBox2 != null) {
            checkBox2.setChecked(isActivated());
        }
        if (this.iconView != null) {
            UAirship.shared().getImageLoader().load(getContext(), this.iconView, ImageRequestOptions.newBuilder(message.getListIconUrl()).setPlaceHolder(i).build());
        }
    }

    public void setActivated(boolean z) {
        super.setActivated(z);
        CheckBox checkBox2 = this.checkBox;
        if (checkBox2 != null) {
            checkBox2.setChecked(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setHighlighted(boolean z) {
        if (this.isHighlighted != z) {
            this.isHighlighted = z;
            refreshDrawableState();
        }
    }

    /* access modifiers changed from: package-private */
    public void setSelectionListener(View.OnClickListener onClickListener) {
        this.selectionListener = onClickListener;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (!this.isHighlighted) {
            return super.onCreateDrawableState(i);
        }
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        mergeDrawableStates(onCreateDrawableState, STATE_HIGHLIGHTED);
        return onCreateDrawableState;
    }
}
