package com.leszeknowinski.WeatherApp.models.weatherData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TempHumPressStats {
    @JsonProperty("temp")
    private float temperature;
    @JsonProperty("pressure")
    private float pressure;
    @JsonProperty("humidity")
    private int humidity;
}
