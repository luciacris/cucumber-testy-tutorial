package org.fasttrackit.example;

import com.sdl.selenium.web.WebLocator;

public class FormTitleView {
    private WebLocator calendar = new WebLocator().setTag("div").setClasses("date").setId("dp3");
    private WebLocator calendarIcon = new WebLocator(calendar).setClasses("icon-calendar");

    public static void main(String[] args) {
        FormTitleView v = new FormTitleView();
        System.out.println(v.calendarIcon.getXPath());
    }
}
