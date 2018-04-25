package com.step.lifehuck.di;

import android.app.Application;
import android.content.Context;

import com.step.lifehuck.di.components.AppComponent;
import com.step.lifehuck.di.components.DaggerAppComponent;
import com.step.lifehuck.di.modules.MvpModule;

public class BaseApp extends Application {
    private AppComponent appComponent;

    public AppComponent getInjector(){
        if(appComponent==null){
            appComponent = DaggerAppComponent.builder()
                    .mvpModule(new MvpModule(this))
                    .build();
        }
        return appComponent;
    }
    public static BaseApp get(Context ctx){
        return (BaseApp)ctx.getApplicationContext();
    }
}
