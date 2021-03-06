package org.fasttrackit.elements;

import com.sdl.selenium.bootstrap.button.UploadFile;
import com.sdl.selenium.bootstrap.form.Form;
import com.sdl.selenium.bootstrap.form.SelectPicker;
import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.Browser;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.form.TextField;
import com.sdl.selenium.web.utils.PropertiesReader;
import com.sdl.selenium.web.utils.Utils;
import org.fasttrackit.example.DropDownList;
import org.fasttrackit.example.GridsView;
import org.fasttrackit.example.MultiSelectDropDownList;
import org.fasttrackit.forms.FirstFormView;
import org.fasttrackit.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class TestyElementsTest extends TestBase{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestyElementsTest.class);

    private Form form = new Form("Form Title");
    private UploadFile uploadBtn = new UploadFile(form, "TPT Test:");

    @Test
    public void uploadTest(){
        openPage();
        System.out.println(PropertiesReader.RESOURCES_DIRECTORY_PATH);
        /*uploadBtn.upload("Select file", new String[]{
                PropertiesReader.RESOURCES_DIRECTORY_PATH +"\\drivers\\upload.exe",
                PropertiesReader.RESOURCES_DIRECTORY_PATH +"\\feature\\login\\login.feature"
        });*/
        uploadBtn.upload(PropertiesReader.RESOURCES_DIRECTORY_PATH +"\\feature\\login\\login.feature");

        //following is another upload but in most cases doesn't work
        /*WebLocator inputUpload = new WebLocator(uploadBtn).setTag("input");
        inputUpload.sendKeys(PropertiesReader.RESOURCES_DIRECTORY_PATH +"\\feature\\login\\login.feature");*/

    }

    private void openPage() {
        //driver.get("file:///C:/app-demo/login.html");
        //driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
        //driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/bootstrap/index.html");
        driver.get("file:///C:/Testy/src/test/functional/app-demo/bootstrap/index.html");
    }

    @Test
    public void gmail(){
        driver.get("https://gmail.com");
        TextField emailField = new TextField().setPlaceholder("Enter your email");
        emailField.setValue("invalid.email@example.com");

        //setValue : sterge inainte si apoi pune
        //sendKeys : pune de unde e cursor, fara sa stearga ce exista momentan in camp

        emailField.sendKeys(Keys.ENTER);
        //emailField.sendKeys(Keys.CONTROL, "a");
        //emailField.sendKeys(Keys.CONTROL, Keys.ARROW_LEFT, Keys.ARROW_LEFT);

        WebLocator errorElement = new WebLocator().setText("Sorry, Google doesn't recognize that email. ");
        //WebLocator error = new WebLocator().setId("errormsg_0_Email") nu afiseaza nimic pentru ca elementul de eroare e de la inceput in pagina dar cu mesaj gol
        //si atunci trebuie asteptat pana apare continut in mesaj => WebLocator error = new WebLocator().setText("Sorry, Google doesn't recognize that email. ");

        //LOGGER.debug(error.getText());

        errorElement.assertExists();
    }

    @Test
    public void twoBrowsers() throws IOException {
        driver.get("https://gmail.com");
        TextField emailField = new TextField().setPlaceholder("Enter your email");
        emailField.setValue("invalid.email@example.com");

        WebDriver driver2 = WebDriverConfig.getWebDriver(Browser.CHROME);
        driver2.get("https://gmail.com");
        emailField.setValue("2invalid.email@example.com");

        WebDriverConfig.init(driver);

        emailField.setValue("xxx.email@example.com");
        WebDriverConfig.init(driver2);
        driver2.close();

        WebDriverConfig.init(driver);
        emailField.setValue("lucia@example.com");
    }

    @Test
    public void scrollToViewTest(){
        GridsView gridsView = new GridsView();

        gridsView.open();

        WebDriverConfig.switchToLastTab();

        WebLocator headerCt = new WebLocator().setClasses("x-grid-header-ct");
        WebLocator header = new WebLocator(headerCt).setText("Manufacturer");
        WebLocator header2 = new WebLocator(headerCt).setText("Title");
        header2.findElement();
        header.click();

        new Actions(driver).dragAndDrop(header.currentElement, header2.currentElement).perform();
        //.currentElement pentru ca e de tip WebLocator si in functie e nevoie de WebElement
    }

    @Test
    public void scrollTest(){
        GridsView gridsView = new GridsView();
        gridsView.open("Miscellaneous", "Resizable");

        WebDriverConfig.switchToLastTab();

        WebLocator basicPanel = new WebLocator().setId("basic");
        WebLocator resizableEast = new WebLocator(basicPanel).setClasses("x-resizable-handle-east");
        resizableEast.mouseOver();
        new Actions(driver).dragAndDropBy(resizableEast.currentElement, 300, 0).build().perform();
    }

    @Test
    public void iterationThrouElements(){
        PropertiesReader appProperties = new PropertiesReader("src\\test\\resources\\app.properties"); //pentru a citi din fisier
        driver.get(appProperties.getProperty("app.url"));

        WebLocator dataView = new WebLocator().setId("dataview-example");
        WebLocator wrap = new WebLocator(dataView).setClasses("thumb-wrap");
        WebLocator img = new WebLocator(wrap).setTag("img");
        img.ready(); //in locul lui sleep. Se poate apela doar daca elementul e declarat ca WebLocator.
        // .ready was needed for findElements but with last version of Testy findElements waits after element to be visible on page
        LOGGER.debug(img.getSelector().toString());

        for(WebElement image : img.findElements()) {
            String title = image.getAttribute("title");
            LOGGER.debug(title);
        }

        //img.setPosition(3); nu ar merge pentru ca nu imaginea e a treia ci div-ul thumb-wrap
        //img.click();
        wrap.setPosition(3);
        LOGGER.debug(img.getSelector().toString());
        wrap.click();

        //metoda cu nativ
      /*  Utils.sleep(2000);
        List<WebElement> images = driver.findElements(By.cssSelector("#dataview-example img"));

        //for(int i = 0; i < images.size(); i++) {
        //    String title = images.get(i).getAttribute("title");
        //    LOGGER.debug(title);
        //}

        //for-ul are acelasi efect ca cel de sus
        for(WebElement image : images) {
            String title = image.getAttribute("title");
            LOGGER.debug(title);
        }
*/
        /*WebLocator selectElement = new WebLocator().setText(appProperties.getProperty("dataview.select.item"));
        WebLocator wrap2 = new WebLocator().setClasses("thumb-wrap").setChildNodes(selectElement);
        wrap2.click();*/
        WebLocator selectWrap = new WebLocator().setClasses("thumb-wrap").setText(appProperties.getProperty("dataview.select.item"), SearchType.DEEP_CHILD_NODE);
        selectWrap.click();
    }
}
