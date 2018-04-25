package com.step.lifehuck.mvp.contracts;

import com.step.lifehuck.entities.Good;
import com.step.lifehuck.entities.LifeHuck;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by mamedov on 02.04.2018.
 */

public interface MainContract {
    interface view{
        void showGoods(List<Good> goods);
        void showIndicator();
        void hideIndicetor();
        Observable<Integer> onUpdateClick();
        Observable<String> onSelectView();
        void showToast(String s);

    }
    interface presenter extends Presenter<view>{}
    interface model{

        Observable<List<LifeHuck>> getHucks();

        Observable<List<Good>> getGoods();
    }
}
