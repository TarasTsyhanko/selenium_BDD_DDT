package ua.com.epam.decorator.elements;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.utils.Wait;

import static ua.com.epam.constant.ScriptConstants.SCRIPT_SCROLL_TO_ELEMENT;

@Log4j2
public abstract class PageElement {
    protected WebElement webElement;
    protected By locator;
    private boolean isSingle;

    protected PageElement(WebElement element, By by) {
        this.webElement = element;
        this.locator = by;
    }

    protected WebElement getElement() {
     //   if (isSingle) {
            return DriverContainer.getDriver().findElement(locator);
     //   }
      //  return webElement;
    }

    public void actionClick() {
        log.info("action click to element : " + locator.toString());
        Actions action = new Actions(DriverContainer.getDriver());
        action.click(getElement()).build().perform();
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public String getAttribute(String atr) {
        return getElement().getAttribute(atr);
    }

    public boolean isDisplayed() {
        log.info("is element displayed : " + locator.toString());
        return getElement().isDisplayed();
    }

    public PageElement scrollToElement() {
        log.info("scroll to element : " + locator.toString());
        ((JavascriptExecutor) DriverContainer.getDriver())
                .executeScript(SCRIPT_SCROLL_TO_ELEMENT, getElement());
        return this;
    }

    public boolean waitUntilStalenessOf() {
        log.info("wait until element StalenessOf : " + locator.toString());
        return Wait.until(ExpectedConditions.stalenessOf(webElement));
    }

    public String getText() {
        log.info("get text from element : " + locator.toString());
        return getElement().getText();
    }

    public String getTagName() {
        return getElement().getTagName();
    }

    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    public Point getLocation() {
        return getElement().getLocation();
    }

    public Dimension getSize() {
        return getElement().getSize();
    }

    public Rectangle getRect() {
        return getElement().getRect();
    }


    public String getCssValue(String s) {
        return getElement().getCssValue(s);
    }

    public <T extends PageElement> PageElement waitUntilVisible() {
        log.info("wait until element visible : " + locator.toString());
        Wait.until(ExpectedConditions.visibilityOf(webElement));
        return this;
    }

    public PageElement waitUntilClickable() {
        log.info("wait until element clickable : " + locator.toString());
        webElement = Wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return this;
    }

    public PageElement waitUntilPresent() {
        log.info("wait until element present : " + locator.toString());
        webElement = Wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this;
    }

    public boolean waitUntilTextPresent(String text) {
        log.info("wait until text present in element : " + locator.toString());
        return Wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public boolean waitUntilAttributeToBe(String attribute, String value) {
        log.info(String.format("wait until attribute [%s] be [%s] in element : %s", attribute, value, locator.toString()));
        return Wait.until(ExpectedConditions.attributeToBe(webElement, attribute, value));
    }
}
