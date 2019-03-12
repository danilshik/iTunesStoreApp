package com.example.itunesapp.ui.result;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.itunesapp.R;
import com.example.itunesapp.data.Result;
import com.facebook.drawee.view.SimpleDraweeView;

public class ResultHolder extends RecyclerView.ViewHolder {
    private TextView mArtistName;
    private TextView mCollectionName;
    private TextView mTrackName;
    SimpleDraweeView mSimpleDraweeView;

    public ResultHolder(@NonNull View itemView) {
        super(itemView);
        mArtistName = itemView.findViewById(R.id.tv_artist_name);
        mCollectionName = itemView.findViewById(R.id.tv_collection_name);
        mSimpleDraweeView = itemView.findViewById(R.id.image);
        mTrackName = itemView.findViewById(R.id.tv_track_name);

    }

    public void bind(Result result) {
        mArtistName.setText(result.getArtistName());
        mCollectionName.setText(result.getCollectionName());
        mSimpleDraweeView.setImageURI(Uri.parse(result.getArtworkUrl100()));
        mTrackName.setText(result.getTrackName());

    }
}
