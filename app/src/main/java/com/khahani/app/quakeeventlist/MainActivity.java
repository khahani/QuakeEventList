package com.khahani.app.quakeeventlist;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String EVENT_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    public ProgressBar loading;

    private ListView mQuakeListView;

    private QuakeAdapter adapter;

    private AdapterView.OnItemClickListener mItemListenr = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Event event = (Event)adapterView.getItemAtPosition(position);

            String siteUrl = event.getSiteUrl();

            if (siteUrl == null || siteUrl.isEmpty()){
                Toast.makeText(MainActivity.this, "There is no more information", Toast.LENGTH_SHORT).show();
                return;
            }

            openWebPage(siteUrl);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = findViewById(R.id.pb_loading);

        mQuakeListView = findViewById(R.id.lv_quakes);

        mQuakeListView.setOnItemClickListener(mItemListenr);

        new QuakeAsyncTask().execute(EVENT_URL);
    }

    public class QuakeAsyncTask extends AsyncTask<String, Void, List<Event>> {

        @Override
        protected void onPreExecute() {
            loading.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Event> doInBackground(String... urls) {
            String url = urls[0];
            return NetworkUtil.fetchData(url);
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            //super.onPostExecute(events);
            loading.setVisibility(View.INVISIBLE);

            adapter = new QuakeAdapter(MainActivity.this, events);

            mQuakeListView.setAdapter(adapter);
        }
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
