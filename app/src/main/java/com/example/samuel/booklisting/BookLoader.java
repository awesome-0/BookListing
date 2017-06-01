package com.example.samuel.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.support.annotation.UiThread;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Samuel on 30/05/2017.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Books>> {
    public BookLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Books> loadInBackground() {
        String dummyyString = FetchBookData.FetchJson();

            return (FetchBookData.FetchDataFromJson(dummyyString));

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public void deliverResult(ArrayList<Books> data) {
        super.deliverResult(data);
    }
}
