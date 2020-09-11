package ua.com.epam.ui.pages;

import org.openqa.selenium.support.FindBy;
import ua.com.epam.decorator.elements.PageElementCollection;
import ua.com.epam.decorator.elements.elementimpl.ButtonElement;
import ua.com.epam.decorator.elements.elementimpl.CheckBoxElement;
import ua.com.epam.decorator.elements.elementimpl.LabelElement;

public class GmailBasePage extends AbstractPage {

    @FindBy(css = "a.gb_D.gb_Ua.gb_i")
    private ButtonElement userIcon;

    @FindBy(css = "div.gb_ub.gb_vb")
    private LabelElement userFullName;

    @FindBy(xpath = "//table[@role='grid']//div[@role='checkbox']")
    private PageElementCollection<CheckBoxElement> listLettersCheckBox;

    @FindBy(css = "span.asa.bjy")
    private ButtonElement letterActionButton;

    @FindBy(xpath = "//*[text()='Позначити як важливе']")
    private ButtonElement markAsImportant;

    @FindBy(css = ".n6 .CJ")
    private ButtonElement extendMenuButton;

    @FindBy(css = ".TN.bzz.aHS-bns .J-Ke.n0")
    private ButtonElement importantLettersButton;


    @FindBy(css = "span.bAq")
    private LabelElement informMessage;

    public ButtonElement getUserIcon() {
        return userIcon;
    }

    public PageElementCollection<CheckBoxElement> getListLettersCheckBox() {
        return listLettersCheckBox;
    }

    public ButtonElement getLetterActionButton() {
        return letterActionButton;
    }

    public ButtonElement getMarkAsImportant() {
        return markAsImportant;
    }

    public ButtonElement getExtendMenuButton() {
        return extendMenuButton;
    }

    public ButtonElement getImportantLettersButton() {
        return importantLettersButton;
    }

    public LabelElement getInformMessage() {
        return informMessage;
    }

    public LabelElement getUserName() {
        return userFullName;
    }
}
