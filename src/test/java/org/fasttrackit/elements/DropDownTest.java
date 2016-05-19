package org.fasttrackit.elements;

import org.fasttrackit.example.DropDownList;
import org.fasttrackit.example.MultiSelectDropDownList;
import org.fasttrackit.util.LoggedInTestBase;
import org.testng.annotations.Test;

public class DropDownTest extends LoggedInTestBase {
    @Test
    public void dropDownTest(){
        DropDownList downList = new DropDownList().setLabel("Tech:");
        downList.select("Manual");

        DropDownList executeDownList = new DropDownList().setLabel("Execute");
        executeDownList.select("No");

        MultiSelectDropDownList soursDownList = new MultiSelectDropDownList().setLabel("Source:");
        soursDownList.multiSelect("Tomatoes", "Mushrooms");
    }
}
