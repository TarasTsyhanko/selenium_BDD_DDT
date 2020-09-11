package ua.com.epam.ddt.gmailtest;

import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.com.epam.asserters.ImportantLetterAsserter;
import ua.com.epam.ui.actions.ImportantLettersAction;
import ua.com.epam.ui.actions.LoginAction;
import ua.com.epam.config.ConfigProperties;
import ua.com.epam.constant.URLConstants;
import ua.com.epam.models.User;
import ua.com.epam.utils.FileManager;
import ua.com.epam.utils.readers.CSVFileReader;

import static ua.com.epam.constant.MessageConstant.*;

public class ImportantLettersTest extends BaseTest {
    @Inject
    private ImportantLettersAction importantListAction;
    @Inject
    private ImportantLetterAsserter letterAsserter;
    @Inject
    private LoginAction loginAction;

    @Test(description = "mark messages like important and delete it ", dataProvider = "users")
    public void markMessagesLikeImportant(String login, String password, String n) {
        loginAction.logInToGmailAccount(login, password);
        Assert.assertTrue(loginAction.isBasePage(URLConstants.BASE_PAGE_URL));

        importantListAction.waitLetterToBeLoaded(Integer.parseInt(n));
        importantListAction.moveNLettersToImportantList(Integer.parseInt(n));
        Assert.assertTrue(importantListAction.isDisplayedMessage());
        letterAsserter.assertMessage(importantListAction.getMessageText(), SUCCESSFUL_MOVING_MESSAGE);

        importantListAction.openImportantLetterList();
        importantListAction.markNImportantLettersAndDelete(Integer.parseInt(n));
        Assert.assertTrue(importantListAction.isDisplayedMessage());
        letterAsserter.assertMessage(importantListAction.getMessageText(), SUCCESSFUL_DELETION_MESSAGE);
    }


    /**
     * use csv file for dataProvider
     */
    @DataProvider()
    public Object[][] users() {
        return CSVFileReader.readUsers();
    }
}
