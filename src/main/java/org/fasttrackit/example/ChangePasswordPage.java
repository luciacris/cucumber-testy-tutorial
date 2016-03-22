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
    @FindBy(how = How.XPATH, using = "//div[@id='preferences-win']//input[@name='password']")
    private WebElement currentPasswordField;

    @FindBy(how = How.XPATH, using = "//div[@id='preferences-win']//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(how = How.XPATH, using = "//div[@id='preferences-win']//input[@name='newPasswordRepeat']")
    private WebElement repeatPasswordField;

    @FindBy(how = How.CSS, using = "#preferences-win button.btn-warning")
    private WebElement saveButton;
    //WebElement saveButton = driver.findElement(By.xpath("//div[@id='preferences-win']//button[text()='Save']"));

    @FindBy(how = How.CSS, using = "#preferences-win .modal-footer.btn")
    //@FindBy(how = How.XPATH, using = "//div[@id='preferences-win'] //button[@text()='Close']")
    private WebElement worningMess;

    @FindBy(how = How.CSS, using = "#preferences-win .status-msg")
    private WebElement closeButton;

    public void changePassword(String currentPass, String newPass, String repeatPass) {
        currentPasswordField.sendKeys(currentPass);
        newPasswordField.sendKeys(newPass);
        repeatPasswordField.sendKeys(repeatPass);
        saveButton.click();
    }

    public String getStatusMessage() {
        return worningMess.getText();
    }
}
