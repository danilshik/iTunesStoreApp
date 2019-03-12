package com.example.itunesapp.ui.result;

import com.example.itunesapp.common.BaseView;
import com.example.itunesapp.data.Result;

import java.util.List;

public interface ResultsView extends BaseView {

    void showResults(List<Result> resultList);
}
