package ua.com.epam.asserters;

import io.qameta.allure.Step;
import org.testng.Assert;

public class ImportantLetterAsserter {
    @Step("assert message is [{expected}]")
    public void assertMessage(String actual, String expected) {
        Assert.assertEquals(actual, expected,
                String.format("Expected message [%s], but found [%s]", expected, actual));
    }
}
