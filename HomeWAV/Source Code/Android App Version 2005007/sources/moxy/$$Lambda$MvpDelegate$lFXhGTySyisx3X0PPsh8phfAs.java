package moxy;

import java.util.Comparator;
import moxy.presenter.PresenterField;

/* renamed from: moxy.-$$Lambda$MvpDelegate$lFXhGTySyisx3X0PPsh-8p-hfAs  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MvpDelegate$lFXhGTySyisx3X0PPsh8phfAs implements Comparator {
    public static final /* synthetic */ $$Lambda$MvpDelegate$lFXhGTySyisx3X0PPsh8phfAs INSTANCE = new $$Lambda$MvpDelegate$lFXhGTySyisx3X0PPsh8phfAs();

    private /* synthetic */ $$Lambda$MvpDelegate$lFXhGTySyisx3X0PPsh8phfAs() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((PresenterField) obj).getTag(null).compareTo(((PresenterField) obj2).getTag(null));
    }
}
