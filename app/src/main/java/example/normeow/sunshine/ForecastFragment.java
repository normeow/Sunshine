package example.normeow.sunshine;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    private ArrayAdapter<String> aa;

    @Override
    public void setInitialSavedState(SavedState state) {
        super.setInitialSavedState(state);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        String[] fakeForecast = {
                "Today - Sunny, 43/9",
                "Tomorrow - Cloudy, 43/9",
                "Tuesday - Sunny, 43/38",
                "Wed - Cloudy, 43/9",
                "Thurs - Sunny, 43/9",
                "Fri - Sunny, 43/9",
                "Sat - Sunny, 43/9"
        };

        ArrayList<String> weekForecast = new ArrayList<>(Arrays.asList(fakeForecast));
        aa = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, weekForecast);
        ListView listView = (ListView) view.findViewById(R.id.listview_forecast);
        listView.setAdapter(aa);
        setHasOptionsMenu(true);

        final String WEATHER_DATA_MTV_JUN_4 = "{\"cod\":\"200\",\"message\":4.2116,\"city\":{\"id\":\"5375480\",\"name\":\"Mountain View\",\"coord\":{\"lon\":-122.075,\"lat\":37.4103},\"country\":\"United States of America\",\"population\":0},\"cnt\":7,\"list\":[{\"dt\":1401912000,\"temp\":{\"day\":20.17,\"min\":12.3,\"max\":20.17,\"night\":12.3,\"eve\":17.74,\"morn\":14.05},\"pressure\":1012.43,\"humidity\":77,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":1.67,\"deg\":253,\"clouds\":0},{\"dt\":1401998400,\"temp\":{\"day\":18.9,\"min\":10.74,\"max\":18.9,\"night\":10.74,\"eve\":15.54,\"morn\":14.02},\"pressure\":1009.89,\"humidity\":76,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":1.51,\"deg\":225,\"clouds\":0},{\"dt\":1402084800,\"temp\":{\"day\":13.59,\"min\":13.57,\"max\":14.1,\"night\":14.1,\"eve\":14.04,\"morn\":13.57},\"pressure\":1022.58,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":8.92,\"deg\":325,\"clouds\":0},{\"dt\":1402171200,\"temp\":{\"day\":13.71,\"min\":13.71,\"max\":13.93,\"night\":13.93,\"eve\":13.73,\"morn\":13.93},\"pressure\":1021.29,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":6.41,\"deg\":326,\"clouds\":0},{\"dt\":1402257600,\"temp\":{\"day\":13.55,\"min\":13.52,\"max\":13.72,\"night\":13.52,\"eve\":13.72,\"morn\":13.62},\"pressure\":1022.14,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":9.72,\"deg\":320,\"clouds\":0},{\"dt\":1402344000,\"temp\":{\"day\":12.72,\"min\":12.72,\"max\":13.22,\"night\":13.22,\"eve\":13.19,\"morn\":13.06},\"pressure\":1027.87,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":7.85,\"deg\":322,\"clouds\":7},{\"dt\":1402430400,\"temp\":{\"day\":13.11,\"min\":12.89,\"max\":13.35,\"night\":13.26,\"eve\":13.35,\"morn\":12.89},\"pressure\":1029.35,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":11.01,\"deg\":330,\"clouds\":0}]}";
        final String WEATHER_DATA_FREMONT_JUN_4 = "{\"cod\":\"200\",\"message\":2.5405,\"city\":{\"id\":\"5350734\",\"name\":\"Fremont\",\"coord\":{\"lon\":-121.982,\"lat\":37.5509},\"country\":\"United States of America\",\"population\":0},\"cnt\":7,\"list\":[{\"dt\":1401912000,\"temp\":{\"day\":28.12,\"min\":12.74,\"max\":28.12,\"night\":12.74,\"eve\":23.73,\"morn\":28.12},\"pressure\":1004.41,\"humidity\":52,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":2.41,\"deg\":263,\"clouds\":0},{\"dt\":1401998400,\"temp\":{\"day\":28.58,\"min\":10.3,\"max\":28.58,\"night\":10.3,\"eve\":22.76,\"morn\":16.08},\"pressure\":1002.75,\"humidity\":47,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":3.13,\"deg\":261,\"clouds\":0},{\"dt\":1402084800,\"temp\":{\"day\":26.73,\"min\":11.2,\"max\":27.55,\"night\":11.2,\"eve\":22.94,\"morn\":12.53},\"pressure\":1001.79,\"humidity\":50,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":2.01,\"deg\":267,\"clouds\":0},{\"dt\":1402171200,\"temp\":{\"day\":30.67,\"min\":11.81,\"max\":31.25,\"night\":11.81,\"eve\":25.92,\"morn\":15.5},\"pressure\":1001.95,\"humidity\":48,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":1.91,\"deg\":271,\"clouds\":0},{\"dt\":1402257600,\"temp\":{\"day\":16.6,\"min\":10.32,\"max\":17.52,\"night\":12.62,\"eve\":17.52,\"morn\":10.32},\"pressure\":1003.29,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":2.84,\"deg\":292,\"clouds\":0},{\"dt\":1402344000,\"temp\":{\"day\":14.82,\"min\":10.66,\"max\":16.14,\"night\":11.97,\"eve\":16.14,\"morn\":10.66},\"pressure\":1009.02,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":3.72,\"deg\":305,\"clouds\":12},{\"dt\":1402430400,\"temp\":{\"day\":15.26,\"min\":9.84,\"max\":16.75,\"night\":12.76,\"eve\":16.75,\"morn\":9.84},\"pressure\":1009.9,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":5.87,\"deg\":325,\"clouds\":0}]}";

        Double temp = 0.0;
        try {
            JSONArray js = new JSONObject(WEATHER_DATA_FREMONT_JUN_4).getJSONArray("list");
            temp = js.getJSONObject(4).getJSONObject("temp").getDouble("max");

        } catch (JSONException e) {
        }
        Toast mToast = Toast.makeText(this.getActivity(), temp.toString(), Toast.LENGTH_LONG);
        mToast.show();
        return view;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            FetchWeatherTask weatherTask = new FetchWeatherTask();
            weatherTask.execute("94043");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            String format = "json";
            String units = "metric";
            int numDays = 7;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast

                final String FORECAST_BASE_URL =
                        "http://api.openweathermap.org/data/2.5/forecast/daily?";
                final String QUERY_PARAM = "q";
                final String FORMAT_PARAM = "mode";
                final String UNITS_PARAM = "units";
                final String DAYS_PARAM = "cnt";

                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, params[0])
                        .appendQueryParameter(FORMAT_PARAM, format)
                        .appendQueryParameter(UNITS_PARAM, units)
                        .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                        .build();

                URL url = new URL(builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getWeatherDataFromJson(forecastJsonStr, numDays);
            } catch (JSONException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (result != null){
                aa.clear();
                for (String str : result)
                    aa.add(str);
            }
        }

        private String getReadableDateString(long time) {
            // Because the API returns a unix timestamp (measured in seconds),
            // it must be converted to milliseconds in order to be converted to valid date.
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
            return shortenedDateFormat.format(time);
        }

        /**
         * Prepare the weather high/lows for presentation.
         */
        private String formatHighLows(double high, double low) {
            // For presentation, assume the user doesn't care about tenths of a degree.
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);

            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

        /**
         * Take the String representing the complete forecast in JSON Format and
         * pull out the data we need to construct the Strings needed for the wireframes.
         * <p/>
         * Fortunately parsing is easy:  constructor takes the JSON string and converts it
         * into an Object hierarchy for us.
         */
        private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_LIST = "list";
            final String OWM_WEATHER = "weather";
            final String OWM_TEMPERATURE = "temp";
            final String OWM_MAX = "max";
            final String OWM_MIN = "min";
            final String OWM_DESCRIPTION = "main";

            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

            // OWM returns daily forecasts based upon the local time of the city that is being
            // asked for, which means that we need to know the GMT offset to translate this data
            // properly.

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            Time dayTime = new Time();
            dayTime.setToNow();

            // we start at the day returned by local time. Otherwise this is a mess.
            int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

            // now we work exclusively in UTC
            dayTime = new Time();

            String[] resultStrs = new String[numDays];
            for (int i = 0; i < weatherArray.length(); i++) {
                // For now, using the format "Day, description, hi/low"
                String day;
                String description;
                String highAndLow;

                // Get the JSON object representing the day
                JSONObject dayForecast = weatherArray.getJSONObject(i);

                // The date/time is returned as a long.  We need to convert that
                // into something human-readable, since most people won't read "1400356800" as
                // "this saturday".
                long dateTime;
                // Cheating to convert this to UTC time, which is what we want anyhow
                dateTime = dayTime.setJulianDay(julianStartDay + i);
                day = getReadableDateString(dateTime);

                // description is in a child array called "weather", which is 1 element long.
                JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
                description = weatherObject.getString(OWM_DESCRIPTION);

                // Temperatures are in a child object called "temp".  Try not to name variables
                // "temp" when working with temperature.  It confuses everybody.
                JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
                double high = temperatureObject.getDouble(OWM_MAX);
                double low = temperatureObject.getDouble(OWM_MIN);

                highAndLow = formatHighLows(high, low);
                resultStrs[i] = day + " - " + description + " - " + highAndLow;
            }

            for (String s : resultStrs) {
                Log.v("f", "Forecast entry: " + s);
            }
            return resultStrs;

        }

    }
}
