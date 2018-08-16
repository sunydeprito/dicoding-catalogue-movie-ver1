package com.raditya.achmad.cataloguemovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Achmad on 14-07-18
 */
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    ListView lvMovie;
    EditText edtTitle;
    ImageView imgMovie;
    Button btnFindMovie;
    MovieAdapter adapter;

    static final String EXTRAS_MOVIE = "extras_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();

        lvMovie = (ListView) findViewById(R.id.lv_movie);
        edtTitle = (EditText) findViewById(R.id.edt_movie);
        imgMovie = (ImageView) findViewById(R.id.image_movie);

        btnFindMovie = (Button) findViewById(R.id.btn_find);
        //Run movie listener function
        btnFindMovie.setOnClickListener(movieListener);

        String title = edtTitle.getText().toString();
        lvMovie.setAdapter(adapter);
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {

                MovieItems item = (MovieItems) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, ActivityDetailMovie.class);

                intent.putExtra(ActivityDetailMovie.EXTRA_TITLE, item.getMovTitle());
                intent.putExtra(ActivityDetailMovie.EXTRA_OVERVIEW, item.getMovOverview());
                intent.putExtra(ActivityDetailMovie.EXTRA_DATE_RELEASE, item.getMovDateRelease());
                intent.putExtra(ActivityDetailMovie.EXTRA_IMAGE_MOVIE, item.getMovImage());
                intent.putExtra(ActivityDetailMovie.EXTRA_RATING, item.getMovRating());
                intent.putExtra(ActivityDetailMovie.EXTRA_VOTE, item.getMovVote());

                startActivity(intent);
            }
        });

        //bundle is used to get data when button is clicked
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, title);

        getLoaderManager().initLoader(0, bundle, this);
    }


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, Bundle bundle) {
        String title = "";
        if (bundle != null) {
            title = bundle.getString(EXTRAS_MOVIE);
        }

        return new MovieAsyncTaskLoader(this, title);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        adapter.setData(movieItems);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    //listener function if button is clicked, check value based on title
    View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String title = edtTitle.getText().toString();
            if (TextUtils.isEmpty(title)) {
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, title);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}