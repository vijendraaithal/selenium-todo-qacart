package com.qacart.vj.testcases;

import com.qacart.vj.apis.UserApi;
import com.qacart.vj.base.BaseTest;
import com.qacart.vj.model.User;
import com.qacart.vj.pages.RegisterPage;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

public class DummySample extends BaseTest {

    @Test
    public void dummy() throws InterruptedException {
        User user = new User();
        Response response = UserApi.getInstance().register(user);

        String access_token = response.path("access_token");
        String userID = response.path("userID");
        String firstName = response.path("firstName");

        Cookie accessTokenCookie = new Cookie("access_token", access_token);
        Cookie userIdCookie = new Cookie("userID", userID);
        Cookie firstNameCookie = new Cookie("firstName", firstName);


        RegisterPage.getInstance().load(driver.get());
        driver.get().manage().addCookie(accessTokenCookie);
        driver.get().manage().addCookie(userIdCookie);
        driver.get().manage().addCookie(firstNameCookie);
        RegisterPage.getInstance().load(driver.get());

        Thread.sleep(12000);
    }
}
