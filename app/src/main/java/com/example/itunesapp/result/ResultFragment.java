package com.example.itunesapp.result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.itunesapp.AppDelegate;
import com.example.itunesapp.R;
import com.example.itunesapp.Response;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;

public class ResultFragment extends Fragment {
    private RecyclerView mRecycler;
    private EditText mSearchETV;
    private final ResultAdapter mResultAdapter = new ResultAdapter();
    private List<Result> mResultList;


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
    }

    public void getResult(String textSearch){
        ApiUtils.getApi().getResponse(textSearch).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Log.d("Ошибка", String.valueOf(response.body()));
                    mResultList = response.body().getResults();
                    mResultAdapter.addData(mResultList);
                }else{
                    Toast.makeText(getContext(), "Не удалось загрузить данные",Toast.LENGTH_SHORT).show();
                    Log.d("Ошибка", String.valueOf(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getContext(), "Не удалось загрузить данные 2" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
