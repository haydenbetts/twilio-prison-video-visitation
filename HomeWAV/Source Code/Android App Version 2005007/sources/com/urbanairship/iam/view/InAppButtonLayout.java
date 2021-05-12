package com.urbanairship.iam.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.urbanairship.automation.R;
import com.urbanairship.iam.ButtonInfo;
import com.urbanairship.iam.DisplayContent;
import java.util.List;

public class InAppButtonLayout extends BoundedLinearLayout {
    /* access modifiers changed from: private */
    public ButtonClickListener buttonClickListener;
    private int buttonLayoutResourceId;
    private int separatedSpaceWidth;
    private int stackedSpaceHeight;

    public interface ButtonClickListener {
        void onButtonClicked(View view, ButtonInfo buttonInfo);
    }

    public InAppButtonLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public InAppButtonLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InAppButtonLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    public InAppButtonLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.UrbanAirshipInAppButtonLayout, i, i2);
            this.stackedSpaceHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UrbanAirshipInAppButtonLayout_urbanAirshipStackedSpaceHeight, 0);
            this.separatedSpaceWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UrbanAirshipInAppButtonLayout_urbanAirshipSeparatedSpaceWidth, 0);
            this.buttonLayoutResourceId = obtainStyledAttributes.getResourceId(R.styleable.UrbanAirshipInAppButtonLayout_urbanAirshipButtonLayoutResourceId, 0);
            obtainStyledAttributes.recycle();
        }
    }

    public void setButtonClickListener(ButtonClickListener buttonClickListener2) {
        this.buttonClickListener = buttonClickListener2;
    }

    public void setButtons(String str, List<ButtonInfo> list) {
        boolean z;
        boolean z2;
        int i;
        if (list.size() > 1) {
            z2 = DisplayContent.BUTTON_LAYOUT_STACKED.equals(str);
            z = DisplayContent.BUTTON_LAYOUT_JOINED.equals(str);
        } else {
            z = false;
            z2 = false;
        }
        removeAllViews();
        setOrientation(z2 ? 1 : 0);
        setMeasureWithLargestChildEnabled(true);
        int i2 = 0;
        while (i2 < list.size()) {
            final ButtonInfo buttonInfo = list.get(i2);
            if (z) {
                i = i2 == 0 ? 9 : i2 == list.size() - 1 ? 6 : 0;
            } else {
                i = 15;
            }
            Button button = (Button) LayoutInflater.from(getContext()).inflate(this.buttonLayoutResourceId, this, false);
            InAppViewUtils.applyButtonInfo(button, buttonInfo, i);
            if (z2) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
                layoutParams.weight = 1.0f;
                button.setLayoutParams(layoutParams);
                if (i2 > 0) {
                    layoutParams.setMargins(0, this.stackedSpaceHeight, 0, 0);
                }
            } else {
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1);
                layoutParams2.weight = 1.0f;
                button.setLayoutParams(layoutParams2);
                if (!z && i2 > 0) {
                    layoutParams2.setMargins(this.separatedSpaceWidth, 0, 0, 0);
                    if (Build.VERSION.SDK_INT >= 17) {
                        layoutParams2.setMarginStart(this.separatedSpaceWidth);
                    }
                }
            }
            addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (InAppButtonLayout.this.buttonClickListener != null) {
                        InAppButtonLayout.this.buttonClickListener.onButtonClicked(view, buttonInfo);
                    }
                }
            });
            i2++;
        }
        requestLayout();
    }
}
