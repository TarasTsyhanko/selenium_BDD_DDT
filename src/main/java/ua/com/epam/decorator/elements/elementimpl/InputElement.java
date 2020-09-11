package ua.com.epam.decorator.elements.elementimpl;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.decorator.elements.PageElement;
import ua.com.epam.utils.Wait;

@Log4j2
public class InputElement extends PageElement {
    public InputElement(WebElement element, By by) {
        super(element, by);
    }

    public void sendKeys(CharSequence... keys) {
        getElement().sendKeys(keys);
    }

    public void clearAndSendKeys(CharSequence... keys) {
        getElement().clear();
        getElement().sendKeys(keys);
    }

    public void clear() {
        log.info("clear element : " + locator.toString());
        getElement().clear();
    }

    public void submit() {
        getElement().submit();
    }

    public InputElement waitUntilVisible() {
        super.waitUntilVisible();
        return this;
    }

    public InputElement waitUntilClickable() {
        super.waitUntilClickable();
        return this;
    }

    public InputElement waitUntilPresent() {
        super.waitUntilPresent();
        return this;
    }
}
