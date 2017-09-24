package fvi.at.ua.weatherforeca.ui.model.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fvi.at.ua.weatherforeca.R;
import fvi.at.ua.weatherforeca.ui.model.pojo.Weather;

/**
 * Created by Vika on 23.09.2017.
 */

public class WeatherArrayAdapter extends ArrayAdapter<Weather> {
    private Map<String, Bitmap> bitmaps = new HashMap<>();


    public WeatherArrayAdapter(@NonNull Context context, @NonNull List<Weather> forecast) {
        super(context, -1, forecast);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather day = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            viewHolder.conditionImageView = (ImageView)convertView.findViewById(R.id.conditionImageView);
            viewHolder.dayTextView = (TextView)convertView.findViewById(R.id.dayTextView);
            viewHolder.lowTextView = (TextView)convertView.findViewById(R.id.lowTextView);
            viewHolder.hiTextView = (TextView)convertView.findViewById(R.id.hiTextView);
            viewHolder.humidityTextView = (TextView)convertView.findViewById(R.id.humidityTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        if (bitmaps.containsKey(day.iconURL)){
            viewHolder.conditionImageView.setImageBitmap(bitmaps.get(day.iconURL));
        } else {
            new LoadImageTask(viewHolder.conditionImageView).execute();
        }

        // fill up
        Context context = getContext();
        viewHolder.dayTextView.setText(context.getString(R.string.day_description, day.dayOfWeek, day.description));
        viewHolder.lowTextView.setText(context.getString(R.string.low_temp, day.minTemp));
        viewHolder.hiTextView.setText(context.getString(R.string.high_temp, day.maxTemp));
        viewHolder.humidityTextView.setText(context.getString(R.string.humidity, day.humidity));

        return convertView;
    }

    private static class ViewHolder{
        ImageView conditionImageView;
        TextView dayTextView, lowTextView, hiTextView, humidityTextView;
    }
}
