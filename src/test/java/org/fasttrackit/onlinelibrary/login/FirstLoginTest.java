package org.fasttrackit.onlinelibrary.login;

import com.sdl.selenium.web.utils.Utils;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


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

    @Test //(dependsOnMethods = "validLoginTest", alwaysRun = true)
    public void whenEnterInvalidPasswordIgetErrorMessage() {
        openLoginPage();
        doLogin("eu@fast.com","wrongPassword");

        assertThatErrorIs("Invalid user or password!");
    }

    @Test
    public void whenEnterNoPasswordIgetErrorMessage() {
        openLoginPage();
        doLogin("eu@fast.com","");

        assertThatErrorIs("Please enter your password!");
    }

    @Test
    public void whenEnterNoEmailIgetErrorMessage() {
        openLoginPage();
        doLogin("","test");

        assertThatErrorIs("Please enter your email!");
    }

    @Test
    public void whenEnterNoEmailAndNoPasswordIgetErrorMessage() {
        openLoginPage();
        doLogin("","");

        assertThatErrorIs("Please enter your email!");
    }

    @Test
    public void successChangePassword(){
        openLoginPage();
        doLogin("eu@fast.com","eu.pass");

        WebElement preferencesButton = driver.findElement(By.xpath("//nav//button"));
        preferencesButton.click();

        Utils.sleep(300);

        WebElement currentPasswordField = driver.findElement(By.xpath("//div[@id='preferences-win']//input[@name='password']"));
        currentPasswordField.sendKeys("eu.pass");

        WebElement newPasswordField = driver.findElement(By.xpath("//div[@id='preferences-win']//input[@name='newPassword']"));
        newPasswordField.sendKeys("eu.pass2");

        WebElement repeatPasswordField = driver.findElement(By.xpath("//div[@id='preferences-win']//input[@name='newPasswordRepeat']"));
        repeatPasswordField.sendKeys("eu.pass2");

        //WebElement saveButton = driver.findElement(By.xpath("//div[@id='preferences-win']//button[text()='Save']"));
        WebElement saveButton = driver.findElement(By.cssSelector("#preferences-win button.btn-warning"));
        saveButton.click();

        WebElement worningMess = driver.findElement(By.cssSelector("#preferences-win .status-msg"));
        assertThat(worningMess.getText(), is("Your password has been successfully changed."));

    }

    private void doLogin(String userName, String password) {
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(userName);
        //Utils.sleep(2000);

        WebElement passField = driver.findElement(By.name("password"));
        passField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.className("login-btn"));
        loginBtn.click();
    }

    private void openLoginPage() {
        System.out.println("open login page");
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
    }

    private void assertThatErrorIs(String message) {
        WebElement messageWrongCredentials = driver.findElement(By.className("error-msg"));
        assertThat(messageWrongCredentials.getText(), is(message));
    }

}
