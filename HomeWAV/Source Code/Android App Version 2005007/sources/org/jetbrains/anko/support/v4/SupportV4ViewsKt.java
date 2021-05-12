package org.jetbrains.anko.support.v4;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewManager;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTabHost;
import androidx.legacy.widget.Space;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\nH\b\u001a+\u0010\b\u001a\u00020\t*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\fH\b\u001a+\u0010\b\u001a\u00020\t*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0002H\b\u001a+\u0010\b\u001a\u00020\t*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\nH\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\fH\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0002H\b\u001a+\u0010\r\u001a\u00020\u000e*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\nH\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\fH\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0002H\b\u001a+\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\nH\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\fH\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\b\u001a+\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\nH\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\fH\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0002H\b\u001a+\u0010\u0015\u001a\u00020\u0016*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\nH\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\fH\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0002H\b\u001a+\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u001a\u001a\u00020\u001b*\u00020\u0002H\b\u001a+\u0010\u001a\u001a\u00020\u001b*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\nH\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\fH\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\u0002H\b\u001a+\u0010\u001c\u001a\u00020\u001d*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010!\u001a\u00020\t*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010!\u001a\u00020\t*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010!\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010!\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010\"\u001a\u00020\u000e*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010\"\u001a\u00020\u000e*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010#\u001a\u00020\u0011*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010#\u001a\u00020\u0011*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010$\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010$\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0014¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010%\u001a\u00020\u0016*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010%\u001a\u00020\u0016*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010&\u001a\u00020\u0018*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010&\u001a\u00020\u0018*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u0019¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010'\u001a\u00020\u001b*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010'\u001a\u00020\u001b*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001b¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010(\u001a\u00020\u001d*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010(\u001a\u00020\u001d*\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070\u001d¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010)\u001a\u00020**\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010)\u001a\u00020**\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010)\u001a\u00020**\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010)\u001a\u00020**\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\u0017\u0010)\u001a\u00020**\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 H\b\u001a5\u0010)\u001a\u00020**\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010,\u001a\u00020**\u00020\nH\b\u001a+\u0010,\u001a\u00020**\u00020\n2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010,\u001a\u00020**\u00020\fH\b\u001a+\u0010,\u001a\u00020**\u00020\f2\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b\u001a\r\u0010,\u001a\u00020**\u00020\u0002H\b\u001a+\u0010,\u001a\u00020**\u00020\u00022\u001c\u0010\u0003\u001a\u0018\u0012\t\u0012\u00070+¢\u0006\u0002\b\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\b¨\u0006-"}, d2 = {"contentLoadingProgressBar", "Landroidx/core/widget/ContentLoadingProgressBar;", "Landroid/view/ViewManager;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/AnkoViewDslMarker;", "", "Lkotlin/ExtensionFunctionType;", "drawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "Landroid/app/Activity;", "Lorg/jetbrains/anko/support/v4/_DrawerLayout;", "Landroid/content/Context;", "fragmentTabHost", "Landroidx/fragment/app/FragmentTabHost;", "Lorg/jetbrains/anko/support/v4/_FragmentTabHost;", "nestedScrollView", "Landroidx/core/widget/NestedScrollView;", "Lorg/jetbrains/anko/support/v4/_NestedScrollView;", "pagerTabStrip", "Landroidx/viewpager/widget/PagerTabStrip;", "pagerTitleStrip", "Landroidx/viewpager/widget/PagerTitleStrip;", "slidingPaneLayout", "Landroidx/slidingpanelayout/widget/SlidingPaneLayout;", "Lorg/jetbrains/anko/support/v4/_SlidingPaneLayout;", "space", "Landroidx/legacy/widget/Space;", "swipeRefreshLayout", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "themedContentLoadingProgressBar", "theme", "", "themedDrawerLayout", "themedFragmentTabHost", "themedNestedScrollView", "themedPagerTabStrip", "themedPagerTitleStrip", "themedSlidingPaneLayout", "themedSpace", "themedSwipeRefreshLayout", "themedViewPager", "Landroidx/viewpager/widget/ViewPager;", "Lorg/jetbrains/anko/support/v4/_ViewPager;", "viewPager", "anko-support-v4_release"}, k = 2, mv = {1, 1, 13})
/* compiled from: Views.kt */
public final class SupportV4ViewsKt {
    public static final PagerTabStrip pagerTabStrip(ViewManager viewManager, Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(ViewManager viewManager, int i, Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip pagerTabStrip(Context context, Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(Context context, int i, Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip pagerTabStrip(Activity activity, Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTabStrip;
    }

    public static /* synthetic */ PagerTabStrip themedPagerTabStrip$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(Activity activity, int i, Function1<? super PagerTabStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        function1.invoke(pagerTabStrip);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTabStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(ViewManager viewManager, Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(ViewManager viewManager, int i, Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(Context context, Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(Context context, int i, Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(Activity activity, Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTitleStrip;
    }

    public static /* synthetic */ PagerTitleStrip themedPagerTitleStrip$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(Activity activity, int i, Function1<? super PagerTitleStrip, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        function1.invoke(pagerTitleStrip);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTitleStrip;
    }

    public static final ContentLoadingProgressBar contentLoadingProgressBar(ViewManager viewManager, Function1<? super ContentLoadingProgressBar, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) invoke;
        function1.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return contentLoadingProgressBar;
    }

    public static /* synthetic */ ContentLoadingProgressBar themedContentLoadingProgressBar$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return contentLoadingProgressBar;
    }

    public static /* synthetic */ ContentLoadingProgressBar themedContentLoadingProgressBar$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) invoke;
        function1.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return contentLoadingProgressBar;
    }

    public static final ContentLoadingProgressBar themedContentLoadingProgressBar(ViewManager viewManager, int i, Function1<? super ContentLoadingProgressBar, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) invoke;
        function1.invoke(contentLoadingProgressBar);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return contentLoadingProgressBar;
    }

    public static final Space space(ViewManager viewManager, Function1<? super Space, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        Space space = (Space) invoke;
        function1.invoke(space);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return space;
    }

    public static /* synthetic */ Space themedSpace$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        Space space = (Space) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return space;
    }

    public static /* synthetic */ Space themedSpace$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        Space space = (Space) invoke;
        function1.invoke(space);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return space;
    }

    public static final Space themedSpace(ViewManager viewManager, int i, Function1<? super Space, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        Space space = (Space) invoke;
        function1.invoke(space);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return space;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(ViewManager viewManager, Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(ViewManager viewManager, int i, Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(Context context, Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(Context context, int i, Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(Activity activity, Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return swipeRefreshLayout;
    }

    public static /* synthetic */ SwipeRefreshLayout themedSwipeRefreshLayout$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(Activity activity, int i, Function1<? super SwipeRefreshLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        function1.invoke(swipeRefreshLayout);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return swipeRefreshLayout;
    }

    public static final FragmentTabHost fragmentTabHost(ViewManager viewManager, Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (FragmentTabHost) invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (FragmentTabHost) invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(ViewManager viewManager, int i, Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost fragmentTabHost(Context context, Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (FragmentTabHost) invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (FragmentTabHost) invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(Context context, int i, Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost fragmentTabHost(Activity activity, Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (FragmentTabHost) invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (FragmentTabHost) invoke;
    }

    public static /* synthetic */ FragmentTabHost themedFragmentTabHost$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(Activity activity, int i, Function1<? super _FragmentTabHost, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_FragmentTabHost) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final ViewPager viewPager(ViewManager viewManager, Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (ViewPager) invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (ViewPager) invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager themedViewPager(ViewManager viewManager, int i, Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager viewPager(Context context, Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (ViewPager) invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (ViewPager) invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager themedViewPager(Context context, int i, Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager viewPager(Activity activity, Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (ViewPager) invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (ViewPager) invoke;
    }

    public static /* synthetic */ ViewPager themedViewPager$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager themedViewPager(Activity activity, int i, Function1<? super _ViewPager, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_ViewPager) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (ViewPager) invoke;
    }

    public static final DrawerLayout drawerLayout(ViewManager viewManager, Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (DrawerLayout) invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (DrawerLayout) invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout themedDrawerLayout(ViewManager viewManager, int i, Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout drawerLayout(Context context, Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (DrawerLayout) invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (DrawerLayout) invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout themedDrawerLayout(Context context, int i, Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout drawerLayout(Activity activity, Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (DrawerLayout) invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (DrawerLayout) invoke;
    }

    public static /* synthetic */ DrawerLayout themedDrawerLayout$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout themedDrawerLayout(Activity activity, int i, Function1<? super _DrawerLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_DrawerLayout) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (DrawerLayout) invoke;
    }

    public static final NestedScrollView nestedScrollView(ViewManager viewManager, Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (NestedScrollView) invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (NestedScrollView) invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView themedNestedScrollView(ViewManager viewManager, int i, Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView nestedScrollView(Context context, Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (NestedScrollView) invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (NestedScrollView) invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView themedNestedScrollView(Context context, int i, Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView nestedScrollView(Activity activity, Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (NestedScrollView) invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (NestedScrollView) invoke;
    }

    public static /* synthetic */ NestedScrollView themedNestedScrollView$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView themedNestedScrollView(Activity activity, int i, Function1<? super _NestedScrollView, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_NestedScrollView) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (NestedScrollView) invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(ViewManager viewManager, Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(ViewManager viewManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(ViewManager viewManager, int i, Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(Context context, Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(Context context, int i, Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(Activity activity, Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static /* synthetic */ SlidingPaneLayout themedSlidingPaneLayout$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(Activity activity, int i, Function1<? super _SlidingPaneLayout, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "init");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        function1.invoke((_SlidingPaneLayout) invoke);
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final PagerTabStrip pagerTabStrip(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip pagerTabStrip(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip pagerTabStrip(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTabStrip;
    }

    public static final PagerTabStrip themedPagerTabStrip(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TAB_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTabStrip pagerTabStrip = (PagerTabStrip) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTabStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip pagerTitleStrip(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTitleStrip;
    }

    public static final PagerTitleStrip themedPagerTitleStrip(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getPAGER_TITLE_STRIP().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        PagerTitleStrip pagerTitleStrip = (PagerTitleStrip) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return pagerTitleStrip;
    }

    public static final ContentLoadingProgressBar contentLoadingProgressBar(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return contentLoadingProgressBar;
    }

    public static final ContentLoadingProgressBar themedContentLoadingProgressBar(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getCONTENT_LOADING_PROGRESS_BAR().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return contentLoadingProgressBar;
    }

    public static final Space space(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        Space space = (Space) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return space;
    }

    public static final Space themedSpace(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSPACE().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        Space space = (Space) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return space;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout swipeRefreshLayout(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return swipeRefreshLayout;
    }

    public static final SwipeRefreshLayout themedSwipeRefreshLayout(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4View.INSTANCE.getSWIPE_REFRESH_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return swipeRefreshLayout;
    }

    public static final FragmentTabHost fragmentTabHost(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost fragmentTabHost(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost fragmentTabHost(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final FragmentTabHost themedFragmentTabHost(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getFRAGMENT_TAB_HOST().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _FragmentTabHost _fragmenttabhost = (_FragmentTabHost) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (FragmentTabHost) invoke;
    }

    public static final ViewPager viewPager(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager themedViewPager(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager viewPager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager themedViewPager(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager viewPager(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (ViewPager) invoke;
    }

    public static final ViewPager themedViewPager(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getVIEW_PAGER().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _ViewPager _viewpager = (_ViewPager) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (ViewPager) invoke;
    }

    public static final DrawerLayout drawerLayout(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout themedDrawerLayout(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout drawerLayout(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout themedDrawerLayout(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout drawerLayout(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (DrawerLayout) invoke;
    }

    public static final DrawerLayout themedDrawerLayout(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getDRAWER_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _DrawerLayout _drawerlayout = (_DrawerLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (DrawerLayout) invoke;
    }

    public static final NestedScrollView nestedScrollView(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView themedNestedScrollView(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView nestedScrollView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView themedNestedScrollView(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView nestedScrollView(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (NestedScrollView) invoke;
    }

    public static final NestedScrollView themedNestedScrollView(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getNESTED_SCROLL_VIEW().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _NestedScrollView _nestedscrollview = (_NestedScrollView) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (NestedScrollView) invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(ViewManager viewManager) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), 0));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(ViewManager viewManager, int i) {
        Intrinsics.checkParameterIsNotNull(viewManager, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(viewManager, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, 0));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(context, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout slidingPaneLayout(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, 0));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (SlidingPaneLayout) invoke;
    }

    public static final SlidingPaneLayout themedSlidingPaneLayout(Activity activity, int i) {
        Intrinsics.checkParameterIsNotNull(activity, "receiver$0");
        View invoke = C$$Anko$Factories$SupportV4ViewGroup.INSTANCE.getSLIDING_PANE_LAYOUT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i));
        _SlidingPaneLayout _slidingpanelayout = (_SlidingPaneLayout) invoke;
        AnkoInternals.INSTANCE.addView(activity, invoke);
        return (SlidingPaneLayout) invoke;
    }
}
