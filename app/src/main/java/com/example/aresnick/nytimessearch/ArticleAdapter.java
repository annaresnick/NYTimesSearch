package com.example.aresnick.nytimessearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by aresnick on 6/21/2016.
 */
public class ArticleAdapter extends
        RecyclerView.Adapter<ArticleAdapter.ViewHolder>{

    private List<Article> articles;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public ArticleAdapter(Context context, List<Article> contacts) {
        articles = contacts;
        mContext = context;
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView tvTitle;
        public ViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.ivImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            imageView.setImageResource(0);
        }
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_article_result, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Article article = articles.get(position);

        // Set item views based on the data model

        String thumbnail = article.getThumbNail();
        viewHolder.tvTitle.setText(article.getHeadline());
        if (!TextUtils.isEmpty(thumbnail)){
            Glide.with(mContext).load(thumbnail).into(viewHolder.imageView);
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return articles.size();
    }
}

