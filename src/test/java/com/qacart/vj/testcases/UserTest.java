package com.qacart.vj.testcases;

import com.qacart.vj.base.BaseTest;
import com.qacart.vj.model.User;
import com.qacart.vj.pages.RegisterPage;
import com.qacart.vj.pages.TodoPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends BaseTest {
    @Test(description = "Should be able to signup")
    public  void shouldBeAbleToRegister() {
        User user = new User();
        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().register(driver.get(),user);
        boolean isWelcomeMsgShown = TodoPage.getInstance().isWelcomeMessageDisplayed(driver.get());
        Assert.assertTrue(isWelcomeMsgShown);
    }
}