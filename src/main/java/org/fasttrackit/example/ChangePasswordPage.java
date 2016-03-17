package org.fasttrackit.example;

import com.sdl.selenium.web.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by lucia on 3/17/2016.
 */
public class ChangePasswordPage {
    @FindBy(how = How.XPATH, using = "//nav//button")
    private WebElement preferencesButton;

    @FindBy(how = How.XPATH, using = "//div[@id='preferences-win']//input[@name='password']")
    private WebElement currentPasswordField;

    @FindBy(how = How.XPATH, using = "//div[@id='preferences-win']//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(how = How.XPATH, using = "//div[@id='preferences-win']//input[@name='newPasswordRepeat']")
    private WebElement repeatPasswordField;

    @FindBy(how = How.CSS, using = "#preferences-win button.btn-warning")
    private WebElement saveButton;
    //WebElement saveButton = driver.findElement(By.xpath("//div[@id='preferences-win']//button[text()='Save']"));

    @FindBy(how = How.CSS, using = "#preferences-win .status-msg")
    private WebElement worningMess;

    public void changePassword(String currentPass, String newpass) {
        preferencesButton.click();
        Utils.sleep(300);
        currentPasswordField.sendKeys(currentPass);
        newPasswordField.sendKeys(newpass);
        repeatPasswordField.sendKeys(newpass);
        saveButton.click();
        assertThat(worningMess.getText(), is("Your password has been successfully changed."));
    }
}
