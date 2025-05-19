package com.qacart.vj.testcases;

import com.qacart.vj.base.BaseTest;
import com.qacart.vj.model.User;
import com.qacart.vj.pages.NewTodoPage;
import com.qacart.vj.pages.RegisterPage;
import com.qacart.vj.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TodoTest extends BaseTest {

    @Test(description = "Should be able to add a todo")
    public void shouldBeAbleToAddTodo() {
        User user = new User();
        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUsingApi(driver.get(), user);
        TodoPage.getInstance().clickPlusIcon(driver.get());
        NewTodoPage.getInstance().createNewTodo(driver.get(), "Selenium");
        String addedTodo = TodoPage.getInstance().getAddedTodoText(driver.get());
        Assert.assertEquals(addedTodo, "Selenium");
    }

    @Test(description = "Should be able to delete the todo that is added")
    public void shouldBeAbleToDeleteTodo() {
        User user = new User();
        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUsingApi(driver.get(), user);
        NewTodoPage.getInstance().addTodoUsingApi(user, "Learn Selenium");
        TodoPage.getInstance().load(driver.get());
        TodoPage.getInstance().clickDeleteIcon(driver.get());
        boolean isNoTodosTextDisplayed = TodoPage.getInstance().isNoTodosTextDisplayed(driver.get());
        Assert.assertTrue(isNoTodosTextDisplayed);
    }
}