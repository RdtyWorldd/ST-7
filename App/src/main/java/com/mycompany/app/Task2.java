package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2
{
    private static final String DRIVER = "/Users/rdtyworldd/Downloads/chromedriver-mac-arm64/chromedriver";
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", DRIVER);
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            Parse(elem);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }

    static void Parse(WebElement elem) throws ParseException {
        String json_str = elem.getText();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(json_str);
        System.out.println((String) obj.get("ip"));
    }
}