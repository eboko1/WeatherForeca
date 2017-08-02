package fvi.at.ua.weatherforeca;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView wDataTextView;
    ProgressBar wProgresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wDataTextView = (TextView)findViewById(R.id.tv_weather_data);
        wProgresBar = (ProgressBar)findViewById(R.id.progressBar);

        loaderWeatherData();

        String[] data =  {
                "Today, May 17 - Clear - 17°C / 15°C",
                "Tomorrow - Cloudy - 19°C / 15°C",
                "Thursday - Rainy- 30°C / 11°C",
                "Friday - Thunderstorms - 21°C / 9°C",
                "Saturday - Thunderstorms - 16°C / 7°C",
                "Sunday - Rainy - 16°C / 8°C",
                "Monday - Partly Cloudy - 15°C / 10°C",
                "Tue, May 24 - Meatballs - 16°C / 18°C",
                "Wed, May 25 - Cloudy - 19°C / 15°C",
                "Thu, May 26 - Stormy - 30°C / 11°C",
                "Fri, May 27 - Hurricane - 21°C / 9°C",
                "Sat, May 28 - Meteors - 16°C / 7°C",
                "Sun, May 29 - Apocalypse - 16°C / 8°C",
                "Mon, May 30 - Post Apocalypse - 15°C / 10°C",
        };
    }

    private void loaderWeatherData(){
       new FetchWeatherTask().execute();
    }

    public class FetchWeatherTask extends AsyncTask<String, Void, String[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            wProgresBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            wProgresBar.setVisibility(View.INVISIBLE);

            if(strings != null){
                for(String weatherStrings: strings){
                    wDataTextView.append(weatherStrings + "\n\n\n");
                }
            }
        }
    }
}
