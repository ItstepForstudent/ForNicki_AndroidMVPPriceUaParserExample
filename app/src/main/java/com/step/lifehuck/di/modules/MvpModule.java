package com.step.lifehuck.di.modules;

import android.content.Context;

import com.step.lifehuck.di.components.AppComponent;
import com.step.lifehuck.mvp.contracts.MainContract;
import com.step.lifehuck.mvp.models.MainModel;
import com.step.lifehuck.mvp.presenters.MainPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MvpModule {
    Context context;
    public MvpModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    MainContract.presenter provideMainContractPresenter(Context context){
        return new MainPresenter(context);
    }
    @Provides
    @Singleton
    MainContract.model provideMainContractModel(){
        return new MainModel();
    }
}
