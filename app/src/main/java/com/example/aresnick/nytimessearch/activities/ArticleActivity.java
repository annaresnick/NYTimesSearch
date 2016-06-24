package com.example.aresnick.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.aresnick.nytimessearch.Article;
import com.example.aresnick.nytimessearch.R;

public class ArticleActivity extends AppCompatActivity {
    private Intent shareIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView rvArticle = (RecyclerView) findViewById(R.id.rvArticles);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
   //     setSupportActionBar(toolbar);

        Article article = (Article) getIntent().getSerializableExtra("article");

        /*
        // Add the scroll listener
        rvArticle.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                customLoadMoreDataFromApi(page);
            }
        });
        // Append more data into the adapter
        // This method probably sends out a network request and appends new data items to your adapter.
        public void customLoadMoreDataFromApi(int offset) {
            // Send an API request to retrieve appropriate data using the offset value as a parameter.
            // Deserialize API response and then construct new objects to append to the adapter
            // Add the new objects to the data source for the adapter
            items.addAll(moreItems);
            // For efficiency purposes, notify the adapter of only the elements that got changed
            // curSize will equal to the index of the first element inserted because the list is 0-indexed
            int curSize = adapter.getItemCount();
            adapter.notifyItemRangeInserted(curSize, items.size() - 1);
        }
        */

        WebView webView = (WebView) findViewById(R.id.wvArticle);
        assert webView != null;
        webView.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                        view.loadUrl(url);
                                        return true;
                                    }
                                 }
        );
        webView.loadUrl(article.getWebUrl());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_article, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        ShareActionProvider miShare = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        // get reference to WebView
        WebView wvArticle = (WebView) findViewById(R.id.wvArticle);
        // pass in the URL currently being used by the WebView
        shareIntent.putExtra(Intent.EXTRA_TEXT, wvArticle.getUrl());
        miShare.setShareIntent(shareIntent);


        return super.onCreateOptionsMenu(menu);
    }

}
