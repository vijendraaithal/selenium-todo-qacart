package com.qacart.vj.base;

import com.qacart.vj.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        WebDriver driver = new DriverFactory().initDriver();
        this.driver.set(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String testCaseName = result.getMethod().getMethodName();
        File destFile = new File("target" + File.separator + "screenshots" + File.separator + testCaseName + ".png");
        takeScreenShot(destFile);
        driver.get().quit();
    }

    public void takeScreenShot(File destFile) {
        File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtil.copyFile(file, destFile);
            InputStream inputStream = new FileInputStream(destFile);
            Allure.addAttachment("screenshot", inputStream);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}