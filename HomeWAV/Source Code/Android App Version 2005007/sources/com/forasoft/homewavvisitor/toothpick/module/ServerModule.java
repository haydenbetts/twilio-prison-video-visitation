package com.forasoft.homewavvisitor.toothpick.module;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.payment.PaymentStateHolder;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.AddConnectionInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterDataInteractor;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.repository.auth.AuthRepository;
import com.forasoft.homewavvisitor.model.repository.register.RegisterRepository;
import com.forasoft.homewavvisitor.model.server.apis.AccountApi;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.apis.PaymentApi;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.model.system.RingtoneManager;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import com.forasoft.homewavvisitor.navigation.CiceroneHolder;
import com.forasoft.homewavvisitor.toothpick.FacilitiesSubjectWrapper;
import com.forasoft.homewavvisitor.toothpick.provider.GsonProvider;
import com.forasoft.homewavvisitor.toothpick.provider.OkHttpClientProvider;
import com.forasoft.homewavvisitor.toothpick.provider.RetrofitProvider;
import com.forasoft.homewavvisitor.toothpick.provider.api.AccountApiProvider;
import com.forasoft.homewavvisitor.toothpick.provider.api.HomewavApiProvider;
import com.forasoft.homewavvisitor.toothpick.provider.api.PaymentApiProvider;
import com.forasoft.homewavvisitor.toothpick.provider.api.SignUpApiProvider;
import com.forasoft.homewavvisitor.toothpick.qualifier.Cards;
import com.forasoft.homewavvisitor.toothpick.qualifier.ServerPath;
import com.google.gson.Gson;
import io.reactivex.subjects.BehaviorSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/module/ServerModule;", "Ltoothpick/config/Module;", "serverUrl", "", "(Ljava/lang/String;)V", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ServerModule.kt */
public final class ServerModule extends Module {
    public ServerModule(String str) {
        Intrinsics.checkParameterIsNotNull(str, "serverUrl");
        bind(Constants.class).toInstance(new Constants());
        Cicerone create = Cicerone.create(new HomewavRouter());
        Intrinsics.checkExpressionValueIsNotNull(create, "Cicerone.create(HomewavRouter())");
        bind(Router.class).toInstance(create.getRouter());
        bind(HomewavRouter.class).toInstance(create.getRouter());
        bind(NavigatorHolder.class).toInstance(create.getNavigatorHolder());
        bind(CiceroneHolder.class).singleton();
        bind(BehaviorSubject.class).toInstance(BehaviorSubject.create());
        bind(BehaviorSubject.class).withName(Cards.class).toInstance(BehaviorSubject.create());
        bind(RegisterRepository.class).singleton();
        bind(RegisterStepsInteractor.class).singleton();
        bind(RegisterDataInteractor.class).singleton();
        bind(AddConnectionInteractor.class).singleton();
        bind(String.class).withName(ServerPath.class).toInstance(str);
        bind(Gson.class).toProvider(GsonProvider.class).providesSingleton();
        bind(Retrofit.class).toProvider(RetrofitProvider.class).providesSingleton();
        bind(OkHttpClient.class).toProvider(OkHttpClientProvider.class).providesSingleton();
        bind(SignUpApi.class).toProvider(SignUpApiProvider.class).providesSingleton();
        bind(PaymentApi.class).toProvider(PaymentApiProvider.class).providesSingleton();
        bind(AccountApi.class).toProvider(AccountApiProvider.class).providesSingleton();
        bind(HomewavApi.class).toProvider(HomewavApiProvider.class).providesSingleton();
        bind(PaymentGateway.class).singleton();
        bind(PaymentStateHolder.class).singleton();
        bind(AuthRepository.class).singleton();
        bind(AuthInteractor.class).singleton();
        bind(HeartbeatRepository.class).singleton();
        bind(PusherHolder.class).singleton();
        bind(FacilitiesSubjectWrapper.class).toInstance(new FacilitiesSubjectWrapper());
        bind(BusNotifier.class).singleton();
        bind(RingtoneManager.class).singleton();
    }
}
