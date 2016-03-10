package org.fasttrackit.onlinelibrary.login;

import com.sdl.selenium.web.utils.Utils;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FirstLoginTest extends TestBase {

    @Test
    public void validLoginTest() {
        openLoginPage();
        doLogin("eu@fast.com","eu.pass");

        try {
            WebElement logoutBtn = driver.findElement(By.linkText("Logout"));
            logoutBtn.click();
        } catch (NoSuchElementException exception) {
            Assert.fail("could not login. Logout button not found");
        }
    }

    @Test (dependsOnMethods = "validLoginTest", alwaysRun = true)
    public void whenEnterInvalidPasswordIgetErrorMessage() {
        openLoginPage();
        doLogin("eu@fast.com","wrongPassword");

        try {
            WebElement messageWrongCredentials = driver.findElement(By.className("error-msg"));
            System.out.println(messageWrongCredentials.getText());
        }
        catch (NoSuchElementException exception) {
            Assert.fail("could not login. Logout button not found");
        }

    }

    private void doLogin(String userName, String password) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(userName);
        //Utils.sleep(2000);

        WebElement passField = driver.findElement(By.id("password"));
        passField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.className("btn"));
        loginBtn.click();
    }

    private void openLoginPage() {
        System.out.println("open login page");
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
    }
}
