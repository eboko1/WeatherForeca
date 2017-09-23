package fvi.at.ua.weatherforeca.ui.model.pojo;

import java.sql.Time;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import fvi.at.ua.weatherforeca.ui.utils.Constants;

/**
 * Created by Vika on 23.09.2017.
 */

public class Weather {

    public final String dayOfWeek;
    public final String minTemp;
    public final String maxTemp;
    public final String humidity;
    public final String description;
    public final String iconURL;


    public Weather(long timeStamp, double minTemp, double maxTemp, double humidity, String description, String iconName) {
        this.dayOfWeek = convertTimeStampToDay(timeStamp);
        this.minTemp = NumberFormat.getInstance().format(minTemp);
        this.maxTemp = NumberFormat.getInstance().format(maxTemp);
        this.humidity = NumberFormat.getPercentInstance().format(humidity/100.0);
        this.description = description;
        this.iconURL = Constants.HTTP.BASE_URL + iconName + ".png";
    }

    private String convertTimeStampToDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp*1000);

        TimeZone timeZone = TimeZone.getDefault();
        calendar.add(Calendar.MILLISECOND, timeZone.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE");

        return dateFormatter.format(calendar.getTime());
    }
}
