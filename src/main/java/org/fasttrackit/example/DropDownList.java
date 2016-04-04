package org.fasttrackit.example;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.form.ICombo;

public class DropDownList extends WebLocator implements ICombo{

    public DropDownList() {
        setTag("button");
        setBaseCls("dropdown-toggle");
    }

    @Override
    public boolean select(String value) {
        assertClick();
        WebLocator select = new WebLocator().setClasses("bootstrap-select", "open");
        WebLocator element = new WebLocator(select).setText(value);
        element.assertClick();
        return true; //daca a ajuns aici inseamna ca assert a fost cu succes asa ca e returnt true
    }

    @Override
    public String getValue() {
        return getText().trim();
    }
}
