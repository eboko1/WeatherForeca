package fvi.at.ua.weatherforeca.ui.ui;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import fvi.at.ua.weatherforeca.R;
import fvi.at.ua.weatherforeca.ui.model.adapter.WeatherArrayAdapter;
import fvi.at.ua.weatherforeca.ui.model.pojo.Weather;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = "MainActivity";

    private List<Weather> weatherList;
    private WeatherArrayAdapter weatherArrayAdapter;
    private ListView weatherListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG, "onCreate ");

        //Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        weatherList = new ArrayList<>();

        // init ListView
        weatherListView = (ListView)findViewById(R.id.weatherListView);
        // adapter
        weatherArrayAdapter = new WeatherArrayAdapter(this, weatherList);
        weatherListView.setAdapter(weatherArrayAdapter);

        // button
        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText locationEditText = (EditText)findViewById(R.id.locationEditText);
                URL url = createURL(locationEditText.getText().toString());
               if(url != null){
                   Log.i(LOG, "onClick url not null  = " + url);
                   dismissKeyboard(locationEditText);
                   GetWeatherTask getLocalWeatherTAsk = new GetWeatherTask();
                   getLocalWeatherTAsk.execute(url);
               } else {
                   Snackbar.make(findViewById(R.id.coordinator_layout),R.string.invalid_url, Snackbar.LENGTH_LONG).show();
               }
            }
        });
    }

    private void dismissKeyboard(View v) {
        Log.i(LOG, "dismissKeyboard View= " + v);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private URL createURL(String city) {
        String apiKey = getString(R.string.api_key);
        String baseURL = getString(R.string.web_service_url);

        //http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID= apy_key
        try {
            String urlString = baseURL + URLEncoder.encode(city, "UTF-8") + "&APPID=" + apiKey;
            Log.i(LOG, "createURL = " + urlString);
            return new URL(urlString);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.forecast_menu, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
