package com.leszeknowinski.WeatherApp.models.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leszeknowinski.WeatherApp.models.weatherData.DayWeatherModel;
import com.leszeknowinski.WeatherApp.models.weatherData.PrognosisContainer;
import com.leszeknowinski.WeatherApp.models.weatherData.WeatherModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class WeatherService {

    @Value("${openweathermap.api_key}")
    private String apiKey;
    private RestTemplate restTemplate;
    private String currWeathUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String progUrl = "http://api.openweathermap.org/data/2.5/forecast?q=";

    public WeatherService() {
        restTemplate = new RestTemplate();
    }
    //"units=metric" +

    public WeatherModel makeCall(String city) {
        return restTemplate.getForObject(currWeathUrl + city + "&appid=" + apiKey, WeatherModel.class);
    }

    public List<DayWeatherModel> callPrognosis(String city) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = restTemplate.getForObject(progUrl + city + "&appid=" + apiKey, String.class);
        PrognosisContainer prognosisContainer = null;
        try {
            prognosisContainer = mapper.readValue(jsonObject, PrognosisContainer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prognosisContainer.getPrognosis();
    }
}
