package com.codeinvestigator.basicauthentication.weatherforecast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherforecastController {

    @GetMapping("/uxdesigner/weathertoday")
    public String weathertodayux(){
        return "Sunny with some cute clouds";
    }

    @GetMapping("/developer/weathertoday")
    public String weathertodaydeveloper(){
        return "Dark gray with some raint... Very windy.";
    }

    @GetMapping("/nosecurity/weatheryesterday")
    public String weatherNoSecurity(){
        return "Snowy and sunny";
    }

}
