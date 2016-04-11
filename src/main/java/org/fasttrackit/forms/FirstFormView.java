package org.fasttrackit.forms;

//import com.sdl.selenium.bootstrap.form.CheckBox;
import com.sdl.selenium.bootstrap.form.DatePicker;
import com.sdl.selenium.bootstrap.form.SelectPicker;
import com.sdl.selenium.extjs3.form.Label;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.form.CheckBox;
import com.sdl.selenium.web.table.Cell;
import com.sdl.selenium.web.table.Row;
import com.sdl.selenium.web.table.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstFormView extends WebLocator{
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstFormView.class);

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
        LOGGER.info("Eu astazi mananc {} {}, si maine {} {}.", 2, "mere", 3, "pere");
        /*FirstFormView formView = new FirstFormView();
        System.out.println(formView.withEnterCheckBox2.getSelector());*/

        //generateTDXpath();

    }

    //exemplu cu tabel
    public static void generateTDXpath(){
        //static = nu-i nevoie sa se faca instanta: new...si se apeleaza direct

        WebLocator emailCell = new WebLocator().setText("peterparker@mail.com");
        WebLocator row = new WebLocator().setTag("tr").setChildNodes(emailCell);
        CheckBox select = new CheckBox(row);

        LOGGER.debug(select.getXPath());

        Table table = new Table();
        Row row1 = table.getRow(new Cell(4, "peterparker@mail.com")); //4 = in a cata coloana se afla emailul dat
        CheckBox select1 = new CheckBox(row1);

        LOGGER.debug(select1.getXPath());

    }
}
