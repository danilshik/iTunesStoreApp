package com.example.itunesapp.result;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itunesapp.R;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultHolder> {

    private final List<Result> mResultList = new ArrayList<>();
    private View view;


    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.list_item_result, viewGroup, false);
        return new ResultHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder viewHolder, int i) {
        viewHolder.bind(mResultList.get(i));
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public void addData(List<Result> results){
        mResultList.addAll(results);
        notifyDataSetChanged();
    }
}
