package com.example.dmitry.deemahweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.visuality.f32.temperature.TemperatureUnit;
import com.visuality.f32.weather.data.entity.Forecast;
import com.visuality.f32.weather.data.entity.Weather;
import com.visuality.f32.weather.manager.WeatherManager;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekActivity extends AppCompatActivity {
public String city="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_week);
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            city= extras.getString("city");
        }
//        BarChart chart = new BarChart(this);
//        setContentView(chart);
//        List<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(0f, 30f));
//        entries.add(new BarEntry(1f, 80f));
//        entries.add(new BarEntry(2f, 60f));
//        entries.add(new BarEntry(3f, 50f));
//        // gap of 2f
//        entries.add(new BarEntry(5f, 70f));
//        entries.add(new BarEntry(6f, 60f));
//
//        BarDataSet set = new BarDataSet(entries, city);
//        BarData data = new BarData(set);
//        data.setBarWidth(0.9f); // set custom bar width
//
//        chart.setData(data);
//        chart.setFitBars(true); // make the x-axis fit exactly all bars
//        chart.invalidate(); // refresh

        new WeatherManager("6dcb24ab5ba3521d26bb40a5968f7532").getFiveDayForecastByCityName(
                city,
                new WeatherManager.ForecastHandler() {
                    @Override
                    public void onReceivedForecast(WeatherManager manager, Forecast forecast) {
                        ValueLineChart mCubicValueLineChart = (ValueLineChart) findViewById(R.id.cubiclinechart);

                        ValueLineSeries series = new ValueLineSeries();
                        series.setColor(0xFF56B7F1);
                        int numberOfAvailableTimestamps = forecast.getNumberOfTimestamps();
                        for (int timestampIndex = 0; timestampIndex < numberOfAvailableTimestamps; timestampIndex+=3) {
                            long timestamp = forecast.getTimestampByIndex(timestampIndex);
                            Weather weather = forecast.getWeatherForTimestamp(timestamp);
                            Date date = new java.util.Date(weather.getWeatherTimestamp()*1000L);
                            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MMM");
                            sdf.setTimeZone(java.util.TimeZone.getDefault());
                            String formattedDate = sdf.format(date);
                                    series.addPoint(new ValueLinePoint(formattedDate, (int)weather.getTemperature().getCurrent()
                                    .getValue(TemperatureUnit.CELCIUS)));
//                            temp[timestampIndex]=(int)weather.getTemperature().getCurrent()
//                                    .getValue(TemperatureUnit.CELCIUS);
//                            day[timestampIndex]="123"+weather.getWeatherTimestamp();
                        }
                        mCubicValueLineChart.addSeries(series);
                        mCubicValueLineChart.startAnimation();
                    }

                    @Override
                    public void onFailedToReceiveForecast(WeatherManager manager) {
                        // Handle error...
                    }
                }
        );



        new WeatherManager("6dcb24ab5ba3521d26bb40a5968f7532").getFiveDayForecastByCityName(
                city,
                new WeatherManager.ForecastHandler() {
                    @Override
                    public void onReceivedForecast(WeatherManager manager, Forecast forecast) {
                        ValueLineChart mCubicValueLineChart = (ValueLineChart) findViewById(R.id.cubiclinechart2);

                        ValueLineSeries series = new ValueLineSeries();
                        series.setColor(0xFF56B7F1);
                        int numberOfAvailableTimestamps = forecast.getNumberOfTimestamps();
                        for (int timestampIndex = 0; timestampIndex < 5; timestampIndex++) {
                            long timestamp = forecast.getTimestampByIndex(timestampIndex);
                            Weather weather = forecast.getWeatherForTimestamp(timestamp);
                            Date date = new java.util.Date(weather.getWeatherTimestamp()*1000L);
                            SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
                            sdf.setTimeZone(java.util.TimeZone.getDefault());
                            String formattedDate = sdf.format(date);
                            series.addPoint(new ValueLinePoint(formattedDate, (int)weather.getTemperature().getCurrent()
                                    .getValue(TemperatureUnit.CELCIUS)));
//                            temp[timestampIndex]=(int)weather.getTemperature().getCurrent()
//                                    .getValue(TemperatureUnit.CELCIUS);
//                            day[timestampIndex]="123"+weather.getWeatherTimestamp();
                        }

                        mCubicValueLineChart.addSeries(series);
                        mCubicValueLineChart.startAnimation();
                    }

                    @Override
                    public void onFailedToReceiveForecast(WeatherManager manager) {
                        // Handle error...
                    }
                }
        );




    }

}
