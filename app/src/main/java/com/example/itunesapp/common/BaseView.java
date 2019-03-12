package com.example.itunesapp.common;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {
    void showError();

    void showRefresh();

    void hideRefresh();

}
