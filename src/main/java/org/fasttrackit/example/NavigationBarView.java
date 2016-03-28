package org.fasttrackit.example;

import com.sdl.selenium.web.button.Button;
import com.sdl.selenium.web.link.WebLink;
import com.sdl.selenium.web.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavigationBarView {
    private Button preferencesButton = new Button().setText("Preferences");

    private WebLink LogoutButton = new WebLink().setText("Logout");

    public void openPreferencesWindows() {
        preferencesButton.click();
        //because of window animation (slide down)
        Utils.sleep(300);
    }
}
