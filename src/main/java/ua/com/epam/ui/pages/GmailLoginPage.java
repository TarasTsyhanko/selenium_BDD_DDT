package ua.com.epam.ui.pages;

import org.openqa.selenium.support.FindBy;
import ua.com.epam.decorator.elements.elementimpl.ButtonElement;
import ua.com.epam.decorator.elements.elementimpl.InputElement;

public class GmailLoginPage extends AbstractPage {
    @FindBy(id = "identifierId")
    private InputElement loginEInput;
    @FindBy(name = "password")
    private InputElement passwordInput;
    @FindBy(css = "div.VfPpkd-RLmnJb")
    private ButtonElement nextButton;

    public void inputLoginAndClickNext(String login) {
        loginEInput.sendKeys(login);
        nextButton.click();
    }

    public void inputPasswordAndClickNext(String password) {
        passwordInput.waitUntilVisible().sendKeys(password);
        nextButton.actionClick();
    }
}
