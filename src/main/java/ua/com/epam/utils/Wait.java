package ua.com.epam.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.factory.DriverFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import static ua.com.epam.factory.DriverFactory.IMPLICITLY_TIME;
import static ua.com.epam.constant.ScriptConstants.COMPLETE;
import static ua.com.epam.constant.ScriptConstants.SCRIPT_READY_STATE;

public class Wait {
    private static final int EXPLICITLY_TIME = 60;

    private static <T> T runWithZeroImplicitly(Supplier<T> supplier) {
        DriverFactory.setWait(DriverContainer.getDriver(), 0);
        T element = supplier.get();
        DriverFactory.setWait(DriverContainer.getDriver(), IMPLICITLY_TIME);
        return element;
    }

    public static <T> T until(Function<? super WebDriver, T> isTrue) {
        return runWithZeroImplicitly(() ->
                new WebDriverWait(DriverContainer.getDriver(), EXPLICITLY_TIME)
                        .ignoring(WebDriverException.class)
                        .pollingEvery(Duration.of(1, ChronoUnit.SECONDS))
                        .until(isTrue));
    }

    public static void untilPageToBeLoaded() {
        until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript(SCRIPT_READY_STATE).toString().equals(COMPLETE));
    }

    public static void untilPageToBeToBeRefreshed() {
        DriverContainer.getDriver().navigate().refresh();
        untilPageToBeLoaded();
    }

    public static boolean forUrlContains(String text) {
        return until(ExpectedConditions.urlContains(text));
    }

    public static boolean forUrlToRe(String url) {
        return until(ExpectedConditions.urlToBe(url));
    }

}
