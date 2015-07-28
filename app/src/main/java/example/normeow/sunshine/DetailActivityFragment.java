package example.normeow.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private String mForecast;
    private DayWeather dayWeather;
    private final String SUNSHINE_HASHTAG = "#SunshineApp";

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.detailfragment, menu);

        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
        if (shareActionProvider != null)
            shareActionProvider.setShareIntent(createShareForecastIntent());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        if (intent != null && intent.hasExtra(ForecastFragment.EXTRA_DAYWEATHER)){

            dayWeather = intent.getParcelableExtra(ForecastFragment.EXTRA_DAYWEATHER);
            TextView dayTextView = (TextView) view.findViewById(R.id.details_day_textview);
            TextView dateTextView = (TextView) view.findViewById(R.id.details_date_textview);
            TextView weatherTextView = (TextView) view.findViewById(R.id.weather_state_details_textview);
            TextView highTextView = (TextView) view.findViewById(R.id.details_high_textview);
            TextView lowTextView = (TextView) view.findViewById(R.id.details_low_textview);
            TextView humidityTextView = (TextView)view.findViewById(R.id.humidity_tv);
            TextView pressureTextView = (TextView)view.findViewById(R.id.pressure_tv);
            TextView windTextView = (TextView)view.findViewById(R.id.wind_tv);
            ImageView imageView = (ImageView) view.findViewById(R.id.details_weather_pic);


            String windUnits = getResources().getString(R.string.wind_metric);
            if (ForecastFragment.unitsType.equals(getResources().getString(R.string.pref_units_imperial)))
                windUnits = getResources().getString(R.string.wind_imperial);

            dayTextView.setText(dayWeather.getDay());
            dateTextView.setText(dayWeather.getDate());
            weatherTextView.setText(dayWeather.getWeather());
            humidityTextView.setText("Humidity: " + Integer.toString((int)dayWeather.getHumidity()) + " %");
            pressureTextView.setText("Pressure: " +Integer.toString((int)dayWeather.getPressure()) + " " + getResources().getString(R.string.pressure_units));
            windTextView.setText("Wind: " + Double.toString(dayWeather.getWind_speed()) + " " + windUnits + " " + dayWeather.getWindDirection());


            highTextView.setText(Integer.toString((int) (dayWeather.getHighTemperature())));
            lowTextView.setText(Integer.toString((int)(dayWeather.getLowTemperature())));

            imageView.setImageResource(dayWeather.getArtIconId());

        }
        return view;
    }


    public Intent createShareForecastIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mForecast + " " + SUNSHINE_HASHTAG);
        return intent;
    }
}
