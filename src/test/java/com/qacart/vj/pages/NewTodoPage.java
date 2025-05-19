package com.qacart.vj.pages;

import com.qacart.vj.apis.TodoApi;
import com.qacart.vj.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewTodoPage {
    private static NewTodoPage newTodoPage;
    private final By inpTodo = By.cssSelector("[data-testid='new-todo']");
    private final By btnCreateTodo = By.cssSelector("[data-testid='submit-newTask']");

    private NewTodoPage() {}

    public static NewTodoPage getInstance() {
        if(newTodoPage == null) {
            newTodoPage = new NewTodoPage();
        }
        return newTodoPage;
    }

    @Step("Add todo using UI")
    public void createNewTodo(WebDriver driver, String todoText) {
        driver.findElement(inpTodo).sendKeys(todoText);
        driver.findElement(btnCreateTodo).click();
    }

    @Step("Add todo using API")
    public void addTodoUsingApi(User user, String item) {
        TodoApi.getInstance().addTodo(user, item);
    }
}