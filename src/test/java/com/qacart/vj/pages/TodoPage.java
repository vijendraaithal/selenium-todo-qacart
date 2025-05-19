package com.qacart.vj.pages;

import com.qacart.vj.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TodoPage {
    private static TodoPage todoPage;
    private final By icnPlus = By.cssSelector("[data-testid='add']");
    private final By icnDelete = By.xpath("(//button[@aria-label='delete'])[2]");
    private final By addedTodoText = By.cssSelector("[data-testid='todo-text']");
    private final By noTodosListedHeader = By.cssSelector("[data-testid='no-todos']");
    private final By msgWelcome = By.cssSelector("[data-testid='welcome']");

    private TodoPage() {}

    public static TodoPage getInstance() {
        if (todoPage == null) {
            todoPage = new TodoPage();
        }
        return todoPage;
    }

    @Step("Visiting the Todo page")
    public void load(WebDriver driver) {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + "/todo");
    }

    @Step("Clicking on Plus Icon")
    public void clickPlusIcon(WebDriver driver) {
        driver.findElement(icnPlus).click();
    }

    @Step("Clicking on Delete icon associated witha todo")
    public void clickDeleteIcon(WebDriver driver) {
        driver.findElement(icnDelete).click();
    }

    @Step("Get the text of added Todo")
    public String getAddedTodoText(WebDriver driver) {
        return driver.findElement(addedTodoText).getText();
    }

    @Step("Check if No Todos message is displayed")
    public boolean isNoTodosTextDisplayed(WebDriver driver) {
        return driver.findElement(noTodosListedHeader).isDisplayed();
    }

    @Step("Check if Welcome message is displayed")
    public boolean isWelcomeMessageDisplayed(WebDriver driver) {
        return driver.findElement(msgWelcome).isDisplayed();
    }
}