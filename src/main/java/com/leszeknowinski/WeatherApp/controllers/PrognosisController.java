package com.leszeknowinski.WeatherApp.controllers;

import com.leszeknowinski.WeatherApp.models.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Controller
public class PrognosisController {


    private WeatherService weatherService;

    @Autowired
    public PrognosisController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/prognosis")
    public String prognosis() {
        return "prognosisInfo";
    }

    @PostMapping("/prognosis")
    public String prognosis(@RequestParam("city") String city, Model model) {
        for(int i = 0; i < weatherService.callPrognosis(city).size(); i++) {
            model.addAttribute("dt" + (i + 1) + "", convertDateTimeFromSec(weatherService.callPrognosis(city).get(i).getDateTime()));
            model.addAttribute("temp" + (i + 1) + "", roundTempPressWind(convertTempIntoC(weatherService.callPrognosis(city).get(i).getGlobalStats().getTemperature())));
            model.addAttribute("cloud" + (i + 1) + "", weatherService.callPrognosis(city).get(i).getCloudStats().getCloudyPercent());
            model.addAttribute("press" + (i + 1) + "",roundTempPressWind( weatherService.callPrognosis(city).get(i).getGlobalStats().getPressure()));
            model.addAttribute("winds" + (i + 1) + "", roundTempPressWind(convertSpeedIntoKmh(weatherService.callPrognosis(city).get(i).getWindStats().getWindSpeed())));
            model.addAttribute("humid" + (i + 1) +"", weatherService.callPrognosis(city).get(i).getGlobalStats().getHumidity());

        }
        return "prognosisInfo";
    }

    public String convertDateTimeFromSec(long sec) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        long timeMS = sec*1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMS);
        return formatter.format(calendar.getTime());
    }

    public float convertTempIntoC(float tempInK) {
        return tempInK - (float) 273.15;
    }

    public float convertSpeedIntoKmh(float speedInMs) {
        return speedInMs * (float) 3.6;
    }

    public double roundTempPressWind(float value){
        double scale = Math.pow(10,1);
        return (double)Math.round(value*scale)/scale;

    }
}
