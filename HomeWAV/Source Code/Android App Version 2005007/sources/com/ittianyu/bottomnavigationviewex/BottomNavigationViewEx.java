package com.ittianyu.bottomnavigationviewex;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewEx extends BottomNavigationViewInner {
    public BottomNavigationViewEx(Context context) {
        super(context);
    }

    public BottomNavigationViewEx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BottomNavigationViewEx(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BottomNavigationViewInner setIconVisibility(boolean z) {
        try {
            return super.setIconVisibility(z);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setTextVisibility(boolean z) {
        try {
            return super.setTextVisibility(z);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner enableAnimation(boolean z) {
        try {
            return super.enableAnimation(z);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner enableShiftingMode(boolean z) {
        try {
            return super.enableShiftingMode(z);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner enableItemShiftingMode(boolean z) {
        try {
            return super.enableItemShiftingMode(z);
        } catch (Exception unused) {
            return this;
        }
    }

    public int getCurrentItem() {
        try {
            return super.getCurrentItem();
        } catch (Exception unused) {
            return 0;
        }
    }

    public int getMenuItemPosition(MenuItem menuItem) {
        try {
            return super.getMenuItemPosition(menuItem);
        } catch (Exception unused) {
            return 0;
        }
    }

    public BottomNavigationViewInner setCurrentItem(int i) {
        try {
            return super.setCurrentItem(i);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        try {
            return super.getOnNavigationItemSelectedListener();
        } catch (Exception unused) {
            return null;
        }
    }

    public void setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        try {
            super.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        } catch (Exception unused) {
        }
    }

    public BottomNavigationMenuView getBottomNavigationMenuView() {
        return super.getBottomNavigationMenuView();
    }

    public BottomNavigationViewInner clearIconTintColor() {
        try {
            return super.clearIconTintColor();
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationItemView[] getBottomNavigationItemViews() {
        try {
            return super.getBottomNavigationItemViews();
        } catch (Exception unused) {
            return null;
        }
    }

    public BottomNavigationItemView getBottomNavigationItemView(int i) {
        try {
            return super.getBottomNavigationItemView(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public ImageView getIconAt(int i) {
        try {
            return super.getIconAt(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public TextView getSmallLabelAt(int i) {
        try {
            return super.getSmallLabelAt(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public TextView getLargeLabelAt(int i) {
        try {
            return super.getLargeLabelAt(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public int getItemCount() {
        try {
            return super.getItemCount();
        } catch (Exception unused) {
            return 0;
        }
    }

    public BottomNavigationViewInner setSmallTextSize(float f) {
        try {
            return super.setSmallTextSize(f);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setLargeTextSize(float f) {
        try {
            return super.setLargeTextSize(f);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setTextSize(float f) {
        try {
            return super.setTextSize(f);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setIconSizeAt(int i, float f, float f2) {
        try {
            return super.setIconSizeAt(i, f, f2);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setIconSize(float f, float f2) {
        try {
            return super.setIconSize(f, f2);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setIconSize(float f) {
        try {
            return super.setIconSize(f);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setItemHeight(int i) {
        try {
            return super.setItemHeight(i);
        } catch (Exception unused) {
            return this;
        }
    }

    public int getItemHeight() {
        try {
            return super.getItemHeight();
        } catch (Exception unused) {
            return 0;
        }
    }

    public BottomNavigationViewInner setTypeface(Typeface typeface, int i) {
        try {
            return super.setTypeface(typeface, i);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setTypeface(Typeface typeface) {
        try {
            return super.setTypeface(typeface);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setupWithViewPager(ViewPager viewPager) {
        try {
            return super.setupWithViewPager(viewPager);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setupWithViewPager(ViewPager viewPager, boolean z) {
        try {
            return super.setupWithViewPager(viewPager, z);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner enableShiftingMode(int i, boolean z) {
        try {
            return super.enableShiftingMode(i, z);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setItemBackground(int i, int i2) {
        try {
            return super.setItemBackground(i, i2);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setIconTintList(int i, ColorStateList colorStateList) {
        try {
            return super.setIconTintList(i, colorStateList);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setTextTintList(int i, ColorStateList colorStateList) {
        try {
            return super.setTextTintList(i, colorStateList);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setIconsMarginTop(int i) {
        try {
            return super.setIconsMarginTop(i);
        } catch (Exception unused) {
            return this;
        }
    }

    public BottomNavigationViewInner setIconMarginTop(int i, int i2) {
        try {
            return super.setIconMarginTop(i, i2);
        } catch (Exception unused) {
            return this;
        }
    }
}
