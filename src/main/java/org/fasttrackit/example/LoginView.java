package org.fasttrackit.example;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.form.TextField;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginView {
    //setElPath pentru a da XPATH aici nu se mai genereaza in spate XPATH si ia exact ce ai pus
    //pentru cele de mai jos se genereaza in spate un XPATH

    //private WebLocator emailField = new WebLocator().setId("email");
    private TextField emailField = new TextField().setLabel("Email:");

    //private WebLocator passField = new WebLocator().setName("password");
    private TextField passField = new TextField().setName("password");

    //private WebLocator loginBtn =  new WebLocator().setClasses("login-btn");
    //private WebLocator loginBtn =  new WebLocator().setText("Login"); nu e bun pentru ca sunt 2 login pe pagina si il va alege pe primul care nu e cel dorit
    //corect ar fi sa setezi si tagul (setTag("..") sau/si id (setId(".."): private WebLocator loginBtn =  new WebLocator().setText("Login").setTag("button");
    private Button loginBtn = new Button().setText("Login");

    private WebLocator errorMsg =  new WebLocator("error-msg");

    public static void main(String[] args) {
        LoginView view = new LoginView();
        System.out.println(view.errorMsg.getSelector());
    }

    public void doLogin(String userName, String password) {
        emailField.sendKeys(userName);
        //Utils.sleep(2000);
        passField.sendKeys(password);
        loginBtn.click();
    }

    public void assertThatErrorIs(String message) {
        System.out.println(errorMsg.getHtmlText());
        assertThat(errorMsg.getHtmlText(), is(message));
    }
}
