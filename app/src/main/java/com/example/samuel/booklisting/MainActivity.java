package com.example.samuel.booklisting;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Books>> {
   public static String search = "";
   private ListView  listview;
    private TextView status_text;
    private ProgressBar progress;
    private  ListAdapter adapter;
    private  LoaderManager lm = getLoaderManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (ProgressBar)findViewById(R.id.progress_bar);
        status_text =(TextView)findViewById(R.id.status_text);
        progress.setVisibility(View.GONE);
        status_text.setVisibility(View.GONE);
       // listview.setVisibility(View.GONE);
        Button search_button = (Button) findViewById(R.id.search_button);
        listview = (ListView) findViewById(R.id.search_list_view);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config);


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.destroyLoader(1);
                listview.setVisibility(View.GONE);
                EditText searchbar = (EditText)findViewById(R.id.search_text);
               String search_criteria = String.valueOf(searchbar.getText());
                search = search_criteria;
                progress.setVisibility(View.VISIBLE);
                status_text.setVisibility(View.GONE);

                lm.initLoader(1,null,MainActivity.this);

            }
        });




    }

    @Override
    public Loader<ArrayList<Books>> onCreateLoader(int id, Bundle args) {
        if(isOnline()){
        return new BookLoader(MainActivity.this);
        }
        else{
            status_text.setText("No internet Connection");
            status_text.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
            return null;
        }

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Books>> loader, ArrayList<Books> data) {

        if(data == null){
            status_text.setText("Can't load any data, please retry");
            status_text.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);

        }
          else if( data.isEmpty()){
            NoResult();
        }
       else  {


            adapter = new BookAdapter(this, data);
                progress.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Books book = (Books) parent.getItemAtPosition(position);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(book.getBook_link()));
                    startActivity(i);

                }
            });
                listview.setAdapter(adapter);
            }



    }



    @Override
    public void onLoaderReset(Loader<ArrayList<Books>> loader) {
    loader.reset();

    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public void NoResult(){
        status_text.setText("Sorry...your search brought no results");
        status_text.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
    }
}
