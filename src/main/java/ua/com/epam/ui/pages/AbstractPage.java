package ua.com.epam.ui.pages;

import org.openqa.selenium.support.PageFactory;
import ua.com.epam.decorator.PageElementDecorator;
import ua.com.epam.factory.DriverContainer;

public class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(new PageElementDecorator(DriverContainer::getDriver), this);
    }
}
