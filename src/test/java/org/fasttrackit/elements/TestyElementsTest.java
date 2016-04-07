package org.fasttrackit.elements;

import com.sdl.selenium.bootstrap.button.UploadFile;
import com.sdl.selenium.bootstrap.form.Form;
import com.sdl.selenium.bootstrap.form.MultiSelect;
import com.sdl.selenium.bootstrap.form.SelectPicker;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.PropertiesReader;
import org.fasttrackit.example.DropDownList;
import org.fasttrackit.example.MultiSelectDropDownList;
import org.fasttrackit.forms.FirstFormView;
import org.fasttrackit.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;


public class TestyElementsTest extends TestBase{

    @Test
    public void checkBoxTest(){
        openPage();

        FirstFormView form = new FirstFormView();
        //form.assertReady();
        //form.selectCalendar.assertClick(); //se poate face direct click pe el pentru ca s-a declarat public. Daca era privat trebuia facut o metoda care facea click.
        form.datePicker.select("25/02/2016", "dd/MM/yyyy", Locale.ENGLISH);
        form.selectTech.select("Manual");
        form.stopProcessCheckBox.assertClick();
        form.withEnterCheckBox.assertClick();
        form.withEnterLabel.assertClick();
        form.withEnterCheckBox2.assertClick();
    }

    @Test
    public void slectTest(){
        SelectPicker picker = new SelectPicker().setLabel("Tech:");

        openPage();

        String v = picker.getValue();
        Assert.assertEquals(v, "Auto");
        picker.select("Manual");
        v = picker.getValue();
        Assert.assertEquals(v, "Manual");
    }

    @Test
    public void dropDownTest(){
        openPage();

        DropDownList downList = new DropDownList().setLabel("Teach:");
        downList.select("Manual");

        DropDownList executeDownList = new DropDownList().setLabel("Execute");
        executeDownList.select("No");

        MultiSelectDropDownList soursDownList = new MultiSelectDropDownList().setLabel("Source:");
        soursDownList.multiSelect("Tomatoes", "Mushrooms");


    }

    private Form form = new Form("Form Title");
    private UploadFile uploadBtn = new UploadFile(form, "TPT Test:");

    @Test
    public void uploadTest(){
        openPage();
        System.out.println(PropertiesReader.RESOURCES_DIRECTORY_PATH);
        uploadBtn.upload("Select file", new String[]{
                PropertiesReader.RESOURCES_DIRECTORY_PATH +"\\drivers\\upload.exe",
                PropertiesReader.RESOURCES_DIRECTORY_PATH +"\\feature\\login\\login.feature"
        });

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
}
