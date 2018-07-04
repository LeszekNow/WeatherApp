package com.leszeknowinski.WeatherApp.models.weatherData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrognosisContainer {

    @JsonProperty("list")
    private List<DayWeatherModel>prognosis = new ArrayList<>();

}
