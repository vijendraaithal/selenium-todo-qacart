package com.qacart.vj.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {

    public WebDriver initDriver() {
        WebDriver driver;
        String browser = System.getProperty( "browser", "CHROME");
        switch (browser.toUpperCase()) {
            case "CHROME" -> driver = new ChromeDriver();
            case "FIREFOX" -> driver = new FirefoxDriver();
            case "SAFARI" -> driver = new SafariDriver();
            case "EDGE" -> driver = new EdgeDriver();
            default -> throw new RuntimeException("Browser is not supported");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        return driver;
    }
}