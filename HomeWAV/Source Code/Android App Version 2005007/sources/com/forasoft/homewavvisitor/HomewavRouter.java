package com.forasoft.homewavvisitor;

import com.forasoft.homewavvisitor.navigation.ResultListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0006R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/HomewavRouter;", "Lru/terrakok/cicerone/Router;", "()V", "resultListeners", "Ljava/util/HashMap;", "", "Lcom/forasoft/homewavvisitor/navigation/ResultListener;", "Lkotlin/collections/HashMap;", "exitWithResult", "", "resultCode", "result", "", "(Ljava/lang/Integer;Ljava/lang/Object;)V", "removeResultListener", "(Ljava/lang/Integer;)V", "sendResult", "", "(Ljava/lang/Integer;Ljava/lang/Object;)Z", "setResultListener", "listener", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HomewavRouter.kt */
public final class HomewavRouter extends Router {
    private final HashMap<Integer, ResultListener> resultListeners = new HashMap<>();

    public final void setResultListener(int i, ResultListener resultListener) {
        Intrinsics.checkParameterIsNotNull(resultListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.resultListeners.put(Integer.valueOf(i), resultListener);
    }

    public final void removeResultListener(Integer num) {
        Map map = this.resultListeners;
        if (map != null) {
            TypeIntrinsics.asMutableMap(map).remove(num);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
    }

    public final boolean sendResult(Integer num, Object obj) {
        ResultListener resultListener = (ResultListener) this.resultListeners.get(num);
        if (resultListener == null) {
            return false;
        }
        resultListener.onResult(obj);
        return true;
    }

    public final void exitWithResult(Integer num, Object obj) {
        exit();
        sendResult(num, obj);
    }
}
