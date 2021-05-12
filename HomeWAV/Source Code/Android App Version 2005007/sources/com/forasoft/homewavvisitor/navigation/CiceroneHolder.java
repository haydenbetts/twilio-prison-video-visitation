package com.forasoft.homewavvisitor.navigation;

import com.forasoft.homewavvisitor.HomewavRouter;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Cicerone;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0005R\"\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/CiceroneHolder;", "", "()V", "cicerones", "Ljava/util/HashMap;", "", "Lru/terrakok/cicerone/Cicerone;", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "getCicerone", "key", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CiceroneHolder.kt */
public final class CiceroneHolder {
    private final HashMap<String, Cicerone<HomewavRouter>> cicerones = new HashMap<>();

    public final Cicerone<HomewavRouter> getCicerone(String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        if (!this.cicerones.containsKey(str)) {
            this.cicerones.put(str, Cicerone.create(new HomewavRouter()));
        }
        return this.cicerones.get(str);
    }
}
