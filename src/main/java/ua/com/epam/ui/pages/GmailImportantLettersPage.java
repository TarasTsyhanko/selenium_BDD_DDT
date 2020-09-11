package ua.com.epam.ui.pages;

import org.openqa.selenium.support.FindBy;
import ua.com.epam.decorator.elements.PageElementCollection;
import ua.com.epam.decorator.elements.elementimpl.ButtonElement;
import ua.com.epam.decorator.elements.elementimpl.CheckBoxElement;


public class GmailImportantLettersPage extends AbstractPage {
    @FindBy(xpath = "//div[@gh='tl']//div[@role='checkbox']")
    private PageElementCollection<CheckBoxElement> importantLettersCheckBox;

    @FindBy(xpath = "//* [@gh='tm']//*[@class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']")
    private ButtonElement deleteAction;

    public PageElementCollection<CheckBoxElement> getImportantLettersCheckBox() {
        return importantLettersCheckBox;
    }

    public ButtonElement getDeleteAction() {
        return deleteAction;
    }
}
