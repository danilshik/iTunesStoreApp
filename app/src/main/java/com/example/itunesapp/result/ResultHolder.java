package com.example.itunesapp.result;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.itunesapp.R;

public class ResultHolder extends RecyclerView.ViewHolder {
    private TextView mArtistName;
    private TextView mCollectionName;

    public ResultHolder(@NonNull View itemView) {
        super(itemView);
        mArtistName = itemView.findViewById(R.id.tv_artist_name);
        mCollectionName = itemView.findViewById(R.id.tv_collection_name);
    }

    public void bind(Result result) {
        mArtistName.setText(result.getArtistName());
        mCollectionName.setText(result.getCollectionName());

    }
}
