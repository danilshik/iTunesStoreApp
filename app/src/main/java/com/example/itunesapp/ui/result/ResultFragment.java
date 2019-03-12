package com.example.itunesapp.ui.result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.itunesapp.R;
import com.example.itunesapp.common.PresenterFragment;
import com.example.itunesapp.data.Result;

import java.util.List;

public class ResultFragment extends PresenterFragment implements SwipeRefreshLayout.OnRefreshListener, ResultsView {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycler;
    private EditText mSearchETV;
    private final ResultAdapter mResultAdapter = new ResultAdapter();
    private View mErrorView;
    private View mListEmptyView;

    @InjectPresenter
    ResultsPresenter mPresenter;

    @ProvidePresenter
    ResultsPresenter providePresenter(){
        return new ResultsPresenter(this);
    }
    @Override
    protected ResultsPresenter getPresenter() {
        return mPresenter;
    }

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
                mPresenter.getResults(searchText);

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

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.post(() -> {
            String searchText = mSearchETV.getText().toString();
            if(!searchText.isEmpty()){
                mPresenter.getResults(searchText);
            }
            else{
                Toast.makeText(getContext(), getString(R.string.search_empty), Toast.LENGTH_SHORT).show();
            }
            if(mSwipeRefreshLayout.isRefreshing()){
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }



    @Override
    public void showResults(List<Result> resultList) {
        if(resultList.size() == 0){
            mListEmptyView.setVisibility(View.VISIBLE);
            mRecycler.setVisibility(View.GONE);
        }
        else{
            mListEmptyView.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
        }
        mResultAdapter.addData(resultList, true);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mListEmptyView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        mPresenter = null;
        super.onDetach();
    }
}
