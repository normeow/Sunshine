package example.normeow.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements ForecastFragment.onItemSelectedListener{

    //todo add check internet-connetcioin, loading animation while app loading info in background
    //todo add patterns for humidity, pressure and wind (StringFormat)
    private android.support.v4.app.Fragment forecastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.art_clear);
        forecastFragment = new ForecastFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, forecastFragment).commit();

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        Log.v("This", Float.toString(dpWidth));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_map){
            openPreferredLocationMap();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationMap(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String location = prefs.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        // Using the URI scheme for showing a location found on a map.  This super-handy
        // intent can is detailed in the "Common Intents" page of Android's developer site:
        // http://developer.android.com/guide/components/intents-common.html#Maps

        Uri geoLocation = Uri.parse("geo: 0, 0?").buildUpon().appendQueryParameter("q", location).build();

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Log.d("MainActivity: ", "Couldn't call " + location + ", no receiving apps installed!");
    }

    @Override
    public void onItemSelected(DayWeather dayWeather) {
        DetailActivityFragment fragment = (DetailActivityFragment)getSupportFragmentManager().findFragmentById(R.id.details_fragment);
        if (fragment != null)
        {
            Log.v("THIS", "Here I am");
            fragment.changeDayWeatherObj(dayWeather);
        }
        else
        {
            Intent intent = new Intent(this, DetailActivity.class).putExtra(ForecastFragment.EXTRA_DAYWEATHER, dayWeather);
            startActivity(intent);
        }

    }
}
