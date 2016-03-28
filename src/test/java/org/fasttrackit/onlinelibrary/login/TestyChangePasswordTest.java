package org.fasttrackit.onlinelibrary.login;

import org.fasttrackit.example.*;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestyChangePasswordTest extends TestBase {

    private LoginView loginPage = new LoginView();
    private ChangePasswordView changePasswordPage = new ChangePasswordView();
    private NavigationBarView navigationBarPage = new NavigationBarView();

    @Test
    public void successChangePassword(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu.pass", "eu.pass2", "eu.pass2");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Your password has been successfully changed."));
        changePasswordPage.close();
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
        changePasswordPage.changePassword("eu.pass", "eu.pass2", "eu.pass22");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Password does not match the confirm password!"));
    }

    @Test
    public void changePasswordWithNoNewPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu.pass", "", "eu.pass22");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Password does not match the confirm password!"));
    }

    @Test
    public void changePasswordWithNoRepeatPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu.pass", "eu.pass2", "");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Password does not match the confirm password!"));
    }

    @Test
    public void changePasswordWithNoNewPassNoRepeatPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("eu@fast.com", "", "");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("New password and repeat password should be provided!"));
    }

    @Test
    public void changePasswordWithNoCurrentPassNoNewPassNoRepeatPass(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");
        navigationBarPage.openPreferencesWindows();
        changePasswordPage.changePassword("", "", "");

        String statusMessage = changePasswordPage.getStatusMessage();
        System.out.println(statusMessage);
        assertThat(statusMessage, is("Current passwor, new password and repeat password should be provided!"));
    }

    private void openLoginPage() {
        System.out.println("open login page");
        driver.get("file:///C:/app-demo/login.html");
        //driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
    }

}
