package com.reddit.nhnic.reddit.dtos;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reddit.nhnic.reddit.R;

import java.util.ArrayList;

/**
 * Created by nhnic on 5/7/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private final String TAG = "PostAdapter";

    private ArrayList<PostDTO.Post> posts;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

        public ViewHolder(View v) {
            super(v);
        }
    }

    public PostAdapter(ArrayList<PostDTO.Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_post, parent, false);
        final ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
