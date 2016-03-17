package org.fasttrackit.onlinelibrary.login;

import com.sdl.selenium.web.utils.Utils;
import org.fasttrackit.example.ChangePasswordPage;
import org.fasttrackit.example.LoginPage;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class FirstLoginTest extends TestBase {

    private LoginPage loginPage;
    private ChangePasswordPage changePasswordPage;

    //constructor
    public FirstLoginTest() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        changePasswordPage = PageFactory.initElements(driver, ChangePasswordPage.class);
    }

    @Test
    public void validLoginTest() {
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        //doLogin("eu@fast.com","eu.pass");

        try {
            WebElement logoutBtn = driver.findElement(By.linkText("Logout"));
            logoutBtn.click();
        } catch (NoSuchElementException exception) {
            Assert.fail("could not login. Logout button not found");
        }
    }

    @Test //(dependsOnMethods = "validLoginTest", alwaysRun = true)
    public void whenEnterInvalidPasswordIgetErrorMessage() {
        openLoginPage();
        loginPage.doLogin("eu@fast.com","wrongPassword");

        loginPage.assertThatErrorIs("Invalid user or password!");
    }

    @Test
    public void whenEnterNoPasswordIgetErrorMessage() {
        openLoginPage();
        loginPage.doLogin("eu@fast.com","");

        loginPage.assertThatErrorIs("Please enter your password!");
    }

    @Test
    public void whenEnterNoEmailIgetErrorMessage() {
        openLoginPage();
        loginPage.doLogin("","test");

        loginPage.assertThatErrorIs("Please enter your email!");
    }

    @Test
    public void whenEnterNoEmailAndNoPasswordIgetErrorMessage() {
        openLoginPage();
        loginPage.doLogin("","");

        loginPage.assertThatErrorIs("Please enter your email!");
    }

    @Test
    public void successChangePassword(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        changePasswordPage.changePassword("eu.pass", "eu.pass2");
    }


    private void openLoginPage() {
        System.out.println("open login page");
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
    }

}
