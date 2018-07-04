package com.leszeknowinski.WeatherApp.models.weatherData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayWeatherModel {

    @JsonProperty("dt")
    private long dateTime;
    @JsonProperty("main")
    private TempHumPressStats globalStats;
    @JsonProperty("wind")
    private WindStats windStats;
    @JsonProperty("clouds")
    private CloudStats cloudStats;
}
