package org.fasttrackit.onlinelibrary.login;

import org.fasttrackit.example.*;
import org.fasttrackit.forms.FirstFormView;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestyFormTitleTest extends TestBase{
    private LoginView loginPage = new LoginView();
    private ChangePasswordView changePasswordPage = new ChangePasswordView();
    private NavigationBarView navigationBarPage = new NavigationBarView();

    @Test
    public void changeDateTest(){
        openLoginPage();
        loginPage.doLogin("eu@fast.com","eu.pass");

        FirstFormView form = new FirstFormView();
        //form.assertReady();
        //form.selectCalendar.assertClick(); //se poate face direct click pe el pentru ca s-a declarat public. Daca era privat trebuia facut o metoda care facea click.
        form.datePicker.select("25/02/2016", "dd/MM/yyyy", Locale.ENGLISH);

    }

    private void openLoginPage() {
        System.out.println("open login page");
        driver.get("file:///C:/app-demo/login.html");
        //driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
    }
}
