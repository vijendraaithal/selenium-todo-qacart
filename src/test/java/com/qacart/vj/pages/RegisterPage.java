package com.qacart.vj.pages;

import com.qacart.vj.apis.UserApi;
import com.qacart.vj.model.User;
import com.qacart.vj.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private static RegisterPage registerPage;
    private final By inpFirstName = By.cssSelector("[data-testid='first-name']");
    private final By inpLastName = By.cssSelector("[data-testid='last-name']");
    private final By inpEmail = By.cssSelector("[data-testid='email']");
    private final By inpPassword = By.cssSelector("[data-testid='password']");
    private final By inpConfirmPassword = By.cssSelector("[data-testid='confirm-password']");
    private final By btnSubmit = By.cssSelector("[data-testid='submit']");

    private RegisterPage() {}

    public static RegisterPage getInstance() {
        if (registerPage == null) {
            registerPage = new RegisterPage();
        }
        return  registerPage;
    }

    @Step("Visit the sign up page")
    public void load(WebDriver driver) {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + "/signup");
    }

    @Step("Register using the UI")
    public void register(WebDriver driver, User user) {
        driver.findElement(inpFirstName).sendKeys(user.getFirstName());
        driver.findElement(inpLastName).sendKeys(user.getLastName());
        driver.findElement(inpEmail).sendKeys(user.getEmail());
        driver.findElement(inpPassword).sendKeys(user.getPassword());
        driver.findElement(inpConfirmPassword).sendKeys(user.getPassword());
        driver.findElement(btnSubmit).click();
    }

    @Step("Register using the API")
    public void registerUsingApi(WebDriver driver, User user) {
        Response response = UserApi.getInstance().register(user);

        String access_token = response.path("access_token");
        String userID = response.path("userID");
        String firstName = response.path("firstName");
        user.setAccessToken(access_token);

        Cookie accessTokenCookie = new Cookie("access_token", access_token);
        Cookie userIdCookie = new Cookie("userID", userID);
        Cookie firstNameCookie = new Cookie("firstName", firstName);

        driver.manage().addCookie(accessTokenCookie);
        driver.manage().addCookie(userIdCookie);
        driver.manage().addCookie(firstNameCookie);
        RegisterPage.getInstance().load(driver);
    }
}