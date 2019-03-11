package com.example.itunesapp.result;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itunesapp.ApiUtils;
import com.example.itunesapp.R;
import com.example.itunesapp.Response;

import java.util.List;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

public class ResultFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycler;
    private EditText mSearchETV;
    private final ResultAdapter mResultAdapter = new ResultAdapter();
    private View mErrorView;
    private View mListEmptyView;


    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecycler = view.findViewById(R.id.recycler);
        mSearchETV = view.findViewById(R.id.etv_search);
        mSwipeRefreshLayout = view.findViewById(R.id.refresher);
        mErrorView = view.findViewById(R.id.error_view);
        mListEmptyView = view.findViewById(R.id.list_empty_view);

        mSearchETV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();
                getResult(searchText);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mResultAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    public void getResult(String textSearch) {
        ApiUtils.getApi()
                .getResponse(textSearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mSwipeRefreshLayout.setRefreshing(true))
                .doFinally(() -> mSwipeRefreshLayout.setRefreshing(false))
                .subscribe(
                        response -> showData(response.getResults()),
                        throwable -> showError()
                );

    }



    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {



                String searchText = mSearchETV.getText().toString();
                if(!searchText.isEmpty()){
                    getResult(searchText);
                }
                else{
                    Toast.makeText(getContext(), "Строка поиска пуста. Введите запрос", Toast.LENGTH_SHORT).show();
                }
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }
        });



    }

    private void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mListEmptyView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);
    }

    private void showData(List<Result> mResultList) {
        Log.d("addData", String.valueOf(mResultList.size()));
        if(mResultList.size() == 0){
            mListEmptyView.setVisibility(View.VISIBLE);
            mRecycler.setVisibility(View.GONE);
        }
        else{
            mListEmptyView.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
        }
        mResultAdapter.addData(mResultList, true);
        mErrorView.setVisibility(View.GONE);
    }
}
