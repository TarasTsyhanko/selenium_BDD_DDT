package ua.com.epam.decorator.elements.elementimpl;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.decorator.elements.PageElement;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.utils.Wait;

import static ua.com.epam.constant.ScriptConstants.SCRIPT_CLICK;

@Log4j2
public class ButtonElement extends PageElement {
    public ButtonElement(WebElement element, By by) {
        super(element, by);
    }

    public void click() {
        log.info("click to element : " + locator.toString());
        getElement().click();
    }

    public void scriptClick() {
        log.info("script click to element : " + locator.toString());
        JavascriptExecutor executor = (JavascriptExecutor) DriverContainer.getDriver();
        executor.executeScript(SCRIPT_CLICK, webElement);
    }

    public ButtonElement waitUntilVisible() {
        super.waitUntilVisible();
        return this;
    }

    public ButtonElement waitUntilClickable() {
        super.waitUntilClickable();
        return this;
    }

    public ButtonElement waitUntilPresent() {
        super.waitUntilPresent();
        return this;
    }
}
