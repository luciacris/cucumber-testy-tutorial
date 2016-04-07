package org.fasttrackit.example;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;

public class MultiSelectDropDownList extends DropDownList {

    public boolean multiSelect(String... values) {
        assertClick();
        WebLocator select = new WebLocator().setClasses("btn-group", "open");

        for (String value : values) {
            WebLocator element = new WebLocator(select).setText(value, SearchType.CHILD_NODE); //vale e copilul nu el insusi
            element.assertClick();
        }

        WebLocator shadow = new WebLocator().setClasses("dropdown-backdrop");
        shadow.assertClick();//face click pe this
        return true; //daca a ajuns aici inseamna ca assert a fost cu succes asa ca e returnt true
    }
}
