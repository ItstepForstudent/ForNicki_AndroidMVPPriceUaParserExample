package com.step.lifehuck.mvp.presenters;

import android.content.Context;

import com.step.lifehuck.di.BaseApp;
import com.step.lifehuck.mvp.contracts.MainContract;

import javax.inject.Inject;

/**
 * Created by mamedov on 02.04.2018.
 */

public class MainPresenter implements MainContract.presenter {
    MainContract.view view;
    @Inject MainContract.model model;

    @Inject
    public MainPresenter(Context context) {
        BaseApp.get(context).getInjector().inject(this);
    }

    @Override
    public void onAttachView(MainContract.view mvpView) {
        view=mvpView;
        update();
        view.onUpdateClick().subscribe(id->update());
        view.onSelectView().subscribe(s->view.showToast(s));

    }


    void update(){
        view.showIndicator();
        view.showToast("Start loading");
        model.getGoods().subscribe(goods->{
            view.hideIndicetor();
            view.showToast("Stop loading");
            view.showGoods(goods);
        });
//        model.getHucks().subscribe(hucks->{
//
//            view.showHucks(hucks);
//        });
    }

    @Override
    public void onDetachView() {
        view=null;
    }
}
