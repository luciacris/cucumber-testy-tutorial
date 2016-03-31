package org.fasttrackit.elements;

import org.fasttrackit.forms.FirstFormView;
import org.fasttrackit.util.TestBase;
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
    }

    private void openPage() {
        //driver.get("file:///C:/app-demo/login.html");
        //driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/login.html");
        driver.get("https://rawgit.com/sdl/Testy/master/src/test/functional/app-demo/bootstrap/index.html");
    }
}
