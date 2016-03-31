package org.fasttrackit.forms;

//import com.sdl.selenium.bootstrap.form.CheckBox;
import com.sdl.selenium.bootstrap.form.DatePicker;
import com.sdl.selenium.bootstrap.form.SelectPicker;
import com.sdl.selenium.extjs3.form.Label;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.form.CheckBox;

public class FirstFormView extends WebLocator{

    public FirstFormView() {
        setTag("form");

        WebLocator legend = new WebLocator().setText("Form Title");
        setChildNodes(legend);
    }

    public DatePicker datePicker = new DatePicker(this); //este definit deja o componenta care se ocupa cu calendarul
    public WebLocator selectCalendar = new WebLocator(this).setClasses("icon-calendar");
    public SelectPicker selectTech = new SelectPicker(this).setLabel("Tech:");

    private WebLocator stopProcessContainer = new WebLocator(this).setTag("div").setPosition(3);
    public CheckBox stopProcessCheckBox = new CheckBox(stopProcessContainer);

    private WebLocator withEnterContainer = new WebLocator(this).setTag("div").setPosition(4);
    public CheckBox withEnterCheckBox = new CheckBox(withEnterContainer);
    public WebLocator withEnterLabel = new WebLocator(this).setTag("label").setText("Label with Enter.", SearchType.TRIM, SearchType.CHILD_NODE);
    public CheckBox withEnterCheckBox2 = new CheckBox(withEnterLabel);

    public static void main(String[] args) {
        FirstFormView formView = new FirstFormView();
        System.out.println(formView.withEnterCheckBox2.getSelector());
    }
}
