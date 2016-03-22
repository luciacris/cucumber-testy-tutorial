package org.fasttrackit.example;

import com.sdl.selenium.web.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavigationBarPage {
    @FindBy(how = How.XPATH, using = "//nav//button")
    private WebElement preferencesButton;

    @FindBy(how = How.XPATH, using = "//nav//a[text()='Logout']")
    private WebElement LogoutButton;

    public void openPreferencesWindows() {
        preferencesButton.click();
        //because of window animation (slide down)
        Utils.sleep(300);
    }
}
