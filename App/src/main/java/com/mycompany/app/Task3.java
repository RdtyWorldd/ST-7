package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task3
{
    private static final String DRIVER = "/Users/rdtyworldd/Downloads/chromedriver-mac-arm64/chromedriver";
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", DRIVER);
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms");
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            parse(elem);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }

    static String formatJsonDate(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy / HH:mm");
        return dateTime.format(formatter);
    }

    static String getTemperatureMesures(JSONObject obj) {
        JSONObject units = (JSONObject) obj.get("hourly_units");
        return  (String) units.get("temperature_2m");
    }

    static void parse(WebElement elem) throws ParseException {
        String json_str = elem.getText();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(json_str);

        String temp_mesure = getTemperatureMesures(obj);
        JSONObject hourly = (JSONObject)obj.get("hourly");
        JSONArray hours = (JSONArray) hourly.get("time");
        JSONArray temperature = (JSONArray) hourly.get("temperature_2m");
        JSONArray rain = (JSONArray) hourly.get("rain");

        int arrays_size = hours.size();

        System.out.println("N |    Date / time     |  Temp   | Rain, mm |");
        for(int i = 0; i < arrays_size; i++) {
            String date_hours = (String) hours.get(i);
            String temp_per_h = ((Double) temperature.get(i)).toString();
            String rain_per_h = ((Double) rain.get(i)).toString();

            System.out.println((i+1) + " | " + formatJsonDate(date_hours) + " | " + temp_per_h +
                    " " + temp_mesure + " | " + rain_per_h + "      |");
        }
        System.out.println();
    }
}