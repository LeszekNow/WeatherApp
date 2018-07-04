package com.leszeknowinski.WeatherApp.models.weatherData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindStats {
    @JsonProperty("speed")
    private float windSpeed;
}
