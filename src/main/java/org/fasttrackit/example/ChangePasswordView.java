package org.fasttrackit.example;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.form.TextField;

public class ChangePasswordView {
    private TextField currentPasswordField = new TextField().setLabel("Current Password");

    private TextField newPasswordField = new TextField().setLabel("New Password");

    private TextField repeatPasswordField = new TextField().setLabel("Repeat Password");

    //private Button saveButton = new Button().setElCssSelector("#preferences-win button.btn-warning");
    private WebLocator title = new WebLocator().setText("Change Password");
    private WebLocator preferenceWin = new WebLocator().setClasses("modal", "in").setChildNodes(title); //"in" ca sa dea doar fereastra cand e activa, nu si cand e ascunsa
    private Button saveButton = new Button(preferenceWin).setText("Save");

    private WebLocator worningMess =  new WebLocator(preferenceWin).setClasses("status-msg");

    private Button closeButton = new Button(preferenceWin).setText("Close");

    public void changePassword(String currentPass, String newPass, String repeatPass) {
        currentPasswordField.sendKeys(currentPass);
        newPasswordField.sendKeys(newPass);
        repeatPasswordField.sendKeys(repeatPass);
        saveButton.assertClick();
    }

    public String getStatusMessage() {
        return worningMess.getHtmlText();
    }

    public void close(){
        closeButton.assertClick();
    }
}
