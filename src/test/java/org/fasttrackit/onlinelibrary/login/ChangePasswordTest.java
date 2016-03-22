package org.fasttrackit.onlinelibrary.login;

import org.fasttrackit.example.ChangePasswordPage;
import org.fasttrackit.example.LoginPage;
import org.fasttrackit.example.NavigationBarPage;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChangePasswordTest extends TestBase {

    private LoginPage loginPage;
    private ChangePasswordPage changePasswordPage;
    private NavigationBarPage navigationBarPage;

    //constructor
    public ChangePasswordTest() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        changePasswordPage = PageFactory.initElements(driver, ChangePasswordPage.class);
        navigationBarPage = PageFactory.initElements(driver, NavigationBarPage.class);
    }

    @Test
    public void successChangePassword(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu.pass", "eu.pass2", "eu.pass2");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your password has been successfully changed."));
    }

    @Test
    public void changePasswordWithWrongCurrentPassword(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("wrongPass", "eu.pass2", "eu.pass2");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your preview password is incorrect!"));
    }

    @Test
    public void changePasswordWithNoCurrentPassword(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("", "eu.pass2", "eu.pass2");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your preview password is incorrect!"));
    }

    @Test
    public void changePasswordWithNewPassDifferentThanRepeatPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu@fast.com", "eu.pass2", "eu.pass22");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your preview password is incorrect!"));
    }

    @Test
    public void changePasswordWithNoNewPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu@fast.com", "", "eu.pass22");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your preview password is incorrect!"));
    }

    @Test
    public void changePasswordWithNoRepeatPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu@fast.com", "eu.pass2", "");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your preview password is incorrect!"));
    }

    @Test
    public void changePasswordWithNoNewPassNoRepeatPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu@fast.com", "", "");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your preview password is incorrect!"));
    }

    @Test
    public void changePasswordWithNoCurrentPassNoNewPassNoRepeatPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("", "", "");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your preview password is incorrect!"));
    }

    private void openLoginPage() {
        System.out.println("open login page");
        driver.get("file:///C:/app-demo/login.html");
        // driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
    }

}
