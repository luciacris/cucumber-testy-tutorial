package org.fasttrackit.onlinelibrary.login;

import org.fasttrackit.example.LoginPage;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class FirstLoginTest extends TestBase {

    private LoginPage loginPage;

    //constructor
    public FirstLoginTest() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }


    @Test
    public void validLoginTest() {
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.passx");
        //doLogin("eu@fast.com","eu.pass");

        try {
            WebElement logoutBtn = driver.findElement(By.linkText("Logout"));
            logoutBtn.click();
        } catch (NoSuchElementException exception) {
            Assert.fail("could not login. Logout button not found");
        }
    }

    @DataProvider
    public static Object[][] invalidLogin(){
        return new Object[][] {
                {"eu@fast.com", "wrongPassword", "Invalid user or password!"},
                {"eu@fast.com", "", "Please enter your password!"},
                {"", "test", "Please enter your email!"}
        };
    }

    @Test(dataProvider = "invalidLogin")
    public void invalidLoginTest(String email, String pass, String errorMsg){
        System.out.println("run this invalid test" + email + " " + pass + " " + errorMsg);
        openLoginPage();
        loginPage.doLogin(email,pass);

        loginPage.assertThatErrorIs(errorMsg);
    }

    //next 3 tests are done by the above one.
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

    private void openLoginPage() {
        System.out.println("open login page");
        //driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
        driver.get("file:///C:/Testy/src/test/functional/app-demo/login.html");
    }

}
