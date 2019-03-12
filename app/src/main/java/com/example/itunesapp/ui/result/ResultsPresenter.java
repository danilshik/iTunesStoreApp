package com.example.itunesapp.ui.result;

import com.example.itunesapp.utils.ApiUtils;
import com.example.itunesapp.common.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ResultsPresenter extends BasePresenter {

    private ResultsView mView;

    public ResultsPresenter(ResultsView view) {
        mView = view;
    }

    public void getResults(String textSearch){
        mCompositeDisposable.add(ApiUtils.getApi()
                .getResponse(textSearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mView.showRefresh())
                .doFinally(() -> mView.hideRefresh())
                .subscribe(
                        response -> {
                            mView.showResults(response.getResults());
                        },
                        throwable -> mView.showError()
                ));
    }

}
