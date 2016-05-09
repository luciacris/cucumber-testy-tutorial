package org.fasttrackit.example;

import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.WebLocator;

public class GridsView {

    public void open(){
       open("Grids", "Grid Data Binding");
    };

    public void open(String title, String example){
        WebDriverConfig.getDriver().get("http://examples.sencha.com/extjs/6.0.2/examples/");
        //ca sa pun direct: driver.get("http://examples.sencha.com/extjs/6.0.2/examples/"); trebuia sa extind clasa TestBase

        WebLocator gridTab = new WebLocator().setText(title);
        WebLocator gridDataBinding = new WebLocator().setText(example);

        //mouseOver because element is not vizible and must scroll to it
        gridTab.mouseOver();
        //gridTab.focus(); //nu scroleaza la element

        gridTab.click();
        gridDataBinding.click();
    }
}
