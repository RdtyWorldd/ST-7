package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 */
public class App {
    private static final String DRIVER = "/Users/rdtyworldd/Downloads/chromedriver-mac-arm64/chromedriver";
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", DRIVER);
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            WebElement lengthField = webDriver.findElement(By.id("resultid"));

            Thread.sleep(1000);

            WebElement resContainer = lengthField.findElement(By.className("verybigtext"));
            String result = resContainer.findElement(By.tagName("b")).getText();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
