package com.step.lifehuck.mvp.contracts;

/**
 * Created by mamedov on 02.04.2018.
 */

public interface Presenter<V> {
    void onAttachView(V mvpView);
    void onDetachView();
}
