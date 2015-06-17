package example.normeow.sunshine;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 16.06.2015.
 */
public class ListForecastAdapter extends BaseAdapter {

    protected List<DayWeather> listWeather;
    Context context;
    String unitsType;

    public ListForecastAdapter(Context context, List<DayWeather> listWeather, String unitsType){
        this.context = context;
        this.listWeather = listWeather;
        this.unitsType = unitsType;
    }

    public void clear(){
        if (listWeather != null){
            if (!listWeather.isEmpty()){
                listWeather.clear();
                notifyDataSetChanged();
            }
        }
    }

    public void add(DayWeather arg){
        this.listWeather.add(arg);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listWeather.size();
    }

    @Override
    public DayWeather getItem(int position) {
        return listWeather.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_forecast, parent, false);
            holder.day = (TextView)convertView.findViewById(R.id.list_item_forecast_textview_day);
            holder.high_temp = (TextView)convertView.findViewById(R.id.list_item_forecast_textview_high);
            holder.low_temp = (TextView)convertView.findViewById(R.id.list_item_forecast_textview_low);
            holder.weather = (TextView)convertView.findViewById(R.id.list_item_forecast_textview_weather);
            holder.weatherPic = (ImageView)convertView.findViewById(R.id.list_item_forecast_pic);

            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
        DayWeather dayWeather = listWeather.get(position);
        holder.day.setText(dayWeather.getDay());
        holder.weather.setText(dayWeather.getWeather());

        int imgId = R.drawable.ic_clear;

        //todo set image

        holder.weatherPic.setImageResource(imgId);

        //todo dont forget about units and changing it. Think how it works now when yu change the units
        holder.high_temp.setText(Double.toString(dayWeather.getHighTemperatureMetric()));
        holder.low_temp.setText(Double.toString(dayWeather.getLowTemperatureMetric()));


        return convertView;
    }
    private class ViewHolder {
        TextView day;
        TextView weather;
        TextView high_temp;
        TextView low_temp;
        ImageView weatherPic;
    }
}
