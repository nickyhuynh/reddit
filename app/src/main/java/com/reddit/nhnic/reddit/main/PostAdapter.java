package com.reddit.nhnic.reddit.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.reddit.nhnic.reddit.R;
import com.reddit.nhnic.reddit.dtos.PostDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by nhnic on 5/7/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private final String TAG = "PostAdapter";

    private ArrayList<PostDTO.Post> posts;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ImageView upvote;
        public ImageView downvote;
        public TextView overallVotes;
        public TextView title;

        public TextView upvotesNumber;
        public TextView downvotesNumber;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;

            upvote = v.findViewById(R.id.upvote);
            downvote = v.findViewById(R.id.downvote);
            overallVotes = v.findViewById(R.id.overall_votes);
            title = v.findViewById(R.id.post_title);

            upvotesNumber = v.findViewById(R.id.upvotes_number);
            downvotesNumber = v.findViewById(R.id.downvotes_number);
        }

        public ViewHolder(View v) {
            super(v);
        }
    }

    public PostAdapter(ArrayList<PostDTO.Post> posts) {
        this.posts = posts;
    }

    @Override
    public @NonNull ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_post, parent, false);
        final ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final PostDTO.Post post = posts.get(position);

        holder.title.setText(post.title);

        /*
            All modifications are made to the original entry in the array so that it updates the count dynamically.
            When changes are made to an entry in a RecyclerView, it updates the item at that position in the layout.
            As they change, it updates the value in the UI as well through a notifyItemChanged(int position) call which recalls this.
         */
        holder.downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posts.get(position).downvotes++;
                holder.overallVotes.setText(withSuffix(post.upvotes - post.downvotes));
                holder.downvotesNumber.setText(withSuffix(posts.get(position).downvotes));
            }
        });

        holder.upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posts.get(position).upvotes++;
                holder.overallVotes.setText(withSuffix(post.upvotes - post.downvotes));
                holder.upvotesNumber.setText(withSuffix(posts.get(position).upvotes));
            }
        });

        holder.downvotesNumber.setText(withSuffix(post.downvotes));
        holder.upvotesNumber.setText(withSuffix(post.upvotes));
        holder.overallVotes.setText(withSuffix(post.upvotes - post.downvotes));
    }

    @Override
    public int getItemCount() {
        return Math.min(posts.size(), 20);
    }

    //function taken from here: https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp
    private static String withSuffix(long count) {
        if (count < 1000) return String.format("%s", count);
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format(Locale.getDefault(), "%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp-1));
    }
}
