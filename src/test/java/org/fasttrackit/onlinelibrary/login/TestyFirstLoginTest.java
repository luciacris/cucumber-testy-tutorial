package org.fasttrackit.onlinelibrary.login;

import com.sdl.selenium.web.link.WebLink;
import org.fasttrackit.example.LoginView;
import org.fasttrackit.util.TestBase;
import org.testng.annotations.Test;

public class TestyFirstLoginTest extends TestBase {

    private LoginView loginPage = new LoginView();

    @Test
    public void validLoginTest() {
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");

        WebLink logoutBtn= new WebLink().setText("Logout");
        logoutBtn.assertClick();

        /*try {
            WebElement logoutBtn = driver.findElement(By.linkText("Logout"));
            logoutBtn.click();
        } catch (NoSuchElementException exception) {
            Assert.fail("could not login. Logout button not found");
        }*/
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

    private void openLoginPage() {
        System.out.println("open login page");
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
    }

}
