package com.ittianyu.bottomnavigationviewex;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.ThemeEnforcement;
import com.urbanairship.iam.DisplayContent;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class BottomNavigationViewInner extends BottomNavigationView {
    /* access modifiers changed from: private */
    public static boolean isNavigationItemClicking = false;
    private boolean animationRecord;
    private BottomNavigationItemView[] mButtons;
    /* access modifiers changed from: private */
    public int mItemHeight;
    private float mLargeLabelSize;
    private BottomNavigationMenuView mMenuView;
    private MyOnNavigationItemSelectedListener mMyOnNavigationItemSelectedListener;
    private BottomNavigationViewExOnPageChangeListener mPageChangeListener;
    private float mScaleDownFactor;
    private float mScaleUpFactor;
    private float mShiftAmount;
    private float mSmallLabelSize;
    private ViewPager mViewPager;
    private boolean textVisibility;
    private boolean visibilityHeightRecord;
    private boolean visibilityTextSizeRecord;

    public BottomNavigationViewInner(Context context) {
        this(context, (AttributeSet) null);
    }

    public BottomNavigationViewInner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavigationViewInner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textVisibility = true;
        TintTypedArray obtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context, attributeSet, R.styleable.BottomNavigationView, i, R.style.Widget_Design_BottomNavigationView, R.styleable.BottomNavigationView_itemTextAppearanceInactive, R.styleable.BottomNavigationView_itemTextAppearanceActive);
        if (!obtainTintedStyledAttributes.hasValue(R.styleable.BottomNavigationView_itemIconTint)) {
            clearIconTintColor();
        }
        obtainTintedStyledAttributes.recycle();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        r9 = (android.widget.ImageView) getField(r9.getClass(), r9, "icon");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ittianyu.bottomnavigationviewex.BottomNavigationViewInner setIconVisibility(boolean r9) {
        /*
            r8 = this;
            com.google.android.material.bottomnavigation.BottomNavigationMenuView r0 = r8.getBottomNavigationMenuView()
            com.google.android.material.bottomnavigation.BottomNavigationItemView[] r1 = r8.getBottomNavigationItemViews()
            int r2 = r1.length
            r3 = 0
            r4 = 0
        L_0x000b:
            java.lang.String r5 = "icon"
            if (r4 >= r2) goto L_0x0026
            r6 = r1[r4]
            java.lang.Class r7 = r6.getClass()
            java.lang.Object r5 = r8.getField(r7, r6, r5)
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            if (r9 == 0) goto L_0x001f
            r6 = 0
            goto L_0x0020
        L_0x001f:
            r6 = 4
        L_0x0020:
            r5.setVisibility(r6)
            int r4 = r4 + 1
            goto L_0x000b
        L_0x0026:
            if (r9 != 0) goto L_0x004e
            boolean r9 = r8.visibilityHeightRecord
            if (r9 != 0) goto L_0x0035
            r9 = 1
            r8.visibilityHeightRecord = r9
            int r9 = r8.getItemHeight()
            r8.mItemHeight = r9
        L_0x0035:
            r9 = r1[r3]
            if (r9 == 0) goto L_0x0058
            java.lang.Class r1 = r9.getClass()
            java.lang.Object r9 = r8.getField(r1, r9, r5)
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            if (r9 == 0) goto L_0x0058
            com.ittianyu.bottomnavigationviewex.BottomNavigationViewInner$1 r1 = new com.ittianyu.bottomnavigationviewex.BottomNavigationViewInner$1
            r1.<init>(r9)
            r9.post(r1)
            goto L_0x0058
        L_0x004e:
            boolean r9 = r8.visibilityHeightRecord
            if (r9 != 0) goto L_0x0053
            return r8
        L_0x0053:
            int r9 = r8.mItemHeight
            r8.setItemHeight(r9)
        L_0x0058:
            r0.updateMenuView()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ittianyu.bottomnavigationviewex.BottomNavigationViewInner.setIconVisibility(boolean):com.ittianyu.bottomnavigationviewex.BottomNavigationViewInner");
    }

    public BottomNavigationViewInner setTextVisibility(boolean z) {
        this.textVisibility = z;
        BottomNavigationMenuView bottomNavigationMenuView = getBottomNavigationMenuView();
        for (BottomNavigationItemView bottomNavigationItemView : getBottomNavigationItemViews()) {
            TextView textView = (TextView) getField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "largeLabel");
            TextView textView2 = (TextView) getField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "smallLabel");
            if (!z) {
                if (!this.visibilityTextSizeRecord && !this.animationRecord) {
                    this.visibilityTextSizeRecord = true;
                    this.mLargeLabelSize = textView.getTextSize();
                    this.mSmallLabelSize = textView2.getTextSize();
                }
                textView.setTextSize(0, 0.0f);
                textView2.setTextSize(0, 0.0f);
            } else if (!this.visibilityTextSizeRecord) {
                break;
            } else {
                textView.setTextSize(0, this.mLargeLabelSize);
                textView2.setTextSize(0, this.mSmallLabelSize);
            }
        }
        if (!z) {
            if (!this.visibilityHeightRecord) {
                this.visibilityHeightRecord = true;
                this.mItemHeight = getItemHeight();
            }
            setItemHeight(this.mItemHeight - getFontHeight(this.mSmallLabelSize));
        } else if (!this.visibilityHeightRecord) {
            return this;
        } else {
            setItemHeight(this.mItemHeight);
        }
        bottomNavigationMenuView.updateMenuView();
        return this;
    }

    private static int getFontHeight(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.top))) + 2;
    }

    public BottomNavigationViewInner enableAnimation(boolean z) {
        BottomNavigationMenuView bottomNavigationMenuView = getBottomNavigationMenuView();
        for (BottomNavigationItemView bottomNavigationItemView : getBottomNavigationItemViews()) {
            TextView textView = (TextView) getField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "largeLabel");
            TextView textView2 = (TextView) getField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "smallLabel");
            if (!z) {
                if (!this.animationRecord) {
                    this.animationRecord = true;
                    this.mShiftAmount = ((Float) getField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "shiftAmount")).floatValue();
                    this.mScaleUpFactor = ((Float) getField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "scaleUpFactor")).floatValue();
                    this.mScaleDownFactor = ((Float) getField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "scaleDownFactor")).floatValue();
                    this.mLargeLabelSize = textView.getTextSize();
                    this.mSmallLabelSize = textView2.getTextSize();
                }
                setField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "shiftAmount", 0);
                setField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "scaleUpFactor", 1);
                setField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "scaleDownFactor", 1);
                textView.setTextSize(0, this.mSmallLabelSize);
            } else if (!this.animationRecord) {
                return this;
            } else {
                setField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "shiftAmount", Float.valueOf(this.mShiftAmount));
                setField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "scaleUpFactor", Float.valueOf(this.mScaleUpFactor));
                setField(bottomNavigationItemView.getClass(), bottomNavigationItemView, "scaleDownFactor", Float.valueOf(this.mScaleDownFactor));
                textView.setTextSize(0, this.mLargeLabelSize);
            }
        }
        bottomNavigationMenuView.updateMenuView();
        return this;
    }

    @Deprecated
    public BottomNavigationViewInner enableShiftingMode(boolean z) {
        setLabelVisibilityMode(z ^ true ? 1 : 0);
        return this;
    }

    @Deprecated
    public BottomNavigationViewInner enableItemShiftingMode(boolean z) {
        setItemHorizontalTranslationEnabled(z);
        return this;
    }

    public int getCurrentItem() {
        BottomNavigationItemView[] bottomNavigationItemViews = getBottomNavigationItemViews();
        Menu menu = getMenu();
        for (int i = 0; i < bottomNavigationItemViews.length; i++) {
            if (menu.getItem(i).isChecked()) {
                return i;
            }
        }
        return 0;
    }

    public int getMenuItemPosition(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        Menu menu = getMenu();
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            if (menu.getItem(i).getItemId() == itemId) {
                return i;
            }
        }
        return -1;
    }

    public BottomNavigationViewInner setCurrentItem(int i) {
        setSelectedItemId(getMenu().getItem(i).getItemId());
        return this;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        return (BottomNavigationView.OnNavigationItemSelectedListener) getField(BottomNavigationView.class, this, "selectedListener");
    }

    public void setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        MyOnNavigationItemSelectedListener myOnNavigationItemSelectedListener = this.mMyOnNavigationItemSelectedListener;
        if (myOnNavigationItemSelectedListener == null) {
            super.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        } else {
            myOnNavigationItemSelectedListener.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        }
    }

    public BottomNavigationMenuView getBottomNavigationMenuView() {
        if (this.mMenuView == null) {
            this.mMenuView = (BottomNavigationMenuView) getField(BottomNavigationView.class, this, "menuView");
        }
        return this.mMenuView;
    }

    public BottomNavigationViewInner clearIconTintColor() {
        getBottomNavigationMenuView().setIconTintList((ColorStateList) null);
        return this;
    }

    public BottomNavigationItemView[] getBottomNavigationItemViews() {
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.mButtons;
        if (bottomNavigationItemViewArr != null) {
            return bottomNavigationItemViewArr;
        }
        BottomNavigationMenuView bottomNavigationMenuView = getBottomNavigationMenuView();
        BottomNavigationItemView[] bottomNavigationItemViewArr2 = (BottomNavigationItemView[]) getField(bottomNavigationMenuView.getClass(), bottomNavigationMenuView, DisplayContent.BUTTONS_KEY);
        this.mButtons = bottomNavigationItemViewArr2;
        return bottomNavigationItemViewArr2;
    }

    public BottomNavigationItemView getBottomNavigationItemView(int i) {
        return getBottomNavigationItemViews()[i];
    }

    public ImageView getIconAt(int i) {
        return (ImageView) getField(BottomNavigationItemView.class, getBottomNavigationItemView(i), "icon");
    }

    public TextView getSmallLabelAt(int i) {
        return (TextView) getField(BottomNavigationItemView.class, getBottomNavigationItemView(i), "smallLabel");
    }

    public TextView getLargeLabelAt(int i) {
        return (TextView) getField(BottomNavigationItemView.class, getBottomNavigationItemView(i), "largeLabel");
    }

    public int getItemCount() {
        BottomNavigationItemView[] bottomNavigationItemViews = getBottomNavigationItemViews();
        if (bottomNavigationItemViews == null) {
            return 0;
        }
        return bottomNavigationItemViews.length;
    }

    public BottomNavigationViewInner setSmallTextSize(float f) {
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            getSmallLabelAt(i).setTextSize(f);
        }
        this.mMenuView.updateMenuView();
        return this;
    }

    public BottomNavigationViewInner setLargeTextSize(float f) {
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            TextView largeLabelAt = getLargeLabelAt(i);
            if (largeLabelAt != null) {
                largeLabelAt.setTextSize(f);
            }
        }
        this.mMenuView.updateMenuView();
        return this;
    }

    public BottomNavigationViewInner setTextSize(float f) {
        setLargeTextSize(f);
        setSmallTextSize(f);
        return this;
    }

    public BottomNavigationViewInner setIconSizeAt(int i, float f, float f2) {
        ImageView iconAt = getIconAt(i);
        ViewGroup.LayoutParams layoutParams = iconAt.getLayoutParams();
        layoutParams.width = dp2px(getContext(), f);
        layoutParams.height = dp2px(getContext(), f2);
        iconAt.setLayoutParams(layoutParams);
        this.mMenuView.updateMenuView();
        return this;
    }

    public BottomNavigationViewInner setIconSize(float f, float f2) {
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            setIconSizeAt(i, f, f2);
        }
        return this;
    }

    public BottomNavigationViewInner setIconSize(float f) {
        setItemIconSize(dp2px(getContext(), f));
        return this;
    }

    public BottomNavigationViewInner setItemHeight(int i) {
        BottomNavigationMenuView bottomNavigationMenuView = getBottomNavigationMenuView();
        setField(bottomNavigationMenuView.getClass(), bottomNavigationMenuView, "itemHeight", Integer.valueOf(i));
        bottomNavigationMenuView.updateMenuView();
        return this;
    }

    public int getItemHeight() {
        BottomNavigationMenuView bottomNavigationMenuView = getBottomNavigationMenuView();
        return ((Integer) getField(bottomNavigationMenuView.getClass(), bottomNavigationMenuView, "itemHeight")).intValue();
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public BottomNavigationViewInner setTypeface(Typeface typeface, int i) {
        int itemCount = getItemCount();
        for (int i2 = 0; i2 < itemCount; i2++) {
            getLargeLabelAt(i2).setTypeface(typeface, i);
            getSmallLabelAt(i2).setTypeface(typeface, i);
        }
        this.mMenuView.updateMenuView();
        return this;
    }

    public BottomNavigationViewInner setTypeface(Typeface typeface) {
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            getLargeLabelAt(i).setTypeface(typeface);
            getSmallLabelAt(i).setTypeface(typeface);
        }
        this.mMenuView.updateMenuView();
        return this;
    }

    private <T> T getField(Class cls, Object obj, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void setField(Class cls, Object obj, String str, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public BottomNavigationViewInner setupWithViewPager(ViewPager viewPager) {
        return setupWithViewPager(viewPager, false);
    }

    public BottomNavigationViewInner setupWithViewPager(ViewPager viewPager, boolean z) {
        BottomNavigationViewExOnPageChangeListener bottomNavigationViewExOnPageChangeListener;
        ViewPager viewPager2 = this.mViewPager;
        if (!(viewPager2 == null || (bottomNavigationViewExOnPageChangeListener = this.mPageChangeListener) == null)) {
            viewPager2.removeOnPageChangeListener(bottomNavigationViewExOnPageChangeListener);
        }
        if (viewPager == null) {
            this.mViewPager = null;
            super.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) null);
            return this;
        }
        this.mViewPager = viewPager;
        if (this.mPageChangeListener == null) {
            this.mPageChangeListener = new BottomNavigationViewExOnPageChangeListener(this);
        }
        viewPager.addOnPageChangeListener(this.mPageChangeListener);
        MyOnNavigationItemSelectedListener myOnNavigationItemSelectedListener = new MyOnNavigationItemSelectedListener(viewPager, this, z, getOnNavigationItemSelectedListener());
        this.mMyOnNavigationItemSelectedListener = myOnNavigationItemSelectedListener;
        super.setOnNavigationItemSelectedListener(myOnNavigationItemSelectedListener);
        return this;
    }

    private static class BottomNavigationViewExOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private final WeakReference<BottomNavigationViewInner> mBnveRef;

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public BottomNavigationViewExOnPageChangeListener(BottomNavigationViewInner bottomNavigationViewInner) {
            this.mBnveRef = new WeakReference<>(bottomNavigationViewInner);
        }

        public void onPageSelected(int i) {
            BottomNavigationViewInner bottomNavigationViewInner = (BottomNavigationViewInner) this.mBnveRef.get();
            if (bottomNavigationViewInner != null && !BottomNavigationViewInner.isNavigationItemClicking) {
                bottomNavigationViewInner.setCurrentItem(i);
            }
        }
    }

    private static class MyOnNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        private SparseIntArray items;
        private BottomNavigationView.OnNavigationItemSelectedListener listener;
        private int previousPosition = -1;
        private boolean smoothScroll;
        private final WeakReference<ViewPager> viewPagerRef;

        MyOnNavigationItemSelectedListener(ViewPager viewPager, BottomNavigationViewInner bottomNavigationViewInner, boolean z, BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
            this.viewPagerRef = new WeakReference<>(viewPager);
            this.listener = onNavigationItemSelectedListener;
            this.smoothScroll = z;
            Menu menu = bottomNavigationViewInner.getMenu();
            int size = menu.size();
            this.items = new SparseIntArray(size);
            for (int i = 0; i < size; i++) {
                this.items.put(menu.getItem(i).getItemId(), i);
            }
        }

        public void setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
            this.listener = onNavigationItemSelectedListener;
        }

        public boolean onNavigationItemSelected(MenuItem menuItem) {
            ViewPager viewPager;
            int i = this.items.get(menuItem.getItemId());
            if (this.previousPosition == i) {
                return true;
            }
            BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = this.listener;
            if ((onNavigationItemSelectedListener != null && !onNavigationItemSelectedListener.onNavigationItemSelected(menuItem)) || (viewPager = (ViewPager) this.viewPagerRef.get()) == null) {
                return false;
            }
            boolean unused = BottomNavigationViewInner.isNavigationItemClicking = true;
            viewPager.setCurrentItem(this.items.get(menuItem.getItemId()), this.smoothScroll);
            boolean unused2 = BottomNavigationViewInner.isNavigationItemClicking = false;
            this.previousPosition = i;
            return true;
        }
    }

    public BottomNavigationViewInner enableShiftingMode(int i, boolean z) {
        getBottomNavigationItemView(i).setShifting(z);
        return this;
    }

    public BottomNavigationViewInner setItemBackground(int i, int i2) {
        getBottomNavigationItemView(i).setItemBackground(i2);
        return this;
    }

    public BottomNavigationViewInner setIconTintList(int i, ColorStateList colorStateList) {
        getBottomNavigationItemView(i).setIconTintList(colorStateList);
        return this;
    }

    public BottomNavigationViewInner setTextTintList(int i, ColorStateList colorStateList) {
        getBottomNavigationItemView(i).setTextColor(colorStateList);
        return this;
    }

    public BottomNavigationViewInner setIconsMarginTop(int i) {
        for (int i2 = 0; i2 < getItemCount(); i2++) {
            setIconMarginTop(i2, i);
        }
        return this;
    }

    public BottomNavigationViewInner setIconMarginTop(int i, int i2) {
        setField(BottomNavigationItemView.class, getBottomNavigationItemView(i), "defaultMargin", Integer.valueOf(i2));
        this.mMenuView.updateMenuView();
        return this;
    }
}
