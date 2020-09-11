package ua.com.epam.decorator.elements.elementimpl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBoxElement extends ButtonElement {
    public CheckBoxElement(WebElement element, By by) {
        super(element, by);
    }

    public void setCheck(boolean value) {
        if (value != isSelected()) {
            click();
        }
    }

    public boolean isSelected() {
        return getElement().isSelected();
    }
}
