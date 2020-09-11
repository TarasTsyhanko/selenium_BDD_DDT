package ua.com.epam.decorator.elements.elementimpl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ua.com.epam.decorator.elements.PageElement;

public class LabelElement extends PageElement {
    public LabelElement(WebElement element, By by) {
        super(element, by);
    }
}
