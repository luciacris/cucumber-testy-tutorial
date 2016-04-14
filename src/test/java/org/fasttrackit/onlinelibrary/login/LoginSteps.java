package org.fasttrackit.onlinelibrary.login;

import com.sdl.selenium.web.WebLocator;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fasttrackit.onlinelibrary.view.LoginView;
import org.fasttrackit.onlinelibrary.view.TopMenuNavigationView;
import org.fasttrackit.util.TestBase;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;

public class LoginSteps extends TestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);
    
    private TopMenuNavigationView topMenuNavigation = new TopMenuNavigationView();
    private LoginView loginView = new LoginView();

    @When("^I click on Login button from top navigation menu$")
    public void I_click_on_Login_button_from_top_navigation_menu() {
        topMenuNavigation.loginButton.assertClick();
    }
    
    @When("^I login using \"([^\"]*)\"/\"([^\"]*)\"$")
    public void I_login_using(String user, String password) {
        loginView.login(user, password);
    }

    @Then("^I click on fake Password field$")
    public void fakePasswordClick() {
        loginView.fakePasswordField.assertClick();
    }
    
    @Then("^login should fail$")
    public void loginShouldFail() {
        WebLocator error = new WebLocator().setTag("strong").setText("Error:");
        boolean ready = error.ready();
        Assert.assertTrue("Element is not found : " + error, ready);
    }

    int a;
    int b;
    int x;
    @Given("^I add first number$")
    public void iAddFirstNumber() throws Throwable {
        a = 3;
    }

    @And("^I add second number$")
    public void iAddSecondNumber() throws Throwable {
        b = 4;
    }

    @When("^I press add$")
    public void iPressAdd() throws Throwable {
        x = a + b;
    }

    @Then("^I should see the correct result$")
    public void iShouldSeeTheCorrectResult() throws Throwable {
        Assert.assertThat("numerele nu sunt egale", x, is(6));
    }

    @Given("^I enter (\\d+) as first number$")
    public void iEnterAsFirstNumber(int n) throws Throwable {
        a = n;
    }

    @And("^I enter (\\d+) as second number$")
    public void iEnterAsSecondNumber(int m) throws Throwable {
        b = m;
    }

    @Then("^Result should be (\\d+)$")
    public void resultShouldBe(int l) throws Throwable {
        Assert.assertThat("numerele nu sunt egale", x, is(l));
    }

    private org.fasttrackit.example.LoginView loginPage = new org.fasttrackit.example.LoginView();
    @When("^I login with \"([^\"]*)\"/\"([^\"]*)\"$")
    public void iLoginWith(String email, String pass) throws Throwable {
        loginPage.doLogin(email,pass);
    }
}
