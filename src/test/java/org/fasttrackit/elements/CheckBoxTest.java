package org.fasttrackit.elements;

import com.sdl.selenium.bootstrap.form.SelectPicker;
import com.sdl.selenium.web.WebLocator;
import org.fasttrackit.example.ChangePasswordView;
import org.fasttrackit.example.NavigationBarPage;
import org.fasttrackit.forms.FirstFormView;
import org.fasttrackit.util.LoggedInTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

public class CheckBoxTest extends LoggedInTestBase {

    @Test
    public void checkBoxTest(){

        FirstFormView form = new FirstFormView();
        //form.assertReady();
        //form.selectCalendar.assertClick(); //se poate face direct click pe el pentru ca s-a declarat public. Daca era privat trebuia facut o metoda care facea click.
        form.datePicker.select("25/02/2016", "dd/MM/yyyy", Locale.ENGLISH);
        form.selectTech.select("Manual");
        form.stopProcessCheckBox.click();
        form.withEnterCheckBox.click();
        form.withEnterLabel.click();
        form.withEnterCheckBox2.click();

        NavigationBarPage.PREFERENCES.click();

        //posible error
        WebLocator x = new WebLocator().setText("xxxx");
        x.click(); //aici va pica, dar pentru ca exista refresh in TestBase, urmatorul test nu va cadea

        ChangePasswordView changePasswordPage = new ChangePasswordView();
        changePasswordPage.close();

    }

    @Test
    public void slectTest(){
        SelectPicker picker = new SelectPicker().setLabel("Tech:");

        String v = picker.getValue();
        Assert.assertEquals(v, "Auto");
        picker.select("Manual");
        v = picker.getValue();
        Assert.assertEquals(v, "Manual");
    }

}
